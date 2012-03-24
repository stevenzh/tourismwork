package com.opentravelsoft.entity;

/**
 * 景点图片
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:32 $
 */
public class SightPhoto implements java.io.Serializable
{
    private static final long serialVersionUID = 7486123945961000475L;

    private String recNo;

    private String sightNo;

    private String mapAddress;

    private String note;

    public SightPhoto()
    {
    }

    public SightPhoto(String recNo)
    {
        this.recNo = recNo;
    }

    public SightPhoto(String recNo, String sightNo, String mapAddress,
            String note)
    {
        this.recNo = recNo;
        this.sightNo = sightNo;
        this.mapAddress = mapAddress;
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

    public String getSightNo()
    {
        return this.sightNo;
    }

    public void setSightNo(String sightNo)
    {
        this.sightNo = sightNo;
    }

    public String getMapAddress()
    {
        return this.mapAddress;
    }

    public void setMapAddress(String mapAddress)
    {
        this.mapAddress = mapAddress;
    }

    public String getNote()
    {
        return this.note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }

}
