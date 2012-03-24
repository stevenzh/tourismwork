package com.opentravelsoft.service.setting;

import java.util.List;

import com.opentravelsoft.util.LabelValueBean;

import com.opentravelsoft.entity.Guide;
import com.opentravelsoft.entity.Team;

public interface GuideService
{
    public List<Guide> roGetGuidesList();

    public List<LabelValueBean> roGetBusinessList();

    public List<LabelValueBean> getBirthplaces();

    public Guide roGetGuideDetail(String accCd);

    public int txUpdate(Guide guide);

    public int txDelete(String accCd);

    public int txInsert(Guide guide);

    public List<Guide> roGetQueryGuide(String accNm);

    public List<Team> getOperatorTeams();

}
