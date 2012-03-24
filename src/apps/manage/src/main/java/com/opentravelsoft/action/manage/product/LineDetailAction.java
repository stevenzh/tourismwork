package com.opentravelsoft.action.manage.product;

import java.util.List;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.common.KeyParams;
import com.opentravelsoft.entity.Line;
import com.opentravelsoft.entity.LineDescription;
import com.opentravelsoft.entity.LinePrice;
import com.opentravelsoft.entity.LineSchedule;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.service.portal.PlanListService;
import com.opentravelsoft.service.product.LineService;

/**
 * 线路详细信息<br>
 * 前台>分销
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:00 $
 */
public class LineDetailAction extends ManageAction {
  private static final long serialVersionUID = 1386841816134989283L;

  private LineService lineService;

  private PlanListService planService;

  private String lineNo;

  private Line line;

  private List<LineSchedule> schedule;

  private List<Plan> planList;

  /** 费用包含 */
  private List<LabelValueBean> expenseCovered;

  /** 费用不包含 */
  private List<LabelValueBean> expenseExcept;

  /** 出行警示 */
  private List<LabelValueBean> alerts;

  /** 重要条款 */
  private List<LabelValueBean> rules;

  /** 小贴士 */
  private List<LineDescription> tipsList;

  private List<LinePrice> quotations;

  /** 线路特色 */
  private List<LineDescription> features;

  @Autowired
  public void setRouteDetailService(LineService routeDetailService) {
    this.lineService = routeDetailService;
  }

  @Autowired
  public void setPlanListService(PlanListService planService) {
    this.planService = planService;
  }

  @Override
  public String execute() throws Exception {
    schedule = lineService.getLineSchedule(lineNo);
    line = lineService.getLine(lineNo);
    expenseCovered = lineService.getNote(lineNo,
        KeyParams.EBIZ_TYPE_EXPENSE_INC);
    expenseExcept = lineService.getNote(lineNo,
        KeyParams.EBIZ_TYPE_EXPENSE_EXCEPT);
    alerts = lineService.getNote(lineNo, KeyParams.EBIZ_TYPE_LINE_ALERT);
    rules = lineService.getNote(lineNo, KeyParams.EBIZ_TYPE_LINE_RULE);

    quotations = lineService.getPriceNotice(lineNo);
    features = lineService
        .getFeatures(lineNo, KeyParams.EBIZ_TYPE_LINE_FEATURE);
    tipsList = lineService.getFeatures(lineNo, KeyParams.EBIZ_TYPE_LINE_TIPS);

    planList = planService.getPlans(lineNo, true, true, true, true);
    return SUCCESS;
  }

  public List<Plan> getPlanList() {
    return planList;
  }

  public Line getRoute() {
    return line;
  }

  public List<LineSchedule> getSchedule() {
    return schedule;
  }

  public List<LinePrice> getQuotations() {
    return quotations;
  }

  public List<LabelValueBean> getExpenseCovered() {
    return expenseCovered;
  }

  public List<LabelValueBean> getExpenseExcept() {
    return expenseExcept;
  }

  public List<LabelValueBean> getAlerts() {
    return alerts;
  }

  public List<LabelValueBean> getRules() {
    return rules;
  }

  public String getRouteNo() {
    return lineNo;
  }

  public void setRouteNo(String lineNo) {
    this.lineNo = lineNo;
  }

  public List<LineDescription> getFeatures() {
    return features;
  }

  public List<LineDescription> getRouteTipsList() {
    return tipsList;
  }

}
