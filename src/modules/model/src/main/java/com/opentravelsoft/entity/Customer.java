package com.opentravelsoft.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "tbl_customer")
public class Customer implements java.io.Serializable {

  private Integer customerId;
  private String code;
  private String name;
  private String shortName;
  /** 所属销售市场 */
  private Team team;
  /** 所在国家 */
  private String countryCd;
  /** 省份 */
  private String provinceCd;

  /** 城市 */
  private City city;
  /** 地区 eq 静安区 */
  private String district;
  private String registeredAdd;
  private String address;
  private String zip;
  private String contactTel;
  private String contactFax;
  /** 联系人姓名 */
  private String contact;
  private String contactEmail;
  /** 公司类别 (有限公司 合伙商行 个人独资 其他) */
  private String type;
  private String passwd;
  /** 观光、度假 */
  private String feature;
  private String route;
  /** 提供资源 */
  private String resource;
  /** 公司结构 总公司、分公司 */
  private String structure;
  /** N:现结、M:月结 */
  private String payment;
  /** 营业执照编号 */
  private String bussId;
  /** 登记日期 */
  private Date bussDate;
  /** 信用度1 */
  private BigDecimal creditAmt1;
  /** 信用度2 */
  private BigDecimal creditAmt2;
  private Integer creditTime;
  private String reserveMail;
  /** 开户银行 */
  private String bankname;
  /** 帐号 */
  private String bankid;
  /** 账户名称 */
  private String bcltname;
  /** 状态 */
  private String status;
  /** 可承受超过额度百分比 */
  private BigDecimal stay;
  private String isActive;
  private boolean isSupplier;
  private boolean isAgent;
  private String del;

  /** 负责销售 */
  private Employee sales;
  private Date created;
  private Integer createdBy;
  /** 审核时间 */
  private Date checked;
  private Integer checkedBy;
  private Date updated;
  private Integer updatedBy;

  public Customer() {
    city = new City();
    sales = new Employee();
    this.del = "N";
    this.contacts = new ArrayList<Contact>();
    this.isSupplier = false;
    this.isAgent = true;
    this.reserveMail = "Y";
  }

  public Customer(Integer id) {
    this();
    this.customerId = id;
  }

  public Customer(String name, boolean isSupplier, boolean isAgent, Date updated) {
    this.name = name;
    this.isSupplier = isSupplier;
    this.isAgent = isAgent;
    this.updated = updated;
  }

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "CUSTOMER_ID", unique = true, nullable = false)
  public Integer getCustomerId() {
    return this.customerId;
  }

  public void setCustomerId(Integer customerId) {
    this.customerId = customerId;
  }

  @Column(name = "CODE", length = 20)
  public String getCode() {
    return this.code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  @Column(name = "NAME", nullable = false, length = 60)
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(name = "SHORT_NAME", length = 20)
  public String getShortName() {
    return this.shortName;
  }

  public void setShortName(String shortName) {
    this.shortName = shortName;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "TEAM_ID")
  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  @Column(name = "COUNTRY_CD", length = 6)
  public String getCountryCd() {
    return this.countryCd;
  }

  public void setCountryCd(String countryCd) {
    this.countryCd = countryCd;
  }

  @Column(name = "PROVINCE_CD", length = 6)
  public String getProvinceCd() {
    return this.provinceCd;
  }

  public void setProvinceCd(String provinceCd) {
    this.provinceCd = provinceCd;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "CITY_CD")
  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }

  @Column(name = "DISTRICT", length = 40)
  public String getDistrict() {
    return this.district;
  }

  public void setDistrict(String district) {
    this.district = district;
  }

  @Column(name = "REGISTERED_ADD", length = 80)
  public String getRegisteredAdd() {
    return this.registeredAdd;
  }

  public void setRegisteredAdd(String registeredAdd) {
    this.registeredAdd = registeredAdd;
  }

  @Column(name = "ADDRESS", length = 80)
  public String getAddress() {
    return this.address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Column(name = "ZIP", length = 12)
  public String getZip() {
    return this.zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  @Column(name = "CONTACT_TEL", length = 40)
  public String getContactTel() {
    return this.contactTel;
  }

  public void setContactTel(String phone) {
    this.contactTel = phone;
  }

  @Column(name = "CONTACT_FAX", length = 40)
  public String getContactFax() {
    return this.contactFax;
  }

  public void setContactFax(String fax) {
    this.contactFax = fax;
  }

  @Column(name = "CONTACT", length = 20)
  public String getContact() {
    return this.contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  @Column(name = "CONTACT_EMAIL", length = 120)
  public String getContactEmail() {
    return this.contactEmail;
  }

  public void setContactEmail(String email) {
    this.contactEmail = email;
  }

  @Column(name = "TYPE", length = 1)
  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Column(name = "PASSWD", length = 20)
  public String getPasswd() {
    return this.passwd;
  }

  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }

  @Column(name = "FEATURE", length = 1)
  public String getFeature() {
    return this.feature;
  }

  public void setFeature(String feature) {
    this.feature = feature;
  }

  @Column(name = "ROUTE", length = 1)
  public String getRoute() {
    return this.route;
  }

  public void setRoute(String route) {
    this.route = route;
  }

  @Column(name = "RESOURCE", length = 1)
  public String getResource() {
    return this.resource;
  }

  public void setResource(String resource) {
    this.resource = resource;
  }

  @Column(name = "STRUCTURE", length = 1)
  public String getStructure() {
    return this.structure;
  }

  public void setStructure(String structure) {
    this.structure = structure;
  }

  @Column(name = "PAYMENT", length = 1)
  public String getPayment() {
    return this.payment;
  }

  public void setPayment(String payment) {
    this.payment = payment;
  }

  @Column(name = "BUSS_ID", length = 30)
  public String getBussId() {
    return this.bussId;
  }

  public void setBussId(String bussId) {
    this.bussId = bussId;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "BUSS_DATE", length = 19)
  public Date getBussDate() {
    return this.bussDate;
  }

  public void setBussDate(Date bussDate) {
    this.bussDate = bussDate;
  }

  @Column(name = "CREDIT_AMT1", precision = 11)
  public BigDecimal getCreditAmt1() {
    return this.creditAmt1;
  }

  public void setCreditAmt1(BigDecimal creditAmt1) {
    this.creditAmt1 = creditAmt1;
  }

  @Column(name = "CREDIT_AMT2", precision = 11)
  public BigDecimal getCreditAmt2() {
    return this.creditAmt2;
  }

  public void setCreditAmt2(BigDecimal creditAmt2) {
    this.creditAmt2 = creditAmt2;
  }

  @Column(name = "CREDIT_TIME")
  public Integer getCreditTime() {
    return this.creditTime;
  }

  public void setCreditTime(Integer creditTime) {
    this.creditTime = creditTime;
  }

  @Column(name = "RECEIVE_MAIL", length = 1)
  public String getReceiveMail() {
    return this.reserveMail;
  }

  public void setReceiveMail(String reserveMail) {
    this.reserveMail = reserveMail;
  }

  @Column(name = "BANKNAME", length = 50)
  public String getBankname() {
    return this.bankname;
  }

  public void setBankname(String bankname) {
    this.bankname = bankname;
  }

  @Column(name = "BANKID", length = 50)
  public String getBankid() {
    return this.bankid;
  }

  public void setBankid(String bankid) {
    this.bankid = bankid;
  }

  @Column(name = "BCLTNAME", length = 50)
  public String getBcltname() {
    return this.bcltname;
  }

  public void setBcltname(String bcltname) {
    this.bcltname = bcltname;
  }

  @Column(name = "STATUS", length = 10)
  public String getStatus() {
    return this.status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Column(name = "STAY", precision = 4, scale = 3)
  public BigDecimal getStay() {
    return this.stay;
  }

  public void setStay(BigDecimal stay) {
    this.stay = stay;
  }

  @Column(name = "IS_ACTIVE", length = 1)
  public String getIsActive() {
    return this.isActive;
  }

  public void setIsActive(String isActive) {
    this.isActive = isActive;
  }

  @Column(name = "IS_SUPPLIER", nullable = false)
  public boolean isIsSupplier() {
    return this.isSupplier;
  }

  public void setIsSupplier(boolean isSupplier) {
    this.isSupplier = isSupplier;
  }

  @Column(name = "IS_AGENT", nullable = false)
  public boolean isIsAgent() {
    return this.isAgent;
  }

  public void setIsAgent(boolean isAgent) {
    this.isAgent = isAgent;
  }

  @Column(name = "DEL", length = 1)
  public String getDel() {
    return this.del;
  }

  public void setDel(String del) {
    this.del = del;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "SALES_ID")
  public Employee getSales() {
    return sales;
  }

  public void setSales(Employee sales) {
    this.sales = sales;
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
  public Integer getCreatedBy() {
    return this.createdBy;
  }

  public void setCreatedBy(Integer createdBy) {
    this.createdBy = createdBy;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "CHECKED", length = 19)
  public Date getChecked() {
    return this.checked;
  }

  public void setChecked(Date checked) {
    this.checked = checked;
  }

  @Column(name = "CHECKEDBY")
  public Integer getCheckedBy() {
    return this.checkedBy;
  }

  public void setCheckedBy(Integer checkedby) {
    this.checkedBy = checkedby;
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
  public Integer getUpdatedBy() {
    return this.updatedBy;
  }

  public void setUpdatedBy(Integer updatedBy) {
    this.updatedBy = updatedBy;
  }

  /** 所在国家 */
  private String countryName;

  /** 所在省份 */
  private String provinceName;

  /** 公司形式["代理商","加盟店","营业部"] */
  private String agentType;

  /** 结算方式 现结、周期结算 */
  private String clearingMode;

  /** 结算周期 月结M 现结N */
  private String clearingCycle;

  private List<Contact> contacts;

  /** 现在欠款 */
  private double owedAmount;

  /** 预订人 */
  private String reserve;

  /** 预订人id */
  private Integer reserveId;

  /** 最后一次登陆时间 */
  private Date lastLogindate;

  /** 地接地区 */
  private String region;

  @Transient
  public Integer getSupplierId() {
    return getCustomerId();
  }

  public void setSupplierId(Integer supplierId) {
    setCustomerId(supplierId);
  }

  @Transient
  public String getAgentType() {
    return agentType;
  }

  public void setAgentType(String agentType) {
    this.agentType = agentType;
  }

  @Transient
  public String getClearingCycle() {
    return clearingCycle;
  }

  public void setClearingCycle(String clearingCycle) {
    this.clearingCycle = clearingCycle;
  }

  public void addContacts(Contact contact) {
    contacts.add(contact);
  }

  @Transient
  public double getOwedAmount() {
    return owedAmount;
  }

  public void setOwedAmount(double owedAmount) {
    this.owedAmount = owedAmount;
  }

  @Transient
  public String getReceive() {
    return reserve;
  }

  public void setReceive(String reserve) {
    this.reserve = reserve;
  }

  @Transient
  public Integer getReceiveId() {
    return reserveId;
  }

  public void setReceiveId(Integer reserveId) {
    this.reserveId = reserveId;
  }

  @Transient
  public Date getLastLogindate() {
    return lastLogindate;
  }

  public void setLastLogindate(Date lastLogindate) {
    this.lastLogindate = lastLogindate;
  }

  @Transient
  public String getCountryName() {
    return countryName;
  }

  public void setCountryName(String countryName) {
    this.countryName = countryName;
  }

  @Transient
  public String getSupplierName() {
    return getName();
  }

  public void setSupplierName(String name) {
    this.setName(name);
  }

  @Transient
  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  @Transient
  public String getClearingMode() {
    return clearingMode;
  }

  public void setClearingMode(String clearingMode) {
    this.clearingMode = clearingMode;
  }

  @Transient
  public List<Contact> getContacts() {
    return contacts;
  }

  public void setContacts(List<Contact> contacts) {
    this.contacts = contacts;
  }

  @Transient
  public String getProvinceName() {
    return provinceName;
  }

  public void setProvinceName(String provinceName) {
    this.provinceName = provinceName;
  }

}
