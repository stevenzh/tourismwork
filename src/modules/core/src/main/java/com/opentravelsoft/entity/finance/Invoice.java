package com.opentravelsoft.entity.finance;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.opentravelsoft.util.ChineseMoney;

/**
 * 发票
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:32 $
 */
public class Invoice implements java.io.Serializable {

  private static final long serialVersionUID = -4066852647517900250L;

  private String recordNo;

  private String customer;

  /** 支付订单号 TR_J_026 RECNO(primary key) */
  private String invoiceNo;

  /** 收款账单单号 TBL_INCOME INCOME_ID(primary key) */
  private Long incomeId;

  private double amount;

  private Date crateDate;

  private Long createUser;

  private String remarks;

  /** 大写金额 */
  private String amountChinese;

  /** 出纳 */
  private String casher;

  /** 经办人 */
  private String signature;

  private List<InvoiceItem> items = new ArrayList<InvoiceItem>();

  private List<InvoicePiece> pieces = new ArrayList<InvoicePiece>();

  private String recNo;

  private Date prtDate;

  private String customs;

  private String exp1;

  private Double amount1;

  private String exp2;

  private Double amount2;

  private String exp3;

  private Double amount3;

  private String exp4;

  private Double amount4;

  private Character type1;

  private Double pamount1;

  private Character type2;

  private Double pamount2;

  private Character type3;

  private Double pamount3;

  private Character type4;

  private Double pamount4;

  private Character type5;

  private Double pamount5;

  private Long opUser;

  private Integer del;

  public Invoice() {
    this.del = 0;
  }

  public String getRecNo() {
    return this.recNo;
  }

  public void setRecNo(String recNo) {
    this.recNo = recNo;
  }

  public Date getPrtDate() {
    return this.prtDate;
  }

  public void setPrtDate(Date prtDate) {
    this.prtDate = prtDate;
  }

  public String getCustoms() {
    return this.customs;
  }

  public void setCustoms(String customs) {
    this.customs = customs;
  }

  public String getExp1() {
    return this.exp1;
  }

  public void setExp1(String exp1) {
    this.exp1 = exp1;
  }

  public Double getAmount1() {
    return this.amount1;
  }

  public void setAmount1(Double amount1) {
    this.amount1 = amount1;
  }

  public String getExp2() {
    return this.exp2;
  }

  public void setExp2(String exp2) {
    this.exp2 = exp2;
  }

  public Double getAmount2() {
    return this.amount2;
  }

  public void setAmount2(Double amount2) {
    this.amount2 = amount2;
  }

  public String getExp3() {
    return this.exp3;
  }

  public void setExp3(String exp3) {
    this.exp3 = exp3;
  }

  public Double getAmount3() {
    return this.amount3;
  }

  public void setAmount3(Double amount3) {
    this.amount3 = amount3;
  }

  public String getExp4() {
    return this.exp4;
  }

  public void setExp4(String exp4) {
    this.exp4 = exp4;
  }

  public Double getAmount4() {
    return this.amount4;
  }

  public void setAmount4(Double amount4) {
    this.amount4 = amount4;
  }

  public Character getType1() {
    return this.type1;
  }

  public void setType1(Character type1) {
    this.type1 = type1;
  }

  public Double getPamount1() {
    return this.pamount1;
  }

  public void setPamount1(Double pamount1) {
    this.pamount1 = pamount1;
  }

  public Character getType2() {
    return this.type2;
  }

  public void setType2(Character type2) {
    this.type2 = type2;
  }

  public Double getPamount2() {
    return this.pamount2;
  }

  public void setPamount2(Double pamount2) {
    this.pamount2 = pamount2;
  }

  public Character getType3() {
    return this.type3;
  }

  public void setType3(Character type3) {
    this.type3 = type3;
  }

  public Double getPamount3() {
    return this.pamount3;
  }

  public void setPamount3(Double pamount3) {
    this.pamount3 = pamount3;
  }

  public Character getType4() {
    return this.type4;
  }

  public void setType4(Character type4) {
    this.type4 = type4;
  }

  public Double getPamount4() {
    return this.pamount4;
  }

  public void setPamount4(Double pamount4) {
    this.pamount4 = pamount4;
  }

  public Character getType5() {
    return this.type5;
  }

  public void setType5(Character type5) {
    this.type5 = type5;
  }

  public Double getPamount5() {
    return this.pamount5;
  }

  public void setPamount5(Double pamount5) {
    this.pamount5 = pamount5;
  }

  public Long getOpUser() {
    return this.opUser;
  }

  public void setOpUser(Long opUser) {
    this.opUser = opUser;
  }

  public Integer getDel() {
    return del;
  }

  public void setDel(Integer del) {
    this.del = del;
  }

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

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
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

  public Long getIncomeId() {
    return incomeId;
  }

  public void setIncomeId(Long incomeId) {
    this.incomeId = incomeId;
  }

  public String getCasher() {
    return casher;
  }

  public void setCasher(String casher) {
    this.casher = casher;
  }

  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }

}
