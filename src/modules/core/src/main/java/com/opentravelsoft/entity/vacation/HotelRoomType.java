package com.opentravelsoft.entity.vacation;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 酒店房型
 */
@Entity
@Table(name = "tbl_hotel_roomtype")
public class HotelRoomType implements java.io.Serializable {

  private String recNo;
  private Hotel hotel;
  private String cnNote;
  private String enNote;
  private Integer roomNum;
  private String mapFile;
  private Byte isDelete;
  private String crUser;
  private Date crDate;
  private String opUser;
  private Date opDate;

  public HotelRoomType() {
  }

  public HotelRoomType(String recNo) {
    this.recNo = recNo;
  }

  @Id
  @Column(name = "REC_NO", unique = true, nullable = false, length = 10)
  public String getRecNo() {
    return this.recNo;
  }

  public void setRecNo(String recNo) {
    this.recNo = recNo;
  }

  @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
  @JoinColumn(name = "HOT_CD")
  public Hotel getHotel() {
    return this.hotel;
  }

  public void setHotel(Hotel hotCd) {
    this.hotel = hotCd;
  }

  @Column(name = "CN_NOTE", length = 50)
  public String getCnNote() {
    return this.cnNote;
  }

  public void setCnNote(String cnNote) {
    this.cnNote = cnNote;
  }

  @Column(name = "EN_NOTE", length = 100)
  public String getEnNote() {
    return this.enNote;
  }

  public void setEnNote(String enNote) {
    this.enNote = enNote;
  }

  @Column(name = "ROOM_NUM")
  public Integer getRoomNum() {
    return this.roomNum;
  }

  public void setRoomNum(Integer roomNum) {
    this.roomNum = roomNum;
  }

  @Column(name = "MAP_FILE", length = 50)
  public String getMapFile() {
    return this.mapFile;
  }

  public void setMapFile(String mapFile) {
    this.mapFile = mapFile;
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
