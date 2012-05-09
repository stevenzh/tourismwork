package com.opentravelsoft.action.booking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opentravelsoft.common.SessionKeyParams;
import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.Member;
import com.opentravelsoft.service.order.BookingService;
import com.opentravelsoft.webapp.action.PortalAction;

/**
 * 我的订单列表
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:59 $
 */
public class MyBookingAction extends PortalAction {
  private static final long serialVersionUID = 7874299238224325327L;

  @Autowired
  private BookingService bookingService;

  private Member member;

  /** 订单列表 */
  private List<Booking> bookings;

  public String input() {
    member = (Member) ActionContext.getContext().getSession()
        .get(SessionKeyParams.EBIZ_USER);
    bookings = bookingService.roGetBookings(member.getId());

    return SUCCESS;
  }

  public List<Booking> getBookings() {
    return bookings;
  }

}
