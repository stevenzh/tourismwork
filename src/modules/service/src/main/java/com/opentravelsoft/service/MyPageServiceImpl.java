package com.opentravelsoft.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.entity.Express;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.providers.ExpressDao;
import com.opentravelsoft.providers.PlanDao;
import com.opentravelsoft.providers.mixed.PlanListDao;
import com.opentravelsoft.workflow.TaskDao;

@Service("MyPageService")
public class MyPageServiceImpl implements MyPageService {

  private ExpressDao expressDao;

  private PlanListDao planListDao;

  private PlanDao tourDao;

  private TaskDao taskService;

  @Autowired
  public void setPlanDao(PlanDao tourDao) {
    this.tourDao = tourDao;
  }

  @Autowired
  public void setPlanListDao(PlanListDao planListDao) {
    this.planListDao = planListDao;
  }

  @Autowired
  public void setTaskService(TaskDao taskService) {
    this.taskService = taskService;
  }

  @Autowired
  public void setExpressDao(ExpressDao expressDao) {
    this.expressDao = expressDao;
  }

  public List<Express> roGetExpressTask(String taskName) {
    return expressDao.getExpressTaskList(taskName);
  }

  public List<Plan> roGetPlanList(long teamId, long userId) {
    return planListDao.getRunPlans(teamId, userId);
  }

  public List<Plan> roGetTours(long teamId, long userId, String lineName,
      Date startDate, Date endDate) {
    return tourDao.getTours(teamId, userId, lineName, startDate, endDate);
  }

}
