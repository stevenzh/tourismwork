package com.opentravelsoft.providers;

import java.util.List;

import com.opentravelsoft.entity.Country;

public interface CountryDao extends GenericDao<Country, String> {
  public List<Country> getAllCountry();

  public List<Country> getCountry();

  public List<Country> getVisaCountry();

}
