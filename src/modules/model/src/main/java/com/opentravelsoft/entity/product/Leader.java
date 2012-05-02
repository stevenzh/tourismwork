package com.opentravelsoft.entity.product;

import com.opentravelsoft.entity.Tourist;

/**
 * 领队
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:31 $
 */
public class Leader extends Tourist
{
    private static final long serialVersionUID = 1L;

    private String leadCard;

    public String getLeadCard()
    {
        return leadCard;
    }

    public void setLeadCard(String leadCard)
    {
        this.leadCard = leadCard;
    }

    public static long getSerialVersionUID()
    {
        return serialVersionUID;
    }

}
