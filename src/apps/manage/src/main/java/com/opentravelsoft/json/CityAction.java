package com.opentravelsoft.json;

import java.util.ArrayList;
import java.util.List;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.City;
import com.opentravelsoft.providers.CityDao;

public class CityAction extends ManageAction {
  private static final long serialVersionUID = 1447584596315986604L;

  private List<LabelValueBean> map = new ArrayList<LabelValueBean>();

  private CityDao cityDao;

  private String countryId;

  private String provinceId;

  @Autowired
  public void setCityDao(CityDao cityDao) {
    this.cityDao = cityDao;
  }

  public String citysByNation() {
    List<City> list = cityDao.getCitysByNation(countryId);

    for (City customer : list) {
      map.add(new LabelValueBean(customer.getCitycd(), customer.getCitynm()));
    }
    return SUCCESS;
  }

  public String citysByProvince() {
    List<City> list = cityDao.getCitysByProvince(provinceId);
    for (City customer : list) {
      map.add(new LabelValueBean(String.valueOf(customer.getCitycd()), customer
          .getCitynm()));
    }
    return SUCCESS;
  }

  public void setCountryId(String countryId) {
    this.countryId = countryId;
  }

  public void setProvinceId(String provinceId) {
    this.provinceId = provinceId;
  }

  public List<LabelValueBean> getMap() {
    return map;
  }

}
