package com.opentravelsoft.action.manage.product;

import java.util.ArrayList;
import java.util.List;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.common.TeamType;
import com.opentravelsoft.entity.Destination;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.entity.Line;
import com.opentravelsoft.service.product.LineService;
import com.opentravelsoft.service.setting.EmployeeService;
import com.opentravelsoft.util.PaginationSupport;

/**
 * 线路查询
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:54 $
 */
public class ListLineAction extends ManageAction {

  private static final long serialVersionUID = 7671898914387730451L;

  @Autowired
  private LineService lineService;

  @Autowired
  private EmployeeService employeeService;

  private List<Employee> employeeList;

  private List<Team> teamList;

  private List<Line> lineList = new ArrayList<Line>();

  private List<LabelValueBean> closekeyList;

  /** 目的地列表 */
  private List<Destination> destinationList;

  // -------------------------------------------------------------------------
  // 检索条件

  /** 专管员 */
  private int kenUserId = 0;

  private String kenClasskey = "";

  private int kenTeamId = 0;

  private String kenLineName = "";

  private String kenActive = Boolean.TRUE.toString();

  private String kenDestination;

  /**
   * 页面初始化
   */
  public String input() {
    Employee user = getUser();

    closekeyList = getSysList("DOM_ProductActive");
    closekeyList.add(new LabelValueBean("全部", ""));
    kenUserId = user.getUserId();
    teamList = lineService.getTeamList(kenUserId, TeamType.Product);
    if (teamList.size() > 0)
      kenTeamId = teamList.get(0).getTeamId();
    employeeList = employeeService.getUserByTeam(kenTeamId);
    destinationList = lineService.getDestination();

    // List all line for this user
    PaginationSupport support = lineService.findLineList(0, kenLineName,
        kenActive, kenUserId, kenDestination, getFromRecord(), getMoveCount());
    lineList = support.getItems();
    if (lineList != null)
      currentPage(lineList.size());

    return INPUT;
  }

  /**
   * 条件查询线路
   * 
   * @return
   * @throws Exception
   */
  public String submit() {
    Employee user = getUser();
    closekeyList = getSysList("DOM_ProductActive");
    closekeyList.add(new LabelValueBean("全部", ""));
    destinationList = lineService.getDestination();

    PaginationSupport support = lineService.findLineList(kenTeamId,
        kenLineName, kenActive, kenUserId, kenDestination, getFromRecord(),
        getMoveCount());

    lineList = support.getItems();
    if (lineList != null)
      currentPage(lineList.size());
    employeeList = employeeService.getUserByTeam(kenTeamId);
    teamList = lineService.getTeamList(user.getUserId(), TeamType.Product);

    return SUCCESS;
  }

  public List<Team> getTeamList() {
    return teamList;
  }

  public List<Employee> getEmployeeList() {
    return employeeList;
  }

  public LineService getLineService() {
    return lineService;
  }

  public List<LabelValueBean> getClosekeyList() {
    return closekeyList;
  }

  public String getKenClasskey() {
    return kenClasskey;
  }

  public void setKenClasskey(String kenClasskey) {
    this.kenClasskey = kenClasskey;
  }

  public String getKenClosekey() {
    return kenActive;
  }

  public void setKenClosekey(String kenClosekey) {
    this.kenActive = kenClosekey;
  }

  public int getKenDepartmentNo() {
    return kenTeamId;
  }

  public void setKenDepartmentNo(int teamId) {
    this.kenTeamId = teamId;
  }

  public String getKenRouteName() {
    return kenLineName;
  }

  public void setKenRouteName(String kenRouteName) {
    this.kenLineName = kenRouteName;
  }

  public int getKenUserId() {
    return kenUserId;
  }

  public void setKenUserId(int kenUserId) {
    this.kenUserId = kenUserId;
  }

  public List<Line> getRouteList() {
    return lineList;
  }

  public void setRouteList(List<Line> routeList) {
    this.lineList = routeList;
  }

  public List<Destination> getDestinationList() {
    return destinationList;
  }

  public String getKenDestination() {
    return kenDestination;
  }

  public void setKenDestination(String kenDestination) {
    this.kenDestination = kenDestination;
  }

}
