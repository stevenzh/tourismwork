package com.opentravelsoft.action.manage.setting;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Group;
import com.opentravelsoft.service.setting.DepartmentService;

/**
 * 参数设置:部门维护
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.2 $ $Date: 2009/04/10 07:47:29 $
 */
public class DepartmentAction extends ManageAction {
  private static final long serialVersionUID = -6773542712338723141L;

  protected static final Log logger = LogFactory.getLog(DepartmentAction.class);

  @Autowired
  private DepartmentService departmentService;

  private Group root = new Group();

  private int groupId;

  private Group group = new Group();

  private List<Group> dptList = new ArrayList<Group>();

  @Override
  public String execute() {
    List<Group> deplist = departmentService.getAllDepartments();

    root.setGroupId(0);
    root.setName("旅行社");

    for (Group department : deplist) {
      if (null == department.getParent()) {
        root.addChildren(department);
      }
    }

    return SUCCESS;
  }

  public String detail() {
    group = departmentService.getDepartment(groupId);
    dptList = departmentService.getAllDepartments();
    return SUCCESS;
  }

  public String submit() {
    departmentService.txSaveDepartment(group);
    return SUCCESS;
  }

  public Group getTreeRootNode() {
    return root;
  }

  public Group getDepartment() {
    return group;
  }

  public int getDptNo() {
    return groupId;
  }

  public void setDptNo(int groupId) {
    this.groupId = groupId;
  }

  public List<Group> getDptList() {
    return dptList;
  }
}
