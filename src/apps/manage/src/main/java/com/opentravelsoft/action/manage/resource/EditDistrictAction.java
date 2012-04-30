package com.opentravelsoft.action.manage.resource;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Country;
import com.opentravelsoft.entity.District;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Province;
import com.opentravelsoft.service.resource.DistrictService;
import com.opentravelsoft.util.StringUtil;

/**
 * 参数设置:目的地维护
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:03 $
 */
public class EditDistrictAction extends ManageAction {
  private static final long serialVersionUID = 7671898914387730451L;

  protected static final Log logger = LogFactory
      .getLog(EditDistrictAction.class);

  @Autowired
  private DistrictService districtService;

  private String districtNo;

  private District district;

  // -------------------------------------------------------------------------
  // 检索条件
  /** 目的地名称 */
  private String kenName;

  /** 国家 */
  private String kenCountry;

  /** 省份 */
  private String kenProvince;

  // -------------------------------------------------------------------------

  private List<Country> countryList;

  private List<Province> provinceList;

  public String input() throws Exception {
    if (StringUtil.hasLength(districtNo))
      district = districtService.roGetDistrict(districtNo);
    else
      district = new District();

    countryList = districtService.roGetCountry();
    provinceList = districtService.roGetProvinceList("CN");
    return INPUT;
  }

  public String submit() throws Exception {
    Employee user = getUser();
    districtService.txEditDistrict(district, user.getUserId());
    return SUCCESS;
  }

  public String getDistrictNo() {
    return districtNo;
  }

  public void setDistrictNo(String districtNo) {
    this.districtNo = districtNo;
  }

  public List<Country> getCountryList() {
    return countryList;
  }

  public List<Province> getProvinceList() {
    return provinceList;
  }

  public String getKenCountry() {
    return kenCountry;
  }

  public void setKenCountry(String kenCountry) {
    this.kenCountry = kenCountry;
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

  public District getDistrict() {
    return district;
  }

  public void setDistrict(District district) {
    this.district = district;
  }

}
