package com.opentravelsoft.providers.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.Sight;
import com.opentravelsoft.entity.SightPhoto;
import com.opentravelsoft.entity.product.SightTrait;
import com.opentravelsoft.providers.SightDao;
import com.opentravelsoft.util.PaginationSupport;
import com.opentravelsoft.util.StringUtil;

@Repository("SightDao")
public class SightDaoImpl extends GenericDaoHibernate<Sight, String> implements
    SightDao {
  public SightDaoImpl() {
    super(Sight.class);
  }

  @SuppressWarnings("unchecked")
  public List<SightTrait> getSightPicMangeList(String sightNo) {
    StringBuilder sql = new StringBuilder();
    sql.append("from Sight ");
    sql.append("where sightNo = ? ");
    sql.append("order by recNo ");

    Object[] params = { sightNo };
    List<SightPhoto> list = getHibernateTemplate().find(sql.toString(), params);
    List<SightTrait> lineSightTrait = new ArrayList<SightTrait>();
    SightTrait sighttrait = null;
    for (SightPhoto ooj : list) {
      sighttrait = new SightTrait();
      sighttrait.setRecNo(ooj.getRecNo());
      sighttrait.setSightNo(sightNo);
      sighttrait.setMapAddress(ooj.getMapAddress());
      sighttrait.setNote(ooj.getNote());

      lineSightTrait.add(sighttrait);
    }
    return lineSightTrait;
  }

  @SuppressWarnings("unchecked")
  public List<Sight> getSightList(String province, String country,
      String sightName, String districtNo) {
    DetachedCriteria criteria = DetachedCriteria.forClass(Sight.class, "d");
    criteria.createAlias("d.country", "c");
    criteria.createAlias("d.district", "t");
    criteria.createAlias("d.province", "p", CriteriaSpecification.LEFT_JOIN);

    if (StringUtil.hasLength(sightName))
      criteria.add(Restrictions.like("d.name", sightName, MatchMode.ANYWHERE));
    if (StringUtil.hasLength(country))
      criteria.add(Restrictions.eq("c.countryId", country));
    if (StringUtil.hasLength(province))
      criteria.add(Restrictions.eq("p.code", province));

    if (StringUtil.hasLength(districtNo))
      criteria.add(Restrictions.eq("t.districtNo", districtNo));

    criteria.addOrder(Order.asc("d.name"));
    criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

    return getHibernateTemplate().findByCriteria(criteria);
  }

  public int editSight(Sight sight, String method) {
    HibernateTemplate template = getHibernateTemplate();
    Sight tfa023 = (Sight) template.get(Sight.class, sight.getSightNo(),
        LockMode.UPGRADE);

    if (null == tfa023 && method.equals("update")) {
      return -1;
    }
    if (method.equals("insert")) {
      tfa023 = new Sight();
    }

    tfa023.setSightNo(sight.getSightNo());
    tfa023.setName(sight.getName());
    tfa023.setEnName(sight.getEnName());
    tfa023.setCnNote(sight.getCnNote());
    tfa023.setEnNote(sight.getEnNote());
    tfa023.setHeadAdd(sight.getHeadAdd());
    tfa023.setMapAdd(sight.getMapAdd());
    tfa023.setCountry(sight.getCountry());
    tfa023.setProvince(sight.getProvince());
    tfa023.setDistrict(sight.getDistrict());
    tfa023.setDistrict(sight.getDistrict());
    tfa023.setSightType(sight.getSightType());

    template.saveOrUpdate(tfa023);

    return 0;
  }

  public PaginationSupport getSightList(String country, String province,
      String sightName, int fromRecord, int pageSize) {
    DetachedCriteria criteria = DetachedCriteria.forClass(Sight.class, "b");
    criteria.createAlias("b.province", "c");
    criteria.createAlias("b.country", "n");

    if (StringUtil.hasLength(country))
      criteria.add(Restrictions.eq("n.countryId", country));
    if (StringUtil.hasLength(province))
      criteria.add(Restrictions.eq("c.code", province));
    if (StringUtil.hasLength(sightName))
      criteria.add(Restrictions.like("b.name", sightName, MatchMode.ANYWHERE));

    PaginationSupport support = findPageByCriteria(criteria, pageSize,
        fromRecord);

    return support;
  }

}
