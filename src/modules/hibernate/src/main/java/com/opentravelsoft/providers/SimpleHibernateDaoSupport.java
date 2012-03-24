package com.opentravelsoft.providers;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.opentravelsoft.entity.Lists;

public class SimpleHibernateDaoSupport extends HibernateDaoSupport {

  /**
   * Log variable for all child classes. Uses LogFactory.getLog(getClass()) from
   * Commons Logging
   */
  protected final Log log = LogFactory.getLog(getClass());

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
