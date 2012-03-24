package com.opentravelsoft.model;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * 模块
 * 
 * @author zhangst
 */
public class Module extends BaseObject {
  private static final long serialVersionUID = 2905321552119510592L;

  /** 模块ID */
  private long moduleId;

  private String moduleName;

  private String moduleTitle;

  /** 说明 */
  private String description;

  private String version;

  /** 是否激活 */
  private boolean isActive;

  private boolean isAdmin;

  private String type;

  private Integer sortOrder;

  private Integer parentId;

  private String action;

  /** 权限Type ep [View ,Edit , Delete] */
  private Set<ModulePermission> modulePermissions;

  /** 一个角色的某个模块的权限<modulePermissionId, value> */
  private Map<Integer, Boolean> rolePermissionMap;

  public Module() {
    sortOrder = 0;
    parentId = 0;
    rolePermissionMap = new TreeMap<Integer, Boolean>();
  }

  public long getModuleId() {
    return moduleId;
  }

  public void setModuleId(long moduleId) {
    this.moduleId = moduleId;
  }

  public String getModuleTitle() {
    return moduleTitle;
  }

  public void setModuleTitle(String moduleTitle) {
    this.moduleTitle = moduleTitle;
  }

  public String getModuleName() {
    return moduleName;
  }

  public void setModuleName(String moduleName) {
    this.moduleName = moduleName;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean getIsActive() {
    return this.isActive;
  }

  public void setIsActive(boolean isActive) {
    this.isActive = isActive;
  }

  public boolean isAdmin() {
    return isAdmin;
  }

  public void setAdmin(boolean isAdmin) {
    this.isAdmin = isAdmin;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Map<Integer, Boolean> getRolePermissionMap() {
    return rolePermissionMap;
  }

  public void setRolePermissionMap(Map<Integer, Boolean> rolePermissionMap) {
    this.rolePermissionMap = rolePermissionMap;
  }

  public Set<ModulePermission> getModulePermissions() {
    return modulePermissions;
  }

  public void setModulePermissions(Set<ModulePermission> modulePermissions) {
    this.modulePermissions = modulePermissions;
  }

  public Integer getSortOrder() {
    return sortOrder;
  }

  public void setSortOrder(Integer sortOrder) {
    this.sortOrder = sortOrder;
  }

  public Integer getParentId() {
    return parentId;
  }

  public void setParentId(Integer parentId) {
    this.parentId = parentId;
  }

  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }

  @Override
  public String toString() {
    return null;
  }

  @Override
  public boolean equals(Object o) {
    return false;
  }

  @Override
  public int hashCode() {
    return 0;
  }

}
