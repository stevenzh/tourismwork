package com.opentravelsoft.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_team_customer", catalog = "tourismwork_db")
public class TeamSupplier implements java.io.Serializable {

  private TeamSupplierId id;

  public TeamSupplier() {
  }

  public TeamSupplier(TeamSupplierId id) {
    this.id = id;
  }

  @EmbeddedId
  @AttributeOverrides({
      @AttributeOverride(name = "customerId", column = @Column(name = "CUSTOMER_ID", nullable = false)),
      @AttributeOverride(name = "teamId", column = @Column(name = "TEAM_ID", nullable = false)) })
  public TeamSupplierId getId() {
    return this.id;
  }

  public void setId(TeamSupplierId id) {
    this.id = id;
  }

}
