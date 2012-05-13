package com.opentravelsoft.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 省份
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:32 $
 */
@Entity
@Table(name = "tbl_province")
public class Province {
  private static final long serialVersionUID = -3397929975585184234L;

  public Province(String code) {
    this.code = code;
  }

  /** 省编码 */
  private String code;
  /** 省名称 */
  private String cnName;
  private String enName;
  private String country;

  public Province() {
  }

  public Province(String code, String cnName) {
    this.code = code;
    this.cnName = cnName;
  }

  @Id
  @Column(name = "CODE", unique = true, nullable = false, length = 2)
  public String getCode() {
    return this.code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  @Column(name = "CN_NAME", nullable = false, length = 20)
  public String getCnName() {
    return this.cnName;
  }

  public void setCnName(String cnName) {
    this.cnName = cnName;
  }

  @Column(name = "EN_NAME", length = 30)
  public String getEnName() {
    return this.enName;
  }

  public void setEnName(String enName) {
    this.enName = enName;
  }

  @Column(name = "COUNTRY", length = 3)
  public String getCountry() {
    return this.country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

}
