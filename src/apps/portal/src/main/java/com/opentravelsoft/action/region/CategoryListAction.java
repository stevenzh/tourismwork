package com.opentravelsoft.action.region;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.entity.Destination;
import com.opentravelsoft.service.resource.DestinationService;
import com.opentravelsoft.webapp.action.PortalAction;

public class CategoryListAction extends PortalAction {

  private static final long serialVersionUID = -6317494752381582812L;

  @Autowired
  private DestinationService categoryService;

  private List<Destination> lds;

  @Override
  public String execute() throws Exception {
    lds = categoryService.getCategirys();
    return super.execute();
  }

  public List<Destination> getLds() {
    return lds;
  }

}
