package com.opentravelsoft.providers.product;

import java.util.List;

import com.opentravelsoft.entity.ProductTmpl;
import com.opentravelsoft.entity.TblExpenseTmpl;
import com.opentravelsoft.providers.GenericDao;

public interface ProductTmplDao extends GenericDao<ProductTmpl, Long> {
  /**
   * 
   * @param nameId
   * @return
   */
  public ProductTmpl getProductTmpl(long tmplId);

  /**
   * 
   * @param tmplId
   * @return
   */
  int deleteTmpl(long tmplId);

  /**
   * 
   * @param teamId
   * @param itemId
   * @param destCode
   * @return
   */
  public List<ProductTmpl> getProductTmplByType(long teamId, long itemId,
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

  public List<ProductTmpl> getProductTmplByType(long teamId, String itemType);

}
