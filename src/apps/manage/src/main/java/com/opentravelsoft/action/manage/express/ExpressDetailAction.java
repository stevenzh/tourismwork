package com.opentravelsoft.action.manage.express;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Express;
import com.opentravelsoft.entity.ExpressList;
import com.opentravelsoft.entity.Lists;
import com.opentravelsoft.entity.product.TourLog;
import com.opentravelsoft.service.operator.ExpressService;
import com.opentravelsoft.util.StringUtil;
import com.opentravelsoft.workflow.TaskDao;

/**
 * 显示配送详细
 * 
 * @author zhangst
 * 
 */
public class ExpressDetailAction extends ManageAction {
  private static final long serialVersionUID = 1L;

  private ExpressService expressService;

  private TaskDao taskService;

  /** 配送单号 */
  private String expressId;

  /** 配送单 */
  private Express express = null;

  private Lists dictionary = null;

  /** 配送单详细 */
  private List<ExpressList> expressList;

  private Boolean isPass;

  /** 配送状态 */
  private String eState;

  /** 收款类别 */
  private String pType;

  /** 支付方式 */
  private String pModlue;

  /** 配送方式 */
  private String eType;

  /** 配送类型 */
  private String eModlue;

  /** 订单的任务列表 */
  private List<Task> taskList = new ArrayList<Task>();

  /** 配送操作历史记录列表 */
  private List<TourLog> expressLogList = new ArrayList<TourLog>();

  @Autowired
  public void setExpressService(ExpressService expressService) {
    this.expressService = expressService;
  }

  @Autowired
  public void setTaskService(TaskDao taskService) {
    this.taskService = taskService;
  }

  public String input() {
    // 取得配送单
    express = expressService.roExpress(expressId);
    // 取得配送明细
    expressList = expressService.roExpressInfoList(expressId);

    if (StringUtil.hasLength(express.getExpressState())) {
      dictionary = expressService.rogetName(express.getExpressState());
      eState = dictionary.getT11();
    }

    if (StringUtil.hasLength(express.getPayType())) {
      dictionary = expressService.rogetName(express.getPayType());
      pType = dictionary.getMoneyType();
    }

    if (StringUtil.hasLength(express.getPayModlue())) {
      dictionary = expressService.rogetName(express.getPayModlue());
      pModlue = dictionary.getPayType();
    }

    if (StringUtil.hasLength(express.getExpressType())) {
      dictionary = expressService.rogetName(express.getExpressType());
      eType = dictionary.getExpressType();
    }
    if (StringUtil.hasLength(express.getExpressModlue())) {
      dictionary = expressService.rogetName(express.getExpressModlue());
      eModlue = dictionary.getExpressModlue();
    }

    taskList = taskService.getExpressTask(expressId);

    // 配送操作历史记录列表
    expressLogList = expressService.roGetExpressLog(expressId);

    return INPUT;
  }

  public String auditing() {
    Employee user = getUser();
    // 提交审核结果
    expressService.txAuditingIsPass(expressId, isPass, user.getUid());
    return SUCCESS;
  }

  public String account() {
    Employee user = getUser();
    // 缴纳团款
    expressService.txAccountExpress(expressId, user.getUid());
    return SUCCESS;
  }

  public String examine() {
    Employee user = getUser();
    // 缴纳团款
    expressService.txExamineExpress(expressId, isPass, user.getUid());
    return SUCCESS;
  }

  public String getExpressId() {
    return expressId;
  }

  public void setExpressId(String expressId) {
    this.expressId = expressId;
  }

  public void setIsPass(Boolean isPass) {
    this.isPass = isPass;
  }

  public Boolean getIsPass() {
    return isPass;
  }

  public Express getExpress() {
    return express;
  }

  public void setExpress(Express express) {
    this.express = express;
  }

  public List<ExpressList> getExpressList() {
    return expressList;
  }

  public void setExpressList(List<ExpressList> expressList) {
    this.expressList = expressList;
  }

  public String getEState() {
    return eState;
  }

  public String getPType() {
    return pType;
  }

  public String getPModlue() {
    return pModlue;
  }

  public String getEType() {
    return eType;
  }

  public String getEModlue() {
    return eModlue;
  }

  public Lists getDictionary() {
    return dictionary;
  }

  public List<Task> getTaskList() {
    return taskList;
  }

  public List<TourLog> getExpressLogList() {
    return expressLogList;
  }
}
