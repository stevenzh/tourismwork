package com.opentravelsoft.service.product;

import java.util.Date;
import java.util.List;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.entity.Airways;
import com.opentravelsoft.entity.LinePrice;
import com.opentravelsoft.providers.AirwaysDao;
import com.opentravelsoft.providers.ListDao;
import com.opentravelsoft.providers.SequenceDao;
import com.opentravelsoft.providers.product.LinePriceDao;
import com.opentravelsoft.util.StringUtil;

@Service("RoutePriceService")
public class LinePriceServiceImpl implements LinePriceService {
  private LinePriceDao linePriceDao;

  private AirwaysDao airwaysDao;

  private ListDao listDao;

  private SequenceDao sequenceDao;

  @Autowired
  public void setSequenceDao(SequenceDao sequenceDao) {
    this.sequenceDao = sequenceDao;
  }

  @Autowired
  public void setListDao(ListDao priceTypeDao) {
    this.listDao = priceTypeDao;
  }

  @Autowired
  public void setAirwaysDao(AirwaysDao airwaysDao) {
    this.airwaysDao = airwaysDao;
  }

  @Autowired
  public void setRoutePriceDao(LinePriceDao routePriceDao) {
    this.linePriceDao = routePriceDao;
  }

  public List<LinePrice> getLinePrice(String lineNo, Date startDate,
      Date endDate) {
    return linePriceDao.getLinePrice(lineNo, startDate, endDate);
  }

  public List<Airways> roGetAllAirways() {
    return airwaysDao.getAll(true);
  }

  public List<LabelValueBean> getPriceType() {
    return listDao.getList("PriceType");
  }

  public int txDeleteLinePrice(String recNo, String note, long userId) {
    return linePriceDao.deleteLinePrice(recNo, note, userId);
  }

  public LinePrice findLinePrice(String recNo) {
    return linePriceDao.get(recNo);
  }

  public int txEditPrice(LinePrice routePrice, String note, long userId) {
    int result = 0;
    if (!StringUtil.hasLength(routePrice.getRecNo())) {
      String no = sequenceDao.getComputerNo("T", userId);
      routePrice.setRecNo(no);
    }
    linePriceDao.save(routePrice);
    return result;
  }

}
