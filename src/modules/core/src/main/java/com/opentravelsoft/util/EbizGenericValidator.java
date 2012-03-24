package com.opentravelsoft.util;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 標準的な入力フィールドの検証を行うメソッド群です。<BR>
 * 汎用的でかつ、Strutsに標準で準備されていない検証ルールを提供します。
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:41 $
 */
public class EbizGenericValidator {

  /**
   * 本クラスはインスタンスを生成しません。
   */
  protected EbizGenericValidator() {
  }

  /**
   * 指定された文字列の長さが指定された長さと等しいかを判別します。
   *
   * @param argValue チェック対象の文字列
   * @param argFix 許可される文字列長
   * @return 条件が成立した場合、true
   */
  public static boolean fixLength(String argValue, int argFix) {
    return (argValue.length() == argFix);
  }

  /**
   * 指定されたlong値がが指定された範囲内であるかを判別します。
   *
   * @param argValue チェック対象の文字列
   * @param argMin 許可される最小値
   * @param argMax 許可される最大値
   * @return 条件が成立した場合、true
   */
  public static boolean isInLongRange(long argValue, long argMin, long argMax) {
    return ((argValue >= argMin) && (argValue <= argMax));
  }

  /**
   * 指定された文字列のBigDecimal型の値が、引数で指定された範囲内に含まれる
   * かどうかを判別します。
   *
   * @param argValue チェック対象の文字列
   * @param argMinValue 許可される最小値
   * @param argMaxValue 許可される最大値
   * @return 条件が成立した場合、true
   */
  public static boolean isDecimalRange(BigDecimal argValue,
    BigDecimal argMinValue, BigDecimal argMaxValue) {    
    return argValue.compareTo(argMinValue) >= 0 && argValue.compareTo(argMaxValue) <= 0;
  }
  
  /**
   * 指定された文字列のBigDecimal型の小数点以下桁数が、引数で指定された小数点
   * 以下桁数の範囲に含まれるかかどうかを判別します。
   *
   * @param argValue チェック対象の文字列
   * @param argMinScale 許可される最小小数点以下桁数
   * @param argMaxScale 許可される最大小数点以下桁数
   * @return 条件が成立した場合、true
   */
  public static boolean isDecimalScale(BigDecimal argValue, int argMinScale, int argMaxScale) {
    int scale = argValue.scale();
    return (scale >= argMinScale) && (scale <= argMaxScale);
  }

  /**
   * 指定された文字列の２つBigDecimal型の値を比較します。
   *
   * @param argLeft 比較対象の左辺の値
   * @param argRight 比較対象の右辺の値
   * @param argOperator 比較演算子("=|==", ">=", <=", "!=|<>", ">", "<")
   * @return 条件が満たされている場合は、true
   */
  public static boolean checkNumberComparison(BigDecimal argLeft, BigDecimal argRight, String argOperator) {
    return checkComparison(argOperator, argLeft.compareTo(argRight));
  }

  /**
   * 指定された文字列の２つDate型の値を比較します。
   *
   * @param argLeft 比較対象の左辺の値
   * @param argRight 比較対象の右辺の値
   * @param argOperator 比較演算子("=|==", ">=", <=", "!=|<>", ">", "<")
   * @return 条件が満たされている場合は、true
   */
  public static boolean checkDateComparison(Date argLeft, Date argRight, String argOperator) {
    return checkComparison(argOperator, argLeft.compareTo(argRight));
  }
  
  /**
   * 指定された演算子の条件に比較結果値が該当しているかどうか判断します。
   *
   * @param argOperator 比較演算子("=|==", ">=", <=", "!=|<>", ">", "<")
   * @param argCompareValue 比較結果値(小さい:負の数, 等しい=0, 大きい=正の数)
   * @return 左辺の値が右辺の値より小さい場合は負の数、等しい場合は 0、大きい
   * 場合は正の数。
   */
  public static boolean checkComparison(String argOperator, int argCompareValue) {
    if ("=".equals(argOperator) || "==".equals(argOperator)) {
      return (argCompareValue == 0);
    }
    if ("!=".equals(argOperator) || "<>".equals(argOperator)) {
      return (argCompareValue != 0);
    }
    if (">".equals(argOperator)) {
      return (argCompareValue > 0);
    }
    if ("<".equals(argOperator)) {
      return (argCompareValue < 0);
    }
    if (">=".equals(argOperator)) {
      return (argCompareValue >= 0);
    }
    if ("<=".equals(argOperator)) {
      return (argCompareValue <= 0);
    }
    return false;
  }
}

