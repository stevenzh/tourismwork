package com.opentravelsoft.service;

import java.util.Date;
import java.util.List;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.providers.mixed.PlanListDao;

@Service("ShareService")
public class ShareServiceImpl implements ShareService {
  
  @Autowired
  private PlanListDao planListDao;

  public String roSearchShare(Date startDate) {
    List<LabelValueBean> list = planListDao.searchShare(startDate);
    StringBuilder ret = new StringBuilder();
    for (LabelValueBean labelValueBean : list) {
      ret.append(labelValueBean.getLabel() + ",");
      ret.append(labelValueBean.getValue() + ",");
    }
    return ret.toString();

  }
}
