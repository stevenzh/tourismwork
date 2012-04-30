package com.opentravelsoft.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.entity.Country;
import com.opentravelsoft.entity.District;
import com.opentravelsoft.entity.Province;
import com.opentravelsoft.providers.CountryDao;
import com.opentravelsoft.providers.ProvinceDao;
import com.opentravelsoft.providers.product.LineDistrictDao;

@Service("RouteDistrictService")
public class LineDistrictServiceImpl implements LineDistrictService {

  @Autowired
  private LineDistrictDao routeDistrictDao;

  @Autowired
  private ProvinceDao provinceDao;

  @Autowired
  private CountryDao countryDao;

  public List<Province> getProvinceList() {
    return provinceDao.getStateByCountry("CN");
  }

  public List<Country> getCountry() {
    return countryDao.getCountry();
  }

  public List<District> findOverseaLineDistrict(String countryNo,
      String provinceNo, String lineNo) {
    return routeDistrictDao.findOverseaLineDistrict(countryNo, provinceNo,
        lineNo);
  }

  public List<District> getDomesticLineDistrict(String lineNo) {
    return routeDistrictDao.getDomesticLineDistrict(lineNo);
  }

  public List<District> getOverseaLineDistrict(String lineNo) {
    return routeDistrictDao.getOverseaLineDistrict(lineNo);
  }

  public void txSaveLineDistrict(List<District> list, String lineNo) {
    routeDistrictDao.saveLineDistrict(list, lineNo);
  }
}
