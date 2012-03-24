package com.opentravelsoft.action.manage.finance.billhead;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Customer;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.entity.finance.Outcome;
import com.opentravelsoft.service.finance.OutcomeService;
import com.opentravelsoft.service.operator.TourService;
import com.opentravelsoft.service.setting.EmployeeService;

/**
 * 实付登记
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.2 $ $Date: 2009/04/10 07:47:29 $
 */
public class FactualRegisterAction extends ManageAction {
  private static final long serialVersionUID = -8806143847619980510L;

  private OutcomeService outcomeService;

  private TourService tourService;

  private EmployeeService employeeSevice;

  @Autowired
  public void setEmployeeSevice(EmployeeService employeeSevice) {
    this.employeeSevice = employeeSevice;
  }

  /** 部门 */
  private long kenTeamId;

  /** 操作员 */
  private long kenUserId;

  private Date kenStartDate;

  private Date kenEndDate;

  private List<Employee> employees = new ArrayList<Employee>();

  private List<Team> teamList = new ArrayList<Team>();

  private List<Customer> supplierList = new ArrayList<Customer>();

  private List<Outcome> outcomeList = new ArrayList<Outcome>();

  private Outcome billhead;

  /** 付款申请号 */
  private int[] outcomeIds;

  /** 水单号 */
  private String billNo;

  /** 支付日期 */
  private Date payDate;

  /** 付款申请号 */
  private int outcomeId;

  /** 是否已登记 */
  private String register = "N";

  private List<LabelValueBean> registerList;

  @Autowired
  public void setOutcomeService(OutcomeService outcomeService) {
    this.outcomeService = outcomeService;
  }

  @Autowired
  public void setTourService(TourService tourSearchService) {
    this.tourService = tourSearchService;
  }

  /**
   * 进入实付登记搜索
   * 
   * @return
   */
  public String logoin() {
    Employee user = getUser();
    kenUserId = user.getUserId();
    teamList = tourService.getOperatorTeamList();
    if (teamList.size() > 0)
      kenTeamId = teamList.get(0).getTeamId();
    employees = employeeSevice.getUserByTeam(kenTeamId);
    registerList = getCodeList("ebiz_register_state");
    register = "N";

    buildSysdate();
    Calendar cal = Calendar.getInstance();
    cal.setTime(systemDate);
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    kenEndDate = cal.getTime();
    cal.add(Calendar.MONTH, -1);
    kenStartDate = cal.getTime();

    return INPUT;
  }

  /**
   * 实付登记搜索
   */
  public String input() {
    outcomeList = outcomeService.roGetOutcomeList(kenUserId, kenStartDate,
        kenEndDate, register);
    // supplierList = contactService.getAllSupplier();
    teamList = tourService.getOperatorTeamList();
    employees = employeeSevice.getUserByTeam(kenTeamId);
    registerList = getCodeList("ebiz_register_state");
    payDate = new Date();

    /*
     * if (!(outcomeList.isEmpty())) for (Billhead outcome : outcomeList) { for
     * (Supplier supplier : supplierList) { if (outcome.getSupplierId() ==
     * supplier.getSupplierId()) {
     * outcome.setSupplierName(supplier.getSupplierName()); break; } } for
     * (Employee employee : employees) { if
     * (outcome.getCreatedby().equals(employee.getUid())) {
     * outcome.setCreatedbyName(employee.getUserName()); break; } } }
     */

    return INPUT;
  }

  /**
   * 实付登记
   * 
   * @return
   */
  public String submit() {
    Employee user = getUser();
    if (null != payDate) {
      int ret = outcomeService.txFactualRegister(outcomeIds, user.getUserId(),
          billNo, payDate);
      if (ret == 0)
        addActionError("登记成功！");
      else
        addActionError("登记失败！");
    } else
      addActionError("支付日期必须输入！");

    return SUCCESS;
  }

  /**
   * 付款申请内容
   * 
   * @return
   */
  public String PayNoticeDetail() {
    billhead = outcomeService.roGetBillhead(outcomeId);

    return SUCCESS;
  }

  public long getKenDepartmentId() {
    return kenTeamId;
  }

  public void setKenDepartmentId(long teamId) {
    this.kenTeamId = teamId;
  }

  public long getKenEmployeeId() {
    return kenUserId;
  }

  public void setKenEmployeeId(long kenEmployeeId) {
    this.kenUserId = kenEmployeeId;
  }

  public List<Employee> getEmployees() {
    return employees;
  }

  public void setEmployees(List<Employee> employees) {
    this.employees = employees;
  }

  public List<Team> getTeamList() {
    return teamList;
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

  public List<Outcome> getOutcomeList() {
    return outcomeList;
  }

  public void setOutcomeList(List<Outcome> outcomeList) {
    this.outcomeList = outcomeList;
  }

  public List<Customer> getSupplierList() {
    return supplierList;
  }

  public void setSupplierList(List<Customer> supplierList) {
    this.supplierList = supplierList;
  }

  public int[] getOutcomeIds() {
    return outcomeIds;
  }

  public void setOutcomeIds(int[] outcomeIds) {
    this.outcomeIds = outcomeIds;
  }

  public String getBillNo() {
    return billNo;
  }

  public void setBillNo(String billNo) {
    this.billNo = billNo;
  }

  public Date getPayDate() {
    return payDate;
  }

  public void setPayDate(Date payDate) {
    this.payDate = payDate;
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

  public String getRegister() {
    return register;
  }

  public void setRegister(String register) {
    this.register = register;
  }

  public List<LabelValueBean> getRegisterList() {
    return registerList;
  }

}
