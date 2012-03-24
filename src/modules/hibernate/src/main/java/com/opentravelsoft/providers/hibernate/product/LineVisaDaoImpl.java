package com.opentravelsoft.providers.hibernate.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.LineVisa;
import com.opentravelsoft.entity.LineVisaId;
import com.opentravelsoft.entity.VisaHelp;
import com.opentravelsoft.providers.hibernate.GenericDaoHibernate;
import com.opentravelsoft.providers.product.LineVisaDao;
import com.opentravelsoft.util.RowDataUtil;

/**
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.2 $ $Date: 2009/03/09 15:37:04 $
 */
@Repository("RouteVisaDao")
public class LineVisaDaoImpl extends GenericDaoHibernate<LineVisa, String>
    implements LineVisaDao {
  public LineVisaDaoImpl() {
    super(LineVisa.class);
  }

  @SuppressWarnings("unchecked")
  public List<LineVisa> getVisaList(String lineNo) {
    StringBuilder sql = new StringBuilder();
    sql.append("select a.id.recNo,b.country,b.subject,b.price1,");
    sql.append("b.price2,b.cost,b.unit,b.note ");
    sql.append("from LineVisa a,");
    sql.append("VisaHelp b ");
    sql.append("where a.id.recNo=b.recNo and a.id.lineNo=? ");

    Object[] params = { lineNo };
    List<Object[]> list = getHibernateTemplate().find(sql.toString(), params);
    List<LineVisa> visaList = new ArrayList<LineVisa>();
    LineVisa visa = null;

    int idx = 0;
    for (Object[] obj : list) {
      visa = new LineVisa();
      visa.setRefNo(idx++);
      visa.setRecNo(RowDataUtil.getString(obj[0]));
      visa.setCountryCode(RowDataUtil.getString(obj[1]));
      visa.setItem(RowDataUtil.getString(obj[2]));
      visa.setUnit(RowDataUtil.getString(obj[6]));
      visa.setDescription(RowDataUtil.getString(obj[7]));
      visa.setChecked("true");
      visaList.add(visa);
    }

    return visaList;
  }

  @SuppressWarnings("unchecked")
  public int saveVisa(String lineNo, List<LineVisa> list) {
    HibernateTemplate template = getHibernateTemplate();
    StringBuilder sb = new StringBuilder();
    sb.append("from LineVisa where id.lineNo=?");

    Object[] params = { lineNo };

    List<LineVisa> dblist = template.find(sb.toString(), params);
    for (LineVisa obj : dblist) {
      boolean has = false;
      for (int i = list.size() - 1; i >= 0; i--) {
        if (list.get(i).getRecNo().equals(obj.getId().getRecNo())) {
          has = true;
          list.remove(i);
          break;
        }
      }

      if (!has)
        template.delete(obj);
    }

    for (LineVisa trait : list) {
      LineVisa tfa = new LineVisa(new LineVisaId(lineNo, trait.getRecNo()));
      template.save(tfa);
    }

    return 0;
  }

  @SuppressWarnings("unchecked")
  public List<LineVisa> getByCountry(String country) {
    StringBuilder sql = new StringBuilder();
    sql.append("from VisaHelp ");
    sql.append("where country=? and SDate<=current_date() ");
    sql.append("and EDate>=current_date() and opKey<>'D'");

    Object[] params = { country };
    List<VisaHelp> list = getHibernateTemplate().find(sql.toString(), params);
    List<LineVisa> visaList = new ArrayList<LineVisa>();
    LineVisa visa = null;
    for (VisaHelp obj : list) {
      visa = new LineVisa();
      visa.setRecNo(obj.getRecNo());
      visa.setItem(obj.getSubject());
      visa.setDescription(obj.getNote());
      visa.setCountryCode(country);
      visa.setUnit(obj.getUnit());
      visaList.add(visa);
    }

    return visaList;
  }

}
