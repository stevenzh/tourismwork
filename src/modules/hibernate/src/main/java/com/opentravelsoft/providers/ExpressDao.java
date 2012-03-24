package com.opentravelsoft.providers;

import java.util.Date;
import java.util.List;

import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.Express;
import com.opentravelsoft.entity.ExpressList;
import com.opentravelsoft.entity.product.TourLog;

public interface ExpressDao extends GenericDao<Express, String> {

  /**
   * 查询所有配送信息 获取当前用户未完成的所有任务信息
   * 
   * @param user
   * @return
   */
  List<Booking> findBooking(String user);

  /**
   * 保存配送资料
   * 
   * @param express
   * @param expressInfo
   * @return
   */
  int assignExpress(Express express, List<ExpressList> expressInfo, String note);

  /**
   * 根据订单号获取配送单信息
   * 
   * @param orderId 订单ID
   * @return
   */
  Express getExpress(String expressId);

  /**
   * 根据配送编号获取配送详细列表
   * 
   * @param expressId 配送单ID
   * @return
   */
  List<ExpressList> getExpressInfoList(String expressId);

  /**
   * 删除配送
   * 
   * @param expressId 配送单ID
   * @return
   */
  int delExpress(String expressId, String note);

  /**
   * 
   * @param taskName
   * @return
   */
  List<Express> getExpressTaskList(String taskName);

  /**
   * 取得订单配送列表
   * 
   * @param bookingNo
   * @return
   */
  List<Express> getExpressList(String bookingNo);

  /**
   * 根据查询条件获得配送列表
   * 
   * @param expressId 配送单号
   * @param contactor 联系人
   * @param expressType 配送方式
   * @param expressModlue 配送类型
   * @param payType 收款类别
   * @param payModlue 支付方式
   * @param expressState 配送状态
   * @param teamNo 团号
   * @param line 线路名称
   * @param expressDate 配送时间
   * @return
   */
  List<Express> findExpressDetail(String expressId, String contactor,
      String expressType, String expressModlue, String payType,
      String payModlue, String expressState, String teamNo, String line,
      Date expressStart, Date expressEnd);

  /**
   * 获取配送项目历史操作记录列表
   * 
   * @param expressId 配送单号
   * @return
   */
  public List<TourLog> getExpressLog(String expressId);
}
