package com.opentravelsoft.action.manage.operator;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.common.SessionKeyParams;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.service.operator.TourService;

/**
 * 打印客人名单（同行）
 * 
 * @author zhangst
 */
public class THReportAction extends ManageAction {

  private static final long serialVersionUID = 4221358089777062127L;

  @Autowired
  private TourService tourService;

  private Plan tour = new Plan();

  private String tourNo;

  public String input() {
    Object obj = ActionContext.getContext().getSession()
        .get(SessionKeyParams.EBIZ_CURRENT_TOUR);
    if (obj != null) {
      tourNo = ((Plan) obj).getTourNo();
    }
    tour = tourService.roGetTourInfo(tourNo, true, false);
    return INPUT;
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

}
