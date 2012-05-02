package com.opentravelsoft.action.account;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opentravelsoft.common.SessionKeyParams;
import com.opentravelsoft.entity.Customer;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.service.account.CustomerService;
import com.opentravelsoft.webapp.action.PortalAction;

/**
 * 客户帐户显示
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 */
public class ManageAccountAction extends PortalAction {

  private static final long serialVersionUID = 3123694585769982557L;

  protected static final Log logger = LogFactory
      .getLog(ManageAccountAction.class);

  @Autowired
  private CustomerService customerService;

  private Customer account;

  private int accountId;

  private List<LabelValueBean> outCityList = new ArrayList<LabelValueBean>();

  public String input() throws Exception {
    Object obj = ActionContext.getContext().getSession()
        .get(SessionKeyParams.EBIZ_USER);

    if (null == obj)
      addActionError("帐户错误.");
    else {
      Employee employee = (Employee) obj;
      accountId = 0;
      account = customerService.findAgent(accountId);
    }
    return INPUT;
  }

  public List<LabelValueBean> getOutCityList() {
    return outCityList;
  }

  public void setOutCityList(List<LabelValueBean> outCityList) {
    this.outCityList = outCityList;
  }

  public Customer getAccount() {
    return account;
  }

  public int getAccountId() {
    return accountId;
  }

  public void setAccountId(int accountId) {
    this.accountId = accountId;
  }

}
