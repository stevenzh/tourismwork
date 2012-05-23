package com.opentravelsoft.service.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.entity.Country;
import com.opentravelsoft.entity.District;
import com.opentravelsoft.entity.Province;
import com.opentravelsoft.entity.Sight;
import com.opentravelsoft.entity.product.SightTrait;
import com.opentravelsoft.providers.CountryDao;
import com.opentravelsoft.providers.DistrictDao;
import com.opentravelsoft.providers.ProvinceDao;
import com.opentravelsoft.providers.SequenceDao;
import com.opentravelsoft.providers.SightDao;
import com.opentravelsoft.util.PaginationSupport;
import com.opentravelsoft.util.StringUtil;

@Service("SightService")
public class SightServiceImpl implements SightService {
  
  @Autowired
  private SightDao sightDao;

  @Autowired
  private CountryDao countryDao;

  @Autowired
  private ProvinceDao provinceDao;

  @Autowired
  private DistrictDao districtDao;

  @Autowired
  private SequenceDao sequenceDao;

  public void txDelete(String sightno) {
    sightDao.remove(sightno);
  }

  public Sight roGetSightDetail(String sightno) {
    return sightDao.get(sightno);
  }

  public List<District> roGetDistrictList(String countryNo, String province) {
    return districtDao.getDistrictList("", countryNo, province);
  }

  public List<Country> roGetCountry() {
    return countryDao.getCountry();
  }

  public List<Province> roGetProvinceList(String country) {
    return provinceDao.getStateByCountry(country);
  }

  public List<SightTrait> roGetSightPicManageList(String sightNo) {
    return sightDao.getSightPicMangeList(sightNo);
  }

  public List<Sight> roGetSightList(String province, String country,
      String sightName, String destinationNo) {
    return sightDao.getSightList(province, country, sightName, destinationNo);
  }

  public int txEditSight(Sight sight) {
    String method = "update";
    if (!StringUtil.hasLength(sight.getSightNo())) {
      method = "insert";
      String code = sequenceDao.getComputerNo("Z", 0);
      sight.setSightNo(code);
    }

    return sightDao.editSight(sight, method);
  }

  public PaginationSupport getSightList(String country, String province,
      String name, long fromRecord, int moveCount) {
    return sightDao
        .getSightList(country, province, name, fromRecord, moveCount);
  }

}
