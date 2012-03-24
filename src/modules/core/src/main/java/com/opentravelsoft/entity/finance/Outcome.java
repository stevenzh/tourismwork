package com.opentravelsoft.entity.finance;

import java.util.Date;

import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;

import com.opentravelsoft.entity.Customer;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.xml.OutcomeInputSource;
import com.opentravelsoft.entity.xml.OutcomeXMLReader;

/**
 * 付款申请书
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:32 $
 */
public class Outcome {

  /** 付款申请书号 */
  private long outcomeId;

  private Customer customer;

  private Plan tour;

  /** 成本ID */
  private long acctId;

  /** 中段说明 */
  private String note;

  /** 制单时间 */
  private Date created;

  /** 制单人 */
  private Long createdBy;

  private Date opApproved;

  private Long opApprovedby;

  /** 计调是否审核 */
  private Character opApprovedFlag;

  private Date frRead;

  private Long frReadby;

  /** 财务人员已读 */
  private Character frReadFlag;

  private Date frApproved;

  private Long frApprovedby;

  /** 财务是否审核 */
  private Character frApprovedFlag;

  private Date payDate;

  private Character payMode;

  private Date billNo;

  /** 支付登记日期 */
  private Date payRegisterDate;

  private String payRegisterby;

  /** 付款总金额 */
  private Double amount;

  private Date updated;

  private Long updatedBy;

  /** 币种 */
  private String currency;
  /** 外币金额 */
  private Double fcAmount;
  /** OP结算汇率 */
  private double opRoe;

  /** 财务结算汇率 */
  private double roe;

  private String workflowId;

  private String del;
  // ----------------------------------
  /** 票务配送 */
  private short carryTicket = 0;

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

  /** 配送最后时间 */
  private Date carryLastDate;

  /** 配送说明 */
  private String carryNote;

  /** 机票配送航班号 */
  private String carryFlightNo;

  private String flightNo;

  // --------------------------------------------------------------------------

  private int id;

  /** 团号 */
  private String tourNo;

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

  private Date carryLastdate;

  // -------------------------------------------------------------------------

  /** 内容 */
  private String description;

  /** 现付款(外币) */
  private double fcNowpayPayment;

  /** 现付款(人民币) */
  private double nowpayPayment;

  /** 付款方式 */
  private String payWay;

  public Outcome() {
    customer = new Customer();
    opRoe = 1d;
    roe = 1d;
    currency = "RMB";
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Long getUpdatedBy() {
    return this.updatedBy;
  }

  public void setUpdatedBy(Long updatedby) {
    this.updatedBy = updatedby;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public Date getCarryLastdate() {
    return this.carryLastdate;
  }

  public void setCarryLastdate(Date carryLastdate) {
    this.carryLastdate = carryLastdate;
  }

  public String getFlightNo() {
    return this.flightNo;
  }

  public void setFlightNo(String flightNo) {
    this.flightNo = flightNo;
  }

  public Plan getTour() {
    return tour;
  }

  public void setTour(Plan tour) {
    this.tour = tour;
  }

  public String getWorkflowId() {
    return workflowId;
  }

  public void setWorkflowId(String workflowId) {
    this.workflowId = workflowId;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public long getOutcomeId() {
    return outcomeId;
  }

  public void setOutcomeId(long outcomeId) {
    this.outcomeId = outcomeId;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
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

  public Date getOpApproved() {
    return opApproved;
  }

  public void setOpApproved(Date opApproved) {
    this.opApproved = opApproved;
  }

  public Long getOpApprovedby() {
    return opApprovedby;
  }

  public void setOpApprovedby(Long opApprovedby) {
    this.opApprovedby = opApprovedby;
  }

  public Character getOpApprovedFlag() {
    return opApprovedFlag;
  }

  public void setOpApprovedFlag(Character opApprovedFlag) {
    this.opApprovedFlag = opApprovedFlag;
  }

  public Date getFrApproved() {
    return frApproved;
  }

  public void setFrApproved(Date frApproved) {
    this.frApproved = frApproved;
  }

  public Long getFrApprovedby() {
    return frApprovedby;
  }

  public void setFrApprovedby(Long frApprovedby) {
    this.frApprovedby = frApprovedby;
  }

  public Character getFrApprovedFlag() {
    return frApprovedFlag;
  }

  public void setFrApprovedFlag(Character frApprovedFlag) {
    this.frApprovedFlag = frApprovedFlag;
  }

  public Date getPayDate() {
    return payDate;
  }

  public void setPayDate(Date payDate) {
    this.payDate = payDate;
  }

  public Character getPayMode() {
    return payMode;
  }

  public void setPayMode(Character payMode) {
    this.payMode = payMode;
  }

  public Date getBillNo() {
    return billNo;
  }

  public void setBillNo(Date billNo) {
    this.billNo = billNo;
  }

  public Date getPayRegisterDate() {
    return payRegisterDate;
  }

  public void setPayRegisterDate(Date payRegisterDate) {
    this.payRegisterDate = payRegisterDate;
  }

  public String getPayRegisterby() {
    return payRegisterby;
  }

  public void setPayRegisterby(String payRegisterby) {
    this.payRegisterby = payRegisterby;
  }

  public Date getUpdated() {
    return updated;
  }

  public void setUpdated(Date updated) {
    this.updated = updated;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public double getAmount() {
    return amount;
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

  public String getTourNo() {
    return tourNo;
  }

  public void setTourNo(String tourNo) {
    this.tourNo = tourNo;
  }

  public Date getFrRead() {
    return frRead;
  }

  public void setFrRead(Date frRead) {
    this.frRead = frRead;
  }

  public Long getFrReadby() {
    return frReadby;
  }

  public void setFrReadby(Long frReadby) {
    this.frReadby = frReadby;
  }

  public Character getFrReadFlag() {
    return frReadFlag;
  }

  public void setFrReadFlag(Character frReadFlag) {
    this.frReadFlag = frReadFlag;
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

  public short getCarryTicket() {
    return carryTicket;
  }

  public void setCarryTicket(short carryTicket) {
    this.carryTicket = carryTicket;
  }

  public Short getCarryStatus() {
    return carryStatus;
  }

  public void setCarryStatus(Short carryStatus) {
    this.carryStatus = carryStatus;
  }

  public Date getCarryStart() {
    return carryStart;
  }

  public void setCarryStart(Date carryStart) {
    this.carryStart = carryStart;
  }

  public String getCarryUserid() {
    return carryUserid;
  }

  public void setCarryUserid(String carryUserid) {
    this.carryUserid = carryUserid;
  }

  public String getCarryWorker() {
    return carryWorker;
  }

  public void setCarryWorker(String carryWorker) {
    this.carryWorker = carryWorker;
  }

  public Date getCarryWorkday() {
    return carryWorkday;
  }

  public void setCarryWorkday(Date carryWorkday) {
    this.carryWorkday = carryWorkday;
  }

  public Date getCarryComplete() {
    return carryComplete;
  }

  public void setCarryComplete(Date carryComplete) {
    this.carryComplete = carryComplete;
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

  public String getCarryNote() {
    return carryNote;
  }

  public void setCarryNote(String carryNote) {
    this.carryNote = carryNote;
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
