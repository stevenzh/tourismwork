package com.opentravelsoft.service.search;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.EbizException;
import com.opentravelsoft.entity.Destination;
import com.opentravelsoft.entity.Line;
import com.opentravelsoft.providers.mixed.CbIndexDao;
import com.opentravelsoft.search.CraftBrotherIndexer;

@Service("CbIndexService")
public class CbSearchServiceImpl implements CbSearchService {

  @Autowired
  private CbIndexDao cbIndexDao;

  public void getLineIndex(String lineNo) throws EbizException {
    try {
      Line lineIndex = cbIndexDao.getLineIndex(lineNo);
      CraftBrotherIndexer.beginIndex(lineIndex);
    } catch (IOException e) {
      throw new EbizException("Index fail.", e);
    }
  }

  public void getAllLineIndex() throws EbizException {
    try {
      List<Line> lineIndexs = cbIndexDao.getAllLineIndex();
      // 批量索引
      CraftBrotherIndexer.beginIndexList(lineIndexs);
    } catch (IOException e) {
      throw new EbizException("Index fail.", e);
    }
  }

  public void getRegionList() throws EbizException {
    try {
      List<Destination> regionList = cbIndexDao.getRegionList();
      // 批量索引
      CraftBrotherIndexer.beginAllRegionIndex(regionList);
    } catch (IOException e) {
      throw new EbizException("Index fail.", e);
    }
  }

}
