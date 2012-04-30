package com.opentravelsoft.action.manage.account;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.service.account.ContactService;

/**
 * 联系人
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:15 $
 */
public class EditContactAction extends ManageAction {
  private static final long serialVersionUID = -6218728273863091126L;

  @Autowired
  private ContactService contactService;

  private int contactId;

  @Override
  public String input() throws Exception {
    return INPUT;
  }

  public int getContactId() {
    return contactId;
  }

  public void setContactId(int contactId) {
    this.contactId = contactId;
  }

}
