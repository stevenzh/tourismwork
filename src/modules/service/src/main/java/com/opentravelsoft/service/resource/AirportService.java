package com.opentravelsoft.service.resource;

import java.util.List;

import com.opentravelsoft.entity.Airport;
import com.opentravelsoft.entity.City;
import com.opentravelsoft.entity.Country;
import com.opentravelsoft.service.GenericManager;
import com.opentravelsoft.util.PaginationSupport;

public interface AirportService extends GenericManager<Airport, String> {

  public List<Country> roGetCountry();

  public List<City> roGetCity(String kenCountryId);

  public int txDelete(Airport airways);

  public PaginationSupport roGetAirportList(String country, String city,
      String delkey, long fromRecord, int pageSize);

  List<Airport> getAirportList(boolean isActive);
}
