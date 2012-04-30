package com.opentravelsoft.action.manage.finance;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.City;
import com.opentravelsoft.entity.Province;
import com.opentravelsoft.service.finance.IncomeService;
import com.opentravelsoft.service.resource.CityService;
import com.opentravelsoft.service.resource.ProvinceService;

/**
 * 应收账款查询
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:06 $
 */
public class SearchIncomeAction extends ManageAction {

  private static final long serialVersionUID = -691156950129863506L;

  @Autowired
  private IncomeService incomeService;

  @Autowired
  private ProvinceService provinceService;

  @Autowired
  private CityService cityService;

  private List<Province> provinceList;

  private List<City> cityList;

  private List<Booking> bookingList = new ArrayList<Booking>();

  private String name;

  // 合计(对应地区) ---------------------
  private int totalpax;

  private double totalDbamt;

  private double totalCramt;

  private double totalUnpay;

  // 查询条件 ---------------------------
  private String kenProvince;

  private String kenCity;

  private int customerId;

  private Date stDate;

  private Date endDate;

  public String input() {
    provinceList = provinceService.getAllProvince();
    cityList = cityService.roGetCitysByProvince(kenProvince);
    return INPUT;
  }

  public String search() {
    provinceList = provinceService.getAllProvince();
    cityList = cityService.roGetCitysByProvince(kenProvince);
    bookingList = incomeService.roSearchIncome(kenProvince, kenCity,
        customerId, stDate, endDate);
    for (int i = 0; i < bookingList.size(); i++) {
      Booking income = bookingList.get(i);
      bookingList.set(i, income);
      totalpax += income.getPax();
      totalDbamt += income.getDbamt();
      totalCramt += income.getCramt();
      totalUnpay = totalDbamt - totalCramt;
    }
    return SUCCESS;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public List<Province> getProvinceList() {
    return provinceList;
  }

  public void setProvinceList(List<Province> provinceList) {
    this.provinceList = provinceList;
  }

  public List<City> getCityList() {
    return cityList;
  }

  public void setCityList(List<City> cityList) {
    this.cityList = cityList;
  }

  public String getKenProvince() {
    return kenProvince;
  }

  public void setKenProvince(String kenProvince) {
    this.kenProvince = kenProvince;
  }

  public String getKenCity() {
    return kenCity;
  }

  public void setKenCity(String kenCity) {
    this.kenCity = kenCity;
  }

  public Date getStDate() {
    return stDate;
  }

  public void setStDate(Date stDate) {
    this.stDate = stDate;
  }

  public List<Booking> getIncomeList() {
    return bookingList;
  }

  public void setIncomeList(List<Booking> incomeList) {
    this.bookingList = incomeList;
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

  public int getCustomerId() {
    return customerId;
  }

  public void setCustomerId(int customerId) {
    this.customerId = customerId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
