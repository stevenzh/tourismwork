package com.opentravelsoft.action.manage.resource;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Airport;
import com.opentravelsoft.entity.City;
import com.opentravelsoft.entity.Country;
import com.opentravelsoft.service.resource.AirportService;
import com.opentravelsoft.util.PaginationSupport;

/**
 * 参数设置:机场维护
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:03 $
 */
public class ListAirportAction extends ManageAction
{
    private static final long serialVersionUID = 4025088482007685362L;

    protected static final Log logger = LogFactory
            .getLog(ListAirportAction.class);

    private AirportService airportService;

    private List<Airport> airportList;

    private List<Country> countrys;

    private List<City> citys;

    private List<LabelValueBean> delStates;

    private String airportId;

    // 查询条件

    /** 国家CODE */
    private String kenCountryId = "";

    private String kenCityId;

    private String kenDelkey = "2";

    public String input()
    {
        countrys = airportService.roGetCountry();
        citys = airportService.roGetCity(kenCountryId);
        delStates = getCodeList("ebiz_cancel_state");
        return INPUT;
    }

    public String execute()
    {
        dreamPage();
        PaginationSupport support = airportService.roGetAirportList(
                kenCountryId, kenCityId, kenDelkey, getFromRecord(),
                getMoveCount());
        airportList = support.getItems();
        countrys = airportService.roGetCountry();
        citys = airportService.roGetCity(kenCountryId);
        delStates = getCodeList("ebiz_cancel_state");
        currentPage(support.getTotalCount());

        return SUCCESS;
    }

    public String delete()
    {
        Airport fm = airportService.get(airportId);
        if (null == fm)
        {
            // 要删除的记录不存在
            addActionError("ERR_A10001");
        } else
        {
            airportService.txDelete(fm);
        }

        return SUCCESS;
    }

    @Autowired
    public void setAirportService(AirportService airportService)
    {
        this.airportService = airportService;
    }

    public List<Country> getCountrys()
    {
        return countrys;
    }

    public List<City> getCitys()
    {
        return citys;
    }

    public List<Airport> getAirportList()
    {
        return airportList;
    }

    public String getKenCityId()
    {
        return kenCityId;
    }

    public void setKenCityId(String kenCityId)
    {
        this.kenCityId = kenCityId;
    }

    public String getKenCountryId()
    {
        return kenCountryId;
    }

    public void setKenCountryId(String kenCountryId)
    {
        this.kenCountryId = kenCountryId;
    }

    public String getKenDelkey()
    {
        return kenDelkey;
    }

    public void setKenDelkey(String kenDelkey)
    {
        this.kenDelkey = kenDelkey;
    }

    public List<LabelValueBean> getDelStates()
    {
        return delStates;
    }

    public String getAirportId()
    {
        return airportId;
    }

    public void setAirportId(String airportId)
    {
        this.airportId = airportId;
    }

}
