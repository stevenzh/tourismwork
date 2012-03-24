package com.opentravelsoft.action.manage.order;

import java.util.List;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.finance.Income;
import com.opentravelsoft.service.order.BookingService;

/**
 * 订单付款
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:51 $
 */
public class BookGatheringAction extends ManageAction {
  private static final long serialVersionUID = 421467675829379367L;

  private BookingService bookingService;

  /** 付款方式列表 */
  private List<LabelValueBean> types;

  /** 付款项目（定金，预付款，余款，全额团款） */
  private List<LabelValueBean> paymentType;

  private Income payment;

  private String bookingNo;

  @Override
  public String input() throws Exception {
    types = bookingService.roGetPaymentTypes();
    paymentType = getCodeList("ebiz_pay_type");
    return INPUT;
  }

  public String submit() throws Exception {
    Employee user = getUser();
    bookingService.txGathering(payment, user.getUserId());
    return SUCCESS;
  }

  @Autowired
  public void setBookingService(BookingService bookingService) {
    this.bookingService = bookingService;
  }

  public List<LabelValueBean> getTypes() {
    return types;
  }

  public Income getPayment() {
    return payment;
  }

  public void setPayment(Income payment) {
    this.payment = payment;
  }

  public List<LabelValueBean> getPaymentType() {
    return paymentType;
  }

  public String getBookingNo() {
    return bookingNo;
  }

  public void setBookingNo(String bookingNo) {
    this.bookingNo = bookingNo;
  }

}
