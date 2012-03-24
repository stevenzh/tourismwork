<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<title>我的积分</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:head/>
</head>
<body>
<table width="100%" height="100%" border="0" cellspacing="2">
  <tr>
    <td bgcolor="#99ccff"valign="top">
      <table align="center" border="0" cellpadding="0" cellspacing="1" width="100%">
          <tr>
          <td align="center" width="80">
            <a href="<s:url action='showMyPage' namespace='/accounts' includeParams='none' />">积分查询</a>&nbsp;&nbsp;|
            </td>
            <td align="center" width="80">
            <a href="<s:url action='showMyPage' namespace='/accounts' includeParams='none' />">积分奖励</a>&nbsp;&nbsp;|
            </td>
            <td align="center" width="80">
            <a href="<s:url action='showMyPage' namespace='/accounts' includeParams='none' />">增加积分</a>&nbsp;&nbsp;|
            </td>
            <td align="center">&nbsp;</td>
          </tr>
        </table>
    </td>
  </tr>
  <tr>
   <td bgcolor="#99ccff">
     <table align="center"  bgcolor="#ffffff" border="0" cellpadding="1" cellspacing="1" width="100%" height="340">
       <tr>
         <td>&nbsp;
         </td>
       </tr>
     </table>
   </td>
  </tr>
  </table>
</body>
</html>