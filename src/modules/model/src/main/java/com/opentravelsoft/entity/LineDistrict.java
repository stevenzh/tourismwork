package com.opentravelsoft.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_line_district")
public class LineDistrict implements java.io.Serializable {

  private LineDistrictId id;

  public LineDistrict() {
  }

  public LineDistrict(LineDistrictId id) {
    this.id = id;
  }

  @EmbeddedId
  @AttributeOverrides({
      @AttributeOverride(name = "lineNo", column = @Column(name = "LINE_NO", nullable = false, length = 8)),
      @AttributeOverride(name = "districtNo", column = @Column(name = "DISTRICT_NO", nullable = false, length = 10)) })
  public LineDistrictId getId() {
    return this.id;
  }

  public void setId(LineDistrictId id) {
    this.id = id;
  }

}
