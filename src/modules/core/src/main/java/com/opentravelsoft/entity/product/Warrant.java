package com.opentravelsoft.entity.product;

import java.util.Date;

/**
 * 担保
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:32 $
 */
public class Warrant
{
    private Character warrantFlag;

    private String warrantBy;

    private double warratMoney;

    private Date warrantDate;

    private Date lastPayDate;

    public Character getWarrantFlag()
    {
        return warrantFlag;
    }

    public void setWarrantFlag(Character warrantFlag)
    {
        this.warrantFlag = warrantFlag;
    }

    public String getWarrantBy()
    {
        return warrantBy;
    }

    public void setWarrantBy(String warrantBy)
    {
        this.warrantBy = warrantBy;
    }

    public Date getWarrantDate()
    {
        return warrantDate;
    }

    public void setWarrantDate(Date warrantDate)
    {
        this.warrantDate = warrantDate;
    }

    public Date getLastPayDate()
    {
        return lastPayDate;
    }

    public void setLastPayDate(Date lastPayDate)
    {
        this.lastPayDate = lastPayDate;
    }

    public double getWarratMoney()
    {
        return warratMoney;
    }

    public void setWarratMoney(double warratMoney)
    {
        this.warratMoney = warratMoney;
    }
}
