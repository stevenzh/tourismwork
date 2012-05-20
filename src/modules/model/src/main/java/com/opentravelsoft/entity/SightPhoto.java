package com.opentravelsoft.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_sight_photo")
public class SightPhoto implements java.io.Serializable {

  private String recNo;
  private String sightNo;
  private String mapAddress;
  private String note;

  public SightPhoto() {
  }

  public SightPhoto(String recNo) {
    this.recNo = recNo;
  }

  public SightPhoto(String recNo, String sightNo, String mapAddress, String note) {
    this.recNo = recNo;
    this.sightNo = sightNo;
    this.mapAddress = mapAddress;
    this.note = note;
  }

  @Id
  @Column(name = "REC_NO", unique = true, nullable = false, length = 10)
  public String getRecNo() {
    return this.recNo;
  }

  public void setRecNo(String recNo) {
    this.recNo = recNo;
  }

  @Column(name = "SIGHT_NO", length = 10)
  public String getSightNo() {
    return this.sightNo;
  }

  public void setSightNo(String sightNo) {
    this.sightNo = sightNo;
  }

  @Column(name = "MAP_ADDRESS", length = 50)
  public String getMapAddress() {
    return this.mapAddress;
  }

  public void setMapAddress(String mapAddress) {
    this.mapAddress = mapAddress;
  }

  @Column(name = "NOTE", length = 200)
  public String getNote() {
    return this.note;
  }

  public void setNote(String note) {
    this.note = note;
  }

}
