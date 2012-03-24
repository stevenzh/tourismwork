package com.opentravelsoft.service.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.entity.City;
import com.opentravelsoft.providers.CityDao;
import com.opentravelsoft.service.impl.GenericManagerImpl;

/**
 * Implementation of UserManager interface.
 * 
 */
@Service("CityService")
public class CityServiceImpl extends GenericManagerImpl<City, String> implements
    CityService {

  private CityDao cityDao;

  @Autowired
  public void setCityDao(CityDao cityDao) {
    this.dao = cityDao;
    this.cityDao = cityDao;
  }

  @Override
  public List<City> getAllCity() {
    return cityDao.getAllCity();
  }

  public List<City> roGetCitysByProvince(String provinceId) {
    return cityDao.getCitysByProvince(provinceId);
  }

  public List<City> getInlandCity() {
    return cityDao.getCitysByNation("CN");
  }

  public List<City> roGetCitys() {
    return cityDao.getAllCity();
  }

  public List<City> roGetCitysByCountry(String countryId) {
    return cityDao.getCitysByNation(countryId);
  }

  public List<City> roGetCitys(String nationCode) {
    return cityDao.getCitysByNation(nationCode);
  }
}
