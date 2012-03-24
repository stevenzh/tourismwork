package com.opentravelsoft.service.resource;

import java.util.List;

import com.opentravelsoft.util.LabelValueBean;

import com.opentravelsoft.entity.Destination;
import com.opentravelsoft.entity.District;
import com.opentravelsoft.entity.Plan;

public interface DestinationService {

  List<Destination> roGetAllCategorys();

  void txSaveCategory(Destination department);

  Destination roGetCategory(long catId);

  int txDelCategorys(long catId);

  List<Destination> getCategirys();

  List<District> getDistrictByRegion(String countryNo, String provinceNo);

  List<List<LabelValueBean>> getRegionGroupList();

  List<Plan> getPlanList(String region, boolean deadline);

  List<List<LabelValueBean>> roGetSubRegions(String regionId);

  List<Destination> getRegionList();

}
