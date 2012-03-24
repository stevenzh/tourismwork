package com.opentravelsoft.providers;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.opentravelsoft.entity.Employee;

public interface EmployeeDao extends GenericDao<Employee, Long> {
  public List<Employee> getEmployees(boolean enabled);

  /**
   * 
   * @param userId
   * @return
   */
  public Employee getEmployee(long userId);

  /**
   * 
   * @param userName
   * @return
   */
  public Employee getEmployeeByName(String userName);

  /**
   * 
   * @param groupId
   * @param userName
   * @return
   */
  public List<Employee> getEmployees(int groupId, String userName);

  /**
   * 将用户状态改为关闭
   * 
   * @param userId
   * @return
   */
  public int deleteEmployee(long userId);

  public int insertEmployee(Employee employee);

  /**
   * 修改用户
   * 
   * @param employee
   * @return
   */
  public int update(Employee employee);

  public Date getSysdate();

  public int updateEmployee(Employee employee);

  public Set<String> getAuthorities(long userId);

  public List<Employee> getSalesmans(boolean active);

  public List<Employee> getSalesByTeam(long teamId);

  public List<Employee> getUserByTeam(long teamId);
}
