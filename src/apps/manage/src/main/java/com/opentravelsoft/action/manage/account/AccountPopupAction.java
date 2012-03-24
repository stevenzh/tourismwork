package com.opentravelsoft.action.manage.account;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.City;
import com.opentravelsoft.entity.Customer;
import com.opentravelsoft.entity.Province;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.service.account.CustomerService;
import com.opentravelsoft.service.resource.CityService;
import com.opentravelsoft.service.resource.ProvinceService;
import com.opentravelsoft.util.StringUtil;

/**
 * 客户查询弹出框
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1.2.1 $ $Date: 2009/06/29 06:17:40 $
 */
public class AccountPopupAction extends ManageAction {
  private static final long serialVersionUID = 7671898914387730451L;

  private CustomerService agentService;

  private ProvinceService provinceService;

  private CityService cityService;

  // -------------------------------------------------------------------------
  // 检索条件

  /** 省份 */
  private String kenProvince = "";

  /** 城市 */
  private String kenCity = "";

  /** 名称 */
  private String kenName = "";

  private String customerCode;

  /** 销售员ID */
  private String salesId;

  // -------------------------------------------------------------------------

  private Customer agent = new Customer();

  private List<Customer> agentList;

  private List<City> cityList;

  private List<Province> provinceList;

  private List<Employee> saleList = new ArrayList<Employee>();

  @Autowired
  public void setProvinceService(ProvinceService provinceService) {
    this.provinceService = provinceService;
  }

  @Autowired
  public void setCityService(CityService cityService) {
    this.cityService = cityService;
  }

  @Override
  public String input() {
    provinceList = provinceService.getAllProvince();
    cityList = cityService.getInlandCity();
    saleList = agentService.roGetSalesmanList();

    return INPUT;
  }

  public String submit() {
    agentList = agentService.getAgent("", kenProvince, kenCity, kenName, "",
        "", Integer.parseInt(salesId), customerCode, 0, "A");

    provinceList = provinceService.getAllProvince();
    saleList = agentService.roGetSalesmanList();

    if (StringUtil.hasLength(kenProvince))
      cityList = cityService.roGetCitysByProvince(kenProvince);
    else
      cityList = cityService.getInlandCity();

    currentPage(agentList.size());

    return SUCCESS;
  }

  @Override
  protected int getMoveCount() {
    return 10;
  }

  @Autowired
  public void setAgentService(CustomerService agentService) {
    this.agentService = agentService;
  }

  public List<Customer> getAgentList() {
    return agentList;
  }

  public List<City> getCityList() {
    return cityList;
  }

  public String getKenCity() {
    return kenCity;
  }

  public void setKenCity(String kenCity) {
    this.kenCity = kenCity;
  }

  public String getKenName() {
    return kenName;
  }

  public void setKenName(String kenName) {
    this.kenName = kenName;
  }

  public String getKenProvince() {
    return kenProvince;
  }

  public void setKenProvince(String kenProvince) {
    this.kenProvince = kenProvince;
  }

  public List<Province> getProvinceList() {
    return provinceList;
  }

  public Customer getAgent() {
    return agent;
  }

  public void setAgent(Customer agent) {
    this.agent = agent;
  }

  public List<Employee> getSaleList() {
    return saleList;
  }

  public String getSalesId() {
    return salesId;
  }

  public void setSalesId(String salesId) {
    this.salesId = salesId;
  }

  public String getCustomerCode() {
    return customerCode;
  }

  public void setCustomerCode(String customerCode) {
    this.customerCode = customerCode;
  }

}
