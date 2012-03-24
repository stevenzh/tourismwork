package com.opentravelsoft.service.setting;

import java.util.List;
import java.util.Set;

import com.opentravelsoft.util.LabelValueBean;

import com.opentravelsoft.entity.Lists;

public interface ListService
{
    List<Lists> getAllList();

    List<Lists> getList(String type);

    void deleteList(long listId);

    Lists getList(long listId);

    int updateList(Lists config);

    List<Lists> searchList(String listName);

    Set<String> getListType();

    List<LabelValueBean> getListByCategory(String listName);

}
