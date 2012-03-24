package com.opentravelsoft.providers.hibernate.product;

import java.util.List;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.ProductItem;
import com.opentravelsoft.entity.product.ProductType;
import com.opentravelsoft.providers.hibernate.GenericDaoHibernate;
import com.opentravelsoft.providers.product.ProductItemDao;

@Repository("ProductItemDao")
public class ProductItemDaoImpl extends GenericDaoHibernate<ProductItem, Long>
    implements ProductItemDao {
  public ProductItemDaoImpl() {
    super(ProductItem.class);
  }

  @SuppressWarnings("unchecked")
  public List<ProductItem> getProductItems(boolean tmpl) {
    if (!tmpl)
      return getHibernateTemplate().loadAll(ProductItem.class);

    DetachedCriteria criteria = DetachedCriteria.forClass(ProductItem.class);
    criteria.add(Restrictions.eq("isTmpl", true));
    criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

    return getHibernateTemplate().findByCriteria(criteria);
  }

  @SuppressWarnings("unchecked")
  public List<ProductItem> getProductItems(ProductType type) {
    DetachedCriteria criteria = DetachedCriteria.forClass(ProductItem.class,
        "p");
    criteria.createAlias("p.category", "c");
    criteria.add(Restrictions.eq("c.code", type.toString()));
    criteria.add(Restrictions.eq("p.isActive", true));

    criteria.addOrder(Order.asc("p.sortOrder"));
    criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

    return getHibernateTemplate().findByCriteria(criteria);
  }

  @SuppressWarnings("unchecked")
  public ProductItem getProdutItem(String itemCode) {
    DetachedCriteria criteria = DetachedCriteria.forClass(ProductItem.class);
    criteria.add(Restrictions.eq("itemCode", itemCode));
    criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
    List<ProductItem> list = getHibernateTemplate().findByCriteria(criteria);
    ProductItem item = null;
    if (list.size() > 0)
      item = list.get(0);

    return item;
  }
}
