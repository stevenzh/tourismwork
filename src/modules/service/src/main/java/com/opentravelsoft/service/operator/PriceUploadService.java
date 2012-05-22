package com.opentravelsoft.service.operator;

import java.util.List;

import com.opentravelsoft.entity.product.FileItem;

public interface PriceUploadService {

  List<FileItem> roGetFileList(int teamId);

  int txSaveFile(FileItem fileItem);

  int txDelFile(int fileId);

}
