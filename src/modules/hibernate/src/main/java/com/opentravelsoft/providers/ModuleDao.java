package com.opentravelsoft.providers;

import java.util.List;

import com.opentravelsoft.entity.Module;

/**
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 */
public interface ModuleDao extends GenericDao<Module, Integer> {
  List<Module> getModuleList(boolean active);

  List<Module> queryModle(String keyword);

}
