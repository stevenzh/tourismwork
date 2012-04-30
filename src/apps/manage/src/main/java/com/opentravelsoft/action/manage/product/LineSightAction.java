package com.opentravelsoft.action.manage.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.common.SessionKeyParams;
import com.opentravelsoft.entity.Line;
import com.opentravelsoft.entity.Sight;
import com.opentravelsoft.service.product.LineSightService;

/**
 * 线路包含景点
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:55 $
 */
public class LineSightAction extends ManageAction {
  private static final long serialVersionUID = 7671898914387730451L;

  @Autowired
  private LineSightService lineSightService;

  private Line line;

  private String[] selectedSights;

  private List<Sight> sightList;

  private List<Sight> searchList = new ArrayList<Sight>();

  public String input() throws Exception {
    Map<String, Object> session = ActionContext.getContext().getSession();
    line = (Line) session.get(SessionKeyParams.EBIZ_CURRENT_ROUTE);

    sightList = lineSightService.roGetLineSights(line.getLineNo());
    searchList = lineSightService.roGetDestinationSights(line.getLineNo());

    for (int i = 0; i < searchList.size(); i++) {
      Sight dts = searchList.get(i);
      if (dts.getCnNote().length() > 100)
        dts.setCnNote(dts.getCnNote().substring(0, 100) + "...");
      for (Sight sight : sightList) {
        if (dts.getSightNo().equals(sight.getSightNo()))
          dts.setChecked("true");
      }
    }

    if (searchList.size() == 0)
      addActionMessage("线路的目的地没有选择或该目的地没有包含的景点．");

    return INPUT;
  }

  public String submit() throws Exception {
    line = (Line) ActionContext.getContext().getSession()
        .get(SessionKeyParams.EBIZ_CURRENT_ROUTE);
    List<Sight> list = new ArrayList<Sight>();
    Sight sight = null;

    for (int i = 0; i < selectedSights.length; i++) {
      sight = new Sight();
      sight.setSightNo(selectedSights[i]);
      list.add(sight);
    }
    lineSightService.txSaveSights(list, line.getLineNo());

    addActionMessage("线路景点保存成功.");
    return SUCCESS;
  }

  public Line getLine() {
    return line;
  }

  public void setCheck(String[] sights) {
    this.selectedSights = sights.clone();
  }

  public List<Sight> getSearchList() {
    return searchList;
  }

}
