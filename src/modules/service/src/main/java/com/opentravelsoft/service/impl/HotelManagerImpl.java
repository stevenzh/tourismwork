package com.opentravelsoft.service.impl;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.entity.vacation.Hotel;
import com.opentravelsoft.providers.HotelDao;
import com.opentravelsoft.service.HotelManager;

/**
 * Implementation of UserManager interface.
 * 
 */
@Service("hotelManager")
@WebService(serviceName = "HotelService", endpointInterface = "com.opentravelsoft.service.HotelManager")
public class HotelManagerImpl extends GenericManagerImpl<Hotel, String>
    implements HotelManager {
  
  private HotelDao hotelDao;

  @Autowired
  public void setHotelDao(HotelDao hotelDao) {
    this.dao = hotelDao;
    this.hotelDao = hotelDao;
  }

  @Override
  public Hotel getHotel(String hotelId) {
    return hotelDao.get(hotelId);
  }

  @Override
  public void deleteHotel(String hotelId) {
    hotelDao.remove(hotelId);
  }

  @Override
  public void saveHotel(Hotel hotel) {
    hotelDao.save(hotel);
  }

  @Override
  public List<Hotel> getHotels(String countryNo, String provinceNo,
      String cityNo, String hotelName) {
    return hotelDao.getHotels(countryNo, provinceNo, cityNo, hotelName);
  }

}
