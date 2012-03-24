package com.opentravelsoft.providers.hibernate.vacation;

import java.util.List;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.vacation.LineTheme;
import com.opentravelsoft.providers.LineThemeDao;
import com.opentravelsoft.providers.hibernate.GenericDaoHibernate;

@Repository("LineThemeDao")
public class LineThemeDaoImpl extends GenericDaoHibernate<LineTheme, String>
    implements LineThemeDao {
  public LineThemeDaoImpl() {
    super(LineTheme.class);
  }

  @SuppressWarnings("unchecked")
  public List<LineTheme> getTypeList() {
    DetachedCriteria criteria = DetachedCriteria.forClass(LineTheme.class);
    criteria.addOrder(Order.asc("code"));
    criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

    return getHibernateTemplate().findByCriteria(criteria);
  }

}
