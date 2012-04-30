package com.opentravelsoft.action.manage.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.common.SessionKeyParams;
import com.opentravelsoft.entity.Country;
import com.opentravelsoft.entity.District;
import com.opentravelsoft.entity.Line;
import com.opentravelsoft.entity.Province;
import com.opentravelsoft.service.product.LineDistrictService;

/**
 * 目的地
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 */
public class LineDistrictAction extends ManageAction {
  private static final long serialVersionUID = 7671898914387730451L;

  protected static final Log logger = LogFactory
      .getLog(LineDistrictAction.class);

  private Map<String, String> teamList;

  @Autowired
  private LineDistrictService routeDistrictService;

  private Line line;

  private String countryNo = "";

  private String provinceNo = "";

  private String[] selectedDistricts;

  private List<Country> countryList;

  private List<Province> provinceList;

  private List<District> districtList;

  private List<District> searchList = new ArrayList<District>();

  public String input() throws Exception {
    Map<String, Object> session = ActionContext.getContext().getSession();
    line = (Line) session.get(SessionKeyParams.EBIZ_CURRENT_ROUTE);
    countryList = routeDistrictService.getCountry();
    provinceList = routeDistrictService.getProvinceList();

    districtList = routeDistrictService
        .getOverseaLineDistrict(line.getLineNo());

    return INPUT;
  }

  public String search() {
    Map<String, Object> session = ActionContext.getContext().getSession();
    line = (Line) session.get(SessionKeyParams.EBIZ_CURRENT_ROUTE);
    countryList = routeDistrictService.getCountry();
    provinceList = routeDistrictService.getProvinceList();

    districtList = routeDistrictService
        .getOverseaLineDistrict(line.getLineNo());
    searchList = routeDistrictService.findOverseaLineDistrict(countryNo,
        provinceNo, line.getLineNo());

    for (int i = 0; i < searchList.size(); i++) {
      District dts = searchList.get(i);
      if (dts.getCnNote().length() > 100)
        dts.setCnNote(dts.getCnNote().substring(0, 100) + "...");
    }

    return SUCCESS;
  }

  public String submit() throws Exception {
    line = (Line) ActionContext.getContext().getSession()
        .get(SessionKeyParams.EBIZ_CURRENT_ROUTE);
    List<District> list = new ArrayList<District>();
    District district = null;
    if (null != selectedDistricts) {
      for (int i = 0; i < selectedDistricts.length; i++) {
        district = new District();
        district.setDistrictNo(selectedDistricts[i]);
        list.add(district);
      }
    } else {
      addActionError("至少填写一个目的地.");
      return INPUT;
    }

    routeDistrictService.txSaveLineDistrict(list, line.getLineNo());

    addActionMessage("目的地保存成功.");
    return SUCCESS;
  }

  public Line getLine() {
    return line;
  }

  public String[] getCheck() {
    return selectedDistricts;
  }

  public void setCheck(String[] districts) {
    this.selectedDistricts = districts.clone();
  }

  public List<Country> getCountryList() {
    return countryList;
  }

  public void setCountryList(List<Country> countryList) {
    this.countryList = countryList;
  }

  public String getCountryNo() {
    return countryNo;
  }

  public void setCountryNo(String countryNo) {
    this.countryNo = countryNo;
  }

  public List<District> getDistrictList() {
    return districtList;
  }

  public List<Province> getProvinceList() {
    return provinceList;
  }

  public void setProvinceList(List<Province> provinceList) {
    this.provinceList = provinceList;
  }

  public String getProvinceNo() {
    return provinceNo;
  }

  public void setProvinceNo(String provinceNo) {
    this.provinceNo = provinceNo;
  }

  public List<District> getSearchList() {
    return searchList;
  }

  public void setSearchList(List<District> searchList) {
    this.searchList = searchList;
  }

  public Map<String, String> getSelectedCountryList() {
    return teamList;
  }

  public void setSelectedCountryList(Map<String, String> selectedCountryList) {
    this.teamList = selectedCountryList;
  }

}
