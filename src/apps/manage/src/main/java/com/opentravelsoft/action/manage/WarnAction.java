package com.opentravelsoft.action.manage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.product.Remind;
import com.opentravelsoft.service.account.CustomerService;
import com.opentravelsoft.service.finance.FinanceAlertService;
import com.opentravelsoft.service.operator.ExpressService;
import com.opentravelsoft.service.operator.OperatorAlertService;
import com.opentravelsoft.workflow.WorkFlowKeyParams;

/**
 * 预警弹出窗口
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:55 $
 */
public class WarnAction extends ManageAction {
  private static final long serialVersionUID = -1454513816209811339L;

  private OperatorAlertService operatorAlertService;

  private FinanceAlertService financeAlertService;

  private CustomerService customerService;

  private ExpressService expressService;

  private Remind re = new Remind();

  /** 未读订单 */
  private List<Remind> remind = new ArrayList<Remind>();

  /** 应收款 */
  private List<Remind> income = new ArrayList<Remind>();

  /** 应付款 */
  private List<Remind> outcome = new ArrayList<Remind>();

  /** 付款申请 */
  private List<Remind> billhead = new ArrayList<Remind>();

  private List<Remind> assignExpress = new ArrayList<Remind>();

  private List<Remind> checkExpress = new ArrayList<Remind>();

  private List<Remind> accountExpress = new ArrayList<Remind>();

  private List<Remind> examineExpress = new ArrayList<Remind>();

  private List<Remind> againExpress = new ArrayList<Remind>();

  /** 客户 */
  private List<Remind> cust = new ArrayList<Remind>();

  @Autowired
  public void setOperatorAlertService(OperatorAlertService operatorAlertService) {
    this.operatorAlertService = operatorAlertService;
  }

  @Autowired
  public void setExpressService(ExpressService expressService) {
    this.expressService = expressService;
  }

  @Autowired
  public void setCustomerService(CustomerService customerService) {
    this.customerService = customerService;
  }

  @Autowired
  public void setFinanceAlertService(FinanceAlertService financeAlertService) {
    this.financeAlertService = financeAlertService;
  }

  @Override
  public String execute() throws Exception {
    Employee user = getUser();

    // if (auth.contains(EbizCommon.ROLE_OPERATOR)
    // || auth.contains(EbizCommon.ROLE_SUPEROPERATOR)
    // || auth.contains(EbizCommon.ROLE_SUPERUSER))
    // {
    // 未读订单
    remind = operatorAlertService.roGetOperator(user.getUserId());
    // }

    // 应付 (未读付款申请, 团出发前n天未付款申请)
    // if (auth.contains(EbizCommon.ROLE_FINANCE)
    // && auth.contains(EbizCommon.ROLE_OUTCOME_INBAND))
    // {
    outcome = financeAlertService.roGetOutcomeInBand(3);
    // // 未阅读付款申请书
    billhead = financeAlertService.roGetBillheadInBand();
    // }

    // 应收 团出发前n天 未收款客户提醒
    // if (auth.contains(EbizCommon.ROLE_FINANCE)
    // && auth.contains(EbizCommon.ROLE_INCOME_INBAND))
    // {
    income = financeAlertService.roGetIncomeInBand(3);
    // }

    // 未审核客户提醒
    // if (auth.contains(EbizCommon.ROLE_CRM))
    // {
    re = customerService.roGetWaitAgents();
    // }

    // 配送中心
    // if (auth.contains(EbizCommon.ROLE_TRANSPORT))
    // {
    // 未安排配送
    assignExpress = expressService
        .roGetExpressTask(WorkFlowKeyParams.EXPRESS_TASK_ASSIGN);
    // }

    // 客服签单
    // if (auth.contains(EbizCommon.ROLE_CALLCENTER_SUPPORT))
    // {
    checkExpress = expressService
        .roGetExpressTask(WorkFlowKeyParams.EXPRESS_TASK_CHECK);
    accountExpress = expressService
        .roGetExpressTask(WorkFlowKeyParams.EXPRESS_TASK_ACCOUNT);
    // }
    // 材料审核
    // if (auth.contains(EbizCommon.ROLE_VISA_EXAMINE))
    // {
    examineExpress = expressService
        .roGetExpressTask(WorkFlowKeyParams.EXPRESS_TASK_EXAMINE);
    // }
    // Call Center
    // if (auth.contains(EbizCommon.ROLE_CALLCENTER))
    // {
    againExpress = expressService
        .roGetExpressTask(WorkFlowKeyParams.EXPRESS_TASK_AGAIN);
    // }

    return SUCCESS;
  }

  public List<Remind> getRemind() {
    return remind;
  }

  public void setRemind(List<Remind> remind) {
    this.remind = remind;
  }

  public List<Remind> getIncome() {
    return income;
  }

  public List<Remind> getOutcome() {
    return outcome;
  }

  public List<Remind> getBillhead() {
    return billhead;
  }

  public List<Remind> getCust() {
    return cust;
  }

  public void setCust(List<Remind> cust) {
    this.cust = cust;
  }

  public Remind getRe() {
    return re;
  }

  public List<Remind> getAssignExpress() {
    return assignExpress;
  }

  public List<Remind> getCheckExpress() {
    return checkExpress;
  }

  public List<Remind> getAccountExpress() {
    return accountExpress;
  }

  public List<Remind> getExamineExpress() {
    return examineExpress;
  }

  public List<Remind> getAgainExpress() {
    return againExpress;
  }

}
