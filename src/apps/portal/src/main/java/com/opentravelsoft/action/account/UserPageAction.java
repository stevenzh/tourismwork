package com.opentravelsoft.action.account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.opentravelsoft.util.LabelValueBean;

import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.product.Notice;
import com.opentravelsoft.service.VisaHelpService;
import com.opentravelsoft.service.order.BookingService;
import com.opentravelsoft.service.setting.TeamService;
import com.opentravelsoft.webapp.action.PortalAction;

/**
 * 用户Homepage
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:06 $
 */
public class UserPageAction extends PortalAction {
  private static final long serialVersionUID = 3913178382399940090L;

  protected static final Log logger = LogFactory.getLog(UserPageAction.class);

  private List<Notice> notices;

  private List<LabelValueBean> visaAreas;

  private List<Plan> lastTours = new ArrayList<Plan>();

  private List<Booking> confirmedBookings;

  private List<Booking> unconfirmedBookings;

  private Map<String, String> groupList = new HashMap<String, String>();

  private BookingService bookingService;

  private VisaHelpService visaService;

  // private NoticeService noticeService;

  private TeamService teamService;

  public List<Notice> getNotices() {
    return notices;
  }

  public String getKenClassType() {
    return "%";
  }

  public List<LabelValueBean> getVisaAreas() {
    return visaAreas;
  }

  public List<Booking> getConfirmedBookings() {
    return confirmedBookings;
  }

  public List<Booking> getUnconfirmedBookings() {
    return unconfirmedBookings;
  }

  public List<Plan> getLastTours() {
    return lastTours;
  }

  @Override
  public String execute() {
    // notices = noticeService.getValidNotices();
    visaAreas = visaService.getAreas();
    groupList = teamService.roGetDeptMap();
    confirmedBookings = bookingService.roGetConfirmBookings(1);
    unconfirmedBookings = bookingService.roGetUnconfirmBookings(1);
    return SUCCESS;
  }

  public Map<String, String> getDepartments() {
    return groupList;
  }
}
