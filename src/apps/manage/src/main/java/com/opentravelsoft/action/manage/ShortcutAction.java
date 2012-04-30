package com.opentravelsoft.action.manage;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.common.EbizCommon;
import com.opentravelsoft.entity.ProductItem;
import com.opentravelsoft.model.Shortcut;
import com.opentravelsoft.service.ShortcutManager;
import com.opentravelsoft.util.StringUtil;

/**
 * Express
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:16 $
 */
public class ShortcutAction extends ManageAction {
  private static final long serialVersionUID = 2886687498402018762L;

  @Autowired
  private ShortcutManager shortcutManager;

  private List<Shortcut> shortcutList;

  private String role;

  public String getRole() {
    return role;
  }

  public void setShortcutList(List<Shortcut> shortcutList) {
    this.shortcutList = shortcutList;
  }

  public void setMenuItemName(String menuItemName) {
    this.menuItemName = menuItemName;
  }

  private String menuItemName;

  public String execute() {
    String nm = "";
    String actionName = getModuleName();
    if (StringUtil.hasLength(actionName)) {
      nm = actionName.substring(0, actionName.indexOf("_"));
    }

    shortcutList = shortcutManager.getShortcutByModule(nm);
    if (nm.equalsIgnoreCase("line")) {
      List<ProductItem> items = (List) ServletActionContext.getContext()
          .getApplication().get(EbizCommon.EBIZ_APP_PACKAGE_ITEM);

      for (ProductItem productItem : items) {
        Shortcut it = new Shortcut();
        it.setDisplayName(productItem.getItemName());
        it.setRelativePath(productItem.getLinkUrl());
        shortcutList.add(it);
      }
    }
    return SUCCESS;
  }

  public List<Shortcut> getShortcutList() {
    return shortcutList;
  }

  public void setRole(String role) {
    this.role = role;
  }

}
