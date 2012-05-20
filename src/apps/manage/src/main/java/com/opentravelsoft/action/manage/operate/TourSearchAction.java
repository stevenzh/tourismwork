package com.opentravelsoft.action.manage.operate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.common.TeamType;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.service.operator.TourService;
import com.opentravelsoft.service.setting.EmployeeService;
import com.opentravelsoft.util.StringUtil;

/**
 * 查找团（通用）
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.2 $ $Date: 2009/04/10 07:47:29 $
 */
public class TourSearchAction extends ManageAction {
  private static final long serialVersionUID = 8271983728920463610L;

  protected static final Log logger = LogFactory.getLog(TourSearchAction.class);

  @Autowired
  private TourService tourService;

  @Autowired
  private EmployeeService employeeSevice;

  private int kenTeamId;

  /** 专管员 */
  private int kenUserId;

  /** 线路名 */
  private String kenLineName = "";

  /** 出团日期 -开始 */
  private Date kenStartDate;

  /** 出团日期 -截止 */
  private Date kenEndDate;

  private String tourNum;

  private String[] nameNo;

  private String lineNo;

  private String tourNo;

  private String tourNumber;

  private List<Employee> employees;

  private List<Team> teamList;

  private List<Plan> tours = new ArrayList<Plan>();

  @Override
  public String input() throws Exception {
    Employee user = getUser();
    buildSysdate();

    kenStartDate = systemDate;
    kenUserId = user.getUserId();
    teamList = tourService.getTeamList(kenUserId, TeamType.Operator);
    if (teamList.size() > 0)
      kenTeamId = teamList.get(0).getTeamId();
    employees = employeeSevice.getUserByTeam(kenTeamId);

    return INPUT;
  }

  public String submit() {

    buildSysdate();
    employees = employeeSevice.getUserByTeam(kenTeamId);
    teamList = tourService.getOperatorTeamList();
    // 查找团
    tours = tourService.roGetTours(kenTeamId, kenUserId, kenLineName,
        kenStartDate, kenEndDate);
    currentPage(tours.size());

    return SUCCESS;
  }

  public String getKenDepartmentId() {
    return String.valueOf(kenTeamId);
  }

  public void setKenDepartmentId(String kenDepartmentId) {
    if (StringUtil.hasLength(kenDepartmentId))
      this.kenTeamId = Integer.parseInt(kenDepartmentId);
  }

  public String getKenEmployeeId() {
    return String.valueOf(kenUserId);
  }

  public void setKenEmployeeId(String kenUserId) {
    if (StringUtil.hasLength(kenUserId))
      this.kenUserId = Integer.parseInt(kenUserId);
  }

  public String getKenRouteName() {
    return kenLineName;
  }

  public void setKenRouteName(String kenRouteName) {
    this.kenLineName = kenRouteName;
  }

  public Date getKenStartDate() {
    return kenStartDate;
  }

  public void setKenStartDate(Date kenStartDate) {
    this.kenStartDate = kenStartDate;
  }

  public Date getKenEndDate() {
    return kenEndDate;
  }

  public void setKenEndDate(Date kenEndDate) {
    this.kenEndDate = kenEndDate;
  }

  public List<Employee> getEmployees() {
    return employees;
  }

  public List<Team> getTeamList() {
    return teamList;
  }

  public List<Plan> getTours() {
    return tours;
  }

  public String getTourNo() {
    return tourNo;
  }

  public void setTourNo(String tourNo) {
    this.tourNo = tourNo;
  }

  public String getTourNumber() {
    return tourNumber;
  }

  public void setTourNumber(String tourNumber) {
    this.tourNumber = tourNumber;
  }

  public String[] getNameNo() {
    return nameNo;
  }

  public void setNameNo(String[] nameNo) {
    this.nameNo = nameNo;
  }

  public String getRouteNo() {
    return lineNo;
  }

  public void setRouteNo(String lineNo) {
    this.lineNo = lineNo;
  }

  public String getTourNum() {
    return tourNum;
  }

  public void setTourNum(String tourNum) {
    this.tourNum = tourNum;
  }

}
