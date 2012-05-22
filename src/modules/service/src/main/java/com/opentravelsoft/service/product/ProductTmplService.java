package com.opentravelsoft.service.product;

import java.util.List;

import com.opentravelsoft.common.TeamType;
import com.opentravelsoft.entity.Destination;
import com.opentravelsoft.entity.ProductItem;
import com.opentravelsoft.entity.ProductTmpl;
import com.opentravelsoft.entity.TblExpenseTmpl;
import com.opentravelsoft.entity.Team;

public interface ProductTmplService {

  ProductTmpl getProductTmpl(int tmplId);

  int insertProductTmpl(ProductTmpl tmpl);

  int updateProductTmpl(ProductTmpl tmpl);

  int deleteTmpl(int tmplId);

  List<ProductTmpl> getProductTmplList(int teamId, int itemId, String destCode);

  List<Destination> roGetDestination();

  public List<TblExpenseTmpl> roGetExpenseTmpl();

  List<Team> getTeamList(int userId, TeamType type);

  List<ProductItem> getProductItems(boolean tmpl);

  List<ProductTmpl> getProductTmplList(int teamId, String type);

}
