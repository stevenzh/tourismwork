package com.opentravelsoft.service.product;

import java.util.List;

import com.opentravelsoft.entity.vacation.LineTheme;

public interface LineThemeService
{

    public List<LineTheme> roGetTypeList();

    public LineTheme roGetTypeDetail(String code);

    public void txInsertType(LineTheme lineTheme);

    public void txUpdateType(LineTheme lineTheme);

    public void txDeleteType(String code);

}
