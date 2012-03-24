package com.opentravelsoft.entity;

import java.util.Date;

/**
 * 出团通知文件
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:32 $
 */
public class TourNoticeFile implements java.io.Serializable {

  private static final long serialVersionUID = 2909794328912152564L;

  private long fileId;

  private String tourNo;

  private String fileName;

  private String filePath;

  private long fileSize;

  private String groupId;

  private Long operator;

  private String note;

  private Date created;

  private String delKey;

  private Integer id;

  private String planNo;

  private String dptCd;

  private String filename;

  private String filepath;

  private Integer filesize;

  private Long createdby;

  private Date overdue;

  private String del;

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getPlanNo() {
    return planNo;
  }

  public void setPlanNo(String planNo) {
    this.planNo = planNo;
  }

  public String getDptCd() {
    return this.dptCd;
  }

  public void setDptCd(String dptCd) {
    this.dptCd = dptCd;
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

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public long getFileId() {
    return fileId;
  }

  public void setFileId(long fileId) {
    this.fileId = fileId;
  }

  public String getDptNo() {
    return groupId;
  }

  public void setDptNo(String groupId) {
    this.groupId = groupId;
  }

  public Long getOperator() {
    return operator;
  }

  public void setOperator(Long operator) {
    this.operator = operator;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public String getNote() {
    return note;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public String getTourNo() {
    return tourNo;
  }

  public void setTourNo(String tourNo) {
    this.tourNo = tourNo;
  }

  public long getFileSize() {
    return fileSize;
  }

  public void setFileSize(long fileSize) {
    this.fileSize = fileSize;
  }

  public String getDelKey() {
    return delKey;
  }

  public void setDelKey(String delKey) {
    this.delKey = delKey;
  }

}
