package com.opentravelsoft.service.resource;

import java.util.List;

import com.opentravelsoft.entity.City;
import com.opentravelsoft.service.GenericManager;

public interface CityService extends GenericManager<City, String> {

  public List<City> getAllCity();

  public List<City> roGetCitysByProvince(String kenProvince);

  public List<City> getInlandCity();

  List<City> roGetCitys();

  List<City> roGetCitysByCountry(String kenCountryId);

}
