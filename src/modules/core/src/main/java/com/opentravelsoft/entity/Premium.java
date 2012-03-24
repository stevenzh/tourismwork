package com.opentravelsoft.entity;

import java.util.Date;

import com.opentravelsoft.model.BaseObject;

/**
 * 保险信息
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:32 $
 */
public class Premium extends BaseObject {

  private static final long serialVersionUID = 4770776705727526727L;

  private String precode;

  private Double prem;

  private Integer preday;

  private Double ywpre;

  private Double ylpre;

  private Double bcpre;

  private Double clpre;

  private Double hkpre;

  private Date dodate;

  private String note;

  private String del;

  private Date created;

  private Long createdBy;

  private Date updated;

  private Long updatedBy;

  public Premium() {
    this.del = "N";
  }

  public Premium(String precode) {
    this();
    this.precode = precode;
  }

  public String getPrecode() {
    return this.precode;
  }

  public void setPrecode(String precode) {
    this.precode = precode;
  }

  public Double getPrem() {
    return this.prem;
  }

  public void setPrem(Double prem) {
    this.prem = prem;
  }

  public Integer getPreday() {
    return this.preday;
  }

  public void setPreday(Integer preday) {
    this.preday = preday;
  }

  public Double getYwpre() {
    return this.ywpre;
  }

  public void setYwpre(Double ywpre) {
    this.ywpre = ywpre;
  }

  public Double getYlpre() {
    return this.ylpre;
  }

  public void setYlpre(Double ylpre) {
    this.ylpre = ylpre;
  }

  public Double getBcpre() {
    return this.bcpre;
  }

  public void setBcpre(Double bcpre) {
    this.bcpre = bcpre;
  }

  public Double getClpre() {
    return this.clpre;
  }

  public void setClpre(Double clpre) {
    this.clpre = clpre;
  }

  public Double getHkpre() {
    return this.hkpre;
  }

  public void setHkpre(Double hkpre) {
    this.hkpre = hkpre;
  }

  public Date getDodate() {
    return this.dodate;
  }

  public void setDodate(Date dodate) {
    this.dodate = dodate;
  }

  public String getNote() {
    return this.note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public String getDel() {
    return this.del;
  }

  public void setDel(String del) {
    this.del = del;
  }

  public Date getCreated() {
    return this.created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Long getCreatedBy() {
    return this.createdBy;
  }

  public void setCreatedBy(Long createdBy) {
    this.createdBy = createdBy;
  }

  public Date getUpdated() {
    return this.updated;
  }

  public void setUpdated(Date updated) {
    this.updated = updated;
  }

  public Long getUpdatedBy() {
    return this.updatedBy;
  }

  public void setUpdatedBy(Long updatedby) {
    this.updatedBy = updatedby;
  }

  @Override
  public String toString() {
    return null;
  }

  @Override
  public boolean equals(Object o) {
    return false;
  }

  @Override
  public int hashCode() {
    return 0;
  }

}
