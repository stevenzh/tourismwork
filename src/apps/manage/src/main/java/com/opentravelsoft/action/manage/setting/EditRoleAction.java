package com.opentravelsoft.action.manage.setting;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.model.Module;
import com.opentravelsoft.model.Role;
import com.opentravelsoft.service.security.RoleService;

/**
 * 系统设置：模块修改
 * 
 * @author udb
 */
public class EditRoleAction extends ManageAction {
  private static final long serialVersionUID = 2909557567120180025L;

  protected static final Log logger = LogFactory.getLog(EditRoleAction.class);

  private long roleId;

  @Autowired
  private RoleService roleService;

  private Role role = new Role();

  /** 系统所有的基础动作（显示、修改、删除） */
  private Map<Integer, String> permission = new TreeMap<Integer, String>();

  /** List All modules */
  private List<Module> modulePerm = new ArrayList<Module>();

  public String input() throws Exception {
    if (roleId != 0)
      role = roleService.roGetRoleDetail(roleId);

    permission = roleService.roGetPermissions();

    modulePerm = roleService.roGetModulePermission(roleId);

    return INPUT;
  }

  public String submit() throws Exception {
    int result = 0;

    if (roleId != 0)
      roleService.txSaveRole(role);
    if (result < 0) {
      addActionError("陪同代码重复!");
    }

    return SUCCESS;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role guide) {
    this.role = guide;
  }

  public long getRoleId() {
    return roleId;
  }

  public void setRoleId(long roleId) {
    this.roleId = roleId;
  }

  public Map<Integer, String> getPermission() {
    return permission;
  }

  public List<Module> getModulePerm() {
    return modulePerm;
  }

}
