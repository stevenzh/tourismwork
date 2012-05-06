package com.opentravelsoft.providers.product;

import java.util.Date;
import java.util.List;

import com.opentravelsoft.entity.LinePrice;
import com.opentravelsoft.providers.GenericDao;

/**
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.2 $ $Date: 2009/03/09 15:37:04 $
 */
public interface LinePriceDao extends GenericDao<LinePrice, String> {
  public List<LinePrice> getLinePrice(String lineNo, Date startDate,
      Date endDate);

  public int deleteLinePrice(String recNo, String note, long uid);

  public List<LinePrice> getPriceNotice(String lineNo);

  public void savePrice(LinePrice routePrice);

}
