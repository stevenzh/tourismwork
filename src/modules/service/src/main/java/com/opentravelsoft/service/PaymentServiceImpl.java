package com.opentravelsoft.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.entity.TblUserPayOL;
import com.opentravelsoft.entity.finance.Income;
import com.opentravelsoft.providers.IncomeDao;

@Service("PaymentService")
public class PaymentServiceImpl implements PaymentService {

  @Autowired
  private IncomeDao incomeDao;

  public List<Income> roGetGatheringList(int customerId, Date paymentDateStart,
      Date paymentDateEnd, double payGatherStart, double payGatherEnd) {
    return incomeDao.getGatheringList(customerId, paymentDateStart,
        paymentDateEnd, payGatherStart, payGatherEnd);
  }

  public Income roGetGathering(int incomeId) {
    return incomeDao.getGathering(incomeId);
  }

  public void insertPayer(TblUserPayOL payer) {
    incomeDao.insertPayer(payer);
  }
}
