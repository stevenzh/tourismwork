package com.opentravelsoft.entity;

import java.util.Date;

/**
 * 线路报价
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:33 $
 */
public class LinePrice implements java.io.Serializable {
  private static final long serialVersionUID = 3447374854636083927L;

  /** 记录号 */
  private String recNo;

  /** 线路号 */
  private String lineNo;

  /** 报价分类 */
  private String type;

  /** 直客价 */
  private Double price;

  /** 同行价 */
  private Double priceOther;

  /** 成本价 */
  private Double priceCost;

  /** 单人房差 */
  private Double priceContrast;

  /** 开始有效期 */
  private Date startDate;

  /** 结束有效期 */
  private Date endDate;

  /** 周表 */
  private String weekBit;

  private String priceAdd;

  private Long opUser;

  private Date opDate;

  private Long crUser;

  private Date crDate;

  /** 订金 */
  private Double subScription;

  /** 差价 */
  private Double difference;

  // ------------------------------------------------------------------------

  /** 机票费 */
  private Double cost1;

  /** 国内段 */
  private Double cost2;

  /** 税 */
  private Double cost3;

  /** 地接费 */
  private Double cost4;

  /** 签证费 */
  private Double cost5;

  /** 名单费 */
  private Double cost6;

  /** 领队费 */
  private Double cost7;

  /** 其他费用 */
  private Double cost8;

  /** 餐费(天/人) */
  private Double cost9;

  /** 住宿费(天/人) */
  private Double cost10;

  /** 小交通 */
  private Double cost11;

  /** 景点门票 */
  private Double cost12;

  private String remarks;

  /** 币种 */
  private String currency;

  /** 汇率 */
  private Double roe;

  /** 单价(外币) */
  private Double unitPrice;

  /** 领队返佣 */
  private Double backMoney;

  /** 离境税 */
  private Double afieldDuty;

  // ------------------------------------------------------------------------

  /** 编号 */
  private int count;

  /** 签证费 */
  private Double priceVisa;

  /** 历史操作记录 */
  private String info;

  private String airId;

  private String areaId;

  private String reamrks;

  // -------------------------------------------------------------------------
  private boolean select;

  private boolean defaultPrice;

  public LinePrice() {
    this.weekBit = "YYYYYYY";
    select = false;
    defaultPrice = false;
    cost1 = 0d;
    cost2 = 0d;
    cost3 = 0d;
    cost4 = 0d;
    cost5 = 0d;
    cost6 = 0d;
    cost7 = 0d;
    cost8 = 0d;
    cost9 = 0d;
    cost10 = 0d;
    cost11 = 0d;
    cost12 = 0d;
    afieldDuty = 0d;
    backMoney = 0d;
    roe = 1d;
  }

  public String getLineNo() {
    return this.lineNo;
  }

  public void setLineNo(String lineNo) {
    this.lineNo = lineNo;
  }

  public String getAirId() {
    return this.airId;
  }

  public void setAirId(String airId) {
    this.airId = airId;
  }

  public String getAreaId() {
    return this.areaId;
  }

  public void setAreaId(String areaId) {
    this.areaId = areaId;
  }

  public Long getCrUser() {
    return this.crUser;
  }

  public void setCrUser(Long crUser) {
    this.crUser = crUser;
  }

  public Date getCrDate() {
    return this.crDate;
  }

  public void setCrDate(Date crDate) {
    this.crDate = crDate;
  }

  public Date getOpDate() {
    return this.opDate;
  }

  public void setOpDate(Date opDate) {
    this.opDate = opDate;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public String getPriceAdd() {
    return priceAdd;
  }

  public void setPriceAdd(String priceAdd) {
    this.priceAdd = priceAdd;
  }

  public Double getPriceContrast() {
    return priceContrast;
  }

  public void setPriceContrast(Double priceContrast) {
    this.priceContrast = priceContrast;
  }

  public Double getPriceCost() {
    return priceCost;
  }

  public void setPriceCost(Double priceCost) {
    this.priceCost = priceCost;
  }

  public Double getPriceOther() {
    return priceOther;
  }

  public void setPriceOther(Double priceOther) {
    this.priceOther = priceOther;
  }

  public String getRecNo() {
    return recNo;
  }

  public void setRecNo(String recNo) {
    this.recNo = recNo;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getWeekBit() {
    return weekBit;
  }

  public void setWeekBit(String weekBit) {
    this.weekBit = weekBit;
  }

  public Double getPriceVisa() {
    return priceVisa;
  }

  public void setPriceVisa(Double priceVisa) {
    this.priceVisa = priceVisa;
  }

  public Long getOpUser() {
    return opUser;
  }

  public void setOpUser(Long opUser) {
    this.opUser = opUser;
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  public Double getSubScription() {
    return subScription;
  }

  public void setSubScription(Double subScription) {
    this.subScription = subScription;
  }

  public Double getDifference() {
    return difference;
  }

  public void setDifference(Double difference) {
    this.difference = difference;
  }

  public Double getBackMoney() {
    return backMoney;
  }

  public void setBackMoney(Double backMoney) {
    this.backMoney = backMoney;
  }

  public Double getAfieldDuty() {
    return afieldDuty;
  }

  public void setAfieldDuty(Double afieldDuty) {
    this.afieldDuty = afieldDuty;
  }

  public String getReamrks() {
    return this.reamrks;
  }

  public void setReamrks(String reamrks) {
    this.reamrks = reamrks;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public Double getCost1() {
    return cost1;
  }

  public void setCost1(Double cost1) {
    this.cost1 = cost1;
  }

  public Double getCost2() {
    return cost2;
  }

  public void setCost2(Double cost2) {
    this.cost2 = cost2;
  }

  public Double getCost3() {
    return cost3;
  }

  public void setCost3(Double cost3) {
    this.cost3 = cost3;
  }

  public Double getCost4() {
    return cost4;
  }

  public void setCost4(Double cost4) {
    this.cost4 = cost4;
  }

  public Double getCost5() {
    return cost5;
  }

  public void setCost5(Double cost5) {
    this.cost5 = cost5;
  }

  public Double getCost6() {
    return cost6;
  }

  public void setCost6(Double cost6) {
    this.cost6 = cost6;
  }

  public Double getCost7() {
    return cost7;
  }

  public void setCost7(Double cost7) {
    this.cost7 = cost7;
  }

  public Double getCost8() {
    return cost8;
  }

  public void setCost8(Double cost8) {
    this.cost8 = cost8;
  }

  public Double getCost9() {
    return cost9;
  }

  public void setCost9(Double cost9) {
    this.cost9 = cost9;
  }

  public Double getCost10() {
    return cost10;
  }

  public void setCost10(Double cost10) {
    this.cost10 = cost10;
  }

  public Double getCost11() {
    return cost11;
  }

  public void setCost11(Double cost11) {
    this.cost11 = cost11;
  }

  public Double getCost12() {
    return cost12;
  }

  public void setCost12(Double cost12) {
    this.cost12 = cost12;
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public Double getRoe() {
    return roe;
  }

  public void setRoe(Double roe) {
    this.roe = roe;
  }

  public Double getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(Double unitPrice) {
    this.unitPrice = unitPrice;
  }

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
