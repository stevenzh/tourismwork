package com.opentravelsoft.providers.hibernate.product;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.LockMode;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.Line;
import com.opentravelsoft.entity.Sight;
import com.opentravelsoft.providers.hibernate.GenericDaoHibernate;
import com.opentravelsoft.providers.product.LineSightDao;

/**
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.2 $ $Date: 2009/03/09 15:37:04 $
 */
@Repository("RouteSightDao")
public class LineSightDaoImpl extends GenericDaoHibernate<Sight, String>
    implements LineSightDao {

  public LineSightDaoImpl() {
    super(Sight.class);
  }

  @SuppressWarnings("unchecked")
  public List<Sight> getDestinationSights(String lineNo) {
    HibernateTemplate template = getHibernateTemplate();
    StringBuilder sql = new StringBuilder();
    sql.append("select s ");
    sql.append("from Sight s,");
    sql.append("LineDistrict d ");
    sql.append("where s.district.districtNo = d.id.districtNo ");
    sql.append("and d.id.lineNo=? ");

    Object[] params = { lineNo };
    return template.find(sql.toString(), params);
  }

  @SuppressWarnings("unchecked")
  public List<Sight> getLineSights(String lineNo) {
    HibernateTemplate template = getHibernateTemplate();
    StringBuilder sql = new StringBuilder();
    sql.append("select l.sights from Line as l where l.lineNo=? ");

    Object[] params = { lineNo };
    List<Sight> list = template.find(sql.toString(), params);

    return list;
  }

  public int saveSights(List<Sight> list, String lineNo) {
    HibernateTemplate template = getHibernateTemplate();
    Line line = (Line) template.get(Line.class, lineNo,
        LockMode.PESSIMISTIC_WRITE);

    Set<Sight> sights = new HashSet<Sight>(0);

    for (Sight obj : list) {
      sights.add(obj);
    }

    line.setSights(sights);

    template.update(line);
    return 0;
  }
}
