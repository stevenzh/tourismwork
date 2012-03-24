package com.opentravelsoft.action.manage.operator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Customer;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.entity.finance.Outcome;
import com.opentravelsoft.service.operator.TicketService;
import com.opentravelsoft.service.operator.TourService;
import com.opentravelsoft.service.setting.EmployeeService;

/**
 * 参数设置:航班维护
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.2 $ $Date: 2009/04/10 07:47:31 $
 */
public class ListParcelAction extends ManageAction {
  private static final long serialVersionUID = 4025088482007685362L;

  protected static final Log logger = LogFactory.getLog(ListParcelAction.class);

  private TicketService ticketService;
  private EmployeeService employeeService;
  private TourService tourService;

  @Autowired
  public void setTicketService(TicketService ticketService) {
    this.ticketService = ticketService;
  }

  @Autowired
  public void setTourService(TourService tourSearchService) {
    this.tourService = tourSearchService;
  }

  private List<Outcome> parcels = null;

  /** 部门 */
  private long kenTeamId = 0;

  /** 操作员 */
  private int kenEmployeeId = 0;

  /**
   * 配送状态 0:申请 1: 开始 2: 暂停 3: 完成 4: 取消
   */
  private short kenCarryStatus = 0;

  /** 供应商ID */
  private String kenSupplierId = "";

  private List<Employee> employees = new ArrayList<Employee>();

  private List<Team> teamList = new ArrayList<Team>();

  private List<Customer> supplierList = new ArrayList<Customer>();

  @Autowired
  public void setEmployeeService(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  public String input() {
    teamList = tourService.getOperatorTeamList();
    if (teamList.size() > 0)
      kenTeamId = teamList.get(0).getTeamId();
    employees = employeeService.getUserByTeam(kenTeamId);
    supplierList = ticketService.getAirSuppliers();

    parcels = ticketService.listParcels(kenSupplierId, kenEmployeeId,
        kenCarryStatus);
    return INPUT;
  }

  public String execute() {
    teamList = tourService.getOperatorTeamList();
    if (teamList.size() > 0)
      kenTeamId = teamList.get(0).getTeamId();
    employees = employeeService.getUserByTeam(kenTeamId);
    supplierList = ticketService.getAirSuppliers();

    parcels = ticketService.listParcels(kenSupplierId, kenEmployeeId,
        kenCarryStatus);
    return SUCCESS;
  }

  public List<Outcome> getParcels() {
    return parcels;
  }

  public long getKenDepartmentId() {
    return kenTeamId;
  }

  public void setKenDepartmentId(long teamId) {
    this.kenTeamId = teamId;
  }

  public int getKenEmployeeId() {
    return kenEmployeeId;
  }

  public void setKenEmployeeId(int kenEmployeeId) {
    this.kenEmployeeId = kenEmployeeId;
  }

  public List<Employee> getEmployees() {
    return employees;
  }

  public List<Team> getTeamList() {
    return teamList;
  }

  public List<Customer> getSupplierList() {
    return supplierList;
  }

  public short getKenCarryStatus() {
    return kenCarryStatus;
  }

  public void setKenCarryStatus(short kenCarryStatus) {
    this.kenCarryStatus = kenCarryStatus;
  }

  public String getKenSupplierId() {
    return kenSupplierId;
  }

  public void setKenSupplierId(String kenSupplierId) {
    this.kenSupplierId = kenSupplierId;
  }

}
