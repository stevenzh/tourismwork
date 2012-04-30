package com.opentravelsoft.action.manage.stat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.Country;
import com.opentravelsoft.service.order.BookingService;

public class DistrictStatAction extends ManageAction {

  private static final long serialVersionUID = 5517507976456680520L;

  @Autowired
  private BookingService bookingService;

  private List<Booking> bookList = new ArrayList<Booking>();

  private List<Country> countryList = new ArrayList<Country>();

  private Date startDate;

  private Date endDate;

  private String country;

  public List<Booking> getBookList() {
    return bookList;
  }

  public void setBookList(List<Booking> bookList) {
    this.bookList = bookList;
  }

  public String init() {
    countryList = bookingService.roGetCountry();
    return SUCCESS;
  }

  public String submit() {
    bookList = bookingService.roDistrictStat(startDate, endDate, country);
    return SUCCESS;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public List<Country> getCountryList() {
    return countryList;
  }

  public void setCountryList(List<Country> countryList) {
    this.countryList = countryList;
  }
}
