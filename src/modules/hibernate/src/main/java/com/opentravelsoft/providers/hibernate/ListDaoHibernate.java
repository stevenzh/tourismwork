package com.opentravelsoft.providers.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.opentravelsoft.util.LabelValueBean;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.Lists;
import com.opentravelsoft.providers.ListDao;
import com.opentravelsoft.util.RowDataUtil;
import com.opentravelsoft.util.StringUtil;

@Repository("ListDao")
public class ListDaoHibernate extends GenericDaoHibernate<Lists, Integer>
    implements ListDao {

  public ListDaoHibernate() {
    super(Lists.class);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.opentravelsoft.providers.ListDao#getList(java.lang.String)
   */
  public List<LabelValueBean> getList(String listName) {
    List<Lists> list = getListByType(listName);
    List<LabelValueBean> bs = new ArrayList<LabelValueBean>();

    for (Lists obj : list) {
      LabelValueBean business = new LabelValueBean();
      business.setValue(RowDataUtil.getString(obj.getValue()));
      business.setLabel(RowDataUtil.getString(obj.getText()));
      bs.add(business);
    }
    return bs;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.opentravelsoft.providers.ListDao#getListByType(java.lang.String)
   */
  @SuppressWarnings("unchecked")
  public List<Lists> getListByType(String listName) {
    DetachedCriteria criteria = DetachedCriteria.forClass(Lists.class);
    if (StringUtil.hasLength(listName))
      criteria.add(Restrictions.eq("listName", listName));
    criteria.addOrder(Order.asc("listName"));
    criteria.addOrder(Order.asc("sortOrder"));
    criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
    List<Lists> list = getHibernateTemplate().findByCriteria(criteria);

    return list;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.opentravelsoft.providers.ListDao#updateList(com.opentravelsoft
   * .ebiz.entity.Lists)
   */
  public int updateList(Lists config) {
    Lists list = null;
    if (config.getEntryId() > 0) {
      list = (Lists) getHibernateTemplate().get(Lists.class,
          config.getEntryId());
      if (null == list)
        return -1;
    } else
      list = new Lists();

    list.setValue(config.getValue());
    list.setText(config.getText());
    list.setListName(config.getListName());
    list.setSortOrder(config.getSortOrder());

    getHibernateTemplate().saveOrUpdate(list);
    return 0;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.opentravelsoft.providers.ListDao#getListType()
   */
  @SuppressWarnings("unchecked")
  public Set<String> getListType() {
    StringBuilder sb = new StringBuilder();
    sb.append("select distinct listName ");
    sb.append("from com.opentravelsoft.entity.Lists ");
    sb.append("order by listName ");
    List<String> list = getHibernateTemplate().find(sb.toString());
    Set<String> set = new TreeSet<String>();
    for (String str : list) {
      set.add(str);
    }

    return set;
  }

}
