package com.opentravelsoft.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tbl_lists", uniqueConstraints = @UniqueConstraint(columnNames = {
    "ListName", "Value" }))
public class Lists implements java.io.Serializable {

  private Integer entryId;
  private String listName;
  private String value;
  private String text;
  private int parentId;
  private int level;
  /** 是否可以使用 */
  private byte enabled;
  /** 排序 */
  private int sortOrder;
  private int definitionId;
  private String description;
  /** 是否系统列表（还存在用户设定列表） */
  private byte systemList;

  public Lists() {
    level = 0;
    enabled = 1;
    sortOrder = 0;
    systemList = 0;
    listName = "";
  }

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "EntryID", unique = true, nullable = false)
  public Integer getEntryId() {
    return this.entryId;
  }

  public void setEntryId(Integer entryId) {
    this.entryId = entryId;
  }

  @Column(name = "ListName", nullable = false, length = 50)
  public String getListName() {
    return this.listName;
  }

  public void setListName(String listName) {
    this.listName = listName;
  }

  @Column(name = "Value", nullable = false, length = 100)
  public String getValue() {
    return this.value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Column(name = "Text", nullable = false, length = 150)
  public String getText() {
    return this.text;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Column(name = "ParentID", nullable = false)
  public int getParentId() {
    return this.parentId;
  }

  public void setParentId(int parentId) {
    this.parentId = parentId;
  }

  @Column(name = "Level", nullable = false)
  public int getLevel() {
    return this.level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  @Column(name = "Enabled", nullable = false)
  public byte getEnabled() {
    return this.enabled;
  }

  public void setEnabled(byte enabled) {
    this.enabled = enabled;
  }

  @Column(name = "SortOrder", nullable = false)
  public int getSortOrder() {
    return this.sortOrder;
  }

  public void setSortOrder(int sortOrder) {
    this.sortOrder = sortOrder;
  }

  @Column(name = "DefinitionID", nullable = false)
  public int getDefinitionId() {
    return this.definitionId;
  }

  public void setDefinitionId(int definitionId) {
    this.definitionId = definitionId;
  }

  @Column(name = "Description", length = 500)
  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Column(name = "SystemList", nullable = false)
  public byte getSystemList() {
    return this.systemList;
  }

  public void setSystemList(byte systemList) {
    this.systemList = systemList;
  }

}
