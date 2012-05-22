package com.opentravelsoft.entity.finance;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;

import com.opentravelsoft.entity.xml.ReckoningInputSource;
import com.opentravelsoft.entity.xml.ReckoningXMLReader;

@Entity
@Table(name = "tbl_reckoning")
public class Reckoning implements java.io.Serializable {
  /** 帐单号 */
  private Integer reckoningId;
  /** 订单号 */
  private String bookingNo;
  private int number;
  /** 帐单分类 */
  private char tourType;
  private String routeNote;
  /** 说明 摘要 */
  private String description;
  private String remarks;
  /** 联系人 */
  private String contact;
  /** 电话 */
  private String phone;
  /** 传真 */
  private String fax;
  private Integer leaderAmt;

  private Character isprinted;
  /** 打印人 */
  private Date printed;
  /** 打印次数 */
  private Integer printedCount;
  private Date created;
  private Integer createdby;
  private Date updated;
  private Integer updatedby;

  public Reckoning() {
  }

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "RECKONING_ID", unique = true, nullable = false)
  public Integer getReckoningId() {
    return this.reckoningId;
  }

  public void setReckoningId(Integer reckoningId) {
    this.reckoningId = reckoningId;
  }

  @Column(name = "BOOKING_NO", nullable = false, length = 20)
  public String getBookingNo() {
    return this.bookingNo;
  }

  public void setBookingNo(String bookingNo) {
    this.bookingNo = bookingNo;
  }

  @Column(name = "NUMBER", nullable = false)
  public int getNumber() {
    return this.number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  @Column(name = "TOUR_TYPE", nullable = false, length = 1)
  public char getTourType() {
    return this.tourType;
  }

  public void setTourType(char tourType) {
    this.tourType = tourType;
  }

  @Column(name = "ROUTE_NOTE", length = 1000)
  public String getRouteNote() {
    return this.routeNote;
  }

  public void setRouteNote(String routeNote) {
    this.routeNote = routeNote;
  }

  @Column(name = "DESCRIPTION", length = 1000)
  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Column(name = "REMARKS", length = 1000)
  public String getRemarks() {
    return this.remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  @Column(name = "CONTACT", length = 20)
  public String getContact() {
    return this.contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  @Column(name = "PHONE", length = 100)
  public String getPhone() {
    return this.phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  @Column(name = "FAX", length = 20)
  public String getFax() {
    return this.fax;
  }

  public void setFax(String fax) {
    this.fax = fax;
  }

  @Column(name = "LEADER_AMT")
  public Integer getLeaderAmt() {
    return this.leaderAmt;
  }

  public void setLeaderAmt(Integer leaderAmt) {
    this.leaderAmt = leaderAmt;
  }

  @Column(name = "ISPRINTED", length = 1)
  public Character getIsprinted() {
    return this.isprinted;
  }

  public void setIsprinted(Character isprinted) {
    this.isprinted = isprinted;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "PRINTED", length = 19)
  public Date getPrinted() {
    return this.printed;
  }

  public void setPrinted(Date printed) {
    this.printed = printed;
  }

  @Column(name = "PRINTED_COUNT")
  public Integer getPrintedCount() {
    return this.printedCount;
  }

  public void setPrintedCount(Integer printedCount) {
    this.printedCount = printedCount;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "CREATED", length = 19)
  public Date getCreated() {
    return this.created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  @Column(name = "CREATEDBY")
  public Integer getCreatedby() {
    return this.createdby;
  }

  public void setCreatedby(Integer createdby) {
    this.createdby = createdby;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "UPDATED", nullable = false, length = 19)
  public Date getUpdated() {
    return this.updated;
  }

  public void setUpdated(Date updated) {
    this.updated = updated;
  }

  @Column(name = "UPDATEDBY")
  public Integer getUpdatedby() {
    return this.updatedby;
  }

  public void setUpdatedby(Integer updatedby) {
    this.updatedby = updatedby;
  }

  /** 版本 */
  private int version;

  /** 领队人数 */
  private int leaderPax;

  /** 人数 */
  private int pax;

  private String createdByName;

  /** 创建时间 */
  private Date createDate;

  /** 修改日期 */
  private Date updateDate;

  /** 打印日期 */
  private Date printDate;

  /** 客户 */
  private String client;

  /** 团号 */
  private String tourNo;

  /** 线路号 */
  private String lineNo;

  /** 线路名 */
  private String lineName;

  /** 出团日期 */
  private Date outDate;

  /** 帐单序号 */
  private int itemId;

  /** 单价 */
  private double unitPrice;

  /** 金额 */
  private BigDecimal amount;

  /** 单位 */
  private String unit;

  /** 开户名 */
  private String payName;

  /** 开户行 */
  private String payBank;

  /** 帐号 */
  private String payAccount;

  /** 打印日期 */
  private String pDate;

  /** 创建时间 */
  private String cDate;

  /** 修改日期 */
  private String upDate;

  /** 用户所在部门 */
  private String userDept;

  private List<ReckoningAcct> reckoningAcctList = new ArrayList<ReckoningAcct>();

  @Transient
  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  @Transient
  public int getLeaderPax() {
    return leaderPax;
  }

  public void setLeaderPax(int pax) {
    this.leaderPax = pax;
  }

  @Transient
  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  @Transient
  public int getPax() {
    return pax;
  }

  public void setPax(int pax) {
    this.pax = pax;
  }

  @Transient
  public String getTourNo() {
    return tourNo;
  }

  public void setTourNo(String tourNo) {
    this.tourNo = tourNo;
  }

  @Transient
  public String getRouteNo() {
    return lineNo;
  }

  public void setRouteNo(String lineNo) {
    this.lineNo = lineNo;
  }

  @Transient
  public String getRouteName() {
    return lineName;
  }

  public void setRouteName(String routeName) {
    this.lineName = routeName;
  }

  @Transient
  public Date getOutDate() {
    return outDate;
  }

  public void setOutDate(Date outDate) {
    this.outDate = outDate;
  }

  @Transient
  public String getClient() {
    return client;
  }

  public void setClient(String client) {
    this.client = client;
  }

  @Transient
  public int getItemId() {
    return itemId;
  }

  public void setItemId(int itemId) {
    this.itemId = itemId;
  }

  @Transient
  public double getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(double unitPrice) {
    this.unitPrice = unitPrice;
  }

  @Transient
  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  @Transient
  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  @Transient
  public Date getPrintDate() {
    return printDate;
  }

  public void setPrintDate(Date printDate) {
    this.printDate = printDate;
  }

  @Transient
  public List<ReckoningAcct> getReckoningAcctList() {
    return reckoningAcctList;
  }

  public void setReckoningAcctList(List<ReckoningAcct> reckoningAcctList) {
    this.reckoningAcctList = reckoningAcctList;
  }

  @Transient
  public Date getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
  }

  @Transient
  public String getPDate() {
    return pDate;
  }

  public void setPDate(String date) {
    pDate = date;
  }

  @Transient
  public String getCDate() {
    return cDate;
  }

  public void setCDate(String date) {
    cDate = date;
  }

  @Transient
  public String getUpDate() {
    return upDate;
  }

  public void setUpDate(String upDate) {
    this.upDate = upDate;
  }

  @Transient
  public Source getSource() {
    return new SAXSource(new ReckoningXMLReader(), new ReckoningInputSource(
        this));
  }

  @Transient
  public String getPayName() {
    return payName;
  }

  public void setPayName(String payName) {
    this.payName = payName;
  }

  @Transient
  public String getPayBank() {
    return payBank;
  }

  public void setPayBank(String payBank) {
    this.payBank = payBank;
  }

  @Transient
  public String getPayAccount() {
    return payAccount;
  }

  public void setPayAccount(String payAccount) {
    this.payAccount = payAccount;
  }

  @Transient
  public String getUserDept() {
    return userDept;
  }

  public void setUserDept(String userDept) {
    this.userDept = userDept;
  }

  @Transient
  public String getCreatedByName() {
    return createdByName;
  }

  public void setCreatedByName(String createdByName) {
    this.createdByName = createdByName;
  }

}
