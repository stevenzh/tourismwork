package com.opentravelsoft.action.manage;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.service.MyPageService;

/**
 * 财务核算
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:16 $
 */
public class FinanceAction extends ManageAction {
  private static final long serialVersionUID = -262531514777979744L;

  @Autowired
  private MyPageService myPageService;

  public String execute() throws Exception {
    return SUCCESS;
  }
}
