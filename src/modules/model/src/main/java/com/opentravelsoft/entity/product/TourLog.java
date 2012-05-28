package com.opentravelsoft.entity.product;

import java.util.Date;

/**
 * 操作日志
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:32 $
 */
public class TourLog
{
    private String state;

    private String creator;

    private Date crDate;

    private String modifiedUser;

    private String modifiedUserName;

    private Date modifiedDate;

    private String expressId;

    private String lineNo;

    private String tourNo;

    private String note;

    public TourLog()
    {
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getCreator()
    {
        return creator;
    }

    public void setCreator(String creator)
    {
        this.creator = creator;
    }

    public Date getCrDate()
    {
        return crDate;
    }

    public void setCrDate(Date crDate)
    {
        this.crDate = crDate;
    }

    public String getModifiedUser()
    {
        return modifiedUser;
    }

    public void setModifiedUser(String modifiedUser)
    {
        this.modifiedUser = modifiedUser;
    }

    public String getModifiedUserName()
    {
        return modifiedUserName;
    }

    public void setModifiedUserName(String modifiedUserName)
    {
        this.modifiedUserName = modifiedUserName;
    }

    public Date getModifiedDate()
    {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate)
    {
        this.modifiedDate = modifiedDate;
    }

    public String getExpressId()
    {
        return expressId;
    }

    public void setExpressId(String expressId)
    {
        this.expressId = expressId;
    }

    public String getRouteNo()
    {
        return lineNo;
    }

    public void setRouteNo(String lineNo)
    {
        this.lineNo = lineNo;
    }

    public String getTourNo()
    {
        return tourNo;
    }

    public void setTourNo(String tourNo)
    {
        this.tourNo = tourNo;
    }

    public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }

}
