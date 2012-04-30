package com.opentravelsoft.action.manage;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.service.setting.EmployeeService;
import com.opentravelsoft.util.MD5;
import com.opentravelsoft.util.StringUtil;

/**
 * 用户信息修改
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:16 $
 */
public class EditMyInfoAction extends ManageAction {
  private static final long serialVersionUID = 2886687498402018762L;

  @Autowired
  private EmployeeService employeeService;

  private Employee employee;

  private String oldPwd;

  private String newPwd;

  private String confirmPwd;

  @Override
  public String input() throws Exception {
    employee = getUser();
    return INPUT;
  }

  public String submit() throws Exception {
    Employee user = getUser();
    int ret = 0;
    MD5 md5 = new MD5();

    if (md5.getMD5ofStr(oldPwd).equalsIgnoreCase(user.getPasswd())) {
      if (StringUtil.hasLength(newPwd) && StringUtil.hasLength(confirmPwd)) {
        if (newPwd.equals(confirmPwd)) {
          employee.setPasswd(md5.getMD5ofStr(newPwd).toLowerCase());
        } else {
          addActionError(getText("ERR_PWD_DIFFERENT"));
          return INPUT;
        }
      }

      ret = employeeService.txUpdateEmployee(employee);
    } else {
      addActionError(getText("ERR_PWD_INVALIDATION"));
    }

    if (ret < 0) {
      addActionError(getText("ERR_UPDATE_FALURRE"));
    } else {
      user.setPasswd(employee.getPasswd());
      user.setPhone(employee.getPhone());
      user.setFax(employee.getFax());
      addActionMessage(getText("MSG_UPDATE_SUCCESS"));
    }
    return SUCCESS;
  }

  public String getOldPwd() {
    return oldPwd;
  }

  public String getNewPwd() {
    return newPwd;
  }

  public String getConfirmPwd() {
    return confirmPwd;
  }

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public void setOldPwd(String oldPwd) {
    this.oldPwd = oldPwd;
  }

  public void setNewPwd(String newPwd) {
    this.newPwd = newPwd;
  }

  public void setConfirmPwd(String confirmPwd) {
    this.confirmPwd = confirmPwd;
  }

}
