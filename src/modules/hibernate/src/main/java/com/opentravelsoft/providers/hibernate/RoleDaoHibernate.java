package com.opentravelsoft.providers.hibernate;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.Module;
import com.opentravelsoft.entity.ModulePermission;
import com.opentravelsoft.entity.Permission;
import com.opentravelsoft.entity.Role;
import com.opentravelsoft.providers.PermissionDao;
import com.opentravelsoft.providers.RoleDao;
import com.opentravelsoft.util.RowDataUtil;

@Repository("RoleDao")
public class RoleDaoHibernate extends GenericDaoHibernate<Role, Integer>
    implements RoleDao {

  private PermissionDao permissionDao;

  public RoleDaoHibernate() {
    super(Role.class);
  }

  @Autowired
  public void setPermissionDao(PermissionDao permissionDao) {
    this.permissionDao = permissionDao;
  }

  @SuppressWarnings("unchecked")
  public List<Role> getRoleList() {
    StringBuilder sql = new StringBuilder();
    sql.append("from Role order by roleId");
    return getHibernateTemplate().find(sql.toString());
  }

  public List<Role> queryRole(String keyword) {
    StringBuilder sql = new StringBuilder();
    sql.append("from Role order by roleId");
    return null;
  }

  @SuppressWarnings("unchecked")
  public List<Module> getModulePermission(int roleId) {
    // Get All active module
    List<Module> modules = getActiveModules();
    Map<Integer, String> perm = permissionDao.getPermissions();

    for (Module module : modules) {
      Map<Integer, Boolean> pp = new TreeMap<Integer, Boolean>();
      for (Integer key : perm.keySet()) {
        pp.put(key, false);
      }
      module.setRolePermissionMap(pp);
    }

    StringBuilder sql = new StringBuilder();
    sql.append("select moduleId,permissionId,allowAccess ");
    sql.append("from ModulePermission ");
    sql.append("where roleId=? ");
    sql.append("order by moduleId, permissionId");

    Object[] params = { roleId };
    List<Object[]> list = getHibernateTemplate().find(sql.toString(), params);

    for (Object[] objects : list) {
      for (Module mm : modules) {
        if (mm.getModuleId() == RowDataUtil.getInt(objects[0])) {
          if (mm.getRolePermissionMap().containsKey(
              RowDataUtil.getInt(objects[1])))
            mm.getRolePermissionMap().remove(RowDataUtil.getInt(objects[1]));

          mm.getRolePermissionMap().put(RowDataUtil.getInt(objects[1]),
              (Boolean) objects[2]);

          log.debug("ModuleId:" + mm.getModuleId() + " PermKey:"
              + RowDataUtil.getInt(objects[1]) + " PermValue:" + objects[2]);
          break;
        }
      }
    }

    return modules;
  }

  @SuppressWarnings("unchecked")
  private List<Module> getActiveModules() {
    StringBuilder sql = new StringBuilder();
    sql.append("from Module where isActive=1 order by moduleId ");
    return getHibernateTemplate().find(sql.toString());
  }

  @SuppressWarnings("unchecked")
  public String saveModulePerm(Integer roleId, String moduleName,
      String moduleAction, String perm) {
    StringBuilder sql = new StringBuilder();
    String result = Boolean.FALSE.toString();

    Object[] params = { moduleAction };
    sql.append("from Permission where permissionKey=? ");
    List<Permission> list = getHibernateTemplate().find(sql.toString(), params);
    if (list.size() < 1)
      return "0";
    Permission permission = list.get(0);

    sql = new StringBuilder();
    Object[] params1 = { moduleName };
    sql.append("from Module where moduleName=? ");
    List<Module> list1 = getHibernateTemplate().find(sql.toString(), params1);
    if (list1.size() < 1)
      return "0";
    Module module = list1.get(0);

    sql = new StringBuilder();
    Object[] params2 = { module.getModuleId(), roleId,
        permission.getPermissionId() };
    sql.append("from ModulePermission where moduleId=? ");
    sql.append("and roleId=? and permissionId=? ");

    List<ModulePermission> list2 = getHibernateTemplate().find(sql.toString(),
        params2);

    if (list2.size() > 0) {
      ModulePermission mp = list2.get(0);
      if (perm.equalsIgnoreCase(Boolean.FALSE.toString())) {
        mp.setAllowAccess((byte) 1);
        result = Boolean.TRUE.toString();
      } else
        mp.setAllowAccess((byte) 0);
      getHibernateTemplate().update(mp);

    } else {
      ModulePermission mp = new ModulePermission();
      mp.setModuleId(module.getModuleId());
      mp.setPermissionId(permission.getPermissionId());
      mp.setRoleId(roleId);
      if (perm.equalsIgnoreCase(Boolean.FALSE.toString())) {
        mp.setAllowAccess((byte) 1);
        result = Boolean.TRUE.toString();
      } else
        mp.setAllowAccess((byte) 0);

      getHibernateTemplate().save(mp);
    }

    return result;
  }

  @SuppressWarnings("unchecked")
  public List<Role> getRoles() {
    StringBuilder sql = new StringBuilder();
    sql.append("from Role where isActive=1 ");
    return getHibernateTemplate().find(sql.toString());
  }
}
