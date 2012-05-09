package com.opentravelsoft.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.opentravelsoft.entity.xml.TourInputSource;
import com.opentravelsoft.entity.xml.TourXMLReader;

/**
 * 出团计划
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:34 $
 */
public class Plan extends BaseObject implements Cloneable {
  private static final long serialVersionUID = 9131394440547038990L;

  /** 计划号 */
  private String planNo;

  /** 线路 */
  private Line line;

  /** 出发日期 */
  private Date outDate;

  /** 团号 */
  private String tourNo;

  /** 团名 */
  private String tourNm;

  /** 出发城市 */
  private String outCity;

  /** 操作负责组 */
  private Team team;

  /** 操作负责人 */
  private Employee assigned;

  /** 预订是否控制名额 */
  private String paxkey;

  /** 计划名额 */
  private Integer pax1;

  /** 预留名额 */
  private Integer pax4;

  /** 最小成团人数 */
  private Integer pax5;

  /**
   * 成团状态
   * 
   * D-取消 Y-延期 T-已成团 N-未成团 H-换团 W-退团
   */
  private String uniteStatus;

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

  /** 领队数 */
  private Integer leaderPax;

  /** 开班说明 */
  private String recRmk;

  /** 报名截至日期（出团日期-截止天数） */
  private Date deadline;

  /** 送签日期 */
  private Date plandate2;

  /** 有护照清仓天数 */
  private Integer planDate3;

  /** 无护照清仓天数 */
  private Integer planDate4;

  /** 出照天数 */
  private Date passDate;

  /** 是否网站发布 Y-发布 N-不发布 */
  private String deployFlag;

  /** 报名提醒 */
  private String message;

  /** 特惠出团计划 Y-特惠 N-普通 */
  private String favourable;

  /** 是否共享航班 */
  private String shareFlight;

  /** 共享航班ID */
  private int shareFlightId;

  /** 价格 */
  private LinePrice packagePrice;

  /** 整团或者散客 */
  private Byte singleFlag;

  /** 产品推广方式 */
  private String traitType;

  /** 已有订单 */
  private int isEnter;

  /** 入境日期 */
  private Date inDate;

  /** 入境口岸 */
  private String inCity;

  /** 集合地点 */
  private String venue;
  /** 双人间 */
  private Integer dbrm;

  /** 单人间 */
  private Integer sgrm;

  /** 加床数 */
  private Integer adrm;

  /** 天数 */
  private int day;

  /** 人天数 */
  private Integer paxday;

  /** 收入 */
  private Double amount;

  /** 成本 */
  private Double cost;

  /** 毛利率 */
  private Double blnrate;

  /** 已收团款 */
  private Double aramt;
  /** 未交款 */
  private Double camt03;

  /** 航空公司代码 */
  private String airco;

  /** 团类型 */
  private String tourKey;

  /** 建团人 */
  private Long opuser1;

  /** 建团时间 */
  private Date optime1;

  /** 修改人 */
  private Long opuser2;

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

  /** 团队取消否 */
  private String delKey;

  /** 备注 */
  private String remarks;

  /** 催款单是否全部打印 */
  private String invpntKey;

  /** 是否需要安排领队 */
  private Integer isNeedLeader;

  private Double dbamt;

  private Double cramt;

  /** 是否建团（Y-成团、N-未成团） */
  private String isBuildup;

  private Date crDate;

  private Employee crUser;

  /** 可开票标记 */
  private String chkKey;

  /** 已开票登记 */
  private String chkIssue;

  /** 开票人 */
  private String chkUser;

  /** 开票时间 */
  private Date chkDate;

  /** 可打印催款单标记 */
  private String invKey;

  /** 确认催款标记人 */
  private String invUser;

  /** 确认标记时间 */
  private Date invDate;

  /** 记录修改人 */
  private Long opUser;

  /** 记录更新时间 */
  private Date opDate;

  private Integer customerId;

  /** 减免人数 */
  private Integer exemptPax;

  /** 单团核算标记 - OP是否提交财务(Y-提交财务) */
  private String opRefactor;

  /** 单团核算标记 - 财务审核否 (Y-财务已审核) */
  private String frChecked;

  /** 单团核算标记 - 授权修改 (Y-授权修改) */
  private String opAccount;

  /** 财务审核人 */
  private Long frUser;

  /** 财务审核时间 */
  private Date frDate;

  private Double extCost;

  private String extCostNote;

  /** 纯团费 */
  private double tourAmount;

  private String workflowId;

  // ------------------------------------------------------------------------

  /** 报名截至天数 */
  private int deadNum;

  /** 直客利润 */
  private Double zkProfit;

  /** 同业利润 */
  private Double tyProfit;

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
  private Double alAmount;

  /** 应收款 */
  private Double muAmount;

  /** 未收款 */
  private Double wiAmount;

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

  // -------------------------------------------------------------------------
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
  private double grossAmount;

  /** 毛利率 */
  private double grossAmountRate;

  /** 人均毛利 */
  private double grossAmountAverage;

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
  private double extrIncome;

  private String extrIncomeDec;

  /** 团队成本明细 */
  private List<TourCost> costList = new ArrayList<TourCost>();

  private List<Booking> bookList = new ArrayList<Booking>();

  // -------------------------------------------------------------------------

  private Long updatedby;

  public Plan() {
    line = new Line();
    team = new Team();
    assigned = new Employee();
    tourNm = "";
    pax = 0;
    pax4 = 0;
    remarks = "";
    deployFlag = "Y";
    favourable = "N";
    enter = false;
    isEnter = 0;
    shareFlight = "N";
    paxkey = "Y";
    singleFlag = 1;
    isBuildup = "N";
    delKey = "N";
    uniteStatus = "N";
    opAccount = "N";
    tourAmount = 0d;
    cost = 0d;
    opRefactor = "N";
    packagePrice = new LinePrice();
  }

  public Plan(String planNo, String lineNo, Date outDate, Byte singleFlag) {
    this();
    this.planNo = planNo;
    this.outDate = outDate;
    this.singleFlag = singleFlag;

  }

  public Plan(String lineNo, Date outDate, Double price) {
    this();
    line.setLineNo(lineNo);
    this.outDate = outDate;
    this.price = price;
  }

  @Override
  public Plan clone() throws CloneNotSupportedException {
    return (Plan) super.clone();
  }

  public Employee getAssigned() {
    return assigned;
  }

  public void setAssigned(Employee assigned) {
    this.assigned = assigned;
  }

  public void setPax1(Integer pax1) {
    this.pax1 = pax1;
  }

  public void setPax2(Integer pax2) {
    this.pax2 = pax2;
  }

  public void setPax3(Integer pax3) {
    this.pax3 = pax3;
  }

  public void setPax4(Integer pax4) {
    this.pax4 = pax4;
  }

  public void setPax5(Integer pax5) {
    this.pax5 = pax5;
  }

  public String getRecRmk() {
    return this.recRmk;
  }

  public void setRecRmk(String recRmk) {
    this.recRmk = recRmk;
  }

  public Date getPlandate2() {
    return this.plandate2;
  }

  public void setPlandate2(Date plandate2) {
    this.plandate2 = plandate2;
  }

  public Integer getPlanDate3() {
    return this.planDate3;
  }

  public void setPlanDate3(Integer planDate3) {
    this.planDate3 = planDate3;
  }

  public Integer getPlanDate4() {
    return this.planDate4;
  }

  public void setPlanDate4(Integer planDate4) {
    this.planDate4 = planDate4;
  }

  public Date getPassDate() {
    return this.passDate;
  }

  public void setPassDate(Date passDate) {
    this.passDate = passDate;
  }

  public Date getCrDate() {
    return this.crDate;
  }

  public void setCrDate(Date crDate) {
    this.crDate = crDate;
  }

  public Employee getCrUser() {
    return this.crUser;
  }

  public void setCrUser(Employee crUser) {
    this.crUser = crUser;
  }

  public void setShareFlightId(Integer shareFlightId) {
    this.shareFlightId = shareFlightId;
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  public void setIsEnter(Integer isEnter) {
    this.isEnter = isEnter;
  }

  public String getTourNo() {
    return tourNo;
  }

  public void setTourNo(String tourNo) {
    this.tourNo = tourNo;
  }

  public Date getOutDate() {
    return outDate;
  }

  public void setOutDate(Date outDate) {
    this.outDate = outDate;
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

  public int getPax2() {
    return pax2;
  }

  /**
   * 设定已订名额
   * 
   * @param pax2 人数
   */
  public void setPax2(int pax2) {
    this.pax2 = pax2;
  }

  public Integer getPax3() {
    return pax3;
  }

  /**
   * 设定可用名额
   * 
   * @param pax3
   */
  public void setPax3(int pax3) {
    this.pax3 = pax3;
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

  public String getTourNm() {
    return tourNm;
  }

  public void setTourNm(String tourNm) {
    this.tourNm = tourNm;
  }

  public Line getLine() {
    return line;
  }

  public void setLine(Line line) {
    this.line = line;
  }

  public String getCancelFlag() {
    return cancelFlag;
  }

  public void setCancelFlag(String cancelFlag) {
    this.cancelFlag = cancelFlag;
  }

  public String getFavourable() {
    return favourable;
  }

  public void setFavourable(String favourable) {
    this.favourable = favourable;
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

  public Long getOpUser() {
    return opUser;
  }

  public void setOpUser(Long opUser) {
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

  public String getPaxkey() {
    return paxkey;
  }

  public void setPaxkey(String paxkey) {
    this.paxkey = paxkey;
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

  public String getShareFlight() {
    return shareFlight;
  }

  public void setShareFlight(String shareFlight) {
    this.shareFlight = shareFlight;
  }

  public Integer getShareFlightId() {
    return shareFlightId;
  }

  public void setShareFlightId(int shareFlightId) {
    this.shareFlightId = shareFlightId;
  }

  public int getIsEnter() {
    return isEnter;
  }

  public void setIsEnter(int isEnter) {
    this.isEnter = isEnter;
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

  public Byte getSingleFlag() {
    return singleFlag;
  }

  public void setSingleFlag(Byte singleFlag) {
    this.singleFlag = singleFlag;
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

  public String getTraitType() {
    return traitType;
  }

  public void setTraitType(String traitType) {
    this.traitType = traitType;
  }

  public Double getZkProfit() {
    return zkProfit;
  }

  public void setZkProfit(Double zkProfit) {
    this.zkProfit = zkProfit;
  }

  public Double getTyProfit() {
    return tyProfit;
  }

  public void setTyProfit(Double tyProfit) {
    this.tyProfit = tyProfit;
  }

  public String getPlanNo() {
    return planNo;
  }

  public void setPlanNo(String cptNo) {
    this.planNo = cptNo;
  }

  public String getRecordNo() {
    return planNo;
  }

  public void setRecordNo(String recordNo) {
    this.planNo = recordNo;
  }

  public Date getInDate() {
    return inDate;
  }

  public void setInDate(Date inDate) {
    this.inDate = inDate;
  }

  public String getInCity() {
    return inCity;
  }

  public void setInCity(String inCity) {
    this.inCity = inCity;
  }

  public Integer getDbrm() {
    return dbrm;
  }

  public void setDbrm(Integer dbrm) {
    this.dbrm = dbrm;
  }

  public Integer getSgrm() {
    return sgrm;
  }

  public void setSgrm(Integer sgrm) {
    this.sgrm = sgrm;
  }

  public Integer getAdrm() {
    return adrm;
  }

  public void setAdrm(Integer adrm) {
    this.adrm = adrm;
  }

  public Integer get_day() {
    return day;
  }

  public void set_day(Integer day) {
    this.day = day;
  }

  public void setDay(int day) {
    this.day = day;
  }

  public Integer getDay() {
    return day;
  }

  public Integer getPaxday() {
    return paxday;
  }

  public void setPaxday(Integer paxday) {
    this.paxday = paxday;
  }

  public Double getCost() {
    return cost;
  }

  public void setCost(Double cost) {
    this.cost = cost;
  }

  public Double getBlnrate() {
    return blnrate;
  }

  public void setBlnrate(Double blnrate) {
    this.blnrate = blnrate;
  }

  public Double getCamt03() {
    return camt03;
  }

  public void setCamt03(Double camt03) {
    this.camt03 = camt03;
  }

  public String getAirco() {
    return airco;
  }

  public void setAirco(String airco) {
    this.airco = airco;
  }

  public Double getAramt() {
    return aramt;
  }

  public void setAramt(Double aramt) {
    this.aramt = aramt;
  }

  public String getTourKey() {
    return tourKey;
  }

  public void setTourKey(String tourKey) {
    this.tourKey = tourKey;
  }

  public Long getOpuser1() {
    return opuser1;
  }

  public void setOpuser1(Long opuser1) {
    this.opuser1 = opuser1;
  }

  public Date getOptime1() {
    return optime1;
  }

  public void setOptime1(Date optime1) {
    this.optime1 = optime1;
  }

  public Long getOpuser2() {
    return opuser2;
  }

  public void setOpuser2(Long opuser2) {
    this.opuser2 = opuser2;
  }

  public Date getOptime2() {
    return optime2;
  }

  public void setOptime2(Date optime2) {
    this.optime2 = optime2;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getPiccNo() {
    return piccNo;
  }

  public void setPiccNo(String piccNo) {
    this.piccNo = piccNo;
  }

  public Integer getGhdNum() {
    return ghdNum;
  }

  public void setGhdNum(Integer ghdNum) {
    this.ghdNum = ghdNum;
  }

  public Date getGhdDate() {
    return ghdDate;
  }

  public void setGhdDate(Date ghdDate) {
    this.ghdDate = ghdDate;
  }

  public String getTourRmk() {
    return tourRmk;
  }

  public void setTourRmk(String tourRmk) {
    this.tourRmk = tourRmk;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public String getInvKey() {
    return invKey;
  }

  public void setInvKey(String invKey) {
    this.invKey = invKey;
  }

  public String getInvUser() {
    return invUser;
  }

  public void setInvUser(String invUser) {
    this.invUser = invUser;
  }

  public Date getInvDate() {
    return invDate;
  }

  public void setInvDate(Date invDate) {
    this.invDate = invDate;
  }

  public String getInvpntKey() {
    return invpntKey;
  }

  public void setInvpntKey(String invpntKey) {
    this.invpntKey = invpntKey;
  }

  public String getChkKey() {
    return chkKey;
  }

  public void setChkKey(String chkKey) {
    this.chkKey = chkKey;
  }

  public String getChkIssue() {
    return chkIssue;
  }

  public void setChkIssue(String chkIssue) {
    this.chkIssue = chkIssue;
  }

  public String getChkUser() {
    return chkUser;
  }

  public void setChkUser(String chkUser) {
    this.chkUser = chkUser;
  }

  public Date getChkDate() {
    return chkDate;
  }

  public void setChkDate(Date chkDate) {
    this.chkDate = chkDate;
  }

  public Integer getIsNeedLeader() {
    return isNeedLeader;
  }

  public void setIsNeedLeader(Integer isNeedLeader) {
    this.isNeedLeader = isNeedLeader;
  }

  public Source getSource() {
    return new SAXSource(new TourXMLReader(), new TourInputSource(this));
  }

  public int getPax() {
    return pax;
  }

  public void setPax(int pax) {
    this.pax = pax;
  }

  public String getVenue() {
    return venue;
  }

  public void setVenue(String venue) {
    this.venue = venue;
  }

  public Integer getMalePax() {
    return malePax;
  }

  public void setMalePax(Integer malePax) {
    this.malePax = malePax;
  }

  public Integer getFemalePax() {
    return femalePax;
  }

  public void setFemalePax(Integer femalePax) {
    this.femalePax = femalePax;
  }

  public Integer getChildPax() {
    return childPax;
  }

  public void setChildPax(Integer childPax) {
    this.childPax = childPax;
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

  public Double getAlAmount() {
    return alAmount;
  }

  public void setAlAmount(Double alAmount) {
    this.alAmount = alAmount;
  }

  public Double getMuAmount() {
    return muAmount;
  }

  public void setMuAmount(Double muAmount) {
    this.muAmount = muAmount;
  }

  public Double getWiAmount() {
    return wiAmount;
  }

  public void setWiAmount(Double wiAmount) {
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

  public Double getDbamt() {
    return dbamt;
  }

  public void setDbamt(Double dbamt) {
    this.dbamt = dbamt;
  }

  public Double getCramt() {
    return cramt;
  }

  public void setCramt(Double cramt) {
    this.cramt = cramt;
  }

  public Integer getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Integer customerId) {
    this.customerId = customerId;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public double getTourAmount() {
    return tourAmount;
  }

  public void setTourAmount(double tourAmount) {
    this.tourAmount = tourAmount;
  }

  public double getGrossAmount() {
    return grossAmount;
  }

  public void setGrossAmount(double grossAmount) {
    this.grossAmount = grossAmount;
  }

  public double getGrossAmountRate() {
    return grossAmountRate;
  }

  public void setGrossAmountRate(double grossAmountRate) {
    this.grossAmountRate = grossAmountRate;
  }

  public double getGrossAmountAverage() {
    return grossAmountAverage;
  }

  public void setGrossAmountAverage(double grossAmountAverage) {
    this.grossAmountAverage = grossAmountAverage;
  }

  public String getOpAccount() {
    return opAccount;
  }

  public void setOpAccount(String opAccount) {
    this.opAccount = opAccount;
  }

  public String getOpUserName() {
    return opUserName;
  }

  public void setOpUserName(String opUserName) {
    this.opUserName = opUserName;
  }

  public String getFrChecked() {
    return frChecked;
  }

  public void setFrChecked(String frChecked) {
    this.frChecked = frChecked;
  }

  public Long getFrUser() {
    return frUser;
  }

  public void setFrUser(Long frUser) {
    this.frUser = frUser;
  }

  public String getFrUserName() {
    return frUserName;
  }

  public void setFrUserName(String frUserName) {
    this.frUserName = frUserName;
  }

  public Date getFrDate() {
    return frDate;
  }

  public void setFrDate(Date frDate) {
    this.frDate = frDate;
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

  public String getOpRefactor() {
    return opRefactor;
  }

  public void setOpRefactor(String opRefactor) {
    this.opRefactor = opRefactor;
  }

  public double getExtrIncome() {
    return extrIncome;
  }

  public void setExtrIncome(double extrIncome) {
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

  public Integer getExemptPax() {
    return this.exemptPax;
  }

  public void setExemptPax(Integer exemptPax) {
    this.exemptPax = exemptPax;
  }

  public Double getExtCost() {
    return this.extCost;
  }

  public void setExtCost(Double extCost) {
    this.extCost = extCost;
  }

  public String getExtCostNote() {
    return this.extCostNote;
  }

  public void setExtCostNote(String extCostNote) {
    this.extCostNote = extCostNote;
  }

  public Long getUpdatedby() {
    return this.updatedby;
  }

  public void setUpdatedby(Long updatedby) {
    this.updatedby = updatedby;
  }

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  public String getUniteStatus() {
    return uniteStatus;
  }

  public void setUniteStatus(String uniteStatus) {
    this.uniteStatus = uniteStatus;
  }

  public LinePrice getPackagePrice() {
    return packagePrice;
  }

  public void setPackagePrice(LinePrice packagePrice) {
    this.packagePrice = packagePrice;
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

  public String getRemarks() {
    return remarks;
  }

  public String getWorkflowId() {
    return workflowId;
  }

  public void setWorkflowId(String workflowId) {
    this.workflowId = workflowId;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("planNo", this.planNo)
        .append("lineNo", this.getLine().getLineNo())
        .append("outDate", this.outDate).toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Plan)) {
      return false;
    }

    final Plan plan = (Plan) o;
    return this.hashCode() == plan.hashCode();
  }

  @Override
  public int hashCode() {
    int result;
    result = (planNo != null ? planNo.hashCode() : 0);
    result = 29 * result + (getLine() != null ? getLineNo().hashCode() : 0);
    result = 29 * result + (outDate != null ? outDate.hashCode() : 0);
    return result;
  }

}
