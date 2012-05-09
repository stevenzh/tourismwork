package com.opentravelsoft.providers.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.hibernate.SessionFactory;

import com.opentravelsoft.entity.PortalRole;
import com.opentravelsoft.providers.LookupDao;

/**
 * Hibernate implementation of LookupDao.
 *
 */
@Repository
public class LookupDaoHibernate implements LookupDao {
    private Log log = LogFactory.getLog(LookupDaoHibernate.class);
    private HibernateTemplate hibernateTemplate;

    @Autowired
    public LookupDaoHibernate(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<PortalRole> getRoles() {
        log.debug("Retrieving all role names...");

        return hibernateTemplate.find("from PortalRole order by name");
    }
}
