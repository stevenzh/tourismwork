package com.opentravelsoft.service.setting;

import java.util.List;

import com.opentravelsoft.entity.Group;

public interface DepartmentService {
  public List<Group> getAllDepartments();

  public Group getDepartment(int groupId);

  public void txSaveDepartment(Group department);
}
