package com.opentravelsoft.entity.vacation;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 酒店图片
 */
@Entity
@Table(name = "tbl_hotel_file")
public class HotelFile implements java.io.Serializable {

  private String recNo;
  private String hotCd;
  private String mapAddress;
  private String note;
  private Byte isDelete;
  private String crUser;
  private Date crDate;
  private String opUser;
  private Date opDate;

  public HotelFile() {
  }

  public HotelFile(String recNo) {
    this.recNo = recNo;
  }

  public HotelFile(String recNo, String hotCd, String mapAddress, String note,
      Byte isDelete, String crUser, Date crDate, String opUser, Date opDate) {
    this.recNo = recNo;
    this.hotCd = hotCd;
    this.mapAddress = mapAddress;
    this.note = note;
    this.isDelete = isDelete;
    this.crUser = crUser;
    this.crDate = crDate;
    this.opUser = opUser;
    this.opDate = opDate;
  }

  @Id
  @Column(name = "REC_NO", unique = true, nullable = false, length = 10)
  public String getRecNo() {
    return this.recNo;
  }

  public void setRecNo(String recNo) {
    this.recNo = recNo;
  }

  @Column(name = "HOT_CD", length = 10)
  public String getHotCd() {
    return this.hotCd;
  }

  public void setHotCd(String hotCd) {
    this.hotCd = hotCd;
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

  @Column(name = "IS_DELETE")
  public Byte getIsDelete() {
    return this.isDelete;
  }

  public void setIsDelete(Byte isDelete) {
    this.isDelete = isDelete;
  }

  @Column(name = "CR_USER", length = 50)
  public String getCrUser() {
    return this.crUser;
  }

  public void setCrUser(String crUser) {
    this.crUser = crUser;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "CR_DATE", length = 19)
  public Date getCrDate() {
    return this.crDate;
  }

  public void setCrDate(Date crDate) {
    this.crDate = crDate;
  }

  @Column(name = "OP_USER", length = 50)
  public String getOpUser() {
    return this.opUser;
  }

  public void setOpUser(String opUser) {
    this.opUser = opUser;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "OP_DATE", length = 19)
  public Date getOpDate() {
    return this.opDate;
  }

  public void setOpDate(Date opDate) {
    this.opDate = opDate;
  }

}
