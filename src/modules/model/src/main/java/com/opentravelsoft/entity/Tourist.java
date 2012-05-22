package com.opentravelsoft.entity;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
@Table(name = "tbl_tourist")
public class Tourist implements Comparable<Tourist>, java.io.Serializable {

  private String nmno;
  private Booking booking;
  private char recType;
  private String name;
  private String name2;
  private Character cardty;

  /** 证件号码 */
  private String card;
  private Character sex;
  /** 出生年月 */
  private Date birthday;
  private String bthplc;
  private String mobile;
  private Character hzKey;
  private String hzzl;
  private String hzno;
  private Date hzdate1;
  private String hzadd;
  private Date hzrang;
  /** 应收团款 */
  private BigDecimal amt01;

  /** 优惠申请 */
  private BigDecimal amt02;

  /**
   * 已收团款
   * 
   * @deprecated
   */
  private BigDecimal amt03;

  /** 已退团款 */
  private BigDecimal amt04;
  private Character vipkey;
  private String remark;
  /** 同房序号 */
  private Integer roomNo;
  private Integer paxnum;
  private String roomKey;
  private Character roomKey1;
  /** 取消状态 */
  private char del;
  private Integer opuser;
  /** 更新时间 */
  private Date opdate;
  /** 参团要求 */
  private Character tourKey;
  private Character leadKey;
  private String visaKey;
  private Integer rmNum;
  /** 报价 */
  private BigDecimal price;
  private Integer num1;
  private String familyName;
  private String givenName;

  private Character nameKey;
  /** 国家 */
  private String nation;
  private BigDecimal areaAmt;
  private String pvia;
  private BigDecimal expAmt;
  private String expNote;
  private String premCode;
  private BigDecimal premExpend;
  private Character premPrinted;
  private String passRmk;
  private String pactNo;
  /**
   * 发票号
   * 
   * @deprecated
   */
  private String invoiceNo;

  public Tourist() {
    uid = "";
    passwd = "";
    userName = "";
    userType = "";
    isActive = true;
    this.sex = 'M';
    setCardty('1');
    del = 'N';
    confirmStatus = "1";
    leaderKey = "N";
    newFlag = "N";
    tourKey = 'N';
    amount = new BigDecimal(0);
    hzKey = '1';

    memberKey = "N";
    isExist = false;
    nation = "";
    province = "";
    city = "";
    vocation = "";
    receiveMail = "N";
    educate = "";
    score = 0;
    homeplace = "";
    people = "";
    booking = new Booking();
  }

  @Id
  @Column(name = "NMNO", unique = true, nullable = false, length = 10)
  public String getNmno() {
    return this.nmno;
  }

  public void setNmno(String nmno) {
    this.nmno = nmno;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "NAME_NO")
  public Booking getBooking() {
    return booking;
  }

  public void setBooking(Booking booking) {
    this.booking = booking;
  }

  @Column(name = "REC_TYPE", nullable = false, length = 1)
  public char getRecType() {
    return this.recType;
  }

  public void setRecType(char recType) {
    this.recType = recType;
  }

  @Column(name = "NAME", nullable = false, length = 20)
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(name = "NAME2", length = 80)
  public String getName2() {
    return this.name2;
  }

  public void setName2(String name2) {
    this.name2 = name2;
  }

  @Column(name = "CARDTY", length = 1)
  public Character getCardty() {
    return this.cardty;
  }

  public void setCardty(Character cardty) {
    this.cardty = cardty;
  }

  @Column(name = "CARD", length = 18)
  public String getCard() {
    return this.card;
  }

  public void setCard(String card) {
    this.card = card;
  }

  @Column(name = "SEX", length = 1)
  public Character getSex() {
    return this.sex;
  }

  public void setSex(Character sex) {
    this.sex = sex;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "BIRTHDAY", length = 19)
  public Date getBirthday() {
    return this.birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  @Column(name = "BTHPLC", length = 2)
  public String getBthplc() {
    return this.bthplc;
  }

  public void setBthplc(String bthplc) {
    this.bthplc = bthplc;
  }

  @Column(name = "MOBILE", length = 30)
  public String getMobile() {
    return this.mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  @Column(name = "HZ_KEY", length = 1)
  public Character getHzKey() {
    return this.hzKey;
  }

  public void setHzKey(Character hzKey) {
    this.hzKey = hzKey;
  }

  @Column(name = "HZZL", length = 2)
  public String getHzzl() {
    return this.hzzl;
  }

  public void setHzzl(String hzzl) {
    this.hzzl = hzzl;
  }

  @Column(name = "HZNO", length = 12)
  public String getHzno() {
    return this.hzno;
  }

  public void setHzno(String hzno) {
    this.hzno = hzno;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "HZDATE1", length = 19)
  public Date getHzdate1() {
    return this.hzdate1;
  }

  public void setHzdate1(Date hzdate1) {
    this.hzdate1 = hzdate1;
  }

  @Column(name = "HZADD", length = 3)
  public String getHzadd() {
    return this.hzadd;
  }

  public void setHzadd(String hzadd) {
    this.hzadd = hzadd;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "HZRANG", length = 19)
  public Date getHzrang() {
    return this.hzrang;
  }

  public void setHzrang(Date hzrang) {
    this.hzrang = hzrang;
  }

  @Column(name = "AMT01", precision = 8)
  public BigDecimal getAmt01() {
    return this.amt01;
  }

  public void setAmt01(BigDecimal amt01) {
    this.amt01 = amt01;
  }

  @Column(name = "AMT02", precision = 8)
  public BigDecimal getAmt02() {
    return this.amt02;
  }

  public void setAmt02(BigDecimal amt02) {
    this.amt02 = amt02;
  }

  @Column(name = "AMT03", precision = 8)
  public BigDecimal getAmt03() {
    return this.amt03;
  }

  public void setAmt03(BigDecimal amt03) {
    this.amt03 = amt03;
  }

  @Column(name = "AMT04", precision = 8)
  public BigDecimal getAmt04() {
    return this.amt04;
  }

  public void setAmt04(BigDecimal amt04) {
    this.amt04 = amt04;
  }

  @Column(name = "VIPKEY", length = 1)
  public Character getVipkey() {
    return this.vipkey;
  }

  public void setVipkey(Character vipkey) {
    this.vipkey = vipkey;
  }

  @Column(name = "REMARK", length = 40)
  public String getRemark() {
    return this.remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  @Column(name = "ROOM_NO")
  public Integer getRoomNo() {
    return this.roomNo;
  }

  public void setRoomNo(Integer roomNo) {
    this.roomNo = roomNo;
  }

  @Column(name = "PAXNUM")
  public Integer getPaxnum() {
    return this.paxnum;
  }

  public void setPaxnum(Integer paxnum) {
    this.paxnum = paxnum;
  }

  @Column(name = "ROOM_KEY", length = 2)
  public String getRoomKey() {
    return this.roomKey;
  }

  public void setRoomKey(String roomKey) {
    this.roomKey = roomKey;
  }

  @Column(name = "ROOM_KEY1", length = 1)
  public Character getRoomKey1() {
    return this.roomKey1;
  }

  public void setRoomKey1(Character roomKey1) {
    this.roomKey1 = roomKey1;
  }

  @Column(name = "DEL", nullable = false, length = 1)
  public char getDel() {
    return this.del;
  }

  public void setDel(char del) {
    this.del = del;
  }

  @Column(name = "OPUSER")
  public Integer getOpuser() {
    return this.opuser;
  }

  public void setOpuser(Integer opuser) {
    this.opuser = opuser;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "OPDATE", nullable = false, length = 19)
  public Date getOpdate() {
    return this.opdate;
  }

  public void setOpdate(Date opdate) {
    this.opdate = opdate;
  }

  @Column(name = "TOUR_KEY", length = 1)
  public Character getTourKey() {
    return this.tourKey;
  }

  public void setTourKey(Character tourKey) {
    this.tourKey = tourKey;
  }

  @Column(name = "LEAD_KEY", length = 1)
  public Character getLeadKey() {
    return this.leadKey;
  }

  public void setLeadKey(Character leadKey) {
    this.leadKey = leadKey;
  }

  @Column(name = "VISA_KEY", length = 10)
  public String getVisaKey() {
    return this.visaKey;
  }

  public void setVisaKey(String visaKey) {
    this.visaKey = visaKey;
  }

  @Column(name = "RM_NUM")
  public Integer getRmNum() {
    return this.rmNum;
  }

  public void setRmNum(Integer rmNum) {
    this.rmNum = rmNum;
  }

  @Column(name = "PRICE", precision = 8)
  public BigDecimal getPrice() {
    return this.price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
    this.priceSTR = DF.format(price);
  }

  @Column(name = "NUM1")
  public Integer getNum1() {
    return this.num1;
  }

  public void setNum1(Integer num1) {
    this.num1 = num1;
  }

  @Column(name = "FAMILY_NAME", length = 20)
  public String getFamilyName() {
    return this.familyName;
  }

  public void setFamilyName(String familyName) {
    this.familyName = familyName;
  }

  @Column(name = "GIVEN_NAME", length = 20)
  public String getGivenName() {
    return this.givenName;
  }

  public void setGivenName(String givenName) {
    this.givenName = givenName;
  }

  @Column(name = "NAME_KEY", length = 1)
  public Character getNameKey() {
    return this.nameKey;
  }

  public void setNameKey(Character nameKey) {
    this.nameKey = nameKey;
  }

  @Column(name = "NATION", length = 3)
  public String getNation() {
    return this.nation;
  }

  public void setNation(String nation) {
    this.nation = nation;
  }

  @Column(name = "AREA_AMT", precision = 8)
  public BigDecimal getAreaAmt() {
    return this.areaAmt;
  }

  public void setAreaAmt(BigDecimal areaAmt) {
    this.areaAmt = areaAmt;
  }

  @Column(name = "PVIA", length = 60)
  public String getPvia() {
    return this.pvia;
  }

  public void setPvia(String pvia) {
    this.pvia = pvia;
  }

  @Column(name = "EXP_AMT", precision = 11)
  public BigDecimal getExpAmt() {
    return this.expAmt;
  }

  public void setExpAmt(BigDecimal expAmt) {
    this.expAmt = expAmt;
  }

  @Column(name = "EXP_NOTE", length = 100)
  public String getExpNote() {
    return this.expNote;
  }

  public void setExpNote(String expNote) {
    this.expNote = expNote;
  }

  @Column(name = "PREM_CODE", length = 10)
  public String getPremCode() {
    return this.premCode;
  }

  public void setPremCode(String premCode) {
    this.premCode = premCode;
  }

  @Column(name = "PREM_EXPEND", precision = 5)
  public BigDecimal getPremExpend() {
    return this.premExpend;
  }

  public void setPremExpend(BigDecimal premExpend) {
    this.premExpend = premExpend;
  }

  @Column(name = "PREM_PRINTED", length = 1)
  public Character getPremPrinted() {
    return this.premPrinted;
  }

  public void setPremPrinted(Character premPrinted) {
    this.premPrinted = premPrinted;
  }

  @Column(name = "PASS_RMK", length = 60)
  public String getPassRmk() {
    return this.passRmk;
  }

  public void setPassRmk(String passRmk) {
    this.passRmk = passRmk;
  }

  @Column(name = "PACT_NO", length = 50)
  public String getPactNo() {
    return this.pactNo;
  }

  public void setPactNo(String pactNo) {
    this.pactNo = pactNo;
  }

  @Column(name = "INVOICE_NO", length = 50)
  public String getInvoiceNo() {
    return this.invoiceNo;
  }

  public void setInvoiceNo(String invoiceNo) {
    this.invoiceNo = invoiceNo;
  }

  /** 用户ID */
  private int userId;

  /** 用户Code */
  private String uid;

  /** 密码 */
  private String passwd;

  /** 用户真实名称 */
  private String userName;

  private String email;

  /** 用户类型 */
  private String userType;

  private boolean isActive;

  private Integer age;

  /** 会员卡号 */
  private String cardNo;

  /** 领队标志 */
  private String leaderKey;

  /** 合同号 */
  private String contractNo;

  /** 出生地 */
  private String birthplace;

  private String birthplaceName;

  /** 网站会员 */
  private int id;

  /** 身份证号 */
  private String idCard;

  /** 姓名的拼音 */
  private String pinYin;

  /** 籍贯 */
  private String homeplace;

  /** 民族 */
  private String people;

  /** 省份 */
  private String province;

  /** 城市 */
  private String city;

  /** 职业 */
  private String vocation;

  /** 教育程度 */
  private String educate;

  /** 每月家庭收入 */
  private String householdIncome;

  /** 每月个人收入 */
  private String personalIncome;

  /** 平均每年外游次数 */
  private String tripTimes;

  /** 每次旅行的平均花费 */
  private String expendOneTrip;

  /** 喜欢的旅游地点 */
  private String yearningTo;

  /** 是否接收E-MAIL 社内即时的特价促销信息、新推线路等等 */
  private String receiveMail;

  /** 积分 */
  private int score;

  /** 是否是会员 */
  private String memberKey;

  /** 数据库是否存在 */
  private boolean isExist;

  /** 电话 */
  private String phone;

  /** 真实姓名 */
  private String realName;

  // -------------------------------------------------------------------------
  /** 护照类型 */
  private String passportType;

  /** 护照号 */
  private String passportNo;

  /** 护照签发日期 */
  private Date passportDate;

  /** 护照有效期 */
  private Date passportExpiry;

  /** 护照签发地ID */
  private String passportPlace;

  /** 护照签发地名称 */
  private String passportPlaceName;

  /** 护照说明 */
  private String passportAnnotation;

  // -------------------------------------------------------------------------
  /** 报价 */
  private String priceSTR;

  /** 应收团款 */
  private BigDecimal receivables;

  /** 应收团款 */
  private String receivablesSTR;

  /**
   * 已收团款
   * 
   * @deprecated
   */
  private BigDecimal amount;

  /**
   * 未收团款
   * 
   * @deprecated
   */
  private BigDecimal residual;

  /** 报价说明 */
  private String remarks;

  /** 确认状态 */
  private String confirmStatus;

  /** 参团要求 */
  private String tourKeyName;

  private String pinyinsurname;

  private String pinyinfirstname;

  private String isLeader;

  private String cptNo;

  // -------------------------------------------------------------------------
  /** 住房要求 */
  private String roomType;

  /** 住房要求 */
  private String roomTypeName;

  protected DecimalFormat DF = new DecimalFormat("##0");

  protected SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

  /** 序号 为页面显示 */
  private int number;

  /** 线路编号 */
  private String lineNo;

  /** 线路名称 */
  private String lineName;

  /** 出发时间 */
  private Date outDate;

  /** 团号 */
  private String tourNo;

  /** 计划号 */
  private String planNo;

  // -------------------------------------------------------------------------
  // 是否打印项
  /** 是否选中 */
  private int stauts;

  /** 是否打印客人名字 */
  private String printName;

  /** 是否打印拼音 */
  private String printPinyin;

  /** 是否打印性别 */
  private String printSex;

  /** 是否打印年龄 */
  private String printAge;

  /** 是否打印代理商 */
  private String printAgent;

  /** 是否打印身份证 */
  private String printIdcard;

  /** 是否打印出生地 */
  private String printBirPla;

  /** 是否打印出生日期 */
  private Date printBirthday;

  /** 是否打印护照种类 */
  private String printPassportType;

  /** 是否打印护照号码 */
  private String printPassportNo;

  /** 是否打印签发日期 */
  private String printPassportDate;

  /** 是否打印签发日期 */
  private String printPassportExpiry;

  /** 是否打印签发地点 */
  private String printPassportPla;

  /** 是否打印护照说明 */
  private String printPassportAnnotation;

  /** 是否打印备注 */
  private String printRemarks;

  /** 记录标记（NEW） */
  private String newFlag;

  private String child;

  private int customerId;

  /** 预订单号 以 booking 为主 */
  private String bookingNo;

  @Transient
  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  @Transient
  public String getUserType() {
    return userType;
  }

  public void setUserType(String userType) {
    this.userType = userType;
  }

  @Transient
  public String getPasswd() {
    return passwd;
  }

  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }

  @Transient
  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  @Transient
  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  @Transient
  public boolean getIsActive() {
    return isActive;
  }

  public void setIsActive(boolean enabled) {
    this.isActive = enabled;
  }

  @Transient
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Transient
  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  @Transient
  public String getLineNo() {
    return this.lineNo;
  }

  public void setLineNo(String lineNo) {
    this.lineNo = lineNo;
  }

  @Transient
  public String getCptNo() {
    return this.cptNo;
  }

  public void setCptNo(String cptNo) {
    this.cptNo = cptNo;
  }

  @Transient
  public BigDecimal getReceivables() {
    return receivables;
  }

  public void setReceivables(BigDecimal receivables) {
    this.receivables = receivables;
    this.receivablesSTR = DF.format(receivables);
    this.residual = receivables.subtract(amount);
  }

  /**
   * @deprecated
   * @return
   */
  @Transient
  public BigDecimal getAmount() {
    return amount;
  }

  /**
   * @deprecated
   * @param amount
   */
  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  @Transient
  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  @Transient
  public String getRoomType() {
    return roomType;
  }

  public void setRoomType(String roomType) {
    this.roomType = roomType;
  }

  @Transient
  public String getRoomTypeName() {
    return roomTypeName;
  }

  public void setRoomTypeName(String roomTypeName) {
    this.roomTypeName = roomTypeName;
  }

  @Transient
  public String getContractNo() {
    return contractNo;
  }

  public void setContractNo(String contractNo) {
    this.contractNo = contractNo;
  }

  /**
   * @deprecated
   * @return
   */
  @Transient
  public BigDecimal getResidual() {
    return residual;
  }

  /**
   * @deprecated
   * @param residual
   */
  public void setResidual(BigDecimal residual) {
    this.residual = residual;
  }

  @Transient
  public String getConfirmStatus() {
    return confirmStatus;
  }

  public void setConfirmStatus(String confirmStatus) {
    this.confirmStatus = confirmStatus;
  }

  /**
   * 
   * @deprecated
   * @return
   */
  @Transient
  public String getPriceSTR() {
    return priceSTR;
  }

  /**
   * @deprecated
   * @return
   */
  @Transient
  public String getReceivablesSTR() {
    return receivablesSTR;
  }

  @Transient
  public Date getOutDate() {
    return outDate;
  }

  public void setOutDate(Date outDate) {
    this.outDate = outDate;
  }

  @Transient
  public String getTourNo() {
    return tourNo;
  }

  public void setTourNo(String tourNo) {
    this.tourNo = tourNo;
  }

  @Transient
  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  @Transient
  public String getLeaderKey() {
    return leaderKey;
  }

  public void setLeaderKey(String leaderKey) {
    this.leaderKey = leaderKey;
  }

  @Transient
  public String getPrintName() {
    return printName;
  }

  public void setPrintName(String printName) {
    this.printName = printName;
  }

  @Transient
  public String getPrintPinyin() {
    return printPinyin;
  }

  public void setPrintPinyin(String printPinyin) {
    this.printPinyin = printPinyin;
  }

  @Transient
  public String getPrintSex() {
    return printSex;
  }

  public void setPrintSex(String printSex) {
    this.printSex = printSex;
  }

  @Transient
  public String getPrintAge() {
    return printAge;
  }

  public void setPrintAge(String printAge) {
    this.printAge = printAge;
  }

  @Transient
  public String getPrintAgent() {
    return printAgent;
  }

  public void setPrintAgent(String printAgent) {
    this.printAgent = printAgent;
  }

  @Transient
  public String getPrintBirPla() {
    return printBirPla;
  }

  public void setPrintBirPla(String printBirPla) {
    this.printBirPla = printBirPla;
  }

  @Transient
  public String getPrintPassportType() {
    return printPassportType;
  }

  public void setPrintPassportType(String printPassportType) {
    this.printPassportType = printPassportType;
  }

  @Transient
  public String getPrintPassportNo() {
    return printPassportNo;
  }

  public void setPrintPassportNo(String printPassportNo) {
    this.printPassportNo = printPassportNo;
  }

  @Transient
  public String getPrintPassportDate() {
    return printPassportDate;
  }

  public void setPrintPassportDate(String printPassportDate) {
    this.printPassportDate = printPassportDate;
  }

  @Transient
  public String getPrintPassportPla() {
    return printPassportPla;
  }

  public void setPrintPassportPla(String printPassportPla) {
    this.printPassportPla = printPassportPla;
  }

  @Transient
  public String getPrintPassportAnnotation() {
    return printPassportAnnotation;
  }

  public void setPrintPassportAnnotation(String printPassportAnnotation) {
    this.printPassportAnnotation = printPassportAnnotation;
  }

  @Transient
  public String getPrintRemarks() {
    return printRemarks;
  }

  public void setPrintRemarks(String printRemarks) {
    this.printRemarks = printRemarks;
  }

  @Transient
  public int getStauts() {
    return stauts;
  }

  public void setStauts(int stauts) {
    this.stauts = stauts;
  }

  @Transient
  public Date getPrintBirthday() {
    return printBirthday;
  }

  public void setPrintBirthday(Date printBirthday) {
    this.printBirthday = printBirthday;
  }

  @Transient
  public String getPrintIdcard() {
    return printIdcard;
  }

  public void setPrintIdcard(String printIdcard) {
    this.printIdcard = printIdcard;
  }

  @Transient
  public int getCustomerId() {
    return customerId;
  }

  public void setCustomerId(int customerId) {
    this.customerId = customerId;
  }

  @Transient
  public String getTourKeyName() {
    return tourKeyName;
  }

  public void setTourKeyName(String tourKeyName) {
    this.tourKeyName = tourKeyName;
  }

  @Transient
  public String getNewFlag() {
    return newFlag;
  }

  public void setNewFlag(String newFlag) {
    this.newFlag = newFlag;
  }

  @Transient
  public String getChild() {
    return child;
  }

  public void setChild(String child) {
    this.child = child;
  }

  @Transient
  public String getPrintPassportExpiry() {
    return printPassportExpiry;
  }

  public void setPrintPassportExpiry(String printPassportExpiry) {
    this.printPassportExpiry = printPassportExpiry;
  }

  @Transient
  public String getPlanNo() {
    return planNo;
  }

  public void setPlanNo(String planNo) {
    this.planNo = planNo;
  }

  @Transient
  public String getPinyinsurname() {
    return pinyinsurname;
  }

  public void setPinyinsurname(String pinyinsurname) {
    this.pinyinsurname = pinyinsurname;
  }

  @Transient
  public String getPinyinfirstname() {
    return pinyinfirstname;
  }

  public void setPinyinfirstname(String pinyinfirstname) {
    this.pinyinfirstname = pinyinfirstname;
  }

  @Transient
  public String getIsLeader() {
    return isLeader;
  }

  public void setIsLeader(String isLeader) {
    this.isLeader = isLeader;
  }

  public int compareTo(Tourist o) {
    if (o.getLeaderKey().equals("T/L")) {
      return 1;
    }
    return -1;
  }

  @Transient
  public String getLineName() {
    return lineName;
  }

  public void setLineName(String lineName) {
    this.lineName = lineName;
  }

  @Transient
  public String getBirthplace() {
    return birthplace;
  }

  public void setBirthplace(String birthplace) {
    this.birthplace = birthplace;
  }

  @Transient
  public String getBirthplaceName() {
    return birthplaceName;
  }

  public void setBirthplaceName(String birthplaceName) {
    this.birthplaceName = birthplaceName;
  }

  @Transient
  public String getPassportType() {
    return passportType;
  }

  public void setPassportType(String passportType) {
    this.passportType = passportType;
  }

  @Transient
  public String getPassportNo() {
    return passportNo;
  }

  public void setPassportNo(String passportNo) {
    this.passportNo = passportNo;
  }

  @Transient
  public Date getPassportDate() {
    return passportDate;
  }

  public void setPassportDate(Date passportDate) {
    this.passportDate = passportDate;
  }

  @Transient
  public Date getPassportExpiry() {
    return passportExpiry;
  }

  public void setPassportExpiry(Date passportExpiry) {
    this.passportExpiry = passportExpiry;
  }

  @Transient
  public String getPassportPlace() {
    return passportPlace;
  }

  public void setPassportPlace(String passportPlace) {
    this.passportPlace = passportPlace;
  }

  @Transient
  public String getPassportPlaceName() {
    return passportPlaceName;
  }

  public void setPassportPlaceName(String passportPlaceName) {
    this.passportPlaceName = passportPlaceName;
  }

  @Transient
  public String getPassportAnnotation() {
    return passportAnnotation;
  }

  public void setPassportAnnotation(String passportAnnotation) {
    this.passportAnnotation = passportAnnotation;
  }

  @Transient
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Transient
  public String getIdCard() {
    return idCard;
  }

  public void setIdCard(String idCard) {
    this.idCard = idCard;
  }

  @Transient
  public String getPinYin() {
    return pinYin;
  }

  public void setPinYin(String pinYin) {
    this.pinYin = pinYin;
  }

  @Transient
  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  @Transient
  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  @Transient
  public String getEducate() {
    return educate;
  }

  public void setEducate(String educate) {
    this.educate = educate;
  }

  @Transient
  public String getVocation() {
    return vocation;
  }

  public void setVocation(String vocation) {
    this.vocation = vocation;
  }

  @Transient
  public String getReceiveMail() {
    return receiveMail;
  }

  public void setReceiveMail(String receiveMail) {
    this.receiveMail = receiveMail;
  }

  @Transient
  public String getHomeplace() {
    return homeplace;
  }

  public void setHomeplace(String homeplace) {
    this.homeplace = homeplace;
  }

  @Transient
  public String getPeople() {
    return people;
  }

  public void setPeople(String people) {
    this.people = people;
  }

  @Transient
  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  @Transient
  public String getExpendOneTrip() {
    return expendOneTrip;
  }

  public void setExpendOneTrip(String expendOneTrip) {
    this.expendOneTrip = expendOneTrip;
  }

  @Transient
  public String getHouseholdIncome() {
    return householdIncome;
  }

  public void setHouseholdIncome(String householdIncome) {
    this.householdIncome = householdIncome;
  }

  @Transient
  public String getPersonalIncome() {
    return personalIncome;
  }

  public void setPersonalIncome(String personalIncome) {
    this.personalIncome = personalIncome;
  }

  @Transient
  public String getTripTimes() {
    return tripTimes;
  }

  public void setTripTimes(String tripTimes) {
    this.tripTimes = tripTimes;
  }

  @Transient
  public String getYearningTo() {
    return yearningTo;
  }

  public void setYearningTo(String yearningTo) {
    this.yearningTo = yearningTo;
  }

  @Transient
  public String getMemberKey() {
    return memberKey;
  }

  public void setMemberKey(String memberKey) {
    this.memberKey = memberKey;
  }

  public boolean isExist() {
    return isExist;
  }

  public void setExist(boolean isExist) {
    this.isExist = isExist;
  }

  @Transient
  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  @Transient
  public String getRealName() {
    return realName;
  }

  public void setRealName(String realName) {
    this.realName = realName;
  }

  @Transient
  public String getCardNo() {
    return cardNo;
  }

  public void setCardNo(String cardNo) {
    this.cardNo = cardNo;
  }

  @Transient
  public String getBookingNo() {
    return bookingNo;
  }

  public void setBookingNo(String bookingNo) {
    this.bookingNo = bookingNo;
  }

}
