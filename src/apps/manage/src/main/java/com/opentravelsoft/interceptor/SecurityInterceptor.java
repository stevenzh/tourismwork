package com.opentravelsoft.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opentravelsoft.common.EbizCommon;
import com.opentravelsoft.common.GlobalMessages;
import com.opentravelsoft.common.SessionKeyParams;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.util.StringUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityInterceptor extends AbstractInterceptor {
  private static final long serialVersionUID = -726209768089734484L;

  protected static final Log logger = LogFactory
      .getLog(SecurityInterceptor.class);

  public String intercept(ActionInvocation actionInvocation) throws Exception {
    ActionContext act = actionInvocation.getInvocationContext();
    Map<String, Object> session = act.getSession();

    // ---------------------------------------------------------------------
    Authentication authent = SecurityContextHolder.getContext()
        .getAuthentication();

    Employee currentUser = null;
    if (authent.getPrincipal() instanceof UserDetails) {
      currentUser = (Employee) authent.getPrincipal();
    } else if (authent.getDetails() instanceof UserDetails) {
      currentUser = (Employee) authent.getDetails();
    } else {
      throw new AccessDeniedException("User not properly authenticated.");
    }

    Object suser = session.get(SessionKeyParams.EBIZ_USER);
    if (null != currentUser && null == suser) {
      session.put(SessionKeyParams.EBIZ_USER, currentUser);
    } else if (null != suser) {
      currentUser = (Employee) suser;
    }

    // ---------------------------------------------------------------------
    String moduleName = "";
    Map<String, Object> params = actionInvocation.getInvocationContext()
        .getParameters();
    if (null == currentUser)
      return actionInvocation.invoke();

    if (params.containsKey("moduleName"))
      moduleName = (String) params.get("moduleName");

    if (!isAuthorized(currentUser, moduleName)) {
      ActionSupport action = (ActionSupport) actionInvocation.getAction();
      action.addActionError(action.getText(GlobalMessages.ERROR_NOTAUTHORIZED));

      return EbizCommon.NOT_AUTHORIZED;
    }

    // ---------------------------------------------------------------------
    // 设置快捷菜单
    // String menuName = KeyParams.EBIZ_SESSION_MANAGE_DESTOP;
    // if (params.containsKey("menuName"))
    // moduleName = (String) params.get("menuName");
    // if (StringUtil.hasLength(menuName))
    // ServletActionContext.getRequest().getSession().setAttribute(
    // SessionKeyParams.EBIZ_CURRENT_MANAGE_CLASS, menuName);

    ActionContext.getContext().getValueStack().push(this);

    return actionInvocation.invoke();
  }

  protected boolean isAuthorized(Employee user, String moduleName) {
    if (!StringUtil.hasLength(moduleName))
      return true;

    Map<String, String> priv = user.getPriv();
    if (priv.containsKey(moduleName)
        && priv.get(moduleName).equalsIgnoreCase("true"))
      return true;

    logger.debug("no authorized :" + moduleName);
    return false;
  }

}