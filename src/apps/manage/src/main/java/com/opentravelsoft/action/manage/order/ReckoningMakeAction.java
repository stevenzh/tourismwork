package com.opentravelsoft.action.manage.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.common.KeyParams;
import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Line;
import com.opentravelsoft.entity.LineDescription;
import com.opentravelsoft.entity.LineSchedule;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.finance.Reckoning;
import com.opentravelsoft.entity.finance.ReckoningAcct;
import com.opentravelsoft.service.finance.ReckoningService;
import com.opentravelsoft.service.order.BookingService;
import com.opentravelsoft.service.product.LineScheduleService;
import com.opentravelsoft.service.product.LineTraitService;
import com.opentravelsoft.util.Arith;
import com.opentravelsoft.util.RowDataUtil;

/**
 * 帐单制作
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:53 $
 */

public class ReckoningMakeAction extends ManageAction {
  private static final long serialVersionUID = -7852853734687360320L;

  @Autowired
  private BookingService bookingService;

  @Autowired
  private ReckoningService reckoningService;

  @Autowired
  private LineScheduleService lineScheduleService;

  @Autowired
  private LineTraitService lineTraitService;

  private List<ReckoningAcct> reckoningAcctList = new ArrayList<ReckoningAcct>();

  private List<Reckoning> reckoningList = new ArrayList<Reckoning>();

  /** 线路行程 */
  private List<LineSchedule> scheduleList = new ArrayList<LineSchedule>();

  /** 线路费用包含 */
  private List<LineDescription> expenseList = new ArrayList<LineDescription>();

  /** 出行警示 */
  private List<LineDescription> alertList = new ArrayList<LineDescription>();

  /** 重要条款 */
  private List<LineDescription> ruleList = new ArrayList<LineDescription>();

  /** 是否为散客或团体 */
  private String nameKey;

  /** 是否为国内短线 */
  private String isShort = "N";

  /** 判断是否已打印 */
  private String isPrint = "N";

  /** 订单号 */
  private String reserveNo;

  private int reckoningId;

  private int version;

  private Booking book = new Booking();

  private Reckoning reckoning = new Reckoning();

  private int itemId = 0;

  private int userId;

  // ------------------------------------------------------------------------------
  /**
   * 帐单制作初始化
   * 
   * @return
   */
  public String makeInput() {
    book = bookingService.roGetReserve(reserveNo);
    nameKey = book.getNameKey();

    // 判断是否为国内短线
    Line route = new Line();
    route = reckoningService.getLineInfo(book.getPlan().getLine().getLineNo());
    if (null == route)
      isShort = "Y";

    if ("N".equals(isShort)) {
      reckoning.setClient(book.getCustomer().getName());
      reckoning.setOutDate(book.getPlan().getOutDate());
      reckoning.setRouteName(book.getPlan().getLine().getLineName());
      reckoning.setTourNo(book.getPlan().getTourNo());
      reckoning.setContact(book.getContact());
      reckoning.setPhone(book.getPhone());
      reckoning.setPax(book.getPax());

      if (null != reckoning.getPrintDate())
        reckoning.setPDate(SDF.format(reckoning.getPrintDate()));
      else
        reckoning.setPDate("尚未打印");
      if (null != reckoning.getCreateDate())
        reckoning.setCDate(SDF.format(reckoning.getCreateDate()));
      else
        reckoning.setCDate("尚未生成");

      if ("A".equals(nameKey)) {
        reckoningAcctList = reckoningService.roGetCustomerList(reserveNo);
        if (!(reckoningAcctList.isEmpty())) {
          BigDecimal amount = new BigDecimal(0);
          for (ReckoningAcct obj : reckoningAcctList) {
            amount = amount.add(obj.getAmount());
          }
          reckoning.setAmount(Arith.round(amount, 2));

        }
      } else {
        Plan tour = reckoningService.roGetTourInfo(book.getPlan().getTourNo());
        if (null != tour)
          reckoning.setLeaderPax(RowDataUtil.getInt(tour.getLeaderPax()));

        if (reckoningAcctList.isEmpty()) {
          ReckoningAcct eckoningAcct = new ReckoningAcct();
          eckoningAcct.setItemId(1);
          reckoningAcctList.add(eckoningAcct);
        }
      }
    } else if ("Y".equals(isShort)) {
      scheduleList = lineScheduleService.getLineSchedule(book.getPlan()
          .getLine().getLineNo());
      expenseList = lineTraitService.roGetExpense(book.getPlan().getLine()
          .getLineNo());
      alertList = lineTraitService.getLineTrait(book.getPlan().getLine()
          .getLineNo(), KeyParams.EBIZ_TYPE_LINE_ALERT);
      ruleList = lineTraitService.getLineTrait(book.getPlan().getLine()
          .getLineNo(), KeyParams.EBIZ_TYPE_LINE_RULE);

      reckoning.setClient(book.getCustomer().getName());
      reckoning.setOutDate(book.getPlan().getOutDate());
      reckoning.setRouteName(book.getPlan().getLine().getLineName());
      reckoning.setPax(book.getPax());

    }
    if (!reckoningAcctList.isEmpty()) {

      for (ReckoningAcct obj : reckoningAcctList) {
        obj.setDescription("团费");
        obj.setUnit("元");
        obj.setUnitPrice(book.getCustomerList().get(0).getResidual());
        obj.setCount(book.getPax());
        obj.setAmount(obj.getUnitPrice().multiply(
            new BigDecimal(reckoning.getPax())));
        reckoning.setAmount(obj.getAmount());
      }

    }

    return SUCCESS;
  }

  // ------------------------------------------------------------------------------
  /**
   * 显示带历史记录的帐单
   * 
   * @return
   */
  public String detailInput() {
    Employee user = getUser();
    reckoningList = reckoningService.roGetReckoning(reserveNo);
    if (null != reckoningList && !(reckoningList.isEmpty())) {
      for (Reckoning obj : reckoningList) {
        if (version < obj.getVersion()) {
          reckoningId = obj.getReckoningId();
          version = obj.getVersion();
        }
      }

      book = bookingService.roGetReserve(reserveNo);
      nameKey = book.getNameKey();

      // 判断是否为国内短线
      Line route = new Line();
      route = reckoningService
          .getLineInfo(book.getPlan().getLine().getLineNo());
      if (null == route)
        isShort = "Y";
      //

      if ("N".equals(isShort)) {
        for (Reckoning obj : reckoningList) {
          if (version == obj.getVersion()) {
            reckoning = obj;
          }
        }
        reckoningList.remove(reckoning);

        reckoning = reckoningService.roGetReckoningInfo(reckoningId);

        userId = user.getUserId();
        reckoning.setClient(book.getCustomer().getName());
        reckoning.setOutDate(book.getPlan().getOutDate());
        reckoning.setRouteName(book.getPlan().getLine().getLineName());
        reckoning.setTourNo(book.getPlan().getTourNo());
        reckoning.setPax(book.getPax());

        if (null != reckoning.getPrintDate())
          reckoning.setPDate(SDF.format(reckoning.getPrintDate()));
        else
          reckoning.setPDate("尚未打印");

        if ("A".equals(nameKey)) {
          reckoningAcctList = reckoningService.roGetCustomerList(reserveNo);
          if (!(reckoningAcctList.isEmpty())) {
            BigDecimal amount = new BigDecimal(0);
            for (ReckoningAcct obj : reckoningAcctList) {
              amount = amount.add(obj.getAmount());
            }
            reckoning.setAmount(Arith.round(amount, 2));
          }
        } else {
          Plan tour = reckoningService
              .roGetTourInfo(book.getPlan().getTourNo());
          if (null != tour)
            reckoning.setLeaderPax(tour.getLeaderPax());

          BigDecimal amount1 = new BigDecimal(0);
          for (int i = 0; i < reckoning.getReckoningAcctList().size(); i++) {
            amount1 = amount1.add(reckoning.getReckoningAcctList().get(i)
                .getAmount());
          }

          reckoning.setAmount(Arith.round(amount1, 2));
        }
      } else if ("Y".equals(isShort)) {

      }
    }

    return SUCCESS;
  }

  // ------------------------------------------------------------------------------
  /**
   * 显示单个帐单
   * 
   * @return
   */
  public String detail() {
    Employee user = getUser();
    book = bookingService.roGetReserve(reserveNo);
    nameKey = book.getNameKey();

    // 判断是否为国内短线
    Line route = new Line();
    route = reckoningService.getLineInfo(book.getPlan().getLine().getLineNo());
    if (null == route)
      isShort = "Y";
    //

    if ("N".equals(isShort)) {
      reckoning = reckoningService.roGetReckoningInfo(reckoningId);

      isPrint = "Y";
      userId = user.getUserId();
      reckoning.setClient(book.getCustomer().getName());
      reckoning.setOutDate(book.getPlan().getOutDate());
      reckoning.setRouteName(book.getPlan().getLine().getLineName());
      reckoning.setTourNo(book.getPlan().getTourNo());
      reckoning.setPax(book.getPax());

      if (null != reckoning.getPrintDate())
        reckoning.setPDate(SDF.format(reckoning.getPrintDate()));
      else
        reckoning.setPDate("尚未打印");

      if ("A".equals(nameKey)) {
        reckoningAcctList = reckoningService.roGetCustomerList(reserveNo);
        if (!(reckoningAcctList.isEmpty())) {
          BigDecimal amount = new BigDecimal(0);
          for (ReckoningAcct obj : reckoningAcctList) {
            amount = amount.add(obj.getAmount());
          }
          reckoning.setAmount(Arith.round(amount, 2));
        }
      } else {
        Plan tour = reckoningService.roGetTourInfo(book.getPlan().getTourNo());
        if (null != tour)
          reckoning.setLeaderPax(tour.getLeaderPax());

        BigDecimal amount1 = new BigDecimal(0);
        for (int i = 0; i < reckoning.getReckoningAcctList().size(); i++) {
          amount1 = amount1.add(reckoning.getReckoningAcctList().get(i)
              .getAmount());
        }

        reckoning.setAmount(Arith.round(amount1, 2));
      }
    } else if ("Y".equals(isShort)) {

    }

    return SUCCESS;
  }

  // ------------------------------------------------------------------------------
  /**
   * 制作帐单
   * 
   * @return
   */
  public String make() {
    Employee user = getUser();

    if ("N".equals(isShort.trim())) {

      if ("A".equals(nameKey.trim()))
        reckoning.setTourType("2");
      else
        reckoning.setTourType("1");

      reckoning.setBookingNo(reserveNo.trim());
      reckoning.setCreatedBy(user.getUserId());
      reckoning.setUserDept("");
      reckoning.setReckoningAcctList(reckoningAcctList);
      reckoning = reckoningService.txWholeReckoningMake(reckoning);

    } else {
      reckoning.setTourType("3");

      reckoning.setBookingNo(reserveNo.trim());
      reckoning.setCreatedBy(user.getUserId());
      reckoning.setUserDept("");
      reckoning = reckoningService.txWholeReckoningMake(reckoning);
    }

    if (!("".equals(reckoning.getReckoningId())))
      addActionMessage("帐单制作成功！");
    reckoningId = reckoning.getReckoningId();

    return SUCCESS;
  }

  // -----------------------------------------------------------------------------
  /**
   * 修改帐单初始化
   * 
   * @return
   */
  public String modifyInput() {
    reckoning = reckoningService.roGetReckoningInfo(reckoningId);
    // 判断是否已打印
    if (reckoning.getPrintedCount() == 0)
      isPrint = "N";
    else
      isPrint = "Y";

    book = bookingService.roGetReserve(reserveNo);
    nameKey = book.getNameKey();

    // 判断是否为国内短线
    Line route = new Line();
    route = reckoningService.getLineInfo(book.getPlan().getLine().getLineNo());
    if (null == route)
      isShort = "Y";
    // ------------------------------------------------------------------

    if ("N".equals(isShort)) {
      reckoning.setClient(book.getCustomer().getName());
      reckoning.setOutDate(book.getPlan().getOutDate());
      reckoning.setRouteName(book.getPlan().getLine().getLineName());
      reckoning.setTourNo(book.getPlan().getTourNo());
      reckoning.setPax(book.getPax());

      if (null != reckoning.getPrintDate())
        reckoning.setPDate(SDF.format(reckoning.getPrintDate()));
      else
        reckoning.setPDate("尚未打印");

      if ("A".equals(nameKey)) {
        reckoningAcctList = reckoningService.roGetCustomerList(reserveNo);
        if (!(reckoningAcctList.isEmpty())) {
          BigDecimal amount = new BigDecimal(0);
          for (ReckoningAcct obj : reckoningAcctList) {
            amount = amount.add(obj.getAmount());
          }
          reckoning.setAmount(Arith.round(amount, 2));
        }
      } else {
        Plan tour = reckoningService.roGetTourInfo(book.getPlan().getTourNo());
        if (null != tour)
          reckoning.setLeaderPax(tour.getLeaderPax());

        BigDecimal amount1 = new BigDecimal(0);
        for (int i = 0; i < reckoning.getReckoningAcctList().size(); i++) {
          amount1 = amount1.add(reckoning.getReckoningAcctList().get(i)
              .getAmount());
        }

        reckoning.setAmount(Arith.round(amount1, 2));
        reckoningAcctList = reckoning.getReckoningAcctList();
      }// -------------------------------------------------------------
    } else if ("Y".equals(isShort)) {

    }

    return SUCCESS;
  }

  // ------------------------------------------------------------------------------
  /**
   * 修改帐单
   * 
   * @return
   */
  public String modify() {
    Employee user = getUser();
    if ("Y".equals(isShort.trim()))

      reckoning.setTourType("3");
    else if ("A".equals(isShort.trim()))
      reckoning.setTourType("2");
    else
      reckoning.setTourType("1");

    int ret = 0;

    reckoning.setReckoningId(reckoningId);
    reckoning.setBookingNo(reserveNo);
    reckoning.setUpdatedBy(user.getUserId());
    reckoning.setReckoningAcctList(reckoningAcctList);
    ret = reckoningService.txWholeReckoningModify(reckoning);
    if (ret == 0)
      addActionMessage("帐单修改成功！");
    else
      addActionMessage("帐单修改失败！");

    return SUCCESS;
  }

  // ------------------------------------------------------------------------------
  /**
   * 增加行
   * 
   * @return
   */
  public String add() {
    int id = 0;
    for (int i = 0; i < reckoningAcctList.size(); i++) {
      if (reckoningAcctList.get(i).getItemId() > id)
        id = reckoningAcctList.get(i).getItemId();
    }

    ReckoningAcct reckoningAcct = new ReckoningAcct();
    reckoningAcct.setItemId(id + 1);
    reckoningAcctList.add(reckoningAcct);

    return SUCCESS;
  }

  // ------------------------------------------------------------------------------
  /**
   * 删除行
   * 
   * @return
   */
  public String delete() {
    ReckoningAcct reckoningAcct = new ReckoningAcct();
    for (ReckoningAcct obj : reckoningAcctList) {

      if (obj.getItemId() == itemId) {

        // 删除行
        for (int i = itemId; i < reckoningAcctList.size(); i++) {
          reckoningAcct = reckoningAcctList.get(i);
          reckoningAcct.setItemId(i);
          reckoningAcctList.set(i, reckoningAcct);
        }
        BigDecimal amount = new BigDecimal(0);
        amount = reckoning.getAmount().subtract(obj.getAmount());
        reckoning.setAmount(amount);
        reckoningAcctList.remove(obj);
        break;
      }
    }
    if (reckoningAcctList.isEmpty()) {
      ReckoningAcct eckoningAcct = new ReckoningAcct();
      eckoningAcct.setItemId(1);
      reckoningAcctList.add(eckoningAcct);
    }

    return SUCCESS;
  }

  public String getNameKey() {
    return nameKey;
  }

  public void setNameKey(String nameKey) {
    this.nameKey = nameKey;
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

  public String getIsShort() {
    return isShort;
  }

  public void setIsShort(String isShort) {
    this.isShort = isShort;
  }

  public Reckoning getReckoning() {
    return reckoning;
  }

  public void setReckoning(Reckoning reckoning) {
    this.reckoning = reckoning;
  }

  public List<ReckoningAcct> getReckoningAcctList() {
    return reckoningAcctList;
  }

  public void setReckoningAcctList(List<ReckoningAcct> reckoningAcctList) {
    this.reckoningAcctList = reckoningAcctList;
  }

  public List<Reckoning> getReckoningList() {
    return reckoningList;
  }

  public void setReckoningList(List<Reckoning> reckoningList) {
    this.reckoningList = reckoningList;
  }

  public int getItemId() {
    return itemId;
  }

  public void setItemId(int itemId) {
    this.itemId = itemId;
  }

  public int getReckoningId() {
    return reckoningId;
  }

  public void setReckoningId(int reckoningId) {
    this.reckoningId = reckoningId;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public List<LineSchedule> getScheduleList() {
    return scheduleList;
  }

  public void setScheduleList(List<LineSchedule> scheduleList) {
    this.scheduleList = scheduleList;
  }

  public List<LineDescription> getExpenseList() {
    return expenseList;
  }

  public void setExpenseList(List<LineDescription> expenseList) {
    this.expenseList = expenseList;
  }

  public List<LineDescription> getAlertList() {
    return alertList;
  }

  public void setAlertList(List<LineDescription> alertList) {
    this.alertList = alertList;
  }

  public List<LineDescription> getRuleList() {
    return ruleList;
  }

  public void setRuleList(List<LineDescription> ruleList) {
    this.ruleList = ruleList;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  public String getIsPrint() {
    return isPrint;
  }

  public void setIsPrint(String isPrint) {
    this.isPrint = isPrint;
  }

}
