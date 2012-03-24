package com.opentravelsoft.service.operator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.entity.Customer;
import com.opentravelsoft.entity.finance.Outcome;
import com.opentravelsoft.providers.OutcomeDao;
import com.opentravelsoft.providers.hibernate.CustomerDao;

@Service("TicketService")
public class TicketServiceImpl implements TicketService {
  private OutcomeDao outcomeDao;

  private CustomerDao agentDao;

  @Autowired
  public void setOutcomeDao(OutcomeDao outcomeDao) {
    this.outcomeDao = outcomeDao;
  }

  @Autowired
  public void setAgentDao(CustomerDao agentDao) {
    this.agentDao = agentDao;
  }

  public List<Customer> getAirSuppliers() {
    /**
     * <option value="1">地接</option> <option value="2">航空</option> <option
     * value="3">酒店</option> <option value="4">景点</option> <option
     * value="5">车队</option> <option value="6">签证</option> <option
     * value="7">油轮</option> <option value="8">导游</option>
     * 
     * <option value="9">火车票</option> <option value="A">保险</option>
     */
    return agentDao.getSupplierByType("2", 0);
  }

  public List<Outcome> listParcels(String supplierId, int userId,
      short carryStatus) {
    return outcomeDao.listParcels(supplierId, userId, carryStatus);
  }

  public int txCompleteParcel(Outcome billhead) {
    // TODO WorkFLow
    return outcomeDao.completeParcel(billhead);
  }

  public int txStartParcel(Outcome billhead) {
    return outcomeDao.startParcel(billhead);
  }

}
