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
  private String dptCn;
  private String fullName;
  private String linkman;
  private String tel;
  private String fax;
  private Set<Group> children = new HashSet<Group>(0);
  private Set<Employee> employees = new HashSet<Employee>(0);

  public Group() {
  }

  public Group(int groupId, String dptCn) {
    this.groupId = groupId;
    this.dptCn = dptCn;
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

  @Column(name = "DPT_CN", nullable = false, length = 20)
  public String getDptCn() {
    return this.dptCn;
  }

  public void setDptCn(String dptCn) {
    this.dptCn = dptCn;
  }

  @Column(name = "FULL_NAME", length = 60)
  public String getFullName() {
    return this.fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  @Column(name = "LINKMAN", length = 20)
  public String getLinkman() {
    return this.linkman;
  }

  public void setLinkman(String linkman) {
    this.linkman = linkman;
  }

  @Column(name = "TEL", length = 40)
  public String getTel() {
    return this.tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
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

  private String name;

  /** 联系人 */
  private String contact;

  private String phone;

  @Transient
  public String getName() {
    return name;
  }

  public void addChildren(Group child) {
    children.add(child);
  }

  @Transient
  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  @Transient
  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int compareTo(Group o) {
    if (this.groupId == o.getGroupId())
      return 0;
    else
      return 1;
  }

}
