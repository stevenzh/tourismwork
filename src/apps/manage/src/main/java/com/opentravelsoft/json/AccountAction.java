package com.opentravelsoft.json;

import java.util.ArrayList;
import java.util.List;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Customer;
import com.opentravelsoft.service.account.CustomerService;

/**
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:16 $
 */
public class AccountAction extends ManageAction {
  private static final long serialVersionUID = -5840178415466047565L;

  private CustomerService accountService;

  private String province;

  private String resource;

  private int teamId;

  private List<LabelValueBean> map = new ArrayList<LabelValueBean>();

  public String execute() {
    List<Customer> list = accountService.getByRegion(province);

    for (Customer customer : list) {
      map.add(new LabelValueBean(String.valueOf(customer.getCustomerId()),
          customer.getName()));
    }
    return SUCCESS;
  }

  public String supplierByType() {
    List<Customer> list = accountService.getSuppliserByType(resource, teamId);
    for (Customer customer : list) {
      map.add(new LabelValueBean(String.valueOf(customer.getCustomerId()),
          customer.getName()));
    }
    return SUCCESS;
  }

  @Autowired
  public void setAccountService(CustomerService accountService) {
    this.accountService = accountService;
  }

  public String info() {
    return SUCCESS;
  }

  public List<LabelValueBean> getMap() {
    return map;
  }

  public void setProvince(String province) {
    this.province = province;
  }

}
