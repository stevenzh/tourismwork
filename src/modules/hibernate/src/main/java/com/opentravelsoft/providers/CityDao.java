package com.opentravelsoft.providers;

import java.util.List;

import com.opentravelsoft.entity.City;

public interface CityDao extends GenericDao<City, String> {

  /**
   * 取得出发城市列表
   * 
   * @return
   */
  public List<City> getLineOutCity();

  /**
   * 
   * @return
   */
  public List<City> getAllCity();

  /**
   * 
   * @param nationCode
   * @return
   */
  public List<City> getCitysByNation(String nationCode);

  /**
   * 
   * @param provinceId
   * @return
   */
  public List<City> getCitysByProvince(String provinceId);

}
