package com.opentravelsoft.service.setting;

import java.util.List;

import com.opentravelsoft.entity.Group;

public interface DepartmentService
{
    public List<Group> getAllDepartments();

    public Group getDepartment(long groupId);

    public void txSaveDepartment(Group department);
}
