<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.opentravelsoft.com/tags/struts" prefix="ots" %>

<head>
<title><fmt:message key="signup.title" /></title>
<meta name="heading" content="<fmt:message key='signup.heading'/>" />
</head>

<body id="signup" />

<div class="separator"></div>

<s:form name="signupForm" action="signup" method="post" validate="true">
  <li class="info"><fmt:message key="signup.message" /></li>

  <s:textfield key="member.username" cssClass="text large" required="true" />

  <li>
  <div>
    <div class="left">
      <s:password key="member.password" showPassword="true" theme="xhtml" required="true" cssClass="text medium" />
    </div>
    <div>
      <s:password key="member.confirmPassword" theme="xhtml" required="true" showPassword="true" cssClass="text medium" />
    </div>
  </div>
  </li>

  <s:textfield key="member.passwordHint" required="true" cssClass="text large" />

  <li>
  <div>
    <div class="left">
      <s:textfield key="member.firstName" theme="xhtml" required="true" cssClass="text medium" />
    </div>
    <div>
      <s:textfield key="member.lastName" name="" theme="xhtml" required="true" cssClass="text medium" />
    </div>
  </div>
  </li>

  <li>
  <div>
    <div class="left">
      <s:textfield key="member.email" theme="xhtml" required="true" cssClass="text medium" />
    </div>
    <div>
      <s:textfield key="member.phoneNumber" theme="xhtml" cssClass="text medium" />
    </div>
  </div>
  </li>

  <s:textfield key="member.website" required="true" cssClass="text large" />

  <li>
    <label class="desc"><fmt:message key="member.address.address" /></label>
    <div class="group">
      <div>
        <s:textfield key="member.address.address" theme="xhtml" cssClass="text large" labelposition="bottom" />
      </div>
      <div class="left">
        <s:textfield key="member.address.city" theme="xhtml" required="true" cssClass="text medium" labelposition="bottom" />
      </div>
      <div>
        <s:textfield key="member.address.province" theme="xhtml" required="true" cssClass="text state" labelposition="bottom" />
      </div>
      <div class="left">
        <s:textfield key="member.address.postalCode" theme="xhtml" required="true" cssClass="text medium" labelposition="bottom" />
      </div>
      <div>
        <s:set name="country" value="member.address.country" scope="page" />
        <ots:country name="member.address.country" prompt="" default="${country}" />
      <p>
      <label for="member.address.country">
      <fmt:message key="member.address.country" />
      <span class="req">*</span> </label>
      </p>
      </div>
    </div>
  </li>
  <li class="buttonBar bottom">
    <s:submit key="button.register" cssClass="button" />
    <s:submit key="button.cancel" name="cancel" cssClass="button" />
  </li>
</s:form>

<script type="text/javascript">
    Form.focusFirstElement(document.forms["signupForm"]);
</script>
