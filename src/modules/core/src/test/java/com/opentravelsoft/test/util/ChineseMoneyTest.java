package com.opentravelsoft.test.util;

import com.opentravelsoft.util.ChineseMoney;

import junit.framework.TestCase;

public class ChineseMoneyTest extends TestCase
{

    public void testGetUpperMoney()
    {
        ChineseMoney cm = new ChineseMoney();

        String strChineseMoney = cm.getUpperMoney(100000000.1f);
        System.out.println(strChineseMoney);
        assertTrue(true);
    }

}
