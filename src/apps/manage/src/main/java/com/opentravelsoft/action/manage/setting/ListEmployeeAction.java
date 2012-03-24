package com.opentravelsoft.action.manage.setting;

import java.util.ArrayList;
import java.util.List;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.entity.Group;
import com.opentravelsoft.service.setting.EmployeeService;
import com.opentravelsoft.util.StringUtil;

/**
 * 参数设置:用户维护
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.3 $ $Date: 2009/04/10 07:47:29 $
 */
public class ListEmployeeAction extends ManageAction {
  private static final long serialVersionUID = 7671898914387730451L;

  private List<LabelValueBean> sexList = new ArrayList<LabelValueBean>();

  private List<LabelValueBean> userCloseKeyList;

  private EmployeeService employeeService;

  private List<Team> maketorgList = new ArrayList<Team>();

  /** 用户代码 */
  private int userId;

  // -------------------------------------------------------------------------
  // 查询条件

  /** 部门号 */
  private int groupId;

  /** 用户 */
  private String kenUserName;

  // -------------------------------------------------------------------------

  private List<Employee> employeeList;

  private List<Group> groupList;

  @Override
  public String input() {
    employeeList = employeeService.getEmployees(groupId, kenUserName);
    groupList = employeeService.getAllDepartments();
    currentPage(employeeList.size());

    return INPUT;
  }

  /**
   * 将用户状态改为关闭，非物理删除
   * 
   * @return
   */
  public String delete() {
    employeeService.txDeleteEmployee(userId);
    return SUCCESS;
  }

  @Autowired
  public void setEmployeeService(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  public List<Group> getDepartmentList() {
    return groupList;
  }

  public int getGroupId() {
    return groupId;
  }

  public void setGroupId(int groupId) {
    this.groupId = groupId;
  }

  public void setGroupId(String groupId) {
    if (StringUtil.hasLength(groupId))
      this.groupId = Integer.parseInt(groupId);
  }

  public List<Employee> getEmployeeList() {
    return employeeList;
  }

  public String getKenUserName() {
    return kenUserName;
  }

  public void setKenUserName(String kenUserName) {
    this.kenUserName = kenUserName;
  }

  public void setEmployeeList(List<Employee> employeeList) {
    this.employeeList = employeeList;
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

  public List<Team> getMaketorgList() {
    return maketorgList;
  }

  public void setMaketorgList(List<Team> maketorgList) {
    this.maketorgList = maketorgList;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public List<Group> getGroupList() {
    return groupList;
  }

  public List<LabelValueBean> getSexList() {
    return sexList;
  }

}
