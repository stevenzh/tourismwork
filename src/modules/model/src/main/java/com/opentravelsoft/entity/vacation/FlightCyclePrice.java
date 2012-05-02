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
@Table(name = "tbl_flightcycleprice")
public class FlightCyclePrice implements java.io.Serializable {

  private String id;
  private byte week;
  private BigDecimal adultCost;
  private BigDecimal childCost;
  private BigDecimal adultExtendPrice;
  private BigDecimal childExtendPrice;
  private Date currentDate;
  private String flightGroupId;
  private boolean isDeleted;

  public FlightCyclePrice() {
  }

  @Id
  @Column(name = "ID", unique = true, nullable = false, length = 36)
  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Column(name = "Week", nullable = false)
  public byte getWeek() {
    return this.week;
  }

  public void setWeek(byte week) {
    this.week = week;
  }

  @Column(name = "AdultCost", nullable = false, precision = 9)
  public BigDecimal getAdultCost() {
    return this.adultCost;
  }

  public void setAdultCost(BigDecimal adultCost) {
    this.adultCost = adultCost;
  }

  @Column(name = "ChildCost", nullable = false, precision = 9)
  public BigDecimal getChildCost() {
    return this.childCost;
  }

  public void setChildCost(BigDecimal childCost) {
    this.childCost = childCost;
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
  @Column(name = "CurrentDate", nullable = false, length = 19)
  public Date getCurrentDate() {
    return this.currentDate;
  }

  public void setCurrentDate(Date currentDate) {
    this.currentDate = currentDate;
  }

  @Column(name = "FlightGroupID", nullable = false, length = 36)
  public String getFlightGroupId() {
    return this.flightGroupId;
  }

  public void setFlightGroupId(String flightGroupId) {
    this.flightGroupId = flightGroupId;
  }

  @Column(name = "IsDeleted", nullable = false)
  public boolean isIsDeleted() {
    return this.isDeleted;
  }

  public void setIsDeleted(boolean isDeleted) {
    this.isDeleted = isDeleted;
  }

}
