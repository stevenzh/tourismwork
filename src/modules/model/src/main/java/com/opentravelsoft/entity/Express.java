package com.opentravelsoft.entity;

import java.util.Date;

import com.opentravelsoft.model.BaseObject;

/**
 * 配送单
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:33 $
 */
public class Express extends BaseObject {

  private static final long serialVersionUID = -3521698455509277040L;
  /** 配送单ID */
  private String expressId;

  /** 线路名称 */
  private String lineName;

  /**
   * 工单号
   * 
   * @deprecated
   */
  private String caseId;

  /** 订单号 */
  private String orderId;

  /** 客户ID */
  private String customer;

  /** 客户名称 */
  private String customerName;

  /** 联系地址 */
  private String address;

  /** 联系电话 */
  private String tel;

  /** 联系人姓名 */
  private String contactor;

  private String zip;

  private String expressType;

  private String expressModlue;

  private String payType;

  private String payModlue;

  private String line;

  private Date expressDate;

  private Date expressLastdate;

  private String teamNo;

  private Double pay;

  private String expressState;

  private String memo;

  private String workflowId;

  private Date created;

  private Long createdBy;

  private Date updated;

  private Long updatedBy;

  private String info;

  @Override
  public Express clone() throws CloneNotSupportedException {
    return (Express) super.clone();
  }

  public String getExpressId() {
    return this.expressId;
  }

  public void setExpressId(String expressId) {
    this.expressId = expressId;
  }

  /**
   * 
   * @deprecated
   * @return
   */
  public String getCaseId() {
    return this.caseId;
  }

  /**
   * 
   * @param caseId
   * @deprecated
   */
  public void setCaseId(String caseId) {
    this.caseId = caseId;
  }

  public String getCustomer() {
    return this.customer;
  }

  public void setCustomer(String customer) {
    this.customer = customer;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public String getAddress() {
    return this.address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getTel() {
    return this.tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String getOrderId() {
    return this.orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public String getContactor() {
    return this.contactor;
  }

  public void setContactor(String contactor) {
    this.contactor = contactor;
  }

  public String getZip() {
    return this.zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public String getExpressType() {
    return this.expressType;
  }

  public void setExpressType(String expressType) {
    this.expressType = expressType;
  }

  public String getExpressModlue() {
    return this.expressModlue;
  }

  public void setExpressModlue(String expressModlue) {
    this.expressModlue = expressModlue;
  }

  public String getPayType() {
    return this.payType;
  }

  public void setPayType(String payType) {
    this.payType = payType;
  }

  public String getPayModlue() {
    return this.payModlue;
  }

  public void setPayModlue(String payModlue) {
    this.payModlue = payModlue;
  }

  public String getLine() {
    return this.line;
  }

  public void setLine(String line) {
    this.line = line;
  }

  public Date getExpressDate() {
    return this.expressDate;
  }

  public void setExpressDate(Date expressDate) {
    this.expressDate = expressDate;
  }

  public Date getExpressLastdate() {
    return this.expressLastdate;
  }

  public void setExpressLastdate(Date expressLastdate) {
    this.expressLastdate = expressLastdate;
  }

  public String getTeamNo() {
    return this.teamNo;
  }

  public void setTeamNo(String teamNo) {
    this.teamNo = teamNo;
  }

  public Double getPay() {
    return this.pay;
  }

  public void setPay(Double pay) {
    this.pay = pay;
  }

  public String getExpressState() {
    return this.expressState;
  }

  public void setExpressState(String expressState) {
    this.expressState = expressState;
  }

  public String getMemo() {
    return this.memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

  public String getWorkflowId() {
    return this.workflowId;
  }

  public void setWorkflowId(String workflowId) {
    this.workflowId = workflowId;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Long getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Long createdBy) {
    this.createdBy = createdBy;
  }

  public Date getUpdated() {
    return updated;
  }

  public void setUpdated(Date updated) {
    this.updated = updated;
  }

  public Long getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(Long updatedby) {
    this.updatedBy = updatedby;
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  public Express() {
  }

  public String getLineName() {
    return lineName;
  }

  public void setLineName(String lineName) {
    this.lineName = lineName;
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
