package com.opentravelsoft.entity;

import com.opentravelsoft.util.StringUtil;

/**
 * 系统列表
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.2 $ $Date: 2009/04/10 07:47:28 $
 */
public class Lists extends BaseObject {
  private static final long serialVersionUID = 5166688079731371698L;

  private long entryId;

  private String listName;

  private String value;

  private String text;

  private int parentId;

  private int level;

  /** 是否可以使用 */
  private boolean enabled;

  /** 排序 */
  private int sortOrder;

  private int definitionId;

  private String description;

  /** 是否系统列表（还存在用户设定列表） */
  private boolean systemList;

  public Lists() {
    level = 0;
    enabled = true;
    sortOrder = 0;
    systemList = false;
    listName = "";
  }

  public long getEntryId() {
    return this.entryId;
  }

  public void setEntryId(long entryId) {
    this.entryId = entryId;
  }

  public void setEntryId(String entryId) {
    if (StringUtil.hasLength(entryId))
      this.entryId = Long.parseLong(entryId);
  }

  public int getLevel() {
    return this.level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public boolean isEnabled() {
    return this.enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public int getSortOrder() {
    return this.sortOrder;
  }

  public void setSortOrder(int sortOrder) {
    this.sortOrder = sortOrder;
  }

  public int getDefinitionId() {
    return this.definitionId;
  }

  public void setDefinitionId(int definitionId) {
    this.definitionId = definitionId;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isSystemList() {
    return this.systemList;
  }

  public void setSystemList(boolean systemList) {
    this.systemList = systemList;
  }

  public String getListName() {
    return listName;
  }

  public void setListName(String listName) {
    this.listName = listName;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public int getParentId() {
    return parentId;
  }

  public void setParentId(int parentId) {
    this.parentId = parentId;
  }

  public String getT11() {
    return null;
  }

  public String getMoneyType() {
    return null;
  }

  public String getPayType() {
    return null;
  }

  public String getExpressType() {
    return null;
  }

  public String getExpressModlue() {
    return null;
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
