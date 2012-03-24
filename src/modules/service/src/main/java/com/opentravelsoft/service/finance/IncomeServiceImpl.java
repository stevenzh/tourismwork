package com.opentravelsoft.service.finance;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.common.TeamType;
import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.Customer;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.entity.finance.Income;
import com.opentravelsoft.entity.product.Warrant;
import com.opentravelsoft.providers.BookingDao;
import com.opentravelsoft.providers.IncomeDao;
import com.opentravelsoft.providers.InvoiceDao;
import com.opentravelsoft.providers.TeamDao;

@Service("IncomeService")
public class IncomeServiceImpl implements IncomeService {
  private IncomeDao incomeDao;

  private InvoiceDao invoiceDao;

  private TeamDao teamDao;

  @Autowired
  public void setTeamDao(TeamDao teamDao) {
    this.teamDao = teamDao;
  }

  @Autowired
  public void setIncomeDao(IncomeDao incomeDao) {
    this.incomeDao = incomeDao;
  }

  @Autowired
  public void setInvoiceDao(InvoiceDao invoiceDao) {
    this.invoiceDao = invoiceDao;
  }

  public List<Booking> roGetIncomeBookings(long customerId) {
    return incomeDao.getIncomeBookings(customerId);
  }

  public List<Booking> roSearchIncome(String proCd, String cityCd,
      long customerId, Date stDate, Date endDate) {
    return incomeDao.searchIncome(proCd, cityCd, customerId, stDate, endDate);
  }

  public long txSaveIncome(Income gathering) {
    // TODO WorkFLow
    return incomeDao.saveIncome(gathering);
  }

  public List<Income> roShowIncomeHis(long teamId, String customerId,
      Date startDate, Date endDate, double startMon, double endMon) {
    return incomeDao.findIncome(teamId, customerId, startDate, endDate,
        startMon, endMon);
  }

  public Income roGetIncome(long incomeId) {
    Income gat = incomeDao.get(incomeId);
    gat.setInvices(invoiceDao.getInvoice(incomeId));
    return gat;
  }

  public int txDeleteIncome(long incomeId) {
    return incomeDao.cancelIncome(incomeId);
  }

  public int txUpdateIncome(Income gather) {
    return incomeDao.updateIncome(gather);
  }

  public List<Booking> roGetUnpayList(int incomeId) {
    return incomeDao.getUnpayList(incomeId);
  }

  public List<Customer> roGetCusByTour(String TourNo) {
    return incomeDao.getCusByTour(TourNo);
  }

  public int txWarrantSubmit(int customerId, String tourNo, Warrant warrant) {
    return incomeDao.warrantSubmit(customerId, tourNo, warrant);
  }

  public List<Team> getOperatorTeamList() {
    return teamDao.getTeamList(TeamType.Operator);
  }

}
