package com.opentravelsoft;

import java.io.File;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BasicDynaBean;
import org.apache.commons.beanutils.BasicDynaClass;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opentravelsoft.util.LabelValueBean;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opentravelsoft.common.EbizCommon;
import com.opentravelsoft.common.SessionKeyParams;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.util.ConvertUtils;
import com.opentravelsoft.util.EbizParams;
import com.opentravelsoft.util.StringUtil;
import com.opentravelsoft.util.XMLUtility;

/**
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.2 $ $Date: 2009/03/09 15:37:04 $
 */
public class BaseAction extends ActionSupport {
  private static final long serialVersionUID = -488244263909156199L;

  protected static final Log logger = LogFactory.getLog(BaseAction.class);

  protected SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

  protected static final String ENCODEING = "GBK";

  protected int totalRecord = 0;

  /** 记录开始 */
  private int fromRecord = 0;

  /** 记录结束 */
  private int toRecord = 0;

  /** 页面表示记录 */
  protected int countInPage = 0;

  /** 页面跳转方式 */
  protected String movePage = "first";

  /** 总页 */
  protected int totalPage = 0;

  /** 当前页码 */
  protected int currentPage = 1;

  /** 排序ID */
  protected String sortId = "0";

  protected Date systemDate = new Date();

  /**
   * 模块名<br>
   * 用于设置用户权限
   */
  private String moduleName;

  /**
   * Convenience method to get the request
   * 
   * @return current request
   */
  protected HttpServletRequest getRequest() {
    return ServletActionContext.getRequest();
  }

  /**
   * Convenience method to get the response
   * 
   * @return current response
   */
  protected HttpServletResponse getResponse() {
    return ServletActionContext.getResponse();
  }

  protected Employee getUser() {
    Employee suser = (Employee) ActionContext.getContext().getSession()
        .get(SessionKeyParams.EBIZ_USER);
    return suser;
  }

  /**
   * 
   * @param argValue
   * @return
   */
  protected String getConfig(String argValue) {
    String strRetVal = null;
    String[] strVal = new String[] { "VALUE" };
    Map<String, Object> application = null;
    XMLUtility m_res_Config = null;

    application = ActionContext.getContext().getApplication();
    m_res_Config = (XMLUtility) application.get(EbizCommon.EBIZ_RES_CONFIG);
    String[] lstValue = m_res_Config.getData(argValue, strVal);
    if (lstValue != null && lstValue.length > 0) {
      strRetVal = lstValue[0];
    }
    return strRetVal;
  }

  /**
   * 
   * @param argValue
   * @return
   */
  protected String getSysConfig(String argValue) {
    return (String) ServletActionContext.getContext().getApplication()
        .get(argValue);
  }

  /**
   * 
   * @param argKey
   * @return
   */
  protected List<LabelValueBean> getCodeList(String argKey) {
    ArrayList<LabelValueBean> msgList = new ArrayList<LabelValueBean>();
    Map<String, Object> application = null;
    XMLUtility m_res_Config = null;

    application = ActionContext.getContext().getApplication();
    m_res_Config = (XMLUtility) application.get(EbizCommon.EBIZ_RES_CONFIG);
    List<String> temp = m_res_Config.getData(argKey, "OPTION");
    String[] listValue = null;
    for (int i = 0; i < temp.size(); i++) {
      listValue = ((String) temp.get(i)).split(",");
      msgList.add(new LabelValueBean(listValue[1], listValue[0]));
    }

    return msgList;
  }

  /**
   * 
   * @param argList
   * @param argField (codekey1,colname1,codekey2,colname2,...)
   */
  protected void setListLabel(ArrayList<DynaBean> argList, String[] argField) {
    Hashtable<String, String> hashValue = new Hashtable<String, String>();
    int nlen = argField.length / 2;
    String[] argColField = new String[nlen];
    for (int i = 0; i < nlen; i++) {
      argColField[i] = argField[2 * i + 1];
      List<LabelValueBean> list = getCodeList(argField[2 * i]);
      if (list != null) {
        for (int m = 0; m < list.size(); m++) {
          LabelValueBean bean = (LabelValueBean) list.get(m);
          hashValue
              .put(argColField[i] + "_" + bean.getValue(), bean.getLabel());
        }
      }
    }

    if ((argList != null) && (argList.size() > 0)) {
      DynaBean dynaBean = (DynaBean) argList.get(0);
      DynaProperty[] oldProp = dynaBean.getDynaClass().getDynaProperties();
      DynaProperty[] newProp = new DynaProperty[oldProp.length
          + argColField.length];

      System.arraycopy(oldProp, 0, newProp, oldProp.length, oldProp.length);
      for (int i = 0; i < argColField.length; i++) {
        newProp[oldProp.length + i] = new DynaProperty(argColField[i]
            + EbizParams.LABEL_FLAG, String.class);
      }
      BasicDynaClass bdc = new BasicDynaClass(null, BasicDynaBean.class,
          newProp);

      try {
        for (int i = 0; i < argList.size(); i++) {
          dynaBean = (DynaBean) argList.get(i);
          DynaBean newBean = bdc.newInstance();
          PropertyUtils.copyProperties(newBean, dynaBean);

          for (int j = 0; j < argColField.length; j++) {
            String strLabel = "";
            String strValue = ConvertUtils.RepNull(newBean.get(argColField[j]))
                .toString();
            if (hashValue.containsKey(argColField[j] + "_" + strValue)) {
              strLabel = ConvertUtils.RepNull(hashValue.get(argColField[j]
                  + "_" + strValue));
            }
            newBean.set(argColField[j] + EbizParams.LABEL_FLAG, strLabel);
          }
          argList.set(i, newBean);
        }
      } catch (Exception exception) {

      }
    }
  }

  protected int getMoveCount() {
    int pageMoveCount = 18;
    pageMoveCount = Integer
        .parseInt(getSysConfig(EbizCommon.DEFAULT_PAGE_DISPLAYCOUNT));
    return pageMoveCount;
  }

  /**
   * 根据页面请求显示的页数计算开始记录和结束记录
   */
  protected void dreamPage() {
    int page = currentPage;
    if (page < 1)
      page = 1;
    int tp = totalRecord / getMoveCount();
    int totalPages = totalRecord % getMoveCount() == 0 ? tp : tp + 1;

    if (StringUtil.hasLength(movePage)) {
      if (movePage.equals("first")) {
        page = 1;
      } else if (movePage.equals("prev")) {
        page--;
      } else if (movePage.equals("next")) {
        page++;
      } else if (movePage.equals("last")) {
        page = totalPages;
      } else if (movePage.matches("^[0-9]+")) {
        page = Integer.parseInt(movePage);
      }
      if (page > totalPages)
        page = totalPages;
    }

    if (page == 0)
      page = 1;

    fromRecord = (page - 1) * getMoveCount();
    toRecord = page * getMoveCount();
  }

  /**
   * 根据记录条数，计算页数
   * 
   * @param totalCount
   */
  protected void currentPage(int totalCount) {
    int page = currentPage;
    if (page < 1)
      page = 1;
    totalRecord = totalCount;
    int tp = totalRecord / getMoveCount();
    totalPage = totalRecord % getMoveCount() == 0 ? tp : tp + 1;

    if (StringUtil.hasLength(movePage)) {
      int totalPages = totalPage;

      if (movePage.equals("first")) {
        page = 1;
      } else if (movePage.equals("prev")) {
        page--;
      } else if (movePage.equals("next")) {
        page++;
      } else if (movePage.equals("last")) {
        page = totalPages;
      } else if (movePage.matches("^[0-9]+")) {
        page = Integer.parseInt(movePage);
      }
      if (page > totalPages)
        page = totalPages;
    }

    if (page == 0)
      page = 1;
    currentPage = page;
    fromRecord = (page - 1) * getMoveCount();
    toRecord = page * getMoveCount();
  }

  /**
   * 
   * @param filePath
   * @param fileOldName
   * @return
   * @throws Exception
   */
  public String getFileName(String filePath, String fileOldName)
      throws Exception {
    File tempDir = new File(filePath);

    int intStart = 0;
    String tmpNam = fileOldName;

    while (tmpNam.indexOf(".", intStart) != -1) {
      intStart = tmpNam.indexOf(".", intStart) + 1;
    }

    tmpNam = "." + tmpNam.substring(intStart);

    File file = File.createTempFile(InetAddress.getLocalHost().getHostName(),
        tmpNam, tempDir);
    return file.getName();
  }

  public int getTotalRecord() {
    return totalRecord;
  }

  public void setTotalRecord(int totalRecord) {
    this.totalRecord = totalRecord;
  }

  public int getFromRecord() {
    return fromRecord;
  }

  public int getToRecord() {
    return toRecord;
  }

  public int getCountInPage() {
    return countInPage;
  }

  public void setCountInPage(int countInPage) {
    this.countInPage = countInPage;
  }

  public String getMovePage() {
    return movePage;
  }

  public void setMovePage(String movePage) {
    this.movePage = movePage;
  }

  public String getSortId() {
    return sortId;
  }

  public void setSortId(String sortId) {
    this.sortId = sortId;
  }

  public int getTotalPage() {
    return totalPage;
  }

  public void setTotalPage(int totalPage) {
    this.totalPage = totalPage;
  }

  public int getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }

  public void setModuleName(String moduleName) {
    this.moduleName = moduleName;
  }

  public String getModuleName() {
    return moduleName;
  }

  public boolean hasAppModule(String name) {
    Map<String, Object> application = null;
    application = ActionContext.getContext().getApplication();
    if (null != application.get(EbizCommon.EBIZ_APP_MODULES)) {
      Set<String> modules = (Set) application.get(EbizCommon.EBIZ_APP_MODULES);
      if (modules.contains(name))
        return true;
    }
    return false;
  }

}
