<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://www.opentravelsoft.com/tags/struts" prefix="ots" %>

  <div class="logo left">
    <img src="<s:url value='/images/logo.gif'/>" />
  </div> <!--logo-->

  <div class="header_right left">
    <div class="hotline left"><img src="<s:url value='/images/hotline.gif'/>" />
      <div class="search">
        <span><input name="text1" type="text" maxlength="20" class="txt" /></span>
        <span><input name="btnAdd" type="button" class="btn" /></span>
      </div> <!--search-->
    </div> <!--hotline-->

    <div class="header_menu left">
      <div class="language green right">
        <span>
          <c:if test="${pageContext.request.locale.language != 'en'}">
          <a href="<s:url value='/?locale=en'/>">English</a>
          </c:if>
          <c:if test="${pageContext.request.locale.language != 'zh_CN'}">
          <a href="<s:url value='/?locale=zh_CN'/>">中文</a>
          </c:if>
        </span>
      </div> <!--language-->

      <div class="login_menu right">
      <span class="login_menu_txt right">
        <c:if test="${empty pageContext.request.remoteUser}">
        <a href="<s:url value="/login.jsp"/>" class="current"><fmt:message key="login.title"/></a>
        </c:if>
        <c:if test="${pageContext.request.remoteUser != null}">
        <a href='<s:url value="/editProfile.html"/>'>
        <fmt:message key="user.status"/> ${pageContext.request.remoteUser}</a>
        <a href="/logout.jsp"><fmt:message key="user.logout"/></a>
        </c:if><a href="#">帮助</a>
        
      </span>
      </div> <!--login_menu-->
    </div> <!--header_menu-->
  </div> <!--header_right-->

<%-- Put constants into request scope --%>
<ots:constants scope="request"/>