package com.opentravelsoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.providers.EmployeeDao;

@Service("LoginService")
public class LoginServiceImpl implements LoginService {

  private EmployeeDao employeeDao;

  @Autowired
  public void setEmployeeDao(EmployeeDao employeeDao) {
    this.employeeDao = employeeDao;
  }

  public Employee roGetEmployee(String userName) {
    return employeeDao.getEmployeeByName(userName);
  }

  public Employee roGetEmployee(long userId) {
    return employeeDao.getEmployee(userId);
  }
}
