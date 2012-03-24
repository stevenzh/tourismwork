package com.opentravelsoft.providers;

import java.util.List;

import com.opentravelsoft.entity.Sight;
import com.opentravelsoft.entity.product.SightTrait;
import com.opentravelsoft.util.PaginationSupport;

/**
 * 景点
 * 
 * @author zhangst
 */
public interface SightDao extends GenericDao<Sight, String> {
  public List<SightTrait> getSightPicMangeList(String sightNo);

  public List<Sight> getSightList(String province, String country,
      String sightName, String destinationNo);

  public int editSight(Sight sight, String method);

  public PaginationSupport getSightList(String country, String province,
      String name, int fromRecord, int moveCount);

}
