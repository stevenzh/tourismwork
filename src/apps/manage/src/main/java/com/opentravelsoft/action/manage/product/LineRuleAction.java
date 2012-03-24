package com.opentravelsoft.action.manage.product;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.common.SessionKeyParams;
import com.opentravelsoft.entity.Line;
import com.opentravelsoft.entity.LineDescription;
import com.opentravelsoft.entity.ProductItem;
import com.opentravelsoft.entity.ProductTmpl;
import com.opentravelsoft.service.product.ProductTmplService;
import com.opentravelsoft.service.product.LineTraitService;
import com.opentravelsoft.util.StringUtil;

/**
 * 线路重要条款
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 */
public class LineRuleAction extends ManageAction {
  private static final long serialVersionUID = 7671898914387730451L;

  private ProductTmplService lineTmplService;

  private LineTraitService lineTraitService;

  private List<LineDescription> ruleList = new ArrayList<LineDescription>();

  private List<ProductTmpl> tmpl = new ArrayList<ProductTmpl>();

  private Line line;

  private int idx;

  private String[] selectedRules;

  // -------------------------------------------------------------------------

  private String itemType;

  private ProductItem item;

  @Autowired
  public void setRouteTraitService(LineTraitService routeTraitService) {
    this.lineTraitService = routeTraitService;
  }

  @Override
  public String input() throws Exception {
    Map<String, Object> session = ActionContext.getContext().getSession();
    line = (Line) session.get(SessionKeyParams.EBIZ_CURRENT_ROUTE);
    ruleList = lineTraitService.getLineTrait(line.getLineNo(), itemType);

    tmpl = lineTmplService.getProductTmplList(line.getTeam().getTeamId(),
        itemType);
    item = lineTraitService.getProductItem(itemType);

    return INPUT;
  }

  public String add() throws Exception {
    line = (Line) ActionContext.getContext().getSession()
        .get(SessionKeyParams.EBIZ_CURRENT_ROUTE);
    item = lineTraitService.getProductItem(itemType);

    ruleList = sort(ruleList);
    int refNo = 1;
    for (LineDescription obj : ruleList) {
      obj.setRefNo(refNo++);
    }

    LineDescription routeTrait = new LineDescription();
    routeTrait.setRefNo(refNo++);
    routeTrait.setTraitDetail("");
    ruleList.add(routeTrait);

    tmpl = lineTmplService.getProductTmplList(line.getTeam().getTeamId(),
        itemType);

    return SUCCESS;
  }

  public String delete() throws Exception {
    for (LineDescription obj : ruleList) {
      if (obj.getRefNo() == idx) {
        ruleList.remove(obj);
        break;
      }
    }
    List<LineDescription> list = new ArrayList<LineDescription>();
    int size = ruleList.size() + 2;
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < ruleList.size(); j++) {
        if (ruleList.get(j).getRefNo() == i) {
          list.add(ruleList.get(j));
          break;
        }
      }
    }

    ruleList = list;

    line = (Line) ActionContext.getContext().getSession()
        .get(SessionKeyParams.EBIZ_CURRENT_ROUTE);
    item = lineTraitService.getProductItem(itemType);
    tmpl = lineTmplService.getProductTmplList(line.getTeam().getTeamId(),
        itemType);

    return SUCCESS;
  }

  public String save() {
    line = (Line) ActionContext.getContext().getSession()
        .get(SessionKeyParams.EBIZ_CURRENT_ROUTE);
    ruleList = sort(ruleList);

    tmpl = lineTmplService.getProductTmplList(line.getTeam().getTeamId(),
        itemType);

    if (null != selectedRules) {
      for (int i = 0; i < selectedRules.length; i++) {
        for (ProductTmpl tip : tmpl) {
          if (selectedRules[i].equals(String.valueOf(tip.getId()))) {
            LineDescription trait = new LineDescription();
            trait.setTraitDetail(tip.getContent());
            trait.setItem(tip.getSubject());
            ruleList.add(trait);
          }
        }
      }
    }

    for (int i = ruleList.size() - 1; i >= 0; i--) {
      LineDescription obj = ruleList.get(i);
      if (!StringUtil.hasLength(obj.getTraitDetail())) {
        ruleList.remove(obj);
        continue;
      }
    }

    int idx = 1;
    for (LineDescription obj : ruleList) {
      obj.setRefNo(idx++);
      obj.setRouteNo(line.getLineNo());
      obj.setType(itemType);
    }

    lineTraitService.txSaveLineTrait(ruleList, line.getLineNo(), itemType);

    addActionMessage("线路重要条款保存成功.");
    return SUCCESS;
  }

  @Override
  public void validate() {
    try {
      for (int i = 0; i < ruleList.size(); i++) {
        LineDescription trait = ruleList.get(i);
        String value = trait.getTraitDetail();

        if (value.getBytes(ENCODEING).length > 2000) {
          addFieldError("描述", "第" + (i + 1) + "行 描述文字长度超过1000汉字或2000英数字.");
        }
      }
    } catch (UnsupportedEncodingException e) {
    }
  }

  private List<LineDescription> sort(List<LineDescription> alertList) {
    List<LineDescription> list = new ArrayList<LineDescription>();

    for (int i = 0; i < alertList.size(); i++) {
      for (int j = 0; j < alertList.size(); j++) {
        LineDescription obj = alertList.get(j);
        if (obj.getRefNo() == i + 1) {
          list.add(obj);
          break;
        }
      }
    }

    return list;
  }

  @Autowired
  public void setLineTmplService(ProductTmplService personalTmplService) {
    this.lineTmplService = personalTmplService;
  }

  public void setRuleList(List<LineDescription> ruleList) {
    this.ruleList = ruleList;
  }

  public List<LineDescription> getRuleList() {
    return ruleList;
  }

  public int getIdx() {
    return idx;
  }

  public void setIdx(int idx) {
    this.idx = idx;
  }

  public Line getLine() {
    return line;
  }

  public void setChecked(String[] rules) {
    this.selectedRules = rules.clone();
  }

  public List<ProductTmpl> getTmpl() {
    return tmpl;
  }

  public String getItemType() {
    return itemType;
  }

  public void setItemType(String itemType) {
    this.itemType = itemType;
  }

  public ProductItem getItem() {
    return item;
  }

}
