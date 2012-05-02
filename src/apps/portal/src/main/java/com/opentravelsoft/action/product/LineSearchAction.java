package com.opentravelsoft.action.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.entity.Destination;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.service.portal.PlanListService;
import com.opentravelsoft.webapp.action.PortalAction;

/**
 * 出团计划查询
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 */
public class LineSearchAction extends PortalAction {

  private static final long serialVersionUID = -7625855842902512964L;

  @Autowired
  private PlanListService planService;

  private Date kenStartDatePeriod;

  private Date kenEndDatePeriod;

  private double kenUpperLimitPrice;

  private double kenLowerLimitPrice;

  private String kenOutCity;

  private String kenDestination;

  /** 目的地列表 */
  private List<Destination> destinations = new ArrayList<Destination>();

  private List<Plan> plans = new ArrayList<Plan>();

  public String input() throws Exception {
    buildSysdate();
    kenStartDatePeriod = systemDate;
    kenEndDatePeriod = systemDate;

    // destinations = planService.getDestinations(); 改为静态加载

    return INPUT;
  }

  public String submit() throws Exception {
    plans = planService.roFind("", 0, 0, kenStartDatePeriod, kenEndDatePeriod,
        kenUpperLimitPrice, kenLowerLimitPrice, true, kenOutCity,
        kenDestination);
    currentPage(plans.size());
    buildSysdate();

    return SUCCESS;
  }

  public List<Plan> getPlans() {
    return plans;
  }

  public Date getKenEndDatePeriod() {
    return kenEndDatePeriod;
  }

  public void setKenEndDatePeriod(Date kenEndDatePeriod) {
    this.kenEndDatePeriod = kenEndDatePeriod;
  }

  public Date getKenStartDatePeriod() {
    return kenStartDatePeriod;
  }

  public void setKenStartDatePeriod(Date kenStartDatePeriod) {
    this.kenStartDatePeriod = kenStartDatePeriod;
  }

  public double getKenLowerLimitPrice() {
    return kenLowerLimitPrice;
  }

  public void setKenLowerLimitPrice(double kenLowerLimitPrice) {
    this.kenLowerLimitPrice = kenLowerLimitPrice;
  }

  public double getKenUpperLimitPrice() {
    return kenUpperLimitPrice;
  }

  public void setKenUpperLimitPrice(double kenUpperLimitPrice) {
    this.kenUpperLimitPrice = kenUpperLimitPrice;
  }

  public String getKenOutCity() {
    return kenOutCity;
  }

  public void setKenOutCity(String kenOutCity) {
    this.kenOutCity = kenOutCity;
  }

  public String getKenDestination() {
    return kenDestination;
  }

  public void setKenDestination(String kenDestination) {
    this.kenDestination = kenDestination;
  }

  public List<Destination> getDestinations() {
    return destinations;
  }

}
