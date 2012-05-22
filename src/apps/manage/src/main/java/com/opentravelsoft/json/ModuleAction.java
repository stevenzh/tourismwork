package com.opentravelsoft.json;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.service.module.ModuleService;

/**
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:16 $
 */
public class ModuleAction extends ManageAction {
  private static final long serialVersionUID = -5840178415466047565L;

  private ModuleService moduleService;

  private String result;

  private int roleId;

  private String moName;

  private String moduleAction;

  private String permValue;

  public String execute() {
    result = moduleService.txSaveRoleModelPerm(roleId, moName, moduleAction,
        permValue);
    return Action.SUCCESS;
  }

  @Autowired
  public void setModuleService(ModuleService moduleService) {
    this.moduleService = moduleService;
  }

  public String getResult() {
    return result;
  }

  public void setRoleId(int roleId) {
    this.roleId = roleId;
  }

  public String getModuleAction() {
    return moduleAction;
  }

  public String getMoName() {
    return moName;
  }

  public void setMoName(String moName) {
    this.moName = moName;
  }

  public void setPermValue(String permValue) {
    this.permValue = permValue;
  }

  public ModuleService getModuleService() {
    return moduleService;
  }

  public int getRoleId() {
    return roleId;
  }

  public String getPermValue() {
    return permValue;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public void setModuleAction(String moduleAction) {
    this.moduleAction = moduleAction;
  }

}
