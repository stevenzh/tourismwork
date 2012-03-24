package com.opentravelsoft.json;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.service.MessageService;

/**
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:16 $
 */
public class MyTaskCountAction extends ManageAction {
  private static final long serialVersionUID = -5840178415466047565L;

  private MessageService messageService;

  private int count = 0;

  private long userId;

  @Autowired
  public void setMessageService(MessageService messageService) {
    this.messageService = messageService;
  }

  public String execute() {
    count = messageService.getMessages(userId);
    return Action.SUCCESS;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

}
