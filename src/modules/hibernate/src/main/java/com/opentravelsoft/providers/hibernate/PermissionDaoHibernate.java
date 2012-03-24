package com.opentravelsoft.providers.hibernate;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.opentravelsoft.model.Permission;
import com.opentravelsoft.providers.PermissionDao;

@Repository("PermissionDao")
public class PermissionDaoHibernate extends
		GenericDaoHibernate<Permission, Long> implements PermissionDao {

	public PermissionDaoHibernate() {
		super(Permission.class);
	}

	public Map<Integer, String> getPermissions() {
		HibernateTemplate templte = getHibernateTemplate();
		List<Permission> list = templte.loadAll(Permission.class);
		Map<Integer, String> map = new TreeMap<Integer, String>();
		for (Permission permission : list) {
			map
					.put(permission.getPermissionId(), permission
							.getPermissionKey());
		}
		return map;
	}
}
