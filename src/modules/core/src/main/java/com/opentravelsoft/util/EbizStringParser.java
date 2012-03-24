package com.opentravelsoft.util;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

/**
 * 文字列を特定の型に変換するヘルパークラスです。<BR>
 * 現在は、許可される書式についてはコーディングで指定していますが、機能拡張可能
 * な用に強化予定です。
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:40 $
 */
public class EbizStringParser {
  /**
   * 本クラスはインスタンスを生成しません。
   */
  protected EbizStringParser() {
  }

  /**
   * 指定されたカンマ付の数値文字列をShort型に変換します。<BR>
   * 文字列中のカンマは無視します。
   *
   * @param argValue パース対象の文字列
   * @return 変換後のShort値。変換できない場合は、null値。
   */
  public static Short shortValue(String argValue)
  {
    if (argValue == null) return null;
    try
    {
      String value = (argValue.indexOf(",") < 0) ? argValue : argValue.replaceAll(",", "");
      return new Short(value);
    }
    catch (NumberFormatException ex)
    {
      return null;
    }
  }

  /**
   * 指定されたカンマ付の数値文字列をInteger型に変換します。<BR>
   * 文字列中のカンマは無視します。
   *
   * @param argValue パース対象の文字列
   * @return 変換後のInteger値。変換できない場合は、null値。
   */
  public static Integer integerValue(String argValue)
  {
    if (argValue == null) return null;
    try
    {
      String value = (argValue.indexOf(",") < 0) ? argValue : argValue.replaceAll(",", "");
      return new Integer(value);
    }
    catch (NumberFormatException ex)
    {
      return null;
    }
  }

  /**
   * 指定されたカンマ付の数値文字列をLong型に変換します。<BR>
   * 文字列中のカンマは無視します。
   *
   * @param argValue パース対象の文字列
   * @return 変換後のLong値。変換できない場合は、null値。
   */
  public static Long longValue(String argValue)
  {
    if (argValue == null) return null;
    try
    {
      String value = (argValue.indexOf(",") < 0) ? argValue : argValue.replaceAll(",", "");
      return new Long(value);
    }
    catch (NumberFormatException ex)
    {
      return null;
    }
  }

  /**
   * 指定されたカンマ付の数値文字列をBigDecimal型に変換します。<BR>
   * 文字列中のカンマは無視します。
   *
   * @param argValue パース対象の文字列
   * @return 変換後のBigDecimal値。変換できない場合は、null値。
   */
  public static BigDecimal decimalValue(String argValue)
  {
    if (argValue == null) return null;

    // JDK1.3.X以前に含まれるバグの回避
    if (argValue.indexOf("--") >= 0) return null;

    try
    {
      String value = (argValue.indexOf(",") < 0) ? argValue : argValue.replaceAll(",", "");
      return  new BigDecimal(value);
    }
    catch (NumberFormatException ex)
    {
      return null;
    }
  }

  /**
   * 指定された日付文字列をDate型に変換します。<BR>
   * 指定可能な書式は以下の通りです。
   * <UL>
   * <LI>y/M/d
   * <LI>y.M.d
   * <LI>y-M-d
   * <LI>yyyyMMdd
   * </UL>
   * 
   * @param argValue パース対象の文字列
   * @return 変換後のDate値。変換できない場合は、null値。
   */
  public static Date dateValue(String argValue)
  {
    if (argValue == null) return null;

    String year;
    String month;
    String day;
    StringTokenizer st = null;

    // yyyy/MM/dd 書式
    if (argValue.indexOf("/") >= 0)
    {
      st = new StringTokenizer(argValue, "/");
      if (st.countTokens() != 3) return null;
    }
    
    // yyyy-MM-dd 書式
    if (argValue.indexOf("-") >= 0)
    {
      st = new StringTokenizer(argValue, "-");
      if (st.countTokens() != 3) return null;
    }
    
    // yyyy.MM.dd 書式
    if (argValue.indexOf(".") >= 0)
    {
      st = new StringTokenizer(argValue, ".");
      if (st.countTokens() != 3) return null;
    }

    if (st != null)
    {
      year = st.nextToken();
      month = st.nextToken();
      day = st.nextToken();
    }
    else
    {
      if (argValue.length() != 8){
        return null;
      }
      // yyyyMMdd 書式
      year = argValue.substring(0, 4);
      month = argValue.substring(4, 6);
      day = argValue.substring(6, 8);
    }

    try
    {    
      //数値に変換
      int yearValue = Integer.parseInt(year);
      int monthValue = Integer.parseInt(month) - 1;
      int dayValue = Integer.parseInt(day);

      //カレンダーの作成
      Calendar calendar = new GregorianCalendar(yearValue, monthValue, dayValue);

      //日付のチェックを厳密に行う
      calendar.setLenient(false);  
      return calendar.getTime();

    }
    catch (NumberFormatException ex)
    {
      return null;
    }
    catch (IllegalArgumentException ex)
    {
      return null;
    }
  }

   /**
   * 指定された時間文字列をDate型に変換します。<BR>
   * 指定可能な書式は以下の通りです。

   * <UL>
   * <LI>HH:mm
   * <LI>HH:mm:ss
   * <LI>HHmm
   * <LI>HHmmss
   * </UL>
   * 日付は、自動的に西暦1年1月1日として扱います。
   * 
   * @param argValue パース対象の文字列
   * @return 変換後のDate値。変換できない場合は、null値。

   */
  public static Date timeValue(String argValue) {

    if (argValue == null) return null;

    try {
      int hour = 0;
      int minute = 0;
      int second = 0;

      if (argValue.indexOf(":") >= 0) {
        StringTokenizer st = new StringTokenizer(argValue, ":");
        switch (st.countTokens()) {
          case 2: // HH:mm
            hour = Integer.parseInt(st.nextToken());
            minute = Integer.parseInt(st.nextToken());
            second = 0;
            break;
          case 3: // HH:mm:ss
            hour = Integer.parseInt(st.nextToken());
            minute = Integer.parseInt(st.nextToken());
            second = Integer.parseInt(st.nextToken());
            break;
          default:
          return null;
        }
      } else {
        switch (argValue.length()) {
          case 4: // HHmm
            hour = Integer.parseInt(argValue.substring(0, 2));
            minute = Integer.parseInt(argValue.substring(2, 4));
            second = 0;
            break;
          case 6: // HHmmss
            hour = Integer.parseInt(argValue.substring(0, 2));
            minute = Integer.parseInt(argValue.substring(2, 4));
            second = Integer.parseInt(argValue.substring(4, 6));
            break;
          default:
            return null;
        }
      }

      //カレンダーの作成
      Calendar calendar = new GregorianCalendar(1, 1, 1, hour, minute, second);

      //日付のチェックを厳密に行う
      calendar.setLenient(false);  
      return calendar.getTime();
    } catch (NumberFormatException ex) {
      return null;
    } catch (IllegalArgumentException ex) {
      return null;
    }
  }
}
