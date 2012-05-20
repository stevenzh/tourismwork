package com.opentravelsoft.action.manage.finance.income;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.opentravelsoft.service.order.BookingService;
import com.opentravelsoft.service.setting.EmployeeService;

/**
 * 制作收款账单
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 */
public class IncomeAction extends ManageAction {
  private static final long serialVersionUID = -1255674570726952412L;

  @Autowired
  private IncomeService incomeService;

  @Autowired
  private EmployeeService salesmanService;

  @Autowired
  private BookingService bookingService;

  @Autowired
  private CustomerService agentService;

  private Customer agent;

  /** 订单 */
  private Booking book = new Booking();

  /** 客户(客人提供商) */
  private List<LabelValueBean> agentList = new ArrayList<LabelValueBean>();

  /** 地区 */
  private List<LabelValueBean> regions = new ArrayList<LabelValueBean>();

  // -------------------------------------------------------------------------

  /** 查询条件 */
  private int kenCustomerId;

  /**
   * 部门
   */
  private long teamId;

  /**
   * 人数合计
   */
  private int totalPax;

  /**
   * 应付合计
   */
  private BigDecimal totalExpense;

  /**
   * 已收合计
   */
  private BigDecimal totalPayCosts;

  /**
   * 未收合计
   */
  private BigDecimal totalUnPay;

  /** 收款保存后生成的ID */
  private long incomeId;

  // -------------------------------------------------------------------------

  private Income gathering = new Income();

  private List<Team> teamList;

  private List<Employee> salesManList;

  private List<LabelValueBean> payModeList;

  private List<Booking> bookings = new ArrayList<Booking>();

  public String input() {
    regions = bookingService.getStateByCountry("CN");
    teamList = incomeService.getOperatorTeamList();
    payModeList = getCodeList("ebiz_pay_mode");
    if (teamList.size() > 0)
      teamId = teamList.get(0).getTeamId();

    salesManList = salesmanService.roGetSalesList();
    agentList = bookingService.roGetAgentByArea("", "");

    gathering.setAmount(new BigDecimal(0));
    gathering.setOffSetAmount(new BigDecimal(0));
    return INPUT;
  }

  public String search() {
    regions = bookingService.getStateByCountry("CN");
    teamList = incomeService.getOperatorTeamList();
    payModeList = getCodeList("ebiz_pay_mode");
    if (teamList.size() > 0)
      teamId = teamList.get(0).getTeamId();
    salesManList = salesmanService.roGetSalesList();
    agentList = bookingService.roGetAgentByArea("", "");
    bookings = incomeService.roGetIncomeBookings(kenCustomerId);
    agent = agentService.findAgent(kenCustomerId);

    for (int i = 0; i < bookings.size(); i++) {
      Booking book = bookings.get(i);
      book.setId(i + 1);
      bookings.set(i, book);

      totalPax += book.getPax();
      totalExpense = totalExpense.add(book.getDbamt());
      totalPayCosts = totalPayCosts.add(book.getPayCosts());
      totalUnPay = totalExpense.subtract(totalPayCosts);
    }
    return SUCCESS;
  }

  public String submit() {
    Employee user = getUser();
    for (int i = bookings.size() - 1; i >= 0; i--) {
      Booking book = bookings.get(i);
      if (book.getPayBack().doubleValue() <= 0)
        bookings.remove(i);
    }

    gathering.getCustomer().setCustomerId(kenCustomerId);
    gathering.setCreatedBy(user.getUserId());

    incomeId = incomeService.txSaveIncome(gathering);
    addActionMessage("收款登记成功.");
    return SUCCESS;
  }

  public List<Team> getTeamList() {
    return teamList;
  }

  public Income getGathering() {
    return gathering;
  }

  public void setGathering(Income gathering) {
    this.gathering = gathering;
  }

  public List<Employee> getSalesManList() {
    return salesManList;
  }

  public List<LabelValueBean> getPayModeList() {
    return payModeList;
  }

  public List<Booking> getBookings() {
    return bookings;
  }

  public long getDepartmentNo() {
    return teamId;
  }

  public void setDepartmentNo(long teamId) {
    this.teamId = teamId;
  }

  public int getTotalPax() {
    return totalPax;
  }

  public void setTotalPax(int totalPax) {
    this.totalPax = totalPax;
  }

  public BigDecimal getTotalExpense() {
    return totalExpense;
  }

  public void setTotalExpense(BigDecimal totalExpense) {
    this.totalExpense = totalExpense;
  }

  public BigDecimal getTotalPayCosts() {
    return totalPayCosts;
  }

  public void setTotalPayCosts(BigDecimal totalPayCosts) {
    this.totalPayCosts = totalPayCosts;
  }

  public BigDecimal getTotalUnPay() {
    return totalUnPay;
  }

  public void setTotalUnPay(BigDecimal totalUnPay) {
    this.totalUnPay = totalUnPay;
  }

  public int getKenCustomerId() {
    return kenCustomerId;
  }

  public void setKenCustomerId(int kenCustomerId) {
    this.kenCustomerId = kenCustomerId;
  }

  public List<LabelValueBean> getRegions() {
    return regions;
  }

  public void setRegions(List<LabelValueBean> regions) {
    this.regions = regions;
  }

  public Booking getBook() {
    return book;
  }

  public void setBook(Booking book) {
    this.book = book;
  }

  public List<LabelValueBean> getAgentList() {
    return agentList;
  }

  public void setAgentList(List<LabelValueBean> agentList) {
    this.agentList = agentList;
  }

  public Customer getAgent() {
    return agent;
  }

  public void setAgent(Customer agent) {
    this.agent = agent;
  }

  public long getIncomeId() {
    return incomeId;
  }

  public void setIncomeId(long incomeId) {
    this.incomeId = incomeId;
  }
}
