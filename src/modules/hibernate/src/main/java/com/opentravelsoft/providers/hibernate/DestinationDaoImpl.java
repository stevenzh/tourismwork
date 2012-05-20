package com.opentravelsoft.providers.hibernate;

import java.util.ArrayList;
import java.util.List;

import com.opentravelsoft.util.LabelValueBean;
import org.hibernate.LockMode;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.Destination;
import com.opentravelsoft.providers.DestinationDao;
import com.opentravelsoft.util.RowDataUtil;

@Repository("DestinationDao")
public class DestinationDaoImpl extends
    GenericDaoHibernate<Destination, Integer> implements DestinationDao {

  public DestinationDaoImpl() {
    super(Destination.class);
  }

  @SuppressWarnings("unchecked")
  public List<Destination> getAllDestination() {
    DetachedCriteria criteria = DetachedCriteria.forClass(Destination.class);

    criteria.addOrder(Order.asc("code"));
    criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

    return getHibernateTemplate().findByCriteria(criteria);
  }

  public int delDestination(int destId) {
    // 取得父分类ID
    Destination dest = (Destination) getHibernateTemplate().get(
        Destination.class, destId, LockMode.PESSIMISTIC_WRITE);
    String code = "";
    String parentId = "";
    if (null == dest) {
      return -1;

    } else {
      code = dest.getCode();
      parentId = dest.getParent().getCode();
    }
    getHibernateTemplate().delete(dest);

    // 把属于该分类的产品都转移到父结点
    // 修改对应计划
    StringBuilder sb = new StringBuilder();
    sb.append("update Line ");
    sb.append("set destination.code=? where destination.code=? ");
    Object[] param = { parentId, code };
    getHibernateTemplate().bulkUpdate(sb.toString(), param);
    return 0;
  }

  @SuppressWarnings("unchecked")
  public List<List<LabelValueBean>> getRegionGroupList() {
    StringBuilder sql = new StringBuilder();
    sql.append("select distinct code, cnName ");
    sql.append("from Destination ");
    sql.append("where length(trim(code)) <= 4 order by code");

    List<Object[]> list = getHibernateTemplate().find(sql.toString());
    List<LabelValueBean> temp = null;
    List<List<LabelValueBean>> ret = new ArrayList<List<LabelValueBean>>();

    LabelValueBean bean = null;
    String s = "";
    for (Object[] obj : list) {
      String code = (String) obj[0];
      bean = new LabelValueBean();
      if (!s.equals(code.substring(0, 2))) {
        if (null != temp)
          ret.add(temp);
        s = code.substring(0, 2);
        temp = new ArrayList<LabelValueBean>();
        bean.setLabel(RowDataUtil.getString(obj[0]));
        bean.setValue(RowDataUtil.getString(obj[1]));
        temp.add(bean);
      } else {
        bean.setLabel(RowDataUtil.getString(obj[0]));
        bean.setValue(RowDataUtil.getString(obj[1]));
        temp.add(bean);
      }
    }

    if (null != temp)
      ret.add(temp);

    return ret;
  }

  @SuppressWarnings("unchecked")
  public List<List<LabelValueBean>> getSubRegions(String regionId) {
    StringBuilder sql = new StringBuilder();
    sql.append("select distinct code, cnName ");
    sql.append("from Destination ");
    sql.append("where trim(code) like ? and length(trim(code))>3 order by code");

    Object[] param = { regionId.trim() + "%" };

    List<Object[]> list = getHibernateTemplate().find(sql.toString(), param);
    List<LabelValueBean> temp = null;
    List<List<LabelValueBean>> ret = new ArrayList<List<LabelValueBean>>();

    String s = "";
    for (Object[] obj : list) {
      String code = (String) obj[0];
      LabelValueBean bean = new LabelValueBean();
      if (!s.equals(code.substring(0, 4))) {
        if (null != temp)
          ret.add(temp);
        s = code.substring(0, 4);
        temp = new ArrayList<LabelValueBean>();
        bean.setLabel(RowDataUtil.getString(obj[0]).trim());
        bean.setValue(RowDataUtil.getString(obj[1]).trim());
        temp.add(bean);
      } else {
        bean.setLabel(RowDataUtil.getString(obj[0]).trim());
        bean.setValue(RowDataUtil.getString(obj[1]).trim());
        temp.add(bean);
      }
    }

    if (null != temp)
      ret.add(temp);

    return ret;
  }

  @SuppressWarnings("unchecked")
  public List<Destination> getTopDestination() {
    StringBuilder sql = new StringBuilder();
    sql.append("from Destination ");
    sql.append("where length(trim(code))<3 ");
    sql.append("order by code ");

    return getHibernateTemplate().find(sql.toString());
  }

  @SuppressWarnings("unchecked")
  public List<Destination> getRegionList() {
    List<Object[]> list;

    StringBuilder sql = new StringBuilder();
    sql.append("select distinct code, cnName, classType ");
    sql.append("from Destination ");
    sql.append("where length(trim(code)) <= 4 order by code");
    list = getHibernateTemplate().find(sql.toString());

    List<Destination> regionList = new ArrayList<Destination>();

    Destination region = null;
    for (Object[] obj : list) {
      String code = RowDataUtil.getString(obj[0]);
      // code.length() == 2为区域代码，比如code为11时即为东南亚
      if (code.length() == 2) {
        String regionName = RowDataUtil.getString(obj[1]);
        for (Object[] obj4 : list) {
          String code4 = RowDataUtil.getString(obj4[0]);
          // code.length() == 4为具体的目的地代码，比如code为1101时即为泰国
          if (code4.length() == 4 && code4.substring(0, 2).equals(code)) {
            region = new Destination();
            region.setCode(RowDataUtil.getString(obj4[0]));
            region.setCnName(RowDataUtil.getString(obj4[1]));
            region.setClassType(RowDataUtil.getChar(obj4[2]));
            region.setParent(new Destination());
            region.getParent().setCnName(regionName);
            region.getParent().setCode(code);

            regionList.add(region);
          }
        }
      }
    }

    return regionList;
  }
}
