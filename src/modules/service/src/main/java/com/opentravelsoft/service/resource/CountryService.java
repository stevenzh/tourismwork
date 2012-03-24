package com.opentravelsoft.service.resource;

import java.util.List;

import com.opentravelsoft.entity.Country;
import com.opentravelsoft.service.GenericManager;

public interface CountryService extends GenericManager<Country, String> {
  public List<Country> getCountryList();

  List<Country> roGetCountrys();

  List<Country> roGetNations();

}
