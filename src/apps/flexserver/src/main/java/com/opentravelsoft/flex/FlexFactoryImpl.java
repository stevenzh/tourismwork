package com.opentravelsoft.flex;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import flex.messaging.FactoryInstance;
import flex.messaging.FlexFactory;
import flex.messaging.config.ConfigMap;

/**
 * Flex service factory used by Flex to lookup flex service object in Spring 
 * WebApplicationContext.
 * 
 * @author Michael Liao (askxuefeng@gmail.com)
 */
public class FlexFactoryImpl implements FlexFactory {

    private Log log = LogFactory.getLog(getClass());

    public FactoryInstance createFactoryInstance(String id, ConfigMap properties) {
        log.info("Create FactoryInstance.");
        SpringFactoryInstance instance = new SpringFactoryInstance(this, id, properties);
        instance.setSource(properties.getPropertyAsString(SOURCE, instance.getId()));
        return instance;
    }

    public Object lookup(FactoryInstance instanceInfo) {
        log.info("Lookup service object.");
        return instanceInfo.lookup();
    }

    public void initialize(String id, ConfigMap configMap) {
    }
}

