package com.opentravelsoft.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;

import com.opentravelsoft.entity.xml.LineInputSource;
import com.opentravelsoft.entity.xml.LineXMLReader;
import com.opentravelsoft.util.LabelValueBean;

@Entity
@Table(name = "tbl_line")
public class Line implements java.io.Serializable {
  /** 线路号 */
  private String lineNo;
  private Team team;
  private City outCity;
  private String lineName;
  private Integer day;
  private Employee assigned;
  /** 线路操作组 */
  private Team opTeam;
  /** 市场划分 */
  private Character classKey1;
  private Character classKey2;
  private Character classKey3;
  private String classKey4;
  /** 目的地 */
  private Destination destination;
  private String classKey6;
  private Character classKey7;
  private String firstCity;
  /** 副标题 */
  private String title;
  /** 广告 */
  private String description;

  private boolean isActive;
  /** 状态 */
  private Character delKey;
  private Character newKey;
  private Integer crUser;
  private Date crDate;
  private Integer opUser;
  private Date opDate;
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
  private Byte isHot;
  /** 是否能够延住 */
  private Byte canDefer;
  /** 最少天数 */
  private Integer minDays;

  private Integer minNights;
  /** 航空公司 */
  private String airwaysId;
  private Integer createdBy;
  private Date createdDate;
  private String createdByIp;
  private Integer modifiedBy;
  private Date modifiedDate;
  private String modifiedByIp;
  private Set<Plan> plans = new HashSet<Plan>(0);

  /** 游览景点 */
  private Set<Sight> sights = new HashSet<Sight>(0);

  /** 途径国家 */
  private Set<Country> countrys = new HashSet<Country>(0);

  public Line() {
    this.plan = new ArrayList<Plan>();
    this.delKey = 'N';
    this.isActive = true;
    // *团队旅游 自由行
    this.classKeyContent = "1";
    outCity = new City();
    destination = new Destination();

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

  public Line(String lineNo, Team team, String lineName, boolean isActive,
      String flag) {
    this.lineNo = lineNo;
    this.team = team;
    this.lineName = lineName;
    this.isActive = isActive;
    this.flag = flag;
  }

  @Id
  @Column(name = "LINE_NO", unique = true, nullable = false, length = 8)
  public String getLineNo() {
    return this.lineNo;
  }

  public void setLineNo(String lineNo) {
    this.lineNo = lineNo;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "TEAM_ID", nullable = false)
  public Team getTeam() {
    return this.team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "OUT_CITY")
  public City getOutCity() {
    return this.outCity;
  }

  public void setOutCity(City outCity) {
    this.outCity = outCity;
  }

  @Column(name = "LINE_NAME", nullable = false, length = 60)
  public String getLineName() {
    return this.lineName;
  }

  public void setLineName(String lineName) {
    this.lineName = lineName;
  }

  @Column(name = "DAY")
  public Integer getDay() {
    return this.day;
  }

  public void setDay(Integer day) {
    this.day = day;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ASSIGNED_USER_ID")
  public Employee getAssigned() {
    return assigned;
  }

  public void setAssigned(Employee assigned) {
    this.assigned = assigned;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "OP_TEAM_ID")
  public Team getOpTeam() {
    return opTeam;
  }

  public void setOpTeam(Team opTeam) {
    this.opTeam = opTeam;
  }

  @Column(name = "CLASS_KEY1", length = 1)
  public Character getClassKey1() {
    return this.classKey1;
  }

  public void setClassKey1(Character classKey1) {
    this.classKey1 = classKey1;
  }

  @Column(name = "CLASS_KEY2", length = 1)
  public Character getClassKey2() {
    return this.classKey2;
  }

  public void setClassKey2(Character classKey2) {
    this.classKey2 = classKey2;
  }

  @Column(name = "CLASS_KEY3", length = 1)
  public Character getClassKey3() {
    return this.classKey3;
  }

  public void setClassKey3(Character classKey3) {
    this.classKey3 = classKey3;
  }

  @Column(name = "CLASS_KEY4", length = 2)
  public String getClassKey4() {
    return this.classKey4;
  }

  public void setClassKey4(String classKey4) {
    this.classKey4 = classKey4;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "CLASS_KEY5")
  public Destination getDestination() {
    return destination;
  }

  public void setDestination(Destination destination) {
    this.destination = destination;
  }

  @Column(name = "CLASS_KEY6", length = 2)
  public String getClassKey6() {
    return this.classKey6;
  }

  public void setClassKey6(String classKey6) {
    this.classKey6 = classKey6;
  }

  @Column(name = "CLASS_KEY7", length = 1)
  public Character getClassKey7() {
    return this.classKey7;
  }

  public void setClassKey7(Character classKey7) {
    this.classKey7 = classKey7;
  }

  @Column(name = "FIRST_CITY", length = 4)
  public String getFirstCity() {
    return this.firstCity;
  }

  public void setFirstCity(String firstCity) {
    this.firstCity = firstCity;
  }

  @Column(name = "TITLE", length = 100)
  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Column(name = "DESCRIPTION", length = 200)
  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Column(name = "IS_ACTIVE", nullable = false)
  public boolean isIsActive() {
    return this.isActive;
  }

  public void setIsActive(boolean isActive) {
    this.isActive = isActive;
  }

  @Column(name = "DEL_KEY", length = 1)
  public Character getDelKey() {
    return this.delKey;
  }

  public void setDelKey(Character delKey) {
    this.delKey = delKey;
  }

  @Column(name = "NEW_KEY", length = 1)
  public Character getNewKey() {
    return this.newKey;
  }

  public void setNewKey(Character newKey) {
    this.newKey = newKey;
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
  @Column(name = "OP_DATE", length = 19)
  public Date getOpDate() {
    return this.opDate;
  }

  public void setOpDate(Date opDate) {
    this.opDate = opDate;
  }

  @Column(name = "FIT_TYPE", length = 2)
  public String getFitType() {
    return this.fitType;
  }

  public void setFitType(String fitType) {
    this.fitType = fitType;
  }

  @Column(name = "O_PAX")
  public Integer getOPax() {
    return this.OPax;
  }

  public void setOPax(Integer OPax) {
    this.OPax = OPax;
  }

  @Column(name = "O_NIGHT1")
  public Integer getONight1() {
    return this.ONight1;
  }

  public void setONight1(Integer ONight1) {
    this.ONight1 = ONight1;
  }

  @Column(name = "O_NIGHT2")
  public Integer getONight2() {
    return this.ONight2;
  }

  public void setONight2(Integer ONight2) {
    this.ONight2 = ONight2;
  }

  @Column(name = "COM_OWN_EXPENSE", length = 500)
  public String getComOwnExpense() {
    return this.comOwnExpense;
  }

  public void setComOwnExpense(String comOwnExpense) {
    this.comOwnExpense = comOwnExpense;
  }

  @Column(name = "PER_OWN_EXPENSE", length = 500)
  public String getPerOwnExpense() {
    return this.perOwnExpense;
  }

  public void setPerOwnExpense(String perOwnExpense) {
    this.perOwnExpense = perOwnExpense;
  }

  @Column(name = "TRAIT_TYPE")
  public Integer getTraitType() {
    return this.traitType;
  }

  public void setTraitType(Integer traitType) {
    this.traitType = traitType;
  }

  @Column(name = "PORT_OF_ENTRY", length = 20)
  public String getPortOfEntry() {
    return this.portOfEntry;
  }

  public void setPortOfEntry(String portOfEntry) {
    this.portOfEntry = portOfEntry;
  }

  @Column(name = "PORT_OF_DEPARTURE", length = 20)
  public String getPortOfDeparture() {
    return this.portOfDeparture;
  }

  public void setPortOfDeparture(String portOfDeparture) {
    this.portOfDeparture = portOfDeparture;
  }

  @Column(name = "PERCENTAGE", length = 10)
  public String getPercentage() {
    return this.percentage;
  }

  public void setPercentage(String percentage) {
    this.percentage = percentage;
  }

  @Column(name = "PROFIT", length = 10)
  public String getProfit() {
    return this.profit;
  }

  public void setProfit(String profit) {
    this.profit = profit;
  }

  @Column(name = "FLAG", nullable = false, length = 1)
  public String getFlag() {
    return this.flag;
  }

  public void setFlag(String flag) {
    this.flag = flag;
  }

  @Column(name = "GUEST_PERCENTAGE", length = 10)
  public String getGuestPercentage() {
    return this.guestPercentage;
  }

  public void setGuestPercentage(String guestPercentage) {
    this.guestPercentage = guestPercentage;
  }

  @Column(name = "GUEST_PROFIT", length = 10)
  public String getGuestProfit() {
    return this.guestProfit;
  }

  public void setGuestProfit(String guestProfit) {
    this.guestProfit = guestProfit;
  }

  @Column(name = "IS_HOT")
  public Byte getIsHot() {
    return this.isHot;
  }

  public void setIsHot(Byte isHot) {
    this.isHot = isHot;
  }

  @Column(name = "CAN_DEFER")
  public Byte getCanDefer() {
    return this.canDefer;
  }

  public void setCanDefer(Byte canDefer) {
    this.canDefer = canDefer;
  }

  @Column(name = "MIN_DAYS")
  public Integer getMinDays() {
    return this.minDays;
  }

  public void setMinDays(Integer minDays) {
    this.minDays = minDays;
  }

  @Column(name = "MIN_NIGHTS")
  public Integer getMinNights() {
    return this.minNights;
  }

  public void setMinNights(Integer minNights) {
    this.minNights = minNights;
  }

  @Column(name = "AIRWAYS_ID", length = 20)
  public String getAirwaysId() {
    return this.airwaysId;
  }

  public void setAirwaysId(String airwaysId) {
    this.airwaysId = airwaysId;
  }

  @Column(name = "CreatedBy")
  public Integer getCreatedBy() {
    return this.createdBy;
  }

  public void setCreatedBy(Integer createdBy) {
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

  @Column(name = "CreatedByIP", length = 20)
  public String getCreatedByIp() {
    return this.createdByIp;
  }

  public void setCreatedByIp(String createdByIp) {
    this.createdByIp = createdByIp;
  }

  @Column(name = "ModifiedBy")
  public Integer getModifiedBy() {
    return this.modifiedBy;
  }

  public void setModifiedBy(Integer modifiedBy) {
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

  @Column(name = "ModifiedByIP", length = 20)
  public String getModifiedByIp() {
    return this.modifiedByIp;
  }

  public void setModifiedByIp(String modifiedByIp) {
    this.modifiedByIp = modifiedByIp;
  }

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "line")
  public Set<Plan> getPlans() {
    return this.plans;
  }

  public void setPlans(Set<Plan> plans) {
    this.plans = plans;
  }

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "line")
  public Set<Sight> getSights() {
    return sights;
  }

  public void setSights(Set<Sight> sights) {
    this.sights = sights;
  }

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "line")
  public Set<Country> getCountrys() {
    return countrys;
  }

  public void setCountrys(Set<Country> countrys) {
    this.countrys = countrys;
  }

  // -------------------------------------------------------------------------

  /** 天数 */
  private Integer lineDay;

  /** 产品内容划分的类型 1-团队旅游,2-自助游,3-奖励旅游 */
  private String classKeyContent;

  /** 交通工具划分的类型 按交通工具分类 06表示'大巴游'或'汽车游',在国内时即为国内短线 */
  private String classKeyVehicle;

  /** 出发城市编号 */

  /** 创建人 */
  private Integer createUserId;

  /** 创建时间 */
  private Date createDate;

  /** 最后操作人 */
  private Integer operateUserId;

  /** 最后操作时间 */
  private Date operateDate;
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

  /** 出团计划 */
  private List<Plan> plan;

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

  @Transient
  public Source getSource() {
    return new SAXSource(new LineXMLReader(), new LineInputSource(this));
  }

  @Transient
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

  @Transient
  public List<Plan> getPlan() {
    return plan;
  }

  public void setPlan(List<Plan> plan) {
    this.plan = plan;
  }

  @Transient
  public String getPlanView() {
    return planView;
  }

  public void setPlanView(String planView) {
    this.planView = planView;
  }

  @Transient
  public List<LineSchedule> getSchedule() {
    return schedule;
  }

  public void setSchedule(List<LineSchedule> schedule) {
    this.schedule = schedule;
  }

  @Transient
  public Integer getLineDay() {
    return lineDay;
  }

  public void setLineDay(Integer lineDay) {
    this.lineDay = lineDay;
  }

  @Transient
  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  @Transient
  public Date getOperateDate() {
    return operateDate;
  }

  public void setOperateDate(Date operateDate) {
    this.operateDate = operateDate;
  }

  @Transient
  public Integer getCreateUserId() {
    return createUserId;
  }

  public void setCreateUserId(Integer createUserId) {
    this.createUserId = createUserId;
  }

  @Transient
  public Integer getOperateUserId() {
    return operateUserId;
  }

  public void setOperateUserId(Integer operateUserId) {
    this.operateUserId = operateUserId;
  }

  @Transient
  public String getClassKeyContent() {
    return classKeyContent;
  }

  public void setClassKeyContent(String classKeyContent) {
    this.classKeyContent = classKeyContent;
  }

  @Transient
  public String getClassKeyVehicle() {
    return classKeyVehicle;
  }

  public void setClassKeyVehicle(String classKeyVehicle) {
    this.classKeyVehicle = classKeyVehicle;
  }

  @Transient
  public List<LineDescription> getExpenseIn() {
    return expenseIn;
  }

  public void setExpenseIn(List<LineDescription> expenseIn) {
    this.expenseIn = expenseIn;
  }

  @Transient
  public List<LineDescription> getExpenseOut() {
    return expenseOut;
  }

  public void setExpenseOut(List<LineDescription> expenseOut) {
    this.expenseOut = expenseOut;
  }

  @Transient
  public String getTyOwnExpense() {
    return tyOwnExpense;
  }

  public void setTyOwnExpense(String tyOwnExpense) {
    this.tyOwnExpense = tyOwnExpense;
  }

  @Transient
  public String getZkOwnExpense() {
    return zkOwnExpense;
  }

  public void setZkOwnExpense(String zkOwnExpense) {
    this.zkOwnExpense = zkOwnExpense;
  }

  @Transient
  public List<LineDescription> getFeatures() {
    return features;
  }

  public void setFeatures(List<LineDescription> features) {
    this.features = features;
  }

  @Transient
  public List<LabelValueBean> getExpenseCovered() {
    return expenseCovered;
  }

  public void setExpenseCovered(List<LabelValueBean> expenseCovered) {
    this.expenseCovered = expenseCovered;
  }

  @Transient
  public List<LabelValueBean> getExpenseExcept() {
    return expenseExcept;
  }

  public void setExpenseExcept(List<LabelValueBean> expenseExcept) {
    this.expenseExcept = expenseExcept;
  }

  @Transient
  public List<LabelValueBean> getAlerts() {
    return alerts;
  }

  public void setAlerts(List<LabelValueBean> alerts) {
    this.alerts = alerts;
  }

  @Transient
  public List<LabelValueBean> getRules() {
    return rules;
  }

  public void setRules(List<LabelValueBean> rules) {
    this.rules = rules;
  }

  @Transient
  public List<LineDescription> getRouteTipsList() {
    return tipsList;
  }

  public void setRouteTipsList(List<LineDescription> routeTipsList) {
    this.tipsList = routeTipsList;
  }

  @Transient
  public List<LinePrice> getQuotations() {
    return quotations;
  }

  public void setQuotations(List<LinePrice> quotations) {
    this.quotations = quotations;
  }

  @Transient
  public List<LineVisa> getVisaList() {
    return visaList;
  }

  public void setVisaList(List<LineVisa> visaList) {
    this.visaList = visaList;
  }

  @Transient
  public String getCountryName() {
    return countryName;
  }

  public void setCountryName(String countryName) {
    this.countryName = countryName;
  }

  @Transient
  public String getJourney() {
    return journey;
  }

  public void setJourney(String journey) {
    this.journey = journey;
  }

  @Transient
  public int getPax() {
    return pax;
  }

  public void setPax(int pax) {
    this.pax = pax;
  }

  @Transient
  public double getVisa() {
    return visa;
  }

  public void setVisa(double visa) {
    this.visa = visa;
  }

  @Transient
  public double getRoe() {
    return roe;
  }

  public void setRoe(double roe) {
    this.roe = roe;
  }

  @Transient
  public double getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(double unitPrice) {
    this.unitPrice = unitPrice;
  }

  @Transient
  public double getCost4() {
    return cost4;
  }

  public void setCost4(double cost4) {
    this.cost4 = cost4;
  }

  @Transient
  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  @Transient
  public double getCost1() {
    return cost1;
  }

  public void setCost1(double cost1) {
    this.cost1 = cost1;
  }

  @Transient
  public double getCost3() {
    return cost3;
  }

  public void setCost3(double cost3) {
    this.cost3 = cost3;
  }

  @Transient
  public double getCost6() {
    return cost6;
  }

  public void setCost6(double cost6) {
    this.cost6 = cost6;
  }

  @Transient
  public double getCost7() {
    return cost7;
  }

  public void setCost7(double cost7) {
    this.cost7 = cost7;
  }

  @Transient
  public double getBackMoney() {
    return backMoney;
  }

  public void setBackMoney(double backMoney) {
    this.backMoney = backMoney;
  }

  @Transient
  public double getAfieldDuty() {
    return afieldDuty;
  }

  public void setAfieldDuty(double afieldDuty) {
    this.afieldDuty = afieldDuty;
  }

  @Transient
  public Date getEDate() {
    return EDate;
  }

  public void setEDate(Date date) {
    EDate = date;
  }

  @Transient
  public Date getSDate() {
    return SDate;
  }

  public void setSDate(Date date) {
    SDate = date;
  }

  @Transient
  public String getDestinationNo() {
    return destinationNo;
  }

  public void setDestinationNo(String destinationNo) {
    this.destinationNo = destinationNo;
  }

  @Transient
  public String getRouteTrait() {
    return routeTrait;
  }

  public void setRouteTrait(String routeTrait) {
    this.routeTrait = routeTrait;
  }

  @Transient
  public double getPrice1() {
    return price1;
  }

  public void setPrice1(double price1) {
    this.price1 = price1;
  }

  @Transient
  public double getPrice2() {
    return price2;
  }

  public void setPrice2(double price2) {
    this.price2 = price2;
  }

  @Transient
  public Date getOutDate() {
    return outDate;
  }

  public void setOutDate(Date outDate) {
    this.outDate = outDate;
  }

  @Transient
  public String getOutDateStr() {
    return outDateStr;
  }

  public void setOutDateStr(String outDateStr) {
    this.outDateStr = outDateStr;
  }

  @Transient
  public String getPrice1Str() {
    return price1Str;
  }

  public void setPrice1Str(String price1Str) {
    this.price1Str = price1Str;
  }

  @Transient
  public String getPrice2Str() {
    return price2Str;
  }

  public void setPrice2Str(String price2Str) {
    this.price2Str = price2Str;
  }

  @Transient
  public String getDistrictNo() {
    return districtNo;
  }

  public void setDistrictNo(String districtNo) {
    this.districtNo = districtNo;
  }

  @Transient
  public String getDistrictName() {
    return districtName;
  }

  public void setDistrictName(String districtName) {
    this.districtName = districtName;
  }

  @Transient
  public String getDistrictCountry() {
    return districtCountry;
  }

  public void setDistrictCountry(String districtCountry) {
    this.districtCountry = districtCountry;
  }

  @Transient
  public String getDistrictProvince() {
    return districtProvince;
  }

  public void setDistrictProvince(String districtProvince) {
    this.districtProvince = districtProvince;
  }

  @Transient
  public String getPlanPax() {
    return planPax;
  }

  public void setPlanPax(String planPax) {
    this.planPax = planPax;
  }

  @Transient
  public String getIsPrefer() {
    return isPrefer;
  }

  public void setIsPrefer(String isPrefer) {
    this.isPrefer = isPrefer;
  }

  @Transient
  public String getDistrictCountryName() {
    return districtCountryName;
  }

  public void setDistrictCountryName(String districtCountryName) {
    this.districtCountryName = districtCountryName;
  }

  @Transient
  public String getDistrictProvinceName() {
    return districtProvinceName;
  }

  public void setDistrictProvinceName(String districtProvinceName) {
    this.districtProvinceName = districtProvinceName;
  }

  @Transient
  public String getSightNo() {
    return sightNo;
  }

  public void setSightNo(String sightNo) {
    this.sightNo = sightNo;
  }

  @Transient
  public String getSightName() {
    return sightName;
  }

  public void setSightName(String sightName) {
    this.sightName = sightName;
  }

  @Transient
  public String getOutDate_price1() {
    return outDate_price1;
  }

  public void setOutDate_price1(String outDate_price1) {
    this.outDate_price1 = outDate_price1;
  }

  @Transient
  public String getOutDate_price2() {
    return outDate_price2;
  }

  public void setOutDate_price2(String outDate_price2) {
    this.outDate_price2 = outDate_price2;
  }

  @Transient
  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  @Transient
  public String getClassifyRegion() {
    return classifyRegion;
  }

  public void setClassifyRegion(String classifyRegion) {
    this.classifyRegion = classifyRegion;
  }

  @Transient
  public String getRegionId() {
    return regionId;
  }

  public void setRegionId(String regionId) {
    this.regionId = regionId;
  }

  @Transient
  public String getClassifyRegionId() {
    return classifyRegionId;
  }

  public void setClassifyRegionId(String classifyRegionId) {
    this.classifyRegionId = classifyRegionId;
  }

}
