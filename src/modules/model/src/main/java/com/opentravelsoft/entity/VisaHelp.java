package com.opentravelsoft.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.opentravelsoft.entity.product.ProductBase;
import com.opentravelsoft.entity.product.VisaItem;

/**
 * 签证服务
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:32 $
 */
public class VisaHelp extends ProductBase implements java.io.Serializable {
  private static final long serialVersionUID = 4356810574212931608L;

  private String recordNo;

  /** 国家/地区 CODE */
  private String country;

  /** 国家/地区名称 */
  private String cnName;

  /** 项目 */
  private String subject;

  /** 市场价（及直客价） */
  private double marketPrice;

  /** 同行价 */
  private double quotedPrice;

  /** 成本价 */
  private double costPrice;

  /** 单位 */
  private String unit;

  /** 开始有效日期 */
  private Date startDate;

  /** 终止有效日期 */
  private Date endDate;

  /** 条款内容 */
  private String note;

  private Long opUser;

  /** 签证种类 */
  private String visaKind;

  /** 停留天数 */
  private String stayDays;

  /** 办理时间 */
  private String transactDays;

  /** 网站开放 */
  private Integer isOpen;

  /** 是否可以办理加急 */
  private Integer canQuick;

  /** 所须材料列表 */
  private List<VisaItem> items;

  /** 所须附件列表 */
  private List<VisaItem> fileItems;

  private String recNo;

  /** 直客价 */
  private Double price1;

  /** 同行价 */
  private Double price2;

  private Double cost;

  private Date SDate;

  private Date EDate;

  private String opKey;

  private Date opDate;

  public VisaHelp() {
    country = "";
    subject = "";
    marketPrice = 0;
    quotedPrice = 0;
    costPrice = 0;
    unit = "";
    visaKind = "S";
    items = new ArrayList<VisaItem>();
    fileItems = new ArrayList<VisaItem>();
  }

  public String getRecNo() {
    return this.recNo;
  }

  public void setRecNo(String recNo) {
    this.recNo = recNo;
  }

  public Double getPrice1() {
    return this.price1;
  }

  public void setPrice1(Double price1) {
    this.price1 = price1;
  }

  public Double getPrice2() {
    return this.price2;
  }

  public void setPrice2(Double price2) {
    this.price2 = price2;
  }

  public Double getCost() {
    return this.cost;
  }

  public void setCost(Double cost) {
    this.cost = cost;
  }

  public Date getSDate() {
    return this.SDate;
  }

  public void setSDate(Date SDate) {
    this.SDate = SDate;
  }

  public Date getEDate() {
    return this.EDate;
  }

  public void setEDate(Date EDate) {
    this.EDate = EDate;
  }

  public String getOpKey() {
    return this.opKey;
  }

  public void setOpKey(String opKey) {
    this.opKey = opKey;
  }

  public Date getOpDate() {
    return this.opDate;
  }

  public void setOpDate(Date opDate) {
    this.opDate = opDate;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public double getMarketPrice() {
    return marketPrice;
  }

  public void setMarketPrice(double marketPrice) {
    this.marketPrice = marketPrice;
  }

  public double getQuotedPrice() {
    return quotedPrice;
  }

  public void setQuotedPrice(double quotedPrice) {
    this.quotedPrice = quotedPrice;
  }

  public double getCostPrice() {
    return costPrice;
  }

  public void setCostPrice(double costPrice) {
    this.costPrice = costPrice;
  }

  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String item) {
    this.subject = item;
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

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public String getCnName() {
    return cnName;
  }

  public void setCnName(String cnName) {
    this.cnName = cnName;
  }

  public Long getOpUser() {
    return opUser;
  }

  public void setOpUser(Long opUser) {
    this.opUser = opUser;
  }

  public List<VisaItem> getItems() {
    return items;
  }

  public void setItems(List<VisaItem> items) {
    this.items = items;
  }

  public String getVisaKind() {
    return visaKind;
  }

  public void setVisaKind(String visaKind) {
    this.visaKind = visaKind;
  }

  public String getStayDays() {
    return stayDays;
  }

  public void setStayDays(String stayDays) {
    this.stayDays = stayDays;
  }

  public String getTransactDays() {
    return transactDays;
  }

  public void setTransactDays(String transactDays) {
    this.transactDays = transactDays;
  }

  public Integer getIsOpen() {
    return isOpen;
  }

  public void setIsOpen(Integer isOpen) {
    this.isOpen = isOpen;
  }

  public List<VisaItem> getFileItems() {
    return fileItems;
  }

  public void setFileItems(List<VisaItem> fileItems) {
    this.fileItems = fileItems;
  }

  public Integer getCanQuick() {
    return canQuick;
  }

  public void setCanQuick(Integer canQuick) {
    this.canQuick = canQuick;
  }
}
