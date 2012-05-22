package com.opentravelsoft.entity;


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_line_sights")
public class TblLineSights implements java.io.Serializable {

  private TblLineSightsId id;
  private Sight sight;

  public TblLineSights() {
  }

  public TblLineSights(TblLineSightsId id, Sight sight) {
    this.id = id;
    this.sight = sight;
  }

  @EmbeddedId
  @AttributeOverrides({
      @AttributeOverride(name = "lineNo", column = @Column(name = "LINE_NO", nullable = false, length = 10)),
      @AttributeOverride(name = "sightNo", column = @Column(name = "SIGHT_NO", nullable = false, length = 10)) })
  public TblLineSightsId getId() {
    return this.id;
  }

  public void setId(TblLineSightsId id) {
    this.id = id;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "SIGHT_NO", nullable = false, insertable = false, updatable = false)
  public Sight getSight() {
    return this.sight;
  }

  public void setSight(Sight sight) {
    this.sight = sight;
  }

}
