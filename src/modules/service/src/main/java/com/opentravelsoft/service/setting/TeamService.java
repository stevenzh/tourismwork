package com.opentravelsoft.service.setting;

import java.util.List;
import java.util.Map;

import com.opentravelsoft.entity.Team;
import com.opentravelsoft.util.PaginationSupport;

public interface TeamService {
  PaginationSupport getTeamList(long fromRecord, int pageSize);

  Team getTeamDetail(int teamId);

  void deleteTeam(Team team);

  Team updateTeam(Team team);

  Map<String, String> roGetDeptMap();

  /**
   * Market Teams
   * 
   * @return
   */
  public List<Team> getMarketTeam();

  /**
   * Operator Teams
   * 
   * @return
   */
  public List<Team> getOperatorTeam();

}
