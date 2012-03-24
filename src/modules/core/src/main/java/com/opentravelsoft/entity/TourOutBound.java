package com.opentravelsoft.entity;

import java.util.Date;

/**
 * 团队出境信息
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:32 $
 */
public class TourOutBound implements java.io.Serializable
{

    private static final long serialVersionUID = 5952346129336850716L;

    private int id;

    private String type;

    private String planNo;

    private String text1;

    private String text2;

    private String text3;

    private String text4;

    private String text5;

    private String opuser;

    private Date opdate;

    private String tourNo;

    public TourOutBound()
    {
    }

    public TourOutBound(int id)
    {
        this.id = id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    public String getPlanNo()
    {
        return planNo;
    }

    public void setPlanNo(String planNo)
    {
        this.planNo = planNo;
    }

    public String getText1()
    {
        return this.text1;
    }

    public void setText1(String text1)
    {
        this.text1 = text1;
    }

    public String getText2()
    {
        return this.text2;
    }

    public void setText2(String text2)
    {
        this.text2 = text2;
    }

    public String getText3()
    {
        return this.text3;
    }

    public void setText3(String text3)
    {
        this.text3 = text3;
    }

    public String getText4()
    {
        return this.text4;
    }

    public void setText4(String text4)
    {
        this.text4 = text4;
    }

    public String getText5()
    {
        return this.text5;
    }

    public void setText5(String text5)
    {
        this.text5 = text5;
    }

    public String getOpuser()
    {
        return this.opuser;
    }

    public void setOpuser(String opuser)
    {
        this.opuser = opuser;
    }

    public Date getOpdate()
    {
        return this.opdate;
    }

    public void setOpdate(Date opdate)
    {
        this.opdate = opdate;
    }

    public String getTourNo()
    {
        return tourNo;
    }

    public void setTourNo(String tourNo)
    {
        this.tourNo = tourNo;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public void setShowStr(String string)
    {
    }

    public void setShowId(String string)
    {
    }

}
