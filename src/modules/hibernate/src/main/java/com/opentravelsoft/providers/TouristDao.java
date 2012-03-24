package com.opentravelsoft.providers;

import java.util.List;

import com.opentravelsoft.entity.Tourist;

public interface TouristDao extends GenericDao<Tourist, String> {

  /**
   * 取得游客列表
   * 
   * @param nmno
   * @return
   */
  public List<Tourist> findByNmno(String[] nmno);

  /**
   * 取得一个游客信息
   * 
   * @param nmno 游客ID
   * @return
   */
  public Tourist findCustomerByNmno(String nmno);

}
