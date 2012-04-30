package com.opentravelsoft.action.manage.stat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.City;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Province;
import com.opentravelsoft.service.order.BookingService;
import com.opentravelsoft.service.resource.CityService;
import com.opentravelsoft.service.resource.ProvinceService;
import com.opentravelsoft.service.setting.EmployeeService;

/**
 * 按地区统计销售情况
 * 
 * @author zhangst
 * 
 */
public class DbamtForCusAction extends ManageAction {
  private static final long serialVersionUID = 1L;

  @Autowired
  private BookingService bookingService;

  @Autowired
  private ProvinceService provinceService;

  @Autowired
  private CityService cityService;

  @Autowired
  private EmployeeService employeeService;

  private Booking book = new Booking();

  private List<Province> provinceList;

  private List<City> cityList;

  private List<LabelValueBean> payment = new ArrayList<LabelValueBean>();

  private List<Booking> bookList = new ArrayList<Booking>();

  /** 销售员 */
  private List<Employee> salesmans = new ArrayList<Employee>();

  /**
   * 出发日期
   */
  private Date startDate;

  private Date endDate;

  /**
   * 省份
   */
  private String kenProvinceId;

  private String kenSales;

  private String pay;

  private String kenCity;

  private int customerId;

  private int totalbatch;

  private int totalpax;

  private double totalDbamt;

  private double totalCramt;

  private double totalUnpay;

  public String init() {
    provinceList = provinceService.getAllProvince();
    cityList = cityService.getInlandCity();
    salesmans = employeeService.roGetSalesList();
    payment = getCodeList("ebiz_clearing_cycle");
    return SUCCESS;
  }

  public String submit() {
    bookList = bookingService.roFindCustomerDbamt(kenProvinceId, kenSales,
        startDate, endDate, pay, kenCity);
    Booking book;
    for (int i = 0; i < bookList.size(); i++) {
      book = bookList.get(i);
      bookList.set(i, book);
      totalbatch += book.getBatch();
      totalpax += book.getSumpax();
      totalDbamt += book.getSumDbamt();
      totalCramt += book.getSumCramt();
      totalUnpay = totalDbamt - totalCramt;
    }
    return SUCCESS;
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

  public int getCustomerId() {
    return customerId;
  }

  public void setCustomerId(int customerId) {
    this.customerId = customerId;
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

  public String getKenProvinceId() {
    return kenProvinceId;
  }

  public void setKenProvinceId(String kenProvinceId) {
    this.kenProvinceId = kenProvinceId;
  }

  public List<Province> getProvinceList() {
    return provinceList;
  }

  public void setProvinceList(List<Province> provinceList) {
    this.provinceList = provinceList;
  }

  public List<Booking> getBookList() {
    return bookList;
  }

  public void setBookList(List<Booking> bookList) {
    this.bookList = bookList;
  }

  public List<Employee> getSalesmans() {
    return salesmans;
  }

  public String getKenSales() {
    return kenSales;
  }

  public void setKenSales(String kenSales) {
    this.kenSales = kenSales;
  }

  public Booking getBook() {
    return book;
  }

  public void setBook(Booking book) {
    this.book = book;
  }

  public int getTotalpax() {
    return totalpax;
  }

  public void setTotalpax(int totalpax) {
    this.totalpax = totalpax;
  }

  public int getTotalbatch() {
    return totalbatch;
  }

  public void setTotalbatch(int totalbatch) {
    this.totalbatch = totalbatch;
  }

  public List<LabelValueBean> getPayment() {
    return payment;
  }

  public void setPayment(List<LabelValueBean> payment) {
    this.payment = payment;
  }

  public String getPay() {
    return pay;
  }

  public void setPay(String pay) {
    this.pay = pay;
  }

  public List<City> getCityList() {
    return cityList;
  }

  public void setCityList(List<City> cityList) {
    this.cityList = cityList;
  }

  public String getKenCity() {
    return kenCity;
  }

  public void setKenCity(String kenCity) {
    this.kenCity = kenCity;
  }
}
