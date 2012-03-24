package com.opentravelsoft.service.operator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.entity.TourNoticeFile;
import com.opentravelsoft.providers.TourNoticeUploadDao;

@Service("TourNoticeUploadService")
public class TourNoticeUploadServiceImpl implements TourNoticeUploadService {
  private TourNoticeUploadDao tourNoticeUploadDao;

  @Autowired
  public void setTourNoticeUploadDao(TourNoticeUploadDao tourNoticeUploadDao) {
    this.tourNoticeUploadDao = tourNoticeUploadDao;
  }

  public int txSaveFileInfo(TourNoticeFile tourNoticeFile) {
    return tourNoticeUploadDao.saveFileInfo(tourNoticeFile);
  }

  public TourNoticeFile getTourNoticeFile(String tourNo) {
    return tourNoticeUploadDao.getTourNoticeFile(tourNo);
  }

  public int txDeleteUploadFile(long fileId) {
    return tourNoticeUploadDao.deleteUploadFile(fileId);
  }

}
