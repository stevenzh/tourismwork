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
import com.opentravelsoft.entity.product.SightTrait;
import com.opentravelsoft.service.resource.SightService;
import com.opentravelsoft.util.StringUtil;

/**
 * 参数设置:景点
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:03 $
 */
public class EditSightAction extends ManageAction {
  private static final long serialVersionUID = -2281630888842253087L;

  protected static final Log logger = LogFactory.getLog(EditSightAction.class);

  private SightService sightService;

  private List<SightTrait> sightPicList = new ArrayList<SightTrait>();

  private String sightNo;

  // 检索条件 ------------------------------------------------------------------
  /** 目的地名称 */
  private String kenName;

  /** 国家 */
  private String kenCountry;

  /** 省份 */
  private String kenProvince;

  /** 目的地 */
  private String kenDistrict;

  // -------------------------------------------------------------------------
  /** 旅游目的地列表 */
  private List<District> districtList;

  /** 国家列表 */
  private List<Country> countryList;

  private List<Province> provinceList;

  private Sight sight;

  public String input() throws Exception {
    countryList = sightService.roGetCountry();
    provinceList = sightService.roGetProvinceList("CN");

    if (StringUtil.hasLength(sightNo))
      sight = sightService.roGetSightDetail(sightNo);
    else {
      sight = new Sight();
      sight.setCountry(new Country(kenCountry));
      sight.setProvince(new Province(kenProvince));
      sight.setDistrict(new District(kenDistrict));
    }

    districtList = sightService.roGetDistrictList(kenCountry, kenProvince);

    return INPUT;
  }

  public String submit() throws Exception {
    int resu = 0;

    resu = sightService.txEditSight(sight);
    if (resu < 0) {
      addActionError("修改失败!");
      return INPUT;

    }
    return SUCCESS;
  }

  public String sightPicInput() throws Exception {
    sightPicList = sightService.roGetSightPicManageList(sightNo);
    if (null == sightPicList) {
      sightPicList = new ArrayList<SightTrait>();
    }
    if (sightPicList.isEmpty()) {
      SightTrait sightTrait = new SightTrait();
      sightTrait.setId(0);
      sightPicList.add(sightTrait);
    }

    return SUCCESS;
  }

  public String sightPicAdd() throws Exception {
    int id = 1;

    for (int i = 0; i < sightPicList.size(); i++) {
      if (sightPicList.get(i).getId() > id)
        id = sightPicList.get(i).getId();
    }
    SightTrait sighttrait = new SightTrait();
    sighttrait.setId(id + 1);

    sightPicList.add(sighttrait);
    return SUCCESS;
  }

  public Sight getSight() {
    return sight;
  }

  public void setSight(Sight sight) {
    this.sight = sight;
  }

  @Autowired
  public void setSightService(SightService sightService) {
    this.sightService = sightService;
  }

  public String getSightNo() {
    return sightNo;
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

  public List<SightTrait> getSightPicList() {
    return sightPicList;
  }

  public void setSightPicList(List<SightTrait> sightPicList) {
    this.sightPicList = sightPicList;
  }

  public String getKenDestination() {
    return kenDistrict;
  }

  public void setKenDestination(String kenDestination) {
    this.kenDistrict = kenDestination;
  }

}
