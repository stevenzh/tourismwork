package com.opentravelsoft.action.product;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.entity.VisaHelp;
import com.opentravelsoft.service.VisaHelpService;
import com.opentravelsoft.webapp.action.PortalAction;

public class VisaHelpAction extends PortalAction {
  private static final long serialVersionUID = 2918705663769956262L;

  @Autowired
  private VisaHelpService visaHelpService;

  private VisaHelp visaItem;

  private String visaId;

  @Override
  public String execute() {
    visaItem = visaHelpService.roGetVisaItem(visaId);
    return SUCCESS;
  }

  public String getVisaId() {
    return visaId;
  }

  public void setVisaId(String visaId) {
    this.visaId = visaId;
  }

  public VisaHelp getVisaItem() {
    return visaItem;
  }
}
