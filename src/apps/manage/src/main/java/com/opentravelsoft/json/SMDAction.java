package com.opentravelsoft.json;

import org.apache.struts2.json.annotations.SMDMethod;

import com.opensymphony.xwork2.Action;

public class SMDAction
{
    public String smd()
    {
        return Action.SUCCESS;
    }

    @SMDMethod
    public Bean doSomething(Bean bean, int quantity)
    {
        bean.setPrice(quantity * 10);
        return bean;
    }
}
