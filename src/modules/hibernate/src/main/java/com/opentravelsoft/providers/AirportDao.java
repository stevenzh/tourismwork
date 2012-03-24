package com.opentravelsoft.providers;

import java.util.List;

import com.opentravelsoft.entity.Airport;
import com.opentravelsoft.util.PaginationSupport;

public interface AirportDao extends GenericDao<Airport, String>
{

    public PaginationSupport getAirportList(String country, String city,
            String delkey, int fromRecord, int pageSize);

    public int delete(Airport airways);

    public List<Airport> getAirportList(boolean isActive);

}
