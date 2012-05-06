<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>

<page:applyDecorator name="default">

  <head>
  <title><fmt:message key="404.title" /></title>
  <meta name="heading" content="<fmt:message key='404.title'/>" />
  </head>

  <p>
  <fmt:message key="404.message">
    <fmt:param>
      <c:url value="/index.html" />
    </fmt:param>
  </fmt:message>
  </p>
  <p style="text-align: center; margin-top: 20px">
  <a href="http://www.opentravelsoft.com/photos/5682156/5s5aw8e-6qwe54r-6q5we4r.jpg"
    title="Maple, click to Zoom In"> <img
    src="<c:url value="/images/404.jpg"/>" alt="Maple" /></a></p>
</page:applyDecorator>