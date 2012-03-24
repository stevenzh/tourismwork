package com.opentravelsoft.providers.mixed;

import java.util.Date;
import java.util.List;

import com.opentravelsoft.util.LabelValueBean;

import com.opentravelsoft.entity.LinePrice;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.PlanPrice;

/**
 * 从数据库取得连路列表
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:23 $
 */
public interface PlanListDao {
  public List<Plan> getPlanList(long rowCount, boolean deadline, String region);

  /**
   * 
   * @param recodeNo
   * @return
   */
  public Plan getPlanDetail(String recordNo);

  /**
   * 
   * 
   * @param lineName 线路名
   * @param groupId 部门
   * @param userId 专管员
   * @param startDate 出团日期-开始
   * @param endDate 出团日期-截止
   * @param destination
   * @param outCity
   * @param openKey
   * @return
   */
  public List<Plan> find(String lineName, long teamId, long userId,
      Date startDate, Date endDate, double lowerPrice, double upperPrice,
      boolean openFlag, String outCity, String destination);

  /**
   * 
   * @param plan
   * @return <tt>0</tt> success, <tt>-1</tt> not exist <tt>-2</tt> can't delete
   */
  public int deletePlan(Plan plan, long userId, String note);

  /**
   * 
   * @param plan
   * @param shareId
   * @return <tt>0</tt> -success, <tt>-1</tt> failure
   */
  public int updatePlan(Plan plan, int shareId, String note);

  public Plan findLastPlan(String lineNo);

  /**
   * 添加出团计划
   * 
   * @param plans
   * @param shareId
   * @return
   */
  public int insertPlan(List<Plan> plans, int shareId);

  public List<Plan> getLinePlans(String lineNo, boolean openFlag,
      boolean after, boolean outDateSort, boolean deadline);

  // -------------------------------------------------------------------------

  public List<LinePrice> getLinePrices(String planNo);

  public List<PlanPrice> getPlanPrices(String planNo);

  public int savePlanPrices(String planNo, List<PlanPrice> list);

  public List<LabelValueBean> searchShare(Date startDate);

  public List<LabelValueBean> searchShare();

  public List<Plan> getPlans(Date outDate, String lineNo);

  /**
   * 重新计算计划中的人数
   * 
   * @param recNo
   * @return
   */
  public int reCountPlanPax(String recNo);

  /**
   * 验证计划中团号是否重复（返回0没有，-1重复）
   */
  public int isTourNoRepeat(String tourNo);

  /**
   * 
   * @param teamId
   * @param userId
   * @return
   */
  public List<Plan> getRunPlans(long teamId, long userId);

  /**
   * 
   * @param districtNo
   * @param startDate
   * @param endDate
   * @return
   */
  public List<Plan> getRouteByDis(String districtNo, Date startDate,
      Date endDate);

  public List<Plan> getRoutePlans(String lineNo, boolean openFlag,
      boolean after, boolean outDateSort, boolean deadline);

  public PlanPrice getPlanFlight(String planNo);

}
