<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	response.setHeader("Pragma", "no-cache");
  response.setHeader("Cache-Control", "no-cache");
  response.setDateHeader("Expires", 0);
%>

<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
 <meta charset="utf-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
 <title><decorator:title /> | <s:property value="#application['shop_title']" /></title>
 <!-- Tell the browser to be responsive to screen width -->
 <meta name="viewport" content="width=device-width, initial-scale=1">
 
 <!-- Font Awesome -->
 <link rel="stylesheet" href="../lib/fontawesome-free/css/all.min.css">
 <!-- Ionicons -->
 <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
 <!-- overlayScrollbars -->
 <link rel="stylesheet" href="../lib/adminlte/css/adminlte.min.css">
 
 <!-- jQuery -->
 <script src="../lib/jquery/jquery.min.js"></script>
 <!-- Bootstrap 4 -->
 <script src="../lib/bootstrap/js/bootstrap.bundle.min.js"></script>
 <!-- AdminLTE App -->
 <script src="../lib/adminlte/js/adminlte.min.js"></script>
 
 <script type="text/javascript" src="<s:url value='/scripts/main.js' encode='false' includeParams='none'/>"></script>
 <script language="JavaScript" src="/struts/utils.js" type="text/javascript"></script>
 <script type='text/javascript' src='<s:url value='/scripts/jquery/lib/jquery.ajaxQueue.js' encode='false' includeParams='none'/>'></script>
 <script type='text/javascript' src='<s:url value='/scripts/jquery/lib/thickbox-compressed.js' encode='false' includeParams='none'/>'></script>
 <script type='text/javascript' src='<s:url value='/scripts/jquery/autocomplete/jquery.autocomplete.js' encode='false' includeParams='none'/>'></script>
 
 <link rel="stylesheet" type="text/css" href="<s:url value='/scripts/jquery/autocomplete/jquery.autocomplete.css' encode='false' includeParams='none'/>" />
 <link rel="stylesheet" type="text/css" href="<s:url value='/scripts/jquery/lib/thickbox.css' encode='false' includeParams='none'/>" />
 <decorator:head />
</head>
<body <decorator:getProperty property="body.id" writeEntireProperty="true"/> <decorator:getProperty property="body.class" writeEntireProperty="true"/>>
 <!-- Site wrapper -->
 <div class="wrapper">

    <%@ include file="/includes/decorators/header.jsp"%>

    <%@ include file="/includes/decorators/menu.jsp"  %>


<%--     <c:set var="currentMenu" scope="request"> --%>
<%--      <decorator:getProperty property="meta.menu" /> --%>
<%--     </c:set> --%>
<%--     <c:if test="${currentMenu == 'AdminMenu'}"> --%>
<!--      <div id="sub"> -->
<!--       <menu:useMenuDisplayer name="Velocity" config="cssVerticalMenu.vm" permissions="rolesAdapter"> -->
<!--        <menu:displayMenu name="AdminMenu" /> -->
<!--       </menu:useMenuDisplayer> -->
<!--      </div> -->
<%--     </c:if> --%>
  
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
     <%@ include file="/includes/decorators/messages.jsp"%> 
    <decorator:body/>
  </div>
  <!-- /.content-wrapper -->
  
  <div id="footer" class="clearfix">
    <%@ include file="/includes/decorators/footer.jsp"%>
  </div>

  <s:url id="ajaxTest" value="/showWarnMessage.jspa" />
  <sj:dialog id="pane" title="任务列表" href="%{ajaxTest}" autoOpen="false" width="300" height="150" position="['right','bottom']">
  </sj:dialog>
</div>
<script type="text/javascript">
	var sh;
	sh = setInterval(showTask, 100000);
	function showTask() {
		$.ajax({
					type : "post",
					url : '<s:url value="/json/myTaskCount.jspa" encode="false" includeParams="none"/>',
					timeout : 20000,
					error : function() {
						// alert('服务器错误');
					},
					data : {
						userId : <s:property value="#session['EBIZ_USER'].userId" />
					},
					async : false,
					success : function(data) {
						$.each(data, function(i, n) {

							if (i == 'count') {
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
			var mycookie = document.cookie;
			function readcookie(name) {
				var start1 = mycookie.indexOf(name + "=");
				if (start1 != -1) {
					start = mycookie.indexOf("=", start1) + 1;
					var end = mycookie.indexOf(";", start);
					if (end == -1) {
						end = mycookie.length;
					}
					var value = unescape(mycookie.substring(start, end));
					if (value != null) {
						if (value == 'false')
							showLeftCol(false, false);
						else
							showLeftCol(true, false);
					}
				}
			}
			readcookie("showLeftCol");
		</script>

</body>
</html>
