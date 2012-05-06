package com.opentravelsoft.action.manage.operate;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Line;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.service.operator.TourPlanService;

/**
 * 出团计划
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:00 $
 */
public class PlanBookingAction extends ManageAction {
  private static final long serialVersionUID = 5212732570107400742L;

  protected static final Log logger = LogFactory
      .getLog(PlanBookingAction.class);

  @Autowired
  private TourPlanService tourPlanService;

  /** 计划编号 */
  private String recordNo;

  private Line route;

  private List<LabelValueBean> deploys;

  private Plan plan;

  private List<Booking> bookings;

  // -------------------------------------------------------------------------
  // 查询条件

  /** 线路名 */
  private String kenLineName;

  /** 部门编号 */
  private String kenTeam;

  /** 专管员 */
  private String kenPrincipal;

  /** 出团时间-开始 */
  private Date kenStartDate;

  /** 出团时间-截止 */
  private Date kenEndDate;

  /**
   * 出团计划详细
   * 
   * @return
   * @throws Exception
   */
  public String input() {
    deploys = getCodeList("ebiz_plan_deploy");
    plan = tourPlanService.roGetPlanDetail(recordNo);

    bookings = tourPlanService.roGetPlanBookings(recordNo);
    if (null == plan) {
      logger.warn("Plan no found.");
      addActionError("Plan no found.");
    }

    return INPUT;
  }

  /**
   * 修改出团计划
   * 
   * @return
   * @throws Exception
   */
  public String submit() {
    Employee user = getUser();
    plan.setOpUser(user.getUserId());

    return SUCCESS;
  }

  protected int getMoveCount() {
    return 20;
  }

  public String getRecordNo() {
    return recordNo;
  }

  public void setRecordNo(String recordNo) {
    this.recordNo = recordNo;
  }

  public Plan getPlan() {
    return plan;
  }

  public void setPlan(Plan plan) {
    this.plan = plan;
  }

  public List<LabelValueBean> getDeploys() {
    return deploys;
  }

  public Line getRoute() {
    return route;
  }

  public void setRoute(Line route) {
    this.route = route;
  }

  public List<Booking> getBookings() {
    return bookings;
  }

  public String getKenRrouteName() {
    return kenLineName;
  }

  public void setKenRrouteName(String kenRrouteName) {
    this.kenLineName = kenRrouteName;
  }

  public String getKenDepartment() {
    return kenTeam;
  }

  public void setKenDepartment(String kenDepartment) {
    this.kenTeam = kenDepartment;
  }

  public String getKenPrincipal() {
    return kenPrincipal;
  }

  public void setKenPrincipal(String kenPrincipal) {
    this.kenPrincipal = kenPrincipal;
  }

  public Date getKenStartDate() {
    return kenStartDate;
  }

  public void setKenStartDate(Date kenStartDate) {
    this.kenStartDate = kenStartDate;
  }

  public Date getKenEndDate() {
    return kenEndDate;
  }

  public void setKenEndDate(Date kenEndDate) {
    this.kenEndDate = kenEndDate;
  }

}
