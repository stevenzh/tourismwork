package com.opentravelsoft.service.module;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.entity.Module;
import com.opentravelsoft.providers.ModuleDao;
import com.opentravelsoft.providers.RoleDao;

@Service("ModuleService")
public class ModuleServiceImpl implements ModuleService {
  
  @Autowired
  private ModuleDao moduleDao;
  @Autowired
  private RoleDao roleDao;

  public Module roGetModuleDetail(long moduleId) {
    return moduleDao.get(moduleId);
  }

  public List<Module> roGetModuleList(boolean active) {
    return moduleDao.getModuleList(active);
  }

  public List<Module> roGetQueryModule(String keyword) {
    return moduleDao.queryModle(keyword);
  }

  public void txDelModule(long moduleId) {
    moduleDao.remove(moduleId);
  }

  public void txSaveModule(Module module) {
    moduleDao.save(module);
  }

  public String txSaveRoleModelPerm(long roleId, String moduleName,
      String moduleAction, String permValue) {
    return roleDao.saveModulePerm(roleId, moduleName, moduleAction, permValue);
  }
}
