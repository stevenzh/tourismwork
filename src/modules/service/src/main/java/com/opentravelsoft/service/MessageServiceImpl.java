package com.opentravelsoft.service;

import java.util.List;
import java.util.Set;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.product.Remind;
import com.opentravelsoft.providers.BookingDao;
import com.opentravelsoft.providers.EmployeeDao;
import com.opentravelsoft.providers.IncomeDao;
import com.opentravelsoft.providers.OutcomeDao;

@WebService
@Service("MessageService")
public class MessageServiceImpl implements MessageService {

  @Autowired
  private BookingDao bookingDao;

  @Autowired
  private EmployeeDao employeeDao;

  @Autowired
  private IncomeDao incomeDao;

  @Autowired
  private OutcomeDao outcomeDao;

  public int getMessages(int uid) {
    int count = 0;

    List<Booking> list = bookingDao.getUnreadBookings(uid);
    count = count + list.size();

    Set<String> auth = employeeDao.getAuthorities(uid);

    // 应付 (未读付款申请, 团出发前n天未付款申请)

    // if (auth.contains(EbizCommon.ROLE_FINANCE)
    // && auth.contains(EbizCommon.ROLE_OUTCOME_INBAND) && count == 0) {
    List<Remind> tb1 = outcomeDao.getOutcomeInBand(3);
    count = count + tb1.size();

    // 未阅读付款申请书
    List<Remind> billhead = outcomeDao.getBillheadInBand();
    count = count + billhead.size();
    // }

    // 应收 团出发前n天 未收款客户提醒
    // if (auth.contains(EbizCommon.ROLE_FINANCE)
    // && auth.contains(EbizCommon.ROLE_INCOME_INBAND) && count == 0)
    // {
    List<Remind> tb2 = incomeDao.getIncomeInBand(3);
    count = count + tb2.size();
    // }

    return count;
  }
}
