package com.opentravelsoft.action.manage.product;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.common.SessionKeyParams;
import com.opentravelsoft.entity.Line;
import com.opentravelsoft.entity.LineSchedule;
import com.opentravelsoft.entity.LineTraffic;
import com.opentravelsoft.service.product.LineScheduleService;
import com.opentravelsoft.util.StringUtil;

/**
 * 线路行程
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:54 $
 */
public class LineScheduleAction extends ManageAction {
  private static final long serialVersionUID = 7671898914387730451L;

  private LineScheduleService routeScheduleService;

  private Line line;

  private String lineNo;

  private int day;

  /** 交通说明 */
  private String traffic;

  /** 日程 */
  private String program;

  /** 交通 */
  private String traffic1;

  /** 早餐 */
  private String breakfast;

  /** 午餐 */
  private String lunch;

  /** 晚餐 */
  private String supper;

  /** 住宿餐列表 */
  private List<LabelValueBean> eatList;

  /** 交通列表 */
  private List<LabelValueBean> traffic1List;

  /** 线路行程列表 */
  private List<LineTraffic> lineTrafficList = new ArrayList<LineTraffic>();

  private List<LineSchedule> scheduleList = new ArrayList<LineSchedule>();

  @Autowired
  public void setRouteScheduleService(LineScheduleService routeScheduleService) {
    this.routeScheduleService = routeScheduleService;
  }

  public String execute() {
    Map<String, Object> session = ActionContext.getContext().getSession();
    line = (Line) session.get(SessionKeyParams.EBIZ_CURRENT_ROUTE);
    lineNo = line.getLineNo();
    scheduleList = routeScheduleService.getLineSchedule(line.getLineNo());
    lineTrafficList = routeScheduleService.roGetLineTraffic(line.getLineNo());

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
        traffic.setLineNo(line.getLineNo());
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
          traffic.setLineNo(line.getLineNo());
          lineTrafficList.add(traffic);
        }
      }

    }

    lineTrafficList = sortByStep(lineTrafficList);
    eatList = getSysList("DOM_LineDine");
    traffic1List = getSysList("DOM_LineTraffic");
    return SUCCESS;
  }

  public String add() {
    scheduleList = sort(scheduleList);
    int day = 1;
    for (LineSchedule obj : scheduleList) {
      obj.setDay(day++);
    }

    // -----------------------------------------------------------
    LineSchedule schedule = new LineSchedule();
    schedule.setDay(day);
    scheduleList.add(schedule);

    LineTraffic traffic = new LineTraffic();
    traffic.setDay(day);
    traffic.setStep(lineTrafficList.size() + 1);
    traffic.setLineNo(lineNo);
    lineTrafficList.add(traffic);

    lineTrafficList = sortByStep(lineTrafficList);
    for (int i = lineTrafficList.size(); i > 0; i--) {
      LineTraffic tra = lineTrafficList.get(i - 1);
      if (tra.getDay() > day) {
        tra.setStep(tra.getStep() + 1);
      }
    }

    eatList = getSysList("DOM_LineDine");
    traffic1List = getSysList("DOM_LineTraffic");
    return SUCCESS;
  }

  public String addCity() {
    int days = 1;
    scheduleList = sort(scheduleList);
    lineTrafficList = sort1(lineTrafficList);
    for (LineTraffic obj : lineTrafficList) {
      obj.setDay(days++);
    }

    lineTrafficList = sortByStep(lineTrafficList);

    int inpoint = 0;
    for (int i = lineTrafficList.size(); i > 0; i--) {
      LineTraffic tra = lineTrafficList.get(i - 1);
      if (tra.getDay() > day) {
        tra.setStep(tra.getStep() + 1);
      } else if (tra.getDay() == day) {
        inpoint = tra.getStep() + 1;
        break;
      }
    }

    LineTraffic traffic = new LineTraffic();
    traffic.setDay(day);
    traffic.setStep(inpoint);
    traffic.setLineNo(lineNo);
    lineTrafficList.add(traffic);

    lineTrafficList = sortByStep(lineTrafficList);
    eatList = getSysList("DOM_LineDine");
    traffic1List = getSysList("DOM_LineTraffic");
    return SUCCESS;

  }

  public String delete() {
    line = (Line) ActionContext.getContext().getSession()
        .get(SessionKeyParams.EBIZ_CURRENT_ROUTE);
    for (LineSchedule obj : scheduleList) {
      if (obj.getDay() == day) {
        LineSchedule schedule = new LineSchedule();
        schedule.setDay(day);
        schedule.setRouteNo(line.getLineNo());

        routeScheduleService.txDeleteLineSchedule(schedule);
        scheduleList.remove(obj);
        break;
      }
    }

    for (int i = lineTrafficList.size(); i > 0; i--) {
      LineTraffic obj = lineTrafficList.get(i - 1);
      if (obj.getDay() == day)
        lineTrafficList.remove(obj);
    }

    // -----------------------------------------------------------
    lineTrafficList = sortByStep(lineTrafficList);

    for (int i = lineTrafficList.size(); i > 0; i--) {
      LineTraffic tra = lineTrafficList.get(i - 1);
      tra.setLineNo(lineNo);
      if (tra.getDay() > day) {
        tra.setStep(tra.getStep() + 1);
        tra.setDay(tra.getDay() - 1);
      }
    }

    // ---------------------------------------------------------------------
    scheduleList = sort(scheduleList);
    int day1 = 1;
    for (LineSchedule obj : scheduleList) {
      obj.setRouteNo(lineNo);
      obj.setDay(day1++);
    }

    routeScheduleService.txSaveLineSchedule(scheduleList, lineTrafficList);

    eatList = getSysList("DOM_LineDine");
    traffic1List = getSysList("DOM_LineTraffic");

    return SUCCESS;
  }

  public String save() {
    for (LineSchedule obj : scheduleList) {
      obj.setRouteNo(lineNo);
    }

    for (LineTraffic obj1 : lineTrafficList) {
      obj1.setLineNo(lineNo);
    }

    lineTrafficList = sort2(lineTrafficList);
    routeScheduleService.txSaveLineSchedule(scheduleList, lineTrafficList);
    addActionMessage("行程保存成功.");

    eatList = getSysList("DOM_LineDine");
    traffic1List = getSysList("DOM_LineTraffic");

    return SUCCESS;
  }

  @Override
  public void validate() {
    try {
      for (int i = 0; i < scheduleList.size(); i++) {
        LineSchedule trait = scheduleList.get(i);
        String value = trait.getProgram();

        if (value.getBytes(ENCODEING).length > 3000) {
          addFieldError("描述", "第" + (i + 1) + "行 描述文字长度超过1500汉字或3000英数字.");
        }
      }
    } catch (UnsupportedEncodingException e) {
    }
  }

  private List<LineSchedule> sort(List<LineSchedule> scheduleList) {
    List<LineSchedule> list = new ArrayList<LineSchedule>();
    int maxDay = 1;
    for (LineSchedule schedule : scheduleList) {
      if (schedule.getDay() > maxDay)
        maxDay = schedule.getDay();
    }
    for (int i = 0; i < maxDay; i++) {
      for (int j = 0; j < scheduleList.size(); j++) {
        LineSchedule obj = scheduleList.get(j);
        if (obj.getDay() == i + 1) {
          list.add(obj);
          break;
        }
      }
    }
    return list;
  }

  private List<LineTraffic> sort1(List<LineTraffic> lineTrafficList) {
    List<LineTraffic> list = new ArrayList<LineTraffic>();
    int maxStep = 1;
    for (LineTraffic traffic : lineTrafficList) {
      if (traffic.getStep() > maxStep)
        maxStep = traffic.getStep();
    }

    for (int i = 0; i < maxStep; i++) {
      for (int j = 0; j < lineTrafficList.size(); j++) {
        LineTraffic obj = lineTrafficList.get(j);
        if (obj.getStep() == i + 1) {
          list.add(obj);
          break;
        }

      }
    }
    return list;
  }

  private List<LineTraffic> sort2(List<LineTraffic> lineTrafficList) {
    List<LineTraffic> list = new ArrayList<LineTraffic>();

    // 删除离开城市和抵达城市都为空
    for (int j = lineTrafficList.size(); j > 0; j--) {
      LineTraffic obj = lineTrafficList.get(j - 1);
      if (!StringUtil.hasLength(obj.getFromCity())
          && !StringUtil.hasLength(obj.getToCity())) {
        lineTrafficList.remove(obj);
      }
    }

    int step = 1;
    for (int i = 0; i < lineTrafficList.size() * 2; i++) {
      for (int j = 0; j < lineTrafficList.size(); j++) {
        LineTraffic obj = lineTrafficList.get(j);
        if (obj.getStep() == i + 1) {
          obj.setStep(step);
          list.add(obj);
          step++;
          break;
        }
      }
    }
    return list;
  }

  private List<LineTraffic> sortByStep(List<LineTraffic> lineTrafficList) {
    List<LineTraffic> list = new ArrayList<LineTraffic>();

    int step = 1;
    for (int i = 0; i < lineTrafficList.size() * 2; i++) {
      for (int j = 0; j < lineTrafficList.size(); j++) {
        LineTraffic obj = lineTrafficList.get(j);
        if (obj.getStep() == i + 1) {
          obj.setStep(step);
          list.add(obj);
          step++;
          break;
        }
      }
    }
    return list;
  }

  public int getDay() {
    return day;
  }

  public void setDay(int day) {
    this.day = day;
  }

  public String getProgram() {
    return program;
  }

  public void setProgram(String program) {
    this.program = program;
  }

  public List<LineSchedule> getScheduleList() {
    return scheduleList;
  }

  public void setScheduleList(List<LineSchedule> scheduleList) {
    this.scheduleList = scheduleList;
  }

  public String getTraffic() {
    return traffic;
  }

  public void setTraffic(String traffic) {
    this.traffic = traffic;
  }

  public Line getLine() {
    return line;
  }

  public String getLineNo() {
    return lineNo;
  }

  public void setLineNo(String lineNo) {
    this.lineNo = lineNo;
  }

  public String getTraffic1() {
    return traffic1;
  }

  public void setTraffic1(String traffic1) {
    this.traffic1 = traffic1;
  }

  public String getBreakfast() {
    return breakfast;
  }

  public void setBreakfast(String breakfast) {
    this.breakfast = breakfast;
  }

  public String getLunch() {
    return lunch;
  }

  public void setLunch(String lunch) {
    this.lunch = lunch;
  }

  public String getSupper() {
    return supper;
  }

  public void setSupper(String supper) {
    this.supper = supper;
  }

  public List<LabelValueBean> getEatList() {
    return eatList;
  }

  public List<LabelValueBean> getTraffic1List() {
    return traffic1List;
  }

  public void setTraffic1List(List<LabelValueBean> traffic1List) {
    this.traffic1List = traffic1List;
  }

  public List<LineTraffic> getLineTrafficList() {
    return lineTrafficList;
  }

  public void setLineTrafficList(List<LineTraffic> lineTrafficList) {
    this.lineTrafficList = lineTrafficList;
  }

}
