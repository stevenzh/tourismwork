package com.opentravelsoft.service.operator;

import java.util.List;

import com.opentravelsoft.util.LabelValueBean;

import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.Tourist;

public interface TouristService {
  /**
   * 取得一个游客信息
   * 
   * @param nmno 游客ID
   * @return
   */
  public Tourist roFindCustomerByNmno(String nmno);

  public List<LabelValueBean> roGetPassportPlaceList();

  /**
   * 根据团号取得计划信息
   * 
   * @param tourNo
   * @return
   */
  public Plan roGetPlanDetail(String tourNo);

  /**
   * 
   * @param customerList
   * @param tourNo
   * @return
   */
  public int txModifyCustomerInfo(List<Tourist> customerList, String tourNo,
      String note, long uid);
}
