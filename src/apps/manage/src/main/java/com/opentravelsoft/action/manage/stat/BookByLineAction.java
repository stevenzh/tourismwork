package com.opentravelsoft.action.manage.stat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.service.order.BookingService;

public class BookByLineAction extends ManageAction {

  private static final long serialVersionUID = -8777085632387927815L;

  private BookingService bookingService;

  private List<Booking> bookList = new ArrayList<Booking>();

  private Date startDate;

  private Date endDate;

  private String lineNo;

  public String getbyline() {
    bookList = bookingService.getBookByLineNo(lineNo, startDate, endDate);
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

  public String getRouteNo() {
    return lineNo;
  }

  public void setRouteNo(String lineNo) {
    this.lineNo = lineNo;
  }

}
