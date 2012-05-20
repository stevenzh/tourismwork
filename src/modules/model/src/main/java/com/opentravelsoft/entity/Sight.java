package com.opentravelsoft.entity;

import com.opentravelsoft.entity.out.TblLineSights;

import java.util.Date;
import java.util.HashSet;
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

@Entity
@Table(name = "tbl_sight")
public class Sight implements java.io.Serializable {
  /** 景点编号 */
  private String sightNo;
  /** 国家/地区 */
  private Country country;
  /** 景区 */
  private District district;
  private String cnName;
  /** 景点英文名称 */
  private String enName;
  /** 景点中文概况 */
  private String cnNote;
  /** 景点英文概况 */
  private String enNote;
  private String mapAddress1;
  private String mapAddress2;
  /** 景点分类 */
  private String sightType;
  private Double clickNumber;
  private Date clickDate;
  private Character districtKey;
  /** 所在省 */
  private Province province;

  private byte isDelete;
  private Set<TblLineSights> tblLineSightses = new HashSet<TblLineSights>(0);

  public Sight() {
    this.district = new District();
    this.country = new Country();
    this.province = new Province();
    this.country.setCountryId("CN");
  }

  public Sight(String sightNo, Country country, byte isDelete) {
    this.sightNo = sightNo;
    this.country = country;
    this.isDelete = isDelete;
  }

  @Id
  @Column(name = "SIGHT_NO", unique = true, nullable = false, length = 10)
  public String getSightNo() {
    return this.sightNo;
  }

  public void setSightNo(String sightNo) {
    this.sightNo = sightNo;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "COUNTRY", nullable = false)
  public Country getCountry() {
    return this.country;
  }

  public void setCountry(Country country) {
    this.country = country;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "DISTRICT_NO")
  public District getDistrict() {
    return this.district;
  }

  public void setDistrict(District district) {
    this.district = district;
  }

  @Column(name = "CN_NAME", length = 30)
  public String getCnName() {
    return this.cnName;
  }

  public void setCnName(String cnName) {
    this.cnName = cnName;
  }

  @Column(name = "EN_NAME", length = 60)
  public String getEnName() {
    return this.enName;
  }

  public void setEnName(String enName) {
    this.enName = enName;
  }

  @Column(name = "CN_NOTE", length = 1500)
  public String getCnNote() {
    return this.cnNote;
  }

  public void setCnNote(String cnNote) {
    this.cnNote = cnNote;
  }

  @Column(name = "EN_NOTE", length = 1500)
  public String getEnNote() {
    return this.enNote;
  }

  public void setEnNote(String enNote) {
    this.enNote = enNote;
  }

  @Column(name = "MAP_ADDRESS1", length = 50)
  public String getMapAddress1() {
    return this.mapAddress1;
  }

  public void setMapAddress1(String mapAddress1) {
    this.mapAddress1 = mapAddress1;
  }

  @Column(name = "MAP_ADDRESS2", length = 50)
  public String getMapAddress2() {
    return this.mapAddress2;
  }

  public void setMapAddress2(String mapAddress2) {
    this.mapAddress2 = mapAddress2;
  }

  @Column(name = "SIGHT_TYPE", length = 10)
  public String getSightType() {
    return this.sightType;
  }

  public void setSightType(String sightType) {
    this.sightType = sightType;
  }

  @Column(name = "CLICK_NUMBER", precision = 22, scale = 0)
  public Double getClickNumber() {
    return this.clickNumber;
  }

  public void setClickNumber(Double clickNumber) {
    this.clickNumber = clickNumber;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "CLICK_DATE", length = 19)
  public Date getClickDate() {
    return this.clickDate;
  }

  public void setClickDate(Date clickDate) {
    this.clickDate = clickDate;
  }

  @Column(name = "DISTRICT_KEY", length = 1)
  public Character getDistrictKey() {
    return this.districtKey;
  }

  public void setDistrictKey(Character districtKey) {
    this.districtKey = districtKey;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "DUCHY")
  public Province getProvince() {
    return province;
  }

  public void setProvince(Province province) {
    this.province = province;
  }

  @Column(name = "IS_DELETE", nullable = false)
  public byte getIsDelete() {
    return this.isDelete;
  }

  public void setIsDelete(byte isDelete) {
    this.isDelete = isDelete;
  }

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "sight")
  public Set<TblLineSights> getTblLineSightses() {
    return this.tblLineSightses;
  }

  public void setTblLineSightses(Set<TblLineSights> tblLineSightses) {
    this.tblLineSightses = tblLineSightses;
  }

  /** 景点中文名称 */
  private String name;
  /** 景点首图文件地址 */
  private String headAdd;

  /** 景点地图文件地址 */
  private String mapAdd;

  /** 景点分类名称 */
  private String sightTypeName;

  /** 城市 */
  private String cityId;

  private String checked;

  public String getSightTypeName() {
    return sightTypeName;
  }

  public void setSightTypeName(String sightTypeName) {
    this.sightTypeName = sightTypeName;
  }

  public String getHeadAdd() {
    return headAdd;
  }

  public void setHeadAdd(String headAdd) {
    this.headAdd = headAdd;
  }

  public String getMapAdd() {
    return mapAdd;
  }

  public void setMapAdd(String mapAdd) {
    this.mapAdd = mapAdd;
  }

  public String getCityId() {
    return cityId;
  }

  public void setCityId(String cityId) {
    this.cityId = cityId;
  }

  public String getChecked() {
    return checked;
  }

  public void setChecked(String checked) {
    this.checked = checked;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String cnName) {
    this.name = cnName;
  }

}