package com.opentravelsoft.service.finance;

import java.util.List;

import com.opentravelsoft.entity.product.Remind;

public interface FinanceAlertService {

  /**
   * 应付 (未读付款申请, 团出发前3天未付款申请)
   * 
   * @return
   */
  List<Remind> roGetOutcomeInBand(int daynum);

  /**
   * 应收 团出发前3天 未收款客户提醒
   * 
   * @return
   */
  List<Remind> roGetIncomeInBand(int daynum);

  /**
   * 未读付款申请
   * @return
   */
  List<Remind> roGetBillheadInBand();

}
