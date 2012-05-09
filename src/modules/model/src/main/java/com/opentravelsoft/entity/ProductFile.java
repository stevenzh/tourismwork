package com.opentravelsoft.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 产品附带文件
 */
@Entity
@Table(name = "tbl_product_file")
public class ProductFile implements java.io.Serializable {

  private String prodNo;
  private String type;
  private String path;
  private String desc;
  private Byte del;

  public ProductFile() {
  }

  public ProductFile(String prodNo) {
    this.prodNo = prodNo;
  }

  public ProductFile(String prodNo, String type, String path, String desc,
      Byte del) {
    this.prodNo = prodNo;
    this.type = type;
    this.path = path;
    this.desc = desc;
    this.del = del;
  }

  @Id
  @Column(name = "PROD_NO", unique = true, nullable = false, length = 12)
  public String getProdNo() {
    return this.prodNo;
  }

  public void setProdNo(String prodNo) {
    this.prodNo = prodNo;
  }

  @Column(name = "TYPE_", length = 5)
  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Column(name = "PATH_", length = 200)
  public String getPath() {
    return this.path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  @Column(name = "DESC_", length = 45)
  public String getDesc() {
    return this.desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  @Column(name = "DEL")
  public Byte getDel() {
    return this.del;
  }

  public void setDel(Byte del) {
    this.del = del;
  }

}
