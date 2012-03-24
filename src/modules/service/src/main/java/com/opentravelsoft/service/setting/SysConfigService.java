package com.opentravelsoft.service.setting;

import java.util.List;
import java.util.Set;

import com.opentravelsoft.entity.SysConfig;

public interface SysConfigService
{

    List<SysConfig> getAllConfig();

    void deleteConfig(long configId);

    SysConfig getConfig(long configId);

    void updateConfig(SysConfig config);

    Set<String> getCategory();

    List<SysConfig> getConfigByCategory(String category);

}
