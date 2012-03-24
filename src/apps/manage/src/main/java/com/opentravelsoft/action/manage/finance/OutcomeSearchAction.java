package com.opentravelsoft.action.manage.finance;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.City;
import com.opentravelsoft.entity.Country;
import com.opentravelsoft.entity.TourCost;
import com.opentravelsoft.service.finance.OutcomeService;
import com.opentravelsoft.service.resource.CityService;
import com.opentravelsoft.service.resource.CountryService;
import com.opentravelsoft.util.StringUtil;

/**
 * 应付帐款查询
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:28 $
 */
public class OutcomeSearchAction extends ManageAction {
  private static final long serialVersionUID = -2109168126484132451L;

  private OutcomeService outcomeService;

  private CountryService countryService;
  private CityService cityService;
  private Date startDate;

  private Date endDate;

  /** 供应商类型 */
  private String supplierType;

  private int supplierId;

  private String supplierName;

  private List<LabelValueBean> resourceList;

  private List<Country> countryList;

  private List<City> cityList;

  private List<TourCost> outcomeList;

  private String kenCountryId;

  private String kenCityId;

  @Autowired
  public void setCountryService(CountryService countryService) {
    this.countryService = countryService;
  }

  @Autowired
  public void setCityService(CityService cityService) {
    this.cityService = cityService;
  }

  /**
   * 应付帐款查询初始化
   * 
   * @return
   */
  public String init() {
    buildSysdate();
    endDate = systemDate;
    Calendar cal = Calendar.getInstance();
    cal.setTime(systemDate);
    cal.add(Calendar.MONTH, -1);
    startDate = cal.getTime();

    kenCountryId = "CN";
    countryList = countryService.roGetCountrys();
    if (StringUtil.hasLength(kenCountryId))
      cityList = cityService.roGetCitysByCountry(kenCountryId);
    else
      cityList = cityService.roGetCitys();

    resourceList = getCodeList("ebiz_supplier_resource");
    return INPUT;
  }

  /**
   * 应付帐款查询
   * 
   * @return
   */
  public String search() {
    outcomeList = outcomeService.roGetSupplierOutcomeList(supplierType,
        supplierName, kenCountryId, kenCityId, startDate, endDate);
    countryList = countryService.roGetCountrys();
    if (StringUtil.hasLength(kenCountryId))
      cityList = cityService.roGetCitysByCountry(kenCountryId);
    else
      cityList = cityService.roGetCitys();
    resourceList = getCodeList("ebiz_supplier_resource");

    return SUCCESS;
  }

  @Autowired
  public void setOutcomeService(OutcomeService outcomeService) {
    this.outcomeService = outcomeService;
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

  public String getSupplierType() {
    return supplierType;
  }

  public void setSupplierType(String supplierType) {
    this.supplierType = supplierType;
  }

  public List<LabelValueBean> getResourceList() {
    return resourceList;
  }

  public void setResourceList(List<LabelValueBean> resourceList) {
    this.resourceList = resourceList;
  }

  public List<Country> getCountryList() {
    return countryList;
  }

  public void setCountryList(List<Country> countryList) {
    this.countryList = countryList;
  }

  public List<City> getCityList() {
    return cityList;
  }

  public void setCityList(List<City> cityList) {
    this.cityList = cityList;
  }

  public String getKenCountryId() {
    return kenCountryId;
  }

  public void setKenCountryId(String kenCountryId) {
    this.kenCountryId = kenCountryId;
  }

  public String getKenCityId() {
    return kenCityId;
  }

  public void setKenCityId(String kenCityId) {
    this.kenCityId = kenCityId;
  }

  public List<TourCost> getOutcomeList() {
    return outcomeList;
  }

  public int getSupplierId() {
    return supplierId;
  }

  public void setSupplierId(int supplierId) {
    this.supplierId = supplierId;
  }

  public String getSupplierName() {
    return supplierName;
  }

  public void setSupplierName(String supplierName) {
    this.supplierName = supplierName;
  }

}
