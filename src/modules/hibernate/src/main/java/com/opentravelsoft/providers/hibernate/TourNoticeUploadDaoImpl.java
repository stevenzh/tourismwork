package com.opentravelsoft.providers.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.TourNoticeFile;
import com.opentravelsoft.providers.TourNoticeUploadDao;
import com.opentravelsoft.util.RowDataUtil;

/**
 * 出团通知书上传下载
 */
@Repository("TourNoticeUploadDao")
public class TourNoticeUploadDaoImpl extends
    GenericDaoHibernate<TourNoticeFile, Integer> implements TourNoticeUploadDao {

  public TourNoticeUploadDaoImpl() {
    super(TourNoticeFile.class);
  }

  @SuppressWarnings("unchecked")
  public int saveFileInfo(TourNoticeFile tourNoticeFile) {
    HibernateTemplate template = getHibernateTemplate();
    StringBuilder sql = new StringBuilder();
    sql.append("from TblTourNoticeFile ");
    sql.append("where tourNo=? and del='N' ");
    List<TourNoticeFile> list1 = template.find(sql.toString(),
        tourNoticeFile.getTourNo());

    // 同一团号是否已有出团通知书
    if (list1.isEmpty()) {
      TourNoticeFile tblTourNoticeFile = new TourNoticeFile();
      tblTourNoticeFile.setTourNo(tourNoticeFile.getTourNo());
      tblTourNoticeFile.setDptCd(tourNoticeFile.getDptNo());
      tblTourNoticeFile.setFileName(tourNoticeFile.getFileName());
      tblTourNoticeFile.setFilePath(tourNoticeFile.getFilePath());
      tblTourNoticeFile.setFileSize(tourNoticeFile.getFileSize());
      tblTourNoticeFile.setDel("N");
      tblTourNoticeFile.setCreatedBy(tourNoticeFile.getOperator());
      tblTourNoticeFile.setNote(tourNoticeFile.getNote());

      template.save(tblTourNoticeFile);
      return 0;
    } else {
      return -1;
    }

  }

  @SuppressWarnings("unchecked")
  public TourNoticeFile getTourNoticeFile(String tourNo) {
    HibernateTemplate template = getHibernateTemplate();
    StringBuilder sql = new StringBuilder();
    sql.append("from TblTourNoticeFile where tourNo=?  and del='N'");
    List<TourNoticeFile> list1 = template.find(sql.toString(), tourNo);
    TourNoticeFile tourNoticeFile;
    if (!list1.isEmpty()) {
      tourNoticeFile = new TourNoticeFile();
      TourNoticeFile tblTourNoticeFile = list1.get(0);
      tourNoticeFile
          .setFileId(RowDataUtil.getInt(tblTourNoticeFile.getFileId()));
      tourNoticeFile.setTourNo(RowDataUtil.getString(tblTourNoticeFile
          .getTourNo()));
      tourNoticeFile.setFileName(RowDataUtil.getString(tblTourNoticeFile
          .getFileName()));
      tourNoticeFile.setFileSize(RowDataUtil.getLong(tblTourNoticeFile
          .getFileSize()));
      tourNoticeFile.setFilePath(RowDataUtil.getString(tblTourNoticeFile
          .getFilePath()));
      tourNoticeFile.setCreated(RowDataUtil.getDate(tblTourNoticeFile
          .getCreated()));
      tourNoticeFile.setDptNo(RowDataUtil.getString(tblTourNoticeFile
          .getDptCd()));
      tourNoticeFile
          .setDelKey(RowDataUtil.getString(tblTourNoticeFile.getDel()));
      tourNoticeFile.setOperator(tblTourNoticeFile.getCreatedBy());

      return tourNoticeFile;
    } else {
      return null;
    }

  }

  public int deleteUploadFile(int fileId) {
    HibernateTemplate template = getHibernateTemplate();
    TourNoticeFile tblTourNoticeFile = (TourNoticeFile) template.get(
        TourNoticeFile.class, fileId);
    if (null != tblTourNoticeFile) {
      // template.delete(tblTourNoticeFile);
      tblTourNoticeFile.setDel("Y");
      template.update(tblTourNoticeFile);
      return 0;
    } else {
      return -1;
    }

  }

}
