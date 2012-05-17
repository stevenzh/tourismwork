package com.opentravelsoft.entity;

import java.util.Date;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "tbl_tour_cost", catalog = "tourismwork_db")
public class TourCost implements java.io.Serializable {
  /** 结算单号 */
  private Integer acctId;
  private String costType;
  /** 说明 */
  private String description;
  /** 单价 */
  private BigDecimal unitPrice;
  /** 份数 */
  private Integer count;
  /** 单位 */
  private String unit;
  /** 币种 */
  private String currency;
  /** 汇率 */
  private BigDecimal roe;
  /** 外币金额 */
  private BigDecimal fcAmount;
  /** 金额 */
  private BigDecimal amount;
  /** 已付金额 */
  private BigDecimal payAmount;

  private char isMakePaper;
  /** 财务审核否 */
  private String frChecked;
  /** 创建时间 */
  private Date created;
  private Integer createdby;

  private Plan tour;

  /** 供应商 */
  private Customer customer;

  public TourCost() {
    currency = "RMB";
    opRoe = 1d;
    isMakeOutcome = "N";
    customer = new Customer();
  }

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "ACCT_ID", unique = true, nullable = false)
  public Integer getAcctId() {
    return this.acctId;
  }

  public void setAcctId(Integer acctId) {
    this.acctId = acctId;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "PLAN_NO")
  public Plan getTour() {
    return tour;
  }

  public void setTour(Plan tour) {
    this.tour = tour;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "CUSTOMER_ID")
  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  @Column(name = "COST_TYPE", length = 20)
  public String getCostType() {
    return this.costType;
  }

  public void setCostType(String costType) {
    this.costType = costType;
  }

  @Column(name = "DESCRIPTION", length = 1000)
  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Column(name = "UNIT_PRICE", precision = 9)
  public BigDecimal getUnitPrice() {
    return this.unitPrice;
  }

  public void setUnitPrice(BigDecimal unitPrice) {
    this.unitPrice = unitPrice;
  }

  @Column(name = "COUNT")
  public Integer getCount() {
    return this.count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  @Column(name = "UNIT", length = 10)
  public String getUnit() {
    return this.unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  @Column(name = "CURRENCY", length = 4)
  public String getCurrency() {
    return this.currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  @Column(name = "ROE", precision = 9, scale = 4)
  public BigDecimal getRoe() {
    return this.roe;
  }

  public void setRoe(BigDecimal roe) {
    this.roe = roe;
  }

  @Column(name = "FC_AMOUNT", precision = 9)
  public BigDecimal getFcAmount() {
    return this.fcAmount;
  }

  public void setFcAmount(BigDecimal fcAmount) {
    this.fcAmount = fcAmount;
  }

  @Column(name = "AMOUNT", precision = 9)
  public BigDecimal getAmount() {
    return this.amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  @Column(name = "PAY_AMOUNT", precision = 9)
  public BigDecimal getPayAmount() {
    return this.payAmount;
  }

  public void setPayAmount(BigDecimal payAmount) {
    this.payAmount = payAmount;
  }

  @Column(name = "IS_MAKE_PAPER", nullable = false, length = 1)
  public char getIsMakePaper() {
    return this.isMakePaper;
  }

  public void setIsMakePaper(char isMakePaper) {
    this.isMakePaper = isMakePaper;
  }

  @Column(name = "FR_CHECKED", length = 1)
  public String getFrChecked() {
    return this.frChecked;
  }

  public void setFrChecked(String frChecked) {
    this.frChecked = frChecked;
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
  public Integer getCreatedby() {
    return this.createdby;
  }

  public void setCreatedby(Integer createdby) {
    this.createdby = createdby;
  }

  /** 是否做了付款申请书,已做-Y */
  private String isMakeOutcome;

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
