package com.opentravelsoft.flex;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import flex.messaging.FactoryInstance;
import flex.messaging.FlexContext;
import flex.messaging.FlexFactory;
import flex.messaging.config.ConfigMap;
import flex.messaging.services.ServiceException;

class SpringFactoryInstance extends FactoryInstance {

    private Log log = LogFactory.getLog(getClass());

    SpringFactoryInstance(FlexFactory factory, String id, ConfigMap properties) {
        super(factory, id, properties);
    }

    public Object lookup() {
        ApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(
                FlexContext.getServletConfig().getServletContext()
        );
        String beanName = getSource();
        try {
            log.info("Lookup bean from Spring ApplicationContext: " + beanName);
            return appContext.getBean(beanName);
        }
        catch (NoSuchBeanDefinitionException nex) {
            ServiceException e = new ServiceException();
            String msg = "Spring service named '" + beanName + "' does not exist.";
            e.setMessage(msg);
            e.setRootCause(nex);
            e.setDetails(msg);
            e.setCode("Server.Processing");
            throw e;
        }
        catch (BeansException bex) {
            ServiceException e = new ServiceException();
            String msg = "Unable to create Spring service named '" + beanName + "'.";
            e.setMessage(msg);
            e.setRootCause(bex);
            e.setDetails(msg);
            e.setCode("Server.Processing");
            throw e;
        }
        catch (Exception ex) {
            ServiceException e = new ServiceException();
            String msg = "Unexpected exception when trying to create Spring service named '" + beanName + "'.";
            e.setMessage(msg);
            e.setRootCause(ex);
            e.setDetails(msg);
            e.setCode("Server.Processing");
            throw e;
        }
    }
}
