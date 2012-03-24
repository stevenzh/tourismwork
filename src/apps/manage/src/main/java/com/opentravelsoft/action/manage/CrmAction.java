package com.opentravelsoft.action.manage;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.service.MyPageService;

/**
 * 客户关系管理
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:16 $
 */
public class CrmAction extends ManageAction {
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
