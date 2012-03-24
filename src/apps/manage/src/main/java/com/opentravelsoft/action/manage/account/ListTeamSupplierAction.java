package com.opentravelsoft.action.manage.account;

import java.util.ArrayList;
import java.util.List;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.City;
import com.opentravelsoft.entity.Country;
import com.opentravelsoft.entity.Customer;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.service.account.CustomerService;
import com.opentravelsoft.service.resource.CityService;
import com.opentravelsoft.service.resource.CountryService;
import com.opentravelsoft.service.setting.TeamService;

/**
 * 计调部门对应供应商
 * 
 * @author zhangst
 * 
 */
public class ListTeamSupplierAction extends ManageAction {
  private static final long serialVersionUID = -2148479645399144534L;

  private List<Team> teamList = new ArrayList<Team>();

  private CustomerService supplierService;

  private CountryService countryService;

  private CityService cityService;

  private TeamService teamService;

  private int supplierId;

  private long teamId;

  private String[] select;

  private List<Customer> listSupplier;

  // -------------------------------------------------------------------------
  // 检索条件
  /** 国家 */
  private String kenCountryId = "CN";

  private String kenSupplierResource;

  // -------------------------------------------------------------------------
  private List<City> cityList;

  private List<Customer> supplierList = new ArrayList<Customer>();

  private List<Country> countryList;

  private List<LabelValueBean> resourceList;

  @Autowired
  public void setCountryService(CountryService countryService) {
    this.countryService = countryService;
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
    teamList = teamService.getOperatorTeam();
    countryList = countryService.roGetCountrys();
    resourceList = getCodeList("ebiz_supplier_resource");
    cityList = cityService.roGetCitysByCountry(kenCountryId);
    if (teamList.size() > 0)
      teamId = teamList.get(0).getTeamId();

    // if (auth.contains(EbizCommon.ROLE_OPERATOR))
    supplierList = supplierService.roGetSupplier(teamId, kenSupplierResource,
        false);

    for (Customer Supplier : supplierList) {
      Supplier.setChecked("true");
    }

    return INPUT;
  }

  public String search() {
    teamList = teamService.getOperatorTeam();
    countryList = countryService.roGetCountrys();
    resourceList = getCodeList("ebiz_supplier_resource");
    cityList = cityService.roGetCitysByCountry(kenCountryId);

    if (teamList.size() > 0)
      teamId = teamList.get(0).getTeamId();

    supplierList = supplierService.roGetSupplier(teamId, null, false);
    listSupplier = supplierService.roFindSupplier(kenCountryId,
        kenSupplierResource);

    for (Customer Supplier : supplierList) {
      Supplier.setChecked("true");
    }

    for (Customer supplier : listSupplier) {
      for (int i = supplierList.size() - 1; i >= 0; i--) {
        if (supplier.getSupplierId() == supplierList.get(i).getSupplierId()) {
          supplier.setChecked("true");
          supplierList.remove(i);
          break;
        }
      }
    }

    return SUCCESS;
  }

  public String delete() {
    int result = supplierService.txDeleteSupplier(supplierId);
    return SUCCESS;
  }

  public String submit() {
    Employee user = getUser();
    if (teamId == 0)
      teamId = user.getTeams().iterator().next();

    int result = supplierService.txSaveGroupSupplier(teamId, select);
    return SUCCESS;
  }

  @Autowired
  public void setSupplierService(CustomerService supplierService) {
    this.supplierService = supplierService;
  }

  public List<Team> getTeamList() {
    return teamList;
  }

  public List<Customer> getListSupplier() {
    return listSupplier;
  }

  public List<Country> getCountryList() {
    return countryList;
  }

  public List<Customer> getSupplierList() {
    return supplierList;
  }

  public void setSupplierList(List<Customer> SupplierList) {
    this.supplierList = SupplierList;
  }

  public int getSupplierId() {
    return supplierId;
  }

  public void setSupplierId(int SupplierId) {
    this.supplierId = SupplierId;
  }

  public List<Customer> getListSupplierList() {
    return listSupplier;
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

  public void setSelect(String[] select) {
    this.select = select;
  }

  public long getGroupId() {
    return teamId;
  }

  public void setGroupId(long groupId) {
    this.teamId = groupId;
  }

  public String getKenSupplierResource() {
    return kenSupplierResource;
  }

  public void setKenSupplierResource(String kenSupplierResource) {
    this.kenSupplierResource = kenSupplierResource;
  }

  public List<LabelValueBean> getResourceList() {
    return resourceList;
  }

}
