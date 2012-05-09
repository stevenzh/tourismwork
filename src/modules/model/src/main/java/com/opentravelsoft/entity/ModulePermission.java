package com.opentravelsoft.entity;

/**
 * 
 * @author zhangst
 * 
 */
public class ModulePermission {

  private int modulePermissionId;

  private long moduleId;

  private int permissionId;

  private boolean allowAccess;

  private Long roleId;

  private Long userId;

  public Integer getModulePermissionId() {
    return modulePermissionId;
  }

  public void setModulePermissionId(Integer modulePermissionId) {
    this.modulePermissionId = modulePermissionId;
  }

  public long getModuleId() {
    return moduleId;
  }

  public void setModuleId(long moduleId) {
    this.moduleId = moduleId;
  }

  public int getPermissionId() {
    return permissionId;
  }

  public void setPermissionId(int permissionId) {
    this.permissionId = permissionId;
  }

  public boolean isAllowAccess() {
    return allowAccess;
  }

  public void setAllowAccess(boolean allowAccess) {
    this.allowAccess = allowAccess;
  }

  public Long getRoleId() {
    return roleId;
  }

  public void setRoleId(Long roleId) {
    this.roleId = roleId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public void setModulePermissionId(int modulePermissionId) {
    this.modulePermissionId = modulePermissionId;
  }

}
