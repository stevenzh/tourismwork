package com.opentravelsoft.service;

import java.util.List;

import com.opentravelsoft.util.LabelValueBean;

import com.opentravelsoft.entity.Destination;
import com.opentravelsoft.providers.DestinationDao;

/**
 * 
 * @author zhangst
 * @deprecated
 */
public class RegionServiceImpl implements RegionService {
  private DestinationDao categoryDao;

  public void setCategoryDao(DestinationDao categoryDao) {
    this.categoryDao = categoryDao;
  }

  public List<List<LabelValueBean>> getRegionGroupList() {
    return categoryDao.getRegionGroupList();
  }

  public List<Destination> getRegionList() {
    return categoryDao.getAllDestination();
  }

}
