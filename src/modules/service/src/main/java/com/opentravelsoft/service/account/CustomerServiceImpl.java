package com.opentravelsoft.service.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.entity.Contact;
import com.opentravelsoft.entity.Customer;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.product.Remind;
import com.opentravelsoft.providers.CustomerDao;
import com.opentravelsoft.providers.EmployeeDao;

@Service("AgentService")
public class CustomerServiceImpl implements CustomerService {

  @Autowired
  private EmployeeDao salesmanDao;

  @Autowired
  private CustomerDao customerDao;

  public Customer findAgent(int agentId) {
    return customerDao.findAccount(agentId);
  }

  public int txCheckedAgent(Customer agent) {
    return customerDao.checkedAccount(agent);
  }

  public int txEditAgent(Customer agent, List<Contact> contacts) {
    return customerDao.editAccount(agent, contacts);
  }

  // -------------------------------------------------------------------------

  public int txDeleteAgent(int agentId) {
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

  public List<Customer> roGetSupplier(Integer teamId, String resource, boolean b) {
    return customerDao.getSuppliers(teamId, resource, b);
  }

  public int txSaveGroupSupplier(Integer teamId, String[] select) {
    return customerDao.saveGroupSupplier(teamId, select);
  }

  public int txEditSupplier(Customer supplier, Integer teamId) {
    return customerDao.editSupplier(supplier, teamId);
  }

  public List<Customer> getByRegion(String province) {
    return customerDao.getCustomerByProvince(province, "");
  }

  public String getContact(String agentId) {
    if (agentId.isEmpty()) {
      return "" + "," + "" + "," + "";
    }
    Customer agent = customerDao.findAccount(Integer.parseInt(agentId));
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

  public List<Customer> getUsableSupplier(Integer teamId) {
    return customerDao.getUsableSupplier(teamId);
  }
}
