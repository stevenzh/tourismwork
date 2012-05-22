package com.opentravelsoft.providers.product;

import java.util.List;

import com.opentravelsoft.entity.ProductItem;
import com.opentravelsoft.entity.product.ProductType;
import com.opentravelsoft.providers.GenericDao;

public interface ProductItemDao extends GenericDao<ProductItem, Integer> {
  List<ProductItem> getProductItems(boolean tmpl);

  ProductItem getProdutItem(String itemCode);

  List<ProductItem> getProductItems(ProductType type);

}
