package com.opentravelsoft.util;

import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;

/**
 * 文字列の検証を行うメソッド群です<BR>
 * 本クラスには以下の機能があります。
 * <UL>
 * <LI>指定されたエンコーディングによるエンコード後の文字列長の検証。
 * <LI>文字列フィールドの文字種別の検証。
 * </UL>
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:41 $
 */
public class EbizStringValidator
{
  /** 「制限なし」を表す予約語(TYPE_ANY) */
  public static final String S_TYPE_ANY = "TYPE_ANY";

  /** 「半角英字」を表す予約語(TYPE_ALPHABET) */
  public static final String S_TYPE_ALPHABET = "TYPE_ALPHABET";

  /** 「半角数字」を表す予約語(TYPE_NUMERIC) */
  public static final String S_TYPE_NUMERIC = "TYPE_NUMERIC";

  /** 「半角英数字」を表す予約語(TYPE_ALPHABET_NUMERIC) */
  public static final String S_TYPE_ALPHABET_NUMERIC = "TYPE_ALPHABET_NUMERIC";

  /** 「半角カナ」を表す予約語(TYPE_KANA) */
  public static final String S_TYPE_KANA = "TYPE_KANA";

  /** 「半角英数字カナ」を表す予約語(TYPE_ANK) */
  public static final String S_TYPE_ANK = "TYPE_ANK";

  /** 「Shift-JIS 2バイト文字」を表す予約語(TYPE_SJIS) */
  public static final String S_TYPE_SJIS = "TYPE_SJIS";

  /** 「半角記号」を表す予約語(TYPE_SYMBOL) */
  public static final String S_TYPE_SYMBOL = "TYPE_SYMBOL";

  /** 「ASCII文字」を表す予約語(TYPE_SYMBOL) */
  public static final String S_TYPE_ASCII = "TYPE_ASCII";

  /** 「制限なし」を表す内部データ表現 */
  public static final int TYPE_ANY = 0;

  /** 「半角英字」を表す内部データ表現  */
  public static final int TYPE_ALPHABET = 1;

  /** 「半角数字」を表す内部データ表現  */
  public static final int TYPE_NUMERIC = 2;

  /** 「半角英数字」を表す内部データ表現  */
  public static final int TYPE_ALPHABET_NUMERIC = TYPE_ALPHABET | TYPE_NUMERIC;

  /** 「半角カナ」を表す内部データ表現  */
  public static final int TYPE_KANA = 4;

  /** 「半角英数字カナ」を表す内部データ表現  */
  public static final int TYPE_ANK = TYPE_ALPHABET_NUMERIC | TYPE_KANA;

  /** 「Shift-JIS 2バイト文字」を表す内部データ表現  */
  public static final int TYPE_SJIS = 8;

  /** 「半角記号」を表す内部データ表現  */
  public static final int TYPE_SYMBOL = 16;

  /** 「ASCII文字」を表す内部データ表現  */
  public static final int TYPE_ASCII = TYPE_ALPHABET_NUMERIC | TYPE_SYMBOL;

  /**
   * 本クラスはインスタンスを生成しません。
   */
  protected EbizStringValidator() {
  }

  /**
   * 指定された文字列を指定されたエンコーディングでエンコードした後の長さが指定
   * された長さ以下であるかを判断します。
   *
   * @param argValue チェック対象の文字列
   * @param argMax 許可される文字列最大長
   * @param argEncoding エンコーディング
   * @return 条件が成立した場合、true
   */
  public static boolean maxLength(String argValue, int argMax, String argEncoding) 
    throws UnsupportedEncodingException {
    
    return !(argValue.getBytes(argEncoding).length > argMax);
  }

  /**
   * 指定された文字列を指定されたエンコーディングでエンコードした後の長さが指定
   * された長さ以上であるかを判断します。
   *
   * @param argValue チェック対象の文字列
   * @param argMin 許可される文字列最小長
   * @param argEncoding エンコーディング
   * @return 条件が成立した場合、true
   */
  public static boolean minLength(String argValue, int argMin, String argEncoding)
    throws UnsupportedEncodingException {
  
    return !(argValue.getBytes(argEncoding).length < argMin);
  }

  /**
   * 指定された文字列を指定されたエンコーディングでエンコードした後の長さが指定
   * された長さと等しいかを判断します。
   *
   * @param argValue チェック対象の文字列
   * @param argFix 許可される文字列長
   * @param argEncoding エンコーディング
   * @return 条件が成立した場合、true
   */
  public static boolean fixLength(String argValue, int argFix, String argEncoding)
    throws UnsupportedEncodingException {
    return (argValue.getBytes(argEncoding).length == argFix);
  }
  
  /**
   * 指定された文字列が指定された文字種別の文字のみから構成されているかを判断
   * します。
   *
   * @param argValue チェック対象の文字列
   * @param argType 許可される文字種別
   * @return 条件が成立した場合、true
   */
  public static boolean stringType(String argValue, String argType) {
    int stringType = getStringType(argType);
    if (stringType == TYPE_ANY) return true;

    int length = argValue.length();
    for (int i1 = 0; i1 < length; i1++) {
      if ((getType(argValue.charAt(i1)) & stringType) == 0) {
      //指定されたタイプ以外の文字を含んでいる
      return false;
      }
    }
    return true;
  }

  /**
   * 文字種別の予約文字列を内部コード表現に変換します。
   *
   * @param argType 文字種別（文字表現）
   * @return 文字種別（内部コード表現）
   */
  protected static int getStringType(String argType) {
    if (argType.indexOf('|') > 0)  {
      int type = 0;
      StringTokenizer st = new StringTokenizer(argType, "|");
      while (st.hasMoreElements()) {
        type |=  getStringType(st.nextToken().trim());
      }
      return type;
    }
    if (S_TYPE_ANY.equals(argType)) return TYPE_ANY;
    if (S_TYPE_ALPHABET.equals(argType)) return TYPE_ALPHABET;
    if (S_TYPE_NUMERIC.equals(argType)) return TYPE_NUMERIC;
    if (S_TYPE_ALPHABET_NUMERIC.equals(argType)) return TYPE_ALPHABET_NUMERIC;
    if (S_TYPE_KANA.equals(argType)) return TYPE_KANA;
    if (S_TYPE_ANK.equals(argType)) return TYPE_ANK;
    if (S_TYPE_SJIS.equals(argType)) return TYPE_SJIS;
    if (S_TYPE_SYMBOL.equals(argType)) return TYPE_SYMBOL;
    if (S_TYPE_ASCII.equals(argType)) return TYPE_ASCII;
    throw new IllegalArgumentException("Bad stringType = " + argType);    
  }

  /**
   * 与えられた文字の種別を調べます。<BR>
   * 全角・半角の判断は、対象文字列をMS932エンコーディングに変換し、文字列長が
   * 2バイトになるものを全角として取り扱います。
   *
   * @param argCharacter 調べる文字
   * @return キャラクターの種別
   */
  protected static int getType(char argCharacter) {
    byte[] sjisCharacter;
    try{
      //与えられた文字をShift-JISコードに変換する
      sjisCharacter = (new Character(argCharacter).toString()).getBytes("MS932");
    } catch (UnsupportedEncodingException ex) {
      return TYPE_ANY; //その他の文字

    }
    if ((sjisCharacter.length == 2)) {
      return TYPE_SJIS; //全角文字

    } else {
      int type = Character.getType(argCharacter);
      if (type == Character.OTHER_LETTER || type == Character.MODIFIER_LETTER) {
        return TYPE_KANA; //半角カナ

      } else if (Character.isDigit(argCharacter)) {
        return TYPE_NUMERIC; //数字

      } else if (Character.isLetter(argCharacter)) {
        return TYPE_ALPHABET; //英文字

      }
    }
    return TYPE_SYMBOL; //その他のASCII文字(半角記号)
  }
}
