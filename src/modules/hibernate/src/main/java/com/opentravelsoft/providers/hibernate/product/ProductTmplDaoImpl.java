package com.opentravelsoft.providers.hibernate.product;

import java.util.List;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.ProductTmpl;
import com.opentravelsoft.entity.TblExpenseTmpl;
import com.opentravelsoft.providers.hibernate.GenericDaoHibernate;
import com.opentravelsoft.providers.product.ProductTmplDao;
import com.opentravelsoft.util.StringUtil;

@Repository("LineTmplDao")
public class ProductTmplDaoImpl extends
    GenericDaoHibernate<ProductTmpl, Integer> implements ProductTmplDao {

  public ProductTmplDaoImpl() {
    super(ProductTmpl.class);
  }

  public ProductTmpl getProductTmpl(int tmplId) {
    HibernateTemplate template = getHibernateTemplate();
    ProductTmpl tmpl = (ProductTmpl) template.get(ProductTmpl.class, tmplId);
    return tmpl;
  }

  public int deleteTmpl(int tmplId) {
    ProductTmpl tmpl = (ProductTmpl) getHibernateTemplate().get(
        ProductTmpl.class, tmplId);
    if (null == tmpl)
      return -1;

    getHibernateTemplate().save(tmpl);
    return 0;
  }

  @SuppressWarnings("unchecked")
  public List<ProductTmpl> getProductTmplByType(int teamId, int itemId,
      String destCode) {
    DetachedCriteria criteria = DetachedCriteria.forClass(ProductTmpl.class,
        "p");
    criteria.createAlias("p.team", "t");
    criteria.createAlias("p.item", "m");

    if (teamId != 0)
      criteria.add(Restrictions.eq("t.teamId", teamId));
    if (itemId != 0)
      criteria.add(Restrictions.eq("m.itemCode", itemId));

    if (StringUtil.hasLength(destCode))
      criteria.add(Restrictions.like("p.destCode", destCode, MatchMode.START));

    criteria.addOrder(Order.asc("sortOrder"));
    criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

    return getHibernateTemplate().findByCriteria(criteria);
  }

  public int insertProductTmpl(ProductTmpl tmpl) {
    getHibernateTemplate().save(tmpl);
    return 0;
  }

  public int updateProductTmpl(ProductTmpl tipsTmpl) {
    if (tipsTmpl.getId() >= 0) {
      ProductTmpl tmpl = (ProductTmpl) getHibernateTemplate().get(
          ProductTmpl.class, tipsTmpl.getId());

      if (null == tmpl)
        return -1;

      tmpl.setSubject(tipsTmpl.getSubject());
      tmpl.setContent(tipsTmpl.getContent());
      tmpl.setItem(tipsTmpl.getItem());
      tmpl.setTeam(tipsTmpl.getTeam());
      getHibernateTemplate().update(tmpl);
    } else {
      getHibernateTemplate().save(tipsTmpl);
    }

    return 0;
  }

  @SuppressWarnings("unchecked")
  public List<TblExpenseTmpl> getExpenseTmpl() {
    DetachedCriteria criteria = DetachedCriteria.forClass(TblExpenseTmpl.class);
    criteria.addOrder(Order.asc("number)"));
    criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

    return getHibernateTemplate().findByCriteria(criteria);
  }

  @SuppressWarnings("unchecked")
  public List<ProductTmpl> getProductTmplByType(int teamId, String itemType) {
    DetachedCriteria criteria = DetachedCriteria.forClass(ProductTmpl.class,
        "p");
    criteria.createAlias("p.team", "t");
    criteria.createAlias("p.item", "m");

    if (teamId != 0)
      criteria.add(Restrictions.eq("t.teamId", teamId));
    if (StringUtil.hasLength(itemType))
      criteria.add(Restrictions.eq("m.itemCode", itemType));

    criteria.addOrder(Order.asc("sortOrder"));
    criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

    return getHibernateTemplate().findByCriteria(criteria);
  }

}
