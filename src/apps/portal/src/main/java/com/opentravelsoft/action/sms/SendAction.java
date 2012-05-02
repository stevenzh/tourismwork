package com.opentravelsoft.action.sms;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.service.SmsService;
import com.opentravelsoft.util.HttpClientUtil;
import com.opentravelsoft.webapp.action.PortalAction;

public class SendAction extends PortalAction {

  private static final long serialVersionUID = -3520469942385139969L;

  @Autowired
  private SmsService smsService;

  private String msg;

  private String mob;

  public String getMob() {
    return mob;
  }

  public void setMob(String mob) {
    this.mob = mob;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  @Override
  public String execute() {
    Employee user = getUser();
    String url = getConfig("ebiz_sms_url");
    String uid = getConfig("ebiz_sms_uid");
    String pswd = getConfig("ebiz_sms_pswd");
    String extno = getConfig("ebiz_sms_extno");

    long userId = 0;
    // uid=xkh&pswd=5537&msg=+_()*^%$@!hello%20world&extno=5537&mob=13816617311

    if (user != null) {
      userId = ((Employee) user).getUserId();
    }

    int result = smsService.txSend("", msg, mob, userId);

    if (result == 0) {
      byte[] resu = HttpClientUtil.execute(url + "?uid=" + uid + "&pswd="
          + pswd + "&msg=" + msg + "&extno=" + extno + "&mob=" + mob);
    }

    return NONE;
  }
}
