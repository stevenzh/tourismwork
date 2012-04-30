package com.opentravelsoft.action.manage.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Contact;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.service.account.ContactService;
import com.opentravelsoft.service.setting.TeamService;

/**
 * 联系人
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:15 $
 */
public class ListContactAction extends ManageAction {

  private static final long serialVersionUID = -7138389244051722L;

  @Autowired
  private ContactService contactService;

  @Autowired
  private TeamService teamService;

  private List<Contact> contactList;

  private List<Team> teamList;

  // -------------------------------------------------------------------------
  private int kenTeamId;

  private int accountId;

  private String kenName;

  private String kenMobile;

  @Override
  public String input() throws Exception {
    contactList = contactService.getAllContacts();
    teamList = teamService.getMarketTeam();
    return INPUT;
  }

  public String execute() throws Exception {
    contactList = contactService.searchContact(accountId, true);
    return SUCCESS;
  }

  public List<Contact> getContactList() {
    return contactList;
  }

  public void setContactList(List<Contact> contactList) {
    this.contactList = contactList;
  }

  public int getAccountId() {
    return accountId;
  }

  public void setAccountId(int accountId) {
    this.accountId = accountId;
  }

  public List<Team> getTeamList() {
    return teamList;
  }

  public int getKenTeamId() {
    return kenTeamId;
  }

  public void setKenTeamId(int kenTeamId) {
    this.kenTeamId = kenTeamId;
  }

  public String getKenName() {
    return kenName;
  }

  public void setKenName(String kenName) {
    this.kenName = kenName;
  }

  public String getKenMobile() {
    return kenMobile;
  }

  public void setKenMobile(String kenMobile) {
    this.kenMobile = kenMobile;
  }

}
