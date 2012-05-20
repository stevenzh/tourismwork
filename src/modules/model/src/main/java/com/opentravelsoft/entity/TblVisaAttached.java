package com.opentravelsoft.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tbl_visa_attached")
public class TblVisaAttached implements java.io.Serializable {

  private Integer visaAttachedId;
  private String recNo;
  private String note;
  private String filePath;
  private Integer fileSize;
  private Date created;
  private Integer createdby;

  public TblVisaAttached() {
  }

  public TblVisaAttached(String recNo) {
    this.recNo = recNo;
  }

  public TblVisaAttached(String recNo, String note, String filePath,
      Integer fileSize, Date created, Integer createdby) {
    this.recNo = recNo;
    this.note = note;
    this.filePath = filePath;
    this.fileSize = fileSize;
    this.created = created;
    this.createdby = createdby;
  }

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "VISA_ATTACHED_ID", unique = true, nullable = false)
  public Integer getVisaAttachedId() {
    return this.visaAttachedId;
  }

  public void setVisaAttachedId(Integer visaAttachedId) {
    this.visaAttachedId = visaAttachedId;
  }

  @Column(name = "REC_NO", nullable = false, length = 10)
  public String getRecNo() {
    return this.recNo;
  }

  public void setRecNo(String recNo) {
    this.recNo = recNo;
  }

  @Column(name = "NOTE", length = 200)
  public String getNote() {
    return this.note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  @Column(name = "FILE_PATH", length = 200)
  public String getFilePath() {
    return this.filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  @Column(name = "FILE_SIZE")
  public Integer getFileSize() {
    return this.fileSize;
  }

  public void setFileSize(Integer fileSize) {
    this.fileSize = fileSize;
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
  public Integer getCreatedby() {
    return this.createdby;
  }

  public void setCreatedby(Integer createdby) {
    this.createdby = createdby;
  }

}
