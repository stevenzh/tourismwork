package com.opentravelsoft.action.manage.operator;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Tourist;
import com.opentravelsoft.service.operator.TouristService;

/**
 * 成团处理
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.2 $ $Date: 2009/04/10 07:47:29 $
 */
public class TouristInfoAction extends ManageAction {

  private static final long serialVersionUID = 7926144965633325472L;

  protected SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMdd");

  @Autowired
  private TouristService touristService;

  private Tourist tcustomer = new Tourist();

  /** 名单号 */
  private String nmno;

  public String input() {
    // 显示一个游客信息
    tcustomer = touristService.roFindCustomerByNmno(nmno);
    if (null != tcustomer) {
      if (tcustomer.getSex().equals("M")) {
        tcustomer.setSex("男");
      } else if (tcustomer.getSex().equals("F")) {
        tcustomer.setSex("女");
      }
    }
    return INPUT;
  }

}
