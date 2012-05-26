package com.opentravelsoft.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_airways")
public class Airways implements java.io.Serializable {

  private String code;
  private String name;
  private String fullname;
  private String contact;
  private String phone;
  private Byte isActive;

  public Airways() {
    this.isActive = 1;
  }

  public Airways(String code, String name) {
    this.code = code;
    this.name = name;
  }

  @Id
  @Column(name = "CODE", unique = true, nullable = false, length = 2)
  public String getCode() {
    return this.code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  @Column(name = "NAME", nullable = false, length = 20)
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(name = "FULLNAME", length = 40)
  public String getFullname() {
    return this.fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  @Column(name = "CONTACT", length = 20)
  public String getContact() {
    return this.contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  @Column(name = "PHONE", length = 20)
  public String getPhone() {
    return this.phone;
  }

  public void setPhone(String phoneNumber) {
    this.phone = phoneNumber;
  }

  @Column(name = "IS_ACTIVE")
  public Byte getIsActive() {
    return this.isActive;
  }

  public void setIsActive(Byte isActive) {
    this.isActive = isActive;
  }

}
