package com.opentravelsoft.action.manage;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.service.MyPageService;

public class OperatorAction extends ManageAction {
  private static final long serialVersionUID = 2886687498402018762L;

  @Autowired
  private MyPageService myPageService;

  public String execute() throws Exception {
    return SUCCESS;
  }
}
