package com.opentravelsoft.service;

import java.util.List;

import com.opentravelsoft.entity.product.FileItem;

/**
 * 上传文件
 * 
 * @author zhangst
 *
 */
public interface PriceFileService {

	FileItem roGetFileItem(int fileId);

	List<FileItem> roGetFileList();

}
