package com.opentravelsoft.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.common.TeamType;
import com.opentravelsoft.entity.Destination;
import com.opentravelsoft.entity.ProductItem;
import com.opentravelsoft.entity.ProductTmpl;
import com.opentravelsoft.entity.TblExpenseTmpl;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.providers.DestinationDao;
import com.opentravelsoft.providers.TeamDao;
import com.opentravelsoft.providers.product.ProductTmplDao;
import com.opentravelsoft.providers.product.ProductItemDao;

@Service("LineTmplService")
public class ProductTmplServiceImpl implements ProductTmplService {

  @Autowired
  private ProductTmplDao lineTmplDao;

  @Autowired
  private DestinationDao categoryDao;

  @Autowired
  private ProductItemDao productItemDao;

  @Autowired
  private TeamDao teamDao;

  public int insertProductTmpl(ProductTmpl alertTbl) {
    return lineTmplDao.insertProductTmpl(alertTbl);
  }

  public int updateProductTmpl(ProductTmpl tmpl) {
    return lineTmplDao.updateProductTmpl(tmpl);
  }

  public ProductTmpl getProductTmpl(int tmplId) {
    return lineTmplDao.getProductTmpl(tmplId);
  }

  public List<ProductTmpl> getProductTmplList(int teamId, int itemId,
      String destCode) {
    return lineTmplDao.getProductTmplByType(teamId, itemId, destCode);
  }

  public int deleteTmpl(int tmplId) {
    return lineTmplDao.deleteTmpl(tmplId);
  }

  public List<Destination> roGetDestination() {
    return categoryDao.getTopDestination();
  }

  public List<TblExpenseTmpl> roGetExpenseTmpl() {
    return lineTmplDao.getExpenseTmpl();
  }

  public List<Team> getTeamList(int userId, TeamType type) {
    return teamDao.getTeam(userId, type);
  }

  public List<ProductItem> getProductItems(boolean tmpl) {
    return productItemDao.getProductItems(tmpl);
  }

  public List<ProductTmpl> getProductTmplList(int teamId, String itemType) {
    return lineTmplDao.getProductTmplByType(teamId, itemType);
  }

}
