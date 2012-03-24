package com.opentravelsoft.action.booking;

import com.opentravelsoft.webapp.action.PortalAction;

/**
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:59 $
 */
public class OrderAgreementAction extends PortalAction
{
    private static final long serialVersionUID = 9060116145473041520L;

    private String planNo;

    /** 操作类型 */
    private int paymentType;

    public String execute()
    {
        return SUCCESS;
    }

    public String getPlanNo()
    {
        return planNo;
    }

    public void setPlanNo(String planNo)
    {
        this.planNo = planNo;
    }

    public int getPaymentType()
    {
        return paymentType;
    }

    public void setPaymentType(int paymentType)
    {
        this.paymentType = paymentType;
    }

}
