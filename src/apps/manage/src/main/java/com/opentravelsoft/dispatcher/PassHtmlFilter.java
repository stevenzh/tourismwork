package com.opentravelsoft.dispatcher;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.RequestUtils;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.OpenSessionInViewFilter;

public class PassHtmlFilter extends OpenSessionInViewFilter
{
    /**
     * we do a different flushmode than in the codebase here
     */
    protected Session getSession(SessionFactory sessionFactory)
            throws DataAccessResourceFailureException
    {
        Session session = SessionFactoryUtils.getSession(sessionFactory, true);
        session.setFlushMode(FlushMode.COMMIT);
        return session;
    }

    /**
     * we do an explicit flush here just in case we do not have an automated
     * flush
     */
    protected void closeSession(Session session, SessionFactory factory)
    {
        session.flush();
        super.closeSession(session, factory);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException
    {
        String resourcePath = RequestUtils.getServletPath(request);

        if ("".equals(resourcePath) && null != request.getPathInfo())
        {
            resourcePath = request.getPathInfo();
        }

        // 月度广告
        resourcePath.endsWith("");

    }

}
