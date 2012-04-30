package com.opentravelsoft.action.manage.product;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.common.KeyParams;
import com.opentravelsoft.common.SessionKeyParams;
import com.opentravelsoft.entity.Line;
import com.opentravelsoft.entity.LineDescription;
import com.opentravelsoft.entity.TblExpenseTmpl;
import com.opentravelsoft.service.product.ProductTmplService;
import com.opentravelsoft.service.product.LineTraitService;

/**
 * 线路费用包含-不包含
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:54 $
 */
public class LineExpenseAction extends ManageAction {
  private static final long serialVersionUID = 7671898914387730451L;

  @Autowired
  private LineTraitService routeTraitService;

  @Autowired
  private ProductTmplService lineTmplService;

  /** 线路包含 */
  private List<LineDescription> expenseList = new ArrayList<LineDescription>();

  private LineDescription routeTrait = new LineDescription();

  private Line line;

  private int refNo;

  /** 线路费用包含(A:包含，B:不包含) */
  private List<LabelValueBean> expenseTypes;

  /** 同业自费 */
  private String tyOwnExpense;

  /** 直客自费 */
  private String zkOwnExpense;

  public String input() throws Exception {
    Map<String, Object> session = ActionContext.getContext().getSession();
    line = (Line) session.get(SessionKeyParams.EBIZ_CURRENT_ROUTE);
    tyOwnExpense = line.getTyOwnExpense();
    zkOwnExpense = line.getZkOwnExpense();

    routeTrait.setRouteNo(line.getLineNo());
    // 费用项目
    expenseList = routeTraitService.roGetExpense(routeTrait.getRouteNo());
    // 费用项目模板
    List<TblExpenseTmpl> tmplList = lineTmplService.roGetExpenseTmpl();
    expenseTypes = getCodeList("ebiz_expense_status");

    int count = tmplList.size() - expenseList.size();
    if (count > 0)
      for (int i = 0; i < count; i++) {
        expenseList.add(new LineDescription());
      }

    for (int i = 0; i < tmplList.size(); i++) {
      expenseList.get(i).setItem(tmplList.get(i).getItem());
      expenseList.get(i).setCanDel("N");
      if (tmplList.get(i).getInclude().equals("Y"))
        expenseList.get(i).setExpenseType(KeyParams.EBIZ_TYPE_EXPENSE_EXCEPT);
      else
        expenseList.get(i).setExpenseType(KeyParams.EBIZ_TYPE_EXPENSE_INC);
    }
    for (int i = 0; i < expenseList.size(); i++) {
      expenseList.get(i).setRefNo(i + 1);
    }

    return INPUT;
  }

  public String add() throws Exception {

    expenseList = sort(expenseList);
    int reNo = 1;
    for (LineDescription obj : expenseList) {
      obj.setRefNo(reNo++);
    }

    LineDescription routeTrait = new LineDescription();
    routeTrait.setRefNo(reNo++);
    List<TblExpenseTmpl> tmplList = lineTmplService.roGetExpenseTmpl();

    expenseList.add(routeTrait);

    for (int i = 0; i < tmplList.size(); i++) {
      expenseList.get(i).setItem(tmplList.get(i).getItem());
      expenseList.get(i).setCanDel("N");
    }

    expenseTypes = getCodeList("ebiz_expense_status");

    return SUCCESS;
  }

  public String delete() throws Exception {
    List<TblExpenseTmpl> tmplList = lineTmplService.roGetExpenseTmpl();
    expenseTypes = getCodeList("ebiz_expense_status");
    expenseList = sort(expenseList);

    for (LineDescription obj : expenseList) {
      if (obj.getRefNo() == refNo) {
        expenseList.remove(obj);
        break;
      }
    }

    for (int i = 0; i < tmplList.size(); i++) {
      expenseList.get(i).setItem(tmplList.get(i).getItem());
      expenseList.get(i).setCanDel("N");
    }

    return SUCCESS;
  }

  public String save() throws Exception {
    line = (Line) ActionContext.getContext().getSession()
        .get(SessionKeyParams.EBIZ_CURRENT_ROUTE);
    line.setTyOwnExpense(tyOwnExpense);
    line.setZkOwnExpense(zkOwnExpense);

    routeTraitService.txSaveExpense(line, expenseList);

    addActionMessage("费用包含／不包含保存成功.");
    return SUCCESS;
  }

  @Override
  public void validate() {
    try {
      for (int i = 0; i < expenseList.size(); i++) {
        LineDescription trait = expenseList.get(i);
        String value = trait.getTraitDetail();

        if (value.getBytes(ENCODEING).length > 2000) {
          addFieldError("描述", "第" + (i + 1) + "行 描述文字长度超过1000汉字或2000英数字.");
        }
      }
    } catch (UnsupportedEncodingException e) {
    }
  }

  private List<LineDescription> sort(List<LineDescription> expenseList) {
    List<LineDescription> list = new ArrayList<LineDescription>();

    for (int i = 0; i < expenseList.size(); i++) {
      for (int j = 0; j < expenseList.size(); j++) {
        LineDescription obj = expenseList.get(j);
        if (obj.getRefNo() == i + 1) {
          list.add(obj);
          break;
        }
      }
    }

    return list;
  }

  public LineDescription getRouteTrait() {
    return routeTrait;
  }

  public void setRouteTrait(LineDescription routeTrait) {
    this.routeTrait = routeTrait;
  }

  public List<LineDescription> getExpenseList() {
    return expenseList;
  }

  public void setExpenseList(List<LineDescription> expenseList) {
    this.expenseList = expenseList;
  }

  public void setRefNo(int refNo) {
    this.refNo = refNo;
  }

  public void setLine(Line line) {
    this.line = line;
  }

  public List<LabelValueBean> getExpenseTypes() {
    return expenseTypes;
  }

  public String getTyOwnExpense() {
    return tyOwnExpense;
  }

  public void setTyOwnExpense(String tyOwnExpense) {
    this.tyOwnExpense = tyOwnExpense;
  }

  public String getZkOwnExpense() {
    return zkOwnExpense;
  }

  public void setZkOwnExpense(String zkOwnExpense) {
    this.zkOwnExpense = zkOwnExpense;
  }

}
