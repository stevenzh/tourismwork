package com.opentravelsoft.action.manage.setting;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.entity.Guide;
import com.opentravelsoft.service.setting.GuideService;
import com.opentravelsoft.util.StringUtil;

/**
 * 参数设置:导陪档案
 * 
 * @author udb
 */
public class EditGuideAction extends ManageAction {
  private static final long serialVersionUID = 2909557567120180025L;

  protected static final Log logger = LogFactory.getLog(EditGuideAction.class);

  private String accCd;

  private GuideService guideService;

  private Guide guide = new Guide();

  /** 性别列表 */
  private List<LabelValueBean> sexList = new ArrayList<LabelValueBean>();

  /** 有无列表 */
  private List<LabelValueBean> holdKeyList = new ArrayList<LabelValueBean>();

  /** 分类列表(一) */
  private List<LabelValueBean> workType1List = new ArrayList<LabelValueBean>();

  /** 分类列表(二) */
  private List<LabelValueBean> workType2List = new ArrayList<LabelValueBean>();

  /** 签约列表 */
  private List<LabelValueBean> signKeyList = new ArrayList<LabelValueBean>();

  /** 血型列表 */
  private List<LabelValueBean> bloodTypeList = new ArrayList<LabelValueBean>();

  /** 部门列表 */
  private List<Team> teamList = new ArrayList<Team>();

  /** 职业列表 */
  private List<LabelValueBean> businessList = new ArrayList<LabelValueBean>();

  /** 出生地列表 */
  private List<LabelValueBean> birthPlaceList = new ArrayList<LabelValueBean>();

  /** 护照签发地 */
  private List<LabelValueBean> hzaddList = new ArrayList<LabelValueBean>();

  /** 护照种类 */
  private List<LabelValueBean> hzzlList = new ArrayList<LabelValueBean>();

  public String input() throws Exception {
    sexList = getSysList("DOM_sex");
    holdKeyList = getCodeList("ebiz_setting_hold");
    workType1List = getCodeList("ebiz_setting_workType1");
    workType2List = getCodeList("ebiz_setting_workType2");
    signKeyList = getCodeList("ebiz_setting_signKey");
    bloodTypeList = getSysList("DOM_BloodType");
    hzzlList = getSysList("PassportType");
    teamList = guideService.getOperatorTeams();
    businessList = guideService.roGetBusinessList();
    birthPlaceList = guideService.getBirthplaces();
    hzaddList = guideService.getBirthplaces();
    if (StringUtil.hasLength(accCd))
      guide = guideService.roGetGuideDetail(accCd);

    return INPUT;
  }

  public String submit() throws Exception {
    int result = 0;
    if (StringUtil.hasLength(accCd))
      result = guideService.txUpdate(guide);
    else
      result = guideService.txInsert(guide);
    if (result < 0) {
      addActionError("陪同代码重复!");
    }

    return SUCCESS;
  }

  public String getAccCd() {
    return accCd;
  }

  public void setAccCd(String accCd) {
    this.accCd = accCd;
  }

  @Autowired
  public void setGuideService(GuideService guideService) {
    this.guideService = guideService;
  }

  public List<LabelValueBean> getSexList() {
    return sexList;
  }

  public List<LabelValueBean> getWorkType1List() {
    return workType1List;
  }

  public List<LabelValueBean> getWorkType2List() {
    return workType2List;
  }

  public List<LabelValueBean> getSignKeyList() {
    return signKeyList;
  }

  public Guide getGuide() {
    return guide;
  }

  public void setGuide(Guide guide) {
    this.guide = guide;
  }

  public List<LabelValueBean> getHoldKeyList() {
    return holdKeyList;
  }

  public List<LabelValueBean> getBloodTypeList() {
    return bloodTypeList;
  }

  public List<Team> getTeamList() {
    return teamList;
  }

  public List<LabelValueBean> getBusinessList() {
    return businessList;
  }

  public List<LabelValueBean> getBirthPlaceList() {
    return birthPlaceList;
  }

  public List<LabelValueBean> getHzaddList() {
    return hzaddList;
  }

  public List<LabelValueBean> getHzzlList() {
    return hzzlList;
  }

}
