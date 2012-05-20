package com.opentravelsoft.job;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;

import com.opentravelsoft.EbizException;

public class QuartzJob implements Job {

  protected static final Log logger = LogFactory.getLog(QuartzJob.class);

  public void execute(JobExecutionContext jctx) throws JobExecutionException {
    logger.info("Quartz的任务调度开始！！！");

    Map dataMap = jctx.getJobDetail().getJobDataMap();
    ApplicationContext ctx = (ApplicationContext) dataMap
        .get("applicationContext");
    // ApplicationContext ctx = new
    // FileSystemXmlApplicationContext("D:/eclipse/workspace/TourismWork/src/main/webapp/WEB-INF/applicationContext.xml");

    // CbSearchService cbIndexAction = (CbSearchService) ctx
    // .getBean("CbIndexService");
    // try {
    // cbIndexAction.getAllLineIndex();
    // } catch (EbizException e) {
    // logger.error("", e);
    // }
    logger.info("Quartz的任务调度开始结束！！！");

    // 对JobDataMap所做的更改是否被会持久，取决于任务的类型
  }

}
