package com.opentravelsoft.entity;

public class TeamSupplierId implements java.io.Serializable {

  private static final long serialVersionUID = -6452551624336104238L;

  private long supplierId;

  private long teamId;

  public TeamSupplierId() {
  }

  public TeamSupplierId(long supplierId, long teamId) {
    this.supplierId = supplierId;
    this.teamId = teamId;
  }

  public long getSupplierId() {
    return this.supplierId;
  }

  public void setSupplierId(long supplierId) {
    this.supplierId = supplierId;
  }

  public long getTeamId() {
    return teamId;
  }

  public void setTeamId(long teamId) {
    this.teamId = teamId;
  }

  public boolean equals(Object other) {
    if ((this == other))
      return true;
    if ((other == null))
      return false;
    if (!(other instanceof TeamSupplierId))
      return false;
    TeamSupplierId castOther = (TeamSupplierId) other;

    return (this.getSupplierId() == castOther.getSupplierId())
        && (this.getTeamId() == castOther.getTeamId());
  }

  public int hashCode() {
    int result = 17;

    result = 37 * result + (int) this.getSupplierId();
    result = (int) (37 * result + this.getTeamId());
    return result;
  }
}
