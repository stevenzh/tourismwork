package com.opentravelsoft.action.manage.setting;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Module;
import com.opentravelsoft.entity.Shortcut;
import com.opentravelsoft.service.ShortcutManager;
import com.opentravelsoft.service.module.ModuleService;
import com.opentravelsoft.util.StringUtil;

/**
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 */
public class ListShortcutAction extends ManageAction {
  private static final long serialVersionUID = 4025088482007685362L;

  protected static final Log logger = LogFactory
      .getLog(ListShortcutAction.class);

  @Autowired
  private ShortcutManager shortcutManager;

  @Autowired
  private ModuleService moduleService;

  private List<Shortcut> shortcutList;

  private List<Module> moduleList;

  private String shortcutId;

  private String kenModuleName;

  public String execute() {
    if (StringUtil.hasLength(kenModuleName))
      shortcutList = shortcutManager.getShortcutByModule(kenModuleName);
    else
      shortcutList = shortcutManager.getShortcuts();
    moduleList = moduleService.roGetModuleList(true);
    currentPage(shortcutList.size());
    return SUCCESS;
  }

  public String del() {
    int ret;
    try {
      ret = shortcutManager.removeShortcut(shortcutId);
      if (ret < 0) {
        // 要删除的记录不存在
        addActionError("ERR_A10001");
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
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

  public List<Shortcut> getShortcutList() {
    return shortcutList;
  }

  public void setShortcutList(List<Shortcut> shortcutList) {
    this.shortcutList = shortcutList;
  }

}
