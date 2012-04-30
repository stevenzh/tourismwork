package com.opentravelsoft.action.manage.setting;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.service.setting.TeamService;
import com.opentravelsoft.util.PaginationSupport;

/**
 * Team 维护
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:03 $
 */
public class EditTeamAction extends ManageAction {
  private static final long serialVersionUID = 4025088482007685362L;

  protected static final Log logger = LogFactory.getLog(EditTeamAction.class);

  @Autowired
  private TeamService teamService;

  private List<Team> teamList;

  private Team team = new Team();

  public String input() {
    dreamPage();
    PaginationSupport support = teamService.getTeamList(getFromRecord(),
        getMoveCount());
    teamList = support.getItems();
    currentPage(support.getTotalCount());

    return INPUT;
  }

  public String edit() {
    if (team.getTeamId() != 0)
      team = teamService.getTeamDetail(team.getTeamId());
    return INPUT;
  }

  public String delete() {
    Team fm = teamService.getTeamDetail(team.getTeamId());
    if (null == fm) {
      // 要删除的记录不存在
      addActionError("ERR_A10001");
    } else {
      teamService.deleteTeam(team);
    }

    return SUCCESS;
  }

  public String save() {
    teamService.updateTeam(team);

    return SUCCESS;
  }

  public String detail() {
    team = teamService.getTeamDetail(team.getTeamId());
    return SUCCESS;
  }

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  public List<Team> getTeamList() {
    return teamList;
  }
}
