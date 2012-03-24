package com.opentravelsoft.service;

import java.util.List;

import javax.jws.WebService;

import com.opentravelsoft.entity.vacation.Hotel;

@WebService
public interface HotelManager extends GenericManager<Hotel, String> {

  Hotel getHotel(String hotelId);

  void deleteHotel(String hotelId);

  void saveHotel(Hotel hotel);

  List<Hotel> getHotels(String countryNo, String provinceNo, String cityNo,
      String hotelName);

}