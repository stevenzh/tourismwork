package com.opentravelsoft.service.resource;

import java.util.List;

import com.opentravelsoft.entity.Country;
import com.opentravelsoft.entity.District;
import com.opentravelsoft.entity.Province;

public interface DistrictService {
  public List<District> roGetDistrictList(String districtName,
      String countryNo, String duchy);

  public List<Country> roGetCountry();

  public List<Province> roGetProvinceList(String country);

  public District roGetDistrict(String districtNo);

  public int txDeleteDistrict(String districtNo);

  public int txEditDistrict(District district, int userId);
}
