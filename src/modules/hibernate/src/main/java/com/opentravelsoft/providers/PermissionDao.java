package com.opentravelsoft.providers;

import java.util.Map;

import com.opentravelsoft.entity.Permission;

public interface PermissionDao extends GenericDao<Permission, Integer> {
  Map<Integer, String> getPermissions();
}
