package com.opentravelsoft.providers;

import java.util.List;

import com.opentravelsoft.entity.vacation.Hotel;

public interface HotelDao extends GenericDao<Hotel, String> {

  List<Hotel> getHotels(String countryNo, String provinceNo, String cityNo,
      String hotelName);
}
