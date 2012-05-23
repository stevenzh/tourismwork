package com.opentravelsoft.service.product;

import java.util.ArrayList;
import java.util.List;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

import com.opentravelsoft.providers.CityDao;
import com.opentravelsoft.providers.DestinationDao;
import com.opentravelsoft.providers.ListDao;
import com.opentravelsoft.providers.SequenceDao;
import com.opentravelsoft.providers.TeamDao;
import com.opentravelsoft.providers.product.LineDao;
import com.opentravelsoft.providers.product.LinePriceDao;
import com.opentravelsoft.providers.product.LineScheduleDao;
import com.opentravelsoft.providers.product.LineTraitDao;
import com.opentravelsoft.providers.product.LineVisaDao;

import com.opentravelsoft.util.PaginationSupport;

@Service("RouteService")
public class LineServiceImpl implements LineService {

  @Autowired
  private LineDao lineDao;

  @Autowired
  private CityDao cityDao;

  @Autowired
  private ListDao listDao;

  @Autowired
  private SequenceDao sequenceDao;

  @Autowired
  private DestinationDao destinationDao;

  @Autowired
  private LineTraitDao lineTraitDao;

  @Autowired
  private LineScheduleDao lineScheduleDao;

  @Autowired
  private LineVisaDao lineVisaDao;

  @Autowired
  private LinePriceDao linePriceDao;

  @Autowired
  private TeamDao teamDao;

  public PaginationSupport findLineList(int teamId, String lineName,
      String isActive, int userId, String kenDestination, long fromRecord,
      int pageSize) {
    return lineDao.findLineList(teamId, lineName, isActive, userId,
        kenDestination, fromRecord, pageSize);
  }

  public List<City> getCity() {
    return cityDao.getLineOutCity();
  }

  public List<Destination> getDestination() {
    return destinationDao.getAllDestination();
  }

  public List<LabelValueBean> getVehicle() {
    return listDao.getList("Vehicle");
  }

  public List<PortalCategory> getWebNavigation() {
    return lineDao.getWebNavigation();
  }

  public List<Team> getTeamList(int userId, TeamType type) {
    return teamDao.getTeam(userId, type);
  }

  public List<Team> getOperatorTeams() {
    return teamDao.getTeamList(TeamType.Operator);
  }

  public String txInsertLine(Line line) {
    String lineNo = sequenceDao.getComputerNo("T", line.getCreateUserId());
    line.setLineNo(lineNo);
    lineDao.save(line);
    return lineNo;
  }

  public int txModifyLine(Line line) {
    return lineDao.updateLine(line);
  }

  public String txDuplicateRoute(String lineNo, String newRouteName,
      String copyFeature, String copySchedule, String copyPrice,
      String copyDestination, String copySight, String copyVisa, int userId)
      throws EbizException {
    String newLineNo = sequenceDao.getComputerNo("T", userId);

    int result = lineDao.duplicateRoute(lineNo, newLineNo, newRouteName,
        copyFeature, copySchedule, copyPrice, copyDestination, copySight,
        copyVisa, userId);

    if (result >= 0)
      return newLineNo;
    else {
      throw new EbizException("");
    }
  }

  public int txDeleteLine(String lineNo) {
    return lineDao.cancelLine(lineNo);
  }

  public List<LineSchedule> getLineSchedule(String lineNo) {
    return lineScheduleDao.getLineSchedule(lineNo);
  }

  public Line getLine(String lineNo) {
    return lineDao.get(lineNo);
  }

  public List<LabelValueBean> getNote(String lineNo, String type) {
    List<LineDescription> list = lineTraitDao.getLineTrait(lineNo, type);
    List<LabelValueBean> ret = new ArrayList<LabelValueBean>();

    int idx = 1;
    for (LineDescription obj : list) {
      ret.add(new LabelValueBean(String.valueOf(idx++), obj.getDescription()));
    }

    return ret;
  }

  public List<LinePrice> getPriceNotice(String lineNo) {
    return linePriceDao.getLinePrice(lineNo, null, null);
  }

  public List<LineDescription> getFeatures(String lineNo, String type) {
    return lineTraitDao.getLineTrait(lineNo, type);
  }

  public List<LineVisa> getVisaList(String lineNo) {
    return lineVisaDao.getVisaList(lineNo);
  }

  public Line roGetRouteInfo(String lineNo) {
    Line route = lineDao.get(lineNo);
    route.setSchedule(lineScheduleDao.getLineSchedule(lineNo));
    return route;
  }
}
