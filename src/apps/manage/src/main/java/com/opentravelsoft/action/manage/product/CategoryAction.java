package com.opentravelsoft.action.manage.product;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Destination;
import com.opentravelsoft.service.resource.DestinationService;

/**
 * 产品分类
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:03 $
 */
public class CategoryAction extends ManageAction {
  private static final long serialVersionUID = -6773542712338723141L;

  protected static final Log logger = LogFactory.getLog(CategoryAction.class);

  @Autowired
  private DestinationService categoryService;

  private Destination root = new Destination();

  private int catId;

  private Destination category = new Destination();

  private List<Destination> catList = new ArrayList<Destination>();

  @Override
  public String input() {
    List<Destination> deplist = categoryService.roGetAllCategorys();

    root.setDestId(0);
    root.setCnName("顶级分类");

    for (Destination cat : deplist) {
      if (cat.getParent() == null) {
        root.addChildren(cat);
      }
    }
    return INPUT;
  }

  public String detail() {
    category = categoryService.roGetCategory(catId);
    catList = categoryService.roGetAllCategorys();
    Destination boot = new Destination(0, "TOP", "顶级分类");
    catList.add(0, boot);

    return SUCCESS;
  }

  public String submit() {
    if (category.getParent().getDestId() == 0) {
      category.setParent(null);
    }
    categoryService.txSaveCategory(category);
    return SUCCESS;
  }

  public String add() {
    Destination parent = new Destination(category.getDestId(),
        category.getCode(), category.getCnName());
    category = new Destination();
    category.setParent(parent);
    category.setCode(parent.getCode());
    catList = categoryService.roGetAllCategorys();
    Destination boot = new Destination(0, "TOP", "顶级分类");
    catList.add(0, boot);

    return SUCCESS;
  }

  public String del() {
    categoryService.txDelCategorys(category.getDestId());
    return SUCCESS;
  }

  public Destination getTreeRootNode() {
    return root;
  }

  public Destination getCategory() {
    return category;
  }

  public int getCatId() {
    return catId;
  }

  public void setCatId(int catId) {
    this.catId = catId;
  }

  public List<Destination> getCatList() {
    return catList;
  }

}
