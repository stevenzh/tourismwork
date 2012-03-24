package com.opentravelsoft.action.manage.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Premium;
import com.opentravelsoft.service.product.PremiumService;

/**
 * 保险信息列表
 * 
 * @author zhangst
 * 
 */
public class ListPremiumAction extends ManageAction {

  private PremiumService premiumService;

  private static final long serialVersionUID = 7538617605340027473L;

  private List<Premium> preminus;

  private String preminuCode;

  @Autowired
  public void setPremiumService(PremiumService premiumService) {
    this.premiumService = premiumService;
  }

  public String input() {
    preminus = premiumService.roFind();
    return INPUT;
  }

  public String delete() {
    int result = premiumService.txDeletePrem(preminuCode);
    return SUCCESS;
  }

  public List<Premium> getPreminus() {
    return preminus;
  }

  public void setPreminuCode(String preminuCode) {
    this.preminuCode = preminuCode;
  }

}
