package com.opentravelsoft.action.manage.product;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.common.SessionKeyParams;
import com.opentravelsoft.common.TeamType;
import com.opentravelsoft.entity.City;
import com.opentravelsoft.entity.Destination;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.entity.Line;
import com.opentravelsoft.entity.PortalCategory;
import com.opentravelsoft.service.product.LineService;
import com.opentravelsoft.service.setting.EmployeeService;

/**
 * 修改线路基本信息(update)
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.2 $ $Date: 2009/04/10 07:47:29 $
 */
public class EditLineAction extends ManageAction {

  private static final long serialVersionUID = 7671898914387730451L;

  protected static final Log logger = LogFactory.getLog(EditLineAction.class);

  @Autowired
  private LineService lineService;

  @Autowired
  private EmployeeService employeeService;

  /** 线路号 */
  private String lineNo;

  private Line line = new Line();;

  /** 出发城市列表 */
  private List<City> outCityList;

  /** 目的地列表 */
  private List<Destination> destinationList;

  private List<LabelValueBean> vehicleList;

  private List<PortalCategory> webNavigationList;

  private List<LabelValueBean> keyContentList;

  private List<Team> productTeams;

  private List<Team> operatorTeams;

  /** 线路状态(关闭，试用) */
  private List<LabelValueBean> closeKeyList;

  /** 入境口岸 */
  private List<LabelValueBean> portOfEntryList;

  /** 出境口岸 */
  private List<LabelValueBean> portOfDepartureList;

  private List<Employee> employees;

  // -------------------------------------------------------------------------
  // 检索条件

  /** 专管员 */
  private String kenUserId;

  private String kenClasskey;

  /** 部门编码 */
  private String kenGroupName;

  /** 线路名称 */
  private String kenLineName;

  private String kenClosekey;

  private String kenDestination;

  /**
   * 线路修改初始化
   * 
   * @return
   * @throws Exception
   */
  public String input() {
    Employee user = getUser();
    Map<String, Object> session = ActionContext.getContext().getSession();

    logger.debug(user.getUserName() + " 修改线路.ROUTE_NO:" + lineNo);
    if (lineNo != null) {
      line = lineService.getLine(lineNo);
      session.put(SessionKeyParams.EBIZ_CURRENT_ROUTE, line);
    } else {
      Line ru = (Line) session.get(SessionKeyParams.EBIZ_CURRENT_ROUTE);
      logger.debug(user.getUserName() + " 修改线路.ROUTE:" + ru);

      lineNo = ru.getLineNo();
      line = lineService.getLine(lineNo);
    }

    closeKeyList = getSysList("DOM_ProductActive");
    keyContentList = getCodeList("ebiz_route_class_content");
    productTeams = lineService.getTeamList(user.getUserId(), TeamType.Product);
    operatorTeams = lineService.getOperatorTeams();
    employees = employeeService.getUserByTeam(productTeams.get(0).getTeamId());
    outCityList = lineService.getCity();
    destinationList = lineService.getDestination();
    vehicleList = lineService.getVehicle();
    webNavigationList = lineService.getWebNavigation();
    portOfEntryList = getCodeList("ebiz_route_portOfEntry_Departure");
    portOfDepartureList = getCodeList("ebiz_route_portOfEntry_Departure");

    return INPUT;
  }

  /**
   * 线路修改
   * 
   * @return
   * @throws Exception
   */
  public String submit() {
    logger.debug("Save Line" + line.getLineNo());
    Employee user = getUser();
    line.setOperateUserId(user.getUserId());
    int ret = lineService.txModifyLine(line);

    if (ret == 0) {
      addActionMessage("线路修改成功.");
      // success
    } else {
      addActionError("线路修改失败.");
      // failure
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
    return productTeams;
  }

  public List<Destination> getDestinationList() {
    return destinationList;
  }

  public String getRouteNo() {
    return lineNo;
  }

  public void setRouteNo(String lineNo) {
    this.lineNo = lineNo;
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
    return kenGroupName;
  }

  public void setKenDepartmentNo(String kenDepartmentNo) {
    this.kenGroupName = kenDepartmentNo;
  }

  public String getKenRouteName() {
    return kenLineName;
  }

  public void setKenRouteName(String kenRouteName) {
    this.kenLineName = kenRouteName;
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

  public List<Team> getOperatorTeams() {
    return operatorTeams;
  }

  public List<Team> getProductTeams() {
    return productTeams;
  }

  public String getKenDestination() {
    return kenDestination;
  }

  public void setKenDestination(String kenDestination) {
    this.kenDestination = kenDestination;
  }

  public List<Employee> getEmployees() {
    return employees;
  }

}
