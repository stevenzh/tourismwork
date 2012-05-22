package com.opentravelsoft.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "tbl_guide")
public class Guide implements java.io.Serializable {

  private String accCd;
  private String accNm;
  private String accSex;
  private String pinyin;
  private Integer teamId;
  private String card;
  private String leadCard;
  private String business;
  private String bloodType;
  private String high;
  private String leadKey;
  private Date dateStart;
  private Date dateEnd;
  private String weight;
  private String duty;
  private String tourKey;
  private String tourCard;
  private Date checkDate;
  private String icCard;
  private String remarks1;
  private String workType1;
  private String workType2;
  private String signKey;
  private String tel;
  private String bp;
  private String mobile;
  private String EMail;
  private String address;
  private String character;
  private String taste;
  private String speciality;
  private String workRemark;
  private String finishSchool;
  private Date finishDate;
  private Date birthday;
  private String bthplc;
  private String hzzl;
  private String hzno;
  private String hzadd;
  private Date hzdate1;
  private Date hzrang;
  private String photoFile;
  private String hkPass;

  public Guide() {
  }

  public Guide(String accCd, String accNm, String accSex) {
    this.accCd = accCd;
    this.accNm = accNm;
    this.accSex = accSex;
  }

  @Id
  @Column(name = "ACC_CD", unique = true, nullable = false, length = 4)
  public String getAccCd() {
    return this.accCd;
  }

  public void setAccCd(String accCd) {
    this.accCd = accCd;
  }

  @Column(name = "ACC_NM", nullable = false, length = 10)
  public String getAccNm() {
    return this.accNm;
  }

  public void setAccNm(String accNm) {
    this.accNm = accNm;
  }

  @Column(name = "ACC_SEX", nullable = false, length = 2)
  public String getAccSex() {
    return this.accSex;
  }

  public void setAccSex(String accSex) {
    this.accSex = accSex;
  }

  @Column(name = "PINYIN", length = 50)
  public String getPinyin() {
    return this.pinyin;
  }

  public void setPinyin(String pinyin) {
    this.pinyin = pinyin;
  }

  @Column(name = "TEAM_ID")
  public Integer getTeamId() {
    return this.teamId;
  }

  public void setTeamId(Integer teamId) {
    this.teamId = teamId;
  }

  @Column(name = "CARD", length = 20)
  public String getCard() {
    return this.card;
  }

  public void setCard(String card) {
    this.card = card;
  }

  @Column(name = "LEAD_CARD", length = 20)
  public String getLeadCard() {
    return this.leadCard;
  }

  public void setLeadCard(String leadCard) {
    this.leadCard = leadCard;
  }

  @Column(name = "BUSINESS", length = 3)
  public String getBusiness() {
    return this.business;
  }

  public void setBusiness(String business) {
    this.business = business;
  }

  @Column(name = "BLOOD_TYPE", length = 2)
  public String getBloodType() {
    return this.bloodType;
  }

  public void setBloodType(String bloodType) {
    this.bloodType = bloodType;
  }

  @Column(name = "HIGH", length = 6)
  public String getHigh() {
    return this.high;
  }

  public void setHigh(String high) {
    this.high = high;
  }

  @Column(name = "LEAD_KEY", length = 1)
  public String getLeadKey() {
    return this.leadKey;
  }

  public void setLeadKey(String leadKey) {
    this.leadKey = leadKey;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "DATE_START", length = 19)
  public Date getDateStart() {
    return this.dateStart;
  }

  public void setDateStart(Date dateStart) {
    this.dateStart = dateStart;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "DATE_END", length = 19)
  public Date getDateEnd() {
    return this.dateEnd;
  }

  public void setDateEnd(Date dateEnd) {
    this.dateEnd = dateEnd;
  }

  @Column(name = "WEIGHT", length = 6)
  public String getWeight() {
    return this.weight;
  }

  public void setWeight(String weight) {
    this.weight = weight;
  }

  @Column(name = "DUTY", length = 3)
  public String getDuty() {
    return this.duty;
  }

  public void setDuty(String duty) {
    this.duty = duty;
  }

  @Column(name = "TOUR_KEY", length = 1)
  public String getTourKey() {
    return this.tourKey;
  }

  public void setTourKey(String tourKey) {
    this.tourKey = tourKey;
  }

  @Column(name = "TOUR_CARD", length = 20)
  public String getTourCard() {
    return this.tourCard;
  }

  public void setTourCard(String tourCard) {
    this.tourCard = tourCard;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "CHECK_DATE", length = 19)
  public Date getCheckDate() {
    return this.checkDate;
  }

  public void setCheckDate(Date checkDate) {
    this.checkDate = checkDate;
  }

  @Column(name = "IC_CARD", length = 20)
  public String getIcCard() {
    return this.icCard;
  }

  public void setIcCard(String icCard) {
    this.icCard = icCard;
  }

  @Column(name = "REMARKS1", length = 200)
  public String getRemarks1() {
    return this.remarks1;
  }

  public void setRemarks1(String remarks1) {
    this.remarks1 = remarks1;
  }

  @Column(name = "WORK_TYPE1", length = 1)
  public String getWorkType1() {
    return this.workType1;
  }

  public void setWorkType1(String workType1) {
    this.workType1 = workType1;
  }

  @Column(name = "WORK_TYPE2", length = 1)
  public String getWorkType2() {
    return this.workType2;
  }

  public void setWorkType2(String workType2) {
    this.workType2 = workType2;
  }

  @Column(name = "SIGN_KEY", length = 1)
  public String getSignKey() {
    return this.signKey;
  }

  public void setSignKey(String signKey) {
    this.signKey = signKey;
  }

  @Column(name = "TEL", length = 20)
  public String getTel() {
    return this.tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  @Column(name = "BP", length = 20)
  public String getBp() {
    return this.bp;
  }

  public void setBp(String bp) {
    this.bp = bp;
  }

  @Column(name = "MOBILE", length = 20)
  public String getMobile() {
    return this.mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  @Column(name = "E_MAIL", length = 30)
  public String getEMail() {
    return this.EMail;
  }

  public void setEMail(String EMail) {
    this.EMail = EMail;
  }

  @Column(name = "ADDRESS", length = 40)
  public String getAddress() {
    return this.address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Column(name = "_CHARACTER", length = 20)
  public String getCharacter() {
    return this.character;
  }

  public void setCharacter(String character) {
    this.character = character;
  }

  @Column(name = "TASTE", length = 50)
  public String getTaste() {
    return this.taste;
  }

  public void setTaste(String taste) {
    this.taste = taste;
  }

  @Column(name = "SPECIALITY", length = 50)
  public String getSpeciality() {
    return this.speciality;
  }

  public void setSpeciality(String speciality) {
    this.speciality = speciality;
  }

  @Column(name = "WORK_REMARK", length = 512)
  public String getWorkRemark() {
    return this.workRemark;
  }

  public void setWorkRemark(String workRemark) {
    this.workRemark = workRemark;
  }

  @Column(name = "FINISH_SCHOOL", length = 40)
  public String getFinishSchool() {
    return this.finishSchool;
  }

  public void setFinishSchool(String finishSchool) {
    this.finishSchool = finishSchool;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "FINISH_DATE", length = 19)
  public Date getFinishDate() {
    return this.finishDate;
  }

  public void setFinishDate(Date finishDate) {
    this.finishDate = finishDate;
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

  @Column(name = "HZADD", length = 3)
  public String getHzadd() {
    return this.hzadd;
  }

  public void setHzadd(String hzadd) {
    this.hzadd = hzadd;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "HZDATE1", length = 19)
  public Date getHzdate1() {
    return this.hzdate1;
  }

  public void setHzdate1(Date hzdate1) {
    this.hzdate1 = hzdate1;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "HZRANG", length = 19)
  public Date getHzrang() {
    return this.hzrang;
  }

  public void setHzrang(Date hzrang) {
    this.hzrang = hzrang;
  }

  @Column(name = "PHOTO_FILE", length = 40)
  public String getPhotoFile() {
    return this.photoFile;
  }

  public void setPhotoFile(String photoFile) {
    this.photoFile = photoFile;
  }

  @Column(name = "HK_PASS", length = 20)
  public String getHkPass() {
    return this.hkPass;
  }

  public void setHkPass(String hkPass) {
    this.hkPass = hkPass;
  }

  private String idCard;

  private String leaderKey;

  private String birthplace;

  private String passportType;

  private String passportNo;

  private String passportPlace;

  private Date passportDate;

  private Date passportExpiry;

  @Transient
  public String getLeaderKey() {
    return this.leaderKey;
  }

  public void setLeaderKey(String leadKey) {
    this.leaderKey = leadKey;
  }

  @Transient
  public String getBirthplace() {
    return this.birthplace;
  }

  public void setBirthplace(String birthplace) {
    this.birthplace = birthplace;
  }

  @Transient
  public String getPassportType() {
    return this.passportType;
  }

  public void setPassportType(String type) {
    this.passportType = type;
  }

  @Transient
  public String getPassportNo() {
    return this.passportNo;
  }

  public void setPassportNo(String passportNo) {
    this.passportNo = passportNo;
  }

  @Transient
  public String getPassportPlace() {
    return this.passportPlace;
  }

  public void setPassportPlace(String hzadd) {
    this.passportPlace = hzadd;
  }

  @Transient
  public Date getPassportDate() {
    return this.passportDate;
  }

  public void setPassportDate(Date date) {
    this.passportDate = date;
  }

  @Transient
  public Date getPassportExpiry() {
    return this.passportExpiry;
  }

  public void setPassportExpiry(Date expiry) {
    this.passportExpiry = expiry;
  }

  @Transient
  public String getIdCard() {
    return idCard;
  }

  public void setIdCard(String idCard) {
    this.idCard = idCard;
  }

}
