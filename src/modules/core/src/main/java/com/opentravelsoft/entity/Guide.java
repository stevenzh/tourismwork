package com.opentravelsoft.entity;

import java.util.Date;

import com.opentravelsoft.model.BaseObject;

/**
 * 导游
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:33 $
 */
public class Guide extends BaseObject {
  private static final long serialVersionUID = 2649259141484415927L;

  private String accCd;

  private String accNm;

  private String accSex;

  private String pinyin;

  private Long teamId;

  private String idCard;

  private String leadCard;

  private String business;

  private String bloodType;

  private String high;

  private String leaderKey;

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

  private String birthplace;

  private String passportType;

  private String passportNo;

  private String passportPlace;

  private Date passportDate;

  private Date passportExpiry;

  private String photoFile;

  private String hkPass;

  public Guide() {
  }

  public Guide(String accCd, String accNm, String accSex) {
    this.accCd = accCd;
    this.accNm = accNm;
    this.accSex = accSex;
  }

  public String getAccCd() {
    return this.accCd;
  }

  public void setAccCd(String accCd) {
    this.accCd = accCd;
  }

  public String getAccNm() {
    return this.accNm;
  }

  public void setAccNm(String accNm) {
    this.accNm = accNm;
  }

  public String getAccSex() {
    return this.accSex;
  }

  public void setAccSex(String accSex) {
    this.accSex = accSex;
  }

  public String getPinyin() {
    return this.pinyin;
  }

  public void setPinyin(String pinyin) {
    this.pinyin = pinyin;
  }

  public Long getTeamId() {
    return this.teamId;
  }

  public void setTeamId(Long teamId) {
    this.teamId = teamId;
  }

  public String getLeadCard() {
    return this.leadCard;
  }

  public void setLeadCard(String leadCard) {
    this.leadCard = leadCard;
  }

  public String getBusiness() {
    return this.business;
  }

  public void setBusiness(String business) {
    this.business = business;
  }

  public String getBloodType() {
    return this.bloodType;
  }

  public void setBloodType(String bloodType) {
    this.bloodType = bloodType;
  }

  public String getHigh() {
    return this.high;
  }

  public void setHigh(String high) {
    this.high = high;
  }

  public String getLeaderKey() {
    return this.leaderKey;
  }

  public void setLeaderKey(String leadKey) {
    this.leaderKey = leadKey;
  }

  public Date getDateStart() {
    return this.dateStart;
  }

  public void setDateStart(Date dateStart) {
    this.dateStart = dateStart;
  }

  public Date getDateEnd() {
    return this.dateEnd;
  }

  public void setDateEnd(Date dateEnd) {
    this.dateEnd = dateEnd;
  }

  public String getWeight() {
    return this.weight;
  }

  public void setWeight(String weight) {
    this.weight = weight;
  }

  public String getDuty() {
    return this.duty;
  }

  public void setDuty(String duty) {
    this.duty = duty;
  }

  public String getTourKey() {
    return this.tourKey;
  }

  public void setTourKey(String tourKey) {
    this.tourKey = tourKey;
  }

  public String getTourCard() {
    return this.tourCard;
  }

  public void setTourCard(String tourCard) {
    this.tourCard = tourCard;
  }

  public Date getCheckDate() {
    return this.checkDate;
  }

  public void setCheckDate(Date checkDate) {
    this.checkDate = checkDate;
  }

  public String getIcCard() {
    return this.icCard;
  }

  public void setIcCard(String icCard) {
    this.icCard = icCard;
  }

  public String getRemarks1() {
    return this.remarks1;
  }

  public void setRemarks1(String remarks1) {
    this.remarks1 = remarks1;
  }

  public String getWorkType1() {
    return this.workType1;
  }

  public void setWorkType1(String workType1) {
    this.workType1 = workType1;
  }

  public String getWorkType2() {
    return this.workType2;
  }

  public void setWorkType2(String workType2) {
    this.workType2 = workType2;
  }

  public String getSignKey() {
    return this.signKey;
  }

  public void setSignKey(String signKey) {
    this.signKey = signKey;
  }

  public String getTel() {
    return this.tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String getBp() {
    return this.bp;
  }

  public void setBp(String bp) {
    this.bp = bp;
  }

  public String getMobile() {
    return this.mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getEMail() {
    return this.EMail;
  }

  public void setEMail(String EMail) {
    this.EMail = EMail;
  }

  public String getAddress() {
    return this.address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getCharacter() {
    return this.character;
  }

  public void setCharacter(String character) {
    this.character = character;
  }

  public String getTaste() {
    return this.taste;
  }

  public void setTaste(String taste) {
    this.taste = taste;
  }

  public String getSpeciality() {
    return this.speciality;
  }

  public void setSpeciality(String speciality) {
    this.speciality = speciality;
  }

  public String getWorkRemark() {
    return this.workRemark;
  }

  public void setWorkRemark(String workRemark) {
    this.workRemark = workRemark;
  }

  public String getFinishSchool() {
    return this.finishSchool;
  }

  public void setFinishSchool(String finishSchool) {
    this.finishSchool = finishSchool;
  }

  public Date getFinishDate() {
    return this.finishDate;
  }

  public void setFinishDate(Date finishDate) {
    this.finishDate = finishDate;
  }

  public Date getBirthday() {
    return this.birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public String getBirthplace() {
    return this.birthplace;
  }

  public void setBirthplace(String birthplace) {
    this.birthplace = birthplace;
  }

  public String getPassportType() {
    return this.passportType;
  }

  public void setPassportType(String type) {
    this.passportType = type;
  }

  public String getPassportNo() {
    return this.passportNo;
  }

  public void setPassportNo(String passportNo) {
    this.passportNo = passportNo;
  }

  public String getPassportPlace() {
    return this.passportPlace;
  }

  public void setPassportPlace(String hzadd) {
    this.passportPlace = hzadd;
  }

  public Date getPassportDate() {
    return this.passportDate;
  }

  public void setPassportDate(Date date) {
    this.passportDate = date;
  }

  public Date getPassportExpiry() {
    return this.passportExpiry;
  }

  public void setPassportExpiry(Date expiry) {
    this.passportExpiry = expiry;
  }

  public String getPhotoFile() {
    return this.photoFile;
  }

  public void setPhotoFile(String photoFile) {
    this.photoFile = photoFile;
  }

  public String getHkPass() {
    return this.hkPass;
  }

  public void setHkPass(String hkPass) {
    this.hkPass = hkPass;
  }

  public String getIdCard() {
    return idCard;
  }

  public void setIdCard(String idCard) {
    this.idCard = idCard;
  }

  @Override
  public String toString() {
    return null;
  }

  @Override
  public boolean equals(Object o) {
    return false;
  }

  @Override
  public int hashCode() {
    return 0;
  }

}
