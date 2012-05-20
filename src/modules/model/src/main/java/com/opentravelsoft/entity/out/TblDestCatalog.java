package com.opentravelsoft.entity.out;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_dest_catalog")
public class TblDestCatalog implements java.io.Serializable {

  private String type;
  private String name;

  public TblDestCatalog() {
  }

  public TblDestCatalog(String type) {
    this.type = type;
  }

  public TblDestCatalog(String type, String name) {
    this.type = type;
    this.name = name;
  }

  @Id
  @Column(name = "TYPE", unique = true, nullable = false, length = 6)
  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Column(name = "NAME", length = 20)
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
