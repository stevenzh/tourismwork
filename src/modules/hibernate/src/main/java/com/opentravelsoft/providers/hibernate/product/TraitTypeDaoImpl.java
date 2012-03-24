package com.opentravelsoft.providers.hibernate.product;

import java.util.List;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.TraitType;
import com.opentravelsoft.providers.hibernate.GenericDaoHibernate;
import com.opentravelsoft.providers.product.TraitTypeDao;

@Repository("TraitTypeDao")
public class TraitTypeDaoImpl extends GenericDaoHibernate<TraitType, Long>
    implements TraitTypeDao {
  public TraitTypeDaoImpl() {
    super(TraitType.class);
  }

  @SuppressWarnings("unchecked")
  public List<TraitType> getTypeList() {
    DetachedCriteria criteria = DetachedCriteria.forClass(TraitType.class);
    criteria.addOrder(Order.asc("traitId"));
    criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

    return getHibernateTemplate().findByCriteria(criteria);
  }

}
