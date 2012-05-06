package com.opentravelsoft.action.manage.operate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.common.EbizCommon;
import com.opentravelsoft.common.SessionKeyParams;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.TourNoticeFile;
import com.opentravelsoft.service.operator.TourNoticeUploadService;

/**
 * 上传出团通知书
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 */
public class TourNoticeUploadAction extends ManageAction {
  private static final long serialVersionUID = 47847991228161139L;

  @Autowired
  private TourNoticeUploadService tourNoticeUploadService;

  /** 文件标题 */
  private String title;

  /** 上传的文件 */
  private File upload;

  /** 上传的文件类型 */
  private String uploadContentType;

  /** 文件名 */
  private String uploadFileName;

  /** 文件保存路径 */
  private String savePath;

  /** 允许上传的文件类型 */
  private String allowTypes;

  private TourNoticeFile tourNoticeFile;

  private String tourNo;

  private Long fileId;

  /**
   * 初始化
   */
  public String input() {
    Object obj = ActionContext.getContext().getSession()
        .get(SessionKeyParams.EBIZ_CURRENT_TOUR);
    if (null != obj) {
      tourNo = ((Plan) obj).getTourNo();
    }

    tourNoticeFile = tourNoticeUploadService.getTourNoticeFile(tourNo);
    if (null != tourNoticeFile) {
      if (tourNoticeFile.getDelKey().equals("Y")) {
        tourNoticeFile = null;
      } else {
        fileId = tourNoticeFile.getFileId();
      }
    }
    return INPUT;
  }

  /**
   * 上传文件
   * 
   * @return
   * @throws Exception
   */
  public String upload() throws Exception {
    Employee user = getUser();
    Object obj = ActionContext.getContext().getSession()
        .get(SessionKeyParams.EBIZ_CURRENT_TOUR);
    if (null != obj) {
      tourNo = ((Plan) obj).getTourNo();
    }
    String filterResult = filterType(getAllowTypes().split(","));
    if (filterResult != null) {
      addActionError(getText("tourNotionUpload.typeError"));
      return filterResult;
    }
    SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    String targetDirectory = getSysConfig(EbizCommon.TOUR_UPLOAD_DIRECTORY);
    String downloadDirectory = getConfig(EbizCommon.TOUR_DOWNLOAD_DIRECTORY);
    // File target = new File(getSavePath(), SDF.format(new Date())
    // + uploadFileName.substring(uploadFileName.lastIndexOf('.')));

    String targetName = SDF.format(new Date())
        + uploadFileName.substring(uploadFileName.lastIndexOf('.'));
    String targetPath = targetDirectory + "\\" + targetName;
    FileOutputStream fos = new FileOutputStream(targetPath);
    FileInputStream fis = new FileInputStream(getUpload());
    byte[] buffer = new byte[1024];
    int len = 0;
    while ((len = fis.read(buffer)) > 0) {
      fos.write(buffer, 0, len);
    }

    tourNoticeFile = new TourNoticeFile();
    tourNoticeFile.setTourNo(tourNo);
    tourNoticeFile.setDptNo("");
    tourNoticeFile.setOperator(user.getUserId());
    tourNoticeFile.setFileName(uploadFileName);
    tourNoticeFile.setFilePath(downloadDirectory + targetName);
    tourNoticeFile.setNote(title);
    tourNoticeFile.setFileSize(upload.length());

    int ret = tourNoticeUploadService.txSaveFileInfo(tourNoticeFile);
    if (ret == 0) {
      addActionMessage(getText("tourNotionUpload.success"));
      return SUCCESS;
    } else {
      addActionMessage(getText("tourNotionUpload.reload"));
      return INPUT;
    }

  }

  /**
   * 删除上传文件信息
   * 
   * @return
   * @throws Exception
   */
  public String deleteUploadFile() throws Exception {
    int ret = tourNoticeUploadService.txDeleteUploadFile(fileId);
    if (ret == -1) {
      addActionError(getText("tourNotionUpload.delete.fail"));
      return INPUT;
    } else {
      addActionMessage(getText("tourNotionUpload.delete.success"));
      return SUCCESS;
    }

  }

  /**
   * 判断是否是允许上传的文件类型
   * 
   * @param types
   * @return
   */
  public String filterType(String[] types) {
    String fileType = getUploadContentType();
    for (String type : types) {
      if (type.equals(fileType)) {
        return null;
      }
    }

    return INPUT;

  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public File getUpload() {
    return upload;
  }

  public void setUpload(File upload) {
    this.upload = upload;
  }

  public String getUploadContentType() {
    return uploadContentType;
  }

  public void setUploadContentType(String uploadContentType) {
    this.uploadContentType = uploadContentType;
  }

  public String getUploadFileName() {
    return uploadFileName;
  }

  public void setUploadFileName(String uploadFileName) {
    this.uploadFileName = uploadFileName;
  }

  public String getSavePath() {
    // return savePath;
    return ServletActionContext.getRequest().getRealPath(savePath);
  }

  public void setSavePath(String savePath) {
    this.savePath = savePath;
  }

  public String getAllowTypes() {
    return allowTypes;
  }

  public void setAllowTypes(String allowTypes) {
    this.allowTypes = allowTypes;
  }

  public String getTourNo() {
    return tourNo;
  }

  public void setTourNo(String tourNo) {
    this.tourNo = tourNo;
  }

  public TourNoticeFile getTourNoticeFile() {
    return tourNoticeFile;
  }

  public void setTourNoticeFile(TourNoticeFile tourNoticeFile) {
    this.tourNoticeFile = tourNoticeFile;
  }

  public Long getFileId() {
    return fileId;
  }

  public void setFileId(Long fileId) {
    this.fileId = fileId;
  }

}
