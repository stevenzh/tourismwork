package com.opentravelsoft.entity;

public class LineDescription implements java.io.Serializable {

  private static final long serialVersionUID = 8662894231442978469L;

  private long id;

  private String lineNo;

  private String item;

  private String description;

  private Integer sortOrder;

  private int itemId;

  private int idx;

  // 操作字段
  private String destination;

  private String canDel;

  private String recNo;

  /** 类型 */
  private String type;

  public LineDescription() {
    destination = "";
    item = "";
    description = "";
    canDel = "Y";
  }

  public LineDescription(int id, String lineNo, String item) {
    this.id = id;
    this.lineNo = lineNo;
    this.item = item;
  }

  public LineDescription(int id, String lineNo, String item, String description) {
    this.id = id;
    this.lineNo = lineNo;
    this.item = item;
    this.description = description;
  }

  public LineDescription(String lineNo, String description) {
    this.lineNo = lineNo;
    this.description = description;
  }

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getLineNo() {
    return this.lineNo;
  }

  public void setLineNo(String lineNo) {
    this.lineNo = lineNo;
  }

  public String getItem() {
    return this.item;
  }

  public void setItem(String item) {
    this.item = item;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getSortOrder() {
    return sortOrder;
  }

  public void setSortOrder(Integer sortOrder) {
    this.sortOrder = sortOrder;
  }

  public int getItemId() {
    return itemId;
  }

  public void setItemId(int itemId) {
    this.itemId = itemId;
  }

  public int getIdx() {
    return idx;
  }

  public void setIdx(int idx) {
    this.idx = idx;
  }

  public String getDestination() {
    return destination;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }

  public String getCanDel() {
    return canDel;
  }

  public void setCanDel(String canDel) {
    this.canDel = canDel;
  }

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

  public String getRouteNo() {
    return this.lineNo;
  }

  public void setRouteNo(String lineNo) {
    this.lineNo = lineNo;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getExpenseType() {
    return type;
  }

  public void setExpenseType(String expenseType) {
    this.type = expenseType;
  }

}
