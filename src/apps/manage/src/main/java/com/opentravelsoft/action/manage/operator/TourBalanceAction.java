package com.opentravelsoft.action.manage.operator;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.common.TeamType;
import com.opentravelsoft.entity.Customer;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.service.MyPageService;
import com.opentravelsoft.service.operator.TourService;
import com.opentravelsoft.service.setting.EmployeeService;
import com.opentravelsoft.util.Arith;

/**
 * 需要确认的单团核算
 */
public class TourBalanceAction extends ManageAction {

  private static final long serialVersionUID = 1L;

  protected static final Log logger = LogFactory.getLog(ManageAction.class);

  private TourService tourService;

  private EmployeeService employeeService;

  private MyPageService myPageService;

  /** 团号 */
  private String tourNo;

  /** 部门 */
  private long kenTeamId = 0;

  /** 专管员 */
  private Long kenUserId;

  /** 线路名 */
  private String kenLineName;

  /** 出团日期 -开始 */
  private Date kenStartDate;

  /** 出团日期 -截止 */
  private Date kenEndDate;

  private List<Employee> employees = new ArrayList<Employee>();

  private List<Team> teamList = new ArrayList<Team>();

  private List<Plan> tours = new ArrayList<Plan>();

  private List<Customer> supplierList = new ArrayList<Customer>();

  private Plan tour = new Plan();

  private List<LabelValueBean> currencyList = new ArrayList<LabelValueBean>();

  /** 总人数(领队除外) */
  private int allPax;

  /** 总领队数年 */
  private int allLeaderPax;

  /** 总收入 */
  private double allAmount;

  /** 已收款 */
  private double allAlAmount;

  /** 总的纯团费 */
  private double allTourAmount;

  /** 总的成本 */
  private double allCostAmount;

  /** 总毛利 */
  private double allGrossAmount;

  /** 总毛利率 */
  private double allGrossAmountRate;

  @Autowired
  public void setEmployeeService(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  public String execute() {
    Employee user = getUser();
    kenUserId = user.getUserId();
    teamList = tourService.getTeamList(kenUserId, TeamType.Operator);
    if (teamList.size() > 0)
      kenTeamId = teamList.get(0).getTeamId();
    employees = employeeService.getUserByTeam(kenTeamId);
    kenStartDate = new Date();

    tours = myPageService.roGetTours(kenTeamId, kenUserId, kenLineName,
        kenStartDate, kenEndDate);

    // --------------------------------
    allPax = 0;
    allLeaderPax = 0;
    allAmount = 0.0;
    allAlAmount = 0.0;
    allTourAmount = 0.0;
    allCostAmount = 0.0;
    allGrossAmount = 0.0;
    allGrossAmountRate = 0.0;

    for (Plan singleTourBalance : tours) {
      allPax += singleTourBalance.getPax();
      allLeaderPax += singleTourBalance.getLeaderPax();
      allAmount = Arith.add(allAmount, singleTourBalance.getAmount());
      allAlAmount = Arith.add(allAlAmount, singleTourBalance.getAlAmount());
      allTourAmount = Arith.add(allTourAmount,
          singleTourBalance.getTourAmount());
      allCostAmount = Arith.add(allCostAmount, singleTourBalance.getCost());
      // 四舍五入保留两位小数
      allAmount = Arith.round(allAmount, 2);
      allTourAmount = Arith.round(allTourAmount, 2);
      allCostAmount = Arith.round(allCostAmount, 2);
    }

    allGrossAmount = Arith.sub(allTourAmount, allCostAmount);
    if (allTourAmount != 0.0) {
      allGrossAmountRate = Arith.div(allGrossAmount, allTourAmount);
      allGrossAmountRate = Arith.mul(allGrossAmountRate, 100.0);
      // 四舍五入保留两位小数
      allGrossAmountRate = Arith.round(allGrossAmountRate, 2);
    }

    currentPage(tours.size());
    return SUCCESS;
  }

  @Autowired
  public void setTourService(TourService tourService) {
    this.tourService = tourService;
  }

  @Autowired
  public void setMyPageService(MyPageService myPageService) {
    this.myPageService = myPageService;
  }

  public String getTourNo() {
    return tourNo;
  }

  public void setTourNo(String tourNo) {
    this.tourNo = tourNo;
  }

  public long getKenDepartmentId() {
    return kenTeamId;
  }

  public void setKenDepartmentId(long teamId) {
    this.kenTeamId = teamId;
  }

  public Long getKenEmployeeId() {
    return kenUserId;
  }

  public void setKenEmployeeId(Long kenEmployeeId) {
    this.kenUserId = kenEmployeeId;
  }

  public String getKenRouteName() {
    return kenLineName;
  }

  public void setKenRouteName(String kenRouteName) {
    this.kenLineName = kenRouteName;
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

  public List<Employee> getEmployees() {
    return employees;
  }

  public void setEmployees(List<Employee> employees) {
    this.employees = employees;
  }

  public List<Team> getTeamList() {
    return teamList;
  }

  public List<Plan> getTours() {
    return tours;
  }

  public void setTours(List<Plan> tours) {
    this.tours = tours;
  }

  public List<Customer> getSupplierList() {
    return supplierList;
  }

  public void setSupplierList(List<Customer> supplierList) {
    this.supplierList = supplierList;
  }

  public Plan getTour() {
    return tour;
  }

  public void setTour(Plan tour) {
    this.tour = tour;
  }

  public List<LabelValueBean> getCurrencyList() {
    return currencyList;
  }

  public void setCurrencyList(List<LabelValueBean> currencyList) {
    this.currencyList = currencyList;
  }

  public int getAllPax() {
    return allPax;
  }

  public void setAllPax(int allPax) {
    this.allPax = allPax;
  }

  public int getAllLeaderPax() {
    return allLeaderPax;
  }

  public void setAllLeaderPax(int allLeaderPax) {
    this.allLeaderPax = allLeaderPax;
  }

  public double getAllAmount() {
    return allAmount;
  }

  public void setAllAmount(double allAmount) {
    this.allAmount = allAmount;
  }

  public double getAllAlAmount() {
    return allAlAmount;
  }

  public void setAllAlAmount(double allAlAmount) {
    this.allAlAmount = allAlAmount;
  }

  public double getAllTourAmount() {
    return allTourAmount;
  }

  public void setAllTourAmount(double allTourAmount) {
    this.allTourAmount = allTourAmount;
  }

  public double getAllCostAmount() {
    return allCostAmount;
  }

  public void setAllCostAmount(double allCostAmount) {
    this.allCostAmount = allCostAmount;
  }

  public double getAllGrossAmount() {
    return allGrossAmount;
  }

  public void setAllGrossAmount(double allGrossAmount) {
    this.allGrossAmount = allGrossAmount;
  }

  public double getAllGrossAmountRate() {
    return allGrossAmountRate;
  }

  public void setAllGrossAmountRate(double allGrossAmountRate) {
    this.allGrossAmountRate = allGrossAmountRate;
  }

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }
}