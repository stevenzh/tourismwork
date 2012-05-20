package com.opentravelsoft.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_role")
public class Role implements java.io.Serializable {

  private Integer roleId;
  private String roleCode;
  private String roleName;
  private String roleDesc;
  private Byte isActive;
  private Set<Employee> employees = new HashSet<Employee>(0);

  public Role() {
  }

  public Role(Integer role_id) {
    this.roleId = role_id;
  }

  public Role(String roleCode, String roleName) {
    this.roleCode = roleCode;
    this.roleName = roleName;
  }

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "ROLE_ID", unique = true, nullable = false)
  public Integer getRoleId() {
    return this.roleId;
  }

  public void setRoleId(Integer roleId) {
    this.roleId = roleId;
  }

  @Column(name = "ROLE_CODE", nullable = false, length = 50)
  public String getRoleCode() {
    return this.roleCode;
  }

  public void setRoleCode(String roleCode) {
    this.roleCode = roleCode;
  }

  @Column(name = "ROLE_NAME", nullable = false, length = 50)
  public String getRoleName() {
    return this.roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  @Column(name = "ROLE_DESC", length = 50)
  public String getRoleDesc() {
    return this.roleDesc;
  }

  public void setRoleDesc(String roleDesc) {
    this.roleDesc = roleDesc;
  }

  @Column(name = "IS_ACTIVE")
  public Byte getIsActive() {
    return this.isActive;
  }

  public void setIsActive(Byte isActive) {
    this.isActive = isActive;
  }

  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
  public Set<Employee> getEmployees() {
    return this.employees;
  }

  public void setEmployees(Set<Employee> employees) {
    this.employees = employees;
  }

}
