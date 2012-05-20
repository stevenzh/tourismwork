package com.opentravelsoft.service.finance;

import java.util.Date;
import java.util.List;

import com.opentravelsoft.util.LabelValueBean;

import com.opentravelsoft.common.TeamType;
import com.opentravelsoft.entity.Customer;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.entity.TourCost;
import com.opentravelsoft.entity.finance.Outcome;

public interface OutcomeService {

  /**
   * 取得未付款
   * 
   * @param supplierType
   * @param supplierName
   * @param kenCountryId
   * @param kenCityId
   * @param startDate
   * @param endDate
   * @return
   */
  public List<TourCost> roGetSupplierOutcomeList(String supplierType,
      String supplierName, String kenCountryId, String kenCityId,
      Date startDate, Date endDate);

  public Outcome roGetBillhead(Integer outcomeId);

  public int txOpApproved(Integer outcomeId, Integer userId);

  public Customer roGetSupplier(int supplierId);

  public int txPoModifyBillhead(Outcome outcome, Integer uid);

  public int txDeleteBillhead(long outcomeId);

  public List<Outcome> roGetBillheadList(Integer userId, Date kenStartDate,
      Date kenEndDate, Date kenStartOutDate, Date kenEndOutDate,
      boolean opAudited, String kenAudit, String kenPay, Date frStartDate,
      Date frEndDate);

  public List<TourCost> roGetOwedList(Integer supplierId, String tourNo);

  public long txSaveBillhead(Outcome outcome, String audit);

  public List<Customer> roGetSupplierByType(String resource, Integer teamId);

  public List<LabelValueBean> roGetTourList(Integer teamId, Integer uid,
      Integer supplierId);

  // -------------------------------------------------------------------------
  // 财务
  public int txAuditingBillhead(Integer outcomeId, Integer uid);

  public int txFrReadBillhead(Outcome outcome, Integer uid);

  public int txFrModifyBillhead(Outcome outcome, Integer uid);

  public int roIsAuditing(String tourNo, double amount);

  // 实付登记
  public List<Outcome> roGetOutcomeList(Integer userId, Date startDate,
      Date endDate, String register);

  public int txFactualRegister(int[] outcomeIds, Integer uid, String billNo,
      Date payDate);

  /**
   * 付款申请书置位(打回计调操作)
   * 
   * @param outcomeId
   * @return
   */
  public int txOpModifyPayReturn(int outcomeId);

  public List<Team> getOperatorTeamList();

  public List<Team> roGetTeams(long userId, TeamType type);
}
