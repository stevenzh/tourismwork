package com.opentravelsoft.service.finance;

import java.util.Date;
import java.util.List;

import com.opentravelsoft.entity.finance.Invoice;

public interface InvoiceService {
  int txSaveInvoice(Invoice invoice, long groupId);

  List<Invoice> roGetInvoices(Date startDate, Date endDate, double minAmount,
      double maxAmount, String sort);

  /**
   * 删除发票
   * 
   * @param inviceId
   * @return 0 success -1 failure
   */
  int txDeleteInvoice(String inviceId);

}
