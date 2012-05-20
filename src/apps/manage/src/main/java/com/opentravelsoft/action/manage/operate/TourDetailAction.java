package com.opentravelsoft.action.manage.operate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.City;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.entity.Tourist;
import com.opentravelsoft.service.operator.TourService;
import com.opentravelsoft.service.setting.EmployeeService;

/**
 * 并团处理
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.2 $ $Date: 2009/04/10 07:47:29 $
 */
public class TourDetailAction extends ManageAction {
  private static final long serialVersionUID = 8751890782466429464L;

  @Autowired
  private TourService tourService;

  @Autowired
  private EmployeeService employeeService;

  private Plan tour;

  private String tourNo;

  private String tourNum;

  private String tourNumber;

  private int length;

  /** 查询客人结果 */
  private List<Tourist> tcustomerList = new ArrayList<Tourist>();

  private String[] nameNo;

  /** 名单号 */
  private String nmno;

  /** 是否已成团 */
  private char nameKey;

  private String lineNo;

  /** 部门列表 */
  private List<Team> teamList;

  /** 员工列表 */
  private List<Employee> employees;

  private List<LabelValueBean> nameKeys;

  private List<Plan> tours = new ArrayList<Plan>();

  private List<String> orderList = new ArrayList<String>();

  private List<String> routeList = new ArrayList<String>();

  private List<String> outDateList = new ArrayList<String>();

  private List<City> portCitys;

  private List<Employee> userList;

  /** 性别选择列表 */
  private List<LabelValueBean> sexList = new ArrayList<LabelValueBean>();

  /** 出生地选择列表 */
  private List<LabelValueBean> birthPlaceList = new ArrayList<LabelValueBean>();

  /** 护照签发地列表 */
  private List<LabelValueBean> passportPlaceList = new ArrayList<LabelValueBean>();

  protected SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMdd");

  // -------------------------------------------------------------------------
  // 查询条件
  /** 部门编号 */
  private int kenTeamId;

  /** 专管员 */
  private int kenUserId;

  /** 线路 */
  private String kenLineName;

  /** 出发开始时间 */
  private Date kenStartDate;

  /** 出发截止时间 */
  private Date kenEndDate;

  /** 建团人 */
  private int kenCreator;

  // -------------------------------------------------------------------------
  public TourDetailAction() {
    nameKey = 'N';
    kenTeamId = 0;
    kenUserId = 0;
  }

  /**
   * 查找团信息
   * 
   * @return
   */
  public String execute() {
    tour = tourService.roGetTourInfo(tourNo, true, false);

    portCitys = tourService.roGetPortCitys();
    userList = employeeService.getEmployees(false);
    sexList = getSysList("DOM_sex");
    birthPlaceList = tourService.roGetBirthplaceList();
    passportPlaceList = tourService.roGetPassportPlaceList();

    Map<String, String> birthPlace = new HashMap<String, String>();
    Map<String, String> passportPlace = new HashMap<String, String>();
    for (LabelValueBean lbn : birthPlaceList) {
      birthPlace.put(lbn.getLabel(), lbn.getValue());
    }
    for (LabelValueBean lbn : passportPlaceList) {
      passportPlace.put(lbn.getLabel(), lbn.getValue());
    }

    for (Tourist item : tour.getCustomerList()) {
      item.setBirthplaceName(birthPlace.get(item.getBirthplace()));
      item.setPassportPlaceName(passportPlace.get(item.getPassportPlace()));
      item.setSex(item.getSex());
    }

    return SUCCESS;
  }

  public List<Plan> getTours() {
    return tours;
  }

  public void setTours(List<Plan> tours) {
    this.tours = tours;
  }

  public List<LabelValueBean> getNameKeys() {
    return nameKeys;
  }

  public char getNameKey() {
    return nameKey;
  }

  public void setNameKey(char nameKey) {
    this.nameKey = nameKey;
  }

  public String getKenRouteName() {
    return kenLineName;
  }

  public void setKenRouteName(String kenRouteName) {
    this.kenLineName = kenRouteName;
  }

  public List<Team> getTeamList() {
    return teamList;
  }

  public List<Employee> getEmployees() {
    return employees;
  }

  public void setEmployees(List<Employee> employees) {
    this.employees = employees;
  }

  public String getNmno() {
    return nmno;
  }

  public void setNmno(String nmno) {
    this.nmno = nmno;
  }

  public List<Tourist> getTcustomerList() {
    return tcustomerList;
  }

  public void setTcustomerList(List<Tourist> tcustomerList) {
    this.tcustomerList = tcustomerList;
  }

  public List<String> getOrderList() {
    return orderList;
  }

  public List<String> getRouteList() {
    return routeList;
  }

  public List<String> getOutDateList() {
    return outDateList;
  }

  public String getTourNo() {
    return tourNo;
  }

  public void setTourNo(String tourNo) {
    this.tourNo = tourNo;
  }

  public String getTourNum() {
    return tourNum;
  }

  public void setTourNum(String tourNum) {
    this.tourNum = tourNum;
  }

  public Integer getKenCreator() {
    return kenCreator;
  }

  public void setKenCreator(Integer kenCreator) {
    this.kenCreator = kenCreator;
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

  public int getLength() {
    return length;
  }

  public void setLength(int length) {
    this.length = length;
  }

  public String getRouteNo() {
    return lineNo;
  }

  public void setRouteNo(String lineNo) {
    this.lineNo = lineNo;
  }

  public Plan getTour() {
    return tour;
  }

  public void setTour(Plan tour) {
    this.tour = tour;
  }

  public List<LabelValueBean> getSexList() {
    return sexList;
  }

  public void setSexList(List<LabelValueBean> sexList) {
    this.sexList = sexList;
  }

  public List<LabelValueBean> getBirthPlaceList() {
    return birthPlaceList;
  }

  public void setBirthPlaceList(List<LabelValueBean> birthPlaceList) {
    this.birthPlaceList = birthPlaceList;
  }

  public List<LabelValueBean> getPassportPlaceList() {
    return passportPlaceList;
  }

  public void setPassportPlaceList(List<LabelValueBean> passportPlaceList) {
    this.passportPlaceList = passportPlaceList;
  }

  public List<City> getPortCitys() {
    return portCitys;
  }

  public void setPortCitys(List<City> portCitys) {
    this.portCitys = portCitys;
  }

  public List<Employee> getUserList() {
    return userList;
  }

  public void setUserList(List<Employee> userList) {
    this.userList = userList;
  }

  public int getKenDepartmentId() {
    return kenTeamId;
  }

  public void setKenDepartmentId(int kenDepartmentId) {
    this.kenTeamId = kenDepartmentId;
  }

  public int getKenEmployeeId() {
    return kenUserId;
  }

  public void setKenEmployeeId(int kenEmployeeId) {
    this.kenUserId = kenEmployeeId;
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

}
