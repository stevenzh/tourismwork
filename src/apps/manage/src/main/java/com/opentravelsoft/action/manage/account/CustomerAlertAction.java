package com.opentravelsoft.action.manage.account;

import java.util.ArrayList;
import java.util.List;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.City;
import com.opentravelsoft.entity.Country;
import com.opentravelsoft.entity.Customer;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.entity.Province;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.service.account.CustomerService;
import com.opentravelsoft.service.resource.CityService;
import com.opentravelsoft.service.resource.CountryService;
import com.opentravelsoft.service.resource.ProvinceService;
import com.opentravelsoft.service.setting.EmployeeService;

/**
 * 客户审核预警
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 */
public class CustomerAlertAction extends ManageAction {

  private static final long serialVersionUID = 3324646809556120903L;

  @Autowired
  private CustomerService agentService;

  @Autowired
  private EmployeeService salesmanService;

  @Autowired
  private ProvinceService provinceService;

  @Autowired
  private CountryService countryService;

  @Autowired
  private CityService cityService;

  private Customer agent = new Customer();

  /** 代理商编码 */
  private int agentId;

  // -------------------------------------------------------------------------
  // 检索条件
  /** 省份 */
  private String kenCountryId = "";

  /** 省份 */
  private String kenProvince = "";

  /** 城市 */
  private String kenCity = "";

  /** 名称 */
  private String kenName = "";

  private String kenClearingCycle = "";

  private String customerCode;

  private int teamId;

  /** 销售员ID */
  private String salesId;

  // -------------------------------------------------------------------------

  /** 状态 - 已审核 未审核 所有 */
  private String kenState = "O";

  private List<Customer> agentList;

  private List<Country> countryList;

  private List<City> cityList;

  private List<Province> provinceList;

  private List<LabelValueBean> opKeyList;

  private List<LabelValueBean> comKey;

  /** 结算周期 月结 现结 */
  private List<LabelValueBean> comClearing;

  private List<Employee> saleList = new ArrayList<Employee>();

  private List<Team> teamList = new ArrayList<Team>();

  @Override
  public String input() throws Exception {
    Employee user = getUser();
    countryList = countryService.getCountryList();
    provinceList = provinceService.getAllProvince();
    cityList = cityService.getInlandCity();
    if (user.getTeams().size() > 0)
      teamId = user.getTeams().iterator().next();
    teamList = salesmanService.roGetAllTeams();
    opKeyList = getCodeList("ebiz_agent_opKey");
    comKey = getCodeList("ebiz_company_key");
    comClearing = getCodeList("ebiz_clearing_cycle");
    saleList = agentService.roGetSalesmanList();

    return INPUT;
  }

  public Customer getAgent() {
    return agent;
  }

  public void setAgent(Customer agent) {
    this.agent = agent;
  }

  public int getAgentId() {
    return agentId;
  }

  public void setAgentId(int agentId) {
    this.agentId = agentId;
  }

  public String getKenCountryId() {
    return kenCountryId;
  }

  public void setKenCountryId(String kenCountryId) {
    this.kenCountryId = kenCountryId;
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

  public String getKenName() {
    return kenName;
  }

  public void setKenName(String kenName) {
    this.kenName = kenName;
  }

  public String getKenClearingCycle() {
    return kenClearingCycle;
  }

  public void setKenClearingCycle(String kenClearingCycle) {
    this.kenClearingCycle = kenClearingCycle;
  }

  public String getCustomerCode() {
    return customerCode;
  }

  public void setCustomerCode(String customerCode) {
    this.customerCode = customerCode;
  }

  public int getTeamId() {
    return teamId;
  }

  public void setTeamId(int teamId) {
    this.teamId = teamId;
  }

  public String getSalesId() {
    return salesId;
  }

  public void setSalesId(String salesId) {
    this.salesId = salesId;
  }

  public String getKenState() {
    return kenState;
  }

  public void setKenState(String kenState) {
    this.kenState = kenState;
  }

  public List<Customer> getAgentList() {
    return agentList;
  }

  public void setAgentList(List<Customer> agentList) {
    this.agentList = agentList;
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

  public List<Province> getProvinceList() {
    return provinceList;
  }

  public void setProvinceList(List<Province> provinceList) {
    this.provinceList = provinceList;
  }

  public List<LabelValueBean> getOpKeyList() {
    return opKeyList;
  }

  public void setOpKeyList(List<LabelValueBean> opKeyList) {
    this.opKeyList = opKeyList;
  }

  public List<LabelValueBean> getComKey() {
    return comKey;
  }

  public void setComKey(List<LabelValueBean> comKey) {
    this.comKey = comKey;
  }

  public List<LabelValueBean> getComClearing() {
    return comClearing;
  }

  public void setComClearing(List<LabelValueBean> comClearing) {
    this.comClearing = comClearing;
  }

  public List<Employee> getSaleList() {
    return saleList;
  }

  public List<Team> getMaketorgList() {
    return teamList;
  }

  public void setMaketorgList(List<Team> maketorgList) {
    this.teamList = maketorgList;
  }
}
