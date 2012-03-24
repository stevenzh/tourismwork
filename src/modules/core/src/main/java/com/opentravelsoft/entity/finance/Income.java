package com.opentravelsoft.entity.finance;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.opentravelsoft.entity.Customer;

/**
 * 收款单<br>
 * 一单唯一一个订单
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:32 $
 */
public class Income implements java.io.Serializable {

  private static final long serialVersionUID = -2485586888207997076L;

  /** 收款ID */
  private long incomeId;

  /** 客户ID */
  private Customer customer;

  /** 收款方式 (现金\转账\网上) */
  private String payMode;

  /** 收款人 */
  private String receiver;

  /** 备注 */
  private String note;

  /** 收款金额 */
  private Double amount;

  /** 付款时间 */
  private Date incomeDate;

  private Double offSetAmount;

  /** 创建时间 */
  private Date created;

  /** 创建人 */
  private Long createdBy;

  /** 更新时间 */
  private Date updated;

  /** 更新人 */
  private Long updatedBy;

  /** 付款类别（定金、预付款、余款） */
  private String useType;

  /** 订单ID */
  private String bookingNo;

  private String del;

  // -------------------------------------------------------------------------

  /** 未收 */
  private double unpay;

  /** 批数 */
  private int batch;

  /** 未销金额 */
  private double unOffSetMon;

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

  public Income() {
    this.payMode = "1";
    this.invices = new ArrayList<Invoice>();
    this.customer = new Customer();
  }

  public Income(int incomeId, int customerId) {
    this();
    this.incomeId = incomeId;
    customer.setCustomerId(customerId);
  }

  public long getIncomeId() {
    return this.incomeId;
  }

  public void setIncomeId(long incomeId) {
    this.incomeId = incomeId;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public String getReceiver() {
    return this.receiver;
  }

  public void setReceiver(String receiver) {
    this.receiver = receiver;
  }

  public String getNote() {
    return this.note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public Double getAmount() {
    return this.amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public Date getIncomeDate() {
    return this.incomeDate;
  }

  public void setIncomeDate(Date incomeDate) {
    this.incomeDate = incomeDate;
  }

  public Double getOffSetAmount() {
    return this.offSetAmount;
  }

  public void setOffSetAmount(Double offsetAmount) {
    this.offSetAmount = offsetAmount;
  }

  public Date getCreated() {
    return this.created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Long getCreatedBy() {
    return this.createdBy;
  }

  public void setCreatedBy(Long createdBy) {
    this.createdBy = createdBy;
  }

  public Date getUpdated() {
    return this.updated;
  }

  public void setUpdated(Date updated) {
    this.updated = updated;
  }

  public Long getUpdatedBy() {
    return this.updatedBy;
  }

  public void setUpdatedBy(Long updatedBy) {
    this.updatedBy = updatedBy;
  }

  public int getBatch() {
    return batch;
  }

  public void setBatch(int batch) {
    this.batch = batch;
  }

  public double getUnpay() {
    return unpay;
  }

  public void setUnpay(double unpay) {
    this.unpay = unpay;
  }

  public String getPayMode() {
    return payMode;
  }

  public void setPayMode(String payMode) {
    this.payMode = payMode;
  }

  public double getUnOffSetMon() {
    return unOffSetMon;
  }

  public void setUnOffSetMon(double unOffSetMon) {
    this.unOffSetMon = unOffSetMon;
  }

  public String getIncomeModeShow() {
    return incomeModeShow;
  }

  public void setIncomeModeShow(String incomeModeShow) {
    this.incomeModeShow = incomeModeShow;
  }

  public double getFinalExpense() {
    return finalExpense;
  }

  public void setFinalExpense(double finalExpense) {
    this.finalExpense = finalExpense;
  }

  public double getPayCosts() {
    return payCosts;
  }

  public void setPayCosts(double payCosts) {
    this.payCosts = payCosts;
  }

  public double getPayBack() {
    return payBack;
  }

  public void setPayBack(double payBack) {
    this.payBack = payBack;
  }

  public double getUnPay() {
    return unPay;
  }

  public void setUnPay(double unPay) {
    this.unPay = unPay;
  }

  public List<Invoice> getInvices() {
    return invices;
  }

  public void putInvices(Invoice inv) {
    invices.add(inv);
  }

  public void setInvices(List<Invoice> list) {
    this.invices = list;
  }

  public void setUpdateBy(Long updatedBy2) {
    this.updatedBy = updatedBy2;
  }

  public Long getUpdateBy() {
    return updatedBy;
  }

  public String getBookingNo() {
    return this.bookingNo;
  }

  public void setBookingNo(String bookingNo) {
    this.bookingNo = bookingNo;
  }

  public String getUseType() {
    return useType;
  }

  public void setUseType(String useType) {
    this.useType = useType;
  }

  public String getUseTypeLabel() {
    return useTypeLabel;
  }

  public void setUseTypeLabel(String useTypeLabel) {
    this.useTypeLabel = useTypeLabel;
  }

  public String getDel() {
    return del;
  }

  public void setDel(String del) {
    this.del = del;
  }

}
