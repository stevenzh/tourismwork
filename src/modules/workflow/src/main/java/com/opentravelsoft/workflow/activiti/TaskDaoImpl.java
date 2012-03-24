package com.opentravelsoft.workflow.activiti;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.Express;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.finance.Outcome;
import com.opentravelsoft.workflow.TaskDao;
import com.opentravelsoft.workflow.WorkFlowKeyParams;

@Repository("TaskService")
public class TaskDaoImpl extends HibernateDaoSupport implements TaskDao {

  private ProcessEngine processEngine;

  public void init(SessionFactory factory) {
    setSessionFactory(factory);
  }

  @Autowired
  public void setProcessEngine(ProcessEngine processEngine) {
    this.processEngine = processEngine;
  }

  public void supplyJobs() {
    logger.debug("scheduling job starting...");

    StringBuilder sb = new StringBuilder();
    sb.append("from com.opentravelsoft.entity.Express ");
    sb.append("where workflowId is null ");
    List<Express> list = getHibernateTemplate().find(sb.toString());

    logger.debug(list.size() + "个新配送单.");
    for (Express express : list) {
      final String order_sn = express.getOrderId();
      final String expressId = express.getExpressId();
      String inAccount = "False";
      String inVisa = "False";

      if (express.getExpressState().equals("1")
          || express.getExpressState().equals("2")) {
        inAccount = "True";
      }
      if (express.getExpressState().equals("0")
          || express.getExpressState().equals("2")) {
        inVisa = "True";
      }

      // -----------------------------------------------------------------
      Map<String, Object> map = new HashMap<String, Object>();
      // 订单ID
      map.put(WorkFlowKeyParams.WORKFLOW_ORDER_ID, order_sn);
      // 配送单ID
      map.put(WorkFlowKeyParams.WORKFLOW_EXPRESS_ID, expressId);
      // 是否包含团款
      map.put(WorkFlowKeyParams.WORKFLOW_EXPRESS_IN_ACCOUNT, inAccount);
      // 是否包含签证材料
      map.put(WorkFlowKeyParams.WORKFLOW_EXPRESS_IN_VISA, inVisa);

      ProcessInstance pi = processEngine.getRuntimeService()
          .startProcessInstanceByKey("expressWorkflow", map);

      express.setWorkflowId(pi.getId());
      processEngine.getRuntimeService().createExecutionQuery().singleResult();
      getHibernateTemplate().update(express);
    }

    sb = new StringBuilder();
    sb.append("from com.opentravelsoft.entity.Booking ");
    sb.append("where readKey!='Y' and workflowId is null ");
    List<Booking> list2 = getHibernateTemplate().find(sb.toString());

    logger.debug(list2.size() + "个新订单.");

    for (Booking order : list2) {
      Map<String, Object> map = new HashMap<String, Object>();
      // 订单号
      map.put("order", order);
      ProcessInstance pi = processEngine.getRuntimeService()
          .startProcessInstanceByKey("reserveOrder", map);
      order.setWorkflowId(pi.getId());
      getHibernateTemplate().update(order);
    }

    logger.debug("scheduling job end.");
  }

  @Override
  public List<Task> getMyTask(String userId) {
    return processEngine.getTaskService().createTaskQuery()
        .taskAssignee(userId).list();
  }

  @Override
  public int callMakePlan(String planNo, String userId) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put(WorkFlowKeyParams.WORKFLOW_PLAN_NO, planNo);

    processEngine.getRuntimeService().startProcessInstanceByKey("tourWorkFlow",
        map);
    return 0;
  }

  @Override
  public int callMakeTourAccounts(String planNo, String userId) {
    TaskService taskService = processEngine.getTaskService();
    Plan plan = getHibernateTemplate().get(Plan.class, planNo);
    List<Task> tasks = taskService.createTaskQuery()
        .processInstanceId(plan.getWorkflowId()).list();

    for (Task task2 : tasks) {
      if (task2.getName().equals(WorkFlowKeyParams.CALCULATION_TASK_MAKE))
        taskService.complete(task2.getId());
    }

    return 0;
  }

  @Override
  public int callAuditTourAccounts(String planNo, String userId) {
    TaskService taskService = processEngine.getTaskService();
    Plan plan = getHibernateTemplate().get(Plan.class, planNo);
    Task task = taskService.createTaskQuery()
        .processInstanceId(plan.getWorkflowId()).singleResult();

    if (task.getName().equals(
        WorkFlowKeyParams.FINANCE_CALCULATION_TASK_AUDITING))
      taskService.complete(task.getId());

    return 0;
  }

  public int checkTourAccounts(String planNo, String uid) {

    TaskService taskService = processEngine.getTaskService();
    Plan plan = getHibernateTemplate().get(Plan.class, planNo);
    Task task = taskService.createTaskQuery()
        .processInstanceId(plan.getWorkflowId()).singleResult();

    if (task.getName().equals(WorkFlowKeyParams.CALCULATION_TASK_AUDITING))
      taskService.complete(task.getId());

    return 0;
  }

  public List<Task> getTourTaskList(String planNo) {
    TaskService taskService = processEngine.getTaskService();
    Plan plan = getHibernateTemplate().get(Plan.class, planNo);
    List<Task> list = taskService.createTaskQuery()
        .processInstanceId(plan.getWorkflowId()).list();

    return list;
  }

  // public List<Plan> getCanBuild(String userId) {
  // // TaskService taskService = processEngine.getTaskService();
  // // List<Task> list = taskService.createTaskQuery()
  // // .taskName(WorkFlowKeyParams.TOUR_TASK_GROUPTOUR).taskAssignee(userId)
  // // .list();
  // // return list;
  // StringBuilder sb = new StringBuilder();
  // sb.append("select p ");
  // sb.append("from com.opentravelsoft.entity.Plan p, ");
  // sb.append("org.jbpm.pvm.internal.task.Task t ");
  // sb.append("where t.executionId=p.workflowId ");
  // sb.append("and p.line.assigned.userId=? and t.name=? ");
  // // sb.append("and t.state!='" + Task.STATE_COMPLETED + "' ");
  // sb.append("and p.isBuildup='N' "); // 未成团
  // Object[] params = { userId, WorkFlowKeyParams.TOUR_TASK_GROUPTOUR };
  // List<Plan> list = getHibernateTemplate().find(sb.toString(), params);
  // return list;
  // }

  // @Override
  // public int callSaveBooking(Booking book) {
  // String enabled = (String) ActionContext.getContext().getApplication()
  // .get(EbizCommon.WORKFLOW_ENABLED);
  // if (enabled.equals("1")) {
  // Map<String, Object> vals = new HashMap<String, Object>();
  // vals.put("order", book);
  // ProcessInstance intd = processEngine.getRuntimeService()
  // .startProcessInstanceByKey("reserveOrder", vals);
  // book.setWorkflowId(intd.getId());
  // if (canBuild && enabled.equals("1")) {
  // // 如果预订总人数 >= 计划的最小成团人数 就可以生成成团任务
  // StringBuilder sb1 = new StringBuilder();
  // sb1.append("select t ");
  // sb1.append("from org.jbpm.pvm.internal.task.Task t ");
  // sb1.append(",com.opentravelsoft.entity.Plan p ");
  // sb1.append("where t.executionId=p.workflowId and p.planNo=? ");
  // sb1.append("and t.name=? and t.state!='" + Task.STATE_COMPLETED + "' ");
  // Object[] params1 = { book.getPlan().getPlanNo(),
  // WorkFlowKeyParams.TOUR_TASK_AUTO_BOOK };
  // List<Task> taskIns = getHibernateTemplate().find(sb1.toString(),
  // params1);
  // for (Task taskInstance : taskIns) {
  // processEngine.getTaskService().complete(taskInstance.getId());
  // } } }
  // return 1;
  // }

  @Override
  public int callConfirmBooking(Booking book) {
    Booking book1 = getHibernateTemplate().get(Booking.class,
        book.getBookingNo());
    TaskService taskService = processEngine.getTaskService();
    List<Task> list = taskService.createTaskQuery()
        .processInstanceId(book1.getWorkflowId()).list();

    for (Task task2 : list) {
      if (task2.getName().equals(WorkFlowKeyParams.ORDER_TASK_CONFIRM))
        taskService.complete(task2.getId());
    }

    return 0;
  }

  public List<Task> getOrderTaskList(String bookingNo) {
    Booking book1 = getHibernateTemplate().get(Booking.class, bookingNo);
    TaskService taskService = processEngine.getTaskService();
    List<Task> list = taskService.createTaskQuery()
        .processInstanceId(book1.getWorkflowId()).list();

    return list;
  }

  // @Override
  // public List<Booking> getOrderTaskList(String teamId, String userId,
  // String taskName) {
  // StringBuilder sb = new StringBuilder();
  // sb.append("select b ");
  // sb.append("from com.opentravelsoft.entity.Booking b");
  // sb.append(",org.jbpm.pvm.internal.task.Task t ");
  // sb.append("where t.executionId=b.workflowId ");
  // // sb.append("and t.name=? and t.state<>'" + Task.STATE_COMPLETED + "' ");
  // sb.append("and b.plan.assigned.userId=? ");
  // Object[] params = { taskName, userId };
  // List<Booking> list = getHibernateTemplate().find(sb.toString(), params);
  // return list;
  // }

  @Override
  public List<Task> getExpressTask(String expressId) {
    Express express = getHibernateTemplate().get(Express.class, expressId);
    TaskService taskService = processEngine.getTaskService();
    List<Task> list = taskService.createTaskQuery()
        .processInstanceId(express.getWorkflowId()).list();

    return list;
  }

  // public List<Plan> getTaskList(String taskName, String userId) {
  // StringBuilder sb = new StringBuilder();
  // List<String> params = new ArrayList<String>();
  // sb.append("select p.planNo,p.outDate ");
  // sb.append("from com.opentravelsoft.entity.Plan p, ");
  // sb.append("org.jbpm.pvm.internal.task.Task t ");
  // sb.append("where p.workflowId=t.executionId and t.name=? ");
  // // sb.append("and t.state!='" + Task.STATE_COMPLETED + "' ");
  // params.add(taskName);
  // // 线路专管员
  // if (StringUtil.hasLength(userId)) {
  // sb.append("and t.assigned.userId=? ");
  // params.add(String.valueOf(userId));
  // }
  // sb.append("order by p.outDate ");
  // String[] param = null;
  // if (params.size() > 0) {
  // param = new String[params.size()];
  // for (int i = 0; i < params.size(); i++) {
  // param[i] = params.get(i);
  // }
  // }
  // List<Object[]> list = getHibernateTemplate().find(sb.toString(), param);
  // List<Plan> res = new ArrayList<Plan>();
  // for (Object[] obj : list) {
  // Plan tour = new Plan();
  // tour.setTourNo(RowDataUtil.getString(obj[0]));
  // tour.setOutDate(RowDataUtil.getDate(obj[1]));
  // res.add(tour);
  // }
  // return res;
  // }

  @Override
  public int callArrangeLeader(String planNo, String userId) {
    TaskService taskService = processEngine.getTaskService();
    Plan plan = getHibernateTemplate().get(Plan.class, planNo);
    List<Task> list = taskService.createTaskQuery()
        .processInstanceId(plan.getWorkflowId())
        .taskName(WorkFlowKeyParams.TOUR_TASK_ARRANGE_LEADER).list();

    for (Task taskInstance : list) {
      processEngine.getTaskService().complete(taskInstance.getId());
    }
    return 0;
  }

  public void flowMethod(String planNo) {
    TaskService taskService = processEngine.getTaskService();
    Plan plan = getHibernateTemplate().get(Plan.class, planNo);
    List<Task> list = taskService.createTaskQuery()
        .processInstanceId(plan.getWorkflowId())
        .taskName(WorkFlowKeyParams.TOUR_TASK_OUTTOURNOTIFY).list();

    for (Task taskInstance : list) {
      Map<String, Object> map = new HashMap<String, Object>();
      map.put(WorkFlowKeyParams.WORKFLOW_TOUR_NO, planNo);
      map.put(WorkFlowKeyParams.WORKFLOW_ACTOR, "");
      map.put(WorkFlowKeyParams.WORKFLOW_PLAN_NO, planNo);

      processEngine.getTaskService().complete(taskInstance.getId(), map);
    }
  }

  @Override
  public int callMakeIncome(String bookingNo, String userId) {
    Booking book1 = getHibernateTemplate().get(Booking.class, bookingNo);
    TaskService taskService = processEngine.getTaskService();
    List<Task> list = taskService.createTaskQuery()
        .processInstanceId(book1.getWorkflowId())
        .taskName(WorkFlowKeyParams.ORDER_AGREEON).list();
    for (Task taskInstance : list) {
      Map<String, Object> map = new HashMap<String, Object>();
      map.put(WorkFlowKeyParams.WORKFLOW_ACTOR, book1.getOpuser());
      processEngine.getTaskService().complete(taskInstance.getId());
    }
    return 0;
  }

  @Override
  public int callMakePayment(long paymentId, String userId) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put(WorkFlowKeyParams.PAY_REQUISITION_ID, paymentId);
    map.put(WorkFlowKeyParams.WORKFLOW_ACTOR, "");
    map.put(WorkFlowKeyParams.WORKFLOW_TOUR_NO, "");
    map.put(WorkFlowKeyParams.OP_AUDITING, "");
    map.put(WorkFlowKeyParams.AIR_TICKET_DISTRIBUTION, "");

    processEngine.getRuntimeService().startProcessInstanceById(
        "DefrayWorkFlow", map);

    return 0;
  }

  @Override
  public int callAuditPayment(long paymentId, String userId) {
    Outcome book1 = getHibernateTemplate().get(Outcome.class, paymentId);
    TaskService taskService = processEngine.getTaskService();
    List<Task> list = taskService.createTaskQuery()
        .processInstanceId(book1.getWorkflowId())
        .taskName(WorkFlowKeyParams.FINANCE_REQUISITION_TASK_AUDITING).list();
    for (Task taskInstance : list) {
      // if (book1.getFrChecked().equals('Y'))
      // {
      // taskService.completeTask(taskInstance.getId(), "审核结束");
      // } else
      // {
      // taskService.completeTask(taskInstance.getId(), "审核不通过");
      // }
      processEngine.getTaskService().complete(taskInstance.getId());
    }

    return 0;
  }

  @Override
  public int callOpAuditPayment(long paymentId, String userId) {
    Outcome book1 = getHibernateTemplate().get(Outcome.class, paymentId);
    TaskService taskService = processEngine.getTaskService();
    List<Task> list = taskService.createTaskQuery()
        .processInstanceId(book1.getWorkflowId())
        .taskName(WorkFlowKeyParams.CALCULATION_REQUISITION_TASK_AUDITING)
        .list();
    for (Task taskInstance : list) {
      processEngine.getTaskService().complete(taskInstance.getId());
    }
    return 0;
  }

  @Override
  public int callBackPayment(long paymentId, String userId) {
    // 财务审核后打回给计调(需要机票配送)
    // StringBuilder sb1 = new StringBuilder();
    // sb1.append("select t ");
    // sb1.append("from org.jbpm.pvm.internal.task.Task t ");
    // sb1.append(",com.opentravelsoft.entity.Outcome u ");
    // sb1.append("where t.executionId=u.workflowId ");
    // sb1.append("and u.outcomeId=? and t.name='"
    // + WorkFlowKeyParams.AIR_TICKET_TASK_DISTRIBUTION + "' ");
    // sb1.append("and t.state!='" + Task.STATE_COMPLETED + "' ");
    //
    // List<Task> taskInstances1 = getHibernateTemplate().find(sb1.toString(),
    // p1);
    // if (!taskInstances1.isEmpty()) {
    // for (Task taskInstance : taskInstances1) {
    // processEngine.getTaskService().completeTask(taskInstance.getId());
    // }
    // }
    // // -----------------------------------------------------------------
    // // 财务审核后打回给计调(不需要机票配送)
    // StringBuilder sb2 = new StringBuilder();
    // sb2.append("select t ");
    // sb2.append("from org.jbpm.pvm.internal.task.Task t ");
    // sb2.append(",com.opentravelsoft.entity.Outcome u ");
    // sb2.append("where t.executionId=u.workflowId ");
    // sb2.append("and u.outcomeId=? and t.end is null");
    // Object[] p2 = { paymentId };
    // List<Task> taskInstances2 = getHibernateTemplate().find(sb2.toString(),
    // p2);
    //
    // if (taskInstances2.isEmpty()) {
    // Map<String, Object> map = new HashMap<String, Object>();
    // map.put(WorkFlowKeyParams.PAY_REQUISITION_ID, tblOutcome.getOutcomeId());
    // map.put(WorkFlowKeyParams.WORKFLOW_ACTOR, tblOutcome.getOpApprovedby());
    // map.put(WorkFlowKeyParams.WORKFLOW_TOUR_NO, tblOutcome.getTourNo());
    // map.put(WorkFlowKeyParams.OP_AUDITING, "false");
    // map.put(WorkFlowKeyParams.AIR_TICKET_DISTRIBUTION,
    // tblOutcome.getCarryTicket());
    //
    // processEngine.getExecutionService().startProcessInstanceByKey(
    // "DefrayWorkFlow", map);
    // }
    //
    // // -----------------------------------------------------------------
    // // 财务已审核的申请单
    // sb = new StringBuilder();
    // sb.append("select t ");
    // sb.append("from org.jbpm.pvm.internal.task.Task t ");
    // sb.append(",com.opentravelsoft.entity.Outcome u ");
    // sb.append("where t.executionId=u.workflowId ");
    // sb.append("and u.outcomeId=? and t.name='"
    // + WorkFlowKeyParams.FINANCE_REQUISITION_TASK_AUDITING + "' ");
    // sb.append("and t.state!='" + Task.STATE_COMPLETED + "' ");
    // Object[] p = { paymentId };
    // List<Task> taskInstances = getHibernateTemplate().find(sb.toString(), p);
    //
    // for (Task taskInstance : taskInstances) {
    // processEngine.getTaskService().complete(taskInstance.getId());
    // }
    return 0;
  }

  @Override
  public int callParcelPayment(long paymentId, String userId) {
    Outcome book1 = getHibernateTemplate().get(Outcome.class, paymentId);
    TaskService taskService = processEngine.getTaskService();
    List<Task> list = taskService.createTaskQuery()
        .processInstanceId(book1.getWorkflowId())
        .taskName(WorkFlowKeyParams.AIR_TICKET_TASK_DISTRIBUTION).list();
    for (Task taskInstance : list) {
      processEngine.getTaskService().complete(taskInstance.getId());
    }
    return 0;
  }

  @Override
  public int callMakeExpress(String expressId, String userId) {
    Express book1 = getHibernateTemplate().get(Express.class, expressId);
    TaskService taskService = processEngine.getTaskService();
    List<Task> list = taskService.createTaskQuery()
        .processInstanceId(book1.getWorkflowId())
        .taskName(WorkFlowKeyParams.EXPRESS_TASK_ASSIGN).list();
    for (Task taskInstance : list) {
      processEngine.getTaskService().complete(taskInstance.getId());
    }
    return 0;
  }

  public int auditingIsPass(String expressId, Boolean ispass, String userId) {
    Express book1 = getHibernateTemplate().get(Express.class, expressId);
    TaskService taskService = processEngine.getTaskService();
    List<Task> list = taskService.createTaskQuery()
        .processInstanceId(book1.getWorkflowId())
        .taskName(WorkFlowKeyParams.EXPRESS_TASK_CHECK).list();
    for (Task taskInstance : list) {
      processEngine.getTaskService().complete(taskInstance.getId());
    }
    return 0;
  }

  public int isExpressAgain(String expressId, boolean isflag, String userId) {
    Express book1 = getHibernateTemplate().get(Express.class, expressId);
    TaskService taskService = processEngine.getTaskService();
    List<Task> list = taskService.createTaskQuery()
        .processInstanceId(book1.getWorkflowId())
        .taskName(WorkFlowKeyParams.EXPRESS_TASK_AGAIN).list();
    for (Task taskInstance : list) {
      // taskInstance.createVariable(WorkFlowKeyParams.WORKFLOW_ACTOR,
      // userId);
      // if (isflag) {
      // processEngine.getTaskService().completeTask(taskInstance.getId(),
      // "再次配送");
      //
      // } else {
      // processEngine.getTaskService().completeTask(taskInstance.getId(),
      // "to 配送结束");
      // }
      processEngine.getTaskService().complete(taskInstance.getId());
    }
    return 0;
  }

  public int examineExpress(String expressId, boolean isPass, String userId) {
    Express book1 = getHibernateTemplate().get(Express.class, expressId);
    TaskService taskService = processEngine.getTaskService();
    List<Task> list = taskService.createTaskQuery()
        .processInstanceId(book1.getWorkflowId())
        .taskName(WorkFlowKeyParams.EXPRESS_TASK_EXAMINE).list();
    for (Task taskInstance : list) {
      // if (isPass)
      // processEngine.getTaskService().completeTask(taskInstance.getId(),
      // "材料审核通过");
      // else
      // processEngine.getTaskService().completeTask(taskInstance.getId(),
      // "材料审核失败");
      // }
    }
    return 0;
  }

  public int accountExpress(String expressId, String userId) {
    Express book1 = getHibernateTemplate().get(Express.class, expressId);
    TaskService taskService = processEngine.getTaskService();
    List<Task> list = taskService.createTaskQuery()
        .processInstanceId(book1.getWorkflowId())
        .taskName(WorkFlowKeyParams.EXPRESS_TASK_EXAMINE).list();
    for (Task taskInstance : list) {
      // taskInstance.createVariable(WorkFlowKeyParams.WORKFLOW_ACTOR,
      // userId);
      processEngine.getTaskService().complete(taskInstance.getId());
    }
    return 0;
  }

  @Override
  public String callNewOrder(String orderNo) {
    String procId = "";
    Map<String, Object> map = new HashMap<String, Object>();
    map.put(WorkFlowKeyParams.WORKFLOW_ORDER_ID, orderNo);

    procId = processEngine.getRuntimeService()
        .startProcessInstanceByKey("LineReserve", map).getId();
    return procId;
  }

}
