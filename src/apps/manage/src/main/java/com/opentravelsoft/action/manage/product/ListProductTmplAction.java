package com.opentravelsoft.action.manage.product;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.common.TeamType;
import com.opentravelsoft.entity.Destination;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.ProductItem;
import com.opentravelsoft.entity.ProductTmpl;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.service.product.ProductTmplService;

/**
 * 产品描述模板列表
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1.2.1 $ $Date: 2009/07/17 09:14:14 $
 */
public class ListProductTmplAction extends ManageAction {
  private static final long serialVersionUID = -2281630888842253087L;

  protected static final Log logger = LogFactory
      .getLog(ListProductTmplAction.class);

  private ProductTmplService lineTmplService;

  private int tmplId;

  private List<ProductTmpl> tmplList = new ArrayList<ProductTmpl>();

  private List<Team> teamList;

  private List<ProductItem> itemList;

  /** 目的地列表 */
  private List<Destination> destinationList;

  // -------------------------------------------------------------------------
  private int teamId;

  private int itemId;

  private String destCode;

  // -------------------------------------------------------------------------
  public String input() throws Exception {
    Employee user = getUser();
    teamList = lineTmplService.getTeamList(user.getUserId(), TeamType.Product);
    if (teamList.size() > 0)
      tmplList = lineTmplService.getProductTmplList(
          teamList.get(0).getTeamId(), 0, null);
    destinationList = lineTmplService.roGetDestination();
    itemList = lineTmplService.getProductItems(true);

    currentPage(tmplList.size());
    return INPUT;
  }

  public String query() throws Exception {
    Employee user = getUser();
    tmplList = lineTmplService.getProductTmplList(teamId, itemId, destCode);
    destinationList = lineTmplService.roGetDestination();
    teamList = lineTmplService.getTeamList(user.getUserId(), TeamType.Product);
    itemList = lineTmplService.getProductItems(true);

    currentPage(tmplList.size());
    return SUCCESS;
  }

  public String delete() throws Exception {
    int resu = 0;
    resu = lineTmplService.deleteTmpl(tmplId);
    if (resu < 0) {
      addActionError("删除失败!");
    }

    return SUCCESS;
  }

  @Override
  protected int getMoveCount() {
    return 20;
  }

  @Autowired
  public void setLineTmplService(ProductTmplService lineBookNoticeService) {
    this.lineTmplService = lineBookNoticeService;
  }

  public List<ProductTmpl> getTmplList() {
    return tmplList;
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

  public String getDestCode() {
    return destCode;
  }

  public void setDestCode(String destCode) {
    this.destCode = destCode;
  }

  public int getName() {
    return teamId;
  }

  public void setName(int name) {
    this.teamId = name;
  }

  public List<Destination> getDestinationList() {
    return destinationList;
  }

  public int getItemId() {
    return itemId;
  }

  public void setItemId(int itemId) {
    this.itemId = itemId;
  }

  public List<Team> getTeamList() {
    return teamList;
  }

  public List<ProductItem> getItemList() {
    return itemList;
  }

}
