package com.opentravelsoft.flex;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.providers.EmployeeDao;

public class FlexServiceImpl implements FlexService {

	private static final Employee[] EMPTY_EMPLOYEE_ARRAY = new Employee[0];

	private EmployeeDao employeeDao;

	@Autowired
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	public Employee createEmployee(String name, String title, boolean gender,
			Date birth) {
		Employee employee = new Employee();
		employee.setUserName(name);
		employee.setSex("M");
		employee.setBirthday(birth);
		return employeeDao.save(employee);
	}

	public void deleteEmployee(int id) {
		employeeDao.deleteEmployee(id);
	}

	public Employee[] queryAll() {
		return employeeDao.getAll().toArray(EMPTY_EMPLOYEE_ARRAY);
	}

	public Employee[] queryByName(String name) {
		List<Employee> list = new ArrayList<Employee>();
		list.add(employeeDao.getEmployeeByName(name));
		return list.toArray(EMPTY_EMPLOYEE_ARRAY);
	}

}
