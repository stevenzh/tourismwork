package com.opentravelsoft.entity.finance;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import javax.persistence.Transient;

import com.opentravelsoft.entity.Customer;

@Entity
@Table(name = "tbl_income")
public class Income implements java.io.Serializable {
  /** 收款ID */
  private Integer incomeId;
  /** 客户ID */
  private Customer customer;
  /** 收款方式 (现金\转账\网上) */
  private Character payMode;
  private String dptNo;
  /** 收款人 */
  private String receiver;
  /** 备注 */
  private String note;
  /** 收款金额 */
  private BigDecimal amount;
  /** 付款时间 */
  private Date incomeDate;
  private BigDecimal offsetAmount;
  /** 创建时间 */
  private Date created;
  private Integer createdby;
  /** 更新时间 */
  private Date updated;
  private Integer updatedby;
  /** 订单ID */
  private String bookingNo;
  /** 付款类别（定金、预付款、余款） */
  private String useType;

  public Income() {
    this.payMode = '1';
    this.invices = new ArrayList<Invoice>();
    this.customer = new Customer();
  }

  public Income(int incomeId, int customerId) {
    this();
    this.incomeId = incomeId;
    customer.setCustomerId(customerId);
  }

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "INCOME_ID", unique = true, nullable = false)
  public Integer getIncomeId() {
    return this.incomeId;
  }

  public void setIncomeId(Integer incomeId) {
    this.incomeId = incomeId;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "CUSTOMER_ID")
  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  @Column(name = "PAY_MODE", length = 1)
  public Character getPayMode() {
    return this.payMode;
  }

  public void setPayMode(Character payMode) {
    this.payMode = payMode;
  }

  @Column(name = "DPT_NO", length = 6)
  public String getDptNo() {
    return this.dptNo;
  }

  public void setDptNo(String dptNo) {
    this.dptNo = dptNo;
  }

  @Column(name = "RECEIVER", length = 20)
  public String getReceiver() {
    return this.receiver;
  }

  public void setReceiver(String receiver) {
    this.receiver = receiver;
  }

  @Column(name = "NOTE", length = 1000)
  public String getNote() {
    return this.note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  @Column(name = "AMOUNT", precision = 10)
  public BigDecimal getAmount() {
    return this.amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "INCOME_DATE", length = 19)
  public Date getIncomeDate() {
    return this.incomeDate;
  }

  public void setIncomeDate(Date incomeDate) {
    this.incomeDate = incomeDate;
  }

  @Column(name = "OFFSET_AMOUNT", precision = 10)
  public BigDecimal getOffsetAmount() {
    return this.offsetAmount;
  }

  public void setOffsetAmount(BigDecimal offsetAmount) {
    this.offsetAmount = offsetAmount;
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

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "UPDATED", nullable = false, length = 19)
  public Date getUpdated() {
    return this.updated;
  }

  public void setUpdated(Date updated) {
    this.updated = updated;
  }

  @Column(name = "UPDATEDBY")
  public Integer getUpdatedby() {
    return this.updatedby;
  }

  public void setUpdatedby(Integer updatedby) {
    this.updatedby = updatedby;
  }

  @Column(name = "BOOKING_NO", nullable = false, length = 20)
  public String getBookingNo() {
    return this.bookingNo;
  }

  public void setBookingNo(String bookingNo) {
    this.bookingNo = bookingNo;
  }

  @Column(name = "USE_TYPE", length = 20)
  public String getUseType() {
    return this.useType;
  }

  public void setUseType(String useType) {
    this.useType = useType;
  }

  private BigDecimal offSetAmount;

  /** 创建人 */
  private Integer createdBy;

  /** 更新人 */
  private Integer updatedBy;

  private String del;

  /** 未收 */
  private double unpay;

  /** 批数 */
  private int batch;

  /** 未销金额 */
  private BigDecimal unOffSetMon;

  /** 应付 */
  private double finalExpense;

  /** 已付费用 */
  private double payCosts;

  /** 现付费用 */
  private double payBack;

  /** 未付费用 */
  private double unPay;

  private String incomeModeShow;

  private String useTypeLabel;

  /** 发票记录 */
  private List<Invoice> invices;

  @Transient
  public BigDecimal getOffSetAmount() {
    return this.offSetAmount;
  }

  public void setOffSetAmount(BigDecimal offsetAmount) {
    this.offSetAmount = offsetAmount;
  }

  @Transient
  public Integer getCreatedBy() {
    return this.createdBy;
  }

  public void setCreatedBy(Integer createdBy) {
    this.createdBy = createdBy;
  }

  @Transient
  public Integer getUpdatedBy() {
    return this.updatedBy;
  }

  public void setUpdatedBy(Integer updatedBy) {
    this.updatedBy = updatedBy;
  }

  @Transient
  public int getBatch() {
    return batch;
  }

  public void setBatch(int batch) {
    this.batch = batch;
  }

  @Transient
  public double getUnpay() {
    return unpay;
  }

  public void setUnpay(double unpay) {
    this.unpay = unpay;
  }

  @Transient
  public BigDecimal getUnOffSetMon() {
    return unOffSetMon;
  }

  public void setUnOffSetMon(BigDecimal unOffSetMon) {
    this.unOffSetMon = unOffSetMon;
  }

  @Transient
  public String getIncomeModeShow() {
    return incomeModeShow;
  }

  public void setIncomeModeShow(String incomeModeShow) {
    this.incomeModeShow = incomeModeShow;
  }

  @Transient
  public double getFinalExpense() {
    return finalExpense;
  }

  public void setFinalExpense(double finalExpense) {
    this.finalExpense = finalExpense;
  }

  @Transient
  public double getPayCosts() {
    return payCosts;
  }

  public void setPayCosts(double payCosts) {
    this.payCosts = payCosts;
  }

  @Transient
  public double getPayBack() {
    return payBack;
  }

  public void setPayBack(double payBack) {
    this.payBack = payBack;
  }

  @Transient
  public double getUnPay() {
    return unPay;
  }

  public void setUnPay(double unPay) {
    this.unPay = unPay;
  }

  @Transient
  public List<Invoice> getInvices() {
    return invices;
  }

  public void putInvices(Invoice inv) {
    invices.add(inv);
  }

  public void setInvices(List<Invoice> list) {
    this.invices = list;
  }

  public void setUpdateBy(Integer updatedBy2) {
    this.updatedBy = updatedBy2;
  }

  @Transient
  public Integer getUpdateBy() {
    return updatedBy;
  }

  @Transient
  public String getUseTypeLabel() {
    return useTypeLabel;
  }

  public void setUseTypeLabel(String useTypeLabel) {
    this.useTypeLabel = useTypeLabel;
  }

  @Transient
  public String getDel() {
    return del;
  }

  public void setDel(String del) {
    this.del = del;
  }

}
