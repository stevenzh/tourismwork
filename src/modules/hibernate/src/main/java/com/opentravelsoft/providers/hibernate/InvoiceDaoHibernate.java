package com.opentravelsoft.providers.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.LockMode;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.finance.Invoice;
import com.opentravelsoft.entity.finance.InvoiceItem;
import com.opentravelsoft.entity.finance.InvoicePiece;
import com.opentravelsoft.providers.InvoiceDao;
import com.opentravelsoft.util.RowDataUtil;

@Repository("InvoiceDao")
public class InvoiceDaoHibernate extends GenericDaoHibernate<Invoice, String>
    implements InvoiceDao {

  public InvoiceDaoHibernate() {
    super(Invoice.class);
  }

  public List<Invoice> getInvoice(String reserveNo) {
    // StringBuilder sb = new StringBuilder();
    // sb.append("select a.recNo,a.invNo,a.prtDate,a.customs,");
    // sb.append("a.exp1,a.amount1,a.type1,a.pamount1,");
    // sb.append("a.exp2,a.amount2,a.type2,a.pamount2,");
    // sb.append("a.exp3,a.amount3,a.type3,a.pamount3,");
    // sb.append("a.exp4,a.amount4,a.type4,a.pamount4,");
    // sb.append("a.type5,a.pamount5,a.remarks,a.opUser,");
    // sb.append("a.casher,a.signature ");
    // sb.append("from com.opentravelsoft.entity.finance.Invoice a,");
    // sb.append("where b.nameNo=?");

    List<Invoice> result = new ArrayList<Invoice>();
    // Invoice invoice;
    // Object[] params = { book.getRecordNo() };
    // List<Object[]> list = getHibernateTemplate()
    // .find(sb.toString(), params);
    //
    // for (Object[] obj : list)
    // {
    // invoice = new Invoice();
    // invoice.setRecordNo(RowDataUtil.getString(obj[0]));
    // invoice.setInvoiceNo(RowDataUtil.getString(obj[1]));
    // invoice.setCrateDate(RowDataUtil.getDate(obj[2]));
    // invoice.setCustomer(RowDataUtil.getString(obj[3]));
    // invoice.setRemarks(RowDataUtil.getString(obj[22]));
    // invoice.setCreateUser(RowDataUtil.getString(obj[23]));
    //
    // invoice.addItem(RowDataUtil.getString(obj[4]), RowDataUtil
    // .getDouble(obj[5]));
    // invoice.addItem(RowDataUtil.getString(obj[8]), RowDataUtil
    // .getDouble(obj[9]));
    // invoice.addItem(RowDataUtil.getString(obj[12]), RowDataUtil
    // .getDouble(obj[13]));
    // invoice.addItem(RowDataUtil.getString(obj[16]), RowDataUtil
    // .getDouble(obj[17]));
    //
    // invoice.addPiece(RowDataUtil.getString(obj[6]), RowDataUtil
    // .getDouble(obj[7]));
    // invoice.addPiece(RowDataUtil.getString(obj[10]), RowDataUtil
    // .getDouble(obj[11]));
    // invoice.addPiece(RowDataUtil.getString(obj[14]), RowDataUtil
    // .getDouble(obj[15]));
    // invoice.addPiece(RowDataUtil.getString(obj[18]), RowDataUtil
    // .getDouble(obj[19]));
    // invoice.addPiece(RowDataUtil.getString(obj[20]), RowDataUtil
    // .getDouble(obj[21]));
    // // 出纳
    // invoice.setCasher(RowDataUtil.getString(obj[24]));
    // // 经办人
    // invoice.setSignature(RowDataUtil.getString(obj[25]));
    //
    // invoice.setAmount(RowDataUtil.getDouble(obj[5])
    // + RowDataUtil.getDouble(obj[9])
    // + RowDataUtil.getDouble(obj[13])
    // + RowDataUtil.getDouble(obj[17]));
    //
    // result.add(invoice);
    // }
    return result;
  }

  @SuppressWarnings("unchecked")
  public List<Invoice> getInvoiceByBook(String orderSn) {
    StringBuilder sb = new StringBuilder();
    sb.append("select a.recNo,a.prtDate,a.customs,");
    sb.append("a.exp1,a.amount1,a.type1,a.pamount1,");
    sb.append("a.exp2,a.amount2,a.type2,a.pamount2,");
    sb.append("a.exp3,a.amount3,a.type3,a.pamount3,");
    sb.append("a.exp4,a.amount4,a.type4,a.pamount4,");
    sb.append("a.type5,a.pamount5,a.remarks,a.opUser,");
    sb.append("a.casher,a.signature ");
    sb.append("from Invoice a,");
    sb.append("Income b ");
    sb.append("where a.incomeId=b.incomeId and b.bookingNo=? ");

    List<Invoice> result = new ArrayList<Invoice>();
    Invoice invoice;
    Object[] params = { orderSn };
    List<Object[]> list = getHibernateTemplate().find(sb.toString(), params);

    for (Object[] obj : list) {
      invoice = new Invoice();
      invoice.setRecordNo(RowDataUtil.getString(obj[0]));
      // invoice.setInvoiceNo(RowDataUtil.getString(obj[1]));
      invoice.setCrateDate(RowDataUtil.getDate(obj[1]));
      invoice.setCustomer(RowDataUtil.getString(obj[3]));
      invoice.setRemarks(RowDataUtil.getString(obj[21]));
      invoice.setCreateUser(RowDataUtil.getInt(obj[22]));

      invoice.addItem(RowDataUtil.getString(obj[3]),
          RowDataUtil.getBigDecimal(obj[4]));
      invoice.addItem(RowDataUtil.getString(obj[7]),
          RowDataUtil.getBigDecimal(obj[8]));
      invoice.addItem(RowDataUtil.getString(obj[11]),
          RowDataUtil.getBigDecimal(obj[12]));
      invoice.addItem(RowDataUtil.getString(obj[15]),
          RowDataUtil.getBigDecimal(obj[16]));

      invoice.addPiece(RowDataUtil.getString(obj[5]),
          RowDataUtil.getBigDecimal(obj[6]));
      invoice.addPiece(RowDataUtil.getString(obj[9]),
          RowDataUtil.getBigDecimal(obj[10]));
      invoice.addPiece(RowDataUtil.getString(obj[13]),
          RowDataUtil.getBigDecimal(obj[14]));
      invoice.addPiece(RowDataUtil.getString(obj[17]),
          RowDataUtil.getBigDecimal(obj[18]));
      invoice.addPiece(RowDataUtil.getString(obj[19]),
          RowDataUtil.getBigDecimal(obj[20]));

      // 出纳
      invoice.setCasher(RowDataUtil.getString(obj[23]));
      // 经办人
      invoice.setSignature(RowDataUtil.getString(obj[24]));

      invoice.setAmount(RowDataUtil.getDouble(obj[4])
          + RowDataUtil.getDouble(obj[8]) + RowDataUtil.getDouble(obj[12])
          + RowDataUtil.getDouble(obj[16]));

      result.add(invoice);
    }
    return result;
  }

  public int save(Invoice invoice, int userId) {
    Invoice invoiceInst = new Invoice();
    Date sysdate = getSysdate();

    invoiceInst.setRecNo(invoice.getRecordNo());
    //
    invoiceInst.setCustoms(invoice.getCustomer());
    invoiceInst.setOpUser(userId);
    invoiceInst.setPrtDate(sysdate);

    List<InvoiceItem> items = invoice.getItems();
    InvoiceItem item = items.get(0);
    invoiceInst.setAmount1(item.getExpense());
    invoiceInst.setExp1(item.getItem());
    invoiceInst.setPamount1(item.getExpense());

    item = items.get(1);
    invoiceInst.setAmount2(item.getExpense());
    invoiceInst.setExp2(item.getItem());
    invoiceInst.setPamount2(item.getExpense());

    item = items.get(2);
    invoiceInst.setAmount3(item.getExpense());
    invoiceInst.setExp3(item.getItem());
    invoiceInst.setPamount3(item.getExpense());

    item = items.get(3);
    invoiceInst.setAmount4(item.getExpense());
    invoiceInst.setExp4(item.getItem());
    invoiceInst.setPamount4(item.getExpense());

    // ---------------------------------------------------------------------
    List<InvoicePiece> pieces = invoice.getPieces();
    InvoicePiece piece = pieces.get(0);
    invoiceInst.setType1(getCheckFlag(piece.getType()));
    invoiceInst.setPamount1(piece.getAmount());

    piece = pieces.get(1);
    invoiceInst.setType2(getCheckFlag(piece.getType()));
    invoiceInst.setPamount2(piece.getAmount());

    piece = pieces.get(2);
    invoiceInst.setType3(getCheckFlag(piece.getType()));
    invoiceInst.setPamount3(piece.getAmount());

    piece = pieces.get(3);
    invoiceInst.setType4(getCheckFlag(piece.getType()));
    invoiceInst.setPamount4(piece.getAmount());

    piece = pieces.get(4);
    invoiceInst.setType5(getCheckFlag(piece.getType()));
    invoiceInst.setPamount5(piece.getAmount());

    invoiceInst.setRemarks(invoice.getRemarks());
    invoiceInst.setIncomeId(invoice.getIncomeId());

    // 出纳
    invoiceInst.setCasher(invoice.getCasher());
    // 经办人
    invoiceInst.setSignature(invoice.getSignature());

    invoiceInst.setDel((byte) 0);

    getHibernateTemplate().save(invoiceInst);
    return 0;
  }

  /**
   * 
   * @param str
   * @return
   */
  private Character getCheckFlag(String str) {
    if (null == str || str.length() == 0)
      return 'N';

    if (str.equals("true"))
      return 'Y';
    else
      return 'N';
  }

  @SuppressWarnings("unchecked")
  public List<Invoice> getInvoice(int incomeId) {
    StringBuilder sb = new StringBuilder();
    sb.append("select a.recNo,a.invNo,a.prtDate,a.customs,");
    sb.append("a.exp1,a.amount1,a.type1,a.pamount1,");
    sb.append("a.exp2,a.amount2,a.type2,a.pamount2,");
    sb.append("a.exp3,a.amount3,a.type3,a.pamount3,");
    sb.append("a.exp4,a.amount4,a.type4,a.pamount4,");
    sb.append("a.type5,a.pamount5,a.remarks,a.opUser,");
    sb.append("a.casher,a.signature ");
    sb.append("from Invoice a,");
    sb.append("Income b ");
    sb.append("where a.incomeId=b.incomeId and a.incomeId=?");

    List<Invoice> result = new ArrayList<Invoice>();
    Invoice invoice;
    Object[] params = { incomeId };
    List<Object[]> list = getHibernateTemplate().find(sb.toString(), params);

    for (Object[] obj : list) {
      invoice = new Invoice();
      invoice.setRecordNo(RowDataUtil.getString(obj[0]));
      invoice.setInvoiceNo(RowDataUtil.getString(obj[1]));
      invoice.setCrateDate(RowDataUtil.getDate(obj[2]));
      invoice.setCustomer(RowDataUtil.getString(obj[3]));
      invoice.setRemarks(RowDataUtil.getString(obj[22]));
      invoice.setCreateUser(RowDataUtil.getInt(obj[23]));

      invoice.addItem(RowDataUtil.getString(obj[4]),
          RowDataUtil.getBigDecimal(obj[5]));
      invoice.addItem(RowDataUtil.getString(obj[8]),
          RowDataUtil.getBigDecimal(obj[9]));
      invoice.addItem(RowDataUtil.getString(obj[12]),
          RowDataUtil.getBigDecimal(obj[13]));
      invoice.addItem(RowDataUtil.getString(obj[16]),
          RowDataUtil.getBigDecimal(obj[17]));

      invoice.addPiece(RowDataUtil.getString(obj[6]),
          RowDataUtil.getBigDecimal(obj[7]));
      invoice.addPiece(RowDataUtil.getString(obj[10]),
          RowDataUtil.getBigDecimal(obj[11]));
      invoice.addPiece(RowDataUtil.getString(obj[14]),
          RowDataUtil.getBigDecimal(obj[15]));
      invoice.addPiece(RowDataUtil.getString(obj[18]),
          RowDataUtil.getBigDecimal(obj[19]));
      invoice.addPiece(RowDataUtil.getString(obj[20]),
          RowDataUtil.getBigDecimal(obj[21]));
      // 出纳
      invoice.setCasher(RowDataUtil.getString(obj[24]));
      // 经办人
      invoice.setSignature(RowDataUtil.getString(obj[25]));

      invoice.setAmount(RowDataUtil.getDouble(obj[5])
          + RowDataUtil.getDouble(obj[9]) + RowDataUtil.getDouble(obj[13])
          + RowDataUtil.getDouble(obj[17]));

      result.add(invoice);
    }

    return result;
  }

  @SuppressWarnings("unchecked")
  public List<Invoice> getInvoices(Date startDate, Date endDate,
      double minAmount, double maxAmount, String sort) {
    StringBuilder sb = new StringBuilder();
    List<Object> params = new ArrayList<Object>();

    sb.append("from Invoice ");
    sb.append("where del<>1 ");

    if (minAmount > 0) {
      sb.append("and amount1+amount2+amount3+amount4 >= ? ");
      params.add(minAmount);
    }
    if (maxAmount > 0) {
      sb.append("and amount1+amount2+amount3+amount4 <= ? ");
      params.add(maxAmount);
    }
    if (null != startDate) {
      sb.append("and prtDate>=? ");
      params.add(startDate);
    }
    if (null != startDate) {
      sb.append("and prtDate<=? ");
      params.add(endDate);
    }
    sb.append("order by prtDate desc");

    Object[] param = null;
    if (params.size() > 0) {
      param = new Object[params.size()];
      for (int i = 0; i < params.size(); i++) {
        param[i] = params.get(i);
      }
    }

    List<Invoice> result = new ArrayList<Invoice>();

    List<Invoice> list = getHibernateTemplate().find(sb.toString(), param);

    for (Invoice obj : list) {
      Invoice invoice = new Invoice();
      invoice.setRecordNo(obj.getRecNo());
      invoice.setCrateDate(obj.getPrtDate());
      invoice.setCustomer(obj.getCustoms());
      invoice.setRemarks(obj.getRemarks());
      invoice.setCreateUser(obj.getOpUser());

      invoice.addItem(obj.getExp1(), obj.getAmount1());
      invoice.addItem(obj.getExp2(), obj.getAmount2());
      invoice.addItem(obj.getExp3(), obj.getAmount3());
      invoice.addItem(obj.getExp4(), obj.getAmount4());

      invoice.addPiece(String.valueOf(obj.getType1()), obj.getPamount1());
      invoice.addPiece(String.valueOf(obj.getType2()), obj.getPamount2());
      invoice.addPiece(String.valueOf(obj.getType3()), obj.getPamount3());
      invoice.addPiece(String.valueOf(obj.getType4()), obj.getPamount4());
      invoice.addPiece(String.valueOf(obj.getType5()), obj.getPamount5());
      // 出纳
      invoice.setCasher(obj.getCasher());
      // 经办人
      invoice.setSignature(obj.getSignature());

      invoice.setAmount(RowDataUtil.getDouble(obj.getAmount1())
          + RowDataUtil.getDouble(obj.getAmount2())
          + RowDataUtil.getDouble(obj.getAmount3())
          + RowDataUtil.getDouble(obj.getAmount4()));

      result.add(invoice);
    }

    return result;
  }

  public int deleteInvoice(String inviceId) {
    HibernateTemplate template = getHibernateTemplate();
    Invoice trj051 = (Invoice) template.get(Invoice.class, inviceId,
        LockMode.PESSIMISTIC_WRITE);
    if (null == trj051)
      return -1;

    trj051.setDel((byte) 1);
    template.update(trj051);

    return 0;
  }

  public List<Invoice> getInvoice1(int incomeId) {
    // TODO Auto-generated method stub
    return null;
  }
}
