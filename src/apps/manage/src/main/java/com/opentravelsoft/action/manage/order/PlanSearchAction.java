package com.opentravelsoft.action.manage.order;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.common.TeamType;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.service.order.PlanSearchService;
import com.opentravelsoft.service.setting.EmployeeService;

/**
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.2 $ $Date: 2009/04/10 07:47:32 $
 */
public class PlanSearchAction extends ManageAction {
  private static final long serialVersionUID = -7625855842902512964L;

  @Autowired
  private PlanSearchService planSearchService;

  @Autowired
  private EmployeeService employeeService;

  private List<Employee> employeeList;

  private List<Team> teamList;

  // -------------------------------------------------------------------------
  // 检索条件

  /** 专管员 */
  private long kenUserId;

  /** 部门 */
  private long kenTeamId;

  /** 线路名 */
  private String kenLineName;

  /** 出团日期 -开始 */
  private Date kenStartDatePeriod;

  /** 出团日期 -截止 */
  private Date kenEndDatePeriod;

  /** 出团日期 -开始 */
  private Date kenOrderStartPeriod;

  /** 出团日期 -截止 */
  private Date kenOrderEndPeriod;

  private List<Plan> plans = new ArrayList<Plan>();

  public String input() throws Exception {
    Employee user = getUser();
    teamList = planSearchService.getTeamList(user.getUserId(),
        TeamType.Operator);
    if (teamList.size() > 0)
      kenTeamId = teamList.get(0).getTeamId();

    kenUserId = user.getUserId();
    employeeList = employeeService.getUserByTeam(kenTeamId);
    Calendar calc = Calendar.getInstance();
    kenStartDatePeriod = new Date();
    calc.setTime(kenStartDatePeriod);
    calc.add(Calendar.MONTH, 1);
    kenEndDatePeriod = calc.getTime();

    return INPUT;
  }

  public String submit() throws Exception {
    Employee user = getUser();
    plans = planSearchService.roFind(kenLineName, kenTeamId, kenUserId,
        kenStartDatePeriod, kenEndDatePeriod, 0f, 0f, false);
    currentPage(plans.size());

    employeeList = employeeService.getUserByTeam(kenTeamId);
    teamList = planSearchService.getTeamList(user.getUserId(),
        TeamType.Operator);
    return SUCCESS;
  }

  protected int getMoveCount() {
    return 20;
  }

  public List<Plan> getPlans() {
    return plans;
  }

  public Date getKenEndDatePeriod() {
    return kenEndDatePeriod;
  }

  public void setKenEndDatePeriod(Date kenEndDatePeriod) {
    this.kenEndDatePeriod = kenEndDatePeriod;
  }

  public String getKenRouteName() {
    return kenLineName;
  }

  public void setKenRouteName(String kenRouteName) {
    this.kenLineName = kenRouteName;
  }

  public Date getKenStartDatePeriod() {
    return kenStartDatePeriod;
  }

  public void setKenStartDatePeriod(Date kenStartDatePeriod) {
    this.kenStartDatePeriod = kenStartDatePeriod;
  }

  public List<Team> getTeamList() {
    return teamList;
  }

  public List<Employee> getEmployeeList() {
    return employeeList;
  }

  public long getKenDepartmentNo() {
    return kenTeamId;
  }

  public void setKenDepartmentNo(long teamId) {
    this.kenTeamId = teamId;
  }

  public long getKenUserId() {
    return kenUserId;
  }

  public void setKenUserId(long kenUserId) {
    this.kenUserId = kenUserId;
  }

  public void setKenUserId(String kenUserId) {
    this.kenUserId = Long.parseLong(kenUserId);
  }

  public Date getKenOrderEndPeriod() {
    return kenOrderEndPeriod;
  }

  public void setKenOrderEndPeriod(Date kenOrderEndPeriod) {
    this.kenOrderEndPeriod = kenOrderEndPeriod;
  }

  public Date getKenOrderStartPeriod() {
    return kenOrderStartPeriod;
  }

  public void setKenOrderStartPeriod(Date kenOrderStartPeriod) {
    this.kenOrderStartPeriod = kenOrderStartPeriod;
  }

}
