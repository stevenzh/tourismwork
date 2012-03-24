package com.opentravelsoft.action.manage.operator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.City;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.entity.Tourist;
import com.opentravelsoft.service.operator.TourService;
import com.opentravelsoft.service.order.BookingService;
import com.opentravelsoft.service.setting.EmployeeService;

/**
 * 出境游名单打印
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.2 $ $Date: 2009/04/10 07:47:29 $
 */
public class ToursSearchPrintAction extends ManageAction {
  private static final long serialVersionUID = -4562101264530149531L;

  protected static final Log logger = LogFactory
      .getLog(ToursSearchPrintAction.class);

  protected SimpleDateFormat SDF = new SimpleDateFormat("yyyy　　MM　　dd");

  private TourService tourService;

  private BookingService bookingService;

  private EmployeeService employeeSevice;

  private String[] tourNos;

  private String[] tourNum;

  /** 部门 */
  private long kenTeamId;

  /** 专管员 */
  private long kenUserId;

  /** 线路名 */
  private String kenLineName;

  /** 出团日期 -开始 */
  private Date kenStartDate;

  /** 出团日期 -截止 */
  private Date kenEndDate;

  /** 建团人 */
  private Integer kenCreator;

  private List<Employee> employees;

  private List<Team> teamList;

  private List<Plan> tours = new ArrayList<Plan>();

  private List<City> citys = new ArrayList<City>();

  private List<LabelValueBean> leaders = new ArrayList<LabelValueBean>();

  private Plan tour = new Plan();

  @Autowired
  public void setEmployeeSevice(EmployeeService employeeSevice) {
    this.employeeSevice = employeeSevice;
  }

  @Autowired
  public void setTourService(TourService tourService) {
    this.tourService = tourService;
  }

  @Autowired
  public void setBookingService(BookingService bookService) {
    this.bookingService = bookService;
  }

  public String execute() {
    Employee user = getUser();
    logger.debug("查找团列表页面初始化，开始...");

    kenUserId = user.getUserId();
    teamList = tourService.getOperatorTeamList();
    if (teamList.size() > 0)
      kenTeamId = teamList.get(0).getTeamId();
    employees = employeeSevice.getUserByTeam(kenTeamId);

    return SUCCESS;
  }

  public String search() {
    logger.debug("查找团列表, 开始...");
    employees = employeeSevice.getUserByTeam(kenTeamId);
    teamList = tourService.getOperatorTeamList();
    // 查找团
    tours = tourService.roGetTours(kenTeamId, kenUserId, kenLineName,
        kenStartDate, kenEndDate);
    currentPage(tours.size());

    return SUCCESS;
  }

  public String next() {
    String tourString = new String();
    for (int j = 0; j < tourNum.length; j++)
      tourString = tourString + "," + tourNum[j];
    tourNum = tourString.split(",");

    // 取得多个团信息（包含名单）
    tours = tourService.roGetToursAndCustomer(tourNos);

    citys = tourService.toGetAllCity();
    List<LabelValueBean> birthCitys = tourService.roGetBirthplaceList();
    List<LabelValueBean> passportPlaces = bookingService
        .roGetPassportPlaceList();

    // 将城市代码转化为城市名
    // --------------------------------------------------------------------------
    for (int k = 0; k < tours.size(); k++) {
      // for (int i = 0; i < citys.size(); i++)
      // {
      // if ((tours.get(k).getInCity().trim()).equals(citys.get(i)
      // .getCitycd()))
      // tours.get(k).setInCity(citys.get(i).getCitynm());
      // if ((tours.get(k).getOutCity().trim()).equals(citys.get(i)
      // .getCitycd()))
      // tours.get(k).setOutCity(citys.get(i).getCitynm());
      // if ((tours.get(k).getVenue().trim()).equals(citys.get(i)
      // .getCitycd()))
      // tours.get(k).setVenue(citys.get(i).getCitynm());
      // }
      if (null != tours.get(k).getCustomerList()
          && (!(tours.get(k).getCustomerList().isEmpty()))) {
        for (int m = 0; m < tours.get(k).getCustomerList().size(); m++) {
          for (int n = 0; n < birthCitys.size(); n++) {
            if (((tours.get(k).getCustomerList().get(m).getBirthplace()).trim())
                .equals(birthCitys.get(n).getLabel()))
              tours.get(k).getCustomerList().get(m)
                  .setBirthplace(birthCitys.get(n).getValue());

          }
          for (int l = 0; l < passportPlaces.size(); l++) {
            if ((tours.get(k).getCustomerList().get(m).getPassportPlace())
                .trim().equals(passportPlaces.get(l).getLabel()))
              tours.get(k).getCustomerList().get(m)
                  .setPassportPlace(passportPlaces.get(l).getValue());
          }
        }
      }
    }
    // -------------------------------------------------------------------------

    // 判断是否已选
    // -----------------------------------------------------------------------
    int m;
    for (int n = 0; n < tours.size(); n++) {
      m = 0;
      if (null != tours.get(n).getCustomerList()
          && (!(tours.get(n).getCustomerList().isEmpty()))) {
        for (int j = 0; j < tours.get(n).getCustomerList().size(); j++) {
          for (int i = 0; i < tourNum.length; i++) {
            if ((tours.get(n).getCustomerList().get(j).getNmno().trim())
                .equals(tourNum[i].trim())) {
              tours.get(n).getCustomerList().get(j).setStauts(1);
              m++;
            }
          }
        }
      }
      if (m == 0 && null != tours.get(n).getCustomerList())
        for (int k = 0; k < tours.get(n).getCustomerList().size(); k++)
          tours.get(n).getCustomerList().get(k).setStauts(1);
    }
    // ----------------------------------------------------------------------
    // 清空数组
    tourNum = null;

    return SUCCESS;
  }

  public String back() {
    if (tourNos.length == 1) {
      tourNos = tourNos[0].split(",");
    }
    for (int i = 0; i < tourNos.length; i++)
      tourNos[i] = tourNos[i].trim();
    return SUCCESS;
  }

  public String print() {
    // tour
    Employee user = getUser();
    if (tourNos.length == 1) {
      tourNos = tourNos[0].split(",");
    }
    for (int i = 0; i < tourNos.length; i++) {
      if (!("".equals(tourNos[i].trim()))) {
        tour = tourService.roGetTourInfo(tourNos[i].trim(), false, false);
        break;
      }
    }

    Employee employee = employeeSevice.roGetEmployee(user.getUserId());

    List<Tourist> trips = tourService.roFindByNmno(tourNum);
    int malePax = 0;
    int femalePax = 0;
    int leadPax = 0;

    for (int i = 0; i < trips.size(); i++) {
      if ("M".equals(trips.get(i).getSex()))
        malePax++;
      else if ("F".equals(trips.get(i).getSex()))
        femalePax++;
      if ("Y".equals(trips.get(i).getLeaderKey())) {
        leadPax++;
        leaders.add(new LabelValueBean(trips.get(i).getUserName(), trips.get(i)
            .getNmno()));
      }

    }

    if (null == tour.getInDate())
      tour.setPrintInDate("");
    else
      tour.setPrintInDate(SDF.format(tour.getInDate()));
    if (null == tour.getOutDate())
      tour.setPrintOutDate("");
    tour.setPrintOutDate(SDF.format(tour.getOutDate()));
    tour.setLocalEcontact(employee.getUserName() + " " + employee.getPhone());

    tour.setPax(trips.size());
    tour.setMalePax(malePax);
    tour.setFemalePax(femalePax);
    tour.setLeaderPax(leadPax);

    return SUCCESS;
  }

  public String confirm() {
    return SUCCESS;
  }

  public long getKenDepartmentId() {
    return kenTeamId;
  }

  public void setKenDepartmentId(long teamId) {
    this.kenTeamId = teamId;
  }

  public long getKenEmployeeId() {
    return kenUserId;
  }

  public void setKenEmployeeId(long kenEmployeeId) {
    this.kenUserId = kenEmployeeId;
  }

  public String getKenRouteName() {
    return kenLineName;
  }

  public void setKenRouteName(String kenRouteName) {
    this.kenLineName = kenRouteName;
  }

  public Date getKenStartDate() {
    return kenStartDate;
  }

  public void setKenStartDate(Date kenStartDate) {
    this.kenStartDate = kenStartDate;
  }

  public Date getKenEndDate() {
    return kenEndDate;
  }

  public void setKenEndDate(Date kenEndDate) {
    this.kenEndDate = kenEndDate;
  }

  public List<Employee> getEmployees() {
    return employees;
  }

  public List<Team> getTeamList() {
    return teamList;
  }

  public Integer getKenCreator() {
    return kenCreator;
  }

  public void setKenCreator(Integer kenCreator) {
    this.kenCreator = kenCreator;
  }

  public List<Plan> getTours() {
    return tours;
  }

  public String[] getTourNos() {
    return tourNos;
  }

  public void setTourNos(String[] tourNos) {
    this.tourNos = tourNos;
  }

  public String[] getTourNum() {
    return tourNum;
  }

  public void setTourNum(String[] tourNum) {
    this.tourNum = tourNum;
  }

  public Plan getTour() {
    return tour;
  }

  public void setTour(Plan tour) {
    this.tour = tour;
  }

  public List<LabelValueBean> getLeaders() {
    return leaders;
  }

  public void setLeaders(List<LabelValueBean> leaders) {
    this.leaders = leaders;
  }

  public List<City> getCitys() {
    return citys;
  }

  public void setCitys(List<City> citys) {
    this.citys = citys;
  }

}
