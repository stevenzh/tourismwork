package com.opentravelsoft.entity.vacation;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.opentravelsoft.entity.City;
import static javax.persistence.CascadeType.REFRESH;

/**
 * 酒店
 */
@Entity
@Table(name = "tbl_hotel")
public class Hotel implements java.io.Serializable {

  private static final long serialVersionUID = -8170467127541155748L;

  /** 酒店ID */
  private String hotCd;

  /** 酒店简称 */
  private String hotJc;

  private String hotEn;

  /** 所在城市 */
  private City city;

  /** 联系人 */
  private String cont;
  private String tel;
  private String fax;
  private String email;
  private Byte isDelete;
  private String opUser;
  private Date opDate;
  private String webKey;
  private String hotName;
  private String userName;
  private String level;
  private String locCn;
  private String locEn;
  private String address;
  private String zip;
  private String canton;
  private String location;
  private String mobile;
  private String httpFiler;
  private String note1;
  private String mapFiler1;
  private String note2;
  private String mapFlier2;
  private String mapFlier3;
  private String region;

  /** 酒店设施 */
  private List<HotelFacility> facilities;

  /** 酒店房型 */
  private List<HotelRoomType> roomTypes;

  public Hotel() {
  }

  @Id
  @Column(name = "HOT_CD", unique = true, nullable = false, length = 10)
  public String getHotCd() {
    return this.hotCd;
  }

  public void setHotCd(String hotCd) {
    this.hotCd = hotCd;
  }

  @Column(name = "HOT_JC", nullable = false, length = 50)
  public String getHotJc() {
    return this.hotJc;
  }

  public void setHotJc(String hotJc) {
    this.hotJc = hotJc;
  }

  @Column(name = "HOT_EN", nullable = false, length = 60)
  public String getHotEn() {
    return this.hotEn;
  }

  public void setHotEn(String hotEn) {
    this.hotEn = hotEn;
  }

  @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
  @JoinColumn(name = "CITYCD")
  public City getCity() {
    return this.city;
  }

  public void setCity(City citycd) {
    this.city = citycd;
  }

  @Column(name = "CONT", length = 30)
  public String getCont() {
    return this.cont;
  }

  public void setCont(String cont) {
    this.cont = cont;
  }

  @Column(name = "TEL", length = 40)
  public String getTel() {
    return this.tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  @Column(name = "FAX", length = 40)
  public String getFax() {
    return this.fax;
  }

  public void setFax(String fax) {
    this.fax = fax;
  }

  @Column(name = "EMAIL", length = 50)
  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Column(name = "IS_DELETE")
  public Byte getIsDelete() {
    return this.isDelete;
  }

  public void setIsDelete(Byte isDelete) {
    this.isDelete = isDelete;
  }

  @Column(name = "OP_USER", length = 20)
  public String getOpUser() {
    return this.opUser;
  }

  public void setOpUser(String opUser) {
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

  @Column(name = "WEB_KEY", length = 20)
  public String getWebKey() {
    return this.webKey;
  }

  public void setWebKey(String webKey) {
    this.webKey = webKey;
  }

  @Column(name = "HOT_NAME", length = 40)
  public String getHotName() {
    return this.hotName;
  }

  public void setHotName(String hotName) {
    this.hotName = hotName;
  }

  @Column(name = "USER_NAME", length = 2)
  public String getUserName() {
    return this.userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  @Column(name = "LEVEL", length = 50)
  public String getLevel() {
    return this.level;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  @Column(name = "LOC_CN", length = 100)
  public String getLocCn() {
    return this.locCn;
  }

  public void setLocCn(String locCn) {
    this.locCn = locCn;
  }

  @Column(name = "LOC_EN", length = 200)
  public String getLocEn() {
    return this.locEn;
  }

  public void setLocEn(String locEn) {
    this.locEn = locEn;
  }

  @Column(name = "ADDRESS", length = 200)
  public String getAddress() {
    return this.address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Column(name = "ZIP", length = 12)
  public String getZip() {
    return this.zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  @Column(name = "CANTON", length = 26)
  public String getCanton() {
    return this.canton;
  }

  public void setCanton(String canton) {
    this.canton = canton;
  }

  @Column(name = "LOCATION", length = 100)
  public String getLocation() {
    return this.location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  @Column(name = "MOBILE", length = 50)
  public String getMobile() {
    return this.mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  @Column(name = "HTTP_FILER", length = 50)
  public String getHttpFiler() {
    return this.httpFiler;
  }

  public void setHttpFiler(String httpFiler) {
    this.httpFiler = httpFiler;
  }

  @Column(name = "NOTE1", length = 400)
  public String getNote1() {
    return this.note1;
  }

  public void setNote1(String note1) {
    this.note1 = note1;
  }

  @Column(name = "MAP_FILER1", length = 50)
  public String getMapFiler1() {
    return this.mapFiler1;
  }

  public void setMapFiler1(String mapFiler1) {
    this.mapFiler1 = mapFiler1;
  }

  @Column(name = "NOTE2")
  public String getNote2() {
    return this.note2;
  }

  public void setNote2(String note2) {
    this.note2 = note2;
  }

  @Column(name = "MAP_FLIER2", length = 50)
  public String getMapFlier2() {
    return this.mapFlier2;
  }

  public void setMapFlier2(String mapFlier2) {
    this.mapFlier2 = mapFlier2;
  }

  @Column(name = "MAP_FLIER3", length = 50)
  public String getMapFlier3() {
    return this.mapFlier3;
  }

  public void setMapFlier3(String mapFlier3) {
    this.mapFlier3 = mapFlier3;
  }

  @Column(name = "REGION", length = 50)
  public String getRegion() {
    return this.region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  @OneToMany(mappedBy = "hotel", cascade = REFRESH)
  public List<HotelFacility> getFacilities() {
    return facilities;
  }

  public void setFacilities(List<HotelFacility> facilities) {
    this.facilities = facilities;
  }

  @OneToMany(mappedBy = "hotel", cascade = REFRESH)
  public List<HotelRoomType> getRoomTypes() {
    return roomTypes;
  }

  public void setRoomTypes(List<HotelRoomType> roomTypes) {
    this.roomTypes = roomTypes;
  }

}
