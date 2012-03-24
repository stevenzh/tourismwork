package com.opentravelsoft.service.product;

import java.util.List;

import com.opentravelsoft.entity.Airways;
import com.opentravelsoft.entity.ShareFlight;

public interface ShareFlightService
{
    List<ShareFlight> roFind();

    ShareFlight roGetShareFlight(long shareFlightId);

    int txEditShareFlight(ShareFlight shareFlight);

    void txDeleteShareFlight(long shareFlightId);

    List<Airways> roGetAllAirWays();

}
