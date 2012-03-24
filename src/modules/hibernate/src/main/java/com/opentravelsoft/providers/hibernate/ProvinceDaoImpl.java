package com.opentravelsoft.providers.hibernate;

import java.util.List;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.Province;
import com.opentravelsoft.providers.ProvinceDao;

@Repository("ProvinceDao")
public class ProvinceDaoImpl extends GenericDaoHibernate<Province, String>
    implements ProvinceDao {

  public ProvinceDaoImpl() {
    super(Province.class);
  }

  @SuppressWarnings("unchecked")
  public List<Province> getStateByCountry(String country) {
    DetachedCriteria criteria = DetachedCriteria.forClass(Province.class);
    criteria.add(Restrictions.eq("country", country));
    criteria.addOrder(Order.asc("cnName"));
    criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
    return getHibernateTemplate().findByCriteria(criteria);
  }

}
