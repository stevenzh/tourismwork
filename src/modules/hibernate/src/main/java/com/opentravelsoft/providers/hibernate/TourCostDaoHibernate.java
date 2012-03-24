package com.opentravelsoft.providers.hibernate;

import com.opentravelsoft.entity.TourCost;
import com.opentravelsoft.providers.TourCostDao;

public class TourCostDaoHibernate extends GenericDaoHibernate<TourCost, Long>
    implements TourCostDao {

  public TourCostDaoHibernate() {
    super(TourCost.class);
  }

}
