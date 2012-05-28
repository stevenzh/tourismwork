package com.opentravelsoft.action.manage.operate.billhead;

import java.util.List;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.common.TeamType;
import com.opentravelsoft.entity.Customer;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.entity.TourCost;
import com.opentravelsoft.entity.finance.Outcome;
import com.opentravelsoft.service.finance.OutcomeService;

/**
 * 制作付款申请书
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:29 $
 */

public class PayRequisitionAction extends ManageAction {
  private static final long serialVersionUID = -7852853734687360320L;

  @Autowired
  private OutcomeService outcomeService;

  // -------------------------------------------------------------------------

  /** 应付客户 */
  private int kenSupplierId;

  /** 团号 */
  private String kenTourNo;

  /** 供应商类型 */
  private String kenSupplierType;

  private String uid;

  // -------------------------------------------------------------------------

  private List<Customer> supplierList;

  private List<LabelValueBean> paymentList;

  private List<LabelValueBean> supplierTypeList;

  /** 团号列表 */
  private List<LabelValueBean> stnList;

  private List<TourCost> paynoticeList;

  // -------------------------------------------------------------------------

  private int outcomeId;

  private Outcome outcome = new Outcome();;

  /** 提交财务 */
  private String audit = "false";

  private List<Team> teamList;

  public String input() {
    Employee user = getUser();
    supplierTypeList = getCodeList("ebiz_supplier_resource");
    paymentList = getCodeList("ebiz_pay_mode");
    supplierList = outcomeService.roGetSupplierByType(kenSupplierType, user
        .getGroup().getGroupId());
    stnList = outcomeService.roGetTourList(user.getGroup().getGroupId(),
        user.getUserId(), 0);
    teamList = outcomeService.roGetTeams(user.getUserId(), TeamType.Operator);
    return INPUT;
  }

  public String search() {
    Employee user = getUser();
    supplierTypeList = getCodeList("ebiz_supplier_resource");
    supplierList = outcomeService.roGetSupplierByType(kenSupplierType, user
        .getGroup().getGroupId());
    paymentList = getCodeList("ebiz_pay_mode");
    //
    paynoticeList = outcomeService.roGetOwedList(kenSupplierId, kenTourNo);
    stnList = outcomeService.roGetTourList(user.getGroup().getGroupId(),
        user.getUserId(), kenSupplierId);
    for (int i = 0; i < paynoticeList.size(); i++) {
      TourCost payNotice = paynoticeList.get(i);
      payNotice.setId(i + 1);
    }
    return SUCCESS;
  }

  public String submit() {
    Employee user = getUser();

    if (user.getUserId() == 0) {
      addActionError("严重错误,重新登陆系统.");
      return INPUT;
    }

    for (int i = paynoticeList.size() - 1; i >= 0; i--) {
      TourCost item = paynoticeList.get(i);
      if (item.getNowpayPayment() == 0f) {
        paynoticeList.remove(i);
      }
    }

    if (paynoticeList.size() == 0) {
      addActionError("未填写付款金额.");
    }

    if (hasActionErrors())
      return INPUT;

    outcome.setCreatedBy(user.getUserId());
    outcome.setUpdatedBy(user.getUserId());
    outcome.getCustomer().setCustomerId(kenSupplierId);
    outcome.setTourNo(kenTourNo);
    outcome.setOpApprovedby(user.getUserId());

    outcomeService.txSaveBillhead(outcome, audit);
    addActionMessage("付款申请书制作成功！");
    return SUCCESS;
  }

  public List<LabelValueBean> getSupplierTypeList() {
    return supplierTypeList;
  }

  public List<Customer> getSupplierList() {
    return supplierList;
  }

  public void setSupplierTypeList(List<LabelValueBean> supplierTypeList) {
    this.supplierTypeList = supplierTypeList;
  }

  public Outcome getOutcome() {
    return outcome;
  }

  public void setOutcome(Outcome outcome) {
    this.outcome = outcome;
  }

  public int getKenSupplierId() {
    return kenSupplierId;
  }

  public void setKenSupplierId(int kenSupplierId) {
    this.kenSupplierId = kenSupplierId;
  }

  public void setSupplierList(List<Customer> supplierList) {
    this.supplierList = supplierList;
  }

  public int getOutcomeId() {
    return outcomeId;
  }

  public void setOutcomeId(int outcomeId) {
    this.outcomeId = outcomeId;
  }

  public String getKenTourNo() {
    return kenTourNo;
  }

  public void setKenTourNo(String kenTourNo) {
    this.kenTourNo = kenTourNo;
  }

  public List<LabelValueBean> getPaymentList() {
    return paymentList;
  }

  public void setPaymentList(List<LabelValueBean> paymentList) {
    this.paymentList = paymentList;
  }

  public String getKenSupplierType() {
    return kenSupplierType;
  }

  public void setKenSupplierType(String kenSupplierType) {
    this.kenSupplierType = kenSupplierType;
  }

  public List<LabelValueBean> getStnList() {
    return stnList;
  }

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  public String getAudit() {
    return audit;
  }

  public void setAudit(String audit) {
    this.audit = audit;
  }

  public List<Team> getTeamList() {
    return teamList;
  }

}
