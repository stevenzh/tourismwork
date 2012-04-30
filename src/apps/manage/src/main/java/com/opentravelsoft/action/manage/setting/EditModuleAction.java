package com.opentravelsoft.action.manage.setting;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.model.Module;
import com.opentravelsoft.service.module.ModuleService;

/**
 * 系统设置：模块修改
 * 
 * @author udb
 */
public class EditModuleAction extends ManageAction {
  private static final long serialVersionUID = 2909557567120180025L;

  protected static final Log logger = LogFactory.getLog(EditModuleAction.class);

  private int moduleId;

  @Autowired
  private ModuleService moduleService;

  private Module module = new Module();

  public String input() throws Exception {
    if (moduleId != 0)
      module = moduleService.roGetModuleDetail(moduleId);

    return INPUT;
  }

  public String submit() throws Exception {
    moduleService.txSaveModule(module);
    return SUCCESS;
  }

  public Module getModule() {
    return module;
  }

  public void setModule(Module guide) {
    this.module = guide;
  }

  public int getModuleId() {
    return moduleId;
  }

  public void setModuleId(int moduleId) {
    this.moduleId = moduleId;
  }

}
