package com.opentravelsoft.entity.xml;

import org.xml.sax.InputSource;

import com.opentravelsoft.entity.finance.Outcome;

/**
 * 
 * @author zhangst
 * 
 */
public class OutcomeInputSource extends InputSource
{
    private Outcome outcome;

    public OutcomeInputSource(Outcome billhead)
    {
        this.outcome = billhead;
    }

    public Outcome getBillhead()
    {
        return outcome;
    }

    public void setBillhead(Outcome billhead)
    {
        this.outcome = billhead;
    }

}
