package com.opentravelsoft.providers;

import java.util.List;

import com.opentravelsoft.entity.Airways;
import com.opentravelsoft.util.PaginationSupport;

public interface AirwaysDao extends GenericDao<Airways, String> {
  public List<Airways> getAll(boolean usable);

  public int delete(Airways airways);

  public PaginationSupport getAirwaysList(boolean usable, int fromRecord,
      int pageSize);

}
