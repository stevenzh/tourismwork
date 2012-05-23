package com.opentravelsoft.providers.product;

import java.util.List;

import com.opentravelsoft.entity.ProductTmpl;
import com.opentravelsoft.entity.TblExpenseTmpl;
import com.opentravelsoft.providers.GenericDao;

public interface ProductTmplDao extends GenericDao<ProductTmpl, Integer> {
  /**
   * 
   * @param nameId
   * @return
   */
  public ProductTmpl getProductTmpl(int tmplId);

  /**
   * 
   * @param tmplId
   * @return
   */
  int deleteTmpl(int tmplId);

  /**
   * 
   * @param teamId
   * @param itemId
   * @param destCode
   * @return
   */
  public List<ProductTmpl> getProductTmplByType(int teamId, int itemId,
      String destCode);

  /**
   * 
   * @param tmpl
   * @return
   */
  public int insertProductTmpl(ProductTmpl tmpl);

  /**
   * 
   * @param tmpl
   * @return
   */
  public int updateProductTmpl(ProductTmpl tmpl);

  /**
   * 
   * @return
   */
  public List<TblExpenseTmpl> getExpenseTmpl();

  public List<ProductTmpl> getProductTmplByType(int teamId, String itemType);

}
