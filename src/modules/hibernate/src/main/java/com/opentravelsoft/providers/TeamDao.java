package com.opentravelsoft.providers;

import java.util.List;

import com.opentravelsoft.common.TeamType;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.util.PaginationSupport;

public interface TeamDao extends GenericDao<Team, Integer> {

  public List<Team> getTeam(int userId, TeamType type);

  public PaginationSupport getTeamList(long fromRecord, int pageSize);

  public List<Team> getTeamList(TeamType type);

}
