package com.opentravelsoft.entity.finance;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.opentravelsoft.util.ChineseMoney;

@Entity
@Table(name = "tbl_invoice", catalog = "tourismwork_db")
public class Invoice implements java.io.Serializable {

  private String recNo;
  private String invNo;
  /** 收款账单单号 TBL_INCOME INCOME_ID(primary key) */
  private Integer incomeId;
  private Date prtDate;
  private String customs;
  private String exp1;
  private BigDecimal amount1;
  private String exp2;
  private BigDecimal amount2;
  private String exp3;
  private BigDecimal amount3;
  private String exp4;
  private BigDecimal amount4;
  private Character type1;
  private BigDecimal pamount1;
  private Character type2;
  private BigDecimal pamount2;
  private Character type3;
  private BigDecimal pamount3;
  private Character type4;
  private BigDecimal pamount4;
  private String type5;
  private BigDecimal pamount5;
  private String remarks;
  private Byte del;
  /** 经办人 */
  private String signature;
  /** 出纳 */
  private String casher;
  private Integer opUser;

  public Invoice() {
    this.del = 0;
  }

  public Invoice(String recNo) {
    this.recNo = recNo;
  }

  @Id
  @Column(name = "REC_NO", unique = true, nullable = false, length = 10)
  public String getRecNo() {
    return this.recNo;
  }

  public void setRecNo(String recNo) {
    this.recNo = recNo;
  }

  @Column(name = "INV_NO", length = 12)
  public String getInvNo() {
    return this.invNo;
  }

  public void setInvNo(String invNo) {
    this.invNo = invNo;
  }

  @Column(name = "INCOME_ID")
  public Integer getIncomeId() {
    return this.incomeId;
  }

  public void setIncomeId(Integer incomeId) {
    this.incomeId = incomeId;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "PRT_DATE", length = 19)
  public Date getPrtDate() {
    return this.prtDate;
  }

  public void setPrtDate(Date prtDate) {
    this.prtDate = prtDate;
  }

  @Column(name = "CUSTOMS", length = 70)
  public String getCustoms() {
    return this.customs;
  }

  public void setCustoms(String customs) {
    this.customs = customs;
  }

  @Column(name = "EXP1", length = 60)
  public String getExp1() {
    return this.exp1;
  }

  public void setExp1(String exp1) {
    this.exp1 = exp1;
  }

  @Column(name = "AMOUNT1", precision = 18)
  public BigDecimal getAmount1() {
    return this.amount1;
  }

  public void setAmount1(BigDecimal amount1) {
    this.amount1 = amount1;
  }

  @Column(name = "EXP2", length = 60)
  public String getExp2() {
    return this.exp2;
  }

  public void setExp2(String exp2) {
    this.exp2 = exp2;
  }

  @Column(name = "AMOUNT2", precision = 18)
  public BigDecimal getAmount2() {
    return this.amount2;
  }

  public void setAmount2(BigDecimal amount2) {
    this.amount2 = amount2;
  }

  @Column(name = "EXP3", length = 60)
  public String getExp3() {
    return this.exp3;
  }

  public void setExp3(String exp3) {
    this.exp3 = exp3;
  }

  @Column(name = "AMOUNT3", precision = 18)
  public BigDecimal getAmount3() {
    return this.amount3;
  }

  public void setAmount3(BigDecimal amount3) {
    this.amount3 = amount3;
  }

  @Column(name = "EXP4", length = 60)
  public String getExp4() {
    return this.exp4;
  }

  public void setExp4(String exp4) {
    this.exp4 = exp4;
  }

  @Column(name = "AMOUNT4", precision = 18)
  public BigDecimal getAmount4() {
    return this.amount4;
  }

  public void setAmount4(BigDecimal amount4) {
    this.amount4 = amount4;
  }

  @Column(name = "TYPE1", length = 1)
  public Character getType1() {
    return this.type1;
  }

  public void setType1(Character type1) {
    this.type1 = type1;
  }

  @Column(name = "PAMOUNT1", precision = 18)
  public BigDecimal getPamount1() {
    return this.pamount1;
  }

  public void setPamount1(BigDecimal pamount1) {
    this.pamount1 = pamount1;
  }

  @Column(name = "TYPE2", length = 1)
  public Character getType2() {
    return this.type2;
  }

  public void setType2(Character type2) {
    this.type2 = type2;
  }

  @Column(name = "PAMOUNT2", precision = 18)
  public BigDecimal getPamount2() {
    return this.pamount2;
  }

  public void setPamount2(BigDecimal pamount2) {
    this.pamount2 = pamount2;
  }

  @Column(name = "TYPE3", length = 1)
  public Character getType3() {
    return this.type3;
  }

  public void setType3(Character type3) {
    this.type3 = type3;
  }

  @Column(name = "PAMOUNT3", precision = 18)
  public BigDecimal getPamount3() {
    return this.pamount3;
  }

  public void setPamount3(BigDecimal pamount3) {
    this.pamount3 = pamount3;
  }

  @Column(name = "TYPE4", length = 1)
  public Character getType4() {
    return this.type4;
  }

  public void setType4(Character type4) {
    this.type4 = type4;
  }

  @Column(name = "PAMOUNT4", precision = 18)
  public BigDecimal getPamount4() {
    return this.pamount4;
  }

  public void setPamount4(BigDecimal pamount4) {
    this.pamount4 = pamount4;
  }

  @Column(name = "TYPE5", length = 1)
  public String getType5() {
    return this.type5;
  }

  public void setType5(String type5) {
    this.type5 = type5;
  }

  @Column(name = "PAMOUNT5", precision = 18)
  public BigDecimal getPamount5() {
    return this.pamount5;
  }

  public void setPamount5(BigDecimal pamount5) {
    this.pamount5 = pamount5;
  }

  @Column(name = "REMARKS", length = 100)
  public String getRemarks() {
    return this.remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  @Column(name = "DEL")
  public Byte getDel() {
    return this.del;
  }

  public void setDel(Byte del) {
    this.del = del;
  }

  @Column(name = "SIGNATURE", length = 20)
  public String getSignature() {
    return this.signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }

  @Column(name = "CASHER", length = 20)
  public String getCasher() {
    return this.casher;
  }

  public void setCasher(String casher) {
    this.casher = casher;
  }

  @Column(name = "OP_USER")
  public Integer getOpUser() {
    return this.opUser;
  }

  public void setOpUser(Integer opUser) {
    this.opUser = opUser;
  }

  private String recordNo;

  private String customer;

  /** 支付订单号 TR_J_026 RECNO(primary key) */
  private String invoiceNo;

  private double amount;

  private Date crateDate;

  private Long createUser;

  /** 大写金额 */
  private String amountChinese;

  private List<InvoiceItem> items = new ArrayList<InvoiceItem>();

  private List<InvoicePiece> pieces = new ArrayList<InvoicePiece>();

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
    this.amountChinese = ChineseMoney.getUpperMoney(amount);
  }

  public String getCustomer() {
    return customer;
  }

  public void setCustomer(String customer) {
    this.customer = customer;
  }

  public String getInvoiceNo() {
    return invoiceNo;
  }

  public void setInvoiceNo(String invoiceNo) {
    this.invoiceNo = invoiceNo;
  }

  public String getRecordNo() {
    return recordNo;
  }

  public void setRecordNo(String recordNo) {
    this.recordNo = recordNo;
  }

  public Date getCrateDate() {
    return crateDate;
  }

  public void setCrateDate(Date crateDate) {
    this.crateDate = crateDate;
  }

  public Long getCreateUser() {
    return createUser;
  }

  public void setCreateUser(Long createUser) {
    this.createUser = createUser;
  }

  public List<InvoiceItem> getItems() {
    return items;
  }

  public void setItems(List<InvoiceItem> items) {
    this.items = items;
  }

  public void addItem(String item, double expense) {
    items.add(new InvoiceItem(item, expense));

  }

  public List<InvoicePiece> getPieces() {
    return pieces;
  }

  public void setPieces(List<InvoicePiece> pieces) {
    this.pieces = pieces;
  }

  public void addPiece(String type, double expense) {
    pieces.add(new InvoicePiece(type, expense));

  }

  public String getAmountChinese() {
    return amountChinese;
  }

}
