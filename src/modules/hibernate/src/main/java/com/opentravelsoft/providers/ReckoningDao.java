package com.opentravelsoft.providers;

import java.util.List;

import com.opentravelsoft.entity.finance.Reckoning;
import com.opentravelsoft.entity.finance.ReckoningAcct;

/**
 * 帐单
 * 
 * @author zhangst
 * 
 */
public interface ReckoningDao extends GenericDao<Reckoning, Long> {
  /**
   * 得到订单所对应的所有帐单
   * 
   * @param reserveNo
   * @return
   */
  public List<Reckoning> getReckoning(String reserveNo);

  /**
   * 保存帐单
   * 
   * @param reckoning
   * @return
   */
  public Reckoning wholeReckoningMake(Reckoning reckoning);

  /**
   * 得到一个帐单的信息和所有帐单明细
   * 
   * @param reckoningId
   * @return
   */
  public Reckoning getReckoningInfo(long reckoningId);

  /**
   * 修改帐单
   * 
   * @param reckoning
   * @return
   */
  public int wholeReckoningModify(Reckoning reckoning);

  /**
   * 取订单所对应的名单
   * 
   * @param reserveNo
   * @return
   */
  public List<ReckoningAcct> getCustomerList(String reserveNo);

  /**
   * 记录打印信息
   * 
   * @param reckoningId
   * @return
   */
  public int setPrint(int reckoningId);

  public List<ReckoningAcct> getTourReckoningAcctList(String tourNo);

}
