package com.opentravelsoft.action.manage.operator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.common.SessionKeyParams;
import com.opentravelsoft.entity.City;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.Tourist;
import com.opentravelsoft.entity.product.Leader;
import com.opentravelsoft.service.operator.ArrangeLeaderService;
import com.opentravelsoft.service.operator.TourService;

/**
 * 安排领队
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 */

public class ArrangeLeaderAction extends ManageAction {
  private static final long serialVersionUID = 4583611932565978060L;

  @Autowired
  private ArrangeLeaderService arrangeLeaderService;

  @Autowired
  private TourService tourService;

  /** 团号 */
  private String tourNo;

  private Plan tour;

  private List<City> portCitys;

  private String[] nameKey;

  /** 性别选择列表 */
  private List<LabelValueBean> sexList = new ArrayList<LabelValueBean>();

  /** 出生地选择列表 */
  private List<LabelValueBean> birthPlaceList = new ArrayList<LabelValueBean>();

  /** 护照签发地列表 */
  private List<LabelValueBean> passportPlaceList = new ArrayList<LabelValueBean>();

  private List<Leader> leaderList;

  @Override
  public String input() throws Exception {
    Object obj = ActionContext.getContext().getSession()
        .get(SessionKeyParams.EBIZ_CURRENT_TOUR);
    if (null != obj) {
      tourNo = ((Plan) obj).getTourNo();
    }

    tour = arrangeLeaderService.roGetDetail(tourNo);
    portCitys = tourService.roGetPortCitys();
    sexList = getSysList("DOM_sex");
    birthPlaceList = tourService.roGetBirthplaceList();
    passportPlaceList = tourService.roGetPassportPlaceList();

    Map<String, String> birthPlace = new HashMap<String, String>();
    Map<String, String> passportPlace = new HashMap<String, String>();
    for (LabelValueBean lbn : birthPlaceList) {
      birthPlace.put(lbn.getLabel(), lbn.getValue());
    }
    for (LabelValueBean lbn : passportPlaceList) {
      passportPlace.put(lbn.getLabel(), lbn.getValue());
    }

    for (Tourist item : tour.getCustomerList()) {
      item.setBirthplaceName(birthPlace.get(item.getBirthplace()));
      item.setPassportPlaceName(passportPlace.get(item.getPassportPlace()));
      item.setSex(item.getSex().equals("M") ? "男" : "女");
    }

    return INPUT;
  }

  public String submit() throws Exception {
    Employee user = getUser();
    Object obj = ActionContext.getContext().getSession()
        .get(SessionKeyParams.EBIZ_CURRENT_TOUR);
    if (null != obj) {
      tourNo = ((Plan) obj).getTourNo();
    }
    // arrangeLeaderService.txArrangeLeader(tourNo, nameKey);
    arrangeLeaderService.txArrangeLeader(tourNo, nameKey, user.getUserId());
    return SUCCESS;
  }

  public String choose() throws Exception {
    leaderList = arrangeLeaderService.roGetLeaderList();
    sexList = getSysList("DOM_sex");
    birthPlaceList = tourService.roGetBirthplaceList();
    passportPlaceList = tourService.roGetPassportPlaceList();
    Map<String, String> birthPlace = new HashMap<String, String>();
    Map<String, String> passportPlace = new HashMap<String, String>();
    for (LabelValueBean lbn : birthPlaceList) {
      birthPlace.put(lbn.getLabel(), lbn.getValue());
    }
    for (LabelValueBean lbn : passportPlaceList) {
      passportPlace.put(lbn.getLabel(), lbn.getValue());
    }
    for (Leader item : leaderList) {
      item.setBirthplaceName(birthPlace.get(item.getBirthplace()));
      item.setPassportPlaceName(passportPlace.get(item.getPassportPlace()));
      item.setSex(item.getSex().equals("M") ? "男" : "女");
    }

    currentPage(leaderList.size());
    return SUCCESS;
  }

  /**
   * 选择领队
   * 
   * @return
   * @throws Exception
   */
  public String submitChoose() throws Exception {
    Employee user = getUser();
    Object obj = ActionContext.getContext().getSession()
        .get(SessionKeyParams.EBIZ_CURRENT_TOUR);
    if (null != obj) {
      tourNo = ((Plan) obj).getTourNo();
    }
    arrangeLeaderService.txArrangeFromLeader(tourNo, nameKey, user.getUserId());
    return SUCCESS;
  }

  /**
   * 取消领队
   * 
   * @return
   */
  public String cancelLeader() {
    Employee user = getUser();
    Object obj = ActionContext.getContext().getSession()
        .get(SessionKeyParams.EBIZ_CURRENT_TOUR);
    if (null != obj) {
      tourNo = ((Plan) obj).getTourNo();
    }
    int ret = arrangeLeaderService.txCancelLeader(tourNo, nameKey,
        user.getUserId());
    if (ret == 0)
      addActionMessage("成功取消领队！");
    else
      addActionMessage("取消领队失败！");

    return SUCCESS;
  }

  protected int getMoveCount() {
    return 40;
  }

  public Plan getTour() {
    return tour;
  }

  public List<City> getPortCitys() {
    return portCitys;
  }

  public List<LabelValueBean> getSexList() {
    return sexList;
  }

  public List<LabelValueBean> getBirthPlaceList() {
    return birthPlaceList;
  }

  public List<LabelValueBean> getPassportPlaceList() {
    return passportPlaceList;
  }

  public void setNameKey(String[] nameKey) {
    this.nameKey = nameKey;
  }

  public String[] getNameKey() {
    return nameKey;
  }

  public void setLeaderList(List<Leader> leaderList) {
    this.leaderList = leaderList;
  }

  public List<Leader> getLeaderList() {
    return leaderList;
  }

  public void setTourNo(String tourNo) {
    this.tourNo = tourNo;
  }

  public String getTourNo() {
    return tourNo;
  }

}
