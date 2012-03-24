package com.opentravelsoft.service.portal;

import java.util.Date;
import java.util.List;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.entity.Destination;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.providers.DestinationDao;
import com.opentravelsoft.providers.mixed.PlanListDao;

@Service("PlanListService")
public class PlanListServiceImpl implements PlanListService {

  private PlanListDao planListDao;

  private DestinationDao destinationDao;

  @Autowired
  public void setDestinationDao(DestinationDao destinationDao) {
    this.destinationDao = destinationDao;
  }

  @Autowired
  public void setPlanListDao(PlanListDao planListDao) {
    this.planListDao = planListDao;
  }

  public List<Plan> getPlanList(String region, boolean deadline) {
    return planListDao.getPlanList(0L, deadline, region);
  }

  public List<List<LabelValueBean>> roGetSubRegions(String regionId) {
    return destinationDao.getSubRegions(regionId);
  }

  public List<List<LabelValueBean>> getRegionGroupList() {
    return null;
  }

  public List<Plan> roFind(String lineName, int groupId, int userId,
      Date startDate, Date endDate, double lowerPrice, double upperPrice,
      boolean openKey, String outCity, String destination) {
    return planListDao.find(lineName, groupId, userId, startDate, endDate,
        lowerPrice, upperPrice, openKey, outCity, destination);
  }

  public List<Destination> getDestinations() {
    return destinationDao.getTopDestination();
  }

  public List<Plan> getPlans(long rowCount, boolean deadline, String region) {
    return planListDao.getPlanList(rowCount, deadline, region);
  }

  public List<Plan> getPlans(String lineNo, boolean openFlag, boolean after,
      boolean outDateSort, boolean deadline) {
    return planListDao.getLinePlans(lineNo, openFlag, after, outDateSort,
        deadline);
  }
}
