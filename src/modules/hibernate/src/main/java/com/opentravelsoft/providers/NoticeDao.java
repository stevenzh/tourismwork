package com.opentravelsoft.providers;

import java.util.List;

import com.opentravelsoft.entity.product.Notice;

/**
 * 没有实体类
 * 
 * @author zhangst
 * 
 */
public interface NoticeDao extends GenericDao<Notice, Long> {
  /**
   * 取得所有公告
   * 
   * @return
   */
  public List<Notice> getAll();

  public List<Notice> getValidNotices();

}
