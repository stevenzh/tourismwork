package com.opentravelsoft.action.manage.vacation;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.vacation.LineTheme;
import com.opentravelsoft.service.product.LineThemeService;
import com.opentravelsoft.util.StringUtil;

/**
 * 参数设置:线路主题维护
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:04 $
 */
public class LineThemeAction extends ManageAction {
  private static final long serialVersionUID = -2281630888842253087L;

  protected static final Log logger = LogFactory.getLog(LineThemeAction.class);

  private String code;

  private List<LineTheme> lineThemeList;

  private LineTheme lineTheme = new LineTheme();

  @Autowired
  private LineThemeService lineThemeService;

  public String input() throws Exception {
    return INPUT;
  }

  public String execute() throws Exception {
    lineThemeList = lineThemeService.roGetTypeList();
    return SUCCESS;
  }

  public String detail() throws Exception {
    lineTheme = lineThemeService.roGetTypeDetail(code);
    return SUCCESS;
  }

  public String delete() throws Exception {
    if (StringUtil.hasLength(code))
      lineThemeService.txDeleteType(code);
    return SUCCESS;
  }

  public String add() throws Exception {
    lineThemeService.txInsertType(lineTheme);
    return SUCCESS;
  }

  public String change() throws Exception {
    lineThemeService.txUpdateType(lineTheme);
    return SUCCESS;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public LineTheme getFitType() {
    return lineTheme;
  }

  public void setFitType(LineTheme fitType) {
    this.lineTheme = fitType;
  }

  public List<LineTheme> getFitTypeList() {
    return lineThemeList;
  }

}