package com.opentravelsoft.service.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.entity.City;
import com.opentravelsoft.entity.Country;
import com.opentravelsoft.entity.Flight;
import com.opentravelsoft.providers.CityDao;
import com.opentravelsoft.providers.CountryDao;
import com.opentravelsoft.providers.FlightDao;
import com.opentravelsoft.util.PaginationSupport;

@Service("FlightService")
public class FlightServiceImpl implements FlightService {
  private CountryDao countryDao;

  private CityDao cityDao;

  private FlightDao flightDao;

  @Autowired
  public void setCountryDao(CountryDao countryDao) {
    this.countryDao = countryDao;
  }

  @Autowired
  public void setCityDao(CityDao cityDao) {
    this.cityDao = cityDao;
  }

  @Autowired
  public void setFlightDao(FlightDao flightDao) {
    this.flightDao = flightDao;
  }

  public void txDelete(String flightNo) {
    flightDao.remove(flightNo);
  }

  public List<City> roGetCity(String kenCountryId) {
    return cityDao.getAllCity();
  }

  public List<Country> roGetCountry() {
    return countryDao.getCountry();
  }

  public Flight roGetFlightDetail(String flightNo) {
    return flightDao.get(flightNo);
  }

  public void txInsert(Flight flight) {
    flightDao.save(flight);
  }

  public void txUpdate(Flight flight) {
    flightDao.save(flight);
  }

  public List<Flight> roGetFlightList() {
    return flightDao.getAll();
  }

  public PaginationSupport getFlightList(String aireways, String lvAirport,
      String goAirport, int fromRecord, int moveCount) {
    return flightDao.getFlightList(aireways, lvAirport, goAirport, fromRecord,
        moveCount);
  }

}
