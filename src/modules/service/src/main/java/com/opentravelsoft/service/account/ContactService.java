package com.opentravelsoft.service.account;

import java.util.List;

import com.opentravelsoft.entity.Contact;

public interface ContactService {

  /**
   * 
   * @param accountId
   * @param active
   * @return
   */
  public List<Contact> searchContact(int accountId, boolean active);

  public List<Contact> getAllContacts();

}
