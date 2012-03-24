package com.opentravelsoft.action.booking;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.opentravelsoft.util.LabelValueBean;

import com.opentravelsoft.EbizException;
import com.opentravelsoft.common.EbizCommon;
import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.Tourist;
import com.opentravelsoft.entity.finance.Income;
import com.opentravelsoft.entity.product.NetPayEntity;
import com.opentravelsoft.service.order.BookingService;
import com.opentravelsoft.util.ConvertUtils;
import com.opentravelsoft.util.StringUtil;
import com.opentravelsoft.webapp.action.PortalAction;

/**
 * 预订客人，填写基本信息
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:06 $
 */
public class BookDetailAction extends PortalAction {

  private static final long serialVersionUID = -183999872939636800L;

  protected static final Log logger = LogFactory.getLog(BookDetailAction.class);

  private BookingService bookService;

  /** 订单 */
  private Booking book = new Booking();

  /** 订单编号编号 */
  private String reserveNo;

  /** 性别选择列表 */
  private List<LabelValueBean> sexList = new ArrayList<LabelValueBean>();

  /** 出生地选择列表 */
  private List<LabelValueBean> birthPlaceList = new ArrayList<LabelValueBean>();

  /** 性别选择列表 */
  private List<LabelValueBean> passportPlaceList = new ArrayList<LabelValueBean>();

  /** 选中客人 */
  private String[] tourists;

  /** 房间类型选择列表 */
  private List<LabelValueBean> roomTypeList = new ArrayList<LabelValueBean>();

  private List<Tourist> customerList = new ArrayList<Tourist>();

  private String note = "";

  public void setNote(String note) {
    this.note = note;
  }

  private static final DecimalFormat DF = new DecimalFormat("##.00");

  private Booking booking;

  private NetPayEntity entity = new NetPayEntity();

  private List<Income> payments;

  public void setBookingService(BookingService bookService) {
    this.bookService = bookService;
  }

  @Override
  public String input() {
    sexList = getSysList("DOM_sex");
    birthPlaceList = bookService.roGetBirthplaceList();
    passportPlaceList = bookService.roGetPassportPlaceList();
    roomTypeList = bookService.roGetRoomTypeList();
    book = bookService.roGetReserve(reserveNo);

    Map<String, String> sexMap = ConvertUtils.beansToMap(sexList);
    Map<String, String> typeMap = ConvertUtils.beansToMap(roomTypeList);
    customerList = book.getCustomerList();
    for (Tourist trip : customerList) {
      trip.setSex(sexMap.get(trip.getSex()));
      trip.setRoomType(typeMap.get(trip.getRoomType()));
    }
    // 订单详细
    booking = bookService.roGetReserve(reserveNo);
    // 付款记录
    payments = bookService.roGetPayments(reserveNo);
    // 商户号
    entity.setMid(EbizCommon.CHINA_BANK_MID);
    // MD5 Key
    entity.setKey(EbizCommon.CHINA_BANK_KEY);
    // 团款
    entity.setAmount(DF.format(booking.getDbamt()));
    // 订单号
    entity.setOid(reserveNo);

    entity.refreshMd5key();
    return INPUT;
  }

  public String submit() {
    logger.info("submit");

    try {
      book.setOpuser(0L);
      bookService.txUpdateBooking(book, customerList, note);
    } catch (EbizException e) {
      logger.error("update failure", e);
      return INPUT;
    }
    return SUCCESS;
  }

  /**
   * 取消订单
   * 
   * @return
   */
  public String cancel() {
    book.setBookingNo(reserveNo);
    book.setOpuser(0L);
    bookService.txCancelBooking(book, note);

    sexList = getSysList("DOM_sex");
    birthPlaceList = bookService.roGetBirthplaceList();
    passportPlaceList = bookService.roGetPassportPlaceList();
    roomTypeList = bookService.roGetRoomTypeList();
    book = bookService.roGetReserve(reserveNo);

    return SUCCESS;
  }

  /**
   * 取消订单中的部分客人
   * 
   * @return
   */
  public String cancelCustomers() {
    book.setBookingNo(reserveNo);
    book.setOpuser(0L);

    Set<String> set = new HashSet<String>(0);
    for (int i = 0; i < tourists.length; i++) {
      if (StringUtil.hasLength(tourists[i]))
        set.add(tourists[i]);
    }

    bookService.txCancelCustomers(book, set, note);

    sexList = getSysList("DOM_sex");
    birthPlaceList = bookService.roGetBirthplaceList();
    passportPlaceList = bookService.roGetPassportPlaceList();
    roomTypeList = bookService.roGetRoomTypeList();
    book = bookService.roGetReserve(reserveNo);
    return SUCCESS;
  }

  public Booking getBook() {
    return book;
  }

  public List<LabelValueBean> getSexList() {
    return sexList;
  }

  public List<LabelValueBean> getBirthPlaceList() {
    return birthPlaceList;
  }

  public List<LabelValueBean> getPassportPlaceList() {
    return passportPlaceList;
  }

  public List<LabelValueBean> getRoomTypeList() {
    return roomTypeList;
  }

  public String[] getSelects() {
    return tourists;
  }

  public void setSelects(String[] sTourist) {
    this.tourists = sTourist;
  }

  public String getReserveNo() {
    return reserveNo;
  }

  public void setReserveNo(String reserveNo) {
    this.reserveNo = reserveNo;
  }

  public Booking getBooking() {
    return booking;
  }

  public List<Income> getPayments() {
    return payments;
  }

  public NetPayEntity getEntity() {
    return entity;
  }
}
