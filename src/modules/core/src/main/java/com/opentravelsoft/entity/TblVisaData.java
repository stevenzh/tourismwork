package com.opentravelsoft.entity;

public class TblVisaData implements java.io.Serializable
{

    private static final long serialVersionUID = -6889837542140989230L;

    private int itemId;

    private String name;

    private String unit;

    private String description;

    public TblVisaData()
    {
    }

    public TblVisaData(int itemId)
    {
        this.itemId = itemId;
    }

    public int getItemId()
    {
        return this.itemId;
    }

    public void setItemId(int itemId)
    {
        this.itemId = itemId;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getUnit()
    {
        return this.unit;
    }

    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

}
