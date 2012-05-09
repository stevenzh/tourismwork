package com.opentravelsoft.entity;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


/**
 * 系统宣告
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:31 $
 */
public class Announcement extends BaseObject {

  private static final long serialVersionUID = -3417120979257764444L;

  private String recNo;

  private String title;

  private String type;

  private Integer chrnum;

  private String text;

  private Date savingDate;

  private Integer opUser;

  private Date opDate;

  public Announcement() {
  }

  public Announcement(String recNo) {
    this.recNo = recNo;
  }

  public String getRecNo() {
    return this.recNo;
  }

  public void setRecNo(String recNo) {
    this.recNo = recNo;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Integer getChrnum() {
    return this.chrnum;
  }

  public void setChrnum(Integer chrnum) {
    this.chrnum = chrnum;
  }

  public String getText() {
    return this.text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Date getSavingDate() {
    return this.savingDate;
  }

  public void setSavingDate(Date savingDate) {
    this.savingDate = savingDate;
  }

  public Integer getOpUser() {
    return this.opUser;
  }

  public void setOpUser(Integer opUser) {
    this.opUser = opUser;
  }

  public Date getOpDate() {
    return this.opDate;
  }

  public void setOpDate(Date opDate) {
    this.opDate = opDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Announcement)) {
      return false;
    }

    final Announcement announcement = (Announcement) o;
    return this.hashCode() == announcement.hashCode();
  }

  @Override
  public int hashCode() {
    int result;
    result = (recNo != null ? recNo.hashCode() : 0);
    result = 29 * result + (title != null ? title.hashCode() : 0);
    result = 29 * result + (text != null ? text.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
    .append("code", this.recNo)
    .append("title", this.title)
    .append("text", this.text)
    .append("type", this.type).toString();
  }

}
