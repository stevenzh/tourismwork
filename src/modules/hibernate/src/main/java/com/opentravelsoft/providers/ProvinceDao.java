package com.opentravelsoft.providers;

import java.util.List;

import com.opentravelsoft.entity.Province;

public interface ProvinceDao extends GenericDao<Province, String> {
  public List<Province> getStateByCountry(String country);
}
