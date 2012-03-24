package com.opentravelsoft.action.manage.order;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.service.operator.OperatorAlertService;

/**
 * 计调预警（未审核订单）
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.2 $ $Date: 2009/04/10 07:47:29 $
 */
public class OrderAlertAction extends ManageAction {
  private static final long serialVersionUID = 1525354479672739838L;

  protected static final Log logger = LogFactory.getLog(OrderAlertAction.class);

  private OperatorAlertService operatorAlertService;

  private List<Booking> bookList;

  @Autowired
  public void setOperatorAlertService(OperatorAlertService operatorAlertService) {
    this.operatorAlertService = operatorAlertService;
  }

  @Override
  public String input() throws Exception {
    logger.debug("取得计调预警信息列表,开始...");

    Employee user = getUser();
    bookList = operatorAlertService.roGetNewBookings(user.getUserId());
    currentPage(bookList.size());
    return INPUT;
  }

  public List<Booking> getBookList() {
    return bookList;
  }

}
