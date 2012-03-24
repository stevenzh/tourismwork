package com.opentravelsoft.action.booking;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import com.opentravelsoft.util.LabelValueBean;

import com.opensymphony.xwork2.ActionContext;
import com.opentravelsoft.common.SessionKeyParams;
import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.Tourist;
import com.opentravelsoft.model.Member;
import com.opentravelsoft.service.order.BookingService;
import com.opentravelsoft.webapp.action.PortalAction;

/**
 * 修改订单
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:59 $
 */
public class OrderBookingAction extends PortalAction {
  private static final long serialVersionUID = 3629364796321206259L;

  private static final Logger log = Logger.getLogger(OrderBookingAction.class);

  private BookingService bookingService;

  private String planNo;

  private Plan plan;

  private List<LabelValueBean> certifTypes;

  private List<Tourist> customerList = new ArrayList<Tourist>();

  /** 订单 */
  private Booking book = new Booking();

  /** 可预订人数 */
  private int paxSum = 0;

  /** 性别选择列表 */
  private List<LabelValueBean> sexList = new ArrayList<LabelValueBean>();

  /** 出生地选择列表 */
  private List<LabelValueBean> birthPlaceList = new ArrayList<LabelValueBean>();

  /** 房间类型选择列表 */
  private List<LabelValueBean> roomTypeList = new ArrayList<LabelValueBean>();

  private List<LabelValueBean> canSplitList = new ArrayList<LabelValueBean>();

  /** 客户(客人提供商) */
  private List<LabelValueBean> agentList = new ArrayList<LabelValueBean>();

  private List<LabelValueBean> confirmStatusList;

  /** 价格列表 */
  private List<LabelValueBean> priceList = new ArrayList<LabelValueBean>();

  /** 订单号 */
  private String bookingNo;

  public String input() {
    plan = bookingService.roGetPlanDetail(planNo);
    book.setPlan(plan);

    /** 可预订人数 */
    paxSum = plan.getPax3();
    book.setPax(1);

    Member member = getMember();
    Tourist me = new Tourist();
    me.setId(0);
    me.setUserName(member.getUsername());
    customerList.add(me);

    // 初始化列表
    certifTypes = bookingService.roGetCertifTypes();
    sexList = getSysList("DOM_sex");
    sexList = getSysList("DOM_sex");
    birthPlaceList = bookingService.roGetBirthplaceList();
    roomTypeList = bookingService.roGetRoomTypeList();
    priceList = new ArrayList<LabelValueBean>();

    return SUCCESS;
  }

  public String changePax() {
    plan = bookingService.roGetPlanDetail(book.getBookingNo());

    // 初始化列表
    sexList = getSysList("DOM_sex");

    /** 可预订人数 */
    paxSum = plan.getPax3();

    if (customerList.size() > book.getPax()) {
      for (int i = customerList.size(); i > book.getPax(); i--) {
        customerList.remove(i - 1);
      }
    } else if (customerList.size() < book.getPax()) {
      for (int i = customerList.size(); i < book.getPax(); i++) {
        Tourist trip = new Tourist();
        trip.setId(i);
        customerList.add(trip);
      }
    }

    return "changePax";
  }

  public String submit() {
    Member customer = (Member) ActionContext.getContext().getSession()
        .get(SessionKeyParams.EBIZ_USER);
    bookingNo = bookingService.txAddBooking(book, customerList);
    if (null == bookingNo) {
      log.error("订单失败");
      addActionError("订单失败");
      return "";
    }

    return SUCCESS;
  }

  public List<LabelValueBean> getSexList() {
    return sexList;
  }

  public void setBookingService(BookingService memberBookingService) {
    this.bookingService = memberBookingService;
  }

  public String getPlanNo() {
    return planNo;
  }

  public void setPlanNo(String planNo) {
    this.planNo = planNo;
  }

  public List<LabelValueBean> getCertifTypes() {
    return certifTypes;
  }

  public List<Tourist> getCustomerList() {
    return customerList;
  }

  public void setCustomerList(List<Tourist> customerList) {
    this.customerList = customerList;
  }

  public Booking getBook() {
    return book;
  }

  public void setBook(Booking book) {
    this.book = book;
  }

  public int getPaxSum() {
    return paxSum;
  }

  public List<LabelValueBean> getBirthPlaceList() {
    return birthPlaceList;
  }

  public List<LabelValueBean> getRoomTypeList() {
    return roomTypeList;
  }

  public List<LabelValueBean> getCanSplitList() {
    return canSplitList;
  }

  public List<LabelValueBean> getAgentList() {
    return agentList;
  }

  public List<LabelValueBean> getConfirmStatusList() {
    return confirmStatusList;
  }

  public List<LabelValueBean> getPriceList() {
    return priceList;
  }

}
