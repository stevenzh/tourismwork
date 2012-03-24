package com.opentravelsoft.action.manage.resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Airways;
import com.opentravelsoft.service.resource.AirwaysService;
import com.opentravelsoft.util.StringUtil;

/**
 * 参数设置:航空公司维护
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:04 $
 */
public class EditAirwaysAction extends ManageAction {
  private static final long serialVersionUID = 4025088482007685362L;

  protected static final Log logger = LogFactory
      .getLog(EditAirwaysAction.class);

  private AirwaysService airwaysService;

  private Airways airways = new Airways();

  private String airwaysId;

  private String opKey;

  @Autowired
  public void setAirwaysService(AirwaysService airwaysService) {
    this.airwaysService = airwaysService;
  }

  public String input() {
    if (StringUtil.hasLength(airwaysId)) {
      airways = airwaysService.roGetAirwaysDetail(airwaysId);
    }

    return INPUT;
  }

  public String submit() {
    Airways fm = null;

    if (StringUtil.hasLength(airways.getCode())) {
      fm = airwaysService.roGetAirwaysDetail(airways.getCode());
      if (null == fm) {
        // 要删除的记录不存在
        addActionError(getText("ERR_A08002"));
        return INPUT;
      }
    }

    airwaysService.txSave(airways);
    return SUCCESS;
  }

  public Airways getAirways() {
    return airways;
  }

  public void setAirways(Airways airways) {
    this.airways = airways;
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
