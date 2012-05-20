package com.opentravelsoft.providers;

import java.util.List;

import com.opentravelsoft.entity.Group;

public interface GroupDao extends GenericDao<Group, Integer> {
  public List<Group> getAllGroups();
}
