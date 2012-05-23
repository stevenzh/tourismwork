package com.opentravelsoft.service.setting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.common.TeamType;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.providers.TeamDao;
import com.opentravelsoft.util.PaginationSupport;

@Service("TeamService")
public class TeamServiceImpl implements TeamService {

  @Autowired
  private TeamDao teamDao;

  public void deleteTeam(Team team) {
    teamDao.remove(team.getTeamId());
  }

  public Team getTeamDetail(int teamId) {
    return teamDao.get(teamId);
  }

  public Team updateTeam(Team team) {
    return teamDao.save(team);
  }

  public PaginationSupport getTeamList(long fromRecord, int pageSize) {
    return teamDao.getTeamList(fromRecord, pageSize);
  }

  public Map<String, String> roGetDeptMap() {
    List<Team> dpt = teamDao.getTeamList(TeamType.Operator);
    Map<String, String> ret = new HashMap<String, String>();

    for (Team gropu : dpt) {
      ret.put(String.valueOf(gropu.getTeamId()), gropu.getName());
    }
    return ret;
  }

  public List<Team> getMarketTeam() {
    return teamDao.getTeamList(TeamType.Sales);
  }

  public List<Team> getOperatorTeam() {
    return teamDao.getTeamList(TeamType.Operator);
  }
}
