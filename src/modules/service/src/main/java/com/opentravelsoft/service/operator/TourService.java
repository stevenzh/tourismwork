package com.opentravelsoft.service.operator;

import java.util.Date;
import java.util.List;

import com.opentravelsoft.util.LabelValueBean;

import com.opentravelsoft.common.TeamType;
import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.City;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.entity.TourOutBound;
import com.opentravelsoft.entity.Tourist;

public interface TourService {
  /**
   * 取得团信息
   * 
   * @param tourNo 团号
   * @param actor 是否包含客人名单
   * @return
   */
  Plan roGetTourInfo(String tourNo, boolean actor, boolean cost);

  List<City> roGetPortCitys();

  List<LabelValueBean> roGetBirthplaceList();

  List<LabelValueBean> roGetPassportPlaceList();

  int txCancelTour(String tourNo, String note, long uid);

  /**
   * 
   * @param tour
   * @param note
   * @return
   */
  int txSaveTour(Plan tour, String note);

  List<TourOutBound> roGetOutBandobjectList(String tourNo, String type);

  List<Tourist> roGetLeaders(String tourNo);

  /**
   * 保存境外报团名单
   * 
   * @param outBandObject
   */
  void txSaveOutBandObject(TourOutBound outBandObject);

  public List<Tourist> roFindByNmno(String[] nmno);

  /**
   * 取得部门列表
   * 
   * @return
   */
  public List<Team> getOperatorTeamList();

  List<Team> getTeamList(long userId, TeamType type);

  /**
   * 查找团（团处理）
   * 
   * @param teamId
   * @param userId
   * @param lineName
   * @param startDate
   * @param endDate
   * @return
   */
  public List<Plan> roGetTours(long teamId, long userId, String lineName,
      Date startDate, Date endDate);

  /**
   * 取得多团信息（包含人名单）
   * 
   * @param tourNos
   * @return
   */
  public List<Plan> roGetToursAndCustomer(String[] tourNos);

  public List<City> toGetAllCity();

  /**
   * 取得该团号的所有订单
   * 
   * @param tourNo
   * @return
   */
  public List<Booking> roGetBookList(String tourNo);

  /**
   * 制作单团核算
   * 
   * @param plan
   * @return
   */
  public int txSingleTourBalanceMake(Plan plan);

  /**
   * 财务审核核算单
   * 
   * @param accountId
   * @param userId
   * @return
   */
  public Plan txSingleTourBalanceAuditing(String tourNo, long userId);

  /**
   * 修改订单应收款（危险）
   * 
   * @param bookList
   * @param uid
   * @return
   */
  public List<Booking> txMustPayModify(List<Booking> bookList, long uid);

  public int txAuthorizationModify(int accountId, long uid);

  public List<LabelValueBean> roGetCurrencyList();

}
