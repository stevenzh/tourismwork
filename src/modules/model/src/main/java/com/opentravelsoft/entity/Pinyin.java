package com.opentravelsoft.entity;

import java.util.Date;


/**
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:32 $
 */
public class Pinyin extends BaseObject
{

    private static final long serialVersionUID = 6385971327502988770L;

    private String chinese;

    private String english;

    private String telexcd;

    public Pinyin()
    {
    }

    public Pinyin(String chinese, String english, String telexcd)
    {
        this.chinese = chinese;
        this.english = english;
        this.telexcd = telexcd;
    }

    public Pinyin(String chinese, String english, String telexcd,
            Character opKey, String opUser, Date opDate)
    {
        this.chinese = chinese;
        this.english = english;
        this.telexcd = telexcd;
    }

    public String getChinese()
    {
        return this.chinese;
    }

    public void setChinese(String chinese)
    {
        this.chinese = chinese;
    }

    public String getEnglish()
    {
        return this.english;
    }

    public void setEnglish(String english)
    {
        this.english = english;
    }

    public String getTelexcd()
    {
        return this.telexcd;
    }

    public void setTelexcd(String telexcd)
    {
        this.telexcd = telexcd;
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
