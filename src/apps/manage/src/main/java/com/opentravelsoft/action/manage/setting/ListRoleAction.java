package com.opentravelsoft.action.manage.setting;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.model.Role;
import com.opentravelsoft.service.security.RoleService;

/**
 * 系统管理：模块列表
 * 
 * @author udb
 */
public class ListRoleAction extends ManageAction {
  private static final long serialVersionUID = 2914390488320236698L;

  protected static final Log logger = LogFactory.getLog(ListRoleAction.class);

  private int roleId;

  private String keyword;

  @Autowired
  private RoleService roleService;

  private List<Role> roleList;

  public String input() throws Exception {
    roleList = roleService.roGetRoleList();
    return INPUT;
  }

  public String query() throws Exception {
    roleList = roleService.roGetQueryRole(keyword);
    return SUCCESS;
  }

  public String delete() throws Exception {
    roleService.txDelRole(roleId);
    return SUCCESS;
  }

  public int getRoleId() {
    return roleId;
  }

  public void setRoleId(int roleId) {
    this.roleId = roleId;
  }

  public List<Role> getRoleList() {
    return roleList;
  }

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

}
