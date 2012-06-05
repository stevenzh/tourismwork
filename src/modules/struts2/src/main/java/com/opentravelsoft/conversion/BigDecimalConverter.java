package com.opentravelsoft.conversion;

import org.apache.struts2.util.StrutsTypeConverter;

import java.math.BigDecimal;
import java.util.Map;

public class BigDecimalConverter extends StrutsTypeConverter {

  @SuppressWarnings("rawtypes")
  @Override
  public Object convertFromString(Map ctx, String[] value, Class arg2) {
    if (value[0] == null || value[0].trim().equals("")) {
      return null;
    }

    return new BigDecimal(value[0]);
  }

  @SuppressWarnings("rawtypes")
  @Override
  public String convertToString(Map ctx, Object data) {
    return ((BigDecimal) data).toString();
  }
}
