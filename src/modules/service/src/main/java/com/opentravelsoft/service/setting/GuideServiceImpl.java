package com.opentravelsoft.service.setting;

import java.util.List;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.common.TeamType;
import com.opentravelsoft.entity.Guide;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.providers.GuideDao;
import com.opentravelsoft.providers.ListDao;
import com.opentravelsoft.providers.TeamDao;

@Service("GuideService")
public class GuideServiceImpl implements GuideService {
  
  @Autowired
  private GuideDao guideDao;

  @Autowired
  private ListDao listDao;

  @Autowired
  private TeamDao teamDao;

  public int txDelete(String accCd) {
    return guideDao.deleteGuide(accCd);
  }

  public Guide roGetGuideDetail(String accCd) {
    return guideDao.getGuideDetail(accCd);
  }

  public List<Guide> roGetGuidesList() {
    return guideDao.getAll();
  }

  public int txInsert(Guide guide) {
    return guideDao.insertGuide(guide);
  }

  public int txUpdate(Guide guide) {
    return guideDao.updateGuide(guide);
  }

  public List<LabelValueBean> roGetBusinessList() {
    return listDao.getList("Occupation");
  }

  public List<LabelValueBean> getBirthplaces() {
    return listDao.getList("Homeplace");
  }

  public List<Guide> roGetQueryGuide(String accNm) {
    return guideDao.queryGuide(accNm);
  }

  public List<Team> getOperatorTeams() {
    return teamDao.getTeamList(TeamType.Operator);
  }

}
