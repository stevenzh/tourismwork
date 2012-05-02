package com.opentravelsoft.entity;

/**
 * 线路所需办理签证
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:33 $
 */
public class LineVisa implements java.io.Serializable
{
    private static final long serialVersionUID = -2340581556614913495L;

    private int refNo;

    private String recNo;

    /** 国家 */
    private String countryCode;

    /** 国家 */
    private String countryName;

    /** 项目 */
    private String item;

    /** 单位 */
    private String unit;

    /** 特色描述 */
    private String description;

    private String checked;

    private LineVisaId id;

    public LineVisa(LineVisaId id)
    {
        this.id = id;
    }

    public LineVisaId getId()
    {
        return this.id;
    }

    public void setId(LineVisaId id)
    {
        this.id = id;
    }

    public LineVisa()
    {
        checked = "false";
    }

    public String getItem()
    {
        return item;
    }

    public void setItem(String item)
    {
        this.item = item;
    }

    public String getRecNo()
    {
        return recNo;
    }

    public void setRecNo(String recNo)
    {
        this.recNo = recNo;
    }

    public String getCountryCode()
    {
        return countryCode;
    }

    public void setCountryCode(String countryCode)
    {
        this.countryCode = countryCode;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getCountryName()
    {
        return countryName;
    }

    public void setCountryName(String countryName)
    {
        this.countryName = countryName;
    }

    public String getUnit()
    {
        return unit;
    }

    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    public int getRefNo()
    {
        return refNo;
    }

    public void setRefNo(int refNo)
    {
        this.refNo = refNo;
    }

    public String getChecked()
    {
        return checked;
    }

    public void setChecked(String checked)
    {
        this.checked = checked;
    }

}
