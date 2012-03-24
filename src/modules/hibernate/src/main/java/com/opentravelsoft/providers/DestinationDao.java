package com.opentravelsoft.providers;

import java.util.List;

import com.opentravelsoft.util.LabelValueBean;

import com.opentravelsoft.entity.Destination;

/**
 * 旅游目的地
 * 
 * @author zhangst
 * 
 */
public interface DestinationDao extends GenericDao<Destination, Long> {
  /**
   * 取得所有的目的地
   * @return
   */
  List<Destination> getAllDestination();

  /**
   * 删除分类
   * 
   * @param catId
   * @return
   */
  int delDestination(long catId);

  public List<List<LabelValueBean>> getRegionGroupList();

  /**
   * 取得该分类的所有子分类
   * 
   * @param regionId
   * @return
   */
  public List<List<LabelValueBean>> getSubRegions(String regionId);

  /**
   * 网站显示常用目的地
   * @return
   */
  public List<Destination> getTopDestination();

  List<Destination> getRegionList();

}
