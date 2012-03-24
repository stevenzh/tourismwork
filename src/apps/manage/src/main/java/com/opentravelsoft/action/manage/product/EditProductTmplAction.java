package com.opentravelsoft.action.manage.product;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.common.KeyParams;
import com.opentravelsoft.common.TeamType;
import com.opentravelsoft.entity.Destination;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.ProductItem;
import com.opentravelsoft.entity.ProductTmpl;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.service.product.ProductTmplService;

/**
 * 线路模板维护
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1.2.1 $ $Date: 2009/07/17 09:14:13 $
 */
public class EditProductTmplAction extends ManageAction {

  private static final long serialVersionUID = -2281630888842253087L;

  protected static final Log logger = LogFactory
      .getLog(EditProductTmplAction.class);

  private ProductTmplService lineTmplService;

  private int tmplId = 0;

  private ProductTmpl tmpl = new ProductTmpl();

  private List<Team> teamList;

  /** 目的地列表 */
  private List<Destination> destinationList;

  private List<ProductItem> itemList;

  // -------------------------------------------------------------------------
  private int teamId;

  private int itemId;

  private String destCode;

  // -------------------------------------------------------------------------
  public String input() throws Exception {
    Employee user = getUser();
    destinationList = lineTmplService.roGetDestination();
    teamList = lineTmplService.getTeamList(user.getUserId(), TeamType.Product);
    itemList = lineTmplService.getProductItems(true);

    if (tmplId != 0)
      tmpl = lineTmplService.getProductTmpl(tmplId);
    return INPUT;
  }

  public String submit() throws Exception {
    int resu = 0;
    tmpl.setType(KeyParams.EBIZ_TYPE_BOOK_NOTICE);
    if (tmplId != 0)
      resu = lineTmplService.updateProductTmpl(tmpl);
    else
      resu = lineTmplService.insertProductTmpl(tmpl);
    if (resu < 0) {
      addActionError("编号已存在!");
    }

    return SUCCESS;
  }

  @Autowired
  public void setLineTmplService(ProductTmplService lineBookNoticeService) {
    this.lineTmplService = lineBookNoticeService;
  }

  public ProductTmpl getTmpl() {
    return tmpl;
  }

  public void setTmpl(ProductTmpl tmpl) {
    this.tmpl = tmpl;
  }

  public List<Destination> getDestinationList() {
    return destinationList;
  }

  public List<Team> getTeamList() {
    return teamList;
  }

  public int getTmplId() {
    return tmplId;
  }

  public void setTmplId(int tmplId) {
    this.tmplId = tmplId;
  }

  public int getTeamId() {
    return teamId;
  }

  public void setTeamId(int teamId) {
    this.teamId = teamId;
  }

  public int getItemId() {
    return itemId;
  }

  public void setItemId(int itemId) {
    this.itemId = itemId;
  }

  public String getDestCode() {
    return destCode;
  }

  public void setDestCode(String destCode) {
    this.destCode = destCode;
  }

  public List<ProductItem> getItemList() {
    return itemList;
  }

}
