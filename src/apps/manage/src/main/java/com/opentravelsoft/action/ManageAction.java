package com.opentravelsoft.action;

import java.util.List;
import java.util.Map;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opentravelsoft.BaseAction;
import com.opentravelsoft.common.SessionKeyParams;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.service.SmsService;
import com.opentravelsoft.service.setting.ListService;
import com.opentravelsoft.util.HttpClientUtil;

public class ManageAction extends BaseAction {

  private static final long serialVersionUID = -4686799509744606110L;

  @Autowired
  private SmsService smsService;

  @Autowired
  protected ListService listService;

  private String sysdate;

  /**
   * 
   * @param listName
   * @return
   */
  protected List<LabelValueBean> getSysList(String listName) {
    return listService.getListByCategory(listName);
  }

  protected byte[] sendSms(String msg, String mob) throws Exception {
    byte[] rlt = null;
    Map<String, Object> session = ActionContext.getContext().getSession();

    String url = getConfig("ebiz_sms_url");
    String uid = getConfig("ebiz_sms_uid");
    String pswd = getConfig("ebiz_sms_pswd");
    String extno = getConfig("ebiz_sms_extno");

    long userId = 0;
    // uid=xkh&pswd=5537&msg=+_()*^%$@!hello%20world&extno=5537&mob=13816617311
    Object user = session.get(SessionKeyParams.EBIZ_USER);

    if (user != null) {
      userId = ((Employee) user).getUserId();
    }

    rlt = HttpClientUtil.execute(url + "?uid=" + uid + "&pswd=" + pswd
        + "&msg=" + msg + "&extno=" + extno + "&mob=" + mob);
    String[] sb = new String(rlt).split("\n");
    String seqno = "";
    if (sb.length > 2)
      seqno = sb[2];

    smsService.txSend(seqno, msg, mob, userId);
    return rlt;
  }

  /**
   * 取得数据库服务器时间
   */
  protected void buildSysdate() {
    systemDate = smsService.roGetSysdate();
    sysdate = SDF.format(systemDate);
  }

  public String getSysdate() {
    return sysdate;
  }

}
