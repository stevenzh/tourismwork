<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  response.setHeader("Pragma", "no-cache");
  response.setHeader("Cache-Control", "no-cache");
  response.setDateHeader("Expires", 0);
%>

<%@ taglib uri="sitemesh-decorator" prefix="decorator"%>
<%@ taglib uri="sitemesh-page" prefix="page"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><decorator:title/> | <s:property value="#application['shop_title']" /></title>
<%@ include file="/includes/decorators/stylesheettag.jsp" %>
<script type="text/javascript" src="<s:url value='/scripts/main.js' encode='false' includeParams='none'/>"></script>
<sj:head compressed="false" jquerytheme="cupertino" jqueryui="true" locale="zh-CN" ajaxhistory="true"/>

<script language="JavaScript" src="/struts/utils.js" type="text/javascript"></script>
<script type='text/javascript' src='<s:url value='/scripts/jquery/lib/jquery.ajaxQueue.js' encode='false' includeParams='none'/>'></script>
<script type='text/javascript' src='<s:url value='/scripts/jquery/lib/thickbox-compressed.js' encode='false' includeParams='none'/>'></script>
<script type='text/javascript' src='<s:url value='/scripts/jquery/autocomplete/jquery.autocomplete.js' encode='false' includeParams='none'/>'></script>

<link rel="stylesheet" type="text/css" href="<s:url value='/scripts/jquery/autocomplete/jquery.autocomplete.css' encode='false' includeParams='none'/>" />
<link rel="stylesheet" type="text/css" href="<s:url value='/scripts/jquery/lib/thickbox.css' encode='false' includeParams='none'/>" />
<link rel="stylesheet" type="text/css" href="/scripts/jquery-easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="/scripts/jquery-easyui/themes/icon.css">
<script type="text/javascript" src="/scripts/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">

var sh;
sh = setInterval(showTask,100000);
function showTask()
{
  $.ajax({
      type: "post",
      url: '<s:url value="/json/myTaskCount.jspa" encode="false" includeParams="none"/>',
      timeout: 20000,
      error: function(){
          // alert('服务器错误');
      },
      data: {userId:<s:property value="#session['EBIZ_USER'].userId" />},
      async: false,
      success: function(data){
          $.each(data, function(i, n){

          if (i =='count')
          {
            if (n > '0')
                $('#pane').dialog('open');
              else 
               $('#pane').dialog('close');
              //clearInterval(sh);
          }
      });
      }
  });
}

</script>
<script type="text/javascript">
function showLeftCol(bShow, bSetCookie)
{
	var fldLeftSidebar = document.getElementById('tdShortcuts');
	var fldShowHandle  = document.getElementById('imgShowHandle');
	var fldHideHandle  = document.getElementById('imgHideHandle');
	if ( bShow )
	{
		if ( fldLeftSidebar != null ) fldLeftSidebar.style.display = 'inline';
		if ( fldShowHandle  != null ) fldShowHandle.style.display  = 'none'  ;
		if ( fldHideHandle  != null ) fldHideHandle.style.display  = 'inline';
		if ( bSetCookie )
			document.cookie = 'showLeftCol=true;path=/';
	}
	else
	{
		if ( fldLeftSidebar != null ) fldLeftSidebar.style.display = 'none'  ;
		if ( fldShowHandle  != null ) fldShowHandle.style.display  = 'inline';
		if ( fldHideHandle  != null ) fldHideHandle.style.display  = 'none'  ;
		if ( bSetCookie )
			document.cookie = 'showLeftCol=false;path=/';
	}
}
</script>

<decorator:head/>
</head>

<body<decorator:getProperty property="body.id" writeEntireProperty="true"/><decorator:getProperty property="body.class" writeEntireProperty="true"/>>
<div id="formResult" class="result">

<div id="page">
	<div id="header" class="clearfix">
	<%@ include file="/includes/decorators/header.jsp" %>
	</div>

	<div id="content" class="clearfix">
	  <jsp:include page="/includes/decorators/menu.jsp"/>
    <div id="main">
		<table border="0" style="width: 100%">
		  <tr>
		    <td valign="top" id="tdShortcuts" width="8%">
        <s:action name="showShortcut" namespace="/" executeResult="true"  />
		    </td>
		    <td style="width: 10px; vertical-align: top;">
		      <img id="imgShowHandle" alt="显示快捷菜单" src="<s:url value="/images/show.gif" />" style="display: none;" onclick="showLeftCol(true, true);">
		      <img id="imgHideHandle" alt="隐藏快捷菜单" src="<s:url value="/images/hide.gif" />" style="display: inline;" onclick="showLeftCol(false, true);">
		    </td>
		    <td valign="top">
		      <%@ include file="/includes/decorators/messages.jsp" %>
          <span class="header"><decorator:getProperty property="meta.heading"/></span>
		      <decorator:body />
		    </td>
		  </tr>
		</table>
		</div>

    <c:set var="currentMenu" scope="request"><decorator:getProperty property="meta.menu"/></c:set>
    <c:if test="${currentMenu == 'AdminMenu'}">
    <div id="sub">
        <menu:useMenuDisplayer name="Velocity" config="cssVerticalMenu.vm" permissions="rolesAdapter">
            <menu:displayMenu name="AdminMenu"/>
        </menu:useMenuDisplayer>
    </div>
    </c:if>	
	</div>

  <div id="footer" class="clearfix">
	  <%@ include file="/includes/decorators/footer.jsp" %>
	</div>
</div>

<s:url id="ajaxTest" value="/showWarnMessage.jspa" />
<sj:dialog id="pane" title="任务列表" href="%{ajaxTest}" autoOpen="false"
           width="300" height="150" position="['right','bottom']">
</sj:dialog>
<script type="text/javascript">
var mycookie = document.cookie;
function readcookie(name)
{
	var start1 = mycookie.indexOf(name + "=");
	if (start1 != -1)	{
		start=mycookie.indexOf("=",start1)+1;
		var end = mycookie.indexOf(";",start);
		if (end==-1) { end=mycookie.length;}
		var value=unescape(mycookie.substring(start,end));
		if (value!=null) {
		  if (value=='false') showLeftCol(false, false);
		  else showLeftCol(true,false);
		}
	}
}
readcookie("showLeftCol");
</script>
</div>
</body>
</html>
