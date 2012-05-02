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
@Table(name = "tbl_linecycleprice")
public class LineCyclePrice implements java.io.Serializable {

  private String id;
  private String planNo;
  private String lineNo;
  private byte week;
  private BigDecimal agentPrice;
  private BigDecimal marketPrice;
  private Date currentDate;
  private boolean isPassed;
  private boolean isOpen;
  private String notes;

  public LineCyclePrice() {
  }

  public LineCyclePrice(String id, String planNo, String lineNo, byte week,
      BigDecimal agentPrice, BigDecimal marketPrice, Date currentDate,
      boolean isPassed, boolean isOpen) {
    this.id = id;
    this.planNo = planNo;
    this.lineNo = lineNo;
    this.week = week;
    this.agentPrice = agentPrice;
    this.marketPrice = marketPrice;
    this.currentDate = currentDate;
    this.isPassed = isPassed;
    this.isOpen = isOpen;
  }

  public LineCyclePrice(String id, String planNo, String lineNo, byte week,
      BigDecimal agentPrice, BigDecimal marketPrice, Date currentDate,
      boolean isPassed, boolean isOpen, String notes) {
    this.id = id;
    this.planNo = planNo;
    this.lineNo = lineNo;
    this.week = week;
    this.agentPrice = agentPrice;
    this.marketPrice = marketPrice;
    this.currentDate = currentDate;
    this.isPassed = isPassed;
    this.isOpen = isOpen;
    this.notes = notes;
  }

  @Id
  @Column(name = "ID", unique = true, nullable = false, length = 36)
  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Column(name = "PlanNo", nullable = false, length = 10)
  public String getPlanNo() {
    return this.planNo;
  }

  public void setPlanNo(String planNo) {
    this.planNo = planNo;
  }

  @Column(name = "LineNo", nullable = false, length = 10)
  public String getLineNo() {
    return this.lineNo;
  }

  public void setLineNo(String lineNo) {
    this.lineNo = lineNo;
  }

  @Column(name = "Week", nullable = false)
  public byte getWeek() {
    return this.week;
  }

  public void setWeek(byte week) {
    this.week = week;
  }

  @Column(name = "AgentPrice", nullable = false, precision = 9)
  public BigDecimal getAgentPrice() {
    return this.agentPrice;
  }

  public void setAgentPrice(BigDecimal agentPrice) {
    this.agentPrice = agentPrice;
  }

  @Column(name = "MarketPrice", nullable = false, precision = 9)
  public BigDecimal getMarketPrice() {
    return this.marketPrice;
  }

  public void setMarketPrice(BigDecimal marketPrice) {
    this.marketPrice = marketPrice;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "CurrentDate", nullable = false, length = 19)
  public Date getCurrentDate() {
    return this.currentDate;
  }

  public void setCurrentDate(Date currentDate) {
    this.currentDate = currentDate;
  }

  @Column(name = "IsPassed", nullable = false)
  public boolean isIsPassed() {
    return this.isPassed;
  }

  public void setIsPassed(boolean isPassed) {
    this.isPassed = isPassed;
  }

  @Column(name = "IsOpen", nullable = false)
  public boolean isIsOpen() {
    return this.isOpen;
  }

  public void setIsOpen(boolean isOpen) {
    this.isOpen = isOpen;
  }

  @Column(name = "Notes", length = 1000)
  public String getNotes() {
    return this.notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

}
