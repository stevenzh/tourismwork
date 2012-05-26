package com.opentravelsoft.entity;

import java.util.ArrayList;
import java.util.List;

public class MenuItem implements java.io.Serializable {

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

}
