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
 * 旅游扩展服务
 */
@Entity
@Table(name = "tbl_extendservices")
public class ExtendServices implements java.io.Serializable {

  private Integer id;
  private String cityCd;
  private String cnSubject;
  private int lowLimit;
  private int upperLimit;
  private int type;
  private String remark;
  private BigDecimal price;
  private BigDecimal cost;
  private String unit;
  private Date startDate;
  private Date endDate;
  private boolean isDeleted;
  private String createdBy;
  private Date createdDate;
  private String createdByIp;
  private String modifiedBy;
  private Date modifiedDate;
  private String modifiedByIp;

  public ExtendServices() {
  }

  public ExtendServices(String cityCd, String cnSubject, int lowLimit,
      int upperLimit, int type, BigDecimal price, BigDecimal cost,
      Date startDate, Date endDate, boolean isDeleted) {
    this.cityCd = cityCd;
    this.cnSubject = cnSubject;
    this.lowLimit = lowLimit;
    this.upperLimit = upperLimit;
    this.type = type;
    this.price = price;
    this.cost = cost;
    this.startDate = startDate;
    this.endDate = endDate;
    this.isDeleted = isDeleted;
  }

  public ExtendServices(String cityCd, String cnSubject, int lowLimit,
      int upperLimit, int type, String remark, BigDecimal price,
      BigDecimal cost, String unit, Date startDate, Date endDate,
      boolean isDeleted, String createdBy, Date createdDate,
      String createdByIp, String modifiedBy, Date modifiedDate,
      String modifiedByIp) {
    this.cityCd = cityCd;
    this.cnSubject = cnSubject;
    this.lowLimit = lowLimit;
    this.upperLimit = upperLimit;
    this.type = type;
    this.remark = remark;
    this.price = price;
    this.cost = cost;
    this.unit = unit;
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
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "ID", unique = true, nullable = false)
  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Column(name = "CityCD", nullable = false, length = 10)
  public String getCityCd() {
    return this.cityCd;
  }

  public void setCityCd(String cityCd) {
    this.cityCd = cityCd;
  }

  @Column(name = "CnSubject", nullable = false, length = 200)
  public String getCnSubject() {
    return this.cnSubject;
  }

  public void setCnSubject(String cnSubject) {
    this.cnSubject = cnSubject;
  }

  @Column(name = "LowLimit", nullable = false)
  public int getLowLimit() {
    return this.lowLimit;
  }

  public void setLowLimit(int lowLimit) {
    this.lowLimit = lowLimit;
  }

  @Column(name = "UpperLimit", nullable = false)
  public int getUpperLimit() {
    return this.upperLimit;
  }

  public void setUpperLimit(int upperLimit) {
    this.upperLimit = upperLimit;
  }

  @Column(name = "Type", nullable = false)
  public int getType() {
    return this.type;
  }

  public void setType(int type) {
    this.type = type;
  }

  @Column(name = "Remark", length = 65535)
  public String getRemark() {
    return this.remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  @Column(name = "Price", nullable = false, precision = 9)
  public BigDecimal getPrice() {
    return this.price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  @Column(name = "Cost", nullable = false, precision = 9)
  public BigDecimal getCost() {
    return this.cost;
  }

  public void setCost(BigDecimal cost) {
    this.cost = cost;
  }

  @Column(name = "Unit", length = 20)
  public String getUnit() {
    return this.unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
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

  @Column(name = "CreatedByIp", length = 20)
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

  @Column(name = "ModifiedByIp", length = 20)
  public String getModifiedByIp() {
    return this.modifiedByIp;
  }

  public void setModifiedByIp(String modifiedByIp) {
    this.modifiedByIp = modifiedByIp;
  }

}
