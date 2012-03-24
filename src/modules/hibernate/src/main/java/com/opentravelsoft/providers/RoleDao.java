package com.opentravelsoft.providers;

import java.util.List;

import com.opentravelsoft.model.Module;
import com.opentravelsoft.model.Role;

public interface RoleDao extends GenericDao<Role, Long> {

  List<Role> queryRole(String keyword);

  List<Role> getRoleList();

  /**
   * 
   * @param roleId
   * @return
   */
  List<Module> getModulePermission(long roleId);

  String saveModulePerm(long roleId, String moduleName, String moduleAction,
      String perm);

  public List<Role> getRoles();
}
