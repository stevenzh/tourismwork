package com.opentravelsoft.service;

import java.util.List;
import javax.jws.WebService;

import com.opentravelsoft.entity.Shortcut;

@WebService
public interface ShortcutManager
{
    Shortcut getShortcut(String shortcutId);

    List<Shortcut> getShortcutByModule(String moduleName);

    List<Shortcut> getShortcuts();

    Shortcut saveShortcut(Shortcut shortcut);

    int removeShortcut(String shortcutId);

}