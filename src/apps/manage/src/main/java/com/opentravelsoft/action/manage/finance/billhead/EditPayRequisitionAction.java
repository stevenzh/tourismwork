package com.opentravelsoft.action.manage.finance.billhead;

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
 * 财务候修改付款申请书
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 */
public class EditPayRequisitionAction extends ManageAction {
  private static final long serialVersionUID = -7198529997222740248L;

  @Autowired
  private OutcomeService outcomeService;

  // -------------------------------------------------------------------------
  /** 操作员 */
  private String kenMaker;

  /** 部门 */
  private String kenDepartment;

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
  private int supplierId;

  private Customer supplier;

  private int outcomeId;

  private Outcome outcome = new Outcome();

  // 供应商类型
  private String resource;

  private List<Customer> supplierList;

  private List<LabelValueBean> paymentList;

  private List<LabelValueBean> supplierTypeList;

  public String input() {
    supplierTypeList = getCodeList("ebiz_supplier_resource");
    paymentList = getCodeList("ebiz_pay_mode");
    outcome = outcomeService.roGetBillhead(outcomeId);
    supplier = outcome.getCustomer();

    for (LabelValueBean payMode : paymentList) {
      if (outcome.getPayMode().toString().equals(payMode.getValue()))
        outcome.setPayModeName(payMode.getLabel());
    }

    for (LabelValueBean supplierType : supplierTypeList) {
      if (supplier.getResource().equals(supplierType.getValue()))
        resource = supplierType.getLabel();
    }

    return INPUT;
  }

  /**
   * 修改申请书
   * 
   * @return
   */
  public String submit() {
    Employee user = getUser();
    outcome.setOutcomeId(outcomeId);
    // outcome.setCreatedby(user.getUid());
    outcome.setUpdatedby(user.getUserId());

    int ret = outcomeService.txFrModifyBillhead(outcome, user.getUserId());

    if (ret == 0)
      addActionMessage("修改成功！");
    else
      addActionMessage("修改失败！");

    return SUCCESS;
  }

  public String getResource() {
    return resource;
  }

  public void setResource(String resource) {
    this.resource = resource;
  }

  public List<Customer> getSupplierList() {
    return supplierList;
  }

  public void setSupplierList(List<Customer> supplierList) {
    this.supplierList = supplierList;
  }

  public List<LabelValueBean> getPaymentList() {
    return paymentList;
  }

  public void setPaymentList(List<LabelValueBean> paymentList) {
    this.paymentList = paymentList;
  }

  public List<LabelValueBean> getSupplierTypeList() {
    return supplierTypeList;
  }

  public void setSupplierTypeList(List<LabelValueBean> supplierTypeList) {
    this.supplierTypeList = supplierTypeList;
  }

  public int getSupplierId() {
    return supplierId;
  }

  public void setSupplierId(int supplierId) {
    this.supplierId = supplierId;
  }

  public Customer getSupplier() {
    return supplier;
  }

  public void setSupplier(Customer supplier) {
    this.supplier = supplier;
  }

  public int getOutcomeId() {
    return outcomeId;
  }

  public void setOutcomeId(int outcomeId) {
    this.outcomeId = outcomeId;
  }

  public Outcome getOutcome() {
    return outcome;
  }

  public void setOutcome(Outcome outcome) {
    this.outcome = outcome;
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
