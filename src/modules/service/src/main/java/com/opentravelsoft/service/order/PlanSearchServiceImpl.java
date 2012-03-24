package com.opentravelsoft.service.order;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.common.TeamType;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.providers.TeamDao;
import com.opentravelsoft.providers.mixed.PlanListDao;

@Service("SalesPlanSearchService")
public class PlanSearchServiceImpl implements PlanSearchService {
  private PlanListDao planListDao;

  private TeamDao teamDao;

  @Autowired
  public void setPlanListDao(PlanListDao planListDao) {
    this.planListDao = planListDao;
  }

  @Autowired
  public void setTeamDao(TeamDao teamDao) {
    this.teamDao = teamDao;
  }

  public List<Plan> roFind(String lineName, long teamId, long userId,
      Date startDate, Date endDate, double lowerPrice, double upperPrice,
      boolean openKey) {
    return planListDao.find(lineName, teamId, userId, startDate, endDate,
        lowerPrice, upperPrice, openKey, "", "");
  }

  public List<Team> getTeamList(long userId, TeamType type) {
    return teamDao.getTeam(userId, type);
  }
}
