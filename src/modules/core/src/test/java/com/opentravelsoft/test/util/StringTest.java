package com.opentravelsoft.test.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;

import com.opentravelsoft.util.MD5;
import com.opentravelsoft.util.RowDataUtil;

import junit.framework.TestCase;

public class StringTest extends TestCase
{
    private static final String ENCODEING = "GBK";

    public void testString()
    {
        // String rlt = "asbc\r\nasdfasd\r\nasdf";
        // String[] sb = new String(rlt).split("\r\n");

        boolean dk = Pattern.matches("[\\w]*", "TT564d");

        assertTrue(dk);
    }

    public void testsplit()
    {
        String shortName = "昆明 大理 丽江 泸沽湖东方女儿国双飞八日";
        byte[] byts;
        try
        {
            byts = shortName.getBytes(ENCODEING);
            if (byts.length > 30)
                shortName = new String(byts, 0, 30, ENCODEING);

            System.out.println(shortName.charAt(shortName.length() - 1));
            if (shortName.charAt(shortName.length() - 1) < 0)
            {
                System.out.println("errow");
            }
        } catch (UnsupportedEncodingException e)
        {
            System.out.println("errow");
        }
    }

    public void testRandom()
    {
        Random rand = new Random();
        String s = String.valueOf(rand.nextFloat());
        System.out.println(s.substring(2, 8));
    }

    public void testAge()
    {
        System.out.println("1977-10-29" + getAge("1977-10-29"));
        System.out.println("2008-3-1" + getAge("2008-3-1"));
        System.out.println("2008-2-1" + getAge("2008-2-1"));

    }

    public void testMd5()
    {
        String[] args = { "gingkgo" };
        MD5 md5 = new MD5();
        if (Array.getLength(args) == 0)
        {
            System.out.println("MD5 Test suite:");
            System.out.println("MD5(\"\"):" + md5.getMD5ofStr(""));
            System.out.println("MD5(\"a\"):" + md5.getMD5ofStr("a"));
            System.out.println("MD5(\"abc\"):" + md5.getMD5ofStr("abc"));
            System.out.println("MD5(\"message digest\"):"
                    + md5.getMD5ofStr("message digest"));
            System.out.println("MD5(\"abcdefghijklmnopqrstuvwxyz\"):"
                    + md5.getMD5ofStr("abcdefghijklmnopqrstuvwxyz"));
            System.out
                    .println("MD5(\"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789\"):"
                            + md5
                                    .getMD5ofStr("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"));
        } else
        {
            System.out.println("MD5(" + args[0] + ")="
                    + md5.getMD5ofStr(args[0]));
        }

    }

    private int getAge(String ddd)
    {
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

        Calendar cal = Calendar.getInstance();

        Date rand = null;
        try
        {
            rand = SDF.parse(ddd);
        } catch (ParseException e)
        {
            e.printStackTrace();
        }

        cal.setTime(new Date());
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(RowDataUtil.getDate(rand));

        int age = year - cal.get(Calendar.YEAR) - 1;
        if (month > cal.get(Calendar.MONTH) || month == cal.get(Calendar.MONTH)
                && day > cal.get(Calendar.DAY_OF_MONTH))
        {
            age = age + 1;
        }
        return age;
    }

    public void testsplit2()
    {
        String abc = "sldf_k";
        String[] sss = abc.split("_");
        for (String string : sss)
        {
            System.out.println(string);
        }
    }
}
