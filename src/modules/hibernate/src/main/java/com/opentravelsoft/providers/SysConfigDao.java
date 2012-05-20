package com.opentravelsoft.providers;

import java.util.List;
import java.util.Set;

import com.opentravelsoft.entity.SysConfig;

public interface SysConfigDao extends GenericDao<SysConfig, Integer> {
  Set<String> getCategory();

  List<SysConfig> getConfigByCategory(String category);

}
