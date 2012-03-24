package com.opentravelsoft.action.account;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opentravelsoft.service.account.MemberService;
import com.opentravelsoft.webapp.action.PortalAction;

/**
 * 通过手机找回用户信息
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:59 $
 */
public class RetrieveMemberAction extends PortalAction {
  private static final long serialVersionUID = 4593867921861507011L;

  protected static final Log logger = LogFactory
      .getLog(RetrieveMemberAction.class);

  private MemberService memberService;

  private String mobileNo;

  public void setMemberService(MemberService memberService) {
    this.memberService = memberService;
  }

  public String input() {
    return INPUT;
  }

  public String submit() {
    try {
      String pwd = memberService.txGetMemberPwd(mobileNo);
      if (null == pwd)
        addActionError("手机号码不存在，或者您的手机在今天已经索取过密码.");
      else
        sendSms(pwd, mobileNo);
    } catch (Exception e) {
      logger.error("短信发送失败.", e);
    }

    return SUCCESS;
  }

  public String getMobileNo() {
    return mobileNo;
  }

  public void setMobileNo(String mobileNo) {
    this.mobileNo = mobileNo;
  }

}
