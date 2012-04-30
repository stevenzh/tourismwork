package com.opentravelsoft.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.entity.Line;
import com.opentravelsoft.entity.LineDescription;
import com.opentravelsoft.entity.ProductItem;
import com.opentravelsoft.providers.product.LineTraitDao;
import com.opentravelsoft.providers.product.ProductItemDao;

@Service("RouteTraitService")
public class LineTraitServiceImpl implements LineTraitService {
  
  @Autowired
  private LineTraitDao lineTraitDao;

  @Autowired
  private ProductItemDao productItemDao;

  public List<LineDescription> roGetExpense(String lineNo) {
    return lineTraitDao.getExpense(lineNo);
  }

  public int txSaveExpense(Line route, List<LineDescription> list) {
    return lineTraitDao.saveExpense(route, list);
  }

  public List<LineDescription> getLineTrait(String lineNo, String traitType) {
    return lineTraitDao.getLineTrait(lineNo, traitType);
  }

  public int txSaveLineTrait(List<LineDescription> list, String lineNo,
      String type) {
    return lineTraitDao.saveLineTrait(list, lineNo, type);
  }

  public ProductItem getProductItem(String code) {
    return productItemDao.getProdutItem(code);
  }

}
