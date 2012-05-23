package com.opentravelsoft.service.resource;

import java.util.List;

import com.opentravelsoft.entity.Country;
import com.opentravelsoft.entity.District;
import com.opentravelsoft.entity.Province;
import com.opentravelsoft.entity.Sight;
import com.opentravelsoft.entity.product.SightTrait;
import com.opentravelsoft.util.PaginationSupport;

public interface SightService {
  public void txDelete(String sightno);

  public Sight roGetSightDetail(String sightno);

  public List<District> roGetDistrictList(String countryNo, String province);

  public List<Country> roGetCountry();

  public List<Province> roGetProvinceList(String country);

  public List<Sight> roGetSightList(String kenProvince, String kenCountry,
      String kenName, String kenDestination);

  public List<SightTrait> roGetSightPicManageList(String sightNo);

  public int txEditSight(Sight sight);

  public PaginationSupport getSightList(String country, String province,
      String name, int fromRecord, int moveCount);

}
