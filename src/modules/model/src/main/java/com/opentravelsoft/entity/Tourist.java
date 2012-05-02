package com.opentravelsoft.entity;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.opentravelsoft.model.BaseObject;

/**
 * 旅行者
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:32 $
 */
public class Tourist extends BaseObject implements Comparable<Tourist> {
  private static final long serialVersionUID = -7963354820769205284L;

  /** 用户ID */
  private long userId;

  /** 用户Code */
  private String uid;

  /** 密码 */
  private String passwd;

  /** 用户真实名称 */
  private String userName;

  private String givenName;

  private String familyName;

  private String email;

  /** 用户类型 */
  private String userType;

  private boolean isActive;

  private Integer age;

  /** 出生年月 */
  private Date birthday;

  private String sex;

  /** 会员卡号 */
  private String cardNo;

  private String nmno;

  /**
   * 证件类型
   */
  private String cardType;

  private Booking booking;

  private char recType;

  private String hzKey;

  /** 应收团款 */
  private Double amt01;

  /** 优惠申请 */
  private Double amt02;

  /**
   * 已收团款
   * 
   * @deprecated
   */
  private Double amt03;

  /** 已退团款 */
  private Double amt04;

  private Character vipkey;

  private String remark;

  /** 同房序号 */
  private int roomNo;

  private Integer paxnum;

  private String roomKey;

  private Character roomKey1;

  /** 取消状态 */
  private String del;

  private Long opuser;

  /** 更新时间 */
  private Date opdate;

  /** 参团要求 */
  private String tourKey;

  /** 领队标志 */
  private String leaderKey;

  private String visaKey;

  private Integer rmNum;

  /** 报价 */
  private Double price;

  private Integer num1;

  private Double areaAmt;

  private String pvia;

  private Double expAmt;

  private String expNote;

  private String premCode;

  private Double premExpend;

  private Character premPrinted;

  /** 合同号 */
  private String contractNo;

  /**
   * 发票号
   * 
   * @deprecated
   */
  private String invoiceNo;

  /** 出生地 */
  private String birthplace;

  private String birthplaceName;

  /** 网站会员 */
  private int id;

  /** 身份证号 */
  private String idCard;

  /** 证件号码 */
  private String card;

  /** 姓名的拼音 */
  private String pinYin;

  /** 籍贯 */
  private String homeplace;

  /** 民族 */
  private String people;

  /** 国家 */
  private String nation;

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

  private String mobile;

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
  private Double receivables;

  /** 应收团款 */
  private String receivablesSTR;

  /**
   * 已收团款
   * 
   * @deprecated
   */
  private Double amount;

  /**
   * 未收团款
   * 
   * @deprecated
   */
  private Double residual;

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

  // -------------------------------------------------------------------------

  public Tourist() {
    uid = "";
    passwd = "";
    userName = "";
    userType = "";
    isActive = true;
    this.sex = "M";
    setCardty("1");
    del = "N";
    confirmStatus = "1";
    leaderKey = "N";
    newFlag = "N";
    tourKey = "N";
    amount = 0d;
    hzKey = "1";

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

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public String getUserType() {
    return userType;
  }

  public void setUserType(String userType) {
    this.userType = userType;
  }

  public String getPasswd() {
    return passwd;
  }

  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public boolean getIsActive() {
    return isActive;
  }

  public void setIsActive(boolean enabled) {
    this.isActive = enabled;
  }

  public String getGivenName() {
    return givenName;
  }

  public void setGivenName(String firstName) {
    this.givenName = firstName;
  }

  public String getFamilyName() {
    return familyName;
  }

  public void setFamilyName(String familyName) {
    this.familyName = familyName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getLineNo() {
    return this.lineNo;
  }

  public void setLineNo(String lineNo) {
    this.lineNo = lineNo;
  }

  public char getRecType() {
    return this.recType;
  }

  public void setRecType(char recType) {
    this.recType = recType;
  }

  public String getCardty() {
    return this.cardType;
  }

  public void setCardty(String cardty) {
    this.cardType = cardty;
  }

  public String getHzKey() {
    return this.hzKey;
  }

  public void setHzKey(String hzKey) {
    this.hzKey = hzKey;
  }

  public Double getAmt01() {
    return this.amt01;
  }

  public void setAmt01(Double amt01) {
    this.amt01 = amt01;
  }

  public Double getAmt02() {
    return this.amt02;
  }

  public void setAmt02(Double amt02) {
    this.amt02 = amt02;
  }

  /**
   * @deprecated
   * @return
   */
  public Double getAmt03() {
    return this.amt03;
  }

  /**
   * @deprecated
   * @param amt03
   */
  public void setAmt03(Double amt03) {
    this.amt03 = amt03;
  }

  public Double getAmt04() {
    return this.amt04;
  }

  public void setAmt04(Double amt04) {
    this.amt04 = amt04;
  }

  public Character getVipkey() {
    return this.vipkey;
  }

  public void setVipkey(Character vipkey) {
    this.vipkey = vipkey;
  }

  public String getRemark() {
    return this.remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public Integer getPaxnum() {
    return this.paxnum;
  }

  public void setPaxnum(Integer paxnum) {
    this.paxnum = paxnum;
  }

  public String getRoomKey() {
    return this.roomKey;
  }

  public void setRoomKey(String roomKey) {
    this.roomKey = roomKey;
  }

  public Character getRoomKey1() {
    return this.roomKey1;
  }

  public void setRoomKey1(Character roomKey1) {
    this.roomKey1 = roomKey1;
  }

  public Long getOpuser() {
    return this.opuser;
  }

  public void setOpuser(Long opuser) {
    this.opuser = opuser;
  }

  public void setOpuser(int opuser) {
    this.opuser = (long) opuser;
  }

  public Date getOpdate() {
    return this.opdate;
  }

  public void setOpdate(Date opdate) {
    this.opdate = opdate;
  }

  public String getVisaKey() {
    return this.visaKey;
  }

  public void setVisaKey(String visaKey) {
    this.visaKey = visaKey;
  }

  public Integer getRmNum() {
    return this.rmNum;
  }

  public void setRmNum(Integer rmNum) {
    this.rmNum = rmNum;
  }

  public Integer getNum1() {
    return this.num1;
  }

  public void setNum1(Integer num1) {
    this.num1 = num1;
  }

  public Double getAreaAmt() {
    return this.areaAmt;
  }

  public void setAreaAmt(Double areaAmt) {
    this.areaAmt = areaAmt;
  }

  public String getPvia() {
    return this.pvia;
  }

  public void setPvia(String pvia) {
    this.pvia = pvia;
  }

  public String getCptNo() {
    return this.cptNo;
  }

  public void setCptNo(String cptNo) {
    this.cptNo = cptNo;
  }

  public Double getExpAmt() {
    return this.expAmt;
  }

  public void setExpAmt(Double expAmt) {
    this.expAmt = expAmt;
  }

  public String getExpNote() {
    return this.expNote;
  }

  public void setExpNote(String expNote) {
    this.expNote = expNote;
  }

  public String getPremCode() {
    return this.premCode;
  }

  public void setPremCode(String premCode) {
    this.premCode = premCode;
  }

  public Double getPremExpend() {
    return this.premExpend;
  }

  public void setPremExpend(Double premExpend) {
    this.premExpend = premExpend;
  }

  public Character getPremPrinted() {
    return this.premPrinted;
  }

  public void setPremPrinted(Character premPrinted) {
    this.premPrinted = premPrinted;
  }

  public String getNmno() {
    return nmno;
  }

  public void setNmno(String nmno) {
    this.nmno = nmno;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
    this.priceSTR = DF.format(price);
  }

  public Double getReceivables() {
    return receivables;
  }

  public void setReceivables(Double receivables) {
    this.receivables = receivables;
    this.receivablesSTR = DF.format(receivables);
    this.residual = receivables - amount;
  }

  /**
   * @deprecated
   * @return
   */
  public Double getAmount() {
    return amount;
  }

  /**
   * @deprecated
   * @param amount
   */
  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public String getRoomType() {
    return roomType;
  }

  public void setRoomType(String roomType) {
    this.roomType = roomType;
  }

  public String getRoomTypeName() {
    return roomTypeName;
  }

  public void setRoomTypeName(String roomTypeName) {
    this.roomTypeName = roomTypeName;
  }

  public int getRoomNo() {
    return roomNo;
  }

  public void setRoomNo(int roomNo) {
    this.roomNo = roomNo;
  }

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
  public String getInvoiceNo() {
    return invoiceNo;
  }

  /**
   * @deprecated
   * @param invoiceNo
   */
  public void setInvoiceNo(String invoiceNo) {
    this.invoiceNo = invoiceNo;
  }

  /**
   * @deprecated
   * @return
   */
  public Double getResidual() {
    return residual;
  }

  /**
   * @deprecated
   * @param residual
   */
  public void setResidual(Double residual) {
    this.residual = residual;
  }

  public String getConfirmStatus() {
    return confirmStatus;
  }

  public void setConfirmStatus(String confirmStatus) {
    this.confirmStatus = confirmStatus;
  }

  public String getDel() {
    return del;
  }

  public void setDel(String delete) {
    this.del = delete;
  }

  /**
   * 
   * @deprecated
   * @return
   */
  public String getPriceSTR() {
    return priceSTR;
  }

  /**
   * @deprecated
   * @return
   */
  public String getReceivablesSTR() {
    return receivablesSTR;
  }

  public Date getOutDate() {
    return outDate;
  }

  public void setOutDate(Date outDate) {
    this.outDate = outDate;
  }

  public String getTourNo() {
    return tourNo;
  }

  public void setTourNo(String tourNo) {
    this.tourNo = tourNo;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public String getLeaderKey() {
    return leaderKey;
  }

  public void setLeaderKey(String leaderKey) {
    this.leaderKey = leaderKey;
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

  public int getStauts() {
    return stauts;
  }

  public void setStauts(int stauts) {
    this.stauts = stauts;
  }

  public Date getPrintBirthday() {
    return printBirthday;
  }

  public void setPrintBirthday(Date printBirthday) {
    this.printBirthday = printBirthday;
  }

  public String getPrintIdcard() {
    return printIdcard;
  }

  public void setPrintIdcard(String printIdcard) {
    this.printIdcard = printIdcard;
  }

  public int getCustomerId() {
    return customerId;
  }

  public void setCustomerId(int customerId) {
    this.customerId = customerId;
  }

  public String getTourKey() {
    return tourKey;
  }

  public void setTourKey(String tourKey) {
    this.tourKey = tourKey;
  }

  public String getTourKeyName() {
    return tourKeyName;
  }

  public void setTourKeyName(String tourKeyName) {
    this.tourKeyName = tourKeyName;
  }

  public String getNewFlag() {
    return newFlag;
  }

  public void setNewFlag(String newFlag) {
    this.newFlag = newFlag;
  }

  public String getChild() {
    return child;
  }

  public void setChild(String child) {
    this.child = child;
  }

  public String getPrintPassportExpiry() {
    return printPassportExpiry;
  }

  public void setPrintPassportExpiry(String printPassportExpiry) {
    this.printPassportExpiry = printPassportExpiry;
  }

  public String getPlanNo() {
    return planNo;
  }

  public void setPlanNo(String planNo) {
    this.planNo = planNo;
  }

  public String getPinyinsurname() {
    return pinyinsurname;
  }

  public void setPinyinsurname(String pinyinsurname) {
    this.pinyinsurname = pinyinsurname;
  }

  public String getPinyinfirstname() {
    return pinyinfirstname;
  }

  public void setPinyinfirstname(String pinyinfirstname) {
    this.pinyinfirstname = pinyinfirstname;
  }

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

  public String getLineName() {
    return lineName;
  }

  public void setLineName(String lineName) {
    this.lineName = lineName;
  }

  public Booking getBooking() {
    return booking;
  }

  public void setBooking(Booking booking) {
    this.booking = booking;
  }

  public String getBirthplace() {
    return birthplace;
  }

  public void setBirthplace(String birthplace) {
    this.birthplace = birthplace;
  }

  public String getBirthplaceName() {
    return birthplaceName;
  }

  public void setBirthplaceName(String birthplaceName) {
    this.birthplaceName = birthplaceName;
  }

  public String getPassportType() {
    return passportType;
  }

  public void setPassportType(String passportType) {
    this.passportType = passportType;
  }

  public String getPassportNo() {
    return passportNo;
  }

  public void setPassportNo(String passportNo) {
    this.passportNo = passportNo;
  }

  public Date getPassportDate() {
    return passportDate;
  }

  public void setPassportDate(Date passportDate) {
    this.passportDate = passportDate;
  }

  public Date getPassportExpiry() {
    return passportExpiry;
  }

  public void setPassportExpiry(Date passportExpiry) {
    this.passportExpiry = passportExpiry;
  }

  public String getPassportPlace() {
    return passportPlace;
  }

  public void setPassportPlace(String passportPlace) {
    this.passportPlace = passportPlace;
  }

  public String getPassportPlaceName() {
    return passportPlaceName;
  }

  public void setPassportPlaceName(String passportPlaceName) {
    this.passportPlaceName = passportPlaceName;
  }

  public String getPassportAnnotation() {
    return passportAnnotation;
  }

  public void setPassportAnnotation(String passportAnnotation) {
    this.passportAnnotation = passportAnnotation;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getIdCard() {
    return idCard;
  }

  public void setIdCard(String idCard) {
    this.idCard = idCard;
  }

  public String getPinYin() {
    return pinYin;
  }

  public void setPinYin(String pinYin) {
    this.pinYin = pinYin;
  }

  public String getCard() {
    return card;
  }

  public void setCard(String card) {
    this.card = card;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getNation() {
    return nation;
  }

  public void setNation(String nation) {
    this.nation = nation;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getEducate() {
    return educate;
  }

  public void setEducate(String educate) {
    this.educate = educate;
  }

  public String getVocation() {
    return vocation;
  }

  public void setVocation(String vocation) {
    this.vocation = vocation;
  }

  public String getReceiveMail() {
    return receiveMail;
  }

  public void setReceiveMail(String receiveMail) {
    this.receiveMail = receiveMail;
  }

  public String getHomeplace() {
    return homeplace;
  }

  public void setHomeplace(String homeplace) {
    this.homeplace = homeplace;
  }

  public String getPeople() {
    return people;
  }

  public void setPeople(String people) {
    this.people = people;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public String getExpendOneTrip() {
    return expendOneTrip;
  }

  public void setExpendOneTrip(String expendOneTrip) {
    this.expendOneTrip = expendOneTrip;
  }

  public String getHouseholdIncome() {
    return householdIncome;
  }

  public void setHouseholdIncome(String householdIncome) {
    this.householdIncome = householdIncome;
  }

  public String getPersonalIncome() {
    return personalIncome;
  }

  public void setPersonalIncome(String personalIncome) {
    this.personalIncome = personalIncome;
  }

  public String getTripTimes() {
    return tripTimes;
  }

  public void setTripTimes(String tripTimes) {
    this.tripTimes = tripTimes;
  }

  public String getYearningTo() {
    return yearningTo;
  }

  public void setYearningTo(String yearningTo) {
    this.yearningTo = yearningTo;
  }

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

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getRealName() {
    return realName;
  }

  public void setRealName(String realName) {
    this.realName = realName;
  }

  public String getCardNo() {
    return cardNo;
  }

  public void setCardNo(String cardNo) {
    this.cardNo = cardNo;
  }

  public String getBookingNo() {
    return bookingNo;
  }

  public void setBookingNo(String bookingNo) {
    this.bookingNo = bookingNo;
  }

  @Override
  public String toString() {
    return "User[UID:" + uid + " Enabled:" + isActive + "]";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Employee)) {
      return false;
    }

    final Employee user = (Employee) o;

    return this.hashCode() == user.hashCode();
  }

  @Override
  public int hashCode() {
    int result;
    result = (uid != null ? uid.hashCode() : 0);
    result = 29 * result + (userName != null ? userName.hashCode() : 0);
    return result;
  }
}
