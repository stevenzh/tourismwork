package com.opentravelsoft.providers.hibernate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.LockMode;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.TourCost;
import com.opentravelsoft.entity.finance.Outcome;
import com.opentravelsoft.entity.product.Remind;

import com.opentravelsoft.providers.OutcomeDao;
import com.opentravelsoft.util.Arith;
import com.opentravelsoft.util.RowDataUtil;
import com.opentravelsoft.util.StringUtil;

@Repository("OutcomeDao")
public class OutcomeDaoHibernate extends GenericDaoHibernate<Outcome, Long>
    implements OutcomeDao {

  public OutcomeDaoHibernate() {
    super(Outcome.class);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.opentravelsoft.providers.OutcomeDao#getOwedList(long,
   * java.lang.String)
   */
  @SuppressWarnings("unchecked")
  public List<TourCost> getOwedList(long customerId, String tourNo) {
    HibernateTemplate template = getHibernateTemplate();
    StringBuilder sb = new StringBuilder();
    sb.append("from TourCost ");
    sb.append("where payAmount<amount and customer.customerId=? ");
    if (StringUtil.hasLength(tourNo))
      sb.append("and tourNo='" + tourNo + "' ");
    sb.append("order by customer.customerId ");
    Object[] param = { customerId };
    return template.find(sb.toString(), param);
  }

  @SuppressWarnings("unchecked")
  public List<TourCost> getSupplierOutcomeList(String supplierType,
      String customerName, String countryId, String cityId, Date startDate,
      Date endDate) {
    HibernateTemplate template = getHibernateTemplate();
    StringBuilder sql = new StringBuilder();
    sql.append("from TourCost ");
    sql.append("where tour.outDate>=? and tour.outDate<=? ");
    // sql.append("and opAccount='Y' ");

    if (StringUtil.hasLength(supplierType)) {
      sql.append("and customer.resource = '" + supplierType + "' ");
    }
    if (StringUtil.hasLength(countryId)) {
      sql.append("and customer.countryCd = '" + countryId + "' ");
    }
    if (StringUtil.hasLength(cityId)) {
      sql.append("and customer.city.citycd = '" + cityId + "' ");
    }
    if (StringUtil.hasLength(customerName)) {
      sql.append("and customer.name like '%" + customerName + "%' ");
    }
    sql.append("order by tour.outDate, tour.planNo ");

    Object[] param2 = { startDate, endDate };
    return template.find(sql.toString(), param2);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.opentravelsoft.providers.OutcomeDao#getOutcomeDetail(long,
   * java.util.Date, java.util.Date)
   */
  @SuppressWarnings("unchecked")
  public List<TourCost> findBill(long customerId, Date startDate, Date endDate) {
    HibernateTemplate template = getHibernateTemplate();

    StringBuilder sql = new StringBuilder();
    sql.append("from TourCost ");
    sql.append("where customer.customerId=? ");
    // sql.append("and opAccount='Y' ");
    sql.append("and tour.outDate>=? and tour.outDate<=? ");

    Object[] param1 = { customerId, startDate, endDate };
    return template.find(sql.toString(), param1);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.opentravelsoft.providers.OutcomeDao#saveBillhead(com.opentravelsoft
   * .ebiz.entity.finance.Outcome, java.lang.String)
   */
  public long saveBill(Outcome bill, String audit) {
    Outcome tblOutcome = new Outcome();
    if (bill.getOutcomeId() > 0) {
      tblOutcome = (Outcome) getHibernateTemplate().get(Outcome.class,
          bill.getOutcomeId(), LockMode.UPGRADE);
    }

    if (null == tblOutcome)
      return -1;

    tblOutcome.setCustomer(bill.getCustomer());
    tblOutcome.setAmount(bill.getAmount());
    tblOutcome.setNote(bill.getNote());
    Date sysdate = getSysdate();
    if (bill.getOutcomeId() <= 0) {
      tblOutcome.setCreatedBy(bill.getCreatedBy());
    }
    tblOutcome.setPayMode(bill.getPayMode());
    tblOutcome.setUpdatedBy(bill.getUpdatedBy());
    tblOutcome.setOpApprovedFlag('N');
    if (audit.equals("true")) {
      tblOutcome.setOpApprovedFlag('Y');
      tblOutcome.setOpApproved(sysdate);
      tblOutcome.setOpApprovedby(bill.getOpApprovedby());
    }
    tblOutcome.setFrReadFlag('N');
    tblOutcome.setFrApprovedFlag('N');
    // 只对单团时
    tblOutcome.setTourNo(bill.getTourNo());
    // 是否安排配送
    tblOutcome.setCarryTicket(bill.getCarryTicket());
    //
    if (bill.getCarryLastDate() != null)
      tblOutcome.setCarryLastdate(bill.getCarryLastDate());
    //
    tblOutcome.setCarryNote(bill.getCarryNote());
    tblOutcome.setCarryStatus((short) 0);

    // tblOutcome.setAmount(paynotice.getNowpayPayment());
    // tblOutcome.setTourNo(paynotice.getTourNo());
    // tblOutcome.setCurrency(paynotice.getCurrency());
    // tblOutcome.setFcAmount(paynotice.getFcNowpayPayment());
    // // OP结算汇率
    // tblOutcome.setOpRoe(paynotice.getOpRoe());
    // // 财务结算汇率
    // tblOutcome.setRoe(paynotice.getOpRoe());
    // tblOutcome.setNote(paynotice.getNote());
    getHibernateTemplate().save(tblOutcome);

    return tblOutcome.getOutcomeId();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.opentravelsoft.providers.OutcomeDao#deleteBillhead(long)
   */
  public int cancelBill(long outcomeId) {
    HibernateTemplate template = getHibernateTemplate();
    StringBuilder sb = new StringBuilder();
    sb.append("update Outcome set del='Y' ");
    sb.append("where outcomeId = ?");
    Object[] params = { outcomeId };
    template.bulkUpdate(sb.toString(), params);

    // 单团核算成本审核复位
    sb = new StringBuilder();
    sb.append("update TourCost set frChecked='N' ");
    sb.append("where planNo in (");
    sb.append("select acctId from Outcome ");
    sb.append("where outcomeId = ?");
    sb.append(')');
    template.bulkUpdate(sb.toString(), params);

    return 0;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.opentravelsoft.providers.OutcomeDao#getBillheadList(long,
   * java.util.Date, java.util.Date, java.util.Date, java.util.Date, boolean,
   * java.lang.String, java.lang.String, java.util.Date, java.util.Date)
   */
  @SuppressWarnings("unchecked")
  public List<Outcome> getBillList(long userId, Date startDate, Date endDate,
      Date startOutDate, Date endOutDate, boolean opAudited, String frAudit,
      String kenPay, Date frStartDate, Date frEndDate) {
    StringBuilder sql = new StringBuilder();
    List<Object> params = new ArrayList<Object>();
    sql.append("from Outcome where del='N' ");
    if (opAudited)
      sql.append("and opApprovedFlag = 'Y' ");

    if (frAudit.equals("Y"))
      sql.append("and frApprovedFlag = 'Y' ");
    else if (frAudit.equals("N"))
      sql.append("and frApprovedFlag = 'N' ");

    // 是否付款
    if (kenPay.equals("Y"))
      sql.append("and payRegisterDate is not null ");
    else if (kenPay.equals("N"))
      sql.append("and payRegisterDate is null ");

    if (userId != 0) {
      sql.append("and createdBy=? ");
      params.add(userId);
    }

    // 申请书创建时间
    if (null != startDate) {
      sql.append("and created>=? ");
      params.add(startDate);
    }
    if (null != endDate) {
      sql.append("and created<=? ");
      params.add(endDate);
    }

    // 团出发时间
    if (null != startOutDate) {
      sql.append("and tour.outDate>=? ");
      params.add(startOutDate);
    }
    if (null != endOutDate) {
      sql.append("and tour.outDate<=? ");
      params.add(endOutDate);
    }

    // 提交到财务时间
    if (null != frStartDate) {
      sql.append("and opApproved>=? ");
      params.add(frStartDate);
    }
    if (null != frEndDate) {
      sql.append("and opApproved<=? ");
      params.add(frEndDate);
    }
    sql.append("order by created ");
    Object[] param = null;
    if (params.size() > 0) {
      param = new Object[params.size()];
      for (int i = 0; i < params.size(); i++) {
        param[i] = params.get(i);
      }
    }
    return getHibernateTemplate().find(sql.toString(), param);
  }

  @SuppressWarnings("unchecked")
  public List<Outcome> getBillList(long userId, Date startDate, Date endDate,
      String register) {
    StringBuilder sql = new StringBuilder();
    List<Object> params = new ArrayList<Object>();
    sql.append("from Outcome a,");
    sql.append("Employee c ");
    sql.append("where a.createdBy=c.userCd and a.opApprovedFlag='Y' ");
    sql.append("and a.frApprovedFlag='Y' ");
    if (register.equals("N")) {
      sql.append("and a.payRegisterDate is null ");
    } else if (register.equals("Y")) {
      sql.append("and a.payRegisterDate is not null ");
    }

    if (userId != 0) {
      sql.append("and a.createdBy=? ");
      params.add(userId);
    }
    if (null != startDate) {
      sql.append("and a.created>=? ");
      params.add(startDate);
    }
    if (null != endDate) {
      sql.append("and a.created<=? ");
      params.add(endDate);
    }

    sql.append("order by a.created  ");
    Object[] param = null;
    if (params.size() > 0) {
      param = new Object[params.size()];
      for (int i = 0; i < params.size(); i++) {
        param[i] = params.get(i);
      }
    }
    List<Object[]> outcomeList = getHibernateTemplate().find(sql.toString(),
        param);
    List<Outcome> billheadList = new ArrayList<Outcome>();

    return billheadList;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.opentravelsoft.providers.OutcomeDao#auditingBillhead(long,
   * long)
   */
  public int auditingBill(long outcomeId, long uid) {
    HibernateTemplate template = getHibernateTemplate();
    Outcome outcome = (Outcome) template.get(Outcome.class, outcomeId);
    TourCost tblCostAcct = null;
    if (null != outcome) {
      Date sysdate = getSysdate();
      outcome.setFrApproved(sysdate);
      outcome.setFrApprovedby(uid);
      outcome.setFrApprovedFlag('Y');
      outcome.setFrReadby(uid);
      outcome.setFrRead(sysdate);
      outcome.setFrReadFlag('Y');
      template.update(outcome);

      tblCostAcct = (TourCost) template
          .get(TourCost.class, outcome.getAcctId());
      if (null != tblCostAcct) {
        // 标记为账务已审核
        tblCostAcct.setFrChecked("Y");
        template.update(tblCostAcct);
      }

      return 0;
    }
    return -1;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.opentravelsoft.providers.OutcomeDao#opApproved(long, long)
   */
  public int opApproved(long outcomeId, long uid) {
    HibernateTemplate template = getHibernateTemplate();
    Outcome tblOutcome = (Outcome) template.get(Outcome.class, outcomeId);
    if (null != tblOutcome) {
      Date sysdate = getSysdate();
      tblOutcome.setOpApproved(sysdate);
      tblOutcome.setOpApprovedby(uid);
      tblOutcome.setOpApprovedFlag('Y');
      tblOutcome.setFrReadFlag('N');
      template.update(tblOutcome);

      return 0;
    }

    return -1;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.opentravelsoft.providers.OutcomeDao#frReadBillhead(com.opentravelsoft
   * .ebiz.entity.finance.Outcome, long)
   */
  public int frReadBill(Outcome outcome, long uid) {
    HibernateTemplate template = getHibernateTemplate();
    Outcome tblOutcome = (Outcome) template.get(Outcome.class,
        outcome.getOutcomeId());
    if (null != tblOutcome) {
      Date sysdate = getSysdate();
      tblOutcome.setFrReadby(uid);
      tblOutcome.setFrRead(sysdate);
      tblOutcome.setFrReadFlag('Y');

      template.update(tblOutcome);

      return 0;
    }

    return -1;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.opentravelsoft.providers.OutcomeDao#frModifyBillhead(com.
   * opentravelsoft.ebiz.entity.finance.Outcome, long)
   */
  public int frModifyBill(Outcome outcome, long uid) {
    HibernateTemplate template = getHibernateTemplate();
    Outcome tblOutcome = (Outcome) template.get(Outcome.class,
        outcome.getOutcomeId());
    if (null != tblOutcome) {
      tblOutcome.setAmount(outcome.getAmount());
      tblOutcome.setPayMode(outcome.getPayMode());
      tblOutcome.setNote(outcome.getNote());
      // 是否安排配送
      tblOutcome.setCarryTicket(outcome.getCarryTicket());
      if (outcome.getCarryLastDate() != null)
        tblOutcome.setCarryLastdate(outcome.getCarryLastDate());
      tblOutcome.setCarryNote(outcome.getCarryNote());
      tblOutcome.setUpdatedBy(uid);

      // tblOutcome.setRoe(billheadItem.getRoe());
      // tblOutcome.setFcAmount(billheadItem.getFcNowpayPayment());
      // tblOutcome.setAmount(billheadItem.getNowpayPayment());

      template.update(tblOutcome);

      return 0;
    }

    return -1;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.opentravelsoft.providers.OutcomeDao#factualRegister(int[],
   * long, java.lang.String, java.util.Date)
   */
  @SuppressWarnings("unchecked")
  public int factualRegister(int[] outcomeIds, long uid, String billNo,
      Date payDate) {
    StringBuilder sql = new StringBuilder();
    sql.append("from Outcome ");
    sql.append("where outcomeId in ( ");

    for (int i = 0; i < outcomeIds.length; i++) {
      sql.append(outcomeIds[i] + ",");
    }

    List<Outcome> outcomeList = getHibernateTemplate().find(
        sql.substring(0, sql.length() - 1) + ")");

    if (!(outcomeList.isEmpty())) {

      sql = new StringBuilder();
      sql.append("from TourCost ");
      sql.append("where acctId in ( ");

      for (int i = 0; i < outcomeList.size(); i++) {
        sql.append(outcomeList.get(i).getAcctId() + ",");
      }

      List<TourCost> costList = getHibernateTemplate().find(
          sql.substring(0, sql.length() - 1) + ")");

      double payAmount = 0;
      for (TourCost cost : costList) {
        for (Outcome outcome : outcomeList) {
          if (outcome.getAcctId() == cost.getAcctId()) {
            payAmount = RowDataUtil.getDouble(cost.getPayAmount());
            payAmount += RowDataUtil.getDouble(outcome.getAmount());
            cost.setPayAmount(payAmount);
          }
          break;
        }
      }

      getHibernateTemplate().saveOrUpdateAll(costList);

      sql = new StringBuilder();
      sql.append("update Outcome ");
      sql.append("set payRegisterby=? ");
      sql.append("where outcomeId in ( ");

      for (int i = 0; i < outcomeIds.length; i++) {
        sql.append(outcomeIds[i] + ",");
      }

      Object[] params = { uid };
      getHibernateTemplate().bulkUpdate(
          sql.substring(0, sql.length() - 1) + ")", params);

      return 0;
    } else
      return -1;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.opentravelsoft.providers.OutcomeDao#poModifyBillhead(com.
   * opentravelsoft.ebiz.entity.finance.Outcome, long)
   */
  public int opModifyBill(Outcome outcome, long uid) {
    HibernateTemplate template = getHibernateTemplate();
    Outcome tblOutcome = (Outcome) template.get(Outcome.class,
        outcome.getOutcomeId());
    if (null != tblOutcome) {
      tblOutcome.setAmount(outcome.getAmount());
      tblOutcome.setPayMode(outcome.getPayMode());
      tblOutcome.setNote(outcome.getNote());
      // 是否安排配送
      // tblOutcome.setCarryTicket(outcome.getCarryTicket());
      if (outcome.getCarryLastDate() != null)
        tblOutcome.setCarryLastdate(outcome.getCarryLastDate());
      tblOutcome.setCarryNote(outcome.getCarryNote());
      tblOutcome.setUpdatedBy(uid);
      // tblOutcome.setTourNo(outcome.getTourNo());
      // tblOutcome.setAmount(billheadItem.getNowpayPayment());
      // tblOutcome.setFcAmount(billheadItem.getFcNowpayPayment());

      template.update(tblOutcome);
      return 0;
    }

    return -1;
  }

  @SuppressWarnings("unchecked")
  public int isAuciting(String tourNo, double amount) {
    HibernateTemplate template = getHibernateTemplate();
    StringBuilder sql = new StringBuilder();
    sql.append("select b.customerId,b.name ");
    sql.append("from Booking a, Customer b ");
    sql.append("where a.plan.tourNo=? and a.customerId=b.customerId ");
    sql.append("and b.payment='N' and a.dbamt>a.cramt ");
    // warrantFlag='Y'即此订单已有人担保
    sql.append("and (a.warrantFlag is null or a.warrantFlag<>'Y') ");
    sql.append("and a.delkey='N' ");

    List<Object[]> list = template.find(sql.toString(), tourNo);

    // 如果存在现结客户未付清且无人担保时,当此团的已收款不小于本次申请款加上已审核的付款,可以通过审核
    if (!list.isEmpty()) {
      sql = new StringBuilder();
      sql.append("select distinct(nameNo) ");
      sql.append("from Booking ");
      sql.append("where plan.tourNo=? and delkey='N' ");
      Object[] params1 = { tourNo };
      List<String> nameNoList = getHibernateTemplate().find(sql.toString(),
          params1);
      if (!nameNoList.isEmpty()) {
        sql = new StringBuilder();
        sql.append("from Booking  ");
        sql.append("where nameNo in (");
        for (String objects : nameNoList) {
          sql.append("'" + RowDataUtil.getString(objects) + "',");
        }

        List<Booking> tfj006List = getHibernateTemplate().find(
            sql.substring(0, sql.length() - 1) + ")");
        // 应收
        double dbamt = 0.0;
        // 已收
        double cramt = 0.0;
        for (Booking tfj006 : tfj006List) {
          dbamt = Arith.add(dbamt, RowDataUtil.getDouble(tfj006.getDbamt()));
          cramt = Arith.add(cramt, RowDataUtil.getDouble(tfj006.getCramt()));
        }

        sql = new StringBuilder();
        sql.append("from Outcome ");
        sql.append("where tour.planNo=? and frApprovedFlag='Y' ");

        List<Outcome> tblOutcomeList = getHibernateTemplate().find(
            sql.toString(), tourNo);
        // 已付
        double yiAmount = 0.0;
        for (Outcome tblOutcome : tblOutcomeList) {
          yiAmount = Arith.add(yiAmount,
              RowDataUtil.getDouble(tblOutcome.getAmount()));
        }
        // 加上本次要付的款
        yiAmount = Arith.add(yiAmount, amount);
        if (yiAmount > cramt)
          return -1;
        else
          return 0;
      }
    }

    return 0;
  }

  @SuppressWarnings("unchecked")
  public List<Plan> getTourList(long teamId, long userId, long customerId) {
    StringBuilder sql = new StringBuilder();

    sql.append("select distinct plan.tourNo,plan.outDate,plan.line.lineNo,");
    sql.append("plan.line.lineName ");
    sql.append("from " + Booking.class.getName());
    sql.append(" where plan.team.teamId=" + teamId + " ");
    if (userId != 0)
      sql.append("and plan.assigned.userId=" + userId + " ");
    if (customerId != 0)
      sql.append("and customerId=" + customerId + " ");

    sql.append("and plan.outDate>=current_date() ");
    sql.append("order by plan.outDate");

    List<Plan> planList = new ArrayList<Plan>();
    List<Object[]> list = getHibernateTemplate().find(sql.toString());

    Plan plan = null;
    for (Object[] obj : list) {
      plan = new Plan();
      plan.setTourNo(RowDataUtil.getString(obj[0]));
      plan.setOutDate(RowDataUtil.getDate(obj[1]));
      plan.getLine().setLineNo(RowDataUtil.getString(obj[2]));
      plan.getLine().setLineName(RowDataUtil.getString(obj[2]));

      planList.add(plan);
    }

    return planList;
  }

  // -------------------------------------------------------------------------

  @SuppressWarnings("unchecked")
  public List<Remind> getOutcomeInBand(int daynum) {
    // 国内应付 (未读付款申请, 团出发前n天未付款申请)
    // 根据单团核算
    // 团号，线路名 供应商名 金额

    StringBuilder sql = new StringBuilder();
    Calendar cal = Calendar.getInstance();
    cal.setTime(new Date());
    cal.add(Calendar.DAY_OF_MONTH, -daynum);

    sql.append("select tour.tourNo,tour.line.lineName,tour.outDate,");
    sql.append("customer.customerId,customer.name,customer.resource,");
    sql.append("amount,payAmount,customer.payment ");
    sql.append("from " + TourCost.class.getName());
    sql.append(" where tour.outDate<=? ");
    // sql.append("and tour.outDate >='2011-01-01' ");

    Object[] params = { cal.getTime() };
    List<Object[]> list = getHibernateTemplate().find(sql.toString(), params);
    List<Remind> reminds = new ArrayList<Remind>();

    for (Object[] obj : list) {
      if (null != obj) {
        Remind remind = new Remind();
        remind.setTourNo(RowDataUtil.getString(obj[0]));
        remind.setRouteName(RowDataUtil.getString(obj[1]));
        remind.setCustomerName(RowDataUtil.getString(obj[4]));
        remind.setExpense(RowDataUtil.getDouble(obj[6])
            - RowDataUtil.getDouble(obj[7]));
        reminds.add(remind);
      }
    }

    return reminds;
  }

  @SuppressWarnings("unchecked")
  public List<Remind> getBillheadInBand() {
    // 国内应付 (未读付款申请, 团出发前n天未付款申请)
    // 根据单团核算
    // 团号，线路名 供应商名 金额
    StringBuilder sql = new StringBuilder();

    sql.append("select outcomeId,tour.tourNo,tour.outDate,");
    sql.append("customer.customerId,customer.name,");
    sql.append("customer.resource,customer.payment,");
    sql.append("amount,tour.line.lineName ");
    sql.append("from " + Outcome.class.getName());
    sql.append(" where opApprovedFlag='Y' ");
    sql.append("and frReadFlag='N' and frApprovedFlag='N' ");
    sql.append("and tour.outDate<=current_date() ");

    List<Object[]> list = getHibernateTemplate().find(sql.toString());
    List<Remind> reminds = new ArrayList<Remind>();

    for (Object[] obj : list) {
      if (null != obj) {
        Remind remind = new Remind();
        remind.setOutcomeId(RowDataUtil.getInt(obj[0]));
        remind.setTourNo(RowDataUtil.getString(obj[1]));
        remind.setCustomerName(RowDataUtil.getString(obj[4]));
        remind.setExpense(RowDataUtil.getDouble(obj[7]));
        remind.setRouteName(RowDataUtil.getString(obj[8]));

        reminds.add(remind);
      }
    }

    return reminds;
  }

  public int opModifyPayReturn(long outcomeId) {
    // 付款申请书置位(打回计调操作)
    HibernateTemplate template = getHibernateTemplate();
    Outcome tblOutcome = (Outcome) template.get(Outcome.class, outcomeId);
    if (null != tblOutcome) {
      tblOutcome.setOpApprovedFlag('N');
      tblOutcome.setFrReadFlag('N');
      tblOutcome.setFrApprovedFlag('N');

      template.update(tblOutcome);

      // -----------------------------------------------
      // 单团核算成本审核复位
      StringBuilder sb = new StringBuilder();
      Object[] p1 = { tblOutcome.getOutcomeId() };
      sb.append("update TourCost set frChecked='N' ");
      sb.append("where acctId in (");
      sb.append("select acctId from Outcome ");
      sb.append("where tblOutcome.outcomeId = ?");
      sb.append(')');
      template.bulkUpdate(sb.toString(), p1);

      return 0;
    } else {
      return -1;
    }
  }

  // --------------------------------------------------------------------------
  @SuppressWarnings("unchecked")
  public List<Outcome> listParcels(String supplierId, long userId,
      short carryStatus) {
    StringBuilder sql = new StringBuilder();

    sql.append("select a.outcomeId,a.customer.customerId,a.customer.name,");
    sql.append("a.amount,a.createdBy,a.created,c.userName,");
    sql.append("a.payRegisterDate,a.frApproved,a.frApprovedFlag,");
    sql.append("a.carryStatus,a.carryComplete,a.carryStart,");
    sql.append("d.line.lineNo,d.line.lineName,d.tourNo ");
    sql.append("from Outcome a, Employee c, Plan d ");
    sql.append("where a.createdBy=c.userCd and a.planNo=d.planNo ");
    sql.append("and a.opApprovedFlag='Y' and a.carryTicket=1 ");
    // sql.append("and a.frApprovedFlag='Y' ");
    sql.append("and a.carryStatus=" + carryStatus + " ");

    if (StringUtil.hasLength(supplierId))
      sql.append("and a.customer.customerId=" + supplierId + " ");
    if (userId != 0)
      sql.append("and a.createdBy=" + userId + " ");

    sql.append("order by a.carryLastdate  ");

    List<Object[]> outcomeList = getHibernateTemplate().find(sql.toString());
    List<Outcome> billheadList = new ArrayList<Outcome>();
    Outcome billhead = null;
    for (Object[] obj : outcomeList) {
      billhead = new Outcome();
      billhead.setOutcomeId(RowDataUtil.getInt(obj[0]));
      billhead.getCustomer().setCustomerId(RowDataUtil.getInt(obj[1]));
      billhead.getCustomer().setName(RowDataUtil.getString(obj[2]));
      billhead.setAmount(RowDataUtil.getDouble(obj[3]));
      billhead.setCreatedBy(RowDataUtil.getLong(obj[4]));
      billhead.setCreated(RowDataUtil.getDate(obj[5]));
      billhead.setCreatedByName(RowDataUtil.getString(obj[6]));
      billhead.setPayRegisterDate(RowDataUtil.getDate(obj[7]));
      billhead.setFrApproved(RowDataUtil.getDate(obj[8]));
      billhead.setFrApprovedFlag(RowDataUtil.getChar(obj[9]));

      billhead.setCarryStatus(RowDataUtil.getShort(obj[10]));
      billhead.setCarryComplete(RowDataUtil.getDate(obj[11]));
      billhead.setCarryStart(RowDataUtil.getDate(obj[12]));

      // 单团
      billhead.setRouteNo(RowDataUtil.getString(obj[13]));
      billhead.setRouteName(RowDataUtil.getString(obj[14]));
      billhead.setTourNo(RowDataUtil.getString(obj[15]));

      billheadList.add(billhead);
    }

    return billheadList;
  }

  public int startParcel(Outcome billhead) {
    Outcome outcome = (Outcome) getHibernateTemplate().get(Outcome.class,
        billhead.getOutcomeId());

    if (outcome != null) {
      Date sysdate = getSysdate();
      outcome.setCarryStart(sysdate);
      outcome.setCarryStatus((short) 1);
      outcome.setCarryWorker(billhead.getCarryWorker());
      outcome.setCarryWorkday(billhead.getCarryWorkday());
      outcome.setCarryNote(billhead.getCarryNote());

      getHibernateTemplate().update(outcome);
    }

    return 0;
  }

  public int completeParcel(Outcome billhead) {
    Outcome outcome = (Outcome) getHibernateTemplate().get(Outcome.class,
        billhead.getOutcomeId());

    if (outcome != null) {
      Date sysdate = getSysdate();
      outcome.setCarryComplete(sysdate);
      outcome.setCarryStatus((short) 3);
      outcome.setCarryNote(billhead.getCarryNote());

      getHibernateTemplate().update(outcome);
    }

    return 0;
  }

}
