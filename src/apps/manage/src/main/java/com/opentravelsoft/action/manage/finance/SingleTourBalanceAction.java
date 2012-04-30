package com.opentravelsoft.action.manage.finance;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.Customer;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.entity.TourCost;
import com.opentravelsoft.service.account.CustomerService;
import com.opentravelsoft.service.operator.TourService;
import com.opentravelsoft.service.setting.EmployeeService;
import com.opentravelsoft.util.Arith;

/**
 * 财务单团核算
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.2 $ $Date: 2009/04/10 07:47:29 $
 */
public class SingleTourBalanceAction extends ManageAction {
  private static final long serialVersionUID = 4639080263620319176L;

  @Autowired
  private TourService tourService;

  @Autowired
  private CustomerService customerService;

  @Autowired
  private EmployeeService employeeSevice;

  private int id;

  /** 团号 */
  private String tourNo;

  /** 部门 */
  private long kenTeamId = 0;

  /** 专管员 */
  private int kenUserId;

  /** 线路名 */
  private String kenLineName;

  /** 出团日期 -开始 */
  private Date kenStartDate;

  /** 出团日期 -截止 */
  private Date kenEndDate;

  private Plan plan = new Plan();

  private List<TourCost> costList = new ArrayList<TourCost>();

  private List<Employee> employees = new ArrayList<Employee>();

  private List<Team> teamList = new ArrayList<Team>();

  private List<Plan> tours = new ArrayList<Plan>();

  private List<Customer> supplierList = new ArrayList<Customer>();

  private List<LabelValueBean> costTypeList = new ArrayList<LabelValueBean>();

  private Booking book = new Booking();

  // 是否财务审核
  private String status = "N";

  private List<Booking> bookList = new ArrayList<Booking>();

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

  public String execute() {
    // 页面初始化
    teamList = tourService.getOperatorTeamList();
    if (teamList.size() > 0)
      kenTeamId = teamList.get(0).getTeamId();
    employees = employeeSevice.getUserByTeam(kenTeamId);
    return SUCCESS;
  }

  /**
   * 查找团
   * 
   * @return
   */
  public String search() {
    employees = employeeSevice.getUserByTeam(kenTeamId);
    teamList = tourService.getOperatorTeamList();
    tours = tourService.roGetTours(kenTeamId, kenUserId, kenLineName,
        kenStartDate, kenEndDate);

    allPax = 0;
    allLeaderPax = 0;
    allAmount = 0.0;
    allAlAmount = 0.0;
    allTourAmount = 0.0;
    allCostAmount = 0.0;
    allGrossAmount = 0.0;
    allGrossAmountRate = 0.0;

    for (Plan tour : tours) {
      allPax += tour.getPax();
      allLeaderPax += tour.getLeaderPax();
      allAmount = Arith.add(allAmount, tour.getAmount());
      allAlAmount = Arith.add(allAlAmount, tour.getAlAmount());
      allTourAmount = Arith.add(allTourAmount, tour.getTourAmount());
      allCostAmount = Arith.add(allCostAmount, tour.getCost());
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

  /**
   * 单团核算表修改初始化
   * 
   * @return
   */
  public String modifyInput() {
    plan = tourService.roGetTourInfo(tourNo, false, true);
    currencyList = tourService.roGetCurrencyList();

    if (!plan.getFrChecked().equals("Y")) {
      status = "N";
      costTypeList = getCodeList("ebiz_cost_type");
      supplierList = customerService.getUsableSupplier(0);
      bookList = tourService.roGetBookList(tourNo);
      double amount = 0;
      double payCosts = 0;
      int pax = 0;

      String str = new String();
      for (Booking book : bookList) {
        amount += (book.getDbamt() + book.getFinalExpense());
        payCosts += book.getPayCosts();
        pax += book.getPax();
        if (null != book.getLeaders() && !"".equals(book.getLeaders()))
          str = str + book.getLeaders();
      }
      plan.setLeaderName(str);

      plan.setMuAmount(Arith.round(amount, 2));
      plan.setAlAmount(payCosts);
      plan.setWiAmount(Arith.round(amount - payCosts, 2));
      double grossAmount = plan.getTourAmount() - plan.getCost();
      plan.setGrossAmount(Arith.round(grossAmount, 2));
      if (plan.getTourAmount() != 0) {
        double grossAmountRate = plan.getGrossAmount() / plan.getTourAmount()
            * 100;
        plan.setGrossAmountRate(Arith.round(grossAmountRate, 2));
      }

      costList = plan.getCostList();

      if (costList.isEmpty()) {
        TourCost singleTourCostAcct1 = new TourCost();
        singleTourCostAcct1.setId(1);
        singleTourCostAcct1.setUnit("元");
        singleTourCostAcct1.setFrChecked("N");
        costList.add(singleTourCostAcct1);
      }
    } else {
      status = "Y";
      addActionError("此团的核算表已审核，不能再进行修改！");
    }

    return SUCCESS;
  }

  /**
   * 单团核算表修改
   * 
   * @return
   */
  public String modify() {
    for (TourCost singleTourCostAcct : costList) {
      if (singleTourCostAcct.getCostType().equals("")) {
        addActionMessage("第" + singleTourCostAcct.getId() + "行，费用类型必须填写！");
        costTypeList = getCodeList("ebiz_cost_type");
        supplierList = customerService.getUsableSupplier(0);
        return INPUT;
      }
    }
    // singleTourBalance.setUpdatedBy(user.getUserId());
    plan.setCostList(costList);
    costTypeList = getCodeList("ebiz_cost_type");
    currencyList = tourService.roGetCurrencyList();
    supplierList = customerService.getUsableSupplier(0);
    int result = tourService.txSingleTourBalanceMake(plan);
    status = "N";

    if (0 == result) {
      addActionError("保存成功！");
      return SUCCESS;
    } else {
      addActionError("保存失败！");
      return INPUT;
    }
  }

  /**
   * 审核核算表初始化
   * 
   * @return
   */
  public String auditingInput() {
    currencyList = tourService.roGetCurrencyList();
    plan = tourService.roGetTourInfo(tourNo, false, true);

    if (!plan.getFrChecked().equals("Y")) {
      status = "N";
    } else {
      status = "Y";
      addActionError("此团的核算单已经审核！");
    }

    // 根据供应商和费用类型代码得到其名字---------------------------------------------
    supplierList = customerService.getUsableSupplier(0);
    costTypeList = getCodeList("ebiz_cost_type");
    for (TourCost singleTourCostAcct : plan.getCostList()) {
      for (Customer supplier : supplierList) {
        if (singleTourCostAcct.getCustomer().getCustomerId() == supplier
            .getSupplierId()) {
          singleTourCostAcct.getCustomer().setName(supplier.getSupplierName());
          break;
        }
      }
    }

    bookList = tourService.roGetBookList(tourNo);
    double amount = 0;
    double payCosts = 0;
    int pax = 0;

    String str = new String();
    for (Booking book : bookList) {
      amount += (book.getDbamt() + book.getFinalExpense());
      payCosts += book.getPayCosts();
      pax += book.getPax();
      if (null != book.getLeaders() && !"".equals(book.getLeaders()))
        str = str + book.getLeaders();
    }
    plan.setLeaderName(str);
    plan.setTotalPax(pax);

    if (null != plan) {
      plan.setMuAmount(Arith.round(amount, 2));
      plan.setAlAmount(payCosts);
      plan.setWiAmount(Arith.round(amount - payCosts, 2));
      double grossAmount = plan.getTourAmount() - plan.getCost();
      plan.setGrossAmount(Arith.round(grossAmount, 2));
      if (plan.getTourAmount() != 0) {
        double grossAmountRate = plan.getGrossAmount() / plan.getTourAmount()
            * 100;
        plan.setGrossAmountRate(Arith.round(grossAmountRate, 2));
      }

      costList = plan.getCostList();

    }
    if (plan.getPax() != 0) {
      double grossAmountAverage = plan.getGrossAmount() / plan.getPax();
      plan.setGrossAmountAverage(Arith.round(grossAmountAverage, 2));
    }

    return SUCCESS;
  }

  /**
   * 审核核算表
   * 
   * @return
   */
  public String auditing() {
    Employee user = getUser();
    plan = tourService.txSingleTourBalanceAuditing(tourNo, user.getUserId());

    if (null != plan && plan.getFrChecked().equals("Y")) {
      addActionError("核算表审核成功!");
      status = "Y";
    } else
      addActionError("核算表审核失败!");

    // 根据供应商和费用类型代码得到其名字---------------------------------------------
    supplierList = customerService.getUsableSupplier(0);
    currencyList = tourService.roGetCurrencyList();
    costTypeList = getCodeList("ebiz_cost_type");
    for (TourCost singleTourCostAcct : plan.getCostList()) {
      for (Customer supplier : supplierList) {
        if (singleTourCostAcct.getCustomer().getCustomerId() == supplier
            .getSupplierId()) {
          singleTourCostAcct.getCustomer().setName(supplier.getSupplierName());
          break;
        }
      }
    }
    // ----------------------------------------------------------------------

    plan = tourService.roGetTourInfo(tourNo, false, true);
    bookList = tourService.roGetBookList(tourNo);
    double amount = 0;
    double payCosts = 0;
    int pax = 0;

    String str = new String();
    for (Booking book : bookList) {
      amount += (book.getDbamt() + book.getFinalExpense());
      payCosts += book.getPayCosts();
      pax += book.getPax();
      if (null != book.getLeaders() && !"".equals(book.getLeaders()))
        str = str + book.getLeaders();
    }
    plan.setLeaderName(str);
    plan.setTotalPax(pax);

    if (null != plan) {
      plan.setMuAmount(Arith.round(amount, 2));
      plan.setAlAmount(payCosts);
      plan.setWiAmount(Arith.round(amount - payCosts, 2));
      double grossAmount = plan.getTourAmount() - plan.getCost();
      plan.setGrossAmount(Arith.round(grossAmount, 2));
      if (plan.getTourAmount() != 0) {
        double grossAmountRate = plan.getGrossAmount() / plan.getTourAmount()
            * 100;
        plan.setGrossAmountRate(Arith.round(grossAmountRate, 2));
      }

      costList = plan.getCostList();

    }
    if (plan.getPax() != 0) {
      double grossAmountAverage = plan.getGrossAmount() / plan.getPax();
      plan.setGrossAmountAverage(Arith.round(grossAmountAverage, 2));
    }

    return SUCCESS;
  }

  /**
   * 单团核算表信息
   * 
   * @return
   */
  public String balanceDetail() {
    currencyList = tourService.roGetCurrencyList();
    plan = tourService.roGetTourInfo(tourNo, false, true);

    if (null != plan) {

      // 根据供应商和费用类型代码得到其名字---------------------------------------------
      supplierList = customerService.getUsableSupplier(0);
      costTypeList = getCodeList("ebiz_cost_type");
      for (TourCost singleTourCostAcct : plan.getCostList()) {
        for (Customer supplier : supplierList) {
          if (singleTourCostAcct.getCustomer().getCustomerId() == supplier
              .getSupplierId()) {
            singleTourCostAcct.getCustomer()
                .setName(supplier.getSupplierName());
            break;
          }
        }
      }
      // ----------------------------------------------------------------------

      bookList = tourService.roGetBookList(tourNo);
      double amount = 0;
      double payCosts = 0;
      int pax = 0;

      String str = new String();
      for (Booking book : bookList) {
        amount += (book.getDbamt() + book.getFinalExpense());
        payCosts += book.getPayCosts();
        pax += book.getPax();
        if (null != book.getLeaders() && !"".equals(book.getLeaders()))
          str = str + book.getLeaders();
      }
      plan.setLeaderName(str);
      plan.setTotalPax(pax);

      if (null != plan) {
        plan.setMuAmount(Arith.round(amount, 2));
        plan.setAlAmount(payCosts);
        plan.setWiAmount(Arith.round(amount - payCosts, 2));
        double grossAmount = plan.getTourAmount() - plan.getCost();
        plan.setGrossAmount(Arith.round(grossAmount, 2));
        if (plan.getTourAmount() != 0) {
          double grossAmountRate = plan.getGrossAmount() / plan.getTourAmount()
              * 100;
          plan.setGrossAmountRate(Arith.round(grossAmountRate, 2));
        }

        costList = plan.getCostList();
      }
      if (plan.getPax() != 0) {
        double grossAmountAverage = plan.getGrossAmount() / plan.getPax();
        plan.setGrossAmountAverage(Arith.round(grossAmountAverage, 2));
      }

    }

    return SUCCESS;
  }

  /**
   * 增加行
   * 
   * @return
   */
  public String add() {
    supplierList = customerService.getUsableSupplier(0);
    currencyList = tourService.roGetCurrencyList();
    costTypeList = getCodeList("ebiz_cost_type");
    int id = 0;
    for (int i = 0; i < costList.size(); i++) {
      if (costList.get(i).getId() > id)
        id = costList.get(i).getId();
    }

    TourCost singleTourCostAcct = new TourCost();
    singleTourCostAcct.setId(id + 1);
    singleTourCostAcct.setUnit("元");
    singleTourCostAcct.setFrChecked("N");
    costList.add(singleTourCostAcct);

    return SUCCESS;
  }

  /**
   * 删除行
   * 
   * @return
   */
  public String delete() {
    supplierList = customerService.getUsableSupplier(0);
    currencyList = tourService.roGetCurrencyList();
    costTypeList = getCodeList("ebiz_cost_type");
    TourCost singleTourCostAcct = new TourCost();
    for (TourCost obj : costList) {

      if (obj.getId() == id) {

        // 删除行
        for (int i = id; i < costList.size(); i++) {
          singleTourCostAcct = costList.get(i);
          singleTourCostAcct.setId(i);
          costList.set(i, singleTourCostAcct);
        }
        double amount = 0;
        amount = plan.getCost() - obj.getAmount();
        plan.setCost(Arith.round(amount, 2));
        costList.remove(obj);
        break;
      }
    }

    if (costList.isEmpty()) {
      TourCost singleTourCostAcct1 = new TourCost();
      singleTourCostAcct.setId(id + 1);
      singleTourCostAcct1.setUnit("元");
      singleTourCostAcct.setFrChecked("N");
      costList.add(singleTourCostAcct1);
    }

    return SUCCESS;
  }

  public long getKenDepartmentId() {
    return kenTeamId;
  }

  public void setKenDepartmentId(long teamId) {
    this.kenTeamId = teamId;
  }

  public int getKenEmployeeId() {
    return kenUserId;
  }

  public void setKenEmployeeId(int kenEmployeeId) {
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

  public String getTourNo() {
    return tourNo;
  }

  public void setTourNo(String tourNo) {
    this.tourNo = tourNo;
  }

  public Plan getSingleTourBalance() {
    return plan;
  }

  public void setSingleTourBalance(Plan singleTourBalance) {
    this.plan = singleTourBalance;
  }

  public List<TourCost> getCostList() {
    return costList;
  }

  public void setCostList(List<TourCost> costList) {
    this.costList = costList;
  }

  public List<Customer> getSupplierList() {
    return supplierList;
  }

  public List<LabelValueBean> getCostTypeList() {
    return costTypeList;
  }

  public void setCostTypeList(List<LabelValueBean> costTypeList) {
    this.costTypeList = costTypeList;
  }

  public Booking getBook() {
    return book;
  }

  public void setBook(Booking book) {
    this.book = book;
  }

  public List<Booking> getBookList() {
    return bookList;
  }

  public void setBookList(List<Booking> bookList) {
    this.bookList = bookList;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public List<LabelValueBean> getCurrencyList() {
    return currencyList;
  }

  public int getAllPax() {
    return allPax;
  }

  public double getAllAmount() {
    return allAmount;
  }

  public double getAllTourAmount() {
    return allTourAmount;
  }

  public double getAllCostAmount() {
    return allCostAmount;
  }

  public double getAllGrossAmount() {
    return allGrossAmount;
  }

  public double getAllGrossAmountRate() {
    return allGrossAmountRate;
  }

  public int getAllLeaderPax() {
    return allLeaderPax;
  }

  public double getAllAlAmount() {
    return allAlAmount;
  }

}
