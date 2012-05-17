package com.opentravelsoft.providers.hibernate.product;

import java.util.List;

import org.hibernate.LockMode;
import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.Premium;
import com.opentravelsoft.providers.hibernate.GenericDaoHibernate;
import com.opentravelsoft.providers.product.PremiumDao;

@Repository("PremiumDao")
public class PremiumDaoImpl extends GenericDaoHibernate<Premium, String>
    implements PremiumDao {

  public PremiumDaoImpl() {
    super(Premium.class);
  }

  @SuppressWarnings("unchecked")
  public List<Premium> findAll() {
    StringBuilder sb = new StringBuilder();
    sb.append("from Premium where del='N' ");
    List<Premium> list = getHibernateTemplate().find(sb.toString());
    return list;
  }

  public int deletePrem(String preminuCode) {
    Premium prem = (Premium) getHibernateTemplate().load(Premium.class,
        preminuCode, LockMode.UPGRADE);
    if (null == prem)
      return -1;
    prem.setDel('Y');
    getHibernateTemplate().update(prem);
    return 0;
  }

  public int editPrem(Premium premium) {
    Premium prem = (Premium) getHibernateTemplate().get(Premium.class,
        premium.getPrecode(), LockMode.UPGRADE);
    boolean newflag = false;
    if (null == prem) {
      prem = new Premium();
      newflag = true;
    }
    prem.setBcpre(premium.getBcpre());
    prem.setPreday(premium.getPreday());
    prem.setPrem(premium.getPrem());
    prem.setYlpre(premium.getYlpre());
    prem.setYwpre(premium.getYwpre());
    prem.setPrecode(premium.getPrecode());
    if (newflag) {
      prem.setCreatedby(premium.getUpdatedby());
    }
    prem.setDel('N');
    prem.setDodate(premium.getDodate());
    // prem.setHkpre(tblpremium.getHkpre());
    // prem.setClpre(tblpremium.getClpre());
    prem.setNote(premium.getNote());
    prem.setUpdatedby(premium.getUpdatedby());
    getHibernateTemplate().saveOrUpdate(prem);
    return 0;
  }

}
