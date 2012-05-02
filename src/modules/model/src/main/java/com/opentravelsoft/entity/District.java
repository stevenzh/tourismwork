package com.opentravelsoft.entity;

import com.opentravelsoft.model.BaseObject;

/**
 * 景区
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:33 $
 */
public class District extends BaseObject
{
    private static final long serialVersionUID = -8859715069196603360L;

    /** 景区编号 */
    private String districtNo;

    /** 景区中文名称 */
    private String cnName;

    /** 景区中文概况 */
    private String cnNote;

    /** 景区首图文件地址 */
    private String mapHead;

    /** 景区地图文件地址 */
    private String mapAddress;

    private String mapAddress1;

    private String mapAddress2;

    private Country country;

    private Province province;

    public District()
    {
        this.country = new Country();
        this.province = new Province();
        this.country.setCountryId("CN");
    }

    public District(String districtNo)
    {
        this();
        this.districtNo = districtNo;
    }

    public String getMapAddress1()
    {
        return this.mapAddress1;
    }

    public void setMapAddress1(String mapAddress1)
    {
        this.mapAddress1 = mapAddress1;
    }

    public String getMapAddress2()
    {
        return this.mapAddress2;
    }

    public void setMapAddress2(String mapAddress2)
    {
        this.mapAddress2 = mapAddress2;
    }

    public Country getCountry()
    {
        return country;
    }

    public void setCountry(Country country)
    {
        this.country = country;
    }

    public Province getProvince()
    {
        return province;
    }

    public void setProvince(Province province)
    {
        this.province = province;
    }

    public String getCnName()
    {
        return cnName;
    }

    public void setCnName(String cnName)
    {
        this.cnName = cnName;
    }

    public String getCnNote()
    {
        return cnNote;
    }

    public void setCnNote(String cnNote)
    {
        this.cnNote = cnNote;
    }

    public String getDistrictNo()
    {
        return districtNo;
    }

    public void setDistrictNo(String districtNo)
    {
        this.districtNo = districtNo;
    }

    public String getMapAddress()
    {
        return mapAddress;
    }

    public void setMapAddress(String mapAddress)
    {
        this.mapAddress = mapAddress;
    }

    public String getMapHead()
    {
        return mapHead;
    }

    public void setMapHead(String mapHead)
    {
        this.mapHead = mapHead;
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
