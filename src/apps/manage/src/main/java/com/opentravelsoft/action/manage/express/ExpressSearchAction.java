package com.opentravelsoft.action.manage.express;

import java.util.Date;
import java.util.List;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Express;
import com.opentravelsoft.service.operator.ExpressService;

public class ExpressSearchAction extends ManageAction {
  private static final long serialVersionUID = 1L;

  private ExpressService expressService;

  private List<Express> expressList;

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

  // -------------------------------------------------------------------------
  // 检索条件
  /** 配送单号 */
  private String kenExpressId;

  /** 联系人 */
  private String kenContactor;

  /** 配送方式 */
  private String kenExpressType;

  /** 配送类型 */
  private String kenExpressModlue;

  /** 收款类别 */
  private String kenPayType;

  /** 支付方式 */
  private String kenPayModlue;

  /** 配送状态 */
  private String kenExpressState;

  /** 团号 */
  private String kenTeamNo;

  /** 旅行线路 */
  private String kenLine;

  /** 配送时间 下限 */
  private Date kenExpressEnd;

  /** 配送时间 上限 */
  private Date kenExpressStart;

  public String input() throws Exception {
    expressModlueList = getCodeList("ebiz_express_modlue");
    payModlueList = getCodeList("ebiz_pay_modlue");
    payTypeList = getCodeList("ebiz_pay_type");
    expressStateList = getCodeList("ebiz_express_state");
    expressTypeList = getCodeList("ebiz_express_type_list");

    return INPUT;
  }

  public String submit() throws Exception {
    expressList = expressService.rofindExpressDetail(kenExpressId,
        kenContactor, kenExpressType, kenExpressModlue, kenPayType,
        kenPayModlue, kenExpressState, kenTeamNo, kenLine, kenExpressStart,
        kenExpressEnd);
    if (null != expressList)
      currentPage(expressList.size());

    expressModlueList = getCodeList("ebiz_express_modlue");
    payModlueList = getCodeList("ebiz_pay_modlue");
    payTypeList = getCodeList("ebiz_pay_type");
    expressStateList = getCodeList("ebiz_express_state");
    expressTypeList = getCodeList("ebiz_express_type_list");

    return SUCCESS;
  }

  public String delete() throws Exception {
    return SUCCESS;
  }

  @Autowired
  public void setExpressService(ExpressService expressService) {
    this.expressService = expressService;
  }

  public String getKenExpressId() {
    return kenExpressId;
  }

  public void setKenExpressId(String kenExpressId) {
    this.kenExpressId = kenExpressId;
  }

  public String getKenContactor() {
    return kenContactor;
  }

  public void setKenContactor(String kenContactor) {
    this.kenContactor = kenContactor;
  }

  public String getKenExpressType() {
    return kenExpressType;
  }

  public void setKenExpressType(String kenExpressType) {
    this.kenExpressType = kenExpressType;
  }

  public String getKenExpressModlue() {
    return kenExpressModlue;
  }

  public void setKenExpressModlue(String kenExpressModlue) {
    this.kenExpressModlue = kenExpressModlue;
  }

  public String getKenPayType() {
    return kenPayType;
  }

  public void setKenPayType(String kenPayType) {
    this.kenPayType = kenPayType;
  }

  public String getKenPayModlue() {
    return kenPayModlue;
  }

  public void setKenPayModlue(String kenPayModlue) {
    this.kenPayModlue = kenPayModlue;
  }

  public String getKenExpressState() {
    return kenExpressState;
  }

  public void setKenExpressState(String kenExpressState) {
    this.kenExpressState = kenExpressState;
  }

  public String getKenTeamNo() {
    return kenTeamNo;
  }

  public void setKenTeamNo(String kenTeamNo) {
    this.kenTeamNo = kenTeamNo;
  }

  public String getKenLine() {
    return kenLine;
  }

  public void setKenLine(String kenLine) {
    this.kenLine = kenLine;
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

  public List<Express> getExpressList() {
    return expressList;
  }

  public Date getKenExpressEnd() {
    return kenExpressEnd;
  }

  public void setKenExpressEnd(Date kenExpressEnd) {
    this.kenExpressEnd = kenExpressEnd;
  }

  public Date getKenExpressStart() {
    return kenExpressStart;
  }

  public void setKenExpressStart(Date kenExpressStart) {
    this.kenExpressStart = kenExpressStart;
  }
}
