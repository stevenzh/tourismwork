package com.opentravelsoft.action.manage.finance.billhead;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Customer;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.finance.Outcome;
import com.opentravelsoft.service.finance.OutcomeService;

/**
 * 财务审核付款申请书
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:33 $
 */
public class AuditPayRequisitionAction extends ManageAction {
  private static final long serialVersionUID = 7046152940418063549L;

  @Autowired
  private OutcomeService outcomeService;

  // -------------------------------------------------------------------------

  /** 操作员 */
  private String kenMaker;

  /** 部门 */
  private String kenDepartment;

  /** 申请书创建时间 */
  private Date kenStartDate;

  private Date kenEndDate;

  /** 出发时间 */
  private Date kenStartOutDate;

  private Date kenEndOutDate;

  /** 团号 */
  private String kenTourNo;

  /** 是否财务审核 */
  private String kenAudit;

  /** 是否付款 */
  private String kenPay;

  // -------------------------------------------------------------------------
  /** 应付客户 */
  private Customer supplier;

  private int outcomeId;

  private Outcome billhead = new Outcome();

  private List<LabelValueBean> payModeList = new ArrayList<LabelValueBean>();

  public String input() {
    int isAuditing = -1;

    billhead = outcomeService.roGetBillhead(outcomeId);
    if (isAuditing == 0)
      billhead.setIsAuditing('Y');
    // else
    // {
    // billhead.setIsAuditing('N');
    // addActionError("本团有现结客户未付清款项，此付款申请书不能审核！");
    // }

    payModeList = getCodeList("ebiz_pay_mode");
    for (LabelValueBean payMode : payModeList) {
      if (billhead.getPayMode().toString().equals(payMode.getValue()))
        billhead.setPayModeName(payMode.getLabel());
    }

    supplier = billhead.getCustomer();

    return INPUT;
  }

  /**
   * 标记为已读
   * 
   * @return
   */
  public String read() {
    Employee user = getUser();
    billhead.setOutcomeId(outcomeId);
    billhead.setUpdatedBy(user.getUserId());

    int ret = outcomeService.txFrReadBillhead(billhead, user.getUserId());

    if (ret == 0)
      addActionMessage("标记成功！");
    else
      addActionMessage("标记失败！");

    return SUCCESS;
  }

  public String submit() {
    Employee user = getUser();
    billhead = outcomeService.roGetBillhead(outcomeId);
    if (billhead.getFrApprovedFlag() == 'Y') {
      addActionMessage("此单已经审核！");
      return INPUT;
    }
    int ret = outcomeService.txAuditingBillhead(outcomeId, user.getUserId());
    if (ret == 0)
      addActionMessage("审核成功！");
    else
      addActionMessage("审核失败！");

    return SUCCESS;
  }

  public Date getKenStartDate() {
    return kenStartDate;
  }

  public void setKenStartDate(Date kenStartDate) {
    this.kenStartDate = kenStartDate;
  }

  public Date getKenEndDate() {
    return kenEndDate;
  }

  public void setKenEndDate(Date kenEndDate) {
    this.kenEndDate = kenEndDate;
  }

  public int getOutcomeId() {
    return outcomeId;
  }

  public void setOutcomeId(int outcomeId) {
    this.outcomeId = outcomeId;
  }

  public Outcome getBillhead() {
    return billhead;
  }

  public void setBillhead(Outcome billhead) {
    this.billhead = billhead;
  }

  public List<LabelValueBean> getPayModeList() {
    return payModeList;
  }

  public void setPayModeList(List<LabelValueBean> payModeList) {
    this.payModeList = payModeList;
  }

  public Date getKenStartOutDate() {
    return kenStartOutDate;
  }

  public void setKenStartOutDate(Date kenStartOutDate) {
    this.kenStartOutDate = kenStartOutDate;
  }

  public Date getKenEndOutDate() {
    return kenEndOutDate;
  }

  public void setKenEndOutDate(Date kenEndOutDate) {
    this.kenEndOutDate = kenEndOutDate;
  }

  public String getKenMaker() {
    return kenMaker;
  }

  public void setKenMaker(String kenMaker) {
    this.kenMaker = kenMaker;
  }

  public String getKenDepartment() {
    return kenDepartment;
  }

  public void setKenDepartment(String kenDepartment) {
    this.kenDepartment = kenDepartment;
  }

  public String getKenTourNo() {
    return kenTourNo;
  }

  public void setKenTourNo(String kenTourNo) {
    this.kenTourNo = kenTourNo;
  }

  public String getKenAudit() {
    return kenAudit;
  }

  public void setKenAudit(String kenAudit) {
    this.kenAudit = kenAudit;
  }

  public String getKenPay() {
    return kenPay;
  }

  public void setKenPay(String kenPay) {
    this.kenPay = kenPay;
  }

  public Customer getSupplier() {
    return supplier;
  }

}
