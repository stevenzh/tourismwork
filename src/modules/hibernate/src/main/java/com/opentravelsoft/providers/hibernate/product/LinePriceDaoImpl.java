package com.opentravelsoft.providers.hibernate.product;

import java.util.Date;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.LinePrice;
import com.opentravelsoft.providers.hibernate.GenericDaoHibernate;
import com.opentravelsoft.providers.product.LinePriceDao;

/**
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.2 $ $Date: 2009/03/09 15:37:04 $
 */
@Repository("RoutePriceDao")
public class LinePriceDaoImpl extends GenericDaoHibernate<LinePrice, String>
    implements LinePriceDao {

  public LinePriceDaoImpl() {
    super(LinePrice.class);
  }

  @SuppressWarnings("unchecked")
  public List<LinePrice> getLinePrice(String lineNo, Date startDate,
      Date endDate) {
    DetachedCriteria criteria = DetachedCriteria.forClass(LinePrice.class);
    criteria.add(Restrictions.eq("lineNo", lineNo));
    if (null != startDate)
      criteria.add(Restrictions.ge("endDate", startDate));
    if (null != endDate)
      criteria.add(Restrictions.le("startDate", endDate));
    criteria.addOrder(Order.desc("endDate"));
    criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

    List<LinePrice> list = getHibernateTemplate().findByCriteria(criteria);

    int count = 1;
    for (LinePrice obj : list) {
      obj.setCount(count++);
    }

    return list;
  }

  @SuppressWarnings("unchecked")
  public int deleteLinePrice(String priceNo, String note, long uid) {
    HibernateTemplate template = getHibernateTemplate();
    LinePrice tfa606 = (LinePrice) template.get(LinePrice.class, priceNo,
        LockMode.UPGRADE);
    if (tfa606 != null) {
      // 修改对应计划
      StringBuilder sb = new StringBuilder();
      sb.append("select count(*) Plan ");
      sb.append("where packagePrice.priceNo=? ");
      Object[] param = { priceNo };
      List<Integer> row = template.find(sb.toString(), param);
      if (row.get(0) > 0)
        return -1;
      else
        // 删除报价
        template.delete(tfa606);
    } else
      return -1;

    return 0;
  }

  @SuppressWarnings("unchecked")
  public List<LinePrice> getPriceNotice(String lineNo) {
    StringBuilder sql = new StringBuilder();
    sql.append("from LinePrice ");
    sql.append("where endDate>current_date() and lineNo=? ");
    sql.append("order by startDate desc");
    Object[] ooj = { lineNo };
    return getHibernateTemplate().find(sql.toString(), ooj);
  }

  @Override
  public void savePrice(LinePrice routePrice) {
    getHibernateTemplate().saveOrUpdate(routePrice);
//    LinePrice price = get(routePrice.getRecNo());
//    price.setStartDate(routePrice.getStartDate());
//    getHibernateTemplate().saveOrUpdate(price);
  }
}
