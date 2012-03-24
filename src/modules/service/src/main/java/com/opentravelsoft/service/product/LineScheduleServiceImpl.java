package com.opentravelsoft.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.entity.Line;
import com.opentravelsoft.entity.LineSchedule;
import com.opentravelsoft.entity.LineTraffic;
import com.opentravelsoft.providers.product.LineDao;
import com.opentravelsoft.providers.product.LineScheduleDao;

@Service("RouteScheduleService")
public class LineScheduleServiceImpl implements LineScheduleService {

  private LineScheduleDao routeScheduleDao;

  private LineDao routeDao;

  @Autowired
  public void setRouteDao(LineDao routeDao) {
    this.routeDao = routeDao;
  }

  @Autowired
  public void setRouteScheduleDao(LineScheduleDao routeScheduleDao) {
    this.routeScheduleDao = routeScheduleDao;
  }

  public int txDeleteLineSchedule(LineSchedule schedule) {
    return routeScheduleDao.deleteLineSchedule(schedule);
  }

  public List<LineSchedule> getLineSchedule(String lineNo) {
    return routeScheduleDao.getLineSchedule(lineNo);
  }

  public int txInsertLineSchedule(String lineNo) {
    return routeScheduleDao.insertLineSchedule(lineNo);
  }

  public int txSaveLineSchedule(List<LineSchedule> list, List<LineTraffic> list1) {
    return routeScheduleDao.saveLineSchedule(list, list1);
  }

  public Line findLine(String lineNo) {
    return routeDao.get(lineNo);
  }

  public List<LineTraffic> roGetLineTraffic(String lineNo) {
    return routeScheduleDao.getLineTraffic(lineNo);
  }
}
