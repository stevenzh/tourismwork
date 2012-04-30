package com.opentravelsoft.action.manage.order;

import java.util.ArrayList;
import java.util.List;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.service.order.BookingService;

/**
 * 计调未读订单
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:55 $
 */
public class ReadBookingAction extends ManageAction {
  private static final long serialVersionUID = 1L;

  @Autowired
  private BookingService bookingService;

  private String bookingNo;

  private Booking booking = new Booking();

  /** 性别选择列表 */
  private List<LabelValueBean> sexList = new ArrayList<LabelValueBean>();

  /** 出生地选择列表 */
  private List<LabelValueBean> birthPlaceList = new ArrayList<LabelValueBean>();

  /** 性别选择列表 */
  private List<LabelValueBean> passportPlaceList = new ArrayList<LabelValueBean>();

  /** 房间类型选择列表 */
  private List<LabelValueBean> roomTypeList = new ArrayList<LabelValueBean>();

  // -------------------------------------------------------------------------

  private String kenDepartmentId;

  private String kenEmployeeId;

  @Override
  public String input() throws Exception {
    // setMenu(KeyParams.EBIZ_SESSION_MANAGE_SALE);
    booking = bookingService.roGetReserve(bookingNo);
    sexList = getSysList("DOM_sex");
    birthPlaceList = bookingService.roGetBirthplaceList();
    passportPlaceList = bookingService.roGetPassportPlaceList();
    roomTypeList = bookingService.roGetRoomTypeList();
    return INPUT;
  }

  public String submit() {
    Employee user = getUser();
    booking.setOpuser(user.getUserId());
    bookingService.txReadBooking(booking);
    return SUCCESS;
  }

  public String getBookingNo() {
    return bookingNo;
  }

  public void setBookingNo(String bookingNo) {
    this.bookingNo = bookingNo;
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

  public Booking getBooking() {
    return booking;
  }

  public void setBooking(Booking booking) {
    this.booking = booking;
  }

  public String getKenDepartmentId() {
    return kenDepartmentId;
  }

  public void setKenDepartmentId(String kenDepartmentId) {
    this.kenDepartmentId = kenDepartmentId;
  }

  public String getKenEmployeeId() {
    return kenEmployeeId;
  }

  public void setKenEmployeeId(String kenEmployeeId) {
    this.kenEmployeeId = kenEmployeeId;
  }

}
