package com.opentravelsoft.action.manage.express;

import java.util.ArrayList;
import java.util.List;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Express;
import com.opentravelsoft.entity.ExpressList;
import com.opentravelsoft.service.operator.ExpressService;

/**
 * 显示配送信息<br>
 * 安排配送
 */
public class AssignExpressAction extends ManageAction {
  private static final long serialVersionUID = 7113453645461822081L;

  private ExpressService expressService;

  /** 配送单号 */
  private String expressId;

  /** 配送信息 */
  private Express express;

  /** 配送方式列表 */
  private List<LabelValueBean> expressTypeList;

  /** 配送类型列表 */
  private List<LabelValueBean> expressModlueList;

  /** 支付方式 */
  private List<LabelValueBean> payModlueList;

  /** 收款类别 */
  private List<LabelValueBean> payTypeList;

  /** 配送状态 */
  private List<LabelValueBean> expressStateList;

  private List<LabelValueBean> eTypeList;

  /** 审核结果 */
  private Boolean isPass;

  /** 配送明细 */
  private List<ExpressList> expressList = new ArrayList<ExpressList>();

  /** 备注 */
  private String note;

  public void setNote(String note) {
    this.note = note;
  }

  public String input() throws Exception {
    eTypeList = getCodeList("ebiz_express_type_list");
    expressModlueList = getCodeList("ebiz_express_modlue");
    payModlueList = getCodeList("ebiz_pay_modlue");
    payTypeList = getCodeList("ebiz_pay_type");
    expressStateList = getCodeList("ebiz_express_state");
    expressTypeList = getCodeList("ebiz_express_type");

    // 配送
    express = expressService.roExpress(expressId);
    // 配送明细
    expressList = expressService.roExpressInfoList(expressId);
    return INPUT;
  }

  public String submit() throws Exception {
    Employee user = getUser();
    express.setUpdatedBy(user.getUserId());
    int result = expressService.txAssignExpress(express, expressList, note);
    if (result < 0) {
      addActionError("添加失败");
      return INPUT;
    }
    return SUCCESS;
  }

  public String delete() throws Exception {
    int result = expressService.txDeleExpressInfo(expressId, note);

    return SUCCESS;
  }

  public String auditiong() {
    Employee user = getUser();
    // 提交审核结果
    expressService.txAuditingIsPass(expressId, isPass, user.getUid());
    return SUCCESS;
  }

  @Autowired
  public void setExpressService(ExpressService expressService) {
    this.expressService = expressService;
  }

  public List<LabelValueBean> getExpressTypeList() {
    return expressTypeList;
  }

  public List<LabelValueBean> getExpressModlueList() {
    return expressModlueList;
  }

  public List<LabelValueBean> getPayModlueList() {
    return payModlueList;
  }

  public List<LabelValueBean> getPayTypeList() {
    return payTypeList;
  }

  public List<LabelValueBean> getExpressStateList() {
    return expressStateList;
  }

  public List<LabelValueBean> getETypeList() {
    return eTypeList;
  }

  public void setExpress(Express express) {
    this.express = express;
  }

  public Express getExpress() {
    return express;
  }

  public List<ExpressList> getExpressList() {
    return expressList;
  }

  public void setExpressList(List<ExpressList> expressList) {
    this.expressList = expressList;
  }

  public String getExpressId() {
    return expressId;
  }

  public void setExpressId(String expressId) {
    this.expressId = expressId;
  }

}
