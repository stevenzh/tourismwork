package com.opentravelsoft.entity.finance;

import java.math.BigDecimal;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tbl_reckoning_acct")
public class ReckoningAcct implements java.io.Serializable {

  private ReckoningAcctId id;
  /** 说明 摘要 */
  private String description;
  /** 单价 */
  private BigDecimal unitPrice;
  /** 份数 */
  private Integer count;
  /** 金额 */
  private BigDecimal amount;
  /** 单位 */
  private String unit;

  public ReckoningAcct() {
  }

  public ReckoningAcct(ReckoningAcctId id) {
    this.id = id;
  }

  public ReckoningAcct(ReckoningAcctId id, String description,
      BigDecimal unitPrice, Integer count, BigDecimal amount, String unit) {
    this.id = id;
    this.description = description;
    this.unitPrice = unitPrice;
    this.count = count;
    this.amount = amount;
    this.unit = unit;
  }

  @EmbeddedId
  @AttributeOverrides({
      @AttributeOverride(name = "reckoningId", column = @Column(name = "RECKONING_ID", nullable = false)),
      @AttributeOverride(name = "itemId", column = @Column(name = "ITEM_ID", nullable = false)) })
  public ReckoningAcctId getId() {
    return this.id;
  }

  public void setId(ReckoningAcctId id) {
    this.id = id;
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

  @Column(name = "AMOUNT", precision = 9)
  public BigDecimal getAmount() {
    return this.amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  @Column(name = "UNIT", length = 10)
  public String getUnit() {
    return this.unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  /** 帐单序号 */
  private int itemId;

  private String name;

  /** 订单号 */
  private String bookingNo;

  @Transient
  public int getItemId() {
    return itemId;
  }

  public void setItemId(int itemId) {
    this.itemId = itemId;
  }

  @Transient
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Transient
  public String getBookingNo() {
    return bookingNo;
  }

  public void setBookingNo(String bookingNo) {
    this.bookingNo = bookingNo;
  }

}
