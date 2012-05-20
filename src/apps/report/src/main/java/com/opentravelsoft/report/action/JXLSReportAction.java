package com.opentravelsoft.report.action;

import java.sql.Connection;
import java.text.ParseException;
import java.util.Date;
import java.util.ArrayList;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;

import org.efs.openreports.ORStatics;
import org.efs.openreports.actions.QueryReportAction;
import org.efs.openreports.engine.JXLSReportEngine;
import org.efs.openreports.engine.input.ReportEngineInput;
import org.efs.openreports.engine.output.ReportEngineOutput;
import org.efs.openreports.objects.Report;
import org.efs.openreports.objects.ReportLog;
import org.efs.openreports.objects.ReportUser;
import org.efs.openreports.providers.ProviderException;
import org.efs.openreports.providers.ReportProvider;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.util.LabelValueBean;
import com.opentravelsoft.workflow.TaskDao;
import com.opentravelsoft.common.KeyParams;
import com.opentravelsoft.entity.Line;
import com.opentravelsoft.entity.LineSchedule;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.Tourist;
import com.opentravelsoft.providers.ListDao;
import com.opentravelsoft.providers.PlanDao;
import com.opentravelsoft.service.operator.TourPlanService;
import com.opentravelsoft.service.product.LineService;
import com.opentravelsoft.report.util.Parameter;

public class JXLSReportAction extends QueryReportAction {

  private static final long serialVersionUID = 824866564485287929L;

  protected static Logger log = Logger.getLogger(JXLSReportAction.class);

  private PlanDao planDao;

  private TourPlanService tourPlanService;

  private LineService lineService;

  private ListDao listDao;

  private TaskDao taskService;

  private int reportId;

  private ReportProvider reportProvider;

  private List<Parameter> parameters = new ArrayList<Parameter>();

  List<LabelValueBean> birthCitys = new ArrayList<LabelValueBean>();

  private List<Plan> plans = new ArrayList<Plan>();

  private String journey = "";

  @Autowired
  public ListDao getListDao() {
    return listDao;
  }

  @Autowired
  public void setTourPlanService(TourPlanService tourPlanService) {
    this.tourPlanService = tourPlanService;
  }

  @Autowired
  public void setTaskService(TaskDao taskService) {
    this.taskService = taskService;
  }

  @Autowired
  public void setPlanDao(PlanDao planDao) {
    this.planDao = planDao;
  }

  @Autowired
  public void setLineService(LineService lineService) {
    this.lineService = lineService;
  }

  public void setReportProvider(ReportProvider reportProvider) {
    this.reportProvider = reportProvider;
  }

  public String execute() {
    ReportUser user = (ReportUser) ActionContext.getContext().getSession()
        .get(ORStatics.REPORT_USER);

    report = (Report) ActionContext.getContext().getSession()
        .get(ORStatics.REPORT);

    try {
      report = reportProvider.getReport(reportId);
    } catch (ProviderException e) {
      log.error("Report get failure.", e);
    }
    Map<String, Object> param = new HashMap<String, Object>();
    for (int i = 0; i < parameters.size(); i++) {
      Parameter p = parameters.get(i);
      param.put(p.getName(), p.getData());
    }

    if (24 == reportId || 25 == reportId) {
      java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
          "yyyy-MM-dd");
      Date kenStartDate;
      Date kenEndDate;

      try {
        kenStartDate = sdf.parse(param.get("kenStartDate").toString());

        kenEndDate = sdf.parse(param.get("kenEndDate").toString());

        plans = tourPlanService.roFind(param.get("kenRrouteName").toString(),
            Integer.parseInt(param.get("kenDepartment").toString()),
            Integer.parseInt(param.get("kenPrincipal").toString()),
            kenStartDate, kenEndDate, 0f, 0f, false);
      } catch (ParseException e) {
        e.printStackTrace();
      }
      int count = 1;
      for (Plan plan : plans) {

        plan.setCount(count++);
        plan.setZkProfit(plan.getPackagePrice().getPrice()
            .subtract(plan.getPackagePrice().getPriceCost()));
        plan.setTyProfit(plan.getPackagePrice().getPriceOther()
            .subtract(plan.getPackagePrice().getPriceCost()));

      }
      param.put("plans", plans);
    }
    Plan tour = null;
    if (param.containsKey("TOUR_NO")) {
      tour = planDao.getTourInfo((String) param.get("TOUR_NO"), true, false);

      Collections.sort(tour.getCustomerList());
      for (Tourist trip : tour.getCustomerList()) {

        String[] str = trip.getPinYin().split(" ");

        trip.setPinyinsurname(str[0]);
        if (str.length >= 2) {

          trip.setPinyinfirstname(str[1]);
        }

        if (trip.getLeaderKey().equals("T/L")) {
          trip.setIsLeader("是");

        } else {
          trip.setIsLeader("否");
        }

      }
      param.put("tour", tour);
    }

    if (param.containsKey("ROUTE_NO")) {
      String lineNo = (String) (param.get("ROUTE_NO"));
      Line route = lineService.getLine(lineNo);
      route.setSchedule(lineService.getLineSchedule(lineNo));
      route.setFeatures(lineService.getFeatures(lineNo,
          KeyParams.EBIZ_TYPE_LINE_FEATURE));
      route.setExpenseCovered(lineService.getNote(lineNo,
          KeyParams.EBIZ_TYPE_EXPENSE_INC));
      route.setExpenseExcept(lineService.getNote(lineNo,
          KeyParams.EBIZ_TYPE_EXPENSE_EXCEPT));
      route.setAlerts(lineService.getNote(lineNo,
          KeyParams.EBIZ_TYPE_LINE_ALERT));
      route
          .setRules(lineService.getNote(lineNo, KeyParams.EBIZ_TYPE_LINE_RULE));
      route.setVisaList(lineService.getVisaList(lineNo));
      route.setRouteTipsList(lineService.getFeatures(lineNo,
          KeyParams.EBIZ_TYPE_LINE_TIPS));

      List<LineSchedule> schedule = route.getSchedule();
      for (LineSchedule s : schedule) {

        journey += "第" + s.getId().getDay() + "天" + "," + s.getTraffic() + ","
            + s.getProgram() + ";";

      }
      route.setJourney(journey);

      param.put("route", route);

      // -----------------------------------------------------------------------

    }

    if (22 == reportId) {
      param.put("plan", tourPlanService.roGetPlanDetail(tour.getPlanNo()));
    }

    ReportLog reportLog = new ReportLog(user, report, new Date());

    Connection conn = null;

    try {
      log.debug("Starting JXLS Report: " + report.getName());

      reportLogProvider.insertReportLog(reportLog);

      ReportEngineInput input = new ReportEngineInput(report, param);

      JXLSReportEngine reportEngine = new JXLSReportEngine(dataSourceProvider,
          directoryProvider, propertiesProvider);

      ReportEngineOutput output = reportEngine.generateReport(input);

      HttpServletResponse response = ServletActionContext.getResponse();
      response.setContentType("application/vnd.ms-excel; charset=UTF-8");
      response.setHeader("Content-disposition", "inline; filename="
          + StringUtils.deleteWhitespace(report.getName()) + ".xls");

      ServletOutputStream out = response.getOutputStream();

      out.write(output.getContent(), 0, output.getContent().length);

      out.flush();
      out.close();

      reportLog.setEndTime(new Date());
      reportLog.setStatus(ReportLog.STATUS_SUCCESS);
      reportLogProvider.updateReportLog(reportLog);

      log.debug("Finished JRXLS Report: " + report.getName());
    } catch (Exception e) {
      addActionError(e.getMessage());

      log.error(e.getMessage());

      reportLog.setMessage(e.getMessage());
      reportLog.setStatus(ReportLog.STATUS_FAILURE);

      reportLog.setEndTime(new Date());

      try {
        reportLogProvider.updateReportLog(reportLog);
      } catch (Exception ex) {
        log.error("Unable to create ReportLog: " + ex.getMessage());
      }

      return ERROR;
    } finally {
      try {
        if (conn != null)
          conn.close();
      } catch (Exception c) {
        log.error("Error closing");
      }
    }

    // ----------------------------------------------------------------------
    // Workflow
    if (22 == reportId) {
      taskService.flowMethod(tour.getPlanNo());
    }

    return NONE;
  }

  public List<Parameter> getParameters() {
    return parameters;
  }

  public void setParameters(List<Parameter> parameters) {
    this.parameters = parameters;
  }

  public int getReportId() {
    return reportId;
  }

  public void setReportId(int reportId) {
    this.reportId = reportId;
  }

  public List<LabelValueBean> getBirthCitys() {
    return birthCitys;
  }

  public void setBirthCitys(List<LabelValueBean> birthCitys) {
    this.birthCitys = birthCitys;
  }

  public List<Plan> getPlans() {
    return plans;
  }

  public void setPlans(List<Plan> plans) {
    this.plans = plans;
  }

}