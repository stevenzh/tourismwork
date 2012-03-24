package com.opentravelsoft.action.manage.stat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.service.order.BookingService;

public class BookByDistrictDetailAction extends ManageAction {

  private static final long serialVersionUID = -3990803412577254366L;

  private BookingService bookingService;

  private List<Booking> bookList = new ArrayList<Booking>();

  private List<Plan> routeList = new ArrayList<Plan>();

  private Date startDate;

  private Date endDate;

  private String districtNo;

  public String get() {
    routeList = bookingService.roGetRouteByDis(districtNo, startDate, endDate);
    return SUCCESS;
  }

  @Autowired
  public void setBookingService(BookingService bookingService) {
    this.bookingService = bookingService;
  }

  public List<Booking> getBookList() {
    return bookList;
  }

  public void setBookList(List<Booking> bookList) {
    this.bookList = bookList;
  }

  public String getDistrictNo() {
    return districtNo;
  }

  public void setDistrictNo(String districtNo) {
    this.districtNo = districtNo;
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

  public List<Plan> getRouteList() {
    return routeList;
  }
}
