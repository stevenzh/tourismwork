package com.opentravelsoft.action.manage;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.BaseAction;

/**
 * 发布工作流
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 */
public class DeployAction extends BaseAction {
  private static final long serialVersionUID = -6773542712338723141L;

  protected static final Log logger = LogFactory.getLog(DeployAction.class);

  private List<ProcessDefinition> definitions;

  private String srcClasspath;

  @Autowired
  private ProcessEngine processEngine;

  @Override
  public String execute() {
    definitions = processEngine.getRepositoryService()
        .createProcessDefinitionQuery().list();
    return SUCCESS;
  }

  public String submit() {
    DeploymentBuilder builder = processEngine.getRepositoryService()
        .createDeployment();
    String deploymentId = builder.addClasspathResource(srcClasspath).deploy()
        .getId();
    logger.debug("Deployment ID:" + deploymentId);
    addActionMessage("Deloy process success.");

    return SUCCESS;
  }

  public List<ProcessDefinition> getDefinitions() {
    return definitions;
  }

  public String getSrcClasspath() {
    return srcClasspath;
  }

  public void setSrcClasspath(String srcClasspath) {
    this.srcClasspath = srcClasspath;
  }

}
