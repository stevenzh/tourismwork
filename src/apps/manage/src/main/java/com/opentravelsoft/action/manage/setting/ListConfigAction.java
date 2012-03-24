package com.opentravelsoft.action.manage.setting;

import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.SysConfig;
import com.opentravelsoft.service.setting.SysConfigService;

/**
 * 参数设置:旅游小贴士模板
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:04 $
 */
public class ListConfigAction extends ManageAction {
  private static final long serialVersionUID = 4025088482007685362L;

  protected static final Log logger = LogFactory.getLog(ListConfigAction.class);

  private SysConfigService sysConfigService;

  private List<SysConfig> tmplList;

  private Set<String> category;

  private int configId;

  private String listName;

  public String input() {
    tmplList = sysConfigService.getAllConfig();
    category = sysConfigService.getCategory();
    currentPage(tmplList.size());

    return INPUT;
  }

  public String execute() {
    tmplList = sysConfigService.getConfigByCategory(listName);
    category = sysConfigService.getCategory();
    currentPage(tmplList.size());
    return SUCCESS;
  }

  public String delete() {
    sysConfigService.deleteConfig(configId);
    return SUCCESS;
  }

  @Autowired
  public void setSysConfigService(SysConfigService sysConfigService) {
    this.sysConfigService = sysConfigService;
  }

  public List<SysConfig> getTmplList() {
    return tmplList;
  }

  public int getConfigId() {
    return configId;
  }

  public void setConfigId(int configId) {
    this.configId = configId;
  }

  public Set<String> getCategory() {
    return category;
  }

  public void setListName(String listName) {
    this.listName = listName;
  }

  public String getListName() {
    return listName;
  }

}
