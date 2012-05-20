package com.opentravelsoft.entity.out;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_income_acct")
public class TblIncomeAcct implements java.io.Serializable {

	private Integer acctId;
	private int incomeId;
	private String bookingNo;
	private BigDecimal amount;

	public TblIncomeAcct() {
	}

	public TblIncomeAcct(int incomeId, String bookingNo) {
		this.incomeId = incomeId;
		this.bookingNo = bookingNo;
	}

	public TblIncomeAcct(int incomeId, String bookingNo, BigDecimal amount) {
		this.incomeId = incomeId;
		this.bookingNo = bookingNo;
		this.amount = amount;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ACCT_ID", unique = true, nullable = false)
	public Integer getAcctId() {
		return this.acctId;
	}

	public void setAcctId(Integer acctId) {
		this.acctId = acctId;
	}

	@Column(name = "INCOME_ID", nullable = false)
	public int getIncomeId() {
		return this.incomeId;
	}

	public void setIncomeId(int incomeId) {
		this.incomeId = incomeId;
	}

	@Column(name = "BOOKING_NO", nullable = false, length = 20)
	public String getBookingNo() {
		return this.bookingNo;
	}

	public void setBookingNo(String bookingNo) {
		this.bookingNo = bookingNo;
	}

	@Column(name = "AMOUNT", precision = 10)
	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
