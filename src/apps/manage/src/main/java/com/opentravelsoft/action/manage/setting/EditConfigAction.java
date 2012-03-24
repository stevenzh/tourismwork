package com.opentravelsoft.action.manage.setting;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.SysConfig;
import com.opentravelsoft.service.setting.SysConfigService;

/**
 * 参数设:旅游小贴士模板
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:03 $
 */
public class EditConfigAction extends ManageAction {
  private static final long serialVersionUID = 4025088482007685362L;

  protected static final Log logger = LogFactory.getLog(EditConfigAction.class);

  private SysConfigService sysConfigService;

  private SysConfig config;

  private int configId = -1;

  public String input() {
    if (configId > -1) {
      config = sysConfigService.getConfig(configId);
    }

    return INPUT;
  }

  public String submit() {
    sysConfigService.updateConfig(config);
    ActionContext.getContext().getApplication()
        .put(config.getName(), config.getValue());
    return SUCCESS;
  }

  @Autowired
  public void setSysConfigService(SysConfigService sysConfigService) {
    this.sysConfigService = sysConfigService;
  }

  public int getConfigId() {
    return configId;
  }

  public void setConfigId(int configId) {
    this.configId = configId;
  }

  public SysConfig getConfig() {
    return config;
  }

  public void setConfig(SysConfig config) {
    this.config = config;
  }

}
