package com.opentravelsoft.action.manage.operator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.common.SessionKeyParams;
import com.opentravelsoft.entity.City;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.Tourist;
import com.opentravelsoft.service.operator.TourService;
import com.opentravelsoft.service.operator.TouristService;
import com.opentravelsoft.service.order.BookingService;
import com.opentravelsoft.service.setting.EmployeeService;

/**
 * 团处理 - 修改客人信息
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 */
public class EditTouristInfoAction extends ManageAction {
  private static final long serialVersionUID = 7384681404062382613L;

  @Autowired
  private TouristService touristService;

  @Autowired
  private EmployeeService employeeService;

  @Autowired
  private TourService tourService;

  @Autowired
  private BookingService bookingService;

  private List<Tourist> customerList = new ArrayList<Tourist>();

  private List<Employee> userList;

  /** 性别选择列表 */
  private List<LabelValueBean> sexList = new ArrayList<LabelValueBean>();

  /** 出生地选择列表 */
  private List<LabelValueBean> birthPlaceList = new ArrayList<LabelValueBean>();

  /** 护照签发地列表 */
  private List<LabelValueBean> passportPlaceList = new ArrayList<LabelValueBean>();

  /** 团号 */
  private String tourNo;

  private Plan tour;

  private List<City> portCitys;

  /** 备注 */
  private String note;

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  /**
   * 修改信息初始化
   */
  public String input() {
    Object obj = ActionContext.getContext().getSession()
        .get(SessionKeyParams.EBIZ_CURRENT_TOUR);
    if (null != obj) {
      tourNo = ((Plan) obj).getTourNo();
    }

    // 取得计划信息
    Plan plan = touristService.roGetPlanDetail(tourNo);

    tour = tourService.roGetTourInfo(tourNo, true, false);
    portCitys = tourService.roGetPortCitys();
    // for (City city : portCitys)
    // {
    // if (tour.getOutCity().equals(city.getCitycd()))
    // tour.setOutCity(city.getCitynm());
    // if (tour.getInCity().equals(city.getCitycd()))
    // tour.setInCity(city.getCitynm());
    // if (tour.getVenue().equals(city.getCitycd()))
    // tour.setVenue(city.getCitynm());
    // }
    sexList = getSysList("DOM_sex");
    userList = employeeService.roGetSalesList();
    for (Employee employee : userList) {
      if (tour.getOpUser() == employee.getUserId())
        tour.setOpUserName(employee.getUserName());
    }
    passportPlaceList = touristService.roGetPassportPlaceList();
    birthPlaceList = bookingService.roGetBirthplaceList();
    Map<String, String> birthPlace = new HashMap<String, String>();
    Map<String, String> passportPlace = new HashMap<String, String>();
    for (LabelValueBean lbn : birthPlaceList) {
      birthPlace.put(lbn.getLabel(), lbn.getValue());
    }
    for (LabelValueBean bp : passportPlaceList) {
      passportPlace.put(bp.getLabel(), bp.getValue());
    }
    for (Tourist item : tour.getCustomerList()) {
      item.setBirthplaceName(birthPlace.get(item.getBirthplace()));
      item.setPassportPlaceName(passportPlace.get(item.getPassportPlace()));

    }
    customerList = tour.getCustomerList();

    return INPUT;
  }

  /**
   * 修改信息
   * 
   * @return
   */
  public String modify() {
    Employee user = getUser();
    int realPax = 0;
    for (Tourist trip : customerList) {

      if (trip.getUserName().trim().length() == 0)
        realPax++;
    }
    if (realPax != 0) {
      addActionError("客人的名字必须填写.");
      sexList = getSysList("DOM_sex");
      return INPUT;
    }

    Object obj = ActionContext.getContext().getSession()
        .get(SessionKeyParams.EBIZ_CURRENT_TOUR);
    if (null != obj) {
      tourNo = ((Plan) obj).getTourNo();
    }

    // 修改游客信息
    int ret = touristService.txModifyCustomerInfo(customerList, tourNo, note,
        user.getUserId());

    if (ret == -1) {
      addActionError("修改失败!");
      sexList = getSysList("DOM_sex");
      return INPUT;
    } else
      addActionMessage("修改成功!");

    return SUCCESS;
  }

  public String getTourNo() {
    return tourNo;
  }

  public void setTourNo(String tourNo) {
    this.tourNo = tourNo;
  }

  public Plan getTour() {
    return tour;
  }

  public void setTour(Plan tour) {
    this.tour = tour;
  }

  public List<City> getPortCitys() {
    return portCitys;
  }

  public void setPortCitys(List<City> portCitys) {
    this.portCitys = portCitys;
  }

  public List<Tourist> getCustomerList() {
    return customerList;
  }

  public void setCustomerList(List<Tourist> customerList) {
    this.customerList = customerList;
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

  public List<Employee> getUserList() {
    return userList;
  }

  public void setUserList(List<Employee> userList) {
    this.userList = userList;
  }

}
