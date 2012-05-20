package com.opentravelsoft.service.security;

import java.util.List;
import java.util.Map;

import com.opentravelsoft.entity.Module;
import com.opentravelsoft.entity.Role;

public interface RoleService {

  List<Role> roGetRoleList();

  List<Role> roGetQueryRole(String keyword);

  void txDelRole(int roleId);

  Role roGetRoleDetail(int roleId);

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
  List<Module> roGetModulePermission(int roleId);

}
