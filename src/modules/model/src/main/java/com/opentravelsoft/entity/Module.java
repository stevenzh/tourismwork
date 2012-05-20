package com.opentravelsoft.entity;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "tbl_module")
public class Module implements java.io.Serializable {

  private Integer moduleId;
  private String version;
  private String moduleName;
  private String moduleTitle;
  private String action;
  /** 说明 */
  private String description;
  private Integer sortOrder;
  private String type;
  private Integer parentId;
  /** 是否激活 */
  private byte isActive;

  public Module() {
    sortOrder = 0;
    parentId = 0;
    rolePermissionMap = new TreeMap<Integer, Boolean>();
  }

  public Module(String name, byte isActive) {
    this.moduleName = name;
    this.isActive = isActive;
  }

  public Module(String name, String title, String action, String description,
      Integer sortOrder, String type, Integer parentId, byte isActive) {
    this.moduleName = name;
    this.moduleTitle = title;
    this.action = action;
    this.description = description;
    this.sortOrder = sortOrder;
    this.type = type;
    this.parentId = parentId;
    this.isActive = isActive;
  }

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "MID", unique = true, nullable = false)
  public Integer getModuleId() {
    return moduleId;
  }

  public void setModuleId(Integer moduleId) {
    this.moduleId = moduleId;
  }

  @Version
  @Column(name = "VERSION", length = 50)
  public String getVersion() {
    return this.version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  @Column(name = "NAME", nullable = false, length = 50)
  public String getModuleName() {
    return moduleName;
  }

  public void setModuleName(String moduleName) {
    this.moduleName = moduleName;
  }

  @Column(name = "TITLE", length = 50)
  public String getModuleTitle() {
    return moduleTitle;
  }

  public void setModuleTitle(String moduleTitle) {
    this.moduleTitle = moduleTitle;
  }

  @Column(name = "ACTION", length = 50)
  public String getAction() {
    return this.action;
  }

  public void setAction(String action) {
    this.action = action;
  }

  @Column(name = "DESCRIPTION", length = 500)
  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Column(name = "SORT_ORDER")
  public Integer getSortOrder() {
    return this.sortOrder;
  }

  public void setSortOrder(Integer sortOrder) {
    this.sortOrder = sortOrder;
  }

  @Column(name = "TYPE", length = 10)
  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Column(name = "PARENT_ID")
  public Integer getParentId() {
    return this.parentId;
  }

  public void setParentId(Integer parentId) {
    this.parentId = parentId;
  }

  @Column(name = "IS_ACTIVE", nullable = false)
  public byte getIsActive() {
    return this.isActive;
  }

  public void setIsActive(byte isActive) {
    this.isActive = isActive;
  }

  private boolean isAdmin;

  /** 权限Type ep [View ,Edit , Delete] */
  private Set<ModulePermission> modulePermissions;

  /** 一个角色的某个模块的权限<modulePermissionId, value> */
  private Map<Integer, Boolean> rolePermissionMap;

  public boolean isAdmin() {
    return isAdmin;
  }

  public void setAdmin(boolean isAdmin) {
    this.isAdmin = isAdmin;
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

}
