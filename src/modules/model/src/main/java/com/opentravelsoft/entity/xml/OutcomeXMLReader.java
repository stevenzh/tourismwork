package com.opentravelsoft.entity.xml;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.opentravelsoft.entity.finance.Outcome;
import com.opentravelsoft.util.AbstractObjectReader;
import com.opentravelsoft.util.RowDataUtil;

public class OutcomeXMLReader extends AbstractObjectReader {

  @Override
  public void parse(InputSource input) throws IOException, SAXException {
    if (input instanceof OutcomeInputSource) {
      parse(((OutcomeInputSource) input).getBillhead());
    } else {
      throw new SAXException("Unsupported InputSource specified. "
          + "Must be a ProjectTeamInputSource");
    }
  }

  private void parse(Outcome billhead) throws SAXException {
    if (billhead == null) {
      throw new NullPointerException("Parameter projectTeam must not be null");
    }
    if (handler == null) {
      throw new IllegalStateException("ContentHandler not set");
    }

    handler.startDocument();
    generateFor(billhead);
    handler.endDocument();
  }

  private void generateFor(Outcome billhead) throws SAXException {
    if (billhead == null) {
      throw new NullPointerException("Parameter projectTeam must not be null");
    }

    if (handler == null) {
      throw new IllegalStateException("ContentHandler not set");
    }

    handler.startElement("billhead");
    handler.element("outcomeId",
        RowDataUtil.doubleFormat(billhead.getOutcomeId()));
    handler.element("supplierName", billhead.getCustomer().getName());
    handler.element("note", billhead.getNote());
    // 付款合计
    handler.element("amount", RowDataUtil.doubleFormat(billhead.getAmount()));
    handler.element("amountCn", billhead.getAmountCn());
    // 付款方式
    handler.element("payModeName", billhead.getPayModeName());
    // 借款人
    handler.element("opApprovedbyName", billhead.getOpApprovedbyName());
    // 部门
    handler.element("opApprovedbyDptName", billhead.getOpApprovedbyDptName());

    SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
    // 日期
    handler.element("opApprovedTime", SDF.format(billhead.getOpApproved()));

    // 经办人
    handler.element("frApprovedbyName", billhead.getFrApprovedbyName());

    handler.element("id", RowDataUtil.intFormat(billhead.getId()));
    handler.element("tourNo", billhead.getTourNo());
    handler.element("description", billhead.getDescription());
    // 币种
    handler.element("currency", billhead.getCurrency());
    // OP结算汇率
    handler.element("opRoe", RowDataUtil.doubleFormat(billhead.getOpRoe()));
    // 财务结算汇率
    handler.element("roe", RowDataUtil.doubleFormat(billhead.getRoe()));
    // 现付款(外币)
    handler.element("fcNowpayPayment",
        RowDataUtil.doubleFormat(billhead.getFcNowpayPayment()));
    handler.element("nowpayPayment",
        RowDataUtil.doubleFormat(billhead.getNowpayPayment()));

  }
}
