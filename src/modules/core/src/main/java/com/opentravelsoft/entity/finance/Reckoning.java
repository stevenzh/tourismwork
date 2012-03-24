package com.opentravelsoft.entity.finance;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;

import com.opentravelsoft.entity.FopTeam;
import com.opentravelsoft.entity.xml.ReckoningInputSource;
import com.opentravelsoft.entity.xml.ReckoningXMLReader;

/**
 * 帐单
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:32 $
 */
public class Reckoning implements FopTeam {
  /** 帐单号 */
  private long reckoningId;

  /** 版本 */
  private int version;

  /** 订单号 */
  private String bookingNo;

  /** 线路说明 */
  private String lineNote;

  /** 帐单分类 */
  private char tourType;

  /** 联系人 */
  private String contact;

  /** 电话 */
  private String phone;

  /** 传真 */
  private String fax;

  /** 领队人数 */
  private int leaderPax;

  /** 人数 */
  private int pax;

  /** 创建人 */
  private Long createdBy;

  private String createdByName;

  /** 打印人 */
  private String printed;

  /** 创建时间 */
  private Date createDate;

  /** 修改日期 */
  private Date updateDate;

  /** 打印日期 */
  private Date printDate;

  /** 打印次数 */
  private int printedCount;

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

  /** 说明 摘要 */
  private String description;

  /** 单价 */
  private double unitPrice;

  /** 金额 */
  private double amount;

  /** 单位 */
  private String unit;

  /** 开户名 */
  private String payName;

  /** 开户行 */
  private String payBank;

  /** 帐号 */
  private String payAccount;

  // -------------------------------------------------------------------------

  /** 打印日期 */
  private String pDate;

  /** 创建时间 */
  private String cDate;

  /** 修改日期 */
  private String upDate;

  /** 用户所在部门 */
  private String userDept;

  // -------------------------------------------------------------------------

  private int number;

  private String remarks;

  private Date created;

  private Date updated;

  private Long updatedBy;

  private String isprinted;

  private List<ReckoningAcct> reckoningAcctList = new ArrayList<ReckoningAcct>();

  public long getReckoningId() {
    return reckoningId;
  }

  public void setReckoningId(long reckoningId) {
    this.reckoningId = reckoningId;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  public String getBookingNo() {
    return bookingNo;
  }

  public void setBookingNo(String bookingNo) {
    this.bookingNo = bookingNo;
  }

  public String getRouteNote() {
    return lineNote;
  }

  public void setRouteNote(String note) {
    this.lineNote = note;
  }

  public char getTourType() {
    return tourType;
  }

  public void setTourType(char tourType) {
    this.tourType = tourType;
  }

  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getFax() {
    return fax;
  }

  public void setFax(String fax) {
    this.fax = fax;
  }

  public int getLeaderPax() {
    return leaderPax;
  }

  public void setLeaderPax(int pax) {
    this.leaderPax = pax;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public String getPrinted() {
    return printed;
  }

  public void setPrinted(String printed) {
    this.printed = printed;
  }

  public int getPrintedCount() {
    return printedCount;
  }

  public void setPrintedCount(int printedCount) {
    this.printedCount = printedCount;
  }

  public int getPax() {
    return pax;
  }

  public void setPax(int pax) {
    this.pax = pax;
  }

  public Long getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Long createdBy) {
    this.createdBy = createdBy;
  }

  public String getTourNo() {
    return tourNo;
  }

  public void setTourNo(String tourNo) {
    this.tourNo = tourNo;
  }

  public String getRouteNo() {
    return lineNo;
  }

  public void setRouteNo(String lineNo) {
    this.lineNo = lineNo;
  }

  public String getRouteName() {
    return lineName;
  }

  public void setRouteName(String routeName) {
    this.lineName = routeName;
  }

  public Date getOutDate() {
    return outDate;
  }

  public void setOutDate(Date outDate) {
    this.outDate = outDate;
  }

  public String getClient() {
    return client;
  }

  public void setClient(String client) {
    this.client = client;
  }

  public int getItemId() {
    return itemId;
  }

  public void setItemId(int itemId) {
    this.itemId = itemId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public double getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(double unitPrice) {
    this.unitPrice = unitPrice;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  public Date getPrintDate() {
    return printDate;
  }

  public void setPrintDate(Date printDate) {
    this.printDate = printDate;
  }

  public List<ReckoningAcct> getReckoningAcctList() {
    return reckoningAcctList;
  }

  public void setReckoningAcctList(List<ReckoningAcct> reckoningAcctList) {
    this.reckoningAcctList = reckoningAcctList;
  }

  public Date getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
  }

  public String getPDate() {
    return pDate;
  }

  public void setPDate(String date) {
    pDate = date;
  }

  public String getCDate() {
    return cDate;
  }

  public void setCDate(String date) {
    cDate = date;
  }

  public String getUpDate() {
    return upDate;
  }

  public void setUpDate(String upDate) {
    this.upDate = upDate;
  }

  public Source getSource() {
    return new SAXSource(new ReckoningXMLReader(), new ReckoningInputSource(
        this));
  }

  public String getPayName() {
    return payName;
  }

  public void setPayName(String payName) {
    this.payName = payName;
  }

  public String getPayBank() {
    return payBank;
  }

  public void setPayBank(String payBank) {
    this.payBank = payBank;
  }

  public String getPayAccount() {
    return payAccount;
  }

  public void setPayAccount(String payAccount) {
    this.payAccount = payAccount;
  }

  public String getUserDept() {
    return userDept;
  }

  public void setUserDept(String userDept) {
    this.userDept = userDept;
  }

  public String getCreatedByName() {
    return createdByName;
  }

  public void setCreatedByName(String createdByName) {
    this.createdByName = createdByName;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Date getUpdated() {
    return updated;
  }

  public void setUpdated(Date updated) {
    this.updated = updated;
  }

  public Long getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(Long updatedby) {
    this.updatedBy = updatedby;
  }

  public String getIsprinted() {
    return isprinted;
  }

  public void setIsprinted(String isprinted) {
    this.isprinted = isprinted;
  }
}
