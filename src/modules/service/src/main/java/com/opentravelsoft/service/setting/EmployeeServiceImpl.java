package com.opentravelsoft.service.setting;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.opentravelsoft.common.TeamType;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Role;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.entity.Group;
import com.opentravelsoft.providers.EmployeeDao;
import com.opentravelsoft.providers.GroupDao;
import com.opentravelsoft.providers.RoleDao;
import com.opentravelsoft.providers.TeamDao;

@Service("UserService")
public class EmployeeServiceImpl implements EmployeeService, UserDetailsService {

  @Autowired
  private EmployeeDao employeeDao;

  @Autowired
  private RoleDao roleDao;

  @Autowired
  private GroupDao departmentDao;

  @Autowired
  private TeamDao teamDao;

  public List<Employee> getEmployees(int groupId, String userName) {
    return employeeDao.getEmployees(groupId, userName);
  }

  public List<Group> getAllDepartments() {
    return departmentDao.getAllGroups();
  }

  public int txDeleteEmployee(int userId) {
    return employeeDao.deleteEmployee(userId);
  }

  public int txInsertEmployee(Employee employee) {
    return employeeDao.insertEmployee(employee);
  }

  public Employee roGetEmployee(int userId) {
    return employeeDao.getEmployee(userId);
  }

  public int txUpdateEmployee(Employee employee) {
    return employeeDao.update(employee);
  }

  public Date roGetSysdate() {
    return employeeDao.getSysdate();
  }

  public List<Role> roGetRoles() {
    return roleDao.getRoles();
  }

  public Employee roGetEmployeeByName(String userName) {
    return employeeDao.getEmployeeByName(userName);
  }

  public List<Employee> roGetSalesList() {
    return employeeDao.getSalesmans(false);
  }

  public List<Team> roGetAllTeams() {
    return teamDao.getAll();
  }

  public List<Team> roGetMarketTeams() {
    return teamDao.getTeamList(TeamType.Sales);
  }

  public List<Employee> roGetSalesByTeam(int teamId) {
    return employeeDao.getSalesByTeam(teamId);
  }

  @Override
  public List<Employee> getUserByTeam(int teamId) {
    return employeeDao.getUserByTeam(teamId);
  }

  @Override
  public List<Employee> getEmployees(boolean enabled) {
    return employeeDao.getEmployees(enabled);
  }

  @Override
  public UserDetails loadUserByUsername(String username)
      throws UsernameNotFoundException, DataAccessException {
    Employee empl = employeeDao.getEmployeeByName(username);
    empl.setAuthorities(obtainGrantedAuthorities(empl));
    return empl;
  }

  /**
   * 获得用户所有角色的权限.
   */
  private Set<GrantedAuthority> obtainGrantedAuthorities(Employee user) {
    Set<String> roles = employeeDao.getAuthorities(user.getUserId());
    Set<GrantedAuthority> dbAuthsSet = new HashSet<GrantedAuthority>();

    for (String role : roles) {
      dbAuthsSet.add(new GrantedAuthorityImpl(role));
    }
    return dbAuthsSet;
  }
}
