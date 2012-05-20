package com.opentravelsoft.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_visa_data")
public class TblVisaData implements java.io.Serializable {

  private Integer itemId;
  private String name;
  private String unit;
  private String description;

  public TblVisaData() {
  }

  public TblVisaData(String name, String unit, String desc) {
    this.name = name;
    this.unit = unit;
    this.description = desc;
  }

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "ITEM_ID", unique = true, nullable = false)
  public Integer getItemId() {
    return this.itemId;
  }

  public void setItemId(Integer itemId) {
    this.itemId = itemId;
  }

  @Column(name = "NAME", length = 50)
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(name = "UNIT", length = 10)
  public String getUnit() {
    return this.unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  @Column(name = "DESC_", length = 50)
  public String getDescription() {
    return this.description;
  }

  public void setDescription(String desc) {
    this.description = desc;
  }

}
