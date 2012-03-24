package com.opentravelsoft.action.manage.setting;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Guide;
import com.opentravelsoft.service.setting.GuideService;

/**
 * 参数设置:导陪档案
 * 
 * @author udb
 */
public class ListGuideAction extends ManageAction {
  private static final long serialVersionUID = 2914390488320236698L;

  protected static final Log logger = LogFactory.getLog(ListGuideAction.class);

  private String accCd;

  private String accNm;

  private GuideService guideService;

  private List<Guide> guideList;

  public String input() throws Exception {
    guideList = guideService.roGetGuidesList();
    for (Guide obj : guideList) {
      if ("M".equals(obj.getAccSex())) {
        obj.setAccSex("男");
      } else {
        obj.setAccSex("女");
      }
    }
    currentPage(guideList.size());

    return INPUT;
  }

  public String query() throws Exception {
    guideList = guideService.roGetQueryGuide(accNm);
    for (Guide obj : guideList) {
      if ("M".equals(obj.getAccSex())) {
        obj.setAccSex("男");
      } else {
        obj.setAccSex("女");
      }
    }
    currentPage(guideList.size());
    return SUCCESS;
  }

  public String delete() throws Exception {
    int result = 0;
    result = guideService.txDelete(accCd);
    if (result < 0) {
      addActionError("删除失败!");
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

  public List<Guide> getGuideList() {
    return guideList;
  }

  public String getAccNm() {
    return accNm;
  }

  public void setAccNm(String accNm) {
    this.accNm = accNm;
  }

}
