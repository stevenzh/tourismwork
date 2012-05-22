package com.opentravelsoft.service.operator;

import com.opentravelsoft.entity.TourNoticeFile;

public interface TourNoticeUploadService {

  int txSaveFileInfo(TourNoticeFile tourNoticeFile);

  TourNoticeFile getTourNoticeFile(String tourNo);

  int txDeleteUploadFile(int fileId);

}
