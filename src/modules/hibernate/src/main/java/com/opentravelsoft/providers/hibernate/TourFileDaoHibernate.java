package com.opentravelsoft.providers.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.TblPriceFile;
import com.opentravelsoft.entity.product.FileItem;
import com.opentravelsoft.providers.PriceUploadDao;

@Repository("PriceUploadDao")
public class TourFileDaoHibernate extends
    GenericDaoHibernate<FileItem, Integer> implements PriceUploadDao {
  public TourFileDaoHibernate() {
    super(FileItem.class);
  }

  @SuppressWarnings("unchecked")
  public List<FileItem> getFileList(long groupCd) {
    StringBuilder sb = new StringBuilder();
    sb.append("from TblPriceFile where del='N' ");
    if (groupCd != 0)
      sb.append("and groupId=" + groupCd + " ");
    sb.append("order by created desc ");
    List<TblPriceFile> list = getHibernateTemplate().find(sb.toString());

    List<FileItem> ret = new ArrayList<FileItem>();
    for (TblPriceFile tblPriceFile : list) {
      FileItem item = new FileItem();
      item.setFileId(tblPriceFile.getId());
      item.setFileName(tblPriceFile.getFilename());
      item.setFilePath(tblPriceFile.getFilepath());
      item.setCreated(tblPriceFile.getCreated());
      ret.add(item);
    }

    return ret;
  }

  public FileItem getFileItem(int fileId) {
    TblPriceFile fileItem = (TblPriceFile) getHibernateTemplate().get(
        TblPriceFile.class, fileId);
    FileItem item = null;

    if (null != fileItem) {
      item = new FileItem();
      item.setFilePath(fileItem.getFilepath());
      item.setFileName(fileItem.getFilename());
    }

    return item;
  }

  public List<FileItem> getAllFileList() {
    return getFileList(0);
  }

  public int saveFile(FileItem fileItem) {
    TblPriceFile file = new TblPriceFile();
    file.setGroupId(fileItem.getGroupId());
    file.setFilename(fileItem.getFileName());
    file.setFilepath(fileItem.getFilePath());
    file.setCreatedby(fileItem.getOperator());
    file.setDel('N');

    getHibernateTemplate().save(file);
    return 0;
  }

  public int deleteFile(int fileId) {
    TblPriceFile fileItem = (TblPriceFile) getHibernateTemplate().get(
        TblPriceFile.class, fileId);

    if (null != fileItem) {
      fileItem.setDel('Y');
      getHibernateTemplate().save(fileItem);
      return 0;
    }
    return -1;
  }

}
