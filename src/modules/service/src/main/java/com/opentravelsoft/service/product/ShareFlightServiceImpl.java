package com.opentravelsoft.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.entity.Airways;
import com.opentravelsoft.entity.ShareFlight;
import com.opentravelsoft.providers.AirwaysDao;
import com.opentravelsoft.providers.product.ShareFlightDao;

@Service("ShareFlightService")
public class ShareFlightServiceImpl implements ShareFlightService {

  private ShareFlightDao shareFlightDao;

  private AirwaysDao airwaysDao;

  @Autowired
  public void setAirwaysDao(AirwaysDao airwaysDao) {
    this.airwaysDao = airwaysDao;
  }

  @Autowired
  public void setShareFlightDao(ShareFlightDao shareFlightDao) {
    this.shareFlightDao = shareFlightDao;
  }

  public List<ShareFlight> roFind() {
    return shareFlightDao.findAll();
  }

  public ShareFlight roGetShareFlight(long shareFlightId) {
    return shareFlightDao.get(shareFlightId);
  }

  public void txDeleteShareFlight(long shareFlightId) {
    shareFlightDao.remove(shareFlightId);
  }

  public int txEditShareFlight(ShareFlight shareFlight) {
    return shareFlightDao.editShareFlight(shareFlight);
  }

  public List<Airways> roGetAllAirWays() {
    return airwaysDao.getAll(true);
  }

}
