package com.opentravelsoft.entity;

import java.util.Date;

/**
 * 团成本项目
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:32 $
 */
public class TourCost implements java.io.Serializable {

  private static final long serialVersionUID = 4584699774720196609L;

  /** 结算单号 */
  private long acctId;

  private Plan tour;

  /** 供应商 */
  private Customer customer;

  private String costType;

  /** 说明 */
  private String description;

  /** 单价 */
  private double unitPrice;

  /** 份数 */
  private Integer count;

  /** 单位 */
  private String unit;

  /** 币种 */
  private String currency;

  /** 汇率 */
  private double roe;

  /** 外币金额 */
  private double fcAmount;

  /** 金额 */
  private double amount;

  /** 已付金额 */
  private double payAmount;

  /** 是否做了付款申请书,已做-Y */
  private String isMakeOutcome;

  /** 财务审核否 */
  private String frChecked;

  /** 创建时间 */
  private Date created;

  /** 创建人ID */
  private Long createdBy;

  // ------------------------------------------------------------------------
  /** 创建人姓名 */
  private String createdName;

  private String info;

  private int id = 1;

  /** OP计算汇率 */
  private double opRoe;

  /** 修改时间 */
  private Date updated;

  /** 修改人 */
  private String updatedBy;

  private double nowpayPayment;

  public TourCost() {
    currency = "RMB";
    roe = 1d;
    opRoe = 1d;
    isMakeOutcome = "N";
    customer = new Customer();
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public String getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }

  public double getOpRoe() {
    return opRoe;
  }

  public void setOpRoe(double opRoe) {
    this.opRoe = opRoe;
  }

  public String getIsMakeOutcome() {
    return isMakeOutcome;
  }

  public void setIsMakeOutcome(String isMakeOutcome) {
    this.isMakeOutcome = isMakeOutcome;
  }

  public long getAcctId() {
    return this.acctId;
  }

  public void setAcctId(long acctId) {
    this.acctId = acctId;
  }

  public Plan getTour() {
    return tour;
  }

  public void setTour(Plan tour) {
    this.tour = tour;
  }

  public String getCostType() {
    return this.costType;
  }

  public void setCostType(String costType) {
    this.costType = costType;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public double getUnitPrice() {
    return this.unitPrice;
  }

  public void setUnitPrice(double unitPrice) {
    this.unitPrice = unitPrice;
  }

  public Integer getCount() {
    return this.count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public String getUnit() {
    return this.unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  public String getCurrency() {
    return this.currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public double getRoe() {
    return this.roe;
  }

  public void setRoe(double roe) {
    this.roe = roe;
  }

  public double getFcAmount() {
    return this.fcAmount;
  }

  public void setFcAmount(double fcAmount) {
    this.fcAmount = fcAmount;
  }

  public double getAmount() {
    return this.amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public double getPayAmount() {
    return this.payAmount;
  }

  public void setPayAmount(double payAmount) {
    this.payAmount = payAmount;
  }

  public Date getCreated() {
    return this.created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public String getFrChecked() {
    return this.frChecked;
  }

  public void setFrChecked(String frChecked) {
    this.frChecked = frChecked;
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  public String getCreatedName() {
    return createdName;
  }

  public void setCreatedName(String createdName) {
    this.createdName = createdName;
  }

  public double getNowpayPayment() {
    return nowpayPayment;
  }

  public void setNowpayPayment(double nowpayPayment) {
    this.nowpayPayment = nowpayPayment;
  }

}
