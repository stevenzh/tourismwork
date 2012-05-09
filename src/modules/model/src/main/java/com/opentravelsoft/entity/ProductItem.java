package com.opentravelsoft.entity;

import com.opentravelsoft.util.RowDataUtil;

public class ProductItem extends BaseObject {

  private static final long serialVersionUID = -8500221284153007603L;

  private long itemId;

  private Category category;

  private String itemCode;

  private String itemName;

  private int sortOrder;

  private boolean isActive;

  private boolean showInPortal;

  private boolean isTmpl;

  private boolean isText;

  private boolean hasTitle;

  private boolean systemList;

  private String linkUrl;

  public ProductItem() {
  }

  public long getItemId() {
    return this.itemId;
  }

  public void setItemId(long itemId) {
    this.itemId = itemId;
  }

  public String getItemCode() {
    return this.itemCode;
  }

  public void setItemCode(String itemCode) {
    this.itemCode = itemCode;
  }

  public String getItemName() {
    return this.itemName;
  }

  public String getItemName1() {
    String str = RowDataUtil.getString(category.getName());
    return str + "_" + this.itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  public int getSortOrder() {
    return this.sortOrder;
  }

  public void setSortOrder(int sortOrder) {
    this.sortOrder = sortOrder;
  }

  public boolean getIsActive() {
    return this.isActive;
  }

  public void setIsActive(boolean isActive) {
    this.isActive = isActive;
  }

  public boolean getShowInPortal() {
    return this.showInPortal;
  }

  public void setShowInPortal(boolean showInPortal) {
    this.showInPortal = showInPortal;
  }

  public boolean getIsTmpl() {
    return this.isTmpl;
  }

  public void setIsTmpl(boolean isTmpl) {
    this.isTmpl = isTmpl;
  }

  public boolean getSystemList() {
    return this.systemList;
  }

  public void setSystemList(boolean systemList) {
    this.systemList = systemList;
  }

  public boolean getIsText() {
    return isText;
  }

  public void setIsText(boolean isText) {
    this.isText = isText;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public boolean getHasTitle() {
    return hasTitle;
  }

  public void setHasTitle(boolean hasTitle) {
    this.hasTitle = hasTitle;
  }

  public String getLinkUrl() {
    return linkUrl;
  }

  public void setLinkUrl(String linkUrl) {
    this.linkUrl = linkUrl;
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
