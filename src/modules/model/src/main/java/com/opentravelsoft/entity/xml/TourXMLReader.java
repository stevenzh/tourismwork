package com.opentravelsoft.entity.xml;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.TourCost;
import com.opentravelsoft.entity.Tourist;
import com.opentravelsoft.util.AbstractObjectReader;
import com.opentravelsoft.util.RowDataUtil;

public class TourXMLReader extends AbstractObjectReader {

  protected SimpleDateFormat SDF1 = new SimpleDateFormat("yyyy/MM/dd");

  @Override
  public void parse(InputSource input) throws IOException, SAXException {
    if (input instanceof TourInputSource) {
      parse(((TourInputSource) input).getTour());
    } else {
      throw new SAXException("Unsupported InputSource specified. "
          + "Must be a ProjectTeamInputSource");
    }
  }

  private void parse(Plan tour) throws SAXException {
    if (tour == null) {
      throw new NullPointerException("Parameter projectTeam must not be null");
    }
    if (handler == null) {
      throw new IllegalStateException("ContentHandler not set");
    }

    handler.startDocument();
    generateFor(tour);
    handler.endDocument();
  }

  private void generateFor(Plan tour) throws SAXException {
    SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    if (tour == null) {
      throw new NullPointerException("Parameter projectTeam must not be null");
    }

    if (handler == null) {
      throw new IllegalStateException("ContentHandler not set");
    }

    handler.startElement("tour");
    handler.element("tour-name", tour.getTourNo());
    handler.element("line-name", tour.getLine().getLineName());

    if (null == tour.getOutDate()) {
      handler.element("out-date", "");
    } else {
      handler.element("out-date", (tour.getOutDate()).toString());
    }

    if (null == tour.getInDate()) {
      handler.element("in-date", "");
    } else {
      handler.element("in-date", (tour.getInDate()).toString());
    }

    handler.element("out-city", tour.getLine().getOutCity().getCitynm());
    handler.element("in-city", tour.getInCity());
    //
    handler.element("double-room", RowDataUtil.intFormat(tour.getDoubleRoom()));
    handler.element("single-room", RowDataUtil.intFormat(tour.getSingleRoom()));
    handler.element("singleRoom", RowDataUtil.intFormat(tour.getSingleRoom()));
    handler.element("extra-bed-room",
        RowDataUtil.intFormat(tour.getExtraBedRoom()));
    handler.element("doubleRoom", RowDataUtil.intFormat(tour.getDoubleRoom()));
    handler.element("extraBedRoom",
        RowDataUtil.intFormat(tour.getExtraBedRoom()));

    // 境外报团名单
    handler.element("label1", tour.getLabel1());
    handler.element("label2", tour.getLabel2());
    handler.element("label3", tour.getLabel3());

    // 出境游名单打印
    handler.element("tourSerialNumber", tour.getTourSerialNumber());
    handler.element("tourNo", tour.getTourNo());
    handler.element("year", tour.getYear());
    handler.element("routeName", tour.getLine().getLineName());
    handler.element("printPax", tour.getPrintPax());
    handler.element("printMalePax", tour.getPrintMalePax());
    handler.element("printFemalePax", tour.getPrintFemalePax());
    handler.element("localTname", tour.getLocalTname());
    handler.element("localEcontact", tour.getLocalEcontact());
    handler.element("receptionTname", tour.getReceptionTname());
    handler.element("receptionEcontact", tour.getReceptionEcontact());
    handler.element("leader", tour.getLeader());
    handler.element("leaderKey", tour.getLeaderKey());

    handler.element("leaderPrintName", tour.getLeaderPrintName());
    handler.element("leaderPrintPinyin", tour.getLeaderPrintPinyin());
    handler.element("leaderPrintSex", tour.getLeaderPrintSex());
    handler.element("leaderPrintBirthday", tour.getLeaderPrintBirthday());
    handler.element("leaderPrintBirPla", tour.getLeaderPrintBirPla());
    handler.element("leaderPrintPassportNo", tour.getLeaderPrintPassportNo());
    handler.element("leaderPrintPassportPla", tour.getLeaderPrintPassportPla());
    handler.element("leaderPrintPassportDate",
        tour.getLeaderPrintPassportDate());

    handler.element("outDate", tour.getPrintOutDate());
    handler.element("inDate", tour.getPrintInDate());
    handler.element("outCity", tour.getLine().getOutCity().getCitynm());
    handler.element("inCity", tour.getInCity());
    handler.element("outInKey", tour.getOutInKey());

    // 简单打印
    handler.element("simpleReport", tour.getSimpleReport());
    handler.element("title", tour.getTitle());
    handler.element("file-title", tour.getFileTitle());
    handler.element("remark", tour.getRemarks());
    handler.element("send", tour.getSend());
    handler.element("receive", tour.getReceive());
    handler.element("pax", RowDataUtil.intFormat(tour.getPax()));
    handler.element("allPax", RowDataUtil.intFormat(tour.getPax()));
    handler.element("leadpax", RowDataUtil.intFormat(tour.getLeaderPax()));

    // 是否显示项
    handler.element("printname", tour.getPrintName());
    handler.element("printpinyin", tour.getPrintPinyin());
    handler.element("printsex", tour.getPrintSex());
    handler.element("printage", tour.getPrintAge());
    handler.element("printagent", tour.getPrintAgent());
    handler.element("printIdcard", tour.getPrintIdcard());

    handler.element("printBirthday", tour.getPrintBirthday());
    handler.element("printBirPla", tour.getPrintBirPla());
    handler.element("printPassportType", tour.getPrintPassportType());
    handler.element("printPassportNo", tour.getPrintPassportNo());
    handler.element("printPassportDate", tour.getPrintPassportDate());
    handler.element("printPassportExpiry", tour.getPrintPassportDate());
    handler.element("printPassportPla", tour.getPrintPassportPla());
    handler.element("printPassportAnnotation",
        tour.getPrintPassportAnnotation());

    // 打印港澳游名单表
    handler.element("leadnum", tour.getLeadnum());
    if (tour.getDate1() != null) {
      handler.element("date1", tour.getDate1());
    } else {
      handler.element("date1", "");
    }
    handler.element("pla1", tour.getPla1());
    handler.element("pass1", tour.getPass1());
    handler.element("end1", tour.getEnd1());
    handler.element("date2", tour.getDate2());
    handler.element("pla2", tour.getPla2());
    handler.element("pass2", tour.getPass2());
    handler.element("end2", tour.getEnd2());
    handler.element("date3", tour.getDate3());
    handler.element("pla3", tour.getPla3());
    handler.element("pass3", tour.getPass3());
    handler.element("end3", tour.getEnd3());

    handler.element("arrhkdate", tour.getArrHKdate());
    handler.element("arrhktime", tour.getArrHKtime());
    handler.element("arrhkvehicle", tour.getArrHKvehicle());
    handler.element("leavehkdate", tour.getLeaveHKdate());
    handler.element("leavehktime", tour.getLeaveHKtime());
    handler.element("leavehkvehicle", tour.getLeaveHKvehicle());
    handler.element("arrmcdate", tour.getArrMCdate());
    handler.element("arrmctime", tour.getArrMCtime());
    handler.element("arrmcvehicle", tour.getArrMCvehicle());
    handler.element("leavemcdate", tour.getLeaveMCdate());
    handler.element("leavemctime", tour.getLeaveMCtime());
    handler.element("leavemcvehicle", tour.getLeaveMCvehicle());

    handler.element("localtname", tour.getLocalTname());
    handler.element("hktname", tour.getHKTname());
    handler.element("mctname", tour.getMCTname());
    handler.element("localecon", tour.getLocalEcontact());
    handler.element("hkecon", tour.getHKEcontact());
    handler.element("mcecon", tour.getMCEcontact());

    handler.element("pax", tour.getGapax());
    handler.element("male", tour.getGamalePax());
    handler.element("female", tour.getGafemalePax());
    handler.element("child", tour.getGachildPax());

    // 单团
    handler.element("department", tour.getTeam().getName());
    handler.element("day", RowDataUtil.intFormat(tour.getLine().getLineDay()));
    handler.element("leaderName", tour.getLeaderName());

    handler.element("muAmount", RowDataUtil.doubleFormat(tour.getMuAmount()));
    handler.element("alAmount", RowDataUtil.doubleFormat(tour.getAlAmount()));
    handler.element("wiAmount", RowDataUtil.doubleFormat(tour.getWiAmount()));
    handler.element("exemptPax", RowDataUtil.intFormat(tour.getExemptPax()));
    handler.element("childPax", RowDataUtil.intFormat(tour.getChildPax()));
    handler.element("amount", RowDataUtil.doubleFormat(tour.getAmount()));
    handler.element("tourAmount",
        RowDataUtil.doubleFormat(tour.getTourAmount()));
    handler.element("costAmount", RowDataUtil.doubleFormat(tour.getCost()));
    handler.element("grossAmount",
        RowDataUtil.doubleFormat(tour.getGrossAmount()));
    handler.element("grossAmountRate",
        RowDataUtil.doubleFormat(tour.getGrossAmountRate()));
    handler.element("grossAmountAverage",
        RowDataUtil.doubleFormat(tour.getGrossAmountAverage()));
    handler.element("extrIncome",
        RowDataUtil.doubleFormat(tour.getExtrIncome()));
    handler.element("extrIncomeDec", tour.getExtrIncomeDec());
    handler.element("totalPax", RowDataUtil.intFormat(tour.getTotalPax()));
    handler.element("oprateUser", tour.getOprateUserName());

    if (null != tour.getDateCreated()) {
      handler.element("CTourDate", SDF.format(tour.getDateCreated()));
    } else {
      handler.element("CTourDate", " ");
    }

    handler.element("opUserName", tour.getOpUserName());
    if (null != tour.getOpDate()) {
      handler.element("opDate", SDF.format(tour.getOpDate()));
    } else {
      handler.element("opDate", " ");
    }

    handler.element("frUserName", tour.getFrUserName());
    if (null != tour.getFrDate()) {
      handler.element("frDate", SDF.format(tour.getFrDate()));
    } else {
      handler.element("frDate", " ");
    }

    // 游客
    for (Tourist trip : tour.getCustomerList()) {
      generateFor(trip);
    }
    // 订单
    for (Booking book : tour.getBookList()) {
      generateFor(book);
    }
    // 成本
    for (TourCost singleTourCostAcct : tour.getCostList()) {
      generateFor(singleTourCostAcct);
    }

    // handler.element("create-date", (tour.getDateCreated()).toString());
    // handler.element("creator", tour.getCreator());
    // handler.element("opuser", tour.getOpUser());
    handler.endElement("tour");
  }

  private void generateFor(Tourist trip) throws SAXException {
    if (trip == null) {
      throw new NullPointerException("Parameter projectMember must not be null");
    }

    if (handler == null) {
      throw new IllegalStateException("ContentHandler not set");
    }

    handler.startElement("customer");
    handler.element("idx", Integer.toString(trip.getNumber()));
    if (trip.getUserName() != null && trip.getUserName().length() > 15)
      handler.element("agentname", trip.getUserName().substring(0, 15));
    else
      handler.element("agentname", trip.getUserName());
    handler.element("name", trip.getUserName());
    handler.element("pinYin", trip.getPinYin());
    handler.element("name3", "");
    handler.element("sex", trip.getSex().equals("M") ? "Male" : "Female");
    handler.element("age", RowDataUtil.intFormat(trip.getAge()));
    handler.element("idcard", trip.getIdCard());
    handler.element("leader", trip.getLeaderKey());
    if (trip.getBirthday() == null) {
      handler.element("birdate", "");
    } else {
      handler.element("birdate", (trip.getBirthday()).toString());
    }
    handler.element("birpla", trip.getBirthplace());

    // handler.element("tl", trip.get)
    // handler.element("country", trip.getNation());
    // handler.element("province", trip.getProvince());
    // handler.element("city", trip.getCity());
    // handler.element("vocation", trip.getVocation());
    // handler.element("educate", trip.getEducate());
    // handler.element("phone", trip.getPhone());
    // handler.element("mobile", trip.getMobile());
    // handler.element("e-mail", trip.getEmail());
    // handler.element("fax", trip.getFax());
    // handler.element("address", trip.getAddress());
    handler.element("passportType", trip.getPassportType());
    handler.element("passportNo", trip.getPassportNo());

    if (null == trip.getPassportDate()) {
      handler.element("passportDate", "");

    } else {
      handler.element("passportDate", (trip.getPassportDate()).toString());
    }

    if (null == trip.getPassportExpiry()) {
      handler.element("passportExpiry", "");

    } else {
      handler.element("passportExpiry", (trip.getPassportExpiry()).toString());
    }
    handler.element("passportPla", trip.getPassportPlace());
    handler.element("passportAnnotation", trip.getPassportAnnotation());

    // 是否显示项
    handler.element("printname", trip.getPrintName());
    handler.element("printpinyin", trip.getPrintPinyin());
    handler.element("printsex", trip.getPrintSex());
    handler.element("printage", trip.getPrintAge());
    handler.element("printidcard", trip.getPrintIdcard());
    if (null == trip.getPrintBirthday()) {
      handler.element("printBirthday", "");
    } else {
      handler.element("printBirthday", SDF1.format(trip.getPrintBirthday()));
    }

    handler.element("printBirPla", trip.getPrintBirPla());

    handler.element("printagent", trip.getPrintAgent());
    handler.element("printPassportType", trip.getPrintPassportType());
    handler.element("printPassportNo", trip.getPrintPassportNo());
    handler.element("printPassportDate", trip.getPrintPassportDate());
    handler.element("printPassportExpiry", trip.getPrintPassportExpiry());
    handler.element("printPassportPla", trip.getPrintPassportPla());
    handler.element("printPassportAnnotation",
        trip.getPrintPassportAnnotation());
    handler.element("child", trip.getChild());
    handler.endElement("customer");
  }

  /**
   * 此团所有的订单
   * 
   * @param book
   * @throws SAXException
   */
  private void generateFor(Booking book) throws SAXException {
    if (book == null) {
      throw new NullPointerException("Parameter projectTeam must not be null");
    }

    if (handler == null) {
      throw new IllegalStateException("ContentHandler not set");
    }

    handler.startElement("book");
    handler.element("id", RowDataUtil.intFormat(book.getId() + 1));
    handler.element("customerName", book.getCustomer().getName());
    handler.element("seller", String.valueOf(book.getSalesman()));
    handler.element("pax", RowDataUtil.intFormat(book.getPax()));
    handler.element("expenseFinalExpense",
        RowDataUtil.doubleFormat(book.getDbamt().add(book.getFinalExpense())));
    handler.element("payCosts", RowDataUtil.doubleFormat(book.getPayCosts()));
    handler.element("unPay", RowDataUtil.doubleFormat(book.getUnPay()));

    handler.endElement("book");
  }

  /**
   * 此团的成本明细
   * 
   * @param singleTourCostAcct
   * @throws SAXException
   */
  private void generateFor(TourCost singleTourCostAcct) throws SAXException {
    if (singleTourCostAcct == null) {
      throw new NullPointerException("Parameter projectTeam must not be null");
    }

    if (handler == null) {
      throw new IllegalStateException("ContentHandler not set");
    }

    handler.startElement("singleTourCostAcct");
    handler
        .element("id", RowDataUtil.intFormat(singleTourCostAcct.getId() + 1));
    handler.element("amountTypeName", singleTourCostAcct.getCostType());
    handler.element("supplier", singleTourCostAcct.getCustomer().getName());
    handler.element("description", singleTourCostAcct.getDescription());
    handler.element("currency", singleTourCostAcct.getCurrency());
    handler.element("roe",
        RowDataUtil.doubleFormat(singleTourCostAcct.getRoe()));
    handler.element("unitPrice",
        RowDataUtil.doubleFormat(singleTourCostAcct.getUnitPrice()));
    handler.element("count",
        RowDataUtil.intFormat(singleTourCostAcct.getCount()));
    handler.element("unit", singleTourCostAcct.getUnit());
    handler.element("amount",
        RowDataUtil.doubleFormat(singleTourCostAcct.getAmount()));

    handler.endElement("singleTourCostAcct");
  }
}
