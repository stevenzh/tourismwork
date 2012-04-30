package com.opentravelsoft.action.manage.account;

import java.util.ArrayList;
import java.util.List;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.City;
import com.opentravelsoft.entity.Contact;
import com.opentravelsoft.entity.Country;
import com.opentravelsoft.entity.Customer;
import com.opentravelsoft.entity.Province;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.service.account.CustomerService;
import com.opentravelsoft.service.resource.CityService;
import com.opentravelsoft.service.resource.CountryService;
import com.opentravelsoft.service.resource.ProvinceService;

/**
 * 代理商维护
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:15 $
 */
public class EditCustomerAction extends ManageAction {
  private static final long serialVersionUID = 7671898914387730451L;

  @Autowired
  private CustomerService agentService;
  @Autowired
  private ProvinceService provinceService;
  @Autowired
  private CountryService countryService;
  @Autowired
  private CityService cityService;
  
  private Customer agent = new Customer();

  /** 代理商编码 */
  private int agentId = 0;

  private int idx;

  // -------------------------------------------------------------------------
  // 检索条件
  /** 省份 */
  private String kenCountryId = "";

  /** 省份 */
  private String kenProvince = "";

  /** 城市 */
  private String kenCity = "";

  private int teamId;

  /** 名称 */
  private String kenName = "";

  /** 状态 - 网上申请 活动 待审核 关闭 删除 所有 */
  private String kenState = "O";

  private String kenClearingCycle = "N";

  /** 销售员ID */
  private String salesId;

  private String kenSupplierResource;

  private String kenDestination;

  // -------------------------------------------------------------------------

  private List<Country> countryList;

  private List<City> cityList;

  private List<Province> provinceList;

  private List<LabelValueBean> opKeyList;

  private List<LabelValueBean> comKey;

  /** 公司结构 总公司、分公司 */
  private List<LabelValueBean> comStructure;

  /** 结算周期 月结 现结 */
  private List<LabelValueBean> comClearing;

  /** 联系人 */
  private List<Contact> contacts = new ArrayList<Contact>();

  private List<Employee> saleList = new ArrayList<Employee>();

  private List<LabelValueBean> featureList;

  private List<LabelValueBean> resourceList;

  public String input() {
    if (agentId > 0) {
      agent = agentService.findAgent(agentId);
      contacts = agent.getContacts();
      cityList = cityService.roGetCitysByProvince(agent.getProvinceCd());
    } else {
      cityList = cityService.getInlandCity();
      agent.setStay(10f);
    }

    countryList = countryService.getCountryList();
    provinceList = provinceService.getAllProvince();
    comKey = getCodeList("ebiz_company_key");
    comClearing = getCodeList("ebiz_clearing_cycle");
    comStructure = getCodeList("ebiz_crm_structure");
    saleList = agentService.roGetSalesmanList();
    featureList = getCodeList("ebiz_supplier_feature");
    resourceList = getCodeList("ebiz_supplier_resource");

    return INPUT;
  }

  public String addContact() {
    if (agentId > 0) {
      agent = agentService.findAgent(agentId);
      cityList = cityService.roGetCitysByProvince(agent.getProvinceCd());
    } else {
      cityList = cityService.getInlandCity();
    }
    countryList = countryService.getCountryList();
    provinceList = provinceService.getAllProvince();
    comKey = getCodeList("ebiz_company_key");
    comClearing = getCodeList("ebiz_clearing_cycle");
    comStructure = getCodeList("ebiz_crm_structure");
    saleList = agentService.roGetSalesmanList();
    featureList = getCodeList("ebiz_supplier_feature");
    resourceList = getCodeList("ebiz_supplier_resource");

    int index = 0;
    for (Contact obj : contacts) {
      obj.setIdx(index++);
    }
    Contact routeFlight = new Contact();
    routeFlight.setIdx(index + 1);
    contacts.add(routeFlight);

    return SUCCESS;
  }

  public String deleteContact() {
    if (agentId > 0) {
      agent = agentService.findAgent(agentId);
      cityList = cityService.roGetCitysByProvince(agent.getProvinceCd());
    } else {
      cityList = cityService.getInlandCity();
    }
    countryList = countryService.getCountryList();
    provinceList = provinceService.getAllProvince();
    comKey = getCodeList("ebiz_company_key");
    comClearing = getCodeList("ebiz_clearing_cycle");
    comStructure = getCodeList("ebiz_crm_structure");
    saleList = agentService.roGetSalesmanList();
    featureList = getCodeList("ebiz_supplier_feature");
    resourceList = getCodeList("ebiz_supplier_resource");

    for (Contact obj : contacts) {
      if (obj.getIdx() == idx) {
        contacts.remove(obj);
        break;
      }
    }
    return SUCCESS;
  }

  public String submit() {
    Employee user = getUser();
    agent.setUpdatedBy(user.getUserId());
    int result = agentService.txEditAgent(agent, contacts);
    if (result < 0) {
      addActionError("客户的名称和联系人姓名重复.");
      return INPUT;
    }

    return SUCCESS;
  }

  public String checked() {
    Employee user = getUser();
    agent.setCheckedBy(user.getUserId());
    agentService.txCheckedAgent(agent);

    return SUCCESS;
  }

  public int getAgentId() {
    return agentId;
  }

  public void setAgentId(int agentId) {
    this.agentId = agentId;
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

  public void setProvinceList(List<Province> provinceList) {
    this.provinceList = provinceList;
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

  public List<LabelValueBean> getComStructure() {
    return comStructure;
  }

  public List<LabelValueBean> getComClearing() {
    return comClearing;
  }

  public String getKenClearingCycle() {
    return kenClearingCycle;
  }

  public void setKenClearingCycle(String kenClearingCycle) {
    this.kenClearingCycle = kenClearingCycle;
  }

  public List<Contact> getContacts() {
    return contacts;
  }

  public void setContacts(List<Contact> contacts) {
    this.contacts = contacts;
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

  public List<LabelValueBean> getFeatureList() {
    return featureList;
  }

  public List<LabelValueBean> getResourceList() {
    return resourceList;
  }

}
