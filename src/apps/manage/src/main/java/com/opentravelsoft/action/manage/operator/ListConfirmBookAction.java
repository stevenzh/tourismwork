package com.opentravelsoft.action.manage.operator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.service.order.BookingService;
import com.opentravelsoft.service.setting.EmployeeService;

/**
 * 列出需要审核订单
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.2 $ $Date: 2009/04/10 07:47:29 $
 */
public class ListConfirmBookAction extends ManageAction {
  private static final long serialVersionUID = 1873002169910914015L;

  protected static final Log logger = LogFactory
      .getLog(ListConfirmBookAction.class);

  private EmployeeService employeeService;

  private BookingService bookService;

  private List<Employee> employees;

  private List<Team> teamList;

  private List<Booking> books = new ArrayList<Booking>();

  // -------------------------------------------------------------------------
  private long kenTeamId;

  private long kenUserId;

  /** 出团日期 -开始 */
  private Date kenStartDate;

  /** 出团日期 -截止 */
  private Date kenEndDate;

  private String kenLineName;

  // -------------------------------------------------------------------------
  @Autowired
  public void setEmployeeService(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @Autowired
  public void setBookService(BookingService bookService) {
    this.bookService = bookService;
  }

  public String input() {
    Employee user = getUser();
    logger.debug("取得该审核的订单, 开始...");

    kenUserId = user.getUserId();
    teamList = bookService.getOperatorTeamList();
    if (teamList.size() > 0)
      kenTeamId = teamList.get(0).getTeamId();
    employees = employeeService.getUserByTeam(kenTeamId);

    return INPUT;
  }

  public String execute() {
    Employee user = getUser();
    logger.debug("取得该审核的订单, 开始...");

    teamList = bookService.getOperatorTeamList();
    if (kenTeamId == 0 && teamList.size() > 0)
      kenTeamId = teamList.get(0).getTeamId();

    // if (!auth.contains(EbizCommon.ROLE_SUPERUSER)
    // && !auth.contains(EbizCommon.ROLE_SUPEROPERATOR)
    // && !auth.contains(EbizCommon.ROLE_GROUPOPERATOR))
    // {
    if (kenUserId == 0)
      kenUserId = user.getUserId();
    // }

    employees = employeeService.getUserByTeam(kenTeamId);
    books = bookService.roGetBooks(kenTeamId, kenUserId);
    currentPage(books.size());

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

  public List<Team> getTeamList() {
    return teamList;
  }

  public List<Employee> getEmployees() {
    return employees;
  }

  public List<Booking> getBooks() {
    return books;
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

  public String getKenRouteName() {
    return kenLineName;
  }

  public void setKenRouteName(String kenRouteName) {
    this.kenLineName = kenRouteName;
  }
}
