package com.opentravelsoft.action.manage.product;

import java.util.ArrayList;
import java.util.List;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.common.TeamType;
import com.opentravelsoft.entity.City;
import com.opentravelsoft.entity.Destination;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Line;
import com.opentravelsoft.entity.PortalCategory;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.service.product.LineService;
import com.opentravelsoft.service.setting.EmployeeService;

/**
 * 添加新线路（add,delete）
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:54 $
 */
public class AddLineAction extends ManageAction {

  private static final long serialVersionUID = 7671898914387730451L;

  private LineService lineService;

  private EmployeeService employeeService;

  /** 线路号 */
  private String lineNo;

  private Line line;

  private List<City> outCityList;

  private List<Destination> destinationList;

  private List<LabelValueBean> vehicleList;

  private List<PortalCategory> webNavigationList;

  private List<LabelValueBean> keyContentList;

  /** 部门列表 */
  private List<Team> teamList;

  /** 线路状态(关闭，试用) */
  private List<LabelValueBean> closeKeyList;

  /** 入境口岸 */
  private List<LabelValueBean> portOfEntryList;

  /** 出境口岸 */
  private List<LabelValueBean> portOfDepartureList;

  private List<Employee> employees = new ArrayList<Employee>();

  // -------------------------------------------------------------------------
  // 检索条件

  /** 专管员 */
  private String kenUserId;

  private String kenClasskey;

  /** 部门编码 */
  private String kenDepartmentNo;

  /** 线路名称 */
  private String kenRouteName;

  private String kenClosekey;

  @Autowired
  public void setRouteService(LineService routeService) {
    this.lineService = routeService;
  }

  @Autowired
  public void setEmployeeService(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  public AddLineAction() {
    line = new Line();
    // *团队旅游 自由行
    line.setClassKeyContent("1");
  }

  public String input() {
    Employee user = getUser();

    closeKeyList = getSysList("DOM_ProductActive");
    keyContentList = getCodeList("ebiz_route_class_content");
    teamList = lineService.getTeamList(user.getUserId(), TeamType.Product);
    if (teamList.size() > 0) {
      employees = employeeService.getUserByTeam(teamList.get(0).getTeamId());
      line.setTeam(teamList.get(0));
    }

    outCityList = lineService.getCity();
    destinationList = lineService.getDestination();
    vehicleList = lineService.getVehicle();
    webNavigationList = lineService.getWebNavigation();
    portOfEntryList = getCodeList("ebiz_route_portOfEntry_Departure");
    portOfDepartureList = getCodeList("ebiz_route_portOfEntry_Departure");

    return SUCCESS;
  }

  /**
   * 新线路保存
   * 
   * @return
   * @throws Exception
   */
  public String add() {
    Employee user = getUser();

    line.setCreateUserId(user.getUserId());
    lineNo = lineService.txInsertLine(line);
    if (null == lineNo) {
      addActionMessage("add success");
    } else {
      addActionMessage("add failure");
    }

    return SUCCESS;
  }

  public String delete() {
    int ret = lineService.txDeleteLine(lineNo);

    if (ret == 0) {
      addActionMessage("delete success");
    } else if (ret == -1) {
      addActionError("线路不存在.");
    } else if (ret == -2) {
      addActionError("线路包含出团计划，不得删除.");
    } else {
      addActionError("线路删除失败.");
    }

    return SUCCESS;
  }

  public Line getRoute() {
    return line;
  }

  public void setRoute(Line route) {
    this.line = route;
  }

  public List<Team> getTeamList() {
    return teamList;
  }

  public List<Destination> getDestinationList() {
    return destinationList;
  }

  public String getRouteNo() {
    return lineNo;
  }

  public void setRouteNo(String routeNo) {
    this.lineNo = routeNo;
  }

  public List<LabelValueBean> getVehicleList() {
    return vehicleList;
  }

  public List<PortalCategory> getWebNavigationList() {
    return webNavigationList;
  }

  public LineService getLineService() {
    return lineService;
  }

  public List<LabelValueBean> getCloseKeyList() {
    return closeKeyList;
  }

  public List<City> getOutCityList() {
    return outCityList;
  }

  public List<LabelValueBean> getKeyContentList() {
    return keyContentList;
  }

  public String getKenClasskey() {
    return kenClasskey;
  }

  public void setKenClasskey(String kenClasskey) {
    this.kenClasskey = kenClasskey;
  }

  public String getKenClosekey() {
    return kenClosekey;
  }

  public void setKenClosekey(String kenClosekey) {
    this.kenClosekey = kenClosekey;
  }

  public String getKenDepartmentNo() {
    return kenDepartmentNo;
  }

  public void setKenDepartmentNo(String kenDepartmentNo) {
    this.kenDepartmentNo = kenDepartmentNo;
  }

  public String getKenRouteName() {
    return kenRouteName;
  }

  public void setKenRouteName(String kenRouteName) {
    this.kenRouteName = kenRouteName;
  }

  public String getKenUserId() {
    return kenUserId;
  }

  public void setKenUserId(String kenUserId) {
    this.kenUserId = kenUserId;
  }

  public List<LabelValueBean> getPortOfEntryList() {
    return portOfEntryList;
  }

  public List<LabelValueBean> getPortOfDepartureList() {
    return portOfDepartureList;
  }

  public List<Employee> getEmployees() {
    return employees;
  }

}
