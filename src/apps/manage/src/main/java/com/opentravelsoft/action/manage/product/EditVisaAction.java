package com.opentravelsoft.action.manage.product;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.common.EbizCommon;
import com.opentravelsoft.entity.Country;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.VisaHelp;
import com.opentravelsoft.entity.product.VisaItem;
import com.opentravelsoft.service.VisaHelpService;
import com.opentravelsoft.util.StringUtil;

/**
 * 签证服务
 * 
 * @author zhangst
 * 
 */
public class EditVisaAction extends ManageAction {
  private static final long serialVersionUID = 1082931041437688165L;

  protected static final Log logger = LogFactory.getLog(EditVisaAction.class);

  @Autowired
  private VisaHelpService visaService;

  private int attachedId;

  private String[] notes;

  /** 国家列表 */
  private List<Country> countrys;

  /** 具体的签证国家 */
  private String country;

  /** 具体签证国家的记录号 */
  private String recordNo;

  /** 具体签证项目 */
  private VisaHelp visaHelp;

  /** 删除签证项目ID */
  private int idx;

  /** 签证所须资料 */
  private List<VisaItem> items = new ArrayList<VisaItem>();

  /** 附件 */
  private List<VisaItem> fileItems = new ArrayList<VisaItem>();

  private List<LabelValueBean> visaKinds = new ArrayList<LabelValueBean>();

  private List<LabelValueBean> visaOpens = new ArrayList<LabelValueBean>();

  private List<LabelValueBean> visaDatas = new ArrayList<LabelValueBean>();

  /** 办理加急签证是/否 */
  private List<LabelValueBean> quickList = new ArrayList<LabelValueBean>();

  /** 实际上传文件 */
  private File[] uploads;

  /** 上传文件的内容类型 */
  private String[] uploadContentTypes;

  /** 上传文件的名字 */
  private String[] uploadFileNames;

  /**
   * 修改签证初始化
   * 
   * @return
   */
  public String input() {
    countrys = visaService.roGetCountrys();
    visaKinds = getCodeList("ebiz_visa_kind");
    visaOpens = getCodeList("ebiz_visa_open_list");
    quickList = getCodeList("ebiz_visa_quick");

    visaDatas = visaService.roGetDatas();
    if (StringUtil.hasLength(recordNo))
      visaHelp = visaService.roGetVisaItem(recordNo);
    else
      visaHelp = new VisaHelp();
    items = visaHelp.getItems();
    fileItems = visaHelp.getFileItems();
    return INPUT;
  }

  /**
   * 修改签证
   * 
   * @return
   */
  public String submit() {
    Employee user = getUser();
    visaHelp.setOpUser(user.getUserId());
    String targetDirectory = getSysConfig(EbizCommon.VISA_UPLOAD_DIRECTORY);
    SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    if (null != uploads)
      for (int i = 0; i < uploads.length; i++) {
        File temp = uploads[i];
        // 临时文件是否存在
        if (temp.exists()) {
          String targetFileName = uploadFileNames[i];
          File target = new File(targetDirectory, SDF.format(new Date())
              + targetFileName.substring(targetFileName.lastIndexOf('.')));
          try {
            FileUtils.copyFile(temp, target);
          } catch (IOException e) {
            logger.warn("File copy failure.");
          }
          VisaItem fileItem = new VisaItem();
          fileItem.setNote(uploadFileNames[i]);
          if (StringUtil.hasLength(notes[i]))
            fileItem.setNote(notes[i]);
          fileItem.setFilePath("/download/visa_/" + target.getName());
          fileItem.setFileName(target.getName());
          fileItems.add(fileItem);
        }
      }

    items = sort(items);
    visaService.txEditVisaItem(visaHelp, items, fileItems);
    return SUCCESS;
  }

  public String addItem() {
    int idx = 0;
    countrys = visaService.roGetCountrys();
    visaKinds = getCodeList("ebiz_visa_kind");
    visaOpens = getCodeList("ebiz_visa_open_list");
    quickList = getCodeList("ebiz_visa_quick");
    visaDatas = visaService.roGetDatas();

    items = sort(items);
    for (VisaItem obj : items) {
      obj.setIdx(idx++);
    }
    VisaItem item = new VisaItem();
    item.setIdx(idx++);
    items.add(item);
    return SUCCESS;
  }

  public String delItem() {
    visaKinds = getCodeList("ebiz_visa_kind");
    visaOpens = getCodeList("ebiz_visa_open_list");
    quickList = getCodeList("ebiz_visa_quick");
    visaDatas = visaService.roGetDatas();
    countrys = visaService.roGetCountrys();

    items = sort(items);
    for (VisaItem obj : items) {
      if (obj.getIdx() == idx) {
        items.remove(obj);
        break;
      }
    }
    return SUCCESS;
  }

  /**
   * 查询签证细节
   * 
   * @return
   */
  public String detail() {
    visaHelp = visaService.roGetVisaItem(recordNo);
    return SUCCESS;
  }

  public String delAttached() {
    visaKinds = getCodeList("ebiz_visa_kind");
    visaOpens = getCodeList("ebiz_visa_open_list");
    quickList = getCodeList("ebiz_visa_quick");
    visaDatas = visaService.roGetDatas();
    countrys = visaService.roGetCountrys();
    VisaItem item = visaService.roGetVisaAttached(attachedId);
    boolean result = visaService.txDelAttached(attachedId);
    if (result && item != null) {
      File file = new File(item.getFilePath());
      file.delete();
    }
    items = sort(items);

    return SUCCESS;
  }

  private List<VisaItem> sort(List<VisaItem> items) {
    List<VisaItem> list = new ArrayList<VisaItem>();

    for (int i = 0; i < items.size(); i++) {
      for (int j = 0; j < items.size(); j++) {
        VisaItem obj = items.get(j);
        if (obj.getIdx() == i + 1) {
          list.add(obj);
          break;
        }
      }
    }

    return list;
  }

  public List<Country> getCountrys() {
    return countrys;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public VisaHelp getVisaHelp() {
    return visaHelp;
  }

  public void setVisaHelp(VisaHelp visaHelp) {
    this.visaHelp = visaHelp;
  }

  public List<VisaItem> getItems() {
    return items;
  }

  public void setItems(List<VisaItem> items) {
    this.items = items;
  }

  public String getRecordNo() {
    return recordNo;
  }

  public void setRecordNo(String recordNo) {
    this.recordNo = recordNo;
  }

  public int getIdx() {
    return idx;
  }

  public void setIdx(int idx) {
    this.idx = idx;
  }

  public List<LabelValueBean> getVisaKinds() {
    return visaKinds;
  }

  public File[] getUpload() {
    return uploads;
  }

  public void setUpload(File[] upload) {
    this.uploads = upload;
  }

  public String[] getUploadContentType() {
    return uploadContentTypes;
  }

  public void setUploadContentType(String[] contentType) {
    this.uploadContentTypes = contentType.clone();
  }

  public String[] getUploadFileName() {
    return uploadFileNames;
  }

  public void setUploadFileName(String[] fileName) {
    this.uploadFileNames = fileName.clone();
  }

  public List<VisaItem> getFileItems() {
    return fileItems;
  }

  public int getAttachedId() {
    return attachedId;
  }

  public void setAttachedId(int attachedId) {
    this.attachedId = attachedId;
  }

  public String[] getNote() {
    return notes;
  }

  public void setNote(String[] note) {
    this.notes = note.clone();
  }

  public List<LabelValueBean> getVisaOpens() {
    return visaOpens;
  }

  public List<LabelValueBean> getVisaDatas() {
    return visaDatas;
  }

  public List<LabelValueBean> getQuickList() {
    return quickList;
  }

}
