package com.opentravelsoft.action.manage.finance.invoice;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.finance.Invoice;
import com.opentravelsoft.service.finance.InvoiceService;

public class ListInvoice extends ManageAction {
  private static final long serialVersionUID = -4285814141474897738L;

  @Autowired
  private InvoiceService invoiceService;

  private List<Invoice> invoiceList;

  private Date startDate;

  private Date endDate;

  private double minAmount;

  private double maxAmount;

  private String inviceId;

  @Override
  public String input() throws Exception {
    invoiceList = invoiceService.roGetInvoices(startDate, endDate, minAmount,
        maxAmount, "DATE");

    currentPage(invoiceList.size());

    return INPUT;
  }

  public String delete() {
    int result = invoiceService.txDeleteInvoice(inviceId);

    if (result < 0) {
      addActionError("删除失败");
      return INPUT;
    }

    return SUCCESS;
  }

  public List<Invoice> getInvoiceList() {
    return invoiceList;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public double getMinAmount() {
    return minAmount;
  }

  public void setMinAmount(double minAmount) {
    this.minAmount = minAmount;
  }

  public double getMaxAmount() {
    return maxAmount;
  }

  public void setMaxAmount(double maxAmount) {
    this.maxAmount = maxAmount;
  }

  public String getInviceId() {
    return inviceId;
  }

  public void setInviceId(String inviceId) {
    this.inviceId = inviceId;
  }

}
