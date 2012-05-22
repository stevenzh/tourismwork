package com.opentravelsoft.service.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import com.opentravelsoft.util.LabelValueBean;

import com.opentravelsoft.EbizException;
import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.Country;
import com.opentravelsoft.entity.Express;
import com.opentravelsoft.entity.LinePrice;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.entity.Tourist;
import com.opentravelsoft.entity.finance.Income;

public interface BookingService {

  List<Team> getOperatorTeamList();

  /**
   * 
   * @param routeName
   * @param teamId
   * @param userId
   * @param startDatePeriod
   * @param endDatePeriod
   * @param reserveStart
   * @param reserveEnd
   * @param tourist
   * @param agentId
   * @param salesman
   * @param cfmKey
   * @param readKey
   * @param delKey
   * @param reserveNo
   * @return
   */
  List<Booking> roFind(String routeName, Integer teamId, Integer userId,
      Date startDatePeriod, Date endDatePeriod, Date reserveStart,
      Date reserveEnd, String tourist, String agentId, String salesman,
      String cfmKey, String readKey, String delKey, String reserveNo);

  public Plan roGetPlanDetail(String recordNo);

  /**
   * 取得区域代理商列表
   * 
   * @param userId 销售员ID
   * @param area 区域ID
   * @return
   */
  public List<LabelValueBean> getAgentBySales(int userId, String area);

  /**
   * 取得客户列表
   * 
   * @param area 按地区过滤
   * @param payment 按照付款方式过滤
   * @return
   */
  public List<LabelValueBean> roGetAgentByArea(String area, String payment);

  /**
   * 增加预订
   * 
   * @param book 订单
   * @param customers 游客信息
   * @param userId 操作员
   * @param ishold 是否占位
   * @return 订单号 如果为null则增加失败，不为null则增加成功
   */
  public Hashtable<String, String> txAddBook(Booking book,
      List<Tourist> customers, int userId, boolean ishold);

  public List<LabelValueBean> roGetBirthplaceList();

  public List<LabelValueBean> roGetPassportPlaceList();

  public List<LabelValueBean> roGetRoomTypeList();

  /**
   * 取得订单详细(包含付款记录)
   * 
   * @param bookingNo 订单号
   * @return
   */
  public Booking roGetReserve(String bookingNo);

  List<LabelValueBean> getStateByCountry(String country);

  public String roGetBirthplaces(String place);

  List<Tourist> roFindCustomer(String[] nmno);

  String txSplitBooking(Booking book, Set<String> set, String note);

  public int txResumeCustomers(Booking book, Set<String> customers, String note);

  /**
   * 按照客户地区统计
   * 
   * @param provinceId 省份
   * @param sales 销售员
   * @param startDate 开始日期
   * @param endDate 结束如期
   * @param payment 结算方式
   * @param kenCity 城市
   * @return
   */
  public List<Booking> roFindCustomerDbamt(String provinceId, String sales,
      Date startDate, Date endDate, String payment, String kenCity);

  /**
   * 
   * @param customerId
   * @param kenSales
   * @param startDate
   * @param endDate
   * @return
   */
  public List<Booking> roGetBooksByCustomer(int customerId, String kenSales,
      Date startDate, Date endDate);

  public List<Booking> roDistrictStat(Date startDate, Date endDate,
      String country);

  public List<Plan> roGetRouteByDis(String districtNo, Date startDate,
      Date endDate);

  public List<Booking> getBookByLineNo(String lineNo, Date startDate,
      Date endDate);

  public List<Country> roGetCountry();

  /**
   * 
   * @param orderNo
   * @param tourist
   * @param account
   * @return
   */
  List<Booking> roSearch(String orderNo, String tourist, double account);

  /**
   * 网上支付
   * 
   * @param orderId 订单号
   * @param paymentMode 付款方式
   * @param amount 金额
   * @param moneyType 币种
   * @return
   */
  int txNetPay(String orderId, String paymentMode, BigDecimal amount,
      String moneyType) throws EbizException;

  /**
   * 付款记录
   * 
   * @param bookingNo
   * @return
   */
  List<Income> roGetPayments(String bookingNo);

  /**
   * 取得订单配送列表
   * 
   * @param bookingNo
   * @return
   */
  List<Express> rogetExpressList(String bookingNo);

  /**
   * 
   * @param book
   * @param customerList
   * @param note
   * @return
   * @throws EbizException
   */
  public int txUpdateBooking(Booking book, List<Tourist> customerList,
      String note) throws EbizException;

  public int txCancelBooking(Booking book, String note);

  public int txCancelCustomers(Booking book, Set<String> set, String note);

  public List<LabelValueBean> roGetPaymentTypes();

  /**
   * 客户交团款[分销系统]
   * 
   * @param payment 付款信息
   * @param userId 操作员
   * @return
   */
  public int txGathering(Income payment, int userId);

  public List<LinePrice> roGetPrices(String planNo);

  List<LabelValueBean> roGetCertifTypes();

  String txAddBooking(Booking book, List<Tourist> customerList);

  /**
   * 
   * @param memberId
   * @return
   */
  List<Booking> roGetBookings(long memberId);

  public List<Booking> roFind(String routeName, Date startDatePeriod,
      Date endDatePeriod, Date orderStartDatePeriod,
      Date orderStartDatePeriod2, String contractNo, String invoiceNo,
      String touristName, String bookState, String cancelFlag);

  List<Booking> roGetNewBooking(int uid);

  int txReadBooking(Booking booking);

  public List<Booking> roGetBooks(int teamId, int userId);

  public Booking roGetReserveBook(String reserveNo);

  /**
   * 审核订单（占位）
   * 
   * @param book
   * @return
   */
  public int txConfirm(Booking book);

  /**
   * 设置为已读
   * 
   * @param booking
   * @return
   */
  public int txRead(Booking booking);

  List<Booking> roGetBookings(int userId);

  /**
   * 已确认订单
   * 
   * @param accountId
   * @return
   */
  public List<Booking> roGetConfirmBookings(int accountId);

  /**
   * 未确认订单
   * 
   * @param accountId
   * @return
   */
  public List<Booking> roGetUnconfirmBookings(int accountId);
}
