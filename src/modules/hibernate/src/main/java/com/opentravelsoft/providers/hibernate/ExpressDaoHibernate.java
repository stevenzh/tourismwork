package com.opentravelsoft.providers.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.LockMode;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.Express;
import com.opentravelsoft.entity.ExpressList;
import com.opentravelsoft.entity.product.TourLog;

import com.opentravelsoft.providers.ExpressDao;
import com.opentravelsoft.util.RowDataUtil;
import com.opentravelsoft.util.StringUtil;

@Repository("ExpressDao")
public class ExpressDaoHibernate extends GenericDaoHibernate<Express, String>
    implements ExpressDao {

  public ExpressDaoHibernate() {
    super(Express.class);
  }

  @SuppressWarnings("unchecked")
  public List<Booking> findBooking(String user) {
    StringBuilder sql = new StringBuilder();
    sql.append("select distinct ");
    sql.append("a.nameNo,a.receiveDt,a.plan.outDate,a.pax,a.dbamt,");
    sql.append("a.cramt,a.dbamt - a.cramt,a.cfmKey,a.delkey,");
    sql.append("a.plan.line.lineNo,a.plan.line.lineName,a.readKey, ");
    sql.append("t.start,t.end,a.plan.tourNo ");
    sql.append("from Booking a,");
    sql.append("org.jbpm.taskmgmt.exe.TaskInstance t ");
    sql.append("where a.lineNo = b.lineNo ");
    sql.append("and  a.nameNo = t.bookId ");
    // sql.append("and t.state!='" + Task.STATE_COMPLETED + "' ");
    sql.append("and t.actorId = ?");
    sql.append("and t.name = 'toExpress' ");

    Object[] params = { user };
    List<Object[]> list = getHibernateTemplate().find(sql.toString(), params);

    List<Booking> ret = new ArrayList<Booking>();
    for (Object[] obj : list) {
      Booking book = new Booking();
      // 订单号
      book.setBookingNo(RowDataUtil.getString(obj[0]));
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
      // 未收款
      book.setUnPay(RowDataUtil.getBigDecimal(obj[6]));
      // 审核否
      book.setCfmKey(RowDataUtil.getString(obj[7]));
      // 取消状态
      book.setDelkey(RowDataUtil.getString(obj[8]));
      // 线路编号
      book.getPlan().getLine().setLineNo(RowDataUtil.getString(obj[9]));
      // 线路名称
      book.getPlan().getLine().setLineName(RowDataUtil.getString(obj[10]));
      // 是否标记为已读
      book.setReadKey(RowDataUtil.getString(obj[11]));
      // 开始时间
      book.setStart(RowDataUtil.getDate(obj[12]));
      // 结束时间
      book.setEnd(RowDataUtil.getDate(obj[13]));
      // 团号
      book.getPlan().setTourNo(RowDataUtil.getString(obj[14]));
      ret.add(book);
    }

    return ret;
  }

  @SuppressWarnings("unchecked")
  public Express getExpress(String expressId) {
    StringBuilder sb = new StringBuilder();
    sb.append("from Express where expressId=?");
    Object[] params = { expressId };
    List<Express> list = getHibernateTemplate().find(sb.toString(), params);
    return list.get(0);
  }

  @SuppressWarnings("unchecked")
  public List<ExpressList> getExpressInfoList(String expressId) {
    StringBuilder sb = new StringBuilder();
    sb.append("from Expresslist where expressId=? ");
    Object[] params = { expressId };
    List<ExpressList> list = getHibernateTemplate().find(sb.toString(), params);
    return list;
  }

  @SuppressWarnings("unchecked")
  public List<Express> getExpressTaskList(String taskName) {

    StringBuilder sb = new StringBuilder();
    sb.append("select b ");
    sb.append("from Express b,");
    sb.append("org.jbpm.pvm.internal.task.TaskImpl t ");
    sb.append("where b.workflowId=t.executionId ");
    // sb.append("and t.name=? and t.state!='" + Task.STATE_COMPLETED + "' ");
    sb.append("order by b.expressDate ");

    Object[] params = { taskName };
    List<Express> list = getHibernateTemplate().find(sb.toString(), params);
    return list;
  }

  @SuppressWarnings("unchecked")
  public List<Express> findExpressDetail(String expressId, String contactor,
      String expressType, String expressModlue, String payType,
      String payModlue, String expressState, String teamNo, String line,
      Date expressStart, Date expressEnd) {
    StringBuilder sb = new StringBuilder();
    sb.append("select a.address,a.contactor,a.customer,a.expressDate,");
    sb.append("a.expressId,a.expressLastdate,a.expressModlue,");
    sb.append("a.expressState,a.expressType,a.line,a.memo,a.orderId,");
    sb.append("a.pay,a.payModlue,a.payType,a.teamNo,a.tel,a.zip,");
    sb.append("b.lineName ");
    sb.append("from Express a,Line b ");
    sb.append("where a.line=b.lineNo ");

    List<Object> params = new ArrayList<Object>();

    if (StringUtil.hasLength(expressId)) {
      sb.append(" and a.expressId=? ");
      params.add(expressId);
    }
    if (StringUtil.hasLength(contactor)) {
      sb.append(" and a.contactor like ? ");
      params.add("%" + contactor + "%");
    }
    if (StringUtil.hasLength(expressType)) {
      sb.append(" and a.expressType=? ");
      params.add(expressType);
    }
    if (StringUtil.hasLength(expressModlue)) {
      sb.append(" and a.expressModlue=? ");
      params.add(expressModlue);
    }
    if (StringUtil.hasLength(payType)) {
      sb.append(" and a.payType = ? ");
      params.add(payType);
    }
    if (StringUtil.hasLength(payModlue)) {
      sb.append(" and a.payModlue = ? ");
      params.add(payModlue);
    }
    if (StringUtil.hasLength(expressState)) {
      sb.append(" and a.expressState = ? ");
      params.add(expressState);
    }
    if (StringUtil.hasLength(teamNo)) {
      sb.append(" and a.teamNo like ? ");
      params.add("%" + teamNo + "%");
    }
    if (StringUtil.hasLength(line)) {
      sb.append(" and b.lineName like ? ");
      params.add("%" + line + "%");
    }
    if (null != expressStart) {
      sb.append(" and a.expressDate >= ? ");
      params.add(expressStart);
    }
    if (null != expressEnd) {
      sb.append(" and a.expressDate <= ? ");
      params.add(expressEnd);
    }

    Object[] param = null;
    if (params.size() > 0) {
      param = new Object[params.size()];
      for (int i = 0; i < params.size(); i++) {
        param[i] = params.get(i);
      }
    }

    List<Object[]> list = getHibernateTemplate().find(sb.toString(), param);
    List<Express> ret = new ArrayList<Express>();
    for (Object[] obj : list) {
      Express expressInfo = new Express();
      expressInfo.setAddress(RowDataUtil.getString(obj[0]));
      expressInfo.setContactor(RowDataUtil.getString(obj[1]));
      expressInfo.setCustomer(RowDataUtil.getString(obj[2]));
      expressInfo.setExpressDate(RowDataUtil.getDate(obj[3]));
      expressInfo.setExpressId(RowDataUtil.getString(obj[4]));
      expressInfo.setExpressLastdate(RowDataUtil.getDate(obj[5]));
      expressInfo.setExpressModlue(RowDataUtil.getString(obj[6]));
      expressInfo.setExpressState(RowDataUtil.getString(obj[7]));
      expressInfo.setExpressType(RowDataUtil.getString(obj[8]));
      expressInfo.setLine(RowDataUtil.getString(obj[9]));
      expressInfo.setMemo(RowDataUtil.getString(obj[10]));
      expressInfo.setOrderId(RowDataUtil.getString(obj[11]));
      expressInfo.setPay(RowDataUtil.getBigDecimal(obj[12]));
      expressInfo.setPayModlue(RowDataUtil.getString(obj[13]));
      expressInfo.setPayType(RowDataUtil.getString(obj[14]));
      expressInfo.setTeamNo(RowDataUtil.getString(obj[15]));
      expressInfo.setTel(RowDataUtil.getString(obj[16]));
      expressInfo.setZip(RowDataUtil.getString(obj[17]));
      expressInfo.setLineName(RowDataUtil.getString(obj[18]));

      ret.add(expressInfo);
    }

    return ret;
  }

  @SuppressWarnings("unchecked")
  public List<Express> getExpressList(String reserveNo) {
    StringBuilder sb = new StringBuilder();
    sb.append("from Express where orderId=? ");

    Object[] params = { reserveNo };
    List<Express> list = getHibernateTemplate().find(sb.toString(), params);

    return list;
  }

  public int assignExpress(Express express, List<ExpressList> expressInfo,
      String note) {
    HibernateTemplate template = getHibernateTemplate();

    Express tblCrmExpress = (Express) template.get(Express.class,
        express.getExpressId(), LockMode.PESSIMISTIC_WRITE);
    ArrayList<Express> all = new ArrayList<Express>();
    try {
      all.add(tblCrmExpress.clone());
    } catch (CloneNotSupportedException ex) {
      ex.printStackTrace();
    }

    tblCrmExpress.setAddress(express.getAddress());
    tblCrmExpress.setContactor(express.getContactor());
    // tblCrmExpress.setCustomer(express.getCustomer());
    tblCrmExpress.setExpressDate(express.getExpressDate());
    tblCrmExpress.setExpressLastdate(express.getExpressLastdate());
    tblCrmExpress.setExpressModlue(express.getExpressModlue());
    tblCrmExpress.setExpressType(express.getExpressType());
    tblCrmExpress.setMemo(express.getMemo());
    tblCrmExpress.setPay(express.getPay());
    tblCrmExpress.setPayModlue(express.getPayModlue());
    tblCrmExpress.setPayType(express.getPayType());
    tblCrmExpress.setTeamNo(express.getTeamNo());
    tblCrmExpress.setTel(express.getTel());
    tblCrmExpress.setZip(express.getZip());
    tblCrmExpress.setUpdatedby(express.getUpdatedby());
    template.update(tblCrmExpress);

    all.add(tblCrmExpress);

    for (ExpressList item : expressInfo) {
      ExpressList tblCrmExpresslist = (ExpressList) template.get(
          ExpressList.class, item.getItemId(), LockMode.PESSIMISTIC_WRITE);

      tblCrmExpresslist.setItemNum(item.getItemNum());
      tblCrmExpresslist.setExpressType(item.getExpressType());
      tblCrmExpresslist.setNote(item.getNote());
      tblCrmExpresslist.setUnit(item.getUnit());

      template.update(tblCrmExpresslist);
    }

    return 0;
  }

  public int delExpress(String expressId, String note) {
    HibernateTemplate template = getHibernateTemplate();
    Express express = (Express) template.get(Express.class, expressId,
        LockMode.PESSIMISTIC_WRITE);
    ArrayList<Express> all = new ArrayList<Express>();
    all.add(express);
    all.add(null);

    ExpressList expresslist = (ExpressList) template.get(ExpressList.class,
        expressId, LockMode.PESSIMISTIC_WRITE);

    if (null == express || null == expresslist) {
      return -1;
    }

    template.delete(express);
    template.delete(expresslist);

    return 0;
  }

  @SuppressWarnings("unused")
  private String getExpressInfo(ArrayList<Express> array) {
    String info = "";

    try {
      if (array != null) {
        if (array.size() > 1) {
          Express old_express = array.get(0);
          Express new_express = array.get(1);

          if (old_express != null && new_express != null) {
            if (!old_express.equals(new_express)) {
              info = old_express.getInfo();
            }
          }
        }
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return info;
  }

  @SuppressWarnings("unchecked")
  public List<TourLog> getExpressLog(String expressId) {
    StringBuilder sb = new StringBuilder();
    sb.append("select a.modifiedUser,b.userNm,a.modifiedDate,");
    sb.append("a.expressId,a.routeNo,a.tourNo,a.note,a.info ");
    sb.append("from TblExpressLog a, Employee b ");
    sb.append("where a.modifiedUser =b.userCd and a.expressId = ? ");
    sb.append("order by a.modifiedDate desc ");

    Object[] params = { expressId };
    List<Object[]> list = getHibernateTemplate().find(sb.toString(), params);
    List<TourLog> res = null;
    if (list != null) {
      res = new ArrayList<TourLog>();
      for (Object[] obj : list) {
        TourLog expressLog = new TourLog();
        expressLog.setModifiedUser(RowDataUtil.getString(obj[0]));
        expressLog.setModifiedUserName(RowDataUtil.getString(obj[1]));
        expressLog.setModifiedDate(RowDataUtil.getDate(obj[2]));
        expressLog.setExpressId(RowDataUtil.getString(obj[3]));
        expressLog.setRouteNo(RowDataUtil.getString(obj[4]));
        expressLog.setTourNo(RowDataUtil.getString(obj[5]));
        expressLog.setNote(RowDataUtil.getString(obj[6]));
        res.add(expressLog);
      }
    }
    return res;
  }

}
