package com.opentravelsoft.action.manage.finance.income;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.entity.finance.Income;
import com.opentravelsoft.service.finance.IncomeService;
import com.opentravelsoft.service.operator.TourService;
import com.opentravelsoft.service.setting.EmployeeService;

/**
 * 单团对应订单销账
 * 
 * @author zhangst
 */
public class TourIncomeAction extends ManageAction {
  private static final long serialVersionUID = 5799682184580813453L;

  @Autowired
  private IncomeService incomeService;

  @Autowired
  private EmployeeService salesmanService;

  @Autowired
  private TourService tourService;

  /** 部门列表 */
  private List<Team> teamList;

  /** 销售员列表 */
  private List<Employee> salesManList;

  /** 付款方式列表 */
  private List<LabelValueBean> payModeList;

  // private List<LabelValueBean> supplierTypeList;

  private List<Booking> bookList = new ArrayList<Booking>();

  private Plan tour;

  private Income gathering;

  private String tourNo;

  private String routeName;

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

  public String get() {
    teamList = incomeService.getOperatorTeamList();
    salesManList = salesmanService.roGetSalesList();
    payModeList = getCodeList("ebiz_pay_mode");

    bookList = tourService.roGetBookList(tourNo);
    Booking book;
    for (int i = 0; i < bookList.size(); i++) {
      book = bookList.get(i);
      book.setId(i + 1);
      bookList.set(i, book);

      totalPax += book.getPax();
      totalExpense = totalExpense.add(book.getDbamt());
      totalPayCosts = totalPayCosts.add(book.getPayCosts());
      totalUnPay = totalExpense.subtract(totalPayCosts);
    }
    return INPUT;
  }

  public String submit() {
    Employee user = getUser();
    for (int i = bookList.size() - 1; i >= 0; i--) {
      Booking book = bookList.get(i);
      if (book.getPayBack().doubleValue() == 0)
        bookList.remove(i);
    }
    gathering.setCreatedBy(user.getUserId());
    // gathering.setBookList(bookList);
    //
    // // 收款登记
    // int i = incomeService.txSingleTourSaveIncome(gathering);
    // if (i == 0) {
    // addActionMessage("单团收款登记成功.");
    // } else {
    // addActionError("单团收款登记失败.");
    // }
    return SUCCESS;
  }

  public Plan getTour() {
    return tour;
  }

  public void setTour(Plan tour) {
    this.tour = tour;
  }

  public String getTourNo() {
    return tourNo;
  }

  public void setTourNo(String tourNo) {
    this.tourNo = tourNo;
  }

  public List<LabelValueBean> getPayModeList() {
    return payModeList;
  }

  public void setPayModeList(List<LabelValueBean> payModeList) {
    this.payModeList = payModeList;
  }

  public Income getGathering() {
    return gathering;
  }

  public void setGathering(Income gathering) {
    this.gathering = gathering;
  }

  public List<Booking> getBookList() {
    return bookList;
  }

  public void setBookList(List<Booking> bookList) {
    this.bookList = bookList;
  }

  public String getRouteName() {
    return routeName;
  }

  public void setRouteName(String routeName) {
    this.routeName = routeName;
  }

  // public List<LabelValueBean> getSupplierTypeList()
  // {
  // return supplierTypeList;
  // }

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

  public List<Team> getTeamList() {
    return teamList;
  }

  public List<Employee> getSalesManList() {
    return salesManList;
  }

}
