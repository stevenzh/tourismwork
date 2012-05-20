package com.opentravelsoft.service.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.entity.Contact;
import com.opentravelsoft.providers.ContactDao;

@Service("ContactService")
public class ContactServiceImpl implements ContactService {

  @Autowired
  private ContactDao contactDao;

  public List<Contact> searchContact(int accountId, boolean active) {
    return contactDao.searchContact(accountId, active);
  }

  public List<Contact> getAllContacts() {
    return contactDao.getAll();
  }

}
