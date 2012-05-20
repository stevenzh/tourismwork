package com.opentravelsoft.entity.product;

import java.util.Date;

import com.opentravelsoft.entity.Group;

/**
 * 通知
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 */
public class Notice {

  private String noticeId;

  /** 主题 */
  private String subject;

  /** 内容 */
  private String text;

  /** 类型 */
  private int type;

  /** 发布开始时间 */
  private Date startingTime;

  /** 发布截止时间 */
  private Date deadline;

  /** 发布部门 */
  private Group group;

  public Notice() {
    group = new Group();
  }

  public String getNoticeId() {
    return noticeId;
  }

  public void setNoticeId(String noticeId) {
    this.noticeId = noticeId;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Date getDeadline() {
    return deadline;
  }

  public void setDeadline(Date deadline) {
    this.deadline = deadline;
  }

  public Date getStartingTime() {
    return startingTime;
  }

  public void setStartingTime(Date startingTime) {
    this.startingTime = startingTime;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public Group getDepartment() {
    return group;
  }

}
