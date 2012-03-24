package com.opentravelsoft.providers.hibernate.product;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.LineSchedule;
import com.opentravelsoft.entity.LineScheduleId;
import com.opentravelsoft.entity.LineTraffic;
import com.opentravelsoft.providers.hibernate.GenericDaoHibernate;
import com.opentravelsoft.providers.product.LineScheduleDao;
import com.opentravelsoft.util.StringUtil;

/**
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.2 $ $Date: 2009/03/09 15:37:04 $
 */
@Repository("LineScheduleDao")
public class LineScheduleDaoImpl extends
    GenericDaoHibernate<LineSchedule, Long> implements LineScheduleDao {
  public LineScheduleDaoImpl() {
    super(LineSchedule.class);
  }

  @SuppressWarnings("unchecked")
  public List<LineSchedule> getLineSchedule(String lineNo) {
    DetachedCriteria criteria = DetachedCriteria.forClass(LineSchedule.class);
    criteria.add(Restrictions.eq("id.lineNo", lineNo));
    criteria.addOrder(Order.asc("id.day"));
    criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

    return getHibernateTemplate().findByCriteria(criteria);
  }

  @SuppressWarnings("unchecked")
  public int insertLineSchedule(String lineNo) {
    StringBuilder sql = new StringBuilder();
    sql.append("from LineSchedule ");
    sql.append("where id.lineNo =? ");
    sql.append("order by id.day desc ");

    Object[] params = { lineNo };

    List<LineSchedule> list = getHibernateTemplate().find(sql.toString(),
        params);
    int day = 1;
    if (list.size() > 0) {
      LineSchedule schedule2 = list.get(0);
      day = schedule2.getId().getDay();
      day = day + 1;
    }
    LineScheduleId tfa602Id = new LineScheduleId();
    tfa602Id.setLineNo(lineNo);
    tfa602Id.setDay(day);
    HibernateTemplate template = getHibernateTemplate();
    LineSchedule schedule = (LineSchedule) template.get(LineSchedule.class,
        tfa602Id, LockMode.UPGRADE);
    if (schedule == null) {
      LineSchedule schedule1 = new LineSchedule();
      schedule1.setId(tfa602Id);
      template.save(schedule1);
    }
    return 0;
  }

  @SuppressWarnings("unchecked")
  public int deleteLineSchedule(LineSchedule schedule) {
    LineScheduleId tfa602Id = new LineScheduleId();
    tfa602Id.setLineNo(schedule.getRouteNo());
    tfa602Id.setDay(schedule.getDay());
    HibernateTemplate template = getHibernateTemplate();
    LineSchedule tfa602 = (LineSchedule) template.get(LineSchedule.class,
        tfa602Id, LockMode.UPGRADE);
    if (tfa602 != null) {
      template.delete(tfa602);
    }

    StringBuilder sb = new StringBuilder();
    sb.append("from LineTraffic ");
    sb.append("where lineNo=? and day=? ");

    Object[] params = { schedule.getRouteNo(), schedule.getDay() };
    List<LineTraffic> list = template.find(sb.toString(), params);
    if (list != null) {
      template.deleteAll(list);
    }

    return 0;
  }

  @SuppressWarnings("unchecked")
  public int saveLineSchedule(List<LineSchedule> scheduleList,
      List<LineTraffic> trifficList) {
    String lineNo = null;
    List<Integer> keyList = new ArrayList<Integer>();
    List<Integer> keyList1 = new ArrayList<Integer>();
    List<Integer> keyList2 = new ArrayList<Integer>();
    HibernateTemplate template = getHibernateTemplate();

    for (LineSchedule obj : scheduleList) {
      LineScheduleId tfa602Id = new LineScheduleId();
      lineNo = obj.getRouteNo();
      tfa602Id.setLineNo(lineNo);
      tfa602Id.setDay(obj.getDay());
      LineSchedule schedule = (LineSchedule) template.get(LineSchedule.class,
          tfa602Id, LockMode.UPGRADE);

      if (schedule != null) {
        if (StringUtil.hasLength(obj.getProgram())
            || StringUtil.hasLength(obj.getBreakfast())
            || StringUtil.hasLength(obj.getLunch())
            || StringUtil.hasLength(obj.getSupper())
            || StringUtil.hasLength(obj.getQuarter())) {
          schedule.setId(tfa602Id);
          schedule.setProgram(obj.getProgram());
          // tfa602.setTraffic(obj.getTraffic());
          // tfa602.setTraffic1(obj.getTraffic1());
          schedule.setBreakfast(obj.getBreakfast());
          schedule.setLunch(obj.getLunch());
          schedule.setSupper(obj.getSupper());
          schedule.setQuarter(obj.getQuarter());
          template.saveOrUpdate(schedule);
        } else {
          keyList1.add(obj.getDay());
        }
      } else {
        if (StringUtil.hasLength(obj.getProgram())
            || StringUtil.hasLength(obj.getBreakfast())
            || StringUtil.hasLength(obj.getLunch())
            || StringUtil.hasLength(obj.getSupper())
            || StringUtil.hasLength(obj.getQuarter())) {
          schedule = new LineSchedule();
          schedule.setId(tfa602Id);
          schedule.setProgram(obj.getProgram());
          // tfa602.setTraffic(obj.getTraffic());
          // tfa602.setTraffic1(obj.getTraffic1());
          schedule.setBreakfast(obj.getBreakfast());
          schedule.setLunch(obj.getLunch());
          schedule.setSupper(obj.getSupper());
          schedule.setQuarter(obj.getQuarter());
          template.save(schedule);
        } else {
          keyList1.add(obj.getDay());
        }
      }
      keyList.add(obj.getDay());

    }

    List<LineSchedule> oldList = this.getLineSchedule(lineNo);
    for (LineSchedule schedule : oldList) {
      // 删除多余的天
      if (!keyList.contains(schedule.getDay())) {
        LineScheduleId tfa602Id = new LineScheduleId();
        tfa602Id.setLineNo(lineNo);
        tfa602Id.setDay(schedule.getDay());
        LineSchedule tfa602 = (LineSchedule) template.get(LineSchedule.class,
            tfa602Id, LockMode.UPGRADE);
        template.delete(tfa602);
      }
    }

    for (LineTraffic obj1 : trifficList) {
      LineTraffic tbllinetraffic = (LineTraffic) template.get(
          LineTraffic.class, obj1.getId(), LockMode.UPGRADE);

      if (tbllinetraffic != null) {
        if (StringUtil.hasLength(obj1.getFromCity())
            || StringUtil.hasLength(obj1.getToCity())
            || StringUtil.hasLength(obj1.getTravelTime())) {
          tbllinetraffic.setDay(obj1.getDay());
          tbllinetraffic.setStep(obj1.getStep());
          tbllinetraffic.setLineNo(obj1.getLineNo());
          tbllinetraffic.setFromCity(obj1.getFromCity());
          tbllinetraffic.setToCity(obj1.getToCity());
          tbllinetraffic.setTravelTime(obj1.getTravelTime());
          tbllinetraffic.setTraffic(obj1.getTraffic());
          template.saveOrUpdate(tbllinetraffic);
        } else {
          keyList2.add(obj1.getStep());
        }
      } else {
        if (StringUtil.hasLength(obj1.getFromCity())
            || StringUtil.hasLength(obj1.getToCity())
            || StringUtil.hasLength(obj1.getTravelTime())) {
          tbllinetraffic = new LineTraffic();
          tbllinetraffic.setDay(obj1.getDay());
          tbllinetraffic.setStep(obj1.getStep());
          tbllinetraffic.setLineNo(obj1.getLineNo());
          tbllinetraffic.setFromCity(obj1.getFromCity());
          tbllinetraffic.setToCity(obj1.getToCity());
          tbllinetraffic.setTravelTime(obj1.getTravelTime());
          tbllinetraffic.setTraffic(obj1.getTraffic());
          template.save(tbllinetraffic);
        } else {
          keyList2.add(obj1.getStep());
        }
      }
    }

    StringBuilder sb = new StringBuilder();
    sb.append("from LineTraffic ");
    sb.append("where lineNo= ? order by day,step");

    Object[] params = { lineNo };
    List<LineTraffic> oldList1 = getHibernateTemplate().find(sb.toString(),
        params);

    for (LineTraffic tbllinetraffic : oldList1) {
      if (keyList1.contains(tbllinetraffic.getDay())) {
        LineTraffic tbllinetraffic1 = (LineTraffic) template.get(
            LineTraffic.class, tbllinetraffic.getId(), LockMode.UPGRADE);
        template.delete(tbllinetraffic1);
      }

    }

    for (LineTraffic tbllinetraffic : oldList1) {
      // 删除多余的天
      if (keyList2.contains(tbllinetraffic.getStep())) {
        LineTraffic tbllinetraffic1 = (LineTraffic) template.get(
            LineTraffic.class, tbllinetraffic.getId(), LockMode.UPGRADE);
        template.delete(tbllinetraffic1);
      }
    }

    return 0;
  }

  @SuppressWarnings("unchecked")
  public List<LineTraffic> getLineTraffic(String lineNo) {
    DetachedCriteria criteria = DetachedCriteria.forClass(LineTraffic.class);
    criteria.add(Restrictions.eq("lineNo", lineNo));
    criteria.addOrder(Order.asc("day"));
    criteria.addOrder(Order.asc("step"));
    criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

    return getHibernateTemplate().findByCriteria(criteria);
  }

}
