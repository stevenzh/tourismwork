package com.opentravelsoft.action.manage.product;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Premium;
import com.opentravelsoft.service.product.PremiumService;
import com.opentravelsoft.util.StringUtil;

/**
 * 修改保险信息
 * 
 * @author zhangst
 * 
 */
public class EditPremiumAction extends ManageAction {
  private PremiumService premiumService;

  private static final long serialVersionUID = 7538617605340027473L;

  private String preminuCode;

  private Premium tblPremium;

  @Autowired
  public void setPremiumService(PremiumService premiumService) {
    this.premiumService = premiumService;
  }

  public String input() {
    if (StringUtil.hasLength(preminuCode)) {
      // Modify
      tblPremium = premiumService.roGetPrem(preminuCode);
    } else {
      tblPremium = new Premium();
    }
    return INPUT;
  }

  public String submit() {
    Employee user = getUser();
    tblPremium.setUpdatedBy(user.getUserId());
    premiumService.txEditPrem(tblPremium);
    return SUCCESS;
  }

  public String getPreminuCode() {
    return preminuCode;
  }

  public void setPreminuCode(String preminuCode) {
    this.preminuCode = preminuCode;
  }

  public Premium getTblPremium() {
    return tblPremium;
  }

  public void setTblPremium(Premium tblPremium) {
    this.tblPremium = tblPremium;
  }

}
