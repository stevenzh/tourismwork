package com.opentravelsoft.providers.hibernate.product;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.Line;
import com.opentravelsoft.entity.PortalCategory;
import com.opentravelsoft.providers.hibernate.GenericDaoHibernate;
import com.opentravelsoft.providers.product.LineDao;
import com.opentravelsoft.util.PaginationSupport;
import com.opentravelsoft.util.StringUtil;

/**
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.2 $ $Date: 2009/03/09 15:37:04 $
 */
@Repository("RouteDao")
public class LineDaoImpl extends GenericDaoHibernate<Line, String> implements
    LineDao {
  public LineDaoImpl() {
    super(Line.class);
  }

  public PaginationSupport findLineList(int teamId, String lineName,
      String isActive, int userId, String kenDestination, int fromRecord,
      int pageSize) {
    DetachedCriteria criteria = DetachedCriteria.forClass(Line.class, "l");
    criteria.createAlias("l.outCity", "c");
    criteria.createAlias("l.destination", "d");
    criteria.add(Restrictions.eq("l.delKey", "N"));

    if (StringUtil.hasLength(lineName))
      criteria.add(Restrictions
          .like("l.lineName", lineName, MatchMode.ANYWHERE));
    if (teamId != 0)
      criteria.add(Restrictions.eq("l.team.teamId", teamId));
    if (userId != 0)
      criteria.add(Restrictions.eq("l.assigned.userId", userId));
    if (StringUtil.hasLength(isActive))
      criteria
          .add(Restrictions.eq("l.isActive", Boolean.parseBoolean(isActive)));
    if (StringUtil.hasLength(kenDestination))
      criteria
          .add(Restrictions.like("d.code", kenDestination, MatchMode.START));

    criteria.addOrder(Order.asc("l.lineName"));

    PaginationSupport support = findPageByCriteria(criteria, pageSize,
        fromRecord);

    return support;
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  public int cancelLine(String lineNo) {
    HibernateTemplate template = getHibernateTemplate();
    Line line = (Line) template.get(Line.class, lineNo,
        LockMode.PESSIMISTIC_WRITE);

    Object[] params = { lineNo };
    StringBuilder sb = new StringBuilder();
    sb.append("select count(*) from Plan where line.lineNo=?");
    List<Long> obj = (List) template.find(sb.toString(), params);

    if (obj.get(0) > 0) // has
      return -2;

    if (line == null)
      return -1;

    line.setDelKey("Y");
    template.update(line);

    return 0;
  }

  public int updateLine(Line line) {
    HibernateTemplate template = getHibernateTemplate();
    Line line1 = (Line) template.load(Line.class, line.getLineNo(),
        LockMode.PESSIMISTIC_WRITE);

    if (line1 != null) {
      line1.setLineName(line.getLineName());
      line1.setLineDay(line.getLineDay());
      line1.setTeam(line.getTeam());
      line1.setAssigned(line.getAssigned());
      line1.setOpTeam(line.getOpTeam());

      line1.setOutCity(line.getOutCity());
      line1.setClassKeyContent(line.getClassKeyContent());
      line1.setDestination(line.getDestination());
      line1.setClassKeyVehicle(line.getClassKeyVehicle());
      line1.setIsActive(line.isIsActive());
      line1.setTitle(line.getTitle());
      line1.setDescription(line.getDescription());
      line1.setModifiedBy(line.getModifiedBy());

      line1.setPortOfEntry(line.getPortOfEntry());
      line1.setPortOfDeparture(line.getPortOfDeparture());

      template.update(line1);
    } else
      return -1;

    return 0;
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  public int duplicateRoute(String lineNo, String newLineNo,
      String newLineName, String copyFeature, String copySchedule,
      String copyPrice, String copyDestination, String copySight,
      String copyVisa, Integer userId) {
    HibernateTemplate template = getHibernateTemplate();
    Line line = (Line) template.get(Line.class, lineNo, LockMode.READ);
    if (line == null) {
      return -1;
    }

    line.setLineNo(newLineNo);
    line.setLineName(newLineName);
    line.getAssigned().setUserId(userId);
    final Object[] params = { lineNo };
    template.save(line);

    // 行程
    if (copySchedule.equals("Y")) {
      final StringBuilder sb1 = new StringBuilder();
      sb1.append("INSERT INTO TBL_LINE_SCHEDULE (LINE_NO,DAY,TRAFFIC,");
      sb1.append("TRAFFIC1,BREAKFAST,LUNCH,SUPPER,PROGRAM) ");
      sb1.append("SELECT '" + newLineNo + "',DAY,TRAFFIC,TRAFFIC1,");
      sb1.append("BREAKFAST,LUNCH,SUPPER,PROGRAM ");
      sb1.append("FROM TBL_LINE_SCHEDULE WHERE LINE_NO=?");

      template.execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException {
          SQLQuery queryObject = session.createSQLQuery(sb1.toString());
          if (params != null) {
            for (int i = 0; i < params.length; i++) {
              queryObject.setParameter(i, params[i]);
            }
          }

          return new Integer(queryObject.executeUpdate());
        }
      });
    }

    // 描述
    if (copyFeature.equals("Y")) {
      final StringBuilder sb2 = new StringBuilder();
      sb2.append("INSERT INTO TBL_LINE_DESC (LINE_NO,TYPE,ITEM,DESCRIPTION,SORT_ORDER) ");
      sb2.append("SELECT '" + newLineNo + "',TYPE,ITEM,DESCRIPTION,SORT_ORDER ");
      sb2.append("FROM TBL_LINE_DESC WHERE LINE_NO=?");

      template.execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException {
          SQLQuery queryObject = session.createSQLQuery(sb2.toString());
          if (params != null) {
            for (int i = 0; i < params.length; i++) {
              queryObject.setParameter(i, params[i]);
            }
          }

          return new Integer(queryObject.executeUpdate());
        }
      });
    }

    // 报价
    // if (copyPrice.equals("Y"))
    // {
    // final StringBuilder sb8 = new StringBuilder();
    // sb8.append("INSERT INTO TFA606 (REC_NO,LINE_NO,");
    // sb8.append("AREA_ID,PRICE_KEY,CLASS,PRICE1,PRICE2,PRICE3,PRICE4,");
    // sb8.append("S_DATE,E_DATE,WEEK_BIT,CR_USER,CR_DATE,OP_USER,OP_DATE)");
    // sb8.append("SELECT '" + newLineName + "',DISTRICT_NO ");
    // sb8.append("FROM TFA601E WHERE LINE_NO=? ");
    //
    // template.execute(new HibernateCallback()
    // {
    // public Object doInHibernate(Session session)
    // throws HibernateException
    // {
    // SQLQuery queryObject = session.createSQLQuery(sb8
    // .toString());
    // if (params != null)
    // {
    // for (int i = 0; i < params.length; i++)
    // {
    // queryObject.setParameter(i, params[i]);
    // }
    // }
    // return new Integer(queryObject.executeUpdate());
    // }
    // }, true);
    // }

    // 目的地
    if (copyDestination.equals("Y")) {
      final StringBuilder sb8 = new StringBuilder();
      sb8.append("INSERT INTO TBL_LINE_DISTRICT (LINE_NO,DISTRICT_NO) ");
      sb8.append("SELECT '" + newLineNo + "',DISTRICT_NO ");
      sb8.append("FROM TBL_LINE_DISTRICT WHERE LINE_NO=? ");

      template.execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException {
          SQLQuery queryObject = session.createSQLQuery(sb8.toString());
          if (params != null) {
            for (int i = 0; i < params.length; i++) {
              queryObject.setParameter(i, params[i]);
            }
          }

          return new Integer(queryObject.executeUpdate());
        }
      });
    }

    // 景点
    if (copySight.equals("Y")) {
      final StringBuilder sb8 = new StringBuilder();
      sb8.append("INSERT INTO TBL_LINE_SIGHTS (LINE_NO,SIGHT_NO) ");
      sb8.append("SELECT '" + newLineNo + "',SIGHT_NO ");
      sb8.append("FROM TBL_LINE_SIGHTS WHERE LINE_NO=? ");

      template.execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException {
          SQLQuery queryObject = session.createSQLQuery(sb8.toString());
          if (params != null) {
            for (int i = 0; i < params.length; i++) {
              queryObject.setParameter(i, params[i]);
            }
          }

          return new Integer(queryObject.executeUpdate());
        }
      });
    }

    // 签证
    if (copyVisa.equals("Y")) {
      final StringBuilder sb8 = new StringBuilder();
      sb8.append("INSERT INTO TBL_LINE_VISA (LINE_NO,REC_NO) ");
      sb8.append("SELECT '" + newLineNo + "',REC_NO ");
      sb8.append("FROM TBL_LINE_VISA WHERE LINE_NO=? ");

      template.execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException {
          SQLQuery queryObject = session.createSQLQuery(sb8.toString());
          if (params != null) {
            for (int i = 0; i < params.length; i++) {
              queryObject.setParameter(i, params[i]);
            }
          }

          return new Integer(queryObject.executeUpdate());
        }
      });
    }

    return 0;

  }

  @SuppressWarnings("unchecked")
  public List<PortalCategory> getWebNavigation() {
    DetachedCriteria criteria = DetachedCriteria.forClass(PortalCategory.class);
    criteria.addOrder(Order.asc("code"));
    criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

    return getHibernateTemplate().findByCriteria(criteria);
  }

}
