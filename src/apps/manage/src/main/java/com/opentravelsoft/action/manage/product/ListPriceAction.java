package com.opentravelsoft.action.manage.product;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.common.SessionKeyParams;
import com.opentravelsoft.entity.Airways;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Line;
import com.opentravelsoft.entity.LinePrice;
import com.opentravelsoft.service.product.LinePriceService;

/**
 * 线路价格列表
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:54 $
 */
public class ListPriceAction extends ManageAction {
  private static final long serialVersionUID = 7671898914387730451L;

  protected static final Log logger = LogFactory.getLog(ListPriceAction.class);

  private LinePriceService routePriceService;

  private Line line;

  private String recNo;

  // -------------------------------------------------------------------------
  // 查询条件
  private Date kenStartDate;

  private Date kenEndDate;

  // -------------------------------------------------------------------------

  private List<LinePrice> priceList;

  private List<Airways> airways;

  private String note;

  public void setNote(String note) {
    this.note = note;
  }

  /**
   * 查询线路报价 Today -- Today+2Month
   * 
   */
  public String input() {
    Map<String, Object> session = ActionContext.getContext().getSession();
    // 取得系统时间
    buildSysdate();

    line = (Line) session.get(SessionKeyParams.EBIZ_CURRENT_ROUTE);
    if (kenStartDate == null) {
      kenStartDate = systemDate;
      Calendar calDate = Calendar.getInstance();
      calDate.setTime(systemDate);
      calDate.add(Calendar.MONTH, 2);
      kenEndDate = calDate.getTime();
    }
    priceList = routePriceService.getLinePrice(line.getLineNo(), kenStartDate,
        kenEndDate);

    return INPUT;
  }

  public String delete() {
    Employee user = getUser();
    int result = routePriceService.txDeleteLinePrice(recNo, note,
        user.getUserId());
    if (result < 0)
      addActionError("线路报价删除失败.");
    return SUCCESS;
  }

  public Line getLine() {
    return line;
  }

  public List<Airways> getAirways() {
    return airways;
  }

  public List<LinePrice> getPriceList() {
    return priceList;
  }

  public void setPriceList(List<LinePrice> priceList) {
    this.priceList = priceList;
  }

  public Date getKenEndDate() {
    return kenEndDate;
  }

  public void setKenEndDate(Date kenEndDate) {
    this.kenEndDate = kenEndDate;
  }

  public Date getKenStartDate() {
    return kenStartDate;
  }

  public void setKenStartDate(Date kenStartDate) {
    this.kenStartDate = kenStartDate;
  }

  @Autowired
  public void setRoutePriceService(LinePriceService routePriceService) {
    this.routePriceService = routePriceService;
  }

  public void setRecNo(String recNo) {
    this.recNo = recNo;
  }

}
