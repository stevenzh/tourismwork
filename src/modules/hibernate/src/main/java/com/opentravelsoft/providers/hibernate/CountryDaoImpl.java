package com.opentravelsoft.providers.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.Country;
import com.opentravelsoft.providers.CountryDao;
import com.opentravelsoft.util.RowDataUtil;

@Repository("CountryDao")
public class CountryDaoImpl extends GenericDaoHibernate<Country, String>
    implements CountryDao {

  public CountryDaoImpl() {
    super(Country.class);
  }

  @SuppressWarnings("unchecked")
  public List<Country> getAllCountry() {
    DetachedCriteria criteria = DetachedCriteria.forClass(Country.class);
    criteria.addOrder(Order.asc("name"));
    criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

    return getHibernateTemplate().findByCriteria(criteria);
  }

  @SuppressWarnings("unchecked")
  public List<Country> getCountry() {
    DetachedCriteria criteria = DetachedCriteria.forClass(Country.class);
    criteria.add(Restrictions.eq("enabled", (byte) 1));
    criteria.addOrder(Order.asc("name"));
    criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

    return getHibernateTemplate().findByCriteria(criteria);
  }

  @SuppressWarnings("unchecked")
  public List<Country> getVisaCountry() {
    StringBuilder sql = new StringBuilder();
    sql.append("select countryId,name ");
    sql.append("from Country where countryId in (");
    sql.append("select country from VisaHelp ");
    sql.append("where EDate>=current_date())");
    sql.append("and enabled=1 ");
    sql.append("order by name ");
    List<Object[]> list = getHibernateTemplate().find(sql.toString());
    List<Country> ret = new ArrayList<Country>();
    Country country = null;
    for (Object[] obj : list) {
      country = new Country();
      country.setCountryId(RowDataUtil.getString(obj[0]));
      country.setName(RowDataUtil.getString(obj[1]));
      ret.add(country);
    }
    return ret;
  }

}
