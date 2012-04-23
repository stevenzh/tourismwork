package com.opentravelsoft.action.manage.operator;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.common.SessionKeyParams;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.service.operator.TourService;

/**
 * 团处理 - 简单打印
 * 
 * @author zhangst
 * 
 */
public class SimpleReportAction extends ManageAction {

  private static final long serialVersionUID = 4221358089777062127L;

  @Autowired
  private TourService tourService;

  private Plan tour = new Plan();

  private String tourNo;

  private String sp;

  public String input() {
    Object obj = ActionContext.getContext().getSession()
        .get(SessionKeyParams.EBIZ_CURRENT_TOUR);
    if (obj != null) {
      tourNo = ((Plan) obj).getTourNo();
    }

    // 取得团信息，客人名单
    tour = tourService.roGetTourInfo(tourNo, true, false);
    return INPUT;
  }

  public SimpleReportAction() {
    sp = "请订  月  日机票，谢谢！\n" + "行程：\n" + "  月    日      飞\n"
        + "  月    日      飞\n" + "  月    日      飞";
  }

  public Plan getTour() {
    return tour;
  }

  public void setTour(Plan tour) {
    this.tour = tour;
  }

  public String getTourNo() {
    return tourNo;
  }

  public void setTourNo(String tourNo) {
    this.tourNo = tourNo;
  }

  public void setTourService(TourService tourService) {
    this.tourService = tourService;
  }

  public String getSp() {
    return sp;
  }

  public void setSp(String sp) {
    this.sp = sp;
  }

}
