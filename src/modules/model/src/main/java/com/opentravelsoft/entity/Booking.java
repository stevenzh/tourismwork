package com.opentravelsoft.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.opentravelsoft.entity.finance.Income;
import com.opentravelsoft.entity.finance.Invoice;

/**
 * 订单
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:33 $
 */
public class Booking extends BaseObject implements Cloneable {

  private static final long serialVersionUID = -1149976893867767506L;

  /** 订单号 */
  private String nameNo;

  /** 出团计划编号 */
  private Plan plan;

  /** 负责订单的组 */
  private Team team;

  /** 跟单人 */
  private Employee assigned;

  /** 客户 */
  private Customer customer;

  /** 联系人 */
  private String contact;

  /** 联系电话 */
  private String phone;

  /** 预订人数 */
  private int pax;

  /** 确认人数 */
  private Integer confirmPax;

  /** 应收款 */
  private Double dbamt;

  /** 已收款 */
  private Double cramt;

  /** 参团集合 A-个人,B-团体,C-商务团,D-代理商 */
  private String namekey;

  /** 来源 团体/代理商 */
  private String tourK1;

  private String delkey;

  private Long opuser;

  private Date opDate;

  /** 预订人 */
  private Long reserve;

  /** 预订日期 */
  private Date reserveDate;

  private Employee salesman;

  /** 合同 */
  private String pactNo;

  /** 是否可拆分 */
  private String tourKey;

  /** 备注 */
  private String remarks;

  /** 确认状态 1:团确 2:团候 */
  private String confirmStatus;

  /** 付款方式 */
  private String invKey;

  private String invUser;

  private Date invDate;

  /** 是否以阅读 */
  private String readKey;

  private Long readUser;

  private Date readDate;

  /** 预订单来源 */
  private String planKey;

  /** 调整金额 */
  private Double finalAmount;

  /** 调整人 */
  private Long finalUser;

  /** 调整日期 */
  private Date finalDate;

  /** 调整原因 */
  private String finalNote;

  // ----------------------------------
  /** 是否担保 */
  private String warrantFlag;

  /** 担保人 */
  private String warrantBy;

  /** 担保日期 */
  private Date warrantDate;

  /** 担保金额 */
  private Double warrantMoney;

  /** 最终付款时间 */
  private Date lastPayDate;
  // ----------------------------------

  /**
   * 订单来源 A-后台,W-网站,S-分销
   */
  private String source;

  private String workflowId;

  private String checkKey;

  private Double expAmt;

  /** 订单状态 */
  private String orderState;

  private Integer isAgreeon;

  private Long memberId;

  // -------------------------------------------------------------------------

  /** 序号 */
  private int id;

  /** 是否可拆分 */
  private String canSplit;

  /** 客户月结/现结 */
  private String clearingCycle;

  /** 付款方式 */
  private String paymentType;

  /** 最终费用 */
  private Double finalExpense;

  /** 已付费用 */
  private Double payCosts;

  /** 未付费用 */
  private Double unPay;

  /** 现付费用 */
  private Double payBack;

  /** 付款状态 */
  private String paymentStatus;

  /** 占位状态 */
  private String placeStatus;

  /** 标记（可能是是否成团） */
  private String nameKey;

  /** 调整金额为 */
  private Double adjustExpense;

  /** 调整原因 */
  private String adjustReason;

  /** 上次调整人 */
  private Long lastAdjustBy;

  /** 上次调整时间 */
  private Date lastAdjustDate;

  private int isSuccess;

  // ----------------------------------
  // 订单收款

  /** 收款日期 */
  private Date incomeDate;

  /** 收款人 */
  private String receiver;

  // ----------------------------------
  // 代理商应收团款情况一览
  /** 代理商所在省份ID */
  private String region;

  /** 批数 */
  private int batch;

  /** 目的地编号 */
  private String districtNo;

  /** 国家 */
  private String country;

  /** 目的地 */
  private String district;

  /** 总人数 */
  private int sumpax;

  /** 总应收 */
  private Double sumDbamt;

  /** 总已收 */
  private Double sumCramt;

  /** 总未收 */
  private Double sumUnpay;

  private String leaders;

  /** 出团通知 */
  private String tourNoticeFilepath;

  /** 是否存在出团通知 */
  private String tourNoticeIsExist;

  // ----------------------------------

  /** 客人明晰 */
  private List<Tourist> customerList;

  /**
   * 付款记录
   */
  private List<Income> payments;

  /** 发票记录 */
  private List<Invoice> invices;

  // ----------------------------------
  // 配送任务
  /** 开始时间 */
  private Date start;

  /** 结束时间 */
  private Date end;

  private String info;

  private String checkOp;

  private Date checkDate;

  private String placeType;

  private Date updDate;

  private Date delDate;

  private String remark;

  public Booking() {
    confirmStatus = "2";
    delkey = "N";
    readKey = "N";
    customerList = new ArrayList<Tourist>();
    payments = new ArrayList<Income>();
    invices = new ArrayList<Invoice>();
    plan = new Plan();
    customer = new Customer();
    salesman = new Employee();
    payCosts = 0d;
  }

  @Override
  public Booking clone() throws CloneNotSupportedException {
    return (Booking) super.clone();
  }

  public String getNameNo() {
    return this.nameNo;
  }

  public void setNameNo(String nameNo) {
    this.nameNo = nameNo;
  }

  public Long getMemberId() {
    return memberId;
  }

  public void setMemberId(Long memberId) {
    this.memberId = memberId;
  }

  public Double getCramt() {
    return this.cramt;
  }

  public void setCramt(Double cramt) {
    this.cramt = cramt;
  }

  public String getNamekey() {
    return this.namekey;
  }

  public void setNamekey(String namekey) {
    this.namekey = namekey;
  }

  public String getTourK1() {
    return this.tourK1;
  }

  public void setTourK1(String tourK1) {
    this.tourK1 = tourK1;
  }

  public String getDelkey() {
    return this.delkey;
  }

  public void setDelkey(String delkey) {
    this.delkey = delkey;
  }

  public Date getOpDate() {
    return opDate;
  }

  public void setOpDate(Date opDate) {
    this.opDate = opDate;
  }

  public Long getReserve() {
    return this.reserve;
  }

  public void setReserve(Long receive) {
    this.reserve = receive;
  }

  public Employee getSalesman() {
    return this.salesman;
  }

  public void setSalesman(Employee salesman) {
    this.salesman = salesman;
  }

  public String getPactNo() {
    return this.pactNo;
  }

  public void setPactNo(String pactNo) {
    this.pactNo = pactNo;
  }

  public String getTourKey() {
    return this.tourKey;
  }

  public void setTourKey(String tourKey) {
    this.tourKey = tourKey;
  }

  public int getPax() {
    return pax;
  }

  public void setPax(int pax) {
    this.pax = pax;
  }

  public Integer getConfirmPax() {
    return this.confirmPax;
  }

  public void setConfirmPax(Integer confirmPax) {
    this.confirmPax = confirmPax;
  }

  public String getConfirmStatus() {
    return this.confirmStatus;
  }

  public void setConfirmStatus(String cfmKey) {
    this.confirmStatus = cfmKey;
  }

  public String getInvKey() {
    return this.invKey;
  }

  public void setInvKey(String invKey) {
    this.invKey = invKey;
  }

  public String getInvUser() {
    return this.invUser;
  }

  public void setInvUser(String invUser) {
    this.invUser = invUser;
  }

  public Date getInvDate() {
    return this.invDate;
  }

  public void setInvDate(Date invDate) {
    this.invDate = invDate;
  }

  public Long getReadUser() {
    return this.readUser;
  }

  public void setReadUser(Long readUser) {
    this.readUser = readUser;
  }

  public Date getReadDate() {
    return this.readDate;
  }

  public void setReadDate(Date readDate) {
    this.readDate = readDate;
  }

  public String getPlanKey() {
    return this.planKey;
  }

  public void setPlanKey(String planKey) {
    this.planKey = planKey;
  }

  public Double getFinalAmount() {
    return this.finalAmount;
  }

  public void setFinalAmount(Double finalAmount) {
    this.finalAmount = finalAmount;
  }

  public Long getFinalUser() {
    return this.finalUser;
  }

  public void setFinalUser(Long finalUser) {
    this.finalUser = finalUser;
  }

  public Date getFinalDate() {
    return this.finalDate;
  }

  public void setFinalDate(Date finalDate) {
    this.finalDate = finalDate;
  }

  public String getFinalNote() {
    return this.finalNote;
  }

  public void setFinalNote(String finalNote) {
    this.finalNote = finalNote;
  }

  public String getWarrantFlag() {
    return this.warrantFlag;
  }

  public void setWarrantFlag(String warrantFlag) {
    this.warrantFlag = warrantFlag;
  }

  public String getWarrantBy() {
    return this.warrantBy;
  }

  public void setWarrantBy(String warrantBy) {
    this.warrantBy = warrantBy;
  }

  public Date getWarrantDate() {
    return this.warrantDate;
  }

  public void setWarrantDate(Date warrantDate) {
    this.warrantDate = warrantDate;
  }

  public Date getLastPayDate() {
    return this.lastPayDate;
  }

  public void setLastPayDate(Date lastPayDate) {
    this.lastPayDate = lastPayDate;
  }

  public Double getWarrantMoney() {
    return this.warrantMoney;
  }

  public void setWarrantMoney(Double warrantMoney) {
    this.warrantMoney = warrantMoney;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getWorkflowId() {
    return workflowId;
  }

  public void setWorkflowId(String workflowId) {
    this.workflowId = workflowId;
  }

  public String getCheckKey() {
    return checkKey;
  }

  public void setCheckKey(String checkKey) {
    this.checkKey = checkKey;
  }

  public Double getExpAmt() {
    return expAmt;
  }

  public void setExpAmt(Double expAmt) {
    this.expAmt = expAmt;
  }

  public String getOrderState() {
    return orderState;
  }

  public void setOrderState(String orderState) {
    this.orderState = orderState;
  }

  public Integer getIsAgreeon() {
    return isAgreeon;
  }

  public void setIsAgreeon(Integer isAgreeon) {
    this.isAgreeon = isAgreeon;
  }

  public String getCanSplit() {
    return canSplit;
  }

  public void setCanSplit(String canSplit) {
    this.canSplit = canSplit;
  }

  public String getContact() {
    return contact;
  }

  public void setContact(String linkman) {
    this.contact = linkman;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String linkPhone) {
    this.phone = linkPhone;
  }

  public Date getReserveDate() {
    return reserveDate;
  }

  public void setReserveDate(Date reserveDate) {
    this.reserveDate = reserveDate;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public List<Tourist> getCustomerList() {
    return customerList;
  }

  public void setCustomerList(List<Tourist> customerList) {
    this.customerList = customerList;
  }

  public void addCustomer(Tourist tourist) {
    customerList.add(tourist);
  }

  public Long getOpuser() {
    return opuser;
  }

  public void setOpuser(Long operator) {
    this.opuser = operator;
  }

  public Double getDbamt() {
    return dbamt;
  }

  public void setDbamt(Double expense) {
    this.dbamt = expense;
  }

  public Double getPayCosts() {
    return payCosts;
  }

  public void setPayCosts(Double payCosts) {
    this.payCosts = payCosts;
  }

  public String getPaymentStatus() {
    return paymentStatus;
  }

  public void setPaymentStatus(String paymentStatus) {
    this.paymentStatus = paymentStatus;
  }

  public String getPlaceStatus() {
    return placeStatus;
  }

  public void setPlaceStatus(String placeStatus) {
    this.placeStatus = placeStatus;
  }

  public String getPaymentType() {
    return paymentType;
  }

  public void setPaymentType(String paymentType) {
    this.paymentType = paymentType;
  }

  public List<Invoice> getInvices() {
    return invices;
  }

  public void setInvices(List<Invoice> invices) {
    this.invices = invices;
  }

  /**
   * @return
   */
  public List<Income> getPayments() {
    return payments;
  }

  /**
   * @param payments
   */
  public void setPayments(List<Income> payments) {
    this.payments = payments;
  }

  public String getReadKey() {
    return readKey;
  }

  public void setReadKey(String readKey) {
    this.readKey = readKey;
  }

  public String getNameKey() {
    return nameKey;
  }

  public void setNameKey(String nameKey) {
    this.nameKey = nameKey;
  }

  public Double getPayBack() {
    return payBack;
  }

  public void setPayBack(Double payBack) {
    this.payBack = payBack;
  }

  public Double getUnPay() {
    return unPay;
  }

  public void setUnPay(Double unPay) {
    this.unPay = unPay;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Double getAdjustExpense() {
    return adjustExpense;
  }

  public void setAdjustExpense(Double adjustExpense) {
    this.adjustExpense = adjustExpense;
  }

  public String getAdjustReason() {
    return adjustReason;
  }

  public void setAdjustReason(String adjustReason) {
    this.adjustReason = adjustReason;
  }

  public Long getLastAdjustBy() {
    return lastAdjustBy;
  }

  public void setLastAdjustBy(Long lastAdjustBy) {
    this.lastAdjustBy = lastAdjustBy;
  }

  public Date getLastAdjustDate() {
    return lastAdjustDate;
  }

  public void setLastAdjustDate(Date lastAdjustDate) {
    this.lastAdjustDate = lastAdjustDate;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customerId) {
    this.customer = customerId;
  }

  public int getIsSuccess() {
    return isSuccess;
  }

  public void setIsSuccess(int isSuccess) {
    this.isSuccess = isSuccess;
  }

  public String getLeaders() {
    return leaders;
  }

  public void setLeaders(String leaders) {
    this.leaders = leaders;
  }

  public Double getFinalExpense() {
    return finalExpense;
  }

  public void setFinalExpense(Double finalExpense) {
    this.finalExpense = finalExpense;
  }

  public String getClearingCycle() {
    return clearingCycle;
  }

  public void setClearingCycle(String clearingCycle) {
    this.clearingCycle = clearingCycle;
  }

  public int getBatch() {
    return batch;
  }

  public void setBatch(int batch) {
    this.batch = batch;
  }

  public int getSumpax() {
    return sumpax;
  }

  public void setSumpax(int sumpax) {
    this.sumpax = sumpax;
  }

  public Double getSumDbamt() {
    return sumDbamt;
  }

  public void setSumDbamt(Double sumDbamt) {
    this.sumDbamt = sumDbamt;
  }

  public Double getSumCramt() {
    return sumCramt;
  }

  public void setSumCramt(Double sumCramt) {
    this.sumCramt = sumCramt;
  }

  public Double getSumUnpay() {
    return sumUnpay;
  }

  public void setSumUnpay(Double sumUnpay) {
    this.sumUnpay = sumUnpay;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public String getDistrict() {
    return district;
  }

  public void setDistrict(String district) {
    this.district = district;
  }

  public String getDistrictNo() {
    return districtNo;
  }

  public void setDistrictNo(String districtNo) {
    this.districtNo = districtNo;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getTourNoticeFilepath() {
    return tourNoticeFilepath;
  }

  public void setTourNoticeFilepath(String tourNoticeFilepath) {
    this.tourNoticeFilepath = tourNoticeFilepath;
  }

  public String getTourNoticeIsExist() {
    return tourNoticeIsExist;
  }

  public void setTourNoticeIsExist(String tourNoticeIsExist) {
    this.tourNoticeIsExist = tourNoticeIsExist;
  }

  public Date getIncomeDate() {
    return incomeDate;
  }

  public void setIncomeDate(Date incomeDate) {
    this.incomeDate = incomeDate;
  }

  public String getReceiver() {
    return receiver;
  }

  public void setReceiver(String receiver) {
    this.receiver = receiver;
  }

  public Date getStart() {
    return start;
  }

  public void setStart(Date start) {
    this.start = start;
  }

  public Date getEnd() {
    return end;
  }

  public void setEnd(Date end) {
    this.end = end;
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  public String getBookingNo() {
    return nameNo;
  }

  public void setBookingNo(String bookingNo) {
    this.nameNo = bookingNo;
  }

  public Plan getPlan() {
    return plan;
  }

  public void setPlan(Plan plan) {
    this.plan = plan;
  }

  public String getCheckOp() {
    return checkOp;
  }

  public void setCheckOp(String checkOp) {
    this.checkOp = checkOp;
  }

  public Date getCheckDate() {
    return checkDate;
  }

  public void setCheckDate(Date checkDate) {
    this.checkDate = checkDate;
  }

  public String getPlaceType() {
    return placeType;
  }

  public void setPlaceType(String placeType) {
    this.placeType = placeType;
  }

  public Date getUpdDate() {
    return updDate;
  }

  public void setUpdDate(Date updDate) {
    this.updDate = updDate;
  }

  public Date getDelDate() {
    return delDate;
  }

  public void setDelDate(Date delDate) {
    this.delDate = delDate;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  public Employee getAssigned() {
    return assigned;
  }

  public void setAssigned(Employee assigned) {
    this.assigned = assigned;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Booking)) {
      return false;
    }

    final Booking booking = (Booking) o;
    return this.hashCode() == booking.hashCode();
  }

  @Override
  public int hashCode() {
    int result;
    result = (nameNo != null ? nameNo.hashCode() : 0);
    result = 29 * result
        + (getCustomer() != null ? getCustomer().hashCode() : 0);
    result = 29 * result + (getPlan() != null ? getPlan().hashCode() : 0);
    result = 29 * result + (contact != null ? contact.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("bookingNo", this.nameNo)
        .append("planNo", this.getPlan().getPlanNo())
        .append("customer", this.getCustomer().getName())
        .append("contact", this.contact).toString();
  }

}
