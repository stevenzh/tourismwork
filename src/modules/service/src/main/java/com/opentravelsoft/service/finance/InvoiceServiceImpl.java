package com.opentravelsoft.service.finance;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.entity.finance.Invoice;
import com.opentravelsoft.providers.InvoiceDao;
import com.opentravelsoft.providers.SequenceDao;

@Service("InvoiceService")
public class InvoiceServiceImpl implements InvoiceService {

  @Autowired
  private InvoiceDao invoiceDao;

  @Autowired
  private SequenceDao sequenceDao;

  public int txSaveInvoice(Invoice invoice, int teamId) {
    String no = sequenceDao.getComputerNo("M", teamId);
    invoice.setRecordNo(no);
    return invoiceDao.save(invoice, teamId);
  }

  public List<Invoice> roGetInvoices(Date startDate, Date endDate,
      double minAmount, double maxAmount, String sort) {
    return invoiceDao.getInvoices(startDate, endDate, minAmount, maxAmount,
        sort);
  }

  public int txDeleteInvoice(String inviceId) {
    return invoiceDao.deleteInvoice(inviceId);
  }
}
