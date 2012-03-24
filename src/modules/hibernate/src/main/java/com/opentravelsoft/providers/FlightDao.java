package com.opentravelsoft.providers;

import com.opentravelsoft.entity.Flight;
import com.opentravelsoft.util.PaginationSupport;

public interface FlightDao extends GenericDao<Flight, String>
{
    PaginationSupport getFlightList(String aireways, String lvAirport,
            String goAirport, int fromRecord, int moveCount);

}
