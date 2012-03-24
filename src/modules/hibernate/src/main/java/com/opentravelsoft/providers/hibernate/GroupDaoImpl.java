package com.opentravelsoft.providers.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.Group;
import com.opentravelsoft.providers.GroupDao;

@Repository("DepartmentDao")
public class GroupDaoImpl extends GenericDaoHibernate<Group, Long> implements
    GroupDao {
  public GroupDaoImpl() {
    super(Group.class);
  }

  @SuppressWarnings("unchecked")
  public List<Group> getAllGroups() {
    StringBuilder sql = new StringBuilder();
    sql.append("from Group order by groupId");

    List<Group> list = getHibernateTemplate().find(sql.toString());

    return list;
  }

}
