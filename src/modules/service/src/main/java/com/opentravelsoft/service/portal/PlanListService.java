package com.opentravelsoft.service.portal;

import java.util.Date;
import java.util.List;

import com.opentravelsoft.util.LabelValueBean;

import com.opentravelsoft.entity.Destination;
import com.opentravelsoft.entity.Plan;

public interface PlanListService {
  /**
   * 
   * @return
   */
  List<List<LabelValueBean>> getRegionGroupList();

  /**
   * 
   * @param region
   * @param deadline
   * @return
   */
  List<Plan> getPlanList(String region, boolean deadline);

  /**
   * 取得该分类的子分类
   * 
   * @param regionId
   * @return
   */
  List<List<LabelValueBean>> roGetSubRegions(String regionId);

  /**
   * 
   * @param lineName
   * @param groupId
   * @param userId
   * @param startDatePeriod
   * @param endDatePeriod
   * @param upperLimitPrice
   * @param lowerLimitPrice
   * @param openKey
   * @param outCity
   * @param destination
   * @return
   */
  List<Plan> roFind(String lineName, int groupId, int userId,
      Date startDatePeriod, Date endDatePeriod, double upperLimitPrice,
      double lowerLimitPrice, boolean openKey, String outCity,
      String destination);

  /**
   * 取得常用目的地
   * 
   * @return
   */
  List<Destination> getDestinations();

  /**
   * 
   * @param rowCount 行数
   * @param deadline 以截止日期为限
   * @param region 区域
   * @return
   */
  List<Plan> getPlans(long rowCount, boolean deadline, String region);

  /**
   * 
   * @param lineNo
   * @param openFlag
   * @param after
   * @param outDateSort
   * @param deadline
   * @return
   */
  List<Plan> getPlans(String lineNo, boolean openFlag, boolean after,
      boolean outDateSort, boolean deadline);
}
