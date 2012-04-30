package com.opentravelsoft.action.manage.order;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.LinePrice;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.Tourist;
import com.opentravelsoft.service.operator.TourService;
import com.opentravelsoft.service.order.BookingService;
import com.opentravelsoft.service.setting.EmployeeService;
import com.opentravelsoft.util.StringUtil;

/**
 * 拆分订单
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 */
public class BookingSplitAction extends ManageAction {
  private static final long serialVersionUID = -7463230324280513531L;

  @Autowired
  private BookingService bookingService;

  @Autowired
  private EmployeeService employeeService;

  @Autowired
  private TourService tourService;

  protected DecimalFormat DF = new DecimalFormat("##0");

  /** 出团计划编号 */
  private String recordNo;

  /** 订单编号 */
  private String reserveNo;

  /** 订单 */
  private Booking book = new Booking();

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

  /** 客户(客人提供商) */
  private List<LabelValueBean> agentList = new ArrayList<LabelValueBean>();

  private List<LabelValueBean> confirmStatusList;

  /** 地区 */
  private List<LabelValueBean> regions = new ArrayList<LabelValueBean>();

  /** 销售员 */
  private List<Employee> salesmans = new ArrayList<Employee>();

  /** 价格列表 */
  private List<LabelValueBean> priceList = new ArrayList<LabelValueBean>();

  // 用于拆分订单所选的名单
  private String[] selects;

  /**
   * 拆分订单初始化
   */
  public String input() {
    Employee user = getUser();

    double defaultPrice = 0d;
    Plan plan = bookingService.roGetPlanDetail(recordNo);

    canSplitList = getCodeList("ebiz_can_split");

    // 2008年7月启动 只显示月结客户
    String payment = "";
    Calendar cal = Calendar.getInstance();
    cal.setTime(plan.getOutDate());
    if (cal.get(Calendar.YEAR) >= 2008 && cal.get(Calendar.MONTH) > 5)
      payment = "M";

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
    book.setCanSplit("2");
    /** 可预订人数 */
    paxSum = plan.getPax3();

    book.setBookingNo(recordNo);
    book.setPlan(plan);
    // book.setLinkman(user.getUserName());
    // book.setLinkPhone(user.getPhone());
    book.setPax(2);

    String[] nmno;
    if (1 == selects.length) {
      nmno = selects[0].split(",");
    } else {
      nmno = selects;
    }
    customerList = bookingService.roFindCustomer(nmno);

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

  /**
   * 拆分订单
   * 
   * @return
   */
  public String submit() {
    return SUCCESS;
  }

  public String changePax() {
    Employee user = getUser();

    Plan plan = bookingService.roGetPlanDetail(recordNo);
    double defaultPrice = 0d;
    canSplitList = getCodeList("ebiz_can_split");

    // 2008年7月启动 只显示月结客户
    String payment = "";
    Calendar cal = Calendar.getInstance();
    cal.setTime(plan.getOutDate());
    if (cal.get(Calendar.YEAR) >= 2008 && cal.get(Calendar.MONTH) > 5)
      payment = "M";

    // 得到当前操作人的联系代理商列表
    // if (auth.contains(EbizCommon.ROLE_SALES)
    // && !auth.contains(EbizCommon.ROLE_OPERATOR))
    // {
    agentList = bookingService.getAgentBySales(user.getUserId(), "");
    // } else if (book.getRegion() != null)
    // {
    // agentList = bookingService.roGetAgentByArea(book.getRegion(),
    // payment);
    // }

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

  public Booking getBook() {
    return book;
  }

  public void setBook(Booking book) {
    this.book = book;
  }

  public int getPaxSum() {
    return paxSum;
  }

  public void setPaxSum(int paxSum) {
    this.paxSum = paxSum;
  }

  public List<Tourist> getCustomerList() {
    return customerList;
  }

  public void setCustomerList(List<Tourist> customerList) {
    this.customerList = customerList;
  }

  public List<LabelValueBean> getSexList() {
    return sexList;
  }

  public void setSexList(List<LabelValueBean> sexList) {
    this.sexList = sexList;
  }

  public List<LabelValueBean> getBirthPlaceList() {
    return birthPlaceList;
  }

  public void setBirthPlaceList(List<LabelValueBean> birthPlaceList) {
    this.birthPlaceList = birthPlaceList;
  }

  public List<LabelValueBean> getPassportPlaceList() {
    return passportPlaceList;
  }

  public void setPassportPlaceList(List<LabelValueBean> passportPlaceList) {
    this.passportPlaceList = passportPlaceList;
  }

  public List<LabelValueBean> getRoomTypeList() {
    return roomTypeList;
  }

  public void setRoomTypeList(List<LabelValueBean> roomTypeList) {
    this.roomTypeList = roomTypeList;
  }

  public List<LabelValueBean> getCanSplitList() {
    return canSplitList;
  }

  public void setCanSplitList(List<LabelValueBean> canSplitList) {
    this.canSplitList = canSplitList;
  }

  public List<LabelValueBean> getAgentList() {
    return agentList;
  }

  public void setAgentList(List<LabelValueBean> agentList) {
    this.agentList = agentList;
  }

  public List<LabelValueBean> getRegions() {
    return regions;
  }

  public void setRegions(List<LabelValueBean> regions) {
    this.regions = regions;
  }

  public List<Employee> getSalesmans() {
    return salesmans;
  }

  public List<LabelValueBean> getPriceList() {
    return priceList;
  }

  public void setPriceList(List<LabelValueBean> priceList) {
    this.priceList = priceList;
  }

  public List<LabelValueBean> getConfirmStatusList() {
    return confirmStatusList;
  }

  public void setConfirmStatusList(List<LabelValueBean> confirmStatusList) {
    this.confirmStatusList = confirmStatusList;
  }

  public String[] getSelects() {
    return selects;
  }

  public void setSelects(String[] selects) {
    this.selects = selects;
  }

}
