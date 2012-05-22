package com.opentravelsoft.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Transient;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_line_desc")
public class LineDescription implements java.io.Serializable {

  private Integer id;
  private String lineNo;
  /** 类型 */
  private String type;
  private String item;
  private String description;
  private Integer sortOrder;

  public LineDescription() {
    destination = "";
    item = "";
    description = "";
    canDel = "Y";
  }

  public LineDescription(String lineNo, String type) {
    this.lineNo = lineNo;
    this.type = type;
  }

  public LineDescription(String lineNo, String type, String item,
      String description, Integer sortOrder) {
    this.lineNo = lineNo;
    this.type = type;
    this.item = item;
    this.description = description;
    this.sortOrder = sortOrder;
  }

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "ID", unique = true, nullable = false)
  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Column(name = "LINE_NO", nullable = false, length = 8)
  public String getLineNo() {
    return this.lineNo;
  }

  public void setLineNo(String lineNo) {
    this.lineNo = lineNo;
  }

  @Column(name = "TYPE", nullable = false, length = 50)
  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Column(name = "ITEM", length = 100)
  public String getItem() {
    return this.item;
  }

  public void setItem(String item) {
    this.item = item;
  }

  @Column(name = "DESCRIPTION", length = 1500)
  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Column(name = "SORT_ORDER")
  public Integer getSortOrder() {
    return this.sortOrder;
  }

  public void setSortOrder(Integer sortOrder) {
    this.sortOrder = sortOrder;
  }

  private int itemId;

  private int idx;

  // 操作字段
  private String destination;

  private String canDel;

  private String recNo;

  @Transient
  public int getItemId() {
    return itemId;
  }

  public void setItemId(int itemId) {
    this.itemId = itemId;
  }

  @Transient
  public int getIdx() {
    return idx;
  }

  public void setIdx(int idx) {
    this.idx = idx;
  }

  @Transient
  public String getDestination() {
    return destination;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }

  @Transient
  public String getCanDel() {
    return canDel;
  }

  public void setCanDel(String canDel) {
    this.canDel = canDel;
  }

  @Transient
  public String getRecNo() {
    return recNo;
  }

  public void setRecNo(String recNo) {
    this.recNo = recNo;
  }

  public String getServiceDetail() {
    return this.description;
  }

  public void setServiceDetail(String serviceDetail) {
    this.description = serviceDetail;
  }

  public int getRefNo() {
    return sortOrder;
  }

  public void setRefNo(int refNo) {
    this.sortOrder = refNo;
  }

  public String getTraitDetail() {
    return description;
  }

  public void setTraitDetail(String traitDetail) {
    this.description = traitDetail;
  }

  @Transient
  public String getRouteNo() {
    return this.lineNo;
  }

  public void setRouteNo(String lineNo) {
    this.lineNo = lineNo;
  }

  @Transient
  public String getExpenseType() {
    return type;
  }

  public void setExpenseType(String expenseType) {
    this.type = expenseType;
  }

}
