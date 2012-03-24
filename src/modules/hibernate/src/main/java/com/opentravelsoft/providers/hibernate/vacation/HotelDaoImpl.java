package com.opentravelsoft.providers.hibernate.vacation;

import java.util.List;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.vacation.Hotel;
import com.opentravelsoft.providers.HotelDao;
import com.opentravelsoft.providers.hibernate.GenericDaoHibernate;
import com.opentravelsoft.util.StringUtil;

@Repository("HotelDao")
public class HotelDaoImpl extends GenericDaoHibernate<Hotel, String> implements
    HotelDao {
  public HotelDaoImpl() {
    super(Hotel.class);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Hotel> getHotels(String countryNo, String provinceNo,
      String cityNo, String hotelName) {
    DetachedCriteria criteria = DetachedCriteria.forClass(Hotel.class);
    criteria.add(Restrictions.like("hotJc", hotelName));
    if (StringUtil.hasLength(cityNo))
      criteria.add(Restrictions.eq("city.citycd", cityNo));
    else if (StringUtil.hasLength(provinceNo))
      criteria.add(Restrictions.eq("city.province.code", provinceNo));
    else if (StringUtil.hasLength(countryNo))
      criteria.add(Restrictions.eq("city.country.countrycd", countryNo));

    criteria.addOrder(Order.asc("citynm"));
    criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

    return getHibernateTemplate().findByCriteria(criteria);
  }

}
