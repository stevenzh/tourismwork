package com.opentravelsoft.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.opentravelsoft.model.BaseObject;

/**
 * 城市
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:33 $
 */
@Entity
@Table(name = "tbl_city")
public class City extends BaseObject {

  private static final long serialVersionUID = -8804934097042527415L;

  /** 城市编码 */
  private String citycd;
  private Country country;

  /** 城市名称 */
  private String citynm;
  private String cityen;
  // private String province;
  private Province province;
  /** 入境城市 */
  private Character inOut;
  /** 出境城市 */

  private Character ioCity;
  private char webKey;

  private short isDelete;

  // private Set<Line> tblLines = new HashSet<Line>(0);
  // private Set<Plan> tblPlans = new HashSet<Plan>(0);
  // private Set<Airport> tblAirports = new HashSet<Airport>(0);

  public City() {
    citycd = "";
    citynm = "";
    country = new Country();
    isDelete = 0;
    // province = new Province();

  }

  @Id
  @Column(name = "CITYCD", unique = true, nullable = false, length = 4)
  public String getCitycd() {
    return this.citycd;
  }

  public void setCitycd(String citycd) {
    this.citycd = citycd;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "COUNTRY")
  public Country getCountry() {
    return this.country;
  }

  public void setCountry(Country tblCountry) {
    this.country = tblCountry;
  }

  @Column(name = "CITYNM", nullable = false, length = 20)
  public String getCitynm() {
    return this.citynm;
  }

  public void setCitynm(String citynm) {
    this.citynm = citynm;
  }

  @Column(name = "CITYEN", length = 20)
  public String getCityen() {
    return this.cityen;
  }

  public void setCityen(String cityen) {
    this.cityen = cityen;
  }

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "PROVINCE")
  public Province getProvince() {
    return this.province;
  }

  public void setProvince(Province province) {
    this.province = province;
  }

  @Column(name = "IN_OUT", length = 1)
  public Character getInOut() {
    return this.inOut;
  }

  public void setInOut(Character inOut) {
    this.inOut = inOut;
  }

  @Column(name = "IO_CITY", length = 1)
  public Character getIoCity() {
    return this.ioCity;
  }

  public void setIoCity(Character ioCity) {
    this.ioCity = ioCity;
  }

  @Column(name = "WEB_KEY", nullable = false, length = 1)
  public char getWebKey() {
    return this.webKey;
  }

  public void setWebKey(char webKey) {
    this.webKey = webKey;
  }

  @Column(name = "IS_DELETE", nullable = false)
  public short getIsDelete() {
    return this.isDelete;
  }

  public void setIsDelete(short isDelete) {
    this.isDelete = isDelete;
  }

  // @OneToMany(fetch = FetchType.LAZY, mappedBy = "city")
  // public Set<Line> getLines() {
  // return this.tblLines;
  // }
  //
  // public void setLines(Set<Line> tblLines) {
  // this.tblLines = tblLines;
  // }
  //
  // @OneToMany(fetch = FetchType.LAZY, mappedBy = "city")
  // public Set<Plan> getPlans() {
  // return this.tblPlans;
  // }
  //
  // public void setPlans(Set<Plan> tblPlans) {
  // this.tblPlans = tblPlans;
  // }
  //
  // @OneToMany(fetch = FetchType.LAZY, mappedBy = "city")
  // public Set<Airport> getAirports() {
  // return this.tblAirports;
  // }
  //
  // public void setAirports(Set<Airport> tblAirports) {
  // this.tblAirports = tblAirports;
  // }

  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean equals(Object o) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public int hashCode() {
    // TODO Auto-generated method stub
    return 0;
  }
}
