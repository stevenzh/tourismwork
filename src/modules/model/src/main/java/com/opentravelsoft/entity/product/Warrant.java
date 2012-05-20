package com.opentravelsoft.entity.product;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 担保
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 */
public class Warrant {
  private Character warrantFlag;

  private Integer warrantBy;

  private BigDecimal warratMoney;

  private Date warrantDate;

  private Date lastPayDate;

  public Character getWarrantFlag() {
    return warrantFlag;
  }

  public void setWarrantFlag(Character warrantFlag) {
    this.warrantFlag = warrantFlag;
  }

  public Integer getWarrantBy() {
    return warrantBy;
  }

  public void setWarrantBy(Integer warrantBy) {
    this.warrantBy = warrantBy;
  }

  public Date getWarrantDate() {
    return warrantDate;
  }

  public void setWarrantDate(Date warrantDate) {
    this.warrantDate = warrantDate;
  }

  public Date getLastPayDate() {
    return lastPayDate;
  }

  public void setLastPayDate(Date lastPayDate) {
    this.lastPayDate = lastPayDate;
  }

  public BigDecimal getWarratMoney() {
    return warratMoney;
  }

  public void setWarratMoney(BigDecimal warratMoney) {
    this.warratMoney = warratMoney;
  }
}
