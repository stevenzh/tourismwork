package com.opentravelsoft.action.manage.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.service.order.BookingService;
import com.opentravelsoft.service.setting.EmployeeService;
import com.opentravelsoft.util.LabelValueBean;

/**
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.2 $ $Date: 2009/04/10 07:47:32 $
 */
public class BookingSearchAction extends ManageAction {
  private static final long serialVersionUID = -7625855842902512964L;

  @Autowired
  private BookingService bookingService;

  @Autowired
  private EmployeeService employeeService;

  private List<Employee> employeeList;

  private List<Team> teamList;

  /** 是否占位列表[1:已占位、2:未占位、%:所有] */
  private List<LabelValueBean> cfmList;

  /** 列表[Y:已读订单、N:未读订单] */
  private List<LabelValueBean> readList;

  /** 列表[Y:已取消、N:未取消] */
  private List<LabelValueBean> delList;

  // -------------------------------------------------------------------------
  // 检索条件

  /** 订单号 */
  private String kenReserveNo;

  /** 专管员 */
  private int kenUserId;

  /** 部门 */
  private int kenTeamId;

  /** 线路名 */
  private String kenLineName;

  /** 出团日期 -开始 */
  private Date kenStartDatePeriod;

  /** 出团日期 -截止 */
  private Date kenEndDatePeriod;

  /** 预订日期 起始时间 */
  private Date kenReserveStart;

  /** 预订日期 截止时间 */
  private Date kenReserveEnd;

  /** 客户名 */
  private String kenAgent;

  /** 客人姓名 */
  private String kenTourist;

  /** 销售员 */
  private String kenSalesman;

  /** 是否占位 */
  private String kenCfm = "%";

  /** 是否已读 */
  private String kenRead;

  /** 是否取消 */
  private String kenDel;

  // -------------------------------------------------------------------------
  /** 合计人数 */
  private int totalPax = 0;

  /** 合计确认人数 */
  private int totalConfirmPax = 0;

  /** 应收款 */
  private BigDecimal totalExpense = new BigDecimal(0);

  /** 已付费用 */
  private BigDecimal totalPay = new BigDecimal(0);

  /** 未付费用 */
  private BigDecimal totalUnPay = new BigDecimal(0);

  // -------------------------------------------------------------------------

  private String[] nameNo;

  private List<Booking> bookings = new ArrayList<Booking>();

  public String input() throws Exception {
    Employee user = getUser();
    teamList = bookingService.getOperatorTeamList();

    if (teamList.size() > 0)
      kenTeamId = teamList.get(0).getTeamId();
    kenUserId = user.getUserId();
    employeeList = employeeService.getUserByTeam(kenTeamId);

    cfmList = getCodeList("ebiz_book_state");
    readList = getCodeList("ebiz_yes_no");
    delList = getCodeList("ebiz_yes_no");

    return SUCCESS;
  }

  public String submit() throws Exception {
    bookings = bookingService.roFind(kenLineName, kenTeamId, kenUserId,
        kenStartDatePeriod, kenEndDatePeriod, kenReserveStart, kenReserveEnd,
        kenTourist, kenAgent, kenSalesman, kenCfm, kenRead, kenDel,
        kenReserveNo);

    if (null != bookings)
      currentPage(bookings.size());
    for (int i = 0; i < bookings.size(); i++) {
      Booking book = bookings.get(i);
      totalPax = totalPax + book.getPax();
      totalConfirmPax = totalConfirmPax + book.getConfirmPax();
      totalExpense = totalExpense.add(book.getDbamt());
      totalPay = totalPay.add(book.getPayCosts());
      totalUnPay = totalExpense.subtract(totalPay);
    }

    employeeList = employeeService.getUserByTeam(kenTeamId);
    teamList = bookingService.getOperatorTeamList();

    cfmList = getCodeList("ebiz_book_state");
    readList = getCodeList("ebiz_yes_no");
    delList = getCodeList("ebiz_yes_no");

    return SUCCESS;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.opentravelsoft.BaseAction#getMoveCount()
   */
  protected int getMoveCount() {
    // 返回记录行数/页
    return 20;
  }

  public String getKenReserveNo() {
    return kenReserveNo;
  }

  public void setKenReserveNo(String kenReserveNo) {
    this.kenReserveNo = kenReserveNo;
  }

  public Date getKenEndDatePeriod() {
    return kenEndDatePeriod;
  }

  public void setKenEndDatePeriod(Date kenEndDatePeriod) {
    this.kenEndDatePeriod = kenEndDatePeriod;
  }

  public String getKenRouteName() {
    return kenLineName;
  }

  public void setKenRouteName(String kenRouteName) {
    this.kenLineName = kenRouteName;
  }

  public Date getKenStartDatePeriod() {
    return kenStartDatePeriod;
  }

  public void setKenStartDatePeriod(Date kenStartDatePeriod) {
    this.kenStartDatePeriod = kenStartDatePeriod;
  }

  public List<Team> getTeamList() {
    return teamList;
  }

  public List<Employee> getEmployeeList() {
    return employeeList;
  }

  public int getKenDepartmentNo() {
    return kenTeamId;
  }

  public void setKenDepartmentNo(int teamId) {
    this.kenTeamId = teamId;
  }

  public int getKenUserId() {
    return kenUserId;
  }

  public void setKenUserId(int kenUserId) {
    this.kenUserId = kenUserId;
  }

  public String getKenAgent() {
    return kenAgent;
  }

  public void setKenAgent(String kenAgent) {
    this.kenAgent = kenAgent;
  }

  public String getKenSalesman() {
    return kenSalesman;
  }

  public void setKenSalesman(String kenSalesman) {
    this.kenSalesman = kenSalesman;
  }

  public Date getKenReserveStart() {
    return kenReserveStart;
  }

  public void setKenReserveStart(Date kenReserveStart) {
    this.kenReserveStart = kenReserveStart;
  }

  public Date getKenReserveEnd() {
    return kenReserveEnd;
  }

  public void setKenReserveEnd(Date kenReserveEnd) {
    this.kenReserveEnd = kenReserveEnd;
  }

  public String getKenTourist() {
    return kenTourist;
  }

  public void setKenTourist(String kenTourist) {
    this.kenTourist = kenTourist;
  }

  public List<Booking> getBookings() {
    return bookings;
  }

  public int getTotalPax() {
    return totalPax;
  }

  public int getTotalConfirmPax() {
    return totalConfirmPax;
  }

  public BigDecimal getTotalExpense() {
    return totalExpense;
  }

  public BigDecimal getTotalPay() {
    return totalPay;
  }

  public String[] getNameNo() {
    return nameNo;
  }

  public void setNameNo(String[] nameNo) {
    this.nameNo = nameNo;
  }

  public BigDecimal getTotalUnPay() {
    return totalUnPay;
  }

  public void setTotalUnPay(BigDecimal totalUnPay) {
    this.totalUnPay = totalUnPay;
  }

  public String getKenCfm() {
    return kenCfm;
  }

  public void setKenCfm(String kenCfm) {
    this.kenCfm = kenCfm;
  }

  public String getKenRead() {
    return kenRead;
  }

  public void setKenRead(String kenRead) {
    this.kenRead = kenRead;
  }

  public String getKenDel() {
    return kenDel;
  }

  public void setKenDel(String kenDel) {
    this.kenDel = kenDel;
  }

  public List<LabelValueBean> getCfmList() {
    return cfmList;
  }

  public List<LabelValueBean> getReadList() {
    return readList;
  }

  public List<LabelValueBean> getDelList() {
    return delList;
  }

}
