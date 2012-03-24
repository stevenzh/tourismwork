package com.opentravelsoft.entity.vacation;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 */
@Entity
@Table(name = "tbl_flightplan")
public class FlightPlan implements java.io.Serializable {

  private String flightPlanId;
  private int mode;
  private Integer ticketType;
  private int flightAmount;
  private String adultPrice;
  private String childPrice;
  private BigDecimal adultExtendPrice;
  private BigDecimal childExtendPrice;
  private Date startDate;
  private Date endDate;
  private boolean isDeleted;
  private String createdBy;
  private Date createdDate;
  private String createdByIp;
  private String modifiedBy;
  private Date modifiedDate;
  private String modifiedByIp;

  public FlightPlan() {
  }

  public FlightPlan(String flightPlanId, int mode, int flightAmount,
      BigDecimal adultExtendPrice, BigDecimal childExtendPrice, Date startDate,
      Date endDate, boolean isDeleted) {
    this.flightPlanId = flightPlanId;
    this.mode = mode;
    this.flightAmount = flightAmount;
    this.adultExtendPrice = adultExtendPrice;
    this.childExtendPrice = childExtendPrice;
    this.startDate = startDate;
    this.endDate = endDate;
    this.isDeleted = isDeleted;
  }

  public FlightPlan(String flightPlanId, int mode, Integer ticketType,
      int flightAmount, String adultPrice, String childPrice,
      BigDecimal adultExtendPrice, BigDecimal childExtendPrice, Date startDate,
      Date endDate, boolean isDeleted, String createdBy, Date createdDate,
      String createdByIp, String modifiedBy, Date modifiedDate,
      String modifiedByIp) {
    this.flightPlanId = flightPlanId;
    this.mode = mode;
    this.ticketType = ticketType;
    this.flightAmount = flightAmount;
    this.adultPrice = adultPrice;
    this.childPrice = childPrice;
    this.adultExtendPrice = adultExtendPrice;
    this.childExtendPrice = childExtendPrice;
    this.startDate = startDate;
    this.endDate = endDate;
    this.isDeleted = isDeleted;
    this.createdBy = createdBy;
    this.createdDate = createdDate;
    this.createdByIp = createdByIp;
    this.modifiedBy = modifiedBy;
    this.modifiedDate = modifiedDate;
    this.modifiedByIp = modifiedByIp;
  }

  @Id
  @Column(name = "FlightPlanID", unique = true, nullable = false, length = 36)
  public String getFlightPlanId() {
    return this.flightPlanId;
  }

  public void setFlightPlanId(String flightPlanId) {
    this.flightPlanId = flightPlanId;
  }

  @Column(name = "Mode", nullable = false)
  public int getMode() {
    return this.mode;
  }

  public void setMode(int mode) {
    this.mode = mode;
  }

  @Column(name = "TicketType")
  public Integer getTicketType() {
    return this.ticketType;
  }

  public void setTicketType(Integer ticketType) {
    this.ticketType = ticketType;
  }

  @Column(name = "FlightAmount", nullable = false)
  public int getFlightAmount() {
    return this.flightAmount;
  }

  public void setFlightAmount(int flightAmount) {
    this.flightAmount = flightAmount;
  }

  @Column(name = "AdultPrice", length = 100)
  public String getAdultPrice() {
    return this.adultPrice;
  }

  public void setAdultPrice(String adultPrice) {
    this.adultPrice = adultPrice;
  }

  @Column(name = "ChildPrice", length = 100)
  public String getChildPrice() {
    return this.childPrice;
  }

  public void setChildPrice(String childPrice) {
    this.childPrice = childPrice;
  }

  @Column(name = "AdultExtendPrice", nullable = false, precision = 7)
  public BigDecimal getAdultExtendPrice() {
    return this.adultExtendPrice;
  }

  public void setAdultExtendPrice(BigDecimal adultExtendPrice) {
    this.adultExtendPrice = adultExtendPrice;
  }

  @Column(name = "ChildExtendPrice", nullable = false, precision = 7)
  public BigDecimal getChildExtendPrice() {
    return this.childExtendPrice;
  }

  public void setChildExtendPrice(BigDecimal childExtendPrice) {
    this.childExtendPrice = childExtendPrice;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "StartDate", nullable = false, length = 19)
  public Date getStartDate() {
    return this.startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "EndDate", nullable = false, length = 19)
  public Date getEndDate() {
    return this.endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  @Column(name = "IsDeleted", nullable = false)
  public boolean isIsDeleted() {
    return this.isDeleted;
  }

  public void setIsDeleted(boolean isDeleted) {
    this.isDeleted = isDeleted;
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

  @Column(name = "CreatedByIp", length = 50)
  public String getCreatedByIp() {
    return this.createdByIp;
  }

  public void setCreatedByIp(String createdByIp) {
    this.createdByIp = createdByIp;
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

  @Column(name = "ModifiedByIp", length = 50)
  public String getModifiedByIp() {
    return this.modifiedByIp;
  }

  public void setModifiedByIp(String modifiedByIp) {
    this.modifiedByIp = modifiedByIp;
  }

}
