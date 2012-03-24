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
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Province;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.service.account.CustomerService;
import com.opentravelsoft.service.resource.CityService;
import com.opentravelsoft.service.resource.CountryService;
import com.opentravelsoft.service.resource.ProvinceService;
import com.opentravelsoft.service.setting.TeamService;

/**
 * 计调部门对应供应商
 * 
 * @author zhangst
 * 
 */
public class EditTeamSupplierAction extends ManageAction {
  private static final long serialVersionUID = -2148479645399144534L;

  private List<Team> teamList = new ArrayList<Team>();

  private CustomerService supplierService;
  private ProvinceService provinceService;
  private CountryService countryService;
  private CityService cityService;
  private TeamService teamService;

  private Customer supplier = new Customer();

  /** 联系人 */
  private List<Contact> contacts = new ArrayList<Contact>();

  private int supplierId;

  private long teamId;

  /** 付款申请书ID 用于记录付款申请时修改供应商信息 */
  private String outcomeId;

  private int idx;

  // -------------------------------------------------------------------------
  // 检索条件
  /** 国家 */
  private String kenCountryId = "CN";

  /** 城市 */
  private String kenCity = "";

  // -------------------------------------------------------------------------

  private List<Country> countryList;

  private List<City> cityList;

  private List<Province> provinceList;

  /** 结算周期 月结 现结 */
  private List<LabelValueBean> comClearing;

  private List<LabelValueBean> featureList;

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

  public String input() {
    teamList = teamService.getMarketTeam();
    countryList = countryService.roGetCountrys();
    provinceList = provinceService.roGetProvinces();
    cityList = cityService.roGetCitysByCountry(kenCountryId);

    if (teamId == 0)
      teamId = teamList.get(0).getTeamId();

    if (supplierId > 0) {
      supplier = supplierService.roGetSupplier(supplierId);
      contacts = supplier.getContacts();
      cityList = cityService.roGetCitysByCountry(supplier.getCountryCd());
    } else {
      cityList = cityService.getAllCity();
    }
    comClearing = getCodeList("ebiz_clearing_cycle");
    featureList = getCodeList("ebiz_supplier_feature");
    resourceList = getCodeList("ebiz_supplier_resource");

    return INPUT;
  }

  public String submit() {
    Employee user = getUser();
    supplier.setUpdatedBy(user.getUserId());
    supplier.setContacts(contacts);
    supplierService.txEditSupplier(supplier, teamId);

    return SUCCESS;
  }

  public String addContact() {
    if (supplierId > 0) {
      supplier = supplierService.roGetSupplier(supplierId);
      cityList = cityService.roGetCitysByCountry(supplier.getCountryCd());
    } else {
      cityList = cityService.getAllCity();
    }
    countryList = countryService.roGetCountrys();
    provinceList = provinceService.roGetProvinces();
    cityList = cityService.roGetCitysByCountry(kenCountryId);
    comClearing = getCodeList("ebiz_clearing_cycle");
    featureList = getCodeList("ebiz_supplier_feature");
    resourceList = getCodeList("ebiz_supplier_resource");

    int index = 0;
    for (int i = 0; i < contacts.size(); i++) {
      if (contacts.get(i).getIdx() > index)
        index = contacts.get(i).getIdx();
    }
    Contact routeFlight = new Contact();
    routeFlight.setIdx(index + 1);
    contacts.add(routeFlight);

    return SUCCESS;
  }

  public String deleteContact() {
    if (supplierId > 0) {
      supplier = supplierService.roGetSupplier(supplierId);
      cityList = cityService.roGetCitysByCountry(supplier.getCountryCd());
    } else {
      cityList = cityService.getAllCity();
    }
    countryList = countryService.roGetCountrys();
    provinceList = provinceService.roGetProvinces();
    //
    cityList = cityService.roGetCitysByCountry(kenCountryId);
    comClearing = getCodeList("ebiz_clearing_cycle");
    featureList = getCodeList("ebiz_supplier_feature");
    resourceList = getCodeList("ebiz_supplier_resource");

    Contact routeFlight = new Contact();
    for (Contact obj : contacts) {
      if (obj.getIdx() == idx) {
        // 删除行
        for (int i = idx; i < contacts.size(); i++) {
          routeFlight = contacts.get(i);
          routeFlight.setIdx(i);
          contacts.set(i, routeFlight);
        }
        contacts.remove(obj);
        break;
      }
    }

    return SUCCESS;
  }

  @Autowired
  public void setSupplierService(CustomerService supplierService) {
    this.supplierService = supplierService;
  }

  public List<Team> getTeamList() {
    return teamList;
  }

  public List<Country> getCountryList() {
    return countryList;
  }

  public int getSupplierId() {
    return supplierId;
  }

  public void setSupplierId(int SupplierId) {
    this.supplierId = SupplierId;
  }

  public List<City> getCityList() {
    return cityList;
  }

  public String getKenCountryId() {
    return kenCountryId;
  }

  public void setKenCountryId(String kenCountryId) {
    this.kenCountryId = kenCountryId;
  }

  public String getKenCity() {
    return kenCity;
  }

  public void setKenCity(String kenCity) {
    this.kenCity = kenCity;
  }

  public long getGroupId() {
    return teamId;
  }

  public void setGroupId(long groupId) {
    this.teamId = groupId;
  }

  public Customer getSupplier() {
    return supplier;
  }

  public void setSupplier(Customer supplier) {
    this.supplier = supplier;
  }

  public List<Province> getProvinceList() {
    return provinceList;
  }

  public List<LabelValueBean> getComClearing() {
    return comClearing;
  }

  public List<LabelValueBean> getFeatureList() {
    return featureList;
  }

  public List<LabelValueBean> getResourceList() {
    return resourceList;
  }

  public List<Contact> getContacts() {
    return contacts;
  }

  public void setContacts(List<Contact> contacts) {
    this.contacts = contacts;
  }

  public int getIdx() {
    return idx;
  }

  public void setIdx(int idx) {
    this.idx = idx;
  }

  public String getOutcomeId() {
    return outcomeId;
  }

  public void setOutcomeId(String outcomeId) {
    this.outcomeId = outcomeId;
  }

}
