package com.opentravelsoft.action.manage.resource;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Airways;
import com.opentravelsoft.service.resource.AirwaysService;
import com.opentravelsoft.util.PaginationSupport;

/**
 * 参数设置:航空公司维护
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:03 $
 */
public class ListAirwaysAction extends ManageAction {
  private static final long serialVersionUID = 4025088482007685362L;

  protected static final Log logger = LogFactory
      .getLog(ListAirwaysAction.class);

  private AirwaysService airwaysService;

  private List<Airways> airwaysList;

  private String airwaysId;

  private String opKey;

  @Autowired
  public void setAirwaysService(AirwaysService airwaysService) {
    this.airwaysService = airwaysService;
  }

  public List<Airways> getAirwaysList() {
    return airwaysList;
  }

  public String input() {
    dreamPage();
    PaginationSupport support = airwaysService.getAirwaysList(false,
        getFromRecord(), getMoveCount());
    airwaysList = support.getItems();
    currentPage(support.getTotalCount());
    return INPUT;
  }

  public String delete() {
    Airways fm = airwaysService.roGetAirwaysDetail(airwaysId);
    if (null == fm) {
      // 要删除的记录不存在
      addActionError(getText("ERR_A08001"));
    } else {
      airwaysService.txDelete(fm);
    }

    return SUCCESS;
  }

  @Override
  protected int getMoveCount() {
    return 20;
  }

  public String getAirwaysId() {
    return airwaysId;
  }

  public void setAirwaysId(String airwaysId) {
    this.airwaysId = airwaysId;
  }

  public String getOpKey() {
    return opKey;
  }

  public void setOpKey(String opKey) {
    this.opKey = opKey;
  }

}
