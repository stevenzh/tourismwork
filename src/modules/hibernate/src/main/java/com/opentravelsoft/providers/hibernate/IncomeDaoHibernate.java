package com.opentravelsoft.providers.hibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.opentravelsoft.util.LabelValueBean;
import org.hibernate.LockMode;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.opentravelsoft.EbizException;
import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.Customer;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Lists;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.TblUserPayOL;
import com.opentravelsoft.entity.Tourist;
import com.opentravelsoft.entity.finance.Income;
import com.opentravelsoft.entity.product.Remind;
import com.opentravelsoft.entity.product.Warrant;
import com.opentravelsoft.providers.IncomeDao;
import com.opentravelsoft.util.RowDataUtil;
import com.opentravelsoft.util.StringUtil;

/**
 * 收款账单
 * 
 * @author zhangst
 */
@Repository("IncomeDao")
public class IncomeDaoHibernate extends GenericDaoHibernate<Income, Integer>
    implements IncomeDao {

  public IncomeDaoHibernate() {
    super(Income.class);
  }

  @SuppressWarnings("unchecked")
  public List<Booking> getIncomeBookings(int customerId) {
    StringBuilder sb = new StringBuilder();
    sb.append("from Booking ");
    sb.append("where customer.customerId=? and dbamt<>cramt ");
    sb.append("and cfmKey='1' and delkey='N' ");
    sb.append("order by reserveDate ");
    Object[] param = { customerId };
    return getHibernateTemplate().find(sb.toString(), param);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.opentravelsoft.providers.IncomeDao#saveIncome(com.opentravelsoft
   * .ebiz.entity.finance.Income)
   */
  public int saveIncome(Income gathering) {
    // 更新订单收款
    Booking book = getHibernateTemplate().get(Booking.class,
        gathering.getBookingNo());
    // 保存交款内容
    gathering.setCustomer(book.getCustomer());
    getHibernateTemplate().save(gathering);

    if (book != null) {
      book.setCramt(book.getPayCosts().add(book.getPayBack()));
      getHibernateTemplate().update(book);
    }

    return gathering.getIncomeId();
  }

  @SuppressWarnings("unchecked")
  public List<Booking> searchIncome(String provinceCd, String cityCd,
      Integer customerId, Date stDate, Date endDate) {
    StringBuilder sb = new StringBuilder();
    List<Object> params = new ArrayList<Object>();
    sb.append("from Booking ");
    sb.append("where dbamt<>cramt ");

    if (StringUtil.hasLength(provinceCd)) {
      sb.append("and customer.provinceCd = ? ");
      params.add(provinceCd);
    }
    if (StringUtil.hasLength(cityCd)) {
      sb.append("and customer.city.citycd = ? ");
      params.add(cityCd);
    }
    if (customerId != 0) {
      sb.append("and customer.customerId = ? ");
      params.add(customerId);
    }
    if (stDate != null) {
      sb.append("and plan.outDate >= ? ");
      params.add(stDate);
    }
    if (endDate != null) {
      sb.append("and plan.outDate <= ? ");
      params.add(endDate);
    }
    sb.append("order by plan.outDate, plan.planNo");
    Object[] param = null;
    if (params.size() > 0) {
      param = new Object[params.size()];
      for (int i = 0; i < params.size(); i++) {
        param[i] = params.get(i);
      }
    }
    return getHibernateTemplate().find(sb.toString(), param);
  }

  @SuppressWarnings("unchecked")
  public List<Income> getGatheringList(Integer customerId,
      Date paymentDateStart, Date paymentDateEnd, double payGatherStart,
      double payGatherEnd) {
    HibernateTemplate template = getHibernateTemplate();
    List<Object> params = new ArrayList<Object>();
    StringBuilder sql = new StringBuilder();
    sql.append("from Income where customer.customerId=? ");
    params.add(customerId);
    if (null != paymentDateStart) {
      sql.append("and incomeDate>=? ");
      params.add(paymentDateStart);
    }
    if (null != paymentDateEnd) {
      sql.append("and incomeDate<=? ");
      params.add(paymentDateEnd);
    }
    if (payGatherStart != 0 || payGatherEnd != 0) {
      if (payGatherStart <= payGatherEnd && payGatherStart != 0) {
        // payGatherStart<=payGatherEnd时,搜索在payGatherStart与payGatherEnd之间的项
        sql.append("and amount>=? ");
        params.add(payGatherStart);
        sql.append("and amount<=? ");
        params.add(payGatherEnd);
      } else if (payGatherStart > payGatherEnd) {
        // payGatherStart>payGatherEnd时,搜索大于payGatherStart的项
        sql.append("and amount>=? ");
        params.add(payGatherStart);
      } else if (payGatherStart == 0 && payGatherEnd > 0) {
        // payGatherStart为零时,搜索等于payGatherEnd的项
        sql.append("and amount=? ");
        params.add(payGatherEnd);
      } else if (payGatherStart > 0 && payGatherEnd == 0) {
        // payGatherEnd为零时,搜索等于payGatherStart的项
        sql.append("and amount=? ");
        params.add(payGatherStart);
      }

    }
    sql.append("order by incomeDate ");
    Object[] param = null;
    if (params.size() > 0) {
      param = new Object[params.size()];
      for (int i = 0; i < params.size(); i++) {
        param[i] = params.get(i);
      }
    }

    List<Income> tblIncomeList = template.find(sql.toString(), param);
    for (Income obj : tblIncomeList) {
      obj.setUnOffSetMon(obj.getAmount().subtract(obj.getOffSetAmount()));
      if (obj.getNote().equals(""))
        obj.setNote(obj.getIncomeDate().toString());
    }

    return tblIncomeList;
  }

  @SuppressWarnings("unchecked")
  public List<Income> findIncome(Integer teamId, String customerId,
      Date startDate, Date endDate, double startMon, double endMon) {
    StringBuilder sb = new StringBuilder();
    List<Object> params = new ArrayList<Object>();
    sb.append("from Income ");
    sb.append("where 1=1 ");

    // if (StringUtil.hasLength(teamId))
    // {
    // sb.append("and a.teamId = ? ");
    // params.add(teamId);
    // }

    if (StringUtil.hasLength(customerId)) {
      sb.append("and customer.customerId=" + customerId + " ");
    }
    // 收款日期
    if (startDate != null) {
      sb.append("and incomeDate >= ? ");
      params.add(startDate);
    }
    // 收款日期
    if (endDate != null) {
      sb.append("and incomeDate <= ? ");
      params.add(endDate);
    }
    // 收款金额
    if (startMon > 0) {
      sb.append("and amount >= ? ");
      params.add(startMon);
    }
    // 收款金额
    if (endMon > 0) {
      sb.append("and amount <= ? ");
      params.add(endMon);
    }
    sb.append("order by incomeDate");
    Object[] param = null;
    if (params.size() > 0) {
      param = new Object[params.size()];
      for (int i = 0; i < params.size(); i++) {
        param[i] = params.get(i);
      }
    }

    List<Income> incomeList = getHibernateTemplate().find(sb.toString(), param);
    return incomeList;
  }

  public int cancelIncome(int incomeId) {
    Income tblIncome = getHibernateTemplate().get(Income.class, incomeId);
    if (null == tblIncome) {
      return -1;
    }
    Booking tfj006 = (Booking) getHibernateTemplate().get(Booking.class,
        tblIncome.getBookingNo(), LockMode.PESSIMISTIC_WRITE);

    tfj006.setCramt(tfj006.getCramt().subtract(tblIncome.getAmount()));
    getHibernateTemplate().update(tfj006);

    tblIncome.setDel("Y");
    getHibernateTemplate().update(tblIncome);
    return 0;
  }

  @SuppressWarnings("unchecked")
  public Income getGathering(int incomeId) {
    HibernateTemplate template = getHibernateTemplate();
    StringBuilder sql = new StringBuilder();
    sql.append("select a.incomeId,a.customer.customerId,b.name,a.payMode,");
    sql.append("a.note,a.incomeDate,a.amount,a.offSetAmount,");
    sql.append("c.dbamt,c.cramt,c.pax,cb.plan.tourNo, ");
    sql.append("c.plan.line.lineNo,c.plan.line.lineName,c.plan.outDate");
    sql.append("from Income a,");
    sql.append("Booking c ");
    sql.append("where a.bookingNo=c.bookingNo and a.incomeId=? ");
    List<Object[]> list = template.find(sql.toString(), incomeId);

    if (!list.isEmpty()) {
      Income gathering = new Income();
      Object[] objG = list.get(0);
      gathering.setIncomeId(RowDataUtil.getInt(objG[0]));
      gathering.getCustomer().setCustomerId(RowDataUtil.getInt(objG[1]));
      gathering.getCustomer().setName(RowDataUtil.getString(objG[2]));
      gathering.setPayMode(RowDataUtil.getString(objG[3]));
      gathering.setNote(RowDataUtil.getString(objG[4]));
      gathering.setIncomeDate(RowDataUtil.getDate(objG[5]));
      gathering.setAmount(RowDataUtil.getBigDecimal(objG[6]));
      gathering.setOffSetAmount(RowDataUtil.getBigDecimal(objG[7]));
      gathering.setUnOffSetMon(gathering.getAmount().subtract(
          gathering.getOffSetAmount()));

      // book.setFinalExpense(RowDataUtil.getDouble(obj[0]));
      // book.setPayCosts(RowDataUtil.getDouble(obj[1]));
      // book.setUnPay(book.getFinalExpense() - book.getPayCosts());
      // book.setPayBack(RowDataUtil.getDouble(obj[2]));
      // book.setPax(RowDataUtil.getInt(obj[3]));
      //
      //
      // book.getPlan().setTourNo(RowDataUtil.getString(obj[4]));
      // book.getPlan().getLine().setLineNo(RowDataUtil.getString(obj[5]));
      // book.getPlan().getLine().setLineName(RowDataUtil.getString(obj[6]));
      // book.getPlan().setOutDate(RowDataUtil.getDate(obj[7]));
      //
      // gathering.setBookList(bookList);
      // gathering.setFinalExpense(finalExpense);
      // gathering.setPayCosts(payCosts);
      // gathering.setUnPay(finalExpense - payCosts);
      // gathering.setPayBack(payBack);
      // gathering.setPax(pax);

      return gathering;
    }

    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.opentravelsoft.providers.IncomeDao#updateIncome(com.opentravelsoft
   * .ebiz.entity.finance.Income)
   */
  public int updateIncome(Income gather) {
    Income tblIncome = getHibernateTemplate().get(Income.class,
        gather.getIncomeId());
    if (tblIncome == null) {
      return -1;
    }

    // 更新订单
    Booking tfj006 = (Booking) getHibernateTemplate().get(Booking.class,
        gather.getBookingNo(), LockMode.PESSIMISTIC_WRITE);
    tfj006.setCramt(tfj006.getCramt().subtract(gather.getAmount())
        .add(tfj006.getPayBack()));
    gather.setAmount(tfj006.getPayBack());
    getHibernateTemplate().update(tfj006);

    tblIncome.setPayMode(gather.getPayMode());
    tblIncome.setIncomeDate(gather.getIncomeDate());
    tblIncome.setAmount(gather.getAmount());
    tblIncome.setOffSetAmount(gather.getOffSetAmount());
    tblIncome.setNote(gather.getNote());
    tblIncome.setReceiver(gather.getReceiver());
    tblIncome.setUpdatedBy(gather.getUpdateBy());
    getHibernateTemplate().update(tblIncome);

    return 0;
  }

  @SuppressWarnings("unchecked")
  public List<Booking> getUnpayList(int incomeId) {
    StringBuilder sb = new StringBuilder();
    sb.append("select a.plan.tourNo,a.plan.outDate,a.pax,");
    sb.append("a.salesman.userId,a.dbamt,a.cramt,a.plan.line.lineName,");
    sb.append("a.nameNo,a.customer.name,c.amount ");
    sb.append("from Booking a ");
    sb.append("Income c ");
    sb.append("where c.bookingNo=a.nameNo and c.incomeId=? ");
    sb.append("and a.cfmKey='1' and a.delkey='N' ");
    sb.append("order by a.reserveDate ");
    Object[] param = { incomeId };
    List<Booking> books = new ArrayList<Booking>();
    List<Object[]> list = getHibernateTemplate().find(sb.toString(), param);
    Booking book = null;
    if (list != null && list.size() > 0) {
      for (Object[] obj : list) {
        book = new Booking();
        book.getPlan().setTourNo(RowDataUtil.getString(obj[0]));
        book.getPlan().setOutDate(RowDataUtil.getDate(obj[1]));
        book.setPax(RowDataUtil.getInt(obj[2]));
        book.setSalesman(new Employee(RowDataUtil.getInt(obj[3])));
        book.setDbamt(RowDataUtil.getBigDecimal(obj[4]));
        book.setPayCosts(RowDataUtil.getBigDecimal(obj[5]));
        book.setUnPay(RowDataUtil.getBigDecimal(obj[4]).subtract(
            RowDataUtil.getBigDecimal(obj[5])));
        book.getPlan().getLine().setLineName(RowDataUtil.getString(obj[6]));
        book.setBookingNo(RowDataUtil.getString(obj[7]));
        book.getCustomer().setName(RowDataUtil.getString(obj[8]));
        book.setPayBack(RowDataUtil.getBigDecimal(obj[9]));
        books.add(book);
      }
    }
    return books;
  }

  // -------------------------------------------------------------------------

  @SuppressWarnings("unchecked")
  public List<Remind> getIncomeInBand(int daynum) {
    Date sysdate = getSysdate();
    Calendar cal = Calendar.getInstance();

    cal.setTime(sysdate);
    cal.add(Calendar.DAY_OF_MONTH, -daynum);

    // 国内线路 现结客户 %月结客户不作处理
    StringBuilder sb = new StringBuilder();

    sb.append("from " + Booking.class.getName());
    sb.append(" where dbamt<>cramt ");
    sb.append("and customer.payment='N' and plan.outDate <= ? ");
    // sb.append("and id.outDate >= '2008-07-01' ");
    sb.append("order by plan.outDate ");

    Object[] params = { cal.getTime() };
    List<Remind> books = new ArrayList<Remind>();
    List<Booking> list = getHibernateTemplate().find(sb.toString(), params);

    for (Booking alert : list) {
      Remind book = new Remind();
      book.setBookingNo(alert.getBookingNo());
      book.setTourNo(alert.getPlan().getTourNo());
      book.setOutDate(alert.getPlan().getOutDate());
      book.setRouteName(alert.getPlan().getLine().getLineName());
      book.setExpense(RowDataUtil.getDouble(alert.getDbamt())
          - RowDataUtil.getDouble(alert.getCramt()));
    }
    return books;
  }

  @SuppressWarnings("unchecked")
  public List<Customer> getCusByTour(String TourNo) {
    StringBuilder sb = new StringBuilder();
    sb.append("SELECT customer.customerId,customer.name ");
    sb.append("FROM Booking ");
    sb.append("WHERE plan.tourNo = ?");
    Object[] param = { TourNo };
    List<Customer> list = new ArrayList<Customer>();
    Customer agent = null;
    List<Object[]> cusList = getHibernateTemplate().find(sb.toString(), param);
    for (Object[] obj : cusList) {
      agent = new Customer();
      agent.setCustomerId(RowDataUtil.getInt(obj[0]));
      agent.setName(RowDataUtil.getString(obj[1]));
      list.add(agent);
    }
    return list;
  }

  @SuppressWarnings("unchecked")
  public int warrantSubmit(int customerId, String tourNo, Warrant warrant) {
    StringBuilder sb = new StringBuilder();
    sb.append("FROM Booking ");
    sb.append("WHERE customer.customerId=? and plan.tourNo = ? ");
    Object[] param = { customerId, tourNo };
    List<Booking> tfj006List = getHibernateTemplate()
        .find(sb.toString(), param);
    if (tfj006List != null && tfj006List.size() > 0) {
      for (Booking tfj006 : tfj006List) {
        tfj006.setWarrantFlag("Y");
        tfj006.setWarrantBy(warrant.getWarrantBy());
        tfj006.setWarrantMoney(warrant.getWarratMoney());
        tfj006.setWarrantDate(warrant.getWarrantDate());
        tfj006.setLastPayDate(warrant.getLastPayDate());
        getHibernateTemplate().update(tfj006);
      }
    }
    return 0;
  }

  @SuppressWarnings("unchecked")
  public List<Income> getPayments(String bookingNo) {
    List<Income> result = new ArrayList<Income>();
    StringBuilder sb = new StringBuilder();
    sb.append("select a.bookingNo,c.text,a.amount,a.useType,a.incomeDate,a.note ");
    sb.append("from Income a, Lists c ");
    sb.append("where a.payMode=c.value ");
    sb.append("and a.bookingNo=? and c.listName='PaymentMethod' ");
    Object[] params = { bookingNo };

    List<Object[]> list = getHibernateTemplate().find(sb.toString(), params);

    for (Object[] obj : list) {
      Income pay = new Income();

      // 付款单NO
      pay.setBookingNo(RowDataUtil.getString(obj[0]));
      // 付款方式
      pay.setPayMode(RowDataUtil.getString(obj[1]));
      // 付款金额
      pay.setAmount(RowDataUtil.getBigDecimal(obj[2]));
      // 付款类别（0：定金 1：预付款...）
      pay.setUseType(RowDataUtil.getString(obj[3]));
      // 操作时间
      pay.setIncomeDate(RowDataUtil.getDate(obj[4]));
      // 备注
      pay.setNote(RowDataUtil.getString(obj[5]));
      result.add(pay);
    }
    return result;
  }

  @SuppressWarnings("unchecked")
  public List<LabelValueBean> getPaymentTypes() {
    List<LabelValueBean> result = new ArrayList<LabelValueBean>();
    StringBuilder sb = new StringBuilder();
    sb.append("from Lists where listName='PaymentMethod' ");
    sb.append("order by value");

    List<Lists> list = getHibernateTemplate().find(sb.toString());

    for (Lists obj : list) {
      result.add(new LabelValueBean(RowDataUtil.getString(obj.getValue()),
          RowDataUtil.getString(obj.getText())));
    }
    return result;
  }

  @SuppressWarnings("unchecked")
  public int netPay(String orderId, String paymentMode, BigDecimal amount,
      String moneyType, String paymentNo, String invNo) throws EbizException {
    HibernateTemplate template = getHibernateTemplate();

    // ---------------------------------------------------------------------
    // 更新 报名单索引表（TFJ006） 预订人数 确认人数 已交款 确认状态
    Booking tfj006 = (Booking) template.load(Booking.class, orderId,
        LockMode.PESSIMISTIC_WRITE);
    if (null == tfj006)
      throw new EbizException("报名单索引表(TFJ006)记录错误.");

    // ---------------------------------------------------------------------
    //
    Plan plan = (Plan) template.load(Plan.class, tfj006.getPlan().getPlanNo(),
        LockMode.PESSIMISTIC_WRITE);
    if (null == plan)
      throw new EbizException("线路计划表(TBL_PLAN)记录错误.");

    // ---------------------------------------------------------------------
    // 写TFJ007中的付款金额
    StringBuilder sql = new StringBuilder();
    Object[] params = { orderId };
    sql.append("from Tourist where booking.nameNo=? ");
    List<Tourist> list = template.find(sql.toString(), params);
    int pax = list.size();

    if (tfj006.getCfmKey().equals("2") && plan.getPax3() - pax > 0) {
      // 已定人数
      plan.setPax2(RowDataUtil.getShort(plan.getPax2()) + pax);
      // 可订人数
      plan.setPax3(RowDataUtil.getShort(plan.getPax3()) - pax);
      // 更新计划
      template.update(plan);

      // 预订人数
      tfj006.setPax(pax);
      // 确认人数
      tfj006.setConfirmPax(pax);
      // 团队确认状态 1-团确 2-团候
      tfj006.setCfmKey("1");
    } else
      throw new EbizException("名额不足(TBL_PLAN)记录错误.");

    // ---------------------------------------------------------------------
    // 已交款
    tfj006.setCramt(tfj006.getCramt().subtract(amount));
    // 客户收款登记
    tfj006.setCramt(tfj006.getCramt().subtract(amount));
    // 最后修改人
    tfj006.setOpuser(0);

    template.update(tfj006);

    return 0;
  }

  /** 加入付款信息 */
  public int insertPayer(TblUserPayOL payer) {
    getHibernateTemplate().save(payer);
    return -1;
  }
}
