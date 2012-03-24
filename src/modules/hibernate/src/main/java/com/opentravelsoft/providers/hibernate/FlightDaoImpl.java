package com.opentravelsoft.providers.hibernate;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.Flight;
import com.opentravelsoft.providers.FlightDao;
import com.opentravelsoft.util.PaginationSupport;
import com.opentravelsoft.util.StringUtil;

@Repository("FlightDao")
public class FlightDaoImpl extends GenericDaoHibernate<Flight, String>
    implements FlightDao {
  public FlightDaoImpl() {
    super(Flight.class);
  }

  public PaginationSupport getFlightList(String aireways, String lvAirport,
      String goAirport, int fromRecord, int moveCount) {
    DetachedCriteria criteria = DetachedCriteria.forClass(Flight.class, "b");

    if (StringUtil.hasLength(aireways))
      criteria.createAlias("b.airways", "c");
    if (StringUtil.hasLength(lvAirport))
      criteria.createAlias("b.leavingAirport", "d");
    if (StringUtil.hasLength(goAirport))
      criteria.createAlias("b.goingAirport", "e");

    if (StringUtil.hasLength(aireways))
      criteria.add(Restrictions.eq("c.code", aireways));
    if (StringUtil.hasLength(lvAirport))
      criteria.add(Restrictions.eq("d.code", aireways));
    if (StringUtil.hasLength(goAirport))
      criteria.add(Restrictions.eq("e.code", aireways));

    PaginationSupport support = findPageByCriteria(criteria, moveCount,
        fromRecord);

    return support;

  }

}
