package com.opentravelsoft.action.manage.resource;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Country;
import com.opentravelsoft.entity.District;
import com.opentravelsoft.entity.Province;
import com.opentravelsoft.entity.Sight;
import com.opentravelsoft.service.resource.SightService;
import com.opentravelsoft.util.PaginationSupport;

/**
 * 参数设置:景点
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:04 $
 */
public class ListSightAction extends ManageAction {
  private static final long serialVersionUID = -2281630888842253087L;

  protected static final Log logger = LogFactory.getLog(ListSightAction.class);

  private SightService sightService;

  private List<Sight> sightList = new ArrayList<Sight>();

  // private List<SightTrait> sightPicList = new ArrayList<SightTrait>();

  private String sightNo;

  // 检索条件 ------------------------------------------------------------------

  /** 所在国家 */
  private String kenCountry = "CN";

  /** 所在省份 */
  private String kenProvince;

  /** 景区 */
  private String kenDestination;

  /** 景点名称 */
  private String kenName;

  // -------------------------------------------------------------------------

  /** 景区列表 */
  private List<District> districtList;

  private List<Country> countryList;

  private List<Province> provinceList;

  public String input() throws Exception {
    countryList = sightService.roGetCountry();
    provinceList = sightService.roGetProvinceList("CN");
    districtList = sightService.roGetDistrictList(kenCountry, kenProvince);

    return INPUT;
  }

  @Override
  public String execute() throws Exception {
    provinceList = sightService.roGetProvinceList("CN");
    countryList = sightService.roGetCountry();

    districtList = sightService.roGetDistrictList(kenCountry, kenProvince);

    PaginationSupport support = sightService.getSightList(kenCountry,
        kenProvince, kenName, getFromRecord(), getMoveCount());

    sightList = sightService.roGetSightList(kenProvince, kenCountry, kenName,
        kenDestination);

    currentPage(sightList.size());

    return SUCCESS;
  }

  public String delete() throws Exception {
    sightService.txDelete(sightNo);
    return SUCCESS;
  }

  public List<Sight> getSightList() {
    return sightList;
  }

  @Autowired
  public void setSightService(SightService sightService) {
    this.sightService = sightService;
  }

  public void setSightNo(String sightNo) {
    this.sightNo = sightNo;
  }

  public String getKenName() {
    return kenName;
  }

  public void setKenName(String kenName) {
    this.kenName = kenName;
  }

  public String getKenCountry() {
    return kenCountry;
  }

  public void setKenCountry(String kenCountry) {
    this.kenCountry = kenCountry;
  }

  public String getKenProvince() {
    return kenProvince;
  }

  public void setKenProvince(String kenProvince) {
    this.kenProvince = kenProvince;
  }

  public List<District> getDistrictList() {
    return districtList;
  }

  public List<Country> getCountryList() {
    return countryList;
  }

  public List<Province> getProvinceList() {
    return provinceList;
  }

  public String getKenDestination() {
    return kenDestination;
  }

  public void setKenDestination(String kenDestination) {
    this.kenDestination = kenDestination;
  }

  // public List<SightTrait> getSightPicList()
  // {
  // return sightPicList;
  // }
  //
  // public void setSightPicList(List<SightTrait> sightPicList)
  // {
  // this.sightPicList = sightPicList;
  // }

}
