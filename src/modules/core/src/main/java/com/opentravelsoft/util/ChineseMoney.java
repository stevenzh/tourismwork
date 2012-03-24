package com.opentravelsoft.util;

import java.text.DecimalFormat;

public class ChineseMoney
{
    protected DecimalFormat DF = new DecimalFormat("#0.00");

    public static String getChineseMoney(String num)
    {
        ChineseMoney cn = new ChineseMoney();
        return cn.numToChn(num);
    }

    public static String getUpperMoney(double money)
    {
        ChineseMoney cn = new ChineseMoney();
        return cn.getMoneyChinese(money);
    }

    /**
     * 转换数字
     * 
     * @param x
     * @return
     */
    private char charToNum(char x)
    {
        String StringChnNames = "零一二三四五六七八九";
        String StringNumNames = "0123456789";
        return StringChnNames.charAt(StringNumNames.indexOf(x));
    }

    /**
     * 转换万以下整数
     * 
     * @param x
     * @return
     */
    private String wanStrToInt(String x)
    {
        String[] StringArrayLevelNames = { "", "十", "百", "千" };
        String ret = "";
        int i;
        for (i = x.length() - 1; i >= 0; i--)
            if (x.charAt(i) == '0')
                ret = charToNum(x.charAt(i)) + ret.toString();
            else
                ret = charToNum(x.charAt(i))
                        + StringArrayLevelNames[x.length() - 1 - i] + ret;

        while ((i = ret.indexOf("零零")) != -1)
            // ret = ret.Remove(i, 1);
            ret = ret.substring(0, i) + ret.substring(i + 1);

        if (ret.charAt(ret.length() - 1) == '零' && ret.length() > 1)
            // ret = ret.Remove(ret.length() - 1, 1);
            ret = ret.substring(0, ret.length() - 1)
                    + ret.substring(ret.length());

        if (ret.length() >= 2 && ret.substring(0, 2).equals("一十"))
            ret = ret.substring(1);
        return ret;
    }

    /**
     * 转换整数
     * 
     * @param x
     * @return
     */
    private String strToInt(String x)
    {
        int len = x.length();
        String ret, temp;
        if (len <= 4)
            ret = wanStrToInt(x);
        else if (len <= 8)
        {
            ret = wanStrToInt(x.substring(0, len - 4)) + "万";
            temp = wanStrToInt(x.substring(len - 4, 4));
            if (temp.indexOf("千") == -1 && temp.length() != 0)
                ret += "零" + temp;
            else
                ret += temp;
        } else
        {
            ret = wanStrToInt(x.substring(0, len - 8)) + "亿";
            temp = wanStrToInt(x.substring(len - 8, 4));
            if (temp.indexOf("千") == -1 && temp.length() != 0)
                ret += "零" + temp;
            else
                ret += temp;
            ret += "万";
            temp = wanStrToInt(x.substring(len - 4, 4));
            if (temp.indexOf("千") == -1 && temp.length() != 0)
                ret += "零" + temp;
            else
                ret += temp;
        }
        int i;

        if ((i = ret.indexOf("零万")) != -1)
            // ret = ret.Remove(i + 1, 1);
            ret = ret.substring(0, i + 1) + ret.substring(i + 2);
        while ((i = ret.indexOf("零零")) != -1)
            // ret = ret.Remove(i, 1);
            ret = ret.substring(0, i) + ret.substring(i + 1);
        if (ret.charAt(ret.length() - 1) == '零' && ret.length() > 1)
            // ret = ret.Remove(ret.length() - 1, 1);
            ret = ret.substring(0, ret.length() - 1)
                    + ret.substring(ret.length());
        return ret;
    }

    /**
     * 转换小数
     * 
     * @param x
     * @return
     */
    private String strToDouble(String x)
    {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < x.length(); i++)
            ret.append(charToNum(x.charAt(i)));
        return ret.toString();
    }

    private String numToChn(String x)
    {
        if (x.length() == 0)
            return "";
        StringBuilder ret = new StringBuilder();
        if (x.charAt(0) == '-')
        {
            ret = new StringBuilder("负");
            x = x.substring(1);
        }
        if (x.charAt(0) == '.')
            x = "0" + x;
        if (x.charAt(x.length() - 1) == '.')
            // x = x.Remove(x.length() - 1, 1);
            x = x.substring(0, x.length() - 1) + x.substring(x.length());
        if (x.indexOf(".") > -1)
            ret.append(strToInt(x.substring(0, x.indexOf("."))) + "点"
                    + strToDouble(x.substring(x.indexOf(".") + 1)));
        else
            ret.append(strToInt(x));
        return ret.toString();
    }

    /**
     * 
     * @param money
     * @return
     */
    private String getMoneyChinese(Double money)
    {
        int i;
        String mstrSource;

        // return null
        if (null == money || money == 0)
            return "";

        mstrSource = DF.format(money);
        i = mstrSource.indexOf(".");
        if (i > 0)
        {
            mstrSource = mstrSource.replace(".", "");
        }
        if (mstrSource.substring(0, 1).equals("0"))
        {
            mstrSource = mstrSource.substring(1);
        }

        // 
        mstrSource = NumstrToChinese(mstrSource);
        if (mstrSource.length() == 0)
        {
            return "";
        }

        // 负
        if (money < 0)
        {
            mstrSource = "负" + mstrSource;
        }

        mstrSource = mstrSource.replace("0", "零");
        mstrSource = mstrSource.replace("1", "壹");
        mstrSource = mstrSource.replace("2", "贰");
        mstrSource = mstrSource.replace("3", "叁");
        mstrSource = mstrSource.replace("4", "肆");
        mstrSource = mstrSource.replace("5", "伍");
        mstrSource = mstrSource.replace("6", "陆");
        mstrSource = mstrSource.replace("7", "柒");
        mstrSource = mstrSource.replace("8", "捌");
        mstrSource = mstrSource.replace("9", "玖");
        mstrSource = mstrSource.replace("M", "亿");
        mstrSource = mstrSource.replace("W", "万");
        mstrSource = mstrSource.replace("S", "仟");
        mstrSource = mstrSource.replace("H", "佰");
        mstrSource = mstrSource.replace("T", "拾");
        mstrSource = mstrSource.replace("Y", "圆");
        mstrSource = mstrSource.replace("J", "角");
        mstrSource = mstrSource.replace("F", "分");

        if (mstrSource.charAt(mstrSource.length() - 1) != '分')
        {
            mstrSource = mstrSource + "整";
        }

        return mstrSource;
    }

    /**
     * 金额转换
     * 
     * @param numstr
     * @return
     */
    private String NumstrToChinese(String numstr)
    {
        int i;
        int j;
        char mstrChar;
        String[] mstrFlag = new String[4];
        String mstrReturn = "";
        boolean mblnAddzero = false;

        mstrFlag[0] = "";
        // 拾
        mstrFlag[1] = "T";
        // 佰
        mstrFlag[2] = "H";
        // 仟
        mstrFlag[3] = "S";

        for (i = 1; i <= numstr.length(); i++)
        {
            j = numstr.length() - i;
            mstrChar = numstr.charAt(i - 1);

            if (mstrChar == '-' && j > 1)
            {
                mstrReturn = "";
            }

            if (mstrChar != '0' && j > 1 && mstrChar != '-')
            {
                mstrReturn += mstrChar + mstrFlag[(j - 2) % 4];
            }
            if (mstrChar == '0' && mblnAddzero == false)
            {
                mstrReturn += "0";
                mblnAddzero = true;
            }

            // 元
            if (j == 2)
            {
                if (mstrReturn.endsWith("0"))
                {
                    mstrReturn = mstrReturn.substring(0,
                            mstrReturn.length() - 1)
                            + "Y0";
                } else
                {
                    mstrReturn += "Y";
                }
            }

            // 万
            if (j == 6)
            {
                if (mstrReturn.length() > 2)
                {
                    if (!mstrReturn.endsWith("M0"))
                    {
                        if (mstrReturn.endsWith("0"))
                        {
                            mstrReturn = mstrReturn.substring(0, mstrReturn
                                    .length() - 1)
                                    + "W0";
                        } else
                        {
                            mstrReturn += "W";
                        }
                    }
                } else
                {
                    if (mstrReturn.endsWith("0"))
                    {
                        mstrReturn = mstrReturn.substring(0, mstrReturn
                                .length() - 1)
                                + "W0";
                    } else
                    {
                        mstrReturn += "W";
                    }
                }
            }

            // 亿
            if (j == 10)
            {
                if (mstrReturn.endsWith("0"))
                {
                    mstrReturn = mstrReturn.substring(0,
                            mstrReturn.length() - 1)
                            + "M0";
                } else
                {
                    mstrReturn += "M";
                }
            }

            // 万（亿）
            if (j == 14)
            {
                if (mstrReturn.endsWith("0"))
                {
                    mstrReturn = mstrReturn.substring(0,
                            mstrReturn.length() - 1)
                            + "W0";
                } else
                {
                    mstrReturn += "W";
                }
            }

            // 分
            if (j == 0 && mstrChar != '0')
            {
                mstrReturn += mstrChar + "F";
            }

            // 角
            if (j == 1 && mstrChar != '0')
            {
                mstrReturn += mstrChar + "J";
            }

            if (mstrChar != '0')
            {
                mblnAddzero = false;
            }
        }

        // 去掉十前面的一
        // if (mstrReturn.charAt(0) == '1' && mstrReturn.charAt(1) == 'T')
        // {
        // mstrReturn = mstrReturn.substring(1);
        // }
        if (mstrReturn.endsWith("0"))
        {
            mstrReturn = mstrReturn.substring(0, mstrReturn.length() - 1);
        }
        if (mstrReturn.charAt(0) == '0')
        {
            mstrReturn = mstrReturn.substring(1);
        }

        if (mstrReturn.endsWith("M") || mstrReturn.endsWith("W")
                || mstrReturn.endsWith("S") || mstrReturn.endsWith("H")
                || mstrReturn.endsWith("T"))
        {
            mstrReturn = mstrReturn + "Y";
        }
        return mstrReturn;
    }
}
