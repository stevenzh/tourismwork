package com.opentravelsoft.action.manage.product;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.common.SessionKeyParams;
import com.opentravelsoft.entity.Airways;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Line;
import com.opentravelsoft.entity.LinePrice;
import com.opentravelsoft.service.operator.TourService;
import com.opentravelsoft.service.product.LinePriceService;
import com.opentravelsoft.util.StringUtil;

/**
 * 修改线路价格
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:55 $
 */
public class EditPriceAction extends ManageAction {
  private static final long serialVersionUID = 7671898914387730451L;

  protected static final Log logger = LogFactory.getLog(EditPriceAction.class);

  private LinePriceService routePriceService;

  private TourService tourService;

  private LinePrice lineePrice = new LinePrice();

  private Line line;

  private String recNo;

  private boolean weekKey1 = false;

  private boolean weekKey2 = false;

  private boolean weekKey3 = false;

  private boolean weekKey4 = false;

  private boolean weekKey5 = false;

  private boolean weekKey6 = false;

  private boolean weekKey7 = false;

  // -------------------------------------------------------------------------

  private Date kenStartDate;

  private Date kenEndDate;

  // -------------------------------------------------------------------------

  private List<LinePrice> priceList;

  private List<Airways> airways;

  private List<LabelValueBean> priceTypeList;

  private List<LabelValueBean> currencyList;

  /** 附加价格个数 */
  private int unit = 0;

  private String note;

  public void setNote(String note) {
    this.note = note;
  }

  public String input() throws Exception {
    airways = routePriceService.roGetAllAirways();
    priceTypeList = routePriceService.getPriceType();

    if (StringUtil.hasLength(recNo)) {
      // 修改
      lineePrice = routePriceService.findLinePrice(recNo);
      // routeCostPrice = routePriceService.roGetRouteCostPrice(recNo);
    } else {
      // 取得系统时间
      buildSysdate();
      Calendar calDate = Calendar.getInstance();
      calDate.setTime(systemDate);
      calDate.add(Calendar.DATE, 30);

      lineePrice.setStartDate(systemDate);
      lineePrice.setEndDate(calDate.getTime());
    }

    String weekBit = lineePrice.getWeekBit();

    if ((weekBit.charAt(0) == 'Y'))
      weekKey1 = true;
    else
      weekKey1 = false;

    if ((weekBit.substring(1, 2)).equals("Y"))
      weekKey2 = true;
    else
      weekKey2 = false;

    if ((weekBit.substring(2, 3)).equals("Y"))
      weekKey3 = true;
    else
      weekKey3 = false;

    if ((weekBit.substring(3, 4)).equals("Y"))
      weekKey4 = true;
    else
      weekKey4 = false;

    if ((weekBit.substring(4, 5)).equals("Y"))
      weekKey5 = true;
    else
      weekKey5 = false;

    if ((weekBit.substring(5, 6)).equals("Y")) {
      weekKey6 = true;
    } else {
      weekKey6 = false;
    }
    if ((weekBit.substring(6, 7)).equals("Y")) {
      weekKey7 = true;
    } else {
      weekKey7 = false;
    }

    currencyList = tourService.roGetCurrencyList();

    return INPUT;
  }

  /**
   * 更新价格记录
   * 
   * @return
   */
  public String submit() {
    Employee user = getUser();
    StringBuilder weekBit = new StringBuilder();

    weekBit.append(weekKey1 == false ? "N" : "Y");
    weekBit.append(weekKey2 == false ? "N" : "Y");
    weekBit.append(weekKey3 == false ? "N" : "Y");
    weekBit.append(weekKey4 == false ? "N" : "Y");
    weekBit.append(weekKey5 == false ? "N" : "Y");
    weekBit.append(weekKey6 == false ? "N" : "Y");
    weekBit.append(weekKey7 == false ? "N" : "Y");

    if (weekBit.toString().equals("NNNNNNN"))
      weekBit = new StringBuilder("YYYYYYY");
    lineePrice.setWeekBit(weekBit.toString());
    line = (Line) ActionContext.getContext().getSession()
        .get(SessionKeyParams.EBIZ_CURRENT_ROUTE);
    lineePrice.setLineNo(line.getLineNo());
    lineePrice.setOpUser(user.getUserId());

    routePriceService.txEditPrice(lineePrice, note, user.getUserId());

    addActionMessage("更新价格成功.");
    return SUCCESS;
  }

  @Autowired
  public void setTourService(TourService tourService) {
    this.tourService = tourService;
  }

  @Autowired
  public void setRoutePriceService(LinePriceService routePriceService) {
    this.routePriceService = routePriceService;
  }

  public String getRecNo() {
    return recNo;
  }

  public void setRecNo(String recNo) {
    this.recNo = recNo;
  }

  public Line getRoute() {
    return line;
  }

  public void setRoute(Line route) {
    this.line = route;
  }

  public List<Airways> getAirways() {
    return airways;
  }

  public List<LabelValueBean> getPriceTypeList() {
    return priceTypeList;
  }

  public List<LinePrice> getPriceList() {
    return priceList;
  }

  public Date getKenEndDate() {
    return kenEndDate;
  }

  public void setKenEndDate(Date kenEndDate) {
    this.kenEndDate = kenEndDate;
  }

  public Date getKenStartDate() {
    return kenStartDate;
  }

  public void setKenStartDate(Date kenStartDate) {
    this.kenStartDate = kenStartDate;
  }

  public LinePrice getRoutePrice() {
    return lineePrice;
  }

  public void setRoutePrice(LinePrice routePrice) {
    this.lineePrice = routePrice;
  }

  public Boolean getWeekKey1() {
    return weekKey1;
  }

  public void setWeekKey1(Boolean weekKey1) {
    this.weekKey1 = weekKey1;
  }

  public Boolean getWeekKey2() {
    return weekKey2;
  }

  public void setWeekKey2(Boolean weekKey2) {
    this.weekKey2 = weekKey2;
  }

  public Boolean getWeekKey3() {
    return weekKey3;
  }

  public void setWeekKey3(Boolean weekKey3) {
    this.weekKey3 = weekKey3;
  }

  public Boolean getWeekKey4() {
    return weekKey4;
  }

  public void setWeekKey4(Boolean weekKey4) {
    this.weekKey4 = weekKey4;
  }

  public Boolean getWeekKey5() {
    return weekKey5;
  }

  public void setWeekKey5(Boolean weekKey5) {
    this.weekKey5 = weekKey5;
  }

  public Boolean getWeekKey6() {
    return weekKey6;
  }

  public void setWeekKey6(Boolean weekKey6) {
    this.weekKey6 = weekKey6;
  }

  public Boolean getWeekKey7() {
    return weekKey7;
  }

  public void setWeekKey7(Boolean weekKey7) {
    this.weekKey7 = weekKey7;
  }

  public List<LabelValueBean> getCurrencyList() {
    return currencyList;
  }

  public void setCurrencyList(List<LabelValueBean> currencyList) {
    this.currencyList = currencyList;
  }

  public int getUnit() {
    return unit;
  }

  public void setUnit(int unit) {
    this.unit = unit;
  }

}
