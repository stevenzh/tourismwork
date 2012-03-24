package com.opentravelsoft.service.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.entity.Country;
import com.opentravelsoft.providers.CountryDao;
import com.opentravelsoft.service.impl.GenericManagerImpl;

/**
 * Implementation of UserManager interface.
 * 
 */
@Service("CountryService")
public class CountryServiceImpl extends GenericManagerImpl<Country, String>
    implements CountryService {

  private CountryDao countryDao;

  @Autowired
  public void setCountryDao(CountryDao countryDao) {
    this.dao = countryDao;
    this.countryDao = countryDao;
  }

  public List<Country> getCountryList() {
    return countryDao.getCountry();
  }

  public List<Country> roGetCountrys() {
    return countryDao.getCountry();
  }

  public List<Country> roGetNations() {
    return countryDao.getCountry();
  }
}
