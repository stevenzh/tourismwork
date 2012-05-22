package com.opentravelsoft.service.operator;

import java.util.Date;
import java.util.List;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.common.TeamType;
import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.City;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.entity.TourOutBound;
import com.opentravelsoft.entity.Tourist;
import com.opentravelsoft.entity.finance.Income;

import com.opentravelsoft.providers.CityDao;
import com.opentravelsoft.providers.IncomeDao;
import com.opentravelsoft.providers.InvoiceDao;
import com.opentravelsoft.providers.ListDao;
import com.opentravelsoft.providers.TeamDao;
import com.opentravelsoft.providers.PlanDao;
import com.opentravelsoft.providers.TouristDao;

@Service("TourService")
public class TourServiceImpl implements TourService {

  @Autowired
  private PlanDao planDao;

  @Autowired
  private TouristDao touristDao;

  @Autowired
  private CityDao cityDao;

  @Autowired
  private ListDao listDao;

  @Autowired
  private TeamDao teamDao;

  @Autowired
  private IncomeDao incomeDao;

  @Autowired
  private InvoiceDao invoiceDao;

  public List<City> roGetPortCitys() {
    return cityDao.getLineOutCity();
  }

  public List<LabelValueBean> roGetCurrencyList() {
    return listDao.getList("Currency");
  }

  public List<LabelValueBean> roGetBirthplaceList() {
    return listDao.getList("Homeplace");
  }

  public List<LabelValueBean> roGetPassportPlaceList() {
    return listDao.getList("Homeplace");
  }

  public int txCancelTour(String tourNo, String note, int uid) {
    return planDao.cancelTour(tourNo, note, uid);
  }

  public int txSaveTour(Plan tour, String note) {
    return planDao.saveTour(tour, note);
  }

  public List<TourOutBound> roGetOutBandobjectList(String tourNo, String type) {
    return planDao.getOutBandObjectList(tourNo, type);
  }

  public List<Tourist> roGetLeaders(String tourNo) {
    return planDao.getLeaders(tourNo);
  }

  public void txSaveOutBandObject(TourOutBound outBandObject) {
    planDao.saveOutBandObject(outBandObject);
  }

  public Plan roGetTourInfo(String tourNo, boolean actor, boolean cost) {
    return planDao.getTourInfo(tourNo, actor, cost);
  }

  public List<Tourist> roFindByNmno(String[] nmno) {
    return touristDao.findByNmno(nmno);
  }

  public List<Team> getOperatorTeamList() {
    return teamDao.getTeamList(TeamType.Operator);
  }

  public List<Plan> roGetTours(Integer teamId, Integer userId, String lineName,
      Date kenStartDate, Date kenEndDate) {
    return planDao.getTours(teamId, userId, lineName, kenStartDate, kenEndDate);
  }

  public List<Plan> roGetToursAndCustomer(String[] tourNos) {
    return planDao.getToursAndCustomer(tourNos);
  }

  public List<City> toGetAllCity() {
    return cityDao.getAllCity();
  }

  public List<Team> getTeamList(int userId, TeamType type) {
    return teamDao.getTeam(userId, type);
  }

  public List<Booking> roGetBookList(String tourNo) {
    List<Booking> books = planDao.getBookList(tourNo);

    for (Booking book : books) {
      List<Income> payments = incomeDao.getPayments(book.getBookingNo());
      book.setInvices(invoiceDao.getInvoiceByBook(book.getBookingNo()));
      book.setPayments(payments);
    }
    return books;
  }

  public int txSingleTourBalanceMake(Plan singleTourBalance) {
    // TODO WorkFLow
    return planDao.makeTourAccounts(singleTourBalance);
  }

  public Plan txSingleTourBalanceAuditing(String tourNo, int uid) {
    // TODO WorkFLow
    return planDao.auditTourAccounts(tourNo, uid);
  }

  public List<Booking> txMustPayModify(List<Booking> bookList, int uid) {
    return planDao.mustPayModify(bookList, uid);
  }

  public int txAuthorizationModify(int accountId, int uid) {
    return planDao.authorizationModify(accountId, uid);
  }

}
