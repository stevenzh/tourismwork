package com.opentravelsoft.service.product;

import java.util.List;

import com.opentravelsoft.util.LabelValueBean;

import com.opentravelsoft.EbizException;
import com.opentravelsoft.common.TeamType;

import com.opentravelsoft.entity.City;
import com.opentravelsoft.entity.Destination;
import com.opentravelsoft.entity.Line;
import com.opentravelsoft.entity.LineDescription;
import com.opentravelsoft.entity.LinePrice;
import com.opentravelsoft.entity.LineSchedule;
import com.opentravelsoft.entity.PortalCategory;
import com.opentravelsoft.entity.LineVisa;
import com.opentravelsoft.entity.Team;

import com.opentravelsoft.util.PaginationSupport;

public interface LineService {
  public List<Team> getTeamList(long userId, TeamType type);

  public List<Team> getOperatorTeams();

  public PaginationSupport findLineList(long teamId, String lineName,
      String isActive, long userId, String kenDestination, int fromRecord,
      int pageSize);

  /**
   * 出发城市列表
   * 
   * @return
   */
  public List<City> getCity();

  /**
   * 取得目的地列表
   * 
   * @return
   */
  public List<Destination> getDestination();

  public List<LabelValueBean> getVehicle();

  public List<PortalCategory> getWebNavigation();

  public String txInsertLine(Line line);

  /**
   * 删除线路
   * 
   * @param lineNo
   * @return
   */
  public int txDeleteLine(String lineNo);

  public int txModifyLine(Line line);

  public String txDuplicateRoute(String lineNo, String newLineName,
      String copyFeature, String copySchedule, String copyPrice,
      String copyDestination, String copySight, String copyVisa, long userId)
      throws EbizException;

  public List<LineSchedule> getLineSchedule(String lineNo);

  public Line getLine(String lineNo);

  public List<LabelValueBean> getNote(String lineNo, String type);

  public List<LinePrice> getPriceNotice(String lineNo);

  public List<LineDescription> getFeatures(String lineNo, String type);

  public List<LineVisa> getVisaList(String lineNo);

  public Line roGetRouteInfo(String lineNo);
}
