package com.opentravelsoft.providers;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.opentravelsoft.EbizException;
import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.Tourist;

public interface BookingDao extends GenericDao<Booking, String> {
  /**
   * 预订旅游产品<br>
   * 内部和门市预订
   * 
   * @param book
   * @param customers
   * @param isHold
   * @return 0:成功
   */
  public int saveBooking(Booking book, List<Tourist> customers, boolean isHold);

  /**
   * 
   * @param book
   * @param customerList
   * @param userId
   * @return
   */
  public String addBooking(Booking book, List<Tourist> customerList);

  /**
   * 取得订单
   * 
   * @param bookingNo
   * @return
   */
  public Booking getBooking(String bookingNo);

  /**
   * 取消订单
   * 
   * @param book
   * @return
   */
  public int cancelBook(Booking book, String note);

  /**
   * 取消订单
   * 
   * @param book
   * @return
   */
  public int cancelCustomers(Booking book, Set<String> list, String note);

  /**
   * 
   * @param book
   * @param customerList
   * @return
   * @throws EbizException
   */
  public int updateBooking(Booking book, List<Tourist> customerList, String note)
      throws EbizException;

  /**
   * 阅读订单后更新标记
   * 
   * @param booking
   * @return
   */
  public int readBooking(Booking booking);

  /**
   * 确认订单后占位
   * 
   * @param book
   * @return
   */
  public int confirm(Booking book);

  /**
   * 拆分订单
   * 
   * @param book
   * @param set
   * @param reserveNo
   * @param note
   * @return
   */
  public int splitBooking(Booking book, Set<String> set, String reserveNo,
      String note);

  /**
   * 
   * @param book
   * @param customers
   * @return
   */
  public int resumeCustomers(Booking book, Set<String> customers, String note);

  /**
   * 取得未确认的订单
   * 
   * @param groupCd
   * @param uid
   * @return
   */
  public List<Booking> findUndetermined(int teamId, int userId);

  /**
   * 取得会员的订单
   * 
   * @param memberId
   * @return
   */
  public List<Booking> getBookings(long memberId);

  /**
   * 取得已确认的订单（分销）
   * 
   * @param accountId
   * @return
   */
  public List<Booking> getConfirmBookings(int accountId);

  /**
   * 取得未确认的订单（分销）
   * 
   * @param accountId
   * @return
   */
  public List<Booking> getUnconfirmBookings(int accountId);

  public List<Booking> find(String lineName, Date startDatePeriod,
      Date endDatePeriod, Date orderStartDatePeriod,
      Date orderStartDatePeriod2, String contractNo, String invoiceNo,
      String touristName, String bookState, String cancelFlag);

  public List<Booking> find(String lineName, int teamId, int userId,
      Date startDatePeriod, Date endDatePeriod, Date reserveStart,
      Date reserveEnd, String tourist, String agentId, String salesman,
      String cfmKey, String readKey, String delKey, String reserveNo);

  /**
   * 取得未阅读的订单
   * 
   * @param uid
   * @return
   */
  public List<Booking> getUnreadBookings(int uid);

  /**
   * 取得出团计划的所有订单
   * 
   * @param planNo
   * @return
   */
  public List<Booking> getPlanBookings(String planNo);

  /**
   * 按照客户地区统计
   * 
   * @param provinceId 省份
   * @param kenSales 销售员
   * @param startDate 开始日期
   * @param endDate 结束如期
   * @param payment 结算方式
   * @param kenCity 城市
   * @return
   */
  public List<Booking> findCustomerDbamt(String provinceId, String kenSales,
      Date startDate, Date endDate, String payment, String kenCity);

  /**
   * 
   * @param customerId
   * @param kenSales
   * @param startDate
   * @param endDate
   * @return
   */
  public List<Booking> getBooksByCustomer(int customerId, String kenSales,
      Date startDate, Date endDate);

  /**
   * 
   * @param startDate
   * @param endDate
   * @param country
   * @return
   */
  public List<Booking> districtStat(Date startDate, Date endDate, String country);

  /**
   * 
   * @param lineNo
   * @param startDate
   * @param endDate
   * @return
   */
  public List<Booking> getBookByLineNo(String lineNo, Date startDate,
      Date endDate);

  /**
   * 
   * @param bookingNo
   * @param tourist
   * @param account
   * @return
   */
  public List<Booking> findBookings(String bookingNo, String tourist,
      double account);

}
