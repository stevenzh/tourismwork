package com.opentravelsoft.providers.hibernate;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.hibernate.LockMode;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.Guide;
import com.opentravelsoft.entity.Pinyin;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.TourCost;
import com.opentravelsoft.entity.TourOutBound;
import com.opentravelsoft.entity.Tourist;
import com.opentravelsoft.entity.product.Leader;
import com.opentravelsoft.providers.PlanDao;
import com.opentravelsoft.util.RowDataUtil;
import com.opentravelsoft.util.StringUtil;

@Repository("TourDao")
public class PlanDaoHibernate extends GenericDaoHibernate<Plan, String>
    implements PlanDao {

  public PlanDaoHibernate() {
    super(Plan.class);
  }

  @SuppressWarnings("unchecked")
  public Plan getTourInfo(String tourNo, boolean actor, boolean cost) {
    HibernateTemplate template = getHibernateTemplate();
    SimpleDateFormat SDF = new SimpleDateFormat("yyyy");
    StringBuilder sql = new StringBuilder();
    sql.append("from Plan where tourNo=? ");
    Object[] params = { tourNo };

    List<Plan> list = template.find(sql.toString(), params);
    Plan tour = null;
    if (list.size() > 0) {
      tour = list.get(0);
      tour.setDay(RowDataUtil.getInt(tour.get_day()));
      tour.setDoubleRoom(RowDataUtil.getInt(tour.getDbrm()));
      tour.setSingleRoom(RowDataUtil.getInt(tour.getSgrm()));
      tour.setExtraBedRoom(RowDataUtil.getInt(tour.getAdrm()));
      // 应收
      tour.setMuAmount(tour.getAmount());
      // 已收
      tour.setAlAmount(tour.getAramt());
      // 未收
      tour.setWiAmount(tour.getAmount().subtract(tour.getAramt()));

      tour.setYear(SDF.format(tour.getOutDate()));

      Object[] param = { tour.getPlanNo() };
      // 是否包含客人名单主
      if (actor) {
        // 查找客人名单
        sql = new StringBuilder();
        sql.append("from Tourist where del='N' and booking.plan.tourNo=? ");
        sql.append("order by booking.nameNo ");

        List<Tourist> list1 = template.find(sql.toString(), params);

        int idx = 1;
        for (Tourist trip : list1) {
          trip.setNumber(idx++);
          if (null != trip.getBirthday()) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(tour.getOutDate());
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            cal.setTime(RowDataUtil.getDate(trip.getBirthday()));
            trip.setAge(year - cal.get(Calendar.YEAR) - 1);
            if (month > cal.get(Calendar.MONTH)
                || month == cal.get(Calendar.MONTH)
                && day > cal.get(Calendar.DAY_OF_MONTH)) {
              trip.setAge(trip.getAge() + 1);
            }
            if (trip.getAge() < 12)
              trip.setChild("CHD");
          }

          if (trip.getLeaderKey().equals("Y")) {
            trip.setLeaderKey("T/L");
          } else {
            trip.setLeaderKey("");
          }
        }

        tour.setCustomerList(list1);
      }

      if (cost) {
        tour.setExtrIncome(tour.getExtCost());
        tour.setExtrIncomeDec(RowDataUtil.getString(tour.getExtCostNote()));
        sql = new StringBuilder();
        sql.append("from TourCost where tour.planNo=? ");
        sql.append("order by frChecked desc ");

        List<TourCost> costList = template.find(sql.toString(), param);
        tour.setCostList(costList);
      }
    }

    return tour;
  }

  @SuppressWarnings("unchecked")
  public List<Plan> getToursAndCustomer(String[] tourNos) {
    String tourString = new String();
    for (int i = 0; i < tourNos.length; i++)
      tourString = tourString + "," + tourNos[i];
    String[] tours = tourString.split(",");

    StringBuilder sql;
    HibernateTemplate template = getHibernateTemplate();
    sql = new StringBuilder();
    sql.append("from Plan where tourNo in (");
    for (int i = 0; i < tours.length; i++) {
      if (!("".equals(tours[i].trim()) || tours[i].trim() == null))
        sql.append("'" + tours[i].trim() + "',");
    }

    List<Plan> planList = template.find(sql.substring(0, sql.length() - 1)
        + ")");
    Object param;
    List<Plan> tourList = new ArrayList<Plan>();
    for (Plan tour : planList) {
      sql = new StringBuilder();
      sql.append("from Tourist where booking.plan.tourNo=? and del='N' ");
      param = tour.getTourNo();
      List<Tourist> tfj007List = template.find(sql.toString(), param);
      for (Tourist trip : tfj007List) {
        trip.setRealName(trip.getUserName());
        trip.setStauts(0);
      }
      tour.setCustomerList(tfj007List);

      tour.setDoubleRoom(RowDataUtil.getInt(tour.getDbrm()));
      tour.setSingleRoom(RowDataUtil.getInt(tour.getSgrm()));
      tour.setExtraBedRoom(RowDataUtil.getInt(tour.getAdrm()));

      tourList.add(tour);
    }

    return tourList;
  }

  public int cancelTour(String tourNo, String note, int userId) {
    // 取消团
    StringBuilder sb = new StringBuilder();
    sb.append("update Plan set delKey='Y' where tourNo=?");
    Object[] param = { tourNo };
    getHibernateTemplate().bulkUpdate(sb.toString(), param);

    return 0;
  }

  @SuppressWarnings("unchecked")
  public int saveTour(Plan tour, String note) {
    // 如团信息中所登记的人数与实际客人不符，更新团信息
    // tourNo 旧团号
    HibernateTemplate template = getHibernateTemplate();
    Date sysdate = getSysdate();

    // 通过团号查询团并修改基本信息
    Object[] params = { tour.getPlanNo() };
    Plan plan = (Plan) template.get(Plan.class, tour.getPlanNo(),
        LockMode.PESSIMISTIC_WRITE);
    if (null == plan)
      return -1;

    // --------------------------------------------------------------------
    // 设置新团号
    plan.setTourNo(tour.getTourNo());
    plan.setOutDate(tour.getOutDate());
    plan.setInDate(tour.getInDate());
    plan.setInCity(tour.getInCity());
    plan.setVenue(tour.getVenue());
    plan.setDbrm(tour.getDoubleRoom());
    plan.setSgrm(tour.getSingleRoom());
    plan.setAdrm(tour.getExtraBedRoom());
    plan.setRemarks(tour.getRemarks());
    // 是否需要安排领队
    plan.setIsNeedLeader(tour.getIsNeedLeader());
    // ---------------------------------------------------------------------
    // 查询所有客户并更新客户总数和男女数
    StringBuilder sb3 = new StringBuilder();
    sb3.append("from Tourist where booking.plan.planNo=? and del<>'Y'");

    List<Tourist> list = template.find(sb3.toString(), params);
    // 总人数
    int pax = list.size();
    // 男性人数
    int malePax = 0;
    // 女性人数
    int femalePax = 0;
    // 领队人数
    int leadPax = 0;
    for (Tourist tfj007 : list) {
      if (tfj007.getSex().equals("M"))
        malePax++;
      if (tfj007.getSex().equals("F"))
        femalePax++;
      if (tfj007.getLeaderKey().equals("Y"))
        leadPax++;
    }
    plan.setPax(pax);
    plan.setMalePax(malePax);
    plan.setFemalePax(femalePax);
    plan.setLeaderPax(leadPax);

    // 新加入名单后，根据名单的应收修改团款
    StringBuilder sb4 = new StringBuilder();
    sb4.append("from Booking where plan.planNo=? ");
    // 应收款
    BigDecimal dbamt = new BigDecimal(0);
    // 已收款
    BigDecimal cramt = new BigDecimal(0);
    List<Booking> tfj006List = template.find(sb4.toString(), params);
    for (Booking tfj006 : tfj006List) {
      dbamt = dbamt.add(tfj006.getDbamt());
      cramt = cramt.add(tfj006.getCramt());
    }

    plan.setOptime2(sysdate);
    plan.setOpuser2(tour.getOpUser());
    plan.setAmount(dbamt);
    plan.setAramt(cramt);

    template.update(plan);
    return 0;
  }

  // -------------------------------------------------------------------------

  @SuppressWarnings("unchecked")
  public List<TourOutBound> getOutBandObjectList(String tourNo, String type) {
    // 取境外报团打印对象
    HibernateTemplate template = getHibernateTemplate();
    SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
    StringBuilder sql = new StringBuilder();
    sql.append("from TourOutBound where planNo=? ");
    if (null != type && !type.equals(""))
      sql.append("and type = '" + type + "' ");

    List<TourOutBound> trJ039List = template.find(sql.toString(), tourNo);
    List<TourOutBound> OutBandList = new ArrayList<TourOutBound>();
    TourOutBound outBandObject;
    for (TourOutBound obj : trJ039List) {
      outBandObject = new TourOutBound();
      outBandObject.setTourNo(RowDataUtil.getString(obj.getTourNo()));
      outBandObject.setText1(RowDataUtil.getString(obj.getText1()));
      outBandObject.setText2(RowDataUtil.getString(obj.getText2()));
      outBandObject.setText3(RowDataUtil.getString(obj.getText3()));
      outBandObject.setType(RowDataUtil.getString(obj.getType()));
      outBandObject.setOpuser(RowDataUtil.getInt(obj.getOpuser()));
      outBandObject.setOpdate(RowDataUtil.getDate(obj.getOpdate()));
      outBandObject.setShowStr(outBandObject.getType() + "　"
          + outBandObject.getOpuser() + "　"
          + SDF.format(outBandObject.getOpdate()));
      outBandObject.setShowId(outBandObject.getTourNo() + ":"
          + outBandObject.getType());
      OutBandList.add(outBandObject);
    }

    return OutBandList;
  }

  public void saveOutBandObject(TourOutBound outBandObject) {
    // 保存境外报团打印对象
    HibernateTemplate template = getHibernateTemplate();
    TourOutBound trj039 = (TourOutBound) template.get(TourOutBound.class,
        outBandObject.getId());
    if (null == trj039) {
      trj039 = new TourOutBound();
      trj039.setId(outBandObject.getId());
      trj039.setPlanNo(outBandObject.getPlanNo());
      trj039.setText1(outBandObject.getText1());
      trj039.setText2(outBandObject.getText2());
      trj039.setText3(outBandObject.getText3());
      trj039.setOpuser(outBandObject.getOpuser());
      template.save(trj039);
    }
  }

  @SuppressWarnings("unchecked")
  public List<Tourist> getLeaders(String tourNo) {
    // 取团所对的领队
    HibernateTemplate template = getHibernateTemplate();
    StringBuilder sql = new StringBuilder();
    sql.append("from Tourist ");
    sql.append("where booking.plan.tourNo=? and leaderKey='Y' and del='N' ");

    List<Tourist> leaderList = template.find(sql.toString(), tourNo);
    return leaderList;
  }

  @SuppressWarnings("unchecked")
  public int modifyCustomerInfo(List<Tourist> customerList, String tourNo,
      String note, int userId) {
    // 修改团中客人信息
    HibernateTemplate template = getHibernateTemplate();
    Date sysdate = getSysdate();
    StringBuilder sql = new StringBuilder();
    Object[] params = { tourNo };
    sql.append("from Tourist ");
    sql.append("where booking.plan.tourNo=? ");
    List<Tourist> list = template.find(sql.toString(), params);
    sql = new StringBuilder();
    sql.append("from Plan where tourNo=? ");
    List<Plan> list2 = template.find(sql.toString(), tourNo);

    int femalPax = 0;
    int malePax = 0;
    if (list2.isEmpty())
      return -1;

    Plan tfj012 = list2.get(0);
    for (Tourist tfj007 : list) {
      Tourist trip = null;
      for (int i = 0; i < customerList.size(); i++) {
        if (customerList.get(i).getNmno().equals(tfj007.getNmno()))
          trip = customerList.get(i);
      }
      if (null == trip)
        continue;

      template.lock(tfj007, LockMode.PESSIMISTIC_WRITE);

      // 姓名
      tfj007.setUserName(trip.getUserName());
      // 汉语拼音
      tfj007.setPinYin(trip.getPinYin());
      // 证件号码
      tfj007.setCard(trip.getIdCard());
      // 性别
      tfj007.setSex(trip.getSex());
      if (tfj007.getSex().equals("M"))
        malePax++;
      else
        femalPax++;

      // 出生日期
      tfj007.setBirthday(trip.getBirthday());
      // 出生地
      tfj007.setBirthplace(trip.getBirthplace());
      // 护照号
      tfj007.setPassportNo(trip.getPassportNo());
      // 发照日期
      tfj007.setPassportDate(trip.getPassportDate());
      // 发照地
      tfj007.setPassportPlace(trip.getPassportPlace());
      // 护照有效期
      tfj007.setPassportExpiry(trip.getPassportExpiry());
      // 备注
      tfj007.setRemark(trip.getRemarks());

      template.update(tfj007);
    }
    tfj012.setMalePax(malePax);
    tfj012.setFemalePax(femalPax);
    tfj012.setOpuser2(userId);
    tfj012.setOptime2(sysdate);
    template.update(tfj012);

    return 0;

  }

  @SuppressWarnings("unchecked")
  public List<Booking> getBookList(String tourNo) {
    StringBuilder sql = new StringBuilder();
    sql.append("from Booking where plan.tourNo=? and delkey='N'");
    List<Booking> bookList = getHibernateTemplate()
        .find(sql.toString(), tourNo);

    // 取领队
    sql = new StringBuilder();
    sql.append("select nmno,userName from Tourist ");
    sql.append("where leaderKey='Y' and del='N' and booking.plan.tourNo=? ");

    Object[] params2 = { tourNo };
    List<Object[]> list2 = getHibernateTemplate().find(sql.toString(), params2);

    int i = 0;
    String str = new String();
    if (!list2.isEmpty())
      for (Object[] obj : list2)
        str = str + obj[1] + "　";

    for (Booking book : bookList) {
      book.setId(i++);
      book.setFinalExpense(book.getFinalAmount());
      book.setPayCosts(book.getCramt());
      book.setUnPay(book.getDbamt().add(book.getFinalExpense())
          .subtract(book.getPayCosts()));
      //
      if (book.getCustomer().getPayment().equals('N'))
        book.setClearingCycle("现结客户");
      else if (book.getCustomer().getPayment().equals('M'))
        book.setClearingCycle("月结客户");

      // 确认人数
      book.setPax(RowDataUtil.getInt(book.getConfirmPax()));
      book.setAdjustExpense(book.getFinalAmount().add(book.getDbamt()));

      book.setLastAdjustBy(book.getFinalUser());
      book.setLastAdjustDate(RowDataUtil.getDate(book.getFinalDate()));
      book.setAdjustReason(RowDataUtil.getString(book.getFinalNote()));
    }
    bookList.get(0).setLeaders(str);

    return bookList;
  }

  // -------------------------------------------------------------------------

  public int makeTourAccounts(Plan plan) {
    Plan tour = getHibernateTemplate().get(Plan.class, plan.getPlanNo(),
        LockMode.PESSIMISTIC_WRITE);
    tour.setOpAccount("N");
    tour.setFrChecked("N");
    tour.setOpRefactor("N");
    // 其它收入
    tour.setExtCost(plan.getExtrIncome());
    tour.setExtCostNote(plan.getExtrIncomeDec());
    getHibernateTemplate().update(tour);

    for (int i = 0; i < plan.getCostList().size(); i++) {
      TourCost tourcost = new TourCost();
      tourcost.setTour(plan);
      tourcost.getCustomer().setCustomerId(
          plan.getCostList().get(i).getCustomer().getCustomerId());
      tourcost.setDescription(plan.getCostList().get(i).getDescription());
      tourcost.setUnitPrice(plan.getCostList().get(i).getUnitPrice());
      tourcost.setUnit(plan.getCostList().get(i).getUnit());
      tourcost.setCount(plan.getCostList().get(i).getCount());
      tourcost.setAmount(plan.getCostList().get(i).getAmount());
      tourcost.setCostType(plan.getCostList().get(i).getCostType());
      tourcost.setCreatedBy(plan.getOpUser());
      tourcost.setFrChecked("N");

      // Currency
      tourcost.setCurrency(plan.getCostList().get(i).getCurrency());
      // ROE
      tourcost.setRoe(plan.getCostList().get(i).getRoe());

      getHibernateTemplate().save(tourcost);
    }

    return 0;
  }

  @SuppressWarnings("unchecked")
  public Plan auditTourAccounts(String tourNo, int uid) {
    HibernateTemplate template = getHibernateTemplate();
    Plan tour = getTourInfo(tourNo, false, true);
    List<TourCost> costList = tour.getCostList();
    for (TourCost tblCostAcct : costList) {
      tblCostAcct.setFrChecked("Y");
    }
    template.saveOrUpdateAll(costList);

    // ---------------------------------------------------------------------
    tour.setFrChecked("Y");
    tour.setOpRefactor("N");
    tour.setFrUser(uid);
    tour.setFrDate(getSysdate());

    // 毛利率----------
    BigDecimal blnrate = tour.getTourAmount().subtract(tour.getCost())
        .divide(tour.getTourAmount()).divide(new BigDecimal(100));
    tour.setBlnrate(blnrate);
    // ---------------- 取各订单已交款-----------------------------------------
    StringBuilder sql = new StringBuilder();
    sql.append("from Booking where tourNo=? ");

    Object[] params = { tour.getTourNo() };
    List<Booking> tfj006s = template.find(sql.toString(), params);

    BigDecimal cramt = new BigDecimal(0);
    for (Booking tfj006 : tfj006s) {
      cramt = cramt.add(tfj006.getCramt());
    }

    tour.setAramt(cramt);
    template.update(tour);

    return tour;
  }

  @SuppressWarnings("unchecked")
  public List<Booking> mustPayModify(List<Booking> bookList, int uid) {
    StringBuilder sql = new StringBuilder();
    sql.append("from Booking where nameNo in ( ");

    for (Booking book : bookList) {
      sql.append(" '" + book.getBookingNo() + "' ,");
    }

    List<Booking> bookings = getHibernateTemplate().find(
        sql.substring(0, sql.length() - 1) + ")");
    List<Booking> books = new ArrayList<Booking>();

    Date sysdate = getSysdate();
    BigDecimal allAmount = new BigDecimal(0);

    for (int i = 0; i < bookList.size(); i++)
      for (Booking tfj006 : bookings) {
        Booking book1 = bookList.get(i);
        if (tfj006.getNameNo().equals(book1.getBookingNo())) {
          tfj006.setFinalAmount(book1.getAdjustExpense().subtract(
              book1.getDbamt()));
          tfj006.setFinalUser(uid);
          tfj006.setFinalDate(sysdate);
          tfj006.setFinalNote(book1.getAdjustReason());
          allAmount = allAmount.add(book1.getAdjustExpense());
          book1.setLastAdjustBy(uid);
          book1.setLastAdjustDate(sysdate);
          book1.setIsSuccess(1);
          book1.setId(i);

          getHibernateTemplate().update(tfj006);
          books.add(book1);
          break;
        }
      }

    // 修改团的总收入
    Plan plan = (Plan) getHibernateTemplate().get(Plan.class,
        bookings.get(0).getPlan().getTourNo(), LockMode.PESSIMISTIC_WRITE);
    if (null != plan) {
      // 修改总收入
      plan.setAmount(allAmount);
      // 修改未款
      plan.setCamt03(allAmount.subtract(plan.getAramt()));
      getHibernateTemplate().update(plan);

      // 修改核算单的金额
      sql = new StringBuilder();
      sql.append("from Plan where tourNo=? ");

      Object[] params1 = { plan.getTourNo() };
      List<Plan> tourList = getHibernateTemplate()
          .find(sql.toString(), params1);
      if (null != tourList && !(tourList.isEmpty())) {
        Plan tour = tourList.get(0);
        tour.setAmount(allAmount);

        getHibernateTemplate().update(tour);
      }
    }

    return books;
  }

  @SuppressWarnings("unchecked")
  public Plan opGetBalanceAndCost(String tourNo) {
    StringBuilder sql = new StringBuilder();
    Plan plan = getTourInfo(tourNo, false, true);
    List<TourCost> costList = plan.getCostList();

    sql = new StringBuilder();
    sql.append("select a.outcomeId,a.supplierId,a.frApprovedFlag ");
    sql.append("from Outcome a, TourCost c ");
    sql.append("where c.acctId=? ");
    sql.append("and a.outcomeId=c.tblOutcome.outcomeId ");

    if (null != costList && !(costList.isEmpty())) {
      int i = 0;
      for (TourCost costAcct : costList) {
        costAcct.setId(++i);
        costAcct.setCostType(RowDataUtil.getString(costAcct.getCostType()));

        List<Object[]> tempList = getHibernateTemplate().find(sql.toString(),
            costAcct.getAcctId());
        if (!tempList.isEmpty()) {
          Object[] objTemp = tempList.get(0);
          costAcct.setFrChecked(RowDataUtil.getString(objTemp[2]));
          costAcct.setIsMakeOutcome("Y");
        }
      }
    }

    return plan;

  }

  @SuppressWarnings("unchecked")
  public List<Plan> getTours(Integer teamId, Integer userId, String lineName,
      Date kenStartDate, Date kenEndDate) {
    StringBuilder sql = new StringBuilder();
    List<Object> params = new ArrayList<Object>();
    HibernateTemplate template = getHibernateTemplate();

    sql.append("from Plan where opAccount='Y' and delKey='N' ");

    if (null != kenStartDate) {
      sql.append("and outDate >=? ");
      params.add(kenStartDate);
    }
    if (null != kenEndDate) {
      sql.append("and outDate<=? ");
      params.add(kenEndDate);
    }

    if (StringUtil.hasLength(lineName)) {
      sql.append("and line.lineName like ? ");
      params.add("%" + lineName.trim() + "%");
    }

    // 线路专管员
    if (userId != 0) {
      sql.append("and assigned.userId=? ");
      params.add(userId);
    }
    // 团所属部门
    if (teamId != 0) {
      sql.append("and team.teamId=? ");
      params.add(teamId);
    }
    sql.append("order by outDate");

    Object[] param = null;
    if (params.size() > 0) {
      param = new Object[params.size()];
      for (int i = 0; i < params.size(); i++) {
        param[i] = params.get(i);
      }
    }

    List<Plan> list = template.find(sql.toString(), param);

    for (Plan singlePlan : list) {
      BigDecimal ml = new BigDecimal(0);
      if (singlePlan.getTourAmount().doubleValue() != 0.0) {
        ml = singlePlan.getTourAmount().subtract(singlePlan.getCost());
        // 保留两位小数点
        singlePlan.setGrossAmount(ml);
        BigDecimal num = new BigDecimal(0);
        num = singlePlan.getGrossAmount().divide(singlePlan.getTourAmount())
            .multiply(new BigDecimal(100));
        // 保留两位小数点
        singlePlan.setGrossAmountRate(num);
      }

      singlePlan.setLeaderPax(RowDataUtil.getInt(singlePlan.getPax3()));

      // 查找已收款
      sql = new StringBuilder();
      sql.append("select sum(cramt) ");
      sql.append("from Booking ");
      sql.append("where plan.planNo=? and delkey='N' ");

      List<Double> list2 = template
          .find(sql.toString(), singlePlan.getPlanNo());
      BigDecimal cramt = RowDataUtil.getBigDecimal(list2.get(0));
      // 已收款(保留两位小数)
      singlePlan.setAlAmount(cramt);
    }

    return list;

  }

  public int authorizationModify(String planNo, int uid) {
    Plan plan = (Plan) getHibernateTemplate().get(Plan.class, planNo);
    if (null != plan) {
      plan.setOpRefactor("Y");
      plan.setUpdatedBy(uid);
      // tblBalance.setNumber(tblBalance.getNumber() + 1);
      getHibernateTemplate().update(plan);
    } else
      return -1;
    return 0;
  }

  // --------------------------------------------------------------------------

  @SuppressWarnings("unchecked")
  public int arrangeLeader(String tourNo, String[] nameKey, int operator) {
    Plan tour = (Plan) getHibernateTemplate().get(Plan.class, tourNo,
        LockMode.PESSIMISTIC_WRITE);

    if (null == tour)
      return -1;

    StringBuilder sb = new StringBuilder();
    sb.append("from Tourist ");
    sb.append("where nmno in (");
    for (int i = 0; i < nameKey.length; i++) {
      sb.append("'" + nameKey[i] + "',");
    }

    List<Tourist> list = getHibernateTemplate().find(
        sb.substring(0, sb.length() - 1) + ")");
    for (Tourist obj : list) {
      if (obj.getLeaderKey().equals("N")) {
        obj.setLeaderKey("Y");
        getHibernateTemplate().update(obj);
        tour.setPax3(tour.getPax3() + 1);
      }
    }
    getHibernateTemplate().update(tour);

    return 0;
  }

  @SuppressWarnings("unchecked")
  public List<Leader> getLeaderList() {
    StringBuilder sql = new StringBuilder();

    sql.append("select accCd,accNm,accSex,birthplace,business,hzzl,hzno,");
    sql.append("hzadd,hzdate1,mobile,tel,leadCard,card,hzrang ");
    sql.append("from Guide ");
    sql.append("order by accNm ");

    List<Object[]> list = getHibernateTemplate().find(sql.toString());

    List<Leader> ret = new ArrayList<Leader>();
    for (Object[] obj : list) {
      Leader lead = new Leader();

      lead.setUid(RowDataUtil.getString(obj[0]));
      lead.setUserName(RowDataUtil.getString(obj[1]));
      lead.setSex(RowDataUtil.getString(obj[2]));
      lead.setBirthplace(RowDataUtil.getString(obj[3]));
      lead.setVocation(RowDataUtil.getString(4));
      lead.setPassportType(RowDataUtil.getString(obj[5]));
      lead.setPassportNo(RowDataUtil.getString(obj[6]));
      lead.setPassportPlace(RowDataUtil.getString(obj[7]));
      lead.setPassportDate(RowDataUtil.getDate(obj[8]));
      // 护照有效期
      lead.setPassportExpiry(RowDataUtil.getDate(obj[13]));
      lead.setMobile(RowDataUtil.getString(obj[9]));
      lead.setPhone(RowDataUtil.getString(obj[10]));
      lead.setLeadCard(RowDataUtil.getString(obj[11]));
      lead.setCard(RowDataUtil.getString(obj[12]));
      ret.add(lead);
    }

    return ret;
  }

  @SuppressWarnings("unchecked")
  public int arrangeFromLeader(String tourNo, String[] nameKey, String[] keys,
      Integer operator) {
    Plan ooj = (Plan) getHibernateTemplate().get(Plan.class, tourNo,
        LockMode.PESSIMISTIC_WRITE);

    if (null == ooj)
      return -1;

    Plan tour = new Plan();
    tour.setTourNo(ooj.getTourNo());
    tour.setLine(ooj.getLine());
    tour.setOutDate(ooj.getOutDate());
    tour.setInDate(ooj.getInDate());
    tour.setInCity(ooj.getInCity());
    tour.setVenue(ooj.getVenue());
    tour.setPax(ooj.getPax());
    tour.setMalePax(ooj.getMalePax());
    tour.setFemalePax(ooj.getFemalePax());
    tour.setLeaderPax(ooj.getLeaderPax());
    tour.setDoubleRoom(ooj.getDbrm());
    tour.setSingleRoom(ooj.getSgrm());
    tour.setExtraBedRoom(ooj.getAdrm());

    StringBuilder sb = new StringBuilder();
    sb.append("FROM Guide ");
    sb.append("WHERE accCd in (");

    for (int i = 0; i < nameKey.length; i++) {
      sb.append("'" + nameKey[i] + "',");
    }

    List<Guide> list = getHibernateTemplate().find(
        sb.substring(0, sb.length() - 1) + ")");

    Tourist tourist;
    String[] piny = supplyYin(list);
    for (int i = 0; i < list.size(); i++) {
      Guide obj = list.get(i);

      tourist = new Tourist();
      // 名单号
      tourist.setNmno(keys[i]);
      // 记录类型
      tourist.setRecType('A');
      // 姓名
      tourist.setUserName(obj.getAccNm());
      // 汉语拼音
      tourist.setPinYin(piny[i]);
      // 证件种类
      // tourist.setCardty("");
      // 证件号码
      tourist.setCard(obj.getIdCard());
      // 性别
      String sex = "M";
      if ("F".equals(obj.getAccSex().trim())) {
        sex = "F";
        ooj.setPax2(ooj.getPax2() + 1);
      } else
        ooj.setPlanPax(ooj.getPlanPax() + 1);
      tourist.setSex(sex);
      // 出生日期
      tourist.setBirthday(obj.getBirthday());
      // 出生地
      tourist.setBirthplace(obj.getBirthplace());
      // 手机
      // tourist.setMobile("");
      // 是否办护照
      // tourist.setHzKey("");
      // 护照种类
      tourist.setPassportType(obj.getPassportType());
      // 护照号
      tourist.setPassportNo(obj.getPassportNo());
      // 因私护照国籍
      // tfj007.setCountry("");
      // 发照日期
      tourist.setPassportDate(obj.getPassportDate());
      // 发照地
      tourist.setPassportPlace(obj.getPassportPlace());
      // 护照有效期
      tourist.setPassportExpiry(obj.getPassportExpiry());
      // -----------------------------------------------------------------
      // 团号
      tourist.setTourNo(tour.getTourNo());
      // 线路报价
      tourist.setPrice(new BigDecimal(0));
      // 应收团款
      tourist.setAmt01(new BigDecimal(0));
      // 优惠申请
      tourist.setAmt02(new BigDecimal(0));
      // 已收团款
      tourist.setAmt03(new BigDecimal(0));
      // 已退团款
      tourist.setAmt04(new BigDecimal(0));
      // 重点客人否
      tourist.setVipkey("N");
      // 备注
      tourist.setRemark(" ");
      // 同行人数
      tourist.setPaxnum(0);
      // 住房要求
      tourist.setRoomKey(" ");
      // 同房序号
      tourist.setRmNum(0);
      // 是否同意与他人同住
      tourist.setRoomKey1("Y");
      // 取消标志
      tourist.setDel("N");
      // 操作人
      tourist.setOpuser(operator);
      // 分团标志
      tourist.setTourKey("N");
      // 领队标志
      tourist.setLeaderKey("Y");
      // 办签状态
      tourist.setVisaKey(" ");
      // -------------------------------------------------------------
      getHibernateTemplate().save(tourist);

      ooj.setPax(ooj.getPax() + 1);
      ooj.setPax3(ooj.getPax3() + 1);
    }

    return 0;
  }

  @SuppressWarnings("unchecked")
  private String[] supplyYin(List<Guide> trips) {
    StringBuilder sql = new StringBuilder();
    sql.append("from Pinyin ");
    sql.append("where chinese in (");

    String[] pinyin = new String[trips.size()];

    for (int i = 0; i < trips.size(); i++) {
      Guide trip = trips.get(i);

      String name = trip.getAccNm();
      String oldpinyin = trip.getPinyin();
      if (StringUtil.hasLength(oldpinyin)) {
        pinyin[i] = oldpinyin;
      } else {
        StringBuilder py = new StringBuilder();
        char[] ch = name.toCharArray();
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (char c : ch) {
          sb.append("'" + c + "',");
        }
        List<Pinyin> pys = getHibernateTemplate().find(
            sql.toString() + sb.substring(0, sb.length() - 1) + ")");
        Map<String, String> map = new TreeMap<String, String>();
        for (Pinyin tfj112 : pys) {
          map.put(tfj112.getChinese(), tfj112.getEnglish());
        }

        for (char c : ch) {
          if (null == map.get(String.valueOf(c)))
            py.append(c);
          else {
            py.append(map.get(String.valueOf(c)));
            count++;

            if (count == 1)
              py.append(' ');
          }
        }
        pinyin[i] = py.toString().trim();
      }
    }

    return pinyin;
  }

  /**
   * 取消领队
   */
  @SuppressWarnings("unchecked")
  public int cancelLeader(String tourNo, String[] nameKey, int uid) {

    Plan tour = (Plan) getHibernateTemplate().get(Plan.class, tourNo,
        LockMode.PESSIMISTIC_WRITE);
    if (null == tour)
      return -1;

    Date sysdate = getSysdate();
    StringBuilder sb = new StringBuilder();
    sb.append("from Tourist ");
    sb.append("where nmno in (");
    for (int i = 0; i < nameKey.length; i++) {
      sb.append("'" + nameKey[i] + "',");
    }
    List<Tourist> list = getHibernateTemplate().find(
        sb.substring(0, sb.length() - 1) + ")");

    for (Tourist obj : list) {
      if (obj.getLeaderKey().equals("Y")) {
        if ("".equals(obj.getBooking().getNameNo().trim())) {
          // obj.setLeaderKey("N");
          // obj.setDel('Y');
          if (obj.getSex().equals("F"))
            tour.setFemalePax(tour.getFemalePax() - 1);
          else
            tour.setMalePax(tour.getMalePax() - 1);
          getHibernateTemplate().delete(obj);
        } else {
          obj.setLeaderKey("N");
          getHibernateTemplate().update(obj);
        }

        tour.setLeaderPax(tour.getLeaderPax() - 1);
      }
    }
    tour.setOpuser2(uid);
    tour.setOptime2(sysdate);
    getHibernateTemplate().update(tour);

    return 0;
  }
}
