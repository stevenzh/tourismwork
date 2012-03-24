package com.opentravelsoft.service.finance;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.common.TeamType;
import com.opentravelsoft.entity.Customer;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.entity.TourCost;
import com.opentravelsoft.entity.finance.Outcome;
import com.opentravelsoft.providers.OutcomeDao;
import com.opentravelsoft.providers.TeamDao;
import com.opentravelsoft.providers.hibernate.CustomerDao;

@Service("OutcomeService")
public class OutcomeServiceImpl implements OutcomeService {
  private OutcomeDao outcomeDao;

  private TeamDao teamDao;

  private CustomerDao customerDao;

  @Autowired
  public void setCustomerDao(CustomerDao customerDao) {
    this.customerDao = customerDao;
  }

  @Autowired
  public void setTeamDao(TeamDao teamDao) {
    this.teamDao = teamDao;
  }

  @Autowired
  public void setOutcomeDao(OutcomeDao outcomeDao) {
    this.outcomeDao = outcomeDao;
  }

  public List<TourCost> roGetSupplierOutcomeList(String supplierType,
      String supplierName, String kenCountryId, String kenCityId,
      Date startDate, Date endDate) {
    return outcomeDao.getSupplierOutcomeList(supplierType, supplierName,
        kenCountryId, kenCityId, startDate, endDate);
  }

  public List<Team> getOperatorTeamList() {
    return teamDao.getTeamList(TeamType.Operator);
  }

  public int txFactualRegister(int[] outcomeIds, long uid, String billNo,
      Date payDate) {
    return outcomeDao.factualRegister(outcomeIds, uid, billNo, payDate);
  }

  public List<Outcome> roGetBillheadList(long userId, Date startDate,
      Date endDate, Date startOutDate, Date endOutDate, boolean opAudited,
      String kenAudit, String kenPay, Date frStartDate, Date frEndDate) {
    return outcomeDao.getBillList(userId, startDate, endDate, startOutDate,
        endOutDate, opAudited, kenAudit, kenPay, frStartDate, frEndDate);
  }

  public List<TourCost> roGetOwedList(long supplierId, String tourNo) {
    return outcomeDao.getOwedList(supplierId, tourNo);
  }

  public long txSaveBillhead(Outcome billhead, String audit) {
    // TODO WorkFLow
    return outcomeDao.saveBill(billhead, audit);
  }

  public int txDeleteBillhead(long billheadId) {
    return outcomeDao.cancelBill(billheadId);
  }

  public Outcome roGetBillhead(long outcomeId) {
    return outcomeDao.get(outcomeId);
  }

  public Customer roGetSupplier(long supplierId) {
    return customerDao.findAccount(supplierId);
  }

  public List<Outcome> roGetOutcomeList(long userId, Date startDate,
      Date endDate, String register) {
    return outcomeDao.getBillList(userId, startDate, endDate, register);
  }

  public List<Customer> roGetSupplierByType(String resource, long teamId) {
    return customerDao.getSupplierByType(resource, teamId);
  }

  public int txAuditingBillhead(long outcomeId, long uid) {
    // TODO WorkFLow
    return outcomeDao.auditingBill(outcomeId, uid);
  }

  public int txOpApproved(long outcomeId, long uid) {
    // TODO WorkFLow
    return outcomeDao.opApproved(outcomeId, uid);
  }

  public int txFrReadBillhead(Outcome outcome, long uid) {
    return outcomeDao.frReadBill(outcome, uid);
  }

  public int txFrModifyBillhead(Outcome outcome, long uid) {
    return outcomeDao.frModifyBill(outcome, uid);
  }

  public int txPoModifyBillhead(Outcome outcome, long uid) {
    return outcomeDao.opModifyBill(outcome, uid);
  }

  public int roIsAuditing(String tourNo, double amount) {
    return outcomeDao.isAuciting(tourNo, amount);
  }

  public List<LabelValueBean> roGetTourList(long teamId, long uid,
      long supplierId) {
    List<LabelValueBean> arr = new ArrayList<LabelValueBean>();
    List<Plan> tours = outcomeDao.getTourList(teamId, uid, supplierId);
    for (Plan tour : tours) {
      arr.add(new LabelValueBean(tour.getTourNo(), tour.getTourNo()
          + tour.getLine().getLineNo()));
    }
    return arr;
  }

  public int txOpModifyPayReturn(long outcomeId) {
    // TODO WorkFLow
    return outcomeDao.opModifyPayReturn(outcomeId);
  }

  public List<Team> roGetTeams(long userId, TeamType type) {
    return teamDao.getTeam(userId, type);
  }

}
