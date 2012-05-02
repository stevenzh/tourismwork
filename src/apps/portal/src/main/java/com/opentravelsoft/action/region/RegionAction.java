package com.opentravelsoft.action.region;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.util.LabelValueBean;

import com.opentravelsoft.entity.Destination;
import com.opentravelsoft.service.resource.DestinationService;
import com.opentravelsoft.webapp.action.PortalAction;

public class RegionAction extends PortalAction {
  private static final long serialVersionUID = -578354316071883166L;

  @Autowired
  private DestinationService destinationService;

  private List<List<LabelValueBean>> regions;

  private List<Destination> destinations;

  public String execute() throws Exception {
    /** 地区 */
    String regionId = "";

    // 取得子分类F
    List<List<LabelValueBean>> subRegions = destinationService
        .roGetSubRegions(regionId);

    regions = destinationService.getRegionGroupList();
    destinations = destinationService.getRegionList();

    return SUCCESS;
  }

  public List<List<LabelValueBean>> getRegions() {
    return regions;
  }

}
