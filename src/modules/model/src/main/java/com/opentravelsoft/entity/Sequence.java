package com.opentravelsoft.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_sequence")
public class Sequence implements java.io.Serializable {

  private SequenceId id;
  private Integer groupId;
  private String regPlcd;
  private Integer cptno;

  public Sequence() {
  }

  public Sequence(SequenceId id) {
    this.id = id;
  }

  public Sequence(SequenceId id, Integer groupId, String regPlcd,
      Integer cptno) {
    this.id = id;
    this.groupId = groupId;
    this.regPlcd = regPlcd;
    this.cptno = cptno;
  }

  @EmbeddedId
  @AttributeOverrides({
      @AttributeOverride(name = "rectype", column = @Column(name = "RECTYPE", nullable = false, length = 1)),
      @AttributeOverride(name = "year", column = @Column(name = "YEAR", nullable = false, length = 4)),
      @AttributeOverride(name = "month", column = @Column(name = "MONTH", nullable = false, length = 2)) })
  public SequenceId getId() {
    return this.id;
  }

  public void setId(SequenceId id) {
    this.id = id;
  }

  @Column(name = "GROUP_ID")
  public Integer getGroupId() {
    return this.groupId;
  }

  public void setGroupId(Integer groupId) {
    this.groupId = groupId;
  }

  @Column(name = "REG_PLCD", length = 1)
  public String getRegPlcd() {
    return this.regPlcd;
  }

  public void setRegPlcd(String regPlcd) {
    this.regPlcd = regPlcd;
  }

  @Column(name = "CPTNO")
  public Integer getCptno() {
    return this.cptno;
  }

  public void setCptno(Integer cptno) {
    this.cptno = cptno;
  }

}
