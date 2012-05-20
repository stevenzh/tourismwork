package com.opentravelsoft.service.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.util.LabelValueBean;
import com.opentravelsoft.EbizException;
import com.opentravelsoft.common.EbizCommon;
import com.opentravelsoft.common.TeamType;
import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.Country;
import com.opentravelsoft.entity.Customer;
import com.opentravelsoft.entity.Express;
import com.opentravelsoft.entity.LinePrice;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.Province;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.entity.Tourist;
import com.opentravelsoft.entity.finance.Income;
import com.opentravelsoft.entity.finance.Invoice;
import com.opentravelsoft.providers.BookingDao;
import com.opentravelsoft.providers.CountryDao;
import com.opentravelsoft.providers.CustomerDao;
import com.opentravelsoft.providers.ExpressDao;
import com.opentravelsoft.providers.IncomeDao;
import com.opentravelsoft.providers.InvoiceDao;
import com.opentravelsoft.providers.ListDao;
import com.opentravelsoft.providers.ProvinceDao;
import com.opentravelsoft.providers.SequenceDao;
import com.opentravelsoft.providers.TeamDao;
import com.opentravelsoft.providers.TouristDao;
import com.opentravelsoft.providers.mixed.PlanListDao;
import com.opentravelsoft.util.RowDataUtil;
import com.opentravelsoft.util.StringUtil;
import com.opentravelsoft.workflow.TaskDao;

@Service("BookingService")
public class BookingServiceImpl implements BookingService {

  @Autowired
  private BookingDao bookingDao;

  @Autowired
  private TeamDao teamDao;

  @Autowired
  private PlanListDao planListDao;

  @Autowired
  private CustomerDao customerDao;

  @Autowired
  private SequenceDao sequenceDao;

  @Autowired
  private InvoiceDao invoiceDao;

  @Autowired
  private IncomeDao paymentDao;

  @Autowired
  private ProvinceDao provinceDao;

  @Autowired
  private CountryDao countryDao;

  @Autowired
  private ExpressDao expressDao;

  @Autowired
  private ListDao listDao;

  @Autowired
  private TouristDao touristDao;

  @Autowired
  private TaskDao taskService;

  public List<Team> getOperatorTeamList() {
    return teamDao.getTeamList(TeamType.Operator);
  }

  public List<Booking> roFind(String lineName, long teamId, long userId,
      Date startDatePeriod, Date endDatePeriod, Date reserveStart,
      Date reserveEnd, String tourist, String agentId, String salesman,
      String cfmKey, String readKey, String delKey, String reserveNo) {
    return bookingDao.find(lineName, teamId, userId, startDatePeriod,
        endDatePeriod, reserveStart, reserveEnd, tourist, agentId, salesman,
        cfmKey, readKey, delKey, reserveNo);
  }

  public Plan roGetPlanDetail(String recordNo) {
    return planListDao.getPlanDetail(recordNo);
  }

  public List<LabelValueBean> roGetBirthplaceList() {
    return listDao.getList("Homeplace");
  }

  public List<LabelValueBean> roGetPassportPlaceList() {
    return listDao.getList("Homeplace");
  }

  public List<LabelValueBean> roGetRoomTypeList() {
    return listDao.getList("RoomType");
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.opentravelsoft.service.order.BookingService#txAddBook(com.
   * opentravelsoft.ebiz.entity.Booking, java.util.List, long, boolean)
   */
  public Hashtable<String, String> txAddBook(Booking book,
      List<Tourist> customers, int userId, boolean isHold) {
    // 检查前款额度
    Hashtable<String, String> ht = new Hashtable<String, String>();
    ht.put("CHECK", "1");

    String computerNo = sequenceDao.getComputerNo("A", userId);
    book.setBookingNo(computerNo);

    String[] computerNos = sequenceDao.getComputerNo("H", customers.size(),
        userId);

    int i = 0;
    for (Tourist tourist : customers) {
      tourist.setNmno(computerNos[i++]);
    }

    // Save Book

    // Save Tourists

    // Update Resource

    if (bookingDao.saveBooking(book, customers, isHold) != 0) {
      return null;
    }

    // 保存成功,添加工作流任务
    ht.put("NO", computerNo);

    String enabled = (String) ActionContext.getContext().getApplication()
        .get(EbizCommon.WORKFLOW_ENABLED);
    if (enabled.equals("1")) {
      // 流程
      taskService.callNewOrder(computerNo);
    }

    return ht;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.opentravelsoft.service.order.BookingService#getAgentBySales(long,
   * java.lang.String)
   */
  public List<LabelValueBean> getAgentBySales(int userId, String area) {
    return customerDao.getCustomerBySales(userId, area);
  }

  public int txUpdateBooking(Booking book, List<Tourist> customerList,
      String note) throws EbizException {
    int count = 0;
    for (Tourist trip : customerList) {
      if (!StringUtil.hasLength(trip.getNmno()))
        count++;
    }

    if (count > 0) {
      // 团队确认状态 1-团确 2-团候
      // book.setConfirmStatus("2");

      String[] computerNos = sequenceDao.getComputerNo("H", count,
          book.getOpuser());
      count = 0;
      for (Tourist tourist : customerList) {
        if (!StringUtil.hasLength(tourist.getNmno())) {
          tourist.setNmno(computerNos[count++]);
          tourist.setNewFlag("Y");
        }
      }
    }
    return bookingDao.updateBooking(book, customerList, note);
  }

  public int txCancelBooking(Booking book, String note) {
    // 更新Booking
    // 更新Tourists
    // 更新资源

    return bookingDao.cancelBook(book, note);
  }

  public int txCancelCustomers(Booking book, Set<String> selects, String note) {
    return bookingDao.cancelCustomers(book, selects, note);
  }

  public List<LinePrice> roGetPrices(String planNo) {
    return planListDao.getLinePrices(planNo);
  }

  public List<LabelValueBean> getStateByCountry(String country) {
    List<Province> list = provinceDao.getStateByCountry(country);
    List<LabelValueBean> duchyList = new ArrayList<LabelValueBean>();
    LabelValueBean duchy = null;
    for (Province obj : list) {
      duchy = new LabelValueBean();
      duchy.setLabel(RowDataUtil.getString(obj.getCode()));
      duchy.setValue(RowDataUtil.getString(obj.getCnName()));
      duchyList.add(duchy);
    }

    return duchyList;

  }

  public List<LabelValueBean> roGetAgentByArea(String areaId, String payment) {
    List<Customer> branchs = customerDao.getCustomerByProvince(areaId, payment);
    List<LabelValueBean> list = new ArrayList<LabelValueBean>();
    for (Customer branch : branchs) {
      list.add(new LabelValueBean(String.valueOf(branch.getCustomerId()),
          branch.getName()));
    }
    return list;
  }

  public String roGetBirthplaces(String place) {
    List<LabelValueBean> birthplace = listDao.getList("Homeplace");
    StringBuilder ret = new StringBuilder();
    for (LabelValueBean bir : birthplace) {
      ret.append(bir.getValue() + "　");
      ret.append(bir.getLabel() + ",");
    }
    return ret.toString();
  }

  public List<Tourist> roFindCustomer(String[] nmno) {
    return touristDao.findByNmno(nmno);
  }

  public String txSplitBooking(Booking book, Set<String> set, String note) {
    String reserveNo = sequenceDao.getComputerNo("A", book.getOpuser());
    // 保存新订单
    // 更新游客
    // 更新新订单
    bookingDao.splitBooking(book, set, reserveNo, note);
    return reserveNo;
  }

  public int txResumeCustomers(Booking book, Set<String> customers, String note) {
    // 恢复游客
    return bookingDao.resumeCustomers(book, customers, note);
  }

  public List<Booking> roFindCustomerDbamt(String provinceId, String sales,
      Date startDate, Date endDate, String payment, String kenCity) {
    return bookingDao.findCustomerDbamt(provinceId, sales, startDate, endDate,
        payment, kenCity);
  }

  public List<Booking> roGetBooksByCustomer(int customerId, String kenSales,
      Date startDate, Date endDate) {
    return bookingDao.getBooksByCustomer(customerId, kenSales, startDate,
        endDate);
  }

  public List<Booking> roDistrictStat(Date startDate, Date endDate,
      String country) {
    return bookingDao.districtStat(startDate, endDate, country);
  }

  public List<Plan> roGetRouteByDis(String districtNo, Date startDate,
      Date endDate) {
    return planListDao.getRouteByDis(districtNo, startDate, endDate);
  }

  public List<Booking> getBookByLineNo(String lineNo, Date startDate,
      Date endDate) {
    return bookingDao.getBookByLineNo(lineNo, startDate, endDate);
  }

  public List<Country> roGetCountry() {
    return countryDao.getCountry();
  }

  public List<Booking> roSearch(String orderNo, String tourist, double account) {
    return bookingDao.findBookings(orderNo, tourist, account);
  }

  public int txNetPay(String orderId, String paymentMode, BigDecimal amount,
      String moneyType) throws EbizException {
    String[] code = sequenceDao.getComputerNo("J", 2, 0);
    String paymentNo = code[0];
    String inverceNo = code[1];

    return paymentDao.netPay(orderId, paymentMode, amount, moneyType,
        paymentNo, inverceNo);
  }

  public List<Income> roGetPayments(String bookingNo) {
    return paymentDao.getPayments(bookingNo);
  }

  public List<Express> rogetExpressList(String reserveNo) {
    return expressDao.getExpressList(reserveNo);
  }

  public List<Booking> roFind(String routeName, Date startDatePeriod,
      Date endDatePeriod, Date orderStartDatePeriod,
      Date orderStartDatePeriod2, String contractNo, String invoiceNo,
      String touristName, String bookState, String cancelFlag) {

    return bookingDao.find(routeName, startDatePeriod, endDatePeriod,
        orderStartDatePeriod, orderStartDatePeriod2, contractNo, invoiceNo,
        touristName, bookState, cancelFlag);
  }

  public Booking roGetReserve(String bookingNo) {
    Booking book = bookingDao.getBooking(bookingNo);
    List<Income> pays = paymentDao.getPayments(bookingNo);
    List<Invoice> invoices = invoiceDao.getInvoice(bookingNo);

    for (Income object : pays) {
      for (Invoice invoice : invoices) {
        if (invoice.getInvoiceNo().equals(object.getBookingNo())) {
          // object.putInvices(invoice);
          // TODO
        }
      }
    }

    book.setPayments(pays);
    return book;
  }

  public List<LabelValueBean> roGetPaymentTypes() {
    return paymentDao.getPaymentTypes();
  }

  public long txGathering(Income payment, long userId) {
    // TODO WorkFLow
    return paymentDao.saveIncome(payment);
  }

  public List<LabelValueBean> roGetCertifTypes() {
    return null;
  }

  public String txAddBooking(Booking book, List<Tourist> customerList) {
    String computerNo = sequenceDao.getComputerNo("A", 0);
    book.setBookingNo(computerNo);

    int space = 0;
    for (Tourist customer : customerList) {
      if (!StringUtil.hasLength(customer.getUid())) {
        space++;
      }
    }

    if (space > 0) {
      String[] computerNos = sequenceDao.getComputerNo("Y", space, 0);

      int i = 0;
      for (Tourist tourist : customerList) {
        if (!StringUtil.hasLength(tourist.getUid())) {
          tourist.setUid(computerNos[i++]);
        }
      }
    }

    return bookingDao.addBooking(book, customerList);
  }

  public List<Booking> roGetBookings(long memberId) {
    return bookingDao.getBookings(memberId);

  }

  public List<Booking> roGetNewBooking(int uid) {
    return bookingDao.getUnreadBookings(uid);
  }

  public int txReadBooking(Booking booking) {
    return bookingDao.readBooking(booking);
  }

  public List<Booking> roGetBooks(long teamId, long userId) {
    return bookingDao.findUndetermined(teamId, userId);
  }

  public Booking roGetReserveBook(String reserveNo) {
    return bookingDao.getBooking(reserveNo);
  }

  public int txConfirm(Booking book) {
    int result = bookingDao.confirm(book);
    // 订单确认 更新工作流状态
    return result;
  }

  public int txRead(Booking booking) {
    return bookingDao.readBooking(booking);
  }

  // public String txAddBooking(Booking book, List<Tourist> customerList) {
  // String computerNo = sequenceDao.getComputerNo("A", 0);
  // book.setBookingNo(computerNo);
  //
  // int space = 0;
  // for (Tourist customer : customerList) {
  // if (!StringUtil.hasLength(customer.getUid())) {
  // space++;
  // }
  // }
  //
  // if (space > 0) {
  // String[] computerNos = sequenceDao.getComputerNo("Y", space, 0);
  //
  // int i = 0;
  // for (Tourist tourist : customerList) {
  // if (!StringUtil.hasLength(tourist.getUid())) {
  // tourist.setUid(computerNos[i++]);
  // }
  // }
  // }
  //
  // return memberBookingDao.addBooking(book, customerList);
  // }

  public List<Booking> roGetBookings(int userId) {
    return bookingDao.getBookings(userId);

  }

  public List<Booking> roGetConfirmBookings(int accountId) {
    return bookingDao.getConfirmBookings(accountId);
  }

  public List<Booking> roGetUnconfirmBookings(int accountId) {
    return bookingDao.getUnconfirmBookings(accountId);
  }
}
