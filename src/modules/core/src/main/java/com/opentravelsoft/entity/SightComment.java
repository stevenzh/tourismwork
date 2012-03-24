package com.opentravelsoft.entity;

import com.opentravelsoft.model.BaseObject;

/**
 * 景点说明
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:32 $
 */
public class SightComment extends BaseObject
{
    private static final long serialVersionUID = -2620484040187630810L;

    private String recNo;

    private String districtNo;

    private String tital;

    private String note;

    public SightComment()
    {
    }

    public SightComment(String recNo)
    {
        this.recNo = recNo;
    }

    public SightComment(String recNo, String districtNo, String tital,
            String note)
    {
        this.recNo = recNo;
        this.districtNo = districtNo;
        this.tital = tital;
        this.note = note;
    }

    public String getRecNo()
    {
        return this.recNo;
    }

    public void setRecNo(String recNo)
    {
        this.recNo = recNo;
    }

    public String getDistrictNo()
    {
        return this.districtNo;
    }

    public void setDistrictNo(String districtNo)
    {
        this.districtNo = districtNo;
    }

    public String getTital()
    {
        return this.tital;
    }

    public void setTital(String tital)
    {
        this.tital = tital;
    }

    public String getNote()
    {
        return this.note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }

    @Override
    public String toString()
    {
        return null;
    }

    @Override
    public boolean equals(Object o)
    {
        return false;
    }

    @Override
    public int hashCode()
    {
        return 0;
    }

}
