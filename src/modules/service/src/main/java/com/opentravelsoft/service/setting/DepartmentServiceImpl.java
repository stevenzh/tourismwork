package com.opentravelsoft.service.setting;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.entity.Group;
import com.opentravelsoft.providers.GroupDao;

@Service("DepartmentService")
public class DepartmentServiceImpl implements DepartmentService {
  
  @Autowired
  private GroupDao groupDao;

  public List<Group> getAllDepartments() {
    return groupDao.getAllGroups();
  }

  public Group getDepartment(long groupId) {
    return groupDao.get(groupId);
  }

  public void txSaveDepartment(Group department) {
    groupDao.save(department);
  }

}
