package com.opentravelsoft.service.resource;

import java.util.List;

import com.opentravelsoft.entity.Airways;
import com.opentravelsoft.util.PaginationSupport;

public interface AirwaysService {
  public void txSave(Airways airways);

  public int txDelete(Airways airways);

  public List<Airways> roGetAirwaysList();

  public Airways roGetAirwaysDetail(String airwaysId);

  public PaginationSupport getAirwaysList(boolean isActive, int fromRecord,
      int moveCount);

  List<Airways> getAirwaysList(boolean isActive);
}
