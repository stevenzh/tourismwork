package com.opentravelsoft.entity;

import java.util.Date;

public class TblSmsReceive implements java.io.Serializable {

  private static final long serialVersionUID = -895142926560960395L;

  private int id;

  private String seqno;

  private String mobile;

  private String message;

  private Date receiveDate;

  private String reply;

  private Date replyDate;

  private Date updDate;

  private String msgSrc;

  public TblSmsReceive() {
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getSeqno() {
    return this.seqno;
  }

  public void setSeqno(String seqno) {
    this.seqno = seqno;
  }

  public String getMobile() {
    return this.mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Date getReceiveDate() {
    return this.receiveDate;
  }

  public void setReceiveDate(Date receiveDate) {
    this.receiveDate = receiveDate;
  }

  public String getReply() {
    return this.reply;
  }

  public void setReply(String reply) {
    this.reply = reply;
  }

  public Date getReplyDate() {
    return this.replyDate;
  }

  public void setReplyDate(Date replyDate) {
    this.replyDate = replyDate;
  }

  public Date getUpdDate() {
    return this.updDate;
  }

  public void setUpdDate(Date updDate) {
    this.updDate = updDate;
  }

  public String getMsgSrc() {
    return this.msgSrc;
  }

  public void setMsgSrc(String msgSrc) {
    this.msgSrc = msgSrc;
  }

}
