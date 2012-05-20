package com.opentravelsoft.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;

import com.opentravelsoft.entity.xml.TourInputSource;
import com.opentravelsoft.entity.xml.TourXMLReader;

@Entity
@Table(name = "tbl_plan")
public class Plan implements java.io.Serializable, Cloneable {
  /** 计划号 */
  private String planNo;
  /** 操作负责组 */
  private Team team;
  /** 线路 */
  private Line line;

  private Employee crUser;

  private City city;
  /** 出发日期 */
  private Date outDate;
  /** 团号 */
  private String tourNo;
  /** 团名 */
  private String tourNm;
  /** 操作负责人 */
  private Employee assigned;
  /** 预订是否控制名额 */
  private String paxkey;
  private Integer planPax;
  private Integer holdPax;
  private Integer buildMinPax;
  /**
   * 成团状态
   * 
   * D-取消 Y-延期 T-已成团 N-未成团 H-换团 W-退团
   */
  private char uniteStatus;
  /** 已订名额 */
  private Integer pax2;
  /** 可用名额 */
  private Integer pax3;
  /** 团队人数 */
  private int pax;
  /** 男性人数 */
  private Integer malePax;
  /** 女性人数 */
  private Integer femalePax;
  /** 小孩数 */
  private Integer childPax;
  private Integer leadPax;
  private String workflowId;
  /** 开班说明 */
  private String recRmk;
  private Date plandate1;
  /** 送签日期 */
  private Date plandate2;
  /** 有护照清仓天数 */
  private Integer planDate3;
  /** 无护照清仓天数 */
  private Integer planDate4;
  /** 出照天数 */
  private Date passDate;
  private String otherKey1;
  private String messagebox;
  /** 特惠出团计划 Y-特惠 N-普通 */
  private Character favourable;
  /** 是否共享航班 */
  private Character shareFlight;
  /** 共享航班ID */
  private Integer shareFlightId;
  /** 价格 */
  private LinePrice packagePrice;
  /** 整团或者散客 */
  private Byte singleFlag;
  /** 产品推广方式 */
  private Integer traitType;
  /** 已有订单 */
  private Integer isEnter;
  /** 入境日期 */
  private Date inDate;
  /** 入境口岸 */
  private String inCity;
  private String gpCity;

  private Integer dbrm;
  /**
   * 双人间 * private Integer dbrm; /** 单人间
   */
  private Integer sgrm;
  /** 加床数 */
  private Integer adrm;
  /** 天数 */
  private Integer day;
  /** 人天数 */
  private Integer paxday;
  private BigDecimal income;
  /** 成本 */
  private BigDecimal cost;
  /** 毛利率 */
  private BigDecimal blnrate;
  /** 未交款 */
  private BigDecimal camt03;
  /** 航空公司代码 */
  private String airco;
  /** 已收团款 */
  private BigDecimal aramt;
  /** 团类型 */
  private String tourKey;
  /** 建团人 */
  private Integer opuser1;
  /** 建团时间 */
  private Date optime1;
  /** 修改人 */
  private Integer opuser2;
  /** 修改日期 */
  private Date optime2;
  /** 所经国家或地区 */
  private String country;
  /** 保险单号 */
  private String piccNo;
  /** 打印购汇清单次数 */
  private Integer ghdNum;
  /** 最后打印购汇单日期 */
  private Date ghdDate;
  /** 领队列表 */
  private String tourRmk;

  private Character tourDel;
  /** 备注 */
  private String remarks;
  /** 催款单是否全部打印 */
  private Character invpntKey;
  private String visaNcode;
  private String visaNname;
  /** 是否需要安排领队 */
  private Integer isNeedLeader;
  private BigDecimal dbamt;
  private BigDecimal cramt;
  private Character opKey;
  private Date crDate;
  /** 可开票标记 */
  private Character chkKey;
  /** 已开票登记 */
  private String chkIssue;
  /** 开票人 */
  private Integer chkUser;
  /** 开票时间 */
  private Date chkDate;
  /** 可打印催款单标记 */
  private Character invKey;
  /** 确认催款标记人 */
  private Integer invUser;
  /** 确认标记时间 */
  private Date invDate;
  private Integer opuser;
  private Date optime;
  private Integer customerId;
  /** 减免人数 */
  private Integer exemptPax;
  /** 收入 */
  private BigDecimal amount;
  private BigDecimal costAmount;
  /** 单团核算标记 - 授权修改 (Y-授权修改) */
  private char opAccount;
  /** 单团核算标记 - OP是否提交财务(Y-提交财务) */
  private char opRefactor;
  /** 单团核算标记 - 财务审核否 (Y-财务已审核) */
  private Character frChecked;
  /** 财务审核人 */
  private Integer frUser;
  /** 财务审核时间 */
  private Date frDate;

  private BigDecimal extCost;

  private String extCostNote;
  /** 纯团费 */
  private BigDecimal tourAmount;

  public Plan() {
    line = new Line();
    team = new Team();
    assigned = new Employee();
    tourNm = "";
    pax = 0;
    pax4 = 0;
    remarks = "";
    deployFlag = "Y";
    favourable = 'N';
    enter = false;
    isEnter = 0;
    shareFlight = 'N';
    paxkey = "Y";
    singleFlag = 1;
    isBuildup = "N";
    delKey = "N";
    uniteStatus = 'N';
    opAccount = 'N';
    opRefactor = 'N';
    packagePrice = new LinePrice();
  }

  public Plan(String string, Date outDate2, double price) {
  }

  public Plan(String planNo, Line line, Date outDate, char uniteStatus,
      int pax, Date plandate1, Date optime, BigDecimal costAmount,
      char opAccount, char opRefactor) {
    this.planNo = planNo;
    this.line = line;
    this.outDate = outDate;
    this.uniteStatus = uniteStatus;
    this.pax = pax;
    this.plandate1 = plandate1;
    this.optime = optime;
    this.costAmount = costAmount;
    this.opAccount = opAccount;
    this.opRefactor = opRefactor;
  }

  @Id
  @Column(name = "PLAN_NO", unique = true, nullable = false, length = 10)
  public String getPlanNo() {
    return this.planNo;
  }

  public void setPlanNo(String planNo) {
    this.planNo = planNo;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "TEAM_ID")
  public Team getTeam() {
    return this.team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "LINE_NO", nullable = false)
  public Line getLine() {
    return this.line;
  }

  public void setLine(Line line) {
    this.line = line;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "CR_USER")
  public Employee getCrUser() {
    return this.crUser;
  }

  public void setCrUser(Employee crUser) {
    this.crUser = crUser;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "OUT_CITY")
  public City getCity() {
    return this.city;
  }

  public void setCity(City city) {
    this.city = city;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "OUT_DATE", nullable = false, length = 19)
  public Date getOutDate() {
    return this.outDate;
  }

  public void setOutDate(Date outDate) {
    this.outDate = outDate;
  }

  @Column(name = "TOUR_NO", length = 100)
  public String getTourNo() {
    return this.tourNo;
  }

  public void setTourNo(String tourNo) {
    this.tourNo = tourNo;
  }

  @Column(name = "TOUR_NM", length = 100)
  public String getTourNm() {
    return this.tourNm;
  }

  public void setTourNm(String tourNm) {
    this.tourNm = tourNm;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ASSIGNED_USER_ID")
  public Employee getAssigned() {
    return assigned;
  }

  public void setAssigned(Employee assigned) {
    this.assigned = assigned;
  }

  @Column(name = "PAXKEY", length = 1)
  public String getPaxkey() {
    return this.paxkey;
  }

  public void setPaxkey(String paxkey) {
    this.paxkey = paxkey;
  }

  @Column(name = "PLAN_PAX")
  public Integer getPlanPax() {
    return this.planPax;
  }

  public void setPlanPax(Integer planPax) {
    this.planPax = planPax;
  }

  @Column(name = "HOLD_PAX")
  public Integer getHoldPax() {
    return this.holdPax;
  }

  public void setHoldPax(Integer holdPax) {
    this.holdPax = holdPax;
  }

  @Column(name = "BUILD_MIN_PAX")
  public Integer getBuildMinPax() {
    return this.buildMinPax;
  }

  public void setBuildMinPax(Integer buildMinPax) {
    this.buildMinPax = buildMinPax;
  }

  @Column(name = "UNITE_STATUS", nullable = false, length = 1)
  public char getUniteStatus() {
    return this.uniteStatus;
  }

  public void setUniteStatus(char uniteStatus) {
    this.uniteStatus = uniteStatus;
  }

  @Column(name = "PAX2")
  public Integer getPax2() {
    return this.pax2;
  }

  public void setPax2(Integer pax2) {
    this.pax2 = pax2;
  }

  @Column(name = "PAX3")
  public Integer getPax3() {
    return this.pax3;
  }

  public void setPax3(Integer pax3) {
    this.pax3 = pax3;
  }

  @Column(name = "PAX", nullable = false)
  public int getPax() {
    return this.pax;
  }

  public void setPax(int pax) {
    this.pax = pax;
  }

  @Column(name = "MALE_PAX")
  public Integer getMalePax() {
    return this.malePax;
  }

  public void setMalePax(Integer malePax) {
    this.malePax = malePax;
  }

  @Column(name = "FEMALE_PAX")
  public Integer getFemalePax() {
    return this.femalePax;
  }

  public void setFemalePax(Integer femalePax) {
    this.femalePax = femalePax;
  }

  @Column(name = "CHILD_PAX")
  public Integer getChildPax() {
    return this.childPax;
  }

  public void setChildPax(Integer childPax) {
    this.childPax = childPax;
  }

  @Column(name = "LEAD_PAX")
  public Integer getLeadPax() {
    return this.leadPax;
  }

  public void setLeadPax(Integer leadPax) {
    this.leadPax = leadPax;
  }

  @Column(name = "WORKFLOW_ID")
  public String getWorkflowId() {
    return this.workflowId;
  }

  public void setWorkflowId(String workflowId) {
    this.workflowId = workflowId;
  }

  @Column(name = "REC_RMK", length = 60)
  public String getRecRmk() {
    return this.recRmk;
  }

  public void setRecRmk(String recRmk) {
    this.recRmk = recRmk;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "PLANDATE1", nullable = false, length = 19)
  public Date getPlandate1() {
    return this.plandate1;
  }

  public void setPlandate1(Date plandate1) {
    this.plandate1 = plandate1;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "PLANDATE2", length = 19)
  public Date getPlandate2() {
    return this.plandate2;
  }

  public void setPlandate2(Date plandate2) {
    this.plandate2 = plandate2;
  }

  @Column(name = "PLAN_DATE3")
  public Integer getPlanDate3() {
    return this.planDate3;
  }

  public void setPlanDate3(Integer planDate3) {
    this.planDate3 = planDate3;
  }

  @Column(name = "PLAN_DATE4")
  public Integer getPlanDate4() {
    return this.planDate4;
  }

  public void setPlanDate4(Integer planDate4) {
    this.planDate4 = planDate4;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "PASS_DATE", length = 19)
  public Date getPassDate() {
    return this.passDate;
  }

  public void setPassDate(Date passDate) {
    this.passDate = passDate;
  }

  @Column(name = "OTHER_KEY1", length = 1)
  public String getOtherKey1() {
    return this.otherKey1;
  }

  public void setOtherKey1(String otherKey1) {
    this.otherKey1 = otherKey1;
  }

  @Column(name = "MESSAGEBOX", length = 500)
  public String getMessagebox() {
    return this.messagebox;
  }

  public void setMessagebox(String messagebox) {
    this.messagebox = messagebox;
  }

  @Column(name = "FAVOURABLE", length = 1)
  public Character getFavourable() {
    return this.favourable;
  }

  public void setFavourable(Character favourable) {
    this.favourable = favourable;
  }

  @Column(name = "SHARE_FLIGHT", length = 1)
  public Character getShareFlight() {
    return this.shareFlight;
  }

  public void setShareFlight(Character shareFlight) {
    this.shareFlight = shareFlight;
  }

  @Column(name = "SHARE_FLIGHT_ID")
  public Integer getShareFlightId() {
    return this.shareFlightId;
  }

  public void setShareFlightId(Integer shareFlightId) {
    this.shareFlightId = shareFlightId;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "PRICE_NO")
  public LinePrice getPackagePrice() {
    return packagePrice;
  }

  public void setPackagePrice(LinePrice packagePrice) {
    this.packagePrice = packagePrice;
  }

  @Column(name = "SINGLE_FLAG")
  public Byte getSingleFlag() {
    return this.singleFlag;
  }

  public void setSingleFlag(Byte singleFlag) {
    this.singleFlag = singleFlag;
  }

  @Column(name = "TRAIT_TYPE")
  public Integer getTraitType() {
    return this.traitType;
  }

  public void setTraitType(Integer traitType) {
    this.traitType = traitType;
  }

  @Column(name = "IS_ENTER")
  public Integer getIsEnter() {
    return this.isEnter;
  }

  public void setIsEnter(Integer isEnter) {
    this.isEnter = isEnter;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "IN_DATE", length = 19)
  public Date getInDate() {
    return this.inDate;
  }

  public void setInDate(Date inDate) {
    this.inDate = inDate;
  }

  @Column(name = "IN_CITY", length = 4)
  public String getInCity() {
    return this.inCity;
  }

  public void setInCity(String inCity) {
    this.inCity = inCity;
  }

  @Column(name = "GP_CITY", length = 4)
  public String getGpCity() {
    return this.gpCity;
  }

  public void setGpCity(String gpCity) {
    this.gpCity = gpCity;
  }

  @Column(name = "DBRM")
  public Integer getDbrm() {
    return this.dbrm;
  }

  public void setDbrm(Integer dbrm) {
    this.dbrm = dbrm;
  }

  @Column(name = "SGRM")
  public Integer getSgrm() {
    return this.sgrm;
  }

  public void setSgrm(Integer sgrm) {
    this.sgrm = sgrm;
  }

  @Column(name = "ADRM")
  public Integer getAdrm() {
    return this.adrm;
  }

  public void setAdrm(Integer adrm) {
    this.adrm = adrm;
  }

  @Column(name = "DAY")
  public Integer getDay() {
    return this.day;
  }

  public void setDay(Integer day) {
    this.day = day;
  }

  @Column(name = "PAXDAY")
  public Integer getPaxday() {
    return this.paxday;
  }

  public void setPaxday(Integer paxday) {
    this.paxday = paxday;
  }

  @Column(name = "INCOME", precision = 10)
  public BigDecimal getIncome() {
    return this.income;
  }

  public void setIncome(BigDecimal income) {
    this.income = income;
  }

  @Column(name = "COST", precision = 10)
  public BigDecimal getCost() {
    return this.cost;
  }

  public void setCost(BigDecimal cost) {
    this.cost = cost;
  }

  @Column(name = "BLNRATE", precision = 6)
  public BigDecimal getBlnrate() {
    return this.blnrate;
  }

  public void setBlnrate(BigDecimal blnrate) {
    this.blnrate = blnrate;
  }

  @Column(name = "CAMT03", precision = 10)
  public BigDecimal getCamt03() {
    return this.camt03;
  }

  public void setCamt03(BigDecimal camt03) {
    this.camt03 = camt03;
  }

  @Column(name = "AIRCO", length = 2)
  public String getAirco() {
    return this.airco;
  }

  public void setAirco(String airco) {
    this.airco = airco;
  }

  @Column(name = "ARAMT", precision = 10)
  public BigDecimal getAramt() {
    return this.aramt;
  }

  public void setAramt(BigDecimal aramt) {
    this.aramt = aramt;
  }

  @Column(name = "TOUR_KEY", length = 1)
  public String getTourKey() {
    return this.tourKey;
  }

  public void setTourKey(String tourKey) {
    this.tourKey = tourKey;
  }

  @Column(name = "OPUSER1")
  public Integer getOpuser1() {
    return this.opuser1;
  }

  public void setOpuser1(Integer opuser1) {
    this.opuser1 = opuser1;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "OPTIME1", length = 19)
  public Date getOptime1() {
    return this.optime1;
  }

  public void setOptime1(Date optime1) {
    this.optime1 = optime1;
  }

  @Column(name = "OPUSER2")
  public Integer getOpuser2() {
    return this.opuser2;
  }

  public void setOpuser2(Integer opuser2) {
    this.opuser2 = opuser2;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "OPTIME2", length = 19)
  public Date getOptime2() {
    return this.optime2;
  }

  public void setOptime2(Date optime2) {
    this.optime2 = optime2;
  }

  @Column(name = "COUNTRY", length = 100)
  public String getCountry() {
    return this.country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  @Column(name = "PICC_NO", length = 12)
  public String getPiccNo() {
    return this.piccNo;
  }

  public void setPiccNo(String piccNo) {
    this.piccNo = piccNo;
  }

  @Column(name = "GHD_NUM")
  public Integer getGhdNum() {
    return this.ghdNum;
  }

  public void setGhdNum(Integer ghdNum) {
    this.ghdNum = ghdNum;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "GHD_DATE", length = 19)
  public Date getGhdDate() {
    return this.ghdDate;
  }

  public void setGhdDate(Date ghdDate) {
    this.ghdDate = ghdDate;
  }

  @Column(name = "TOUR_RMK", length = 40)
  public String getTourRmk() {
    return this.tourRmk;
  }

  public void setTourRmk(String tourRmk) {
    this.tourRmk = tourRmk;
  }

  @Column(name = "TOUR_DEL", length = 1)
  public Character getTourDel() {
    return this.tourDel;
  }

  public void setTourDel(Character tourDel) {
    this.tourDel = tourDel;
  }

  @Column(name = "REMARKS", length = 1000)
  public String getRemarks() {
    return this.remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  @Column(name = "INVPNT_KEY", length = 1)
  public Character getInvpntKey() {
    return this.invpntKey;
  }

  public void setInvpntKey(Character invpntKey) {
    this.invpntKey = invpntKey;
  }

  @Column(name = "VISA_NCODE", length = 30)
  public String getVisaNcode() {
    return this.visaNcode;
  }

  public void setVisaNcode(String visaNcode) {
    this.visaNcode = visaNcode;
  }

  @Column(name = "VISA_NNAME", length = 100)
  public String getVisaNname() {
    return this.visaNname;
  }

  public void setVisaNname(String visaNname) {
    this.visaNname = visaNname;
  }

  @Column(name = "IS_NEED_LEADER")
  public Integer getIsNeedLeader() {
    return this.isNeedLeader;
  }

  public void setIsNeedLeader(Integer isNeedLeader) {
    this.isNeedLeader = isNeedLeader;
  }

  @Column(name = "DBAMT", precision = 18)
  public BigDecimal getDbamt() {
    return this.dbamt;
  }

  public void setDbamt(BigDecimal dbamt) {
    this.dbamt = dbamt;
  }

  @Column(name = "CRAMT", precision = 18)
  public BigDecimal getCramt() {
    return this.cramt;
  }

  public void setCramt(BigDecimal cramt) {
    this.cramt = cramt;
  }

  @Column(name = "OP_KEY", length = 1)
  public Character getOpKey() {
    return this.opKey;
  }

  public void setOpKey(Character opKey) {
    this.opKey = opKey;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "CR_DATE", length = 19)
  public Date getCrDate() {
    return this.crDate;
  }

  public void setCrDate(Date crDate) {
    this.crDate = crDate;
  }

  @Column(name = "CHK_KEY", length = 1)
  public Character getChkKey() {
    return this.chkKey;
  }

  public void setChkKey(Character chkKey) {
    this.chkKey = chkKey;
  }

  @Column(name = "CHK_ISSUE", length = 1)
  public String getChkIssue() {
    return this.chkIssue;
  }

  public void setChkIssue(String chkIssue) {
    this.chkIssue = chkIssue;
  }

  @Column(name = "CHK_USER")
  public Integer getChkUser() {
    return this.chkUser;
  }

  public void setChkUser(Integer chkUser) {
    this.chkUser = chkUser;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "CHK_DATE", length = 19)
  public Date getChkDate() {
    return this.chkDate;
  }

  public void setChkDate(Date chkDate) {
    this.chkDate = chkDate;
  }

  @Column(name = "INV_KEY", length = 1)
  public Character getInvKey() {
    return this.invKey;
  }

  public void setInvKey(Character invKey) {
    this.invKey = invKey;
  }

  @Column(name = "INV_USER")
  public Integer getInvUser() {
    return this.invUser;
  }

  public void setInvUser(Integer invUser) {
    this.invUser = invUser;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "INV_DATE", length = 19)
  public Date getInvDate() {
    return this.invDate;
  }

  public void setInvDate(Date invDate) {
    this.invDate = invDate;
  }

  @Column(name = "OPUSER")
  public Integer getOpuser() {
    return this.opuser;
  }

  public void setOpuser(Integer opuser) {
    this.opuser = opuser;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "OPTIME", nullable = false, length = 19)
  public Date getOptime() {
    return this.optime;
  }

  public void setOptime(Date optime) {
    this.optime = optime;
  }

  @Column(name = "CUSTOMER_ID")
  public Integer getCustomerId() {
    return this.customerId;
  }

  public void setCustomerId(Integer customerId) {
    this.customerId = customerId;
  }

  @Column(name = "EXEMPT_PAX")
  public Integer getExemptPax() {
    return this.exemptPax;
  }

  public void setExemptPax(Integer exemptPax) {
    this.exemptPax = exemptPax;
  }

  @Column(name = "AMOUNT", precision = 18)
  public BigDecimal getAmount() {
    return this.amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  @Column(name = "COST_AMOUNT", nullable = false, precision = 18)
  public BigDecimal getCostAmount() {
    return this.costAmount;
  }

  public void setCostAmount(BigDecimal costAmount) {
    this.costAmount = costAmount;
  }

  @Column(name = "OP_ACCOUNT", nullable = false, length = 1)
  public char getOpAccount() {
    return this.opAccount;
  }

  public void setOpAccount(char opAccount) {
    this.opAccount = opAccount;
  }

  @Column(name = "OP_REFACTOR", nullable = false, length = 1)
  public char getOpRefactor() {
    return this.opRefactor;
  }

  public void setOpRefactor(char opRefactor) {
    this.opRefactor = opRefactor;
  }

  @Column(name = "FR_CHECKED", length = 1)
  public Character getFrChecked() {
    return this.frChecked;
  }

  public void setFrChecked(Character frChecked) {
    this.frChecked = frChecked;
  }

  @Column(name = "FR_USER")
  public Integer getFrUser() {
    return this.frUser;
  }

  public void setFrUser(Integer frUser) {
    this.frUser = frUser;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "FR_DATE", length = 19)
  public Date getFrDate() {
    return this.frDate;
  }

  public void setFrDate(Date frDate) {
    this.frDate = frDate;
  }

  @Column(name = "EXT_COST", precision = 10)
  public BigDecimal getExtCost() {
    return this.extCost;
  }

  public void setExtCost(BigDecimal extCost) {
    this.extCost = extCost;
  }

  @Column(name = "EXT_COST_NOTE", length = 200)
  public String getExtCostNote() {
    return this.extCostNote;
  }

  public void setExtCostNote(String extCostNote) {
    this.extCostNote = extCostNote;
  }

  @Column(name = "TOUR_AMOUNT", precision = 16)
  public BigDecimal getTourAmount() {
    return this.tourAmount;
  }

  public void setTourAmount(BigDecimal tourAmount) {
    this.tourAmount = tourAmount;
  }

  /** 出发城市 */
  private String outCity;

  /** 计划名额 */
  private Integer pax1;

  /** 预留名额 */
  private Integer pax4;

  /** 最小成团人数 */
  private Integer pax5;

  /** 领队数 */
  private Integer leaderPax;

  /** 报名截至日期（出团日期-截止天数） */
  private Date deadline;

  /** 是否网站发布 Y-发布 N-不发布 */
  private String deployFlag;

  /** 报名提醒 */
  private String message;

  /** 集合地点 */
  private String venue;

  /** 团队取消否 */
  private String delKey;

  /** 是否建团（Y-成团、N-未成团） */
  private String isBuildup;

  /** 记录修改人 */
  private Integer opUser;

  /** 记录更新时间 */
  private Date opDate;

  // ------------------------------------------------------------------------

  /** 报名截至天数 */
  private int deadNum;

  /** 直客利润 */
  private BigDecimal zkProfit;

  /** 同业利润 */
  private BigDecimal tyProfit;

  /** 整团或者散客的值 */
  private String singleFlagValue;

  private String singleShow;

  /** 取消标记 Y-取消 N-正常 */
  private String cancelFlag;

  /** 当前可否报名 */
  private boolean enter;

  /** 计划起始日期（制作计划时使用） */
  private Date startDate;

  /** 计划截止日期（制作计划时使用） */
  private Date endDate;

  private String weekBit;

  /** 产品推广方式 */
  private int traitId;

  /** 领队 */
  private String leader;

  /** 修改记录 */
  private String info;

  /** 序号 */
  private int count;

  private Double price;

  // -------------------------------------------------------------------------
  // 机位共享
  /** 新建共享或者使用已有共享 */
  private String selectNO;

  /** 航班号 */
  private String flightNo;

  /** 航空公司 */
  private String airwaysCode;

  /** 出发日期 */
  private Date departureDate;

  /** 座位数 */
  private Integer seating;

  /** 可操作座位数 */
  private Integer handle;

  /** 备注 */
  private String note;

  // -------------------------------------------------------------------------
  // 统计使用
  private int sumPax;

  private Double sumDbamt;

  /** 单人间 */
  private int singleRoom;

  /** 双人间 */
  private int doubleRoom;

  /** 加床间 */
  private int extraBedRoom;

  /** 建团时间 */
  private Date dateCreated;

  /** 已收款 */
  private BigDecimal alAmount;

  /** 应收款 */
  private BigDecimal muAmount;

  /** 未收款 */
  private BigDecimal wiAmount;

  /** 客人明晰 */
  private List<Tourist> customerList;

  // -------------------------------------------------------------------------
  /** 简单打印-表头 */
  private String simpleReport;

  /** 打印客人名单页头说明 */
  private String title;

  /** 发件人 */
  private String send;

  /** 收件人 */
  private String receive;

  /** 文件标题 */
  private String fileTitle;

  // -------------------------------------------------------------------------
  // 是否打印项
  /** 名字 */
  private String printName;

  /** 拼音 */
  private String printPinyin;

  /** 性别 */
  private String printSex;

  /** 年龄 */
  private String printAge;

  /** 代理商 */
  private String printAgent;

  /** 身份证 */
  private String printIdcard;

  /** 出生日期 */
  private String printBirthday;

  /** 出生地 */
  private String printBirPla;

  /** 护照种类 */
  private String printPassportType;

  /** 护照号码 */
  private String printPassportNo;

  /** 签发日期 */
  private String printPassportDate;

  /** 护照有效期 */
  private String printPassportExpiry;

  /** 签发地点 */
  private String printPassportPla;

  /** 护照说明 */
  private String printPassportAnnotation;

  /** 备注 */
  private String printRemarks;

  // -------------------------------------------------------------------------
  // 境外报团名单
  /** 标注一 */
  private String label1;

  /** 标注二 */
  private String label2;

  /** 标注三 */
  private String label3;

  // --------------------港澳名单打印----------------------------------------
  /** 领队号 */
  private String leadnum;

  /** 行程时间1 */
  private String date1;

  /** 行程起始地1 */
  private String pla1;

  /** 行程经过地1 */
  private String pass1;

  /** 行程目的地1 */
  private String end1;

  /** 行程时间2 */
  private String date2;

  /** 行程起始地2 */
  private String pla2;

  /** 行程经过地2 */
  private String pass2;

  /** 行程目的地2 */
  private String end2;

  /** 行程时间3 */
  private String date3;

  /** 行程起始地3 */
  private String pla3;

  /** 行程经过地3 */
  private String pass3;

  /** 行程目的地3 */
  private String end3;

  /** 抵港日期 */
  private String arrHKdate;

  /** 抵港时间 */
  private String arrHKtime;

  /** 抵港交通 */
  private String arrHKvehicle;

  /** 离港日期 */
  private String leaveHKdate;

  /** 离港时间 */
  private String leaveHKtime;

  /** 离港交通 */
  private String leaveHKvehicle;

  /** 抵澳日期 */
  private String arrMCdate;

  /** 抵澳时间 */
  private String arrMCtime;

  /** 抵澳交通 */
  private String arrMCvehicle;

  /** 离澳日期 */
  private String leaveMCdate;

  /** 离澳时间 */
  private String leaveMCtime;

  /** 离澳交通 */
  private String leaveMCvehicle;

  private String gapax;

  private String gamalePax;

  private String gafemalePax;

  private String gachildPax;

  private String galeadPax;

  /** 内地组团社名称 */
  private String localTname;

  /** 香港组团社名称 */
  private String HKTname;

  /** 澳门组团社名称 */
  private String MCTname;

  /** 内地联系人 */
  private String localEcontact;

  /** 香港联系人 */
  private String HKEcontact;

  /** 澳门联系人 */
  private String MCEcontact;

  /** 组团社序号 */
  private String tourSerialNumber;

  /** 接待社名称 */
  private String receptionTname;

  /** 接待社联系人 */
  private String receptionEcontact;

  private String printPax;

  private String printMalePax;

  private String printFemalePax;

  private String printOutDate;

  private String printInDate;

  private String year;

  private String leaderKey;

  private String outInKey;

  private String leaderPrintName;

  private String leaderPrintPinyin;

  private String leaderPrintSex;

  private String leaderPrintBirthday;

  private String leaderPrintBirPla;

  private String leaderPrintPassportNo;

  private String leaderPrintPassportPla;

  private String leaderPrintPassportDate;

  /** 毛利 */
  private BigDecimal grossAmount;

  /** 毛利率 */
  private BigDecimal grossAmountRate;

  /** 人均毛利 */
  private BigDecimal grossAmountAverage;

  /** 计调作帐人名 */
  private String opUserName;

  /** 财务审核人名 */
  private String frUserName;

  private String lineNo;

  /** 领队姓名 */
  private String leaderName;

  /** 房间情况 */
  private String roomStatus;

  /** 操作人 */
  private Integer oprateUser;

  private String oprateUserName;

  private int totalPax;

  /** 其它收入 */
  private BigDecimal extrIncome;

  private String extrIncomeDec;

  /** 团队成本明细 */
  private List<TourCost> costList = new ArrayList<TourCost>();

  private List<Booking> bookList = new ArrayList<Booking>();

  private Integer updatedby;

  @Override
  public Plan clone() throws CloneNotSupportedException {
    return (Plan) super.clone();
  }

  public void setPax1(Integer pax1) {
    this.pax1 = pax1;
  }

  public void setPax4(Integer pax4) {
    this.pax4 = pax4;
  }

  public void setPax5(Integer pax5) {
    this.pax5 = pax5;
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  public Integer getPax1() {
    return pax1;
  }

  /**
   * 设定计划名额
   * 
   * @param pax1 人数
   */
  public void setPax1(int pax1) {
    this.pax1 = pax1;
  }

  public Integer getPax4() {
    return pax4;
  }

  public void setPax4(int pax4) {
    this.pax4 = pax4;
  }

  public Integer getPax5() {
    return pax5;
  }

  public void setPax5(int pax5) {
    this.pax5 = pax5;
  }

  public String getCancelFlag() {
    return cancelFlag;
  }

  public void setCancelFlag(String cancelFlag) {
    this.cancelFlag = cancelFlag;
  }

  public Date getDeadline() {
    return deadline;
  }

  public void setDeadline(Date deadline) {
    this.deadline = deadline;
  }

  public String getDeployFlag() {
    return deployFlag;
  }

  public void setDeployFlag(String deployFlag) {
    this.deployFlag = deployFlag;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Date getOpDate() {
    return opDate;
  }

  public void setOpDate(Date opDate) {
    this.opDate = opDate;
  }

  public Integer getOpUser() {
    return opUser;
  }

  public void setOpUser(Integer opUser) {
    this.opUser = opUser;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public int getDeadNum() {
    return deadNum;
  }

  public void setDeadNum(int deadNum) {
    this.deadNum = deadNum;
  }

  public boolean isEnter() {
    return enter;
  }

  public void setEnter(boolean enter) {
    this.enter = enter;
  }

  public Date getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(Date dateCreated) {
    this.dateCreated = dateCreated;
  }

  public String getFlightNo() {
    return flightNo;
  }

  public void setFlightNo(String flightNo) {
    this.flightNo = flightNo;
  }

  public String getAirwaysCode() {
    return airwaysCode;
  }

  public void setAirwaysCode(String airwaysCode) {
    this.airwaysCode = airwaysCode;
  }

  public Date getDepartureDate() {
    return departureDate;
  }

  public void setDepartureDate(Date departureDate) {
    this.departureDate = departureDate;
  }

  public Integer getSeating() {
    return seating;
  }

  public void setSeating(Integer seating) {
    this.seating = seating;
  }

  public Integer getHandle() {
    return handle;
  }

  public void setHandle(Integer handle) {
    this.handle = handle;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public String getSelectNO() {
    return selectNO;
  }

  public void setSelectNO(String selectNO) {
    this.selectNO = selectNO;
  }

  public int getSumPax() {
    return sumPax;
  }

  public void setSumPax(int sumPax) {
    this.sumPax = sumPax;
  }

  public Double getSumDbamt() {
    return sumDbamt;
  }

  public void setSumDbamt(Double sumDbamt) {
    this.sumDbamt = sumDbamt;
  }

  public String getSingleShow() {
    return singleShow;
  }

  public void setSingleShow(String singleShow) {
    this.singleShow = singleShow;
  }

  public String getSingleFlagValue() {
    return singleFlagValue;
  }

  public void setSingleFlagValue(String singleFlagValue) {
    this.singleFlagValue = singleFlagValue;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public String getIsBuildup() {
    return isBuildup;
  }

  public void setIsBuildup(String isBuildup) {
    this.isBuildup = isBuildup;
  }

  public int getTraitId() {
    return traitId;
  }

  public void setTraitId(int traitId) {
    this.traitId = traitId;
  }

  public BigDecimal getZkProfit() {
    return zkProfit;
  }

  public void setZkProfit(BigDecimal zkProfit) {
    this.zkProfit = zkProfit;
  }

  public BigDecimal getTyProfit() {
    return tyProfit;
  }

  public void setTyProfit(BigDecimal tyProfit) {
    this.tyProfit = tyProfit;
  }

  public String getRecordNo() {
    return planNo;
  }

  public void setRecordNo(String recordNo) {
    this.planNo = recordNo;
  }

  public Integer get_day() {
    return day;
  }

  public void set_day(Integer day) {
    this.day = day;
  }

  public Source getSource() {
    return new SAXSource(new TourXMLReader(), new TourInputSource(this));
  }

  public String getVenue() {
    return venue;
  }

  public void setVenue(String venue) {
    this.venue = venue;
  }

  public int getSingleRoom() {
    return singleRoom;
  }

  public void setSingleRoom(int singleRoom) {
    this.singleRoom = singleRoom;
  }

  public int getDoubleRoom() {
    return doubleRoom;
  }

  public void setDoubleRoom(int doubleRoom) {
    this.doubleRoom = doubleRoom;
  }

  public int getExtraBedRoom() {
    return extraBedRoom;
  }

  public void setExtraBedRoom(int extraBedRoom) {
    this.extraBedRoom = extraBedRoom;
  }

  public BigDecimal getAlAmount() {
    return alAmount;
  }

  public void setAlAmount(BigDecimal alAmount) {
    this.alAmount = alAmount;
  }

  public BigDecimal getMuAmount() {
    return muAmount;
  }

  public void setMuAmount(BigDecimal muAmount) {
    this.muAmount = muAmount;
  }

  public BigDecimal getWiAmount() {
    return wiAmount;
  }

  public void setWiAmount(BigDecimal wiAmount) {
    this.wiAmount = wiAmount;
  }

  public String getDelKey() {
    return delKey;
  }

  public void setDelKey(String delKey) {
    this.delKey = delKey;
  }

  public List<Tourist> getCustomerList() {
    return customerList;
  }

  public void setCustomerList(List<Tourist> customerList) {
    this.customerList = customerList;
  }

  public String getSimpleReport() {
    return simpleReport;
  }

  public void setSimpleReport(String simpleReport) {
    this.simpleReport = simpleReport;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getSend() {
    return send;
  }

  public void setSend(String send) {
    this.send = send;
  }

  public String getReceive() {
    return receive;
  }

  public void setReceive(String receive) {
    this.receive = receive;
  }

  public String getFileTitle() {
    return fileTitle;
  }

  public void setFileTitle(String fileTitle) {
    this.fileTitle = fileTitle;
  }

  public String getPrintName() {
    return printName;
  }

  public void setPrintName(String printName) {
    this.printName = printName;
  }

  public String getPrintPinyin() {
    return printPinyin;
  }

  public void setPrintPinyin(String printPinyin) {
    this.printPinyin = printPinyin;
  }

  public String getPrintSex() {
    return printSex;
  }

  public void setPrintSex(String printSex) {
    this.printSex = printSex;
  }

  public String getPrintAge() {
    return printAge;
  }

  public void setPrintAge(String printAge) {
    this.printAge = printAge;
  }

  public String getPrintAgent() {
    return printAgent;
  }

  public void setPrintAgent(String printAgent) {
    this.printAgent = printAgent;
  }

  public String getPrintIdcard() {
    return printIdcard;
  }

  public void setPrintIdcard(String printIdcard) {
    this.printIdcard = printIdcard;
  }

  public String getPrintBirthday() {
    return printBirthday;
  }

  public void setPrintBirthday(String printBirthday) {
    this.printBirthday = printBirthday;
  }

  public String getPrintBirPla() {
    return printBirPla;
  }

  public void setPrintBirPla(String printBirPla) {
    this.printBirPla = printBirPla;
  }

  public String getPrintPassportType() {
    return printPassportType;
  }

  public void setPrintPassportType(String printPassportType) {
    this.printPassportType = printPassportType;
  }

  public String getPrintPassportNo() {
    return printPassportNo;
  }

  public void setPrintPassportNo(String printPassportNo) {
    this.printPassportNo = printPassportNo;
  }

  public String getPrintPassportDate() {
    return printPassportDate;
  }

  public void setPrintPassportDate(String printPassportDate) {
    this.printPassportDate = printPassportDate;
  }

  public String getPrintPassportExpiry() {
    return printPassportExpiry;
  }

  public void setPrintPassportExpiry(String printPassportExpiry) {
    this.printPassportExpiry = printPassportExpiry;
  }

  public String getPrintPassportPla() {
    return printPassportPla;
  }

  public void setPrintPassportPla(String printPassportPla) {
    this.printPassportPla = printPassportPla;
  }

  public String getPrintPassportAnnotation() {
    return printPassportAnnotation;
  }

  public void setPrintPassportAnnotation(String printPassportAnnotation) {
    this.printPassportAnnotation = printPassportAnnotation;
  }

  public String getPrintRemarks() {
    return printRemarks;
  }

  public void setPrintRemarks(String printRemarks) {
    this.printRemarks = printRemarks;
  }

  public String getLabel1() {
    return label1;
  }

  public void setLabel1(String label1) {
    this.label1 = label1;
  }

  public String getLabel2() {
    return label2;
  }

  public void setLabel2(String label2) {
    this.label2 = label2;
  }

  public String getLabel3() {
    return label3;
  }

  public void setLabel3(String label3) {
    this.label3 = label3;
  }

  public String getLeadnum() {
    return leadnum;
  }

  public void setLeadnum(String leadnum) {
    this.leadnum = leadnum;
  }

  public String getDate1() {
    return date1;
  }

  public void setDate1(String date1) {
    this.date1 = date1;
  }

  public String getPla1() {
    return pla1;
  }

  public void setPla1(String pla1) {
    this.pla1 = pla1;
  }

  public String getPass1() {
    return pass1;
  }

  public void setPass1(String pass1) {
    this.pass1 = pass1;
  }

  public String getEnd1() {
    return end1;
  }

  public void setEnd1(String end1) {
    this.end1 = end1;
  }

  public String getDate2() {
    return date2;
  }

  public void setDate2(String date2) {
    this.date2 = date2;
  }

  public String getPla2() {
    return pla2;
  }

  public void setPla2(String pla2) {
    this.pla2 = pla2;
  }

  public String getPass2() {
    return pass2;
  }

  public void setPass2(String pass2) {
    this.pass2 = pass2;
  }

  public String getEnd2() {
    return end2;
  }

  public void setEnd2(String end2) {
    this.end2 = end2;
  }

  public String getDate3() {
    return date3;
  }

  public void setDate3(String date3) {
    this.date3 = date3;
  }

  public String getPla3() {
    return pla3;
  }

  public void setPla3(String pla3) {
    this.pla3 = pla3;
  }

  public String getPass3() {
    return pass3;
  }

  public void setPass3(String pass3) {
    this.pass3 = pass3;
  }

  public String getEnd3() {
    return end3;
  }

  public void setEnd3(String end3) {
    this.end3 = end3;
  }

  public String getArrHKdate() {
    return arrHKdate;
  }

  public void setArrHKdate(String arrHKdate) {
    this.arrHKdate = arrHKdate;
  }

  public String getArrHKtime() {
    return arrHKtime;
  }

  public void setArrHKtime(String arrHKtime) {
    this.arrHKtime = arrHKtime;
  }

  public String getArrHKvehicle() {
    return arrHKvehicle;
  }

  public void setArrHKvehicle(String arrHKvehicle) {
    this.arrHKvehicle = arrHKvehicle;
  }

  public String getLeaveHKdate() {
    return leaveHKdate;
  }

  public void setLeaveHKdate(String leaveHKdate) {
    this.leaveHKdate = leaveHKdate;
  }

  public String getLeaveHKtime() {
    return leaveHKtime;
  }

  public void setLeaveHKtime(String leaveHKtime) {
    this.leaveHKtime = leaveHKtime;
  }

  public String getLeaveHKvehicle() {
    return leaveHKvehicle;
  }

  public void setLeaveHKvehicle(String leaveHKvehicle) {
    this.leaveHKvehicle = leaveHKvehicle;
  }

  public String getArrMCdate() {
    return arrMCdate;
  }

  public void setArrMCdate(String arrMCdate) {
    this.arrMCdate = arrMCdate;
  }

  public String getArrMCtime() {
    return arrMCtime;
  }

  public void setArrMCtime(String arrMCtime) {
    this.arrMCtime = arrMCtime;
  }

  public String getArrMCvehicle() {
    return arrMCvehicle;
  }

  public void setArrMCvehicle(String arrMCvehicle) {
    this.arrMCvehicle = arrMCvehicle;
  }

  public String getLeaveMCdate() {
    return leaveMCdate;
  }

  public void setLeaveMCdate(String leaveMCdate) {
    this.leaveMCdate = leaveMCdate;
  }

  public String getLeaveMCtime() {
    return leaveMCtime;
  }

  public void setLeaveMCtime(String leaveMCtime) {
    this.leaveMCtime = leaveMCtime;
  }

  public String getLeaveMCvehicle() {
    return leaveMCvehicle;
  }

  public void setLeaveMCvehicle(String leaveMCvehicle) {
    this.leaveMCvehicle = leaveMCvehicle;
  }

  public String getGapax() {
    return gapax;
  }

  public void setGapax(String gapax) {
    this.gapax = gapax;
  }

  public String getGamalePax() {
    return gamalePax;
  }

  public void setGamalePax(String gamalePax) {
    this.gamalePax = gamalePax;
  }

  public String getGafemalePax() {
    return gafemalePax;
  }

  public void setGafemalePax(String gafemalePax) {
    this.gafemalePax = gafemalePax;
  }

  public String getGachildPax() {
    return gachildPax;
  }

  public void setGachildPax(String gachildPax) {
    this.gachildPax = gachildPax;
  }

  public String getGaleadPax() {
    return galeadPax;
  }

  public void setGaleadPax(String galeadPax) {
    this.galeadPax = galeadPax;
  }

  public String getLocalTname() {
    return localTname;
  }

  public void setLocalTname(String localTname) {
    this.localTname = localTname;
  }

  public String getHKTname() {
    return HKTname;
  }

  public void setHKTname(String tname) {
    HKTname = tname;
  }

  public String getMCTname() {
    return MCTname;
  }

  public void setMCTname(String tname) {
    MCTname = tname;
  }

  public String getLocalEcontact() {
    return localEcontact;
  }

  public void setLocalEcontact(String localEcontact) {
    this.localEcontact = localEcontact;
  }

  public String getHKEcontact() {
    return HKEcontact;
  }

  public void setHKEcontact(String econtact) {
    HKEcontact = econtact;
  }

  public String getMCEcontact() {
    return MCEcontact;
  }

  public void setMCEcontact(String econtact) {
    MCEcontact = econtact;
  }

  public String getTourSerialNumber() {
    return tourSerialNumber;
  }

  public void setTourSerialNumber(String tourSerialNumber) {
    this.tourSerialNumber = tourSerialNumber;
  }

  public String getReceptionTname() {
    return receptionTname;
  }

  public void setReceptionTname(String receptionTname) {
    this.receptionTname = receptionTname;
  }

  public String getReceptionEcontact() {
    return receptionEcontact;
  }

  public void setReceptionEcontact(String receptionEcontact) {
    this.receptionEcontact = receptionEcontact;
  }

  public String getPrintPax() {
    return printPax;
  }

  public void setPrintPax(String printPax) {
    this.printPax = printPax;
  }

  public String getPrintMalePax() {
    return printMalePax;
  }

  public void setPrintMalePax(String printMalePax) {
    this.printMalePax = printMalePax;
  }

  public String getPrintFemalePax() {
    return printFemalePax;
  }

  public void setPrintFemalePax(String printFemalePax) {
    this.printFemalePax = printFemalePax;
  }

  public String getPrintOutDate() {
    return printOutDate;
  }

  public void setPrintOutDate(String printOutDate) {
    this.printOutDate = printOutDate;
  }

  public String getPrintInDate() {
    return printInDate;
  }

  public void setPrintInDate(String printInDate) {
    this.printInDate = printInDate;
  }

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public String getLeaderKey() {
    return leaderKey;
  }

  public void setLeaderKey(String leaderKey) {
    this.leaderKey = leaderKey;
  }

  public String getOutInKey() {
    return outInKey;
  }

  public void setOutInKey(String outInKey) {
    this.outInKey = outInKey;
  }

  public String getLeaderPrintName() {
    return leaderPrintName;
  }

  public void setLeaderPrintName(String leaderPrintName) {
    this.leaderPrintName = leaderPrintName;
  }

  public String getLeaderPrintPinyin() {
    return leaderPrintPinyin;
  }

  public void setLeaderPrintPinyin(String leaderPrintPinyin) {
    this.leaderPrintPinyin = leaderPrintPinyin;
  }

  public String getLeaderPrintSex() {
    return leaderPrintSex;
  }

  public void setLeaderPrintSex(String leaderPrintSex) {
    this.leaderPrintSex = leaderPrintSex;
  }

  public String getLeaderPrintBirthday() {
    return leaderPrintBirthday;
  }

  public void setLeaderPrintBirthday(String leaderPrintBirthday) {
    this.leaderPrintBirthday = leaderPrintBirthday;
  }

  public String getLeaderPrintBirPla() {
    return leaderPrintBirPla;
  }

  public void setLeaderPrintBirPla(String leaderPrintBirPla) {
    this.leaderPrintBirPla = leaderPrintBirPla;
  }

  public String getLeaderPrintPassportNo() {
    return leaderPrintPassportNo;
  }

  public void setLeaderPrintPassportNo(String leaderPrintPassportNo) {
    this.leaderPrintPassportNo = leaderPrintPassportNo;
  }

  public String getLeaderPrintPassportPla() {
    return leaderPrintPassportPla;
  }

  public void setLeaderPrintPassportPla(String leaderPrintPassportPla) {
    this.leaderPrintPassportPla = leaderPrintPassportPla;
  }

  public String getLeaderPrintPassportDate() {
    return leaderPrintPassportDate;
  }

  public void setLeaderPrintPassportDate(String leaderPrintPassportDate) {
    this.leaderPrintPassportDate = leaderPrintPassportDate;
  }

  public String getLeader() {
    return leader;
  }

  public void setLeader(String leader) {
    this.leader = leader;
  }

  public String getWeekBit() {
    return weekBit;
  }

  public void setWeekBit(String weekBit) {
    this.weekBit = weekBit;
  }

  public BigDecimal getGrossAmount() {
    return grossAmount;
  }

  public void setGrossAmount(BigDecimal grossAmount) {
    this.grossAmount = grossAmount;
  }

  public BigDecimal getGrossAmountRate() {
    return grossAmountRate;
  }

  public void setGrossAmountRate(BigDecimal grossAmountRate) {
    this.grossAmountRate = grossAmountRate;
  }

  public BigDecimal getGrossAmountAverage() {
    return grossAmountAverage;
  }

  public void setGrossAmountAverage(BigDecimal grossAmountAverage) {
    this.grossAmountAverage = grossAmountAverage;
  }

  public String getOpUserName() {
    return opUserName;
  }

  public void setOpUserName(String opUserName) {
    this.opUserName = opUserName;
  }

  public String getFrUserName() {
    return frUserName;
  }

  public void setFrUserName(String frUserName) {
    this.frUserName = frUserName;
  }

  public String getLineNo() {
    return lineNo;
  }

  public void setLineNo(String lineNo) {
    this.lineNo = lineNo;
  }

  public Integer getLeaderPax() {
    return leaderPax;
  }

  public void setLeaderPax(Integer leaderPax) {
    this.leaderPax = leaderPax;
  }

  public String getLeaderName() {
    return leaderName;
  }

  public void setLeaderName(String leaderName) {
    this.leaderName = leaderName;
  }

  public String getRoomStatus() {
    return roomStatus;
  }

  public void setRoomStatus(String roomStatus) {
    this.roomStatus = roomStatus;
  }

  public Integer getOprateUser() {
    return oprateUser;
  }

  public void setOprateUser(Integer oprateUser) {
    this.oprateUser = oprateUser;
  }

  public int getTotalPax() {
    return totalPax;
  }

  public void setTotalPax(int totalPax) {
    this.totalPax = totalPax;
  }

  public BigDecimal getExtrIncome() {
    return extrIncome;
  }

  public void setExtrIncome(BigDecimal extrIncome) {
    this.extrIncome = extrIncome;
  }

  public String getExtrIncomeDec() {
    return extrIncomeDec;
  }

  public void setExtrIncomeDec(String extrIncomeDec) {
    this.extrIncomeDec = extrIncomeDec;
  }

  public List<TourCost> getCostList() {
    return costList;
  }

  public void setCostList(List<TourCost> costList) {
    this.costList = costList;
  }

  public List<Booking> getBookList() {
    return bookList;
  }

  public void setBookList(List<Booking> bookList) {
    this.bookList = bookList;
  }

  public String getOprateUserName() {
    return oprateUserName;
  }

  public void setOprateUserName(String oprateUserName) {
    this.oprateUserName = oprateUserName;
  }

  public Integer getUpdatedby() {
    return this.updatedby;
  }

  public void setUpdatedby(Integer updatedby) {
    this.updatedby = updatedby;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public String getOutCity() {
    return outCity;
  }

  public void setOutCity(String outCity) {
    this.outCity = outCity;
  }

}