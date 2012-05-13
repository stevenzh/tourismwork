package com.opentravelsoft.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_pinyin", catalog = "tourismwork_db")
public class Pinyin implements java.io.Serializable {

  private String chinese;
  private String english;
  private String telexcd;

  public Pinyin() {
  }

  public Pinyin(String chinese, String english, String telexcd) {
    this.chinese = chinese;
    this.english = english;
    this.telexcd = telexcd;
  }

  @Id
  @Column(name = "CHINESE", unique = true, nullable = false, length = 2)
  public String getChinese() {
    return this.chinese;
  }

  public void setChinese(String chinese) {
    this.chinese = chinese;
  }

  @Column(name = "ENGLISH", nullable = false, length = 6)
  public String getEnglish() {
    return this.english;
  }

  public void setEnglish(String english) {
    this.english = english;
  }

  @Column(name = "TELEXCD", nullable = false, length = 4)
  public String getTelexcd() {
    return this.telexcd;
  }

  public void setTelexcd(String telexcd) {
    this.telexcd = telexcd;
  }
}
