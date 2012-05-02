package com.opentravelsoft.entity;

import java.util.Date;

/**
 * 公司
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:32 $
 */
public class Company {

  private static final long serialVersionUID = -740505319670932793L;

  /** 公司代码 */
  private String companyId;

  /***/
  private int id;

  /** 公司名称 */
  private String name;

  /** 公司经营地址 */
  private String address;

  /** 电话号码 */
  private String phone;

  /** 传真 */
  private String fax;

  /** 邮政编码 */
  private String zipCode;

  /** 经理 */
  private String manager;

  /** 记录创建时间 */
  private Date created;

  /** 记录创建人 */
  private Long createdBy;

  /** 记录修改时间 */
  private Date updated;

  /** 记录修改人 */
  private Long updatedBy;

  private String enabled;

  public String getEnabled() {
    return enabled;
  }

  public void setEnabled(String enabled) {
    this.enabled = enabled;
  }

  public Company() {
    companyId = "";
    name = "";
    zipCode = "";
    manager = "";
    enabled = "N";
  }

  public String getCompanyId() {
    return companyId;
  }

  public void setCompanyId(String companyId) {
    this.companyId = companyId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zip) {
    this.zipCode = zip;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getManager() {
    return manager;
  }

  public void setManager(String manager) {
    this.manager = manager;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Long getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Long createdBy) {
    this.createdBy = createdBy;
  }

  public Date getUpdated() {
    return updated;
  }

  public void setUpdated(Date updated) {
    this.updated = updated;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Long getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(Long updatedBy) {
    this.updatedBy = updatedBy;
  }

}
