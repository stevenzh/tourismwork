package com.opentravelsoft.service.operator;

import java.util.Date;
import java.util.List;

import com.opentravelsoft.entity.Express;
import com.opentravelsoft.entity.ExpressList;
import com.opentravelsoft.entity.Lists;
import com.opentravelsoft.entity.product.Remind;
import com.opentravelsoft.entity.product.TourLog;

public interface ExpressService {

  /**
   * 取得配送信息
   * 
   * @param expressId 配送单号
   * @return
   */
  Express roExpress(String expressId);

  /**
   * 取得配送详细信息
   * 
   * @param expressId
   * @return
   */
  List<ExpressList> roExpressInfoList(String expressId);

  /**
   * 取消配送单
   * 
   * @param expressId
   * @return
   */
  int txDeleExpressInfo(String expressId, String note);

  /**
   * 安排配送
   * 
   * @param express
   * @param expressInfo
   * @return
   */
  int txAssignExpress(Express express, List<ExpressList> expressInfo,
      String note);

  /**
   * 签单部审核结果
   * 
   * @param expressId2
   * @param ispass
   * @param expressId
   */
  int txAuditingIsPass(String expressId, boolean ispass, String userId);

  /**
   * 缴纳团款
   * 
   * @param expressId
   * @param userId
   */
  int txAccountExpress(String expressId, String userId);

  /**
   * 材料审核
   * 
   * @param expressId
   * @param userId
   */
  int txExamineExpress(String expressId, boolean ispass, String userId);

  /**
   * 是否再次配送
   * 
   * @param isflag
   * @param expressId
   * @param userId
   */
  int isExpressAgain(String expressId, boolean isflag, String userId);

  /**
   * 查找配送单
   * 
   * @param expressId
   * @param contactor
   * @param expressType
   * @param expressModlue
   * @param payType
   * @param payModlue
   * @param expressState
   * @param teamNo
   * @param line
   * @param expressStart
   * @param expressEnd
   * @return
   */
  List<Express> rofindExpressDetail(String expressId, String contactor,
      String expressType, String expressModlue, String payType,
      String payModlue, String expressState, String teamNo, String line,
      Date expressStart, Date expressEnd);

  /**
   * 
   * @param taskName
   * @return
   */
  public List<Remind> roGetExpressTask(String taskName);

  /**
   * 获取配送项目的改动历史记录
   * 
   * @param expressId 配送单号
   * @return
   */
  public List<TourLog> roGetExpressLog(String expressId);

  Lists rogetName(String expressState);
}
