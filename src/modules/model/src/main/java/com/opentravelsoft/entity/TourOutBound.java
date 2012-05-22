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
@Table(name = "tbl_tour_outbound")
public class TourOutBound implements java.io.Serializable {

  private Integer id;
  private String planNo;
  private String type;
  private String text1;
  private String text2;
  private String text3;
  private String text4;
  private String text5;
  private Integer opuser;
  private Date opdate;

  public TourOutBound() {
  }

  public TourOutBound(String planNo, String type, Date opdate) {
    this.planNo = planNo;
    this.type = type;
    this.opdate = opdate;
  }

  public TourOutBound(String planNo, String type, String text1, String text2,
      String text3, String text4, String text5, Integer opuser, Date opdate) {
    this.planNo = planNo;
    this.type = type;
    this.text1 = text1;
    this.text2 = text2;
    this.text3 = text3;
    this.text4 = text4;
    this.text5 = text5;
    this.opuser = opuser;
    this.opdate = opdate;
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

  @Column(name = "PLAN_NO", nullable = false, length = 20)
  public String getPlanNo() {
    return this.planNo;
  }

  public void setPlanNo(String planNo) {
    this.planNo = planNo;
  }

  @Column(name = "TYPE", nullable = false, length = 50)
  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Column(name = "TEXT1", length = 100)
  public String getText1() {
    return this.text1;
  }

  public void setText1(String text1) {
    this.text1 = text1;
  }

  @Column(name = "TEXT2", length = 500)
  public String getText2() {
    return this.text2;
  }

  public void setText2(String text2) {
    this.text2 = text2;
  }

  @Column(name = "TEXT3")
  public String getText3() {
    return this.text3;
  }

  public void setText3(String text3) {
    this.text3 = text3;
  }

  @Column(name = "TEXT4", length = 400)
  public String getText4() {
    return this.text4;
  }

  public void setText4(String text4) {
    this.text4 = text4;
  }

  @Column(name = "TEXT5", length = 400)
  public String getText5() {
    return this.text5;
  }

  public void setText5(String text5) {
    this.text5 = text5;
  }

  @Column(name = "OPUSER")
  public Integer getOpuser() {
    return this.opuser;
  }

  public void setOpuser(Integer opuser) {
    this.opuser = opuser;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "OPDATE", nullable = false, length = 19)
  public Date getOpdate() {
    return this.opdate;
  }

  public void setOpdate(Date opdate) {
    this.opdate = opdate;
  }

  private String tourNo;

  private String showStr;

  private String showId;

  private String opUserName;

  @Transient
  public String getTourNo() {
    return tourNo;
  }

  public void setTourNo(String tourNo) {
    this.tourNo = tourNo;
  }

  @Transient
  public String getShowStr() {
    return showStr;
  }

  public void setShowStr(String showStr) {
    this.showStr = showStr;
  }

  @Transient
  public String getShowId() {
    return showId;
  }

  public void setShowId(String showId) {
    this.showId = showId;
  }

  @Transient
  public String getOpUserName() {
    return opUserName;
  }

  public void setOpUserName(String opUserName) {
    this.opUserName = opUserName;
  }
}
