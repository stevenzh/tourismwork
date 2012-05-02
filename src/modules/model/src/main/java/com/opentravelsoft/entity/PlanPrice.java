package com.opentravelsoft.entity;

import com.opentravelsoft.model.BaseObject;

/**
 * 出团航班信息
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:32 $
 */
public class PlanPrice extends BaseObject {

  private static final long serialVersionUID = 5240784419494487667L;

  private long id;

  private int idx;

  private boolean defaultValue;

  /** 计划编号 */
  private String planNo;

  private String priceNo;

  public PlanPrice() {
    defaultValue = false;
  }

  public PlanPrice(String planNo, String priceNo) {
    this();
    this.priceNo = priceNo;
    this.planNo = planNo;
  }

  public int getIdx() {
    return idx;
  }

  public void setIdx(int idx) {
    this.idx = idx;
  }

  public String getPlanNo() {
    return this.planNo;
  }

  public void setPlanNo(String planNo) {
    this.planNo = planNo;
  }

  public String getPriceNo() {
    return priceNo;
  }

  public void setPriceNo(String priceNo) {
    this.priceNo = priceNo;
  }

  public boolean getDefaultValue() {
    return defaultValue;
  }

  public void setDefaultValue(boolean defaultValue) {
    this.defaultValue = defaultValue;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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
