package com.opentravelsoft.service.product;

import java.util.List;

import com.opentravelsoft.entity.Category;
import com.opentravelsoft.entity.ProductItem;
import com.opentravelsoft.entity.product.ProductType;

public interface ProductService {

  List<Category> getCategorys();

  List<ProductItem> getProductItems();

  ProductItem getProductItem(int itemId);

  void saveItem(ProductItem item);

  List<ProductItem> getProductItems(ProductType type);

}
