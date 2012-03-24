package com.opentravelsoft.providers.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.Tourist;
import com.opentravelsoft.providers.TouristDao;
import com.opentravelsoft.util.RowDataUtil;
import com.opentravelsoft.util.StringUtil;

@Repository("TouristDao")
public class TouristDaoHibernate extends GenericDaoHibernate<Tourist, String>
    implements TouristDao {
  public TouristDaoHibernate() {
    super(Tourist.class);
  }

  /**
   * 查找客人名单
   */
  @SuppressWarnings("unchecked")
  public List<Tourist> findCustomer(String[] nmno) {
    StringBuilder sb = new StringBuilder();
    sb.append("from Tourist where nmno in (");
    for (int i = 0; i < nmno.length; i++) {
      sb.append("'" + nmno[i].trim() + "',");
    }

    return getHibernateTemplate().find(sb.substring(0, sb.length() - 1) + ")");
  }

  @SuppressWarnings("unchecked")
  public List<Tourist> findByNmno(String[] nmno) {
    StringBuilder sb = new StringBuilder();
    sb.append("from Tourist where nmno in (");
    for (int i = 0; i < nmno.length; i++) {
      sb.append("'" + nmno[i].trim() + "',");
    }

    return getHibernateTemplate().find(sb.substring(0, sb.length() - 1) + ")");
  }

  @SuppressWarnings("unchecked")
  public Tourist findCustomerByNmno(String nmno) {
    StringBuilder sb = new StringBuilder();
    sb.append("from Tourist where nmno=? ");
    Object[] param = { nmno };
    Tourist tcustomer = null;
    List<Tourist> list = getHibernateTemplate().find(sb.toString(), param);

    if (list.size() > 0) {
      tcustomer = list.get(0);
    }

    return tcustomer;
  }

  @SuppressWarnings("unchecked")
  public Tourist findOneTourist(String bookingNo) {
    StringBuilder sb = new StringBuilder();
    sb.append("from Tourist where booking.nameNo=? ");
    Object[] param = { bookingNo };
    Tourist tcustomer = null;
    List<Tourist> list = getHibernateTemplate().find(sb.toString(), param);

    if (list.size() > 0) {
      tcustomer = list.get(0);
    }

    return tcustomer;
  }

  /**
   * 成团使用
   * 
   * @param teamId
   * @param userId
   * @param lineName
   * @param startDate
   * @param endDate
   * @return
   */
  @SuppressWarnings("unchecked")
  public List<Tourist> findCustomer(int teamId, int userId, String lineName,
      Date startDate, Date endDate) {
    // 成团使用
    StringBuilder sb = new StringBuilder();
    List<Object> params = new ArrayList<Object>();

    sb.append("from Tourist ");
    sb.append("where booking.confirmStatus='1' and del='N' ");
    sb.append("and leaderKey='N' and recType='A' ");
    // 预订员所属部门
    if (teamId != 0) {
      sb.append("and booking.plan.team.teamId=? ");
      params.add(teamId);
    }

    if (userId != 0) {
      sb.append("and booking.plan.assigned.userId=" + userId + " ");
    }
    if (startDate != null && endDate != null) {
      sb.append("and booking.plan.outDate>=? and booking.plan.outDate<=? ");
      params.add(startDate);
      params.add(endDate);
    }

    if (StringUtil.hasLength(lineName)) {
      sb.append("and booking.plan.line.lineName like ? ");
      params.add("%" + lineName + "%");
    }

    Object[] param = null;
    if (params.size() > 0) {
      param = new Object[params.size()];
      for (int i = 0; i < params.size(); i++) {
        param[i] = params.get(i);
      }
    }
    return getHibernateTemplate().find(sb.toString(), param);
  }

  /**
   * 取得团人名单（分团）
   * 
   * @param tourNo
   * @return
   */
  @SuppressWarnings("unchecked")
  public List<Tourist> findCustomer(String tourNo) {
    // 取得团人名单（分团）
    StringBuilder sql = new StringBuilder();
    sql.append("from Tourist where booking.plan.tourNo=? and del='N' ");
    Object[] param = { tourNo };

    List<Tourist> list = getHibernateTemplate().find(sql.toString(), param);

    // 取得房间类型
    StringBuilder sb = new StringBuilder();
    sb.append("select value,text from Lists where listName='RoomType' ");
    List<Object[]> list1 = getHibernateTemplate().find(sb.toString());

    String roomType;

    for (Tourist guest : list) {
      if (guest.getTourKey().equals("1"))
        guest.setTourKeyName("独立成团");
      else if (guest.getTourKey().equals("2"))
        guest.setTourKeyName("不可分拆");
      else
        guest.setTourKeyName("无");

      roomType = RowDataUtil.getString(guest.getRoomType());
      for (int i = 0; i < list1.size(); i++) {
        if (RowDataUtil.getString(list1.get(i)[0]).equals(roomType)) {
          guest.setRoomTypeName(RowDataUtil.getString(list1.get(i)[1]));
          break;
        }
      }
    }

    return list;
  }

  /**
   * 
   * 取得需要并团人名单（并团使用）
   * 
   * @param groupId 线路所属部门
   * @param userId 线路专管员
   * @param lineName 线路名称
   * @param startDate 出团时间
   * @param endDate 出团时间
   * @param nameKey
   * @return
   */
  @SuppressWarnings("unchecked")
  public List<Tourist> findCustomer(int teamId, int userId, String lineName,
      Date startDate, Date endDate, char nameKey) {
    StringBuilder sb = new StringBuilder();
    List<Object> params = new ArrayList<Object>();
    sb.append("from Tourist ");
    sb.append("where booking.confirmStatus='1' and del='N' ");
    sb.append("and leaderKey<>'Y' and recType='A' ");

    if (teamId != 0)
      sb.append("and booking.plan.team.teamId=" + teamId + " ");
    if (userId != 0)
      sb.append("and booking.plan.assigned.userId=" + userId + " ");
    if (startDate != null && endDate != null) {
      sb.append("and booking.plan.outDate>=? and booking.plan.outDate<=? ");
      params.add(startDate);
      params.add(endDate);
    }

    if (StringUtil.hasLength(lineName)) {
      sb.append("and booking.plan.line.lineName like ? ");
      params.add("%" + lineName + "%");
    }

    sb.append("order by nmno ");

    Object[] param = null;
    if (params.size() > 0) {
      param = new Object[params.size()];
      for (int i = 0; i < params.size(); i++) {
        param[i] = params.get(i);
      }
    }
    List<Tourist> list = getHibernateTemplate().find(sb.toString(), param);

    StringBuilder sb1 = new StringBuilder();
    sb1.append("select value,text from Lists where listName='RoomType' ");
    List<Object[]> list1 = getHibernateTemplate().find(sb1.toString());

    for (Tourist tourCustomer : list) {
      if (tourCustomer.getTourKey().equals("1"))
        tourCustomer.setTourKeyName("独立成团");
      else if (tourCustomer.getTourKey().equals("2"))
        tourCustomer.setTourKeyName("不可分拆");
      else
        tourCustomer.setTourKeyName("无");

      String roomType = RowDataUtil.getString(tourCustomer.getRoomType());
      for (int i = 0; i < list1.size(); i++) {
        if (RowDataUtil.getString(list1.get(i)[0]).equals(roomType)) {
          tourCustomer.setRoomTypeName(RowDataUtil.getString(list1.get(i)[1]));
          break;
        }
      }

    }

    return list;
  }

}
