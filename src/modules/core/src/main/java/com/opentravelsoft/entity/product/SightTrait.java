package com.opentravelsoft.entity.product;

/**
 * 景区
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:33 $
 */
public class SightTrait
{
    private int id;

    /** 记录号 */
    private String recNo;

    /** 景区/景点代号 */
    private String sightNo;

    /** 图文件地址 */
    private String mapAddress;

    /** 图片说明 */
    private String note;

    public String getRecNo()
    {
        return recNo;
    }

    public void setRecNo(String recNo)
    {
        this.recNo = recNo;
    }

    public String getSightNo()
    {
        return sightNo;
    }

    public void setSightNo(String sightNo)
    {
        this.sightNo = sightNo;
    }

    public String getMapAddress()
    {
        return mapAddress;
    }

    public void setMapAddress(String mapAddress)
    {
        this.mapAddress = mapAddress;
    }

    public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
}