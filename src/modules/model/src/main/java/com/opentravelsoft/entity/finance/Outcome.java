package com.opentravelsoft.entity.finance;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;

import com.opentravelsoft.entity.Customer;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.xml.OutcomeInputSource;
import com.opentravelsoft.entity.xml.OutcomeXMLReader;

@Entity
@Table(name = "tbl_outcome", catalog = "tourismwork_db")
public class Outcome implements java.io.Serializable {

  /** 付款申请书号 */
  private Integer outcomeId;
  private Customer customer;

  /** 团号 */
  private String tourNo;
  /** 中段说明 */
  private String note;
  private Date payDate;
  private Character payMode;
  private String billNo;
  /** 支付登记日期 */
  private Date payRegisterDate;
  private String payRegisterby;
  /** 付款总金额 */
  private BigDecimal amount;
  /** 票务配送 */
  private Short carryTicket = 0;
  /** 配送状态 1: 开始 2: 暂停 3: 完成 4: 取消 */
  private Short carryStatus;
  /** 配送启动时间 */
  private Date carryStart;
  /** 配送启动操作员 */
  private String carryUserid;
  /** 配送人员 */
  private String carryWorker;
  /** 配送时间 */
  private Date carryWorkday;
  /** 配送完成时间 */
  private Date carryComplete;

  private Date carryLastdate;
  /** 配送说明 */
  private String carryNote;
  private String flightNo;
  private Plan tour;
  private String workflowId;
  /** 制单时间 */
  private Date created;
  private Integer createdby;

  private Date opApproved;
  private Integer opApprovedby;
  /** 计调是否审核 */
  private Character opApprovedFlag;
  private Date frRead;
  private Integer frReadby;
  /** 财务人员已读 */
  private Character frReadFlag;
  private Date frApproved;
  private Integer frApprovedby;
  /** 财务是否审核 */
  private Character frApprovedFlag;
  private Date updated;
  private Integer updatedby;

  public Outcome() {
    customer = new Customer();
    opRoe = 1d;
    roe = 1d;
    currency = "RMB";
  }

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "OUTCOME_ID", unique = true, nullable = false)
  public Integer getOutcomeId() {
    return this.outcomeId;
  }

  public void setOutcomeId(Integer outcomeId) {
    this.outcomeId = outcomeId;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "CUSTOMER_ID")
  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  @Column(name = "TOUR_NO", length = 100)
  public String getTourNo() {
    return this.tourNo;
  }

  public void setTourNo(String tourNo) {
    this.tourNo = tourNo;
  }

  @Column(name = "NOTE", length = 1000)
  public String getNote() {
    return this.note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "PAY_DATE", length = 19)
  public Date getPayDate() {
    return this.payDate;
  }

  public void setPayDate(Date payDate) {
    this.payDate = payDate;
  }

  @Column(name = "PAY_MODE", length = 1)
  public Character getPayMode() {
    return this.payMode;
  }

  public void setPayMode(Character payMode) {
    this.payMode = payMode;
  }

  @Column(name = "BILL_NO", length = 30)
  public String getBillNo() {
    return this.billNo;
  }

  public void setBillNo(String billNo) {
    this.billNo = billNo;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "PAY_REGISTER_DATE", length = 19)
  public Date getPayRegisterDate() {
    return this.payRegisterDate;
  }

  public void setPayRegisterDate(Date payRegisterDate) {
    this.payRegisterDate = payRegisterDate;
  }

  @Column(name = "PAY_REGISTERBY", length = 20)
  public String getPayRegisterby() {
    return this.payRegisterby;
  }

  public void setPayRegisterby(String payRegisterby) {
    this.payRegisterby = payRegisterby;
  }

  @Column(name = "AMOUNT", precision = 10)
  public BigDecimal getAmount() {
    return this.amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  @Column(name = "CARRY_TICKET")
  public Short getCarryTicket() {
    return this.carryTicket;
  }

  public void setCarryTicket(Short carryTicket) {
    this.carryTicket = carryTicket;
  }

  @Column(name = "CARRY_STATUS")
  public Short getCarryStatus() {
    return this.carryStatus;
  }

  public void setCarryStatus(Short carryStatus) {
    this.carryStatus = carryStatus;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "CARRY_START", length = 19)
  public Date getCarryStart() {
    return this.carryStart;
  }

  public void setCarryStart(Date carryStart) {
    this.carryStart = carryStart;
  }

  @Column(name = "CARRY_USERID", length = 10)
  public String getCarryUserid() {
    return this.carryUserid;
  }

  public void setCarryUserid(String carryUserid) {
    this.carryUserid = carryUserid;
  }

  @Column(name = "CARRY_WORKER", length = 20)
  public String getCarryWorker() {
    return this.carryWorker;
  }

  public void setCarryWorker(String carryWorker) {
    this.carryWorker = carryWorker;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "CARRY_WORKDAY", length = 19)
  public Date getCarryWorkday() {
    return this.carryWorkday;
  }

  public void setCarryWorkday(Date carryWorkday) {
    this.carryWorkday = carryWorkday;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "CARRY_COMPLETE", length = 19)
  public Date getCarryComplete() {
    return this.carryComplete;
  }

  public void setCarryComplete(Date carryComplete) {
    this.carryComplete = carryComplete;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "CARRY_LASTDATE", length = 19)
  public Date getCarryLastdate() {
    return this.carryLastdate;
  }

  public void setCarryLastdate(Date carryLastdate) {
    this.carryLastdate = carryLastdate;
  }

  @Column(name = "CARRY_NOTE", length = 1000)
  public String getCarryNote() {
    return this.carryNote;
  }

  public void setCarryNote(String carryNote) {
    this.carryNote = carryNote;
  }

  @Column(name = "FLIGHT_NO", length = 10)
  public String getFlightNo() {
    return this.flightNo;
  }

  public void setFlightNo(String flightNo) {
    this.flightNo = flightNo;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "PLAN_NO")
  public Plan getTour() {
    return tour;
  }

  public void setTour(Plan tour) {
    this.tour = tour;
  }

  @Column(name = "WORKFLOW_ID")
  public String getWorkflowId() {
    return this.workflowId;
  }

  public void setWorkflowId(String workflowId) {
    this.workflowId = workflowId;
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
  public Integer getCreatedby() {
    return this.createdby;
  }

  public void setCreatedby(Integer createdby) {
    this.createdby = createdby;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "OP_APPROVED", length = 19)
  public Date getOpApproved() {
    return this.opApproved;
  }

  public void setOpApproved(Date opApproved) {
    this.opApproved = opApproved;
  }

  @Column(name = "OP_APPROVEDBY")
  public Integer getOpApprovedby() {
    return this.opApprovedby;
  }

  public void setOpApprovedby(Integer opApprovedby) {
    this.opApprovedby = opApprovedby;
  }

  @Column(name = "OP_APPROVED_FLAG", length = 1)
  public Character getOpApprovedFlag() {
    return this.opApprovedFlag;
  }

  public void setOpApprovedFlag(Character opApprovedFlag) {
    this.opApprovedFlag = opApprovedFlag;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "FR_READ", length = 19)
  public Date getFrRead() {
    return this.frRead;
  }

  public void setFrRead(Date frRead) {
    this.frRead = frRead;
  }

  @Column(name = "FR_READBY")
  public Integer getFrReadby() {
    return this.frReadby;
  }

  public void setFrReadby(Integer frReadby) {
    this.frReadby = frReadby;
  }

  @Column(name = "FR_READ_FLAG", length = 1)
  public Character getFrReadFlag() {
    return this.frReadFlag;
  }

  public void setFrReadFlag(Character frReadFlag) {
    this.frReadFlag = frReadFlag;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "FR_APPROVED", length = 19)
  public Date getFrApproved() {
    return this.frApproved;
  }

  public void setFrApproved(Date frApproved) {
    this.frApproved = frApproved;
  }

  @Column(name = "FR_APPROVEDBY")
  public Integer getFrApprovedby() {
    return this.frApprovedby;
  }

  public void setFrApprovedby(Integer frApprovedby) {
    this.frApprovedby = frApprovedby;
  }

  @Column(name = "FR_APPROVED_FLAG", length = 1)
  public Character getFrApprovedFlag() {
    return this.frApprovedFlag;
  }

  public void setFrApprovedFlag(Character frApprovedFlag) {
    this.frApprovedFlag = frApprovedFlag;
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
  public Integer getUpdatedby() {
    return this.updatedby;
  }

  public void setUpdatedby(Integer updatedby) {
    this.updatedby = updatedby;
  }

  /** 成本ID */
  private long acctId;

  /** 制单人 */
  private Long createdBy;

  private Long updatedBy;

  /** 币种 */
  private String currency;
  /** 外币金额 */
  private Double fcAmount;
  /** OP结算汇率 */
  private double opRoe;

  /** 财务结算汇率 */
  private double roe;

  private String del;

  /** 配送最后时间 */
  private Date carryLastDate;

  /** 机票配送航班号 */
  private String carryFlightNo;

  private int id;

  private String lineNo;

  private String lineName;

  /** 付款总金额中文大写 */
  private String amountCn;

  private String createdByName;

  private String opApprovedbyDptNo;

  private String opApprovedbyName;

  private String opApprovedbyDptName;

  private String frApprovedbyName;

  private String payModeName;

  /** 是否已登记 */
  private String isRegister;

  /** 是否能审核 */
  private Character isAuditing;

  /** 内容 */
  private String description;

  /** 现付款(外币) */
  private double fcNowpayPayment;

  /** 现付款(人民币) */
  private double nowpayPayment;

  /** 付款方式 */
  private String payWay;

  public Long getUpdatedBy() {
    return this.updatedBy;
  }

  public void setUpdatedBy(Long updatedby) {
    this.updatedBy = updatedby;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Long getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Long createdBy) {
    this.createdBy = createdBy;
  }

  public String getCreatedByName() {
    return createdByName;
  }

  public void setCreatedByName(String createdByName) {
    this.createdByName = createdByName;
  }

  public String getPayModeName() {
    return payModeName;
  }

  public void setPayModeName(String payModeName) {
    this.payModeName = payModeName;
  }

  public Character getIsAuditing() {
    return isAuditing;
  }

  public void setIsAuditing(Character isAuditing) {
    this.isAuditing = isAuditing;
  }

  public String getOpApprovedbyName() {
    return opApprovedbyName;
  }

  public void setOpApprovedbyName(String opApprovedbyName) {
    this.opApprovedbyName = opApprovedbyName;
  }

  public String getOpApprovedbyDptName() {
    return opApprovedbyDptName;
  }

  public void setOpApprovedbyDptName(String opApprovedbyDptName) {
    this.opApprovedbyDptName = opApprovedbyDptName;
  }

  public String getOpApprovedbyDptNo() {
    return opApprovedbyDptNo;
  }

  public void setOpApprovedbyDptNo(String opApprovedbyDptNo) {
    this.opApprovedbyDptNo = opApprovedbyDptNo;
  }

  public String getAmountCn() {
    return amountCn;
  }

  public void setAmountCn(String amountCn) {
    this.amountCn = amountCn;
  }

  public String getFrApprovedbyName() {
    return frApprovedbyName;
  }

  public void setFrApprovedbyName(String frApprovedbyName) {
    this.frApprovedbyName = frApprovedbyName;
  }

  public Source getSource() {
    return new SAXSource(new OutcomeXMLReader(), new OutcomeInputSource(this));
  }

  public String getIsRegister() {
    return isRegister;
  }

  public void setIsRegister(String isRegister) {
    this.isRegister = isRegister;
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

  public Date getCarryLastDate() {
    return carryLastDate;
  }

  public void setCarryLastDate(Date carryLastDate) {
    this.carryLastDate = carryLastDate;
  }

  public String getCarryFlightNo() {
    return carryFlightNo;
  }

  public void setCarryFlightNo(String carryFlightNo) {
    this.carryFlightNo = carryFlightNo;
  }

  public String getLineNo() {
    return lineNo;
  }

  public void setLineNo(String lineNo) {
    this.lineNo = lineNo;
  }

  public String getLineName() {
    return lineName;
  }

  public void setLineName(String lineName) {
    this.lineName = lineName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public double getFcNowpayPayment() {
    return fcNowpayPayment;
  }

  public void setFcNowpayPayment(double fcNowpayPayment) {
    this.fcNowpayPayment = fcNowpayPayment;
  }

  public double getNowpayPayment() {
    return nowpayPayment;
  }

  public void setNowpayPayment(double nowpayPayment) {
    this.nowpayPayment = nowpayPayment;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public double getOpRoe() {
    return opRoe;
  }

  public void setOpRoe(double opRoe) {
    this.opRoe = opRoe;
  }

  public double getRoe() {
    return roe;
  }

  public void setRoe(double roe) {
    this.roe = roe;
  }

  public String getPayWay() {
    return payWay;
  }

  public void setPayWay(String payWay) {
    this.payWay = payWay;
  }

  public Double getFcAmount() {
    return fcAmount;
  }

  public void setFcAmount(Double fcAmount) {
    this.fcAmount = fcAmount;
  }

  public long getAcctId() {
    return acctId;
  }

  public void setAcctId(long acctId) {
    this.acctId = acctId;
  }

  public String getDel() {
    return del;
  }

  public void setDel(String del) {
    this.del = del;
  }

}
