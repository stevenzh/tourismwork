package com.opentravelsoft.service.security;

import java.util.List;
import java.util.Map;

import com.opentravelsoft.model.Module;
import com.opentravelsoft.model.Role;

public interface RoleService {

  List<Role> roGetRoleList();

  List<Role> roGetQueryRole(String keyword);

  void txDelRole(long roleId);

  Role roGetRoleDetail(long roleId);

  Role txSaveRole(Role role);

  /**
   * 
   * @return
   */
  Map<Integer, String> roGetPermissions();

  /**
   * 
   * @param roleId
   * @return
   */
  List<Module> roGetModulePermission(long roleId);

}
