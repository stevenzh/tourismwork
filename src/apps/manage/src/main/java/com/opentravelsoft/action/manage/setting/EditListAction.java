package com.opentravelsoft.action.manage.setting;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Lists;

/**
 * 参数设:旅游小贴士模板
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:03 $
 */
public class EditListAction extends ManageAction {
  private static final long serialVersionUID = 4025088482007685362L;

  protected static final Log logger = LogFactory.getLog(EditListAction.class);

  private Lists list = new Lists();

  private int listId = -1;

  private String listName;

  public String input() {
    if (listId > -1) {
      list = listService.getList(listId);
    }

    return INPUT;
  }

  public String submit() {
    int ret = listService.updateList(list);
    return SUCCESS;
  }

  public Lists getList() {
    return list;
  }

  public void setList(Lists list) {
    this.list = list;
  }

  public int getListId() {
    return listId;
  }

  public void setListId(int listId) {
    this.listId = listId;
  }

  public String getListName() {
    return listName;
  }

  public void setListName(String listName) {
    this.listName = listName;
  }

}
