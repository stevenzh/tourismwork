package com.opentravelsoft.service.account;

import java.util.List;

import com.opentravelsoft.entity.Contact;
import com.opentravelsoft.entity.Customer;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.product.Remind;

public interface CustomerService {
  public List<Customer> getAgent(String countryId, String provinceId,
      String cityId, String agentName, String opKey, String clearingCycle,
      int salesId, String customerCode, int teamId, String accountType);

  public int txDeleteAgent(long agentId);

  public Customer findAgent(long agentId);

  /**
   * 修改客户资料
   * 
   * @param agent
   * @param contacts
   * @return
   */
  public int txEditAgent(Customer agent, List<Contact> contacts);

  public int txCheckedAgent(Customer agent);

  // -------------------------------------------------------------------------

  public List<Employee> roGetSalesmanList();

  Customer roGetSupplier(int supplierId);

  int txDeleteSupplier(int supplierId);

  List<Customer> roGetSupplier(long teamId, String supplierResource, boolean b);

  List<Customer> roFindSupplier(String countryId, String supplierResource);

  int txSaveGroupSupplier(long teamId, String[] select);

  int txEditSupplier(Customer supplier, long teamId);

  /**
   * 根据地区取得代理商
   * 
   * @param nationCode
   * @return
   */
  public List<Customer> getByRegion(String code);

  public String getContact(String agentId);

  Remind roGetWaitAgents();

  public List<Customer> getSuppliserByType(String resource, int teamId);

  public List<Customer> getUsableSupplier(long teamId);
}
