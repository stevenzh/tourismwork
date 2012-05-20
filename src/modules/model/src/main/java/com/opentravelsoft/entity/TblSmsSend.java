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
@Table(name = "tbl_sms_send")
public class TblSmsSend implements java.io.Serializable {

  private Integer id;
  private String message;
  private Integer sendCnt;
  private Integer receivedCnt;
  private Character status;
  private Date sendDate;
  private Integer opUser;

  public TblSmsSend() {
  }

  public TblSmsSend(Date sendDate) {
    this.sendDate = sendDate;
  }

  public TblSmsSend(String message, Integer sendCnt, Integer receivedCnt,
      Character status, Date sendDate, Integer opUser) {
    this.message = message;
    this.sendCnt = sendCnt;
    this.receivedCnt = receivedCnt;
    this.status = status;
    this.sendDate = sendDate;
    this.opUser = opUser;
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

  @Column(name = "MESSAGE", length = 200)
  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Column(name = "SEND_CNT")
  public Integer getSendCnt() {
    return this.sendCnt;
  }

  public void setSendCnt(Integer sendCnt) {
    this.sendCnt = sendCnt;
  }

  @Column(name = "RECEIVED_CNT")
  public Integer getReceivedCnt() {
    return this.receivedCnt;
  }

  public void setReceivedCnt(Integer receivedCnt) {
    this.receivedCnt = receivedCnt;
  }

  @Column(name = "STATUS", length = 1)
  public Character getStatus() {
    return this.status;
  }

  public void setStatus(Character status) {
    this.status = status;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "SEND_DATE", nullable = false, length = 19)
  public Date getSendDate() {
    return this.sendDate;
  }

  public void setSendDate(Date sendDate) {
    this.sendDate = sendDate;
  }

  @Column(name = "OP_USER")
  public Integer getOpUser() {
    return this.opUser;
  }

  public void setOpUser(Integer opUser) {
    this.opUser = opUser;
  }

  private String seqno;

  private String mobile;

  private Long opId;

  private String stat;

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

  public Long getOpId() {
    return this.opId;
  }

  public void setOpId(Long opId) {
    this.opId = opId;
  }

  public String getStat() {
    return this.stat;
  }

  public void setStat(String stat) {
    this.stat = stat;
  }

}
