package com.opentravelsoft.service.finance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.entity.product.Remind;
import com.opentravelsoft.providers.IncomeDao;
import com.opentravelsoft.providers.OutcomeDao;

@Service("FinanceAlertService")
public class FinanceAlertServiceImpl implements FinanceAlertService {
  
  @Autowired
  private OutcomeDao outcomeDao;

  @Autowired
  private IncomeDao incomeDao;

  public List<Remind> roGetOutcomeInBand(int daynum) {
    return outcomeDao.getOutcomeInBand(daynum);
  }

  public List<Remind> roGetIncomeInBand(int daynum) {
    return incomeDao.getIncomeInBand(daynum);
  }

  public List<Remind> roGetBillheadInBand() {
    return outcomeDao.getBillheadInBand();
  }

}
