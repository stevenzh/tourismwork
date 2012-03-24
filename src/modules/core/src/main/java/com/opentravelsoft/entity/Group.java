package com.opentravelsoft.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.opentravelsoft.model.BaseObject;

/**
 * 组织结构单元
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/04/10 07:47:28 $
 */
public class Group extends BaseObject implements Comparable<Group> {
  private static final long serialVersionUID = 6053620586258625185L;

  private long groupId;

  private String name;

  private String fullName;

  private Group parent;

  /** 联系人 */
  private String contact;

  private String phone;

  private String fax;

  private Set<Group> children = new TreeSet<Group>();

  private Set<Employee> employees = new HashSet<Employee>(0);

  public Group() {
  }

  public Group(long groupId) {
    this.groupId = groupId;
  }

  public long getGroupId() {
    return this.groupId;
  }

  public void setGroupId(long groupId) {
    this.groupId = groupId;
  }

  public Group getParent() {
    return parent;
  }

  public void setParent(Group parent) {
    this.parent = parent;
  }

  public String getName() {
    return name;
  }

  public Set<Group> getChildren() {
    return children;
  }

  public void setChildren(Set<Group> children) {
    this.children = children;
  }

  public void addChildren(Group child) {
    children.add(child);
  }

  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  public String getFax() {
    return fax;
  }

  public void setFax(String fax) {
    this.fax = fax;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<Employee> getEmployees() {
    return employees;
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

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("Id", this.getGroupId())
        .append("Name", this.getName())
        .append("FullName", this.getFullName()).toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Group)) {
      return false;
    }

    final Group t = (Group) o;
    return groupId == t.getGroupId();
  }

  @Override
  public int hashCode() {
    int result;
    result = (int) groupId;
    result = 29 * result + (this.name != null ? this.name.hashCode() : 0);
    result = 29 * result + (this.fullName != null ? this.fullName.hashCode() : 0);
    result = 29 * result + (this.contact != null ? this.contact.hashCode() : 0);
    return result;
  }

}
