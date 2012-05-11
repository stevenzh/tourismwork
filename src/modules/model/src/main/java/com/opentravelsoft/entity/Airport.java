package com.opentravelsoft.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_airport")
public class Airport implements java.io.Serializable {

  /** 机场三字码 */
  private String code;
  private City city;
  /** 机场中文名 */
  private String name;
  private String enName;
  private Byte isActive;

  public Airport() {
    isActive = 0;
    city = new City();
  }

  public Airport(String code) {
    this.code = code;
  }

  public Airport(String code, City city, String name, String enName,
      Byte isActive) {
    this.code = code;
    this.city = city;
    this.name = name;
    this.enName = enName;
    this.isActive = isActive;
  }

  @Id
  @Column(name = "CODE", unique = true, nullable = false, length = 3)
  public String getCode() {
    return this.code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "CITY_ID")
  public City getCity() {
    return this.city;
  }

  public void setCity(City city) {
    this.city = city;
  }

  @Column(name = "NAME", length = 20)
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(name = "EN_NAME", length = 50)
  public String getEnName() {
    return this.enName;
  }

  public void setEnName(String enName) {
    this.enName = enName;
  }

  @Column(name = "IS_ACTIVE")
  public Byte getIsActive() {
    return this.isActive;
  }

  public void setIsActive(Byte isActive) {
    this.isActive = isActive;
  }

  /** 所在城市 */
  private String cityName;

  private String countryId;

  /** 所在国家 */
  private String countryName;

  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  public String getCountryName() {
    return countryName;
  }

  public void setCountryName(String countryName) {
    this.countryName = countryName;
  }

  public String getCountryId() {
    return countryId;
  }

  public void setCountryId(String countryCode) {
    this.countryId = countryCode;
  }

}
