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

  @Autowired
  private PlanListDao planListDao;

  @Autowired
  private TeamDao teamDao;

  public List<Plan> roFind(String lineName, int teamId, int userId,
      Date startDate, Date endDate, double lowerPrice, double upperPrice,
      boolean openKey) {
    return planListDao.find(lineName, teamId, userId, startDate, endDate,
        lowerPrice, upperPrice, openKey, "", "");
  }

  public List<Team> getTeamList(int userId, TeamType type) {
    return teamDao.getTeam(userId, type);
  }
}
