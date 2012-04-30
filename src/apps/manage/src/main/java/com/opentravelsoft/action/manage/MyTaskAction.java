package com.opentravelsoft.action.manage;

import java.util.List;

import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.common.EbizCommon;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.workflow.TaskDao;

/**
 * Express
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:16 $
 */
public class MyTaskAction extends ManageAction {
  private static final long serialVersionUID = 2886687498402018762L;

  @Autowired
  private TaskDao taskService;

  private List<Task> tasks;

  public String execute() throws Exception {
    Employee user = getUser();
    if (getSysConfig(EbizCommon.WORKFLOW_ENABLED).equals("1")) {
      tasks = taskService.getMyTask(user.getUid());
    }
    return SUCCESS;
  }

  public List<Task> getTasks() {
    return tasks;
  }

}
