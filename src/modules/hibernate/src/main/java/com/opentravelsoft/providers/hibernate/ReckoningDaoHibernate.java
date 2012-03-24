package com.opentravelsoft.providers.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.LockMode;

import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Tourist;
import com.opentravelsoft.entity.finance.Reckoning;
import com.opentravelsoft.entity.finance.ReckoningAcct;
import com.opentravelsoft.entity.finance.ReckoningAcctId;
import com.opentravelsoft.providers.ReckoningDao;
import com.opentravelsoft.util.RowDataUtil;

/**
 * 帐单制作
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 */
@Repository("ReckoningDao")
public class ReckoningDaoHibernate extends GenericDaoHibernate<Reckoning, Long>
    implements ReckoningDao {

  public ReckoningDaoHibernate() {
    super(Reckoning.class);
  }

  @SuppressWarnings("unchecked")
  public List<Reckoning> getReckoning(String reserveNo) {
    StringBuilder sql = new StringBuilder();
    sql.append("from Reckoning where bookingNo=? ");

    Object[] param = { reserveNo };
    List<Reckoning> list = getHibernateTemplate().find(sql.toString(), param);
    for (Reckoning reckoning : list) {
      reckoning.setVersion(RowDataUtil.getInt(reckoning.getNumber()));

      reckoning.setCreateDate(RowDataUtil.getDate(reckoning.getCreated()));
      reckoning.setPrintDate(RowDataUtil.getDate(reckoning.getPrinted()));
    }

    return list;
  }

  @SuppressWarnings("unchecked")
  public Reckoning wholeReckoningMake(Reckoning reckoning) {
    StringBuilder sql = new StringBuilder();
    String reserveNo = reckoning.getBookingNo();
    Reckoning tblReckoning = new Reckoning();
    int version = 1;

    // 续上版本号
    sql.append("from Reckoning where bookingNo=? order by number ");
    Object[] param = { reserveNo };
    List<Reckoning> list = getHibernateTemplate().find(sql.toString(), param);

    if (null == list || list.isEmpty()) {
      tblReckoning.setNumber(version);
    } else {

      for (Reckoning obj : list) {
        if (obj.getNumber() > version)
          version = obj.getNumber();
      }
      tblReckoning.setNumber(++version);
    }
    //

    tblReckoning.setBookingNo(reckoning.getBookingNo());
    tblReckoning.setTourType(reckoning.getTourType());
    tblReckoning.setContact(reckoning.getContact());
    tblReckoning.setPhone(reckoning.getPhone());
    tblReckoning.setFax(reckoning.getFax());
    tblReckoning.setCreatedBy(reckoning.getCreatedBy());
    tblReckoning.setPrintedCount(0);

    getHibernateTemplate().save(tblReckoning);

    // 得到帐单号
    sql = new StringBuilder();
    sql.append("from Reckoning where bookingNo=? and number=? ");
    Object[] params = { reserveNo, version };
    List<Reckoning> tblReckonings = getHibernateTemplate().find(sql.toString(),
        params);

    ReckoningAcct tblReckoningAcct;
    ReckoningAcct reckoningAcct;
    ReckoningAcctId tblReckoningAcctId;

    reckoning.setReckoningId(tblReckonings.get(0).getReckoningId());
    reckoning.setVersion(tblReckonings.get(0).getNumber());

    if (reckoning.getTourType() == '1') {
      if (!reckoning.getReckoningAcctList().isEmpty()) {
        if (null != tblReckonings && !(tblReckonings.isEmpty())) {
          for (int i = 0; i < reckoning.getReckoningAcctList().size(); i++) {
            tblReckoningAcct = new ReckoningAcct();
            tblReckoningAcctId = new ReckoningAcctId();
            reckoningAcct = reckoning.getReckoningAcctList().get(i);

            tblReckoningAcctId.setReckoningId(tblReckonings.get(0)
                .getReckoningId());
            tblReckoningAcctId.setItemId(reckoningAcct.getItemId());

            tblReckoningAcct.setId(tblReckoningAcctId);
            tblReckoningAcct.setAmount(reckoningAcct.getAmount());
            tblReckoningAcct.setUnit(reckoningAcct.getUnit());
            tblReckoningAcct.setDescription(reckoningAcct.getDescription());
            tblReckoningAcct.setUnitPrice(reckoningAcct.getUnitPrice());
            tblReckoningAcct.setCount(reckoningAcct.getCount());

            getHibernateTemplate().save(tblReckoningAcct);
          }
        }
      }
    }
    // 保存应收帐款
    Booking tfj006 = (Booking) getHibernateTemplate().get(Booking.class,
        reckoning.getBookingNo());
    tfj006.setDbamt(reckoning.getAmount());

    // --------------------------------------------------------------------
    // String enabled = (String) ActionContext.getContext().getApplication()
    // .get(EbizCommon.WORKFLOW_ENABLED);
    // if (enabled.equals("1"))
    // {
    // StringBuilder sb = new StringBuilder();
    // sb.append("select t ");
    // sb.append("from org.jbpm.pvm.internal.task.TaskImpl t ");
    // sb.append(",Booking b ");
    // sb.append("where t.executionId=b.workflowId and b.nameNo=? ");
    // sb.append("and t.name=? ");
    // sb.append("and t.state!='" + Task.STATE_COMPLETED + "' ");
    // Object[] p = { reckoning.getBookingNo(),
    // WorkFlowKeyParams.ORDER_TASK_ACCOUNT };
    // List<TaskImpl> taskInstances = getHibernateTemplate().find(
    // sb.toString(), p);
    //
    // for (TaskImpl taskInstance : taskInstances)
    // {
    // taskInstance.createVariable(WorkFlowKeyParams.WORKFLOW_ACTOR,
    // reckoning.getCreatedBy());
    // taskService.completeTask(taskInstance.getId());
    // }
    // }
    // --------------------------------------------------------------------

    getHibernateTemplate().update(tfj006);

    return reckoning;
  }

  @SuppressWarnings("unchecked")
  public Reckoning getReckoningInfo(long reckoningId) {
    Reckoning reckoning = (Reckoning) getHibernateTemplate().get(
        Reckoning.class, reckoningId);

    if (null != reckoning) {

      reckoning.setVersion(RowDataUtil.getInt(reckoning.getNumber()));
      reckoning.setCreateDate(RowDataUtil.getDate(reckoning.getCreated()));
      reckoning.setPrintDate(RowDataUtil.getDate(reckoning.getPrinted()));

      StringBuilder sql = new StringBuilder();
      sql.append("from Employee where userId=?");
      Object[] params = { reckoning.getCreatedBy() };

      List<Employee> employees = getHibernateTemplate().find(sql.toString(),
          params);

      if (null != employees && !(employees.isEmpty()))
        reckoning.setCreatedByName(employees.get(0).getUserName());

      // 如果为整团，则取出帐单明细
      if (reckoning.getTourType() == '1') {
        sql = new StringBuilder();
        sql.append("from ReckoningAcct where id.reckoningId=? ");
        Object[] param = { reckoningId };

        List<ReckoningAcct> tblReckoningAcctList = getHibernateTemplate().find(
            sql.toString(), param);

        List<ReckoningAcct> reckoningAcctList = new ArrayList<ReckoningAcct>();
        for (ReckoningAcct obj : tblReckoningAcctList) {
          obj.setReckoningId(obj.getId().getReckoningId());
          obj.setItemId(obj.getId().getItemId());

          reckoningAcctList.add(obj);
        }
        reckoning.setReckoningAcctList(reckoningAcctList);
      }
    }

    return reckoning;
  }

  @SuppressWarnings("unchecked")
  public int wholeReckoningModify(Reckoning reckoning) {
    StringBuilder sql = new StringBuilder();

    Reckoning tblReckoning = new Reckoning();

    tblReckoning = (Reckoning) getHibernateTemplate().get(Reckoning.class,
        reckoning.getReckoningId(), LockMode.UPGRADE);

    if (null != tblReckoning
        && tblReckoning.getReckoningId() == reckoning.getReckoningId()) {
      //String str = getReckoningInfo(tblReckoning, reckoning);
      tblReckoning.setContact(reckoning.getContact());
      tblReckoning.setPhone(reckoning.getPhone());
      tblReckoning.setFax(reckoning.getFax());
      tblReckoning.setUpdatedBy(reckoning.getUpdatedBy());

      getHibernateTemplate().update(tblReckoning);

      if (reckoning.getTourType() == '1') {
        sql = new StringBuilder();
        sql.append("from ReckoningAcct where id.reckoningId=? ");
        Object[] param2 = { reckoning.getReckoningId() };
        List<ReckoningAcct> tblReckoningAcctList = getHibernateTemplate().find(
            sql.toString(), param2);

        getHibernateTemplate().deleteAll(tblReckoningAcctList);

        ReckoningAcct tblReckoningAcct;
        ReckoningAcct reckoningAcct;
        ReckoningAcctId tblReckoningAcctId;

        for (int i = 0; i < reckoning.getReckoningAcctList().size(); i++) {
          tblReckoningAcct = new ReckoningAcct();
          tblReckoningAcctId = new ReckoningAcctId();
          reckoningAcct = reckoning.getReckoningAcctList().get(i);

          tblReckoningAcctId.setReckoningId(reckoning.getReckoningId());

          tblReckoningAcctId.setItemId(reckoningAcct.getItemId());

          tblReckoningAcct.setId(tblReckoningAcctId);
          tblReckoningAcct.setAmount(reckoningAcct.getAmount());
          tblReckoningAcct.setUnit(reckoningAcct.getUnit());
          tblReckoningAcct.setDescription(reckoningAcct.getDescription());
          tblReckoningAcct.setUnitPrice(reckoningAcct.getUnitPrice());
          tblReckoningAcct.setCount(reckoningAcct.getCount());

          getHibernateTemplate().save(tblReckoningAcct);
        }
      }

      // 修改应收帐款
      Booking tfj006 = (Booking) getHibernateTemplate().get(Booking.class,
          reckoning.getBookingNo());

      Double old_Amount = tfj006.getDbamt();

      tfj006.setDbamt(reckoning.getAmount());
      getHibernateTemplate().update(tfj006);

      String str1 = "账单号," + reckoning.getReckoningId() + ","
          + tblReckoning.getReckoningId() + ",";
      if (old_Amount != tfj006.getDbamt()) {
        str1 = str1 + "应收账款," + tfj006.getDbamt() + "," + old_Amount + ",";
      }

      return 0;

    } else
      return -1;

  }

  @SuppressWarnings("unchecked")
  public List<ReckoningAcct> getCustomerList(String bookingNo) {
    StringBuilder sql = new StringBuilder();
    sql.append("from Tourist where booking.nameNo=? and del='N' ");
    List<ReckoningAcct> reckoningAcctList = new ArrayList<ReckoningAcct>();
    ReckoningAcct reckoningAcct;

    Object[] params = { bookingNo };
    List<Tourist> tourists = getHibernateTemplate()
        .find(sql.toString(), params);

    int itemId = 1;
    for (Tourist obj : tourists) {
      reckoningAcct = new ReckoningAcct();
      reckoningAcct.setItemId(itemId++);
      reckoningAcct.setName(RowDataUtil.getString(obj.getUserName()));
      reckoningAcct.setAmount(RowDataUtil.getDouble(obj.getAmt01()));

      reckoningAcctList.add(reckoningAcct);
    }

    return reckoningAcctList;
  }

  public int setPrint(int reckoningId) {
    Date sysdate = getSysdate();
    Reckoning tblReckoning = new Reckoning();

    tblReckoning = (Reckoning) getHibernateTemplate().get(Reckoning.class,
        reckoningId, LockMode.UPGRADE);

    tblReckoning.setPrintDate(sysdate);
    tblReckoning.setPrintedCount(tblReckoning.getPrintedCount() + 1);

    getHibernateTemplate().update(tblReckoning);

    return 0;
  }

  @SuppressWarnings("unchecked")
  public List<ReckoningAcct> getTourReckoningAcctList(String tourNo) {
    StringBuilder sql = new StringBuilder();
    sql.append("select b.reckoningId,c.description,c.unitPrice, ");
    sql.append("c.count,c.amount,c.unit,b.bookingNo,b.number ");
    sql.append("from Booking a, ");
    sql.append("Reckoning b, ");
    sql.append("ReckoningAcct c ");
    sql.append("where a.plan.tourNo=? and a.nameNo=b.bookingNo ");
    sql.append("and b.reckoningId=c.id.reckoningId ");
    sql.append("order by b.bookingNo ");

    Object[] param = { tourNo };
    List<Object[]> list = getHibernateTemplate().find(sql.toString(), param);

    List<ReckoningAcct> reckoningAcctList = new ArrayList<ReckoningAcct>();

    if (!list.isEmpty()) {
      ReckoningAcct reckoningAcct;
      int size = list.size();
      int number = 0;
      String bookingNo = new String();
      Object[] object1;
      Object[] object2;
      // 移除订单低版本的帐单
      for (int i = 0; i < size; i++) {
        if (null != list && i < list.size()) {
          object1 = list.get(0);
          bookingNo = RowDataUtil.getString(object1[6]);
          number = RowDataUtil.getInt(object1[7]);

          // 取此订单帐单最高版本号
          for (int j = 0; j < list.size(); j++) {
            object2 = list.get(j);
            if (bookingNo.equals(RowDataUtil.getString(object2[6])))
              if (number < RowDataUtil.getInt(object2[7]))
                number = RowDataUtil.getInt(object2[7]);
          }

          // 取此订单帐单最高版本号所对应的帐单明细
          for (Object[] obj : list) {
            if (RowDataUtil.getString(obj[6]).equals(bookingNo)
                && RowDataUtil.getInt(obj[7]) == number) {
              reckoningAcct = new ReckoningAcct();
              reckoningAcct.setReckoningId(RowDataUtil.getInt(obj[0]));
              reckoningAcct.setDescription(RowDataUtil.getString(obj[1]));
              reckoningAcct.setUnitPrice(RowDataUtil.getDouble(obj[2]));
              reckoningAcct.setCount(RowDataUtil.getInt(obj[3]));
              reckoningAcct.setAmount(RowDataUtil.getDouble(obj[4]));
              reckoningAcct.setUnit(RowDataUtil.getString(obj[5]));
              reckoningAcct.setBookingNo(RowDataUtil.getString(obj[6]));

              reckoningAcctList.add(reckoningAcct);
            }
          }

          int lastSize = list.size();
          // 移除已取订单的所有帐单
          for (int k = 0; k < lastSize; k++) {
            if (k < list.size() && (list.get(k))[6].equals(bookingNo)) {
              list.remove(k);
              k--;
            }
          }
        }
      }

    }

    return reckoningAcctList;
  }

  private String getReckoningInfo(Reckoning tblReckoning, Reckoning reckoning) {
    String info = "";
    if (reckoning != null && tblReckoning != null) {
      if (!reckoning.getContact().equals(tblReckoning.getContact())) {
        info = info + "联系人," + reckoning.getContact() + ","
            + tblReckoning.getContact() + ",";
      }
      if (!reckoning.getPhone().equals(tblReckoning.getPhone())) {
        info = info + "电话," + reckoning.getPhone() + ","
            + tblReckoning.getPhone() + ",";
      }
      if (!reckoning.getFax().equals(tblReckoning.getFax())) {
        info = info + "传真," + reckoning.getFax() + "," + tblReckoning.getFax()
            + ",";
      }

    }
    return info;
  }
}
