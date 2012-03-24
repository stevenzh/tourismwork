package com.opentravelsoft.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 客户
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:33 $
 */
public class Customer extends Company implements Serializable {

  private static final long serialVersionUID = 7689960497685672810L;

  private long customerId;

  private String code;

  private String shortName;

  /** 所属销售市场 */
  private Team team;

  /** 负责销售 */
  private Employee sales;

  /** 所在国家 */
  private String countryCd;

  /** 所在国家 */
  private String countryName;

  /** 省份 */
  private String provinceCd;

  /** 所在省份 */
  private String provinceName;

  /** 城市 */
  private City city;

  /** 地区 eq 静安区 */
  private String district;

  private String registeredAdd;

  /** 联系人姓名 */
  private String contact;

  /** Email */
  private String contactEmail;

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
  private Double creditAmt1 = 0d;

  /** 信用度2 */
  private Double creditAmt2 = 0d;

  private Integer creditTime;

  private String receiveMail;

  /** 开户银行 */
  private String bankname1;

  /** 帐号 */
  private String bankid1;

  /** 账户名称 */
  private String bcltname1;

  /** 状态 */
  private String status;

  /** 可承受超过额度百分比 */
  private double stay;

  private boolean isSupplier = false;

  private boolean isAgent = true;

  private String del;

  /** 审核人 */
  private Long checkedBy;

  /** 审核时间 */
  private Date checkDate;

  // -------------------------------------------------------------------------
  /** 公司类别 (有限公司 合伙商行 个人独资 其他) */
  private String companyType;

  /** 公司形式["代理商","加盟店","营业部"] */
  private String agentType;

  /** 结算方式 现结、周期结算 */
  private String clearingMode;

  /** 结算周期 月结M 现结N */
  private String clearingCycle;

  private String checked;

  private List<Contact> contacts;

  /** 现在欠款 */
  private double owedAmount;

  /** 预订人 */
  private String reserve;

  /** 预订人id */
  private long reserveId;

  /** 最后一次登陆时间 */
  private Date lastLogindate;

  /** 地接地区 */
  private String region;

  public Customer() {
    super();
    city = new City();
    sales = new Employee();
    this.del = "N";
    this.checked = "false";
    this.contacts = new ArrayList<Contact>();
    this.isSupplier = false;
    this.isAgent = true;
    this.receiveMail = "Y";
  }
  
  public Customer(long id)
  {
    this();
    this.customerId = id;
  }

  public long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(long customerId) {
    this.customerId = customerId;
  }

  public long getSupplierId() {
    return getCustomerId();
  }

  public void setSupplierId(long supplierId) {
    setCustomerId(supplierId);
  }

  public String getAgentType() {
    return agentType;
  }

  public void setAgentType(String agentType) {
    this.agentType = agentType;
  }

  public Date getCheckDate() {
    return checkDate;
  }

  public void setCheckDate(Date checkDate) {
    this.checkDate = checkDate;
  }

  public String getCompanyType() {
    return companyType;
  }

  public void setCompanyType(String companyType) {
    this.companyType = companyType;
  }

  public String getContactEmail() {
    return contactEmail;
  }

  public void setContactEmail(String contactEmail) {
    this.contactEmail = contactEmail;
  }

  public String getContactFax() {
    return getFax();
  }

  public void setContactFax(String contactFax) {
    setFax(contactFax);
  }

  public String getContactTel() {
    return getPhone();
  }

  public void setContactTel(String contactTel) {
    setPhone(contactTel);
  }

  public Double getCreditAmt1() {
    return creditAmt1;
  }

  public void setCreditAmt1(Double creditAmt1) {
    this.creditAmt1 = creditAmt1;
  }

  public Double getCreditAmt2() {
    return creditAmt2;
  }

  public void setCreditAmt2(Double creditAmt2) {
    this.creditAmt2 = creditAmt2;
  }

  public Integer getCreditTime() {
    return creditTime;
  }

  public void setCreditTime(Integer creditTime) {
    this.creditTime = creditTime;
  }

  public String getDistrict() {
    return district;
  }

  public void setDistrict(String district) {
    this.district = district;
  }

  public Long getCheckedBy() {
    return checkedBy;
  }

  public void setCheckedBy(Long checkBy) {
    this.checkedBy = checkBy;
  }

  public String getClearingCycle() {
    return clearingCycle;
  }

  public void setClearingCycle(String clearingCycle) {
    this.clearingCycle = clearingCycle;
  }

  public String getStructure() {
    return structure;
  }

  public void setStructure(String structure) {
    this.structure = structure;
  }

  public void addContacts(Contact contact) {
    contacts.add(contact);
  }

  public double getStay() {
    return stay;
  }

  public void setStay(double stay) {
    this.stay = stay;
  }

  public String getPasswd() {
    return passwd;
  }

  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }

  public double getOwedAmount() {
    return owedAmount;
  }

  public void setOwedAmount(double owedAmount) {
    this.owedAmount = owedAmount;
  }

  public String getReceive() {
    return reserve;
  }

  public void setReceive(String reserve) {
    this.reserve = reserve;
  }

  public long getReceiveId() {
    return reserveId;
  }

  public void setReceiveId(long reserveId) {
    this.reserveId = reserveId;
  }

  public Date getLastLogindate() {
    return lastLogindate;
  }

  public void setLastLogindate(Date lastLogindate) {
    this.lastLogindate = lastLogindate;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getShortName() {
    return shortName;
  }

  public void setShortName(String shortName) {
    this.shortName = shortName;
  }

  public String getProvinceCd() {
    return provinceCd;
  }

  public void setProvinceCd(String provinceCd) {
    this.provinceCd = provinceCd;
  }

  public String getRegisteredAdd() {
    return registeredAdd;
  }

  public void setRegisteredAdd(String registeredAdd) {
    this.registeredAdd = registeredAdd;
  }

  public String getZip() {
    return getZipCode();
  }

  public void setZip(String zip) {
    setZipCode(zip);
  }

  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getPayment() {
    return payment;
  }

  public void setPayment(String payment) {
    this.payment = payment;
  }

  public String getBussId() {
    return bussId;
  }

  public void setBussId(String bussId) {
    this.bussId = bussId;
  }

  public Date getBussDate() {
    return bussDate;
  }

  public void setBussDate(Date bussDate) {
    this.bussDate = bussDate;
  }

  public String getIsActive() {
    return getEnabled();
  }

  public void setIsActive(String isActive) {
    setEnabled(isActive);
  }

  public String getDel() {
    return del;
  }

  public void setDel(String del) {
    this.del = del;
  }

  public Employee getSales() {
    return sales;
  }

  public void setSales(Employee sales) {
    this.sales = sales;
  }

  public String getCountryName() {
    return countryName;
  }

  public void setCountryName(String countryName) {
    this.countryName = countryName;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getResource() {
    return resource;
  }

  public void setResource(String resource) {
    this.resource = resource;
  }

  public String getFeature() {
    return feature;
  }

  public void setFeature(String feature) {
    this.feature = feature;
  }

  public String getSupplierName() {
    return getName();
  }

  public void setSupplierName(String name) {
    this.setName(name);
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public String getChecked() {
    return checked;
  }

  public void setChecked(String checked) {
    this.checked = checked;
  }

  public String getClearingMode() {
    return clearingMode;
  }

  public void setClearingMode(String clearingMode) {
    this.clearingMode = clearingMode;
  }

  public List<Contact> getContacts() {
    return contacts;
  }

  public void setContacts(List<Contact> contacts) {
    this.contacts = contacts;
  }

  public String getProvinceName() {
    return provinceName;
  }

  public void setProvinceName(String provinceName) {
    this.provinceName = provinceName;
  }

  public String getCountryCd() {
    return countryCd;
  }

  public void setCountryCd(String countryCd) {
    this.countryCd = countryCd;
  }

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }

  public String getRoute() {
    return route;
  }

  public void setRoute(String route) {
    this.route = route;
  }

  public String getBankname1() {
    return bankname1;
  }

  public void setBankname1(String bankname1) {
    this.bankname1 = bankname1;
  }

  public String getBankid1() {
    return bankid1;
  }

  public void setBankid1(String bankid1) {
    this.bankid1 = bankid1;
  }

  public String getBcltname1() {
    return bcltname1;
  }

  public void setBcltname1(String bcltname1) {
    this.bcltname1 = bcltname1;
  }

  public boolean getIsSupplier() {
    return isSupplier;
  }

  public void setIsSupplier(boolean isSupplier) {
    this.isSupplier = isSupplier;
  }

  public boolean getIsAgent() {
    return isAgent;
  }

  public void setIsAgent(boolean isAgent) {
    this.isAgent = isAgent;
  }

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  public String getReceiveMail() {
    return receiveMail;
  }

  public void setReceiveMail(String receiveMail) {
    this.receiveMail = receiveMail;
  }

}
