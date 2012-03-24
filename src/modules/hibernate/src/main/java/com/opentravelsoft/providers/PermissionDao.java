package com.opentravelsoft.providers;

import java.util.Map;

import com.opentravelsoft.model.Permission;

public interface PermissionDao extends GenericDao<Permission, Long> {
	Map<Integer, String> getPermissions();
}
