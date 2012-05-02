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
 * 线路开班计划
 */
@Entity
@Table(name = "tbl_planfit")
public class FitPlan implements java.io.Serializable {

  private String recNo;
  private String lineNo;
  private Character planType;
  private String weekbit;
  private Integer checkDays;
  private Integer leastMan;
  private BigDecimal price;
  private Date SDate;
  private Date EDate;
  private Byte isDelete;
  private Integer pax;
  private Integer surpluspax;
  private Integer confirmPax;
  private Byte isShare;
  private Integer shareFlightId;
  private Character isLastMinute;
  private BigDecimal discountRate;
  
  private String createdBy;
  private Date createdDate;
  private String modifiedBy;
  private Date modifiedDate;
  private String createdByIp;
  private String modifiedByIp;

  public FitPlan() {
  }

  public FitPlan(String recNo) {
    this.recNo = recNo;
  }

  @Id
  @Column(name = "REC_NO", unique = true, nullable = false, length = 10)
  public String getRecNo() {
    return this.recNo;
  }

  public void setRecNo(String recNo) {
    this.recNo = recNo;
  }

  @Column(name = "LINE_NO", length = 10)
  public String getLineNo() {
    return this.lineNo;
  }

  public void setLineNo(String lineNo) {
    this.lineNo = lineNo;
  }

  @Column(name = "PLAN_TYPE", length = 1)
  public Character getPlanType() {
    return this.planType;
  }

  public void setPlanType(Character planType) {
    this.planType = planType;
  }

  @Column(name = "WEEKBIT", length = 7)
  public String getWeekbit() {
    return this.weekbit;
  }

  public void setWeekbit(String weekbit) {
    this.weekbit = weekbit;
  }

  @Column(name = "CHECK_DAYS")
  public Integer getCheckDays() {
    return this.checkDays;
  }

  public void setCheckDays(Integer checkDays) {
    this.checkDays = checkDays;
  }

  @Column(name = "LEAST_MAN")
  public Integer getLeastMan() {
    return this.leastMan;
  }

  public void setLeastMan(Integer leastMan) {
    this.leastMan = leastMan;
  }

  @Column(name = "PRICE", precision = 9)
  public BigDecimal getPrice() {
    return this.price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "S_DATE", length = 19)
  public Date getSDate() {
    return this.SDate;
  }

  public void setSDate(Date SDate) {
    this.SDate = SDate;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "E_DATE", length = 19)
  public Date getEDate() {
    return this.EDate;
  }

  public void setEDate(Date EDate) {
    this.EDate = EDate;
  }

  @Column(name = "IS_DELETE")
  public Byte getIsDelete() {
    return this.isDelete;
  }

  public void setIsDelete(Byte isDelete) {
    this.isDelete = isDelete;
  }

  @Column(name = "PAX")
  public Integer getPax() {
    return this.pax;
  }

  public void setPax(Integer pax) {
    this.pax = pax;
  }

  @Column(name = "SURPLUSPAX")
  public Integer getSurpluspax() {
    return this.surpluspax;
  }

  public void setSurpluspax(Integer surpluspax) {
    this.surpluspax = surpluspax;
  }

  @Column(name = "ConfirmPax")
  public Integer getConfirmPax() {
    return this.confirmPax;
  }

  public void setConfirmPax(Integer confirmPax) {
    this.confirmPax = confirmPax;
  }

  @Column(name = "IS_SHARE")
  public Byte getIsShare() {
    return this.isShare;
  }

  public void setIsShare(Byte isShare) {
    this.isShare = isShare;
  }

  @Column(name = "SHARE_FLIGHT_ID")
  public Integer getShareFlightId() {
    return this.shareFlightId;
  }

  public void setShareFlightId(Integer shareFlightId) {
    this.shareFlightId = shareFlightId;
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

  @Column(name = "IS_LAST_MINUTE", length = 1)
  public Character getIsLastMinute() {
    return this.isLastMinute;
  }

  public void setIsLastMinute(Character isLastMinute) {
    this.isLastMinute = isLastMinute;
  }

  @Column(name = "DISCOUNT_RATE", precision = 5)
  public BigDecimal getDiscountRate() {
    return this.discountRate;
  }

  public void setDiscountRate(BigDecimal discountRate) {
    this.discountRate = discountRate;
  }

  @Column(name = "CreatedByIp", length = 50)
  public String getCreatedByIp() {
    return this.createdByIp;
  }

  public void setCreatedByIp(String createdByIp) {
    this.createdByIp = createdByIp;
  }

  @Column(name = "ModifiedByIp", length = 50)
  public String getModifiedByIp() {
    return this.modifiedByIp;
  }

  public void setModifiedByIp(String modifiedByIp) {
    this.modifiedByIp = modifiedByIp;
  }

}
