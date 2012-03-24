package com.opentravelsoft.workflow;

import java.util.List;
import org.activiti.engine.task.Task;
import com.opentravelsoft.entity.Booking;

public interface TaskDao {

  List<Task> getMyTask(String uid);

  void supplyJobs();

  /**
   * 制作计划
   * 
   * @param planNo
   * @param userId
   * @return
   */
  int callMakePlan(String planNo, String userId);

  /**
   * 安排领队
   * 
   * @param tourNo
   * @param userId
   * @return
   */
  int callArrangeLeader(String tourNo, String userId);

  /**
   * 制作单团核算
   * 
   * @param planNo
   * @param userId
   * @return
   */
  int callMakeTourAccounts(String planNo, String userId);

  /**
   * 审核单团核算
   * 
   * @param planNo
   * @param userId
   * @return
   */
  int callAuditTourAccounts(String planNo, String userId);

  /**
   * 取得团所有任务
   * 
   * @param planNo
   * @return
   */
  public List<Task> getTourTaskList(String planNo);

  /**
   * 
   * @param taskName
   * @param userId
   * @return
   */
  // public List<Plan> getTaskList(String taskName, String userId);

  /**
   * 计调提交核算表到财务
   * 
   * @param planNo
   * @param uid
   * @return
   */
  public int checkTourAccounts(String planNo, String uid);

  /**
   * 标记完成当前计划的任务
   * 
   * @param planNo
   */
  public void flowMethod(String planNo);

  /**
   * 可以成团的计划
   * 
   * @param userId
   * @return
   */
  // public List<Plan> getCanBuild(String userId);

  /**
   * 制作订单
   * 
   * @param booking
   * @return
   */
  // int callSaveBooking(Booking booking);

  /**
   * 审核订单
   * 
   * @param book
   * @return
   */
  int callConfirmBooking(Booking book);

  /**
   * 取得订单的任务
   * 
   * @param bookingNo 订单号
   */
  public List<Task> getOrderTaskList(String bookingNo);

  /**
   * 取得订单的任务
   * 
   * @param teamId 组
   * @param userId 用户
   * @param taskName 任务名
   */
  // List<Booking> getOrderTaskList(String teamId, String userId, String
  // taskName);

  /**
   * 制作收款单
   * 
   * @param bookingNo
   * @param userId
   * @return
   */
  int callMakeIncome(String bookingNo, String userId);

  int callMakePayment(long paymentId, String userId);

  int callOpAuditPayment(long paymentId, String userId);

  @Deprecated
  int callAuditPayment(long paymentId, String userId);

  @Deprecated
  int callBackPayment(long paymentId, String userId);

  /**
   * 机票配送
   * 
   * @deprecated
   * @param paymentId
   * @param userId
   * @return
   */
  int callParcelPayment(long paymentId, String userId);

  /**
   * @deprecated
   * @param expressId
   * @param userId
   * @return
   */
  int callMakeExpress(String expressId, String userId);

  /**
   * 取得配送单的任务列表
   * 
   * @param expressId
   * @return
   */
  List<Task> getExpressTask(String expressId);

  /**
   * 签单部审核配送是否通过
   * 
   * @param ispass 是否通过
   * @param expressId 配送单号
   * @param userId 审核人
   */
  int auditingIsPass(String expressId, Boolean ispass, String userId);

  /**
   * 是否需要再次配送
   * 
   * @param ispass 是否再次配送
   * @param expressId 配送单号
   */
  int isExpressAgain(String expressId, boolean isflag, String userId);

  /**
   * 材料审核配送是否通过
   * 
   * @param ispass 是否通过
   * @param expressId 配送单号
   */
  int examineExpress(String expressId, boolean ispass, String userId);

  int accountExpress(String expressId, String userId);

  /**
   * 新建订单
   * 
   * @param orderNo
   * @return
   */
  String callNewOrder(String orderNo);

}
