package com.opentravelsoft.action.manage.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Category;
import com.opentravelsoft.entity.ProductItem;
import com.opentravelsoft.service.product.ProductService;

/**
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 */
public class ItemSettingAction extends ManageAction {

  private static final long serialVersionUID = 7671898914387730451L;

  @Autowired
  private ProductService productService;

  private List<Category> categorys;

  private List<ProductItem> items;

  private String category;

  public String input() {
    categorys = productService.getCategorys();
    items = productService.getProductItems();
    return INPUT;
  }

  public List<Category> getCategorys() {
    return categorys;
  }

  public List<ProductItem> getItems() {
    return items;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

}
