package com.opentravelsoft.action.manage.order;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.EbizException;
import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.LinePrice;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.Tourist;
import com.opentravelsoft.service.operator.TourService;
import com.opentravelsoft.service.order.BookingService;
import com.opentravelsoft.service.setting.EmployeeService;
import com.opentravelsoft.util.RowDataUtil;
import com.opentravelsoft.util.StringUtil;

/**
 * 增加订单
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:51 $
 */
public class BookingOrderAction extends ManageAction {
  private static final long serialVersionUID = -8838901770913936645L;

  private static final Logger log = Logger.getLogger(BookingOrderAction.class);

  protected DecimalFormat DF = new DecimalFormat("##0");

  @Autowired
  private BookingService bookingService;

  @Autowired
  private EmployeeService employeeService;

  @Autowired
  private TourService tourService;

  /** 出团计划编号 */
  private String recordNo;

  /** 订单编号 */
  private String reserveNo;

  /** 订单 */
  private Booking book = new Booking();

  private Plan plan = new Plan();

  /** 可预订人数 */
  private int paxSum = 0;

  private List<Tourist> customerList = new ArrayList<Tourist>();

  /** 性别选择列表 */
  private List<LabelValueBean> sexList = new ArrayList<LabelValueBean>();

  /** 出生地选择列表 */
  private List<LabelValueBean> birthPlaceList = new ArrayList<LabelValueBean>();

  /** 护照签发地列表 */
  private List<LabelValueBean> passportPlaceList = new ArrayList<LabelValueBean>();

  /** 房间类型选择列表 */
  private List<LabelValueBean> roomTypeList = new ArrayList<LabelValueBean>();

  private List<LabelValueBean> canSplitList = new ArrayList<LabelValueBean>();

  /** 地区 */
  private List<LabelValueBean> regions = new ArrayList<LabelValueBean>();

  /** 客户(客人提供商) */
  private List<LabelValueBean> agentList = new ArrayList<LabelValueBean>();

  private List<LabelValueBean> confirmStatusList;

  /** 销售员 */
  private List<Employee> salesmans = new ArrayList<Employee>();

  /** 价格列表 */
  private List<LabelValueBean> priceList = new ArrayList<LabelValueBean>();

  /** 备注 */
  private String note;

  public String input() {
    Employee user = getUser();

    BigDecimal defaultPrice = new BigDecimal(0);
    plan = bookingService.roGetPlanDetail(recordNo);

    if (null == plan) {
      addActionError("订单号错误.");
      return INPUT;
    }

    // 2008年7月启动 只显示月结客户
    String payment = "";
    Calendar cal = Calendar.getInstance();
    cal.setTime(plan.getOutDate());
    if (cal.get(Calendar.YEAR) >= 2008 && cal.get(Calendar.MONTH) > 5)
      payment = "M";

    // 订单是否可以拆分
    canSplitList = getCodeList("ebiz_can_split");
    agentList = bookingService.getAgentBySales(user.getUserId(), "");
    sexList = getSysList("DOM_sex");
    // birthPlaceList = bookingService.roGetBirthplaceList();
    passportPlaceList = bookingService.roGetPassportPlaceList();
    roomTypeList = bookingService.roGetRoomTypeList();
    regions = bookingService.getStateByCountry("CN");
    salesmans = employeeService.roGetSalesList();
    priceList = new ArrayList<LabelValueBean>();
    List<LinePrice> prices = bookingService.roGetPrices(plan.getPlanNo());
    for (LinePrice price : prices) {
      LabelValueBean bean = new LabelValueBean();
      bean.setLabel(price.getType() + " 直客价:" + DF.format(price.getPrice())
          + " 同行价:" + DF.format(price.getPriceOther()));
      bean.setValue(DF.format(price.getPriceOther()));
      priceList.add(bean);
      if (price.isDefaultPrice()) {
        defaultPrice = price.getPriceOther();
      }
    }

    // 独立成团 1 不可分拆 2 无 3
    book.setCanSplit('2');
    /** 可预订人数 */
    paxSum = plan.getPax3();

    book.setPlan(plan);
    book.getPlan().setPlanNo(recordNo);
    // book.setLinkman(user.getUserName());
    // book.setLinkPhone(user.getPhone());
    book.setPax(2);

    if (customerList.size() > book.getPax()) {
      for (int i = customerList.size(); i < book.getPax(); i--) {
        customerList.remove(i);
      }
    } else if (customerList.size() < book.getPax()) {
      for (int i = customerList.size(); i < book.getPax(); i++) {
        Tourist trip = new Tourist();
        trip.setId(i);
        trip.setPrice(defaultPrice);
        trip.setReceivables(defaultPrice);
        customerList.add(trip);
      }
    }

    birthPlaceList = tourService.roGetBirthplaceList();
    passportPlaceList = tourService.roGetPassportPlaceList();

    Map<String, String> birthPlace = new HashMap<String, String>();
    Map<String, String> passportPlace = new HashMap<String, String>();

    for (LabelValueBean lbn : birthPlaceList) {
      birthPlace.put(lbn.getLabel(), lbn.getValue());
    }
    for (LabelValueBean lbn : passportPlaceList) {
      passportPlace.put(lbn.getLabel(), lbn.getValue());
    }

    for (Tourist item : customerList) {
      item.setBirthplaceName(birthPlace.get(item.getBirthplace()));
      item.setPassportPlaceName(passportPlace.get(item.getPassportPlace()));
    }

    return INPUT;
  }

  public String changePax() {
    Employee user = getUser();

    plan = bookingService.roGetPlanDetail(recordNo);
    BigDecimal defaultPrice = new BigDecimal(0);
    canSplitList = getCodeList("ebiz_can_split");

    // 2008年7月启动 只显示月结客户
    String payment = "";
    Calendar cal = Calendar.getInstance();
    cal.setTime(plan.getOutDate());
    if (cal.get(Calendar.YEAR) >= 2008 && cal.get(Calendar.MONTH) > 5)
      payment = "M";
    agentList = bookingService.getAgentBySales(user.getUserId(), "");
    sexList = getSysList("DOM_sex");
    birthPlaceList = bookingService.roGetBirthplaceList();
    passportPlaceList = bookingService.roGetPassportPlaceList();
    roomTypeList = bookingService.roGetRoomTypeList();
    regions = bookingService.getStateByCountry("CN");
    salesmans = employeeService.roGetSalesList();
    confirmStatusList = getCodeList("ebiz_confirm_status");

    priceList = new ArrayList<LabelValueBean>();
    List<LinePrice> prices = bookingService.roGetPrices(plan.getPlanNo());
    for (LinePrice price : prices) {
      LabelValueBean bean = new LabelValueBean();
      bean.setLabel(price.getType() + " 直客价:" + DF.format(price.getPrice())
          + " 同行价:" + DF.format(price.getPriceOther()));
      bean.setValue(DF.format(price.getPriceOther()));
      priceList.add(bean);
      if (price.isDefaultPrice()) {
        defaultPrice = price.getPriceOther();
      }
    }

    /** 可预订人数 */
    paxSum = plan.getPax3();
    int size = customerList.size() + 2;
    List<Tourist> list = new ArrayList<Tourist>();
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < customerList.size(); j++) {
        if (customerList.get(j).getId() == i) {
          list.add(customerList.get(j));
          break;
        }
      }
    }

    customerList = list;

    if (customerList.size() > book.getPax()) {
      if (StringUtil.hasLength(reserveNo)) {
        book.setPax(customerList.size());
        addActionMessage("订单人数不可以减少,可以[取消客人].");
      } else
        for (int i = customerList.size(); i > book.getPax(); i--) {
          customerList.remove(i - 1);
        }
    } else if (customerList.size() < book.getPax()) {
      for (int i = customerList.size(); i < book.getPax(); i++) {
        Tourist trip = new Tourist();
        trip.setId(i);
        trip.setPrice(defaultPrice);
        trip.setReceivables(defaultPrice);
        customerList.add(trip);
      }
    }
    return SUCCESS;
  }

  public String submit() {
    Employee user = getUser();

    book.setReserve(user.getUserId());
    // 团队确认状态 1-团确 2-团候
    book.setConfirmStatus("2");
    // 操作人
    book.setOpuser(user.getUserId());

    BigDecimal expense = new BigDecimal(0);
    int realPax = 0;
    for (int i = customerList.size() - 1; i >= 0; i--) {
      Tourist trip = customerList.get(i);
      if (trip.getUserName().trim().length() > 0) {
        realPax++;
        expense = expense.add(trip.getReceivables());
      } else
        customerList.remove(i);
    }

    if (realPax == 0) {
      addActionError("客人的名字必须填写.");
      return INPUT;
    }
    book.setDbamt(expense);

    Hashtable<String, String> ht = bookingService.txAddBook(book, customerList,
        user.getUserId(), false);
    String check = ht.get("CHECK");
    reserveNo = ht.get("NO");
    if (check.equals("-1")) {
      addActionError("客户欠款超过最大限制.");
      return ERROR;
    } else if (check.equals("0")) {
      addActionError("客户欠款超过限制.");
    }

    if (null == reserveNo) {
      log.error("订单失败");
      addActionError("订单失败");
      return ERROR;
    }

    return SUCCESS;
  }

  @Override
  public void validate() {
    for (int i = 0; i < customerList.size(); i++) {
      Tourist trip = customerList.get(i);
      // 姓名
      String name = trip.getUserName();

      if (!StringUtil.hasLength(name)) {
        addFieldError("customerName", "客人姓名必须输入.");
      }
      // 身份证
      if (StringUtil.hasLength(trip.getIdCard())) {
        boolean a = Pattern.matches("[0-9]{15}", trip.getIdCard());
        boolean b = Pattern.matches("[0-9]{17}[0-9a-zA-z]", trip.getIdCard());
        if (!a && !b)
          addFieldError("idCard", "客人：" + RowDataUtil.getString(name)
              + "[身份证]格式错误.身份证为15位或18位数字.");
      }

      // 性别
      // 出生日期
      // 护照号
      // 签发日期
      // 住房要求
      // 同住序号
      // 报价
      // 应收团款
      // 报价说明
    }
  }

  @Override
  public String execute() {
    Employee user = getUser();

    BigDecimal defaultPrice = new BigDecimal(0);
    sexList = getSysList("DOM_sex");
    birthPlaceList = bookingService.roGetBirthplaceList();
    passportPlaceList = bookingService.roGetPassportPlaceList();
    canSplitList = getCodeList("ebiz_can_split");
    confirmStatusList = getCodeList("ebiz_confirm_status");
    roomTypeList = bookingService.roGetRoomTypeList();
    regions = bookingService.getStateByCountry("CN");
    salesmans = employeeService.roGetSalesList();

    book = bookingService.roGetReserve(reserveNo);

    // 2008年7月启动 只显示月结客户
    String payment = "";
    Calendar cal = Calendar.getInstance();
    cal.setTime(book.getPlan().getOutDate());
    if (cal.get(Calendar.YEAR) >= 2008 && cal.get(Calendar.MONTH) > 5)
      payment = "M";

    priceList = new ArrayList<LabelValueBean>();
    List<LinePrice> prices = bookingService.roGetPrices(book.getPlan()
        .getPlanNo());
    for (LinePrice price : prices) {
      LabelValueBean bean = new LabelValueBean();
      bean.setLabel(price.getType() + " 直客价:" + DF.format(price.getPrice())
          + " 同行价:" + DF.format(price.getPriceOther()));
      bean.setValue(DF.format(price.getPriceOther()));
      priceList.add(bean);
      if (price.isDefaultPrice()) {
        defaultPrice = price.getPriceOther();
      }
    }
    agentList = bookingService.getAgentBySales(user.getUserId(), "");
    customerList = book.getCustomerList();

    if (book.getPax() > customerList.size()) {
      for (int i = customerList.size(); i < book.getPax(); i++) {
        Tourist trip = new Tourist();
        trip.setId(i);
        trip.setPrice(defaultPrice);
        trip.setReceivables(defaultPrice);
        customerList.add(trip);
      }
    }

    Map<String, String> birthPlace = new HashMap<String, String>();
    Map<String, String> passportPlace = new HashMap<String, String>();

    for (LabelValueBean lbn : birthPlaceList) {
      birthPlace.put(lbn.getLabel(), lbn.getValue());
    }
    for (LabelValueBean lbn : passportPlaceList) {
      passportPlace.put(lbn.getLabel(), lbn.getValue());
    }

    for (Tourist item : customerList) {
      item.setBirthplaceName(birthPlace.get(item.getBirthplace()));
      item.setPassportPlaceName(passportPlace.get(item.getPassportPlace()));
    }

    return SUCCESS;
  }

  /**
   * 提交修改的订单
   * 
   * @return
   * @throws Exception
   */
  public String change() {
    logger.info("submit");
    Employee user = getUser();

    BigDecimal expense = new BigDecimal(0);
    for (Tourist trip : customerList) {
      expense = expense.add(trip.getReceivables());
      if (!StringUtil.hasLength(trip.getUserName())) {
        trip.setUserName("客人");
        trip.setPinYin("KE REN");
      }
    }
    book.setDbamt(expense);
    book.setOpuser(user.getUserId());
    book.setReserve(user.getUserId());

    int result;
    try {
      result = bookingService.txUpdateBooking(book, customerList, note);
    } catch (EbizException e) {
      logger.error("plan is not find.", e);
      addActionError("更新失败.");
      return INPUT;
    }
    if (result < 0) {
      addActionError("订单修改失败,请检查出团计划名额.");
    }
    return SUCCESS;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public String getRecordNo() {
    return recordNo;
  }

  public void setRecordNo(String recordNo) {
    this.recordNo = recordNo;
  }

  public String getReserveNo() {
    return reserveNo;
  }

  public void setReserveNo(String reserveNo) {
    this.reserveNo = reserveNo;
  }

  public List<LabelValueBean> getCanSplitList() {
    return canSplitList;
  }

  public List<LabelValueBean> getAgentList() {
    return agentList;
  }

  public int getPaxSum() {
    return paxSum;
  }

  public Booking getBook() {
    return book;
  }

  public void setBook(Booking book) {
    this.book = book;
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

  public void setCustomerList(List<Tourist> customerList) {
    this.customerList = customerList;
  }

  public List<Tourist> getCustomerList() {
    return customerList;
  }

  public List<LabelValueBean> getConfirmStatusList() {
    return confirmStatusList;
  }

  public List<LabelValueBean> getPriceList() {
    return priceList;
  }

  public List<LabelValueBean> getRegions() {
    return regions;
  }

  public List<Employee> getSalesmans() {
    return salesmans;
  }

  public Plan getPlan() {
    return plan;
  }

}
