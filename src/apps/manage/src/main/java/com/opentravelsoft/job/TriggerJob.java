package com.opentravelsoft.job;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TriggerJob implements ServletContextListener
{

    protected static final Log logger = LogFactory.getLog(TriggerJob.class);

    public void contextDestroyed(ServletContextEvent arg0)
    {
        // TODO Auto-generated method stub
    }

    public void contextInitialized(ServletContextEvent arg0)
    {
        logger.info("启动任务调度监听");
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "quartz.xml");
    }
}
