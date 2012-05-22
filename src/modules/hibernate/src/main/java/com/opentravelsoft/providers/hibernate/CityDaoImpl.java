package com.opentravelsoft.providers.hibernate;

import java.util.List;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.City;
import com.opentravelsoft.providers.CityDao;

@Repository("CityDao")
public class CityDaoImpl extends GenericDaoHibernate<City, String> implements
    CityDao {
  public CityDaoImpl() {
    super(City.class);
  }

  @SuppressWarnings("unchecked")
  public List<City> getAllCity() {
    DetachedCriteria criteria = DetachedCriteria.forClass(City.class);
    criteria.addOrder(Order.asc("citynm"));
    criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

    return getHibernateTemplate().findByCriteria(criteria);
  }

  @SuppressWarnings("unchecked")
  public List<City> getLineOutCity() {
    DetachedCriteria criteria = DetachedCriteria.forClass(City.class);
    criteria.add(Restrictions.eq("inOut", 'N'));
    criteria.addOrder(Order.asc("citynm"));
    criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

    return getHibernateTemplate().findByCriteria(criteria);
  }

  @SuppressWarnings("unchecked")
  public List<City> getCitysByNation(String nationCode) {
    DetachedCriteria criteria = DetachedCriteria.forClass(City.class, "c");
    criteria.createAlias("c.country", "n");
    // criteria.add(Restrictions.eq("c.inOut", "N"));
    criteria.add(Restrictions.eq("n.countryId", nationCode));
    criteria.addOrder(Order.asc("c.citynm"));
    criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

    return getHibernateTemplate().findByCriteria(criteria);
  }

  @SuppressWarnings("unchecked")
  public List<City> getCitysByProvince(String provinceId) {
    DetachedCriteria criteria = DetachedCriteria.forClass(City.class, "c");
    criteria.createAlias("c.province", "p");
    criteria.add(Restrictions.eq("p.code", provinceId));
    criteria.addOrder(Order.asc("c.citynm"));
    criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

    return getHibernateTemplate().findByCriteria(criteria);
  }

}
