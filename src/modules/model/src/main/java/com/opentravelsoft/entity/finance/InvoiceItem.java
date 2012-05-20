package com.opentravelsoft.entity.finance;

import java.math.BigDecimal;

/**
 * 发票细目
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $DatFe: 2009/02/07 14:11:58 $
 */
public class InvoiceItem {
  private int id;

  /** 费用 */
  private BigDecimal expense;

  /** 项目名 */
  private String item;

  public InvoiceItem() {
    id = 0;
    expense = new BigDecimal(0);
    item = "";
  }

  public InvoiceItem(String item, BigDecimal expense) {
    this.expense = expense;
    this.item = item;
  }

  public BigDecimal getExpense() {
    return expense;
  }

  public void setExpense(BigDecimal expense) {
    this.expense = expense;
  }

  public String getItem() {
    return item;
  }

  public void setItem(String item) {
    this.item = item;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

}