package com.opentravelsoft.providers;

import java.util.List;

import com.opentravelsoft.entity.Shortcut;

public interface ShortcutDao extends GenericDao<Shortcut, Long>
{
    public List<Shortcut> loadShortByModule(String moduleName);
}
