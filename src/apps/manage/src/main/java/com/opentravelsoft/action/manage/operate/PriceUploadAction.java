package com.opentravelsoft.action.manage.operate;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.common.EbizCommon;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.product.FileItem;
import com.opentravelsoft.service.operator.PriceUploadService;

/**
 * 月度报价文件上传
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:00 $
 */
public class PriceUploadAction extends ManageAction {
  private static final long serialVersionUID = -8419187802314706988L;

  @Autowired
  private PriceUploadService priceUploadService;

  private List<FileItem> items = new ArrayList<FileItem>();

  private FileItem fileItem = new FileItem();

  private String contentType;

  private File upload;

  private String fileName;

  private String caption;

  private int fileId;

  private int teamId;

  @Override
  public String input() throws Exception {
    Employee user = getUser();
    items = priceUploadService.roGetFileList(user.getGroup().getGroupId());
    return INPUT;
  }

  public String upload() throws Exception {
    Employee user = getUser();
    SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    String targetDirectory = getSysConfig(EbizCommon.VISA_UPLOAD_DIRECTORY);
    String downloadDirectory = getConfig(EbizCommon.VISA_DOWNLOAD_DIRECTORY);
    File target = new File(targetDirectory, SDF.format(new Date())
        + fileName.substring(fileName.lastIndexOf('.')));
    try {
      FileUtils.copyFile(upload, target);
    } catch (IOException e) {
      logger.warn("File copy failure.");
    }

    fileItem.setNote(fileName);
    fileItem.setGroupId(user.getGroup().getGroupId());
    fileItem.setOperator(user.getUserId());
    fileItem.setFileName(fileName);
    fileItem.setFilePath(downloadDirectory + target.getName());

    int result = priceUploadService.txSaveFile(fileItem);
    return SUCCESS;
  }

  public String del() {
    int result = priceUploadService.txDelFile(fileId);
    return SUCCESS;
  }

  public List<FileItem> getItems() {
    return items;
  }

  // since we are using <s:file name="upload" .../> the file name will be
  // obtained through getter/setter of <file-tag-name>FileName
  public String getUploadFileName() {
    return fileName;
  }

  public void setUploadFileName(String fileName) {
    this.fileName = fileName;
  }

  // since we are using <s:file name="upload" ... /> the content type will be
  // obtained through getter/setter of <file-tag-name>ContentType
  public String getUploadContentType() {
    return contentType;
  }

  public void setUploadContentType(String contentType) {
    this.contentType = contentType;
  }

  // since we are using <s:file name="upload" ... /> the File itself will be
  // obtained through getter/setter of <file-tag-name>
  public File getUpload() {
    return upload;
  }

  public void setUpload(File upload) {
    this.upload = upload;
  }

  public String getCaption() {
    return caption;
  }

  public void setCaption(String caption) {
    this.caption = caption;
  }

  public void setFileId(int fileId) {
    this.fileId = fileId;
  }
}
