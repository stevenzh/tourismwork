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
 * 订单选择航班
 */
@Entity
@Table(name = "tbl_booking_flight")
public class BookFlight implements java.io.Serializable {

  private Integer recNo;
  private String legNo;
  private String bookingNo;
  private String flightNo;
  private BigDecimal price;
  private BigDecimal childPrice;
  private BigDecimal extraPrice;
  private BigDecimal childExtraPrice;
  private BigDecimal totalPrice;
  private Integer refNo;
  private Integer state;
  private String remark;
  private String createdBy;
  private Date createdDate;
  private String modifiedBy;
  private Date modifiedDate;
  private Date departureDate;
  private Byte trafficType;
  private Byte isDelete;

  public BookFlight() {
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

  @Column(name = "LEG_NO", length = 10)
  public String getLegNo() {
    return this.legNo;
  }

  public void setLegNo(String legNo) {
    this.legNo = legNo;
  }

  @Column(name = "BookingNo", length = 10)
  public String getBookingNo() {
    return this.bookingNo;
  }

  public void setBookingNo(String bookingNo) {
    this.bookingNo = bookingNo;
  }

  @Column(name = "FlightNo", length = 10)
  public String getFlightNo() {
    return this.flightNo;
  }

  public void setFlightNo(String flightNo) {
    this.flightNo = flightNo;
  }

  @Column(name = "Price", precision = 9)
  public BigDecimal getPrice() {
    return this.price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  @Column(name = "ChildPrice", precision = 9)
  public BigDecimal getChildPrice() {
    return this.childPrice;
  }

  public void setChildPrice(BigDecimal childPrice) {
    this.childPrice = childPrice;
  }

  @Column(name = "ExtraPrice", precision = 9)
  public BigDecimal getExtraPrice() {
    return this.extraPrice;
  }

  public void setExtraPrice(BigDecimal extraPrice) {
    this.extraPrice = extraPrice;
  }

  @Column(name = "ChildExtraPrice", precision = 9)
  public BigDecimal getChildExtraPrice() {
    return this.childExtraPrice;
  }

  public void setChildExtraPrice(BigDecimal childExtraPrice) {
    this.childExtraPrice = childExtraPrice;
  }

  @Column(name = "TotalPrice", precision = 9)
  public BigDecimal getTotalPrice() {
    return this.totalPrice;
  }

  public void setTotalPrice(BigDecimal totalPrice) {
    this.totalPrice = totalPrice;
  }

  @Column(name = "RefNo")
  public Integer getRefNo() {
    return this.refNo;
  }

  public void setRefNo(Integer refNo) {
    this.refNo = refNo;
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

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "DepartureDate", length = 19)
  public Date getDepartureDate() {
    return this.departureDate;
  }

  public void setDepartureDate(Date departureDate) {
    this.departureDate = departureDate;
  }

  @Column(name = "TrafficType")
  public Byte getTrafficType() {
    return this.trafficType;
  }

  public void setTrafficType(Byte trafficType) {
    this.trafficType = trafficType;
  }

  @Column(name = "IsDelete")
  public Byte getIsDelete() {
    return this.isDelete;
  }

  public void setIsDelete(Byte isDelete) {
    this.isDelete = isDelete;
  }

}
