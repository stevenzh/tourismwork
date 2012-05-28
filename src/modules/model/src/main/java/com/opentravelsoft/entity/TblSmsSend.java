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
@Table(name = "tbl_sms_send")
public class TblSmsSend implements java.io.Serializable {

  private Integer id;
  private String message;
  private Integer sendCnt;
  private Integer reservedCnt;
  private String status;
  private Date sendDate;
  private Integer opUser;

  public TblSmsSend() {
  }

  public TblSmsSend(Date sendDate) {
    this.sendDate = sendDate;
  }

  public TblSmsSend(String message, Integer sendCnt, Integer reservedCnt,
      String status, Date sendDate, Integer opUser) {
    this.message = message;
    this.sendCnt = sendCnt;
    this.reservedCnt = reservedCnt;
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
    return this.reservedCnt;
  }

  public void setReceivedCnt(Integer reservedCnt) {
    this.reservedCnt = reservedCnt;
  }

  @Column(name = "STATUS", length = 1)
  public String getStatus() {
    return this.status;
  }

  public void setStatus(String status) {
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

  private Integer opId;

  private String stat;

  @Transient
  public String getSeqno() {
    return this.seqno;
  }

  public void setSeqno(String seqno) {
    this.seqno = seqno;
  }

  @Transient
  public String getMobile() {
    return this.mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  @Transient
  public Integer getOpId() {
    return this.opId;
  }

  public void setOpId(Integer opId) {
    this.opId = opId;
  }

  @Transient
  public String getStat() {
    return this.stat;
  }

  public void setStat(String stat) {
    this.stat = stat;
  }

}
