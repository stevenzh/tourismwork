package com.opentravelsoft.service.operator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;
import com.opentravelsoft.common.EbizCommon;
import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.Express;
import com.opentravelsoft.entity.ExpressList;
import com.opentravelsoft.entity.Lists;
import com.opentravelsoft.entity.product.Remind;
import com.opentravelsoft.entity.product.TourLog;
import com.opentravelsoft.providers.ExpressDao;
import com.opentravelsoft.workflow.TaskDao;

@Service("ExpressService")
public class ExpressServiceImpl implements ExpressService {

  @Autowired
  private ExpressDao expressDao;

  @Autowired
  private TaskDao taskService;

  public List<Booking> roFind(String user) {
    return expressDao.findBooking(user);
  }

  public Express roExpress(String expressId) {
    return expressDao.getExpress(expressId);
  }

  public List<ExpressList> roExpressInfoList(String expressId) {
    return expressDao.getExpressInfoList(expressId);
  }

  public int txAssignExpress(Express express, List<ExpressList> expressInfo,
      String note) {
    // TODO WorkFLow
    return expressDao.assignExpress(express, expressInfo, note);
  }

  public int txDeleExpressInfo(String expressId, String note) {
    return expressDao.delExpress(expressId, note);
  }

  public List<Express> rofindExpressDetail(String expressId, String contactor,
      String expressType, String expressModlue, String payType,
      String payModlue, String expressState, String teamNo, String line,
      Date expressStart, Date expressEnd) {
    return expressDao.findExpressDetail(expressId, contactor, expressType,
        expressModlue, payType, payModlue, expressState, teamNo, line,
        expressStart, expressEnd);
  }

  public List<Remind> roGetExpressTask(String taskName) {
    List<Remind> reminds = new ArrayList<Remind>();
    List<Express> list = expressDao.getExpressTaskList(taskName);
    for (int i = 0; i < list.size(); i++) {
      Express express = list.get(i);
      Remind remind = new Remind();
      remind.setExpressId(express.getExpressId());
      remind.setContext(express.getContactor() + express.getMemo());
      if (remind.getContext().length() > 20)
        remind.setContext(remind.getContext().substring(0, 20) + "...");
      reminds.add(remind);
    }
    return reminds;
  }

  public List<TourLog> roGetExpressLog(String expressId) {
    return expressDao.getExpressLog(expressId);
  }

  public Lists rogetName(String expressState) {
    return null;
  }

  public int isExpressAgain(String expressId, boolean isflag, String userId) {
    String enabled = (String) ActionContext.getContext().getApplication()
        .get(EbizCommon.WORKFLOW_ENABLED);
    if (enabled.equals("1"))
      return taskService.isExpressAgain(expressId, isflag, userId);
    else
      return 0;
  }

  public int txExamineExpress(String expressId, boolean ispass, String userId) {
    String enabled = (String) ActionContext.getContext().getApplication()
        .get(EbizCommon.WORKFLOW_ENABLED);
    if (enabled.equals("1"))
      return taskService.examineExpress(expressId, ispass, userId);
    else
      return 0;
  }

  public int txAuditingIsPass(String expressId, boolean ispass, String userId) {
    String enabled = (String) ActionContext.getContext().getApplication()
        .get(EbizCommon.WORKFLOW_ENABLED);
    if (enabled.equals("1"))
      return taskService.auditingIsPass(expressId, ispass, userId);
    else
      return 1;
  }

  public int txAccountExpress(String expressId, String userId) {
    String enabled = (String) ActionContext.getContext().getApplication()
        .get(EbizCommon.WORKFLOW_ENABLED);
    if (enabled.equals("1"))
      return taskService.accountExpress(expressId, userId);
    else
      return 0;
  }

}
