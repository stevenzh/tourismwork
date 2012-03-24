package com.opentravelsoft.service.product;

import java.util.Date;
import java.util.List;

import com.opentravelsoft.util.LabelValueBean;

import com.opentravelsoft.entity.Airways;
import com.opentravelsoft.entity.LinePrice;

public interface LinePriceService {
  public List<LinePrice> getLinePrice(String lineNo, Date startDate,
      Date endDate);

  public List<Airways> roGetAllAirways();

  public List<LabelValueBean> getPriceType();

  public int txDeleteLinePrice(String priceNo, String note, long userId);

  public LinePrice findLinePrice(String priceNo);

  public int txEditPrice(LinePrice linePrice, String note, long userId);

}
