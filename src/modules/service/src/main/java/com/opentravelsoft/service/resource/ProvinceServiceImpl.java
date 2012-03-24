package com.opentravelsoft.service.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.entity.Province;
import com.opentravelsoft.providers.ProvinceDao;
import com.opentravelsoft.service.impl.GenericManagerImpl;

/**
 * Implementation of UserManager interface.
 * 
 */
@Service("ProvinceService")
public class ProvinceServiceImpl extends GenericManagerImpl<Province, String>
    implements ProvinceService {

  private ProvinceDao provinceDao;

  @Autowired
  public void setProvinceDao(ProvinceDao provinceDao) {
    this.dao = provinceDao;
    this.provinceDao = provinceDao;
  }

  public List<Province> getAllProvince() {
    return provinceDao.getStateByCountry("CN");
  }

  public List<Province> roGetProvinces() {
    return provinceDao.getStateByCountry("CN");
  }

}
