package com.opentravelsoft.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_plan_price")
public class PlanPrice implements java.io.Serializable {

  private Integer id;
  /** 计划编号 */
  private String planNo;
  private String priceNo;

  public PlanPrice() {
    defaultValue = false;
  }

  public PlanPrice(String planNo, String priceNo) {
    this();

    this.planNo = planNo;
    this.priceNo = priceNo;
  }

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "ID", unique = true, nullable = false)
  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Column(name = "PLAN_NO", nullable = false, length = 10)
  public String getPlanNo() {
    return this.planNo;
  }

  public void setPlanNo(String planNo) {
    this.planNo = planNo;
  }

  @Column(name = "PRICE_NO", nullable = false, length = 10)
  public String getPriceNo() {
    return this.priceNo;
  }

  public void setPriceNo(String priceNo) {
    this.priceNo = priceNo;
  }

  private int idx;

  private boolean defaultValue;

  public int getIdx() {
    return idx;
  }

  public void setIdx(int idx) {
    this.idx = idx;
  }

  public boolean getDefaultValue() {
    return defaultValue;
  }

  public void setDefaultValue(boolean defaultValue) {
    this.defaultValue = defaultValue;
  }

}
