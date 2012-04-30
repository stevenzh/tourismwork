package com.opentravelsoft.action.manage.finance;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.product.Remind;
import com.opentravelsoft.service.finance.FinanceAlertService;

/**
 * 财务预警
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:55 $
 */
public class FinanceAlertAction extends ManageAction {
  private static final long serialVersionUID = -1246961970794740556L;

  @Autowired
  private FinanceAlertService financeAlertService;

  private String kenEmployeeId;

  /** 应收款 */
  private List<Remind> incomeList = new ArrayList<Remind>();

  /** 应付款 */
  private List<Remind> outcoemList = new ArrayList<Remind>();

  /** 未读付款申请书 */
  private List<Remind> billList = new ArrayList<Remind>();

  @Override
  public String input() throws Exception {
    // 国内应付 (未读付款申请, 团出发前n天未付款申请)
    // if (auth.contains(EbizCommon.ROLE_FINANCE)
    // && auth.contains(EbizCommon.ROLE_OUTCOME_INBAND))
    // {
    outcoemList = financeAlertService.roGetOutcomeInBand(3);

    // 未阅读付款申请书
    billList = financeAlertService.roGetBillheadInBand();
    // }

    // 国内应收 团出发前n天 未收款客户提醒
    // if (auth.contains(EbizCommon.ROLE_FINANCE)
    // && auth.contains(EbizCommon.ROLE_INCOME_INBAND))
    // {
    incomeList = financeAlertService.roGetIncomeInBand(3);
    // }

    return INPUT;
  }

  public String getKenEmployeeId() {
    return kenEmployeeId;
  }

  public void setKenEmployeeId(String kenEmployeeId) {
    this.kenEmployeeId = kenEmployeeId;
  }

  public List<Remind> getIncomeInband() {
    return incomeList;
  }

  public void setIncomeInband(List<Remind> incomeInband) {
    this.incomeList = incomeInband;
  }

  public List<Remind> getOutcoemInband() {
    return outcoemList;
  }

  public void setOutcoemInband(List<Remind> outcoemInband) {
    this.outcoemList = outcoemInband;
  }

  public List<Remind> getBillheadInBand() {
    return billList;
  }

  public void setBillheadInBand(List<Remind> billheadInBand) {
    this.billList = billheadInBand;
  }

  public FinanceAlertService getFinanceAlertService() {
    return financeAlertService;
  }

}
