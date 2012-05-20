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

import com.opentravelsoft.entity.District;
import com.opentravelsoft.providers.DistrictDao;
import com.opentravelsoft.util.RowDataUtil;
import com.opentravelsoft.util.StringUtil;

@Repository("DistrictDao")
public class DistrictDaoImpl extends GenericDaoHibernate<District, String>
    implements DistrictDao {

  public DistrictDaoImpl() {
    super(District.class);
  }

  @SuppressWarnings("unchecked")
  public List<District> getDistrictList(String districtName, String countryNo,
      String provinceId) {

    DetachedCriteria criteria = DetachedCriteria.forClass(District.class, "d");
    criteria.createAlias("d.country", "c");
    criteria.createAlias("d.province", "p", CriteriaSpecification.LEFT_JOIN);

    if (StringUtil.hasLength(districtName))
      criteria.add(Restrictions.like("d.cnName", districtName,
          MatchMode.ANYWHERE));
    if (StringUtil.hasLength(countryNo))
      criteria.add(Restrictions.eq("c.countryId", countryNo));
    if (StringUtil.hasLength(provinceId))
      criteria.add(Restrictions.eq("p.code", provinceId));

    criteria.addOrder(Order.asc("d.districtNo"));
    criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

    return getHibernateTemplate().findByCriteria(criteria);
  }

  public int deleteDistrict(String districtNo) {
    HibernateTemplate template = getHibernateTemplate();
    District tfa021 = (District) template.get(District.class, districtNo,
        LockMode.PESSIMISTIC_WRITE);
    if (tfa021 != null)
      template.delete(tfa021);
    else
      return -1;

    return 0;
  }

  public int editDistrict(District district, String method) {
    String districtNo = district.getDistrictNo();
    HibernateTemplate template = getHibernateTemplate();
    District tfa021 = (District) template.get(District.class, districtNo,
        LockMode.PESSIMISTIC_WRITE);
    if (null == tfa021 && method.equals("update")) {
      return -1;
    }
    if (method.equals("insert")) {
      tfa021 = new District();
    }
    tfa021.setDistrictNo(district.getDistrictNo());
    tfa021.setCnName(district.getCnName());
    tfa021.setCnNote(district.getCnNote());
    tfa021.setCountry(district.getCountry());
    tfa021.setProvince(district.getProvince());
    template.saveOrUpdate(tfa021);

    return 0;
  }

  @SuppressWarnings("unchecked")
  public List<District> getDistrictByCountry(String countryNo) {
    StringBuilder sql = new StringBuilder();
    sql.append("from District ");
    if (countryNo.equals(""))
      sql.append("where cnName<>'' ");
    else
      sql.append("where country = ? and cnName<>'' ");
    sql.append("order by cnName ");
    List<District> list;
    if (countryNo.equals(""))
      list = getHibernateTemplate().find(sql.toString());
    else
      list = getHibernateTemplate().find(sql.toString(), countryNo);
    List<District> districtList = new ArrayList<District>();
    District district = null;
    for (District obj : list) {
      if (!RowDataUtil.getString(obj.getCnName()).equals("")) {
        district = new District();
        district.setDistrictNo(RowDataUtil.getString(obj.getDistrictNo()));
        district.setCnName(RowDataUtil.getString(obj.getCnName()));
        districtList.add(district);
      } else
        continue;
    }

    return districtList;
  }

  @SuppressWarnings("unchecked")
  public List<District> getDistrictByProvince(String provinceNo) {
    StringBuilder sql = new StringBuilder();
    sql.append("from District ");
    sql.append("where 1=1 ");
    if (provinceNo.equals(""))
      sql.append("and duchy.code<>'' ");
    else
      sql.append("and duchy.code = ? and duchy.code<>'' ");
    sql.append("order by cnName ");
    List<District> list;
    if (provinceNo.equals(""))
      list = getHibernateTemplate().find(sql.toString());
    else
      list = getHibernateTemplate().find(sql.toString(), provinceNo);
    List<District> districtList = new ArrayList<District>();
    District district = null;
    for (District obj : list) {
      if (!RowDataUtil.getString(obj.getCnName()).equals("")) {
        district = new District();
        district.setDistrictNo(RowDataUtil.getString(obj.getDistrictNo()));
        district.setCnName(RowDataUtil.getString(obj.getCnName()));
        districtList.add(district);
      } else
        continue;
    }

    return districtList;
  }
}