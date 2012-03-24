package com.opentravelsoft.util;

import java.sql.Clob;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.StringCharacterIterator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

import com.opentravelsoft.EbizException;

/**
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:41 $
 */
public class ConvertUtils
{
    private static final int DEF_DIV_SCALE = 30;

    /**
     * 
     * @param strText
     * @param strKey
     * @return
     * @throws EbizException
     */
    public static String DecryptHex(String strText, String strKey)
            throws EbizException
    {
        String outputString = "";
        try
        {
            byte[] ciphData = Hex.decodeHex(strText.toCharArray());

            SecretKeySpec sksSpec = new SecretKeySpec(strKey.getBytes(),
                    "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.DECRYPT_MODE, sksSpec);

            byte[] compData = cipher.doFinal(ciphData);

            outputString = new String(compData, 0, compData.length);
        } catch (Exception ex)
        {
            throw new EbizException(ex.getMessage());
        }
        return outputString;
    }

    /**
     * 
     * @param strText
     * @param strKey
     * @return
     * @throws EbizException
     */
    public static String EncryptHex(String strText, String strKey)
            throws EbizException
    {
        String strReturn = "";
        try
        {
            SecretKeySpec sksSpec = new SecretKeySpec(strKey.getBytes(),
                    "Blowfish");

            // 暗号化ロジックを暗号化モードでインスタンス化
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, sksSpec);

            // 暗号化実施
            byte[] ByteData = cipher.doFinal(strText.getBytes());

            char[] retVal = Hex.encodeHex(ByteData);

            strReturn = new String(retVal); // 暗号化文字列
        } catch (Exception e)
        {
            ;
        }
        return strReturn;
    }

    /**
     * 変換テーブル
     */
    private static final String convTbl[][] = {
            // 2文字構成(カナ)
            { "ｶﾞ", "ガ" },
            { "ｷﾞ", "ギ" },
            { "ｸﾞ", "グ" },
            { "ｹﾞ", "ゲ" },
            { "ｺﾞ", "ゴ" },
            { "ｻﾞ", "ザ" },
            { "ｼﾞ", "ジ" },
            { "ｽﾞ", "ズ" },
            { "ｾﾞ", "ゼ" },
            { "ｿﾞ", "ゾ" },
            { "ﾀﾞ", "ダ" },
            { "ﾁﾞ", "ヂ" },
            { "ﾂﾞ", "ヅ" },
            { "ﾃﾞ", "デ" },
            { "ﾄﾞ", "ド" },
            { "ﾊﾞ", "バ" },
            { "ﾋﾞ", "ビ" },
            { "ﾌﾞ", "ブ" },
            { "ﾍﾞ", "ベ" },
            { "ﾎﾞ", "ボ" },
            { "ﾊﾟ", "パ" },
            { "ﾋﾟ", "ピ" },
            { "ﾌﾟ", "プ" },
            { "ﾍﾟ", "ペ" },
            { "ﾎﾟ", "ポ" },
            { "ｳﾞ", "ヴ" },
            // 1文字構成(カナ)
            { "ｱ", "ア" }, { "ｲ", "イ" }, { "ｳ", "ウ" }, { "ｴ", "エ" },
            { "ｵ", "オ" }, { "ｶ", "カ" }, { "ｷ", "キ" }, { "ｸ", "ク" },
            { "ｹ", "ケ" }, { "ｺ", "コ" }, { "ｻ", "サ" }, { "ｼ", "シ" },
            { "ｽ", "ス" }, { "ｾ", "セ" }, { "ｿ", "ソ" }, { "ﾀ", "タ" },
            { "ﾁ", "チ" }, { "ﾂ", "ツ" }, { "ﾃ", "テ" }, { "ﾄ", "ト" },
            { "ﾅ", "ナ" }, { "ﾆ", "ニ" }, { "ﾇ", "ヌ" }, { "ﾈ", "ネ" },
            { "ﾉ", "ノ" },
            { "ﾊ", "ハ" },
            { "ﾋ", "ヒ" },
            { "ﾌ", "フ" },
            { "ﾍ", "ヘ" },
            { "ﾎ", "ホ" },
            { "ﾏ", "マ" },
            { "ﾐ", "ミ" },
            { "ﾑ", "ム" },
            { "ﾒ", "メ" },
            { "ﾓ", "モ" },
            { "ﾔ", "ヤ" },
            { "ﾕ", "ユ" },
            { "ﾖ", "ヨ" },
            { "ﾗ", "ラ" },
            { "ﾘ", "リ" },
            { "ﾙ", "ル" },
            { "ﾚ", "レ" },
            { "ﾛ", "ロ" },
            { "ﾜ", "ワ" },
            { "ｦ", "ヲ" },
            { "ﾝ", "ン" },
            { "ｧ", "ァ" },
            { "ｨ", "ィ" },
            { "ｩ", "ゥ" },
            { "ｪ", "ェ" },
            { "ｫ", "ォ" },
            { "ｬ", "ャ" },
            { "ｭ", "ュ" },
            { "ｮ", "ョ" },
            { "ｯ", "ッ" },
            // 英字記号
            { "1", "１" }, { "2", "２" }, { "3", "３" }, { "4", "４" },
            { "5", "５" }, { "6", "６" }, { "7", "７" }, { "8", "８" },
            { "9", "９" }, { "0", "０" }, { "-", "－" }, { "^", "＾" },
            { "\\", "￥" }, { "@", "＠" }, { "[", "［" }, { ";", "；" },
            { ":", "：" }, { "]", "］" }, { ",", "，" }, { ".", "．" },
            { "/", "／" }, { "!", "！" }, { "\"", "”" }, { "#", "＃" },
            { "$", "＄" }, { "%", "％" }, { "&", "＆" }, { "'", "’" },
            { "(", "（" }, { ")", "）" }, { "=", "＝" }, { "~", "～" },
            { "|", "｜" }, { "`", "‘" }, { "{", "｛" }, { "+", "＋" },
            { "*", "＊" }, { "}", "｝" }, { "<", "＜" }, { ">", "＞" },
            { "?", "？" }, { "_", "＿" }, { "A", "Ａ" }, { "B", "Ｂ" },
            { "C", "Ｃ" }, { "D", "Ｄ" }, { "E", "Ｅ" }, { "F", "Ｆ" },
            { "G", "Ｇ" }, { "H", "Ｈ" }, { "I", "Ｉ" }, { "J", "Ｊ" },
            { "K", "Ｋ" }, { "L", "Ｌ" }, { "M", "Ｍ" }, { "N", "Ｎ" },
            { "O", "Ｏ" }, { "P", "Ｐ" }, { "Q", "Ｑ" }, { "R", "Ｒ" },
            { "S", "Ｓ" }, { "T", "Ｔ" }, { "U", "Ｕ" }, { "V", "Ｖ" },
            { "W", "Ｗ" }, { "X", "Ｘ" }, { "Y", "Ｙ" }, { "Z", "Ｚ" },
            { "a", "ａ" }, { "b", "ｂ" }, { "c", "ｃ" }, { "d", "ｄ" },
            { "e", "ｅ" }, { "f", "ｆ" }, { "g", "ｇ" }, { "h", "ｈ" },
            { "i", "ｉ" }, { "j", "ｊ" }, { "k", "ｋ" }, { "l", "ｌ" },
            { "m", "ｍ" }, { "n", "ｎ" }, { "o", "ｏ" }, { "p", "ｐ" },
            { "q", "ｑ" }, { "r", "ｒ" }, { "s", "ｓ" }, { "t", "ｔ" },
            { "u", "ｕ" }, { "v", "ｖ" }, { "w", "ｗ" }, { "x", "ｘ" },
            { "y", "ｙ" }, { "z", "ｚ" },
            // 特殊記号
            { "｡", "。" }, { "｢", "「" }, { "｣", "」" }, { "､", "、" },
            { "･", "・" }, { "ｰ", "ー" } };

    // 全角数字
    // private static final String ZENKAKU_NUM = "０１２３４５６７８９．";
    // 半角数字
    private static final String HANKAKU_NUM = "01234567890.";

    // 半角英数字
    private static final String HANKAKU_ALPHA_NUM = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";

    // 半角カナ
    private static final String HANKAKU_KANA = "ｱｲｳｴｵｶｷｸｹｺｻｼｽｾｿﾀﾁﾂﾃﾄﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝｧｨｩｪｫｯｬｭｮﾞﾟｰ､｡｢｣･";

    // 10進数フォーマッター
    private static final DecimalFormat formatter = new DecimalFormat();

    /**
     * 半角->全角変換 <BR>
     * 半角文字列を全角文字列に変換する <BR>
     * [条件] <BR>
     * 1.半角英数字（特殊記号を含む） <BR>
     * 2.半角カナ文字
     * 
     * @param singleString 半角文字列
     * @return 全角変換後の文字列 <BR>
     *         変換できない文字はそのまま出力する
     */
    public static final String toDoubleWord(String singleString)
    {

        if (isNull(singleString))
        {
            return "";
        }

        StringBuffer strBuff = new StringBuffer();

        for (int i = 0; i < singleString.length(); i++)
        {

            // 半角カナマッチエントリ検索
            int j = 0;
            for (; j < convTbl.length; j++)
            {
                if (singleString.substring(i).startsWith(convTbl[j][0]))
                {
                    strBuff.append(convTbl[j][1]);
                    // 検索文字位置更新（2文字構成カナ文字を考慮）
                    i += convTbl[j][0].length() - 1;
                    break;
                }
            }
            // 対応文字がない場合もとにそのものを設定
            if (j >= convTbl.length)
            {
                strBuff.append(singleString.substring(i, i + 1));
            }
        }
        return strBuff.toString();
    }

    /**
     * 全角->半角文字列 <BR>
     * 全角文字列を半角文字列に変換する
     * 
     * @param doubleString 全角文字列
     * @return 半角文字列 <BR>
     *         ※変換できないない文字は、そのまま出力する
     */
    public static final String toSingleWord(String doubleString)
    {

        if (isNull(doubleString))
        {
            return "";
        }

        StringBuffer strBuff = new StringBuffer();

        for (int i = 0; i < doubleString.length(); i++)
        {

            // 半角カナマッチエントリ検索
            int j = 0;
            for (; j < convTbl.length; j++)
            {
                if (doubleString.substring(i).startsWith(convTbl[j][1]))
                {
                    strBuff.append(convTbl[j][0]);
                    break;
                }
            }
            // 対応文字がない場合もとにそのものを設定
            if (j >= convTbl.length)
            {
                strBuff.append(doubleString.substring(i, i + 1));
            }
        }
        return strBuff.toString();

    }

    /**
     * Null値チェック <BR>
     * null値(又は空文字)をチェックする
     * 
     * @param strValue チェック文字列
     * @return nullの場合True
     */
    public static boolean isNull(String strValue)
    {
        // パラメタがNULL(又は空文字)かチェック
        if (null == strValue || strValue.length() == 0)
            return true;
        return false;
    }

    /**
     * Null値変換
     * 
     * @param strin 変換文字列
     * @return nullの場合空文字
     */
    public static String RepNull(Object strin)
    {
        if (strin == null)
        {
            return "";
        } else
        {
            return strin.toString();
        }
    }

    /**
     * 入力文字列をタグ処理を行う
     * 
     * @param strBy 替わる後の文字列
     * @param strin 入力文字列
     * @param strRe 替わる前の文字列
     * @return 処理した文字列
     */
    public static String ReplaceFirst(String strin, String strRe, String strBy)
    {
        int iPos;
        String strTemp = "";
        iPos = strin.indexOf(strRe);
        if (iPos != -1)
        {
            strTemp = strTemp + strin.substring(0, iPos) + strBy;
            strin = strin.substring(iPos + strRe.length());
            iPos = strin.indexOf(strRe);
        }
        strin = strTemp + strin;
        return strin;
    }

    /**
     * 数値チェック <BR>
     * 数値文字列かチェックする。（全角文字を除く）
     * 
     * @param strValue チェック文字列
     * @return 数値である場合：true
     */
    public static boolean isNumeric(String strValue)
    {
        if (isNull(strValue))
        {
            return false;
        }

        // 数値変換のエラーありか？
        try
        {
            formatter.parse(strValue);
        } catch (ParseException e)
        {
            return false;
        } catch (NumberFormatException e)
        {
            return false;
        }

        // 全角数字の場合はfalse
        return (stringCheck(strValue, HANKAKU_NUM));

    }

    /**
     * 半角英数字チェック <BR>
     * 半角英数字かチェックする
     * 
     * @param strValue チェック文字列
     * @return 半角英数字の場合:true
     */
    public static boolean isAlphaNum(String strValue)
    {
        if (isNull(strValue))
        {
            return false;
        }

        return (stringCheck(strValue, HANKAKU_ALPHA_NUM));

    }

    /**
     * 全角文字チェック <BR>
     * 全角文字かチェックする
     * 
     * @param strValue チェック文字列
     * @return 全角文字の場合：true
     */
    public static boolean isZenkaku(String strValue)
    {
        if (isNull(strValue))
        {
            return false;
        }

        // 半角文字が含まれていないかチェック
        StringCharacterIterator stItr = new StringCharacterIterator(strValue);
        for (char c = stItr.first(); c != StringCharacterIterator.DONE; c = stItr
                .next())
        {
            if ((HANKAKU_ALPHA_NUM.indexOf((int) c) != -1)
                    || (HANKAKU_KANA.indexOf((int) c) != -1))
            {
                return false;
            }
        }
        return true;
    }

    private static boolean stringCheck(String checkStr, String BaseStr)
    {
        if (isNull(checkStr))
        {
            return false;
        }
        // パラメタ文字列が指定文字列のみかチェック
        StringCharacterIterator stItr = new StringCharacterIterator(checkStr);
        for (char c = stItr.first(); c != StringCharacterIterator.DONE; c = stItr
                .next())
        {
            if (-1 == BaseStr.indexOf((int) c))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * 文字列を返却する。
     * 
     * @param str 文字配列
     * @param strSpearater 分
     * @return 文字列
     */
    public static String getArrayString(String[] str, String strSpearater)
    {
        String strRet = "";
        if (str != null && str.length > 0)
        {
            StringBuffer strBuf = new StringBuffer();
            for (int i = 0; i < str.length; i++)
            {
                strBuf.append(str[i]);
                if (i != str.length - 1)
                {
                    strBuf.append(strSpearater);
                }
            }
            strRet = strBuf.toString();
        }
        return strRet;
    }

    /**
     * メソッド名： ReplaceSqlChar <BR>
     * 機能概要： 入力文字列をタグ処理を行う <BR>
     * "%"、"_" <BR>
     * <BR>
     * 引数： strin 入力文字列 <BR>
     * chEscape 指定したEscape文字 <BR>
     * 戻り値： 処理した文字列 <BR>
     */
    public static String ReplaceSqlLike(String strin, char chEscape)
    {

        String strEscape = String.valueOf(chEscape);

        strin = strin.replaceAll(strEscape, strEscape + strEscape);
        strin = strin.replaceAll("%", strEscape + "%");
        strin = strin.replaceAll("_", strEscape + "_");
        strin = strin.replaceAll("％", strEscape + "％");
        strin = strin.replaceAll("＿", strEscape + "＿");
        strin = ReplaceEncodeSQuote(strin);

        return strin;
    }

    /**
     * 入力文字列をタグ処理を行う "'" --> "''"
     * 
     * @param strin 入力文字列
     * @return 処理した文字列
     * @throws
     */
    public static String ReplaceEncodeSQuote(String strin)
    {
        if (strin == null)
        {
            return strin;
        }
        strin = strin.replaceAll("'", "''");
        return strin;
    }

    // ******************************************************
    // Oracleが判別可能な文字コードに変換 <BR>
    // ******************************************************
    public static String convertToOracle(String s)
    {
        char c;
        int len = s.length();
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < len; i++)
        {
            c = s.charAt(i);

            if (c == 0x2014)
            { // ―
                c = 0x2015;
            } else if (c == 0x301c)
            { // ～
                c = 0xff5e;
            } else if (c == 0x2016)
            { // ∥
                c = 0x2225;
            } else if (c == 0x2212)
            { // －
                c = 0xff0d;
            } else if (c == 0x00a2)
            { // ￠
                c = 0xffe0;
            } else if (c == 0x00a3)
            { // ￡
                c = 0xffe1;
            } else if (c == 0x00ac)
            { // ￢
                c = 0xffe2;
            }
            sb.append(c);
        }
        return new String(sb);
    }

    // ******************************************************
    // Oracleから受け取った文字を判別可能な文字コードに変換 <BR>
    // ******************************************************
    public static String convertFromOracle(String s)
    {
        if (s == null)
            return s;

        char c;
        int len = s.length();
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < len; i++)
        {
            c = s.charAt(i);

            if (c == 0x2015)
            { // ―
                c = 0x2014;
            } else if (c == 0xff5e)
            { // ～
                c = 0x301c;
            } else if (c == 0x2225)
            { // ∥
                c = 0x2016;
            } else if (c == 0xff0d)
            { // －
                c = 0x2212;
            } else if (c == 0xffe0)
            { // ￠
                c = 0x00a2;
            } else if (c == 0xffe1)
            { // ￡
                c = 0x00a3;
            } else if (c == 0xffe2)
            { // ￢
                c = 0x00ac;
            }

            sb.append(c);
        }

        return new String(sb);
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
        int iCheck = 1;
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
        int iCheck = 1;
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

    /***************************************************************************
     * メソッド名： SupplyLength 機能概要： 入力文字列を指定長さに変換
     * 
     * 引数： strin 入力文字列 nLength 指定長さ 戻り値： 処理した文字列
     **************************************************************************/
    public static String SupplyLength(String strin, int nLength)
    {

        String strReturnValue = "";
        int nNum;

        nNum = nLength - strin.length();
        strReturnValue = strin;

        for (int i = 1; i <= nNum; i++)
        {
            strReturnValue = "0" + strReturnValue;
        }

        return strReturnValue;
    }

    /***************************************************************************
     * ***************************************************<BR>
     * メソッド名： IsOverLength <BR>
     * 機能概要： 入力文字列の長さが指定長さを超えるか <BR>
     * どうかチェックする <BR>
     * <BR>
     * 引数： strin 入力文字列 <BR>
     * nLength 指定長さ <BR>
     * 戻り値： True： はい <BR>
     * False： いいえ <BR>
     **************************************************************************/
    public static boolean IsOverLength(String strin, int nLength)
    {

        try
        {
            byte[] b = strin.getBytes("UTF8");
            if (b.length > nLength)
                return true;
        } catch (Exception e)
        {

        }

        return false;
    }

    /***************************************************************************
     * ***************************************************<BR>
     * メソッド名： IsHalfNum <BR>
     * 機能概要： 半角数字であるかどうかチェックする <BR>
     * <BR>
     * 引数： strin 入力文字列 <BR>
     * 戻り値： True： はい <BR>
     * False： いいえ <BR>
     **************************************************************************/
    public static boolean IsHalfNum(String strin)
    {

        char c;
        for (int i = 0; i < strin.length(); i++)
        {
            c = strin.charAt(i);
            if (c < 0x0030 || c > 0x0039)
                return false;
        }
        return true;
    }

    /**
     * // カンマ付小数点桁数チェック. // カンマ付小数点桁数チェック //
     * 
     * @param obj オブジェクト //
     * @param len 小数点桁数 //
     * @return false:失敗 // true :成功
     */
    public static boolean checkKDecimalLen(String strValue, String strLen)
    {

        String strNum = "";
        if (strValue.indexOf(",") > 0)
        {
            String[] sizeObj = strValue.split(",");
            for (int i = 0; i < sizeObj.length; i++)
            {
                strNum = "" + strNum + sizeObj[i];
            }
        } else
        {
            strNum = strValue;
        }

        String strValue1 = strLen.substring(0, strLen.indexOf('.'));
        String strValue2 = strLen.substring(strLen.indexOf('.') + 1, strLen
                .length());
        String[] lenObject = { strValue1, strValue2 };
        if (strNum.indexOf(".") == -1)
        {
            if (strNum.length() <= Integer.parseInt(lenObject[0]))
            {
                return true;
            } else
            {
                return false;
            }
        } else
        {
            if (!textDecimalCheck(strNum.substring(0, strNum.indexOf(".")))
                    || !textDecimalCheck(strNum.substring(
                            strNum.indexOf(".") + 1, strNum.length())))
            {
                return false;
            }
            if (strNum.substring(0, strNum.indexOf(".")).length() <= Integer
                    .parseInt(lenObject[0])
                    && strNum.substring(strNum.indexOf(".") + 1,
                            strNum.length()).length() <= Integer
                            .parseInt(lenObject[1]))
            {
                return true;
            } else
            {
                return false;
            }
        }
    }

    /*
     * ------------------------------------------------------------- //
     * 小数点数値チェック // 小数点数値チェック // @param obj オブジェクト // @return false:失敗 // true
     * :成功
     */
    public static boolean textDecimalCheck(String strValue)
    {

        if (strValue.substring(0, 1) == "+")
        {
            return false;
        }
        if (strValue.substring(0, 1) == "-")
        {
            strValue = strValue.substring(1, strValue.length());
        }
        if (strValue.indexOf(".") == -1)
        {
            if (IsHalfNum(strValue))
            {
                return true;
            } else
            {
                return false;
            }
        } else if (strValue.indexOf(".") == 0
                || strValue.indexOf(".") == strValue.length() - 1)
        {
            return false;
        } else
        {
            strValue = strValue.replaceAll(".", "");
            if (IsHalfNum(strValue))
            {
                return true;
            } else
            {
                return false;
            }
        }
    }

    /***************************************************************************
     * メソッド名： SupplyLength 機能概要： 入力文字列を指定長さに変換
     * 
     * 引数： strin 入力文字列 nLength 指定長さ 戻り値： 処理した文字列
     **************************************************************************/
    public static String FillZeroTail(String strin, int nLength)
    {
        String strReturnValue = "";
        int nNum;
        nNum = nLength - strin.length();
        strReturnValue = strin;

        for (int i = 1; i <= nNum; i++)
        {
            strReturnValue = strReturnValue + "0";
        }
        return strReturnValue;
    }

    /***************************************************************************
     * 半角英数字<-->全角英数字に変換する。
     * 
     * @param String strin
     * @param int iType: iType==1:半角英数字-->全角英数字 iType==2:全角英数字-->半角英数字
     * @return String strOut
     * @exception
     * @see
     **************************************************************************/
    public static String ChangeEnglishAndNum(String strin, int iType)
    {
        String strOut = "";

        String t_Zen1[] = { "１", "２", "３", "４", "５", "６", "７", "８", "９", "０" };
        String t_Han1[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" };

        String t_Zen2[] = { "ａ", "ｂ", "ｃ", "ｄ", "ｅ", "ｆ", "ｇ", "ｈ", "ｉ", "ｊ",
                "ｋ", "ｌ", "ｍ", "ｎ", "ｏ", "ｐ", "ｑ", "ｒ", "ｓ", "ｔ", "ｕ", "ｖ",
                "ｗ", "ｘ", "ｙ", "ｚ" };

        String t_Zen3[] = { "Ａ", "Ｂ", "Ｃ", "Ｄ", "Ｅ", "Ｆ", "Ｇ", "Ｈ", "Ｉ", "Ｊ",
                "Ｋ", "Ｌ", "Ｍ", "Ｎ", "Ｏ", "Ｐ", "Ｑ", "Ｒ", "Ｓ", "Ｔ", "Ｕ", "Ｖ",
                "Ｗ", "Ｘ", "Ｙ", "Ｚ" };

        String t_Han2[] = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
                "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
                "w", "x", "y", "z" };

        String t_Han3[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
                "W", "X", "Y", "Z" };

        if (strin == null)
        {
            return strOut;
        } else
        {
            strOut = strin;

            if (iType == 1)
            {
                for (int i = 0; i < 10; i++)
                {
                    strOut = strOut.replaceAll(t_Han1[i], t_Zen1[i]);
                }
                for (int i = 0; i < 26; i++)
                {
                    strOut = strOut.replaceAll(t_Han2[i], t_Zen2[i]);
                }
                for (int i = 0; i < 26; i++)
                {
                    strOut = strOut.replaceAll(t_Han3[i], t_Zen3[i]);
                }

            } else if (iType == 2)
            {
                for (int i = 0; i < 10; i++)
                {
                    strOut = strOut.replaceAll(t_Zen1[i], t_Han1[i]);
                }
                for (int i = 0; i < 26; i++)
                {
                    strOut = strOut.replaceAll(t_Zen2[i], t_Han2[i]);
                }
                for (int i = 0; i < 26; i++)
                {
                    strOut = strOut.replaceAll(t_Zen3[i], t_Han3[i]);
                }

            }
            return strOut;
        }
    }

    /***************************************************************************
     * ***************************************************<BR>
     * メソッド名： IsFullWidth <BR>
     * 機能概要： 全角文字列であるかどうかチェックする <BR>
     * <BR>
     * 引数： strin 入力文字列 <BR>
     * 戻り値： True： はい <BR>
     * False： いいえ <BR>
     **************************************************************************/
    public static boolean IsFullWidth(String strin)
    {
        try
        {
            String str = "";
            for (int i = 0; i < strin.length(); i++)
            {
                str = strin.substring(i, i + 1);
                byte[] b = str.getBytes("SHIFT_JIS");
                if (b.length >= str.length() * 2)
                    return true;
            }
        } catch (Exception e)
        {
        }
        return false;
    }

    /**
     * 
     * 提供精確的加法計算。
     * 
     * @param v1 被加数
     * @param v2 加数
     * @return 参数的和
     */

    public static double add(double v1, double v2)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供精確的減法計算。
     * 
     * @param v1 被減数
     * @param v2 減数
     * @return 参数的差
     */
    public static double sub(double v1, double v2)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供精確的乘法計算。
     * 
     * @param v1 被乘数
     * @param v2 乘数
     * @return 参数的積
     */
    public static double mul(double v1, double v2)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供（相除）精確的除法計算，当発生除不尽的情况時，精確到 小数点以后10位，以后的数字四舍五入。
     * 
     * @param v1 被除数
     * @param v2 除数
     * @return 参数的商
     */
    public static double div(double v1, double v2)
    {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    /**
     * 提供（相除）精確的除法計算。当発生除不尽的情况時，由scale参数指 定精度，以后的数字四舍五入。
     * 
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示表示需要精?到小数点以后几位。
     * @return 参数的商
     */
    public static double div(double v1, double v2, int scale)
    {
        if (scale < 0)
        {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供精確的小数位四舍五入処理。
     * 
     * @param v 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的?果
     */
    public static double round(double v, int scale)
    {
        if (scale < 0)
        {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 按既定幅度規範文字列の序列
     * 
     * @param arrSource 文字列の序列
     * @param intMaxLen 既定行の幅度
     * @return 規範後の文字列の序列
     */
    public static ArrayList getSplitString(ArrayList arrSource, int intMaxLen)
    {
        ArrayList arrTar = new ArrayList();

        for (int index = 0; index < arrSource.size(); index++)
        {
            String strSource = ConvertUtils.RepNull(arrSource.get(index));
            char[] charArr = strSource.toCharArray();
            int iStep = 0;
            int iSize = 0;
            int iBegin = 0;
            int iEnd = 0;

            String strItem = "";
            for (int i = 0; i < charArr.length; i++)
            {
                iStep = String.valueOf(charArr[i]).getBytes().length;
                iSize += iStep;
                iEnd++;
                if ((iSize >= intMaxLen) || (i == charArr.length - 1))
                {
                    if (iSize > intMaxLen)
                    {
                        iEnd--;
                        iSize = iStep;
                    } else
                    {
                        iSize = 0;
                    }
                    strItem = strSource.substring(iBegin, iEnd);

                    iBegin = iEnd;
                    if (iSize == iStep)
                    {
                        iEnd++;
                    }

                    arrTar.add(strItem);

                    if ((i == charArr.length - 1) && (iBegin < iEnd))
                    {
                        strItem = strSource.substring(iBegin, iEnd);
                        arrTar.add(strItem);
                        iBegin = iEnd;
                    }
                }
            }

        }

        return arrTar;
    }

    /**
     * 複数条件取得(中間検索) <BR>
     * 中間一致の条件を返却する
     * 
     * @param strFil ：フィールド名 strVal ：値（条件） strJoin ：接続詞
     * @return 文字列
     * 
     */
    public static String getLikeWhere(String strFil, String strVal,
            String strJoin)
    {
        String strEsc = "/";
        String strWhere = "";
        String strValWk = strVal.replaceAll("　", " "); // 全角を半角に置換
        String strValTb[] = strValWk.split(" ");

        for (int idx = 0; idx < strValTb.length; idx++)
        {
            if (!strWhere.equals(""))
            {
                strWhere += strJoin;
            }
            // ワイルドカードは任意の文字に置き換
            String strHenkan = strValTb[idx];
            strHenkan = strHenkan.replaceAll(strEsc, strEsc + strEsc);
            strHenkan = strHenkan.replaceAll("%", strEsc + "%");
            strHenkan = strHenkan.replaceAll("％", strEsc + "％");
            strHenkan = strHenkan.replaceAll("_", strEsc + "_");
            strHenkan = strHenkan.replaceAll("＿", strEsc + "＿");
            strHenkan = strHenkan.replaceAll("'", "''");
            strWhere += "(" + strFil + " LIKE '%" + strHenkan + "%' ESCAPE '"
                    + strEsc + "')";
        }
        return strWhere;
    }

    /**
     * 複数条件取得(中間検索) <BR>
     * 中間一致の条件を返却する
     * 
     * @param strFil ：フィールド名 strVal ：値（条件）
     * @return 文字列
     * 
     */
    public static String getSingleLikeWhere(String strFil, String strVal)
    {
        String strEsc = "/";
        String strWhere = "";

        String strHenkan = strVal;
        strHenkan = strHenkan.replaceAll(strEsc, strEsc + strEsc);
        strHenkan = strHenkan.replaceAll("%", strEsc + "%");
        strHenkan = strHenkan.replaceAll("％", strEsc + "％");
        strHenkan = strHenkan.replaceAll("_", strEsc + "_");
        strHenkan = strHenkan.replaceAll("＿", strEsc + "＿");
        strHenkan = strHenkan.replaceAll("'", "''");
        strWhere += "(" + strFil + " LIKE '%" + strHenkan + "%' ESCAPE '"
                + strEsc + "')";

        return strWhere;
    }

    /**
     * Clob複数条件取得(中間検索) <BR>
     * 中間一致の条件を返却する
     * 
     * @param strFil ：フィールド名 strVal ：値（条件） strJoin ：接続詞
     * @return 文字列
     * 
     */
    public static String getClobLikeWhere(String strFil, String strVal,
            String strJoin)
    {
        String strWhere = "";
        String strValWk = strVal.replaceAll("　", " "); // 全角を半角に置換

        String strValTb[] = strValWk.split(" ");

        for (int idx = 0; idx < strValTb.length; idx++)
        {
            if (!strWhere.equals(""))
            {
                strWhere += strJoin;
            }
            // ワイルドカードは任意の文字に置き換
            String strHenkan = strValTb[idx];
            strHenkan = strHenkan.replaceAll("'", "''");
            strWhere += " dbms_lob.instr(" + strFil + ",'" + strHenkan
                    + "')>0 ";
        }
        return strWhere;
    }

    /**
     * 現在の日付を取得する。
     * 
     * @return 現在の日付
     */
    public static String getCurrentDate()
    {
        // 数値格式化
        DecimalFormat df1 = new DecimalFormat("0000");
        DecimalFormat df2 = new DecimalFormat("00");
        String strCurrentDate = "";

        Calendar cal = Calendar.getInstance();
        int intCurrentYear = cal.get(Calendar.YEAR);
        int intCurrentMonth = cal.get(Calendar.MONTH) + 1;
        int intCurrentDay = cal.get(Calendar.DATE);
        strCurrentDate = df1.format(intCurrentYear)
                + df2.format(intCurrentMonth) + df2.format(intCurrentDay);

        return strCurrentDate;
    }

    /**
     * 金額を表示する場合、3桁毎にカンマを表示する。
     * 
     * @param strMoney 金額
     * @return 3桁毎にカンマの金額
     */
    public static String formatMoney(String strMoney)
    {
        if (ConvertUtils.isNull(strMoney))
            return "";

        int iNum = 0;
        int intLength = 0;
        String strLittleValue = "";
        if (strMoney.indexOf(".") >= 0)
        {
            intLength = strMoney.indexOf(".");
            strLittleValue = strMoney.substring(intLength);
        } else
        {
            intLength = strMoney.length();
        }

        if (strMoney.indexOf("-") == 0)
        {
            iNum = (intLength - 2) / 3;
        } else
        {
            iNum = intLength / 3;
        }

        int iCount = 0;
        int iPos = 0;
        String strRet = "";
        for (int i = intLength; i > 0; i--)
        {
            iPos++;
            if (iPos % 3 == 0 && iPos != intLength && iCount < iNum)
            {
                iCount++;
                strRet = "," + strMoney.charAt(i - 1) + strRet;
            } else
            {
                strRet = strMoney.charAt(i - 1) + strRet;
            }

        }

        return strRet + strLittleValue;
    }

    /***************************************************************************
     * ***************************************************<BR>
     * メソッド名： ReplaceTagChar <BR>
     * 機能概要： 入力文字列をタグ処理を行う <BR>
     * "&" --> "&amp" <BR> "<" --> "&lt" <BR>
     * ">" --> "&gt" <BR>
     * "\"" --> "&quot" <BR>
     * "\r\n" --> "<br>" <BR>
     * <BR>
     * 引数： strin 入力文字列 <BR>
     * 戻り値： 処理した文字列 <BR>
     **************************************************************************/
    public static String ReplaceTagChar(String strin)
    {

        if (strin == null)
        {
            return strin;
        }
        if (strin.equals(""))
        {
            return strin;
        }
        strin = strin.replaceAll("&", "&amp;");
        strin = strin.replaceAll("<", "&lt;");
        strin = strin.replaceAll(">", "&gt;");
        strin = strin.replaceAll("\"", "&quot;");
        strin = strin.replaceAll("\r\n", "<br>");
        strin = strin.replaceAll("\n", "<br>");

        return strin;
    }

    /**
     * html元素を処理する。
     * 
     * @param strValue
     * @param strFlg charAt(0)=='1': " "->"&nbsp;" charAt(1)=='1': "\n"->"<BR>"
     *            charAt(2)=='1': "\t"->"&nbsp;&nbsp;&nbsp;&nbsp;"
     * @return strReturn
     */
    public static String tranHtml(String strValue, String strFlg)
    {
        if (strValue == null)
            return "";
        String strReturn = strValue;
        if (strFlg.charAt(0) == '1')
        {
            strReturn = strReturn.replaceAll(" ", "&nbsp;");
        }
        if (strFlg.charAt(1) == '1')
        {
            strReturn = strReturn.replaceAll("\n", "<BR>");
        }
        if (strFlg.charAt(2) == '1')
        {
            strReturn = strReturn.replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
        }
        return strReturn;
    }

    /**
     * TextAreaの本文を変換する.
     * 
     * @param strValue TextAreaの本文
     * @return 変換する後のTextAreaの本文
     */
    public static String convertTextAreaValue(String strValue)
    {
        return tranHtml(ReplaceTagChar(strValue), "101");
    }

    /**
     * メソッド名： DeleteSpace <BR>
     * 機能概要： 入力文字列に、先頭と末位置のスペース <BR>
     * （半角と全角）を削除する <BR>
     * <BR>
     * 引数： strin 入力文字列 <BR>
     * 戻り値： 処理した文字列 <BR>
     */
    public static String DeleteSpace(String strin)
    {

        boolean bFlag = true;
        String strReturnValue = "";

        if (strin == null)
        {
            return strin;
        }

        strin = strin.trim();

        while (bFlag)
        {
            if (strin.length() <= 0)
            {
                break;
            }
            String strStart = strin.substring(0, 1);
            String strEnd = strin.substring(strin.length() - 1);

            if (strEnd.equalsIgnoreCase("　") || strEnd.equalsIgnoreCase(" "))
            {
                strin = strin.substring(0, strin.length() - 1);
            } else if (strStart.equalsIgnoreCase("　")
                    || strStart.equalsIgnoreCase(" "))
            {
                strin = strin.substring(1, strin.length());
            } else
            {
                bFlag = false;
            }
        }
        strReturnValue = strin;
        return strReturnValue;
    }

    public static String getSubStr(String strValue, int byteLength)
    {
        int intCount = 0;
        int intByteLength = 0;
        String strRet = "";
        try
        {
            for (int i = 0; i < strValue.length(); i++)
            {
                intByteLength = strValue.substring(i, i + 1).getBytes("UTF-8").length;
                intCount += intByteLength;
                if (byteLength - intCount >= intByteLength)
                {
                    strRet += strValue.substring(i, i + 1);
                } else
                {
                    break;
                }
            }
        } catch (Exception ex)
        {
            strRet = "";
        }
        return strRet;
    }

    /**
     * clob --->string
     * 
     * @param clob
     * @return
     * @throws IOException
     * @throws SQLException
     */
    public static String clob2str(Clob clob) throws IOException, SQLException
    {
        return clob2str(clob, "\n");
    }

    public static String clob2str(Clob clob, String lineDelimiter)
            throws IOException, SQLException
    {
        if (clob == null)
            return "";
        StringBuilder sb = new StringBuilder();
        BufferedReader bReader = new BufferedReader(clob.getCharacterStream());
        String line = null;
        while ((line = bReader.readLine()) != null)
        {
            sb.append(line + lineDelimiter);
        }

        if (sb.length() > 0)
        {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * 半角文字列であるかどうかチェックする <BR>
     * 
     * @param strin 入力文字列
     * @return True： はい False： いいえ
     */
    public static boolean isValidLength(String strin, int max)
    {
        try
        {
            if (strin.getBytes("UTF-8").length > max)
            {
                return false;
            }
        } catch (UnsupportedEncodingException e)
        {
            ;
        }
        return true;
    }

    public static Map<String, String> beansToMap(
            List<LabelValueBean> cancelStateList2)
    {
        Map<String, String> map = new TreeMap<String, String>();
        for (LabelValueBean bean : cancelStateList2)
        {
            map.put(bean.getValue(), bean.getLabel());
        }
        return map;
    }
}