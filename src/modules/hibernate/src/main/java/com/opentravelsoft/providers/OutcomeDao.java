package com.opentravelsoft.providers;

import java.util.Date;
import java.util.List;

import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.TourCost;
import com.opentravelsoft.entity.finance.Outcome;
import com.opentravelsoft.entity.product.Remind;

public interface OutcomeDao extends GenericDao<Outcome, Integer> {

  /**
   * 应付账款查询
   * 
   * @param customerId
   * @param tourNo
   * @return
   */
  public List<TourCost> getOwedList(Integer customerId, String tourNo);

  /**
   * 查找某一时间内对应供应商的应付帐款
   * 
   * @param supplierType 提供产品类型
   * @param supplierName 供应商名称
   * @param kenCountryId 供应商所属国家
   * @param kenCityId 供应上所属城市
   * @param startDate 开始日期
   * @param endDate 截止日期
   * @return
   */
  public List<TourCost> getSupplierOutcomeList(String supplierType,
      String supplierName, String kenCountryId, String kenCityId,
      Date startDate, Date endDate);

  /**
   * 应付帐款查询
   * 
   * @param customerId 供应商ID
   * @param startDate 开始日期
   * @param endDate 截至日期
   * @return
   * @deprecated
   */
  public List<TourCost> findBill(Integer customerId, Date startDate,
      Date endDate);

  /**
   * 保存付款单
   * 
   * @param billhead
   * @param audit
   * @return
   */
  public int saveBill(Outcome billhead, String audit);

  /**
   * 付款单取消
   * 
   * @param billheadId
   * @return
   */
  public int cancelBill(int billheadId);

  /**
   * 查询付款申请书（OP修改,OP审核,FR修改,FR审核）
   * 
   * @param userId 申请人
   * @param startDate
   * @param endDate
   * @param startOutDate
   * @param endOutDate
   * @param opAudited
   * @param kenAudit
   * @param kenPay
   * @param frStartDate
   * @param frEndDate
   * @return
   */
  public List<Outcome> getBillList(Integer userId, Date startDate,
      Date endDate, Date startOutDate, Date endOutDate, boolean opAudited,
      String kenAudit, String kenPay, Date frStartDate, Date frEndDate);

  /**
   * 实付账款查询
   * 
   * @param userId
   * @param startDate
   * @param endDate
   * @param register
   * @return
   */
  public List<Outcome> getBillList(Integer userId, Date startDate,
      Date endDate, String register);

  /**
   * 实付登记
   * 
   * @param outcomeIds
   * @param uid
   * @param billNo
   * @param payDate
   * @return
   */
  public int factualRegister(int[] outcomeIds, Integer uid, String billNo,
      Date payDate);

  /**
   * 账务审核付款申请书
   * 
   * @param outcomeId
   * @param uid
   * @return
   */
  public int auditingBill(Integer outcomeId, Integer uid);

  /**
   * 审核付款申请书--计调
   * 
   * @param outcomeId
   * @param uid
   * @return
   */
  public int opApproved(Integer outcomeId, Integer uid);

  /**
   * 财务读取申请书
   * 
   * @param outcome
   * @param uid
   * @return
   */
  public int frReadBill(Outcome outcome, Integer uid);

  /**
   * 财务修改付款申请书
   * 
   * @param outcome
   * @param uid
   * @return
   */
  public int frModifyBill(Outcome outcome, Integer uid);

  /**
   * 修改付款申请书--计调
   * 
   * @param outcome
   * @param uid
   * @return
   */
  public int opModifyBill(Outcome outcome, Integer uid);

  /**
   * 判断是否能审核付款申请书
   * 
   * @param tourNo
   * @param amount
   * @return 返回0能，返回-1不能
   */
  public int isAuciting(String tourNo, double amount);

  /**
   * 
   * @param teamId
   * @param uid
   * @param supplierId
   * @return
   */
  public List<Plan> getTourList(Integer teamId, Integer uid, Integer supplierId);

  // -------------------------------------------------------------------------
  /**
   * 财务预警<br>
   * 国内应付 (未读付款申请, 团出发前n天未付款申请)
   * 
   * @param daynum
   * 
   * @return
   */
  public List<Remind> getOutcomeInBand(int daynum);

  public List<Remind> getBillheadInBand();

  // --------------------------------------------------------------------------
  /**
   * 付款申请书置位(打回计调操作)
   * 
   * @param outcomeId
   * @return
   */
  public int opModifyPayReturn(int outcomeId);

  /**
   * 付款配送
   * 
   * @param supplierId
   * @param userId
   * @param carryStatus
   * @return
   */
  List<Outcome> listParcels(String supplierId, Integer userId, short carryStatus);

  /**
   * 机票配送完成
   * 
   * @param billhead
   * @return
   */
  int startParcel(Outcome billhead);

  /**
   * 开始机票配送
   * 
   * @param billhead
   * @return
   */
  int completeParcel(Outcome billhead);

}
