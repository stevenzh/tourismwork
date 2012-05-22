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
import javax.persistence.Transient;

@Entity
@Table(name = "tbl_tournotice_file")
public class TourNoticeFile implements java.io.Serializable {

  private Integer fileId;
  private String tourNo;
  private String planNo;
  private String dptCd;
  private String fileName;
  private String filePath;
  private Long fileSize;
  private Date overdue;
  private Character del;
  private String note;
  private Date created;
  private Integer createdby;

  public TourNoticeFile() {
  }

  public TourNoticeFile(String tourNo, String filename, String filepath) {
    this.tourNo = tourNo;
    this.fileName = filename;
    this.filePath = filepath;
  }

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "ID", unique = true, nullable = false)
  public Integer getFileId() {
    return this.fileId;
  }

  public void setFileId(Integer id) {
    this.fileId = id;
  }

  @Column(name = "TOUR_NO", nullable = false, length = 100)
  public String getTourNo() {
    return this.tourNo;
  }

  public void setTourNo(String tourNo) {
    this.tourNo = tourNo;
  }

  @Column(name = "PLAN_NO", length = 20)
  public String getPlanNo() {
    return this.planNo;
  }

  public void setPlanNo(String planNo) {
    this.planNo = planNo;
  }

  @Column(name = "DPT_CD", length = 4)
  public String getDptCd() {
    return this.dptCd;
  }

  public void setDptCd(String dptCd) {
    this.dptCd = dptCd;
  }

  @Column(name = "FILENAME", nullable = false, length = 120)
  public String getFileName() {
    return this.fileName;
  }

  public void setFileName(String filename) {
    this.fileName = filename;
  }

  @Column(name = "FILEPATH", nullable = false, length = 120)
  public String getFilePath() {
    return this.filePath;
  }

  public void setFilePath(String filepath) {
    this.filePath = filepath;
  }

  @Column(name = "FILESIZE")
  public Long getFileSize() {
    return this.fileSize;
  }

  public void setFileSize(Long filesize) {
    this.fileSize = filesize;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "OVERDUE", length = 19)
  public Date getOverdue() {
    return this.overdue;
  }

  public void setOverdue(Date overdue) {
    this.overdue = overdue;
  }

  @Column(name = "DEL", length = 1)
  public Character getDel() {
    return this.del;
  }

  public void setDel(Character del) {
    this.del = del;
  }

  @Column(name = "NOTE", length = 60)
  public String getNote() {
    return this.note;
  }

  public void setNote(String note) {
    this.note = note;
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

  private String groupId;

  private Integer operator;

  private String delKey;

  @Transient
  public String getDptNo() {
    return groupId;
  }

  public void setDptNo(String groupId) {
    this.groupId = groupId;
  }

  @Transient
  public Integer getOperator() {
    return operator;
  }

  public void setOperator(Integer operator) {
    this.operator = operator;
  }

  @Transient
  public String getDelKey() {
    return delKey;
  }

  public void setDelKey(String delKey) {
    this.delKey = delKey;
  }

}
