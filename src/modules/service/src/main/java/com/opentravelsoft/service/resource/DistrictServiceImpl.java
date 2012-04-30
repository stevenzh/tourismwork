package com.opentravelsoft.service.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.entity.Country;
import com.opentravelsoft.entity.District;
import com.opentravelsoft.entity.Province;
import com.opentravelsoft.providers.CountryDao;
import com.opentravelsoft.providers.DistrictDao;
import com.opentravelsoft.providers.ProvinceDao;
import com.opentravelsoft.providers.SequenceDao;
import com.opentravelsoft.util.StringUtil;

@Service("DistrictService")
public class DistrictServiceImpl implements DistrictService {
  
  @Autowired
  private CountryDao countryDao;

  @Autowired
  private ProvinceDao provinceDao;

  @Autowired
  private DistrictDao districtDao;

  @Autowired
  private SequenceDao sequenceDao;

  public List<District> roGetDistrictList(String districtName,
      String countryNo, String duchy) {
    return districtDao.getDistrictList(districtName, countryNo, duchy);
  }

  public List<Country> roGetCountry() {
    return countryDao.getCountry();
  }

  public List<Province> roGetProvinceList(String country) {
    return provinceDao.getStateByCountry(country);
  }

  public District roGetDistrict(String districtNo) {
    return districtDao.get(districtNo);
  }

  public int txDeleteDistrict(String districtNo) {
    return districtDao.deleteDistrict(districtNo);
  }

  public int txEditDistrict(District district, long userId) {
    String method = "update";
    if (!StringUtil.hasLength(district.getDistrictNo())) {
      method = "insert";
      String code = sequenceDao.getComputerNo("Z", userId);
      district.setDistrictNo(code);
    }
    return districtDao.editDistrict(district, method);
  }
}
