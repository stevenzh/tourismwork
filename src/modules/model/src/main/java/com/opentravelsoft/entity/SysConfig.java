package com.opentravelsoft.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_sys_config", catalog = "tourismwork_db")
public class SysConfig implements java.io.Serializable {

  private int id;
  private String name;
  private String category;
  private String type;
  private String storeRange;
  private String value;

  public SysConfig() {
    name = "";
    category = "";
    storeRange = "";
  }

  @Id
  @Column(name = "ID", unique = true, nullable = false)
  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Column(name = "NAME", nullable = false, length = 50)
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(name = "CATEGORY", nullable = false, length = 20)
  public String getCategory() {
    return this.category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  @Column(name = "TYPE", nullable = false, length = 16)
  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Column(name = "STORE_RANGE", length = 170)
  public String getStoreRange() {
    return this.storeRange;
  }

  public void setStoreRange(String storeRange) {
    this.storeRange = storeRange;
  }

  @Column(name = "VALUE", length = 189)
  public String getValue() {
    return this.value;
  }

  public void setValue(String value) {
    this.value = value;
  }

}
