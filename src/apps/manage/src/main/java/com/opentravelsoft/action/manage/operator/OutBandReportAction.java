package com.opentravelsoft.action.manage.operator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.common.SessionKeyParams;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.TourOutBound;
import com.opentravelsoft.entity.Tourist;
import com.opentravelsoft.service.operator.TourService;

/**
 * 境外报团名单打印
 * 
 * @author zhangst
 */
public class OutBandReportAction extends ManageAction {

  private static final long serialVersionUID = 4221358089777062127L;

  @Autowired
  private TourService tourService;

  private Plan tour = new Plan();

  private List<TourOutBound> outBandObjectList = new ArrayList<TourOutBound>();

  private String outBandObject;

  private String objectType;

  private String tourNo;

  private String label_1;

  private String label_2;

  private String label_3;

  private String userName;

  public String input() {
    Employee user = getUser();
    userName = user.getUserName();
    Object obj = ActionContext.getContext().getSession()
        .get(SessionKeyParams.EBIZ_CURRENT_TOUR);
    if (obj != null) {
      tourNo = ((Plan) obj).getTourNo();
    }

    tour = tourService.roGetTourInfo(tourNo, true, false);
    List<Tourist> leaderList = tourService.roGetLeaders(tourNo);
    label_1 = "TO:-------" + "\n" + "FM:  " + user.getUserName();

    label_2 = tour.getTourNo() + "　　" + tour.getLine().getLineName() + "　　"
        + "领队:";
    for (Tourist leader : leaderList) {
      label_2 = label_2 + leader.getUserName() + "　";
    }

    label_3 = "   航班：  月/日    航班号      时间      出发城市--抵达城市" + "\n";
    label_3 = label_3 + "        月/日    航班号      时间      出发城市--抵达城市" + "\n";
    label_3 = label_3 + "        月/日    航班号      时间      出发城市--抵达城市" + "\n";
    label_3 = label_3 + "备注：1、此团分   间房。" + "\n";
    label_3 = label_3 + "   2、此团请回复!导游附上。" + "\n";
    label_3 = label_3 + "   3、我司联系人   " + user.getUserName()
        + "     T:(8621)                              手提:" + "\n";
    label_3 = label_3 + "   4、此团     地接社为：" + "\n";

    outBandObjectList = tourService.roGetOutBandobjectList(tourNo, null);
    return INPUT;
  }

  public String getObject() {
    Object obj = ActionContext.getContext().getSession()
        .get(SessionKeyParams.EBIZ_CURRENT_TOUR);
    if (obj != null) {
      tourNo = ((Plan) obj).getTourNo();
    }
    outBandObjectList = tourService.roGetOutBandobjectList(tourNo,
        outBandObject);
    if (!outBandObjectList.isEmpty()) {
      TourOutBound outBand = outBandObjectList.get(0);
      objectType = outBand.getType();
      label_1 = outBand.getText1();
      label_2 = outBand.getText2();
      label_3 = outBand.getText3();
    }

    outBandObjectList = tourService.roGetOutBandobjectList(tourNo, null);
    return SUCCESS;
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

  public List<TourOutBound> getOutBandObjectList() {
    return outBandObjectList;
  }

  public void setOutBandObjectList(List<TourOutBound> outBandObjectList) {
    this.outBandObjectList = outBandObjectList;
  }

  public String getOutBandObject() {
    return outBandObject;
  }

  public void setOutBandObject(String outBandObject) {
    this.outBandObject = outBandObject;
  }

  public String getLabel_1() {
    return label_1;
  }

  public void setLabel_1(String label_1) {
    this.label_1 = label_1;
  }

  public String getLabel_2() {
    return label_2;
  }

  public void setLabel_2(String label_2) {
    this.label_2 = label_2;
  }

  public String getLabel_3() {
    return label_3;
  }

  public void setLabel_3(String label_3) {
    this.label_3 = label_3;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getObjectType() {
    return objectType;
  }

  public void setObjectType(String objectType) {
    this.objectType = objectType;
  }

}
