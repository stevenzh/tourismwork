package com.opentravelsoft.entity.product;

import java.util.Date;

/**
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:32 $
 */
public class FileItem {
  private int fileId;

  private String fileName;

  private String filePath;

  private Long teamId;

  private Long operator;

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

  public Long getGroupId() {
    return teamId;
  }

  public void setGroupId(Long groupId) {
    this.teamId = groupId;
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
}
