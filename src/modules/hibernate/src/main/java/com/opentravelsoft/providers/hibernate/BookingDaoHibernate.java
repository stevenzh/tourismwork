package com.opentravelsoft.providers.hibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.opentravelsoft.EbizException;
import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.Customer;
import com.opentravelsoft.entity.Pinyin;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.ShareFlight;
import com.opentravelsoft.entity.Tourist;

import com.opentravelsoft.providers.BookingDao;
import com.opentravelsoft.util.RowDataUtil;
import com.opentravelsoft.util.StringUtil;

@Repository("BookingDao")
public class BookingDaoHibernate extends GenericDaoHibernate<Booking, String>
    implements BookingDao {

  public BookingDaoHibernate() {
    super(Booking.class);
  }

  protected final Log log = LogFactory.getLog(getClass());

  @SuppressWarnings("unchecked")
  public int saveBooking(Booking book, List<Tourist> customers, boolean isHold) {
    HibernateTemplate template = getHibernateTemplate();
    Date sysdate = getSysdate();

    boolean manager = isHold;
    boolean place = false;
    // 是否可以成团 订单的人数大于最小成团人数
    boolean canBuild = false;

    Plan plan = (Plan) template.load(Plan.class, book.getPlan().getPlanNo(),
        LockMode.READ);
    book.setPlan(plan);

    // 是否该线路专管员
    if (plan.getAssigned().getUserId() == book.getReserve())
      manager = true;

    if (manager) {
      template.lock(plan, LockMode.PESSIMISTIC_WRITE);
      // 计划名额
      int _pax1 = plan.getPax1();
      Object[] params1 = { book.getBookingNo() };

      StringBuilder sql = new StringBuilder();
      sql.append("select sum(confirmPax) ");
      sql.append("from Booking ");
      sql.append("where plan.planNo=? and cfmKey='1' and delkey='N' ");

      List<Object> list1 = template.find(sql.toString(), params1);
      int pax2 = RowDataUtil.getShort(list1.get(0));

      if (pax2 + customers.size() < _pax1) {
        pax2 = pax2 + customers.size();
        // 已订名额
        plan.setPax2(pax2);
        // 可用名额
        plan.setPax3(plan.getPax1() - pax2 - plan.getPax4());

        if (pax2 >= plan.getPax5())
          canBuild = true;

        // 修改共享资源里的可用名额
        // this.share(book, plan, pax2);
        if (plan.getShareFlightId() != null) {

          ShareFlight tsf = (ShareFlight) template.get(ShareFlight.class,
              plan.getShareFlightId());
          if (tsf != null) {
            tsf.setHandle(tsf.getHandle() - pax2 - plan.getPax4());
            template.update(tsf);
          }
        }
        plan.setIsEnter(1);
        template.update(plan);
        place = true;
      }
    }

    //
    book.setTourKey('3');
    if (place) {
      // 确认人数
      book.setConfirmPax(book.getPax());
    } else {
      // 确认人数
      book.setConfirmPax(0);
    }

    // A-个人,B-团体,C-商务团,D-代理商
    book.setNamekey("D");
    // 取消标志
    book.setDelkey("N");
    // 已付帐款
    book.setCramt(new BigDecimal(0));
    // 是否配送
    // tfj006.setExpresskey(book.getExpressKey());

    // 团队确认状态 1-团确 2-团候
    if (place) {
      book.setCfmKey("1");
      // 阅读时间
      book.setReadDate(sysdate);
      // 是否以阅读
      book.setReadKey("Y");
      // 阅读人
      book.setReadUser(book.getReserve());
    } else {
      book.setCfmKey(book.getCfmKey());
      // 是否以阅读
      book.setReadKey("N");
    }

    book.setCustomer(template.get(Customer.class, book.getCustomer()
        .getCustomerId()));
    template.save(book);

    supplyYin(customers);
    for (Tourist trip : customers) {
      // 订单
      trip.setBooking(book);
      // 记录类型
      trip.setRecType('A');
      // 证件种类
      // tourist.setCardty("");
      // 是否办护照
      // tourist.setHzKey("");
      // 应收团款
      trip.setAmt01(trip.getReceivables());
      // 优惠申请
      trip.setAmt02(new BigDecimal(0));
      // 已收团款
      trip.setAmt03(trip.getAmount());
      // 已退团款
      trip.setAmt04(new BigDecimal(0));
      // 重点客人否
      trip.setVipkey('N');
      // 备注
      trip.setRemark(" ");
      // 同行人数
      trip.setPaxnum(0);
      // 住房要求
      trip.setRoomKey(trip.getRoomType());
      // 住房序号
      trip.setRoomNo(0);
      // 同房序号
      trip.setRmNum(trip.getRoomNo());
      // 是否同意与他人同住
      trip.setRoomKey1('Y');
      // 操作人
      trip.setOpuser(book.getOpuser());
      // 分团标志
      trip.setTourKey('N');
      // 领队标志
      trip.setLeaderKey("N");
      // 送签登记表号
      // tfj007.setIvsNo(" ");
      // 办签状态
      trip.setVisaKey(" ");
      // 签证号码
      // tfj007.setVisaNo(" ");
      // 签证日期
      // tfj007.setVisaDsc(" ");
      // 名单发送标志
      // tfj007.setPlcKey('N');
      // 户口所在地区
      // tfj007.setHkdq(" ");

      template.save(trip);
    }

    return 0;
  }

  /**
   * 网站用户使用<br>
   * 会员直接预订旅游产品
   */
  public String addBooking(Booking book, List<Tourist> customerList) {
    // Booking
    Booking tblbook = new Booking();
    tblbook.setBookingNo(book.getBookingNo());
    // tblbook.setMemberId(userId);
    tblbook.setPax(book.getPax());
    tblbook.setPlan(book.getPlan());
    tblbook.setCfmKey("R");
    getHibernateTemplate().save(tblbook);

    for (Tourist customer : customerList) {
      // Member
      if (!customer.isExist()) {
        Tourist member = new Tourist();
        member.setUid(customer.getUid());
        member.setUserName(customer.getUserName());
        member.setIdCard(customer.getIdCard());
        member.setSex(customer.getSex());
        member.setMemberKey("N");
        getHibernateTemplate().save(member);
      }
    }

    return book.getBookingNo();
  }

  @SuppressWarnings("unchecked")
  public Booking getBooking(String bookingNo) {
    StringBuilder sql = new StringBuilder();

    Object[] params = { bookingNo };
    Booking book = (Booking) getHibernateTemplate().get(Booking.class,
        bookingNo);

    sql = new StringBuilder();
    sql.append("from Tourist where booking.nameNo=?");
    List<Tourist> customerList = getHibernateTemplate().find(sql.toString(),
        params);
    int id = 0;
    for (Tourist trip : customerList) {
      //
      trip.setId(id++);
      // 身份证号
      trip.setIdCard(RowDataUtil.getString(trip.getCard()));
      // 住房要求
      trip.setRoomType(RowDataUtil.getString(trip.getRoomKey()));
      // 住房序号
      trip.setRoomNo(RowDataUtil.getInt(trip.getRmNum()));
      // 应收团款
      trip.setReceivables(trip.getAmt01());
      // 已收团款
      // trip.setAmount(RowDataUtil.getDouble(trip.getAmt03()));
    }

    book.setCustomerList(customerList);

    return book;
  }

  @SuppressWarnings("unchecked")
  public int cancelBook(Booking book, String note) {
    HibernateTemplate template = getHibernateTemplate();
    int zero = 0;
    Booking tfj006 = (Booking) template.load(Booking.class,
        book.getBookingNo(), LockMode.PESSIMISTIC_WRITE);

    // 取消标志
    tfj006.setDelkey("Y");
    // 预订人数
    // tfj006.setPax(0);
    // 确认人数
    tfj006.setConfirmPax(zero);
    tfj006.setDbamt(new BigDecimal(0));
    tfj006.setCramt(new BigDecimal(0));
    // tfj006.setReadKey('N');
    // 最后修改人
    tfj006.setOpuser(book.getOpuser());

    template.update(tfj006);
    // ---------------------------------------------------------------------

    StringBuilder sql = new StringBuilder();
    Object[] params = { book.getBookingNo() };
    sql.append("from Tourist where booking.nameNo=? ");
    List<Tourist> list = template.find(sql.toString(), params);

    for (Tourist tourist : list) {
      // 取消标志
      tourist.setDel('Y');
      // 操作人
      tourist.setOpuser(book.getOpuser());

      template.update(tourist);
    }

    // ---------------------------------------------------------------------

    if (tfj006.getCfmKey().equals("1")) {
      StringBuilder query = new StringBuilder();
      query.append("select sum(confirmPax) ");
      query.append("from Booking ");
      query.append("where plan.planNo=? and cfmKey='1' ");
      query.append("and delkey='N' ");

      Object[] param = { book.getPlan().getPlanNo() };
      List<Object> bookList = getHibernateTemplate().find(query.toString(),
          param);
      int pax2 = RowDataUtil.getShort(bookList.get(0));

      Plan plan = (Plan) getHibernateTemplate().load(Plan.class,
          book.getPlan().getPlanNo(), LockMode.PESSIMISTIC_WRITE);
      // 已订名额
      plan.setPax2(pax2);
      // 可用名额
      plan.setPax3(plan.getPax1() - pax2 - plan.getPax4());
      // 修改共享资源
      if (plan.getShareFlightId() != null) {
        ShareFlight tsf = (ShareFlight) getHibernateTemplate().get(
            ShareFlight.class, plan.getShareFlightId());
        if (tsf != null) {
          tsf.setHandle(tsf.getHandle() + pax2 - plan.getPax4());
          getHibernateTemplate().update(tsf);
        }
      }
      getHibernateTemplate().save(plan);
    }

    return 0;
  }

  @SuppressWarnings("unchecked")
  public int cancelCustomers(Booking book, Set<String> customers, String note) {
    HibernateTemplate template = getHibernateTemplate();

    StringBuilder sql = new StringBuilder();
    Object[] params = { book.getBookingNo() };
    sql.append("from Tourist where booking.nameNo=? ");
    List<Tourist> list = template.find(sql.toString(), params);

    // 确认人数
    int confirmPax = 0;
    StringBuilder customer = new StringBuilder();

    for (Tourist tfj007 : list) {
      if (customers.contains(tfj007.getNmno())) {
        customer.append(tfj007.getUserName() + ",");
        // 取消标志
        tfj007.setDel('Y');
        // 操作人
        tfj007.setOpuser(book.getOpuser());
        tfj007.setAmt01(new BigDecimal(0));
        tfj007.setAmt03(new BigDecimal(0));
        template.update(tfj007);
      }

      if (tfj007.getDel() == 'N')
        confirmPax++;
    }

    // ---------------------------------------------------------------------
    Booking tfj006 = (Booking) template.load(Booking.class,
        book.getBookingNo(), LockMode.PESSIMISTIC_WRITE);

    if (tfj006.getCfmKey().equals("1")) {
      // 确认人数
      tfj006.setConfirmPax(confirmPax);
    }
    // 预订人数
    tfj006.setPax(list.size());
    if (confirmPax == 0) {
      // 取消标志
      tfj006.setDelkey("Y");
    }
    // tfj006.setReadKey('N');
    // 最后修改人
    tfj006.setOpuser(book.getOpuser());

    template.update(tfj006);
    // ---------------------------------------------------------------------
    if (tfj006.getCfmKey().equals("1")) {
      StringBuilder query = new StringBuilder();
      query.append("select sum(confirmPax) ");
      query.append("from Booking ");
      query.append("where plan.planNo=? and cfmKey='1' ");
      query.append("and delkey='N' ");

      Object[] param = { book.getPlan().getPlanNo() };
      List<Object> bookList = getHibernateTemplate().find(query.toString(),
          param);
      int pax2 = RowDataUtil.getShort(bookList.get(0));

      Plan plan = (Plan) getHibernateTemplate().load(Plan.class,
          tfj006.getPlan().getPlanNo(), LockMode.PESSIMISTIC_WRITE);
      // 已订名额
      plan.setPax2(pax2);
      // 可用名额
      plan.setPax3(plan.getPax1() - pax2 - plan.getPax4());
      // 修改共享资源
      if (plan.getShareFlightId() != null) {
        ShareFlight tsf = (ShareFlight) getHibernateTemplate().get(
            ShareFlight.class, plan.getShareFlightId());
        if (tsf != null) {
          tsf.setHandle(tsf.getHandle() + pax2 - plan.getPax4());
          getHibernateTemplate().update(tsf);
        }
      }
      getHibernateTemplate().update(plan);
    }

    return 0;
  }

  @SuppressWarnings("unchecked")
  public int updateBooking(Booking inbook, List<Tourist> customers, String note)
      throws EbizException {
    HibernateTemplate template = getHibernateTemplate();
    String confirm = inbook.getCfmKey();
    int realPax = 0;

    // 更新计划人数
    if (confirm.equals("1")) {
      Plan plan = (Plan) template.get(Plan.class, inbook.getPlan().getPlanNo(),
          LockMode.PESSIMISTIC_WRITE);
      if (null == plan)
        throw new EbizException("plan is not find.");

      // 计划名额
      int _pax1 = plan.getPax1();

      StringBuilder sql = new StringBuilder();
      sql.append("select sum(confirmPax) ");
      sql.append("from Booking ");
      sql.append("where plan.planNo=? and cfmKey ='1' ");
      sql.append("and delkey='N' and nameNo!='" + inbook.getBookingNo() + "'");

      Object[] param = { inbook.getPlan().getPlanNo() };
      List<Object> list = template.find(sql.toString(), param);
      int pax2 = RowDataUtil.getShort(list.get(0));

      // customers 包含已取消客人
      for (Tourist obj : customers) {
        if (obj.getDel() == 'N')
          realPax++;
      }

      if (pax2 + realPax > plan.getPax1()) {
        log.error("计划名额已满,订单审核失败.booking_no=" + inbook.getBookingNo());
        return -1;
      }

      pax2 = pax2 + realPax;
      // 已订名额
      plan.setPax2(pax2);
      // 可用名额
      plan.setPax3(plan.getPax1() - pax2 - plan.getPax4());
      if (plan.getShareFlightId() != null) {
        ShareFlight tsf = (ShareFlight) template.get(ShareFlight.class,
            plan.getShareFlightId());
        if (tsf != null) {
          tsf.setHandle(tsf.getSeating() - pax2 - plan.getPax4());
          template.update(tsf);
        }
      }
      template.update(plan);
    }

    Booking book = (Booking) template.load(Booking.class,
        inbook.getBookingNo(), LockMode.PESSIMISTIC_WRITE);

    // 联系人
    book.setContact(inbook.getContact());
    // 联系方式
    book.setTel(inbook.getTel());
    //
    book.setTourKey(inbook.getCanSplit());
    // 人数
    book.setPax(customers.size());

    if (confirm.equals("1")) {
      // 确认人数
      book.setConfirmPax(realPax);
    }

    // 客户
    book.getCustomer().setCustomerId(inbook.getCustomer().getCustomerId());
    // A-个人,B-团体,C-商务团,D-代理商
    book.setNamekey("D");
    // 取消标志
    book.setDelkey("N");
    // 销售员
    book.setSalesman(inbook.getSalesman());
    // 应收帐款
    book.setDbamt(inbook.getDbamt());
    //
    // tfj006.setCramt(0d);
    // 团队确认状态 1-团确 2-团候
    // tfj006.setCfmKey(book.getConfirmStatus().charAt(0));
    //
    // tfj006.setCptNo(book.getReserveNo());
    // 预订操作人
    // tfj006.setReceive(book.getReserveUser());
    // 备注
    book.setRemarks(inbook.getRemarks());
    // 最后修改人
    book.setOpuser(inbook.getOpuser());

    // 此处用于修改提示
    // tfj006.setReadKey('N');
    template.update(book);
    // ---------------------------------------------------------------------
    StringBuilder sql = new StringBuilder();
    Object[] params = { inbook.getBookingNo() };
    sql.append("from Tourist where booking.nameNo=? ");
    List<Tourist> list = template.find(sql.toString(), params);

    // 更新原有客人
    for (Tourist tourist : list) {
      Tourist trip = null;
      for (int i = 0; i < customers.size(); i++) {
        if (customers.get(i).getNmno().equals(tourist.getNmno()))
          trip = customers.get(i);
      }
      if (null == trip)
        continue;

      template.lock(tourist, LockMode.PESSIMISTIC_WRITE);

      // 名单号
      tourist.setNmno(trip.getNmno());
      // 报名单号
      tourist.setBooking(book);

      // -----------------------------------------------------------------
      // 姓名
      tourist.setUserName(trip.getUserName());
      // 汉语拼音
      tourist.setPinYin(trip.getPinYin());
      // 证件种类
      // tfj007.setCardty(' ');
      // 证件号码
      tourist.setCard(trip.getIdCard());
      // 性别
      tourist.setSex(trip.getSex());
      // 出生日期
      tourist.setBirthday(trip.getBirthday());
      // 出生地
      tourist.setBirthplace(trip.getBirthplace());

      // -----------------------------------------------------------------
      // 护照号
      tourist.setPassportNo(trip.getPassportNo());
      // 因私护照国籍
      // tfj007.setCountry(" ");
      // 发照日期
      tourist.setPassportDate(trip.getPassportDate());
      // 发照地
      tourist.setPassportPlace(trip.getPassportPlace());
      // 护照有效期
      tourist.setPassportExpiry(trip.getPassportExpiry());

      // -----------------------------------------------------------------
      // 线路报价
      tourist.setPrice(trip.getPrice());
      // 应收团款
      tourist.setAmt01(trip.getReceivables());
      // 优惠申请
      // tfj007.setAmt02(0d);
      // 已收团款
      tourist.setAmt03(trip.getAmount());
      // 已退团款
      // tfj007.setAmt04(0d);
      // 重点客人否
      // tfj007.setVipkey('N');
      // 备注
      tourist.setRemark(trip.getRemarks());
      // 参加旅行团次数
      // tfj007.setTrvnum(0);
      // 住房序号
      // tfj007.setRoomNo(0);
      // 住房要求
      tourist.setRoomKey(trip.getRoomType());
      // 同房序号
      tourist.setRmNum(trip.getRoomNo());
      // 取消标志
      // tfj007.setDel('N');
      // 操作人
      tourist.setOpuser(inbook.getOpuser());
      // 领队标志
      // tfj007.setLeaderKey("N");
      // 签证费
      // tfj007.setVisaAmt(0d);
      // 办签状态
      // tfj007.setVisaKey(" ");
      // 签证号码
      // tfj007.setVisaNo(" ");
      // 签证日期
      // tfj007.setVisaDsc(" ");

      template.update(tourist);
    }

    supplyYin(customers);

    // 保存新客人
    for (Tourist trip : customers) {
      if (trip.getNewFlag().equals("N"))
        continue;

      Tourist tourist = new Tourist();
      // 名单号
      tourist.setNmno(trip.getNmno());
      // 报名单号
      tourist.setBooking(book);
      // 记录类型
      tourist.setRecType('A');

      // -----------------------------------------------------------------
      // 姓名
      tourist.setUserName(trip.getUserName());
      // 汉语拼音
      tourist.setPinYin(trip.getPinYin());
      // 证件种类
      // tourist.setCardty("");
      // 证件号码
      tourist.setCard(trip.getIdCard());
      // 性别
      tourist.setSex(trip.getSex());
      // 出生日期
      tourist.setBirthday(trip.getBirthday());
      // 出生地
      tourist.setBirthplace(trip.getBirthplace());
      // 手机
      // tourist.setMobile("");
      // -----------------------------------------------------------------
      // 是否办护照
      // tourist.setHzKey("");
      // 护照种类
      tourist.setPassportType(trip.getPassportType());
      // 护照号
      tourist.setPassportNo(trip.getPassportNo());
      // 因私护照国籍
      // tfj007.setCountry(" ");
      // 发照日期
      tourist.setPassportDate(trip.getPassportDate());
      // 发照地
      tourist.setPassportPlace(trip.getPassportPlace());
      // 护照有效期
      tourist.setPassportExpiry(trip.getPassportExpiry());

      // -----------------------------------------------------------------
      // 线路报价
      tourist.setPrice(trip.getPrice());
      // 应收团款
      tourist.setAmt01(trip.getReceivables());
      // 优惠申请
      tourist.setAmt02(new BigDecimal(0));
      // 已收团款
      tourist.setAmt03(trip.getAmount());
      // 已退团款
      tourist.setAmt04(new BigDecimal(0));
      // 重点客人否
      tourist.setVipkey('N');
      // 备注
      tourist.setRemark(" ");

      // 同行人数
      tourist.setPaxnum(0);
      // 住房要求
      tourist.setRoomKey(trip.getRoomType());
      // 住房序号
      tourist.setRoomNo(0);
      // 同房序号
      tourist.setRmNum(trip.getRoomNo());
      // 是否同意与他人同住
      tourist.setRoomKey1('Y');

      // 取消标志
      tourist.setDel('N');
      // 操作人
      tourist.setOpuser(inbook.getOpuser());
      // 分团标志
      tourist.setTourKey('N');
      // 领队标志
      tourist.setLeaderKey("N");
      // 送签登记表号
      // tfj007.setIvsNo(" ");
      // 签证费
      // tfj007.setVisaAmt(0d);
      // 办签状态
      tourist.setVisaKey(" ");
      // 签证号码
      // tfj007.setVisaNo(" ");
      // 签证日期
      // tfj007.setVisaDsc(" ");
      // 名单发送标志
      // tfj007.setPlcKey('N');
      // 户口所在地区
      // tfj007.setHkdq(" ");

      template.save(tourist);
    }
    return 0;
  }

  public int readBooking(Booking booking) {
    Date sysdate = getSysdate();
    Booking tfj006 = (Booking) getHibernateTemplate().get(Booking.class,
        booking.getBookingNo(), LockMode.PESSIMISTIC_WRITE);

    if (tfj006.getCfmKey().equals("2"))
      return -1;

    tfj006.setReadDate(sysdate);
    tfj006.setReadKey("Y");
    tfj006.setReadUser(booking.getOpuser());

    getHibernateTemplate().update(tfj006);

    return 0;
  }

  @SuppressWarnings("unchecked")
  public int confirm(Booking book) {
    Plan plan = (Plan) getHibernateTemplate().get(Plan.class,
        book.getPlan().getPlanNo(), LockMode.PESSIMISTIC_WRITE);
    // 已订名额
    int _pax2 = plan.getPax2();
    // 订单应收款
    BigDecimal expense = new BigDecimal(0);

    StringBuilder sql = new StringBuilder();
    sql.append("select sum(confirmPax) ");
    sql.append("from Booking ");
    sql.append("where plan.planNo=? and cfmKey='1' and delkey='N' ");
    sql.append("and nameNo<>? ");

    Object[] param = { book.getPlan().getPlanNo(), book.getBookingNo() };
    List<Object> list = getHibernateTemplate().find(sql.toString(), param);
    int pax2 = RowDataUtil.getShort(list.get(0));

    if (pax2 + book.getCustomerList().size() > plan.getPax1()) {
      log.error("计划名额已满,订单审核失败.booking_no=" + book.getBookingNo());
      return -1;
    }

    pax2 = (short) (pax2 + book.getCustomerList().size());

    Date sysdate = getSysdate();
    Booking tfj006 = (Booking) getHibernateTemplate().load(Booking.class,
        book.getBookingNo(), LockMode.PESSIMISTIC_WRITE);

    if (tfj006.getCfmKey().equals("1")) {
      log.warn("订单已经占位.booking_no=" + book.getBookingNo());

      // 标记已读
      if (null == tfj006.getReadKey() || tfj006.getReadKey().equals("N")) {
        tfj006.setReadKey("Y");
        tfj006.setReadDate(sysdate);
        tfj006.setReadUser(book.getOpuser());
        getHibernateTemplate().update(tfj006);
      }

    } else {
      // 数据检查 <-|
      // --------------------------------------------------------------------
      // 更新订单 |->

      // 标记已读
      if (null == tfj006.getReadKey() || tfj006.getReadKey().equals("N")) {
        tfj006.setReadKey("Y");
        tfj006.setReadDate(sysdate);
        tfj006.setReadUser(book.getOpuser());
      }

      for (Tourist trip : book.getCustomerList()) {
        Tourist tfj007 = (Tourist) getHibernateTemplate().load(Tourist.class,
            trip.getNmno(), LockMode.PESSIMISTIC_WRITE);
        // 应收团款
        tfj007.setAmt01(trip.getReceivables());
        expense = expense.add(trip.getReceivables());
        getHibernateTemplate().update(tfj007);
      }

      // 团确
      tfj006.setCfmKey("1");
      // 确认人数
      tfj006.setConfirmPax(book.getCustomerList().size());
      // 应收款
      tfj006.setDbamt(expense);

      getHibernateTemplate().update(tfj006);

      // --------------------------------------------------------------------

      // 已订名额
      plan.setPax2(pax2);
      // 可用名额
      plan.setPax3(plan.getPax1() - pax2 - plan.getPax4());
      // 修改共享资源
      if (plan.getShareFlightId() != null) {
        ShareFlight tsf = (ShareFlight) getHibernateTemplate().get(
            ShareFlight.class, plan.getShareFlightId());
        if (tsf != null) {
          tsf.setHandle(tsf.getHandle() - pax2 - plan.getPax4());
          getHibernateTemplate().update(tsf);
        }
      }
      getHibernateTemplate().update(plan);
    }

    return 0;
  }

  @SuppressWarnings("unchecked")
  public int splitBooking(Booking book, Set<String> set, String reserveNo,
      String note) {
    HibernateTemplate template = getHibernateTemplate();
    StringBuilder sql = new StringBuilder();
    Booking oldbook = (Booking) template.load(Booking.class,
        book.getBookingNo(), LockMode.PESSIMISTIC_WRITE);
    Booking newbook = null;
    try {
      newbook = oldbook.clone();
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }
    if (null == newbook)
      return -1;
    newbook.setBookingNo(reserveNo);

    Object[] params = { book.getBookingNo() };
    sql.append("from Tourist where booking.nameNo=? ");
    List<Tourist> list = template.find(sql.toString(), params);

    // 确认人数
    int confirmPax = 0;
    // 应收团款
    BigDecimal amt01All = new BigDecimal(0);
    for (Tourist tfj007 : list) {
      if (set.contains(tfj007.getNmno())) {
        tfj007.setBooking(newbook);
        // 操作人
        tfj007.setOpuser(newbook.getOpuser());
        amt01All = amt01All.add(RowDataUtil.getBigDecimal(tfj007.getAmt01()));
        template.update(tfj007);

        if (tfj007.getConfirmStatus().equals("1"))
          confirmPax++;
      }
    }

    oldbook.setDbamt(oldbook.getDbamt().subtract(amt01All));
    oldbook.setPax(oldbook.getPax() - set.size());
    oldbook.setConfirmPax(oldbook.getConfirmPax() - confirmPax);
    template.update(oldbook);

    newbook.setDbamt(amt01All);
    newbook.setPax(set.size());
    newbook.setConfirmPax(confirmPax);
    template.save(newbook);

    return 0;
  }

  /**
   * 恢复取消的客人
   * 
   * @return
   */
  @SuppressWarnings("unchecked")
  public int resumeCustomers(Booking book, Set<String> customers, String note) {
    HibernateTemplate template = getHibernateTemplate();

    StringBuilder sql = new StringBuilder();
    Object[] params = { book.getBookingNo() };
    sql.append("from Tourist where booking.nameNo=? ");
    List<Tourist> list = template.find(sql.toString(), params);

    // 确认人数
    int confirmPax = 0;
    for (Tourist tfj007 : list) {
      if (customers.contains(tfj007.getNmno())) {
        // 取消标志
        tfj007.setDel('N');
        // 操作人
        tfj007.setOpuser(book.getOpuser());
        template.update(tfj007);
      }

      if (tfj007.getDel() == 'N')
        confirmPax++;
    }

    // ---------------------------------------------------------------------
    Booking tfj006 = (Booking) template.load(Booking.class,
        book.getBookingNo(), LockMode.PESSIMISTIC_WRITE);

    if (tfj006.getCfmKey().equals("1")) {
      // 确认人数
      tfj006.setConfirmPax(confirmPax);
    }
    // 预订人数
    tfj006.setPax(list.size());
    if (confirmPax == 0) {
      // 取消标志
      tfj006.setDelkey("Y");
    } else {
      tfj006.setDelkey("N");
    }
    // 最后修改人
    tfj006.setOpuser(book.getOpuser());

    template.update(tfj006);
    // ---------------------------------------------------------------------
    if (tfj006.getCfmKey().equals("1")) {
      StringBuilder query = new StringBuilder();
      query.append("select sum(confirmPax) ");
      query.append("from com.opentravelsoft.entity.Booking ");
      query.append("where plan.planNo=? and cfmKey='1' ");
      query.append("and delkey='N' ");

      Object[] param = { book.getPlan().getPlanNo() };
      List<Object> bookList = getHibernateTemplate().find(query.toString(),
          param);
      int pax2 = RowDataUtil.getShort(bookList.get(0));

      Plan plan = (Plan) getHibernateTemplate().load(Plan.class,
          tfj006.getPlan().getPlanNo(), LockMode.PESSIMISTIC_WRITE);
      // 已订名额
      plan.setPax2(pax2);
      // 可用名额
      plan.setPax3(plan.getPax1() - pax2 - plan.getPax4());
      // 修改共享资源
      if (plan.getShareFlightId() != null) {
        ShareFlight tsf = (ShareFlight) getHibernateTemplate().get(
            ShareFlight.class, plan.getShareFlightId());
        if (tsf != null) {
          tsf.setHandle(tsf.getHandle() + pax2 - plan.getPax4());
          getHibernateTemplate().update(tsf);
        }
      }
      getHibernateTemplate().update(plan);
    }

    return 0;
  }

  @SuppressWarnings("unchecked")
  public List<Booking> findUndetermined(long teamId, long uid) {
    StringBuilder sql = new StringBuilder();

    sql.append("from Booking where cfmKey='2' ");
    sql.append("and delkey='N' and plan.outDate>=current_date() ");

    if (teamId != 0)
      sql.append(" and plan.team.teamId=" + teamId + " ");
    if (uid != 0)
      sql.append(" and plan.assigned.userId=" + uid + " ");

    return getHibernateTemplate().find(sql.toString());
  }

  @SuppressWarnings("unchecked")
  public List<Booking> find(String lineName, Date startDatePeriod,
      Date endDatePeriod, Date orderStartDatePeriod,
      Date orderStartDatePeriod2, String contractNo, String invoiceNo,
      String touristName, String bookState, String cancelFlag) {

    StringBuilder sql = new StringBuilder();
    boolean model = false;
    List<Object> params = new ArrayList<Object>();

    if (StringUtil.hasLength(contractNo) || StringUtil.hasLength(invoiceNo)
        || StringUtil.hasLength(touristName)) {
      model = true;
    }
    sql.append("select ");
    if (model)
      sql.append("DISTINCT ");
    sql.append("a.nameNo,a.receiveDt,a.plan.outDate,a.pax,a.dbamt,"); // 4
    sql.append("a.cramt,a.dbamt-a.cramt,a.cfmKey,a.delkey,"); // 8
    sql.append("a.plan.line.lineNo,a.plan.line.lineName,a.readKey "); // 11
    sql.append("from Booking a ");
    if (model)
      sql.append(", st c ");
    sql.append("where 1=1 ");
    if (model)
      sql.append("and a.nameNo=c.booking.nameNo ");

    // 线路名
    if (StringUtil.hasLength(lineName)) {
      sql.append(" and a.plan.line.lineName like ? ");
      params.add(lineName + "%");
    }

    // 订单状态 1:已审核 2:未审核 %:所有
    if (!bookState.equals("%")) {
      sql.append(" and a.cfmKey =?");
      params.add(bookState);
    }
    // 取消标记 Y: 已取消 N:未取消 %:所有
    if (!cancelFlag.equals("%")) {
      sql.append(" and a.delkey =?");
      params.add(cancelFlag);
    }
    // 出发日期
    if (null != startDatePeriod) {
      sql.append(" and a.plan.outDate >= ?");
      params.add(startDatePeriod);
    }
    if (null != endDatePeriod) {
      sql.append(" and a.plan.outDate <= ?");
      params.add(endDatePeriod);
    }
    // 预订日期
    if (null != orderStartDatePeriod) {
      sql.append(" and a.receiveDt >= ?");
      params.add(orderStartDatePeriod);
    }
    if (null != orderStartDatePeriod2) {
      sql.append(" and a.receiveDt >= ?");
      params.add(orderStartDatePeriod2);
    }
    // 合同号
    if (StringUtil.hasLength(contractNo)) {
      sql.append(" and c.pactNo=?");
      params.add(contractNo);
    }
    // 发票号
    if (StringUtil.hasLength(invoiceNo)) {
      sql.append(" and c.invoiceNo=?");
      params.add(invoiceNo);
    }
    // 客人姓名
    if (StringUtil.hasLength(touristName)) {
      sql.append(" and c.userName like ?");
      params.add("%" + touristName + "%");
    }

    Object[] param = null;
    if (params.size() > 0) {
      param = new Object[params.size()];
      for (int i = 0; i < params.size(); i++) {
        param[i] = params.get(i);
      }
    }

    List<Object[]> list = getHibernateTemplate().find(sql.toString(), param);
    List<Booking> ret = new ArrayList<Booking>();
    Booking book = null;
    for (Object[] obj : list) {
      book = new Booking();
      // 出团计划编号
      book.setBookingNo(RowDataUtil.getString(obj[0]));
      // 线路编号
      book.getPlan().getLine().setLineNo(RowDataUtil.getString(obj[9]));
      // 线路名称
      book.getPlan().getLine().setLineName(RowDataUtil.getString(obj[10]));
      // 预订时间
      book.setReserveDate(RowDataUtil.getDate(obj[1]));
      // 出团时间
      book.getPlan().setOutDate(RowDataUtil.getDate(obj[2]));
      // 预订人数
      book.setPax(RowDataUtil.getInt(obj[3]));
      // 应收款
      book.setDbamt(RowDataUtil.getBigDecimal(obj[4]));
      // 已收款
      book.setPayCosts(RowDataUtil.getBigDecimal(obj[5]));
      // 审核否
      book.setCfmKey(RowDataUtil.getString(obj[7]));
      // 取消状态
      book.setDelkey(RowDataUtil.getString(obj[8]));
      // 是否标记为已读
      book.setReadKey(RowDataUtil.getString(obj[11]));
      ret.add(book);
    }

    return ret;
  }

  @SuppressWarnings("unchecked")
  public List<Booking> getBookings(long memberId) {
    StringBuilder sb = new StringBuilder();
    sb.append("from Booking where memberId=? ");

    Object[] params = { memberId };
    return getHibernateTemplate().find(sb.toString(), params);
  }

  @SuppressWarnings("unchecked")
  public List<Booking> getConfirmBookings(int accountId) {
    StringBuilder sql = new StringBuilder();
    sql.append("from Booking where cfmKey='1' ");
    sql.append("and plan.outDate>current_date() and delKey='N' ");
    sql.append("and customerId=?");
    Object[] params = { accountId };

    return getHibernateTemplate().find(sql.toString(), params);
  }

  @SuppressWarnings("unchecked")
  public List<Booking> getUnconfirmBookings(int accountId) {
    StringBuilder sql = new StringBuilder();
    sql.append("from Booking ");
    sql.append("where cfmKey='2' and delkey='N' ");
    sql.append("and plan.outDate>current_date() ");
    sql.append("and customerId=?");
    sql.append("order by receiveDt desc");
    Object[] params = { accountId };

    return getHibernateTemplate().find(sql.toString(), params);
  }

  @SuppressWarnings("unchecked")
  public List<Booking> find(String lineName, long teamId, long userId,
      Date startDatePeriod, Date endDatePeriod, Date reserveStart,
      Date reserveEnd, String tourist, String agentId, String salesman,
      String cfmKey, String readKey, String delKey, String reserveNo) {
    StringBuilder sql = new StringBuilder();
    // 是否根据客人信息查询
    boolean model = false;
    List<Object> params = new ArrayList<Object>();

    if (StringUtil.hasLength(tourist)) {
      model = true;
    }
    sql.append("select ");
    if (model)
      sql.append("DISTINCT ");
    sql.append("a.nameNo,a.receiveDt,a.plan.outDate,a.pax,a.dbamt,"); // 4
    sql.append("a.cramt,a.dbamt-a.cramt,a.cfmKey,a.delkey,"); // 8
    sql.append("a.plan.line.lineNo,a.plan.line.lineName,a.customer.name,"); // 11
    sql.append("a.salesman.userId,a.confirmPax,a.reserve,a.readKey,"); // 15
    sql.append("a.salesman.userNm "); // 16
    sql.append("from Booking as a ");

    if (model)
      sql.append(", Tourist as c ");

    sql.append("where 1=1 ");

    if (model)
      sql.append("and a.nameNo=c.nameNo ");

    // 线路名
    if (StringUtil.hasLength(lineName)) {
      sql.append(" and a.plan.lineName like ? ");
      params.add("%" + lineName + "%");
    }

    // 部门
    if (teamId != 0) {
      sql.append(" and a.plan.team.teamId=? ");
      params.add(teamId);
    }

    // 用户
    if (userId != 0) {
      sql.append(" and a.plan.assigned.userId=? ");
      params.add(userId);
    }

    // 出发日期
    if (null != startDatePeriod) {
      sql.append(" and a.plan.outDate >= ?");
      params.add(startDatePeriod);
    }
    if (null != endDatePeriod) {
      sql.append(" and a.plan.outDate <= ?");
      params.add(endDatePeriod);
    }

    // 预订日期
    if (null != reserveStart) {
      sql.append(" and a.receiveDt >= ?");
      params.add(reserveStart);
    }
    if (null != reserveEnd) {
      sql.append(" and a.receiveDt >= ?");
      params.add(reserveEnd);
    }

    // 订单号
    if (StringUtil.hasLength(reserveNo)) {
      sql.append(" and a.nameNo=?");
      params.add(reserveNo);
    }

    // 客人姓名
    if (StringUtil.hasLength(tourist)) {
      sql.append(" and c.name like ?");
      params.add("%" + tourist + "%");
    }

    // 代理商
    if (StringUtil.hasLength(agentId)) {
      sql.append(" and a.customer.name like ?");
      params.add("%" + agentId + "%");
    }

    // 销售员
    if (StringUtil.hasLength(salesman)) {
      sql.append(" and a.salesman.userNm like ?");
      params.add("%" + salesman + "%");
    }

    // 是否审核
    if (StringUtil.hasLength(cfmKey)) {
      if (!cfmKey.equals("%")) {
        sql.append(" and a.cfmKey = ?");
        params.add(cfmKey);
      }
    }

    // 是否已读
    if (StringUtil.hasLength(readKey)) {
      sql.append(" and a.readKey = ?");
      params.add(readKey);
    }

    // 是否取消
    if (StringUtil.hasLength(delKey)) {
      sql.append(" and a.delkey = ?");
      params.add(delKey.charAt(0));
    }

    Object[] param = null;
    if (params.size() > 0) {
      param = new Object[params.size()];
      for (int i = 0; i < params.size(); i++) {
        param[i] = params.get(i);
      }
    }

    sql.append(" order by a.receiveDt");

    List<Object[]> list = getHibernateTemplate().find(sql.toString(), param);

    List<Booking> ret = new ArrayList<Booking>();
    Booking book = null;
    for (Object[] obj : list) {
      book = new Booking();
      // 出团计划编号
      book.setBookingNo(RowDataUtil.getString(obj[0]));
      // 预订时间
      book.setReserveDate(RowDataUtil.getDate(obj[1]));
      //
      book.getPlan().setOutDate(RowDataUtil.getDate(obj[2]));
      // 预订人数
      book.setPax(RowDataUtil.getInt(obj[3]));
      // 应收款
      book.setDbamt(RowDataUtil.getBigDecimal(obj[4]));
      // 已收款
      book.setPayCosts(RowDataUtil.getBigDecimal(obj[5]));
      // 审核否
      book.setCfmKey(RowDataUtil.getString(obj[7]));
      // 取消状态
      book.setDelkey(RowDataUtil.getString(obj[8]));
      // 线路ID
      book.getPlan().getLine().setLineNo(RowDataUtil.getString(obj[9]));
      // 线路名
      book.getPlan().getLine().setLineName(RowDataUtil.getString(obj[10]));
      // 客户
      book.getCustomer().setName(RowDataUtil.getString(obj[11]));
      // 销售员
      book.getSalesman().setUserId(RowDataUtil.getInt(obj[12]));
      // 销售员
      book.getSalesman().setUserName(RowDataUtil.getString(obj[16]));
      // 确认人数
      book.setConfirmPax(RowDataUtil.getInt(obj[13]));

      book.setReserve(RowDataUtil.getInt(obj[14]));
      // 是否已读
      book.setReadKey(RowDataUtil.getString(obj[15]));

      ret.add(book);
    }

    return ret;
  }

  @SuppressWarnings("unchecked")
  private void supplyYin(List<Tourist> trips) {
    StringBuilder sql = new StringBuilder();
    sql.append("from Pinyin where chinese in (");

    for (Tourist trip : trips) {
      if (StringUtil.hasLength(trip.getPinYin()))
        continue;

      String name = trip.getUserName().trim();
      StringBuilder py = new StringBuilder();
      char[] ch = name.toCharArray();
      StringBuilder sb = new StringBuilder();
      int count = 0;
      for (char c : ch) {
        sb.append("'" + c + "',");
      }

      if (sb.length() > 0) {
        List<Pinyin> pys = getHibernateTemplate().find(
            sql.toString() + sb.substring(0, sb.length() - 1) + ")");
        Map<String, String> map = new TreeMap<String, String>();
        for (Pinyin tfj112 : pys) {
          map.put(tfj112.getChinese(), tfj112.getEnglish());
        }

        for (char c : ch) {
          if (null == map.get(String.valueOf(c)))
            py.append(c);
          else {
            py.append(map.get(String.valueOf(c)));
            count++;
            if (count == 1)
              py.append(' ');
          }
        }
        trip.setPinYin(py.toString().trim());
      }
    }
  }

  @SuppressWarnings("unchecked")
  public List<Booking> getUnreadBookings(long uid) {
    StringBuilder sql = new StringBuilder();
    sql.append("from Booking where delkey<>'Y' and readKey='N' ");
    sql.append("and plan.assigned.userId=? ");

    Object[] params = { uid };
    return getHibernateTemplate().find(sql.toString(), params);
  }

  @SuppressWarnings("unchecked")
  public List<Booking> getPlanBookings(String planNo) {
    StringBuilder sql = new StringBuilder();
    sql.append("from Booking where planNo=? ");

    Object[] params = { planNo };
    return getHibernateTemplate().find(sql.toString(), params);
  }

  @SuppressWarnings("unchecked")
  public List<Booking> findCustomerDbamt(String provinceId, String kenSales,
      Date startDate, Date endDate, String payment, String kenCity) {
    // 按照客户地区统计
    StringBuilder sql = new StringBuilder();
    List<Object> params = new ArrayList<Object>();
    sql.append("SELECT a.customer.name,b.cnName,count(a.nameNo),");
    sql.append("sum(a.confirmPax),sum(a.dbamt),sum(a.cramt),");
    sql.append("sum(a.dbamt-a.cramt),a.customer.customerId,");
    sql.append("a.customer.payment,a.customer.contact ");
    sql.append("from Booking a,");
    sql.append("Province b,");
    sql.append("Customer c,");
    sql.append("where a.delkey='N' and a.customer.provinceCd=b.code ");

    if (StringUtil.hasLength(payment.toString())) {
      sql.append("and a.customer.payment=? ");
      params.add(payment);
    }
    if (StringUtil.hasLength(provinceId)) {
      sql.append("and a.customer.provinceCd=? ");
      params.add(provinceId);
    }
    if (StringUtil.hasLength(kenCity)) {
      sql.append("and a.customer.city.citycd=? ");
      params.add(kenCity);
    }
    if (StringUtil.hasLength(kenSales)) {
      sql.append("and a.salesman.userNm=? ");
      params.add(kenSales);
    }
    if (null != startDate) {
      sql.append("and a.plan.outDate>=? ");
      params.add(startDate);
    }
    if (null != endDate) {
      sql.append("and a.plan.outDate<=? ");
      params.add(endDate);
    }
    sql.append("group by a.customer.name,b.cnName,a.customer.customerId,");
    sql.append("a.customer.payment,a.customer.contact ");
    Object[] param = null;
    if (params.size() > 0) {
      param = new Object[params.size()];
      for (int i = 0; i < params.size(); i++) {
        param[i] = params.get(i);
      }
    }
    List<Booking> bookList = new ArrayList<Booking>();
    List<Object[]> t006List = getHibernateTemplate()
        .find(sql.toString(), param);
    for (Object[] obj : t006List) {
      Booking book = new Booking();
      book.getCustomer().setName(RowDataUtil.getString(obj[0]));
      book.setBatch(RowDataUtil.getInt(obj[2]));
      book.setSumpax(RowDataUtil.getInt(obj[3]));
      book.setSumDbamt(RowDataUtil.getBigDecimal(obj[4]));
      book.setSumCramt(RowDataUtil.getBigDecimal(obj[5]));
      book.setSumUnpay(RowDataUtil.getBigDecimal(obj[6]));
      book.getCustomer().setCustomerId(RowDataUtil.getInt(obj[7]));
      Character pay = RowDataUtil.getChar(obj[8]);
      book.setContact(RowDataUtil.getString(obj[9]));

      if (pay.equals('M')) {
        book.setPaymentType("月结");
      } else if (pay.equals('N')) {
        book.setPaymentType("现结");
      }
      bookList.add(book);
    }
    return bookList;
  }

  /**
   * 根据客户得到订单
   */
  @SuppressWarnings("unchecked")
  public List<Booking> getBooksByCustomer(int customerId, String kenSales,
      Date startDate, Date endDate) {
    StringBuilder sql = new StringBuilder();
    List<Object> params = new ArrayList<Object>();
    sql.append("from Booking where customerId=? and delkey='N' ");
    params.add(customerId);

    if (StringUtil.hasLength(kenSales)) {
      sql.append("and salesman.userNm=? ");
      params.add(kenSales);
    }
    if (null != startDate) {
      sql.append("and plan.outDate >= ? ");
      params.add(startDate);
    }
    if (null != endDate) {
      sql.append("and plan.outDate <= ? ");
      params.add(endDate);
    }
    Object[] param = null;
    if (params.size() > 0) {
      param = new Object[params.size()];
      for (int i = 0; i < params.size(); i++) {
        param[i] = params.get(i);
      }
    }
    return getHibernateTemplate().find(sql.toString(), param);
  }

  /**
   * 某目的地旅客统计
   */
  @SuppressWarnings("unchecked")
  public List<Booking> districtStat(Date startDate, Date endDate, String country) {
    StringBuilder sql = new StringBuilder();
    sql.append("select c.cnName,c.districtNo,d.name,sum(a.pax),");
    sql.append("sum(a.dbamt) ");
    sql.append("from Booking a,");
    sql.append("LineDistrict b,");
    sql.append("District c,");
    sql.append("Country d ");
    sql.append("where a.lineNo=b.id.lineNo ");
    sql.append("and c.country=d.countryId ");
    sql.append("and b.id.districtNo=c.districtNo ");
    sql.append("and a.cfmKey='1' AND a.delkey<>'Y'  ");
    List<Object> params = new ArrayList<Object>();
    if (null != startDate) {
      sql.append("and a.outDate >= ? ");
      params.add(startDate);
    }
    if (null != endDate) {
      sql.append("and a.outDate <= ? ");
      params.add(endDate);
    }
    if (StringUtil.hasLength(country)) {
      sql.append("and c.country = ? ");
      params.add(country);
    }
    sql.append("group by c.cnName,c.districtNo,d.id.value ");
    sql.append("order by d.id.value");

    Object[] param = null;
    if (params.size() > 0) {
      param = new Object[params.size()];
      for (int i = 0; i < params.size(); i++) {
        param[i] = params.get(i);
      }
    }
    List<Booking> bookList = new ArrayList<Booking>();
    List<Object[]> list = getHibernateTemplate().find(sql.toString(), param);
    for (Object[] obj : list) {
      Booking book = new Booking();
      book.setDistrict(RowDataUtil.getString(obj[0]));
      book.setDistrictNo(RowDataUtil.getString(obj[1]));
      book.setCountry(RowDataUtil.getString(obj[2]));
      book.setSumpax(RowDataUtil.getInt(obj[3]));
      book.setSumDbamt(RowDataUtil.getBigDecimal(obj[4]));
      bookList.add(book);
    }
    return bookList;
  }

  /**
   * 查询该线路订单情况
   * 
   * @return
   */
  @SuppressWarnings("unchecked")
  public List<Booking> getBookByLineNo(String lineNo, Date startDate,
      Date endDate) {
    StringBuilder sb = new StringBuilder();
    sb.append("from Booking ");
    sb.append("where plan.linelineNo=? and delkey<>'Y' ");
    sb.append("and cfmKey='1' ");
    sb.append("and plan.outDate>=? and plan.outDate<=?");
    Object[] param = { lineNo, startDate, endDate };
    return getHibernateTemplate().find(sb.toString(), param);
  }

  @SuppressWarnings("unchecked")
  public List<Booking> findBookings(String orderNo, String touristName,
      double account) {
    StringBuilder sql = new StringBuilder();
    boolean model = false;
    List<Object> params = new ArrayList<Object>();

    if (StringUtil.hasLength(touristName)) {
      model = true;
    }
    sql.append("select ");
    if (model)
      sql.append("DISTINCT ");
    sql.append("a.nameNo,a.receiveDt,a.plan.outDate,a.pax,a.dbamt,");
    sql.append("a.cramt,a.dbamt-a.cramt,a.cfmKey,a.delkey,");
    sql.append("a.plan.line.lineNo,a.plan.line.lineName,a.readKey ");
    sql.append("from Booking as a ");
    if (model)
      sql.append(",Tourist as c ");
    sql.append("where a.plan.outDate>current_date() ");
    if (model)
      sql.append("and a.nameNo=c.nameNo ");

    // 订单号
    if (StringUtil.hasLength(orderNo)) {
      sql.append("and a.nameNo=? ");
      params.add(orderNo);
    }

    // 订单金额
    if (account != 0) {
      sql.append("and a.dbamt=? ");
      params.add(account);
    }

    // 客人姓名
    if (StringUtil.hasLength(touristName)) {
      sql.append("and c.name=? ");
      params.add(touristName);
    }

    Object[] param = null;
    if (params.size() > 0) {
      param = new Object[params.size()];
      for (int i = 0; i < params.size(); i++) {
        param[i] = params.get(i);
      }
    }

    List<Object[]> list = getHibernateTemplate().find(sql.toString(), param);
    List<Booking> ret = new ArrayList<Booking>();
    Booking book = null;
    for (Object[] obj : list) {
      book = new Booking();
      // 出团计划编号
      book.setBookingNo(RowDataUtil.getString(obj[0]));
      // 线路编号
      book.getPlan().getLine().setLineNo(RowDataUtil.getString(obj[9]));
      // 线路名称
      book.getPlan().getLine().setLineName(RowDataUtil.getString(obj[10]));
      // 预订时间
      book.setReserveDate(RowDataUtil.getDate(obj[1]));
      // 出团时间
      book.getPlan().setOutDate(RowDataUtil.getDate(obj[2]));
      // 预订人数
      book.setPax(RowDataUtil.getInt(obj[3]));
      // 应收款
      book.setDbamt(RowDataUtil.getBigDecimal(obj[4]));
      // 已收款
      book.setPayCosts(RowDataUtil.getBigDecimal(obj[5]));
      // 审核否
      book.setCfmKey(RowDataUtil.getString(obj[7]));
      // 取消状态
      book.setDelkey(RowDataUtil.getString(obj[8]));
      // 是否标记为已读
      book.setReadKey(RowDataUtil.getString(obj[11]));
      ret.add(book);
    }

    return ret;
  }
}
