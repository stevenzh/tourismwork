package com.opentravelsoft.action.manage.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Line;
import com.opentravelsoft.entity.LineSchedule;
import com.opentravelsoft.entity.LineTraffic;
import com.opentravelsoft.service.product.LineScheduleService;

/**
 * 
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:54 $
 */
public class ShowRouteJourneyAction extends ManageAction {
  private static final long serialVersionUID = 7671898914387730451L;

  private LineScheduleService routeScheduleService;

  private Line line;

  private String lineNo;

  /** 线路行程列表 */
  private List<LineTraffic> lineTrafficList = new ArrayList<LineTraffic>();

  private List<LineSchedule> scheduleList = new ArrayList<LineSchedule>();

  @Autowired
  public void setRouteScheduleService(LineScheduleService routeScheduleService) {
    this.routeScheduleService = routeScheduleService;
  }

  public String execute() {
    line = routeScheduleService.findLine(lineNo);
    scheduleList = routeScheduleService.getLineSchedule(lineNo);
    lineTrafficList = routeScheduleService.roGetLineTraffic(lineNo);

    if (scheduleList.size() == 0) {

      for (int i = 1; i <= line.getLineDay(); i++) {
        LineSchedule schedule = new LineSchedule();
        schedule.setDay(i);
        scheduleList.add(schedule);

      }

    }

    if (lineTrafficList.size() == 0) {
      for (int i = 1; i <= scheduleList.size(); i++) {
        LineTraffic traffic = new LineTraffic();
        traffic.setDay(i);
        traffic.setStep(i);
        traffic.setLineNo(lineNo);
        lineTrafficList.add(traffic);
      }
    } else {

      ArrayList<Integer> array = new ArrayList<Integer>();

      for (LineTraffic obj1 : lineTrafficList) {
        if (!array.contains(obj1.getDay())) {
          array.add(obj1.getDay());
        }
      }

      for (LineSchedule obj : scheduleList) {
        if (!array.contains(obj.getDay())) {
          LineTraffic traffic = new LineTraffic();
          traffic.setDay(obj.getDay());
          traffic.setStep(lineTrafficList.size() + 1);
          traffic.setLineNo(lineNo);
          lineTrafficList.add(traffic);
        }
      }

    }

    return SUCCESS;
  }

  public List<LineSchedule> getScheduleList() {
    return scheduleList;
  }

  public void setScheduleList(List<LineSchedule> scheduleList) {
    this.scheduleList = scheduleList;
  }

  public void setLine(Line line) {
    this.line = line;
  }

  public String getLineNo() {
    return lineNo;
  }

  public void setLineNo(String lineNo) {
    this.lineNo = lineNo;
  }

  public List<LineTraffic> getLineTrafficList() {
    return lineTrafficList;
  }

  public void setLineTrafficList(List<LineTraffic> lineTrafficList) {
    this.lineTrafficList = lineTrafficList;
  }

}
