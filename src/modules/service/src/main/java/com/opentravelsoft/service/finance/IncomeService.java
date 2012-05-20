package com.opentravelsoft.service.finance;

import java.util.Date;
import java.util.List;

import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.Customer;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.entity.finance.Income;
import com.opentravelsoft.entity.product.Warrant;

public interface IncomeService {
  /**
   * 未付清的订单
   * 
   * @param customerId
   * @return
   */
  public List<Booking> roGetIncomeBookings(int customerId);

  /**
   * 未付清款订单
   * 
   * @param proCd
   * @param cityCd
   * @param customerId
   * @param stDate
   * @param endDate
   * @return
   */
  public List<Booking> roSearchIncome(String proCd, String cityCd,
      Integer customerId, Date stDate, Date endDate);

  /**
   * 保存收款账单
   * 
   * @param gathering
   * @return income ID
   */
  public long txSaveIncome(Income gathering);

  /**
   * 查询收款账单
   * 
   * @param deptNo 产品所属部门
   * @param customerId 客户ID
   * @param startDate 收款日期开始
   * @param endDate 收款日期截止
   * @param startMon 付款高于金额
   * @param endMon 付款低于金额
   * @return
   */
  public List<Income> roShowIncomeHis(Integer teamId, String customerId,
      Date startDate, Date endDate, double startMon, double endMon);

  /**
   * 取消收款账单
   * 
   * @param incomeId
   * @return
   */
  public int txDeleteIncome(int incomeId);

  /**
   * 取得收款账单的详细
   * 
   * @param incomeId
   * @return
   */
  public Income roGetIncome(int incomeId);

  /**
   * 更新收款账单
   * 
   * @param gather
   * @return
   */
  public int txUpdateIncome(Income gather);

  /**
   * 取得收款对应订单信息
   * 
   * @param customerId
   * @param incomeId
   * @return
   */
  public List<Booking> roGetUnpayList(int incomeId);

  /**
   * 
   * @param TourNo
   * @return
   */
  public List<Customer> roGetCusByTour(String TourNo);

  /**
   * 
   * @param customerId
   * @param tourNo
   * @param warrant
   * @return
   */
  public int txWarrantSubmit(int customerId, String tourNo, Warrant warrant);

  public List<Team> getOperatorTeamList();
}
