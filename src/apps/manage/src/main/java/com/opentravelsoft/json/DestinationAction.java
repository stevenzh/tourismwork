package com.opentravelsoft.json;

import java.util.ArrayList;
import java.util.List;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Destination;
import com.opentravelsoft.entity.District;
import com.opentravelsoft.service.resource.DestinationService;

public class DestinationAction extends ManageAction {

  private static final long serialVersionUID = -7906334884039552776L;

  private DestinationService destinationService;

  private List<LabelValueBean> map = new ArrayList<LabelValueBean>();

  private String countryNo;

  private String provinceNo;

  @Autowired
  public void setDestinationService(DestinationService destinationService) {
    this.destinationService = destinationService;
  }

  /**
   * 取得目的地列表（','分割）
   * 
   * @return
   */
  public String categorys() {
    List<Destination> list = destinationService.getCategirys();

    for (Destination category : list) {
      map.add(new LabelValueBean(String.valueOf(category.getCode()), category
          .getCode() + " " + category.getCnName()));
    }
    return SUCCESS;
  }

  /**
   * 取得目的地列表（','分割）
   * 
   * @param typeCountry 国家
   * @return
   */
  public String districtByRegion() {

    List<District> list = destinationService.getDistrictByRegion(countryNo,
        provinceNo);
    for (District district : list) {
      map.add(new LabelValueBean(String.valueOf(district.getDistrictNo()),
          district.getCnName()));
    }
    return SUCCESS;
  }

  public void setCountryNo(String countryNo) {
    this.countryNo = countryNo;
  }

  public void setProvinceNo(String provinceNo) {
    this.provinceNo = provinceNo;
  }

  public List<LabelValueBean> getMap() {
    return map;
  }

}
