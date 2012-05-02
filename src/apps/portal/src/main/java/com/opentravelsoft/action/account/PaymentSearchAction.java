package com.opentravelsoft.action.account;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.util.LabelValueBean;

import com.opentravelsoft.entity.finance.Income;
import com.opentravelsoft.service.PaymentService;
import com.opentravelsoft.webapp.action.PortalAction;

/**
 * 付款查询
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 */
public class PaymentSearchAction extends PortalAction {

  private static final long serialVersionUID = 3058811673524960132L;

  @Autowired
  private PaymentService paymentService;

  /**
   * 付款合计
   */
  private double totalExpense;

  /**
   * 销款合计
   */
  private double totalPayCosts;

  /**
   * 未销合计
   */
  private double totalUnPay;

  /** 付款日期 */
  private Date paymentDateStart;

  private Date paymentDateEnd;

  /** 付款金额 */
  private double payGatherStart;

  private double payGatherEnd;

  private Income gathering = new Income();

  private List<Income> gatheringList = new ArrayList<Income>();

  /** 付款记录号 */
  private int incomeId;

  private String outCity = "All";

  /**
   * 进入查询
   */
  public String input() {
    paymentDateStart = new Date();
    paymentDateEnd = new Date();
    payGatherStart = 0f;
    payGatherEnd = 0f;
    return INPUT;
  }

  /**
   * 查询
   * 
   * @return
   */
  public String submit() {
    gatheringList = paymentService.roGetGatheringList(0, paymentDateStart,
        paymentDateEnd, payGatherStart, payGatherEnd);
    List<LabelValueBean> payModeList = new ArrayList<LabelValueBean>();
    payModeList = getCodeList("ebiz_pay_mode");
    Map<String, String> payModeMap = new HashMap<String, String>();
    for (LabelValueBean obj : payModeList) {
      payModeMap.put(obj.getValue(), obj.getLabel());
    }

    totalExpense = 0f;
    totalPayCosts = 0f;
    for (Income obj1 : gatheringList) {
      obj1.setIncomeModeShow(payModeMap.get(obj1.getPayMode().toString()));
      totalExpense += obj1.getAmount();
      totalPayCosts += obj1.getOffSetAmount();
    }
    totalUnPay = totalExpense - totalPayCosts;
    return SUCCESS;
  }

  /**
   * 付款详细
   * 
   * @return
   */
  public String paymentDetail() {
    gathering = paymentService.roGetGathering(incomeId);
    if (null != gathering) {
      List<LabelValueBean> payModeList = new ArrayList<LabelValueBean>();
      payModeList = getCodeList("ebiz_pay_mode");
      for (LabelValueBean obj : payModeList) {
        if (gathering.getPayMode().toString().equals(obj.getValue())) {
          gathering.setIncomeModeShow(obj.getLabel());
          break;
        }
      }

      return SUCCESS;
    }
    return INPUT;
  }

  public Income getGathering() {
    return gathering;
  }

  public void setGathering(Income gathering) {
    this.gathering = gathering;
  }

  public double getTotalExpense() {
    return totalExpense;
  }

  public double getTotalPayCosts() {
    return totalPayCosts;
  }

  public double getTotalUnPay() {
    return totalUnPay;
  }

  public Date getPaymentDateStart() {
    return paymentDateStart;
  }

  public void setPaymentDateStart(Date paymentDateStart) {
    this.paymentDateStart = paymentDateStart;
  }

  public Date getPaymentDateEnd() {
    return paymentDateEnd;
  }

  public void setPaymentDateEnd(Date paymentDateEnd) {
    this.paymentDateEnd = paymentDateEnd;
  }

  public double getPayGatherStart() {
    return payGatherStart;
  }

  public void setPayGatherStart(double payGatherStart) {
    this.payGatherStart = payGatherStart;
  }

  public double getPayGatherEnd() {
    return payGatherEnd;
  }

  public void setPayGatherEnd(double payGatherEnd) {
    this.payGatherEnd = payGatherEnd;
  }

  public List<Income> getGatheringList() {
    return gatheringList;
  }

  public void setGatheringList(List<Income> gatheringList) {
    this.gatheringList = gatheringList;
  }

  public int getIncomeId() {
    return incomeId;
  }

  public void setIncomeId(int incomeId) {
    this.incomeId = incomeId;
  }

  public void setOutCity(String outCity) {
    this.outCity = outCity;
  }

  public String getOutCity() {
    return outCity;
  }

}
