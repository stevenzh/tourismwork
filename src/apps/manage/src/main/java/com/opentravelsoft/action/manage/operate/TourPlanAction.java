package com.opentravelsoft.action.manage.operate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.opentravelsoft.util.LabelValueBean;
import com.opentravelsoft.util.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Line;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.service.operator.TourPlanService;
import com.opentravelsoft.service.setting.EmployeeService;

/**
 * 出团计划查询、删除、修改、保存
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.2 $ $Date: 2009/04/10 07:47:29 $
 */
public class TourPlanAction extends ManageAction {
  private static final long serialVersionUID = 5212732570107400742L;

  protected static final Log logger = LogFactory.getLog(TourPlanAction.class);

  @Autowired
  private TourPlanService tourPlanService;

  @Autowired
  private EmployeeService employeeSevice;

  private Line lineNo;

  /** 计划编号 */
  private String recordNo;

  private List<Team> teamList;

  private List<Employee> employees = new ArrayList<Employee>();

  private List<LabelValueBean> deploys;

  private List<LabelValueBean> favourables;

  private List<LabelValueBean> paxkey;

  private List<Plan> plans = new ArrayList<Plan>();

  private Plan plan;

  /** 出团时间历史 */
  private Date hisOutDate;

  // -------------------------------------------------------------------------
  // 查询条件

  /** 线路名 */
  private String kenLineName;

  /** 部门编号 */
  private long kenTeamId = 0;

  /** 专管员 */
  private long kenUserId = 0;

  /** 出团时间-开始 */
  private Date kenStartDate;

  /** 出团时间-截止 */
  private Date kenEndDate;

  // 用于拆分订单所选的名单
  private String[] selects;

  /** 备注 */
  private String note;

  public String input() {
    // 查询初始化
    Employee user = getUser();
    teamList = tourPlanService.getOperatorTeamList();
    if (teamList.size() > 0) {
      employees = employeeSevice.getUserByTeam(teamList.get(0).getTeamId());
      kenTeamId = teamList.get(0).getTeamId();
    }

    if (null == kenStartDate || null == kenEndDate) {
      buildSysdate();
      Calendar cal = Calendar.getInstance();
      cal.setTime(systemDate);
      cal.set(Calendar.HOUR_OF_DAY, 0);
      cal.set(Calendar.MINUTE, 0);
      cal.set(Calendar.SECOND, 0);
      cal.set(Calendar.MILLISECOND, 0);
      cal.add(Calendar.MONTH, -1);
      kenStartDate = cal.getTime();
      cal.add(Calendar.MONTH, 1);
      kenEndDate = cal.getTime();
    }

    plans = tourPlanService.roFind(kenLineName, kenTeamId, kenUserId,
        kenStartDate, kenEndDate, 0f, 0f, false);

    return INPUT;
  }

  /**
   * 查询出团计划
   * 
   * @return
   * @throws Exception
   */
  public String search() {
    plans = tourPlanService.roFind(kenLineName, kenTeamId, kenUserId,
        kenStartDate, kenEndDate, 0f, 0f, false);
    teamList = tourPlanService.getOperatorTeamList();
    if (kenTeamId != 0)
      employees = employeeSevice.getUserByTeam(kenTeamId);

    currentPage(plans.size());

    return SUCCESS;
  }

  /**
   * 出团计划详细
   * 
   * @return
   * @throws Exception
   */
  public String detail() {
    deploys = getCodeList("ebiz_plan_deploy");
    favourables = getCodeList("ebiz_yes_no");
    paxkey = getCodeList("ebiz_pax_key");
    plan = tourPlanService.roGetPlanDetail(recordNo);
    if (null == plan) {
      addActionError("Plan no found.");
      logger.warn("Plan no found.");
    }

    return SUCCESS;
  }

  /**
   * 删除出团计划
   * 
   * @return
   * @throws Exception
   */
  public String delete() {
    Employee user = getUser();
    Plan plana = tourPlanService.roGetPlanDetail(recordNo);

    if (null == plana) {
      // 要删除的记录不存在
      addActionError("ERR_A10001");
    } else {
      int ret = tourPlanService.txDeletePlan(plana, user.getUserId(), note);
      if (ret != 0)
        addActionError("删除失败！");
    }
    return SUCCESS;
  }

  public String getKenRrouteName() {
    return kenLineName;
  }

  public void setKenRrouteName(String kenRrouteName) {
    this.kenLineName = kenRrouteName;
  }

  public long getKenDepartment() {
    return kenTeamId;
  }

  public void setKenDepartment(long teamId) {
    this.kenTeamId = teamId;
  }

  public Date getKenEndDate() {
    return kenEndDate;
  }

  public void setKenEndDate(Date kenEndDate) {
    this.kenEndDate = kenEndDate;
  }

  public Long getKenPrincipal() {
    return kenUserId;
  }

  public void setKenPrincipal(Long kenPrincipal) {
    this.kenUserId = kenPrincipal;
  }

  public Date getKenStartDate() {
    return kenStartDate;
  }

  public void setKenStartDate(Date kenStartDate) {
    this.kenStartDate = kenStartDate;
  }

  public String getRecordNo() {
    return recordNo;
  }

  public void setRecordNo(String recordNo) {
    this.recordNo = recordNo;
  }

  public Plan getPlan() {
    return plan;
  }

  public void setPlan(Plan plan) {
    this.plan = plan;
  }

  public List<Team> getTeamList() {
    return teamList;
  }

  public List<Employee> getEmployees() {
    return employees;
  }

  public List<Plan> getPlans() {
    return plans;
  }

  public List<LabelValueBean> getDeploys() {
    return deploys;
  }

  public List<LabelValueBean> getFavourables() {
    return favourables;
  }

  public Line getRoute() {
    return lineNo;
  }

  public void setRoute(Line route) {
    this.lineNo = route;
  }

  public Date getHisOutDate() {
    return hisOutDate;
  }

  public void setHisOutDate(Date hisOutDate) {
    this.hisOutDate = hisOutDate;
  }

  public void setFavourables(List<LabelValueBean> favourables) {
    this.favourables = favourables;
  }

  public List<LabelValueBean> getPaxkey() {
    return paxkey;
  }

  public void setPaxkey(List<LabelValueBean> paxkey) {
    this.paxkey = paxkey;
  }

  public String[] getSelects() {
    return selects;
  }

  public void setSelects(String[] selects) {
    this.selects = selects;
  }

  public void setNote(String note) {
    this.note = note;
  }

}
