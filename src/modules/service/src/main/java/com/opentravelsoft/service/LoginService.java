package com.opentravelsoft.service;

import com.opentravelsoft.entity.Employee;

public interface LoginService {

  public Employee roGetEmployee(long userId);

  public Employee roGetEmployee(String uid);

}
