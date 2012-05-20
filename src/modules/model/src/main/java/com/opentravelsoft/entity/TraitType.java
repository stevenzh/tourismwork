package com.opentravelsoft.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_trait_type")
public class TraitType implements java.io.Serializable {

  private Integer traitId;
  private String name;

  public TraitType() {
  }

  public TraitType(String name) {
    this.name = name;
  }

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "TRAIT_ID", unique = true, nullable = false)
  public Integer getTraitId() {
    return this.traitId;
  }

  public void setTraitId(Integer traitId) {
    this.traitId = traitId;
  }

  @Column(name = "NAME", length = 50)
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
