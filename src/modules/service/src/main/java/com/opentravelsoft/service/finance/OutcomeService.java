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

  public Outcome roGetBillhead(long outcomeId);

  public int txOpApproved(long outcomeId, long userId);

  public Customer roGetSupplier(long supplierId);

  public int txPoModifyBillhead(Outcome outcome, long uid);

  public int txDeleteBillhead(long outcomeId);

  public List<Outcome> roGetBillheadList(long userId, Date kenStartDate,
      Date kenEndDate, Date kenStartOutDate, Date kenEndOutDate,
      boolean opAudited, String kenAudit, String kenPay, Date frStartDate,
      Date frEndDate);

  public List<TourCost> roGetOwedList(long supplierId, String tourNo);

  public long txSaveBillhead(Outcome outcome, String audit);

  public List<Customer> roGetSupplierByType(String resource, long teamId);

  public List<LabelValueBean> roGetTourList(long teamId, long uid,
      long supplierId);

  // -------------------------------------------------------------------------
  // 财务
  public int txAuditingBillhead(long outcomeId, long uid);

  public int txFrReadBillhead(Outcome outcome, long uid);

  public int txFrModifyBillhead(Outcome outcome, long uid);

  public int roIsAuditing(String tourNo, double amount);

  // 实付登记
  public List<Outcome> roGetOutcomeList(long userId, Date startDate,
      Date endDate, String register);

  public int txFactualRegister(int[] outcomeIds, long uid, String billNo,
      Date payDate);

  /**
   * 付款申请书置位(打回计调操作)
   * 
   * @param outcomeId
   * @return
   */
  public int txOpModifyPayReturn(long outcomeId);

  public List<Team> getOperatorTeamList();

  public List<Team> roGetTeams(long userId, TeamType type);
}
