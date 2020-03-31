package com.opentravelsoft.action.manage.product;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.common.SessionKeyParams;
import com.opentravelsoft.common.TeamType;
import com.opentravelsoft.entity.Airways;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Line;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.PlanPrice;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.service.operator.TourPlanService;
import com.opentravelsoft.service.setting.EmployeeService;

/**
 * 线路增加出团计划
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:54 $
 */
public class AddPlanAction extends ManageAction {
  private static final long serialVersionUID = 8435596328786314873L;

  protected SimpleDateFormat SDF = new SimpleDateFormat("yy-MMdd");

  protected SimpleDateFormat SDF2 = new SimpleDateFormat("yyyyMMdd");

  @Autowired
  private TourPlanService tourPlanService;

  @Autowired
  private EmployeeService employeeService;

  private Line line;

  private List<Plan> plans;

  private int shareId;

  private List<LabelValueBean> deploys;

  private List<LabelValueBean> favourables;

  private Character shareKey;

  private String recordNo;

  private Plan plan = new Plan();;

  private List<LabelValueBean> paxkey;

  private boolean weekKey1 = false;

  private boolean weekKey2 = false;

  private boolean weekKey3 = false;

  private boolean weekKey4 = false;

  private boolean weekKey5 = false;

  private boolean weekKey6 = false;

  private boolean weekKey7 = false;

  private int deadNum;

  private Date startDate;

  private Date endDate;

  private List<LabelValueBean> shareStateList = new ArrayList<LabelValueBean>();

  private List<LabelValueBean> shareList = new ArrayList<LabelValueBean>();

  private List<LabelValueBean> singleflag = new ArrayList<LabelValueBean>();

  /** 产品推广活动 */
  private List<LabelValueBean> traitList = new ArrayList<LabelValueBean>();

  private List<Employee> employeeList;

  private List<Team> teamList;

  private List<Airways> airways = new ArrayList<Airways>();

  public String input() {
    Employee user = getUser();
    if (plan.getTourNo() == null) {
      line = (Line) ActionContext.getContext().getSession()
          .get(SessionKeyParams.EBIZ_CURRENT_ROUTE);

      startDate = systemDate;
      plan = tourPlanService.findLastPlan(line.getLineNo());

      // airways = tourPlanService.roGetAllAirways();
      // deploys = getCodeList("ebiz_plan_deploy");
      // // 散客或者整团
      // singleflag = getCodeList("ebiz_plan_singleflag");
      // favourables = getCodeList("ebiz_yes_no");
      // paxkey = getCodeList("ebiz_pax_key");
      // shareStateList = getCodeList("ebiz_yes_no");
      // shareList = tourPlanService.roSearchShare();
      // traitList = tourPlanService.roGetTraitList();
      //
      plan.setPaxkey("Y");
      if (plan.getStartDate().before(startDate)) {
        plan.setStartDate(startDate);
        plan.setEndDate(startDate);
      }
    }

    deploys = getCodeList("ebiz_plan_deploy");
    // 散客或者整团
    singleflag = getCodeList("ebiz_plan_singleflag");
    favourables = getCodeList("ebiz_yes_no");
    paxkey = getCodeList("ebiz_pax_key");
    shareStateList = getCodeList("ebiz_yes_no");
    shareList = tourPlanService.roSearchShare();
    traitList = tourPlanService.roGetTraitList();
    airways = tourPlanService.roGetAirways();

    teamList = tourPlanService.getTeamList(user.getUserId(), TeamType.Operator);
    if (teamList.size() > 0)
      employeeList = employeeService.getUserByTeam(teamList.get(0).getTeamId());

    return INPUT;
  }

  /**
   * 提交-添加出团计划
   * 
   * @return
   */
  public String submit() {
    Employee user = getUser();
    line = (Line) ActionContext.getContext().getSession()
        .get(SessionKeyParams.EBIZ_CURRENT_ROUTE);

    List<Plan> planList = new ArrayList<Plan>();

    StringBuilder weekBit = new StringBuilder();
    if (weekKey1 == false) {
      weekBit.append('N');
    } else {
      weekBit.append('Y');
    }
    if (weekKey2 == false) {
      weekBit.append('N');
    } else {
      weekBit.append('Y');
    }
    if (weekKey3 == false) {
      weekBit.append('N');
    } else {
      weekBit.append('Y');
    }
    if (weekKey4 == false) {
      weekBit.append('N');
    } else {
      weekBit.append('Y');
    }
    if (weekKey5 == false) {
      weekBit.append('N');
    } else {
      weekBit.append('Y');
    }
    if (weekKey6 == false) {
      weekBit.append('N');
    } else {
      weekBit.append('Y');
    }
    if (weekKey7 == false) {
      weekBit.append('N');
    } else {
      weekBit.append('Y');
    }
    plan.setLine(line);
    String tourNo = plan.getTourNo();
    String tourYMD = SDF.format(plan.getStartDate());
    int ymdIndex = tourNo.indexOf(tourYMD);
    if (ymdIndex < 0) {
      addActionError("团号不规范,为包含YY-MMDD数字.(例2007年12月6号出发的团,团号中包含07-1206或2007-1206字样)");
      return INPUT;
    }

    plan.setWeekBit(weekBit.toString());

    Calendar calDate = Calendar.getInstance();
    calDate.setTime(plan.getStartDate());
    calDate.add(Calendar.DATE, -deadNum);
    plan.setDeadline(calDate.getTime());
    plan.setOpUser(user.getUserId());

    // 定制出团计划前 要对线路的一些属性特征约束
    // 去返目的地的大交通方式，出境是航空公司、航班、飞行时间，国内短线汽车大巴到达目的地的途中时间
    if (plan.getStartDate().compareTo(plan.getEndDate()) == 0) {
      // 定制一条出团计划
      plan.setOutDate(plan.getStartDate());
      planList.add(plan);
    } else if (plan.getStartDate().compareTo(plan.getEndDate()) < 0) {
      // 按照出团周期定制一段时期的出团计划
      Date date = plan.getStartDate();
      Calendar cal = Calendar.getInstance();
      do {
        cal.setTime(date);
        int week = cal.get(Calendar.DAY_OF_WEEK);

        // weekBit 一 二 ... 日
        // 1 2 3 4 5 6 7
        // calenda 2 3 4 5 6 7 1
        int pos = (week + 6) % 7;
        if (pos == 0)
          pos = 7;
        if (plan.getWeekBit().charAt(pos - 1) == 'Y') {
          Plan tPlan = null;
          try {
            tPlan = (Plan) plan.clone();
          } catch (CloneNotSupportedException e) {
            logger.error("clone failure", e);
          }

          if (null != tPlan) {
            calDate.setTime(date);
            calDate.add(Calendar.DATE, -deadNum);
            tPlan.setDeadline(calDate.getTime());
            tPlan.setOutDate(date);
            tPlan.setTourNo(tPlan.getTourNo().substring(0, ymdIndex)
                + SDF.format(tPlan.getOutDate())
                + tPlan.getTourNo().substring(ymdIndex + 7));
            planList.add(tPlan);
          }
        }

        Calendar calDate1 = Calendar.getInstance();
        calDate1.setTime(date);
        calDate1.add(Calendar.DATE, 1);

        date = calDate1.getTime();
      } while (date.compareTo(plan.getEndDate()) <= 0);

    } else {
      addActionError("开始日期小于结束日期!");
    }

    if (planList.isEmpty()) {
      addActionError("开始日期到结束日期内没有符合周期开班的计划.");
    }

    if (hasActionErrors() || hasFieldErrors())
      return INPUT;

    // 验证团号是否重复
    int isRe = tourPlanService.roIsTourNoRepeat(plan.getTourNo());
    if (isRe == -1) {
      addActionError("团号已经存在，请修改后再保存！");
      return INPUT;
    }

    List<PlanPrice> planPrices = new ArrayList<PlanPrice>();
    int ret = tourPlanService.txInsertPlan(planList, plan.getStartDate(),
        plan.getEndDate(), planPrices, shareId);

    if (ret == 1) {
      addActionError("计划已修改,没有价格所以没有发布到网站上,请填写价格后重新提交计划.");
    } else if (ret < 0) {
      addActionError("计划修改失败.");
    }

    addActionMessage("新的出团计划保存成功.");
    return SUCCESS;
  }

  public String getRecordNo() {
    return recordNo;
  }

  public void setRecordNo(String recordNo) {
    this.recordNo = recordNo;
  }

  public void setPlans(List<Plan> plans) {
    this.plans = plans;
  }

  public List<LabelValueBean> getFavourables() {
    return favourables;
  }

  public void setFavourables(List<LabelValueBean> favourables) {
    this.favourables = favourables;
  }

  public List<Plan> getPlans() {
    return plans;
  }

  public Line getRoute() {
    return line;
  }

  public void setRoute(Line route) {
    this.line = route;
  }

  public List<LabelValueBean> getDeploys() {
    return deploys;
  }

  public void setDeploys(List<LabelValueBean> deploys) {
    this.deploys = deploys;
  }

  public List<LabelValueBean> getPaxkey() {
    return paxkey;
  }

  public Plan getPlan() {
    return plan;
  }

  public void setPlan(Plan plan) {
    this.plan = plan;
  }

  public boolean isWeekKey1() {
    return weekKey1;
  }

  public void setWeekKey1(boolean weekKey1) {
    this.weekKey1 = weekKey1;
  }

  public boolean isWeekKey2() {
    return weekKey2;
  }

  public void setWeekKey2(boolean weekKey2) {
    this.weekKey2 = weekKey2;
  }

  public boolean isWeekKey3() {
    return weekKey3;
  }

  public void setWeekKey3(boolean weekKey3) {
    this.weekKey3 = weekKey3;
  }

  public boolean isWeekKey4() {
    return weekKey4;
  }

  public void setWeekKey4(boolean weekKey4) {
    this.weekKey4 = weekKey4;
  }

  public boolean isWeekKey5() {
    return weekKey5;
  }

  public void setWeekKey5(boolean weekKey5) {
    this.weekKey5 = weekKey5;
  }

  public boolean isWeekKey6() {
    return weekKey6;
  }

  public void setWeekKey6(boolean weekKey6) {
    this.weekKey6 = weekKey6;
  }

  public boolean isWeekKey7() {
    return weekKey7;
  }

  public void setWeekKey7(boolean weekKey7) {
    this.weekKey7 = weekKey7;
  }

  public void setDeadNum(int deadNum) {
    this.deadNum = deadNum;
  }

  public int getDeadNum() {
    return deadNum;
  }

  public Character getShareKey() {
    return shareKey;
  }

  public void setShareKey(Character shareKey) {
    this.shareKey = shareKey;
  }

  public List<LabelValueBean> getShareStateList() {
    return shareStateList;
  }

  public void setShareStateList(List<LabelValueBean> shareStateList) {
    this.shareStateList = shareStateList;
  }

  public List<LabelValueBean> getShareList() {
    return shareList;
  }

  public void setShareList(List<LabelValueBean> shareList) {
    this.shareList = shareList;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public int getShareId() {
    return shareId;
  }

  public void setShareId(int shareId) {
    this.shareId = shareId;
  }

  public List<LabelValueBean> getSingleflag() {
    return singleflag;
  }

  public void setSingleflag(List<LabelValueBean> singleflag) {
    this.singleflag = singleflag;
  }

  public String getStartDate() {
    return SDF2.format(startDate);
  }

  public List<LabelValueBean> getTraitList() {
    return traitList;
  }

  public void setTraitList(List<LabelValueBean> traitList) {
    this.traitList = traitList;
  }

  public List<Employee> getEmployeeList() {
    return employeeList;
  }

  public List<Team> getTeamList() {
    return teamList;
  }

  public List<Airways> getAirways() {
    return airways;
  }

}
