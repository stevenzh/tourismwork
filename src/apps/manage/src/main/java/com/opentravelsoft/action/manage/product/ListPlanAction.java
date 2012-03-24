package com.opentravelsoft.action.manage.product;

import java.util.List;
import java.util.Map;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.common.SessionKeyParams;
import com.opentravelsoft.entity.Airways;
import com.opentravelsoft.entity.Line;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.service.operator.TourPlanService;

/**
 * 列出线路的出团计划
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:54 $
 */
public class ListPlanAction extends ManageAction {
  private static final long serialVersionUID = 8435596328786314873L;

  private TourPlanService tourPlanService;

  private Line line;

  private List<Plan> plans;

  private List<LabelValueBean> deploys;

  private List<LabelValueBean> favourables;

  private String recordNo;

  private Plan plan = new Plan();;

  /** 航空公司列表 */
  private List<Airways> airways;

  private List<LabelValueBean> paxkey;

  @Autowired
  public void setTourPlanService(TourPlanService tourPlanService) {
    this.tourPlanService = tourPlanService;
  }

  @Override
  public String input() {
    Map<String, Object> session = ActionContext.getContext().getSession();
    line = (Line) session.get(SessionKeyParams.EBIZ_CURRENT_ROUTE);
    plans = tourPlanService.roGetLinePlans(line.getLineNo(), false, false,
        false, false);

    currentPage(plans.size());

    // if (judgeRoutePlanService.roJudgeRouteTrait(route.getLineNo()))
    // {
    // addActionError("线路特色还没有添加，必须添加后再做出团计划！");
    // }
    // if (judgeRoutePlanService.roJudgeRouteNotice(lineNo))
    // {
    // addActionError("线路注意事项还没有添加，必须添加后再做出团计划！");
    // }
    // if (judgeRoutePlanService.roJudgeRouteExpense(lineNo))
    // {
    // addActionError("线路费用包含、费用不包含还没有添加，必须添加后再做出团计划！");
    // }
    // if (judgeRoutePlanService.roJudgeRouteTips(lineNo))
    // {
    // addActionError("小贴士还没有添加，必须添加后再做出团计划！");
    // }

    // if (route.getClassKeyMarket().equals("1"))
    // if (judgeRoutePlanService.roJudgeRouteVisa(lineNo))
    // {
    // addActionError("出境线路必须添加签证服务，添加后再做出团计划！");
    // }
    // if (judgeRoutePlanService.roJudgeRouteDistrict(lineNo))
    // {
    // addActionError("线路旅游目的地还没有添加，必须添加后再做出团计划！");
    // }
    // if (judgeRoutePlanService.roJudgeRoutePrice(lineNo))
    // {
    // addActionError("线路报价还没有添加，必须添加后再做出团计划！");
    // }
    // if (judgeRoutePlanService.roJudgeRouteSchedule(lineNo))
    // {
    // addActionError("线路行程还没有添加，必须添加后再做出团计划！");
    // }
    // if (judgeRoutePlanService.roJudgeRouteClassPrice(lineNo, "成人价"))
    // {
    // addActionError("线路报价中的成人价还没有添加，必须添加后再做出团计划！");
    // }
    return INPUT;
  }

  public String getRecordNo() {
    return recordNo;
  }

  public void setRecordNo(String recordNo) {
    this.recordNo = recordNo;
  }

  public void setPlans(List<Plan> plans) {
    this.plans = plans;
  }

  public List<LabelValueBean> getFavourables() {
    return favourables;
  }

  public void setFavourables(List<LabelValueBean> favourables) {
    this.favourables = favourables;
  }

  public List<Plan> getPlans() {
    return plans;
  }

  public void setLe(Line line) {
    this.line = line;
  }

  public List<LabelValueBean> getDeploys() {
    return deploys;
  }

  public void setDeploys(List<LabelValueBean> deploys) {
    this.deploys = deploys;
  }

  public List<Airways> getAirways() {
    return airways;
  }

  public List<LabelValueBean> getPaxkey() {
    return paxkey;
  }

  public Plan getPlan() {
    return plan;
  }

  public void setPlan(Plan plan) {
    this.plan = plan;
  }

}
