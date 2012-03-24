package com.opentravelsoft.service.operator;

import java.util.Date;
import java.util.List;

import com.opentravelsoft.util.LabelValueBean;

import com.opentravelsoft.common.TeamType;
import com.opentravelsoft.entity.Airways;
import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.Line;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.PlanPrice;
import com.opentravelsoft.entity.Team;

public interface TourPlanService {
  public List<Plan> roFind(String lineName, long teamId, long userId,
      Date startDate, Date endDate, double lowerPrice, double upperPrice,
      boolean openKey);

  public Plan roGetPlanDetail(String recordNo);

  /**
   * 
   * @param plan
   * @return <tt>0</tt> success, <tt>-1</tt> not exist <tt>-2</tt> can't delete
   */
  public int txDeletePlan(Plan plan, long userId, String note);

  public Plan findLastPlan(String lineNo);

  public List<Plan> roGetLinePlans(String lineNo, boolean openFlag,
      boolean after, boolean outDateSort, boolean deadline);

  /**
   * 
   * @param plan
   * @param endDate
   * @param startDate
   * @param planFlights
   * @return <tt>1</tt> no price, deploy failure, <tt>0</tt> -success,
   *         <tt>-1</tt> failure
   */
  public int txInsertPlan(List<Plan> plan, Date startDate, Date endDate,
      List<PlanPrice> planFlights, int shareId);

  /**
   * Update the plan
   * 
   * @param plan
   * @param planFlights
   * @return <tt>1</tt> no price, deploy failure, <tt>0</tt> -success,
   *         <tt>-1</tt> failure
   */
  public int txUpdatePlan(Plan plan, List<PlanPrice> planFlights, int shareId,
      String note);

  public List<Booking> roGetPlanBookings(String recordNo);

  /**
   * @param planNo
   * @return
   */
  public List<PlanPrice> roGetPrices(String planNo);

  public Line roGetLine(String lineNo);

  public List<LabelValueBean> roSearchShare();

  public int txReCountPlanPax(String recordNo);

  public int roIsTourNoRepeat(String tourNo);

  /**
   * 取得所有推广
   * 
   * @return
   */
  public List<LabelValueBean> roGetTraitList();

  public List<Team> getOperatorTeamList();

  public List<Team> getTeamList(long userId, TeamType type);

  public List<Airways> roGetAirways();

}
