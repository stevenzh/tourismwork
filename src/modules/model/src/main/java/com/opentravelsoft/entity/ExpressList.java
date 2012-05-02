package com.opentravelsoft.entity;

import com.opentravelsoft.model.BaseObject;

/**
 * 配送单明细
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.2 $ $Date: 2009/04/10 07:47:28 $
 */
public class ExpressList extends BaseObject {
  private static final long serialVersionUID = 6376338227820622785L;

  private int itemId;

  private String expressId;

  private String itemName;

  private String note;

  private Integer itemNum;

  private String expressType;

  private String unit;

  public ExpressList() {
  }

  public ExpressList(int itemId) {
    this.itemId = itemId;
  }

  public int getItemId() {
    return this.itemId;
  }

  public void setItemId(int itemId) {
    this.itemId = itemId;
  }

  public String getExpressId() {
    return this.expressId;
  }

  public void setExpressId(String expressId) {
    this.expressId = expressId;
  }

  public String getItemName() {
    return this.itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  public String getNote() {
    return this.note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public Integer getItemNum() {
    return this.itemNum;
  }

  public void setItemNum(Integer itemNum) {
    this.itemNum = itemNum;
  }

  public String getExpressType() {
    return this.expressType;
  }

  public void setExpressType(String expressType) {
    this.expressType = expressType;
  }

  public String getUnit() {
    return this.unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  @Override
  public String toString() {
    return null;
  }

  @Override
  public boolean equals(Object o) {
    return false;
  }

  @Override
  public int hashCode() {
    return 0;
  }

}
