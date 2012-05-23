package com.opentravelsoft.providers.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;

import com.opentravelsoft.entity.Lists;
import com.opentravelsoft.providers.GenericDao;
import com.opentravelsoft.util.PaginationSupport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

/**
 * This class serves as the Base class for all other DAOs - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 * <p/>
 * <p>
 * To register this class in your Spring context file, use the following XML.
 * 
 * <pre>
 *      &lt;bean id="fooDao" class="com.opentravelsoft.dao.hibernate.GenericDaoHibernate"&gt;
 *          &lt;constructor-arg value="com.opentravelsoft.entity.Foo"/&gt;
 *          &lt;property name="sessionFactory" ref="sessionFactory"/&gt;
 *      &lt;/bean&gt;
 * </pre>
 * 
 * @author <a href="mailto:bwnoll@gmail.com">Bryan Noll</a>
 * @param <T> a type variable
 * @param <PK> the primary key for that type
 */
public class GenericDaoHibernate<T, PK extends Serializable> implements
    GenericDao<T, PK> {
  /**
   * Log variable for all child classes. Uses LogFactory.getLog(getClass()) from
   * Commons Logging
   */
  protected final Log log = LogFactory.getLog(getClass());

  private Class<T> persistentClass;

  private HibernateTemplate hibernateTemplate;

  private SessionFactory sessionFactory;

  /**
   * Constructor that takes in a class to see which type of entity to persist.
   * Use this constructor when subclassing.
   * 
   * @param persistentClass the class type you'd like to persist
   */
  public GenericDaoHibernate(final Class<T> persistentClass) {
    this.persistentClass = persistentClass;
  }

  /**
   * Constructor that takes in a class and sessionFactory for easy creation of
   * DAO.
   * 
   * @param persistentClass the class type you'd like to persist
   * @param sessionFactory the pre-configured Hibernate SessionFactory
   */
  public GenericDaoHibernate(final Class<T> persistentClass,
      SessionFactory sessionFactory) {
    this.persistentClass = persistentClass;
    this.sessionFactory = sessionFactory;
    this.hibernateTemplate = new HibernateTemplate(sessionFactory);
  }

  public HibernateTemplate getHibernateTemplate() {
    return this.hibernateTemplate;
  }

  public SessionFactory getSessionFactory() {
    return this.sessionFactory;
  }

  @Autowired
  @Required
  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
    this.hibernateTemplate = new HibernateTemplate(sessionFactory);
  }

  /**
   * {@inheritDoc}
   */
  public List<T> getAll() {
    return hibernateTemplate.loadAll(this.persistentClass);
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public List<T> getAllDistinct() {
    Collection result = new LinkedHashSet(getAll());
    return new ArrayList(result);
  }

  /**
   * {@inheritDoc}
   */
  public T get(PK id) {
    T entity = (T) hibernateTemplate.get(this.persistentClass, id);

    if (entity == null) {
      log.warn("Uh oh, '" + this.persistentClass + "' object with id '" + id
          + "' not found...");
      throw new ObjectRetrievalFailureException(this.persistentClass, id);
    }

    return entity;
  }

  /**
   * {@inheritDoc}
   */
  public boolean exists(PK id) {
    T entity = (T) hibernateTemplate.get(this.persistentClass, id);
    return entity != null;
  }

  /**
   * {@inheritDoc}
   */
  public T save(T object) {
    return (T) hibernateTemplate.merge(object);
  }

  /**
   * {@inheritDoc}
   */
  public void remove(PK id) {
    hibernateTemplate.delete(this.get(id));
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("unchecked")
  public List<T> findByNamedQuery(String queryName,
      Map<String, Object> queryParams) {
    String[] params = new String[queryParams.size()];
    Object[] values = new Object[queryParams.size()];

    int index = 0;
    for (String s : queryParams.keySet()) {
      params[index] = s;
      values[index++] = queryParams.get(s);
    }

    return hibernateTemplate.findByNamedQueryAndNamedParam(queryName, params,
        values);
  }

  // -------------------------------------------------------------------------

  public PaginationSupport findPageByCriteria(
      final DetachedCriteria detachedCriteria) {
    return findPageByCriteria(detachedCriteria, PaginationSupport.PAGESIZE, 0);
  }

  public PaginationSupport findPageByCriteria(
      final DetachedCriteria detachedCriteria, final int startIndex) {
    return findPageByCriteria(detachedCriteria, PaginationSupport.PAGESIZE,
        startIndex);
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  public PaginationSupport findPageByCriteria(
      final DetachedCriteria detachedCriteria, final int pageSize,
      final int startIndex) {
    Assert.notNull(detachedCriteria, "DetachedCriteria must not be null");
    return (PaginationSupport) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException {
            Criteria criteria = detachedCriteria.getExecutableCriteria(session);
            long totalCount = ((Integer) criteria.setProjection(
                Projections.rowCount()).uniqueResult()).longValue();
            criteria.setProjection(null);
            criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
            List items = criteria.setFirstResult(startIndex)
                .setMaxResults(pageSize).list();
            PaginationSupport ps = new PaginationSupport(items, totalCount,
                pageSize, startIndex);
            return ps;
          }
        });
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  public List<T> findAllByCriteria(final DetachedCriteria detachedCriteria) {
    return (List) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException {
        Criteria criteria = detachedCriteria.getExecutableCriteria(session);
        return criteria.list();
      }
    });
  }

  @SuppressWarnings("rawtypes")
  public int getCountByCriteria(final DetachedCriteria detachedCriteria) {
    @SuppressWarnings("unchecked")
    Integer count = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException {
            Criteria criteria = detachedCriteria.getExecutableCriteria(session);
            return criteria.setProjection(Projections.rowCount())
                .uniqueResult();
          }
        });
    return count.intValue();
  }

  @SuppressWarnings("unchecked")
  public Date getSysdate() {
    Calendar cal = Calendar.getInstance();
    cal.setTime(new Date());
    Date sysdate = new Date(cal.getTimeInMillis());
    String strSql = "select current_date() from " + Lists.class.getName();

    try {
      List<Date> list = getHibernateTemplate().find(strSql);
      if (list.size() > 0) {
        sysdate = list.get(0);
      }
    } catch (Exception ex) {
      log.error("", ex);
    }
    return sysdate;
  }
}
