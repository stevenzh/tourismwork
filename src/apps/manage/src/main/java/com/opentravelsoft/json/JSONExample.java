package com.opentravelsoft.json;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.Action;

public class JSONExample
{
    private String field1 = "str";

    private int[] ints = { 10, 20 };

    private Map<String, String> map = new HashMap<String, String>();

    private String customName = "custom";

    // 'transient' fields are not serialized
    private transient String field2;

    // fields without getter method are not serialized
    private String field3;

    public String execute()
    {
        map.put("John", "Galt");
        return Action.SUCCESS;
    }

    public String getField1()
    {
        return field1;
    }

    public void setField1(String field1)
    {
        this.field1 = field1;
    }

    public int[] getInts()
    {
        return ints;
    }

    public void setInts(int[] ints)
    {
        this.ints = ints;
    }

    public Map<String, String> getMap()
    {
        return map;
    }

    public void setMap(Map<String, String> map)
    {
        this.map = map;
    }

    @JSON(name = "newName")
    public String getCustomName()
    {
        return this.customName;
    }
}
