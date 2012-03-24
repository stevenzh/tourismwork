package com.opentravelsoft.providers;

import java.util.List;

import com.opentravelsoft.entity.Guide;

public interface GuideDao extends GenericDao<Guide, String> {
  public int deleteGuide(String guideId);

  public int insertGuide(Guide guide);

  public int updateGuide(Guide guide);

  public Guide getGuideDetail(String guideId);

  public List<Guide> queryGuide(String name);

}
