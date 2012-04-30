package com.opentravelsoft.action.manage.operator;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Customer;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.finance.Outcome;
import com.opentravelsoft.service.finance.OutcomeService;
import com.opentravelsoft.service.operator.TicketService;

/**
 * 财务审核付款申请书
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:26 $
 */
public class EditParcelAction extends ManageAction {
  private static final long serialVersionUID = 7046152940418063549L;

  @Autowired
  private OutcomeService outcomeService;

  @Autowired
  private TicketService ticketService;

  // -------------------------------------------------------------------------

  /** 操作员 */
  private String kenMaker;

  /** 部门 */
  private String kenDepartment;

  /** 出发时间 */
  private Date kenStartOutDate;

  private Date kenEndOutDate;

  /**
   * 配送状态 0:申请 1: 开始 2: 暂停 3: 完成 4: 取消
   */
  private short kenCarryStatus;

  /** 供应商ID */
  private String kenSupplierId;

  // -------------------------------------------------------------------------
  /** 应付客户 */
  private Customer supplier;

  private int outcomeId;

  private Outcome billhead = new Outcome();

  public String input() {
    int isAuditing = -1;

    billhead = outcomeService.roGetBillhead(outcomeId);
    if (isAuditing == 0)
      billhead.setIsAuditing('Y');
    else {
      billhead.setIsAuditing('N');
      addActionError("本团有现结客户未付清款项，此付款申请书不能审核！");
    }

    supplier = billhead.getCustomer();

    return INPUT;
  }

  /**
   * 标记为已读
   * 
   * @return
   */
  public String start() {
    Employee user = getUser();
    billhead.setOutcomeId(outcomeId);
    billhead.setUpdatedBy(user.getUserId());

    int ret = ticketService.txStartParcel(billhead);

    if (ret == 0)
      addActionMessage("标记成功！");
    else
      addActionMessage("标记失败！");

    return SUCCESS;
  }

  public String complete() {
    billhead.setOutcomeId(outcomeId);
    int ret = ticketService.txCompleteParcel(billhead);
    if (ret == 0)
      addActionMessage("审核成功！");
    else
      addActionMessage("审核失败！");

    return SUCCESS;
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

  public Customer getSupplier() {
    return supplier;
  }

  public short getKenCarryStatus() {
    return kenCarryStatus;
  }

  public void setKenCarryStatus(short kenCarryStatus) {
    this.kenCarryStatus = kenCarryStatus;
  }

  public String getKenSupplierId() {
    return kenSupplierId;
  }

  public void setKenSupplierId(String kenSupplierId) {
    this.kenSupplierId = kenSupplierId;
  }

}
