package com.opentravelsoft.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.entity.Category;
import com.opentravelsoft.entity.ProductItem;
import com.opentravelsoft.entity.product.ProductType;
import com.opentravelsoft.providers.CategoryDao;
import com.opentravelsoft.providers.product.ProductItemDao;

@Service("ProductService")
public class ProductServiceImpl implements ProductService {
  private CategoryDao categoryDao;

  private ProductItemDao productItemDao;

  @Autowired
  public void setCategoryDao(CategoryDao categoryDao) {
    this.categoryDao = categoryDao;
  }

  @Autowired
  public void setProductItemDao(ProductItemDao productItemDao) {
    this.productItemDao = productItemDao;
  }

  public List<Category> getCategorys() {
    return categoryDao.getCategory();
  }

  public List<ProductItem> getProductItems() {
    return productItemDao.getProductItems(false);
  }

  public ProductItem getProductItem(long itemId) {
    return productItemDao.get(itemId);
  }

  public void saveItem(ProductItem item) {
    productItemDao.save(item);
  }

  public List<ProductItem> getProductItems(ProductType type) {
    return productItemDao.getProductItems(type);
  }

}
