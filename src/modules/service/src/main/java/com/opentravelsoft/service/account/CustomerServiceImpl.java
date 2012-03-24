package com.opentravelsoft.service.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.entity.Contact;
import com.opentravelsoft.entity.Customer;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.product.Remind;
import com.opentravelsoft.providers.EmployeeDao;
import com.opentravelsoft.providers.hibernate.CustomerDao;

@Service("AgentService")
public class CustomerServiceImpl implements CustomerService {

  private EmployeeDao salesmanDao;

  private CustomerDao customerDao;

  @Autowired
  public void setCustomerDao(CustomerDao customerDao) {
    this.customerDao = customerDao;
  }

  @Autowired
  public void setSalesmanDao(EmployeeDao salesmanDao) {
    this.salesmanDao = salesmanDao;
  }

  public Customer findAgent(long agentId) {
    return customerDao.findAccount(agentId);
  }

  public int txCheckedAgent(Customer agent) {
    return customerDao.checkedAccount(agent);
  }

  public int txEditAgent(Customer agent, List<Contact> contacts) {
    return customerDao.editAccount(agent, contacts);
  }

  // -------------------------------------------------------------------------

  public int txDeleteAgent(long agentId) {
    return customerDao.deleteAccount(agentId);
  }

  public List<Employee> roGetSalesmanList() {
    return salesmanDao.getSalesmans(false);
  }

  public List<Customer> getAgent(String countryId, String provinceId,
      String cityId, String agentName, String opKey, String clearingCycle,
      int userId, String customerCode, int teamId, String accountType) {
    return customerDao.getAgent(countryId, provinceId, cityId, agentName,
        opKey, clearingCycle, userId, customerCode, teamId, accountType);
  }

  public Customer roGetSupplier(int supplierId) {
    return customerDao.findAccount(supplierId);
  }

  public int txDeleteSupplier(int supplierId) {
    return customerDao.deleteAccount(supplierId);
  }

  public List<Customer> roFindSupplier(String countryId, String supplierResource) {
    return customerDao.getSupplies(countryId, null, null, null, null,
        supplierResource, null, 0, null);
  }

  public List<Customer> roGetSupplier(long teamId, String resource, boolean b) {
    return customerDao.getSuppliers(teamId, resource, b);
  }

  public int txSaveGroupSupplier(long teamId, String[] select) {
    return customerDao.saveGroupSupplier(teamId, select);
  }

  public int txEditSupplier(Customer supplier, long teamId) {
    return customerDao.editSupplier(supplier, teamId);
  }

  public List<Customer> getByRegion(String province) {
    return customerDao.getCustomerByProvince(province, "");
  }

  public String getContact(String agentId) {
    if (agentId.isEmpty()) {
      return "" + "," + "" + "," + "";
    }
    Customer agent = customerDao.findAccount(Long.parseLong(agentId));
    if (null == agent)
      return null;
    else
      return agent.getName() + "," + agent.getContact() + ","
          + agent.getContactTel() + "," + agent.getSales().getUserName();
  }

  public Remind roGetWaitAgents() {
    return customerDao.getUnAuditAgent();
  }

  public List<Customer> getSuppliserByType(String resource, int teamId) {
    return customerDao.getSupplierByType(resource, teamId);
  }

  public List<Customer> getUsableSupplier(long teamId) {
    return customerDao.getUsableSupplier(teamId);
  }
}
