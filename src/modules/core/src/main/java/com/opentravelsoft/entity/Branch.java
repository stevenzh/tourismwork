package com.opentravelsoft.entity;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 营业部
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:34 $
 */
public class Branch extends Customer implements java.io.Serializable {

  private static final long serialVersionUID = 2538748571995534519L;

  /** 交通 */
  private String comeAndGo;

  /** 营业时间 */
  private String officeHours;

  /** 店长电话 */
  private String shopkeeperPhone;

  /** 店长寄语 */
  private String shopkeeperSaying;

  /** 店长照片文件 */
  private String shopkeeperPhoneFile;

  /** 门店照片文件 */
  private String branchPhoneFile;

  /** 门店地图文件 */
  private String mapFile;

  private String agentnm;

  private String agentabb;

  private String agentadd;

  private String agentzip;

  private String agenttel;

  private String agentfax;

  private String citycd;

  private String email;

  private String areaId;

  private String remarks;

  private Character opKey;

  private String webKey;

  private Double arrearage;

  private Character warnKey;

  private Character reckoning;

  private String bussTime;

  private String comeandgo;

  private String gmTel;

  private String gmTalk;

  private String phoneFile1;

  private String phoneFile2;

  private Long createUser;

  private Date createDate;

  private Long opUser;

  private Date opDate;

  private Character checkKey;

  public Branch() {
    super();
  }

  public long getBranchId() {
    return getCustomerId();
  }

  public void setBranchId(long branchId) {
    setCustomerId(branchId);
  }

  public String getAgentnm() {
    return this.agentnm;
  }

  public void setAgentnm(String agentnm) {
    this.agentnm = agentnm;
  }

  public String getAgentabb() {
    return this.agentabb;
  }

  public void setAgentabb(String agentabb) {
    this.agentabb = agentabb;
  }

  public String getAgentadd() {
    return this.agentadd;
  }

  public void setAgentadd(String agentadd) {
    this.agentadd = agentadd;
  }

  public String getAgentzip() {
    return this.agentzip;
  }

  public void setAgentzip(String agentzip) {
    this.agentzip = agentzip;
  }

  public String getAgenttel() {
    return this.agenttel;
  }

  public void setAgenttel(String agenttel) {
    this.agenttel = agenttel;
  }

  public String getAgentfax() {
    return this.agentfax;
  }

  public void setAgentfax(String agentfax) {
    this.agentfax = agentfax;
  }

  public String getCitycd() {
    return this.citycd;
  }

  public void setCitycd(String citycd) {
    this.citycd = citycd;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAreaId() {
    return this.areaId;
  }

  public void setAreaId(String areaId) {
    this.areaId = areaId;
  }

  public String getRemarks() {
    return this.remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public Character getOpKey() {
    return this.opKey;
  }

  public void setOpKey(Character opKey) {
    this.opKey = opKey;
  }

  public Long getOpUser() {
    return this.opUser;
  }

  public void setOpUser(Long opUser) {
    this.opUser = opUser;
  }

  public Date getOpDate() {
    return this.opDate;
  }

  public void setOpDate(Date opDate) {
    this.opDate = opDate;
  }

  public String getWebKey() {
    return this.webKey;
  }

  public void setWebKey(String webKey) {
    this.webKey = webKey;
  }

  public Double getArrearage() {
    return this.arrearage;
  }

  public void setArrearage(Double arrearage) {
    this.arrearage = arrearage;
  }

  public Character getWarnKey() {
    return this.warnKey;
  }

  public void setWarnKey(Character warnKey) {
    this.warnKey = warnKey;
  }

  public Character getReckoning() {
    return this.reckoning;
  }

  public void setReckoning(Character reckoning) {
    this.reckoning = reckoning;
  }

  public String getBussTime() {
    return this.bussTime;
  }

  public void setBussTime(String bussTime) {
    this.bussTime = bussTime;
  }

  public String getComeandgo() {
    return this.comeandgo;
  }

  public void setComeandgo(String comeandgo) {
    this.comeandgo = comeandgo;
  }

  public String getGmTel() {
    return this.gmTel;
  }

  public void setGmTel(String gmTel) {
    this.gmTel = gmTel;
  }

  public String getGmTalk() {
    return this.gmTalk;
  }

  public void setGmTalk(String gmTalk) {
    this.gmTalk = gmTalk;
  }

  public String getPhoneFile1() {
    return this.phoneFile1;
  }

  public void setPhoneFile1(String phoneFile1) {
    this.phoneFile1 = phoneFile1;
  }

  public String getPhoneFile2() {
    return this.phoneFile2;
  }

  public void setPhoneFile2(String phoneFile2) {
    this.phoneFile2 = phoneFile2;
  }

  public Long getCreateUser() {
    return this.createUser;
  }

  public void setCreateUser(Long createUser) {
    this.createUser = createUser;
  }

  public Date getCreateDate() {
    return this.createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public Character getCheckKey() {
    return this.checkKey;
  }

  public void setCheckKey(Character checkKey) {
    this.checkKey = checkKey;
  }

  public String getComeAndGo() {
    return comeAndGo;
  }

  public void setComeAndGo(String comeAndGo) {
    this.comeAndGo = comeAndGo;
  }

  public String getOfficeHours() {
    return officeHours;
  }

  public void setOfficeHours(String officeHours) {
    this.officeHours = officeHours;
  }

  /**
   * 店长
   * 
   * @return
   */
  public String getShopkeeper() {
    return getManager();
  }

  public void setShopkeeper(String keeper) {
    setManager(keeper);
  }

  public String getShopkeeperPhone() {
    return shopkeeperPhone;
  }

  public void setShopkeeperPhone(String shopkeeperPhone) {
    this.shopkeeperPhone = shopkeeperPhone;
  }

  public String getShopkeeperSaying() {
    return shopkeeperSaying;
  }

  public void setShopkeeperSaying(String shopkeeperSaying) {
    this.shopkeeperSaying = shopkeeperSaying;
  }

  public String getBranchPhoneFile() {
    return branchPhoneFile;
  }

  public void setBranchPhoneFile(String branchPhoneFile) {
    this.branchPhoneFile = branchPhoneFile;
  }

  public String getMapFile() {
    return mapFile;
  }

  public void setMapFile(String mapFile) {
    this.mapFile = mapFile;
  }

  public String getShopkeeperPhoneFile() {
    return shopkeeperPhoneFile;
  }

  public void setShopkeeperPhoneFile(String shopkeeperPhoneFile) {
    this.shopkeeperPhoneFile = shopkeeperPhoneFile;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Branch)) {
      return false;
    }

    final Branch branch = (Branch) o;
    return this.hashCode() == branch.hashCode();
  }

  @Override
  public int hashCode() {
    int result;
    result = (getName() != null ? getName().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
    .append("branchId", this.getBranchId())
    .append("name", this.getName()).toString();
  }
}
