package com.opentravelsoft.action.manage.setting;

import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Lists;
import com.opentravelsoft.util.StringUtil;

/**
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:04 $
 */
public class ListAction extends ManageAction {
  private static final long serialVersionUID = 4025088482007685362L;

  protected static final Log logger = LogFactory.getLog(ListAction.class);

  private List<Lists> tmplList;

  private Set<String> listType;

  private int listId;

  private String listName;

  public String input() {
    if (StringUtil.hasLength(listName))
      tmplList = listService.searchList(listName);
    else
      tmplList = listService.getAllList();

    listType = listService.getListType();
    currentPage(tmplList.size());

    return INPUT;
  }

  public String execute() {
    tmplList = listService.searchList(listName);
    listType = listService.getListType();
    currentPage(tmplList.size());
    return SUCCESS;
  }

  public String delete() {
    listService.deleteList(listId);

    return SUCCESS;
  }

  public List<Lists> getTmplList() {
    return tmplList;
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

  public Set<String> getListType() {
    return listType;
  }

}
