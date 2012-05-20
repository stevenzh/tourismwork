package com.opentravelsoft.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tbl_userpayol")
public class TblUserPayOL implements java.io.Serializable {

  private long payId;
  private String payerName;
  private String payerPhone;
  private String tourRoute;
  private double payAmount;
  private String remark;
  private Date payTime;

  public TblUserPayOL() {
  }

  public TblUserPayOL(long payid, String payername, String payerphone,
      String tourroute, double payamount) {
    this.payId = payid;
    this.payerName = payername;
    this.payerPhone = payerphone;
    this.tourRoute = tourroute;
    this.payAmount = payamount;
  }

  public TblUserPayOL(long payid, String payername, String payerphone,
      String tourroute, double payamount, String remark, Date paytime) {
    this.payId = payid;
    this.payerName = payername;
    this.payerPhone = payerphone;
    this.tourRoute = tourroute;
    this.payAmount = payamount;
    this.remark = remark;
    this.payTime = paytime;
  }

  @Id
  @Column(name = "PAYID", unique = true, nullable = false, precision = 12, scale = 0)
  public long getPayId() {
    return this.payId;
  }

  public void setPayId(long payid) {
    this.payId = payid;
  }

  @Column(name = "PAYERNAME", nullable = false, length = 50)
  public String getPayerName() {
    return this.payerName;
  }

  public void setPayerName(String payername) {
    this.payerName = payername;
  }

  @Column(name = "PAYERPHONE", nullable = false, length = 50)
  public String getPayerPhone() {
    return this.payerPhone;
  }

  public void setPayerPhone(String payerphone) {
    this.payerPhone = payerphone;
  }

  @Column(name = "TOURROUTE", nullable = false, length = 100)
  public String getTourRoute() {
    return this.tourRoute;
  }

  public void setTourRoute(String tourroute) {
    this.tourRoute = tourroute;
  }

  @Column(name = "PAYAMOUNT", nullable = false, precision = 22, scale = 0)
  public double getPayAmount() {
    return this.payAmount;
  }

  public void setPayAmount(double payamount) {
    this.payAmount = payamount;
  }

  @Column(name = "REMARK", length = 1000)
  public String getRemark() {
    return this.remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "PAYTIME", length = 19)
  public Date getPayTime() {
    return this.payTime;
  }

  public void setPayTime(Date paytime) {
    this.payTime = paytime;
  }

}