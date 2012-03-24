package com.opentravelsoft.action.manage.product;

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
import com.opentravelsoft.util.StringUtil;

/**
 * 在线路制作是修改景点信息
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:54 $
 */
public class EditSightAction extends ManageAction {
  private static final long serialVersionUID = -2281630888842253087L;

  protected static final Log logger = LogFactory.getLog(EditSightAction.class);

  private SightService sightService;

  private String sightNo;

  // -------------------------------------------------------------------------
  /** 旅游目的地列表 */
  private List<District> districtList = new ArrayList<District>();

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
      // sight.setCountry(kenCountry);
      // sight.setProvinceNo(kenProvince);
      // sight.setDistrictNo(kenDestination);
    }

    districtList = sightService.roGetDistrictList(sight.getCountry()
        .getCountryId(), sight.getProvince().getCode());

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

  public List<District> getDistrictList() {
    return districtList;
  }

  public List<Country> getCountryList() {
    return countryList;
  }

  public List<Province> getProvinceList() {
    return provinceList;
  }

}
