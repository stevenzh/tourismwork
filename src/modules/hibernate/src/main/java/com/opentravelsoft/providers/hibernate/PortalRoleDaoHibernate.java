package com.opentravelsoft.providers.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.PortalRole;
import com.opentravelsoft.providers.MemberRoleDao;

/**
 * This class interacts with Spring's HibernateTemplate to save/delete and
 * retrieve Role objects.
 * 
 */
@Repository
public class PortalRoleDaoHibernate extends
    GenericDaoHibernate<PortalRole, Long> implements MemberRoleDao {

  /**
   * Constructor to create a Generics-based version using Role as the entity
   */
  public PortalRoleDaoHibernate() {
    super(PortalRole.class);
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("rawtypes")
  public PortalRole getRoleByName(String rolename) {
    List roles = getHibernateTemplate().find("from PortalRole where name=?",
        rolename);
    if (roles.isEmpty()) {
      return null;
    } else {
      return (PortalRole) roles.get(0);
    }
  }

  /**
   * {@inheritDoc}
   */
  public void removeRole(String rolename) {
    Object role = getRoleByName(rolename);
    getHibernateTemplate().delete(role);
  }
}
