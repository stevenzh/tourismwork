package com.opentravelsoft.providers;

import java.util.Date;
import java.util.List;

import com.opentravelsoft.entity.finance.Invoice;

/**
 * 发票
 * 
 * @author zhangst
 * 
 */
public interface InvoiceDao extends GenericDao<Invoice, String> {
  /**
   * 取得订单的开发票记录
   * 
   * @param book
   * @return
   */
  public List<Invoice> getInvoice(long reserveNo);

  /**
   * 取得收款账单的发票列表
   * 
   * @param incomeId
   * @return
   */
  public List<Invoice> getInvoice1(long incomeId);

  /**
   * 添加发票
   * 
   * @param invoice
   * @param userId
   * @return
   */
  public int save(Invoice invoice, long userId);

  /**
   * 取得订单的付款记录(新)
   * 
   * @param orderSn
   * @return
   */
  public List<Invoice> getInvoiceByBook(String orderSn);

  /**
   * 查询发票
   * 
   * @param startDate
   * @param endDate
   * @param minAmount
   * @param maxAmount
   * @param sort
   * @return
   */
  public List<Invoice> getInvoices(Date startDate, Date endDate,
      double minAmount, double maxAmount, String sort);

  /**
   * 
   * @param inviceId
   * @return
   */
  public int deleteInvoice(String inviceId);

  public List<Invoice> getInvoice(String reserveNo);
}
