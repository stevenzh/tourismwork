package com.opentravelsoft.entity.finance;

public class ReckoningAcctId implements java.io.Serializable {

  private static final long serialVersionUID = -6571770042115256124L;

  private Long reckoningId;

  private int itemId;

  public ReckoningAcctId() {
  }

  public ReckoningAcctId(Long reckoningId, int itemId) {
    this.reckoningId = reckoningId;
    this.itemId = itemId;
  }

  public Long getReckoningId() {
    return this.reckoningId;
  }

  public void setReckoningId(Long reckoningId) {
    this.reckoningId = reckoningId;
  }

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

    result = 37 * result
        + (reckoningId != null ? this.getReckoningId().intValue() : 0);
    result = 37 * result + this.getItemId();
    return result;
  }

}
