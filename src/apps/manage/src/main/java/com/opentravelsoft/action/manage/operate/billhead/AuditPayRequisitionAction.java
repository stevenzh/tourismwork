package com.opentravelsoft.action.manage.operate.billhead;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.finance.Outcome;
import com.opentravelsoft.service.finance.OutcomeService;

/**
 * 计调审核付款申请书
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:29 $
 */
public class AuditPayRequisitionAction extends ManageAction {
  private static final long serialVersionUID = -7935292259982836195L;

  @Autowired
  private OutcomeService outcomeService;

  private long outcomeId;

  private Outcome billhead;

  private List<LabelValueBean> payModeList = new ArrayList<LabelValueBean>();

  // -------------------------------------------------------------------------

  /** 部门 */
  private String kenDepartment;

  /** 操作员 */
  private String kenMaker;

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

  public String getKenTourNo() {
    return kenTourNo;
  }

  public void setKenTourNo(String kenTourNo) {
    this.kenTourNo = kenTourNo;
  }

  // -------------------------------------------------------------------------
  public String input() {
    billhead = outcomeService.roGetBillhead(outcomeId);
    payModeList = getCodeList("ebiz_pay_mode");
    for (LabelValueBean payMode : payModeList) {
      if (billhead.getPayMode().toString().equals(payMode.getValue()))
        billhead.setPayModeName(payMode.getLabel());
    }

    return INPUT;
  }

  public String submit() {
    Employee user = getUser();
    billhead = outcomeService.roGetBillhead(outcomeId);
    if (billhead.getFrApprovedFlag() == 'Y') {
      addActionMessage("");
      return INPUT;
    }
    int ret = outcomeService.txOpApproved(outcomeId, user.getUserId());
    if (ret == 0)
      addActionMessage("审核成功！");
    else
      addActionMessage("审核失败！");

    return SUCCESS;
  }

  public Outcome getBillhead() {
    return billhead;
  }

  public void setBillhead(Outcome billhead) {
    this.billhead = billhead;
  }

  public long getOutcomeId() {
    return outcomeId;
  }

  public void setOutcomeId(long outcomeId) {
    this.outcomeId = outcomeId;
  }

  public List<LabelValueBean> getPayModeList() {
    return payModeList;
  }

  public void setPayModeList(List<LabelValueBean> payModeList) {
    this.payModeList = payModeList;
  }

  public String getKenDepartment() {
    return kenDepartment;
  }

  public void setKenDepartment(String kenDepartment) {
    this.kenDepartment = kenDepartment;
  }

  public String getKenMaker() {
    return kenMaker;
  }

  public void setKenMaker(String kenMaker) {
    this.kenMaker = kenMaker;
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

}
