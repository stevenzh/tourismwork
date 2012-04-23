package com.opentravelsoft.action.manage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.common.SessionKeyParams;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.service.LoginService;

/**
 * 用户登录
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:16 $
 */
public class LoginAction extends ManageAction {

  private static final long serialVersionUID = 3123694585769982557L;

  protected static final Log logger = LogFactory.getLog(LoginAction.class);

  @Autowired
  private LoginService loginService;

  private String uid;

  private String passwd;

  private String tel;

  public String getPasswd() {
    return passwd;
  }

  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String input() throws Exception {
    return SUCCESS;
  }

  public String execute() throws Exception {
    Employee employee = loginService.roGetEmployee(uid);
    if (null != employee) {
      ActionContext.getContext().getSession()
          .put(SessionKeyParams.EBIZ_USER, employee);
      return SUCCESS;
    }
    return INPUT;
  }
}
