package com.opentravelsoft.action.manage.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Airways;
import com.opentravelsoft.entity.ShareFlight;
import com.opentravelsoft.service.product.ShareFlightService;

public class EditShareFlightAction extends ManageAction {
  private static final long serialVersionUID = -7956804047797701224L;

  private ShareFlightService shareFlightService;

  private Integer shFlightId;

  private ShareFlight tblShareFlight;

  private List<Airways> airways = new ArrayList<Airways>();

  @Autowired
  public void setShareFlightService(ShareFlightService shareFlightService) {
    this.shareFlightService = shareFlightService;
  }

  public String input() {
    if (shFlightId != null) {
      // Modify
      tblShareFlight = shareFlightService.roGetShareFlight(shFlightId);
    } else {
      tblShareFlight = new ShareFlight();
    }
    airways = shareFlightService.roGetAllAirWays();
    return INPUT;
  }

  public String submit() {
    int result = shareFlightService.txEditShareFlight(tblShareFlight);
    if (result < 0) {
      addActionError("添加失败");
      return INPUT;
    }
    return SUCCESS;
  }

  public void setAirways(List<Airways> airways) {
    this.airways = airways;
  }

  public List<Airways> getAirways() {
    return airways;
  }

  public Integer getShFlightId() {
    return shFlightId;
  }

  public void setShFlightId(Integer shFlightId) {
    this.shFlightId = shFlightId;
  }

  public ShareFlight getTblShareFlight() {
    return tblShareFlight;
  }

  public void setTblShareFlight(ShareFlight tblShareFlight) {
    this.tblShareFlight = tblShareFlight;
  }

}
