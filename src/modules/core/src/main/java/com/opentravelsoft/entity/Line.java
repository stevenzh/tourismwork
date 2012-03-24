package com.opentravelsoft.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;

import com.opentravelsoft.entity.product.ProductBase;
import com.opentravelsoft.entity.xml.LineInputSource;
import com.opentravelsoft.entity.xml.LineXMLReader;
import com.opentravelsoft.util.LabelValueBean;

/**
 * 旅游线路
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:32 $
 */
public class Line extends ProductBase implements FopTeam, java.io.Serializable {
  private static final long serialVersionUID = 759680527140271551L;

  /** 线路号 */
  private String lineNo;

  /** 线路操作组 */
  private Team opTeam;

  /** 天数 */
  private Integer lineDay;

  /** 产品内容划分的类型 1-团队旅游,2-自助游,3-奖励旅游 */
  private String classKeyContent;

  private String classKey3;

  private String classKey4;

  /** 目的地 */
  private Destination destination;

  /** 交通工具划分的类型 按交通工具分类 06表示'大巴游'或'汽车游',在国内时即为国内短线 */
  private String classKeyVehicle;

  private String classKey7;

  /** 出发城市编号 */
  private City outCity;

  private String firstCity;

  /** 副标题 */
  private String title;

  /** 广告 */
  private String description;

  /** 状态 */
  private boolean isActive;

  private String delKey;

  /** 创建人 */
  private Long createUserId;

  /** 创建时间 */
  private Date createDate;

  /** 最后操作人 */
  private Long operateUserId;

  /** 最后操作时间 */
  private Date operateDate;

  private String fitType;

  private Integer OPax;

  private Integer ONight1;

  private Integer ONight2;

  /** 同行不包含自费项目 */
  private String comOwnExpense;

  /** 直客包含自费项目 */
  private String perOwnExpense;

  private Integer traitType;

  /** 入境口岸 */
  private String portOfEntry;

  /** 出境口岸 */
  private String portOfDeparture;
  // --------------------------------------------------------------------------
  // 度假产品补充
  /** 市场划分 */
  private Character classKey1;
  /** 同业百分比 */
  private String percentage;
  /** 同业利润额 */
  private String profit;
  /** 区分同业百分比和利润额 */
  private String flag;
  /** 直客百分比 */
  private String guestPercentage;
  /** 直客利润额 */
  private String guestProfit;
  /** 热卖 */
  private Short isHot;
  /** 是否能够延住 */
  private Short canDefer;
  /** 最少天数 */
  private Integer minDays;
  /** */
  private Integer minNights;
  /** 航空公司 */
  private String airwaysId;

  private Integer createdBy;

  private Date createdDate;

  private String createdByIp;

  private Integer modifiedBy;

  private Date modifiedDate;

  private String modifiedByIp;

  // -------------------------------------------------------------------------

  /** 目的地国家名称 */
  private String countryName;

  /** 建议价格 */
  private Double price;

  /** 用于主页显示线路和价格 */
  private String planView;

  /** 签证费用 */
  private double visa;

  /** 汇率 */
  private double roe;

  /** 币种 */
  private String currency;

  /** 单价(外币) */
  private double unitPrice;

  /** 机票费 */
  private double cost1;

  /** 机票税 */
  private double cost3;

  /** 地接费 */
  private double cost4;

  /** 名单费 */
  private double cost6;

  /** 领队费 */
  private double cost7;

  /** 价格截止日期 */
  private Date EDate;

  /** 价格开始日期 */
  private Date SDate;

  /** 领队返佣 */
  private double backMoney;

  /** 离境税 */
  private double afieldDuty;

  /** 同业自费 */
  private String tyOwnExpense;

  /** 直客自费 */
  private String zkOwnExpense;

  private int pax;

  private String journey;

  // -------------------------------------------------------------------------

  /** 线路特色 */
  private List<LineDescription> features;

  /** 行程 */
  private List<LineSchedule> schedule;

  /** 报价包含 */
  private List<LineDescription> expenseIn;

  /** 报价不包含 */
  private List<LineDescription> expenseOut;

  /** 小贴士 */
  private List<LineDescription> tipsList;

  /** 费用包含 */
  private List<LabelValueBean> expenseCovered;

  /** 费用不包含 */
  private List<LabelValueBean> expenseExcept;

  /** 出行警示 */
  private List<LabelValueBean> alerts;

  /** 重要条款 */
  private List<LabelValueBean> rules;

  private List<LinePrice> quotations;

  /** 所需签证 */
  private List<LineVisa> visaList;

  /** 游览景点 */
  private Set<Sight> sights = new HashSet<Sight>(0);

  /** 途径国家 */
  private Set<Country> countrys = new HashSet<Country>(0);

  /** 出团计划 */
  private List<Plan> plan;

  // ------------------------------------------------------------------
  // 以下线路索引使用
  /** 目的地 */
  private String destinationNo;

  /** 线路特色 */
  private String routeTrait;

  /** 最近出发日期 */
  private Date outDate;

  private String outDateStr;

  /** 直客价 */
  private double price1;

  private String price1Str;

  /** 同行价 */
  private double price2;

  private String price2Str;

  /** 目的地 */
  private String districtNo;

  /** 目的地名 */
  private String districtName;

  /** 目的地所属国家 */
  private String districtCountry;

  private String districtCountryName;

  /** 目的地所属省 */
  private String districtProvince;

  private String districtProvinceName;

  private String sightNo;

  private String sightName;

  /** 出团计划的个数 */
  private String planPax;

  /** 是否有特惠团 */
  private String isPrefer;

  /** 出发日期和参考价格 */
  private String outDate_price1;

  /** 出发日期和同行价格 */
  private String outDate_price2;

  /** 目的地(用于分类搜索) */
  private String region;

  private String regionId;

  /** 止的地所在区域(用于分类搜索) */
  private String classifyRegion;

  private String classifyRegionId;

  public Line() {
    this.plan = new ArrayList<Plan>();
    this.delKey = "N";
    this.isActive = true;
    // *团队旅游 自由行
    this.classKeyContent = "1";
    outCity = new City();
    destination = new Destination();

    // -------------------------------------------
    // 线路索引
    isPrefer = "N";
    price1Str = "00000000";
    price2Str = "00000000";
    outDateStr = "20900101";
    planPax = "0";
    districtProvinceName = "";
    sightNo = "";
    sightName = "";
    region = "";
    regionId = "";
    classifyRegion = "";
    classifyRegionId = "";
    outDate_price1 = "";
    outDate_price1 = "";
    outDate_price2 = "";
  }

  public Source getSource() {
    return new SAXSource(new LineXMLReader(), new LineInputSource(this));
  }

  public String getLineNo() {
    return this.lineNo;
  }

  public void setLineNo(String lineNo) {
    this.lineNo = lineNo;
  }

  public String getLineName() {
    return getName();
  }

  public void setLineName(String name) {
    setName(name);
  }

  public String getClassKey3() {
    return this.classKey3;
  }

  public void setClassKey3(String classKey3) {
    this.classKey3 = classKey3;
  }

  public String getClassKey4() {
    return this.classKey4;
  }

  public void setClassKey4(String classKey4) {
    this.classKey4 = classKey4;
  }

  public String getClassKey7() {
    return this.classKey7;
  }

  public void setClassKey7(String classKey7) {
    this.classKey7 = classKey7;
  }

  public City getOutCity() {
    return this.outCity;
  }

  public void setOutCity(City outCity) {
    this.outCity = outCity;
  }

  public String getFirstCity() {
    return this.firstCity;
  }

  public void setFirstCity(String firstCity) {
    this.firstCity = firstCity;
  }

  public String getDelKey() {
    return this.delKey;
  }

  public void setDelKey(String delKey) {
    this.delKey = delKey;
  }

  public String getFitType() {
    return this.fitType;
  }

  public void setFitType(String fitType) {
    this.fitType = fitType;
  }

  public Integer getOPax() {
    return this.OPax;
  }

  public void setOPax(Integer OPax) {
    this.OPax = OPax;
  }

  public Integer getONight1() {
    return this.ONight1;
  }

  public void setONight1(Integer ONight1) {
    this.ONight1 = ONight1;
  }

  public Integer getONight2() {
    return this.ONight2;
  }

  public void setONight2(Integer ONight2) {
    this.ONight2 = ONight2;
  }

  public String getComOwnExpense() {
    return this.comOwnExpense;
  }

  public void setComOwnExpense(String comOwnExpense) {
    this.comOwnExpense = comOwnExpense;
  }

  public String getPerOwnExpense() {
    return this.perOwnExpense;
  }

  public void setPerOwnExpense(String perOwnExpense) {
    this.perOwnExpense = perOwnExpense;
  }

  public Integer getTraitType() {
    return traitType;
  }

  public void setTraitType(Integer traitType) {
    this.traitType = traitType;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public void AddPlan(Plan _plan) {
    plan.add(_plan);
  }

  public void AddPlan(Date outDate, double price) {
    plan.add(new Plan(getLineNo(), outDate, price));
  }

  public void clearPlan() {
    plan = new ArrayList<Plan>();
  }

  public List<Plan> getPlan() {
    return plan;
  }

  public void setPlan(List<Plan> plan) {
    this.plan = plan;
  }

  public String getPlanView() {
    return planView;
  }

  public void setPlanView(String planView) {
    this.planView = planView;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<LineSchedule> getSchedule() {
    return schedule;
  }

  public void setSchedule(List<LineSchedule> schedule) {
    this.schedule = schedule;
  }

  public Integer getLineDay() {
    return lineDay;
  }

  public void setLineDay(Integer lineDay) {
    this.lineDay = lineDay;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public Date getOperateDate() {
    return operateDate;
  }

  public void setOperateDate(Date operateDate) {
    this.operateDate = operateDate;
  }

  public Long getCreateUserId() {
    return createUserId;
  }

  public void setCreateUserId(Long createUserId) {
    this.createUserId = createUserId;
  }

  public Long getOperateUserId() {
    return operateUserId;
  }

  public void setOperateUserId(Long operateUserId) {
    this.operateUserId = operateUserId;
  }

  public String getClassKeyContent() {
    return classKeyContent;
  }

  public void setClassKeyContent(String classKeyContent) {
    this.classKeyContent = classKeyContent;
  }

  public boolean getIsActive() {
    return isActive;
  }

  public void setIsActive(boolean active) {
    this.isActive = active;
  }

  public String getClassKeyVehicle() {
    return classKeyVehicle;
  }

  public void setClassKeyVehicle(String classKeyVehicle) {
    this.classKeyVehicle = classKeyVehicle;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<LineDescription> getExpenseIn() {
    return expenseIn;
  }

  public void setExpenseIn(List<LineDescription> expenseIn) {
    this.expenseIn = expenseIn;
  }

  public List<LineDescription> getExpenseOut() {
    return expenseOut;
  }

  public void setExpenseOut(List<LineDescription> expenseOut) {
    this.expenseOut = expenseOut;
  }

  public String getTyOwnExpense() {
    return tyOwnExpense;
  }

  public void setTyOwnExpense(String tyOwnExpense) {
    this.tyOwnExpense = tyOwnExpense;
  }

  public String getZkOwnExpense() {
    return zkOwnExpense;
  }

  public void setZkOwnExpense(String zkOwnExpense) {
    this.zkOwnExpense = zkOwnExpense;
  }

  public List<LineDescription> getFeatures() {
    return features;
  }

  public void setFeatures(List<LineDescription> features) {
    this.features = features;
  }

  public List<LabelValueBean> getExpenseCovered() {
    return expenseCovered;
  }

  public void setExpenseCovered(List<LabelValueBean> expenseCovered) {
    this.expenseCovered = expenseCovered;
  }

  public List<LabelValueBean> getExpenseExcept() {
    return expenseExcept;
  }

  public void setExpenseExcept(List<LabelValueBean> expenseExcept) {
    this.expenseExcept = expenseExcept;
  }

  public List<LabelValueBean> getAlerts() {
    return alerts;
  }

  public void setAlerts(List<LabelValueBean> alerts) {
    this.alerts = alerts;
  }

  public List<LabelValueBean> getRules() {
    return rules;
  }

  public void setRules(List<LabelValueBean> rules) {
    this.rules = rules;
  }

  public List<LineDescription> getRouteTipsList() {
    return tipsList;
  }

  public void setRouteTipsList(List<LineDescription> routeTipsList) {
    this.tipsList = routeTipsList;
  }

  public List<LinePrice> getQuotations() {
    return quotations;
  }

  public void setQuotations(List<LinePrice> quotations) {
    this.quotations = quotations;
  }

  public List<LineVisa> getVisaList() {
    return visaList;
  }

  public void setVisaList(List<LineVisa> visaList) {
    this.visaList = visaList;
  }

  public String getCountryName() {
    return countryName;
  }

  public void setCountryName(String countryName) {
    this.countryName = countryName;
  }

  public String getJourney() {
    return journey;
  }

  public void setJourney(String journey) {
    this.journey = journey;
  }

  public int getPax() {
    return pax;
  }

  public void setPax(int pax) {
    this.pax = pax;
  }

  public double getVisa() {
    return visa;
  }

  public void setVisa(double visa) {
    this.visa = visa;
  }

  public double getRoe() {
    return roe;
  }

  public void setRoe(double roe) {
    this.roe = roe;
  }

  public double getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(double unitPrice) {
    this.unitPrice = unitPrice;
  }

  public double getCost4() {
    return cost4;
  }

  public void setCost4(double cost4) {
    this.cost4 = cost4;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public double getCost1() {
    return cost1;
  }

  public void setCost1(double cost1) {
    this.cost1 = cost1;
  }

  public double getCost3() {
    return cost3;
  }

  public void setCost3(double cost3) {
    this.cost3 = cost3;
  }

  public double getCost6() {
    return cost6;
  }

  public void setCost6(double cost6) {
    this.cost6 = cost6;
  }

  public double getCost7() {
    return cost7;
  }

  public void setCost7(double cost7) {
    this.cost7 = cost7;
  }

  public double getBackMoney() {
    return backMoney;
  }

  public void setBackMoney(double backMoney) {
    this.backMoney = backMoney;
  }

  public double getAfieldDuty() {
    return afieldDuty;
  }

  public void setAfieldDuty(double afieldDuty) {
    this.afieldDuty = afieldDuty;
  }

  public Date getEDate() {
    return EDate;
  }

  public void setEDate(Date date) {
    EDate = date;
  }

  public String getPortOfEntry() {
    return portOfEntry;
  }

  public void setPortOfEntry(String portOfEntry) {
    this.portOfEntry = portOfEntry;
  }

  public String getPortOfDeparture() {
    return portOfDeparture;
  }

  public void setPortOfDeparture(String portOfDeparture) {
    this.portOfDeparture = portOfDeparture;
  }

  public Date getSDate() {
    return SDate;
  }

  public void setSDate(Date date) {
    SDate = date;
  }

  public Set<Sight> getSights() {
    return sights;
  }

  public void setSights(Set<Sight> sights) {
    this.sights = sights;
  }

  public Set<Country> getCountrys() {
    return countrys;
  }

  public void setCountrys(Set<Country> countrys) {
    this.countrys = countrys;
  }

  public Team getOpTeam() {
    return opTeam;
  }

  public void setOpTeam(Team opTeam) {
    this.opTeam = opTeam;
  }

  public Destination getDestination() {
    return destination;
  }

  public void setDestination(Destination destination) {
    this.destination = destination;
  }

  public String getDestinationNo() {
    return destinationNo;
  }

  public void setDestinationNo(String destinationNo) {
    this.destinationNo = destinationNo;
  }

  public String getRouteTrait() {
    return routeTrait;
  }

  public void setRouteTrait(String routeTrait) {
    this.routeTrait = routeTrait;
  }

  public double getPrice1() {
    return price1;
  }

  public void setPrice1(double price1) {
    this.price1 = price1;
  }

  public double getPrice2() {
    return price2;
  }

  public void setPrice2(double price2) {
    this.price2 = price2;
  }

  public Date getOutDate() {
    return outDate;
  }

  public void setOutDate(Date outDate) {
    this.outDate = outDate;
  }

  public String getOutDateStr() {
    return outDateStr;
  }

  public void setOutDateStr(String outDateStr) {
    this.outDateStr = outDateStr;
  }

  public String getPrice1Str() {
    return price1Str;
  }

  public void setPrice1Str(String price1Str) {
    this.price1Str = price1Str;
  }

  public String getPrice2Str() {
    return price2Str;
  }

  public void setPrice2Str(String price2Str) {
    this.price2Str = price2Str;
  }

  public String getDistrictNo() {
    return districtNo;
  }

  public void setDistrictNo(String districtNo) {
    this.districtNo = districtNo;
  }

  public String getDistrictName() {
    return districtName;
  }

  public void setDistrictName(String districtName) {
    this.districtName = districtName;
  }

  public String getDistrictCountry() {
    return districtCountry;
  }

  public void setDistrictCountry(String districtCountry) {
    this.districtCountry = districtCountry;
  }

  public String getDistrictProvince() {
    return districtProvince;
  }

  public void setDistrictProvince(String districtProvince) {
    this.districtProvince = districtProvince;
  }

  public String getPlanPax() {
    return planPax;
  }

  public void setPlanPax(String planPax) {
    this.planPax = planPax;
  }

  public String getIsPrefer() {
    return isPrefer;
  }

  public void setIsPrefer(String isPrefer) {
    this.isPrefer = isPrefer;
  }

  public String getDistrictCountryName() {
    return districtCountryName;
  }

  public void setDistrictCountryName(String districtCountryName) {
    this.districtCountryName = districtCountryName;
  }

  public String getDistrictProvinceName() {
    return districtProvinceName;
  }

  public void setDistrictProvinceName(String districtProvinceName) {
    this.districtProvinceName = districtProvinceName;
  }

  public String getSightNo() {
    return sightNo;
  }

  public void setSightNo(String sightNo) {
    this.sightNo = sightNo;
  }

  public String getSightName() {
    return sightName;
  }

  public void setSightName(String sightName) {
    this.sightName = sightName;
  }

  public String getOutDate_price1() {
    return outDate_price1;
  }

  public void setOutDate_price1(String outDate_price1) {
    this.outDate_price1 = outDate_price1;
  }

  public String getOutDate_price2() {
    return outDate_price2;
  }

  public void setOutDate_price2(String outDate_price2) {
    this.outDate_price2 = outDate_price2;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public String getClassifyRegion() {
    return classifyRegion;
  }

  public void setClassifyRegion(String classifyRegion) {
    this.classifyRegion = classifyRegion;
  }

  public String getRegionId() {
    return regionId;
  }

  public void setRegionId(String regionId) {
    this.regionId = regionId;
  }

  public String getClassifyRegionId() {
    return classifyRegionId;
  }

  public void setClassifyRegionId(String classifyRegionId) {
    this.classifyRegionId = classifyRegionId;
  }

  public Character getClassKey1() {
    return classKey1;
  }

  public void setClassKey1(Character classKey1) {
    this.classKey1 = classKey1;
  }

  public String getPercentage() {
    return percentage;
  }

  public void setPercentage(String percentage) {
    this.percentage = percentage;
  }

  public String getProfit() {
    return profit;
  }

  public void setProfit(String profit) {
    this.profit = profit;
  }

  public String getFlag() {
    return flag;
  }

  public void setFlag(String flag) {
    this.flag = flag;
  }

  public String getGuestPercentage() {
    return guestPercentage;
  }

  public void setGuestPercentage(String guestPercentage) {
    this.guestPercentage = guestPercentage;
  }

  public String getGuestProfit() {
    return guestProfit;
  }

  public void setGuestProfit(String guestProfit) {
    this.guestProfit = guestProfit;
  }

  public Short getIsHot() {
    return isHot;
  }

  public void setIsHot(Short isHot) {
    this.isHot = isHot;
  }

  public Short getCanDefer() {
    return canDefer;
  }

  public void setCanDefer(Short canDefer) {
    this.canDefer = canDefer;
  }

  public Integer getMinDays() {
    return minDays;
  }

  public void setMinDays(Integer minDays) {
    this.minDays = minDays;
  }

  public Integer getMinNights() {
    return minNights;
  }

  public void setMinNights(Integer minNights) {
    this.minNights = minNights;
  }

  public String getAirwaysId() {
    return airwaysId;
  }

  public void setAirwaysId(String airwaysId) {
    this.airwaysId = airwaysId;
  }

  public Integer getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Integer createdBy) {
    this.createdBy = createdBy;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public String getCreatedByIp() {
    return createdByIp;
  }

  public void setCreatedByIp(String createdByIp) {
    this.createdByIp = createdByIp;
  }

  public Integer getModifiedBy() {
    return modifiedBy;
  }

  public void setModifiedBy(Integer modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  public Date getModifiedDate() {
    return modifiedDate;
  }

  public void setModifiedDate(Date modifiedDate) {
    this.modifiedDate = modifiedDate;
  }

  public String getModifiedByIp() {
    return modifiedByIp;
  }

  public void setModifiedByIp(String modifiedByIp) {
    this.modifiedByIp = modifiedByIp;
  }
}
