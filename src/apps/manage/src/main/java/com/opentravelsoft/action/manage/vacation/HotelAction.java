package com.opentravelsoft.action.manage.vacation;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Country;
import com.opentravelsoft.entity.Province;
import com.opentravelsoft.entity.vacation.Hotel;
import com.opentravelsoft.service.HotelManager;
import com.opentravelsoft.service.resource.CountryService;
import com.opentravelsoft.service.resource.ProvinceService;
import com.opentravelsoft.util.StringUtil;

public class HotelAction extends ManageAction {

  private static final long serialVersionUID = 4371256819868403223L;

  protected static final Log logger = LogFactory.getLog(HotelAction.class);

  @Autowired
  private HotelManager hotelManager;

  @Autowired
  private CountryService countryService;

  @Autowired
  private ProvinceService provinceService;

  // --------------------------------------------------------------------------
  private String countryNo;

  private String provinceNo;

  private String cityNo;

  private String hotelName;

  // --------------------------------------------------------------------------
  private String hotelId;

  private List<Country> countryList;

  private List<Province> provinceList;

  private List<Hotel> hotelList;

  private Hotel hotel = new Hotel();

  public String input() {
    countryList = countryService.getAll();
    provinceList = provinceService.getAll();
    return INPUT;
  }

  public String execute() {
    countryList = countryService.getAll();
    provinceList = provinceService.getAll();
    hotelList = hotelManager
        .getHotels(countryNo, provinceNo, cityNo, hotelName);
    return SUCCESS;
  }

  public String edit() {
    hotel = hotelManager.getHotel(hotelId);
    countryList = countryService.getAll();
    provinceList = provinceService.getAll();
    return INPUT;
  }

  public String delete() {
    if (StringUtil.hasLength(hotelId))
      hotelManager.deleteHotel(hotelId);
    return SUCCESS;
  }

  public String save() {
    hotelManager.saveHotel(hotel);
    return SUCCESS;
  }

  public String getHotelId() {
    return hotelId;
  }

  public void setHotelId(String hotelId) {
    this.hotelId = hotelId;
  }

  public Hotel getHotel() {
    return hotel;
  }

  public void setHotel(Hotel hotel) {
    this.hotel = hotel;
  }

  public List<Hotel> getHotelList() {
    return hotelList;
  }

  public List<Country> getCountryList() {
    return countryList;
  }

  public String getProvinceNo() {
    return provinceNo;
  }

  public void setProvinceNo(String provinceNo) {
    this.provinceNo = provinceNo;
  }

  public List<Province> getProvinceList() {
    return provinceList;
  }

  public String getCountryNo() {
    return countryNo;
  }

  public void setCountryNo(String countryNo) {
    this.countryNo = countryNo;
  }

  public String getCityNo() {
    return cityNo;
  }

  public void setCityNo(String cityNo) {
    this.cityNo = cityNo;
  }

}