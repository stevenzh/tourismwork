package com.opentravelsoft.action.manage.operate;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.common.SessionKeyParams;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.service.operator.TourService;

/**
 * 团处理 - 打印客人名单
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:00 $
 */
public class CustomerListReportAction extends ManageAction {

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
