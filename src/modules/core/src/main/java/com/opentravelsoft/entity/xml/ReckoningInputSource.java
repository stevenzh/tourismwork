package com.opentravelsoft.entity.xml;

import org.xml.sax.InputSource;

import com.opentravelsoft.entity.finance.Reckoning;

/**
 * 帐单
 * 
 * @author zhangst
 */
public class ReckoningInputSource extends InputSource
{
    private Reckoning reckoning;

    public ReckoningInputSource(Reckoning reckoning)
    {
        this.reckoning = reckoning;
    }

    public Reckoning getReckoning()
    {
        return reckoning;
    }

    public void setReckoning(Reckoning reckoning)
    {
        this.reckoning = reckoning;
    }

}
