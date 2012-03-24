<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://www.opentravelsoft.com/tags/struts" prefix="ots" %>

<head>
  <title><fmt:message key="userProfile.title"/></title>
  <meta name="heading" content="<fmt:message key='userProfile.heading'/>"/>
  <meta name="menu" content="UserMenu"/>
  <script type="text/javascript" src="<c:url value='/scripts/selectbox.js'/>"></script>
</head>

<s:form name="userForm" action="saveUser" method="post" validate="true">
  <li style="display: none">
    <s:hidden key="member.id"/>
    <s:hidden key="member.version"/>
    <input type="hidden" name="from" value="${param.from}"/>

    <c:if test="${cookieLogin == 'true'}">
      <s:hidden key="member.password"/>
      <s:hidden key="member.confirmPassword"/>
    </c:if>

    <s:if test="member.version == null">
      <input type="hidden" name="encryptPass" value="true" />
    </s:if>
  </li>
  <li class="buttonBar right">
    <c:set var="buttons">
        <s:submit key="button.save" method="save" onclick="onFormSubmit(this.form)"/>
    <c:if test="${param.from == 'list' and not empty member.id}">
        <s:submit key="button.delete" method="delete" onclick="return confirmDelete('user')"/>
    </c:if>
        <s:submit key="button.cancel" method="cancel"/>
    </c:set>
    <c:out value="${buttons}" escapeXml="false"/>
  </li>
  <li class="info">
    <c:choose>
      <c:when test="${param.from == 'list'}">
          <p><fmt:message key="userProfile.admin.message"/></p>
      </c:when>
      <c:otherwise>
          <p><fmt:message key="userProfile.message"/></p>
      </c:otherwise>
    </c:choose>
  </li>

  <s:textfield key="member.username" cssClass="text large" required="true"/>

  <c:if test="${cookieLogin != 'true'}">
  <li>
    <div>
      <div class="left">
          <s:password key="member.password" showPassword="true" theme="xhtml" required="true" 
              cssClass="text medium" onchange="passwordChanged(this)"/>
      </div>
      <div>
          <s:password key="member.confirmPassword" theme="xhtml" required="true" 
              showPassword="true" cssClass="text medium" onchange="passwordChanged(this)"/>
      </div>
    </div>
  </li>
  </c:if>

  <s:textfield key="member.passwordHint" required="true" cssClass="text large"/>

  <li>
    <div>
      <div class="left">
          <s:textfield key="member.firstName" theme="xhtml" required="true" cssClass="text medium"/>
      </div>
      <div>
          <s:textfield key="member.lastName" theme="xhtml" required="true" cssClass="text medium"/>
      </div>
    </div>
  </li>

  <li>
    <div>
      <div class="left">
          <s:textfield key="member.email" theme="xhtml" required="true" cssClass="text medium"/>
      </div>
      <div>
          <s:textfield key="member.phoneNumber" theme="xhtml" cssClass="text medium"/>
      </div>
    </div>
  </li>

  <s:textfield key="member.website" required="true" cssClass="text large"/>

  <li>
      <label class="desc"><fmt:message key="member.address.address"/></label>
      <div class="group">
          <div>
              <s:textfield key="member.address.address" theme="xhtml" cssClass="text large" labelposition="bottom"/>
          </div>
          <div class="left">
              <s:textfield key="member.address.city" theme="xhtml" required="true" cssClass="text medium" 
                  labelposition="bottom"/>
          </div>
          <div>
              <s:textfield key="member.address.province" theme="xhtml" required="true" cssClass="text state" 
                  labelposition="bottom"/>
          </div>
          <div class="left">
              <s:textfield key="member.address.postalCode" theme="xhtml" required="true" cssClass="text medium" 
                  labelposition="bottom"/>
          </div>
          <div>
              <s:set name="country" value="member.address.country" scope="page"/>
              <ots:country name="member.address.country" prompt="" default="${country}"/>
              <p>
                  <label for="member.address.country">
                      <fmt:message key="member.address.country"/> <span class="req">*</span>
                  </label>
              </p>
          </div>
      </div>
  </li>
<c:choose>
  <c:when test="${param.from == 'list'}">
  <li>
    <fieldset>
      <legend><fmt:message key="userProfile.accountSettings"/></legend>
      <s:checkbox key="member.enabled" id="member.enabled" fieldValue="true" theme="simple"/>
      <label for="member.enabled" class="choice"><fmt:message key="member.enabled"/></label>

      <s:checkbox key="member.accountExpired" id="member.accountExpired" fieldValue="true" theme="simple"/>
      <label for="member.accountExpired" class="choice"><fmt:message key="member.accountExpired"/></label>

      <s:checkbox key="member.accountLocked" id="member.accountLocked" fieldValue="true" theme="simple"/>
      <label for="member.accountLocked" class="choice"><fmt:message key="member.accountLocked"/></label>

      <s:checkbox key="member.credentialsExpired" id="member.credentialsExpired" fieldValue="true" theme="simple"/>
      <label for="member.credentialsExpired" class="choice"><fmt:message key="member.credentialsExpired"/></label>
    </fieldset>
  </li>
  <li>
    <fieldset>
      <legend><fmt:message key="userProfile.assignRoles"/></legend>
      <table class="pickList">
          <tr>
              <th class="pickLabel">
                  <label class="required"><fmt:message key="member.availableRoles"/></label>
              </th>
              <td></td>
              <th class="pickLabel">
                  <label class="required"><fmt:message key="member.roles"/></label>
              </th>
          </tr>
          <c:set var="leftList" value="${availableRoles}" scope="request"/>
          <s:set name="rightList" value="member.roleList" scope="request"/>
          <c:import url="/includes/pickList.jsp">
              <c:param name="listCount" value="1"/>
              <c:param name="leftId" value="availableRoles"/>
              <c:param name="rightId" value="userRoles"/>
          </c:import>
      </table>
    </fieldset>
  </li>
  </c:when>
  <c:otherwise>
  <li>
      <strong><fmt:message key="member.roles"/>:</strong>
      <s:iterator value="member.roleList" status="status">
        <s:property value="label"/><s:if test="!#status.last">,</s:if>
        <input type="hidden" name="userRoles" value="<s:property value="value"/>"/>
      </s:iterator>
      <s:hidden name="member.enabled" value="%{member.enabled}"/>
      <s:hidden name="member.accountExpired" value="%{member.accountExpired}"/>
      <s:hidden name="member.accountLocked" value="%{member.accountLocked}"/>
      <s:hidden name="member.credentialsExpired" value="%{member.credentialsExpired}"/>
  </li>
  </c:otherwise>
</c:choose>
  <li class="buttonBar bottom">
      <c:out value="${buttons}" escapeXml="false"/>
  </li>
</s:form>

<script type="text/javascript">
    Form.focusFirstElement(document.forms["userForm"]);
    highlightFormElements();

    function passwordChanged(passwordField) {
        if (passwordField.name == "member.password") {
            var origPassword = "<s:property value="member.password"/>";
        } else if (passwordField.name == "member.confirmPassword") {
            var origPassword = "<s:property value="member.confirmPassword"/>";
        }
        
        if (passwordField.value != origPassword) {
            createFormElement("input", "hidden",  "encryptPass", "encryptPass",
                              "true", passwordField.form);
        }
    }

<!-- This is here so we can exclude the selectAll call when roles is hidden -->
function onFormSubmit(theForm) {
<c:if test="${param.from == 'list'}">
    selectAll('userRoles');
</c:if>
}
</script>
