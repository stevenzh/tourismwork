package com.opentravelsoft.action.manage.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Country;
import com.opentravelsoft.entity.District;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Province;
import com.opentravelsoft.service.resource.DistrictService;
import com.opentravelsoft.util.StringUtil;

/**
 * 在线路制作中修改景区信息
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:54 $
 */
public class EditDistrictAction extends ManageAction {
  private static final long serialVersionUID = 8905386860268766776L;

  private DistrictService districtService;

  private String countryNo;

  private String districtNo;

  private String provinceNo;

  private District district;

  private List<Country> countryList;

  private List<Province> provinceList;

  @Autowired
  public void setDistrictService(DistrictService districtService) {
    this.districtService = districtService;
  }

  @Override
  public String input() throws Exception {
    if (StringUtil.hasLength(districtNo)) {
      district = districtService.roGetDistrict(districtNo);
    } else {
      district = new District();
      if (StringUtil.hasLength(countryNo))
        district.getCountry().setCountryId(countryNo);
      else
        district.getProvince().setCode(provinceNo);
    }
    countryList = districtService.roGetCountry();
    provinceList = districtService.roGetProvinceList("CN");

    return INPUT;
  }

  public String submit() {
    Employee user = getUser();
    districtService.txEditDistrict(district, user.getUserId());
    return SUCCESS;
  }

  public String getCountryNo() {
    return countryNo;
  }

  public void setCountryNo(String countryNo) {
    this.countryNo = countryNo;
  }

  public String getDistrictNo() {
    return districtNo;
  }

  public void setDistrictNo(String districtNo) {
    this.districtNo = districtNo;
  }

  public District getDistrict() {
    return district;
  }

  public void setDistrict(District district) {
    this.district = district;
  }

  public String getProvinceNo() {
    return provinceNo;
  }

  public void setProvinceNo(String provinceNo) {
    this.provinceNo = provinceNo;
  }

  public List<Country> getCountryList() {
    return countryList;
  }

  public List<Province> getProvinceList() {
    return provinceList;
  }

}
