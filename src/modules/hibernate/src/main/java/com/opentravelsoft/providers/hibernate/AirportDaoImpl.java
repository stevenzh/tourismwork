package com.opentravelsoft.providers.hibernate;

import java.util.Iterator;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.Airport;
import com.opentravelsoft.providers.AirportDao;
import com.opentravelsoft.util.PaginationSupport;
import com.opentravelsoft.util.StringUtil;

@Repository("AirportDao")
public class AirportDaoImpl extends GenericDaoHibernate<Airport, String>
    implements AirportDao {
  public AirportDaoImpl() {
    super(Airport.class);
  }

  public int delete(Airport airways) {
    HibernateTemplate template = getHibernateTemplate();
    Airport rlt = (Airport) template.get(Airport.class, airways.getCode(),
        LockMode.PESSIMISTIC_WRITE);
    if (null == rlt) {
      return -1;
    }
    rlt.setIsActive((byte) 0);
    template.update(rlt);
    return 0;
  }

  @SuppressWarnings("rawtypes")
  public PaginationSupport getAirportList(String country, String city,
      String kenDelkey, int fromRecord, int pageSize) {
    DetachedCriteria airportCriteria = DetachedCriteria.forClass(Airport.class,
        "b");
    airportCriteria.createAlias("b.city", "c");
    airportCriteria.createAlias("c.country", "n");

    if (StringUtil.hasLength(country))
      airportCriteria.add(Restrictions.eq("n.countryId", country));

    if (StringUtil.hasLength(city))
      airportCriteria.add(Restrictions.eq("c.citycd", city));

    PaginationSupport support = findPageByCriteria(airportCriteria, pageSize,
        fromRecord);

    Iterator iter = support.getItems().iterator();
    while (iter.hasNext()) {
      Airport airport = (Airport) iter.next();
      airport.setCityName(airport.getCity().getCitynm());
      airport.setCountryName(airport.getCity().getCountry().getName());
    }

    return support;
  }

  @SuppressWarnings("unchecked")
  public List<Airport> getAirportList(boolean isActive) {
    DetachedCriteria criteria = DetachedCriteria.forClass(Airport.class);

    if (isActive)
      criteria.add(Restrictions.eq("isActive", (byte) 1));

    criteria.addOrder(Order.asc("name"));
    criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

    return getHibernateTemplate().findByCriteria(criteria);
  }

}
