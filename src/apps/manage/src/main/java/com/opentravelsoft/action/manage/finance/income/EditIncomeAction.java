package com.opentravelsoft.action.manage.finance.income;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.Customer;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.entity.finance.Income;
import com.opentravelsoft.service.account.CustomerService;
import com.opentravelsoft.service.finance.IncomeService;
import com.opentravelsoft.service.setting.EmployeeService;

/**
 * 修改收款销帐单
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.2 $ $Date: 2009/04/10 07:47:29 $
 */
public class EditIncomeAction extends ManageAction {
  private static final long serialVersionUID = -4166608849868194948L;

  @Autowired
  private IncomeService incomeService;

  @Autowired
  private EmployeeService salesmanService;

  @Autowired
  private CustomerService customerService;

  private List<Team> teamList;

  private List<Employee> salesManList;

  private List<LabelValueBean> payModeList;

  private Income gathering = new Income();

  private int incomeId;

  List<Booking> bookList = new ArrayList<Booking>();

  /**
   * 人数合计
   */
  private int totalPax;

  /**
   * 应付合计
   */
  private double totalExpense;

  /**
   * 已收合计
   */
  private double totalPayCosts;

  /**
   * 未收合计
   */
  private double totalUnPay;

  private Customer customer = null;

  // -------------------------------------------------------------------------
  // 查询条件

  /** 部门ＩＤ */
  private String departmentNo;

  /** 地区ＩＤ */
  private String regionId;

  private int customerId;

  private Date kenStartDate;

  private Date kenEndDate;

  private double kenStartMon;

  private double kenEndMon;

  // -------------------------------------------------------------------------

  public String input() {
    teamList = incomeService.getOperatorTeamList();
    salesManList = salesmanService.roGetSalesList();
    payModeList = getCodeList("ebiz_pay_mode");

    gathering = incomeService.roGetIncome(incomeId);
    bookList = incomeService.roGetUnpayList(incomeId);
    // 该客户其他未付款订单
    List<Booking> unpayBooks = incomeService.roGetIncomeBookings(gathering
        .getCustomer().getCustomerId());

    customer = customerService.findAgent(gathering.getCustomer()
        .getCustomerId());

    for (int i = 0; i < bookList.size(); i++) {
      Booking book = bookList.get(i);
      book.setId(i + 1);
      bookList.set(i, book);

      totalPax += book.getPax();
      totalExpense += book.getDbamt();
      totalPayCosts += book.getPayCosts();
      totalUnPay = totalExpense - totalPayCosts;
    }

    for (int i = 0; i < unpayBooks.size(); i++) {
      bookList.add(unpayBooks.get(i));
    }

    return INPUT;
  }

  public String submit() {
    Employee user = getUser();
    gathering.setUpdateBy(user.getUserId());
    gathering.getCustomer().setCustomerId(customerId);
    incomeService.txUpdateIncome(gathering);

    payModeList = getCodeList("ebiz_pay_mode");
    salesManList = salesmanService.roGetSalesList();
    return SUCCESS;
  }

  public void setAgentService(CustomerService agentService) {
    this.customerService = agentService;
  }

  public String getDepartmentNo() {
    return departmentNo;
  }

  public void setDepartmentNo(String departmentNo) {
    this.departmentNo = departmentNo;
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

  public List<Team> getTeamList() {
    return teamList;
  }

  public double getKenStartMon() {
    return kenStartMon;
  }

  public void setKenStartMon(double kenStartMon) {
    this.kenStartMon = kenStartMon;
  }

  public double getKenEndMon() {
    return kenEndMon;
  }

  public void setKenEndMon(double kenEndMon) {
    this.kenEndMon = kenEndMon;
  }

  public Income getGathering() {
    return gathering;
  }

  public void setGathering(Income gathering) {
    this.gathering = gathering;
  }

  public int getCompanyId() {
    return customerId;
  }

  public void setCompanyId(int companyId) {
    this.customerId = companyId;
  }

  public List<LabelValueBean> getPayModeList() {
    return payModeList;
  }

  public void setPayModeList(List<LabelValueBean> payModeList) {
    this.payModeList = payModeList;
  }

  public List<Employee> getSalesManList() {
    return salesManList;
  }

  public void setSalesManList(List<Employee> salesManList) {
    this.salesManList = salesManList;
  }

  public int getTotalPax() {
    return totalPax;
  }

  public void setTotalPax(int totalPax) {
    this.totalPax = totalPax;
  }

  public double getTotalExpense() {
    return totalExpense;
  }

  public void setTotalExpense(double totalExpense) {
    this.totalExpense = totalExpense;
  }

  public double getTotalPayCosts() {
    return totalPayCosts;
  }

  public void setTotalPayCosts(double totalPayCosts) {
    this.totalPayCosts = totalPayCosts;
  }

  public double getTotalUnPay() {
    return totalUnPay;
  }

  public void setTotalUnPay(double totalUnPay) {
    this.totalUnPay = totalUnPay;
  }

  public int getIncomeId() {
    return incomeId;
  }

  public void setIncomeId(int incomeId) {
    this.incomeId = incomeId;
  }

  public String getRegionId() {
    return regionId;
  }

  public void setRegionId(String regionId) {
    this.regionId = regionId;
  }

  public Customer getCustomer() {
    return customer;
  }

}
