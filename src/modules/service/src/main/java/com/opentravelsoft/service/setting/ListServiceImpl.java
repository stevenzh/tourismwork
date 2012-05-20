package com.opentravelsoft.service.setting;

import java.util.List;
import java.util.Set;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.entity.Lists;
import com.opentravelsoft.providers.ListDao;

@Service("ListService")
public class ListServiceImpl implements ListService {

  @Autowired
  private ListDao listDao;

  public void deleteList(int listId) {
    listDao.remove(listId);
  }

  public List<Lists> getList(String type) {
    return listDao.getListByType(type);
  }

  public Lists getList(int listId) {
    return listDao.get(listId);
  }

  public int updateList(Lists config) {
    return listDao.updateList(config);
  }

  public List<Lists> getAllList() {
    return listDao.getListByType(null);
  }

  public List<Lists> searchList(String listName) {
    return listDao.getListByType(listName);
  }

  public Set<String> getListType() {
    return listDao.getListType();
  }

  public List<LabelValueBean> getListByCategory(String listName) {
    return listDao.getList(listName);
  }

}
