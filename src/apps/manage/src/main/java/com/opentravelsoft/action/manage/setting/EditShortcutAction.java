package com.opentravelsoft.action.manage.setting;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.model.Module;
import com.opentravelsoft.model.Shortcut;
import com.opentravelsoft.service.ShortcutManager;
import com.opentravelsoft.service.module.ModuleService;
import com.opentravelsoft.util.StringUtil;

/**
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 */
public class EditShortcutAction extends ManageAction {
  private static final long serialVersionUID = 4025088482007685362L;

  protected static final Log logger = LogFactory
      .getLog(EditShortcutAction.class);

  @Autowired
  private ShortcutManager shortcutManager;

  @Autowired
  private ModuleService moduleService;

  private Shortcut shortcut;

  private List<Module> moduleList;

  private String shortcutId;

  private String kenModuleName;

  public String input() {
    if (StringUtil.hasLength(shortcutId))
      shortcut = shortcutManager.getShortcut(shortcutId);
    else
      shortcut = new Shortcut();

    moduleList = moduleService.roGetModuleList(true);

    return INPUT;
  }

  public String execute() {
    try {
      shortcut = shortcutManager.saveShortcut(shortcut);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  public String getShortcutId() {
    return shortcutId;
  }

  public void setShortcutId(String shortcutId) {
    this.shortcutId = shortcutId;
  }

  public String getKenModuleName() {
    return kenModuleName;
  }

  public void setKenModuleName(String kenModuleName) {
    this.kenModuleName = kenModuleName;
  }

  public List<Module> getModuleList() {
    return moduleList;
  }

  public Shortcut getShortcut() {
    return shortcut;
  }

  public void setShortcut(Shortcut shortcutList) {
    this.shortcut = shortcutList;
  }

}
