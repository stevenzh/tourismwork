package com.opentravelsoft.action.manage.finance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.City;
import com.opentravelsoft.entity.Province;
import com.opentravelsoft.entity.finance.Income;
import com.opentravelsoft.service.resource.CityService;
import com.opentravelsoft.service.resource.ProvinceService;

/**
 * 客户对账单（应收/应付）
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 */
public class CollateIncomeAction extends ManageAction {
  private static final long serialVersionUID = 2405110448984190948L;

  @Autowired
  private ProvinceService provinceService;

  @Autowired
  private CityService cityService;

  private List<Province> provinceList;

  private List<City> cityList;

  private List<Income> incomeList;

  // -------------------------------------------------------------------------
  // 查询条件
  private String kenProvince;

  private String kenCity;

  private String kenMonth;

  // -------------------------------------------------------------------------

  public String input() {
    provinceList = provinceService.getAllProvince();
    cityList = cityService.getAllCity();
    return INPUT;
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

  public List<Income> getIncomeList() {
    return incomeList;
  }

  public void setIncomeList(List<Income> incomeList) {
    this.incomeList = incomeList;
  }

  public String getKenMonth() {
    return kenMonth;
  }

  public void setKenMonth(String kenMonth) {
    this.kenMonth = kenMonth;
  }

}
