package com.opentravelsoft.webapp.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import com.opentravelsoft.common.SessionKeyParams;
import com.opentravelsoft.model.Member;
import com.opentravelsoft.service.UserManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class UserInfoInterceptor extends AbstractInterceptor {
  private static final long serialVersionUID = 1729368460754400272L;
  protected static final Log logger = LogFactory
      .getLog(UserInfoInterceptor.class);
  private UserManager userManager;

  public void setUserManager(UserManager userManager) {
    this.userManager = userManager;
  }

  public String intercept(ActionInvocation invocation) throws Exception {
    logger.debug("intercepter user info.");
    ActionContext act = invocation.getInvocationContext();
    Map<String, Object> session = act.getSession();
    
    // -------------------------------------------------------------------------
    Authentication authent = SecurityContextHolder.getContext().getAuthentication();
    User currentUser = null;
    if (authent.getPrincipal() instanceof UserDetails) {
      currentUser = (User) authent.getPrincipal();
    } else if (authent.getDetails() instanceof UserDetails) {
      currentUser = (User) authent.getDetails();
    } else {
      //throw new AccessDeniedException("User not properly authenticated.");
    }
    // -------------------------------------------------------------------------

    Object suser = session.get(SessionKeyParams.EBIZ_USER);
    if (null != currentUser && null == suser) {
      Member user = userManager.getUserByUsername(currentUser.getUsername().toUpperCase());
      session.put(SessionKeyParams.EBIZ_USER, user);
    }

    return invocation.invoke();
  }

  /**
   * This method currently does nothing.
   */
  public void destroy() {
  }

  /**
   * This method currently does nothing.
   */
  public void init() {
  }

}