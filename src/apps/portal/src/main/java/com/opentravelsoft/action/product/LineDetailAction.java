package com.opentravelsoft.action.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.util.LabelValueBean;

import com.opentravelsoft.common.KeyParams;
import com.opentravelsoft.entity.Line;
import com.opentravelsoft.entity.LineDescription;
import com.opentravelsoft.entity.LinePrice;
import com.opentravelsoft.entity.LineSchedule;
import com.opentravelsoft.entity.LineVisa;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.service.portal.PlanListService;
import com.opentravelsoft.service.product.LineService;
import com.opentravelsoft.webapp.action.PortalAction;

/**
 * 线路详细信息
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 */
public class LineDetailAction extends PortalAction {
  private static final long serialVersionUID = 1386841816134989283L;

  @Autowired
  private LineService lineService;

  @Autowired
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

  /** 所需签证 */
  private List<LineVisa> visaList;

  @Override
  public String execute() throws Exception {
    line = lineService.getLine(lineNo);
    schedule = lineService.getLineSchedule(lineNo);
    expenseCovered = lineService.getNote(lineNo,
        KeyParams.EBIZ_TYPE_EXPENSE_INC);
    expenseExcept = lineService.getNote(lineNo,
        KeyParams.EBIZ_TYPE_EXPENSE_EXCEPT);
    alerts = lineService.getNote(lineNo, KeyParams.EBIZ_TYPE_LINE_ALERT);
    rules = lineService.getNote(lineNo, KeyParams.EBIZ_TYPE_LINE_RULE);
    features = lineService
        .getFeatures(lineNo, KeyParams.EBIZ_TYPE_LINE_FEATURE);
    tipsList = lineService.getFeatures(lineNo, KeyParams.EBIZ_TYPE_LINE_TIPS);
    visaList = lineService.getVisaList(lineNo);

    // 出团计划
    planList = planService.getPlans(lineNo, true, true, true, true);
    // 价格表
    quotations = lineService.getPriceNotice(lineNo);

    return SUCCESS;
  }

  public List<Plan> getPlanList() {
    return planList;
  }

  public Line getLine() {
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

  public String getLineNo() {
    return lineNo;
  }

  public void setLineNo(String lineNo) {
    this.lineNo = lineNo;
  }

  public List<LineDescription> getFeatures() {
    return features;
  }

  public List<LineDescription> getRouteTipsList() {
    return tipsList;
  }

  public List<LineVisa> getVisaList() {
    return visaList;
  }

}
