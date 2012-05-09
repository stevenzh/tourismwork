package com.opentravelsoft.service.module;

import java.util.List;

import com.opentravelsoft.entity.Module;

public interface ModuleService {
  List<Module> roGetModuleList(boolean active);

  List<Module> roGetQueryModule(String keyword);

  void txDelModule(long moduleId);

  Module roGetModuleDetail(long moduleId);

  void txSaveModule(Module module);

  public String txSaveRoleModelPerm(long roleId, String moduleName,
      String moduleAction, String permValue);
}
