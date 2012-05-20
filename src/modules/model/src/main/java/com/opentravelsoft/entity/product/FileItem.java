package com.opentravelsoft.entity.product;

import java.util.Date;

/**
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 */
public class FileItem {
  private int fileId;

  private String fileName;

  private String filePath;

  private Integer teamId;

  private Integer operator;

  private String note;

  private Date created;

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

  public int getFileId() {
    return fileId;
  }

  public void setFileId(int fileId) {
    this.fileId = fileId;
  }

  public Integer getGroupId() {
    return teamId;
  }

  public void setGroupId(Integer groupId) {
    this.teamId = groupId;
  }

  public Integer getOperator() {
    return operator;
  }

  public void setOperator(Integer operator) {
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
}
