package com.opentravelsoft.action.manage.finance;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Customer;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.entity.product.Warrant;
import com.opentravelsoft.service.finance.IncomeService;

public class WarrantAction extends ManageAction {

  private static final long serialVersionUID = 7169395117657538586L;

  @Autowired
  private IncomeService incomeService;

  private List<Customer> agentList;

  private Customer agent;

  private Warrant warrant = new Warrant();

  private String tourNo;

  /** 部门 */
  private String kenDepartmentId;

  private Date warrantDate;

  private Date lastPayDate;

  private int kenCustomerId;

  private List<Employee> employees = new ArrayList<Employee>();

  private List<Team> teamList = new ArrayList<Team>();

  public List<Employee> getEmployees() {
    return employees;
  }

  public void setEmployees(List<Employee> employees) {
    this.employees = employees;
  }

  public List<Team> getTeamList() {
    return teamList;
  }

  public int getKenCustomerId() {
    return kenCustomerId;
  }

  public void setKenCustomerId(int kenCustomerId) {
    this.kenCustomerId = kenCustomerId;
  }

  public String show() {
    agentList = incomeService.roGetCusByTour(tourNo);
    return INPUT;
  }

  public String submit() {
    warrant.setLastPayDate(lastPayDate);
    warrant.setWarrantDate(warrantDate);
    int i = incomeService.txWarrantSubmit(kenCustomerId, tourNo, warrant);
    if (i == 0) {
      addActionMessage("担保完成！");
    } else {
      addActionError("担保失败.");
    }
    return SUCCESS;
  }

  public String getTourNo() {
    return tourNo;
  }

  public void setTourNo(String tourNo) {
    this.tourNo = tourNo;
  }

  public List<Customer> getAgentList() {
    return agentList;
  }

  public void setAgentList(List<Customer> agentList) {
    this.agentList = agentList;
  }

  public Customer getAgent() {
    return agent;
  }

  public void setAgent(Customer agent) {
    this.agent = agent;
  }

  public Date getWarrantDate() {
    return warrantDate;
  }

  public void setWarrantDate(Date warrantDate) {
    this.warrantDate = warrantDate;
  }

  public Date getLastPayDate() {
    return lastPayDate;
  }

  public void setLastPayDate(Date lastPayDate) {
    this.lastPayDate = lastPayDate;
  }

  public Warrant getWarrant() {
    return warrant;
  }

  public void setWarrant(Warrant warrant) {
    this.warrant = warrant;
  }

}
