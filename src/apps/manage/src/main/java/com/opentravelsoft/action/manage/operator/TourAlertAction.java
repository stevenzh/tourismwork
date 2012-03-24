package com.opentravelsoft.action.manage.operator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.service.MyPageService;

/**
 * 最近出团计划
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:55 $
 */
public class TourAlertAction extends ManageAction {
  private static final long serialVersionUID = -1454513816209811339L;

  private MyPageService myPageService;

  /** 最近的出团计划 */
  private List<Plan> planList = new ArrayList<Plan>();

  @Autowired
  public void setMyPageService(MyPageService myPageService) {
    this.myPageService = myPageService;
  }

  @Override
  public String execute() throws Exception {
    Employee user = getUser();
    planList = myPageService.roGetPlanList(0, user.getUserId());
    return SUCCESS;
  }

  public List<Plan> getPlanList() {
    return planList;
  }
}
