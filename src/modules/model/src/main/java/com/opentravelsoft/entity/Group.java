package com.opentravelsoft.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tbl_group")
public class Group implements java.io.Serializable {

  private int groupId;
  private Group parent;
  private String name;
  private String fullName;
  private String contact;
  private String phone;
  private String fax;
  private Set<Group> children = new HashSet<Group>(0);
  private Set<Employee> employees = new HashSet<Employee>(0);

  public Group() {
  }

  public Group(int groupId, String dptCn) {
    this.groupId = groupId;
    this.name = dptCn;
  }

  @Id
  @Column(name = "GROUP_ID", unique = true, nullable = false)
  public int getGroupId() {
    return this.groupId;
  }

  public void setGroupId(int groupId) {
    this.groupId = groupId;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "PARENT_ID")
  public Group getParent() {
    return parent;
  }

  public void setParent(Group parent) {
    this.parent = parent;
  }

  @Column(name = "NAME", nullable = false, length = 20)
  public String getName() {
    return this.name;
  }

  public void setName(String dptCn) {
    this.name = dptCn;
  }

  @Column(name = "FULL_NAME", length = 60)
  public String getFullName() {
    return this.fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  @Column(name = "CONTACT", length = 20)
  public String getContact() {
    return this.contact;
  }

  public void setContact(String linkman) {
    this.contact = linkman;
  }

  @Column(name = "PHONE", length = 40)
  public String getPhone() {
    return this.phone;
  }

  public void setPhone(String tel) {
    this.phone = tel;
  }

  @Column(name = "FAX", length = 40)
  public String getFax() {
    return this.fax;
  }

  public void setFax(String fax) {
    this.fax = fax;
  }

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
  public Set<Group> getChildren() {
    return children;
  }

  public void setChildren(Set<Group> children) {
    this.children = children;
  }

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
  public Set<Employee> getEmployees() {
    return this.employees;
  }

  public void setEmployees(Set<Employee> employees) {
    this.employees = employees;
  }

  public int compareTo(Group o) {
    if (this.groupId == o.getGroupId())
      return 0;
    else
      return 1;
  }

  public void addChildren(Group child) {
    children.add(child);
  }

}
