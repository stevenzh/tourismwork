package com.opentravelsoft.test.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

import org.apache.commons.lang.time.DateUtils;

public class DataUtilTest extends TestCase
{

    public void testSDB()
    {
        Date newDate = DateUtils.addDays(new Date(), 1);
        System.out.println(newDate);
        assertNotNull(newDate);
    }

    public void testSDF()
    {
        SimpleDateFormat SDF = new SimpleDateFormat("HH:mm");
        Date abc = null;
        try
        {
            abc = SDF.parse("23:15");
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        System.out.println(abc);
    }

    public void testMill()
    {
        Calendar cal = Calendar.getInstance();
        String da = "2007-03-28 10:03:57";
        int sen = 1175047974;
        int MILLIS_IN_DAY = 24 * 60 * 60;
        int MILLIS_IN_HOUR = 60 * 60;
        int MILLIS_IN_MINUTE = 60;

        int day = sen / MILLIS_IN_DAY;
        System.out.println(day);
        int tmp = sen - day * MILLIS_IN_DAY;
        System.out.println(tmp);
        int hour = tmp / MILLIS_IN_HOUR;
        System.out.println(hour);
        tmp = tmp - hour * MILLIS_IN_HOUR;
        System.out.println(tmp);

    }

    public static double round(double value, int decimalPlace)
    {
        double power_of_ten = 1;
        while (decimalPlace-- > 0)
            power_of_ten *= 10.0;
        return Math.round(value * power_of_ten) / power_of_ten;
    }

}
