package com.opentravelsoft.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.entity.Sight;
import com.opentravelsoft.providers.product.LineSightDao;

@Service("RouteSightService")
public class LineSightServiceImpl implements LineSightService {
  
  @Autowired
  private LineSightDao lineSightDao;

  public int txSaveSights(List<Sight> list, String lineNo) {
    return lineSightDao.saveSights(list, lineNo);
  }

  public List<Sight> roGetDestinationSights(String lineNo) {
    return lineSightDao.getDestinationSights(lineNo);
  }

  public List<Sight> roGetLineSights(String lineNo) {
    return lineSightDao.getLineSights(lineNo);
  }

}
