package com.opentravelsoft.service.search;

import com.opentravelsoft.EbizException;

public interface CbSearchService {

  public void getLineIndex(String lineNo) throws EbizException;

  public void getAllLineIndex() throws EbizException;

  public void getRegionList() throws EbizException;

}
