package com.opentravelsoft.action.manage.finance.invoice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.finance.Income;
import com.opentravelsoft.entity.finance.Invoice;
import com.opentravelsoft.entity.finance.InvoiceItem;
import com.opentravelsoft.entity.finance.InvoicePiece;
import com.opentravelsoft.service.finance.IncomeService;
import com.opentravelsoft.service.finance.InvoiceService;

/**
 * 填写发票
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:11 $
 */
public class InvoiceAction extends ManageAction {
  private static final long serialVersionUID = -8607586773699795330L;

  private InvoiceService invoiceService;

  private IncomeService incomeService;

  private Invoice invoice;

  /** 付款记录ID */
  private long incomeId;

  private List<InvoiceItem> items = new ArrayList<InvoiceItem>();

  private List<InvoicePiece> pieces = new ArrayList<InvoicePiece>();

  // -------------------------------------------------------------------------

  @Autowired
  public void setInvoiceService(InvoiceService invoiceService) {
    this.invoiceService = invoiceService;
  }

  @Autowired
  public void setIncomeService(IncomeService incomeService) {
    this.incomeService = incomeService;
  }

  public String input() {
    Employee user = getUser();
    if (incomeId != 0) {
      // 付款单
      Income gather = incomeService.roGetIncome(incomeId);

      double dAmount = 0d;

      // 发票记录
      if (null != gather)
        for (Invoice item : gather.getInvices()) {
          dAmount += item.getAmount();
        }
    }

    if (items.size() == 0)
      for (int i = 0; i < 4; i++) {
        InvoiceItem item = new InvoiceItem();
        item.setId(i);
        items.add(item);
      }

    if (pieces.size() == 0)
      for (int i = 0; i < 5; i++) {
        InvoicePiece piece = new InvoicePiece();
        piece.setId(i);
        pieces.add(piece);
      }

    invoice = new Invoice();
    // 备注栏填写 线路名，出团时间
    // invoice.setRemarks("旅游线路: 人数:");
    // 经办人
    invoice.setSignature(user.getUserName());

    return INPUT;
  }

  @Override
  public void validate() {
    // Gathering gathering = incomeService.roGetIncome(Integer
    // .parseInt(incomeId));

    double pay = 0d;
    for (InvoiceItem item : items) {
      pay += item.getExpense();
    }

    // if (pay > gathering.getIncomeMon())
    // {
    // addActionError("发票付款项目总额大于付款额.");
    // } else
    if (pay == 0) {
      addActionError("发票付款项目总额为零.");
    }

    double block = 0d;
    for (InvoicePiece piece : pieces) {
      block += piece.getAmount();
    }
    // if (block > gathering.getIncomeMon())
    // {
    // addActionError("发票收款方式一栏总额大于付款额.");
    // } else
    if (block == 0) {
      addActionError("发票收款方式一栏总额为零.");
    }

    if (pay != block)
      addActionError("发票付款项目总额与发票收款方式一栏总额不符.");
  }

  public String submit() {
    Employee user = getUser();
    invoice.setItems(items);
    invoice.setPieces(pieces);
    invoice.setIncomeId(incomeId);
    invoiceService.txSaveInvoice(invoice, user.getGroup().getGroupId());
    return SUCCESS;
  }

  public Invoice getInvoice() {
    return invoice;
  }

  public void setInvoice(Invoice invoice) {
    this.invoice = invoice;
  }

  public List<InvoiceItem> getItems() {
    return items;
  }

  public void setItems(List<InvoiceItem> items) {
    this.items = items;
  }

  public List<InvoicePiece> getPieces() {
    return pieces;
  }

  public void setPieces(List<InvoicePiece> pieces) {
    this.pieces = pieces;
  }

  public Long getIncomeId() {
    return incomeId;
  }

  public void setIncomeId(Long incomeId) {
    this.incomeId = incomeId;
  }

}
