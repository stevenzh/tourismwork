package com.opentravelsoft.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "tbl_crm_express")
public class Express implements java.io.Serializable {
  /** 配送单ID */
  private String expressId;

  /**
   * 工单号
   * 
   * @deprecated
   */
  private String caseId;
  /** 客户ID */
  private String customer;
  /** 联系地址 */
  private String address;
  /** 联系电话 */
  private String tel;
  /** 订单号 */
  private String orderId;
  /** 联系人姓名 */
  private String contactor;
  private String zip;
  private String expressType;
  private String expressModlue;
  private String payType;
  private String payModlue;
  private String line;
  private Byte money;
  private Date expressDate;
  private Date expressLastdate;
  private String teamNo;
  private BigDecimal pay;
  private String expressState;
  private String memo;
  private String workflowId;
  private Date created;
  private Integer createdBy;
  private Date updated;
  private Integer updatedBy;

  public Express() {
  }

  @Id
  @Column(name = "EXPRESS_ID", unique = true, nullable = false, length = 12)
  public String getExpressId() {
    return this.expressId;
  }

  public void setExpressId(String expressId) {
    this.expressId = expressId;
  }

  @Column(name = "CASE_ID", length = 12)
  public String getCaseId() {
    return this.caseId;
  }

  public void setCaseId(String caseId) {
    this.caseId = caseId;
  }

  @Column(name = "CUSTOMER", nullable = false, length = 12)
  public String getCustomer() {
    return this.customer;
  }

  public void setCustomer(String customer) {
    this.customer = customer;
  }

  @Column(name = "ADDRESS", nullable = false, length = 50)
  public String getAddress() {
    return this.address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Column(name = "TEL", nullable = false, length = 35)
  public String getTel() {
    return this.tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  @Column(name = "ORDER_ID", nullable = false, length = 12)
  public String getOrderId() {
    return this.orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  @Column(name = "CONTACTOR", length = 12)
  public String getContactor() {
    return this.contactor;
  }

  public void setContactor(String contactor) {
    this.contactor = contactor;
  }

  @Column(name = "ZIP", length = 12)
  public String getZip() {
    return this.zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  @Column(name = "EXPRESS_TYPE", length = 12)
  public String getExpressType() {
    return this.expressType;
  }

  public void setExpressType(String expressType) {
    this.expressType = expressType;
  }

  @Column(name = "EXPRESS_MODLUE", length = 12)
  public String getExpressModlue() {
    return this.expressModlue;
  }

  public void setExpressModlue(String expressModlue) {
    this.expressModlue = expressModlue;
  }

  @Column(name = "PAY_TYPE", length = 12)
  public String getPayType() {
    return this.payType;
  }

  public void setPayType(String payType) {
    this.payType = payType;
  }

  @Column(name = "PAY_MODLUE", length = 12)
  public String getPayModlue() {
    return this.payModlue;
  }

  public void setPayModlue(String payModlue) {
    this.payModlue = payModlue;
  }

  @Column(name = "LINE", length = 20)
  public String getLine() {
    return this.line;
  }

  public void setLine(String line) {
    this.line = line;
  }

  @Column(name = "MONEY", precision = 2, scale = 0)
  public Byte getMoney() {
    return this.money;
  }

  public void setMoney(Byte money) {
    this.money = money;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "EXPRESS_DATE", nullable = false, length = 19)
  public Date getExpressDate() {
    return this.expressDate;
  }

  public void setExpressDate(Date expressDate) {
    this.expressDate = expressDate;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "EXPRESS_LASTDATE", nullable = false, length = 19)
  public Date getExpressLastdate() {
    return this.expressLastdate;
  }

  public void setExpressLastdate(Date expressLastdate) {
    this.expressLastdate = expressLastdate;
  }

  @Column(name = "TEAM_NO", length = 60)
  public String getTeamNo() {
    return this.teamNo;
  }

  public void setTeamNo(String teamNo) {
    this.teamNo = teamNo;
  }

  @Column(name = "PAY", precision = 16)
  public BigDecimal getPay() {
    return this.pay;
  }

  public void setPay(BigDecimal pay) {
    this.pay = pay;
  }

  @Column(name = "EXPRESS_STATE", nullable = false, length = 10)
  public String getExpressState() {
    return this.expressState;
  }

  public void setExpressState(String expressState) {
    this.expressState = expressState;
  }

  @Column(name = "MEMO", length = 500)
  public String getMemo() {
    return this.memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

  @Column(name = "WORKFLOW_ID", length = 64)
  public String getWorkflowId() {
    return this.workflowId;
  }

  public void setWorkflowId(String workflowId) {
    this.workflowId = workflowId;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "CREATED", length = 19)
  public Date getCreated() {
    return this.created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  @Column(name = "CREATEDBY")
  public Integer getCreatedBy() {
    return this.createdBy;
  }

  public void setCreatedBy(Integer createdby) {
    this.createdBy = createdby;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "UPDATED", nullable = false, length = 19)
  public Date getUpdated() {
    return this.updated;
  }

  public void setUpdated(Date updated) {
    this.updated = updated;
  }

  @Column(name = "UPDATEDBY")
  public Integer getUpdatedBy() {
    return this.updatedBy;
  }

  public void setUpdatedBy(Integer updatedby) {
    this.updatedBy = updatedby;
  }

  /** 线路名称 */
  private String lineName;
  /** 客户名称 */
  private String customerName;

  private String info;

  public Express clone() throws CloneNotSupportedException {
    return (Express) super.clone();
  }

  @Transient
  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  @Transient
  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  @Transient
  public String getLineName() {
    return lineName;
  }

  public void setLineName(String lineName) {
    this.lineName = lineName;
  }

}
