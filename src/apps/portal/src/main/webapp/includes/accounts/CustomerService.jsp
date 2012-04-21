<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<title>客户服务</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:head/>
</head>
<body>
<table style="width: 100%" height="100%" border="0" cellspacing="2">
	<tr>
	 <td bgcolor="#99ccff">
	  <table align="center" border="0" style="width: 100%">
	    <tr>
	      <td align="center" width="80">
	      <a href="<s:url action='FeqCustomerServiceAction' namespace='/accounts' includeParams='none' />">常见问题</a>&nbsp;&nbsp;|
	      </td>
	      <td align="center" width="80">
	      <a href="<s:url action='NewPeopleCustomerServiceAction' namespace='/accounts' includeParams='none' />">新手上路</a>&nbsp;&nbsp;|
	      </td>
	      <td align="center" width="80">
	      <a href="<s:url action='AnswerCustomerServiceAction' namespace='/accounts' includeParams='none' />">在线咨询</a>&nbsp;&nbsp;|
	      </td>
	      <td align="center" width="80">&nbsp;</td>
	      <td align="center" width="80">&nbsp;</td>
	      <td align="center">&nbsp;</td>
	    </tr>
	  </table>
	 </td>
	</tr>
  <s:if test="viewSign==1||viewAllSign==1">
  <tr>
    <td bgcolor="#99ccff" valign="top">
    <table align="center" bgcolor="#ffffff"  style="width: 100%" height="100%"  border="0">
      <tr>
        <td height="25" bgcolor="#0099FF" background="<s:url value='/images/t003-4.gif' encode='false' includeParams='none'/>"><font color="#ffffff"><strong>&nbsp;常见问题</strong></font></td>
      </tr>
      <tr>
        <td>
          <table>
            <tr>
              <td>常见问题搜索</td>
              <td></td>
            </tr>
            </table> 
        </td>
      </tr>
    </table></td>
  </tr>
  </s:if>
	<s:if test="viewSign==2||viewAllSign==1">
	<tr>
	  <td bgcolor="#99ccff" valign="top">
	  <table align="center" bgcolor="#ffffff"  style="width: 100%" height="100%"  border="0">
	    <tr>
	      <td height="25" bgcolor="#0099FF" background="<s:url value='/images/t003-4.gif' encode='false' includeParams='none'/>"><font color="#ffffff"><strong>&nbsp;新手上路</strong></font></td>
	    </tr>
	    <tr>
	      <td>预订旅游</td>
	    </tr>
	  </table></td>
	</tr>
	</s:if>
  <s:if test="viewSign==3||viewAllSign==1">
  <tr>
    <td bgcolor="#99ccff" valign="top">
    <table align="center" bgcolor="#ffffff"  style="width: 100%" height="100%"  border="0">
      <tr>
        <td height="25" bgcolor="#0099FF" background="<s:url value='/images/t003-4.gif' encode='false' includeParams='none'/>"><font color="#ffffff"><strong>&nbsp;在线咨询</strong></font></td>
      </tr>
      <tr>
        <td>在线咨询</td>
      </tr>
    </table></td>
  </tr>
  </s:if>
</table>
</body>
</html>

