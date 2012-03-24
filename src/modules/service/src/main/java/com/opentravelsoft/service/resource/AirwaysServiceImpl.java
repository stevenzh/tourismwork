package com.opentravelsoft.service.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.entity.Airways;
import com.opentravelsoft.providers.AirwaysDao;
import com.opentravelsoft.util.PaginationSupport;

@Service("AirwaysService")
public class AirwaysServiceImpl implements AirwaysService {
  private AirwaysDao airwaysDao;

  @Autowired
  public void setAirwaysDao(AirwaysDao airwaysDao) {
    this.airwaysDao = airwaysDao;
  }

  public Airways roGetAirwaysDetail(String airwaysId) {
    return airwaysDao.get(airwaysId);
  }

  public int txDelete(Airways airways) {
    return airwaysDao.delete(airways);
  }

  public void txSave(Airways airways) {
    airwaysDao.save(airways);
  }

  public List<Airways> roGetAirwaysList() {
    return airwaysDao.getAll(false);
  }

  public PaginationSupport getAirwaysList(boolean isActive, int fromRecord,
      int moveCount) {
    return airwaysDao.getAirwaysList(isActive, fromRecord, moveCount);
  }

  public List<Airways> getAirwaysList(boolean isActive) {
    return airwaysDao.getAll(isActive);
  }
}
