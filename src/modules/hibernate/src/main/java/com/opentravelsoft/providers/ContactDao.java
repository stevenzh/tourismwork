package com.opentravelsoft.providers;

import java.util.List;

import com.opentravelsoft.entity.Contact;

public interface ContactDao extends GenericDao<Contact, Integer> {
  /**
   * 取得客户的联系人
   * 
   * @param accountId 客户ID
   * @return
   */
  public List<Contact> searchContact(int accountId, boolean active);

}
