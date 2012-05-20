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
@Table(name = "tbl_announce")
public class Announcement implements java.io.Serializable {

  private Integer announceId;
  private String title;
  private Character type;
  private Integer chrnum;
  private String text;
  private Date savingDate;
  private Integer opUser;
  private Date opDate;

  public Announcement() {
  }

  public Announcement(String title, Date opDate) {
    this.title = title;
    this.opDate = opDate;
  }

  public Announcement(String title, Character type, Integer chrnum,
      String text, Date savingDate, Integer opUser, Date opDate) {
    this.title = title;
    this.type = type;
    this.chrnum = chrnum;
    this.text = text;
    this.savingDate = savingDate;
    this.opUser = opUser;
    this.opDate = opDate;
  }

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "ANNOUNCE_ID", unique = true, nullable = false)
  public Integer getAnnounceId() {
    return this.announceId;
  }

  public void setAnnounceId(Integer announceId) {
    this.announceId = announceId;
  }

  @Column(name = "TITLE", nullable = false, length = 80)
  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Column(name = "TYPE", length = 1)
  public Character getType() {
    return this.type;
  }

  public void setType(Character type) {
    this.type = type;
  }

  @Column(name = "CHRNUM")
  public Integer getChrnum() {
    return this.chrnum;
  }

  public void setChrnum(Integer chrnum) {
    this.chrnum = chrnum;
  }

  @Column(name = "TEXT", length = 3000)
  public String getText() {
    return this.text;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "SAVING_DATE", length = 19)
  public Date getSavingDate() {
    return this.savingDate;
  }

  public void setSavingDate(Date savingDate) {
    this.savingDate = savingDate;
  }

  @Column(name = "OP_USER")
  public Integer getOpUser() {
    return this.opUser;
  }

  public void setOpUser(Integer opUser) {
    this.opUser = opUser;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "OP_DATE", nullable = false, length = 19)
  public Date getOpDate() {
    return this.opDate;
  }

  public void setOpDate(Date opDate) {
    this.opDate = opDate;
  }
}
