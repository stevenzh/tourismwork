package com.opentravelsoft.action.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.entity.VisaHelp;
import com.opentravelsoft.service.VisaHelpService;
import com.opentravelsoft.webapp.action.PortalAction;

public class VisaListAction extends PortalAction {
  private static final long serialVersionUID = 3694700161945586568L;

  @Autowired
  private VisaHelpService visaHelpService;

  private List<VisaHelp> visas;

  private String type = "S";

  @Override
  public String execute() throws Exception {
    visas = visaHelpService.roGetUsableVisaItems(type);

    return SUCCESS;
  }

  public List<VisaHelp> getVisas() {
    return visas;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

}
