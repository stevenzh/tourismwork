package com.opentravelsoft.flex;

import java.util.Date;

import com.opentravelsoft.entity.Employee;

public interface FlexService {

	Employee createEmployee(String name, String title, boolean gender,
			Date birth);

	void deleteEmployee(int userId);

	Employee[] queryByName(String name);

	Employee[] queryAll();

}
