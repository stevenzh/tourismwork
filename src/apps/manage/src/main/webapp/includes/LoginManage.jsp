<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<HTML>
<HEAD>
<TITLE><s:text name="main.title" /></TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<s:url value='/stylesheet/simple/global.css' encode='false' includeParams='none'/>" type="text/css" media="all">
<link rel="stylesheet" href="<s:url value='/stylesheet/layout.css' encode='false' includeParams='none'/>" type="text/css" media="all">
<script type="text/javascript" src="<s:url value='/scripts/main.js' encode='false' includeParams='none'/>"></script>
</HEAD>

<BODY>

<TABLE width="100%" border="0">
  <TR>
    <TD align="center">
      <img src="<s:url value='/images/tourismwork_logo.png' encode='false' includeParams='none'/>"></TD>
  </TR>
  <TR>
    <TD>&nbsp;</TD>
  </TR>
  <TR>
    <TD align="center">
    <form action="j_spring_security_check" method="POST">
    <TABLE width="48%" border="0">

      <TR>
        <TD colSpan="2" class="header">用户登录</TD>
      </TR>
      <TR>
        <TD colSpan="2">&nbsp;</TD>
      </TR>
      <TR>
        <TD width="18%">用户名&nbsp;&nbsp;：</TD>
        <TD width="82%"><input type='text' name='j_username'>
        </TD>
      </TR>
      <TR>
        <TD>密&nbsp;&nbsp;&nbsp;码：</TD>
        <TD><input type='password' name='j_password'></TD>
      </TR>
      <!-- 
      <tr>
         <td><img src="/captcha-image.html" /></td>
         <td><input type="text" name="j_captcha_response"/></td>
      </tr>
      <tr>
        <td><input type="checkbox" name="_acegi_security_remember_me"></td>
        <td>Don't ask for my password for two weeks</td>
      </tr>
       -->
      <TR>
        <TD colSpan="2" align="center">
        <table style="display: inline" border="1">
          <tr>
            <th>User Name</th>
            <th>Password</th>
            <th>Description</th>
          </tr>
          <tr>
            <td>admin</td>
            <td>opentravel</td>
            <td>Administrator</td>
          </tr>
          <tr>
            <td>mike</td>
            <td>opentravel</td>
            <td>Operator</td>
          </tr>
          <tr>
            <td>jude</td>
            <td>opentravel</td>
            <td>Sales</td>
          </tr>
          <tr>
            <td>jacky</td>
            <td>opentravel</td>
            <td>Product</td>
          </tr>
        </table>
        <br />
        <INPUT type="submit" value="登录"> &nbsp;
        <INPUT onclick="javascript:window.close();" type="button" value="取消">
        </TD>
      </TR>
    </TABLE>
    </form>
    </TD>
  </TR>
</TABLE>

<div align="center">版权所有：<FONT face="Impact">opentravelmsoft.com</FONT></div>

<script src="http://www.google-analytics.com/urchin.js" type="text/javascript"></script><script type="text/javascript">
var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
</script><script type="text/javascript">
try {
var pageTracker = _gat._getTracker("UA-10121320-3");
pageTracker._trackPageview();
} catch(err) {}</script>

</BODY>
</HTML>
