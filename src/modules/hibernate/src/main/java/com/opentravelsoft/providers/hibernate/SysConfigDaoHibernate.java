package com.opentravelsoft.providers.hibernate;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.SysConfig;
import com.opentravelsoft.providers.SysConfigDao;
import com.opentravelsoft.util.StringUtil;

@Repository("SysConfigDao")
public class SysConfigDaoHibernate extends GenericDaoHibernate<SysConfig, Long>
    implements SysConfigDao {

  public SysConfigDaoHibernate() {
    super(SysConfig.class);
  }

  @SuppressWarnings("unchecked")
  public String getSysConfig(String name) {
    DetachedCriteria criteria = DetachedCriteria.forClass(SysConfig.class);
    criteria.add(Restrictions.eq("name", name));
    criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
    List<SysConfig> config = getHibernateTemplate().findByCriteria(criteria);
    if (config.size() > 0)
      return config.get(0).getValue();
    else
      return "";
  }

  @SuppressWarnings("unchecked")
  public Set<String> getCategory() {
    StringBuilder sb = new StringBuilder();
    sb.append("select distinct category from SysConfig ");
    sb.append("order by category ");
    List<String> list = getHibernateTemplate().find(sb.toString());
    Set<String> set = new TreeSet<String>();
    for (String str : list) {
      set.add(str);
    }
    return set;
  }

  @SuppressWarnings("unchecked")
  public List<SysConfig> getConfigByCategory(String category) {
    DetachedCriteria criteria = DetachedCriteria.forClass(SysConfig.class);
    if (StringUtil.hasLength(category))
      criteria.add(Restrictions.eq("category", category));

    criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
    List<SysConfig> list = getHibernateTemplate().findByCriteria(criteria);

    return list;
  }

}
