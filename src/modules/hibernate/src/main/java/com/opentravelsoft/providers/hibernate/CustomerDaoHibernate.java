package com.opentravelsoft.providers.hibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.opentravelsoft.providers.CustomerDao;
import com.opentravelsoft.util.LabelValueBean;
import org.hibernate.LockMode;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.Contact;
import com.opentravelsoft.entity.Customer;
import com.opentravelsoft.entity.TeamSupplier;
import com.opentravelsoft.entity.TeamSupplierId;
import com.opentravelsoft.entity.product.Remind;
import com.opentravelsoft.util.RowDataUtil;
import com.opentravelsoft.util.StringUtil;

@Repository("CustomerDao")
public class CustomerDaoHibernate extends
    GenericDaoHibernate<Customer, Integer> implements CustomerDao {

  public CustomerDaoHibernate() {
    super(Customer.class);
  }

  @SuppressWarnings("unchecked")
  public List<LabelValueBean> getCustomerBySales(int salesId, String area) {
    StringBuilder sql = new StringBuilder();

    sql.append("select customerId,name,contact,contactTel,isActive,code,");
    sql.append("passwd ");
    sql.append("from Customer ");
    sql.append("where sales.userId=? and del='N' and isActive='Y' ");
    sql.append("and isAgent=1 ");
    sql.append("order by name");

    Object[] param = { salesId };
    List<Object[]> list = getHibernateTemplate().find(sql.toString(), param);

    List<LabelValueBean> ret = new ArrayList<LabelValueBean>();
    for (Object[] obj : list) {
      Customer agent = new Customer();
      agent.setCustomerId(RowDataUtil.getInt(obj[0]));
      agent.setName(RowDataUtil.getString(obj[1]));
      agent.setContact(RowDataUtil.getString(obj[2]));
      agent.setContactTel(RowDataUtil.getString(obj[3]));
      agent.setIsActive(RowDataUtil.getChar(obj[4]));
      agent.setCode(RowDataUtil.getString(obj[5]));
      agent.setPasswd(RowDataUtil.getString(obj[6]));

      //
      ret.add(new LabelValueBean(String.valueOf(agent.getCustomerId()), agent
          .getName() + "_" + agent.getContact()));
    }
    return ret;
  }

  @SuppressWarnings("unchecked")
  public List<Customer> getCustomerByProvince(String province, String payment) {
    StringBuilder sql = new StringBuilder();
    sql.append("from Customer ");
    sql.append("where provinceCd=? and isActive='Y' and del='N' ");
    sql.append("and isAgent=1 ");
    if (StringUtil.hasLength(payment))
      sql.append("and payment='" + payment + "' ");
    sql.append("order by name");

    Object[] param = { province };
    List<Customer> list = getHibernateTemplate().find(sql.toString(), param);
    List<Customer> ret = new ArrayList<Customer>();

    for (Customer obj : list) {
      Customer agent = new Customer();
      agent.setCustomerId(obj.getCustomerId());
      agent.setName(obj.getName());
      agent.setContact(obj.getContact());
      agent.setContactTel(obj.getContactTel());
      agent.setIsActive(RowDataUtil.getChar(obj.getIsActive()));
      ret.add(agent);
    }
    return ret;
  }

  public Customer findAccount(int customerId) {
    Customer customer = (Customer) getHibernateTemplate().get(Customer.class,
        customerId, LockMode.READ);

    customer.setType(RowDataUtil.getChar(customer.getType()));
    customer.setStay(customer.getStay().multiply(new BigDecimal(100)));
    customer.setRegion(customer.getRoute());
    customer.setIsActive(RowDataUtil.getChar(customer.getIsActive()));

    return customer;
  }

  @SuppressWarnings("unchecked")
  public List<Customer> getAgent(String countryId, String provinceId,
      String cityId, String agentName, String enabled, String clear,
      Integer userId, String customerCode, Integer teamId, String accountType) {
    StringBuilder sb = new StringBuilder();
    sb.append("select a.customerId,a.name,a.address,b.cnName,"); // 3
    sb.append("a.city.citynm,a.contact,a.contactTel,a.contactFax,"); // 7
    sb.append("a.contactEmail,a.type,a.structure,a.isActive,"); // 11
    sb.append("a.sales.userId,a.sales.userName,a.code,a.passwd,");// 15
    sb.append("a.payment ");
    sb.append("from Customer a,");
    sb.append("Province b ");
    sb.append("where a.provinceCd=b.code and a.del='N' ");
    if (teamId != 0) {
      sb.append("and a.team.teamId=" + teamId + " ");
    }
    if (StringUtil.hasLength(provinceId)) {
      sb.append("and a.provinceCd = '" + provinceId + "' ");
    }
    if (StringUtil.hasLength(cityId)) {
      sb.append("and a.city.citycd = '" + cityId + "' ");
    }
    if (StringUtil.hasLength(agentName)) {
      sb.append("and a.name like '%" + agentName + "%' ");
    }
    if (StringUtil.hasLength(enabled)) {
      sb.append("and a.isActive = '" + enabled + "' ");
    }
    if (StringUtil.hasLength(clear)) {
      sb.append("and a.payment = '" + clear + "' ");
    }
    if (userId != 0) {
      sb.append("and a.sales.userId=" + userId + " ");
    }
    if (StringUtil.hasLength(customerCode)) {
      sb.append("and a.customerId=" + customerCode + " ");
    }

    // 代理商/供应商
    if (accountType.equals("A"))
      sb.append("and a.isAgent=1 ");
    else if (accountType.equals("S"))
      sb.append("and a.isSupplier=1 ");

    sb.append("order by a.name");

    List<Object[]> list = getHibernateTemplate().find(sb.toString());
    List<Customer> ret = new ArrayList<Customer>();
    Customer agent = null;

    for (Object[] obj : list) {
      agent = new Customer();
      agent.setCustomerId(RowDataUtil.getInt(obj[0]));
      agent.setName(RowDataUtil.getString(obj[1]));
      agent.setAddress(RowDataUtil.getString(obj[2]));
      agent.setProvinceCd(RowDataUtil.getString(obj[3]));
      agent.getCity().setCitycd(RowDataUtil.getString(obj[4]));

      // 联系人信息
      agent.setContact(RowDataUtil.getString(obj[5]));
      agent.setContactTel(RowDataUtil.getString(obj[6]));
      agent.setContactFax(RowDataUtil.getString(obj[7]));
      agent.setContactEmail(RowDataUtil.getString(obj[8]));

      agent.setIsActive(RowDataUtil.getChar(obj[11]));
      agent.getSales().setUserId(RowDataUtil.getInt(obj[12]));
      agent.getSales().setUserName(RowDataUtil.getString(obj[13]));
      agent.setCode(RowDataUtil.getString(obj[14]));
      agent.setPasswd(RowDataUtil.getString(obj[15]));

      ret.add(agent);
    }

    return ret;
  }

  public int deleteAccount(int agentId) {
    StringBuilder sql = new StringBuilder();
    sql.append("update Contact set del='Y' where customerId=? ");
    Object[] params = { agentId };
    int result = getHibernateTemplate().bulkUpdate(sql.toString(), params);
    if (result != 1) {
      return -1;
    }
    return 0;
  }

  public int checkedAccount(Customer agent) {
    HibernateTemplate template = getHibernateTemplate();
    Customer tblCustomer = (Customer) template.get(Customer.class,
        agent.getCustomerId(), LockMode.PESSIMISTIC_WRITE);
    if (tblCustomer != null) {
      if (null != tblCustomer.getIsActive()
          && tblCustomer.getIsActive().equals("Y"))
        return -2;

      tblCustomer.setIsActive('Y');
      Date sysdate = getSysdate();
      tblCustomer.setCheckDate(sysdate);
      tblCustomer.setCheckedBy(agent.getCheckedBy());
      template.update(tblCustomer);
    } else {
      return -1;
    }
    return 0;
  }

  @SuppressWarnings("unchecked")
  public int editAccount(Customer agent, List<Contact> contacts) {
    boolean create = false;
    HibernateTemplate template = getHibernateTemplate();
    Customer customer = null;

    if (agent.getCustomerId() != 0) {
      customer = (Customer) template.get(Customer.class, agent.getCustomerId(),
          LockMode.PESSIMISTIC_WRITE);
    }

    if (null == customer) {
      create = true;
      customer = new Customer();
    }

    // Set code
    customer.setName(agent.getName());
    customer.setAddress(agent.getAddress());
    customer.setProvinceCd(agent.getProvinceCd());
    customer.setCity(agent.getCity());
    customer.setDistrict(agent.getDistrict());
    customer.setZip(agent.getZip());

    // 联系人信息
    customer.setContact(agent.getContact());
    customer.setContactTel(agent.getContactTel());
    customer.setContactFax(agent.getContactFax());
    customer.setContactEmail(agent.getContactEmail());

    customer.setBussId(agent.getBussId());
    customer.setBussDate(agent.getBussDate());
    customer.setType(agent.getType());
    customer.setCreditAmt1(agent.getCreditAmt1());
    customer.setCreditAmt2(agent.getCreditAmt2());
    customer.setSales(agent.getSales());

    if (agent.getStay().doubleValue() == 0)
      customer.setStay(new BigDecimal(0));
    else
      customer.setStay(agent.getStay().divide(new BigDecimal(100)));

    customer.setStructure(RowDataUtil.getChar(agent.getStructure()));
    customer.setPayment(agent.getClearingCycle());

    customer.setUpdatedby(agent.getUpdatedby());
    customer.setDel('N');
    customer.setIsAgent(agent.isIsAgent());
    customer.setIsSupplier(agent.isIsSupplier());

    if (create) {
      Random rand = new Random();
      String s = String.valueOf(rand.nextFloat());
      // Set password
      customer.setPasswd(s.substring(2, 8));
      customer.setIsActive('N');
      customer.setCreatedby(agent.getUpdatedby());
      customer.setCode(StringUtil.padding(customer.getCustomerId(), 10));
      template.save(customer);
    }

    customer.setCode(StringUtil.padding(customer.getCustomerId(), 10));
    template.update(customer);

    if (!create) {
      StringBuilder sql1 = new StringBuilder();
      sql1.append("delete from Contact where customerId=? ");
      Object[] params = { customer.getCustomerId() };
      getHibernateTemplate().bulkUpdate(sql1.toString(), params);
    }

    StringBuilder sb1 = new StringBuilder();
    sb1.append("from Contact where customerId=? and mobile=? ");
    Object[] param1 = { customer.getCustomerId(), "" };

    // 保存联系人
    if (null != contacts)
      for (Contact contact : contacts) {
        param1[1] = contact.getMobile();
        // 是否存在相同的手机
        List<Contact> listTemp = getHibernateTemplate().find(sb1.toString(),
            param1);
        if (listTemp.isEmpty()) {
          Contact cont = new Contact();
          cont.setCustomerId(customer.getCustomerId());
          cont.setName(contact.getName());
          cont.setRank(contact.getRank());
          cont.setPhone(contact.getPhone());
          cont.setFax(contact.getFax());
          cont.setMobile(contact.getMobile());
          cont.setMsn(contact.getMsn());
          cont.setEmail(contact.getEmail());
          cont.setQq(contact.getQq());
          cont.setDel('N');

          getHibernateTemplate().save(cont);
        }
      }

    return 0;
  }

  @SuppressWarnings("unchecked")
  public Remind getUnAuditAgent() {
    StringBuilder sb = new StringBuilder();
    sb.append("select count(*) from Customer ");
    sb.append("where del='N' and isActive='N' ");

    List<Long> custCount = getHibernateTemplate().find(sb.toString());
    Remind remind = new Remind();
    remind.setCount(custCount.get(0));

    return remind;
  }

  @SuppressWarnings("unchecked")
  public int checkBound(int agentId) {
    // 客户的欠款额度
    double amt = 0;
    double amt2 = 0;
    double per = 0;
    String payment = "M"; // M 为月结客户
    Object[] param = { agentId };

    StringBuilder sql = new StringBuilder();
    sql.append("select creditAmt2,stay,payment from Customer where customerId=? ");
    List<Object[]> op = getHibernateTemplate().find(sql.toString(), param);
    if (op.size() > 0) {
      amt = RowDataUtil.getDouble(op.get(0)[0]);
      per = RowDataUtil.getDouble(op.get(0)[1]);
      payment = RowDataUtil.getString(op.get(0)[1]);
      amt2 = amt * (1 + per);
    }

    // 客户的上月结算欠款
    /*
     * String month = ""; double mhRemainder = 0; sql = new StringBuilder();
     * sql.append("select id.month, remainder "); sql.append("from
     * com.opentravelsoft.entity.finance.CustomerMonth "); sql.append("where
     * id.customerId=?"); sql.append("ORDER BY id.month DESC ");
     * 
     * List<Object[]> list = getHibernateTemplate() .find(sql.toString(),
     * param); if (null != list && list.size() > 0) { Object[] obj =
     * list.get(0); month = RowDataUtil.getString(obj[0]); mhRemainder =
     * RowDataUtil.getDouble(obj[1]); }
     */

    // 本月欠款合计
    // 本月未付
    double remainder = 0;
    int retu = 1; // 1 : 通过验证

    if (!payment.equals("M")) {
      sql = new StringBuilder();
      sql.append("select sum(dbamt)-sum(cramt) ");
      sql.append("from Booking ");
      sql.append("where customerId=? and delkey='N' and confirmStatus='1' ");
      sql.append("and outDate >='2008-07-01' ");

      List<Object> lista = getHibernateTemplate().find(sql.toString(), param);

      remainder = RowDataUtil.getDouble(lista.get(0));

      if (amt > 0) {
        if (remainder > amt)
          retu = 0;
        if (remainder > amt2)
          retu = -1;
      }
    }

    return retu;
  }

  // -------------------------------------------------------------------------
  /**
   * 取得供应商
   */
  @SuppressWarnings("unchecked")
  public List<Customer> getUsableSupplier(Integer teamId) {
    StringBuilder sb = new StringBuilder();
    sb.append("select a.customerId,a.name ");
    sb.append("from Customer a ");
    if (teamId != 0) {
      sb.append(",TeamSupplier b ");
      sb.append("where a.customerId=b.id.supplierId ");
      sb.append("and b.id.teamId=" + teamId + " and ");
    } else {
      sb.append("where ");
    }
    sb.append("a.del='N' and a.isActive='Y' and a.isSupplier=1 ");
    sb.append("order by a.name");
    List<Object[]> supList = getHibernateTemplate().find(sb.toString());

    List<Customer> newList = new ArrayList<Customer>();
    Customer supplier = null;

    for (Object[] sup : supList) {
      supplier = new Customer();
      supplier.setSupplierId(RowDataUtil.getInt(sup[0]));
      supplier.setSupplierName(RowDataUtil.getString(sup[1]));
      newList.add(supplier);
    }

    return newList;
  }

  /**
   * 按照供应商类型查找供应商
   */
  @SuppressWarnings("unchecked")
  public List<Customer> getSupplierByType(String resource, Integer teamId) {
    StringBuilder sb = new StringBuilder();

    sb.append("select a.customerId,a.name ");
    sb.append("from Customer a ");
    if (teamId != 0) {
      sb.append(", TeamSupplier b ");
      sb.append("where a.customerId=b.id.supplierId ");
      sb.append("and b.id.teamId=" + teamId + " and ");
    } else {
      sb.append("where ");
    }

    sb.append("a.resource=? and a.del='N' and a.isActive='Y' ");
    sb.append("and a.isSupplier=1 ");
    sb.append("order by a.name ");
    Object[] param = { resource };
    List<Object[]> supList = getHibernateTemplate().find(sb.toString(), param);
    List<Customer> newList = new ArrayList<Customer>();
    Customer supplier = null;

    for (Object[] sup : supList) {
      supplier = new Customer();
      supplier.setSupplierId(RowDataUtil.getInt(sup[0]));
      supplier.setSupplierName(RowDataUtil.getString(sup[1]));
      newList.add(supplier);
    }
    return newList;

  }

  @SuppressWarnings("unchecked")
  public List<Customer> getSupplies(String countryId, String provinceId,
      String cityId, String supplierName, String feature, String resource,
      String destination, Integer teamId, String state) {
    StringBuilder sb = new StringBuilder();
    sb.append("select a.customerId,a.name,a.countryCd,b.name,");
    sb.append("a.provinceCd,a.city.citycd,a.city.citynm,");
    sb.append("a.address,a.status,a.isActive,a.contact ");
    sb.append("from Customer a,");
    sb.append("Country b ");
    if (teamId != 0)
      sb.append(",TeamSupplier d ");

    sb.append("where a.countryCd=b.countryId ");
    sb.append("and a.del='N' and a.isSupplier=1 ");

    if (StringUtil.hasLength(cityId)) {
      sb.append("and a.city.citycd = '" + cityId + "' ");
    }

    if (teamId != 0) {
      sb.append("and a.customerId=d.id.supplierId ");
      sb.append("and d.id.teamId=" + teamId + " ");
    }

    if (StringUtil.hasLength(countryId))
      sb.append("and a.countryCd = '" + countryId + "' ");

    if (StringUtil.hasLength(supplierName)) {
      sb.append("and a.name like '%" + supplierName + "%' ");
    }
    if (StringUtil.hasLength(resource)) {
      sb.append("and a.resource ='" + resource + "' ");
    }
    if (StringUtil.hasLength(destination)) {
      sb.append("and a.route like '%" + destination + "%' ");
    }
    if (StringUtil.hasLength(state))
      sb.append("and a.isActive='" + state + "' ");

    sb.append("order by a.name");

    List<Object[]> list = getHibernateTemplate().find(sb.toString());
    List<Customer> ret = new ArrayList<Customer>();
    Customer supplier = null;

    for (Object[] obj : list) {
      supplier = new Customer();
      supplier.setSupplierId(RowDataUtil.getInt(obj[0]));
      supplier.setName(RowDataUtil.getString(obj[1]));
      supplier.setCountryCd(RowDataUtil.getString(obj[2]));
      supplier.setCountryName(RowDataUtil.getString(obj[3]));
      supplier.setProvinceCd(RowDataUtil.getString(obj[4]));
      supplier.getCity().setCitycd(RowDataUtil.getString(obj[5]));
      supplier.getCity().setCitynm(RowDataUtil.getString(obj[6]));
      supplier.setAddress(RowDataUtil.getString(obj[7]));
      supplier.setIsActive(RowDataUtil.getChar(obj[9]));
      supplier.setContact(RowDataUtil.getString(obj[10]));

      ret.add(supplier);
    }

    return ret;
  }

  @SuppressWarnings("unchecked")
  public List<Customer> getSuppliers(Integer teamId, String supplierResource,
      boolean b) {
    StringBuilder sb = new StringBuilder();
    sb.append("select a.customerId,a.name,a.countryCd,a.provinceCd,");
    sb.append("a.city.citycd,a.contact,a.contactTel,a.isActive,a.del ");
    sb.append("from Customer a,");
    sb.append("TeamSupplier b ");
    sb.append("where a.customerId = b.id.supplierId ");
    sb.append("and b.id.teamId=? and a.del='N' and a.isSupplier=1 ");
    if (StringUtil.hasLength(supplierResource)) {
      sb.append("and a.resource ='" + supplierResource + "' ");
    }
    sb.append("order by a.name");

    Object[] params = { teamId };
    List<Object[]> list = getHibernateTemplate().find(sb.toString(), params);
    List<Customer> ret = new ArrayList<Customer>();
    Customer supplier = null;

    for (Object[] obj : list) {
      supplier = new Customer();
      supplier.setSupplierId(RowDataUtil.getInt(obj[0]));
      supplier.setName(RowDataUtil.getString(obj[1]));
      supplier.setCountryCd(RowDataUtil.getString(obj[2]));
      supplier.setProvinceCd(RowDataUtil.getString(obj[3]));
      supplier.getCity().setCitycd(RowDataUtil.getString(obj[4]));
      supplier.setContact(RowDataUtil.getString(obj[5]));
      supplier.setContactTel(RowDataUtil.getString(obj[6]));
      supplier.setIsActive(RowDataUtil.getChar(obj[7]));
      supplier.setDel(RowDataUtil.getChar(obj[8]));
      ret.add(supplier);
    }

    return ret;
  }

  public int saveGroupSupplier(Integer teamId, String[] select) {
    StringBuilder sql = new StringBuilder();

    sql.append("delete from TeamSupplier where id.teamId=? ");

    Object[] param = { teamId };
    getHibernateTemplate().bulkUpdate(sql.toString(), param);

    for (String obj : select) {
      getHibernateTemplate().save(
          new TeamSupplier(new TeamSupplierId(Integer.parseInt(obj), teamId)));
    }

    return 0;
  }

  public int editSupplier(Customer supplier, Integer teamId) {
    boolean create = false;
    HibernateTemplate template = getHibernateTemplate();
    Customer tblCust = (Customer) template.get(Customer.class,
        supplier.getSupplierId(), LockMode.PESSIMISTIC_WRITE);

    if (null == tblCust) {
      tblCust = new Customer();
      create = true;
    }

    tblCust.setName(supplier.getName());
    tblCust.setCountryCd(supplier.getCountryCd());
    tblCust.setProvinceCd(supplier.getProvinceCd());
    tblCust.setCity(supplier.getCity());
    tblCust.setZip(supplier.getZip());
    tblCust.setAddress(supplier.getAddress());
    tblCust.setBussId(supplier.getBussId());

    tblCust.setFax(supplier.getFax());
    tblCust.setContactTel(supplier.getContactTel());
    tblCust.setContact(supplier.getContact());
    tblCust.setContactEmail(supplier.getContactEmail());

    tblCust.setFeature(RowDataUtil.getChar(supplier.getFeature()));
    tblCust.setRoute(supplier.getRegion());
    tblCust.setResource(RowDataUtil.getChar(supplier.getResource()));
    tblCust.setPayment(supplier.getClearingCycle());

    tblCust.setBankid1(supplier.getBankid1());
    tblCust.setBankname1(supplier.getBankname1());
    tblCust.setBcltname1(supplier.getBcltname1());
    // tblCust.setEnabled('N');
    tblCust.setDel('N');

    tblCust.setUpdatedby(supplier.getUpdatedby());
    if (supplier.getSupplierId() <= 0) {
      tblCust.setCreatedby(supplier.getUpdatedby());
    }
    template.saveOrUpdate(tblCust);

    if (!create) {
      StringBuilder sql = new StringBuilder();
      sql.append("delete from Contact where supplierId=? ");
      Object[] params = { tblCust.getSupplierId() };
      getHibernateTemplate().bulkUpdate(sql.toString(), params);
    }

    List<Contact> contacts = supplier.getContacts();
    Contact tblSupplierContact;
    for (Contact contact : contacts) {
      tblSupplierContact = new Contact();
      tblSupplierContact.setCustomerId(tblCust.getSupplierId());
      tblSupplierContact.setName(contact.getName());
      tblSupplierContact.setRank(contact.getRank());
      tblSupplierContact.setPhone(contact.getPhone());
      tblSupplierContact.setFax(contact.getFax());
      tblSupplierContact.setMobile(contact.getMobile());
      tblSupplierContact.setEmail(contact.getEmail());
      tblSupplierContact.setMsn(contact.getMsn());
      tblSupplierContact.setCreatedby(supplier.getUpdatedby());
      template.save(tblSupplierContact);
    }

    if (create && teamId != 0) {
      // template.flush();
      TeamSupplier item = new TeamSupplier(new TeamSupplierId(
          tblCust.getSupplierId(), teamId));

      template.save(item);

    }
    return 0;
  }
}
