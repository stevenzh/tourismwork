package com.opentravelsoft.providers.hibernate.product;

import java.util.ArrayList;
import java.util.List;

import com.opentravelsoft.util.LabelValueBean;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.opentravelsoft.common.KeyParams;
import com.opentravelsoft.entity.Line;
import com.opentravelsoft.entity.LineDescription;
import com.opentravelsoft.providers.hibernate.GenericDaoHibernate;
import com.opentravelsoft.providers.product.LineTraitDao;
import com.opentravelsoft.util.RowDataUtil;

@Repository("RouteTraitDao")
public class LineTraitDaoImpl extends
    GenericDaoHibernate<LineDescription, Long> implements LineTraitDao {

  public LineTraitDaoImpl() {
    super(LineDescription.class);
  }

  @SuppressWarnings("unchecked")
  public List<LineDescription> getLineTrait(String lineNo, String type) {
    DetachedCriteria criteria = DetachedCriteria
        .forClass(LineDescription.class);
    criteria.add(Restrictions.eq("lineNo", lineNo));
    criteria.add(Restrictions.eq("type", type));

    criteria.addOrder(Order.asc("sortOrder"));
    criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

    List<LineDescription> list = getHibernateTemplate()
        .findByCriteria(criteria);
    int idx = 1;
    for (LineDescription lineDescription : list) {
      lineDescription.setIdx(idx++);
    }
    return list;
  }

  public int saveLineTrait(List<LineDescription> list, String lineNo,
      String traitType) {
    HibernateTemplate template = getHibernateTemplate();

    List<LineDescription> oList = getLineTrait(lineNo, traitType);

    for (LineDescription lineDescription : oList) {
      boolean has = false;
      LineDescription desc = null;
      for (int i = 0; i < list.size(); i++) {
        desc = list.get(i);
        if (desc.getId() == lineDescription.getId()) {
          has = true;
          break;
        }
      }

      if (has) {
        lineDescription.setItem(desc.getItem());
        lineDescription.setDescription(desc.getDescription());

        template.update(lineDescription);
      } else
        template.delete(lineDescription);
    }

    // add
    for (int i = 0; i < list.size(); i++) {
      LineDescription descs = list.get(i);
      if (null == descs.getId())
        template.save(descs);
    }

    return 0;
  }

  @SuppressWarnings("unchecked")
  public List<LineDescription> getExpense(String lineNo) {
    StringBuilder sql = new StringBuilder();
    sql.append("from LineDescription ");
    sql.append("where lineNo=? ");
    sql.append("and ( type='" + KeyParams.EBIZ_TYPE_EXPENSE_INC + "' or type='"
        + KeyParams.EBIZ_TYPE_EXPENSE_EXCEPT + "') ");
    sql.append("order by sortOrder ");

    Object[] params = { lineNo };
    List<LineDescription> list = getHibernateTemplate().find(sql.toString(),
        params);
    List<LineDescription> lineTraitList = new ArrayList<LineDescription>();
    LineDescription lineTrait = null;
    for (LineDescription ooj : list) {
      lineTrait = new LineDescription();
      lineTrait.setRouteNo(ooj.getLineNo());
      lineTrait.setRefNo(ooj.getRefNo());
      lineTrait.setTraitDetail(ooj.getServiceDetail());
      lineTrait.setExpenseType(RowDataUtil.getString(ooj.getType()));
      lineTraitList.add(lineTrait);
    }

    return lineTraitList;
  }

  @SuppressWarnings("unchecked")
  public int saveExpense(Line route, List<LineDescription> list) {
    StringBuilder sql = new StringBuilder();
    sql.append("from LineDescription ");
    sql.append("where lineNo=? ");
    sql.append("and ( type='" + KeyParams.EBIZ_TYPE_EXPENSE_INC + "' or type='"
        + KeyParams.EBIZ_TYPE_EXPENSE_EXCEPT + "') ");

    Object[] params = { route.getLineNo() };
    List<LineDescription> lists = getHibernateTemplate().find(sql.toString(),
        params);
    getHibernateTemplate().deleteAll(lists);

    for (LineDescription trait : list) {
      LineDescription linetrait = new LineDescription();
      linetrait.setLineNo(route.getLineNo());
      linetrait.setType(trait.getExpenseType());
      linetrait.setSortOrder(trait.getRefNo());
      linetrait.setServiceDetail(trait.getTraitDetail());

      getHibernateTemplate().save(linetrait);
    }

    sql = new StringBuilder();
    sql.append("from Line where lineNo=? ");
    List<Line> listr = getHibernateTemplate().find(sql.toString(), params);
    if (listr.size() > 0) {
      Line line = listr.get(0);
      line.setComOwnExpense(route.getTyOwnExpense());
      line.setPerOwnExpense(route.getZkOwnExpense());
      getHibernateTemplate().update(line);
    }

    return 0;
  }

  @SuppressWarnings("unchecked")
  public List<LabelValueBean> getNote(String lineNo, String type) {
    StringBuilder sql = new StringBuilder();
    sql.append("select lineNo,type,sortOrder,description ");
    sql.append("from LineDescription ");
    sql.append("where lineNo=? and type=? ");
    Object[] ooj = { lineNo, type };

    List<Object[]> list = getHibernateTemplate().find(sql.toString(), ooj);
    List<LabelValueBean> ret = new ArrayList<LabelValueBean>();
    LabelValueBean note = null;

    int idx = 1;
    for (Object[] obj : list) {
      note = new LabelValueBean();
      note.setLabel(String.valueOf(idx++));
      note.setValue((String) obj[3]);
      ret.add(note);
    }

    return ret;
  }

  @SuppressWarnings("unchecked")
  public List<LineDescription> searchTips(String lineNo) {
    StringBuilder sb = new StringBuilder();
    sb.append("FROM LineDescription ");
    sb.append("where lineNo=? and type='" + KeyParams.EBIZ_TYPE_LINE_TIPS
        + "' ");

    Object[] params = { lineNo };
    List<LineDescription> list = getHibernateTemplate().find(sb.toString(),
        params);
    LineDescription tips = null;
    List<LineDescription> tipsList = new ArrayList<LineDescription>();
    if (list != null && list.size() > 0) {
      int i = 1;
      for (LineDescription obj : list) {
        tips = new LineDescription();
        tips.setIdx(i++);
        tips.setItem(RowDataUtil.getString(obj.getItem()));
        tips.setDescription(RowDataUtil.getString(obj.getDescription()));
        tipsList.add(tips);
      }
    }

    return tipsList;
  }
}
