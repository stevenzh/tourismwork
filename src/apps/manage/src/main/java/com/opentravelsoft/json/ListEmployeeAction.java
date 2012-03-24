package com.opentravelsoft.json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.service.product.LineService;
import com.opentravelsoft.service.setting.EmployeeService;

/**
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:16 $
 */
public class ListEmployeeAction extends ManageAction {
  private static final long serialVersionUID = -5840178415466047565L;

  private EmployeeService employeeSevice;

  private Map<String, String> map = new HashMap<String, String>();

  private int groupId;

  private int teamId;

  @Autowired
  public void setEmployeeSevice(EmployeeService employeeSevice) {
    this.employeeSevice = employeeSevice;
  }

  public String execute() {
    List<Employee> list = employeeSevice.getUserByTeam(groupId);
    for (Employee obj : list) {
      map.put(String.valueOf(obj.getUserId()), obj.getUserName());
    }

    return Action.SUCCESS;
  }

  public Map<String, String> getMap() {
    return map;
  }

  public void setGroupId(int groupId) {
    this.groupId = groupId;
  }

  public void setTeamId(int teamId) {
    this.teamId = teamId;
  }

}
