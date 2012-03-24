package com.opentravelsoft.service.finance;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.entity.finance.Invoice;
import com.opentravelsoft.providers.InvoiceDao;
import com.opentravelsoft.providers.SequenceDao;

@Service("InvoiceService")
public class InvoiceServiceImpl implements InvoiceService
{
    private InvoiceDao invoiceDao;

    private SequenceDao sequenceDao;

    @Autowired
    public void setInvoiceDao(InvoiceDao invoiceDao)
    {
        this.invoiceDao = invoiceDao;
    }

    @Autowired
    public void setSequenceDao(SequenceDao sequenceDao)
    {
        this.sequenceDao = sequenceDao;
    }

    public int txSaveInvoice(Invoice invoice, long groupId)
    {
        String no = sequenceDao.getComputerNo("M", groupId);
        invoice.setRecordNo(no);
        return invoiceDao.save(invoice, groupId);
    }

    public List<Invoice> roGetInvoices(Date startDate, Date endDate,
            double minAmount, double maxAmount, String sort)
    {
        return invoiceDao.getInvoices(startDate, endDate, minAmount, maxAmount,
                sort);
    }

    public int txDeleteInvoice(String inviceId)
    {
        return invoiceDao.deleteInvoice(inviceId);
    }
}
