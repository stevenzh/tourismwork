package com.opentravelsoft.providers;

import java.util.List;

import com.opentravelsoft.entity.Module;
import com.opentravelsoft.entity.Role;

public interface RoleDao extends GenericDao<Role, Integer> {

  List<Role> queryRole(String keyword);

  List<Role> getRoleList();

  /**
   * 
   * @param roleId
   * @return
   */
  List<Module> getModulePermission(int roleId);

  String saveModulePerm(Integer roleId, String moduleName, String moduleAction,
      String perm);

  public List<Role> getRoles();
}
