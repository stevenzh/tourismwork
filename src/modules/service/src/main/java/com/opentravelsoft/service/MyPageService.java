package com.opentravelsoft.service;

import java.util.Date;
import java.util.List;

import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.Express;
import com.opentravelsoft.entity.Plan;

public interface MyPageService {
  /**
   * 
   * @param taskName
   * @return
   */
  public List<Express> roGetExpressTask(String taskName);

  /**
   * 可以成团的计划
   * 
   * @param userId
   * @return
   */
  // public List<Plan> roGetCanBuild(String userId);

  /**
   * 需要安排领队的团
   * 
   * @return
   */
  // public List<Plan> roGetTaskList(String taskName, String userId);

  /**
   * OP需要处理的计划（今天之后的所有计划）
   * 
   * @param teamId
   * @param userId
   * @return
   */
  public List<Plan> roGetPlanList(long teamId, long userId);

  /**
   * 财务需要确认的核算
   * 
   * @return
   */
  public List<Plan> roGetTours(long teamId, long userId, String lineName,
      Date startDate, Date endDate);

  // public List<Booking> roGetOrderTask(String teamId, String userId,
  // String taskName);

}
