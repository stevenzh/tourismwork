package com.opentravelsoft.action.manage;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.service.MyPageService;

/**
 * 任务列表
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:16 $
 */
public class MyPageAction extends ManageAction {
  private static final long serialVersionUID = -598635103771546388L;

  @Autowired
  private MyPageService myPageService;

  public String execute() throws Exception {
    return SUCCESS;
  }

}
