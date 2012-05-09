package com.opentravelsoft.service.impl;

import java.util.List;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.opentravelsoft.entity.Shortcut;
import com.opentravelsoft.providers.ShortcutDao;
import com.opentravelsoft.service.ShortcutManager;

/**
 * Implementation of UserManager interface.
 * 
 */
@Service("shortcutManager")
@WebService(serviceName = "ShortcutService", endpointInterface = "com.opentravelsoft.service.ShortcutManager")
public class ShortcutManagerImpl extends GenericManagerImpl<Shortcut, Long>
    implements ShortcutManager {
  private ShortcutDao shortcutDao;

  @Autowired
  public void setShortcutDao(ShortcutDao shortcutDao) {
    this.dao = shortcutDao;
    this.shortcutDao = shortcutDao;
  }

  public Shortcut getShortcut(String shortcutId) {
    return shortcutDao.get(new Long(shortcutId));
  }

  public List<Shortcut> getShortcuts() {
    return shortcutDao.getAllDistinct();
  }

  public Shortcut saveShortcut(Shortcut shortcut) {
    try {
      return shortcutDao.save(shortcut);
    } catch (DataIntegrityViolationException e) {
      log.warn(e.getMessage() + "User '" + shortcut.getDisplayName()
          + "' already exists!");
    }
    return null;
  }

  public int removeShortcut(String shortcutId) {
    log.debug("removing user: " + shortcutId);
    shortcutDao.remove(new Long(shortcutId));
    return 0;
  }

  public List<Shortcut> getShortcutByModule(String moduleName) {
    return shortcutDao.loadShortByModule(moduleName);
  }

}
