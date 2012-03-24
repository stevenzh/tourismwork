package com.opentravelsoft.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.entity.vacation.LineTheme;
import com.opentravelsoft.providers.LineThemeDao;

@Service("LineThemeService")
public class LineThemeServiceImpl implements LineThemeService {

  @Autowired
  private LineThemeDao lineThemeDao;

  public List<LineTheme> roGetTypeList() {
    return lineThemeDao.getTypeList();
  }

  public LineTheme roGetTypeDetail(String code) {
    return lineThemeDao.get(code);
  }

  public void txInsertType(LineTheme lineTheme) {
    lineThemeDao.save(lineTheme);
  }

  public void txUpdateType(LineTheme lineTheme) {
    lineThemeDao.save(lineTheme);
  }

  public void txDeleteType(String code) {
    lineThemeDao.remove(code);
  }

}
