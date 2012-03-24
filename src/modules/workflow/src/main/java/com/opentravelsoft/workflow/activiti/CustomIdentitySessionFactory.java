package com.opentravelsoft.workflow.activiti;

import org.activiti.engine.IdentityService;
import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;

public class CustomIdentitySessionFactory implements SessionFactory {

  private org.hibernate.SessionFactory sessionFactory;

  public final void setSessionFactory(
      org.hibernate.SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public Session openSession() {
    IdentitySessionImpl s = new IdentitySessionImpl();
     s.session = sessionFactory.getCurrentSession();
     return (Session) s; 
  }

  public Class<?> getSessionType() {
    return IdentityService.class;
  }

}
