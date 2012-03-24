package com.opentravelsoft.service.setting;

import java.util.Date;
import java.util.List;

import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.entity.Group;
import com.opentravelsoft.model.Role;

public interface EmployeeService {
  public List<Employee> getEmployees(int groupId, String userName);

  public List<Group> getAllDepartments();

  public int txDeleteEmployee(long userId);

  public int txInsertEmployee(Employee employee);

  public Employee roGetEmployee(long userId);

  public Employee roGetEmployeeByName(String userName);

  public int txUpdateEmployee(Employee employee);

  public Date roGetSysdate();

  public List<Role> roGetRoles();

  public List<Employee> roGetSalesList();

  public List<Team> roGetAllTeams();

  public List<Team> roGetMarketTeams();

  public List<Employee> roGetSalesByTeam(long teamId);

  public List<Employee> getUserByTeam(long teamId);

  public List<Employee> getEmployees(boolean enabled);
}
