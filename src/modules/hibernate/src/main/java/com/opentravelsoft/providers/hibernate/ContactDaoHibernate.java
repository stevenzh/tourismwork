package com.opentravelsoft.providers.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.Contact;
import com.opentravelsoft.providers.ContactDao;

@Repository("ContactDao")
public class ContactDaoHibernate extends GenericDaoHibernate<Contact, Integer>
    implements ContactDao {
  public ContactDaoHibernate() {
    super(Contact.class);
  }

  @SuppressWarnings("unchecked")
  public List<Contact> searchContact(int customerId, boolean active) {
    StringBuilder sb = new StringBuilder();
    sb.append("from Contact where customerId=? ");
    if (active)
      sb.append("and del='N' ");
    sb.append("order by name");
    Object[] params = { customerId };
    List<Contact> list = getHibernateTemplate().find(sb.toString(), params);
    return list;
  }

}
