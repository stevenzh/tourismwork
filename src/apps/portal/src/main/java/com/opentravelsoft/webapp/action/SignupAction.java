package com.opentravelsoft.webapp.action;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opentravelsoft.Constants;
import com.opentravelsoft.model.Member;
import com.opentravelsoft.service.UserExistsException;
import com.opentravelsoft.webapp.util.RequestUtil;
import org.springframework.mail.MailException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Action to allow new users to sign up.
 */
public class SignupAction extends PortalAction
{
    private static final long serialVersionUID = 6558317334878272308L;

    private Member member;

    private String cancel;

    public void setCancel(String cancel)
    {
        this.cancel = cancel;
    }

    public void setMember(Member user)
    {
        this.member = user;
    }

    /**
     * Return an instance of the user - to display when validation errors occur
     * 
     * @return a populated user
     */
    public Member getMember()
    {
        return member;
    }

    /**
     * When method=GET, "input" is returned. Otherwise, "success" is returned.
     * 
     * @return cancel, input or success
     */
    public String execute()
    {
        if (cancel != null)
        {
            return CANCEL;
        }
        if (ServletActionContext.getRequest().getMethod().equals("GET"))
        {
            return INPUT;
        }
        return SUCCESS;
    }

    /**
     * Returns "input"
     * 
     * @return "input" by default
     */
    public String doDefault()
    {
        return INPUT;
    }

    /**
     * Save the user, encrypting their passwords if necessary
     * 
     * @return success when good things happen
     * @throws Exception when bad things happen
     */
    public String save() throws Exception
    {
        member.setEnabled(true);

        // Set the default user role on this new user
        member.addRole(roleManager.getRole(Constants.USER_ROLE));

        try
        {
            userManager.saveUser(member);
        } catch (AccessDeniedException ade)
        {
            // thrown by UserSecurityAdvice configured in aop:advisor
            // userManagerSecurity
            log.warn(ade.getMessage());
            getResponse().sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        } catch (UserExistsException e)
        {
            log.warn(e.getMessage());
            List<Object> args = new ArrayList<Object>();
            args.add(member.getUsername());
            args.add(member.getEmail());
            addActionError(getText("errors.existing.user", args));

            // redisplay the unencrypted passwords
            member.setPassword(member.getConfirmPassword());
            return INPUT;
        }

        saveMessage(getText("member.registered"));
        getSession().setAttribute(Constants.REGISTERED, Boolean.TRUE);

        // log user in automatically
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                member.getUsername(), member.getConfirmPassword(), member
                        .getAuthorities());
        auth.setDetails(member);
        SecurityContextHolder.getContext().setAuthentication(auth);

        // Send an account information e-mail
        mailMessage.setSubject(getText("signup.email.subject"));

        try
        {
            sendUserMessage(member, getText("signup.email.message"),
                    RequestUtil.getAppURL(getRequest()));
        } catch (MailException me)
        {
            addActionError(me.getMostSpecificCause().getMessage());
        }

        return SUCCESS;
    }
}
