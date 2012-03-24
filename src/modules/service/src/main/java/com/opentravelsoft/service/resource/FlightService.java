package com.opentravelsoft.service.resource;

import java.util.List;

import com.opentravelsoft.entity.City;
import com.opentravelsoft.entity.Country;
import com.opentravelsoft.entity.Flight;
import com.opentravelsoft.util.PaginationSupport;

public interface FlightService {
  List<Flight> roGetFlightList();

  List<Country> roGetCountry();

  List<City> roGetCity(String kenCountryId);

  Flight roGetFlightDetail(String flightNo);

  void txInsert(Flight flight);

  void txDelete(String flightNo);

  void txUpdate(Flight flight);

  PaginationSupport getFlightList(String airewaysNo, String kenLvAirport,
      String kenGoAirport, int fromRecord, int moveCount);

}
