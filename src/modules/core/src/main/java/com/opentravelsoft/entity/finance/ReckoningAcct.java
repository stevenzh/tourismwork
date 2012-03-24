package com.opentravelsoft.entity.finance;

/**
 * 帐单明细
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:32 $
 */
public class ReckoningAcct {
  /** 帐单号 */
  private long reckoningId;

  /** 帐单序号 */
  private int itemId;

  /** 说明 摘要 */
  private String description;

  /** 份数 */
  private int count;

  /** 单价 */
  private double unitPrice;

  /** 金额 */
  private double amount;

  /** 单位 */
  private String unit;

  private String name;

  /** 订单号 */
  private String bookingNo;

  private ReckoningAcctId id;

  public long getReckoningId() {
    return reckoningId;
  }

  public void setReckoningId(long reckoningId) {
    this.reckoningId = reckoningId;
  }

  public int getItemId() {
    return itemId;
  }

  public void setItemId(int itemId) {
    this.itemId = itemId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public double getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(double unitPrice) {
    this.unitPrice = unitPrice;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBookingNo() {
    return bookingNo;
  }

  public void setBookingNo(String bookingNo) {
    this.bookingNo = bookingNo;
  }

  public ReckoningAcctId getId() {
    return this.id;
  }

  public void setId(ReckoningAcctId id) {
    this.id = id;
  }
}
