package com.opentravelsoft.providers.hibernate;

import java.util.List;

import com.opentravelsoft.util.LabelValueBean;

import com.opentravelsoft.entity.Contact;
import com.opentravelsoft.entity.Customer;
import com.opentravelsoft.entity.product.Remind;
import com.opentravelsoft.providers.GenericDao;

public interface CustomerDao extends GenericDao<Customer, Long> {

  /**
   * 取得销售员的所有客户
   * 
   * @param area 区域
   * @param salesId 销售员
   * @return
   */
  public List<LabelValueBean> getCustomerBySales(long salesId, String area);

  /**
   * 
   * @param area 区域
   * @param payment 结算方式
   * @return
   */
  public List<Customer> getCustomerByProvince(String area, String payment);

  /**
   * 取消客户
   * 
   * @param accountId
   * @return
   */
  public int deleteAccount(long accountId);

  /**
   * 
   * @param accountId 客户ID
   * @return
   */
  public Customer findAccount(long accountId);

  /**
   * 
   * @param agent
   * @param contacts
   * @return
   */
  public int editAccount(Customer agent, List<Contact> contacts);

  /**
   * 审核客户
   * 
   * @param agent
   * @return
   */
  public int checkedAccount(Customer agent);

  /**
   * 
   * @param countryId
   * @param provinceId
   * @param cityID
   * @param agentName
   * @param opKey
   * @param clear
   * @param userId
   * @param customerCode
   * @param teamId
   * @param accountType
   * @return
   */
  public List<Customer> getAgent(String countryId, String provinceId,
      String cityID, String agentName, String opKey, String clear, long userId,
      String customerCode, long teamId, String accountType);

  List<Customer> getSupplies(String countryId, String provinceId,
      String cityId, String supplierName, String feature, String resource,
      String destination, long groupId, String state);

  public Remind getUnAuditAgent();

  /**
   * 检查客户的欠款额度
   * 
   * @param agentId
   * @return 1:成功 0:警戒线 -1:失败
   */
  public int checkBound(long agentId);

  public List<Customer> getSupplierByType(String resource, long teamId);

  public List<Customer> getSuppliers(long teamId, String resource, boolean b);

  public int saveGroupSupplier(long teamId, String[] select);

  public int editSupplier(Customer supplier, long teamId);

  public List<Customer> getUsableSupplier(long teamId);

}
