package com.opentravelsoft.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "tbl_line_price")
public class LinePrice implements java.io.Serializable {
  /** 记录号 */
  private String recNo;
  /** 线路号 */
  private String lineNo;
  private Character priceKey;
  private String subject;
  private BigDecimal price1;
  private BigDecimal price2;
  private BigDecimal price3;
  private BigDecimal price4;
  private Date SDate;
  private Date EDate;
  /** 周表 */
  private String weekBit;
  private BigDecimal subscription;
  /** 差价 */
  private BigDecimal difference;
  private Integer crUser;
  private Date crDate;
  private Integer opUser;
  private Date opDate;
  /** 机票费 */
  private BigDecimal cost1;
  /** 国内段 */
  private BigDecimal cost2;
  /** 税 */
  private BigDecimal cost3;
  /** 地接费 */
  private BigDecimal cost4;
  /** 签证费 */
  private BigDecimal cost5;
  /** 名单费 */
  private BigDecimal cost6;
  /** 领队费 */
  private BigDecimal cost7;
  /** 其他费用 */
  private BigDecimal cost8;
  /** 餐费(天/人) */
  private BigDecimal cost9;
  /** 住宿费(天/人) */
  private BigDecimal cost10;
  /** 小交通 */
  private BigDecimal cost11;
  /** 景点门票 */
  private BigDecimal cost12;
  private String remarks;
  /** 币种 */
  private String currency;
  /** 汇率 */
  private BigDecimal roe;
  /** 单价(外币) */
  private BigDecimal unitPrice;
  /** 领队返佣 */
  private BigDecimal backMoney;
  /** 离境税 */
  private BigDecimal afieldDuty;

  public LinePrice() {
    this.weekBit = "YYYYYYY";
    select = false;
    defaultPrice = false;
  }

  public LinePrice(String recNo, Date opDate) {
    this.recNo = recNo;
    this.opDate = opDate;
  }

  @Id
  @Column(name = "REC_NO", unique = true, nullable = false, length = 8)
  public String getRecNo() {
    return this.recNo;
  }

  public void setRecNo(String recNo) {
    this.recNo = recNo;
  }

  @Column(name = "LINE_NO", length = 8)
  public String getLineNo() {
    return this.lineNo;
  }

  public void setLineNo(String lineNo) {
    this.lineNo = lineNo;
  }

  @Column(name = "PRICE_KEY", length = 1)
  public Character getPriceKey() {
    return this.priceKey;
  }

  public void setPriceKey(Character priceKey) {
    this.priceKey = priceKey;
  }

  @Column(name = "SUBJECT", length = 80)
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

  @Column(name = "PRICE3", precision = 9)
  public BigDecimal getPrice3() {
    return this.price3;
  }

  public void setPrice3(BigDecimal price3) {
    this.price3 = price3;
  }

  @Column(name = "PRICE4", precision = 9)
  public BigDecimal getPrice4() {
    return this.price4;
  }

  public void setPrice4(BigDecimal price4) {
    this.price4 = price4;
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

  @Column(name = "WEEK_BIT", length = 7)
  public String getWeekBit() {
    return this.weekBit;
  }

  public void setWeekBit(String weekBit) {
    this.weekBit = weekBit;
  }

  @Column(name = "SUBSCRIPTION", precision = 9)
  public BigDecimal getSubscription() {
    return this.subscription;
  }

  public void setSubscription(BigDecimal subscription) {
    this.subscription = subscription;
  }

  @Column(name = "DIFFERENCE", precision = 9)
  public BigDecimal getDifference() {
    return this.difference;
  }

  public void setDifference(BigDecimal difference) {
    this.difference = difference;
  }

  @Column(name = "CR_USER")
  public Integer getCrUser() {
    return this.crUser;
  }

  public void setCrUser(Integer crUser) {
    this.crUser = crUser;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "CR_DATE", length = 19)
  public Date getCrDate() {
    return this.crDate;
  }

  public void setCrDate(Date crDate) {
    this.crDate = crDate;
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

  @Column(name = "COST1", precision = 11)
  public BigDecimal getCost1() {
    return this.cost1;
  }

  public void setCost1(BigDecimal cost1) {
    this.cost1 = cost1;
  }

  @Column(name = "COST2", precision = 11)
  public BigDecimal getCost2() {
    return this.cost2;
  }

  public void setCost2(BigDecimal cost2) {
    this.cost2 = cost2;
  }

  @Column(name = "COST3", precision = 11)
  public BigDecimal getCost3() {
    return this.cost3;
  }

  public void setCost3(BigDecimal cost3) {
    this.cost3 = cost3;
  }

  @Column(name = "COST4", precision = 11)
  public BigDecimal getCost4() {
    return this.cost4;
  }

  public void setCost4(BigDecimal cost4) {
    this.cost4 = cost4;
  }

  @Column(name = "COST5", precision = 11)
  public BigDecimal getCost5() {
    return this.cost5;
  }

  public void setCost5(BigDecimal cost5) {
    this.cost5 = cost5;
  }

  @Column(name = "COST6", precision = 11)
  public BigDecimal getCost6() {
    return this.cost6;
  }

  public void setCost6(BigDecimal cost6) {
    this.cost6 = cost6;
  }

  @Column(name = "COST7", precision = 11)
  public BigDecimal getCost7() {
    return this.cost7;
  }

  public void setCost7(BigDecimal cost7) {
    this.cost7 = cost7;
  }

  @Column(name = "COST8", precision = 11)
  public BigDecimal getCost8() {
    return this.cost8;
  }

  public void setCost8(BigDecimal cost8) {
    this.cost8 = cost8;
  }

  @Column(name = "COST9", precision = 11)
  public BigDecimal getCost9() {
    return this.cost9;
  }

  public void setCost9(BigDecimal cost9) {
    this.cost9 = cost9;
  }

  @Column(name = "COST10", precision = 11)
  public BigDecimal getCost10() {
    return this.cost10;
  }

  public void setCost10(BigDecimal cost10) {
    this.cost10 = cost10;
  }

  @Column(name = "COST11", precision = 11)
  public BigDecimal getCost11() {
    return this.cost11;
  }

  public void setCost11(BigDecimal cost11) {
    this.cost11 = cost11;
  }

  @Column(name = "COST12", precision = 11)
  public BigDecimal getCost12() {
    return this.cost12;
  }

  public void setCost12(BigDecimal cost12) {
    this.cost12 = cost12;
  }

  @Column(name = "REMARKS", length = 100)
  public String getRemarks() {
    return this.remarks;
  }

  public void setRemarks(String reamrks) {
    this.remarks = reamrks;
  }

  @Column(name = "CURRENCY", length = 10)
  public String getCurrency() {
    return this.currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  @Column(name = "ROE", precision = 7)
  public BigDecimal getRoe() {
    return this.roe;
  }

  public void setRoe(BigDecimal roe) {
    this.roe = roe;
  }

  @Column(name = "UNIT_PRICE", precision = 11)
  public BigDecimal getUnitPrice() {
    return this.unitPrice;
  }

  public void setUnitPrice(BigDecimal unitPrice) {
    this.unitPrice = unitPrice;
  }

  @Column(name = "BACK_MONEY", precision = 11)
  public BigDecimal getBackMoney() {
    return this.backMoney;
  }

  public void setBackMoney(BigDecimal backMoney) {
    this.backMoney = backMoney;
  }

  @Column(name = "AFIELD_DUTY", precision = 11)
  public BigDecimal getAfieldDuty() {
    return this.afieldDuty;
  }

  public void setAfieldDuty(BigDecimal afieldDuty) {
    this.afieldDuty = afieldDuty;
  }

  /** 报价分类 */
  private String type;
  /** 直客价 */
  private BigDecimal price;
  /** 同行价 */
  private BigDecimal priceOther;
  /** 成本价 */
  private BigDecimal priceCost;
  /** 单人房差 */
  private Double priceContrast;
  /** 开始有效期 */
  private Date startDate;
  /** 结束有效期 */
  private Date endDate;
  private String priceAdd;
  /** 订金 */
  private Double subScription;

  /** 编号 */
  private int count;

  /** 签证费 */
  private Double priceVisa;

  /** 历史操作记录 */
  private String info;

  private String airId;

  private String areaId;

  private boolean select;

  private boolean defaultPrice;

  @Transient
  public String getAirId() {
    return this.airId;
  }

  public void setAirId(String airId) {
    this.airId = airId;
  }

  @Transient
  public String getAreaId() {
    return this.areaId;
  }

  public void setAreaId(String areaId) {
    this.areaId = areaId;
  }

  @Transient
  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  @Transient
  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  @Transient
  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  @Transient
  public String getPriceAdd() {
    return priceAdd;
  }

  public void setPriceAdd(String priceAdd) {
    this.priceAdd = priceAdd;
  }

  @Transient
  public Double getPriceContrast() {
    return priceContrast;
  }

  public void setPriceContrast(Double priceContrast) {
    this.priceContrast = priceContrast;
  }

  @Transient
  public BigDecimal getPriceCost() {
    return priceCost;
  }

  public void setPriceCost(BigDecimal priceCost) {
    this.priceCost = priceCost;
  }

  @Transient
  public BigDecimal getPriceOther() {
    return priceOther;
  }

  public void setPriceOther(BigDecimal priceOther) {
    this.priceOther = priceOther;
  }

  @Transient
  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  @Transient
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Transient
  public Double getPriceVisa() {
    return priceVisa;
  }

  public void setPriceVisa(Double priceVisa) {
    this.priceVisa = priceVisa;
  }

  @Transient
  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  @Transient
  public Double getSubScription() {
    return subScription;
  }

  public void setSubScription(Double subScription) {
    this.subScription = subScription;
  }

  @Transient
  public boolean getSelect() {
    return select;
  }

  public void setSelect(boolean select) {
    this.select = select;
  }

  public boolean isDefaultPrice() {
    return defaultPrice;
  }

  public void setDefaultPrice(boolean defaultPrice) {
    this.defaultPrice = defaultPrice;
  }

}
