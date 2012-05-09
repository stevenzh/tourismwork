package com.opentravelsoft.webapp.action;

import com.opensymphony.xwork2.ActionContext;
import com.opentravelsoft.BaseAction;
import com.opentravelsoft.Constants;
import com.opentravelsoft.common.SessionKeyParams;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Member;
import com.opentravelsoft.service.MailEngine;
import com.opentravelsoft.service.RoleManager;
import com.opentravelsoft.service.SmsService;
import com.opentravelsoft.service.UserManager;
import com.opentravelsoft.service.setting.ListService;
import com.opentravelsoft.util.HttpClientUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.opentravelsoft.util.LabelValueBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Implementation of <strong>ActionSupport</strong> that contains convenience
 * methods for subclasses. For example, getting the current user and saving
 * messages/errors. This class is intended to be a base class for all Action
 * classes.
 * 
 */
public class PortalAction extends BaseAction {
  private static final long serialVersionUID = 3525445612504421307L;

  @Autowired
  private SmsService smsService;

  @Autowired
  protected ListService listService;

  /**
   * Constant for cancel result String
   */
  public static final String CANCEL = "cancel";

  /**
   * Transient log to prevent session synchronization issues - children can use
   * instance for logging.
   */
  protected final transient Log log = LogFactory.getLog(getClass());

  /**
   * The UserManager
   */
  @Autowired
  protected UserManager userManager;

  /**
   * The RoleManager
   */
  @Autowired
  protected RoleManager roleManager;

  /**
   * Indicator if the user clicked cancel
   */
  protected String cancel;

  /**
   * Indicator for the page the user came from.
   */
  protected String from;

  /**
   * Set to "delete" when a "delete" request parameter is passed in
   */
  protected String delete;

  /**
   * Set to "save" when a "save" request parameter is passed in
   */
  protected String save;

  /**
   * MailEngine for sending e-mail
   */
  protected MailEngine mailEngine;

  /**
   * A message pre-populated with default data
   */
  protected SimpleMailMessage mailMessage;

  /**
   * Velocity template to use for e-mailing
   */
  protected String templateName;

  private String sysdate;

  /**
   * Simple method that returns "cancel" result
   * 
   * @return "cancel"
   */
  public String cancel() {
    return CANCEL;
  }

  /**
   * 取得数据库服务器时间
   */
  protected void buildSysdate() {
    systemDate = smsService.roGetSysdate();
    sysdate = SDF.format(systemDate);
  }

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
   * Save the message in the session, appending if messages already exist
   * 
   * @param msg the message to put in the session
   */
  @SuppressWarnings("unchecked")
  protected void saveMessage(String msg) {
    List messages = (List) getRequest().getSession().getAttribute("messages");
    if (messages == null) {
      messages = new ArrayList();
    }
    messages.add(msg);
    getRequest().getSession().setAttribute("messages", messages);
  }

  /**
   * Convenience method to get the Configuration HashMap from the servlet
   * context.
   * 
   * @return the user's populated form from the session
   */
  protected Map getConfiguration() {
    Map config = (HashMap) getSession().getServletContext().getAttribute(
        Constants.CONFIG);
    // so unit tests don't puke when nothing's been set
    if (config == null) {
      return new HashMap();
    }
    return config;
  }

  /**
   * Convenience method to get the request
   * 
   * @return current request
   */
  protected HttpServletRequest getRequest() {
    return super.getRequest();
  }

  /**
   * Convenience method to get the response
   * 
   * @return current response
   */
  protected HttpServletResponse getResponse() {
    return super.getResponse();
  }

  /**
   * Convenience method to get the session. This will create a session if one
   * doesn't exist.
   * 
   * @return the session from the request (request.getSession()).
   */
  protected HttpSession getSession() {
    return getRequest().getSession();
  }

  /**
   * Convenience method to get the session. This will create a session if one
   * doesn't exist.
   * 
   * @return the session from the request (request.getSession()).
   */
  protected Map<String, Object> getSessionMap() {
    return ActionContext.getContext().getSession();
  }

  /**
   * Convenience method to send e-mail to users
   * 
   * @param user the user to send to
   * @param msg the message to send
   * @param url the URL to the application (or where ever you'd like to send
   *          them)
   */
  protected void sendUserMessage(Member user, String msg, String url) {
    if (log.isDebugEnabled()) {
      log.debug("sending e-mail to user [" + user.getEmail() + "]...");
    }

    mailMessage.setTo(user.getFullName() + "<" + user.getEmail() + ">");

    Map<String, Object> model = new HashMap<String, Object>();
    model.put("user", user);
    // TODO: figure out how to get bundle specified in struts.xml
    // model.put("bundle", getTexts());
    model.put("message", msg);
    model.put("applicationURL", url);
    mailEngine.sendMessage(mailMessage, templateName, model);
  }

  public void setUserManager(UserManager userManager) {
    this.userManager = userManager;
  }

  public void setRoleManager(RoleManager roleManager) {
    this.roleManager = roleManager;
  }

  public void setMailEngine(MailEngine mailEngine) {
    this.mailEngine = mailEngine;
  }

  public void setMailMessage(SimpleMailMessage mailMessage) {
    this.mailMessage = mailMessage;
  }

  public void setTemplateName(String templateName) {
    this.templateName = templateName;
  }

  /**
   * Convenience method for setting a "from" parameter to indicate the previous
   * page.
   * 
   * @param from indicator for the originating page
   */
  public void setFrom(String from) {
    this.from = from;
  }

  public void setDelete(String delete) {
    this.delete = delete;
  }

  public void setSave(String save) {
    this.save = save;
  }

  protected Member getMember() {
    Member suser = (Member) ActionContext.getContext().getSession()
        .get(SessionKeyParams.EBIZ_USER);
    return suser;
  }
}
