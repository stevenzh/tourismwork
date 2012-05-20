package com.opentravelsoft.providers;

import java.util.List;
import java.util.Set;

import com.opentravelsoft.util.LabelValueBean;

import com.opentravelsoft.entity.Lists;

public interface ListDao extends GenericDao<Lists, Integer> {

  public List<LabelValueBean> getList(String listName);

  public List<Lists> getListByType(String listName);

  public int updateList(Lists config);

  public Set<String> getListType();

}