package com.opentravelsoft.webapp.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.mail.MailException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import com.opensymphony.xwork2.Preparable;
import org.apache.struts2.ServletActionContext;

import com.opentravelsoft.Constants;
import com.opentravelsoft.entity.Member;
import com.opentravelsoft.entity.PortalRole;
import com.opentravelsoft.service.UserExistsException;
import com.opentravelsoft.webapp.util.RequestUtil;

/**
 * Action for facilitating User Management feature.
 */
public class UserAction extends PortalAction implements Preparable {

  private static final long serialVersionUID = 6776558938712115191L;

  private Member member;

  private String id;

  /**
   * Grab the entity from the database before populating with request parameters
   */
  public void prepare() {
    // prevent failures on new
    if (getRequest().getMethod().equalsIgnoreCase("post")
        && (!"".equals(getRequest().getParameter("member.id")))) {
      member = userManager.getUser(getRequest().getParameter("member.id"));
    }
  }

  public void setId(String id) {
    this.id = id;
  }

  public Member getMember() {
    return member;
  }

  public void setMember(Member user) {
    this.member = user;
  }

  /**
   * Delete the user passed in.
   * 
   * @return success
   */
  public String delete() {
    userManager.removeUser(member.getId().toString());
    List<Object> args = new ArrayList<Object>();
    args.add(member.getFullName());
    saveMessage(getText("member.deleted", args));

    return SUCCESS;
  }

  /**
   * Grab the user from the database based on the "id" passed in.
   * 
   * @return success if user found
   * @throws IOException can happen when sending a "forbidden" from
   *           response.sendError()
   */
  public String edit() throws IOException {
    HttpServletRequest request = getRequest();
    boolean editProfile = (request.getRequestURI().indexOf("editProfile") > -1);

    // if URL is "editProfile" - make sure it's the current user
    if (editProfile
        && ((request.getParameter("id") != null) || (request
            .getParameter("from") != null))) {
      ServletActionContext.getResponse().sendError(
          HttpServletResponse.SC_FORBIDDEN);
      log.warn("User '" + request.getRemoteUser()
          + "' is trying to edit user '" + request.getParameter("id") + "'");
      return null;
    }

    // if a user's id is passed in
    if (id != null) {
      // lookup the user using that id
      member = userManager.getUser(id);
    } else if (editProfile) {
      member = userManager.getUserByUsername(request.getRemoteUser());
    } else {
      member = new Member();
      member.addRole(new PortalRole(Constants.USER_ROLE));
    }

    if (member.getUsername() != null) {
      member.setConfirmPassword(member.getPassword());

      // if user logged in with remember me, display a warning that they
      // can't change passwords
      log.debug("checking for remember me login...");

      AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
      SecurityContext ctx = SecurityContextHolder.getContext();

      if (ctx != null) {
        Authentication auth = ctx.getAuthentication();

        if (resolver.isRememberMe(auth)) {
          getSession().setAttribute("cookieLogin", "true");
          saveMessage(getText("userProfile.cookieLogin"));
        }
      }
    }

    return SUCCESS;
  }

  /**
   * Default: just returns "success"
   * 
   * @return "success"
   */
  public String execute() {
    return SUCCESS;
  }

  /**
   * Sends users to "main" when !from.equals("list"). Sends everyone else to
   * "cancel"
   * 
   * @return "main" or "cancel"
   */
  public String cancel() {
    if (!"list".equals(from)) {
      return "main";
    }
    return "cancel";
  }

  /**
   * Save user
   * 
   * @return success if everything worked, otherwise input
   * @throws Exception when setting "access denied" fails on response
   */
  public String save() throws Exception {

    addActionMessage("测试");
    Integer originalVersion = member.getVersion();

    boolean isNew = ("".equals(getRequest().getParameter("member.version")));
    // only attempt to change roles if user is admin
    // for other users, prepare() method will handle populating
    if (getRequest().isUserInRole(Constants.ADMIN_ROLE)) {
      member.getRoles().clear(); // APF-788: Removing roles from user
                                 // doesn't work
      String[] userRoles = getRequest().getParameterValues("userRoles");

      for (int i = 0; userRoles != null && i < userRoles.length; i++) {
        String roleName = userRoles[i];
        member.addRole(roleManager.getRole(roleName));
      }
    }

    try {
      userManager.saveUser(member);
    } catch (AccessDeniedException ade) {
      // thrown by UserSecurityAdvice configured in aop:advisor
      // userManagerSecurity
      log.warn(ade.getMessage());
      getResponse().sendError(HttpServletResponse.SC_FORBIDDEN);
      return null;
    } catch (UserExistsException e) {
      List<Object> args = new ArrayList<Object>();
      args.add(member.getUsername());
      args.add(member.getEmail());
      addActionError(getText("errors.existing.user", args));

      // reset the version # to what was passed in
      member.setVersion(originalVersion);
      // redisplay the unencrypted passwords
      member.setPassword(member.getConfirmPassword());
      return INPUT;
    }

    if (!"list".equals(from)) {
      // add success messages
      saveMessage(getText("member.saved"));
      // return main menu page
      return "main";
    } else {
      // add success messages
      List<Object> args = new ArrayList<Object>();
      args.add(member.getFullName());
      if (isNew) {
        saveMessage(getText("member.added", args));
        // Send an account information e-mail
        mailMessage.setSubject(getText("signup.email.subject"));
        try {
          sendUserMessage(member, getText("newuser.email.message", args),
              RequestUtil.getAppURL(getRequest()));
        } catch (MailException me) {
          addActionError(me.getCause().getLocalizedMessage());
        }
        return SUCCESS;
      } else {
        saveMessage(getText("member.updated.byAdmin", args));
        return INPUT;
      }
    }
  }
}
