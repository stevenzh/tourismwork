package com.opentravelsoft.providers;

import java.util.List;

import com.opentravelsoft.entity.product.FileItem;

/**
 * 月度报价发布
 * 
 * @author zhangst
 * 
 */
public interface PriceUploadDao extends GenericDao<FileItem, Integer> {
  List<FileItem> getAllFileList();

  List<FileItem> getFileList(long groupCd);

  FileItem getFileItem(int fileId);

  int saveFile(FileItem fileItem);

  int deleteFile(int fileId);

}
