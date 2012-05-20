package com.opentravelsoft.providers;

import com.opentravelsoft.entity.TourNoticeFile;

/**
 * 单团核算文件
 * 
 * @author zhangst
 */
public interface TourNoticeUploadDao extends
    GenericDao<TourNoticeFile, Integer> {
  /**
   * 保存出团通知书的上传信息
   * 
   * @param tourNoticeFile
   * @return
   */
  int saveFileInfo(TourNoticeFile tourNoticeFile);

  /**
   * 根据团号取本团的出团通知书信息
   * 
   * @param tourNo
   * @return
   */
  TourNoticeFile getTourNoticeFile(String tourNo);

  /**
   * 删除上传文件信息
   * 
   * @param fileId
   * @return
   */
  int deleteUploadFile(long fileId);

}
