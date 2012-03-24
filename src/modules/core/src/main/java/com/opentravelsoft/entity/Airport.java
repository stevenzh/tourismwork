package com.opentravelsoft.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.opentravelsoft.model.BaseObject;

/**
 * 航空机场
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:33 $
 */
public class Airport extends BaseObject {

  private static final long serialVersionUID = 6629525309631804337L;

  /** 机场三字码 */
  private String code;

  /** 机场中文名 */
  private String name;

  /** 机场英文名 */
  private String enName;

  private City city;

  /** 所在城市 */
  private String cityName;

  private String countryId;

  /** 所在国家 */
  private String countryName;

  private boolean isActive;

  public Airport() {
    isActive = true;
    city = new City();
  }

  public String getName() {
    return name;
  }

  public void setName(String cnName) {
    this.name = cnName;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }

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

  public String getEnName() {
    return enName;
  }

  public void setEnName(String enName) {
    this.enName = enName;
  }

  public String getCountryId() {
    return countryId;
  }

  public void setCountryId(String countryCode) {
    this.countryId = countryCode;
  }

  public boolean getIsActive() {
    return isActive;
  }

  public void setIsActive(boolean isActive) {
    this.isActive = isActive;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Airport)) {
      return false;
    }

    final Airport airport = (Airport) o;
    return !(code != null ? !code.equals(airport.getCode())
        : airport.getCode() != null);
  }

  @Override
  public int hashCode() {
    int result;
    result = (code != null ? code.hashCode() : 0);
    result = 29 * result + (name != null ? name.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("code", this.code)
        .append("name", this.name)
        .append("city", this.city.getCitynm())
        .append("isActive", this.isActive).toString();
  }

}
