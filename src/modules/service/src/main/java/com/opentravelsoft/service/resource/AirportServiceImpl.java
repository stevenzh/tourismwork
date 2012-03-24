package com.opentravelsoft.service.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.entity.Airport;
import com.opentravelsoft.entity.City;
import com.opentravelsoft.entity.Country;
import com.opentravelsoft.providers.AirportDao;
import com.opentravelsoft.providers.CityDao;
import com.opentravelsoft.providers.CountryDao;
import com.opentravelsoft.service.impl.GenericManagerImpl;
import com.opentravelsoft.util.PaginationSupport;

@Service("AirportService")
public class AirportServiceImpl extends GenericManagerImpl<Airport, String>
    implements AirportService {

  private AirportDao airportDao;

  private CountryDao countryDao;

  private CityDao cityDao;

  @Autowired
  public void setAirportDao(AirportDao airportDao) {
    this.dao = airportDao;
    this.airportDao = airportDao;
  }

  @Autowired
  public void setCityDao(CityDao cityDao) {
    this.cityDao = cityDao;
  }

  @Autowired
  public void setCountryDao(CountryDao countryDao) {
    this.countryDao = countryDao;
  }

  public List<Country> roGetCountry() {
    return countryDao.getCountry();
  }

  public List<City> roGetCity(String kenCountryId) {
    return cityDao.getCitysByNation(kenCountryId);
  }

  public int txDelete(Airport airways) {
    return airportDao.delete(airways);
  }

  public PaginationSupport roGetAirportList(String country, String city,
      String delkey, int fromRecord, int pageSize) {
    return airportDao.getAirportList(country, city, delkey, fromRecord,
        pageSize);
  }

  public List<Airport> getAirportList(boolean isActive) {
    return airportDao.getAirportList(isActive);
  }
}
