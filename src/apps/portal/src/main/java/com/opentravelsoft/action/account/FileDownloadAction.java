package com.opentravelsoft.action.account;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.entity.product.FileItem;
import com.opentravelsoft.service.PriceFileService;
import com.opentravelsoft.webapp.action.PortalAction;

/**
 * 下载报价文件
 * 
 * @author zhangst
 */
public class FileDownloadAction extends PortalAction {

  private static final long serialVersionUID = -7557668368553912838L;

  @Autowired
  private PriceFileService priceFileService;

  private int fileId;

  private String filePath;

  private String name;

  private List<FileItem> fileList = new ArrayList<FileItem>();

  public String input() throws Exception {
    fileList = priceFileService.roGetFileList();
    for (FileItem item : fileList) {
      item.setFilePath(item.getFilePath().replace("\\", "/"));
    }
    currentPage(fileList.size());
    return INPUT;
  }

  public String execute() throws Exception {
    FileItem item = priceFileService.roGetFileItem(fileId);
    filePath = item.getFilePath();
    name = filePath.substring(filePath.lastIndexOf("\\") + 1);
    // name = item.getFileName();
    return SUCCESS;
  }

  /**
   * 取页显示记录数
   */
  protected int getMoveCount() {
    return 20;
  }

  public InputStream getInputStream() throws Exception {
    return ServletActionContext.getServletContext().getResourceAsStream(
        filePath);
  }

  public void setFileId(int fileId) {
    this.fileId = fileId;
  }

  public List<FileItem> getFileList() {
    return fileList;
  }

  public String getName() {
    return name;
  }

}
