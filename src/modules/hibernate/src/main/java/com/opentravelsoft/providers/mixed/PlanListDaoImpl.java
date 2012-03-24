package com.opentravelsoft.providers.mixed;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.LockMode;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.opentravelsoft.util.LabelValueBean;
import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Line;
import com.opentravelsoft.entity.LinePrice;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.PlanPrice;
import com.opentravelsoft.entity.ShareFlight;
import com.opentravelsoft.providers.SimpleHibernateDaoSupport;
import com.opentravelsoft.util.RowDataUtil;
import com.opentravelsoft.util.StringUtil;

public class PlanListDaoImpl extends SimpleHibernateDaoSupport implements
    PlanListDao {

  protected SimpleDateFormat SDF = new SimpleDateFormat("HH:mm");

  @SuppressWarnings("unchecked")
  public List<Plan> getPlanList(long rowCount, boolean deadline, String region) {
    StringBuilder sql = new StringBuilder();
    sql.append("from Plan ");
    sql.append("where delKey='N' and deployFlag='Y' ");
    sql.append("and outDate>current_date() ");

    if (deadline)
      sql.append("and deadline>current_date() ");
    if (StringUtil.hasLength(region))
      sql.append("and line.destination.code like '" + region + "%' ");

    sql.append("order by outDate");

    List<Plan> list = getHibernateTemplate().find(sql.toString());

    Date sysdate = getSysdate();
    Calendar cal = Calendar.getInstance();
    cal.setTime(sysdate);
    cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
        cal.get(Calendar.DAY_OF_MONTH));

    for (Plan plan : list) {
      if (plan.getPax3() > 0 && plan.getOutDate().compareTo(cal.getTime()) >= 0) {
        plan.setEnter(true);
        if (null != plan.getDeadline()
            && plan.getDeadline().compareTo(cal.getTime()) < 0)
          plan.setEnter(false);
      }
    }

    return list;
  }

  @SuppressWarnings("unchecked")
  public List<Plan> getLinePlans(String lineNo, boolean openFlag,
      boolean after, boolean outDateSort, boolean deadline) {
    StringBuilder sql = new StringBuilder();
    sql.append("from Plan ");
    sql.append("where line.lineNo=? ");
    //
    if (after)
      sql.append("and outDate>=current_date() ");
    if (deadline)
      sql.append("and deadline>current_date() ");

    //
    if (openFlag)
      sql.append("and deployFlag='Y' ");

    if (outDateSort)
      sql.append("order by outDate");
    else
      sql.append("order by outDate DESC");
    Object[] ooj = { lineNo };

    List<Plan> list = getHibernateTemplate().find(sql.toString(), ooj);
    Date sysdate = getSysdate();
    Calendar cal = Calendar.getInstance();
    cal.setTime(sysdate);
    cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
        cal.get(Calendar.DAY_OF_MONTH));
    for (Plan plan : list) {
      if (plan.getPax3() > 0 && plan.getOutDate().compareTo(cal.getTime()) >= 0) {
        plan.setEnter(true);
        if (null != plan.getDeadline()
            && plan.getDeadline().compareTo(cal.getTime()) < 0)
          plan.setEnter(false);
      }
    }

    return list;
  }

  @SuppressWarnings("unchecked")
  public Plan getPlanDetail(String planNo) {
    Plan plan = getHibernateTemplate().get(Plan.class, planNo, LockMode.READ);
    if (null != plan) {
      // 备注
      plan.setRemarks(RowDataUtil.getString(plan.getRecRmk()));
      //
      plan.setDateCreated(RowDataUtil.getDate(plan.getCrDate()));
      // 产品推广方式
      plan.setTraitId(RowDataUtil.getInt(plan.getTraitType()));

      // -----------------------------------------------------------------
      // 共享资源
      StringBuilder sb2 = new StringBuilder();
      sb2.append("select c.flightNo,c.airwaysCode,c.departureDate,");
      sb2.append("c.seating,c.handle,c.note ");
      sb2.append("from Plan b,");
      sb2.append("ShareFlight c ");
      sb2.append("where b.shareFlightId=c.shareFlightId ");
      sb2.append("and b.shareFlightId=? and b.outDate=?");
      List<Object> params = new ArrayList<Object>();
      params.add(RowDataUtil.getInt(plan.getShareFlightId()));
      params.add(RowDataUtil.getDate(plan.getOutDate()));
      Object[] param = null;
      if (params.size() > 0) {
        param = new Object[params.size()];
        for (int i = 0; i < params.size(); i++) {
          param[i] = params.get(i);
        }
      }

      List<Object[]> shareList = getHibernateTemplate().find(sb2.toString(),
          param);
      if (shareList.isEmpty() == false) {
        Object[] obj2 = shareList.get(0);
        plan.setFlightNo(RowDataUtil.getString(obj2[0]));
        plan.setAirwaysCode(RowDataUtil.getString(obj2[1]));
        plan.setDepartureDate(RowDataUtil.getDate(obj2[2]));
        plan.setSeating(RowDataUtil.getInt(obj2[3]));
        plan.setHandle(RowDataUtil.getInt(obj2[4]));
        plan.setNote(RowDataUtil.getString(obj2[5]));
      }
    }
    return plan;
  }

  @SuppressWarnings("unchecked")
  public List<LabelValueBean> searchShare(Date startDate) {
    StringBuilder sb = new StringBuilder();
    sb.append("select a.shareFlightId,a.airwaysCode,b.name,a.flightNo ");
    sb.append("from ShareFlight a,");
    sb.append("Airways b ");
    sb.append("where a.airwaysCode=b.code and departureDate=?");
    Object[] prm = { startDate };
    List<LabelValueBean> lvbList = new ArrayList<LabelValueBean>();
    List<Object[]> shList = getHibernateTemplate().find(sb.toString(), prm);
    LabelValueBean lvb = null;
    for (Object[] obj : shList) {
      lvb = new LabelValueBean();
      lvb.setLabel(String.valueOf(RowDataUtil.getInt(obj[0])));
      lvb.setValue("航空公司:" + RowDataUtil.getString(obj[1])
          + RowDataUtil.getString(obj[2]) + "|航班号:"
          + RowDataUtil.getString(obj[3]));
      lvbList.add(lvb);
    }

    return lvbList;
  }

  @SuppressWarnings("unchecked")
  public List<LabelValueBean> searchShare() {
    StringBuilder sb = new StringBuilder();
    sb.append("select a.shareFlightId,a.airwaysCode,b.name,a.flightNo,");
    sb.append("a.departureDate,a.seating,a.handle ");
    sb.append("from ShareFlight a,");
    sb.append("Airways b ");
    sb.append("where a.airwaysCode=b.code ");
    List<LabelValueBean> lvbList = new ArrayList<LabelValueBean>();
    List<Object[]> shList = getHibernateTemplate().find(sb.toString());
    LabelValueBean lvb = null;
    for (Object[] obj : shList) {
      lvb = new LabelValueBean();
      lvb.setLabel(String.valueOf(RowDataUtil.getInt(obj[0])));
      lvb.setValue("航空公司:" + RowDataUtil.getString(obj[2]) + "|航班号:"
          + RowDataUtil.getString(obj[3]) + "|出行日期:"
          + RowDataUtil.getDate(obj[4]) + "|座位数:" + RowDataUtil.getInt(obj[5])
          + "|可操作:" + RowDataUtil.getInt(obj[6]));
      lvbList.add(lvb);
    }

    return lvbList;
  }

  @SuppressWarnings("unchecked")
  public List<Plan> find(String lineName, long teamId, long userId,
      Date startDate, Date endDate, double lowerPrice, double upperPrice,
      boolean openFlag, String outCity, String destination) {
    StringBuilder sql = new StringBuilder();
    sql.append("from Plan ");
    sql.append("where outDate>=? and outDate<=? ");//

    if (openFlag) {
      sql.append("and deployFlag='Y' ");
    }
    sql.append("and line.isActive=true ");

    if (StringUtil.hasLength(lineName)) {
      sql.append("and line.lineName like '%" + lineName + "%' ");
    }
    if (teamId != 0) {
      sql.append("and team.teamId=" + teamId + " ");
    }
    if (userId != 0) {
      sql.append("and assigned.userId=" + userId + " ");
    }
    if (StringUtil.hasLength(outCity)) {
      sql.append("and line.outCity.citycd='" + outCity + "' ");
    }
    if (StringUtil.hasLength(destination)) {
      sql.append("and line.destination.code='" + destination + "' ");
    }

    if (lowerPrice > 0 && upperPrice > 0) {
      sql.append("and packagePrice.price>=" + lowerPrice
          + " and packagePrice.price<=" + upperPrice + " ");
    } else if (upperPrice > 0) {
      sql.append("and packagePrice.price<=" + upperPrice + " ");
    } else if (lowerPrice > 0) {
      sql.append("and packagePrice.price>=" + lowerPrice + " ");
    }

    sql.append("order by outDate desc ");

    Object[] ooj = { startDate, endDate };
    List<Plan> list = getHibernateTemplate().find(sql.toString(), ooj);

    // Get now date
    Date sysdate = getSysdate();
    Calendar cal = Calendar.getInstance();
    cal.setTime(sysdate);
    cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
        cal.get(Calendar.DAY_OF_MONTH));

    for (Plan plan : list) {
      if (plan.getSingleFlag() == 0)
        plan.setSingleShow("整团");
      else
        plan.setSingleShow("散拼团");

      if (plan.getPax3() > 0 && plan.getOutDate().compareTo(cal.getTime()) >= 0) {
        plan.setEnter(true);
        if (null != plan.getDeadline()
            && plan.getDeadline().compareTo(cal.getTime()) < 0)
          plan.setEnter(false);
      }
    }
    return list;
  }

  @SuppressWarnings("unchecked")
  public int deletePlan(Plan plan, long userId, String note) {
    HibernateTemplate template = getHibernateTemplate();
    Plan planInt = (Plan) template.get(Plan.class, plan.getPlanNo(),
        LockMode.UPGRADE);

    if (planInt == null)
      return -1;

    StringBuilder sql = new StringBuilder();
    Object[] params = { planInt.getPlanNo() };
    sql.append("from Booking where plan.planNo=?");
    List<Booking> list = template.find(sql.toString(), params);

    if (list.size() > 0)
      return -2;

    template.delete(planInt);
    return 0;
  }

  public int updatePlan(Plan plan, int shareId, String note) {
    HibernateTemplate template = getHibernateTemplate();
    Plan planInts = (Plan) template.get(Plan.class, plan.getPlanNo(),
        LockMode.UPGRADE);
    if (null == planInts) {
      log.error("Plan no find.");
      return -1;
    }
    planInts.setTourNo(plan.getTourNo());
    planInts.setFavourable(plan.getFavourable());
    planInts.setMessage(plan.getMessage());
    planInts.setDeployFlag(plan.getDeployFlag());
    planInts.setPax1(RowDataUtil.getInt(plan.getPax1()));
    planInts.setPax3(RowDataUtil.getInt(plan.getPax3()));
    planInts.setPax4(RowDataUtil.getInt(plan.getPax4()));
    planInts.setPax5(plan.getPax5());
    planInts.setPaxkey(plan.getPaxkey());
    planInts.setDeadline(plan.getDeadline());
    planInts.setRecRmk(plan.getRemarks());
    planInts.setSingleFlag(plan.getSingleFlag());
    planInts.setTraitId(plan.getTraitId());
    planInts.setOpUser(plan.getOpUser());
    planInts.setPackagePrice(template.get(LinePrice.class, plan.getPackagePrice().getRecNo()));
    planInts.setAssigned(plan.getAssigned());
    planInts.setTeam(plan.getTeam());

    ShareFlight tblSF = new ShareFlight();
    if (plan.getShareFlight().equals('Y') && plan.getSelectNO().equals('N')) {
      tblSF = new ShareFlight();
      tblSF.setAirwaysCode(plan.getAirwaysCode());
      tblSF.setFlightNo(plan.getFlightNo());
      tblSF.setDepartureDate(plan.getDepartureDate());
      tblSF.setSeating(plan.getSeating());
      tblSF.setHandle(plan.getHandle());
      tblSF.setNote(plan.getNote());
      template.update(tblSF);
    }
    if (plan.getShareFlight().equals('Y') && plan.getSelectNO().equals('O')) {
      ShareFlight obj = (ShareFlight) template.get(ShareFlight.class, shareId);
      planInts.setShareFlightId(obj.getShareFlightId());
      planInts.setPax1(obj.getSeating());
      planInts.setPax3(obj.getHandle());
    }

    if (plan.getShareFlight().equals("N")) {
      planInts.setShareFlight("N");
      planInts.setShareFlightId(-1);
      ShareFlight tblsf = (ShareFlight) template.get(ShareFlight.class,
          planInts.getShareFlightId());
      if (tblsf != null) {
        template.delete(tblsf);
      }
    }

    template.update(planInts);
    return 0;
  }

  @SuppressWarnings("unchecked")
  public Plan findLastPlan(String lineNo) {
    StringBuilder sql = new StringBuilder();
    sql.append("from Plan ");
    sql.append("where line.lineNo=? ");
    sql.append("order by outDate desc");
    Object[] obj = { lineNo };

    List<Plan> list = getHibernateTemplate().find(sql.toString(), obj);
    Plan plan = null;
    Short zero = new Integer(0).shortValue();

    if (list.size() > 0) {
      plan = list.get(0);
      plan.setDeadline(plan.getDeadline());
      plan.setRemarks(plan.getRecRmk());
      plan.setMessage(plan.getMessage());
      plan.setStartDate(plan.getOutDate());
      plan.setEndDate(plan.getOutDate());
    } else {
      plan = new Plan();
      Date sysdate = getSysdate();
      Calendar calDate = Calendar.getInstance();
      calDate.setTime(sysdate);
      calDate.add(Calendar.DAY_OF_MONTH, 1);
      plan.setStartDate(calDate.getTime());
      plan.setEndDate(calDate.getTime());
      plan.setPax1(zero);
      plan.setPax2(zero);
      plan.setPax3(zero);
      plan.setPax4(zero);
      plan.setPax5(zero);
      plan.setTourNo("");
    }

    return plan;
  }

  public int insertPlan(List<Plan> plans, int shareId) {
    for (Plan plan : plans) {
      HibernateTemplate template = getHibernateTemplate();
      Plan planInst = (Plan) template.get(Plan.class, plan.getPlanNo(),
          LockMode.UPGRADE);

      if (planInst == null) {
        planInst = new Plan();
        planInst.setPlanNo(plan.getPlanNo());
        planInst.setLine(plan.getLine());
        planInst.setOutDate(plan.getOutDate());
        planInst.setTourNo(plan.getTourNo());
        planInst.setPax1(RowDataUtil.getInt(plan.getPax1()));
        planInst.setPax2(RowDataUtil.getInt(plan.getPax2()));
        planInst.setPax3(RowDataUtil.getInt(plan.getPax3()));
        planInst.setPax4(RowDataUtil.getInt(plan.getPax4()));
        planInst.setPax5(RowDataUtil.getInt(plan.getPax5()));
        planInst.setDeployFlag(RowDataUtil.getString(plan.getDeployFlag()));

        if (plan.getTraitId() == 4) {
          plan.setFavourable("Y");
        }

        planInst.setFavourable(plan.getFavourable());
        planInst.setDeadline(plan.getDeadline());
        planInst.setRecRmk(plan.getRemarks());
        planInst.setMessage(plan.getMessage());
        planInst.setPaxkey(plan.getPaxkey());
        // 整团 散客
        planInst.setSingleFlag(plan.getSingleFlag());
        planInst.setCrUser(new Employee(plan.getOpUser()));
        planInst.setOpUser(plan.getOpUser());
        planInst.setTraitId(plan.getTraitId());
        planInst.setAssigned(plan.getAssigned());
        planInst.setTeam(plan.getTeam());

        planInst.setShareFlight(plan.getShareFlight());
        planInst.setPackagePrice(template.get(LinePrice.class, plan.getPackagePrice().getRecNo()));

        ShareFlight tblSF = new ShareFlight();
        if (plan.getShareFlight().equals('Y') && plan.getSelectNO().equals('N')) {
          tblSF = new ShareFlight();
          tblSF.setAirwaysCode(plan.getAirwaysCode());
          tblSF.setFlightNo(plan.getFlightNo());
          tblSF.setDepartureDate(plan.getDepartureDate());
          tblSF.setSeating(plan.getSeating());
          tblSF.setHandle(plan.getHandle());
          tblSF.setNote(plan.getNote());
          template.save(tblSF);
          planInst.setShareFlightId(tblSF.getShareFlightId());
        }
        if (plan.getShareFlight().equals('Y') && plan.getSelectNO().equals('O')) {
          ShareFlight obj = (ShareFlight) template.get(ShareFlight.class,
              shareId);
          planInst.setShareFlightId(obj.getShareFlightId());
          planInst.setPax1(obj.getSeating());
          planInst.setPax3(obj.getHandle());
        }
        template.save(planInst);
      }
    }

    return 0;
  }

  @SuppressWarnings("unchecked")
  public List<PlanPrice> getPlanPrices(String planNo) {
    StringBuilder sql = new StringBuilder();
    sql.append("from PlanPrice where planNo=? ");
    Object[] params = { planNo };
    List<PlanPrice> list = getHibernateTemplate().find(sql.toString(), params);

    int idx = 0;
    for (PlanPrice obj : list)
      obj.setIdx(idx++);

    return list;
  }

  @SuppressWarnings("unchecked")
  public int savePlanPrices(String planNo, List<PlanPrice> list) {
    StringBuilder sql = new StringBuilder();
    sql.append("from PlanPrice where planNo=? ");
    HibernateTemplate template = getHibernateTemplate();
    Object[] params = { planNo };

    List<PlanPrice> priceList = template.find(sql.toString(), params);
    if (priceList != null)
      template.deleteAll(priceList);

    for (PlanPrice obj : list)
      template.save(obj);

    return 0;
  }

  @SuppressWarnings("unchecked")
  public List<LinePrice> getLinePrices(String planNo) {
    StringBuilder sql = new StringBuilder();
    sql.append("select l ");
    sql.append("from LinePrice l,");
    sql.append("PlanPrice r ");
    sql.append("where l.recNo=r.priceNo and r.planNo=? ");
    Object[] params = { planNo };

    List<LinePrice> list = getHibernateTemplate().find(sql.toString(), params);

    sql = new StringBuilder();
    sql.append("select packagePrice.recNo from Plan where planNo=? ");
    List<String> priceNo = getHibernateTemplate().find(sql.toString(), params);
    for (LinePrice obj : list) {
      if (obj.getRecNo().equals(priceNo.get(0)))
        obj.setDefaultPrice(true);
    }

    return list;
  }

  @SuppressWarnings("unchecked")
  public List<Plan> getPlans(Date outDate, String lineNo) {
    StringBuilder sql = new StringBuilder();
    sql.append("from Plan ");
    sql.append("where line.lineNo=? and outDate=? ");
    Object[] param = { lineNo, outDate };
    return getHibernateTemplate().find(sql.toString(), param);
  }

  @SuppressWarnings("unchecked")
  public int reCountPlanPax(String planNo) {
    HibernateTemplate template = getHibernateTemplate();
    StringBuilder sql = new StringBuilder();
    sql.append("from com.opentravelsoft.entity.Plan ");
    sql.append("where planNo = ? ");
    Object[] param = { planNo };
    List<Plan> planList = template.find(sql.toString(), param);
    if (!planList.isEmpty()) {
      Plan plan = planList.get(0);
      sql = new StringBuilder();
      sql.append("select sum(confirmPax) ");
      sql.append("from Booking ");
      sql.append("where plan.planNo=? and plan.delkey='N' ");

      List<Object> list = getHibernateTemplate().find(sql.toString(), param);
      int pax2 = RowDataUtil.getShort(list.get(0));
      plan.setPax2(pax2);
      plan.setPax3(plan.getPax1() - plan.getPax2() - plan.getPax4());
      template.update(plan);
    } else {
      return -1;
    }

    return 0;
  }

  @SuppressWarnings("unchecked")
  public int isTourNoRepeat(String tourNo) {
    HibernateTemplate template = getHibernateTemplate();
    StringBuilder sql = new StringBuilder();
    sql.append("from Plan where tourNo=? ");
    List<Plan> planList = template.find(sql.toString(), tourNo.trim());
    if (planList.isEmpty()) {
      return 0;
    } else {
      return -1;
    }
  }

  @SuppressWarnings("unchecked")
  public List<Plan> getRunPlans(long teamId, long userId) {
    StringBuilder sql = new StringBuilder();
    sql.append("from Plan ");
    sql.append("where outDate>=current_date() ");
    sql.append("and line.isActive=true and team.teamId in (");

    sql.append("select t.teamId ");
    sql.append("from Employee u join u.teamMemberships t ");
    sql.append("where u.userId=? ");

    sql.append(") order by outDate  ");
    Object[] params = { userId };
    List<Plan> list = getHibernateTemplate().find(sql.toString(), params);

    // 设置是否可以预订
    Date sysdate = getSysdate();
    Calendar cal = Calendar.getInstance();
    cal.setTime(sysdate);
    cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
        cal.get(Calendar.DAY_OF_MONTH));

    for (Plan plan : list) {
      if (plan.getSingleFlag() == 0)
        plan.setSingleShow("整团");
      else
        plan.setSingleShow("散拼团");

      if (plan.getPax3() > 0 && plan.getOutDate().compareTo(cal.getTime()) >= 0) {
        plan.setEnter(true);
        if (null != plan.getDeadline()
            && plan.getDeadline().compareTo(cal.getTime()) < 0)
          plan.setEnter(false);
      }
    }

    return list;
  }

  /**
   * 该目的地线路
   */
  @SuppressWarnings("unchecked")
  public List<Plan> getRouteByDis(String districtNo, Date startDate,
      Date endDate) {
    StringBuilder sb = new StringBuilder();
    List<Object> params = new ArrayList<Object>();
    sb.append("select c.plan.line.lineNo,c.plan.line.lineName,");
    sb.append("c.plan.planNo,c.plan.outDate,sum(c.pax),sum(c.dbamt) ");
    sb.append("from Booking c,");
    sb.append("LineDistrict b ");
    sb.append("where c.plan.line.lineNo=b.id.lineNo ");
    sb.append("and c.confirmStatus='1' and c.delkey<>'Y' ");
    sb.append("and b.id.districtNo=? ");
    params.add(districtNo);
    if (null != startDate) {
      sb.append("and c.outDate>=? ");
      params.add(startDate);
    }
    if (null != endDate) {
      sb.append("and c.outDate<=? ");
      params.add(endDate);
    }
    sb.append("group by c.plan.line.lineNo,c.plan.line.lineName,c.plan.planNo,c.plan.outDate ");
    sb.append("order by c.plan.outDate, c.plan.line.lineNo");
    Object[] param = null;
    if (params.size() > 0) {
      param = new Object[params.size()];
      for (int i = 0; i < params.size(); i++) {
        param[i] = params.get(i);
      }
    }
    List<Plan> routeList = new ArrayList<Plan>();
    List<Object[]> list = getHibernateTemplate().find(sb.toString(), param);
    for (Object[] obj : list) {
      Plan plan = new Plan();
      Line route = new Line();
      route.setLineNo(RowDataUtil.getString(obj[0]));
      route.setLineName(RowDataUtil.getString(obj[1]));

      plan.setLine(route);
      plan.setOutDate(RowDataUtil.getDate(obj[3]));
      plan.setSumPax(RowDataUtil.getInt(obj[4]));
      plan.setSumDbamt(RowDataUtil.getDouble(obj[5]));
      routeList.add(plan);
    }
    return routeList;
  }

  @SuppressWarnings("unchecked")
  public List<Plan> getRoutePlans(String lineNo, boolean openFlag,
      boolean after, boolean outDateSort, boolean deadline) {
    StringBuilder sql = new StringBuilder();
    sql.append("from Plan ");
    sql.append("where a.line.lineNo=? ");

    //
    if (after) {
      sql.append("and a.outDate>=current_date() ");
    }

    if (deadline) {
      sql.append("and a.deadline>current_date() ");
    }

    //
    if (openFlag)
      sql.append("and a.deployFlag='Y' ");

    if (outDateSort)
      sql.append("order by a.outDate");
    else
      sql.append("order by a.outDate DESC");

    Object[] ooj = { lineNo };
    List<Plan> list = getHibernateTemplate().find(sql.toString(), ooj);
    Date sysdate = getSysdate();

    Calendar cal = Calendar.getInstance();
    cal.setTime(sysdate);
    cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
        cal.get(Calendar.DAY_OF_MONTH));
    for (Plan plan : list) {
      if (plan.getPax3() > 0 && plan.getOutDate().compareTo(cal.getTime()) >= 0) {
        plan.setEnter(true);
        if (null != plan.getDeadline()
            && plan.getDeadline().compareTo(cal.getTime()) < 0)
          plan.setEnter(false);
      }
    }

    return list;
  }

  @SuppressWarnings("unchecked")
  public PlanPrice getPlanFlight(String planNo) {
    StringBuilder sb = new StringBuilder();
    sb.append("from PlanFlight where planNo=? and process='G' ");

    Object[] param = { planNo };
    List<PlanPrice> planFlightList = getHibernateTemplate().find(sb.toString(),
        param);
    return planFlightList.get(0);
  }

}
