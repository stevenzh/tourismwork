package com.opentravelsoft.action.manage.operate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.common.TeamType;
import com.opentravelsoft.entity.Airways;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.LinePrice;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.PlanPrice;
import com.opentravelsoft.entity.Team;
import com.opentravelsoft.service.operator.TourPlanService;
import com.opentravelsoft.service.product.LinePriceService;
import com.opentravelsoft.service.setting.EmployeeService;
import com.opentravelsoft.util.StringUtil;

/**
 * 修改出团计划
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.2 $ $Date: 2009/04/10 07:47:29 $
 */
public class EditPlanAction extends ManageAction {
  private static final long serialVersionUID = 5212732570107400742L;

  protected static final Log logger = LogFactory.getLog(EditPlanAction.class);

  @Autowired
  private TourPlanService tourPlanService;

  @Autowired
  private LinePriceService linePriceService;

  @Autowired
  private EmployeeService employeeSevice;

  /** 计划编号 */
  private String recordNo;

  private List<Employee> employeeList;

  private List<Team> teamList;

  private List<LabelValueBean> deploys;

  private List<LabelValueBean> favourables;

  private List<LabelValueBean> paxkey;

  private Plan plan;

  private List<LabelValueBean> shareStateList = new ArrayList<LabelValueBean>();

  private List<LabelValueBean> shareList = new ArrayList<LabelValueBean>();

  private List<LabelValueBean> singleFlag = new ArrayList<LabelValueBean>();

  /** 产品推广活动 */
  private List<LabelValueBean> traitList = new ArrayList<LabelValueBean>();

  private List<Airways> airways = new ArrayList<Airways>();

  // -------------------------------------------------------------------------
  // 查询条件

  /** 线路名 */
  private String kenLineName;

  /** 部门编号 */
  private String kenDepartment;

  /** 专管员 */
  private String kenPrincipal;

  /** 出团时间-开始 */
  private Date kenStartDate;

  /** 出团时间-截止 */
  private Date kenEndDate;

  private int shareId;

  /** 是否重新计算名额 */
  private int isReCount = 0;

  /** 当前日期对应的价格 */
  private List<LinePrice> priceList = new ArrayList<LinePrice>();

  /** 备注 */
  private String note;

  private String select;

  // -------------------------------------------------------------------------

  public EditPlanAction() {
    kenDepartment = "";
    kenPrincipal = "";
  }

  /**
   * 出团计划详细
   * 
   * @return
   * @throws Exception
   */
  public String input() {
    Employee user = getUser();
    deploys = getCodeList("ebiz_plan_deploy");
    favourables = getCodeList("ebiz_yes_no");
    paxkey = getCodeList("ebiz_pax_key");
    singleFlag = getCodeList("ebiz_plan_singleflag");
    shareStateList = getCodeList("ebiz_yes_no");
    shareList = tourPlanService.roSearchShare();
    traitList = tourPlanService.roGetTraitList();
    airways = tourPlanService.roGetAirways();

    // Get Plan
    plan = tourPlanService.roGetPlanDetail(recordNo);
    if (null == plan) {
      addActionError("Plan no found.");
      logger.warn("Plan no found.");
      return INPUT;
    }

    priceList = linePriceService.getLinePrice(plan.getLine().getLineNo(),
        plan.getOutDate(), plan.getOutDate());
    List<PlanPrice> planPrices = tourPlanService.roGetPrices(recordNo);
    for (LinePrice lc : priceList) {
      for (PlanPrice pc : planPrices) {
        if (lc.getRecNo().equals(pc.getPriceNo()))
          lc.setSelect(true);
        if (lc.getRecNo().equals(plan.getPackagePrice().getRecNo()))
          lc.setDefaultPrice(true);
      }
    }
    teamList = tourPlanService.getTeamList(user.getUserId(), TeamType.Operator);
    employeeList = employeeSevice.getUserByTeam(plan.getTeam().getTeamId());

    return INPUT;
  }

  /**
   * 修改出团计划
   * 
   * @return
   * @throws Exception
   */
  public String submit() {
    Employee user = getUser();
    List<PlanPrice> planPrices = new ArrayList<PlanPrice>();
    plan.setOpUser(user.getUserId());
    String[] se = select.split(",");
    for (int i = 0; i < se.length; i++) {
      if (StringUtil.hasLength(se[i]))
        planPrices.add(new PlanPrice(plan.getPlanNo(), se[i].trim()));
    }

    int ret = tourPlanService.txUpdatePlan(plan, planPrices, shareId, note);

    // 重新计算团计划名单
    if (1 == isReCount) {
      tourPlanService.txReCountPlanPax(plan.getPlanNo());
    }

    if (ret == 1) {
      addActionMessage("计划已修改,没有价格所以没有发布到网站上,请填写价格后重新提交计划.");
    } else if (ret < 0) {
      addActionError("计划修改失败.");
    }

    return SUCCESS;
  }

  protected int getMoveCount() {
    return 20;
  }

  public String getKenRrouteName() {
    return kenLineName;
  }

  public void setKenRrouteName(String kenRrouteName) {
    this.kenLineName = kenRrouteName;
  }

  public String getKenDepartment() {
    return kenDepartment;
  }

  public void setKenDepartment(String kenDepartment) {
    this.kenDepartment = kenDepartment;
  }

  public Date getKenEndDate() {
    return kenEndDate;
  }

  public void setKenEndDate(Date kenEndDate) {
    this.kenEndDate = kenEndDate;
  }

  public String getKenPrincipal() {
    return kenPrincipal;
  }

  public void setKenPrincipal(String kenPrincipal) {
    this.kenPrincipal = kenPrincipal;
  }

  public Date getKenStartDate() {
    return kenStartDate;
  }

  public void setKenStartDate(Date kenStartDate) {
    this.kenStartDate = kenStartDate;
  }

  public String getRecordNo() {
    return recordNo;
  }

  public void setRecordNo(String recordNo) {
    this.recordNo = recordNo;
  }

  public Plan getPlan() {
    return plan;
  }

  public void setPlan(Plan plan) {
    this.plan = plan;
  }

  public List<LabelValueBean> getDeploys() {
    return deploys;
  }

  public List<LabelValueBean> getFavourables() {
    return favourables;
  }

  public void setFavourables(List<LabelValueBean> favourables) {
    this.favourables = favourables;
  }

  public List<LabelValueBean> getPaxkey() {
    return paxkey;
  }

  public List<LabelValueBean> getShareStateList() {
    return shareStateList;
  }

  public void setShareStateList(List<LabelValueBean> shareStateList) {
    this.shareStateList = shareStateList;
  }

  public List<LabelValueBean> getShareList() {
    return shareList;
  }

  public void setShareList(List<LabelValueBean> shareList) {
    this.shareList = shareList;
  }

  public int getIsReCount() {
    return isReCount;
  }

  public void setIsReCount(int isReCount) {
    this.isReCount = isReCount;
  }

  public List<LinePrice> getPriceList() {
    return priceList;
  }

  public List<LabelValueBean> getSingleFlag() {
    return singleFlag;
  }

  public void setSingleFlag(List<LabelValueBean> singleFlag) {
    this.singleFlag = singleFlag;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public List<LabelValueBean> getTraitList() {
    return traitList;
  }

  public List<Employee> getEmployeeList() {
    return employeeList;
  }

  public List<Team> getTeamList() {
    return teamList;
  }

  public int getShareId() {
    return shareId;
  }

  public void setShareId(int shareId) {
    this.shareId = shareId;
  }

  public void setSelect(String select) {
    this.select = select;
  }

  public List<Airways> getAirways() {
    return airways;
  }

}
