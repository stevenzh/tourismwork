package com.opentravelsoft.action.manage.finance.income;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.entity.finance.Income;
import com.opentravelsoft.service.finance.IncomeService;
import com.opentravelsoft.service.order.BookingService;
import com.opentravelsoft.service.setting.EmployeeService;
import com.opentravelsoft.util.ConvertUtils;

/**
 * 修改收款销帐单
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.2 $ $Date: 2009/04/10 07:47:29 $
 */
public class ListIncomeAction extends ManageAction {
  private static final long serialVersionUID = -4166608849868194948L;

  @Autowired
  private IncomeService incomeService;

  @Autowired
  private EmployeeService salesmanService;

  @Autowired
  private BookingService bookingService;

  private List<Team> teamList;

  private List<Employee> salesManList;

  /** 客户(客人提供商) */
  private List<LabelValueBean> agentList = new ArrayList<LabelValueBean>();

  /** 地区 */
  private List<LabelValueBean> regions = new ArrayList<LabelValueBean>();

  private List<Income> gathList;

  private List<Booking> bookList;

  private Income gathering = new Income();

  private int incomeId;

  // -------------------------------------------------------------------------
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

  // -------------------------------------------------------------------------
  // 查询条件

  /** 部门ＩＤ */
  private long teamId;

  /** 地区ＩＤ */
  private String regionId;

  private String companyId;

  private Date kenStartDate;

  private Date kenEndDate;

  private double kenStartMon;

  private double kenEndMon;

  // -------------------------------------------------------------------------

  public String input() {
    teamList = incomeService.getOperatorTeamList();
    if (teamList.size() > 0)
      teamId = teamList.get(0).getTeamId();
    regions = bookingService.getStateByCountry("CN");
    agentList = bookingService.roGetAgentByArea(regionId, "");
    return INPUT;
  }

  public String search() {
    teamList = incomeService.getOperatorTeamList();
    regions = bookingService.getStateByCountry("CN");
    agentList = bookingService.roGetAgentByArea(regionId, "");
    gathList = incomeService.roShowIncomeHis(teamId, companyId, kenStartDate,
        kenEndDate, kenStartMon, kenEndMon);
    List<LabelValueBean> payModeList = getCodeList("ebiz_pay_mode");
    Map<String, String> modeMap = ConvertUtils.beansToMap(payModeList);

    for (Income gathering : gathList) {
      gathering.setIncomeModeShow(modeMap.get((gathering.getPayMode())
          .toString()));
    }

    return SUCCESS;
  }

  public String delete() {
    incomeService.txDeleteIncome(gathering.getIncomeId());
    teamList = incomeService.getOperatorTeamList();
    salesManList = salesmanService.roGetSalesList();
    regions = bookingService.getStateByCountry("CN");
    agentList = bookingService.roGetAgentByArea(regionId, "");

    return SUCCESS;
  }

  public long getDepartmentNo() {
    return teamId;
  }

  public void setDepartmentNo(long teamId) {
    this.teamId = teamId;
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

  public List<Income> getGathList() {
    return gathList;
  }

  public void setGathList(List<Income> gathList) {
    this.gathList = gathList;
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

  public String getCompanyId() {
    return companyId;
  }

  public void setCompanyId(String companyId) {
    this.companyId = companyId;
  }

  public List<Employee> getSalesManList() {
    return salesManList;
  }

  public List<Booking> getBookList() {
    return bookList;
  }

  public void setBookList(List<Booking> bookList) {
    this.bookList = bookList;
  }

  public int getTotalPax() {
    return totalPax;
  }

  public double getTotalExpense() {
    return totalExpense;
  }

  public double getTotalPayCosts() {
    return totalPayCosts;
  }

  public double getTotalUnPay() {
    return totalUnPay;
  }

  public List<LabelValueBean> getAgentList() {
    return agentList;
  }

  public void setAgentList(List<LabelValueBean> agentList) {
    this.agentList = agentList;
  }

  public List<LabelValueBean> getRegions() {
    return regions;
  }

  public void setRegions(List<LabelValueBean> regions) {
    this.regions = regions;
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
}
