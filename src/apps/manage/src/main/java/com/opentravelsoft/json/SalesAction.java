package com.opentravelsoft.json;

import java.util.ArrayList;
import java.util.List;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.service.setting.EmployeeService;

public class SalesAction extends ManageAction {
  private static final long serialVersionUID = 7124427424744702716L;

  private EmployeeService employeeService;

  private int teamId;

  private List<LabelValueBean> map = new ArrayList<LabelValueBean>();

  public String execute() {
    List<Employee> list = employeeService.roGetSalesByTeam(teamId);
    for (Employee customer : list) {
      map.add(new LabelValueBean(String.valueOf(customer.getUserId()), customer
          .getUserName()));
    }
    return SUCCESS;
  }

  public void setTeamId(int teamId) {
    this.teamId = teamId;
  }

  public List<LabelValueBean> getMap() {
    return map;
  }

  @Autowired
  public void setEmployeeService(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

}
