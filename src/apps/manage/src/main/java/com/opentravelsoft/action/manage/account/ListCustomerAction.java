package com.opentravelsoft.action.manage.account;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

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
import com.opentravelsoft.service.setting.TeamService;
import com.opentravelsoft.util.StringUtil;

/**
 * 代理商
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:15 $
 */
public class ListCustomerAction extends ManageAction {
  private static final long serialVersionUID = 7671898914387730451L;

  private CustomerService agentService;
  private ProvinceService provinceService;
  private CountryService countryService;
  private CityService cityService;
  private TeamService teamService;
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
  private int salesId;

  /** 提供资源 */
  private String kenSupplierResource;

  /** 提供旅游目的地 */
  private String kenDestination;

  /** 状态 - 已审核 未审核 所有 */
  private String kenState = "O";

  /** A：代理商 S：供应商 */
  private String kenAccountType = "A";

  // -------------------------------------------------------------------------

  private List<Customer> agentList;

  private List<Country> countryList;

  private List<Province> provinceList;

  private List<City> cityList;

  private List<LabelValueBean> opKeyList;

  private List<LabelValueBean> comKey;

  /** 结算周期 月结 现结 */
  private List<LabelValueBean> comClearing;

  private List<LabelValueBean> typeList;

  private List<Employee> saleList = new ArrayList<Employee>();

  private List<Team> teamList = new ArrayList<Team>();

  /** 提供资源 */
  private List<LabelValueBean> resourceList;

  @Autowired
  public void setCountryService(CountryService countryService) {
    this.countryService = countryService;
  }

  @Autowired
  public void setProvinceService(ProvinceService provinceService) {
    this.provinceService = provinceService;
  }

  @Autowired
  public void setCityService(CityService cityService) {
    this.cityService = cityService;
  }

  @Autowired
  public void setTeamService(TeamService teamService) {
    this.teamService = teamService;
  }

  @Override
  public String input() {
    Employee user = getUser();
    countryList = countryService.getCountryList();
    provinceList = provinceService.getAllProvince();
    cityList = cityService.getInlandCity();
    if (user.getTeams().size() > 0)
      teamId = user.getTeams().iterator().next();
    teamList = teamService.getMarketTeam();
    opKeyList = getCodeList("ebiz_agent_opKey");
    comKey = getCodeList("ebiz_company_key");
    comClearing = getCodeList("ebiz_clearing_cycle");
    saleList = agentService.roGetSalesmanList();
    resourceList = getCodeList("ebiz_supplier_resource");
    typeList = getSysList("DOM_AccountType");

    return INPUT;
  }

  public String submit() {
    Map<String, String> vb = new Hashtable<String, String>();
    if (kenState.equals("O")) {
      agentList = agentService.getAgent(kenCountryId, kenProvince, kenCity,
          kenName, "", kenClearingCycle, salesId, customerCode, teamId,
          kenAccountType);
    } else {
      agentList = agentService.getAgent(kenCountryId, kenProvince, kenCity,
          kenName, kenState, kenClearingCycle, salesId, customerCode, teamId,
          kenAccountType);
    }
    opKeyList = getCodeList("ebiz_agent_opKey");
    for (LabelValueBean bean : opKeyList) {
      vb.put(bean.getValue(), bean.getLabel());
    }

    countryList = countryService.getCountryList();
    provinceList = provinceService.getAllProvince();
    comClearing = getCodeList("ebiz_clearing_cycle");
    teamList = teamService.getMarketTeam();
    saleList = agentService.roGetSalesmanList();
    resourceList = getCodeList("ebiz_supplier_resource");
    typeList = getSysList("DOM_AccountType");

    if (StringUtil.hasLength(kenProvince))
      cityList = cityService.roGetCitysByProvince(kenProvince);
    else
      cityList = cityService.getInlandCity();

    currentPage(agentList.size());

    return SUCCESS;
  }

  public String delete() {
    agentService.txDeleteAgent(agentId);

    return SUCCESS;
  }

  @Override
  protected int getMoveCount() {
    return 20;
  }

  @Autowired
  public void setAgentService(CustomerService agentService) {
    this.agentService = agentService;
  }

  public void setAgentId(int agentId) {
    this.agentId = agentId;
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

  public String getKenState() {
    return kenState;
  }

  public void setKenState(String kenState) {
    this.kenState = kenState;
  }

  public List<LabelValueBean> getOpKeyList() {
    return opKeyList;
  }

  public void setOpKeyList(List<LabelValueBean> opKeyList) {
    this.opKeyList = opKeyList;
  }

  public List<Province> getProvinceList() {
    return provinceList;
  }

  public List<LabelValueBean> getComKey() {
    return comKey;
  }

  public Customer getAgent() {
    return agent;
  }

  public void setAgent(Customer agent) {
    this.agent = agent;
  }

  public List<Country> getCountryList() {
    return countryList;
  }

  public String getKenCountryId() {
    return kenCountryId;
  }

  public void setKenCountryId(String kenCountryId) {
    this.kenCountryId = kenCountryId;
  }

  public String getKenClearingCycle() {
    return kenClearingCycle;
  }

  public void setKenClearingCycle(String kenClearingCycle) {
    this.kenClearingCycle = kenClearingCycle;
  }

  public List<LabelValueBean> getComClearing() {
    return comClearing;
  }

  public List<Employee> getSaleList() {
    return saleList;
  }

  public int getSalesId() {
    return salesId;
  }

  public void setSalesId(int salesId) {
    this.salesId = salesId;
  }

  public String getCustomerCode() {
    return customerCode;
  }

  public void setCustomerCode(String customerCode) {
    this.customerCode = customerCode;
  }

  public List<Team> getMaketorgList() {
    return teamList;
  }

  public void setMaketorgList(List<Team> maketorgList) {
    this.teamList = maketorgList;
  }

  public int getTeamId() {
    return teamId;
  }

  public void setTeamId(int teamId) {
    this.teamId = teamId;
  }

  public String getKenSupplierResource() {
    return kenSupplierResource;
  }

  public void setKenSupplierResource(String kenSupplierResource) {
    this.kenSupplierResource = kenSupplierResource;
  }

  public String getKenDestination() {
    return kenDestination;
  }

  public void setKenDestination(String kenDestination) {
    this.kenDestination = kenDestination;
  }

  public List<LabelValueBean> getResourceList() {
    return resourceList;
  }

  public String getKenAccountType() {
    return kenAccountType;
  }

  public void setKenAccountType(String kenAccountType) {
    this.kenAccountType = kenAccountType;
  }

  public List<LabelValueBean> getTypeList() {
    return typeList;
  }

  public void setTypeList(List<LabelValueBean> typeList) {
    this.typeList = typeList;
  }

}
