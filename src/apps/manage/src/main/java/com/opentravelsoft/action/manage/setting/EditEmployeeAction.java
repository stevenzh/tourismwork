package com.opentravelsoft.action.manage.setting;

import java.util.ArrayList;
import java.util.List;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.entity.Group;
import com.opentravelsoft.model.Role;
import com.opentravelsoft.service.setting.EmployeeService;
import com.opentravelsoft.util.MD5;
import com.opentravelsoft.util.StringUtil;

/**
 * 参数设置:用户维护
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.3 $ $Date: 2009/04/10 07:47:29 $
 */
public class EditEmployeeAction extends ManageAction {
  private static final long serialVersionUID = 7671898914387730451L;

  @Autowired
  private EmployeeService employeeService;

  private List<LabelValueBean> sexList = new ArrayList<LabelValueBean>();

  private List<LabelValueBean> userCloseKeyList;

  /** 用户代码 */
  private int userId;

  private Employee employee = new Employee();

  // -------------------------------------------------------------------------
  // 查询条件

  /** 部门号 */
  private String groupId;

  /** 用户 */
  private String kenUserName;

  // -------------------------------------------------------------------------

  private List<Group> groupList;

  private List<Role> roles;

  private List<Team> teamList;

  private List<String> destination;

  public String input() {
    groupList = employeeService.getAllDepartments();
    teamList = employeeService.roGetAllTeams();
    roles = employeeService.roGetRoles();
    userCloseKeyList = getSysList("DOM_ProductActive");
    sexList = getSysList("DOM_sex");

    if (userId != 0)
      employee = employeeService.roGetEmployee(userId);
    else

      employee = new Employee();

    return INPUT;
  }

  /**
   * 修改提交
   * 
   * @return
   */
  public String submit() {
    if (StringUtil.hasLength(employee.getPasswd())) {
      MD5 md5 = new MD5();
      employee.setPasswd(md5.getMD5ofStr(employee.getPasswd()).toLowerCase());
    }

    if (userId != 0)
      employeeService.txUpdateEmployee(employee);
    else
      employeeService.txInsertEmployee(employee);

    return SUCCESS;
  }

  public String changePwd() {
    return SUCCESS;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

  public List<Group> getDepartmentList() {
    return groupList;
  }

  public String getGroupId() {
    return groupId;
  }

  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }

  public String getKenUserName() {
    return kenUserName;
  }

  public void setKenUserName(String kenUserName) {
    this.kenUserName = kenUserName;
  }

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public List<LabelValueBean> getUserCloseKeyList() {
    return userCloseKeyList;
  }

  public void setUserCloseKeyList(List<LabelValueBean> userCloseKeyList) {
    this.userCloseKeyList = userCloseKeyList;
  }

  public void setSalesmanService(EmployeeService salesmanService) {
    this.employeeService = salesmanService;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public List<String> getDestination() {
    return destination;
  }

  public void setDestination(List<String> destination) {
    this.destination = destination;
  }

  public List<Group> getGroupList() {
    return groupList;
  }

  public List<LabelValueBean> getSexList() {
    return sexList;
  }

  public List<Team> getTeamList() {
    return teamList;
  }

}
