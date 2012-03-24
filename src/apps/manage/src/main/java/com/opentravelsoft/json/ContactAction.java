package com.opentravelsoft.json;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Contact;
import com.opentravelsoft.service.account.ContactService;

/**
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:16 $
 */
public class ContactAction extends ManageAction {
  private static final long serialVersionUID = -5840178415466047565L;

  private ContactService contactService;

  private int accountId;

  private List<Contact> map = new ArrayList<Contact>();

  public String execute() {
    map = contactService.searchContact(accountId, true);
    return Action.SUCCESS;
  }

  @Autowired
  public void setContactService(ContactService contactService) {
    this.contactService = contactService;
  }

  public String info() {
    return SUCCESS;
  }

  public List<Contact> getMap() {
    return map;
  }

  public int getAccountId() {
    return accountId;
  }

  public void setAccountId(int accountId) {
    this.accountId = accountId;
  }

}
