package com.opentravelsoft.action.manage.setting;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.model.Module;
import com.opentravelsoft.service.module.ModuleService;

/**
 * 系统管理：模块列表
 * 
 * @author udb
 */
public class ListModuleAction extends ManageAction {
  private static final long serialVersionUID = 2914390488320236698L;

  protected static final Log logger = LogFactory.getLog(ListModuleAction.class);

  private int moduleId;

  private String keyword;

  @Autowired
  private ModuleService moduleService;

  private List<Module> moduleList;

  public String input() throws Exception {
    moduleList = moduleService.roGetModuleList(false);
    return INPUT;
  }

  public String query() throws Exception {
    moduleList = moduleService.roGetQueryModule(keyword);
    return SUCCESS;
  }

  public String delete() throws Exception {
    moduleService.txDelModule(moduleId);
    return SUCCESS;
  }

  public int getModuleId() {
    return moduleId;
  }

  public void setModuleId(int moduleId) {
    this.moduleId = moduleId;
  }

  public List<Module> getModuleList() {
    return moduleList;
  }

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

}
