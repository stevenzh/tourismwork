package com.opentravelsoft.entity.vacation;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 其他服务
 */
@Entity
@Table(name = "tbl_booking_ext")
public class BookExtService implements java.io.Serializable {

  private Integer recNo;
  
  /** 订单号 */
  private String bookingNo;
  /** 服务类型 */
  private Byte type;
  
  /** 服务产品号 */
  private String productNo;
  private Integer num;
  private BigDecimal price;
  private BigDecimal totalPrice;
  private Date useDate;
  private Integer state;
  private String remark;
  private Byte isDelete;
  private String createdBy;
  private Date createdDate;
  private String modifiedBy;
  private Date modifiedDate;

  public BookExtService() {
  }

  public BookExtService(String bookingNo, Byte type, String productNo, Integer num,
      BigDecimal price, BigDecimal totalPrice, Date useDate, Integer state,
      String remark, Byte isDelete, String createdBy, Date createdDate,
      String modifiedBy, Date modifiedDate) {
    this.bookingNo = bookingNo;
    this.type = type;
    this.productNo = productNo;
    this.num = num;
    this.price = price;
    this.totalPrice = totalPrice;
    this.useDate = useDate;
    this.state = state;
    this.remark = remark;
    this.isDelete = isDelete;
    this.createdBy = createdBy;
    this.createdDate = createdDate;
    this.modifiedBy = modifiedBy;
    this.modifiedDate = modifiedDate;
  }

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "REC_NO", unique = true, nullable = false)
  public Integer getRecNo() {
    return this.recNo;
  }

  public void setRecNo(Integer recNo) {
    this.recNo = recNo;
  }

  @Column(name = "BookingNo", length = 10)
  public String getBookingNo() {
    return this.bookingNo;
  }

  public void setBookingNo(String bookingNo) {
    this.bookingNo = bookingNo;
  }

  @Column(name = "Type")
  public Byte getType() {
    return this.type;
  }

  public void setType(Byte type) {
    this.type = type;
  }

  @Column(name = "ProductNo", length = 10)
  public String getProductNo() {
    return this.productNo;
  }

  public void setProductNo(String productNo) {
    this.productNo = productNo;
  }

  @Column(name = "Num")
  public Integer getNum() {
    return this.num;
  }

  public void setNum(Integer num) {
    this.num = num;
  }

  @Column(name = "Price", precision = 9)
  public BigDecimal getPrice() {
    return this.price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  @Column(name = "TotalPrice", precision = 9)
  public BigDecimal getTotalPrice() {
    return this.totalPrice;
  }

  public void setTotalPrice(BigDecimal totalPrice) {
    this.totalPrice = totalPrice;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "UseDate", length = 19)
  public Date getUseDate() {
    return this.useDate;
  }

  public void setUseDate(Date useDate) {
    this.useDate = useDate;
  }

  @Column(name = "State")
  public Integer getState() {
    return this.state;
  }

  public void setState(Integer state) {
    this.state = state;
  }

  @Column(name = "Remark", length = 2000)
  public String getRemark() {
    return this.remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  @Column(name = "IsDelete")
  public Byte getIsDelete() {
    return this.isDelete;
  }

  public void setIsDelete(Byte isDelete) {
    this.isDelete = isDelete;
  }

  @Column(name = "CreatedBy", length = 20)
  public String getCreatedBy() {
    return this.createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "CreatedDate", length = 19)
  public Date getCreatedDate() {
    return this.createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  @Column(name = "ModifiedBy", length = 20)
  public String getModifiedBy() {
    return this.modifiedBy;
  }

  public void setModifiedBy(String modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "ModifiedDate", length = 19)
  public Date getModifiedDate() {
    return this.modifiedDate;
  }

  public void setModifiedDate(Date modifiedDate) {
    this.modifiedDate = modifiedDate;
  }

}
