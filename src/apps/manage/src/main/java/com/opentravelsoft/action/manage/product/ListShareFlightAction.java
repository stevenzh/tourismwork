package com.opentravelsoft.action.manage.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.ShareFlight;
import com.opentravelsoft.service.product.ShareFlightService;

public class ListShareFlightAction extends ManageAction {

  private ShareFlightService shareFlightService;

  private static final long serialVersionUID = 1L;

  private List<ShareFlight> shareFlight;

  private Integer shFlightId;

  @Autowired
  public void setShareFlightService(ShareFlightService shareFlightService) {
    this.shareFlightService = shareFlightService;
  }

  public String input() {
    shareFlight = shareFlightService.roFind();
    return INPUT;
  }

  public String delete() {
    shareFlightService.txDeleteShareFlight(shFlightId);
    return SUCCESS;
  }

  public List<ShareFlight> getShareFlight() {
    return shareFlight;
  }

  public void setShareFlight(List<ShareFlight> shareFlight) {
    this.shareFlight = shareFlight;
  }

  public Integer getShFlightId() {
    return shFlightId;
  }

  public void setShFlightId(Integer shFlightId) {
    this.shFlightId = shFlightId;
  }

}
