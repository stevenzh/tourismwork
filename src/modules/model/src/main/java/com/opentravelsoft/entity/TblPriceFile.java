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
@Table(name = "tbl_price_file")
public class TblPriceFile implements java.io.Serializable {

  private Integer id;
  private int groupId;
  private String filename;
  private String filepath;
  private Integer filesize;
  private Date overdue;
  private String del;
  private String note;
  private Date created;
  private Integer createdBy;

  public TblPriceFile() {
  }

  public TblPriceFile(int groupId, String filename, String filepath) {
    this.groupId = groupId;
    this.filename = filename;
    this.filepath = filepath;
  }

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "ID", unique = true, nullable = false)
  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Column(name = "GROUP_ID", nullable = false)
  public int getGroupId() {
    return this.groupId;
  }

  public void setGroupId(int groupId) {
    this.groupId = groupId;
  }

  @Column(name = "FILENAME", nullable = false, length = 120)
  public String getFilename() {
    return this.filename;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }

  @Column(name = "FILEPATH", nullable = false, length = 120)
  public String getFilepath() {
    return this.filepath;
  }

  public void setFilepath(String filepath) {
    this.filepath = filepath;
  }

  @Column(name = "FILESIZE")
  public Integer getFilesize() {
    return this.filesize;
  }

  public void setFilesize(Integer filesize) {
    this.filesize = filesize;
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
  public String getDel() {
    return this.del;
  }

  public void setDel(String del) {
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
  public Integer getCreatedBy() {
    return this.createdBy;
  }

  public void setCreatedBy(Integer createdBy) {
    this.createdBy = createdBy;
  }

}
