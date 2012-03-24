package com.opentravelsoft.action.manage.resource;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Airport;
import com.opentravelsoft.entity.Airways;
import com.opentravelsoft.entity.Flight;
import com.opentravelsoft.service.resource.AirportService;
import com.opentravelsoft.service.resource.AirwaysService;
import com.opentravelsoft.service.resource.FlightService;
import com.opentravelsoft.util.PaginationSupport;
import com.opentravelsoft.util.StringUtil;

/**
 * 参数设置:航班维护
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:04 $
 */
public class ListFlightAction extends ManageAction {
  private static final long serialVersionUID = 4025088482007685362L;

  protected static final Log logger = LogFactory.getLog(ListFlightAction.class);

  private FlightService flightService;

  private AirportService airportService;

  private AirwaysService airwaysService;

  private List<Flight> flightList;

  private String flightNo;

  private List<Airways> airways;

  private List<Airport> airports;

  // ------------------------------------------------------------------------

  private String kenAirways;

  private String kenLvAirport;

  private String kenGoAirport;

  // ------------------------------------------------------------------------
  @SuppressWarnings("unchecked")
  public String execute() {
    airways = airwaysService.getAirwaysList(true);
    airports = airportService.getAirportList(true);
    PaginationSupport support = flightService.getFlightList(kenAirways,
        kenLvAirport, kenGoAirport, getFromRecord(), getMoveCount());
    flightList = support.getItems();
    currentPage(support.getTotalCount());
    return SUCCESS;
  }

  public String delete() {
    if (StringUtil.hasLength(flightNo))
      flightService.txDelete(flightNo);

    return SUCCESS;
  }

  @Autowired
  public void setFlightService(FlightService flightService) {
    this.flightService = flightService;
  }

  @Autowired
  public void setAirportService(AirportService airportService) {
    this.airportService = airportService;
  }

  @Autowired
  public void setAirwaysService(AirwaysService airwaysService) {
    this.airwaysService = airwaysService;
  }

  public List<Flight> getFlightList() {
    return flightList;
  }

  public String getFlightNo() {
    return flightNo;
  }

  public void setFlightNo(String flightNo) {
    this.flightNo = flightNo;
  }

  public String getKenAirways() {
    return kenAirways;
  }

  public void setKenAirways(String kenAirways) {
    this.kenAirways = kenAirways;
  }

  public String getKenLvAirport() {
    return kenLvAirport;
  }

  public void setKenLvAirport(String kenLvAirport) {
    this.kenLvAirport = kenLvAirport;
  }

  public String getKenGoAirport() {
    return kenGoAirport;
  }

  public void setKenGoAirport(String kenGoAirport) {
    this.kenGoAirport = kenGoAirport;
  }

  public List<Airways> getAirways() {
    return airways;
  }

  public List<Airport> getAirports() {
    return airports;
  }

}
