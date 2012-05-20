package com.opentravelsoft.providers;

import java.util.Date;
import java.util.List;

import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.TourOutBound;
import com.opentravelsoft.entity.Tourist;
import com.opentravelsoft.entity.product.Leader;

public interface PlanDao extends GenericDao<Plan, String> {

  /**
   * 查找团（团处理、出境游名单打印）
   * 
   * @param teamId 线路所属部门
   * @param userId 线路专管员
   * @param lineName 线路名称
   * @param startDate 出发时间
   * @param endDate 出发时间
   * @return
   * 
   */
  public List<Plan> getTours(Integer teamId, Integer userId, String lineName,
      Date startDate, Date endDate);

  /**
   * 取得团基本信息
   * 
   * @param tourNo 团号
   * @param actor 是否包含客人名单
   * @return
   */
  public Plan getTourInfo(String tourNo, boolean actor, boolean cost);

  // -------------------------------------------------------------------------

  /**
   * 取得多团信息（包含人名单）<BR>
   * 出境游名单打印使用
   * 
   * @param tourNos 团号列表
   * @return
   */
  public List<Plan> getToursAndCustomer(String[] tourNos);

  /**
   * 取消团
   * 
   * @param tourNo
   * @return
   */
  public int cancelTour(String tourNo, String note, int userId);

  /**
   * 如团信息中所登记的人数与实际客人不符，更新团信息
   * 
   * @param tour
   * @param note
   * @return
   */
  public int saveTour(Plan tour, String note);

  /**
   * 取境外报团打印对象
   * 
   * @param tourNo 团号
   * @param type
   * @return
   */
  public List<TourOutBound> getOutBandObjectList(String tourNo, String type);

  /**
   * 保存境外报团名单
   * 
   * @param outBandObject
   */
  public void saveOutBandObject(TourOutBound outBandObject);

  /**
   * 取团所对的领队
   * 
   * @param tourNo
   * @return
   */
  public List<Tourist> getLeaders(String tourNo);

  /**
   * 修改团中客人信息及团人数信息
   * 
   * @param customerList
   * @param tourNo
   * @return
   */
  public int modifyCustomerInfo(List<Tourist> customerList, String tourNo,
      String note, int userId);

  // -------------------------------------------------------------------------

  public List<Booking> getBookList(String tourNo);

  /**
   * 制作单团核算
   * 
   * @param tour
   * @return
   */
  public int makeTourAccounts(Plan tour);

  /**
   * 财务审核核算单
   * 
   * @param tourNo
   * @param uid
   * @return
   */
  public Plan auditTourAccounts(String tourNo, int uid);

  /**
   * 修改订单应收帐款
   * 
   * @param bookList
   * @param uid
   * @return
   */
  public List<Booking> mustPayModify(List<Booking> bookList, int uid);

  /**
   * 计调取团核算表及其成本明细
   * 
   * @param tourNo
   * @return
   */
  public Plan opGetBalanceAndCost(String tourNo);

  /**
   * 授权修改单团核算
   * 
   * @param accountId
   * @param uid
   * @return
   */
  public int authorizationModify(long accountId, int uid);

  // -----------------------------------------------------------------------
  /**
   * 客人转为领队
   * 
   * @param tourNo 团号
   * @param nameKey 客人数组
   * @return
   */
  public int arrangeLeader(String tourNo, String[] nameKey, int operator);

  public List<Leader> getLeaderList();

  /**
   * 选择领队
   * 
   * @param tourNo
   * @param nameKey
   * @param sqe
   * @param operator
   * @return
   */
  public int arrangeFromLeader(String tourNo, String[] nameKey, String[] sqe,
      Integer operator);

  public int cancelLeader(String tourNo, String[] nameKey, int uid);
}
