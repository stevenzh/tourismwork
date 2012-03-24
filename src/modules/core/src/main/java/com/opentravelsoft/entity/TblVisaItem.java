package com.opentravelsoft.entity;

public class TblVisaItem implements java.io.Serializable
{
    private static final long serialVersionUID = -4550879016140890320L;

    private int visaitemId;

    private String recNo;

    /** 签证材料模板ID */
    private Integer itemId;

    /** 辅助说明 */
    private String outline;

    private int num;

    /** 排序 */
    private Integer sortOrder;

    private String del;

    public TblVisaItem()
    {
    }

    public TblVisaItem(int visaitemId, String recNo, int num)
    {
        this.visaitemId = visaitemId;
        this.recNo = recNo;
        this.num = num;
    }

    public int getVisaitemId()
    {
        return this.visaitemId;
    }

    public void setVisaitemId(int visaitemId)
    {
        this.visaitemId = visaitemId;
    }

    public String getRecNo()
    {
        return this.recNo;
    }

    public void setRecNo(String recNo)
    {
        this.recNo = recNo;
    }

    public Integer getItemId()
    {
        return this.itemId;
    }

    public void setItemId(Integer itemId)
    {
        this.itemId = itemId;
    }

    public String getOutline()
    {
        return this.outline;
    }

    public void setOutline(String outline)
    {
        this.outline = outline;
    }

    public int getNum()
    {
        return this.num;
    }

    public void setNum(int num)
    {
        this.num = num;
    }

    public String getDel()
    {
        return this.del;
    }

    public void setDel(String del)
    {
        this.del = del;
    }

    public Integer getSortOrder()
    {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder)
    {
        this.sortOrder = sortOrder;
    }
}
