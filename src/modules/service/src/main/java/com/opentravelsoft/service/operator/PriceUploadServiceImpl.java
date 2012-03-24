package com.opentravelsoft.service.operator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.opentravelsoft.entity.product.FileItem;
import com.opentravelsoft.providers.PriceUploadDao;

@Service("PriceUploadService")
public class PriceUploadServiceImpl implements PriceUploadService
{
    private PriceUploadDao priceUploadDao;

    @Autowired
    public void setPriceUploadDao(PriceUploadDao priceUploadDao)
    {
        this.priceUploadDao = priceUploadDao;
    }

    public List<FileItem> roGetFileList(long groupCd)
    {
        return priceUploadDao.getFileList(groupCd);
    }

    public int txSaveFile(FileItem fileItem)
    {
        return priceUploadDao.saveFile(fileItem);
    }

    public int txDelFile(int fileId)
    {
        return priceUploadDao.deleteFile(fileId);
    }

}
