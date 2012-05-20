package com.opentravelsoft.entity.out;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_line_country")
public class TblLineCountry implements java.io.Serializable {

  private TblLineCountryId id;

  public TblLineCountry() {
  }

  public TblLineCountry(TblLineCountryId id) {
    this.id = id;
  }

  @EmbeddedId
  @AttributeOverrides({
      @AttributeOverride(name = "lineNo", column = @Column(name = "LINE_NO", nullable = false, length = 8)),
      @AttributeOverride(name = "countryCd", column = @Column(name = "COUNTRY_CD", nullable = false, length = 2)) })
  public TblLineCountryId getId() {
    return this.id;
  }

  public void setId(TblLineCountryId id) {
    this.id = id;
  }

}
