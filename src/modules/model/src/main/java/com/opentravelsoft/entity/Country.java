package com.opentravelsoft.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * 国家
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:32 $
 */
@Entity
@Table(name = "tbl_country")
public class Country extends BaseObject {

  private static final long serialVersionUID = 7448412839346927911L;

  /** 国家两字码 */
  private String countryId;
  private String name;
  private boolean enabled;
  // private Set<District> tblDistricts = new HashSet<District>(0);
  // private Set<Sight> tblSights = new HashSet<Sight>(0);
  private Set<City> tblCities = new HashSet<City>(0);

  public Country() {
  }

  public Country(String countryId) {
    this.countryId = countryId;
  }

  public Country(String countryId, String name, boolean enabled) {
    this.countryId = countryId;
    this.name = name;
    this.enabled = enabled;
  }

  @Id
  @Column(name = "COUNTRY_ID", unique = true, nullable = false, length = 2)
  public String getCountryId() {
    return this.countryId;
  }

  public void setCountryId(String countryId) {
    this.countryId = countryId;
  }

  @Column(name = "NAME", nullable = false, length = 150)
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(name = "ENABLED", nullable = false)
  public boolean getEnabled() {
    return this.enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  // @OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
  // public Set<District> getDistricts() {
  // return this.tblDistricts;
  // }
  //
  // public void setDistricts(Set<District> tblDistricts) {
  // this.tblDistricts = tblDistricts;
  // }
  //
  // @OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
  // public Set<Sight> getSights() {
  // return this.tblSights;
  // }
  //
  // public void setSights(Set<Sight> tblSights) {
  // this.tblSights = tblSights;
  // }

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
  public Set<City> getCities() {
    return this.tblCities;
  }

  public void setCities(Set<City> tblCities) {
    this.tblCities = tblCities;
  }

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
