package com.opentravelsoft.service.order;

import java.util.Date;
import java.util.List;

import com.opentravelsoft.common.TeamType;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.Team;

public interface PlanSearchService {
  public List<Plan> roFind(String lineName, int teamId, int userId,
      Date startDate, Date endDate, double lowerPrice, double upperPrice,
      boolean openKey);

  public List<Team> getTeamList(int userId, TeamType type);

}
