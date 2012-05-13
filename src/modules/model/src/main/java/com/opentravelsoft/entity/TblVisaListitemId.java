package com.opentravelsoft.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TblVisaListitemId implements java.io.Serializable {

  private String nmno;
  private int itemId;

  public TblVisaListitemId() {
  }

  public TblVisaListitemId(String nmno, int itemId) {
    this.nmno = nmno;
    this.itemId = itemId;
  }

  @Column(name = "NMNO", nullable = false, length = 10)
  public String getNmno() {
    return this.nmno;
  }

  public void setNmno(String nmno) {
    this.nmno = nmno;
  }

  @Column(name = "ITEM_ID", nullable = false)
  public int getItemId() {
    return this.itemId;
  }

  public void setItemId(int itemId) {
    this.itemId = itemId;
  }

  public boolean equals(Object other) {
    if ((this == other))
      return true;
    if ((other == null))
      return false;
    if (!(other instanceof TblVisaListitemId))
      return false;
    TblVisaListitemId castOther = (TblVisaListitemId) other;

    return ((this.getNmno() == castOther.getNmno()) || (this.getNmno() != null
        && castOther.getNmno() != null && this.getNmno().equals(
        castOther.getNmno())))
        && (this.getItemId() == castOther.getItemId());
  }

  public int hashCode() {
    int result = 17;

    result = 37 * result + (getNmno() == null ? 0 : this.getNmno().hashCode());
    result = 37 * result + this.getItemId();
    return result;
  }

}
