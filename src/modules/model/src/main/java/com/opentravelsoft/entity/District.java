package com.opentravelsoft.entity;

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
import javax.persistence.Transient;

@Entity
@Table(name = "tbl_district")
public class District implements java.io.Serializable {
  /** 景区编号 */
  private String districtNo;
  private Country country;
  /** 景区中文名称 */
  private String cnName;
  /** 景区中文概况 */
  private String cnNote;
  private String mapAddress1;
  private String mapAddress2;
  private Province province;

  private Set<Sight> sights = new HashSet<Sight>(0);

  public District() {
    this.country = new Country();
    this.province = new Province();
    this.country.setCountryId("CN");
  }

  public District(String districtNo, Country country, String cnName) {
    this.districtNo = districtNo;
    this.country = country;
    this.cnName = cnName;
  }

  public District(String district) {
    this.districtNo = district;
  }

  @Id
  @Column(name = "DISTRICT_NO", unique = true, nullable = false, length = 10)
  public String getDistrictNo() {
    return this.districtNo;
  }

  public void setDistrictNo(String districtNo) {
    this.districtNo = districtNo;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "COUNTRY", nullable = false)
  public Country getCountry() {
    return this.country;
  }

  public void setCountry(Country country) {
    this.country = country;
  }

  @Column(name = "CN_NAME", nullable = false, length = 30)
  public String getCnName() {
    return this.cnName;
  }

  public void setCnName(String cnName) {
    this.cnName = cnName;
  }

  @Column(name = "CN_NOTE", length = 2000)
  public String getCnNote() {
    return this.cnNote;
  }

  public void setCnNote(String cnNote) {
    this.cnNote = cnNote;
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

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "DUCHY", nullable = true)
  public Province getProvince() {
    return province;
  }

  public void setProvince(Province province) {
    this.province = province;
  }

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "district")
  public Set<Sight> getSights() {
    return this.sights;
  }

  public void setSights(Set<Sight> sights) {
    this.sights = sights;
  }

  /** 景区首图文件地址 */
  private String mapHead;

  /** 景区地图文件地址 */
  private String mapAddress;

  @Transient
  public String getMapAddress() {
    return mapAddress;
  }

  public void setMapAddress(String mapAddress) {
    this.mapAddress = mapAddress;
  }

  @Transient
  public String getMapHead() {
    return mapHead;
  }

  public void setMapHead(String mapHead) {
    this.mapHead = mapHead;
  }

}
