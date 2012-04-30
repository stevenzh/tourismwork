package com.opentravelsoft.action.manage.operator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.task.Task;
import com.opentravelsoft.util.LabelValueBean;
import com.opentravelsoft.workflow.TaskDao;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.common.SessionKeyParams;
import com.opentravelsoft.entity.City;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.Tourist;
import com.opentravelsoft.service.operator.TourService;
import com.opentravelsoft.service.setting.EmployeeService;

/**
 * 团基本信息(团处理)
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:00 $
 */
public class TourOperateAction extends ManageAction {
  private static final long serialVersionUID = -4141009845765575704L;

  @Autowired
  private TourService tourService;

  @Autowired
  private TaskDao taskService;

  @Autowired
  private EmployeeService employeeSevice;

  private String kenTeamId;

  private String kenEmployeeId;

  /** 出团日期 -开始 */
  private Date kenStartDate;

  /** 出团日期 -截止 */
  private Date kenEndDate;

  /** 团号 */
  private String tourNo;

  private Plan tour;

  // -------------------------------------------------------------------------

  private List<City> portCitys;

  private List<Employee> userList;

  /** 性别选择列表 */
  private List<LabelValueBean> sexList = new ArrayList<LabelValueBean>();

  /** 出生地选择列表 */
  private List<LabelValueBean> birthPlaceList = new ArrayList<LabelValueBean>();

  /** 护照签发地列表 */
  private List<LabelValueBean> passportPlaceList = new ArrayList<LabelValueBean>();

  /** 是否需要安排领队 */
  private List<LabelValueBean> needLeaderList;

  /** 团处理的任务列表 */
  private List<Task> taskList = new ArrayList<Task>();

  protected SimpleDateFormat SDF2 = new SimpleDateFormat("yyyy-MM-dd");

  /** 备注 */
  private String note;

  @Override
  public String input() throws Exception {
    Map<String, Object> session = ActionContext.getContext().getSession();

    Object obj = session.get(SessionKeyParams.EBIZ_CURRENT_TOUR);
    if (null == tourNo)
      tourNo = ((Plan) obj).getTourNo();
    tour = tourService.roGetTourInfo(tourNo, true, true);

    // 是否需要安排领队
    needLeaderList = getCodeList("ebiz_tour_leader_list");
    portCitys = tourService.roGetPortCitys();
    userList = employeeSevice.getEmployees(false);
    sexList = getSysList("DOM_sex");
    birthPlaceList = tourService.roGetBirthplaceList();
    passportPlaceList = tourService.roGetPassportPlaceList();
    // taskList = taskService.roGetTaskList(tour.getPlanNo());

    session.put(SessionKeyParams.EBIZ_CURRENT_TOUR, tour);

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
      item.setSex(item.getSex().equals("M") ? "男" : "女");
    }

    if (tour.getPax() != tour.getCustomerList().size()) {
      addActionMessage("团信息中所登记的人数与实际客人不符,点击“保存”按钮调整人数.");
    }

    return INPUT;
  }

  public String submit() {
    Employee user = getUser();
    tour.setOpUser(user.getUserId());
    int result = tourService.txSaveTour(tour, note);
    if (result != 0) {
      addActionError("保存信息失败，");
    }

    addActionMessage("团信息更新成功!");
    return SUCCESS;
  }

  public String cancel() {
    Employee user = getUser();
    tourService.txCancelTour(tourNo, note, user.getUserId());
    addActionMessage("团队取消成功!");
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

  public List<City> getPortCitys() {
    return portCitys;
  }

  public List<Employee> getUserList() {
    return userList;
  }

  public List<LabelValueBean> getBirthPlaceList() {
    return birthPlaceList;
  }

  public List<LabelValueBean> getPassportPlaceList() {
    return passportPlaceList;
  }

  public List<LabelValueBean> getSexList() {
    return sexList;
  }

  public void setTour(Plan tour) {
    this.tour = tour;
  }

  public void setPortCitys(List<City> portCitys) {
    this.portCitys = portCitys;
  }

  public void setUserList(List<Employee> userList) {
    this.userList = userList;
  }

  public void setBirthPlaceList(List<LabelValueBean> birthPlaceList) {
    this.birthPlaceList = birthPlaceList;
  }

  public void setPassportPlaceList(List<LabelValueBean> passportPlaceList) {
    this.passportPlaceList = passportPlaceList;
  }

  public String getKenDepartmentId() {
    return kenTeamId;
  }

  public void setKenDepartmentId(String kenDepartmentId) {
    this.kenTeamId = kenDepartmentId;
  }

  public String getKenEmployeeId() {
    return kenEmployeeId;
  }

  public void setKenEmployeeId(String kenEmployeeId) {
    this.kenEmployeeId = kenEmployeeId;
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

  public List<LabelValueBean> getNeedLeaderList() {
    return needLeaderList;
  }

  public List<Task> getTaskList() {
    return taskList;
  }

  public void setNote(String note) {
    this.note = note;
  }

}
