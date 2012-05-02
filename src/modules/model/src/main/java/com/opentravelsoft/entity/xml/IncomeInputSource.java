package com.opentravelsoft.entity.xml;

import org.xml.sax.InputSource;

import com.opentravelsoft.entity.finance.Income;

/**
 * 
 * @author zhangst
 * 
 */
public class IncomeInputSource extends InputSource
{
    private Income income;

    public IncomeInputSource(Income income)
    {
        this.income = income;
    }

    public Income getIncome()
    {
        return income;
    }

    public void setIncome(Income income)
    {
        this.income = income;
    }

}
