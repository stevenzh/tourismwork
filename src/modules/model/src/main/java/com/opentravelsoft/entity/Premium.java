package com.opentravelsoft.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tbl_cpic_prem")
public class Premium implements java.io.Serializable {

  private String precode;
  private BigDecimal prem;
  private Integer preday;
  private BigDecimal ywpre;
  private BigDecimal ylpre;
  private BigDecimal bcpre;
  private BigDecimal clpre;
  private BigDecimal hkpre;
  private Date dodate;
  private String note;
  private String del;
  private Date created;
  private Integer createdBy;
  private Date updated;
  private Integer updatedBy;

  public Premium() {
  }

  public Premium(String precode, Date updated) {
    this.precode = precode;
    this.updated = updated;
  }

  @Id
  @Column(name = "PRECODE", unique = true, nullable = false, length = 8)
  public String getPrecode() {
    return this.precode;
  }

  public void setPrecode(String precode) {
    this.precode = precode;
  }

  @Column(name = "PREM", precision = 16)
  public BigDecimal getPrem() {
    return this.prem;
  }

  public void setPrem(BigDecimal prem) {
    this.prem = prem;
  }

  @Column(name = "PREDAY")
  public Integer getPreday() {
    return this.preday;
  }

  public void setPreday(Integer preday) {
    this.preday = preday;
  }

  @Column(name = "YWPRE", precision = 10, scale = 3)
  public BigDecimal getYwpre() {
    return this.ywpre;
  }

  public void setYwpre(BigDecimal ywpre) {
    this.ywpre = ywpre;
  }

  @Column(name = "YLPRE", precision = 10, scale = 3)
  public BigDecimal getYlpre() {
    return this.ylpre;
  }

  public void setYlpre(BigDecimal ylpre) {
    this.ylpre = ylpre;
  }

  @Column(name = "BCPRE", precision = 10, scale = 3)
  public BigDecimal getBcpre() {
    return this.bcpre;
  }

  public void setBcpre(BigDecimal bcpre) {
    this.bcpre = bcpre;
  }

  @Column(name = "CLPRE", precision = 10, scale = 3)
  public BigDecimal getClpre() {
    return this.clpre;
  }

  public void setClpre(BigDecimal clpre) {
    this.clpre = clpre;
  }

  @Column(name = "HKPRE", precision = 10, scale = 3)
  public BigDecimal getHkpre() {
    return this.hkpre;
  }

  public void setHkpre(BigDecimal hkpre) {
    this.hkpre = hkpre;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "DODATE", length = 19)
  public Date getDodate() {
    return this.dodate;
  }

  public void setDodate(Date dodate) {
    this.dodate = dodate;
  }

  @Column(name = "NOTE", length = 200)
  public String getNote() {
    return this.note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  @Column(name = "DEL", length = 1)
  public String getDel() {
    return this.del;
  }

  public void setDel(String del) {
    this.del = del;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "CREATED", length = 19)
  public Date getCreated() {
    return this.created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  @Column(name = "CREATEDBY")
  public Integer getCreatedBy() {
    return this.createdBy;
  }

  public void setCreatedBy(Integer createdBy) {
    this.createdBy = createdBy;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "UPDATED", nullable = false, length = 19)
  public Date getUpdated() {
    return this.updated;
  }

  public void setUpdated(Date updated) {
    this.updated = updated;
  }

  @Column(name = "UPDATEDBY")
  public Integer getUpdatedBy() {
    return this.updatedBy;
  }

  public void setUpdatedBy(Integer updatedBy) {
    this.updatedBy = updatedBy;
  }

}
