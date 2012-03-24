package com.opentravelsoft.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class MenuItem extends BaseObject implements java.io.Serializable {

  private static final long serialVersionUID = 6129655387608594110L;

  private Long id;

  private String itemName;

  private String displayName;

  private String relativePath;

  private String imageName;

  private boolean itemEnabled;

  private short itemOrder;

  private MenuItem parent;

  private List<MenuItem> child;

  private String roles;

  public MenuItem() {
    itemEnabled = false;
    child = new ArrayList<MenuItem>();
  }

  public MenuItem(String itemName, String displayName, String link, String roles) {
    this();
    this.itemName = itemName;
    this.displayName = displayName;
    this.relativePath = link;
    this.roles = roles;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDisplayName() {
    return this.displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getRelativePath() {
    return this.relativePath;
  }

  public void setRelativePath(String relativePath) {
    this.relativePath = relativePath;
  }

  public String getImageName() {
    return this.imageName;
  }

  public void setImageName(String imageName) {
    this.imageName = imageName;
  }

  public String getRoles() {
    return roles;
  }

  public void setRoles(String roles) {
    this.roles = roles;
  }

  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  public boolean isItemEnabled() {
    return itemEnabled;
  }

  public void setItemEnabled(boolean itemEnabled) {
    this.itemEnabled = itemEnabled;
  }

  public short getItemOrder() {
    return itemOrder;
  }

  public void setItemOrder(short itemOrder) {
    this.itemOrder = itemOrder;
  }

  public MenuItem getParent() {
    return parent;
  }

  public void setParent(MenuItem parent) {
    this.parent = parent;
  }

  public List<MenuItem> getChild() {
    return child;
  }

  public void setChild(List<MenuItem> child) {
    this.child = child;
  }

  /**
   * {@inheritDoc}
   */
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof MenuItem)) {
      return false;
    }

    final MenuItem role = (MenuItem) o;
    return this.hashCode() == role.hashCode();
  }

  /**
   * {@inheritDoc}
   */
  public int hashCode() {
    int result = 0;
    result = (itemName != null ? itemName.hashCode() : 0);
    result = 29 * result + (displayName != null ? displayName.hashCode() : 0);
    return result;
  }

  /**
   * {@inheritDoc}
   */
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
        .append("moduleName", this.getItemName())
        .append("displayName", this.displayName).toString();
  }

}
