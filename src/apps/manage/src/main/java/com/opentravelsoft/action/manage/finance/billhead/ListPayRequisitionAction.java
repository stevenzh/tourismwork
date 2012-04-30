package com.opentravelsoft.action.manage.finance.billhead;

import java.util.Date;
import java.util.List;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.entity.finance.Outcome;
import com.opentravelsoft.service.finance.OutcomeService;
import com.opentravelsoft.service.setting.EmployeeService;
import com.opentravelsoft.util.StringUtil;

/**
 * 查询付款申请书
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.2 $ $Date: 2009/04/10 07:47:29 $
 */
public class ListPayRequisitionAction extends ManageAction {
  private static final long serialVersionUID = -7852853734687360320L;

  @Autowired
  private OutcomeService outcomeService;

  @Autowired
  private EmployeeService employeeService;

  private List<Outcome> outcomeList;

  private List<Employee> employeList;

  private List<Team> teamList;

  private int outcomeId;

  /** 列表[已付款、未付款、所有] */
  private List<LabelValueBean> payList;

  /** 列表[已审核，未审核、所有] */
  private List<LabelValueBean> auditList;

  // 查询条件 ------------------------------------------------------------------

  private int kenMaker;

  private int kenDepartment;

  /** 团号 */
  private String kenTourNo;

  /** 申请书提交到财务时间 */
  private Date kenStartDate;

  private Date kenEndDate;

  /** 出发时间 */
  private Date kenStartOutDate;

  private Date kenEndOutDate;

  /** 是否审核 */
  private String kenAudit;

  /** 是否付款 */
  private String kenPay;

  public String input() {
    buildSysdate();
    kenAudit = "O";
    kenPay = "N"; // 未付款
    kenEndDate = systemDate;
    kenStartOutDate = systemDate;
    kenMaker = 0;
    kenDepartment = 0;
    teamList = outcomeService.getOperatorTeamList();
    employeList = employeeService.getUserByTeam(kenDepartment);

    payList = getCodeList("ebiz_pay_requisition");
    auditList = getCodeList("ebiz_agent_opKey");
    return INPUT;
  }

  public String search() {
    buildSysdate();
    if (!StringUtil.hasLength(kenAudit))
      kenAudit = "O";
    if (!StringUtil.hasLength(kenPay))
      kenPay = "N";

    teamList = outcomeService.getOperatorTeamList();
    employeList = employeeService.getUserByTeam(kenDepartment);
    outcomeList = outcomeService.roGetBillheadList(kenMaker, null, null,
        kenStartOutDate, kenEndOutDate, true, kenAudit, kenPay, kenStartDate,
        kenEndDate);
    payList = getCodeList("ebiz_pay_requisition");
    auditList = getCodeList("ebiz_agent_opKey");
    return SUCCESS;
  }

  public String delete() {
    int resu = outcomeService.txDeleteBillhead(outcomeId);
    return SUCCESS;
  }

  /**
   * 付款申请书置位(打回计调操作)
   * 
   * @return
   */
  public String opModifyPay() {
    int ret = outcomeService.txOpModifyPayReturn(outcomeId);
    if (ret == 0) {
      addActionMessage("已成功打回计调操作！");
      return SUCCESS;
    } else {
      addActionError("操作失败！");
      return INPUT;
    }
  }

  public List<Team> getTeamList() {
    return teamList;
  }

  public List<Outcome> getOutcomeList() {
    return outcomeList;
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

  public int getKenMaker() {
    return kenMaker;
  }

  public void setKenMaker(int kenMaker) {
    this.kenMaker = kenMaker;
  }

  public List<Employee> getEmployeList() {
    return employeList;
  }

  public void setOutcomeId(int outcomeId) {
    this.outcomeId = outcomeId;
  }

  public int getKenDepartment() {
    return kenDepartment;
  }

  public void setKenDepartment(int kenDepartment) {
    this.kenDepartment = kenDepartment;
  }

  public String getKenTourNo() {
    return kenTourNo;
  }

  public void setKenTourNo(String kenTourNo) {
    this.kenTourNo = kenTourNo;
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

  public List<LabelValueBean> getPayList() {
    return payList;
  }

  public List<LabelValueBean> getAuditList() {
    return auditList;
  }

}
