package com.opentravelsoft.action.account;

import com.opentravelsoft.webapp.action.PortalAction;

/**
 * 客户服务
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 */
public class CustomerServiceAction extends PortalAction
{
    private static final long serialVersionUID = -4245119305000104896L;

    private int viewSign;

    private int viewAllSign;

    public int getViewSign()
    {
        return viewSign;
    }

    public int getViewAllSign()
    {
        return viewAllSign;
    }

    public String execute()
    {
        viewAllSign = 1;
        return SUCCESS;
    }

    public String frequentAQ()
    {
        viewSign = 1;
        return SUCCESS;
    }

    public String newPeople()
    {
        viewSign = 2;
        return SUCCESS;
    }

    public String answerOnline()
    {
        viewSign = 3;
        return SUCCESS;
    }
}
