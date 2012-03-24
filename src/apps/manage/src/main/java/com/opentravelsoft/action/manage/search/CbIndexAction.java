package com.opentravelsoft.action.manage.search;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.service.search.CbSearchService;

/**
 * 索引数据文件
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 */
public class CbIndexAction extends ManageAction {

  private static final long serialVersionUID = -6719506848263265234L;

  protected static final Log logger = LogFactory.getLog(CbIndexAction.class);

  private CbSearchService cbIndexService;

  /** 查询字符串 */
  private String queryString;

  /** 出发城市 */
  private String outCity = "All";

  private int length;

  /** 搜索类型:0-所有,1-出境,2-自由行,3-国内长线,4-国内短线 */
  private int searchType = 0;

  private String lineNo;

  @Override
  public String input() throws Exception {
    return INPUT;
  }

  /**
   * 单个索引
   */
  public String execute() {
    try {
      cbIndexService.getLineIndex(lineNo);
      addActionMessage("索引成功!");
      return SUCCESS;

    } catch (Exception e) {
      addActionMessage("索引失败!");
      return INPUT;
    }
  }

  /**
   * 全部索引
   * 
   * @return
   */
  public String allIndex() {
    try {
      logger.debug("全部索引 begin......");
      cbIndexService.getAllLineIndex();
      addActionMessage("全部索引成功!");
      logger.debug("全部索引成功!");
      return SUCCESS;
    } catch (Exception e) {
      e.printStackTrace();
      addActionMessage("全部索引失败!");
      logger.debug("全部索引失败!");
      return INPUT;
    }
  }

  /**
   * 目的地区域索引
   * 
   * @return
   */
  public String regionIndex() {
    try {
      logger.debug(getText("search.region.index"));
      cbIndexService.getRegionList();
      addActionMessage("目的地索引成功!");
      logger.debug("目的地索引成功!");
      return SUCCESS;
    } catch (Exception e) {
      e.printStackTrace();
      addActionMessage("目的地索引失败!");
      logger.debug("目的地索引失败!");
      return INPUT;
    }

  }

  @Autowired
  public void setCbIndexService(CbSearchService cbIndexService) {
    this.cbIndexService = cbIndexService;
  }

  public String getRouteNo() {
    return lineNo;
  }

  public void setRouteNo(String lineNo) {
    this.lineNo = lineNo;
  }

  public String getQueryString() {
    return queryString;
  }

  public void setQueryString(String queryString) {
    this.queryString = queryString;
  }

  public String getOutCity() {
    return outCity;
  }

  public void setOutCity(String outCity) {
    this.outCity = outCity;
  }

  public int getSearchType() {
    return searchType;
  }

  public void setSearchType(int searchType) {
    this.searchType = searchType;
  }

  public void setLength(int length) {
    this.length = length;
  }

  public int getLength() {
    return length;
  }

}
