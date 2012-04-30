package com.opentravelsoft.action.manage.stat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.service.order.BookingService;

/**
 * 显示客户的订单
 * 
 * @author zhangst
 * 
 */
public class CusDbamtDetailAction extends ManageAction {

  private static final long serialVersionUID = 1L;

  @Autowired
  private BookingService bookingService;

  private List<Booking> bookList = new ArrayList<Booking>();

  // -------------------------------------------------------------------------
  // 查询条件
  private int customerId;

  private String kenProvinceId;

  private String kenSales;

  private Date startDate;

  private Date endDate;

  // -------------------------------------------------------------------------
  private int totalbatch;

  private int totalpax;

  private double totalDbamt;

  private double totalCramt;

  private double totalUnpay;

  public String get() {
    bookList = bookingService.roGetBooksByCustomer(customerId, kenSales,
        startDate, endDate);
    Booking book;
    for (int i = 0; i < bookList.size(); i++) {
      book = bookList.get(i);
      bookList.set(i, book);
      totalpax += book.getPax();
      totalDbamt += book.getDbamt();
      totalCramt += book.getPayCosts();
      totalUnpay = totalDbamt - totalCramt;
    }
    return SUCCESS;
  }

  public int getCustomerId() {
    return customerId;
  }

  public void setCustomerId(int customerId) {
    this.customerId = customerId;
  }

  public List<Booking> getBookList() {
    return bookList;
  }

  public void setBookList(List<Booking> bookList) {
    this.bookList = bookList;
  }

  public String getKenProvinceId() {
    return kenProvinceId;
  }

  public void setKenProvinceId(String kenProvinceId) {
    this.kenProvinceId = kenProvinceId;
  }

  public String getKenSales() {
    return kenSales;
  }

  public void setKenSales(String kenSales) {
    this.kenSales = kenSales;
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

  public int getTotalbatch() {
    return totalbatch;
  }

  public void setTotalbatch(int totalbatch) {
    this.totalbatch = totalbatch;
  }

  public int getTotalpax() {
    return totalpax;
  }

  public void setTotalpax(int totalpax) {
    this.totalpax = totalpax;
  }

  public double getTotalDbamt() {
    return totalDbamt;
  }

  public void setTotalDbamt(double totalDbamt) {
    this.totalDbamt = totalDbamt;
  }

  public double getTotalCramt() {
    return totalCramt;
  }

  public void setTotalCramt(double totalCramt) {
    this.totalCramt = totalCramt;
  }

  public double getTotalUnpay() {
    return totalUnpay;
  }

  public void setTotalUnpay(double totalUnpay) {
    this.totalUnpay = totalUnpay;
  }

}
