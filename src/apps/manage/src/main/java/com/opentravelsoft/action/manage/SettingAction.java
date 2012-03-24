package com.opentravelsoft.action.manage;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.service.MyPageService;

public class SettingAction extends ManageAction {

  private static final long serialVersionUID = 2886687498402018762L;

  private MyPageService myPageService;

  @Autowired
  public void setMyPageService(MyPageService myPageService) {
    this.myPageService = myPageService;
  }

  public String execute() throws Exception {
    return SUCCESS;
  }
}
