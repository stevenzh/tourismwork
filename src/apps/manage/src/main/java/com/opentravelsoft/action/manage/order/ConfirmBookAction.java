package com.opentravelsoft.action.manage.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Tourist;
import com.opentravelsoft.service.order.BookingService;

/**
 * 审核订单
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:00 $
 */
public class ConfirmBookAction extends ManageAction {
  private static final long serialVersionUID = 1873002169910914015L;

  @Autowired
  private BookingService bookintService;

  /** 订单号 */
  private String recordNo;

  private Booking book;

  private List<Tourist> customerList = new ArrayList<Tourist>();

  private String[] selects;

  /** 房间类型选择列表 */
  private List<LabelValueBean> roomTypeList = new ArrayList<LabelValueBean>();

  // -------------------------------------------------------------------------
  private String kenDepartmentId;

  private String kenEmployeeId;

  /** 出团日期 -开始 */
  private Date kenStartDate;

  /** 出团日期 -截止 */
  private Date kenEndDate;

  private String kenRouteName;

  // -------------------------------------------------------------------------

  public String input() {
    book = bookintService.roGetReserveBook(recordNo);
    // 设置确认人数
    book.setConfirmPax(book.getPax());

    roomTypeList = bookintService.roGetRoomTypeList();

    Map<String, String> roomMap = new HashMap<String, String>();
    for (int i = 0; i < roomTypeList.size(); i++) {
      roomMap.put(roomTypeList.get(i).getValue(), roomTypeList.get(i)
          .getLabel());
    }

    for (Tourist trip : book.getCustomerList()) {
      trip.setRoomTypeName(roomMap.get(trip.getRoomType()));
    }

    return INPUT;
  }

  public String confirm() {
    // 提交审核
    Employee user = getUser();
    book.setOpuser(user.getUserId());

    for (int i = customerList.size() - 1; i >= 0; i--) {
      if (customerList.get(i).getDel() == 'Y')
        customerList.remove(i);
    }
    book.setCustomerList(customerList);
    int result = bookintService.txConfirm(book);
    if (result < 0) {
      addActionError("审核失败,请检查出团计划名额是否已满.");
      return INPUT;
    }
    return SUCCESS;
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

  public String getRecordNo() {
    return recordNo;
  }

  public void setRecordNo(String recordNo) {
    this.recordNo = recordNo;
  }

  public Booking getBook() {
    return book;
  }

  public void setBook(Booking book) {
    this.book = book;
  }

  public Date getKenStartDate() {
    return kenStartDate;
  }

  public void setKenStartDate(Date kenStartDate) {
    this.kenStartDate = kenStartDate;
  }

  public Date getKenEndDate() {
    return kenEndDate;
  }

  public void setKenEndDate(Date kenEndDate) {
    this.kenEndDate = kenEndDate;
  }

  public List<Tourist> getCustomerList() {
    return customerList;
  }

  public void setCustomerList(List<Tourist> customerList) {
    this.customerList = customerList;
  }

  public String getKenRouteName() {
    return kenRouteName;
  }

  public void setKenRouteName(String kenRouteName) {
    this.kenRouteName = kenRouteName;
  }

  public void setSelects(String[] selects) {
    this.selects = selects;
  }

}
