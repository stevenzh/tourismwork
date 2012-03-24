package com.opentravelsoft.providers.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.opentravelsoft.model.Shortcut;
import com.opentravelsoft.providers.ShortcutDao;

@Repository("shortcutDao")
public class ShortcutHibernate extends GenericDaoHibernate<Shortcut, Long>
    implements ShortcutDao {

  public ShortcutHibernate() {
    super(Shortcut.class);
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  public List<Shortcut> loadShortByModule(String moduleName) {
    List list = getHibernateTemplate()
        .find(
            "from Shortcut where moduleName=? and shortcutEnabled=true order by id",
            moduleName);
    return list;
  }

}
