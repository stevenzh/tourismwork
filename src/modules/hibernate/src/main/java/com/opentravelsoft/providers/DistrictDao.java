package com.opentravelsoft.providers;

import java.util.List;

import com.opentravelsoft.entity.District;

public interface DistrictDao extends GenericDao<District, String> {
  public List<District> getDistrictList(String districtName, String countryNo,
      String duchy);

  public int deleteDistrict(String districtNo);

  public int editDistrict(District district, String method);

  public List<District> getDistrictByCountry(String countryNo);

  public List<District> getDistrictByProvince(String provinceNo);

}
