package com.opentravelsoft.entity.out;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tbl_crm_case_b")
public class TblCrmCaseB implements java.io.Serializable {

  private Integer caseId;
  private Integer customerId;
  private String lineNo;
  private String lineName;
  private String customerSource;
  private String caseType;
  private Date createDt;
  private String createUser;

  public TblCrmCaseB() {
  }

  public TblCrmCaseB(String lineNo) {
    this.lineNo = lineNo;
  }

  public TblCrmCaseB(Integer customerId, String lineNo, String lineName,
      String customerSource, String caseType, Date createDt,
      String createUser) {
    this.customerId = customerId;
    this.lineNo = lineNo;
    this.lineName = lineName;
    this.customerSource = customerSource;
    this.caseType = caseType;
    this.createDt = createDt;
    this.createUser = createUser;
  }

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "CASE_ID", unique = true, nullable = false)
  public Integer getCaseId() {
    return this.caseId;
  }

  public void setCaseId(Integer caseId) {
    this.caseId = caseId;
  }

  @Column(name = "CUSTOMER_ID")
  public Integer getCustomerId() {
    return this.customerId;
  }

  public void setCustomerId(Integer customerId) {
    this.customerId = customerId;
  }

  @Column(name = "LINE_NO", nullable = false, length = 8)
  public String getLineNo() {
    return this.lineNo;
  }

  public void setLineNo(String lineNo) {
    this.lineNo = lineNo;
  }

  @Column(name = "LINE_NAME", length = 60)
  public String getLineName() {
    return this.lineName;
  }

  public void setLineName(String lineName) {
    this.lineName = lineName;
  }

  @Column(name = "CUSTOMER_SOURCE", length = 1)
  public String getCustomerSource() {
    return this.customerSource;
  }

  public void setCustomerSource(String customerSource) {
    this.customerSource = customerSource;
  }

  @Column(name = "CASE_TYPE", length = 1)
  public String getCaseType() {
    return this.caseType;
  }

  public void setCaseType(String caseType) {
    this.caseType = caseType;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "CREATE_DT", length = 19)
  public Date getCreateDt() {
    return this.createDt;
  }

  public void setCreateDt(Date createDt) {
    this.createDt = createDt;
  }

  @Column(name = "CREATE_USER", length = 4)
  public String getCreateUser() {
    return this.createUser;
  }

  public void setCreateUser(String createUser) {
    this.createUser = createUser;
  }

}
