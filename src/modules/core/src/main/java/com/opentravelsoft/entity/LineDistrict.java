package com.opentravelsoft.entity;

/**
 * 途径目的地
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:32 $
 */
public class LineDistrict implements java.io.Serializable
{
    private static final long serialVersionUID = -5415071412021743928L;

    private LineDistrictId id;

    public LineDistrict()
    {
    }

    public LineDistrict(LineDistrictId id)
    {
        this.id = id;
    }

    public LineDistrictId getId()
    {
        return this.id;
    }

    public void setId(LineDistrictId id)
    {
        this.id = id;
    }

}
