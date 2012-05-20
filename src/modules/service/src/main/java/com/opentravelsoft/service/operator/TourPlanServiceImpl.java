package com.opentravelsoft.service.operator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.common.TeamType;
import com.opentravelsoft.entity.Airways;
import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.Line;
import com.opentravelsoft.entity.LinePrice;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.PlanPrice;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.entity.TraitType;
import com.opentravelsoft.providers.AirwaysDao;
import com.opentravelsoft.providers.BookingDao;
import com.opentravelsoft.providers.SequenceDao;
import com.opentravelsoft.providers.TeamDao;
import com.opentravelsoft.providers.mixed.PlanListDao;
import com.opentravelsoft.providers.product.LineDao;
import com.opentravelsoft.providers.product.LinePriceDao;
import com.opentravelsoft.providers.product.TraitTypeDao;
import com.opentravelsoft.util.StringUtil;

@Service("TourPlanService")
public class TourPlanServiceImpl implements TourPlanService {

  @Autowired
  private PlanListDao planListDao;

  @Autowired
  private BookingDao bookingDao;

  @Autowired
  private TeamDao teamDao;

  @Autowired
  private SequenceDao sequenceDao;

  @Autowired
  private LinePriceDao routePriceDao;

  @Autowired
  private LineDao routeDao;

  @Autowired
  private TraitTypeDao traitTypeDao;

  @Autowired
  private AirwaysDao airwaysDao;

  public List<Plan> roFind(String lineName, long teamId, long userId,
      Date startDate, Date endDate, double lowerPrice, double upperPrice,
      boolean openKey) {
    return planListDao.find(lineName, teamId, userId, startDate, endDate,
        lowerPrice, upperPrice, openKey, "", "");
  }

  public List<Team> getOperatorTeamList() {
    return teamDao.getTeamList(TeamType.Operator);
  }

  public int txDeletePlan(Plan plan, long userId, String note) {
    return planListDao.deletePlan(plan, userId, note);
  }

  public Plan roGetPlanDetail(String recordNo) {
    return planListDao.getPlanDetail(recordNo);
  }

  public int txUpdatePlan(Plan plan, List<PlanPrice> planPrices, int shareId,
      String note) {
    BigDecimal price = new BigDecimal(0);
    boolean modi = false;

    List<LinePrice> prices = routePriceDao.getLinePrice(plan.getLine()
        .getLineNo(), plan.getOutDate(), plan.getOutDate());
    for (LinePrice routePrice : prices) {
      Calendar cal = Calendar.getInstance();
      cal.setTime(plan.getOutDate());
      int week = cal.get(Calendar.DAY_OF_WEEK);
      // weekBit 一 二 ... 日
      // 1 2 3 4 5 6 7
      // calenda 2 3 4 5 6 7 1
      int pos = (week + 6) % 7;
      if (pos == 0)
        pos = 7;

      if (routePrice.isDefaultPrice()
          && routePrice.getStartDate().compareTo(plan.getOutDate()) <= 0
          && routePrice.getEndDate().compareTo(plan.getOutDate()) >= 0
          && routePrice.getWeekBit().charAt(pos - 1) == 'Y') {
        price = routePrice.getPrice();
      }

      if (routePrice.isDefaultPrice() && price.doubleValue() == 0
          && routePrice.getWeekBit().equals("NNNNNNN"))
        price = routePrice.getPrice();
    }

    // 报价为0的 不可以发布到网站
    if (price.doubleValue() == 0 && plan.getDeployFlag().equals("Y")) {
      plan.setDeployFlag("N");
      modi = true;
    }

    planListDao.savePlanPrices(plan.getPlanNo(), planPrices);
    int ret = planListDao.updatePlan(plan, shareId, note);

    if (ret == 0 && modi)
      ret = 1;

    return ret;
  }

  public Plan findLastPlan(String lineNo) {
    return planListDao.findLastPlan(lineNo);
  }

  public List<Plan> roGetLinePlans(String lineNo, boolean openFlag,
      boolean after, boolean outDateSort, boolean deadline) {
    return planListDao.getLinePlans(lineNo, openFlag, after, outDateSort,
        deadline);
  }

  public int txInsertPlan(List<Plan> plans, Date startDate, Date endDate,
      List<PlanPrice> planPrices, int shareId) {
    Plan tPlan = plans.get(0);
    List<LinePrice> prices = routePriceDao.getLinePrice(tPlan.getLine()
        .getLineNo(), startDate, endDate);
    BigDecimal price = new BigDecimal(0);
    boolean modi = false;

    String[] no = sequenceDao.getComputerNo("Q", plans.size(),
        tPlan.getOpUser());

    for (int i = 0; i < plans.size(); i++) {
      // 计划对应的报价
      String priceNo = null;
      Plan plan = plans.get(i);
      for (LinePrice linePrice : prices) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(plan.getOutDate());
        int week = cal.get(Calendar.DAY_OF_WEEK);
        // weekBit 一 二 ... 日
        // 1 2 3 4 5 6 7
        // calenda 2 3 4 5 6 7 1
        int pos = (week + 6) % 7;
        if (pos == 0)
          pos = 7;

        if (linePrice.getStartDate().compareTo(plan.getOutDate()) <= 0
            && linePrice.getEndDate().compareTo(plan.getOutDate()) >= 0
            && linePrice.getWeekBit().charAt(pos - 1) == 'Y') {
          price = linePrice.getPrice();
          priceNo = linePrice.getRecNo();
        }

        if (price.doubleValue() == 0
            && linePrice.getWeekBit().equals("NNNNNNN")) {
          price = linePrice.getPrice();
          priceNo = linePrice.getRecNo();
        }

        if (StringUtil.hasLength(priceNo))
          continue;
      }

      plan.getPackagePrice().setRecNo(priceNo);
      plan.setPlanNo(no[i]);

      // 报价为0的 不可以发布到网站
      if (price.doubleValue() == 0 && plan.getDeployFlag().equals("Y")) {
        plan.setDeployFlag("N");
        modi = true;
      }

      for (PlanPrice flight : planPrices) {
        flight.setPlanNo(no[i]);
      }
      planListDao.savePlanPrices(no[i], planPrices);
    }

    // TODO WorkFLow
    int ret = planListDao.insertPlan(plans, shareId);

    if (ret == 0 && modi)
      ret = 1;

    return ret;
  }

  public List<Booking> roGetPlanBookings(String recordNo) {
    return bookingDao.getPlanBookings(recordNo);
  }

  public List<PlanPrice> roGetPrices(String planNo) {
    return planListDao.getPlanPrices(planNo);
  }

  public Line roGetLine(String lineNo) {
    return routeDao.get(lineNo);
  }

  public List<LabelValueBean> roSearchShare() {
    return planListDao.searchShare();
  }

  public int txReCountPlanPax(String recordNo) {
    return planListDao.reCountPlanPax(recordNo);
  }

  public int roIsTourNoRepeat(String tourNo) {
    return planListDao.isTourNoRepeat(tourNo);
  }

  public List<LabelValueBean> roGetTraitList() {
    List<TraitType> list = traitTypeDao.getTypeList();
    List<LabelValueBean> ret = new ArrayList<LabelValueBean>();

    for (TraitType type : list) {
      ret.add(new LabelValueBean(String.valueOf(type.getTraitId()), type
          .getName()));
    }
    return ret;
  }

  public List<Team> getTeamList(long userId, TeamType type) {
    return teamDao.getTeam(userId, type);
  }

  public List<Airways> roGetAirways() {
    return airwaysDao.getAll(false);
  }
}
