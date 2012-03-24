package com.opentravelsoft.action.manage.operator;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.common.SessionKeyParams;
import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.Customer;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.TourCost;
import com.opentravelsoft.service.account.CustomerService;
import com.opentravelsoft.service.operator.TourService;
import com.opentravelsoft.util.Arith;
import com.opentravelsoft.workflow.TaskDao;

/**
 * 计调单团核算
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:00 $
 */

public class OpTourBalanceAction extends ManageAction {
  private static final long serialVersionUID = 6943355664892206072L;

  private TourService tourService;

  private CustomerService customerService;
  
  private TaskDao taskService;

  protected SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

  // 保留两位小数点
  protected DecimalFormat df = new DecimalFormat("#.00");

  private Plan tour = new Plan();

  private List<TourCost> costList = new ArrayList<TourCost>();

  /** 供应商 */
  private List<Customer> supplierList = new ArrayList<Customer>();

  /** 成本类型 */
  private List<LabelValueBean> costTypeList = new ArrayList<LabelValueBean>();

  private int id;

  private int accountId;

  // 是否提交到财务
  private String status = "N";

  // 是否授权修改
  private String opReAction = "N";

  private String tourNo;

  private String planNo;

  private double amountAll;

  private List<Booking> bookList = new ArrayList<Booking>();

  /** 币种 */
  private List<LabelValueBean> currencyList = new ArrayList<LabelValueBean>();


  @Autowired
  public void setTourService(TourService tourService) {
    this.tourService = tourService;
  }

  @Autowired
  public void setCustomerService(CustomerService contactService) {
    this.customerService = contactService;
  }

  @Autowired
  public void setTaskService(TaskDao taskService) {
    this.taskService = taskService;
  }
  
  /**
   * 单团核算表修改初始化
   * 
   * @return
   */
  public String input() {
    Object obj = ActionContext.getContext().getSession()
        .get(SessionKeyParams.EBIZ_CURRENT_TOUR);
    if (obj != null) {
      tourNo = ((Plan) obj).getTourNo();
    }

    tour = tourService.roGetTourInfo(tourNo, false, true);
    if (null != tour) {
      costTypeList = getCodeList("ebiz_cost_type");
      supplierList = customerService.getUsableSupplier(tour.getTeam()
          .getTeamId());
      currencyList = tourService.roGetCurrencyList();
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
      tour.setLeaderName(str);

      if (null != tour) {
        tour.setMuAmount(amount);
        tour.setAlAmount(payCosts);
        tour.setWiAmount(amount - payCosts);
        double grossAmount = tour.getTourAmount()
            - tour.getCost().doubleValue();
        tour.setGrossAmount(new Double(df.format(grossAmount)));
        if (tour.getTourAmount() != 0) {
          double grossAmountRate = tour.getGrossAmount() / tour.getTourAmount()
              * 100;
          tour.setGrossAmountRate(new Double(df.format(grossAmountRate)));
        }
        costList = tour.getCostList();
      }

      if (costList.isEmpty()) {
        TourCost singleTourCostAcct = new TourCost();
        singleTourCostAcct.setId(1);
        singleTourCostAcct.setUnit("元");
        singleTourCostAcct.setFrChecked("N");
        costList.add(singleTourCostAcct);
      }

      // 取明细
      detail();

      // 是否提交财务
      if (tour.getOpRefactor().equals("Y")) {
        status = "Y";
        if (tour.getOpAccount().equals("Y")) {
          opReAction = "Y";
        } else {
          addActionMessage("此团的核算表已提交到财务，只有得到授权后才能修改！");
          return "detail";
        }
      }

    } else {
      status = "U";
      addActionError("团数据读取错误！");
    }

    return INPUT;
  }

  /**
   * 单团核算表修改
   * 
   * @return
   */
  public String modify() {
    Employee user = getUser();
    currencyList = tourService.roGetCurrencyList();
    supplierList = customerService
        .getUsableSupplier(tour.getTeam().getTeamId());
    costTypeList = getCodeList("ebiz_cost_type");

    for (TourCost singleTourCostAcct : costList) {
      for (Customer supplier : supplierList) {
        if (singleTourCostAcct.getCustomer().getCustomerId() == supplier
            .getSupplierId()) {
          singleTourCostAcct.getCustomer().setName(supplier.getSupplierName());
          break;
        }
      }
      if (singleTourCostAcct.getCostType().equals("")) {
        addActionMessage("第" + singleTourCostAcct.getId() + "行，费用类型必须填写！");
        return INPUT;
      }
    }

    tour.setUpdatedby(user.getUserId());
    tour.setCostList(costList);
    int result = tourService.txSingleTourBalanceMake(tour);

    if (0 == result) {
      addActionError("保存成功！");
      return SUCCESS;
    } else {
      addActionError("保存失败！");
      return INPUT;
    }
  }

  /**
   * 提交到财务初始化
   * 
   * @return
   */
  public String submitInput() {
    Object obj = ActionContext.getContext().getSession()
        .get(SessionKeyParams.EBIZ_CURRENT_TOUR);
    if (obj != null) {
      tourNo = ((Plan) obj).getTourNo();
    }

    tour = tourService.roGetTourInfo(tourNo, false, false);
    if (null != tour) {
      if (!tour.getOpAccount().equals("Y")) {
        status = "N";
      } else {
        addActionError("此团的核算表已提交到财务!");
        status = "Y";
      }
      // 取明细
      detail();
    } else {
      status = "U";
      addActionError("核算表还未输入！");
    }

    return SUCCESS;
  }

  /**
   * 提交到财务
   * 
   * @return
   */
  public String submit() {
    Employee user = getUser();
    int ret = taskService.checkTourAccounts(tourNo, user.getUid());
    if (ret == 0) {
      addActionError("核算表已成功提交到财务！");
      status = "Y";
    } else
      addActionError("核算表提交失败！");

    Object obj = ActionContext.getContext().getSession()
        .get(SessionKeyParams.EBIZ_CURRENT_TOUR);
    if (obj != null) {
      tourNo = ((Plan) obj).getTourNo();
    }

    tour = tourService.roGetTourInfo(tourNo, false, false);
    // 取明细
    detail();

    return SUCCESS;
  }

  /**
   * 单团核算详细
   * 
   * @param tourNo
   */
  public void detail() {
    Employee user = getUser();

    // 根据供应商和费用类型代码得到其名字---------------------------------------
    // supplierList =
    // contactService.getUsableSupplier(user.getDepartmentId());
    supplierList = customerService.getUsableSupplier(0);
    currencyList = tourService.roGetCurrencyList();
    costTypeList = getCodeList("ebiz_cost_type");
    for (TourCost singleTourCostAcct : tour.getCostList()) {
      for (Customer supplier : supplierList) {
        if (singleTourCostAcct.getCustomer().getCustomerId() == supplier
            .getSupplierId()) {
          singleTourCostAcct.getCustomer().setName(supplier.getSupplierName());
          break;
        }
      }
    }

    // ------------------------------------------------------------------

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
    tour.setLeaderName(str);
    tour.setTotalPax(pax);

    if (null != tour) {
      tour.setMuAmount(amount);
      tour.setAlAmount(payCosts);
      tour.setWiAmount(amount - payCosts);
      tour.setOprateUserName(user.getUserName());

      double grossAmount = Arith.sub(tour.getTourAmount(), tour.getCost());
      // 四舍五入取小数点后两位
      tour.setGrossAmount(Arith.round(grossAmount, 2));
      if (tour.getTourAmount() != 0) {
        double grossAmountRate = Arith.div(tour.getGrossAmount(),
            tour.getTourAmount());
        grossAmountRate = Arith.mul(grossAmountRate, 100);
        tour.setGrossAmountRate(Arith.round(grossAmountRate, 2));
      }

      costList = tour.getCostList();

    }
    if (tour.getPax() != 0) {
      double grossAmountAverage = Arith.div(tour.getGrossAmount(),
          tour.getPax());
      tour.setGrossAmountAverage(Arith.round(grossAmountAverage, 2));
    }

    if (costList.isEmpty()) {
      costList.add(new TourCost());
    }

  }

  /**
   * 授权修改
   * 
   * @return
   */
  public String authorization() {
    Employee user = getUser();
    int ret = tourService.txAuthorizationModify(accountId, user.getUserId());
    if (ret == -1) {
      addActionMessage("授权失败！");
      return INPUT;
    } else
      addActionMessage("授权成功！");

    return SUCCESS;
  }

  /**
   * 增加行
   * 
   * @return
   */
  public String add() {
    currencyList = tourService.roGetCurrencyList();
    supplierList = customerService
        .getUsableSupplier(tour.getTeam().getTeamId());
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
    currencyList = tourService.roGetCurrencyList();
    supplierList = customerService
        .getUsableSupplier(tour.getTeam().getTeamId());
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
        amount = tour.getCost() - obj.getAmount();
        tour.setCost(new Double(df.format(amount)));
        costList.remove(obj);
        break;
      }
    }
    if (costList.isEmpty()) {
      TourCost singleTourCostAcct1 = new TourCost();
      singleTourCostAcct1.setUnit("元");
      costList.add(singleTourCostAcct1);
    }

    return SUCCESS;
  }

  public String getTourNo() {
    return tourNo;
  }

  public void setTourNo(String tourNo) {
    this.tourNo = tourNo;
  }

  public Plan getSingleTourBalance() {
    return tour;
  }

  public void setSingleTourBalance(Plan singleTourBalance) {
    this.tour = singleTourBalance;
  }

  public List<TourCost> getCostList() {
    return costList;
  }

  public void setCostList(List<TourCost> costList) {
    this.costList = costList;
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

  public int getAccountId() {
    return accountId;
  }

  public void setAccountId(int accountId) {
    this.accountId = accountId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
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

  public String getOpReAction() {
    return opReAction;
  }

  public void setOpReAction(String opReAction) {
    this.opReAction = opReAction;
  }

  public double getAmountAll() {
    return amountAll;
  }

  public void setAmountAll(double amountAll) {
    this.amountAll = amountAll;
  }

  public List<LabelValueBean> getCurrencyList() {
    return currencyList;
  }

  public String getPlanNo() {
    return planNo;
  }

  public void setPlanNo(String planNo) {
    this.planNo = planNo;
  }

}
