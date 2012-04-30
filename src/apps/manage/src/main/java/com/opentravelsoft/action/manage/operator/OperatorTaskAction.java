package com.opentravelsoft.action.manage.operator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.service.MyPageService;
import com.opentravelsoft.workflow.WorkFlowKeyParams;

/**
 * 任务列表
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:16 $
 */
public class OperatorTaskAction extends ManageAction {

  private static final long serialVersionUID = 1L;

  @Autowired
  private MyPageService myPageService;

  /** 未审核订单 */
  private List<Booking> bookList = new ArrayList<Booking>();

  /** 可以成团的计划 */
  private List<Plan> canBuildList = new ArrayList<Plan>();

  /** 需要安排领队的团 */
  private List<Plan> arrangeLeader = new ArrayList<Plan>();

  public String execute() throws Exception {
    Employee user = getUser();

    // 团操作　
    // bookList = myPageService.roGetOrderTask("", user.getUid(),
    // WorkFlowKeyParams.ORDER_TASK_CONFIRM);
    // 可以成团的计划
    // canBuildList = myPageService.roGetCanBuild(user.getUid());
    // 需要安排领队的团
    // arrangeLeader = myPageService.roGetTaskList(
    // WorkFlowKeyParams.TOUR_ARRANGE_LEADER, "");

    return SUCCESS;
  }

  public List<Booking> getBookList() {
    return bookList;
  }

  public List<Plan> getCanBuildList() {
    return canBuildList;
  }

  public List<Plan> getArrangeLeader() {
    return arrangeLeader;
  }

}
