package com.opentravelsoft.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Date;

public class RowDataUtil {
  public static int DEFAULT_SCALE = 2;

  private static DecimalFormat DF = new DecimalFormat("#0.00");

  public static String doubleFormat(Object o) {
    double d = RowDataUtil.getDouble(o);
    return DF.format(d);
  }

  public static String intFormat(Object o) {
    int d = RowDataUtil.getInt(o);
    return String.valueOf(d);
  }

  /**
   * 
   * @param o
   * @return
   */
  public static String getString(Object o) {
    String str = "";
    if (null == o)
      return str;

    if (o instanceof String) {
      str = (String) o;
    } else {
      str = String.valueOf(o);
    }
    return str.trim();
  }

  /**
   * 
   * @param o
   * @return
   */
  public static short getShort(Object o) {
    short sh = 0;
    if (null == o)
      return sh;

    if (o instanceof Number) {
      Number num = (Number) o;
      sh = num.shortValue();
    }
    return sh;
  }

  /**
   * 
   * @param o
   * @return
   */
  public static float getFloat(Object o) {
    float sh = 0f;
    if (null == o)
      return sh;

    if (o instanceof Number) {
      Number num = (Number) o;
      sh = num.floatValue();
    }
    return sh;
  }

  /**
   * 
   * @param o
   * @return
   */
  public static double getDouble(Object o) {
    double sh = 0d;
    if (null == o)
      return sh;

    if (o instanceof Number) {
      Number num = (Number) o;
      sh = num.doubleValue();
    }
    return sh;
  }

  /**
   * 
   * @param o
   * @return
   */
  public static double getDouble(Object o, int scale) {
    return Arith.round(getDouble(o), 2);
  }

  /**
   * 
   * @param o
   * @return
   */
  public static BigDecimal getBigDecimal(Object o) {
    BigDecimal sh = new BigDecimal(0);
    if (null == o)
      return sh;

    if (o instanceof Number) {
      Number num = (Number) o;
      sh = new BigDecimal(num.doubleValue());
    }
    return sh;
  }

  /**
   * 
   * @param o
   * @return
   */
  public static int getInt(Object o) {
    int sh = 0;
    if (null == o)
      return sh;

    if (o instanceof Number) {
      Number num = (Number) o;
      sh = num.intValue();
    }
    return sh;
  }

  /**
   * 
   * @param o
   * @return
   */
  public static byte getByte(Object o) {
    byte sh = 0;
    if (null == o)
      return sh;

    if (o instanceof Number) {
      Number num = (Number) o;
      sh = num.byteValue();
    }
    return sh;
  }

  /**
   * 
   * @param o
   * @return
   */
  public static long getLong(Object o) {
    long sh = 0;
    if (null == o)
      return sh;

    if (o instanceof Number) {
      Number num = (Number) o;
      sh = num.longValue();
    }
    return sh;
  }

  /**
   * 
   * @param o
   * @return
   */
  public static Date getDate(Object o) {
    Date sh = null;
    if (null == o)
      return sh;

    if (o instanceof Date) {
      sh = (Date) o;
    }
    return sh;
  }

  /**
   * 
   * @param o
   * @return
   */
  public static Timestamp getTimestamp(Object o) {
    Timestamp sh = null;
    if (null == o)
      return sh;

    if (o instanceof Date) {
      sh = (Timestamp) o;
    }
    return sh;
  }

  /**
   * 
   * @param o
   * @return
   */
  public static char getChar(Object o) {
    char chr = ' ';
    if (null == o)
      return chr;

    if (o instanceof String) {
      if (((String) o).length() > 0)
        chr = ((String) o).charAt(0);
    }

    if (o instanceof Character) {
      return (Character) o;
    }
    return chr;
  }

  public static BigDecimal toDecimal(double val) {
    BigDecimal b = BigDecimal.valueOf(val);
    return b;
  }

}
