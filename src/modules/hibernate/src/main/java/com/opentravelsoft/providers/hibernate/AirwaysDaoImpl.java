package com.opentravelsoft.providers.hibernate;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.Airways;
import com.opentravelsoft.providers.AirwaysDao;
import com.opentravelsoft.util.PaginationSupport;

@Repository("AirwaysDao")
public class AirwaysDaoImpl extends GenericDaoHibernate<Airways, String>
    implements AirwaysDao {
  public AirwaysDaoImpl() {
    super(Airways.class);
  }

  @SuppressWarnings("unchecked")
  public List<Airways> getAll(boolean usable) {
    DetachedCriteria criteria = DetachedCriteria.forClass(Airways.class);

    if (usable)
      criteria.add(Restrictions.eq("isActive", true));

    criteria.addOrder(Order.asc("name"));
    criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

    return getHibernateTemplate().findByCriteria(criteria);
  }

  public Airways getDetail(String airwaysId) {
    HibernateTemplate template = getHibernateTemplate();
    Airways airways = (Airways) template.get(Airways.class, airwaysId);

    return airways;
  }

  public int delete(Airways airways) {
    HibernateTemplate template = getHibernateTemplate();
    Airways rlt = (Airways) template.get(Airways.class, airways.getCode(),
        LockMode.UPGRADE);
    if (null == rlt) {
      return -1;
    }
    rlt.setIsActive((byte) 0);

    template.update(rlt);
    return 0;
  }

  public PaginationSupport getAirwaysList(boolean usable, int fromRecord,
      int pageSize) {
    DetachedCriteria criteria = DetachedCriteria.forClass(Airways.class);

    if (usable)
      criteria.add(Restrictions.eq("isActive", true));

    PaginationSupport support = findPageByCriteria(criteria, pageSize,
        fromRecord);

    return support;
  }

}
