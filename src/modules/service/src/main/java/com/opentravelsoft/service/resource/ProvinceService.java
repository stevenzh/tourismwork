package com.opentravelsoft.service.resource;

import java.util.List;

import com.opentravelsoft.entity.Province;
import com.opentravelsoft.service.GenericManager;

public interface ProvinceService extends GenericManager<Province, String> {

  public List<Province> getAllProvince();

  List<Province> roGetProvinces();
}
