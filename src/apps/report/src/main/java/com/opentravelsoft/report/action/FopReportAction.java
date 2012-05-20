package com.opentravelsoft.report.action;

import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import org.efs.openreports.ORStatics;
import org.efs.openreports.ReportConstants.ExportType;
import org.efs.openreports.objects.Report;
import org.efs.openreports.objects.ReportUser;
import org.efs.openreports.providers.ProviderException;
import org.efs.openreports.providers.ReportProvider;

import com.opentravelsoft.util.LabelValueBean;
import com.opentravelsoft.util.XMLUtility;
import com.opentravelsoft.common.KeyParams;
import com.opentravelsoft.common.SessionKeyParams;

import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.City;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Line;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.TourCost;
import com.opentravelsoft.entity.TourOutBound;
import com.opentravelsoft.entity.finance.Outcome;
import com.opentravelsoft.entity.finance.Reckoning;
import com.opentravelsoft.entity.finance.ReckoningAcct;
import com.opentravelsoft.service.operator.TourService;
import com.opentravelsoft.service.order.BookingService;
import com.opentravelsoft.providers.CityDao;
import com.opentravelsoft.providers.ListDao;
import com.opentravelsoft.providers.PlanDao;
import com.opentravelsoft.providers.ReckoningDao;
import com.opentravelsoft.providers.TouristDao;
import com.opentravelsoft.providers.product.LineDao;
import com.opentravelsoft.providers.product.LineScheduleDao;
import com.opentravelsoft.providers.product.LineTraitDao;
import com.opentravelsoft.service.finance.OutcomeService;
import com.opentravelsoft.service.finance.ReckoningService;

import com.opentravelsoft.report.util.Parameter;
import com.opentravelsoft.report.view.ApacheFopFactory;

public class FopReportAction extends ActionSupport {

  private static final long serialVersionUID = 6410639554914300771L;

  private String separator = System.getProperty("file.separator");

  private BookingService bookingService;

  private ReckoningService reckoningMakeService;

  private OutcomeService outcomeService;

  private TourService tourService;

  private LineDao lineDao;

  private PlanDao planDao;

  private CityDao cityDao;

  private ListDao listDao;

  private ReckoningDao reckoningDao;

  private LineScheduleDao routeScheduleDao;

  private LineTraitDao routeTraitDao;

  private TouristDao touristDao;

  protected SimpleDateFormat SDF = new SimpleDateFormat("yyyy年MM月dd日");

  protected SimpleDateFormat SDF1 = new SimpleDateFormat("yyyy/MM/dd");

  protected SimpleDateFormat SDF2 = new SimpleDateFormat("yyyy　　MM　　dd　　　");

  protected SimpleDateFormat SDF3 = new SimpleDateFormat("　　MM　　dd　　");

  // 保留两位小数点
  protected DecimalFormat df = new DecimalFormat("#.00");

  private ReportProvider reportProvider;

  private Date date1;

  private Date date2;

  private Date date3;

  private Date arrHKdate;

  private Date arrMCdate;

  private Date leaveHKdate;

  private Date leaveMCdate;

  private String[] tourNum;

  private String userId;

  private Booking book = new Booking();

  private String reserveNo;

  private int reckoningId;

  private int reportId;

  // 应收账款查询条件
  private String proCd;

  private String cityCd;

  private Date stDate;

  private Date endDate;

  private List<Parameter> parameters = new ArrayList<Parameter>();

  @Autowired
  public void setRouteDao(LineDao routeDao) {
    this.lineDao = routeDao;
  }

  @Autowired
  public void setRouteTraitDao(LineTraitDao routeTraitDao) {
    this.routeTraitDao = routeTraitDao;
  }

  @Autowired
  public void setRouteScheduleDao(LineScheduleDao routeScheduleDao) {
    this.routeScheduleDao = routeScheduleDao;
  }

  @Autowired
  public void setReckoningDao(ReckoningDao reckoningDao) {
    this.reckoningDao = reckoningDao;
  }

  @Autowired
  public void setBookingService(BookingService bookingService) {
    this.bookingService = bookingService;
  }

  @Autowired
  public void setReckoningMakeService(ReckoningService reckoningMakeService) {
    this.reckoningMakeService = reckoningMakeService;
  }

  @Autowired
  public void setOutcomeService(OutcomeService outcomeService) {
    this.outcomeService = outcomeService;
  }

  @Autowired
  public void setTourService(TourService tourService) {
    this.tourService = tourService;
  }

  public void setReportProvider(ReportProvider reportProvider) {
    this.reportProvider = reportProvider;
  }

  // public void setDirectoryProvider(DirectoryProvider directoryProvider)
  // {
  // this.directoryProvider = directoryProvider;
  // }

  @Autowired
  public void setPlanDao(PlanDao tourDao) {
    this.planDao = tourDao;
  }

  @Autowired
  public void setCityDao(CityDao cityDao) {
    this.cityDao = cityDao;
  }

  @Autowired
  public void setListDao(ListDao birthplaceDao) {
    this.listDao = birthplaceDao;
  }

  @Override
  public String execute() throws Exception {

    ReportUser user = (ReportUser) ActionContext.getContext().getSession()
        .get(ORStatics.REPORT_USER);

    Report report = (Report) ActionContext.getContext().getSession()
        .get(ORStatics.REPORT);

    // int exportTypeCode =
    // Integer.parseInt(
    // (String)
    // ActionContext.getContext().getSession().get(ORStatics.EXPORT_TYPE));

    ExportType exportType = ExportType.PDF;

    try {
      report = reportProvider.getReport(reportId);
    } catch (ProviderException e1) {
      e1.printStackTrace();
    }

    HttpServletResponse response = ServletActionContext.getResponse();

    // set headers to disable caching
    response.setHeader("Pragma", "public");
    response.setHeader("Cache-Control", "max-age=0");

    ServletOutputStream out = response.getOutputStream();

    // Setup XSLT
    TransformerFactory factory = TransformerFactory.newInstance();
    Transformer transformer = null;
    Source src = null;

    switch (reportId) {
    case 2: {
      // 旅游线路明细
      String routeName = null;
      for (Parameter param : parameters) {
        if (param.getName().equals("ROUTE_NO"))
          routeName = param.getData();
      }
      Line route = lineDao.get(routeName);
      route.setSchedule(routeScheduleDao.getLineSchedule(routeName));
      route.setExpenseIn(routeTraitDao.getLineTrait(routeName,
          KeyParams.EBIZ_TYPE_EXPENSE_INC));

      // Setup input for XSLT transformation
      src = route.getSource();
    }
      break;
    case 3: {
      // 简单打印
      String tourNo = null;
      String simplePrint = null;
      for (Parameter param : parameters) {
        if (param.getName().equals("TOUR_NO"))
          tourNo = param.getData();

        if (param.getName().equals("SIMPLE_PRINT"))
          simplePrint = param.getData();
      }
      // 取得团信息，客人名单
      Plan tour = planDao.getTourInfo(tourNo, true, false);
      tour.setSimpleReport(simplePrint);
      src = tour.getSource();
    }
      break;
    case 4: {
      // 打印客人名单
      String tourNo = null;
      String title = null;
      String remark = null;
      String receive = null;
      String send = null;
      String fileTitle = null;

      String printName = null;
      String printPinyin = null;
      String printSex = null;
      String printAge = null;
      String printAgent = null;
      String printIdcard = null;

      String printPassportType = null;
      String printPassportNo = null;
      String printPassportDate = null;
      String printPassportExpiry = null;
      String printPassportPla = null;
      String printPassportAnnotation = null;
      for (Parameter param : parameters) {
        if (param.getName().equals("TOUR_NO"))
          tourNo = param.getData();
        if (param.getName().equals("TITLE"))
          title = param.getData();
        if (param.getName().equals("REMARK"))
          remark = param.getData();
        if (param.getName().equals("RECEIVE"))
          receive = param.getData();
        if (param.getName().equals("SEND"))
          send = param.getData();
        if (param.getName().equals("FILETITLE"))
          fileTitle = param.getData();

        if (param.getName().equals("PRINTNAME"))
          printName = param.getData();
        if (param.getName().equals("PRINTPINYIN"))
          printPinyin = param.getData();
        if (param.getName().equals("PRINTSEX"))
          printSex = param.getData();
        if (param.getName().equals("PRINTAGE"))
          printAge = param.getData();
        if (param.getName().equals("PRINTAGENT"))
          printAgent = param.getData();
        if (param.getName().equals("PRINTIDCARD"))
          printIdcard = param.getData();

        if (param.getName().equals("PRINTPASSPORTTYPE"))
          printPassportType = param.getData();
        if (param.getName().equals("PRINTPASSPORTNO"))
          printPassportNo = param.getData();
        if (param.getName().equals("PRINTPASSPORTDATE"))
          printPassportDate = param.getData();
        // 是否打印护照有效期
        if (param.getName().equals("PRINTPASSPORTEXPIRY"))
          printPassportExpiry = param.getData();
        if (param.getName().equals("PRINTPASSPORTPLA"))
          printPassportPla = param.getData();
        if (param.getName().equals("PRINTPASSPORTANNOTATION"))
          printPassportAnnotation = param.getData();
      }

      // 取得团信息，客人名单
      Plan tour = planDao.getTourInfo(tourNo, true, false);
      List<LabelValueBean> passportPlace = new ArrayList<LabelValueBean>();
      passportPlace = listDao.getList("Homeplace");

      for (int m = 0; m < tour.getCustomerList().size(); m++) {
        for (int l = 0; l < passportPlace.size(); l++) {
          if (tour.getCustomerList().get(m).getPassportPlace() != null
              && (tour.getCustomerList().get(m).getPassportPlace()).trim()
                  .equals(passportPlace.get(l).getLabel()))
            tour.getCustomerList().get(m)
                .setPassportPlace(passportPlace.get(l).getValue());
        }
      }

      tour.setTitle(title);
      tour.setRemarks(remark);
      tour.setReceive(receive);
      tour.setSend(send);
      tour.setFileTitle(fileTitle);

      tour.setPrintName(printName);
      tour.setPrintPinyin(printPinyin);
      tour.setPrintSex(printSex);
      tour.setPrintAge(printAge);
      tour.setPrintAgent(printAgent);
      tour.setPrintIdcard(printIdcard);

      tour.setPrintPassportType(printPassportType);
      tour.setPrintPassportNo(printPassportNo);
      tour.setPrintPassportDate(printPassportDate);
      tour.setPrintPassportExpiry(printPassportExpiry);
      tour.setPrintPassportPla(printPassportPla);
      tour.setPrintPassportAnnotation(printPassportAnnotation);
      src = tour.getSource();
    }
      break;
    case 5: {
      // 境外报团名单
      String tourNo = null;
      String objectType = null;
      String userName = null;
      String label1 = null;
      String label2 = null;
      String label3 = null;
      for (Parameter param : parameters) {
        if (param.getName().equals("TOUR_NO"))
          tourNo = param.getData();
        if (param.getName().equals("LABEL_ONE"))
          label1 = param.getData();
        if (param.getName().equals("LABEL_TWO"))
          label2 = param.getData();
        if (param.getName().equals("LABEL_THREE"))
          label3 = param.getData();
        if (param.getName().equals("OBJECT_TYPE"))
          objectType = param.getData();
        if (param.getName().equals("USER_NAME"))
          userName = param.getData();
      }

      Plan tour = planDao.getTourInfo(tourNo, true, false);
      if (null != objectType && !objectType.equals("")) {
        TourOutBound outBandObject = new TourOutBound();
        outBandObject.setTourNo(tourNo);
        outBandObject.setType(objectType);
        outBandObject.setText1(label1);
        outBandObject.setText2(label2);
        outBandObject.setText3(label3);
        outBandObject.setOpUserName(userName);
        tourService.txSaveOutBandObject(outBandObject);
      }
      tour.setLabel1(label1);
      tour.setLabel2(label2);
      tour.setLabel3(label3);
      src = tour.getSource();
    }
      break;
    case 6: {
      // 同行名单表打印
      String tourNo = null;
      for (Parameter param : parameters) {
        if (param.getName().equals("TOUR_NO"))
          tourNo = param.getData();
      }
      Plan tour = planDao.getTourInfo(tourNo, true, false);

      List<LabelValueBean> birthCitys = new ArrayList<LabelValueBean>();
      List<LabelValueBean> passportPlace = new ArrayList<LabelValueBean>();
      birthCitys = listDao.getList("Homeplace");
      passportPlace = listDao.getList("Homeplace");

      for (int m = 0; m < tour.getCustomerList().size(); m++) {
        for (int n = 0; n < birthCitys.size(); n++) {
          if (tour.getCustomerList().get(m).getBirthplace() != null
              && ((tour.getCustomerList().get(m).getBirthplace()).trim())
                  .equals(birthCitys.get(n).getLabel()))
            tour.getCustomerList().get(m)
                .setBirthplace(birthCitys.get(n).getValue());
        }

        for (int l = 0; l < passportPlace.size(); l++) {
          if (tour.getCustomerList().get(m).getPassportPlace() != null
              && (tour.getCustomerList().get(m).getPassportPlace()).trim()
                  .equals(passportPlace.get(l).getLabel()))
            tour.getCustomerList().get(m)
                .setPassportPlace(passportPlace.get(l).getValue());
        }
      }

      src = tour.getSource();
    }
      break;
    case 7: {
      // 出境游客人名单表打印
      String tourSerialNumber = null;
      String tourNo = null;

      String year = null;
      String routeName = null;
      String pax = null;
      String malePax = null;
      String femalePax = null;
      String localTname = null;
      String localEcontact = null;
      String receptionTname = null;
      String receptionEcontact = null;
      String leader = null;
      String leaderKey = null;
      String outDate = null;
      String outCity = null;
      String inDate = null;
      String inCity = null;
      String outInKey = null;

      for (Parameter param : parameters) {
        if (param.getName().equals("TOURSERIALNUMBER"))
          tourSerialNumber = param.getData();
        if (param.getName().equals("TOUR_NO"))
          tourNo = param.getData();
        if (param.getName().equals("YEAR"))
          year = param.getData();
        if (param.getName().equals("ROUTENAME"))
          routeName = param.getData();
        if (param.getName().equals("PAX"))
          pax = param.getData();
        if (param.getName().equals("MALEPAX"))
          malePax = param.getData();
        if (param.getName().equals("FEMALEPAX"))
          femalePax = param.getData();
        if (param.getName().equals("LOCALTNAME"))
          localTname = param.getData();
        if (param.getName().equals("LOCALECONTACT"))
          localEcontact = param.getData();
        if (param.getName().equals("RECEPTIONTOUR"))
          receptionTname = param.getData();
        if (param.getName().equals("RECEPTIONCONNECTER"))
          receptionEcontact = param.getData();
        if (param.getName().equals("LEADER"))
          leader = param.getData();
        if (param.getName().equals("LEADERKEY"))
          leaderKey = param.getData();

        if (param.getName().equals("OUTDATE"))
          outDate = param.getData();
        if (param.getName().equals("OUTCITY"))
          outCity = param.getData();
        if (param.getName().equals("INDATE"))
          inDate = param.getData();
        if (param.getName().equals("INCITY"))
          inCity = param.getData();
        if (param.getName().equals("OUTINKEY"))
          outInKey = param.getData();
      }
      Plan tour = new Plan();
      tour.setCustomerList(touristDao.findByNmno(tourNum));

      tour.setTourSerialNumber(tourSerialNumber);
      tour.setTourNo(tourNo);
      tour.setYear(year);
      tour.getLine().setLineName(routeName);
      tour.setPrintPax(pax);
      tour.setPrintMalePax(malePax);
      tour.setPrintFemalePax(femalePax);
      tour.setLocalTname(localTname);
      tour.setLocalEcontact(localEcontact);
      tour.setReceptionTname(receptionTname);
      tour.setReceptionEcontact(receptionEcontact);

      tour.setLeader(leader);
      tour.setLeaderKey(leaderKey);

      tour.setPrintOutDate(outDate);
      tour.getLine().getOutCity().setCitycd(outCity);
      tour.setPrintInDate(inDate);
      tour.setInCity(inCity);
      tour.setOutInKey(outInKey);

      List<LabelValueBean> birthCitys = new ArrayList<LabelValueBean>();
      List<LabelValueBean> passportPlace = new ArrayList<LabelValueBean>();
      birthCitys = listDao.getList("Homeplace");
      passportPlace = listDao.getList("Homeplace");
      List<City> citys = new ArrayList<City>();
      citys = cityDao.getAllCity();
      int lea = 0;

      for (int m = 0; m < tour.getCustomerList().size(); m++) {
        for (int n = 0; n < birthCitys.size(); n++) {
          if (((tour.getCustomerList().get(m).getPrintBirPla()).trim())
              .equals(birthCitys.get(n).getLabel()))
            tour.getCustomerList().get(m)
                .setPrintBirPla(birthCitys.get(n).getValue());

        }
        for (int l = 0; l < passportPlace.size(); l++) {
          if ((tour.getCustomerList().get(m).getPrintPassportPla()).trim()
              .equals(passportPlace.get(l).getLabel())) {
            String str = passportPlace.get(l).getValue();
            if (str.length() > 4)
              str = str.substring(0, 4);
            tour.getCustomerList().get(m).setPrintPassportPla(str);
          }
        }

        if ((tour.getCustomerList().get(m).getNmno().trim()).equals(leader
            .trim())) {

          tour.setLeader(tour.getCustomerList().get(m).getPrintName());

          if ("True".equals(leaderKey.trim())) {

            tour.setLeaderPrintName(tour.getCustomerList().get(m)
                .getPrintName());
            tour.setLeaderPrintPinyin(tour.getCustomerList().get(m)
                .getPrintPinyin());
            tour.setLeaderPrintSex(tour.getCustomerList().get(m).getPrintSex());
            if (null == tour.getCustomerList().get(m).getPrintBirthday())
              tour.setLeaderPrintBirthday("");
            else
              tour.setLeaderPrintBirthday(SDF1.format(tour.getCustomerList()
                  .get(m).getPrintBirthday()));
            tour.setLeaderPrintBirPla(tour.getCustomerList().get(m)
                .getPrintBirPla());
            tour.setLeaderPrintPassportNo(tour.getCustomerList().get(m)
                .getPrintPassportNo());
            tour.setLeaderPrintPassportPla(tour.getCustomerList().get(m)
                .getPrintPassportPla());
            tour.setLeaderPrintPassportDate(tour.getCustomerList().get(m)
                .getPrintPassportDate());

            lea = m;
          }
        }

      }
      tour.getCustomerList().remove(lea);

      // for (int i = 0; i < citys.size(); i++)
      // {
      // if ((tour.getInCity().trim()).equals(citys.get(i).getCitycd()))
      // tour.setInCity(citys.get(i).getCitynm());
      // if ((tour.getOutCity().trim()).equals(citys.get(i).getCitycd()))
      // tour.setOutCity(citys.get(i).getCitynm());
      // }

      // tour.

      src = tour.getSource();
    }
      break;
    case 8: {
      // 港澳游客人名单表打印
      String tourNo = null;
      String pla1 = null;
      String pass1 = null;
      String end1 = null;
      String pla2 = null;
      String pass2 = null;
      String end2 = null;
      String pla3 = null;
      String pass3 = null;
      String end3 = null;

      String arrHKtime = null;
      String arrHKvehicle = null;
      String leaveHKtime = null;
      String leaveHKvehicle = null;
      String arrMCtime = null;
      String arrMCvehicle = null;
      String leaveMCtime = null;
      String leaveMCvehicle = null;

      String localTname = null;
      String HKTname = null;
      String MCTname = null;
      String localEcontact = null;
      String HKEcontact = null;
      String MCEcontact = null;

      String gapax = null;
      String gamalePax = null;
      String gafemalePax = null;
      String gachildPax = null;

      String leader = null;
      String leadnum = null;
      for (Parameter param : parameters) {
        if (param.getName().equals("TOUR_NO"))
          tourNo = param.getData();

        if (param.getName().equals("PLA1"))
          pla1 = param.getData();
        if (param.getName().equals("PASS1"))
          pass1 = param.getData();
        if (param.getName().equals("END1"))
          end1 = param.getData();

        if (param.getName().equals("PLA2"))
          pla2 = param.getData();
        if (param.getName().equals("PASS2"))
          pass2 = param.getData();
        if (param.getName().equals("END2"))
          end2 = param.getData();

        if (param.getName().equals("PLA3"))
          pla3 = param.getData();
        if (param.getName().equals("PASS3"))
          pass3 = param.getData();
        if (param.getName().equals("END3"))
          end3 = param.getData();

        if (param.getName().equals("ARRHKTIME"))
          arrHKtime = param.getData();
        if (param.getName().equals("ARRHKVEHICLE"))
          arrHKvehicle = param.getData();
        if (param.getName().equals("LEAVEHKTIME"))
          leaveHKtime = param.getData();
        if (param.getName().equals("LEAVEHKVEHICLE"))
          leaveHKvehicle = param.getData();

        if (param.getName().equals("ARRMCTIME"))
          arrMCtime = param.getData();
        if (param.getName().equals("ARRMCVEHICLE"))
          arrMCvehicle = param.getData();
        if (param.getName().equals("LEAVEMCTIME"))
          leaveMCtime = param.getData();
        if (param.getName().equals("LEAVEMCVEHICLE"))
          leaveMCvehicle = param.getData();

        if (param.getName().equals("LOCALTNAME"))
          localTname = param.getData();
        if (param.getName().equals("HKTNAME"))
          HKTname = param.getData();
        if (param.getName().equals("MCTNAME"))
          MCTname = param.getData();
        if (param.getName().equals("LOCALECON"))
          localEcontact = param.getData();
        if (param.getName().equals("HKECON"))
          HKEcontact = param.getData();
        if (param.getName().equals("MCECON"))
          MCEcontact = param.getData();

        if (param.getName().equals("MALEPAX"))
          gamalePax = param.getData();
        if (param.getName().equals("FEMALEPAX"))
          gafemalePax = param.getData();
        if (param.getName().equals("CHILDPAX"))
          gachildPax = param.getData();
        if (param.getName().equals("PAX"))
          gapax = param.getData();

        if (param.getName().equals("TNUM"))
          tourNo = param.getData();
        if (param.getName().equals("LEADER"))
          leader = param.getData();
        if (param.getName().equals("LEADNUM"))
          leadnum = param.getData();
      }

      Plan tour = new Plan();
      tour.setCustomerList(touristDao.findByNmno(tourNum));

      List<LabelValueBean> birthCitys = new ArrayList<LabelValueBean>();
      birthCitys = listDao.getList("Homeplace");
      List<City> citys = new ArrayList<City>();
      citys = cityDao.getAllCity();

      for (int m = 0; m < tour.getCustomerList().size(); m++) {
        for (int n = 0; n < birthCitys.size(); n++) {
          if (((tour.getCustomerList().get(m).getPrintBirPla()).trim())
              .equals(birthCitys.get(n).getLabel()))
            tour.getCustomerList().get(m)
                .setPrintBirPla(birthCitys.get(n).getValue());
          if ((tour.getCustomerList().get(m).getPrintPassportPla()).trim()
              .equals(birthCitys.get(n).getLabel()))
            tour.getCustomerList().get(m)
                .setPrintPassportPla(birthCitys.get(n).getValue());
        }
      }
      tour.setTourNo(tourNo);
      tour.setLeader(leader);
      tour.setLeadnum(leadnum);

      if (null != arrHKdate)
        tour.setArrHKdate(SDF3.format(arrHKdate));
      else
        tour.setArrHKdate("");
      tour.setArrHKtime(arrHKtime);
      tour.setArrHKvehicle(arrHKvehicle);
      if (null != arrMCdate)
        tour.setArrMCdate(SDF3.format(arrMCdate));
      else
        tour.setArrMCdate("");
      tour.setArrMCtime(arrMCtime);
      tour.setArrMCvehicle(arrMCvehicle);
      if (null != leaveHKdate)
        tour.setLeaveHKdate(SDF3.format(leaveHKdate));
      else
        tour.setLeaveHKdate("");
      tour.setLeaveHKtime(leaveHKtime);
      tour.setLeaveHKvehicle(leaveHKvehicle);
      if (null != leaveMCdate)
        tour.setLeaveMCdate(SDF3.format(leaveMCdate));
      else
        tour.setLeaveMCdate("");
      tour.setLeaveMCtime(leaveMCtime);
      tour.setLeaveMCvehicle(leaveMCvehicle);

      tour.setGapax(gapax);
      tour.setGamalePax(gamalePax);
      tour.setGafemalePax(gafemalePax);
      tour.setGachildPax(gachildPax);

      if (null != date1)
        tour.setDate1(SDF2.format(date1));
      else
        tour.setDate1("");
      tour.setPla1(pla1);
      tour.setPass1(pass1);
      tour.setEnd1(end1);
      if (null != date2)
        tour.setDate2(SDF2.format(date2));
      else
        tour.setDate2("");
      tour.setPla2(pla2);
      tour.setPass2(pass2);
      tour.setEnd2(end2);
      if (null != date3)
        tour.setDate3(SDF2.format(date3));
      else
        tour.setDate3("");
      tour.setPla3(pla3);
      tour.setPass3(pass3);
      tour.setEnd3(end3);

      tour.setLocalTname(localTname);
      tour.setLocalEcontact(localEcontact);
      tour.setHKTname(HKTname);
      tour.setHKEcontact(HKEcontact);
      tour.setMCTname(MCTname);
      tour.setMCEcontact(MCEcontact);

      src = tour.getSource();
    }
      break;
    case 9: {
      // 记录打印状态
      int ret = reckoningMakeService.txSetPrint(reckoningId);

      Reckoning reckoning = new Reckoning();
      List<ReckoningAcct> reckoningAcctList = new ArrayList<ReckoningAcct>();
      book = bookingService.roGetReserve(reserveNo);
      reckoning = reckoningDao.getReckoningInfo(reckoningId);

      Plan tour = reckoningMakeService
          .roGetTourInfo(book.getPlan().getTourNo());
      if (null != tour)
        reckoning.setLeaderPax(tour.getLeaderPax());

      BigDecimal amount1 = new BigDecimal(0);
      for (int i = 0; i < reckoning.getReckoningAcctList().size(); i++) {
        amount1 = amount1.add(reckoning.getReckoningAcctList().get(i)
            .getAmount());
      }
      reckoning.setAmount(amount1);

      reckoning.setClient(book.getCustomer().getName());
      reckoning.setOutDate(book.getPlan().getOutDate());
      reckoning.setRouteName(book.getPlan().getLine().getLineName());
      reckoning.setTourNo(book.getPlan().getTourNo());
      reckoning.setPax(book.getPax());

      src = reckoning.getSource();

    }
      break;

    case 10: {
      // 记录打印状态
      int ret = reckoningMakeService.txSetPrint(reckoningId);

      Reckoning reckoning = new Reckoning();
      List<ReckoningAcct> reckoningAcctList = new ArrayList<ReckoningAcct>();
      book = bookingService.roGetReserve(reserveNo);
      reckoning = reckoningDao.getReckoningInfo(reckoningId);

      reckoningAcctList = reckoningMakeService.roGetCustomerList(reserveNo);
      if (!(reckoningAcctList.isEmpty())) {
        BigDecimal amount = new BigDecimal(0);
        for (ReckoningAcct obj : reckoningAcctList) {
          amount = amount.add(obj.getAmount());
        }
        reckoning.setAmount(amount);
      }

      reckoning.setClient(book.getCustomer().getName());
      reckoning.setOutDate(book.getPlan().getOutDate());
      reckoning.setRouteName(book.getPlan().getLine().getLineName());
      reckoning.setTourNo(book.getPlan().getTourNo());
      reckoning.setPax(book.getPax());
      reckoning.setReckoningAcctList(reckoningAcctList);

      src = reckoning.getSource();
    }
      break;

    case 11: {
      // 记录打印状态
      int ret = reckoningMakeService.txSetPrint(reckoningId);

    }
      break;

    case 12: {
      // 付款申请书打印
      int outcomeId = 0;
      for (Parameter param : parameters) {
        if (param.getName().equals("OUTCOME_ID"))
          outcomeId = new Integer(param.getData());
      }

      Outcome billhead = outcomeService.roGetBillhead(outcomeId);
      List<LabelValueBean> payModeList = new ArrayList<LabelValueBean>();
      payModeList = getCodeList("ebiz_pay_mode");
      for (LabelValueBean labelValueBean : payModeList) {
        if (labelValueBean.getValue().toString()
            .equals(billhead.getPayMode().toString())) {
          billhead.setPayModeName(labelValueBean.getLabel());
          break;
        }
      }

      src = billhead.getSource();
    }
      break;

    case 13: {
      // 单团核算表打印
      String tourNo = "";
      for (Parameter param : parameters) {
        if (param.getName().equals("TOUR_NO"))
          tourNo = param.getData();
      }

      // 取用户信息
      Employee suser = (Employee) ServletActionContext.getRequest()
          .getSession().getAttribute(SessionKeyParams.EBIZ_USER);
      List<TourCost> costList = new ArrayList<TourCost>();

      Plan tour = tourService.roGetTourInfo(tourNo, false, true);
      // List<Customer> supplierList = customerService.getUsableSupplier(0);
      // for (TourCost costAcct : tour.getCostList()) {
      // for (Customer supplier : supplierList) {
      // if (costAcct.getCustomer().getCustomerId() == supplier
      // .getSupplierId()) {
      // costAcct.getCustomer().setName(supplier.getSupplierName());
      // break;
      // }
      // }
      // }

      List<Booking> bookList = tourService.roGetBookList(tourNo);
      if (!bookList.isEmpty()) {
        tour.setBookList(bookList);
      }

      BigDecimal amount = new BigDecimal(0);
      BigDecimal payCosts = new BigDecimal(0);
      int pax = 0;

      String str = new String();
      for (Booking book : bookList) {
        amount = amount.add(book.getDbamt()).add(book.getFinalExpense());
        payCosts = payCosts.add(book.getPayCosts());
        pax += book.getPax();
        if (null != book.getLeaders() && !"".equals(book.getLeaders()))
          str = str + book.getLeaders();
      }
      tour.setLeaderName(str);
      tour.setTotalPax(pax);

      if (null != tour && null != tour) {
        tour.setTourNo(tour.getTourNo());
        tour.setLine(tour.getLine());
        tour.setOutDate(tour.getOutDate());
        tour.setPax(tour.getPax());
        tour.setLeaderPax(tour.getLeaderPax());
        tour.setDoubleRoom(tour.getDoubleRoom());
        tour.setSingleRoom(tour.getSingleRoom());
        tour.setExtraBedRoom(tour.getExtraBedRoom());
        tour.setMuAmount(amount);
        tour.setAlAmount(payCosts);
        tour.setWiAmount(amount.subtract(payCosts));
        tour.setRemarks(tour.getRemarks());
        tour.setTeam(tour.getTeam());
        if (null != suser) {
          tour.setOprateUserName(suser.getUserName());
        }
        tour.setGrossAmount(tour.getTourAmount().subtract(tour.getCost()));

        // 计算毛利率
        BigDecimal grossAmountRate = new BigDecimal(0);
        if (tour.getTourAmount().doubleValue() != 0)
          grossAmountRate = tour.getGrossAmount().divide(tour.getTourAmount())
              .multiply(new BigDecimal(100));

        tour.setGrossAmountRate(grossAmountRate);

        costList = tour.getCostList();

      }
      BigDecimal grossAmountAverage = tour.getGrossAmount().divide(
          new BigDecimal(tour.getPax()));
      tour.setGrossAmountAverage(grossAmountAverage);

      if (costList.isEmpty()) {
        costList.add(new TourCost());
      }

      src = tour.getSource();
    }
      break;

    default:
      break;
    }

    Result res = null;

    if (exportType == ExportType.PDF) {
      FopFactory fopFactory = ApacheFopFactory.instance();
      FOUserAgent foUserAgent = fopFactory.newFOUserAgent();

      // Allows/disallows copy/paste of content
      foUserAgent.getRendererOptions().put("nocopy", true);
      // Allows/disallows printing of the PDF
      // foUserAgent.getRendererOptions().put("noprint", "FALSE");
      // Allows/disallows editing of content
      foUserAgent.getRendererOptions().put("noedit", true);
      // Allows/disallows editing of annotations
      foUserAgent.getRendererOptions().put("noannotations", true);

      // Construct fop with desired output format
      Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);

      String path = ServletActionContext.getServletContext().getRealPath("");
      path = path + separator + "WEB-INF" + separator + "reports" + separator;

      // String baseDir = directoryProvider.getReportDirectory();

      File file = new File(path + report.getFile());

      transformer = factory.newTransformer(new StreamSource(file));
      // Resulting SAX events (the generated FO) must be piped through to
      // FOP
      res = new SAXResult(fop.getDefaultHandler());

    } else if (exportType == ExportType.XML) {
      transformer = factory.newTransformer();
      res = new StreamResult(out);
    }

    // Start XSLT transformation and FOP processing
    transformer.transform(src, res);

    // fopFactory.
    return NONE;
  }

  /**
   * 取xml配置文件的值
   * 
   * @param argKey
   * @return
   */
  protected List<LabelValueBean> getCodeList(String argKey) {
    ArrayList<LabelValueBean> msgList = new ArrayList<LabelValueBean>();
    String XML_PATH = "WEB-INF/ebizConfig.xml";
    XMLUtility m_res_Config = null;
    try {
      if (null == m_res_Config) {
        m_res_Config = XMLUtility.getInstance(ServletActionContext
            .getServletContext().getRealPath(XML_PATH));
      }
    } catch (Exception pce) {
      pce.printStackTrace();
    }
    List temp = m_res_Config.getData(argKey, "OPTION");
    String[] listValue = null;
    for (int i = 0; i < temp.size(); i++) {
      listValue = ((String) temp.get(i)).split(",");
      msgList.add(new LabelValueBean(listValue[1], listValue[0]));
    }

    return msgList;
  }

  public void setReportId(int reportId) {
    this.reportId = reportId;
  }

  public int getReportId() {
    return reportId;
  }

  public List<Parameter> getParameters() {
    return parameters;
  }

  public void setParameters(List<Parameter> parameters) {
    this.parameters = parameters;
  }

  public String[] getTourNum() {
    return tourNum;
  }

  public void setTourNum(String[] tourNum) {
    this.tourNum = tourNum;
  }

  public Date getArrHKdate() {
    return arrHKdate;
  }

  public void setArrHKdate(Date arrHKdate) {
    this.arrHKdate = arrHKdate;
  }

  public Date getArrMCdate() {
    return arrMCdate;
  }

  public void setArrMCdate(Date arrMCdate) {
    this.arrMCdate = arrMCdate;
  }

  public Date getDate1() {
    return date1;
  }

  public void setDate1(Date date1) {
    this.date1 = date1;
  }

  public Date getDate2() {
    return date2;
  }

  public void setDate2(Date date2) {
    this.date2 = date2;
  }

  public Date getDate3() {
    return date3;
  }

  public void setDate3(Date date3) {
    this.date3 = date3;
  }

  public Date getLeaveHKdate() {
    return leaveHKdate;
  }

  public void setLeaveHKdate(Date leaveHKdate) {
    this.leaveHKdate = leaveHKdate;
  }

  public Date getLeaveMCdate() {
    return leaveMCdate;
  }

  public void setLeaveMCdate(Date leaveMCdate) {
    this.leaveMCdate = leaveMCdate;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getReserveNo() {
    return reserveNo;
  }

  public void setReserveNo(String reserveNo) {
    this.reserveNo = reserveNo;
  }

  public int getReckoningId() {
    return reckoningId;
  }

  public void setReckoningId(int reckoningId) {
    this.reckoningId = reckoningId;
  }

  public Booking getBook() {
    return book;
  }

  public void setBook(Booking book) {
    this.book = book;
  }

  public String getProCd() {
    return proCd;
  }

  public void setProCd(String proCd) {
    this.proCd = proCd;
  }

  public String getCityCd() {
    return cityCd;
  }

  public void setCityCd(String cityCd) {
    this.cityCd = cityCd;
  }

  public Date getStDate() {
    return stDate;
  }

  public void setStDate(Date stDate) {
    this.stDate = stDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

}
