package com.opentravelsoft.entity;

import java.util.Date;

public class TblPriceFile implements java.io.Serializable {

  private static final long serialVersionUID = -4676532699934518524L;

  private int id;

  private Long teamId;

  private String filename;

  private String filepath;

  private Integer filesize;

  private Date created;

  private Long createdby;

  private Date overdue;

  private String del;

  private String note;

  public TblPriceFile() {
    this.teamId = 0L;
  }

  public TblPriceFile(int id, String filename, String filepath) {
    this();
    this.id = id;
    this.filename = filename;
    this.filepath = filepath;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Long getGroupId() {
    return this.teamId;
  }

  public void setGroupId(Long groupId) {
    this.teamId = groupId;
  }

  public String getFilename() {
    return this.filename;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }

  public String getFilepath() {
    return this.filepath;
  }

  public void setFilepath(String filepath) {
    this.filepath = filepath;
  }

  public Integer getFilesize() {
    return this.filesize;
  }

  public void setFilesize(Integer filesize) {
    this.filesize = filesize;
  }

  public Date getCreated() {
    return this.created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Long getCreatedBy() {
    return this.createdby;
  }

  public void setCreatedBy(Long createdby) {
    this.createdby = createdby;
  }

  public Date getOverdue() {
    return this.overdue;
  }

  public void setOverdue(Date overdue) {
    this.overdue = overdue;
  }

  public String getDel() {
    return this.del;
  }

  public void setDel(String del) {
    this.del = del;
  }

  public String getNote() {
    return this.note;
  }

  public void setNote(String note) {
    this.note = note;
  }

}
