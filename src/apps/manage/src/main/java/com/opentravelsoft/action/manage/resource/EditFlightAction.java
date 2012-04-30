package com.opentravelsoft.action.manage.resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Flight;
import com.opentravelsoft.service.resource.FlightService;

/**
 * 参数设置:航班维护
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:04 $
 */
public class EditFlightAction extends ManageAction {
  private static final long serialVersionUID = 4025088482007685362L;

  protected static final Log logger = LogFactory.getLog(EditFlightAction.class);

  @Autowired
  private FlightService flightService;

  private Flight flight = new Flight();

  private String flightNo;

  public String input() {
    flight = flightService.roGetFlightDetail(flightNo);
    return INPUT;
  }

  public String submit() {
    flightService.txUpdate(flight);
    return SUCCESS;
  }

  public Flight getFlight() {
    return flight;
  }

  public void setFlight(Flight flight) {
    this.flight = flight;
  }

  public String getFlightNo() {
    return flightNo;
  }

  public void setFlightNo(String flightNo) {
    this.flightNo = flightNo;
  }

}
