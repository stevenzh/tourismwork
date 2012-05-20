package com.opentravelsoft.providers.hibernate;

import java.util.List;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.Category;
import com.opentravelsoft.providers.CategoryDao;

@Repository("CategoryDao")
public class CategoryDaoImpl extends GenericDaoHibernate<Category, Integer>
    implements CategoryDao {

  public CategoryDaoImpl() {
    super(Category.class);
  }

  @SuppressWarnings("unchecked")
  public List<Category> getCategory() {
    DetachedCriteria criteria = DetachedCriteria.forClass(Category.class);
    criteria.addOrder(Order.asc("catId"));
    criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

    return getHibernateTemplate().findByCriteria(criteria);
  }

}
