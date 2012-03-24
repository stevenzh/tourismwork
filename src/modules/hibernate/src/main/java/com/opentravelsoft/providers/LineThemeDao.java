package com.opentravelsoft.providers;

import java.util.List;

import com.opentravelsoft.entity.vacation.LineTheme;

public interface LineThemeDao extends GenericDao<LineTheme, String>
{
    public List<LineTheme> getTypeList();

}
