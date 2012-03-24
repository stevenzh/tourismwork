package com.opentravelsoft.entity;

import com.opentravelsoft.model.BaseObject;

/**
 * 景点
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:34 $
 */
public class Sight extends BaseObject
{
    private static final long serialVersionUID = 3779481461420419282L;

    /** 景点编号 */
    private String sightNo;

    /** 景点中文名称 */
    private String name;

    /** 景点英文名称 */
    private String enName;

    /** 景点中文概况 */
    private String cnNote;

    /** 景点英文概况 */
    private String enNote;

    /** 景点首图文件地址 */
    private String headAdd;

    /** 景点地图文件地址 */
    private String mapAdd;

    /** 景区 */
    private District district;

    /** 景点分类 */
    private String sightType;

    /** 景点分类名称 */
    private String sightTypeName;

    /** 国家/地区 */
    private Country country;

    /** 所在省 */
    private Province province;

    /** 城市 */
    private String cityId;

    // -------------------------------------------------------------------------

    private String checked;

    public Sight()
    {
        this.district = new District();
        this.country = new Country();
        this.province = new Province();
        this.country.setCountryId("CN");
    }

    public String getSightTypeName()
    {
        return sightTypeName;
    }

    public void setSightTypeName(String sightTypeName)
    {
        this.sightTypeName = sightTypeName;
    }

    public District getDistrict()
    {
        return district;
    }

    public void setDistrict(District district)
    {
        this.district = district;
    }

    public String getHeadAdd()
    {
        return headAdd;
    }

    public void setHeadAdd(String headAdd)
    {
        this.headAdd = headAdd;
    }

    public String getMapAdd()
    {
        return mapAdd;
    }

    public void setMapAdd(String mapAdd)
    {
        this.mapAdd = mapAdd;
    }

    public String getSightNo()
    {
        return sightNo;
    }

    public void setSightNo(String sightNo)
    {
        this.sightNo = sightNo;
    }

    public String getSightType()
    {
        return sightType;
    }

    public void setSightType(String sightType)
    {
        this.sightType = sightType;
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

    public String getCityId()
    {
        return cityId;
    }

    public void setCityId(String cityId)
    {
        this.cityId = cityId;
    }

    public String getChecked()
    {
        return checked;
    }

    public void setChecked(String checked)
    {
        this.checked = checked;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String cnName)
    {
        this.name = cnName;
    }

    public String getEnName()
    {
        return this.enName;
    }

    public void setEnName(String enName)
    {
        this.enName = enName;
    }

    public String getCnNote()
    {
        return this.cnNote;
    }

    public void setCnNote(String cnNote)
    {
        this.cnNote = cnNote;
    }

    public String getEnNote()
    {
        return this.enNote;
    }

    public void setEnNote(String enNote)
    {
        this.enNote = enNote;
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
