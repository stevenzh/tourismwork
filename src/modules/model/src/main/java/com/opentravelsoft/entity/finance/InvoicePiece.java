package com.opentravelsoft.entity.finance;

import java.math.BigDecimal;

/**
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:31 $
 */
public class InvoicePiece {
  private int id;

  private String type;

  private BigDecimal amount;

  public InvoicePiece() {

  }

  public InvoicePiece(String type, BigDecimal expense) {
    this.type = type;
    this.amount = expense;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

}
