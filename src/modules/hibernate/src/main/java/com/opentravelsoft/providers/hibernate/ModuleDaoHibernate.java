package com.opentravelsoft.providers.hibernate;

import java.util.List;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.Module;
import com.opentravelsoft.providers.ModuleDao;

/**
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.2 $ $Date: 2009/03/09 15:37:04 $
 */
@Repository("ModuleDao")
public class ModuleDaoHibernate extends GenericDaoHibernate<Module, Long>
    implements ModuleDao {

  public ModuleDaoHibernate() {
    super(Module.class);
  }

  @SuppressWarnings("unchecked")
  public List<Module> getModuleList(boolean active) {
    DetachedCriteria criteria = DetachedCriteria.forClass(Module.class);
    if (active)
      criteria.add(Restrictions.eq("isActive", true));
    criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
    criteria.addOrder(Order.asc("sortOrder"));
    return getHibernateTemplate().findByCriteria(criteria);
  }

  @SuppressWarnings("unchecked")
  public List<Module> queryModle(String keyword) {
    StringBuilder sql = new StringBuilder();
    sql.append("from Module ");
    sql.append("where moduleName like '%");
    sql.append(keyword + "%'");

    return getHibernateTemplate().find(sql.toString());
  }

}
