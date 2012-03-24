package com.opentravelsoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.entity.product.FileItem;
import com.opentravelsoft.providers.PriceUploadDao;

@Service("PriceFileService")
public class PriceFileServiceImpl implements PriceFileService {

  private PriceUploadDao priceUploadDao;

  @Autowired
  public void setPriceUploadDao(PriceUploadDao priceUploadDao) {
    this.priceUploadDao = priceUploadDao;
  }

  public FileItem roGetFileItem(int fileId) {
    return priceUploadDao.getFileItem(fileId);
  }

  public List<FileItem> roGetFileList() {
    return priceUploadDao.getAllFileList();
  }

}
