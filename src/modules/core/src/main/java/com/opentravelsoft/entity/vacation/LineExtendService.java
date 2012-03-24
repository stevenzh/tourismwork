package com.opentravelsoft.entity.vacation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 */
@Entity
@Table(name = "tbl_lineextendservice")
public class LineExtendService implements java.io.Serializable {

  private Integer id;
  private String lineNo;
  private int serviceId;
  private int type;

  public LineExtendService() {
  }

  public LineExtendService(String lineNo, int serviceId, int type) {
    this.lineNo = lineNo;
    this.serviceId = serviceId;
    this.type = type;
  }

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "ID", unique = true, nullable = false)
  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Column(name = "LineNo", nullable = false, length = 10)
  public String getLineNo() {
    return this.lineNo;
  }

  public void setLineNo(String lineNo) {
    this.lineNo = lineNo;
  }

  @Column(name = "ServiceID", nullable = false)
  public int getServiceId() {
    return this.serviceId;
  }

  public void setServiceId(int serviceId) {
    this.serviceId = serviceId;
  }

  @Column(name = "Type", nullable = false)
  public int getType() {
    return this.type;
  }

  public void setType(int type) {
    this.type = type;
  }

}
