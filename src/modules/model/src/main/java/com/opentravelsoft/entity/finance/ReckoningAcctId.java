package com.opentravelsoft.entity.finance;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ReckoningAcctId implements java.io.Serializable {

  private int reckoningId;
  private int itemId;

  public ReckoningAcctId() {
  }

  public ReckoningAcctId(int reckoningId, int itemId) {
    this.reckoningId = reckoningId;
    this.itemId = itemId;
  }

  @Column(name = "RECKONING_ID", nullable = false)
  public int getReckoningId() {
    return this.reckoningId;
  }

  public void setReckoningId(int reckoningId) {
    this.reckoningId = reckoningId;
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
    if (!(other instanceof ReckoningAcctId))
      return false;
    ReckoningAcctId castOther = (ReckoningAcctId) other;

    return (this.getReckoningId() == castOther.getReckoningId())
        && (this.getItemId() == castOther.getItemId());
  }

  public int hashCode() {
    int result = 17;

    result = 37 * result + this.getReckoningId();
    result = 37 * result + this.getItemId();
    return result;
  }

}
