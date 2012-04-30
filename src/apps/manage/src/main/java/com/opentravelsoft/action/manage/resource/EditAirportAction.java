package com.opentravelsoft.action.manage.resource;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Airport;
import com.opentravelsoft.entity.City;
import com.opentravelsoft.entity.Country;
import com.opentravelsoft.service.resource.AirportService;

/**
 * 参数设置:机场维护
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:03 $
 */
public class EditAirportAction extends ManageAction {
  private static final long serialVersionUID = 4025088482007685362L;

  protected static final Log logger = LogFactory
      .getLog(EditAirportAction.class);

  @Autowired
  private AirportService airportService;

  private Airport airport = new Airport();

  private String airportId;

  private List<Country> countrys;

  private List<City> citys;

  // 查询条件

  /** 国家CODE */
  private String kenCountryId;

  private String kenCityId;

  private String kenDelkey;

  public String input() {
    countrys = airportService.roGetCountry();
    citys = airportService.roGetCity(kenCountryId);
    airport = airportService.get(airportId);
    return INPUT;
  }

  public String submit() {
    Airport sdf = airportService.get(airport.getCode());
    if (null != sdf) {
      addActionError("代码重复");
      return ERROR;
    } else {
      airportService.save(airport);
    }

    return SUCCESS;
  }

  public List<Country> getCountrys() {
    return countrys;
  }

  public List<City> getCitys() {
    return citys;
  }

  public Airport getAirport() {
    return airport;
  }

  public void setAirport(Airport airport) {
    this.airport = airport;
  }

  public String getKenCityId() {
    return kenCityId;
  }

  public void setKenCityId(String kenCityId) {
    this.kenCityId = kenCityId;
  }

  public String getKenCountryId() {
    return kenCountryId;
  }

  public void setKenCountryId(String kenCountryId) {
    this.kenCountryId = kenCountryId;
  }

  public String getKenDelkey() {
    return kenDelkey;
  }

  public void setKenDelkey(String kenDelkey) {
    this.kenDelkey = kenDelkey;
  }

  public String getAirportId() {
    return airportId;
  }

  public void setAirportId(String airportId) {
    this.airportId = airportId;
  }

}
