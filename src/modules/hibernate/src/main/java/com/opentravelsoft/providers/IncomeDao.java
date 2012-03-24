package com.opentravelsoft.providers;

import java.util.Date;
import java.util.List;

import com.opentravelsoft.util.LabelValueBean;

import com.opentravelsoft.EbizException;
import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.Customer;
import com.opentravelsoft.entity.TblUserPayOL;
import com.opentravelsoft.entity.finance.Income;
import com.opentravelsoft.entity.product.Remind;
import com.opentravelsoft.entity.product.Warrant;

public interface IncomeDao extends GenericDao<Income, Long> {
  /**
   * 取得客户应收帐款
   * 
   * @param customerId
   * @return
   */
  public List<Booking> getIncomeBookings(long customerId);

  /**
   * 未付清款的订单
   * 
   * @param proCd
   * @param cityId
   * @param stDate
   * @param endDate
   * @return
   */
  public List<Booking> searchIncome(String proCd, String cityId,
      long customerId, Date stDate, Date endDate);

  /**
   * 保存收款单
   * 
   * @param gathering
   * @return
   */
  public long saveIncome(Income gathering);

  /**
   * 查询收款账单
   * 
   * @param teamId 产品所属部门
   * @param customerId 客户ID
   * @param startDate 收款日期开始
   * @param endDate 收款日期截止
   * @param startMon
   * @param endMon
   * @return
   */
  public List<Income> findIncome(long teamId, String customerId,
      Date startDate, Date endDate, double startMon, double endMon);

  /**
   * 查询客户付款历史<br>
   * [tourismwork-search]
   * 
   * @param customerId
   * @param paymentDateStart
   * @param paymentDateEnd
   * @param payGatherStart
   * @param payGatherEnd
   * @return
   */
  public List<Income> getGatheringList(int customerId, Date paymentDateStart,
      Date paymentDateEnd, double payGatherStart, double payGatherEnd);

  /**
   * 取消收款单
   * 
   * @param incomeId
   * @return
   */
  public int cancelIncome(long incomeId);

  /**
   * 取得收款信息<br>
   * [tourismwork-search]
   * 
   * @param incomeId
   * @return
   */
  public Income getGathering(long incomeId);

  /**
   * 更新收款信息
   * 
   * @param gather
   * @return
   */
  public int updateIncome(Income gather);

  /**
   * 取得收款账对应订单信息
   * 
   * @param incomeId
   * @return
   */
  public List<Booking> getUnpayList(int incomeId);

  // -------------------------------------------------------------------------

  /**
   * 国内应收 团出发前n天 未收款客户提醒
   * 
   * @param daynum
   * @return
   */
  public List<Remind> getIncomeInBand(int daynum);

  /**
   * 查询该团所有供应商
   * 
   * @param TourNo
   * @return
   */
  public List<Customer> getCusByTour(String TourNo);

  /**
   * 设置订单担保
   * 
   * @param customerId 客户ID
   * @param tourNo 团号NO
   * @param warrant 担保单
   * @return
   */
  public int warrantSubmit(long customerId, String tourNo, Warrant warrant);

  /**
   * 取得订单的客户付款记录
   * 
   * @param bookingNo 订单No
   * @return
   */
  public List<Income> getPayments(String bookingNo);

  /**
   * 付款方式
   * 
   * @return
   */
  public List<LabelValueBean> getPaymentTypes();

  /**
   * 网上支付
   * 
   * @param orderId 订单号
   * @param paymentMode 付款方式
   * @param amount 金额
   * @param moneyType 币种
   * @param paymentNo 付款单号
   * @param inverceNo 发票号
   * @return
   * @throws EbizException
   */
  public int netPay(String orderId, String paymentMode, double amount,
      String moneyType, String paymentNo, String inverceNo)
      throws EbizException;

  int insertPayer(TblUserPayOL payer);
}
