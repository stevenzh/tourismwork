package com.opentravelsoft.entity.out;

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
@Table(name = "tbl_ad")
public class TblAd implements java.io.Serializable {

  private Integer adId;
  private short storeId;
  private short positionId;
  private String adType;
  private String adName;
  private String adLink;
  private String adCode;
  private Date startTime;
  private Date endTime;
  private int clickCount;
  private byte enabled;

  public TblAd() {
  }

  public TblAd(short storeId, short positionId, String adType, String adName,
      String adLink, Date startTime, Date endTime, int clickCount, byte enabled) {
    this.storeId = storeId;
    this.positionId = positionId;
    this.adType = adType;
    this.adName = adName;
    this.adLink = adLink;
    this.startTime = startTime;
    this.endTime = endTime;
    this.clickCount = clickCount;
    this.enabled = enabled;
  }

  public TblAd(short storeId, short positionId, String adType, String adName,
      String adLink, String adCode, Date startTime, Date endTime,
      int clickCount, byte enabled) {
    this.storeId = storeId;
    this.positionId = positionId;
    this.adType = adType;
    this.adName = adName;
    this.adLink = adLink;
    this.adCode = adCode;
    this.startTime = startTime;
    this.endTime = endTime;
    this.clickCount = clickCount;
    this.enabled = enabled;
  }

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "AD_ID", unique = true, nullable = false)
  public Integer getAdId() {
    return this.adId;
  }

  public void setAdId(Integer adId) {
    this.adId = adId;
  }

  @Column(name = "STORE_ID", nullable = false)
  public short getStoreId() {
    return this.storeId;
  }

  public void setStoreId(short storeId) {
    this.storeId = storeId;
  }

  @Column(name = "POSITION_ID", nullable = false)
  public short getPositionId() {
    return this.positionId;
  }

  public void setPositionId(short positionId) {
    this.positionId = positionId;
  }

  @Column(name = "AD_TYPE", nullable = false, length = 10)
  public String getAdType() {
    return this.adType;
  }

  public void setAdType(String adType) {
    this.adType = adType;
  }

  @Column(name = "AD_NAME", nullable = false, length = 120)
  public String getAdName() {
    return this.adName;
  }

  public void setAdName(String adName) {
    this.adName = adName;
  }

  @Column(name = "AD_LINK", nullable = false)
  public String getAdLink() {
    return this.adLink;
  }

  public void setAdLink(String adLink) {
    this.adLink = adLink;
  }

  @Column(name = "AD_CODE")
  public String getAdCode() {
    return this.adCode;
  }

  public void setAdCode(String adCode) {
    this.adCode = adCode;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "START_TIME", nullable = false, length = 19)
  public Date getStartTime() {
    return this.startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "END_TIME", nullable = false, length = 19)
  public Date getEndTime() {
    return this.endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  @Column(name = "CLICK_COUNT", nullable = false)
  public int getClickCount() {
    return this.clickCount;
  }

  public void setClickCount(int clickCount) {
    this.clickCount = clickCount;
  }

  @Column(name = "ENABLED", nullable = false)
  public byte getEnabled() {
    return this.enabled;
  }

  public void setEnabled(byte enabled) {
    this.enabled = enabled;
  }

}
