package com.opentravelsoft.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.opentravelsoft.entity.product.VisaItem;

@Entity
@Table(name = "tbl_visa")
public class VisaHelp implements java.io.Serializable {

  private String recNo;
  /** 国家/地区 CODE */
  private String country;
  /** 项目 */
  private String subject;
  /** 直客价 */
  private BigDecimal price1;
  /** 同行价 */
  private BigDecimal price2;

  private BigDecimal cost;
  /** 单位 */
  private String unit;
  private Date SDate;
  private Date EDate;
  /** 条款内容 */
  private String note;
  /** 是否可以办理加急 */
  private Integer canQuick;
  /** 签证种类 */
  private Character visaKind;
  /** 停留天数 */
  private String stayDays;
  /** 办理时间 */
  private String transactDays;
  /** 网站开放 */
  private Integer isOpen;
  private Character opKey;
  private Integer opUser;
  private Date opDate;

  public VisaHelp() {
    country = "";
    subject = "";
    marketPrice = new BigDecimal(0);
    quotedPrice = new BigDecimal(0);
    costPrice = new BigDecimal(0);
    unit = "";
    visaKind = 'S';
    items = new ArrayList<VisaItem>();
    fileItems = new ArrayList<VisaItem>();
  }

  public VisaHelp(String recNo, Date opDate) {
    this.recNo = recNo;
    this.opDate = opDate;
  }

  @Id
  @Column(name = "REC_NO", unique = true, nullable = false, length = 10)
  public String getRecNo() {
    return this.recNo;
  }

  public void setRecNo(String recNo) {
    this.recNo = recNo;
  }

  @Column(name = "COUNTRY", length = 2)
  public String getCountry() {
    return this.country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  @Column(name = "SUBJECT", length = 200)
  public String getSubject() {
    return this.subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  @Column(name = "PRICE1", precision = 9)
  public BigDecimal getPrice1() {
    return this.price1;
  }

  public void setPrice1(BigDecimal price1) {
    this.price1 = price1;
  }

  @Column(name = "PRICE2", precision = 9)
  public BigDecimal getPrice2() {
    return this.price2;
  }

  public void setPrice2(BigDecimal price2) {
    this.price2 = price2;
  }

  @Column(name = "COST", precision = 9)
  public BigDecimal getCost() {
    return this.cost;
  }

  public void setCost(BigDecimal cost) {
    this.cost = cost;
  }

  @Column(name = "UNIT", length = 16)
  public String getUnit() {
    return this.unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
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

  @Column(name = "NOTE", length = 3000)
  public String getNote() {
    return this.note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  @Column(name = "CAN_QUICK")
  public Integer getCanQuick() {
    return this.canQuick;
  }

  public void setCanQuick(Integer canQuick) {
    this.canQuick = canQuick;
  }

  @Column(name = "VISA_KIND", length = 1)
  public Character getVisaKind() {
    return this.visaKind;
  }

  public void setVisaKind(Character visaKind) {
    this.visaKind = visaKind;
  }

  @Column(name = "STAY_DAYS", length = 20)
  public String getStayDays() {
    return this.stayDays;
  }

  public void setStayDays(String stayDays) {
    this.stayDays = stayDays;
  }

  @Column(name = "TRANSACT_DAYS", length = 20)
  public String getTransactDays() {
    return this.transactDays;
  }

  public void setTransactDays(String transactDays) {
    this.transactDays = transactDays;
  }

  @Column(name = "IS_OPEN")
  public Integer getIsOpen() {
    return this.isOpen;
  }

  public void setIsOpen(Integer isOpen) {
    this.isOpen = isOpen;
  }

  @Column(name = "OP_KEY", length = 1)
  public Character getOpKey() {
    return this.opKey;
  }

  public void setOpKey(Character opKey) {
    this.opKey = opKey;
  }

  @Column(name = "OP_USER")
  public Integer getOpUser() {
    return this.opUser;
  }

  public void setOpUser(Integer opUser) {
    this.opUser = opUser;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "OP_DATE", nullable = false, length = 19)
  public Date getOpDate() {
    return this.opDate;
  }

  public void setOpDate(Date opDate) {
    this.opDate = opDate;
  }

  private String recordNo;

  /** 国家/地区名称 */
  private String cnName;

  /** 市场价（及直客价） */
  private BigDecimal marketPrice;

  /** 同行价 */
  private BigDecimal quotedPrice;

  /** 成本价 */
  private BigDecimal costPrice;

  /** 开始有效日期 */
  private Date startDate;

  /** 终止有效日期 */
  private Date endDate;

  /** 所须材料列表 */
  private List<VisaItem> items;

  /** 所须附件列表 */
  private List<VisaItem> fileItems;

  public BigDecimal getMarketPrice() {
    return marketPrice;
  }

  public void setMarketPrice(BigDecimal marketPrice) {
    this.marketPrice = marketPrice;
  }

  public BigDecimal getQuotedPrice() {
    return quotedPrice;
  }

  public void setQuotedPrice(BigDecimal quotedPrice) {
    this.quotedPrice = quotedPrice;
  }

  public BigDecimal getCostPrice() {
    return costPrice;
  }

  public void setCostPrice(BigDecimal costPrice) {
    this.costPrice = costPrice;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public String getRecordNo() {
    return recordNo;
  }

  public void setRecordNo(String recordNo) {
    this.recordNo = recordNo;
  }

  public String getCnName() {
    return cnName;
  }

  public void setCnName(String cnName) {
    this.cnName = cnName;
  }

  public List<VisaItem> getItems() {
    return items;
  }

  public void setItems(List<VisaItem> items) {
    this.items = items;
  }

  public List<VisaItem> getFileItems() {
    return fileItems;
  }

  public void setFileItems(List<VisaItem> fileItems) {
    this.fileItems = fileItems;
  }

}
