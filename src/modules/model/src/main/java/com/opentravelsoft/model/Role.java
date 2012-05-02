package com.opentravelsoft.model;

/**
 * 角色
 * 
 * @author zhangst
 */
public class Role extends BaseObject implements java.io.Serializable {
  private static final long serialVersionUID = -1319396601255838630L;

  private long roleId;

  private String roleCode;

  private String roleName;

  private String roleDesc;

  private byte isActive;

  public Role() {
  }

  public Role(long roleId) {
    this.roleId = roleId;
  }

  public Role(long roleId, String roleCode, String roleName) {
    this.roleId = roleId;
    this.roleCode = roleCode;
    this.roleName = roleName;
  }

  public long getRoleId() {
    return this.roleId;
  }

  public void setRoleId(long roleId) {
    this.roleId = roleId;
  }

  public String getRoleCode() {
    return this.roleCode;
  }

  public void setRoleCode(String roleCode) {
    this.roleCode = roleCode;
  }

  public String getRoleName() {
    return this.roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public String getRoleDesc() {
    return this.roleDesc;
  }

  public void setRoleDesc(String roleDesc) {
    this.roleDesc = roleDesc;
  }

  public byte getIsActive() {
    return this.isActive;
  }

  public void setIsActive(byte isActive) {
    this.isActive = isActive;
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
