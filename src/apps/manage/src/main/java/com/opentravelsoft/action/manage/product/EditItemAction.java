package com.opentravelsoft.action.manage.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Category;
import com.opentravelsoft.entity.ProductItem;
import com.opentravelsoft.service.product.ProductService;

/**
 * 在线路制作是修改景点信息
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:54 $
 */
public class EditItemAction extends ManageAction {
  private static final long serialVersionUID = 7671898914387730451L;

  private ProductService productService;

  private int itemId;

  private String category;

  private List<Category> categorys;

  private ProductItem item;

  @Autowired
  public void setProductService(ProductService productService) {
    this.productService = productService;
  }

  public String input() {
    categorys = productService.getCategorys();
    item = productService.getProductItem(itemId);
    return INPUT;
  }

  public String submit() {
    productService.saveItem(item);
    return SUCCESS;
  }

  public List<Category> getCategorys() {
    return categorys;
  }

  public ProductItem getItem() {
    return item;
  }

  public void setItem(ProductItem item) {
    this.item = item;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public int getItemId() {
    return itemId;
  }

  public void setItemId(int itemId) {
    this.itemId = itemId;
  }

}
