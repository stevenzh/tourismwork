package com.opentravelsoft.action.manage.operator.billhead;

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

/**
 * 修改付款申请书
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.2 $ $Date: 2009/04/10 07:47:31 $
 */

public class ListPayRequisitionAction extends ManageAction {
  private static final long serialVersionUID = -7852853734687360320L;

  @Autowired
  private OutcomeService outcomeService;
  @Autowired
  private EmployeeService employeeSevice;

  private List<Outcome> outcomeList;

  private List<Employee> employeList;

  private List<Team> teamList;

  private int outcomeId;

  /** 团号列表 */
  private List<LabelValueBean> stnList;

  /** 列表[已付款、未付款、所有] */
  private List<LabelValueBean> payList;

  /** 列表[已审核，未审核、所有] */
  private List<LabelValueBean> auditList;

  // 查询条件 ------------------------------------------------------------------

  private long kenUserId;

  private long kenTeamId;

  /** 团号 */
  private String kenTourNo;

  /** 申请书创建时间 */
  private Date kenStartDate;

  private Date kenEndDate;

  /** 出发时间 */
  private Date kenStartOutDate;

  private Date kenEndOutDate;

  /** 是否财务审核 */
  private String kenAudit;

  /** 是否付款 */
  private String kenPay;

  // -------------------------------------------------------------------------

  public String input() {
    Employee user = getUser();
    buildSysdate();
    kenPay = "%";
    kenAudit = "N"; // 财务为审核
    kenEndDate = systemDate;
    kenStartOutDate = systemDate;
    kenUserId = user.getUserId();
    teamList = outcomeService.getOperatorTeamList();
    if (teamList.size() > 0)
      kenTeamId = teamList.get(0).getTeamId();
    employeList = employeeSevice.getUserByTeam(kenTeamId);

    stnList = outcomeService.roGetTourList(kenTeamId, user.getUserId(), 0);

    payList = getCodeList("ebiz_pay_requisition");
    auditList = getCodeList("ebiz_agent_opKey");
    return INPUT;
  }

  public String search() {
    buildSysdate();
    teamList = outcomeService.getOperatorTeamList();
    employeList = employeeSevice.getUserByTeam(kenTeamId);
    outcomeList = outcomeService.roGetBillheadList(kenUserId, kenStartDate,
        kenEndDate, kenStartOutDate, kenEndOutDate, false, kenAudit, kenPay,
        null, null);
    stnList = outcomeService.roGetTourList(kenTeamId, kenUserId, 0);
    payList = getCodeList("ebiz_pay_requisition");
    auditList = getCodeList("ebiz_agent_opKey");
    return SUCCESS;
  }

  public String delete() {
    int resu = outcomeService.txDeleteBillhead(outcomeId);
    return SUCCESS;
  }

  public List<Team> getTeamList() {
    return teamList;
  }

  public List<Outcome> getOutcomeList() {
    return outcomeList;
  }

  public void setOutcomeList(List<Outcome> outcomeList) {
    this.outcomeList = outcomeList;
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

  public long getKenMaker() {
    return kenUserId;
  }

  public void setKenMaker(long kenMaker) {
    this.kenUserId = kenMaker;
  }

  public List<Employee> getEmployeList() {
    return employeList;
  }

  public void setOutcomeId(int outcomeId) {
    this.outcomeId = outcomeId;
  }

  public long getKenDepartment() {
    return kenTeamId;
  }

  public void setKenDepartment(long teamId) {
    this.kenTeamId = teamId;
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

  public List<LabelValueBean> getStnList() {
    return stnList;
  }

  public List<LabelValueBean> getPayList() {
    return payList;
  }

  public List<LabelValueBean> getAuditList() {
    return auditList;
  }

  public String getKenPay() {
    return kenPay;
  }

  public void setKenPay(String kenPay) {
    this.kenPay = kenPay;
  }

  public String getKenAudit() {
    return kenAudit;
  }

  public void setKenAudit(String kenAudit) {
    this.kenAudit = kenAudit;
  }

}
