package com.opentravelsoft.action.manage.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.activiti.engine.task.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.EbizException;
import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.common.EbizCommon;
import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Express;
import com.opentravelsoft.entity.Tourist;
import com.opentravelsoft.entity.finance.Income;
import com.opentravelsoft.entity.finance.Reckoning;
import com.opentravelsoft.service.finance.ReckoningService;
import com.opentravelsoft.service.order.BookingService;
import com.opentravelsoft.util.ConvertUtils;
import com.opentravelsoft.util.StringUtil;
import com.opentravelsoft.workflow.TaskDao;

/**
 * 预订客人，填写基本信息
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:51 $
 */
public class BookingDetailAction extends ManageAction {
  private static final long serialVersionUID = -183999872939636800L;

  protected static final Log logger = LogFactory
      .getLog(BookingDetailAction.class);

  private BookingService bookingService;

  private TaskDao taskService;

  private ReckoningService reckoningMakeService;

  /** 订单 */
  private Booking book = new Booking();

  /** 出团计划编号 */
  private String reserveNo;

  /** 性别选择列表 */
  private List<LabelValueBean> sexList = new ArrayList<LabelValueBean>();

  /** 出生地选择列表 */
  private List<LabelValueBean> birthPlaceList = new ArrayList<LabelValueBean>();

  /** 护照签发地选择列表 */
  private List<LabelValueBean> passportPlaceList = new ArrayList<LabelValueBean>();

  /** 是否可以拆分列表 */
  private List<LabelValueBean> canSplitList = new ArrayList<LabelValueBean>();

  /** 是否制作了帐单 */
  private String status = "F";

  /** 选中客人 */
  private String[] selects;

  /** 房间类型选择列表 */
  private List<LabelValueBean> roomTypeList = new ArrayList<LabelValueBean>();

  /** 游客列表 */
  private List<Tourist> customerList = new ArrayList<Tourist>();

  /** 帐单列表 */
  private List<Reckoning> reckoningList = new ArrayList<Reckoning>();

  /** 订单的任务列表 */
  private List<Task> taskList = new ArrayList<Task>();

  /** 配送任务列表 */
  private List<Express> expressList = new ArrayList<Express>();

  /** 付款记录 */
  private List<Income> payments = new ArrayList<Income>();

  /** 备注 */
  private String note;

  public void setNote(String note) {
    this.note = note;
  }

  @Autowired
  public void setTaskService(TaskDao taskService) {
    this.taskService = taskService;
  }

  @Autowired
  public void setBookingService(BookingService bookingService) {
    this.bookingService = bookingService;
  }

  @Autowired
  public void setReckoningMakeService(ReckoningService reckoningMakeService) {
    this.reckoningMakeService = reckoningMakeService;
  }

  @Override
  public String input() {
    sexList = getSysList("DOM_sex");
    canSplitList = getCodeList("ebiz_can_split");
    birthPlaceList = bookingService.roGetBirthplaceList();
    passportPlaceList = bookingService.roGetPassportPlaceList();
    roomTypeList = bookingService.roGetRoomTypeList();
    book = bookingService.roGetReserve(reserveNo);
    reckoningList = reckoningMakeService.roGetReckoning(reserveNo.trim());
    if (null != reckoningList && !(reckoningList.isEmpty())) {
      status = "T";
    } else
      status = "F";
    Map<String, String> sexMap = ConvertUtils.beansToMap(sexList);
    Map<String, String> typeMap = ConvertUtils.beansToMap(roomTypeList);
    customerList = book.getCustomerList();
    for (Tourist trip : customerList) {
      trip.setSex(sexMap.get(trip.getSex()));
      trip.setRoomType(typeMap.get(trip.getRoomType()));
    }

    // 订单的任务列表
    if (getSysConfig(EbizCommon.WORKFLOW_ENABLED).equals("1")) {
      taskList = taskService.getOrderTaskList(reserveNo);
    }

    // 配送任务列表
    expressList = bookingService.rogetExpressList(reserveNo);

    payments = book.getPayments();
    List<LabelValueBean> paymentType = getCodeList("ebiz_pay_type");
    Map<String, String> map = new HashMap<String, String>();
    for (LabelValueBean labelValueBean : paymentType) {
      map.put(labelValueBean.getValue(), labelValueBean.getLabel());
    }
    for (Income pay : payments) {
      if (map.containsKey(pay.getUseType()))
        pay.setUseTypeLabel(map.get(pay.getUseType()));
    }

    return INPUT;
  }

  public String submit() {
    logger.info("submit");

    try {
      Employee user = getUser();
      book.setOpuser(user.getUserId());
      bookingService.txUpdateBooking(book, customerList, note);
    } catch (EbizException e) {
      logger.error("update failure.", e);
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
    Employee user = getUser();
    book.setOpuser(user.getUserId());
    bookingService.txCancelBooking(book, note);

    sexList = getSysList("DOM_sex");
    birthPlaceList = bookingService.roGetBirthplaceList();
    passportPlaceList = bookingService.roGetPassportPlaceList();
    roomTypeList = bookingService.roGetRoomTypeList();
    canSplitList = getCodeList("ebiz_can_split");
    book = bookingService.roGetReserve(reserveNo);

    return SUCCESS;
  }

  /**
   * 取消订单中的部分客人
   * 
   * @return
   */
  public String cancelCustomers() {
    Employee user = getUser();
    book.setOpuser(user.getUserId());

    Set<String> set = new TreeSet<String>();
    for (int i = 0; i < selects.length; i++) {
      if (StringUtil.hasLength(selects[i]))
        set.add(selects[i]);
    }

    bookingService.txCancelCustomers(book, set, note);

    sexList = getSysList("DOM_sex");
    birthPlaceList = bookingService.roGetBirthplaceList();
    passportPlaceList = bookingService.roGetPassportPlaceList();
    roomTypeList = bookingService.roGetRoomTypeList();
    book = bookingService.roGetReserve(reserveNo);
    canSplitList = getCodeList("ebiz_can_split");

    return SUCCESS;
  }

  /**
   * 恢复游客
   * 
   * @return
   */
  public String resume() {
    Employee user = getUser();
    book.setOpuser(user.getUserId());
    Set<String> set = new TreeSet<String>();
    for (int i = 0; i < selects.length; i++) {
      if (StringUtil.hasLength(selects[i]))
        set.add(selects[i]);
    }
    bookingService.txResumeCustomers(book, set, note);

    return SUCCESS;
  }

  /**
   * 分单
   * 
   * @return
   */
  public String splitBooking() {
    Employee user = getUser();
    book.setOpuser(user.getUserId());

    Set<String> set = new TreeSet<String>();
    for (int i = 0; i < selects.length; i++) {
      if (StringUtil.hasLength(selects[i]))
        set.add(selects[i]);
    }

    bookingService.txSplitBooking(book, set, note);

    sexList = getSysList("DOM_sex");
    birthPlaceList = bookingService.roGetBirthplaceList();
    passportPlaceList = bookingService.roGetPassportPlaceList();
    roomTypeList = bookingService.roGetRoomTypeList();
    book = bookingService.roGetReserve(reserveNo);
    canSplitList = getCodeList("ebiz_can_split");

    return SUCCESS;
  }

  public String getReserveNo() {
    return reserveNo;
  }

  public void setReserveNo(String reserveNo) {
    this.reserveNo = reserveNo;
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
    return selects;
  }

  public void setSelects(String[] selects) {
    this.selects = selects;
  }

  public List<LabelValueBean> getCanSplitList() {
    return canSplitList;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public List<Reckoning> getReckoningList() {
    return reckoningList;
  }

  public void setReckoningList(List<Reckoning> reckoningList) {
    this.reckoningList = reckoningList;
  }

  public List<Task> getTaskList() {
    return taskList;
  }

  public List<Express> getExpressList() {
    return expressList;
  }

  public List<Income> getPayments() {
    return payments;
  }

}
