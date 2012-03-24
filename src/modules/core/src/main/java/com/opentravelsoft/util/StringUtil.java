package com.opentravelsoft.util;

public class StringUtil
{

    /**
     * Check that the given String is neither <code>null</code> nor of length
     * 0. Note: Will return <code>true</code> for a String that purely
     * consists of whitespace.
     * <p>
     * 
     * <pre>
     * StringUtils.hasLength(null) = false
     * StringUtils.hasLength(&quot;&quot;) = false
     * StringUtils.hasLength(&quot; &quot;) = true
     * StringUtils.hasLength(&quot;Hello&quot;) = true
     * </pre>
     * 
     * @param str the String to check (may be <code>null</code>)
     * @return <code>true</code> if the String is not null and has length
     * @see #hasText(String)
     */
    public static boolean hasLength(String str)
    {
        return (str != null && str.length() > 0);
    }

    /**
     * 数値を指定桁数まで左側 0 パディングし，文字列として返却する．<BR/>
     * 
     * @param target パディング対象の数値
     * @param length パディング後の桁数
     * @return パディング後の文字列
     */
    public static String padding(long target, int length)
    {

        return padding(String.valueOf(target), length);
    }

    /**
     * 文字列を指定桁数まで左側 0 パディングする．<BR/>
     * 
     * @param target パディング対象の文字列
     * @param length パディング後の桁数
     * @return パディング後の文字列
     */
    public static String padding(String target, int length)
    {

        return padding(target, length, (char) '0');
    }

    /**
     * 文字列を指定桁数まで左パディングしする．<BR/>
     * 
     * @param target パディング対象文字列
     * @param length パディング語の桁数
     * @param paddingChar パディングに使用する文字
     * @return パディング後の文字
     */
    public static String padding(String target, int length, char paddingChar)
    {

        // パディングする必要がない場合は そのまま返却
        if (target.length() >= length)
        {
            return target;
        }

        // 対象の数を文字列に変換
        StringBuffer ret = new StringBuffer(target);

        // パディング処理
        while (ret.length() < length)
        {
            // 文字列の先頭に追加
            ret.insert(0, paddingChar);
        }

        return ret.toString();
    }

    // *************************************************************************
    // * change char "," to ", " and "\"" to "\"\"" and add " "
    // * @param String strInput
    // * @return String strOutput
    // * @exception
    // * @see
    // *************************************************************************
    public static String encodeCSVData(String strOld)
    {
        if (strOld == null || strOld.length() == 0)
            return "";

        String strNew = "";
        int iCheck = 0;
        char ch;

        for (int i = 0; i < strOld.length(); i++)
        {
            ch = strOld.charAt(i);

            switch (ch)
            {
            case ',':
                iCheck = 1;
                strNew += ch;
                break;
            case '"':
                iCheck = 1;
                strNew += "\"\"";
                break;
            default:
                strNew += ch;
            }

        }
        if (iCheck == 1)
        {
            strNew = "\"" + strNew + "\"";
        }
        if ((strNew.indexOf("\r") >= 0) || (strNew.indexOf("\n") >= 0))
        {
            // 改行コードが含まれるときは「"」で囲む
            strNew = "\"" + strNew + "\"";
        }
        return strNew;
    }

    // *************************************************************************
    // * change char "," to ", " and tab to "\9 " and "\"" to "\"\"" and add " "
    // * @param String strInput
    // * @return String strOutput
    // * @exception
    // * @see
    // *************************************************************************
    public static String encodeCSVData(String strOld, char cFlag)
    {
        if (strOld == null || strOld.length() == 0)
            return "";

        String strNew = "";
        int iCheck = 0;
        char ch;
        if (cFlag == ',')
        {
            return encodeCSVData(strOld);
        } else
        {
            for (int i = 0; i < strOld.length(); i++)
            {
                ch = strOld.charAt(i);

                switch (ch)
                {
                case (char) 9:
                    iCheck = 1;
                    strNew += ch;
                    break;
                case '"':
                    iCheck = 1;
                    strNew += "\"\"";
                    break;
                default:
                    strNew += ch;
                }

            }
            if (iCheck == 1)
            {
                strNew = "\"" + strNew + "\"";
            }

            return strNew;
        }
    }

    /**
     * Like 条件の文字列をエスケープします．
     * 
     * @param s 対象文字列
     * @return エスケープ後の文字列
     */
    public static String replaceLikeString(String s)
    {

        if (s == null)
            return "%";

        String ret = s;
        ret = ret.replaceAll("%", "\\\\%");
        ret = ret.replaceAll("_", "\\\\_");
        ret = "%" + ret + "%";

        return ret;
    }

    public static String SupplyLengthWithSpace(String strin, int nLength)
    {
        String strReturnValue = "";
        int nNum;

        nNum = nLength - strin.length();
        strReturnValue = strin;

        for (int i = 1; i <= nNum; i++)
        {
            strReturnValue = strReturnValue + " ";
        }

        return strReturnValue;
    }
}
